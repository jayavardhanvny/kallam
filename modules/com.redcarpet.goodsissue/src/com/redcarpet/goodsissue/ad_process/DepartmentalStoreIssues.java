package com.redcarpet.goodsissue.ad_process;

import com.rcss.humanresource.data.RCHR_Jobcard;
import com.redcarpet.goodsissue.data.*;
import com.redcarpet.goodsissue.util.RCGISelectPriceData;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.materialmgmt.cost.CostingAlgorithm;
import org.openbravo.model.materialmgmt.cost.TransactionCost;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

import javax.servlet.ServletException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DepartmentalStoreIssues extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rcgi_Departmentissue_ID").toString();
        System.out.println("mid.."+id);
        OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Success");
        msg.setMessage("Process completed successfully");
        RCGIDepartmentIssue issue = OBDal.getInstance().get(RCGIDepartmentIssue.class, id);
        if (issue.getRCGIDiLinesList().size() <= 0) {
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage("Document cannot be booked because it has no lines");
        }else if(checkValidation(issue)){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage("Total Store Amount exceeds total eligibilty amount");
        }
        else {
            try {
                OBContext.setAdminMode();
                doIt(issue);
                System.out.println("mid.." + id);
                updateFifoTransactions(issue);
                updateTotalAmountToRoundOff(issue);
                distributeAmountToFamily(issue);
                issue.setProcessed(Boolean.TRUE);
                OBDal.getInstance().commitAndClose();
                OBContext.restorePreviousMode();
            } catch (Exception e) {
                msg.setType("Error");
                msg.setTitle("Error");
                e.printStackTrace();
                msg.setMessage(e.getMessage());
                OBDal.getInstance().rollbackAndClose();
            }
        }
        bundle.setResult(msg);
    }

    private Boolean checkValidation(RCGIDepartmentIssue issue){
        double lineTotalamt=0;
        for(RCGIDiLines line:issue.getRCGIDiLinesList()){
            lineTotalamt=lineTotalamt+line.getLineNetAmount().doubleValue();
        }
        return lineTotalamt>issue.getTotaleligibityamount().doubleValue()?Boolean.TRUE:Boolean.FALSE;
    }

    private void updateFifoTransactions(RCGIDepartmentIssue issue){


        for(RCGIDiLines line:issue.getRCGIDiLinesList()){
            BigDecimal currentOrderQty=line.getOrderedQuantity();
            BigDecimal currentInventoryQty=line.getMovementQuantity();
            while(currentOrderQty.doubleValue()!=0){
                for(RCGITransactions t:getFIFOLines(line.getProduct().getId(),line.getOrganization().getId())){
                    System.out.println("under"+currentOrderQty.doubleValue());
                    if(t.getOpenQuantity().doubleValue()<=currentOrderQty.doubleValue()){
                       /* if(line.isFrompacking()) {
                            if((line.getMovementQuantity().doubleValue()-line.getOrderedQuantity().doubleValue())%getConversionRate(line.getProduct().getId())==0)

                        }*/
                        System.out.println("if"+currentOrderQty.doubleValue());
                        createDIFifoLines(line,t,currentOrderQty,currentInventoryQty);
                        currentOrderQty=currentOrderQty.subtract(t.getOpenQuantity());
                        currentInventoryQty=currentInventoryQty.subtract(line.getMovementQuantity());
                        t.setConsumedqty(t.getConsumedqty().add(t.getOpenQuantity()));
                        t.setConsumedinventoruqty(t.getConsumedinventoruqty().add(currentInventoryQty));
                        t.setOpenQuantity(new BigDecimal(0));
                        t.setOpeninventoruqty(new BigDecimal(0));

                    }
                    else{
                        System.out.println("else"+currentOrderQty.doubleValue());
                        createDIFifoLines(line,t,currentOrderQty,currentInventoryQty);
                        t.setConsumedqty(t.getConsumedqty().add(currentOrderQty));
                        t.setConsumedinventoruqty(t.getConsumedinventoruqty().add(currentInventoryQty));
                        t.setOpenQuantity(t.getOpenQuantity().subtract(currentOrderQty));
                        t.setOpeninventoruqty(t.getOpeninventoruqty().subtract(currentInventoryQty));
                        currentOrderQty=new BigDecimal(0);
                        currentInventoryQty=new BigDecimal(0);
                      //  createDIFifoLines(line,t,currentOrderQty,currentInventoryQty);
                    }
                }
            }
        }
    }

    private long getConversionRate(String productId){
        String hql=" SELECT COALESCE(r.conversionRate,0) FROM RCGI_ProductConversion r WHERE r.product.id='"+productId+"' LIMIT 1";
        return OBDal.getInstance().getSession().createQuery(hql).list().size()>0?Long.valueOf(OBDal.getInstance().getSession().createQuery(hql).list().get(0).toString()):0;
    }

    /*private void updateFifoTransactions(RCGIDepartmentIssue issue){


        for(RCGIDiLines line:issue.getRCGIDiLinesList()){
            BigDecimal currentOrderQty=line.getOrderedQuantity();
            BigDecimal currentInventoryQty=line.getMovementQuantity();

            while(currentOrderQty.doubleValue()!=0){
            for(RCGITransactions t:getFIFOLines(line.getProduct().getId(),line.getOrganization().getId())){
                        System.out.println("under"+currentOrderQty.doubleValue());
                    if(t.getOpenQuantity().doubleValue()<=currentOrderQty.doubleValue()){
                        System.out.println("if"+currentOrderQty.doubleValue());
                        createDIFifoLines(line,t,currentOrderQty,currentInventoryQty);
                        currentOrderQty=currentOrderQty.subtract(t.getOpenQuantity());
                        currentInventoryQty=currentInventoryQty.subtract(line.getMovementQuantity());
                        t.setConsumedqty(t.getConsumedqty().add(t.getOpenQuantity()));
                        t.setConsumedinventoruqty(t.getConsumedinventoruqty().add(currentInventoryQty));
                        t.setOpenQuantity(new BigDecimal(0));
                        t.setOpeninventoruqty(new BigDecimal(0));

                    }
                    else{
                        System.out.println("else"+currentOrderQty.doubleValue());
                        createDIFifoLines(line,t,currentOrderQty,currentInventoryQty);
                        t.setConsumedqty(t.getConsumedqty().add(currentOrderQty));
                        t.setConsumedinventoruqty(t.getConsumedinventoruqty().add(currentInventoryQty));
                        t.setOpenQuantity(t.getOpenQuantity().subtract(currentOrderQty));
                        t.setOpeninventoruqty(t.getOpeninventoruqty().subtract(currentInventoryQty));
                        currentOrderQty=new BigDecimal(0);
                        currentInventoryQty=new BigDecimal(0);
                        createDIFifoLines(line,t,currentOrderQty,currentInventoryQty);
                    }
                    }
            }
        }
    }*/

    private void updateTotalAmountToRoundOff(RCGIDepartmentIssue issue){
        double totalAmount=0;
        for(RCGIDiLines line:issue.getRCGIDiLinesList()){
            totalAmount=totalAmount+line.getLineNetAmount().doubleValue();
        }
        issue.setSummedLineAmount(new BigDecimal(totalAmount).setScale(0,BigDecimal.ROUND_CEILING));
        issue.setPayableamount(issue.getSummedLineAmount().subtract(issue.getCashreceived()));
    }

    private void distributeAmountToFamily(RCGIDepartmentIssue issue){
        long cnt=0;
        double totalAmount=issue.getPayableamount().doubleValue();
        double sharingAmount=0,sharedAmount=0;
        for(RCGIDiFamilyList line:issue.getRCGIDiFamilyListList()){
            if(cnt==issue.getRCGIDiFamilyListList().size()-1){
                sharingAmount=totalAmount-sharedAmount;
                line.setStoreamount(new BigDecimal(sharingAmount));
            }else{
                sharingAmount=Math.round(((totalAmount/getTotalPresents(issue))*line.getPresents().doubleValue()));
                sharedAmount=sharedAmount+sharingAmount;
                line.setStoreamount(new BigDecimal(sharingAmount));
            }
        }
    }

    private long getTotalPresents(RCGIDepartmentIssue issue){
        String hql=" SELECT SUM(line.presents) FROM RCGI_DiFamilyList line WHERE line.rcgiDepartmentissue.id='"+issue.getId()+"'";
        List<Long> li=OBDal.getInstance().getSession().createQuery(hql).list();
        return li.get(0);
    }

    private void createDIFifoLines(RCGIDiLines line,RCGITransactions t,BigDecimal currentQty,BigDecimal currentInventoryQty){
            RCGIDiFifoLines fifoLine=OBProvider.getInstance().get(RCGIDiFifoLines.class);
            fifoLine.setOrganization(line.getOrganization());
            fifoLine.setClient(line.getClient());
            fifoLine.setRcgiDilines(line);
            fifoLine.setRcgiTransactions(t);
            fifoLine.setProduct(line.getProduct());
            fifoLine.setRcgiDepartmentissue(line.getRcgiDepartmentissue());
            fifoLine.setOrderedQuantity(currentQty);
            fifoLine.setMovementQuantity(currentInventoryQty);
            OBDal.getInstance().save(fifoLine);
            OBDal.getInstance().flush();
    }

    private List<RCGITransactions> getFIFOLines(String productId,String orgId){
        String hql=" SELECT t FROM RCGI_Transactions t WHERE t.product.id='"+productId+"' AND t.organization.id='"+orgId+"' AND t.openQuantity>0 ORDER BY t.movementDate ASC";
        System.out.println("size...."+OBDal.getInstance().getSession().createQuery(hql).list().size());
        return OBDal.getInstance().getSession().createQuery(hql).list();
    }

    private void doIt(RCGIDepartmentIssue issue) throws ServletException {
        for (RCGIDiLines line : issue.getRCGIDiLinesList()) {
            MaterialTransaction trx = OBProvider.getInstance().get(MaterialTransaction.class);
            trx.setProduct(line.getProduct());
            trx.setUOM(line.getProduct().getUOM());
            trx.setOrganization(line.getOrganization());
            trx.setStorageBin(line.getStorageBin());
            BigDecimal orderqty=line.getOrderedQuantity();
            BigDecimal negorderqty=orderqty.negate();
            trx.setMovementQuantity(line.getMovementQuantity().negate());
            trx.setOrderQuantity(line.getOrderedQuantity().negate());
           // trx.setInventoryuom(line.getInventoryuom());
            if (line.getOrderedQuantity().doubleValue() <= 0) {
                throw new NullPointerException("Invalid Ordered Quantity ");
            }
            String qtyInHand = RCGISelectPriceData.getQuantityInHand(new DalConnectionProvider(), line.getProduct().getId(), line.getStorageBin().getId());
            if (!issue.getClient().getClientInformationList().get(0).isAllowNegativeStock()) {
                if (new BigDecimal(qtyInHand).doubleValue() <= 0) {
                    throw new NullPointerException("Insufficient Stock: " + line.getProduct().getName());
                }
            }
            trx.setMovementDate(line.getRcgiDepartmentissue().getMovementDate());
            trx.setTransactionProcessDate(new Date());
            trx.setMovementType("P-");
            trx.setCostCalculated(Boolean.TRUE);
            trx.setCostingStatus("CC");
            trx.setCurrency(trx.getOrganization().getCurrency());
            trx.setCostingAlgorithm(OBDal.getInstance().get(CostingAlgorithm.class, "B069080A0AE149A79CF1FA0E24F16AB6"));
            trx.setRCGIDiLines(line);
            trx.setTransactionCost(line.getLineNetAmount());
            OBDal.getInstance().save(trx);
            insertTransactionCostLine(trx);
            line.setProcessed(Boolean.TRUE);
        }
        issue.setProcess(Boolean.TRUE);
        issue.setProcessed(Boolean.TRUE);
        issue.setDocumentStatus("CO");

    }

    private void insertTransactionCostLine(MaterialTransaction trx) {
        TransactionCost trxCost = OBProvider.getInstance().get(TransactionCost.class);
        trxCost.setInventoryTransaction(trx);
        trxCost.setCost(trx.getTransactionCost());
        trxCost.setCostDate(trx.getMovementDate());
        trxCost.setCurrency(trx.getCurrency());
        OBDal.getInstance().save(trxCost);
    }
}
