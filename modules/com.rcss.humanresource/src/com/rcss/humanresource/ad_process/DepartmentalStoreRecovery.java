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

public class DepartmentalStoreRecovery extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	
    	doIt();
    }

    	public static Connection SqlCon(){
    		// TODO Auto-generated method stub
    		Connection con=null;
    		String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
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
    			ResultSet rs = st.executeQuery("SELECT [BillNo],[MasterId] ,[MasterName],[Date],[BillAmount],[setflag] FROM [employees].[dbo].[Transactions] where setflag='"+setFlag+"'");
    			Statement stamt= sqlcon.createStatement();
    			String query = "UPDATE  [employees].[dbo].[Transactions] SET setflag=1";
    			PreparedStatement pst = null;
    			int j = 0;
    			Long start = System.currentTimeMillis();
    			
    			
    			while(rs.next())
    			{   // System.out.println("str employee "+rs.getString(2));
    				 int flag = rs.getInt(6);
    				 String orgId="256551BD83DF49DB80BCE5691149CA0B";
    				 String strDate = rs.getString(4).toString();
    				// System.out.println("strDate " +strDate);
    				 Date date = null;
    				 Calendar calDate = Calendar.getInstance();
    				 String fromDate = null;
    				 String toDate = null;
    				 
    				 try{
    					 //Parsing the String  
    					 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    					 //Parsing the date and setting after parsing the string date...
    					 date = sdf.parse(strDate);
    					 
    					
    				 }catch(ParseException e){
    					 
    				 }
    				 calDate.setTime(date);
    				 //System.out.println("calDate.getTime() " +calDate.getTime());
    				 calDate.set(Calendar.DATE, calDate.getActualMinimum(Calendar.DATE));
    				 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    				
    				 
    				 
    				 
    				 try{
    					 //Parsing the String  
    					 
    					 //Parsing the date and setting after parsing the string date...
    					 
    					 fromDate = sdf2.format(calDate.getTime());
    					 
    				 }catch(Exception e){
    					 
    				 }
    				 
    				// System.out.println("fromDate " +fromDate);
    				 calDate.set(Calendar.DATE, calDate.getActualMaximum(Calendar.DATE));
    				
    				 
    				 try{
    					 //Parsing the String  
    					 
    					 //Parsing the date and setting after parsing the string date...
    					 toDate = sdf2.format(calDate.getTime());
    					 
    				 }catch(Exception e){
    					 
    				 }
    				 
    				 
    				 /*
    				 System.out.println("toDate " +toDate);
    				 System.out.println("Fromdate String Values " +fromDate.toString());
    				 System.out.println("toDate String Values " +toDate.toString());
    				 System.out.println("rs.getDouble(5) Values " +rs.getDouble(5));
    				 System.out.println("Bigdecimal String Values " +rs.getBigDecimal(5));
    				 System.out.println("Iteger Values " +rs.getInt(5));
    				 */
    				 
    				 
    				 try{
    					 
    				 
    				if(flag==0 && rs.getString(2)!=null)
    				{ 
    					
    					if(!rs.getString(2).toString().contains("Del")){
    						//Get the Employee id based on Organisation from employee records...
    						String employeeId=getEmployeeId(rs.getString(2),orgId);
    						//System.out.println("employeeId -- Got from Employee Records " +toDate.toString());
    						
    						//Preparing Prepared Statement...
    				String stm = "INSERT INTO rcpl_departmentstore(rcpl_departmentstore_id, ad_client_id, ad_org_id, createdby, updatedby, rchr_employee_id, billno, recoveramount," +
    						" recoverdate, fromdate, todate) " +
    							" VALUES(get_uuid(),?, ?, ?, ?, ?, ?, to_number(?), to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS'), to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS'), to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS')) ";
    		            pst = postgrescon.prepareStatement(stm);
    		            pst.setString(1, "15E93AAB4F104C458EEA18F6672CF5DD");//Ad_client_id===
    		            pst.setString(2, orgId);//ad_org_id===
    		            pst.setString(3, "100");//createdby
    		            pst.setString(4, "100");//updatedby
    		            pst.setString(5, employeeId);//rchr_employee_id
    		            pst.setString(6, rs.getString(1).toString());//billno === [BillNo]
    		            pst.setString(7, rs.getString(5).toString());//recoveramount === [BillAmount]
    		            pst.setString(8, rs.getString(4).toString());//recoverdate === [Date]
    		            pst.setString(9, fromDate.toString());//fromdate
    		            pst.setString(10,toDate.toString());//todate
    		            
    		            pst.executeUpdate();
    		            
    		            j++;
    					}  
    				}
    				 }catch(SQLException sql){
    					 pst.cancel();
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
    	 
    	 public String getEmployeeId(String empid,String orgId){
    		 Connection postConn = OBDal.getInstance().getConnection();
    		 String employeeId = "";
    		 try{
    			 Statement stmt = postConn.createStatement();
    			 ResultSet rs = stmt.executeQuery("SELECT rchr_employee_id FROM rchr_employee WHERE documentno='"+empid+"' and ad_org_id='"+orgId+"'");
    			 while(rs.next())
     			{ 
    				 employeeId=rs.getString("rchr_employee_id");
     			}
    			 
    		 }catch(Exception e){
    			 e.printStackTrace();
    		 }
    		 //System.out.println("employeeId "+employeeId);
    		 return employeeId;
    	 }
}