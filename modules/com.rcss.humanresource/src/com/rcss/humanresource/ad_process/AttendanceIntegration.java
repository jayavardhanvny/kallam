package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.RchrAttendLine;
import com.rcss.humanresource.data.RchrAttendance;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrAttdProcess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.*;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class AttendanceIntegration extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	
    	doIt();
    }

    	public static Connection SqlCon(){
    		// TODO Auto-generated method stub
    		Connection con=null;
    		String connectionUrl = "jdbc:sqlserver://172.16.0.47:1433;" +
                    "databaseName=esslsmartoffice;user=sa;password=ksml@123";
    		try
    		{   
    			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    			String userName = "sa";
                String password = "kallam";
                String url = "jdbc:sqlserver://localhost:1433;databaseName=etimetracklite1;integratedSecurity=true";
                //con = DriverManager.getConnection(url, userName, password);
                con = DriverManager.getConnection(connectionUrl);
                
    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    		return con;
    	}
    	 public static Connection PostgresCon() {
    		 Connection connection = null;
    		 try {
    			 Class.forName("org.postgresql.Driver");
    			
    				connection = DriverManager.getConnection(
    						"jdbc:postgresql://localhost:5432/production", "postgres", "rcss@123");
    			}
    		 catch(Exception e)
    			{
    				e.printStackTrace();
    			}
    		 return connection;
    	 }
    	 private void doIt()
    	{   
    		 System.out.println("doIt() method is starting.....!");
    		Connection postgrescon =  PostgresCon();
    		System.out.println("connection is " +postgrescon);
    		Connection sqlcon = SqlCon();
    		System.out.println("connection is " +sqlcon);
    		
    		try
    		{   
    			Statement st = sqlcon.createStatement();
    			int setFlag = 0;
    			ResultSet rs = st.executeQuery("select AttendanceLogId, (select EmployeeCode from Employees where EmployeeId=AttendanceLogs.EmployeeId) as employeeid, " +
						"attendancedate, intime, outtime, ReportPunchRecords, setflag from AttendanceLogs where setflag='"+setFlag+"'");
    			Statement stamt= sqlcon.createStatement();
    			String query = "UPDATE  attendancelogs SET setflag=1";
    			PreparedStatement pst = null;
    			int j = 0;
    			Long start = System.currentTimeMillis();
    			while(rs.next())
    			{   // System.out.println("str employee "+rs.getString(2));
    				 int flag = rs.getInt(7);
    				if(flag==0 && rs.getString(2)!=null)
    				{ 
    					
    					
    					if(!rs.getString(2).toString().contains("Del")){
    						
    				String stm = "INSERT INTO rchr_attend_tempdemo(rchr_attend_tempdemo_id, ad_client_id, ad_org_id, createdby, updatedby, stremployee, attdate, errorlog, strshift) " +
    							" VALUES(get_uuid(),?, ?, ?, ?, ?, to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS'), ?, ?) ";
    		            pst = postgrescon.prepareStatement(stm);
    		            pst.setString(1, "15E93AAB4F104C458EEA18F6672CF5DD");
    		            pst.setString(2, "256551BD83DF49DB80BCE5691149CA0B");
    		            pst.setString(3, "100");
    		            pst.setString(4, "100");
    		            pst.setString(5, rs.getString(2).toString());
    		            pst.setString(6, rs.getString(3).toString()); 
    		          //  pst.setString(7, rs.getString(4).toString());
    		           // pst.setString(8, rs.getString(5).toString());
    		            pst.setString(7, rs.getString(6).toString());
    		            pst.setString(8, "Day Shift");
    		            
    		            pst.executeUpdate();
    		            
    		            j++;
    					}  
    			}
    			
    		}
    			stamt.executeUpdate(query);
    			Long end = System.currentTimeMillis();
    			Long time = end - start;
    			 System.out.println("time "+time);
    			 
    			System.out.println("Update " +j);
    		}
    		 catch(Exception e)
    			{
    				e.printStackTrace();
    			}
    	 
    	}
}