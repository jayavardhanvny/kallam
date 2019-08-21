package com.redcarpet.lotmanagement.ad_process;

import com.redcarpet.lotmanagement.data.RCLO_LotIssue;
import com.redcarpet.lotmanagement.data.RCLO_LotIssueLine;
import com.redcarpet.lotmanagement.data.RCLO_LotReceipt;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
 *
 * @author mateen
 */
public class RCLO_IssueLots extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

        String id = bundle.getParams().get("Rclo_Lotissue_ID").toString();
        OBError error = new OBError();
        error.setType("Success");
        error.setTitle("Success");
        error.setMessage("Process completed successfully");

        try {
            validate(id);
            doIt(id);
        } catch (Exception ex) {
            error.setType("Error");
            error.setTitle("Error");
            error.setMessage(ex.getMessage());
        }
        bundle.setResult(error);

    }

    private void doIt(String id) {
        OBContext.setAdminMode(true);
        RCLO_LotIssue lot = OBDal.getInstance().get(RCLO_LotIssue.class, id);
        for (RCLO_LotIssueLine line : lot.getRCLOLotIssueLineList()) {
            RCLO_LotReceipt receipt = line.getLotReceipt();
            receipt.setOpenBales(line.getAvailableLots());
            receipt.setOpenQuantity(receipt.getOpenQuantity().subtract(line.getIssuedKgs()));
            if(line.getAvailableLots().doubleValue()<=0){
                receipt.setProcessed(Boolean.TRUE);
            }
        }
        lot.setProcessed(Boolean.TRUE);
        OBContext.setAdminMode(false);
    }

    private boolean validate(String id) throws Exception {

        boolean isValidated = true;
        RCLO_LotIssue lot = OBDal.getInstance().get(RCLO_LotIssue.class, id);
        for (RCLO_LotIssueLine line : lot.getRCLOLotIssueLineList()) {
            if(line.getAvailableLots().doubleValue()<0){
                isValidated = false;
                throw new Exception("Line No "+line.getLineNo()+" no of bales entered is greater then available lots");
            }
        }
        return isValidated;
    }
}
