package com.rcss.humanresource.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.redcarpet.epcg.data.EpcgVariant;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;
import org.apache.log4j.Logger;
import org.openbravo.model.common.plm.Product;
import com.rcss.humanresource.data.*;
import com.redcarpet.epcg.data.EpcgYarncosttemplatelines;
import com.redcarpet.payroll.util.PayrollUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.openbravo.dal.service.OBDal;

public class HqlUtils{
    protected Logger logger = Logger.getLogger(HqlUtils.class);
	public double getFirstRecordValue(String hql) throws HibernateException, NumberFormatException {
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            return 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
            return 0;
        }
        return Double.valueOf(li.get(0).toString());
    }
    public List<RchrRelay> getShiftRotation(Date fromDate, Date toDate){
            String hql = " SELECT ess FROM Rchr_Relay ess "
                    + " WHERE ess.fromdate between '" + PayrollUtils.getInstance().getDBCompatiableDate(fromDate) + "' AND  '" + PayrollUtils.getInstance().getDBCompatiableDate(toDate) + "' "
                    + " AND ess.todate between '"+ PayrollUtils.getInstance().getDBCompatiableDate(fromDate) + "' AND '" + PayrollUtils.getInstance().getDBCompatiableDate(toDate) + "' "
                    + " AND ess.active=true and ess.rchrMrelay.isrotated=true order by ess.fromdate desc";
            System.out.println("Relay Hql is "+hql);
            List<RchrRelay> rlyList = OBDal.getInstance().getSession().createQuery(hql).list();

        return rlyList;
    }
    public String getDailyAttendanceHQL(Date fromDate,String employeeId){
        String hql = " SELECT att FROM RCHR_Dailyattend AS att "
                + " WHERE att.attdate ='" + PayrollUtils.getInstance().getDBCompatiableDate(fromDate) + "' "
                + " AND att.stremployee='" + employeeId + "' "
                + " AND att.active=true";
        System.out.println("RCHRDailyattend Hql is "+hql);

        return hql;
    }
    public List<RCHRDailyattend> getDailyAttendanceList(Date fromDate,String employeeId){
        List<RCHRDailyattend> rlyList = OBDal.getInstance().getSession().createQuery(getDailyAttendanceHQL(fromDate, employeeId)).list();
        return rlyList;
    }
    public List<String> getEmployeesList(Date date){
        String hql = " SELECT emp.punchno as punchno FROM Rchr_Employee AS emp WHERE emp.employeestatus='W' " +
                " AND emp.punchno NOT IN (SELECT  daily.employeeId FROM RCHR_Dailyattend AS daily " +
                " WHERE daily.attendanceDate='" + PayrollUtils.getInstance().getDBCompatiableDate(date) + "' AND daily.employeeId IS NOT NULL ) ";
        //System.out.println("RCHRDailyattend Hql is "+hql);
        List<String> rlyList = OBDal.getInstance().getSession().createQuery(hql).list();
        return rlyList;
    }
    public List<String> getEmployeesListName(Date date){
        String hql = " SELECT emp.punchno as punchno ,emp.employeeName as employeename FROM Rchr_Employee AS emp WHERE emp.employeestatus='W' " +
                " AND emp.punchno NOT IN (SELECT  daily.employeeId FROM RCHR_Dailyattend AS daily " +
                " WHERE daily.attendanceDate='" + PayrollUtils.getInstance().getDBCompatiableDate(date) + "' )";
        //System.out.println("RCHRDailyattend Hql is "+hql);
        List<String> rlyList = OBDal.getInstance().getSession().createQuery(hql).list();
        return rlyList;
    }
    public List<RCHRDailyattenddemo> getRchrDailyattenddemoList(Date date){
        String hql = " SELECT demo FROM RCHR_Dailyattenddemo AS demo " +
                " JOIN demo.rchrDailyattend AS daily " +
                " WHERE daily.validate=false AND daily.attendanceDate>='" + PayrollUtils.getInstance().getDBCompatiableDate(date) + "' )";
        List<RCHRDailyattenddemo> rlyList = OBDal.getInstance().getSession().createQuery(hql).list();
        return rlyList;
    }
    public int deleteRchrDailyattenddemoList(Date date){
        String hql = " DELETE FROM RCHR_Dailyattenddemo AS demo " +
                " WHERE demo.rchrDailyattend.id=(SELECT daily.id FROM RCHR_Dailyattend AS daily WHERE daily.validate=false" +
                " AND daily.id=demo.rchrDailyattend.id) " +
                " and demo.attendanceDate >='" + PayrollUtils.getInstance().getDBCompatiableDate(date) + "' )";
        return OBDal.getInstance().getSession().createQuery(hql).executeUpdate();
    }
    public List<EpcgYarncosttemplatelines> getYarnCostTemplateLines(Product product, RCHRWarpyarntype rchrWarpyarntype,
                                                                    EpcgVariant epcgVariant){
        String variant="";
        variant = epcgVariant==null || epcgVariant.equals("") ?  "IS NULL" :  "='"+epcgVariant.getId()+"' ";

        String hql = "SELECT lines FROM Epcg_Yarncosttemplatelines AS lines JOIN lines.epcgYarncosttemplate AS head" +
                " WHERE head.alertStatus='OP' AND lines.product.id='"+product.getId()+"' " +
                " AND lines.rchrWarpyarntype.id='"+rchrWarpyarntype.getId()+"' "+
                " AND lines.epcgVariant IS NULL ";
        //logger.info("HQL "+hql);
        return OBDal.getInstance().getSession().createQuery(hql).list();
    }
    public List<RCHRDailyattend> getGrievancesFromDailyAttendance(Date fromDate, Date toDate){
        String hql = "SELECT daily FROM RCHR_Dailyattend AS daily " +
                " WHERE daily.attendanceDate BETWEEN '"+PayrollUtils.getInstance().getDBCompatiableDate(fromDate)+ "' "+
                " AND '"+PayrollUtils.getInstance().getDBCompatiableDate(toDate) +"' "+
                " AND daily.validate=false " +
                " AND daily.isleave=false " +
                " AND daily.isgrievance=false ";
        return OBDal.getInstance().getSession().createQuery(hql).list();
    }
    public List<RCHRDailyattenddemo> getGrievancesFromDailyAttendanceDemo(Date fromDate, Date toDate){
        String hql = " SELECT demo FROM RCHR_Dailyattenddemo AS demo " +
                //" JOIN demo.rchrDailyattend AS daily " +
                " WHERE demo.present=false AND demo.overtime=false AND demo.weeklyOff=false " +
                " AND demo.attendanceDate BETWEEN '"+PayrollUtils.getInstance().getDBCompatiableDate(fromDate)+ "' "+
                " AND '"+PayrollUtils.getInstance().getDBCompatiableDate(toDate) +"' " +
                " AND demo.isgrievance=false AND demo.ispayroll=false AND demo.errorLog IS NOT NULL";

        List<RCHRDailyattenddemo> rlyList = OBDal.getInstance().getSession().createQuery(hql).list();
        return rlyList;
    }
    public double getDailyAttendanceFromGrievances(RCHRDailyattend rchrDailyattend){
        String hql = "SELECT COUNT(daily.id) FROM Rcpl_Emppprocessmanual AS daily " +
                " WHERE daily.rchrDailyattend.id= '" +rchrDailyattend.getId()+"' "
                //" AND daily.isleave=false " +
                //" AND daily.isgrievance=false ";
                + " AND 1 = 1";
        return getFirstRecordValue(hql);
    }
    public List<RCHRDailyattend> getDailyAttendanceObject(Date fromDate, String employeeId){
        String hql = "SELECT daily FROM RCHR_Dailyattend AS daily " +
                " WHERE daily.attendanceDate = '"+PayrollUtils.getInstance().getDBCompatiableDate(fromDate)+ "' "+
                " AND daily.employeeId= '"+employeeId +"' ";
        return OBDal.getInstance().getSession().createQuery(hql).list();
    }
    public List<RCHRPermission> getEmployeePermission(Date fromDate, String employeeId){

        String hql = "SELECT daily FROM RCHR_Permission AS daily " +
                " WHERE daily.permisindate = '"+PayrollUtils.getInstance().getDBCompatiableDate(fromDate)+ "' "+
                " AND daily.employee.id= '"+employeeId +"' " +
                " AND daily.alertStatus='AP' ";
        return OBDal.getInstance().getSession().createQuery(hql).list();
    }
    public List<RCPL_PayrollProcess> getPayrollProcessCompletedList(Date cancellledDate){
        String hql = "SELECT payrollProcess FROM RCPL_PayrollProcess AS payrollProcess " +
                " WHERE  payrollProcess.startingDate <= '"+PayrollUtils.getInstance().getDBCompatiableDate(cancellledDate)+"' " +
                " AND payrollProcess.endingDate >= '"+PayrollUtils.getInstance().getDBCompatiableDate(cancellledDate)+ "' AND payrollProcess.alertStatus='CO' ";
        logger.info("hql is "+hql);
        return OBDal.getInstance().getSession().createQuery(hql).list();
    }
}


