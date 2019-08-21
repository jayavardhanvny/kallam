package com.rcss.humanresource.ad_actionButtons;


import com.rcss.humanresource.data.RCHRDailyattend;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.EmployeeUtil;
import com.rcss.humanresource.util.HqlUtils;
import com.redcarpet.payroll.data.RcplEmppprocessmanual;
import com.redcarpet.payroll.data.RcplPayrollprocessmanual;
import com.rcss.humanresource.util.RchrConstantType;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import com.rcss.humanresource.data.RCHRDailyattenddemo;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;


public class GetEmployeesFromDaily extends DalBaseProcess implements BundleProcess{
    protected Logger logger = Logger.getLogger("GetEmployeesFromDaily.java");
    @Override
    protected void doExecute(ProcessBundle bundle){
        final OBError msg = new OBError();
        try{
            doIt(bundle);
            msg.setType("Success");
            msg.setTitle("Done");
            msg.setMessage("Success");
            bundle.setResult(msg);
            OBDal.getInstance().commitAndClose();
        }catch(Exception e){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage("Error is : "+e.getMessage());
            bundle.setResult(msg);
            logger.log(Level.SEVERE,e.getMessage());
            OBDal.getInstance().rollbackAndClose();
        }
    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {

       String id = (String) bundle.getParams().get("Rcpl_Payrollprocessmanual_ID");
        RcplPayrollprocessmanual rcplPayrollprocessmanual = OBDal.getInstance().get(RcplPayrollprocessmanual.class,id);

        HqlUtils hqlUtils = new HqlUtils();
        List<RCHRDailyattend> rchrDailyattendList = hqlUtils.getGrievancesFromDailyAttendance(rcplPayrollprocessmanual.getStartingDate(),rcplPayrollprocessmanual.getEndingDate());
        //OBCriteria<RCHRDailyattend> rchrDailyattendOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattend.class);
        //rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_VALIDATE, Boolean.FALSE));;
        //rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_ISGRIEVANCE,Boolean.FALSE));
	    //rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_ISLEAVE,Boolean.FALSE));
        //rchrDailyattendOBCriteria.add(Restrictions.ne(RCHRDailyattend.PROPERTY_ERRORLOG,null));
        //rchrDailyattendOBCriteria.add(Restrictions.ge(RCHRDailyattend.PROPERTY_ATTENDANCEDATE,rcplPayrollprocessmanual.getStartingDate()));
        //rchrDailyattendOBCriteria.add(Restrictions.le(RCHRDailyattend.PROPERTY_ATTENDANCEDATE,rcplPayrollprocessmanual.getEndingDate()));

        EmployeeUtil employeeUtil = new EmployeeUtil();
        HashMap<String,RchrEmployee> rchrEmployeeHashMap = employeeUtil.getWorkingEmployeesList();
        //System.out.println("Size "+rchrDailyattendOBCriteria.list().size()+" "+rcplPayrollprocessmanual.getStartingDate()+" "+rcplPayrollprocessmanual.getEndingDate());
        System.out.println("Size "+rchrDailyattendList.size()+" "+rcplPayrollprocessmanual.getStartingDate()+" "+rcplPayrollprocessmanual.getEndingDate());
        for (RCHRDailyattend rchrDailyattend : rchrDailyattendList){
            //List<RcplEmppprocessmanual> rcplEmppprocessmanualList= hqlUtils.getDailyAttendanceFromGrievances(rchrDailyattend);
            //double size = hqlUtils.getDailyAttendanceFromGrievances(rchrDailyattend);
             double size = rchrDailyattend.getRcplEmppprocessmanualList().size();
            System.out.println("Size 2"+size);
            //OBCriteria<RcplEmppprocessmanual> rcplEmppprocessmanualOBCriteria = OBDal.getInstance().createCriteria(RcplEmppprocessmanual.class);
            //rcplEmppprocessmanualOBCriteria.add(Restrictions.eq(RcplEmppprocessmanual.PROPERTY_RCHRDAILYATTEND,rchrDailyattend));
            if(rchrDailyattend.getEmployeeId()!=null && rchrEmployeeHashMap.containsKey(rchrDailyattend.getEmployeeId()) && size==0){
                RcplEmppprocessmanual rcplEmppprocessmanual = OBProvider.getInstance().get(RcplEmppprocessmanual.class);
                rcplEmppprocessmanual.setAttddate(rchrDailyattend.getAttendanceDate());
                rcplEmppprocessmanual.setOrganization(rchrDailyattend.getOrganization());
                rcplEmppprocessmanual.setRcplPayrollprocessmanual(rcplPayrollprocessmanual);
		int empWeek = employeeUtil.getWeeklyOff(rchrEmployeeHashMap.get(rchrDailyattend.getEmployeeId()), rchrDailyattend.getAttendanceDate());
                Boolean isWeekOff = Boolean.FALSE;
                int weeklyOff = rchrDailyattend.getAttendanceDate().getDay();
                if(weeklyOff==empWeek){
                    isWeekOff=Boolean.TRUE;
                }
                if (isWeekOff)
                    rcplEmppprocessmanual.setOtprocess(Boolean.TRUE);
                if(rchrEmployeeHashMap.containsKey(rchrDailyattend.getEmployeeId())){
                    rcplEmppprocessmanual.setEmployee(rchrEmployeeHashMap.get(rchrDailyattend.getEmployeeId()));
                }
                rchrDailyattend.setGrievance(Boolean.TRUE);
		//rcplEmppprocessmanual.setDaytype(RchrConstantType.DAY_TYPE_LOP);
                rcplEmppprocessmanual.setRchrDailyattend(rchrDailyattend);
                OBDal.getInstance().save(rcplEmppprocessmanual);
                //rchrDailyattend.setRcplEmppprocessmanual(rcplEmppprocessmanual);
            }else {

            }

        }
	//this.insertEmployeesFromDailyAttendanceDemo(hqlUtils,rcplPayrollprocessmanual.getStartingDate(),rcplPayrollprocessmanual.getEndingDate(),rcplPayrollprocessmanual);
	    rcplPayrollprocessmanual.setGetemployees(Boolean.TRUE);
        rcplPayrollprocessmanual.setPosted(Boolean.FALSE);
    }
public void insertEmployeesFromDailyAttendanceDemo(HqlUtils hqlUtils, Date fromDate, Date toDate,RcplPayrollprocessmanual rcplPayrollprocessmanual){
        List<RCHRDailyattenddemo> rchrDailyattenddemoList = hqlUtils.getGrievancesFromDailyAttendanceDemo(fromDate,toDate);
        if(rchrDailyattenddemoList.size()>0){
            for(RCHRDailyattenddemo rchrDailyattenddemo : rchrDailyattenddemoList){
                RcplEmppprocessmanual rcplEmppprocessmanual = OBProvider.getInstance().get(RcplEmppprocessmanual.class);
                rcplEmppprocessmanual.setAttddate(rchrDailyattenddemo.getAttendanceDate());
                rcplEmppprocessmanual.setOrganization(rchrDailyattenddemo.getOrganization());
                rcplEmppprocessmanual.setRcplPayrollprocessmanual(rcplPayrollprocessmanual);
                //if(rchrEmployeeHashMap.containsKey(rchrDailyattenddemo.getEmployeeId())){
                rcplEmppprocessmanual.setEmployee(rchrDailyattenddemo.getEmployee());
                //}
                rchrDailyattenddemo.setGrievance(Boolean.TRUE);
                rcplEmppprocessmanual.setDaytype(RchrConstantType.DAY_TYPE_LOP);
                rcplEmppprocessmanual.setRchrDailyattend(rchrDailyattenddemo.getRchrDailyattend());
                OBDal.getInstance().save(rcplEmppprocessmanual);
            }
        }

    }

}


