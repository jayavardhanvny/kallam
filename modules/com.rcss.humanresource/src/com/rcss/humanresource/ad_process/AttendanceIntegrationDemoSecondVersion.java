package com.rcss.humanresource.ad_process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import com.rcss.humanresource.util.AttendanceIntegrationProcess;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.rcss.humanresource.data.RchrEmployee;

public class AttendanceIntegrationDemoSecondVersion extends DalBaseProcess implements AttendanceIntegrationProcess {
	final private static Logger logger = LoggerFactory.getLogger(AttendanceIntegrationDemoSecondVersion.class);
	@Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
			doIt(bundle);
    }
	public ArrayList getPrimaryIdsList(String query,String flag) throws SQLException{
		Connection conn = OBDal.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select rchr_attend_tempdemo_id from rchr_attend_tempdemo where validate='"+flag+"' order by stremployee,attdate;");
        ArrayList<String> primaryIds = new ArrayList<String>();
		primaryIds.add(rs.getString("rchr_attend_tempdemo_id"));
		return primaryIds;
	}
	public void doIt(ProcessBundle bundle){
		HashMap activeEmployeesList = getWorkingEmployeesList();
		String query = "";
		String flag="N";
		ArrayList<String> primaryIds = null;
		try{
			primaryIds = getPrimaryIdsList(query,flag);
		}catch(SQLException sqlException){
			logger.error("IN SQL Excetion and the Error is {}",sqlException.getMessage());
		}
	}
	// Gets the Active or current working employees... 
	private HashMap getWorkingEmployeesList(){
		OBCriteria<RchrEmployee> employeesCriteriaList = OBDal.getInstance().createCriteria(RchrEmployee.class);
		employeesCriteriaList.add(Restrictions.eq(RchrEmployee.PROPERTY_ACTIVE,true));
    	HashMap<String, RchrEmployee> employeesList = new HashMap<String, RchrEmployee>();
    	for(RchrEmployee employee : employeesCriteriaList.list()){
    		employeesList.put(employee.getPunchno(), employee);
    	}
    	return employeesList;
	}
}