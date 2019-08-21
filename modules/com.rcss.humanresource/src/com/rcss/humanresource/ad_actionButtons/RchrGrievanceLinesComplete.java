package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.util.BundleProcess;
import com.redcarpet.payroll.data.RcplEmppprocessmanual;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class RchrGrievanceLinesComplete extends DalBaseProcess implements BundleProcess {
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        final OBError msg = new OBError();


        try{
            doIt(bundle);
            msg.setType("Success");
            msg.setTitle("Done");
            msg.setMessage("Approved Process Successfully");
            OBDal.getInstance().commitAndClose();
        }catch (Exception e){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(e.getMessage());
            OBDal.getInstance().rollbackAndClose();
        }
        bundle.setResult(msg);

    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {

        String id = (String)bundle.getParams().get("Rcpl_Emppprocessmanual_ID");
        RcplEmppprocessmanual rcplEmppprocessmanual = OBDal.getInstance().get(RcplEmppprocessmanual.class,id);
        rcplEmppprocessmanual.setProcess(Boolean.TRUE);
        rcplEmppprocessmanual.setComplete(Boolean.TRUE);

    }
}
