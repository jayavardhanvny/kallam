package com.redcarpet.production.ad_process;

import com.redcarpet.production.data.RCPR_ProductionRun;
import java.math.BigDecimal;
import java.util.Date;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.materialmgmt.cost.CostingAlgorithm;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
 *
 * @author Mateen
 */
public class RCPR_Finalize_ProductionIssue extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

        String id = bundle.getParams().get("Rcpr_Productionrun_ID").toString();
        RCPR_ProductionRun prun = OBDal.getInstance().get(RCPR_ProductionRun.class, id);
        OBError success = new OBError();
        success.setType("Success");
        success.setTitle("Success");
        success.setMessage("Process Completed Successfully");
        try {
            doIt(prun);
            prun.setProductionIssue(Boolean.TRUE);
            prun.setProductionReceipt(Boolean.FALSE);
        } catch (Exception e) {
            success.setType("Error");
            success.setTitle("Error");
            success.setMessage(e.getMessage());
        }
        bundle.setResult(success);
    }

    public void doIt(RCPR_ProductionRun run) throws Exception {
        if (ProductionProcessUtils.isNegativeQuantityAllowed(run)) {
            insertInventory(run);
        } else {
            throw new Exception("Insufficient Stock");
        }

    }

    private MaterialTransaction insertInventory(RCPR_ProductionRun run) {
        MaterialTransaction trx = OBProvider.getInstance().get(MaterialTransaction.class);
        trx.setProduct(run.getProduct());
        trx.setUOM(run.getProduct().getUOM());
        trx.setOrganization(run.getOrganization());
        trx.setStorageBin(run.getStorageBin());
        trx.setMovementQuantity(run.getIssueQuantity().multiply(new BigDecimal(-1)));
        trx.setMovementDate(new Date());
        trx.setTransactionProcessDate(new Date());
        trx.setMovementType("P-");
        trx.setCostCalculated(Boolean.TRUE);
        trx.setCostingStatus("CC");
        trx.setCurrency(trx.getOrganization().getCurrency());
        trx.setTransactionCost(run.getItemCost().multiply(run.getIssueQuantity()));
        trx.setCostingAlgorithm(OBDal.getInstance().get(CostingAlgorithm.class, "B069080A0AE149A79CF1FA0E24F16AB6"));
        trx.setRcprProductionrun(run);
        OBDal.getInstance().save(trx);
        return trx;
    }

    
}
