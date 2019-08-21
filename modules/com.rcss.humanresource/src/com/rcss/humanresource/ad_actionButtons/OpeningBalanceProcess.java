package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.RCHRLeaveBalanceHistory;
import com.rcss.humanresource.data.RCHRLeaveTemplateLine;
import com.rcss.humanresource.data.RCHR_LeaveOpenBalance;
import com.rcss.humanresource.data.RCHR_LeaveRequisition;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class OpeningBalanceProcess extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle)  {
        String processId = (String)bundle.getParams().get("Rchr_Leaveopenbal_ID");
        OBError msg = new OBError();
        try{

            RCHR_LeaveOpenBalance openBalance = OBDal.getInstance().get(RCHR_LeaveOpenBalance.class, processId);
            prcoessOpeningBalance(openBalance);
            openBalance.setProcess(Boolean.TRUE);
            msg.setType("Success");
            msg.setTitle("Done");
            msg.setMessage("Leave Process has been completed");
            OBDal.getInstance().commitAndClose();

        }catch(Exception ex){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(ex.toString());
            ex.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }

        bundle.setResult(msg);
    }

    private void prcoessOpeningBalance(RCHR_LeaveOpenBalance openBalance){

        RCHRLeaveBalanceHistory obj= OBProvider.getInstance().get(RCHRLeaveBalanceHistory.class);
        obj.setOrganization(openBalance.getOrganization());
        obj.setClient(openBalance.getClient());
        obj.setEmployee(openBalance.getEmployee());
        obj.setLeavedate(openBalance.getObdate());
        obj.setLeavecount(openBalance.getLeavesPerYear().longValue());
        obj.setLeaveType(openBalance.getLeaveType());
        obj.setLeavedoctype("OB");
        OBDal.getInstance().save(obj);
        OBDal.getInstance().flush();
    }
}
