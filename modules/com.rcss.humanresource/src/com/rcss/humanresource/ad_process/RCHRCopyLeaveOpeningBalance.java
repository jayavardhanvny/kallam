package com.rcss.humanresource.ad_process;


import java.math.BigDecimal;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.hibernate.criterion.Restrictions;
import com.rcss.humanresource.data.RCHR_LeaveOpenBalance;
import com.rcss.humanresource.data.RCHR_Leavetype;
import com.rcss.humanresource.data.RchrDesignation;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrEmployeeleaveBal;
//import com.rcss.humanresource.data.RchrEmployeeleaveBal;

public class RCHRCopyLeaveOpeningBalance extends DalBaseProcess {
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		System.out.println("leave process is starting..!");
		final OBCriteria<RCHR_LeaveOpenBalance> rchrLeaveOpenBalanceCriteria = OBDal.getInstance().createCriteria(RCHR_LeaveOpenBalance.class);
		
		for(RCHR_LeaveOpenBalance rchrLeaveOpenBalance: rchrLeaveOpenBalanceCriteria.list()){
			
			
			RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class, rchrLeaveOpenBalance.getEmployee().getId());
			
			OBCriteria<RchrEmployeeleaveBal> rchrEmloyeeLeavBalCriteria = OBDal.getInstance().createCriteria(RchrEmployeeleaveBal.class);
			rchrEmloyeeLeavBalCriteria.add(Restrictions.eq(RchrEmployeeleaveBal.PROPERTY_EMPLOYEE, employee));
			rchrEmloyeeLeavBalCriteria.add(Restrictions.eq(RchrEmployeeleaveBal.PROPERTY_LEAVETYPE, rchrLeaveOpenBalance.getLeaveType()));
			rchrEmloyeeLeavBalCriteria.add(Restrictions.eq(RchrEmployeeleaveBal.PROPERTY_ACTIVE, Boolean.TRUE));
		
			if(rchrEmloyeeLeavBalCriteria.list().size()==0){
				RchrEmployeeleaveBal rchrEmpLeaveBal = OBProvider.getInstance().get(RchrEmployeeleaveBal.class);
				rchrEmpLeaveBal.setOrganization(employee.getOrganization());
				rchrEmpLeaveBal.setEmployee(employee);
				rchrEmpLeaveBal.setLeavesPerYear(rchrLeaveOpenBalance.getLeavesPerYear());
				rchrEmpLeaveBal.setLeaveType(rchrLeaveOpenBalance.getLeaveType());
				rchrEmpLeaveBal.setLobalance(rchrLeaveOpenBalance.getLeavesPerYear());
				OBDal.getInstance().save(rchrEmpLeaveBal);
			}
			else{
			for(RchrEmployeeleaveBal employeeLeaveBal : rchrEmloyeeLeavBalCriteria.list()){
				
				employeeLeaveBal.setLobalance(employeeLeaveBal.getLobalance().add(rchrLeaveOpenBalance.getLeavesPerYear()));
				employeeLeaveBal.setLeavesPerYear(rchrLeaveOpenBalance.getLeavesPerYear());
			}
			}
			
			
		}
	}
}