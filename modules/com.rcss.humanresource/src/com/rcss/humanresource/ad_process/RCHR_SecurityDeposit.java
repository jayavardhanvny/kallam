package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.rowset.serial.SerialException;
import com.rcss.humanresource.data.RchrAttdProcess;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrLossOfPay;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.dal.core.OBContext;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;

public class RCHR_SecurityDeposit extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	
        final String id = bundle.getParams().get("Rchr_Securitydeposite_ID").toString();
        RCHRSecuritydeposite deposit = OBDal.getInstance().get(RCHRSecuritydeposite.class, id);        
        RchrAttdProcess payrollmonth = OBDal.getInstance().get(RchrAttdProcess.class, deposit.getRchrAttdProcess().getId());
        Date strDate = payrollmonth.getStartingDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(strDate);
        for(int i = 1; i<= deposit.getTenureMonths().intValue() ; i++){
        	RCHRSecdepositelines depositlines = OBProvider.getInstance().get(RCHRSecdepositelines.class);
        	depositlines.setOrganization(deposit.getOrganization());
        	depositlines.setLineNo(Long.valueOf((i * 10)+ ""));
        	depositlines.setDueDate(cal.getTime());
        	depositlines.setDepositeamount(deposit.getDepositeamount());
        	depositlines.setRchrSecuritydeposite(deposit);
        	depositlines.setManual(false);
        	depositlines.setPaid(false);
        	cal.add(Calendar.MONTH, +1);
        	OBDal.getInstance().save(depositlines);
        }
        deposit.setProcessing(true);
        OBDal.getInstance().flush();
        }
     }
