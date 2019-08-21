package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
import com.redcarpet.goodsissue.data.RCGI_MiLines;
import com.redcarpet.payroll.data.RCPL_EmpSalSetup;
import com.redcarpet.production.data.RCPRShift;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.sql.Timestamp;
import java.util.Calendar;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import com.timeutils.sam.TimeDiffUtil;
import javax.servlet.ServletException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBDal;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
//import org.openbravo.dal.service.OBDal;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
//import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
*
* @author Tirumal
*/

public class AttendanceIntegrationDemo extends DalBaseProcess {
	protected Logger logger = Logger.getLogger("AttendanceIntegrationDemo.java");
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

    	//System.out.println("in processss.....!!!");  
    	
    	
    	//List<RCHRAttendTempdemo> attndList = OBDal.getInstance().createCriteria(RCHRAttendTempdemo.class).list();
    	
    	OBCriteria<RchrEmployee> employeeList = OBDal.getInstance().createCriteria(RchrEmployee.class);
    	employeeList.add(Restrictions.eq(RchrEmployee.PROPERTY_ACTIVE,true));
    	
    	HashMap<String, RchrEmployee> employeeHashMapList = new HashMap<String, RchrEmployee>();
    	
    	for(RchrEmployee employee : employeeList.list()){
    		employeeHashMapList.put(employee.getPunchno(), employee);
    		
    	}
    	logger.info("HashMap Size " + employeeHashMapList.size());
    	
    	Connection conn = OBDal.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        String setFlag="N";
        ResultSet rs = stmt.executeQuery("select rchr_attend_tempdemo_id from rchr_attend_tempdemo where validate='"+setFlag+"' order by stremployee,attdate");
        int flag=0;
        Long startBeforeWhile = System.currentTimeMillis();
        
        while (rs.next()) {
        	
            String demoId = rs.getString("rchr_attend_tempdemo_id");
            RCHRAttendTempdemo tmpdemo = OBDal.getInstance().get(RCHRAttendTempdemo.class, demoId);
			// Boolean validate=new Boolean(Boolean.FALSE);
            
            if(employeeHashMapList.containsKey(tmpdemo.getEmployeeId())){
            	RchrEmployee emp = (RchrEmployee)employeeHashMapList.get(tmpdemo.getEmployeeId());
            	
			 Boolean validate = tmpdemo.isValidate();
			
			 if(Boolean.FALSE.equals(tmpdemo.isValidate()) && Boolean.TRUE.equals(tmpdemo.isActive())){
				 //String punchRecords="00:00:00(in),00:00:00(out)";
				 String punchRecords=tmpdemo.getErrorLog();
				 if(punchRecords==null)
				 {
					 punchRecords="00:00:00(in),00:00:00(out)";
				 }
				 String[] temp;
				 //String[] inouttemp;
				 String[] outtemp;
				 String delimiter = ",";
				 temp = punchRecords.split(delimiter);
				 String[] inouttemp=punchRecords.split(delimiter);
				 int lngth=temp.length;
				 System.out.println("length is.."+lngth);
				 String punchIn="00:00:00";
				 String punchInDemo=null;
				 String nextpunchIn="00:00:00";
				 String nextpunchOut="00:00:00";
				 String punchOut="00:00:00";
				 String breakOut="00:00:00";     
				 String breakIn="00:00:00";
				 String punchOutDemo=null;
				 String absent="00:00:00";
				 
				  if(lngth==4){
				  for(int i =0; i < lngth ; i++){
					  if(temp[i].contains("(in)"))
					   {	
						    inouttemp[i]=temp[i];
				  			temp[i]=temp[i].replace("(in)", "");
					   }
				else{    
					     inouttemp[i]=temp[i];
				         temp[i]=temp[i].replace("(out)", "");
				   }
				//System.out.println(i+"th elementis.."+temp[i]);
					if(i==0 && inouttemp[0].contains("(in)"))
					{
						punchIn=temp[0];
						punchInDemo=temp[0];
					}else if(inouttemp[0].contains("(out)") && inouttemp[1].contains("(in)"))
					{  
						punchIn=temp[1];
						punchInDemo=temp[1];
					}else if(inouttemp[0].contains("(out)") && inouttemp[1].contains("(out)") && inouttemp[2].contains("(in)"))
					{   
						punchIn=temp[2];
						punchInDemo=temp[2];
					}
					
					
					/*else if(i==0 && inouttemp[0].contains("(out)")){
						nextpunchOut=temp[0];
					}*/
					
					if(i==1 && inouttemp[1].contains("(out)"))
					{
						breakOut=temp[1];
					}
					 if(i==2 && inouttemp[2].contains("(in)"))
					{
						breakIn=temp[2];
					}
					 if(i==3 && inouttemp[3].contains("(out)")){

						punchOut=temp[3];
						punchOutDemo=temp[3];
					}else if(i==lngth-1 && inouttemp[lngth-1].contains("(in)")){
						nextpunchIn= temp[lngth-1];
					}
				  }
				    } 

				else if(lngth==2)
				{
				   for(int i =0; i < lngth ; i++)
				   {
					   if(temp[i].contains("(in)"))
							   {    
						            inouttemp[i]=temp[i];
						   			temp[i]=temp[i].replace("(in)", "");
						   			
							   }
					   else{      
						          inouttemp[i]=temp[i];
						          temp[i]=temp[i].replace("(out)", "");
						          
					   }
				     
					   if(i==0 && inouttemp[0].contains("(in)")){
   						punchIn=temp[0];
   						punchInDemo=temp[0];
   					   }else if(inouttemp[0].contains("(out)") && inouttemp[1].contains("(in)")){
						punchIn=temp[1];
						punchInDemo=temp[1];
   					    }
					   if(i==1 && inouttemp[1].contains("(out)")){
						punchOut=temp[1];
						punchOutDemo=temp[1];
					   }else if(i==lngth-1 && inouttemp[lngth-1].contains("(in)")){
						nextpunchIn= temp[lngth-1];
						System.out.println("nextpunchIn is.."+nextpunchIn);	
					}
				   }
				}
				else if(lngth==3)
				{
					for(int i =0; i < lngth ; i++){
						if(temp[i].contains("(in)"))
						   {    
							    inouttemp[i]=temp[i];
					   			temp[i]=temp[i].replace("(in)", "");
					   			
						   }
				else{		  
					          inouttemp[i]=temp[i];	
					          temp[i]=temp[i].replace("(out)", "");
					          
				     }
						if(i==0 && inouttemp[0].contains("(in)"))
    					{
    						punchIn=temp[0];
    						punchInDemo=temp[0];
    					}else if(inouttemp[0].contains("(out)") && inouttemp[1].contains("(in)"))
    					{   
    						punchIn=temp[1];
    						punchInDemo=temp[1];
    					}
						/*if(i==1 && inouttemp[1].contains("(out)")){
    						punchOut=temp[1];
    						punchOutDemo=temp[1];
    					}*/
						if(i==2 && inouttemp[2].contains("(out)")){
    						punchOut=temp[lngth-1];
    						punchOutDemo=temp[lngth-1];
    					}else if(i==lngth-1 && inouttemp[lngth-1].contains("(in)")){
    						     nextpunchIn= temp[lngth-1];
    							}/*else if(i==2){
    								punchOut=temp[lngth-1];
    								punchOutDemo=temp[lngth-1];
    							}*/
    					//if(inouttemp[lngth-1].contains("(out)")) 
						   
				        }
				       
				}
				else if(lngth>4)
				{     
				      for(int i =0; i < lngth ; i++){
				    	  if(temp[i].contains("(in)"))
						   {    
				    		    inouttemp[i]=temp[i];
					   			temp[i]=temp[i].replace("(in)", "");
					   			
						   }
				  else{       
					          inouttemp[i]=temp[i];
					          temp[i]=temp[i].replace("(out)", "");
					          
				  }
				    	  if(i==0 && inouttemp[0].contains("(in)"))
	    					{
	    						punchIn=temp[0];
	    						punchInDemo=temp[0];
	    					}else if(inouttemp[0].contains("(out)") && inouttemp[1].contains("(in)"))
	    					{   
	    						punchIn=temp[1];
	    						punchInDemo=temp[1];
	    					}else if(inouttemp[0].contains("(out)") && inouttemp[1].contains("(out)") && inouttemp[2].contains("(in)"))
	    					{   
	    						punchIn=temp[2];
	    						punchInDemo=temp[2];
	    					}else if(inouttemp[0].contains("(out)") && inouttemp[1].contains("(out)") && inouttemp[2].contains("(out)") && inouttemp[3].contains("(in)"))
	    					{   
	    						punchIn=temp[3];
	    						punchInDemo=temp[3];
	    					}
				    	   if(i==lngth-1 && inouttemp[lngth-1].contains("(out)"))
	    					{
	    						punchOut=temp[lngth-1];
	    						punchOutDemo=temp[lngth-1];
	    					}else if(i==lngth-1 && inouttemp[lngth-1].contains("(in)")){
	    						nextpunchIn= temp[lngth-1];
	    					}/*else if(i==lngth-1){
	    						punchOut=temp[lngth-1];
	    						punchOutDemo=temp[lngth-1];
	    					}*/
				    }
				}
				else 
				{   
					
					for(int i =0; i < lngth ; i++){
				    	  if(temp[i].contains("(in)"))
						   {    
				    		    inouttemp[i]=temp[i];
					   			temp[i]=temp[i].replace("(in)", "");
					   			
						   }
				  else{        
					          inouttemp[i]=temp[i];
					          temp[i]=temp[i].replace("(out)", "");
					          
				  }
				    	  if(i==0 && inouttemp[0].contains("(in)"))
	    					{
	    						punchIn=temp[0];
	    						punchInDemo=temp[0];
	    					} 
				    	  
				    }
					
					//System.out.println("user is absent..");	
					absent="absent";
				}    
				  
				  // Start next day c-shift late punch ...
				  
				  SimpleDateFormat sdfrmt = new SimpleDateFormat("HH:mm:ss");
				 // String punchOutDummy=null;
				  
		             Date tempin=sdfrmt.parse(punchIn);
		             Date tempout=sdfrmt.parse(punchOut);
		             java.sql.Timestamp timetempin = new java.sql.Timestamp(tempin.getTime());
		             java.sql.Timestamp timetempout = new java.sql.Timestamp(tempout.getTime());
		             Date attnddate = tmpdemo.getAttendanceDate();
				  if((timetempin.getHours() >=0 && timetempin.getMinutes() >=0 && timetempin.getSeconds() >=1) && (timetempin.getHours() <= 2))
				   {
					   Calendar caltemp = Calendar.getInstance();
					   caltemp.setTime(attnddate);
					   caltemp.add(Calendar.DATE, -1);
					   attnddate=caltemp.getTime();
					  //getPunchoutCshift(tmpdemo,attnddate);
					   
				   } 
				  
					   
		           
				 //get the c-shift and blr c-shift Out punch ...
				   Date tempnextin=sdfrmt.parse(nextpunchIn);
				   java.sql.Timestamp timenextpunchIn = new java.sql.Timestamp(tempnextin.getTime());
				   Date outPunchCdate=tmpdemo.getAttendanceDate();
				   if(timenextpunchIn.getHours() >=1)
				   {   
					   Calendar caltemp = Calendar.getInstance();
				       caltemp.setTime(outPunchCdate);
				       caltemp.add(Calendar.DATE, +1);
				       outPunchCdate=caltemp.getTime();
					   String cshiftOutPunch=getPunchoutCshift(tmpdemo,outPunchCdate);
					   if(cshiftOutPunch==null){
						   punchOut=new String("00:00:00");
						   punchOutDemo=null;
						  // punchOutDemo=cshiftOutPunch;
					   }else{
					   punchOut=cshiftOutPunch;
					   punchOutDemo=cshiftOutPunch;
					   }
					   
				   }else if(timenextpunchIn.getHours() == 0 && timenextpunchIn.getMinutes() >0)
				   {   
					   Calendar caltemp = Calendar.getInstance();
				       caltemp.setTime(outPunchCdate);
				       caltemp.add(Calendar.DATE, +1);
				       outPunchCdate=caltemp.getTime();
					   String cshiftOutPunch=getPunchoutCshift(tmpdemo,outPunchCdate);
					   if(cshiftOutPunch==null){
						   punchOut=new String("00:00:00");
						   punchOutDemo=null;
						  // punchOutDemo=cshiftOutPunch;
					   }else{
					   punchOut=cshiftOutPunch;
					   punchOutDemo=cshiftOutPunch;
					   }
					   
				   }  
				   
				   //*********** END *************************
				     RCHR_Attend_Temp atndTmp =  OBProvider.getInstance().get(RCHR_Attend_Temp.class);
				   
				  	 String result=getTimeDuration(punchIn, punchOut, punchOutDemo, punchInDemo);
				  	 String res ;
				  	 
				  	 if(result==null)
		             {
				  		res = new String("00:00:00");
		             
		             }else{
		            	 res = new String(result.replaceAll("-", ""));
		             }
				  	 
				  	 // Two lines will Update IF OT
				  	
				  	Date duration;
				  	java.sql.Timestamp otduration=null;
				  	java.sql.Timestamp timePunchin=null;
				  	java.sql.Timestamp timePunchout=null;
				     try {
				    	  SimpleDateFormat sdft=new SimpleDateFormat("HH:mm:ss");
			              duration=sdft.parse(res);
			              otduration=new java.sql.Timestamp(duration.getTime());
				     }catch (ParseException ex) {
				          // ex.printStackTrace();
				       }
				     
				     String shiftGroup ="1234FAD6C1FE4DAFBEC11C919AFDD540";
				     
				     //String rchrEmployeeId=getEmployee(tmpdemo);
				     //RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, rchrEmployeeId);
				     
				     
			             if(otduration.getHours()>=14 && otduration.getHours()<=18 && !shiftGroup.equals(emp.getRchrShiftgroup().getId()))
			             {   
							    // try {
							     RCHR_Attend_Temp atndTmpnext =  OBProvider.getInstance().get(RCHR_Attend_Temp.class);
							     try {
							     SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
					             Date inPunch=sdft.parse(punchIn);
					             Date outPunch=sdft.parse(punchOut);
					             timePunchin=new java.sql.Timestamp(inPunch.getTime());
					             timePunchout=new java.sql.Timestamp(outPunch.getTime());
							    
					             atndTmpnext.setOrganization(tmpdemo.getOrganization());
							     atndTmpnext.setClient(tmpdemo.getClient());
							     atndTmpnext.setAttendanceDate(attnddate);
							     atndTmpnext.setErrorLog(tmpdemo.getErrorLog());
							     atndTmpnext.setEmployeeId(tmpdemo.getEmployeeId());
					             //String rchrEmployeeId=getEmployee(tmpdemo);
					             //RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, rchrEmployeeId);
					             //String shftid=getEmployeeShift(tmpdemo, emp, timePunchin, timePunchout);
					             //RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shftid);
							     String shftid=getEmployeeShift(tmpdemo, emp, timePunchin, timePunchout);
					             RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shftid);
							     
					             atndTmpnext.setPunchIn(timePunchin);
					             atndTmpnext.setPunchOut(shift.getToTime());
					             atndTmpnext.setRcprShift(shift);
					            // "2015-08-24 19:00:00"
					             String shiftOut=shift.getToTime().toString();
					             SimpleDateFormat sdftOt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					             Date punchoutOT=sdftOt.parse(shiftOut);
					             String otPunchout=sdft.format(punchoutOT);
					             System.out.println("tirumal check value for otPunchout is.."+otPunchout);
					             String resultot=getTimeDuration(punchIn, otPunchout, punchOutDemo, punchInDemo);
					             String resot;
					             if(resultot==null)
					             {
					            	 resot = new String("00:00:00");
					             
					             }else{
					            	 resot = new String(resultot.replaceAll("-", ""));
					             }
					             
					             System.out.println("tirumal check value for duration is.. "+resot);
					             
					             if(resultot==null)
					             {
					            	 atndTmpnext.setDuration(new String("00:00:00"));
					            	 atndTmpnext.setPresent(Boolean.FALSE);
					             }else{
					            	 atndTmpnext.setDuration(resot); 
					             }
					             int weeklyOff=attnddate.getDay();
					             int empWeek=getWeeklyOff(emp);
					             if(weeklyOff==empWeek){
					            	 atndTmpnext.setWeeklyOff(Boolean.TRUE); 
					             }
					              if(result!=null && weeklyOff==empWeek){
					            	  atndTmpnext.setOvertime(Boolean.TRUE);  
					              }
					             String rotatteShift=getShiftRotation(tmpdemo,emp);
					             String staff=new String("Staff");
					             String semoStaff=new String("Semi Staff");
					             String noShift="ED4817728DD24E86A132094AE5B1DCDE";
							            
					             if(!shftid.equals(rotatteShift) && !staff.equals(emp.getEmployeeType()) && !shftid.equals(noShift) && result!=null && !shiftGroup.equals(emp.getRchrShiftgroup().getId())){
					            	 atndTmpnext.setOvertime(Boolean.TRUE); 
					             }
					             atndTmpnext.setEmployee(emp);
					             tmpdemo.setValidate(Boolean.TRUE); 
					             OBDal.getInstance().save(tmpdemo);
					             OBDal.getInstance().save(atndTmpnext);
					             flag++;
					             
					             
					             insertOtherAttendLine(tmpdemo, emp, punchIn, punchOut, punchOutDemo, punchInDemo, attnddate, flag);
							     }catch (ParseException ex) {
							          // ex.printStackTrace();
							       }
			             }else{
			          //------- END Two lines will Update IF OT---------
			         //-------------
				  	 if(result==null)
		             {
		            // System.out.println("result1 is ..."+result);
		             atndTmp.setDuration(new String("00:00:00"));
		             atndTmp.setPresent(Boolean.FALSE);
		             }else{
		            // System.out.println("time duration1 is ..."+res);
		             atndTmp.setDuration(res); 
		             }
				  	 if(otduration.getHours()<=4){
		            	 atndTmp.setPresent(Boolean.FALSE); 
		            	 }
				  	 
				     try {
				  	 String breakResult=getTimeDuration(breakOut, breakIn, absent, absent);
				  	if(breakResult==null)
		             {
		             System.out.println("result2 is ..."+breakResult);
		             atndTmp.setShift(new String("00:00:00"));
		             //atndTmp.setPresent(Boolean.FALSE);
		             }else{
		             String brres = new String(breakResult.replaceAll("-", ""));
		             String time1="24:00:00";
		            // String date3;
		             String strArry[]=brres.split(":");
                    int hrs= new Integer(strArry[0]).intValue();
                     if(hrs>=24){
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
      				timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
      				Date date1 = timeFormat.parse(time1);
      				Date date2 = timeFormat.parse(brres);
      				long summ = date2.getTime() - date1.getTime();
      				String date3 = timeFormat.format(new Date(summ));
      				System.out.println("time duration2 is... "+date3);
      				 atndTmp.setShift(date3);
      				}else{
		             System.out.println("time duration2 is ... "+brres);
		             atndTmp.setShift(brres);}
		             } 	
		             
				  	 //System.out.println("error process1 ...!!");
		             atndTmp.setOrganization(tmpdemo.getOrganization());
		             atndTmp.setClient(tmpdemo.getClient());
		            // System.out.println("error process2 ...!!");
		             
		             SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
		             Date inPunch=sdft.parse(punchIn);
		             Date outPunch=sdft.parse(punchOut);
		              timePunchin=new java.sql.Timestamp(inPunch.getTime());
		              timePunchout=new java.sql.Timestamp(outPunch.getTime());
		             atndTmp.setPunchIn(timePunchin);
		             atndTmp.setPunchOut(timePunchout);
		             
		             Date inBreak=sdft.parse(breakIn);
		             Date outBreak=sdft.parse(breakOut);
		             java.sql.Timestamp timeBreakin=new java.sql.Timestamp(inBreak.getTime());
		             java.sql.Timestamp timeBreakout=new java.sql.Timestamp(outBreak.getTime());
		             atndTmp.setBreakin(timeBreakin);
		             atndTmp.setBreakout(timeBreakout);
		             }catch (ParseException ex) {
				          // ex.printStackTrace();
				       }
				  
			            //
		             atndTmp.setAttendanceDate(attnddate);
		             atndTmp.setErrorLog(tmpdemo.getErrorLog());
		            // System.out.println("error process4 ...!!");
		             atndTmp.setEmployeeId(tmpdemo.getEmployeeId());
		            // try{
		             //String rchrEmployeeId=getEmployee(tmpdemo);
		             System.out.println(" punch no and name is for group..."+tmpdemo.getEmployeeId());
		             //RchrEmployee emp = OBDal.getInstance().get(RchrEmployee.class, rchrEmployeeId);
		             System.out.println(" employee no and name is for group..."+emp.getDocumentNo()+" "+emp.getEmployeeName());
		            // String shftid=getEmployeeShift(tmpdemo, emp, timePunchin, timePunchout);
		             //RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shftid);
		             
		             String shftid=getEmployeeShift(tmpdemo, emp, timePunchin, timePunchout);
		             RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shftid);
		             atndTmp.setRcprShift(shift);
		            /* String shiftgroID = emp.getRchrShiftgroup().getId();
		             RchrShiftgroup shiftGroup= OBDal.getInstance().get(RchrShiftgroup.class, shiftgroID);
		             
		            // shiftid=getEmployeeShift(tmpdemo);
		             for(RchrShiftlines sLine : shiftGroup.getRchrShiftlinesList()){
		            	 String shiftId= sLine.getRcprShift().getId();
		            	 RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shiftId);
		            	 java.sql.Timestamp shiftInTime=shift.getFromTime();
		            	 java.sql.Timestamp shiftOutTime=shift.getToTime();
		            	 if(shift.getFromTime().getHours()-1 < timePunchin.getHours() && shift.getToTime().getHours()-3 > timePunchin.getHours())
		            	 {
		            		 atndTmp.setRcprShift(shift);
		            		 shftid=shift.getId();
		            	 }
		            	 else if((0 <= timePunchin.getHours() && 3 >= timePunchin.getHours()) || (22 <= timePunchin.getHours() && 24 >= timePunchin.getHours()))
		            	 {
		            		 atndTmp.setRcprShift(shift);
		            		 shftid=shift.getId();
		            	 }
		            	 
		             }*/
		             
		             int weeklyOff=attnddate.getDay();
		             int empWeek=getWeeklyOff(emp);
		             if(weeklyOff==empWeek){
		            	 atndTmp.setWeeklyOff(Boolean.TRUE); 
		             }
		              if(result!=null && weeklyOff==empWeek){
		            	  atndTmp.setOvertime(Boolean.TRUE);  
		              }
		             String rotatteShift=getShiftRotation(tmpdemo,emp);
		             String staff=new String ("Staff");
		             String semoStaff=new String("Semi Staff");
		             String noShift="ED4817728DD24E86A132094AE5B1DCDE";
		             //String semistaff = new String("")
		             
		             if(!shftid.equals(rotatteShift) && !staff.equals(emp.getEmployeeType()) && !shftid.equals(noShift) && result!=null && !shiftGroup.equals(emp.getRchrShiftgroup().getId())){
		              atndTmp.setOvertime(Boolean.TRUE); 
		             }
		             atndTmp.setEmployee(emp);
		             tmpdemo.setValidate(Boolean.TRUE); 
		            // atndTmp.set
		             OBDal.getInstance().save(tmpdemo);
		             System.out.println("error process5 ...!!");
		             OBDal.getInstance().save(atndTmp);
		             System.out.println("ending the process...!!");
		            //	info.addResult("inpshiftinmins", getShiftInMinsByInp(info));
		            //info.addResult("inpshiftinmins", getShiftInMins(res));
		             flag++; 
			             }
		           /*  }//try
		             catch(Exception e)
		     		{
		     			e.printStackTrace();
		     		}*/
			 }//if
			 // *********** copy The attnd logic
            }else{
            	logger.info("Employee Not Present in Master");
            }
			//*********** 
		//flag++;
        }//while
        System.out.println("total updated lines.. "+flag);
        Long endAfterWhile = System.currentTimeMillis();
     		Long time = endAfterWhile - startBeforeWhile;
     		
     		long hour = TimeUnit.MILLISECONDS.toHours(time);
     		long minute = TimeUnit.MILLISECONDS.toMinutes(time);
     		
     		long second = TimeUnit.MILLISECONDS.toSeconds(time);
     		System.out.println("Time is " + hour+":"+minute+":"+second);
    }//doexcute
     
    //Method defination oF Insert another line for OT
    
    public void insertOtherAttendLine(RCHRAttendTempdemo tmpdemo, RchrEmployee emp, String punchIn, String punchOut, String punchOutDemo, String punchInDemo, Date attnddate, int flag){
    	
    	   java.sql.Timestamp timePunchin=null;
	  	   java.sql.Timestamp timePunchout=null;
    	try{
    	
    	RCHR_Attend_Temp atndTmpOt =  OBProvider.getInstance().get(RCHR_Attend_Temp.class);
    	//atndTmpnext
    	atndTmpOt.setOrganization(tmpdemo.getOrganization());
    	atndTmpOt.setClient(tmpdemo.getClient());
          SimpleDateFormat sdft = new SimpleDateFormat("HH:mm:ss");
          Date inPunch=sdft.parse(punchIn);
          Date outPunch=sdft.parse(punchOut);
          timePunchin=new java.sql.Timestamp(inPunch.getTime());
          timePunchout=new java.sql.Timestamp(outPunch.getTime());
		    
          atndTmpOt.setAttendanceDate(attnddate);
          atndTmpOt.setErrorLog(tmpdemo.getErrorLog());
          atndTmpOt.setEmployeeId(tmpdemo.getEmployeeId());
          //String rchrEmployeeId=getEmployee(tmpdemo);
          //RchrEmployee empot = OBDal.getInstance().get(RchrEmployee.class, rchrEmployeeId);
          //String shftid=getEmployeeOTShift(tmpdemo, emp, timePunchin, timePunchout);
          //RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shftid);
          String shftid=getEmployeeOTShift(tmpdemo, emp, timePunchin, timePunchout);
          RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shftid);
          
          atndTmpOt.setPunchIn(shift.getFromTime());
          atndTmpOt.setPunchOut(timePunchout);
          atndTmpOt.setRcprShift(shift);
          String shiftIn=shift.getFromTime().toString();
          SimpleDateFormat sdftOt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date punchinOT=sdftOt.parse(shiftIn);
          String otPunchin=sdft.format(punchinOT);
          //String punchinOT=sdft.parse()
          System.out.println("tirumal check value for otPunchin for row2 is.."+otPunchin);
          String result=getTimeDuration(otPunchin, punchOut, punchOutDemo, punchInDemo);
          String res;
          if(result==null)
          {
        	  res = new String("00:00:00");
          
          }else{
        	  res = new String(result.replaceAll("-", ""));
          }
          
          System.out.println("tirumal check value for result for row2 is.. "+res);
          if(result==null)
          {
        	  atndTmpOt.setDuration(new String("00:00:00"));
        	  atndTmpOt.setPresent(Boolean.FALSE);
          }else{
        
        	  atndTmpOt.setDuration(res); 
          }
          int weeklyOff=attnddate.getDay();
          int empWeek=getWeeklyOff(emp);
          if(weeklyOff==empWeek){
        	  atndTmpOt.setWeeklyOff(Boolean.TRUE); 
          }
           if(result!=null && weeklyOff==empWeek){
        	   atndTmpOt.setOvertime(Boolean.TRUE);  
           }
          String rotatteShift=getShiftRotation(tmpdemo,emp);
          String staff=new String ("Staff");
          String semiStaff=new String ("Semi Staff");
          String noShift="ED4817728DD24E86A132094AE5B1DCDE";
          String shiftGroup ="1234FAD6C1FE4DAFBEC11C919AFDD540";
		  if(!shftid.equals(rotatteShift) && !shftid.equals(noShift) && !staff.equals(emp.getEmployeeType()) && !shiftGroup.equals(emp.getRchrShiftgroup().getId()) ){
        	  atndTmpOt.setOvertime(Boolean.TRUE); 
          }else if(staff.equals(emp.getEmployeeType()) || semiStaff.equals(emp.getEmployeeType())){
        	  atndTmpOt.setOvertime(Boolean.TRUE); 
          }
          atndTmpOt.setEmployee(emp);
          tmpdemo.setValidate(Boolean.TRUE); 
          //OBDal.getInstance().save(tmpdemo);
          OBDal.getInstance().save(atndTmpOt);
          flag++;
    	 }catch (ParseException ex) {
	          // ex.printStackTrace();
	       }
    	
    }
    
    public int getWeeklyOff(RchrEmployee emp){
    	int weekNo=8;
    	String weekOff="N/A";
    	if(emp.getWeeklyOff()==null){
    		weekOff="N/A";
    	}else{
    	 weekOff=emp.getWeeklyOff();
    	}
    	if(weekOff.contains("SUNDAY")){
    		weekNo=0;
    	}else if(weekOff.contains("MONDAY")){
    		weekNo=1;
    	}else if(weekOff.contains("TUESDAY")){
    		weekNo=2;
    	}else if(weekOff.contains("WEDNESDAY")){
    		weekNo=3;
    	}else if(weekOff.contains("THURSDAY")){
    		weekNo=4;
    	}else if(weekOff.contains("FRIDAY")){
    		weekNo=5;
    	}else if(weekOff.contains("SATURDAY")){
    		weekNo=6;
    	}else{
    		weekNo=7;
    	}
    	return weekNo;
    }
    
    //get employee shift
    
    
    /*public String getEmployeeShift(RCHRAttendTempdemo tmpdemo, RchrEmployee emp, java.sql.Timestamp timePunchin, java.sql.Timestamp timePunchout){
    	String shiftID="ED4817728DD24E86A132094AE5B1DCDE";
    	 String shiftgroID = emp.getRchrShiftgroup().getId();
    	 System.out.println(" get shift employee no and name is"+emp.getDocumentNo()+" "+emp.getEmployeeName());
         RchrShiftgroup shiftGroup= OBDal.getInstance().get(RchrShiftgroup.class, shiftgroID);
         
         for(RchrShiftlines sLine : shiftGroup.getRchrShiftlinesList()){
        	 String shiftId= sLine.getRcprShift().getId();
        	 RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shiftId);
        	 java.sql.Timestamp shiftInTime=shift.getFromTime();
        	 java.sql.Timestamp shiftOutTime=shift.getToTime();
        	 if(shift.getFromTime().getHours()-1 <= timePunchin.getHours() && shift.getToTime().getHours()-3 > timePunchin.getHours())
        	 {
        		 shiftID=shift.getId();
        	 }
        	 else if((0 <= timePunchin.getHours() && 3 >= timePunchin.getHours()) || (22 <= timePunchin.getHours() && 24 >= timePunchin.getHours()))
        	 {
        		 shiftID=shift.getId();
        	 }
        	 else if(19 <= timePunchin.getHours() && 20 >= timePunchin.getHours())
        	 {
        		 shiftID=shift.getId();
        	 }
        	 
         }
         return shiftID;
    }
    
    
    public String getEmployeeOTShift(RCHRAttendTempdemo tmpdemo, RchrEmployee emp, java.sql.Timestamp timePunchin, java.sql.Timestamp timePunchout){
    	String shiftID="ED4817728DD24E86A132094AE5B1DCDE";
    	 String shiftgroID = emp.getRchrShiftgroup().getId();
         RchrShiftgroup shiftGroup= OBDal.getInstance().get(RchrShiftgroup.class, shiftgroID);
         for(RchrShiftlines sLine : shiftGroup.getRchrShiftlinesList()){
        	 String shiftId= sLine.getRcprShift().getId();
        	 RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shiftId);
        	 java.sql.Timestamp shiftInTime=shift.getFromTime();
        	 java.sql.Timestamp shiftOutTime=shift.getToTime();
        	 if(shift.getToTime().getHours()-1 < timePunchout.getHours() && shift.getToTime().getHours()+1 > timePunchout.getHours())
        	 {
        		 shiftID=shift.getId();
        	 }
        	 else if((0 <= timePunchout.getHours() && 3 >= timePunchout.getHours() && shift.getFromTime().getHours() == 15) || (22 <= timePunchout.getHours() && 24 >= timePunchout.getHours()))
        	 {
        		 shiftID=shift.getId();
        	 }
        	 
         }
         return shiftID;
    }
    */
    
 public String getEmployeeShift(RCHRAttendTempdemo tmpdemo, RchrEmployee emp, java.sql.Timestamp timePunchin, java.sql.Timestamp timePunchout){
		String shiftID="ED4817728DD24E86A132094AE5B1DCDE";
		 String shiftgroID = emp.getRchrShiftgroup().getId();
		 System.out.println(" get shift employee no and name is "+emp.getDocumentNo()+" "+emp.getEmployeeName()+" date is "+tmpdemo.getAttendanceDate());
	     
		 
		 RchrShiftgroup shiftGroup= OBDal.getInstance().get(RchrShiftgroup.class, shiftgroID);
	     
	     
	       for(RchrShiftlines sLine : emp.getRchrShiftgroup().getRchrShiftlinesList()){
	         
	         String shiftId= sLine.getRcprShift().getId();
	         RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shiftId);
	         
	         
	         java.sql.Timestamp shiftInTime=sLine.getRcprShift().getFromTime();
	         java.sql.Timestamp shiftOutTime=sLine.getRcprShift().getToTime();
	         
	         if(sLine.getRcprShift().getFromTime().getHours()-1 <= timePunchin.getHours() && sLine.getRcprShift().getToTime().getHours()-3 > timePunchin.getHours())
	         {
	                 shiftID=sLine.getRcprShift().getId();
	         }
	         else if((0 <= timePunchin.getHours() && 3 >= timePunchin.getHours()) || (22 <= timePunchin.getHours() && 24 >= timePunchin.getHours()))
	         {
	                 shiftID=sLine.getRcprShift().getId();
	         }
	         else if(19 <= timePunchin.getHours() && 20 >= timePunchin.getHours())
	         {
	                 shiftID=sLine.getRcprShift().getId();
	         }
	         
	     }
	     
	     
	     
	     return shiftID;
}
    public String getEmployeeOTShift(RCHRAttendTempdemo tmpdemo, RchrEmployee emp, java.sql.Timestamp timePunchin, java.sql.Timestamp timePunchout){
    	String shiftID="ED4817728DD24E86A132094AE5B1DCDE";
   	  	String shiftgroID = emp.getRchrShiftgroup().getId();
        RchrShiftgroup shiftGroup= OBDal.getInstance().get(RchrShiftgroup.class, shiftgroID);
        for(RchrShiftlines sLine : shiftGroup.getRchrShiftlinesList()){
       	 String shiftId= sLine.getRcprShift().getId();
       	 RCPRShift shift=OBDal.getInstance().get(RCPRShift.class, shiftId);
       	 java.sql.Timestamp shiftInTime=shift.getFromTime();
       	 java.sql.Timestamp shiftOutTime=shift.getToTime();
       	 if(shift.getToTime().getHours()-1 < timePunchout.getHours() && shift.getToTime().getHours()+1 > timePunchout.getHours())
       	 {
       		 shiftID=shift.getId();
       	 }
       	 else if((0 <= timePunchout.getHours() && 3 >= timePunchout.getHours() && shift.getFromTime().getHours() == 15) || (22 <= timePunchout.getHours() && 24 >= timePunchout.getHours()))
       	 {
       		 shiftID=shift.getId();
       	 }
       	 
        }
        return shiftID;
    }
    
    
    
    //end get emp shift
    
    // get shift rotation..
    public String getShiftRotation(RCHRAttendTempdemo tmpdemo, RchrEmployee emp){
    	String shiftId=null;
    	List li = getShiftMemo(tmpdemo, emp);
        if(li.size()>=1){
         // System.out.println("In If condition ");
          
        	shiftId = li.get(0).toString();
            System.out.println("shiftID 1 "+shiftId);
            
        }else{
       	 String mrelayId=emp.getRelay().getId();
    	    
    	    String hql = " SELECT ess FROM Rchr_Relay ess "
    	            + " WHERE ess.rchrMrelay.id='" + mrelayId + "' "
    	            + " AND ess.fromdate <='" + tmpdemo.getAttendanceDate() + "' "
    	            + " AND ess.todate >='" + tmpdemo.getAttendanceDate() + "' "
    	            + " AND ess.active=true";
    	    List<RchrRelay> rlyList = OBDal.getInstance().getSession().createQuery(hql).list();
    	    
    		for(RchrRelay rly : rlyList){
    			shiftId= rly.getShift().getId();
    		}
        }
    	
    	return shiftId;
    }
    // end the get shift rotation
    
    public String getEmployee(RCHRAttendTempdemo tmpdemo){
    	//String result=null;
    	String rchrEmployeeId=null;
    	try{
         	Connection conn = OBDal.getInstance().getConnection();
             Statement stmt1 = conn.createStatement();
             String sqry="select rchr_employee_id from rchr_employee where punchno='"+tmpdemo.getEmployeeId()+"'";
             ResultSet res1 = stmt1.executeQuery(sqry);
            // RchrEmployee emp=new RchrEmployee();
             /*if(!res1.next()){
            	 throw new NullPointerException("Emp PunchNo:"+tmpdemo.getEmployeeId()+ "is not existed in employee Master");
            }*/
             
             while(res1.next())
             {
            	 rchrEmployeeId=res1.getString("rchr_employee_id");
            	 
             }
    	
     
    }catch(Exception ex){
    	ex.printStackTrace();
    }
     return rchrEmployeeId;
    	}
 // get shift rotation..
    private List getShiftMemo(RCHRAttendTempdemo tmpdemo, RchrEmployee emp){
    	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String hql = "SELECT newshift.id from Rchr_Memo_Shift AS aline "
                + " WHERE aline.employee.id = '" + emp.getId()+ "' " 
                + " AND aline.memfromdate <= '" + sdf.format(tmpdemo.getAttendanceDate()) + "' AND aline.memtodate >= '" + sdf.format(tmpdemo.getAttendanceDate()) + "' "
                + " AND 1 = 1";

        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        
        
        System.out.println("Size is 2 "+li.size());
        return li;
    }

    
    public String getTimeDuration(String strpunchin, String strpunchout, String strpunchOutDemo, String punchInDemo){ 
    	 String result=null;
    	 
    	try {
	            //SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	            
	            //Date datein=parseFormat.parse(strinpunch);
	           //Date dateout=parseFormat.parse(stroutpunch);
	          //String strpunchin=sdf.format(datein);
	         //String strpunchout=sdf.format(dateout);
	          /*  if(strpunchout.equals(new String("00:00:00"))){
	            	strpunchout=strpunchin;
	            }*/
	            
	            if(strpunchOutDemo==null){
	            	strpunchout=strpunchin;
	            }
	            if(punchInDemo==null){
	            	strpunchin=strpunchout;
	            }
	            Date dateTo = sdf.parse(strpunchin);
	            System.out.println("dateTo  is ..."+dateTo); 
	            Date dateFrom = sdf.parse(strpunchout);
	            System.out.println("dateFrom  is ..."+dateFrom);
	            String str[] = strpunchin.split(":");
	            String str1[] = strpunchout.split(":");
	            System.out.println("str[]  is ..."+str[0]);
	            System.out.println("str[] length  is ..."+str.length+"--"+str1.length);          
	            if (str.length == 0 || str1.length == 0) {
	                throw new NullPointerException("str is null");
	            }
	            int hours = new Integer(str[0]).intValue();
	            System.out.println("hours  is ..."+hours);
	            
	            int increment = 0;
	            if (hours < 6) {
	                increment = -5;
	            } else {
	                increment = -18;
	            }

	            Calendar calTo = Calendar.getInstance();
	            calTo.setTime(dateTo);
	            calTo.add(Calendar.HOUR, increment);
	            calTo.add(Calendar.MINUTE, -30);

	            Calendar calFrom = Calendar.getInstance();
	            calFrom.setTime(dateFrom);
	            calFrom.add(Calendar.HOUR, increment);
	            calFrom.add(Calendar.MINUTE, -30);
	            String in = sdf.format(new Date(calTo.getTimeInMillis()));
	            System.out.println("time in is ..."+in);
	            String out = sdf.format(new Date(calFrom.getTimeInMillis()));
	            System.out.println("time out is ..."+out);
	           // if(in !="18:30:00" && out !="18:30:00")
	            
	            result = TimeDiffUtil.getworkedhours("13-11-2013", in, out);
	            System.out.println("result is ..."+result);
	            
	             } catch (ParseException ex) {
			          // ex.printStackTrace();
			    	  
			       }
    	return result;
    }

    public String getPunchoutCshift(RCHRAttendTempdemo tmpdemo, Date attnddate)
    {    
    	String cPunchout=null;
    	try{
    	String qrytemp="select rchr_attend_tempdemo_id from rchr_attend_tempdemo where attdate='"+attnddate+"' and stremployee='"+tmpdemo.getEmployeeId()+"'";
    	//System.out.println("cpunchout method is ..."+qrytemp);
    	Connection conn = OBDal.getInstance().getConnection();
    	Statement stmttemp = conn.createStatement();
        ResultSet rstemp= stmttemp.executeQuery(qrytemp);
        
        
        while (rstemp.next()){
        	//System.out.println("inside while loop....");
        	String demoId = rstemp.getString("rchr_attend_tempdemo_id");
            RCHRAttendTempdemo OutPunchAttndDemo = OBDal.getInstance().get(RCHRAttendTempdemo.class, demoId);
            
            if(Boolean.TRUE.equals(OutPunchAttndDemo.isActive())){
            	System.out.println("inside if of method...");
            String outpunch = OutPunchAttndDemo.getErrorLog();
            if(outpunch.isEmpty())
			 {  
            	//System.out.println("inside if of empty...");
            	outpunch="00:00:00(in),00:00:00(out)";
			 }
            String[] outtemp;
            outtemp = outpunch.split(",");
           // int pnchLngth = outtemp.length;
          // if(pnchLngth >= 0){
            
            	/*if(outtemp[0].contains("(in)")) 
				   {	
     			    outtemp[0]=outtemp[0].replace("(in)", "");
     			    cPunchout=outtemp[0];
				   }*/
            	if(outtemp[0].contains("(out)"))
            	{
            		outtemp[0]=outtemp[0].replace("(out)", "");
            		cPunchout=outtemp[0];
            	}
            	
           // }
            	System.out.println("cpunchout method is ..."+cPunchout);
            
            }
        	
        }
    }catch(Exception ex){
    	ex.printStackTrace();
    }
        return cPunchout;
    }//getpunchoutshift
    
    
}//main class