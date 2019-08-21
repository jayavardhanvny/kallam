package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.data.RCHR_Emp_Loanlines;
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

public class RCHR_GasConnection extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {    	
        final String id = bundle.getParams().get("Rchr_Gas_Advance_ID").toString();
        RCHRGasAdvance advance = OBDal.getInstance().get(RCHRGasAdvance.class, id);
        RchrAttdProcess payrollmonth = OBDal.getInstance().get(RchrAttdProcess.class, advance.getRchrAttdProcess().getId());
        RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class,advance.getEmployee().getId());
        if(!(emp.isRoomAllotted()))
        {
        	throw new SerialException("Advance Sanction Only for Room alloted Members...");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(payrollmonth.getStartingDate());       
/*        if(advance.getNoofpresents().intValue()<15){        	
        	throw new SerialException("Must Have Minimum 15 Physical attendance...");
        }else{  */      	             
        	for (int i = 1; i <= advance.getNoofinstallments().intValue(); i++) {
        		System.out.println("else condition");
        		RCHRGasAdvancelines gaslines = OBProvider.getInstance().get(RCHRGasAdvancelines.class);
        		gaslines.setOrganization(advance.getOrganization());
        		gaslines.setLoanSchedule(advance);
        		gaslines.setLineNo(Long.valueOf((i * 10)+ ""));
        		gaslines.setDueDate(cal.getTime());
        		gaslines.setLoanAmount(advance.getAdvanceamount().divide(advance.getNoofinstallments(), MathContext.DECIMAL32));
        		cal.add(Calendar.MONTH, +1);
        		OBDal.getInstance().save(gaslines);
        	}
        	advance.setProcessing(true);
        	advance.setProcess(true);
        	OBDal.getInstance().flush();
//        }
     }
}