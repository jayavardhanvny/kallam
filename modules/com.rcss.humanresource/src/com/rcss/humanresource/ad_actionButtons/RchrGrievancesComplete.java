package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.RCHRDailyattenddemo;
import com.rcss.humanresource.data.RCHRLeaveBalanceHistory;
import com.rcss.humanresource.data.RCHR_Leavetype;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.EmployeeUtil;
import com.rcss.humanresource.util.RchrConstantType;
import com.redcarpet.payroll.data.RcplEmppprocessmanual;
import com.redcarpet.payroll.data.RcplPayrollprocessmanual;
import com.redcarpet.payroll.util.PayrollUtils;
import org.hibernate.criterion.Restrictions;
import org.hibernate.usertype.LoggableUserType;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

public class RchrGrievancesComplete extends DalBaseProcess implements BundleProcess{
    private final Logger logger = Logger.getLogger("RchrGrievancesComplete.java");
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
            e.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }
        bundle.setResult(msg);
    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {
        String tabId = (String) bundle.getParams().get("tabId");
        if(tabId.equals("E28D8318C8D249BDBAA06C584ECC32C2")) {
            String id = (String)bundle.getParams().get("Rcpl_Payrollprocessmanual_ID");
            RcplPayrollprocessmanual rcplPayrollprocessmanual = OBDal.getInstance().get(RcplPayrollprocessmanual.class,id);
            for (RcplEmppprocessmanual rcplEmppprocessmanual: rcplPayrollprocessmanual.getRcplEmppprocessmanualList()) {
                headerProcess(rcplEmppprocessmanual);
            }

            rcplPayrollprocessmanual.setProcess(Boolean.TRUE);
            rcplPayrollprocessmanual.setPosted(Boolean.TRUE);
            //logger.info("Log16");
	} else if (tabId.equals("A88CE224AC9E4285B9742794796EC056") || tabId.equals("B479B97A7086432FB653AA751A94D59F") || tabId.equals("8B4B40AA611544298F503AB589667C90") ){
            String id = (String)bundle.getParams().get("Rcpl_Emppprocessmanual_ID");
            RcplEmppprocessmanual rcplEmppprocessmanual = OBDal.getInstance().get(RcplEmppprocessmanual.class, id);
            headerProcess(rcplEmppprocessmanual);
        }
    }

    private void headerProcess(RcplEmppprocessmanual rcplEmppprocessmanual) throws SQLException{
        if (rcplEmppprocessmanual.getDaytype() != null) {
            if (rcplEmppprocessmanual.isProcessed().equals(Boolean.FALSE)) {
                //logger.info("In Not Processed");
                //try {
                if (rcplEmppprocessmanual.getDaytype().equals(RchrConstantType.DAY_TYPE_SL) || rcplEmppprocessmanual.getDaytype().equals(RchrConstantType.DAY_TYPE_CL)) {
                    OBCriteria<RCHR_Leavetype> rchrLeavetypeOBCriteria = OBDal.getInstance().createCriteria(RCHR_Leavetype.class);
                    rchrLeavetypeOBCriteria.add(Restrictions.eq(RCHR_Leavetype.PROPERTY_SEARCHKEY, rcplEmppprocessmanual.getDaytype()));
                    RCHR_Leavetype rchrLeavetype = OBDal.getInstance().get(RCHR_Leavetype.class, rchrLeavetypeOBCriteria.list().get(0).getId());
                    String firstDate = PayrollUtils.getInstance().getDBCompatiableDate(rcplEmppprocessmanual.getAttddate());
                    //deleteGrievancesLeaves(rcplEmppprocessmanual.getEmployee(),firstDate,rchrLeavetype);
                    insertLeaveBalanceHistory(rcplEmppprocessmanual, rchrLeavetype);
                }
                //try {
                //Boolean parity = insertDailyAttendance(rcplEmppprocessmanual);
                insertDailyAttendance(rcplEmppprocessmanual);
                //if (parity.equals(Boolean.TRUE)) {
                rcplEmppprocessmanual.setProcessed(Boolean.TRUE);
                //}
                //} catch (Exception e){
                //   e.printStackTrace();
                // }
                //logger.info("After insert "+parity);
                //rcplEmppprocessmanual.getRchrDailyattend().setValidate(Boolean.TRUE);
                //rcplEmppprocessmanual.getRchrDailyattend().setReasonForLeave("From Grievance Complete");
                //rcplEmppprocessmanual.setProcessed(Boolean.TRUE);

                //logger.info("Log 1");
                rcplEmppprocessmanual.setProcess(Boolean.TRUE);
                //logger.info("Log 2");
                rcplEmppprocessmanual.setComplete(Boolean.TRUE);
                rcplEmppprocessmanual.getRchrDailyattend().setGrievance(Boolean.TRUE);
                //logger.info("Log 3");

                //} catch (Exception e) {
                // e.printStackTrace();
                //}
            }

        }
    }


    private void insertDailyAttendance(RcplEmppprocessmanual rcplEmppprocessmanual) throws SQLException {
        OBCriteria<RCHRDailyattenddemo> rchrDailyattenddemoOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattenddemo.class);
        rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_ATTENDANCEDATE,rcplEmppprocessmanual.getAttddate()));
        rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_EMPLOYEE,rcplEmppprocessmanual.getEmployee()));
        if(rchrDailyattenddemoOBCriteria.list().size()==1 ){
            //logger.info("In Daily Attendance Demo");
            for(RCHRDailyattenddemo rchrDailyattenddemo: rchrDailyattenddemoOBCriteria.list()){
                /*if(rchrDailyattenddemo.isPresent() && rchrDailyattenddemo.isOvertime()){
                    //logger.info("In Daily Attendance Demo 2");
                    if (rcplEmppprocessmanual.getDaytype().equals(RchrConstantType.DAY_TYPE_PRESENT)){

                    }
                } else { */
                if(rchrDailyattenddemo.isPresent().equals(Boolean.FALSE) || (rchrDailyattenddemo.isPresent().equals(Boolean.TRUE) &&
                        rchrDailyattenddemo.isOvertime().equals(Boolean.TRUE))) {
                    rchrDailyattenddemo.setPresent(Boolean.FALSE);
                    rchrDailyattenddemo.setOvertime(Boolean.FALSE);
                    rchrDailyattenddemo.setDaytype(rcplEmppprocessmanual.getDaytype());
                    rchrDailyattenddemo.setRchrDailyattend(rcplEmppprocessmanual.getRchrDailyattend());
                    rcplEmppprocessmanual.getRchrDailyattend().setDaytype(rcplEmppprocessmanual.getDaytype());
                    rchrDailyattenddemo.setGrievance(Boolean.TRUE);
                    rchrDailyattenddemo.getRchrDailyattend().setEmployee(rcplEmppprocessmanual.getEmployee());
                    rchrDailyattenddemo.getRchrDailyattend().setRcplEmppprocessmanual(rcplEmppprocessmanual);
                    if(rcplEmppprocessmanual.getRchrDailyattend().getErrorLog()!=null)
                        rchrDailyattenddemo.setErrorLog(rcplEmppprocessmanual.getRchrDailyattend().getErrorLog());
                    rcplEmppprocessmanual.getRchrDailyattend().setGrievance(Boolean.TRUE);
                    //rcplEmppprocessmanual.getRchrDailyattend().setReasonForLeave("From Grievance Complete");
                    rcplEmppprocessmanual.getRchrDailyattend().setValidate(Boolean.TRUE);
                    if (rcplEmppprocessmanual.getDaytype().equals(RchrConstantType.DAY_TYPE_PRESENT) || rcplEmppprocessmanual.getDaytype().equals(RchrConstantType.DAY_TYPE_CO)) {
                        rchrDailyattenddemo.setPresent(Boolean.TRUE);
                        rchrDailyattenddemo.setOvertime(Boolean.FALSE);
                    } else if (rcplEmppprocessmanual.getDaytype().equals(RchrConstantType.DAY_TYPE_PROT)){
                        rchrDailyattenddemo.setPresent(Boolean.TRUE);
                        rchrDailyattenddemo.setOvertime(Boolean.TRUE);
                    }
                }
                //logger.info("In Daily Attendance Demo 3");


            }
        }else if (rchrDailyattenddemoOBCriteria.list().size()==0){
            RCHRDailyattenddemo rchrDailyattenddemo = OBProvider.getInstance().get(RCHRDailyattenddemo.class);

            EmployeeUtil employeeUtil = new EmployeeUtil();

            int empWeek = employeeUtil.getWeeklyOff(rcplEmppprocessmanual.getEmployee(), rcplEmppprocessmanual.getRchrDailyattend().getAttendanceDate());
            Boolean isWeekOff = Boolean.FALSE;
            int weeklyOff = rcplEmppprocessmanual.getRchrDailyattend().getAttendanceDate().getDay();
            if(weeklyOff==empWeek){
                isWeekOff=Boolean.TRUE;
            }
            if (isWeekOff)
                rchrDailyattenddemo.setWeeklyOff(Boolean.TRUE);
            if(!rcplEmppprocessmanual.getDaytype().equals(RchrConstantType.DAY_TYPE_PROT)){
                rchrDailyattenddemo.setPresent(Boolean.FALSE);
                if(rcplEmppprocessmanual.getDaytype().equals(RchrConstantType.DAY_TYPE_PRESENT) || rcplEmppprocessmanual.getDaytype().equals(RchrConstantType.DAY_TYPE_CO)){
                    rchrDailyattenddemo.setPresent(Boolean.TRUE);
                    rchrDailyattenddemo.setOvertime(Boolean.FALSE);
                }
                rchrDailyattenddemo.setOrganization(rcplEmppprocessmanual.getRcplPayrollprocessmanual().getOrganization());
                rchrDailyattenddemo.setDaytype(rcplEmppprocessmanual.getDaytype());
                rchrDailyattenddemo.setAttendanceDate(rcplEmppprocessmanual.getAttddate());
                rchrDailyattenddemo.setEmployee(rcplEmppprocessmanual.getEmployee());
                rchrDailyattenddemo.setEmployeeId(rcplEmppprocessmanual.getEmployee().getDocumentNo());
                rchrDailyattenddemo.setRchrDailyattend(rcplEmppprocessmanual.getRchrDailyattend());

                if(rcplEmppprocessmanual.getRchrDailyattend().getErrorLog()!=null)
                    rchrDailyattenddemo.setErrorLog(rcplEmppprocessmanual.getRchrDailyattend().getErrorLog());
                //rchrDailyattenddemo.getRchrDailyattend().setRcplEmppprocessmanual(rcplEmppprocessmanual);
                rcplEmppprocessmanual.getRchrDailyattend().setRcplEmppprocessmanual(rcplEmppprocessmanual);
                rcplEmppprocessmanual.getRchrDailyattend().setEmployee(rcplEmppprocessmanual.getEmployee());
                rcplEmppprocessmanual.getRchrDailyattend().setValidate(Boolean.TRUE);
                rchrDailyattenddemo.setGrievance(Boolean.TRUE);
                //rcplEmppprocessmanual.getRchrDailyattend().setReasonForLeave("From Grievance Complete");
                OBDal.getInstance().save(rchrDailyattenddemo);
                OBDal.getInstance().flush();
                //logger.info("In Daily Attendance Demo 4");


            } else if (rcplEmppprocessmanual.getDaytype().equals(RchrConstantType.DAY_TYPE_PROT)){
                rchrDailyattenddemo.setPresent(Boolean.FALSE);
                if(rcplEmppprocessmanual.getDaytype().equals(RchrConstantType.DAY_TYPE_PROT)){
                    rchrDailyattenddemo.setPresent(Boolean.TRUE);
                    rchrDailyattenddemo.setOvertime(Boolean.TRUE);

                }
                rchrDailyattenddemo.setOrganization(rcplEmppprocessmanual.getRcplPayrollprocessmanual().getOrganization());
                rchrDailyattenddemo.setDaytype(rcplEmppprocessmanual.getDaytype());
                rchrDailyattenddemo.setAttendanceDate(rcplEmppprocessmanual.getAttddate());
                rchrDailyattenddemo.setEmployee(rcplEmppprocessmanual.getEmployee());
                rchrDailyattenddemo.setEmployeeId(rcplEmppprocessmanual.getEmployee().getDocumentNo());
                rchrDailyattenddemo.setRchrDailyattend(rcplEmppprocessmanual.getRchrDailyattend());

                if(rcplEmppprocessmanual.getRchrDailyattend().getErrorLog()!=null)
                    rchrDailyattenddemo.setErrorLog(rcplEmppprocessmanual.getRchrDailyattend().getErrorLog());
                //rchrDailyattenddemo.getRchrDailyattend().setRcplEmppprocessmanual(rcplEmppprocessmanual);
                rcplEmppprocessmanual.getRchrDailyattend().setEmployee(rcplEmppprocessmanual.getEmployee());
                rcplEmppprocessmanual.getRchrDailyattend().setRcplEmppprocessmanual(rcplEmppprocessmanual);
                rcplEmppprocessmanual.getRchrDailyattend().setValidate(Boolean.TRUE);
                rchrDailyattenddemo.setGrievance(Boolean.TRUE);
                //rcplEmppprocessmanual.getRchrDailyattend().setReasonForLeave("From Grievance Complete");
                OBDal.getInstance().save(rchrDailyattenddemo);
                OBDal.getInstance().flush();

            }
        } else if (rchrDailyattenddemoOBCriteria.list().size()==2){

            // Dual OT shift not followed
            rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_PRESENT,Boolean.TRUE));
            rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_OVERTIME,Boolean.TRUE));
	        rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_WEEKLYOFF,Boolean.FALSE));
            if (rchrDailyattenddemoOBCriteria.list().size()==2 && rcplEmppprocessmanual.getDaytype().equals(RchrConstantType.DAY_TYPE_PRESENT)){
                rchrDailyattenddemoOBCriteria.list().get(0).setDaytype(RchrConstantType.DAY_TYPE_PRESENT);
                rchrDailyattenddemoOBCriteria.list().get(0).setPresent(Boolean.TRUE);
                rchrDailyattenddemoOBCriteria.list().get(0).setOvertime(Boolean.FALSE);
                rchrDailyattenddemoOBCriteria.list().get(0).getRchrDailyattend().setDaytype(RchrConstantType.DAY_TYPE_PRESENT);
                rchrDailyattenddemoOBCriteria.list().get(0).setGrievance(Boolean.TRUE);
                rcplEmppprocessmanual.getRchrDailyattend().setRcplEmppprocessmanual(rcplEmppprocessmanual);
                //rchrDailyattenddemoOBCriteria.list().get(0).getRchrDailyattend().setValidate(Boolean.TRUE);
            }

            logger.info("Need to be think Greater than 2 ");
        }

    }
    public long getLeavesBalances(String employeeId, Date fromDate,String leaveId){
        String hql=" SELECT COALESCE(sum(info.leavecount),0) FROM RCHR_LeaveBalanceHistory info"
                +" WHERE info.employee.id='"+employeeId+"' AND info.leavedate<='"+fromDate+"' AND info.leaveType.id='"+leaveId+"' ";
        //logger.info("EL leaves..."+OBDal.getInstance().getSession().createQuery(hql).list().get(0));
        int size=OBDal.getInstance().getSession().createQuery(hql).list().size();
        return size>0?(long)OBDal.getInstance().getSession().createQuery(hql).list().get(0):0;
    }
    private void insertLeaveBalanceHistory(RcplEmppprocessmanual rcplEmppprocessmanual,RCHR_Leavetype rchrLeavetype){
        //logger.info("Log7");


        if(getLeavesBalances(rcplEmppprocessmanual.getEmployee().getId(),rcplEmppprocessmanual.getAttddate(),rchrLeavetype.getId())>0){
            OBCriteria<RCHRLeaveBalanceHistory> rchrLeaveBalanceHistoryOBCriteria = OBDal.getInstance().createCriteria(RCHRLeaveBalanceHistory.class);

            rchrLeaveBalanceHistoryOBCriteria.add(Restrictions.eq(RCHRLeaveBalanceHistory.PROPERTY_EMPLOYEE,rcplEmppprocessmanual.getEmployee()));
            //rchrLeaveBalanceHistoryOBCriteria.add(Restrictions.eq(RCHRLeaveBalanceHistory.PROPERTY_LEAVETYPE,rchrLeavetype));
            //rchrLeaveBalanceHistoryOBCriteria.add(Restrictions.eq(RCHRLeaveBalanceHistory.PROPERTY_LEAVEDOCTYPE,"GL"));
            rchrLeaveBalanceHistoryOBCriteria.add(Restrictions.eq(RCHRLeaveBalanceHistory.PROPERTY_LEAVEDATE,rcplEmppprocessmanual.getAttddate()));
            if(rchrLeaveBalanceHistoryOBCriteria.list().size()==0){
                RCHRLeaveBalanceHistory history= OBProvider.getInstance().get(RCHRLeaveBalanceHistory.class);
                history.setOrganization(rcplEmppprocessmanual.getOrganization());
                history.setClient(rcplEmppprocessmanual.getClient());
                history.setEmployee(rcplEmppprocessmanual.getEmployee());
                history.setLeavedate(rcplEmppprocessmanual.getAttddate());
                history.setLeavecount((long)-1);
                history.setLeaveType(rchrLeavetype);
                //history.setRchrLeaverequisitionline(obj);
                history.setLeavedoctype("GL");
                OBDal.getInstance().save(history);
                OBDal.getInstance().flush();
            }
        }

    }
    private void deleteGrievancesLeaves(RchrEmployee employee, String firstDate,RCHR_Leavetype rchrLeavetype){
        String hql=" DELETE FROM RCHR_LeaveBalanceHistory his WHERE his.leavedate='"+firstDate+"' AND his.leavedoctype='GL' ";
        //String hql=" DELETE FROM RCHR_LeaveBalanceHistory his WHERE his.leavedoctype='MAC' ";
        int i=OBDal.getInstance().getSession().createQuery(hql).executeUpdate();
        logger.info("updated..."+i+" "+firstDate);
    }
}


