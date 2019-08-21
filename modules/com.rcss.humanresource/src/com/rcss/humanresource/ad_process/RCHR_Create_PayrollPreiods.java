package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.RchrAttdProcess;
import com.rcss.humanresource.data.RchrYear;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
 *
 * @author S.A. Mateen
 */
public class RCHR_Create_PayrollPreiods extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        OBContext.setAdminMode();
        OBError err = new OBError();
        Process(bundle);
        err.setMessage("Process Completed Successfully");
        err.setTitle("Success");
        err.setType("Success");
        bundle.setResult(err);
        OBContext.restorePreviousMode();
    }

    private void Process(ProcessBundle bundle) {
        String id = bundle.getParams().get("Rchr_Year_ID").toString();
        RchrYear year = OBDal.getInstance().get(RchrYear.class, id);
        List<Period> periodList = year.getYear().getFinancialMgmtPeriodList();
        int periodNo = 10;
        if (year.getType().equals(PeriodUtil.PERIOD_MONTHLY)) {
            for (Period p : periodList) {
                RchrAttdProcess hrPeriod = OBProvider.getInstance().get(RchrAttdProcess.class);
                hrPeriod.setAttendanceprocess(true);
			    hrPeriod.setOrganization(year.getOrganization());
                hrPeriod.setStartingDate(p.getStartingDate());
                hrPeriod.setEndingDate(p.getEndingDate());
                hrPeriod.setMonthname(new PeriodUtil().getMonthName(p.getStartingDate()));
                hrPeriod.setPeriodNo(new BigDecimal(periodNo));
                hrPeriod.setProcessingdays(new PeriodUtil().getProcessingDays(p.getStartingDate(), p.getEndingDate()));
                hrPeriod.setIncentive(true);
                hrPeriod.setRchrYear(year);
                hrPeriod.setType(year.getType());
                OBDal.getInstance().save(hrPeriod);
                periodNo += 10;
            }
        } else if (year.getType().equals(PeriodUtil.PERIOD_BIWEEKLY)) {
            for (Period p : periodList) {
                RchrAttdProcess hrPeriod = OBProvider.getInstance().get(RchrAttdProcess.class);
                hrPeriod.setAttendanceprocess(true);
				hrPeriod.setOrganization(year.getOrganization());
                hrPeriod.setStartingDate(p.getStartingDate());
                Calendar cal = Calendar.getInstance();
                cal.setTime(p.getStartingDate());
                cal.add(Calendar.DATE, +14);
                hrPeriod.setEndingDate(cal.getTime());
                hrPeriod.setMonthname(new PeriodUtil().getMonthName(p.getStartingDate()));
                hrPeriod.setPeriodNo(new BigDecimal(periodNo));
                hrPeriod.setProcessingdays(new PeriodUtil().getProcessingDays(hrPeriod.getStartingDate(), hrPeriod.getEndingDate()));
                hrPeriod.setIncentive(false);
                hrPeriod.setRchrYear(year);
                hrPeriod.setType(year.getType());
                OBDal.getInstance().save(hrPeriod);
                periodNo += 10;
                RchrAttdProcess hrPeriod2 = OBProvider.getInstance().get(RchrAttdProcess.class);
                hrPeriod2.setAttendanceprocess(true);
				hrPeriod2.setOrganization(year.getOrganization());
                Calendar cal2 = Calendar.getInstance();
                cal2.setTime(hrPeriod.getEndingDate());
                cal2.add(Calendar.DATE, +1);
                hrPeriod2.setStartingDate(cal2.getTime());
                hrPeriod2.setEndingDate(p.getEndingDate());
                hrPeriod2.setMonthname(new PeriodUtil().getMonthName(p.getStartingDate()));
                hrPeriod2.setPeriodNo(new BigDecimal(periodNo));
                hrPeriod2.setProcessingdays(new PeriodUtil().getProcessingDays(hrPeriod2.getStartingDate(), hrPeriod2.getEndingDate()));
                hrPeriod2.setIncentive(true);
                hrPeriod2.setRchrYear(year);
                hrPeriod2.setType(year.getType());
                OBDal.getInstance().save(hrPeriod2);
                periodNo += 10;

            }
        } else {
            System.out.println("Invalid period type");
        }
    }

    public class PeriodUtil {

        private SimpleDateFormat sdf;
        private final static String PERIOD_MONTHLY = "Monthly";
        private final static String PERIOD_BIWEEKLY = "Fifteen Days";

        public PeriodUtil() {
            sdf = new SimpleDateFormat("MMM-yy");
        }

        public String getMonthName(Date date) {
            return sdf.format(date);
        }

        public Long getProcessingDays(Date start, Date end) {
            int days = 0;
            if (start == null || end == null) {
                days = 0;
            }
            days = Days.daysBetween(new DateTime(start), new DateTime(end)).getDays() + 1;
            return new Long(days);
        }
    }
}
