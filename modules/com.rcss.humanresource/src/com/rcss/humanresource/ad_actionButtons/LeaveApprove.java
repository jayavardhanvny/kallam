package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.*;

import com.rcss.humanresource.util.RchrConstantType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LeaveApprove extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle)  {
        String strLeaveId = (String)bundle.getParams().get("Rchr_Leaverequisition_ID");
        final String userId=bundle.getContext().getUser();
        RCHR_LeaveRequisition req=OBDal.getInstance().get(RCHR_LeaveRequisition.class,strLeaveId);
        OBError msg = new OBError();
        try{

            if(!req.getCreatedBy().getId().equals(userId)){
                msg.setType("Error");
                msg.setTitle("Error");
                msg.setMessage("Leave Complete possible for only who are created document");
            }else{
                List<RCHRLeaveRequisitionLine> line=getLinesFromRequistion(strLeaveId);
                for(RCHRLeaveRequisitionLine obj:line){
                    if(obj.getLeaveType().getSearchKey().equals("CL")||obj.getLeaveType().getSearchKey().equals("SL")){
                    System.out.println("leave type..."+obj.getLeaveType().getSearchKey());
                    insertLeaveIntoBalanceHistroy(obj);
                }else if(obj.getLeaveType().getSearchKey().equals("CO")){
                    updateCompensateOff(obj);
		}	
                    updateDailyAttendanceDemo(obj);
                }

                req.setApprove(Boolean.TRUE);
                req.setDocumentStatus("CO");
                msg.setType("Success");
                msg.setTitle("Done");
                msg.setMessage("Leave Final has been completed");
                OBDal.getInstance().commitAndClose();
            }
        }catch(Exception ex){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(ex.getMessage());
            ex.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }

        bundle.setResult(msg);
    }
    private void updateDailyAttendanceDemo(RCHRLeaveRequisitionLine obj){
        OBCriteria<RCHRDailyattenddemo> rchrDailyattenddemoOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattenddemo.class);
        rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_ATTENDANCEDATE,obj.getLeavedate()));
        rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_EMPLOYEE,obj.getLeaveRequisition().getEmployee()));
        if(rchrDailyattenddemoOBCriteria.list().size()>0 ){
            for(RCHRDailyattenddemo rchrDailyattenddemo: rchrDailyattenddemoOBCriteria.list()){
                // If Punch Records are Null...
                //if(rchrDailyattenddemo.getErrorLog().trim().length()==0) {
                    rchrDailyattenddemo.setDaytype(obj.getLeaveType().getSearchKey());
                    rchrDailyattenddemo.getRchrDailyattend().setLeave(Boolean.TRUE);
                    rchrDailyattenddemo.getRchrDailyattend().setDaytype(obj.getLeaveType().getSearchKey());
                    rchrDailyattenddemo.getRchrDailyattend().setReasonForLeave("Leave Applied");
		rchrDailyattenddemo.setPresent(Boolean.FALSE);
                    if(obj.getLeaveType().getSearchKey().equals(RchrConstantType.DAY_TYPE_CO))
                        rchrDailyattenddemo.setPresent(Boolean.TRUE);
                //}
            }
        }else{
            OBCriteria<RCHRDailyattend> rchrDailyattendOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattend.class);

            rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_ATTENDANCEDATE,obj.getLeavedate()));
            //rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_EMPLOYEE,obj.getLeaveRequisition().getEmployee()));
	rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_EMPLOYEEID,obj.getLeaveRequisition().getEmployee().getDocumentNo()));
            // Post-Dated...
            if(rchrDailyattendOBCriteria.list().size()>0){
                for (RCHRDailyattend rchrDailyattend : rchrDailyattendOBCriteria.list()
                     ) {
                        rchrDailyattend.setValidate(Boolean.FALSE);
                        rchrDailyattend.setLeave(Boolean.TRUE);
                        rchrDailyattend.setDaytype(obj.getLeaveType().getSearchKey());
                        rchrDailyattend.setPresent(Boolean.FALSE);
                        rchrDailyattend.setReasonForLeave("Leave Applied");
                }
            }else{
                // Pre-Dated
                RCHRDailyattend rchrDailyattend = OBProvider.getInstance().get(RCHRDailyattend.class);
                rchrDailyattend.setAttendanceDate(obj.getLeavedate());
                rchrDailyattend.setOrganization(obj.getOrganization());
                rchrDailyattend.setEmployee(obj.getLeaveRequisition().getEmployee());
		rchrDailyattend.setEmployeeId(obj.getLeaveRequisition().getEmployee().getPunchno());
                rchrDailyattend.setEmployeeName(obj.getLeaveRequisition().getEmployee().getEmployeeName());
                rchrDailyattend.setPresent(Boolean.FALSE);
                rchrDailyattend.setValidate(Boolean.FALSE);
                rchrDailyattend.setLeave(Boolean.TRUE);
                rchrDailyattend.setReasonForLeave("Leave Applied");
                rchrDailyattend.setDaytype(obj.getLeaveType().getSearchKey());
                OBDal.getInstance().save(rchrDailyattend);
            }
        }

    }

    private void updateCompensateOff(RCHRLeaveRequisitionLine obj){
        String hql=" update RCHR_CompensateOff set alertStatus='US',expriydate='"+obj.getLeavedate()+"' WHERE employee.id=:empId AND id=:compensateId ";
        System.out.println(obj.getLeaveRequisition().getEmployee().getId()+" "+obj.getRchrCompensateoff().getId());
        Query qry=OBDal.getInstance().getSession().createQuery(hql);
        qry.setParameter("empId",obj.getLeaveRequisition().getEmployee().getId());
        qry.setParameter("compensateId",obj.getRchrCompensateoff().getId());
        qry.executeUpdate();
    }

    private void insertLeaveIntoBalanceHistroy(RCHRLeaveRequisitionLine obj){
            RCHRLeaveBalanceHistory history= OBProvider.getInstance().get(RCHRLeaveBalanceHistory.class);
            history.setOrganization(obj.getOrganization());
            history.setClient(obj.getClient());
            history.setEmployee(obj.getLeaveRequisition().getEmployee());
            history.setLeavedate(obj.getLeavedate());
            history.setLeavecount((long)-1);
            history.setLeaveType(obj.getLeaveType());
            history.setRchrLeaverequisitionline(obj);
            history.setLeavedoctype("LR");
            OBDal.getInstance().save(history);
            OBDal.getInstance().flush();
    }

    private List<RCHRLeaveRequisitionLine> getLinesFromRequistion(String requistionId){
            String hql=" SELECT line from RCHR_LeaveRequisitionLine line"
                     + " JOIN line.leaveType ltype"
                     + " WHERE line.leaveRequisition.id='"+requistionId+"' AND (ltype.searchKey='SL' OR ltype.searchKey='CL' OR ltype.searchKey='CO') ";
            System.out.println("list size..."+OBDal.getInstance().getSession().createQuery(hql).list().size());
            return OBDal.getInstance().getSession().createQuery(hql).list();
    }

}
