package com.rcss.humanresource.ad_callouts;
import java.math.BigDecimal;

import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.hibernate.criterion.Restrictions;
import com.rcss.humanresource.data.RCHR_LeaveOpenBalance;
import com.rcss.humanresource.data.RCHR_Leavetype;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrEmployeeleaveBal;

public class RCHRNoOfLeavesRemained extends SimpleCallout{
	public void execute(CalloutInfo info){
		String employeeId = info.getStringParameter("inprchrEmployeeId", null);
//		String rchrLeaveId = info.getStringParameter("inprchrLeavetypeId", null);
		RchrEmployee rchrEmployee = OBDal.getInstance().get(RchrEmployee.class, employeeId);
/*		RCHR_Leavetype rchrLeave = OBDal.getInstance().get(RCHR_Leavetype.class, rchrLeaveId);
		if(Boolean.TRUE.equals(rchrLeave.isLop())){
		OBCriteria<RchrEmployeeleaveBal> rchrEmpLeave = OBDal.getInstance().createCriteria(RchrEmployeeleaveBal.class);
		rchrEmpLeave.add(Restrictions.eq(RchrEmployeeleaveBal.PROPERTY_EMPLOYEE,rchrEmployee));
		//rchrEmpLeave.add(Restrictions.eq(RchrEmployeeleaveBal.PROPERTY_LEAVETYPE,rchrLeave));
		BigDecimal leaveBalance = new BigDecimal(0);
		for(RchrEmployeeleaveBal rchrEmployeeLeave: rchrEmpLeave.list()){
			
			if(Boolean.TRUE.equals(rchrEmployeeLeave.getLeaveType().isCarryforward())){
			 leaveBalance = rchrEmployeeLeave.getLobalance();
			}
		}
		
		info.addResult("inpleaveopening", leaveBalance);
		}else*/
//		{
			OBCriteria<RchrEmployeeleaveBal> rchrEmpLeave = OBDal.getInstance().createCriteria(RchrEmployeeleaveBal.class);
			rchrEmpLeave.add(Restrictions.eq(RchrEmployeeleaveBal.PROPERTY_EMPLOYEE,rchrEmployee));
//			rchrEmpLeave.add(Restrictions.eq(RchrEmployeeleaveBal.PROPERTY_LEAVETYPE,rchrLeave));
			BigDecimal leaveBalance = new BigDecimal(0);
			for(RchrEmployeeleaveBal rchrEmployeeLeave: rchrEmpLeave.list()){
				
				leaveBalance = leaveBalance.add(rchrEmployeeLeave.getLobalance());
			}
			info.addResult("inpemployeetype", rchrEmployee.getEmployeeType());
			info.addResult("inpleaveopening", leaveBalance);
//		}
	}
}