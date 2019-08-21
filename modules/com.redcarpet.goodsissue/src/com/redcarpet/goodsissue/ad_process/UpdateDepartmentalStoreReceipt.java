package com.redcarpet.goodsissue.ad_process;

import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.ProcessStatus;
import com.redcarpet.goodsissue.data.RCGIDepartmentReceipt;
import com.redcarpet.goodsissue.data.RCGIDrLines;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.materialmgmt.cost.CostingAlgorithm;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import java.util.Date;

public class UpdateDepartmentalStoreReceipt   extends DalBaseProcess implements BundleProcess{
    public static final Logger logger = Logger.getLogger(UpdateDepartmentalStoreReceipt.class);
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        OBError err = new OBError();
        ProcessStatus processStatus = new ProcessStatus(bundle, DalBaseProcess.PROCESSING);
        if (processStatus.isProcessing()){
            logger.info("In Second Condtion Already this process is in Running state ");
            //throw new SerialException("Already this Process is Running...!");
            err.setMessage(ProcessStatus.PREVIOUS_PROCESS_RUNNING);
            err.setTitle("Error");
            err.setType("Error");
            //bundle.setResult(err);
            bundle.setResult(err);
        } else {
            doIt(bundle);
        }

    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rcgi_Drlines_ID").toString();
        OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Success");
        msg.setMessage("Process completed successfully");
        RCGIDrLines receiptLines = OBDal.getInstance().get(RCGIDrLines.class, id);

        try {
            OBContext.setAdminMode();
            updateTransaction(receiptLines);
            //insertIntoFIFOTransactions(receipt);
            //receiptLines.setProcess(Boolean.TRUE);
            //receipt.setProcessed(Boolean.TRUE);
            //receipt.setDocumentStatus("CO");
            //receipt.setTotalcost(receipt.getTotallineamount().add(receipt.getFreightcost()));
            OBDal.getInstance().commitAndClose();
        } catch (Exception e) {
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(e.getMessage());
            e.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }
        bundle.setResult(msg);
    }
    public  void updateTransaction(RCGIDrLines receiptLines){
        //MaterialTransaction materialTransaction =
        OBCriteria<MaterialTransaction> materialTransactionOBCriteria = OBDal.getInstance().createCriteria(MaterialTransaction.class);
        materialTransactionOBCriteria.add(Restrictions.eq(MaterialTransaction.PROPERTY_PRODUCT,receiptLines.getProduct()));
        materialTransactionOBCriteria.add(Restrictions.eq(MaterialTransaction.PROPERTY_RCGIDRLINES,receiptLines));
        for (MaterialTransaction materialTransaction : materialTransactionOBCriteria.list()){

            //materialTransaction.setProduct(line.getProduct());
            //trx.setUOM(line.getProduct().getUOM());
            //trx.setOrganization(line.getOrganization());
            //trx.setStorageBin(line.getStorageBin());
            materialTransaction.setOrderQuantity(receiptLines.getOrderedQuantity());
            materialTransaction.setMovementQuantity(receiptLines.getMovementQuantity());
            if (receiptLines.getOrderedQuantity().doubleValue() <= 0) {
                throw new NullPointerException("Invalid Ordered Quantity ");
            }
            //trx.setMovementDate(receiptLines.getRcgiDepartmentreceipt().getMovementDate());
            //trx.setTransactionProcessDate(new Date());
            //trx.setMovementType("P+");
            //trx.setCostCalculated(Boolean.TRUE);
            //trx.setCostingStatus("CC");
            //trx.setCurrency(trx.getOrganization().getCurrency());
            //trx.setCostingAlgorithm(OBDal.getInstance().get(CostingAlgorithm.class, "B069080A0AE149A79CF1FA0E24F16AB6"));
            //trx.setRCGIDrLines(line);
            materialTransaction.setTransactionCost(receiptLines.getLineNetAmount());
            //  trx.setInventoryuom(line.getInventoryuom());
            //OBDal.getInstance().save(trx);
        }
    }
}
