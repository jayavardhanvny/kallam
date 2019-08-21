package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.RchrOvertimeprocessLine;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.RchrConstantType;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class ManualUncheckOverTimeProcess extends DalBaseProcess implements BundleProcess {
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
        String id = (String)bundle.getParams().get("Rchr_Overtimeprocess_Line_ID");
        RchrOvertimeprocessLine rchrOvertimeprocessLine = OBDal.getInstance().get(RchrOvertimeprocessLine.class,id);
        if(rchrOvertimeprocessLine.getRchrDailyattenddemo()!=null){
            rchrOvertimeprocessLine.getRchrDailyattenddemo().setOvertime(Boolean.FALSE);
            rchrOvertimeprocessLine.getRchrDailyattenddemo().setDaytype(RchrConstantType.DAY_TYPE_PRESENT);
            rchrOvertimeprocessLine.getRchrDailyattenddemo().getRchrDailyattend().setDaytype(RchrConstantType.DAY_TYPE_PRESENT);
            rchrOvertimeprocessLine.getRchrDailyattenddemo().setImported(Boolean.TRUE);
        }


        rchrOvertimeprocessLine.setOvertime(Boolean.FALSE);
	    rchrOvertimeprocessLine.setManual(Boolean.TRUE);
    }
}

