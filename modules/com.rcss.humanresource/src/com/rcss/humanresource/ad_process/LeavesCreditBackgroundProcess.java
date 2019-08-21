package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import com.redcarpet.payroll.util.PayrollUtils;
import org.apache.log4j.Logger;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import com.redcarpet.payroll.ad_actionbutton.AttendanceUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LeavesCreditBackgroundProcess  extends DalBaseProcess {
    private static final Logger log = Logger.getLogger(LoomsProductionProcess.class);
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        try{

            Calendar cal=Calendar.getInstance();

            Calendar cal1=Calendar.getInstance();
            cal1.set(Calendar.DATE,1);

            String firstDate=PayrollUtils.getInstance().getDBCompatiableDate(cal1.getTime());

            cal.add(Calendar.MONTH,-1);
            cal.set(Calendar.DATE,1);
            Date fromDate=cal.getTime();
            cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date endDate=cal.getTime();
            deleteAccumulativeLeaves(firstDate);
            creditLeaves(fromDate,endDate,firstDate);
            OBDal.getInstance().commitAndClose();

        }catch(Exception ex){
            ex.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }
    }

    private void deleteAccumulativeLeaves(String firstDate){
        String hql=" DELETE FROM RCHR_LeaveBalanceHistory his WHERE his.leavedate='"+firstDate+"' AND his.leavedoctype='MAC' ";
        //String hql=" DELETE FROM RCHR_LeaveBalanceHistory his WHERE his.leavedoctype='MAC' ";
        int i=OBDal.getInstance().getSession().createQuery(hql).executeUpdate();
        System.out.println("updated..."+i+" "+firstDate);
    }

    private void creditLeaves(Date fromDate,Date endDate,String firstDate){
        System.out.println("dates..."+fromDate+" "+endDate);
        String hql=" SELECT e FROM Rchr_Employee e WHERE e.employeestatus='W' AND e.rchrLeavetemplate IS NOT NULL ";
        List<RchrEmployee> employee= OBDal.getInstance().getSession().createQuery(hql).list();
        for(RchrEmployee e:employee){
            System.out.println("emp.."+e.getDocumentNo()+" "+e.getRchrLeavetemplate().getName());
            processLeaves(fromDate,endDate,firstDate,e.getRchrLeavetemplate(),e);
        }
    }

    public void processLeaves(Date fromDate,Date endDate,String firstDate,RCHRLeaveTemplate template,RchrEmployee e){
        for(RCHRLeaveTemplateLine line:template.getRCHRLeaveTemplateLineList()){
            //if(checkLeaveEligible(line.getPresentdays(),e.getId(),fromDate,endDate)){
                System.out.println("line.."+line.getLeaveType().getEntityName());
                insertIntoLeaveBalanceHistory(e,line.getLeaveType(),firstDate);
            //}

        }
    }

    private void insertIntoLeaveBalanceHistory(RchrEmployee e, RCHR_Leavetype leaveType,String firstDate) {

            try {

                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("date.."+firstDate+" "+sdf.parse(firstDate));
                RCHRLeaveBalanceHistory history = OBProvider.getInstance().get(RCHRLeaveBalanceHistory.class);
                history.setOrganization(e.getOrganization());
                history.setClient(e.getClient());
                history.setEmployee(e);
                history.setLeavedoctype("MAC");
                history.setLeaveType(leaveType);
                history.setLeavedate(sdf.parse(firstDate));
                history.setLeavecount((long) 1);
                OBDal.getInstance().save(history);
                OBDal.getInstance().flush();
            }catch(ParseException pe){
                pe.printStackTrace();
            }

    }

    private Boolean checkLeaveEligible(long days,String empID,Date fromDate,Date endDate){
        AttendanceUtil util=new AttendanceUtil(fromDate, endDate, empID);
        double workingDays=util.getNoOfDaysPresent();
        System.out.println("working days.."+workingDays+" days"+days);
        if(workingDays>=days)return Boolean.TRUE;
        else return Boolean.FALSE;
    }
}
