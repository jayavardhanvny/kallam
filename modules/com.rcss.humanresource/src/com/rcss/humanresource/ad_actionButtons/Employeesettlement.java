package com.rcss.humanresource.ad_actionButtons;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.redcarpet.payroll.util.PayrollUtils;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.hibernate.criterion.Restrictions;

import com.rcss.humanresource.data.*;
import com.redcarpet.payroll.data.RCPL_EmpPayDead;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.payroll.data.RCPL_PayDeducitionsHead;


public class Employeesettlement extends DalBaseProcess {
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		String strEmpSet = (String)bundle.getParams().get("Rchr_Employeesettlement_ID");
		//System.out.println("id is...!"+strEmpSet);
		RCHR_Employeesettlement rchrEmployeeSettle = OBDal.getInstance().get(RCHR_Employeesettlement.class, strEmpSet);
		String EmployeeId = rchrEmployeeSettle.getEmployee().getId();

		rchrEmployeeSettle.getEmployee().setEmployeestatus(rchrEmployeeSettle.getEmployeestatus());
		OBCriteria<RCHR_Employeesettlement> rchrempset = OBDal.getInstance().createCriteria(RCHR_Employeesettlement.class);
		rchrempset.add(Restrictions.eq(RCHR_Employeesettlement.PROPERTY_ID, strEmpSet));

		for(RCHR_Employeesettlement empsettle : rchrempset.list()){
			long gross = (empsettle.getPresentdays()*empsettle.getPerdaysalary().longValue())+(empsettle.getProductionincnetive().longValue())+(empsettle.getOtherincnetive().longValue());
			empsettle.setGross(new BigDecimal(gross));	
			BigDecimal db1 = new BigDecimal((gross-empsettle.getTotaldeduction().longValue()));
			empsettle.setGrandTotalAmount(db1);
			empsettle.setCompleted(Boolean.TRUE);
		}
		Connection conn = OBDal.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        //ResultSet rs = stmt.executeQuery(sql);
        


        if (rchrEmployeeSettle.getEmployee().isRoomAllotted()){
			stmt.executeUpdate(" update rchr_room_employee set vacateddate='"+PayrollUtils.getInstance().getDBCompatiableDate(rchrEmployeeSettle.getSettlementdate())+"', " +
					" isvacating='Y', deallocate='Y' where rchr_room_employee_id=(select rchr_room_employee_id from rchr_room_employee " +
					" where rchr_employee_id='"+EmployeeId+"' order by allocateddate desc limit 1 )");
		}

		// To get Room vacate if all the employees went
	if (rchrEmployeeSettle.getEmployee().isRoomAllotted())
			setRoomVacate(rchrEmployeeSettle.getEmployee().getRoom());

		stmt.executeUpdate("update rchr_employee set employeestatus='R', salarystatus='CL', rchr_room_id=null, isallotment='N' where rchr_employee_id = '"+EmployeeId+"'");
        stmt.executeUpdate(" update rchr_settlemntlines set completed = 'Y' where rchr_employeesettlement_id = '"+strEmpSet+"'");
		rchrEmployeeSettle.setProcess(Boolean.TRUE);

        final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Settlement Process Successfully");
        bundle.setResult(msg);
       
	}
	public void setRoomVacate(RCHR_Room rchrRoom){
		OBCriteria<RchrRoomEmployee> rchrRoomEmployeeOBCriteria = OBDal.getInstance().createCriteria(RchrRoomEmployee.class);
		rchrRoomEmployeeOBCriteria.add(Restrictions.eq(RchrRoomEmployee.PROPERTY_ROOM, rchrRoom));
		rchrRoomEmployeeOBCriteria.add(Restrictions.eq(RchrRoomEmployee.PROPERTY_ISVACATING,Boolean.FALSE));
		if(rchrRoomEmployeeOBCriteria.list().size()==0){
			rchrRoom.setVacant(Boolean.TRUE);
			//rchrRoomEmployee.getRoom().setNoofemployees(new BigDecimal(0));
		}
	}
}


