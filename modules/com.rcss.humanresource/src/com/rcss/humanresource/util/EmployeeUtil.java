package com.rcss.humanresource.util;


import com.rcss.humanresource.data.*;


import com.redcarpet.payroll.util.PayrollUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EmployeeUtil {
    // Gets the Active or current working employees...
    final static Logger logger = Logger.getLogger(EmployeeUtil.class);
    public HashMap<String, RchrEmployee> getWorkingEmployeesList(){
        OBCriteria<RchrEmployee> employeesCriteriaList = OBDal.getInstance().createCriteria(RchrEmployee.class);
        employeesCriteriaList.add(Restrictions.eq(RchrEmployee.PROPERTY_ACTIVE,true));
        employeesCriteriaList.add(Restrictions.eq(RchrEmployee.PROPERTY_EMPLOYEESTATUS,
                RchrConstantType.EMPLOYEE_STATUS_WORKING));

        HashMap<String, RchrEmployee> employeesList = new HashMap<String, RchrEmployee>();
        for(RchrEmployee employee : employeesCriteriaList.list()){
            employeesList.put(employee.getPunchno(), employee);
        }
        return employeesList;
    }

    public int getWeeklyOff(RchrEmployee emp,Date date){
        int weekNo=8;
        String weekOff="N/A";

        if(emp.getWeeklyOff()==null){
            weekOff="N/A";
        }else{
            weekOff=emp.getWeeklyOff();
            List li = this.getWeeklyOffDetails(emp,date);
            if(li.size()>=1){
                // System.out.println("In If condition ");
                weekOff = li.get(0).toString();
                System.out.println("weekOff History "+weekOff);
            }
        }
        if(weekOff.contains("SUNDAY")){
            weekNo=0;
        }else if(weekOff.contains("MONDAY")){
            weekNo=1;
        }else if(weekOff.contains("TUESDAY")){
            weekNo=2;
        }else if(weekOff.contains("WEDNESDAY")){
            weekNo=3;
        }else if(weekOff.contains("THURSDAY")){
            weekNo=4;
        }else if(weekOff.contains("FRIDAY")){
            weekNo=5;
        }else if(weekOff.contains("SATURDAY")){
            weekNo=6;
        }else{
            weekNo=7;
        }
        return weekNo;
    }
    // get Weekly Off
    private List getWeeklyOffDetails(RchrEmployee emp,Date date){

        String hql = "SELECT aline.weeklyOff from RCHR_WeeklyOffDetails AS aline "
                + " WHERE aline.employee.id = '" + emp.getId()+ "' "
                + " AND aline.effectivedate <= '" + PayrollUtils.getInstance().getDBCompatiableDate(date) +
                "' AND aline.effectivetodate >= '" + PayrollUtils.getInstance().getDBCompatiableDate(date) + "' "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        System.out.println("Size Of Weekly Offs is  "+li.size());
        return li;
    }
    public String getEmployeeWeekOffForPayroll(RchrEmployee employee, Date date){
        String weekoff = employee.getWeeklyOff();
        String hql = "SELECT aline.weeklyOff FROM RCHR_WeeklyOffDetails AS aline "
                + " WHERE aline.employee.id = '" + employee.getId()+ "' "
                + " AND aline.effectivedate <= '" + PayrollUtils.getInstance().getDBCompatiableDate(date) +
                "' AND aline.effectivetodate >= '" + PayrollUtils.getInstance().getDBCompatiableDate(date) + "' "
                + " AND 1 = 1 ORDER BY aline.effectivedate DESC";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        logger.info("Size is  "+li.size());
        if(li.size()>=1) {
            weekoff = (String) li.get(0);
            logger.info("Greater Than one in Shift Group Memo  "+li.size());
        }
        return weekoff;
    }
    public List<RCHRLeaveRequisitionLine> getEmployeeLeaves(RchrEmployee employee, Date date){
        String hql = "SELECT aline FROM RCHR_LeaveRequisitionLine AS aline "
                + " JOIN aline.leaveRequisition AS req "
                + " WHERE req.employee.id = '" + employee.getId()+ "' "
		+ " AND req.documentStatus = 'CO' AND aline.canceled=false "
                + " AND aline.leavedate = '" + PayrollUtils.getInstance().getDBCompatiableDate(date)+"' "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List<RCHRLeaveRequisitionLine> li = session.createQuery(hql).list();
        //logger.info("HQL is "+hql);
        return li;
    }

public List<RchrOndutylines> getEmployeesOnduty(RchrEmployee employee, Date date){
        String hql = "SELECT aline FROM Rchr_Ondutylines AS aline "
                + " JOIN aline.rchrOnduty AS req "
                + " WHERE req.employee.id = '" + employee.getId()+ "' "
                + " AND aline.cancel=false "
                + " AND aline.oddate = '" + PayrollUtils.getInstance().getDBCompatiableDate(date)+"' "
		+ " AND aline.weeklyOff=false "
                + " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List<RchrOndutylines> li = session.createQuery(hql).list();
        //logger.info("HQL is "+hql);
        return li;
    }
    public int getWeeklyOff(String weekOff){
        int weekNo=8;

        if(weekOff==null){
            weekOff="N/A";
        }
        if(weekOff.contains("SUNDAY")){
            weekNo=0;
        }else if(weekOff.contains("MONDAY")){
            weekNo=1;
        }else if(weekOff.contains("TUESDAY")){
            weekNo=2;
        }else if(weekOff.contains("WEDNESDAY")){
            weekNo=3;
        }else if(weekOff.contains("THURSDAY")){
            weekNo=4;
        }else if(weekOff.contains("FRIDAY")){
            weekNo=5;
        }else if(weekOff.contains("SATURDAY")){
            weekNo=6;
        }else{
            weekNo=7;
        }
        return weekNo;
    }
public RchrEmpDept getEmployeeDepartment(RchrEmployee employee, Date fromDate,Date toDate){
        RchrEmpDept rchrEmpDept = employee.getEmployeeDepartment();
        String hql = "SELECT aline.employeeDepartment FROM RCHR_JobDetails AS aline "
                + " WHERE aline.employee.id = '" + employee.getId()+ "' "
                + " AND aline.effectivedate <= '" + PayrollUtils.getInstance().getDBCompatiableDate(toDate) + "' "
                //+ " AND '" + PayrollUtils.getInstance().getDBCompatiableDate(toDate) + "' "
                + " AND 1 = 1 ORDER BY aline.effectivedate DESC LIMIT 1";
        Session session = OBDal.getInstance().getSession();
        List<RchrEmpDept> li = session.createQuery(hql).list();
        if(li.size()>0){
            rchrEmpDept = (RchrEmpDept)li.get(0);
        }
        return rchrEmpDept;
    }
    public RCHR_SubDepartment getEmployeeSubDepartment(RchrEmployee employee, Date fromDate,Date toDate){

        RCHR_SubDepartment rchrSubDepartment = employee.getSubDepartment();
        String hql = "SELECT aline.subDepartment FROM RCHR_JobDetails AS aline "
                + " WHERE aline.employee.id = '" + employee.getId()+ "' "
                + " AND aline.effectivedate <= '" + PayrollUtils.getInstance().getDBCompatiableDate(toDate) + "' "
                //+ " AND '" + PayrollUtils.getInstance().getDBCompatiableDate(toDate) + "' "
                + " AND 1 = 1 ORDER BY aline.effectivedate DESC LIMIT 1";
        Session session = OBDal.getInstance().getSession();
        List<RCHR_SubDepartment> li = session.createQuery(hql).list();
        if(li.size()>0){
            rchrSubDepartment = (RCHR_SubDepartment)li.get(0);
        }
        return rchrSubDepartment;
    }
    public RchrDesignation getEmployeeDesignation(RchrEmployee employee, Date fromDate,Date toDate){
        RchrDesignation rchrDesignation = employee.getDesignation();
        String hql = "SELECT aline.designation FROM RCHR_JobDetails AS aline "
                + " WHERE aline.employee.id = '" + employee.getId()+ "' "
                + " AND aline.effectivedate <= '" + PayrollUtils.getInstance().getDBCompatiableDate(toDate) + "' "
                //+ " AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(toDate) + "' "
                + " AND 1 = 1 ORDER BY aline.effectivedate DESC LIMIT 1";
        Session session = OBDal.getInstance().getSession();
        List<RchrDesignation> li = session.createQuery(hql).list();
        if(li.size()>0){
            rchrDesignation = (RchrDesignation)li.get(0);
        }
        return rchrDesignation;
    }
}

