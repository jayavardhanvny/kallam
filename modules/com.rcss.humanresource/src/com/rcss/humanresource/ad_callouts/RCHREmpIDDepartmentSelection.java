package com.rcss.humanresource.ad_callouts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.rcss.humanresource.data.RCHR_Bank;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import com.rcss.humanresource.data.RchrAttendLine;
import com.rcss.humanresource.data.RchrAttendance;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrEmpDept;

/**
 *
 * @author Suneetha
 */
public class RCHREmpIDDepartmentSelection extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	 String strAttendanceId = info.getStringParameter("inprchrAttendanceId", null);
        String employeeId = info.getStringParameter("inprchrEmployeeId", null);
        
        RchrAttendance attend = OBDal.getInstance().get(RchrAttendance.class, strAttendanceId);
        List<RchrAttendLine> lines = attend.getRchrAttendLineList();
        
        for(RchrAttendLine line : lines){
           String empl= line.getRchrEmployee().getId();
           if(employeeId.equals(empl))
           {
        	   String strot="Y";
        	   info.addResult("inpisovertime", strot);
           }
        }
        
        RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, employeeId);
        
        String empdocno=emp.getDocumentNo();
        info.addResult("inpempid", empdocno);
        System.out.println("dptname is" + emp.getEmployeeDepartment().getCommercialName());
        info.addResult("inpdeptname", emp.getEmployeeDepartment().getCommercialName());
        
        /*
        String deptId="";
        try {		
            Connection conn = OBDal.getInstance().getConnection();
            Statement stmt2 = conn.createStatement();
            String sqry2 = "select rchr_emp_dept_id from rchr_emp_job where (rchr_employee_id='" + employeeId + "') ";
            ResultSet rs2 = stmt2.executeQuery(sqry2);
            
            while (rs2.next()) {
            	deptId=rs2.getString("rchr_emp_dept_id");
                //System.out.println("deptId is" + deptId);

        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(deptId != null)
        {
        RchrEmpDept dept=OBDal.getInstance().get(RchrEmpDept.class, deptId);
        if(dept != null)
        {
        String dptname=dept.getCommercialName();
        System.out.println("dptname is" + dptname);
        info.addResult("inpdeptname", dptname);
        }
        }
        else
        {
        	String deptn="";
        	info.addResult("inpdeptname", deptn);
        }
        */
        
        
        
    }
    
}
