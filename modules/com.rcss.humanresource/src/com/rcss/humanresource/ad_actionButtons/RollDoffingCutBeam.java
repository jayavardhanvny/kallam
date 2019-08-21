package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.RchrLoomsdata;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.RchrConstantType;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class RollDoffingCutBeam extends DalBaseProcess implements BundleProcess{
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        OBError msg = new OBError();
        try {
            doIt(bundle);
            msg.setType("Success");
            msg.setTitle("Done");
            msg.setMessage("Success");
            bundle.setResult(msg);
            OBDal.getInstance().commitAndClose();
        }catch (Exception e){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage("Error is : "+e.getMessage());
            bundle.setResult(msg);
            OBDal.getInstance().rollbackAndClose();
        }finally {
            OBDal.getInstance().rollbackAndClose();
            //OBContext.restorePreviousMode();
        }

    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {
        String strProcessId = bundle.getParams().get("Rchr_Loomsdata_ID").toString();
        RchrLoomsdata rchrLoomsdata = OBDal.getInstance().get(RchrLoomsdata.class,strProcessId);
        rchrLoomsdata.setAlertStatus(RchrConstantType.DOCUMENT_STATUS_CO);
        rchrLoomsdata.getRcplLoom().setAlertStatus(RchrConstantType.LOOM_STATUS_IDLE);
        rchrLoomsdata.getRchrBeamLines().setAlertStatus(RchrConstantType.BEAM_LINES_STATUS_SB);
    }
}
