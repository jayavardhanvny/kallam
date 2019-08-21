package com.rcss.humanresource.ad_callouts;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import javax.servlet.ServletException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.BooleanList;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
//import org.openbravo.erpCommon.ad_callouts.SimpleCallout.CalloutInfo;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import com.redcarpet.payroll.util.PayrollUtils;
import com.redcarpet.payroll.util.PayrollTypesConstants;
import com.redcarpet.payroll.ad_actionbutton.AttendanceUtil;
import com.rcss.humanresource.data.*;
import com.redcarpet.payroll.data.RCPLProdIncentive;
import com.redcarpet.payroll.data.RCPL_EmpPayHead;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.data.RCPL_PayrollIncentiveLines;
import com.redcarpet.payroll.data.RCPL_PayrollTemplateLines;

public class RCHR_MobilizerGetEmp extends SimpleCallout{
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	
    	String employeeId = info.getStringParameter("inprchrEmployeeId", null);  
        RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, employeeId); 
        info.addResult("inpname",employee.getEmployeeName());
        
    }
}
