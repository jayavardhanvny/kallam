package com.redcarpet.payroll.ad_actionbutton;

import com.rcss.humanresource.data.RchrEmployee;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.data.RCPL_Processsw;
import com.redcarpet.payroll.data.RCPL_ProcessSWLine;
import com.redcarpet.payroll.data.RCPL_ServiceWeightage;
import com.redcarpet.payroll.util.PayrollUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author S.A. Mateen
 */

@SuppressWarnings("unchecked")
public class RCPL_ProcessServiceWeightage extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rcpl_Processsw_ID").toString();
        RCPL_Processsw sw = OBDal.getInstance().get(RCPL_Processsw.class, id);

        OBContext.setAdminMode();
        OBError err = new OBError();
        err.setMessage("Process Completed Successfully");
        err.setTitle("Success");
        err.setType("Success");

        try {
            doIt(sw);
        } catch (Exception e) {
            err.setMessage(e.getMessage());
            err.setTitle("Error");
            err.setType("Error");
            bundle.setResult(err);
            e.printStackTrace();
        }

        OBContext.restorePreviousMode();
        bundle.setResult(err);
    }

    private void doIt(RCPL_Processsw sw) throws Exception {

        Date fromDate = sw.getFromDate();
        Date toDate = sw.getToDate();
        int difference = PayrollUtils.getInstance().getDaysDifference(fromDate, toDate);
        String strFromDate = PayrollUtils.getInstance().getDBCompatiableDate(fromDate);
        String strToDate = PayrollUtils.getInstance().getDBCompatiableDate(toDate);
        if (difference < 80) {
            throw new Exception("From date and to date is less than 80");
        }
        processItForFresher(sw, strFromDate, strToDate);
        processItForOthers(sw, strFromDate, strToDate);
    }

    private void processItForFresher(RCPL_Processsw sw, String strFromDate, String strToDate) throws ServletException {
        List<RCPL_ServiceWeightage> mastersList = OBDal.getInstance().createCriteria(RCPL_ServiceWeightage.class).list();
        for (RCPL_ServiceWeightage serv : mastersList) {
            RCPLServiceWeightageHelperData[] data = RCPLServiceWeightageHelperData.selectFreshers(new DalConnectionProvider(), serv.getEmployeeDepartment().getId(), serv.getSkill().getId());
            for (RCPLServiceWeightageHelperData emp : data) {
                String strEmployeeId = emp.rchrEmployeeId;
                if (StringUtils.equals("7807B0CC83E849CCACA74B45B6C557BA", strEmployeeId)) {
                    double no_of_days_present = getNoOfDaysPresent(strEmployeeId, strFromDate, strToDate);
                    System.out.println("no_of_days_present " + no_of_days_present + " stremployee " + strEmployeeId);
                    if (no_of_days_present >= 80) {
                        double sal = no_of_days_present * serv.getRate().doubleValue();
                        insertWeightageLine(sw, strEmployeeId, no_of_days_present, serv.getRate());
                    }
                }
            }
        }
    }

    private void processItForOthers(RCPL_Processsw sw, String strFromDate, String strToDate) throws ServletException {
        List<RCPL_ServiceWeightage> mastersList = OBDal.getInstance().createCriteria(RCPL_ServiceWeightage.class).list();
        for (RCPL_ServiceWeightage serv : mastersList) {
            RCPLServiceWeightageHelperData[] data = RCPLServiceWeightageHelperData.selectOtherEmployees(new DalConnectionProvider(), serv.getEmployeeDepartment().getId(), serv.getSkill().getId());
            for (RCPLServiceWeightageHelperData emp : data) {
                String strEmployeeId = emp.rchrEmployeeId;
                double no_of_days_present = getNoOfDaysPresent(strEmployeeId, strFromDate, strToDate);
                if (no_of_days_present > 365) {
                    double sal = no_of_days_present * serv.getRate().doubleValue();
                    insertWeightageLine(sw, strEmployeeId, no_of_days_present, serv.getRate());
                }
            }
        }
    }

    public double getNoOfDaysPresent(String strEmployeeId, String strStartDate, String strEndDate) {
        String hql = "SELECT COUNT(aline.id) from Rchr_Attend_Line AS aline "
                + " JOIN aline.rchrAttendance AS att "
                + " WHERE aline.rchrEmployee.id = '" + strEmployeeId + "' "
                + " AND aline.present = true "
                + " AND att.fromdate BETWEEN '" + strStartDate + "' AND  '" + strEndDate + "' "
                + " AND 1 = 1";
        System.out.println(hql);
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        return Double.valueOf(li.get(0).toString());
    }

    private void insertWeightageLine(RCPL_Processsw sw, String strEmployeeId, double no_of_days_present, BigDecimal rate) throws ServletException {
        RCPL_ProcessSWLine line = OBProvider.getInstance().get(RCPL_ProcessSWLine.class);
        line.setProcessServiceWeightage(sw);
        line.setEmployee(OBDal.getInstance().get(RchrEmployee.class, strEmployeeId));
        line.setLineNo(getLineNo(sw.getId()));
        line.setNoOfDaysPresent(new BigDecimal(no_of_days_present));
        line.setProcess(Boolean.TRUE);
        line.setSalary(getEmployeeSalary(strEmployeeId, true));
        line.setRateCalculated(rate);
        OBDal.getInstance().save(line);
    }

    private Long getLineNo(String id) throws ServletException {
        String strLineNo = RCPLServiceWeightageHelperData.getLineNo(new DalConnectionProvider(), id);
        return Long.valueOf(strLineNo);
    }

    private BigDecimal getEmployeeSalary(String strEmployeeId, boolean isPerDay) {
        BigDecimal retVal = new BigDecimal(BigInteger.ZERO);
        String hql = " SELECT ess FROM RCPL_EmpSalSetup ess "
                + " WHERE ess.employee.id='" + strEmployeeId + "' "
                + " AND ess.active=true";
        List<RCPL_EmpSalSetup> empList = OBDal.getInstance().getSession().createQuery(hql).list();
        if (empList.size() > 0) {
            if (isPerDay) {
                retVal = empList.get(0).getPerDaySalary();
            } else {
                retVal = empList.get(0).getGrossAmtPerYear();
            }
        }
        return retVal;
    }
}
