package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.RCHRLeaveBalanceHistory;
import com.rcss.humanresource.data.RCHRLeaveRequisitionLine;
import com.rcss.humanresource.data.RchrCompensateOff;
import com.rcss.humanresource.util.HqlUtils;
import com.rcss.humanresource.util.RchrConstantType;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import java.util.List;

public class LeaveCancel extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle)  {
        String strLeaveId = (String)bundle.getParams().get("Rchr_Leaverequisitionline_ID");
        OBError msg = new OBError();

            RCHRLeaveRequisitionLine line=OBDal.getInstance().get(RCHRLeaveRequisitionLine.class,strLeaveId);
            final String userId=bundle.getContext().getUser();
            if(!line.getLeaveRequisition().getCreatedBy().getId().equals(userId)){
                msg.setType("Error");
                msg.setTitle("Error");
                msg.setMessage("who ever created document only cancel request");
            }else {
                executeProcess(line, msg);
                //rchrLeaveApprove.setDocumentStatus("CL");
                //updateApprovalHistrory(strLeaveId);
                msg.setType("Success");
                msg.setTitle("Done");
                msg.setMessage("Leave Request has been completed");
            }




        bundle.setResult(msg);
    }

    public void executeProcess(RCHRLeaveRequisitionLine line, OBError msg) {
        try{
            HqlUtils hqlUtils = new HqlUtils();
            if (hqlUtils.getPayrollProcessCompletedList(line.getLeavedate()).size()>0) {
                msg.setType("Error");
                msg.setTitle("Error");
                msg.setMessage("Leave Cancellation cannot possible After Payroll(Salaries) was generated");
                OBDal.getInstance().rollbackAndClose();
            } else if (line.getLeaveType().getSearchKey().equals("SL")||line.getLeaveType().getSearchKey().equals("CL")){
                leaveCancel(line);
                line.setCanceled(Boolean.TRUE);
                line.setCancel(Boolean.TRUE);
                msg.setType("Success");
                msg.setTitle("Done");
                msg.setMessage("Leave Cancellation has been completed");
                OBDal.getInstance().commitAndClose();
                line.getLeaveRequisition().setDocumentStatus("CL");
            } else if (line.getLeaveType().getSearchKey().equals("CO")){
                line.setCanceled(Boolean.TRUE);
                line.setCancel(Boolean.TRUE);
                cancelCompensateOff(line);
                msg.setType("Success");
                msg.setTitle("Done");
                msg.setMessage("Leave Cancellation has been completed");
                line.getLeaveRequisition().setDocumentStatus("CL");
            }  else if (line.getLeaveType().getSearchKey().equals("LOP")){
                line.setCanceled(Boolean.TRUE);
                line.setCancel(Boolean.TRUE);
                msg.setType("Success");
                msg.setTitle("Done");
                msg.setMessage("Leave Cancellation has been completed");
                line.getLeaveRequisition().setDocumentStatus("CL");
            }
            else {
                msg.setType("Error");
                msg.setTitle("Error");
                msg.setMessage("Cancellation is possible for EL or CL");
                OBDal.getInstance().rollbackAndClose();
            }

        }catch(Exception ex){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(ex.getMessage());
            ex.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }
    }
    public void cancelCompensateOff(RCHRLeaveRequisitionLine line){
        OBCriteria<RchrCompensateOff> rchrCompensateOffOBCriteria = OBDal.getInstance().createCriteria(RchrCompensateOff.class);
        rchrCompensateOffOBCriteria.add(Restrictions.eq(RchrCompensateOff.PROPERTY_EXPRIYDATE, line.getLeavedate()));
        rchrCompensateOffOBCriteria.add(Restrictions.eq(RchrCompensateOff.PROPERTY_EMPLOYEE, line.getLeaveRequisition().getEmployee()));
        rchrCompensateOffOBCriteria.add(Restrictions.eq(RchrCompensateOff.PROPERTY_ALERTSTATUS, "US"));
        if (rchrCompensateOffOBCriteria.list().size()==1){
            rchrCompensateOffOBCriteria.list().get(0).setExpriydate(null);
            rchrCompensateOffOBCriteria.list().get(0).setAlertStatus("ACC");
        }
    }
    public void leaveCancel(RCHRLeaveRequisitionLine line){
        insertIntoLeaveBalanceHistory(line);
    }

    public void insertIntoLeaveBalanceHistory(RCHRLeaveRequisitionLine line){
        RCHRLeaveBalanceHistory his= OBProvider.getInstance().get(RCHRLeaveBalanceHistory.class);
        his.setOrganization(line.getOrganization());
        his.setClient(line.getClient());
        his.setEmployee(line.getLeaveRequisition().getEmployee());
        his.setLeavedate(line.getLeavedate());
        his.setLeaveType(line.getLeaveType());
        his.setLeavedoctype("LC");
        his.setLeavecount((long)+1);
        OBDal.getInstance().save(his);
        OBDal.getInstance().flush();

    }
}

