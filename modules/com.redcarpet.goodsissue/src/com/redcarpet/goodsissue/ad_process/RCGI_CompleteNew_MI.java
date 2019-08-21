package com.redcarpet.goodsissue.ad_process;

import java.util.Date;

import com.rcss.humanresource.data.RCHR_Jobcard;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.materialmgmt.cost.CostingAlgorithm;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import com.redcarpet.goodsissue.data.RCGI_MaterialIssue;
import com.redcarpet.goodsissue.data.RCGI_MiLines;
import com.redcarpet.goodsissue.util.RCGISelectPriceData;
import org.openbravo.dal.core.OBContext;
import org.openbravo.model.materialmgmt.cost.TransactionCost;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author Suneetha
 */
public class RCGI_CompleteNew_MI extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rcgi_Materialissue_ID").toString();
        System.out.println("mid.."+id);
        OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Success");
        msg.setMessage("Process completed successfully");
        RCGI_MaterialIssue issue = OBDal.getInstance().get(RCGI_MaterialIssue.class, id);
        if (issue.getRCGIMiLinesList().size() <= 0) {
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage("Document cannot be booked because it has no lines");
        }
        try {
             OBContext.setAdminMode();
            doIt(issue);
            System.out.println("mid.."+id);
            updateFifiTransactions(issue);
            updateJobCard(issue);
            OBDal.getInstance().commitAndClose();
            OBContext.restorePreviousMode();
        } catch (Exception e) {
            msg.setType("Error");
            msg.setTitle("Error");
            e.printStackTrace();
            msg.setMessage(e.getMessage());
            OBDal.getInstance().rollbackAndClose();
        }

        bundle.setResult(msg);
    }
    
private void updateJobCard(RCGI_MaterialIssue issue){
        BigDecimal weftissued=new BigDecimal(0);
        BigDecimal warpissued=new BigDecimal(0);
        RCHR_Jobcard job=issue.getRcgiMaterialindentlines().getRchrJobcard();
        if(issue.getRcgiMaterialindentlines().getIndenttype().equals("Warp")){
            for(RCGI_MiLines line:issue.getRCGIMiLinesList()){
                    warpissued=warpissued.add(line.getOrderedQuantity());
            }
            job.setWarpissued(job.getWarpissued().add(warpissued));
            job.setWarpremainingqty(job.getWarpremainingqty().subtract(warpissued));

        }else{
            for(RCGI_MiLines line:issue.getRCGIMiLinesList()){
                weftissued=weftissued.add(line.getOrderedQuantity());
            }
            job.setWeftissued(job.getWeftissued().add(weftissued));
            job.setWeftremainingqty(job.getWeftremainingqty().subtract(weftissued));
        }
    }

    private void updateFifiTransactions(RCGI_MaterialIssue issue){
        for(RCGI_MiLines line:issue.getRCGIMiLinesList()){
            line.getRcgiTransactions().setConsumedqty(line.getRcgiTransactions().getConsumedqty().add(line.getOrderedQuantity()));
            line.getRcgiTransactions().setOpenQuantity(line.getRcgiTransactions().getOpenQuantity().subtract(line.getOrderedQuantity()));
            line.getRcgiTransactions().setConsumedcones(line.getRcgiTransactions().getConsumedcones().add(line.getIssedcones()));
            line.getRcgiTransactions().setOpencones(line.getRcgiTransactions().getNoofcones().subtract(line.getIssedcones()));
            if(issue.getRcgiMaterialindentlines().getIndenttype().equals("Warp"))
                line.getRcgiTransactions().setWarpissued(line.getRcgiTransactions().getWarpissued().add(line.getOrderedQuantity()));
                else
                line.getRcgiTransactions().setWeftissued(line.getRcgiTransactions().getWeftissued().add(line.getOrderedQuantity()));
        }
    }

    private void doIt(RCGI_MaterialIssue issue) throws ServletException {
        for (RCGI_MiLines line : issue.getRCGIMiLinesList()) {
            MaterialTransaction trx = OBProvider.getInstance().get(MaterialTransaction.class);
            trx.setProduct(line.getProduct());
            trx.setUOM(line.getProduct().getUOM());
            trx.setOrganization(line.getOrganization());
            trx.setStorageBin(line.getStorageBin());
            BigDecimal orderqty=line.getOrderedQuantity();
            BigDecimal negorderqty=orderqty.negate();
            trx.setMovementQuantity(negorderqty);

            if (line.getOrderedQuantity().doubleValue() <= 0) {
                throw new NullPointerException("Invalid Ordered Quantity ");
            }
            String qtyInHand = RCGISelectPriceData.getQuantityInHand(new DalConnectionProvider(), line.getProduct().getId(), line.getStorageBin().getId());
            if (!issue.getClient().getClientInformationList().get(0).isAllowNegativeStock()) {
                if (new BigDecimal(qtyInHand).doubleValue() <= 0) {
                    throw new NullPointerException("Insufficient Stock: " + line.getProduct().getName());
                }
            }
            trx.setMovementDate(line.getMaterialIssue().getMovementDate());
            trx.setTransactionProcessDate(new Date());
            trx.setMovementType("P-");
            trx.setCostCalculated(Boolean.TRUE);
            trx.setCostingStatus("CC");
            trx.setCurrency(trx.getOrganization().getCurrency());
            trx.setCostingAlgorithm(OBDal.getInstance().get(CostingAlgorithm.class, "B069080A0AE149A79CF1FA0E24F16AB6"));
            trx.setRcgiMaterialissue(line.getMaterialIssue());
            trx.setRcgiMilines(line);
            trx.setRchrWarpyarntype(line.getRchrWarpyarntype());
            trx.setEpcgVariant(line.getEpcgVariant());
            trx.setTransactionCost(line.getLineNetAmount());
            OBDal.getInstance().save(trx);
            insertTransactionCostLine(trx);
            line.setProcessed(Boolean.TRUE);
        }
        issue.setProcess(Boolean.TRUE);
        issue.setProcessed(Boolean.TRUE);
		issue.setDocumentStatus("IS");

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
