package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.RCHRLeaveRequisitionLine;
import com.rcss.humanresource.data.RchrCompensateOff;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class CompensateApprove extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle)  {
        String strCoffId = (String)bundle.getParams().get("Rchr_Compensateoff_ID");
        OBError msg = new OBError();
        try{
            RchrCompensateOff coff=OBDal.getInstance().get(RchrCompensateOff.class,strCoffId);
            coff.setAlertStatus("ACC");
            coff.setApprove(Boolean.TRUE);
            msg.setType("Success");
            msg.setTitle("Done");
            msg.setMessage("Compensate Off has been completed Successfully");
            OBDal.getInstance().commitAndClose();
        }catch(Exception ex){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(ex.getMessage());
            ex.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }

        bundle.setResult(msg);
    }
}
