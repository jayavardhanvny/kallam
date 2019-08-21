package com.rcss.humanresource.ad_callouts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import org.openbravo.model.ad.domain.Callout;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import java.util.Calendar;
import org.apache.commons.lang.time.DateUtils;
import org.openbravo.base.session.OBPropertiesProvider;
import org.apache.commons.lang.StringUtils;
import com.rcss.humanresource.data.RCHR_Leavetype;
import com.rcss.humanresource.data.RchrEmployee;

public class RCHRLeavesRequisition extends SimpleCallout{
	@Override
	public void execute(CalloutInfo info) throws ServletException{
		String strEmployeeId = info.getStringParameter("inprchrEmployeeId", null);
		RchrEmployee e=OBDal.getInstance().get(RchrEmployee.class,strEmployeeId);
		String strFromDate = info.getStringParameter("inpfromdate", null);
		System.out.println("dsate..."+strEmployeeId+" "+strFromDate);
		try {

			Date fromDate=new SimpleDateFormat("dd-MM-yyyy").parse(strFromDate);
			info.addResult("inprchrEmpDeptId",e.getEmployeeDepartment().getId());
			info.addResult("inprchrSubdepartmentId",e.getSubDepartment().getId());
			info.addResult("inprchrDesignationId",e.getDesignation().getId());
			info.addResult("inpbalancesl",getSickLeavesBalances(strEmployeeId,fromDate));
			info.addResult("inpbalancecl",getCasualLeavesBalances(strEmployeeId,fromDate));
			info.addResult("inpbalancecoffs",getCompensateBalances(strEmployeeId,fromDate));


		}
		catch (Exception ex) {
			    ex.printStackTrace();
		}
		
		
	}

	public long getSickLeavesBalances(String employeeId,Date fromDate){

		String hql=" SELECT COALESCE(sum(info.leavecount),0) FROM RCHR_LeaveBalanceHistory info"
				+" WHERE info.employee.id='"+employeeId+"' AND info.leavedate<='"+fromDate+"' AND info.leaveType.id='3963E0F3BA314420A7EA2DA537917460' ";
		//System.out.println("EL leaves..."+OBDal.getInstance().getSession().createQuery(hql).list().get(0));
		int size=OBDal.getInstance().getSession().createQuery(hql).list().size();
		return size>0?(long)OBDal.getInstance().getSession().createQuery(hql).list().get(0):0;
	}

	public long getCasualLeavesBalances(String employeeId,Date fromDate){
		String hql=" SELECT COALESCE(sum(info.leavecount),0) FROM RCHR_LeaveBalanceHistory info"
				+" WHERE info.employee.id='"+employeeId+"' AND info.leavedate<='"+fromDate+"' AND info.leaveType.id='0A4A7C4967784E67B231E2567960C413' ";
		//System.out.println("CL leaves..."+OBDal.getInstance().getSession().createQuery(hql).list().get(0));
		int size=OBDal.getInstance().getSession().createQuery(hql).list().size();
		return size>0?(long)OBDal.getInstance().getSession().createQuery(hql).list().get(0):0;
	}

	public long getCompensateBalances(String employeeId,Date fromDate){
		String hql=" SELECT count(info.id) FROM RCHR_CompensateOff info"
				+" WHERE info.employee.id='"+employeeId+"' AND info.alertStatus='ACC' ";
		System.out.println("Compensate Offs..."+OBDal.getInstance().getSession().createQuery(hql).list().get(0));
		return (long)OBDal.getInstance().getSession().createQuery(hql).list().get(0);
	}
}
