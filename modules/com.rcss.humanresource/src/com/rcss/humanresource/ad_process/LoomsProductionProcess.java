package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.RCHRQualitystandard;;

import com.redcarpet.payroll.data.RCPLLoom;
import com.redcarpet.payroll.data.RcplLoomsproduction;

import com.redcarpet.payroll.data.RcplLoomsproductionimport;
import com.redcarpet.production.data.RCPRShift;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.sql.Timestamp;
import java.util.Calendar;
import java.sql.*;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import com.timeutils.sam.TimeDiffUtil;
import javax.servlet.ServletException;
import java.text.ParseException;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBDal;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
* @author K Vinay 
*/

public class LoomsProductionProcess extends DalBaseProcess {
	private static final Logger log = Logger.getLogger(LoomsProductionProcess.class);
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	int i=0;
    	try{
    		
    	 // Date date = new Date();
    	  Calendar cal = Calendar.getInstance();
    	 // cal.setTime(date);
    	  cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
    	  Date fromDate = cal.getTime(); 
    	  System.out.println("fromDate isz "+fromDate);
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	  
    	 
    	  String strFromDate = sdf.format(fromDate);
    	  
    	  System.out.println("strFromDate isz "+strFromDate);
    	  cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DATE));
    	  Date toDate = cal.getTime(); 
    	  String strToDate = sdf.format(toDate);
    	  System.out.println("strToDate isz "+strToDate);
    	 
    	  
    	Connection conn = OBDal.getInstance().getConnection();
    	Statement stmt = conn.createStatement();
    	String sql="SELECT rcpl_loomsproductionimport_id FROM rcpl_loomsproductionimport where validate='N'";
    	//+" and loomdate between '"+strFromDate+"' and '"+strToDate+"'";
    	ResultSet rs = stmt.executeQuery(sql);
    	
    	while(rs.next()){
    		String lmpId = rs.getString("rcpl_loomsproductionimport_id");
    		
    		RcplLoomsproductionimport loomsProdImport = OBDal.getInstance().get(RcplLoomsproductionimport.class, lmpId);
    		
    		System.out.println("loom id "+loomsProdImport.getLoomname());
    		
    		log.info("Dataee=----------- "+loomsProdImport.getLoomdate());
    		log.info("Shifttt =----------- "+loomsProdImport.getShift());
    		
    			RCPLLoom looms = null;	
    			RCHRQualitystandard sort = null;
    			RCPRShift shift = null;
    		try{
    				
    			
	    	    	Statement stmt1 = conn.createStatement();
	    	    	
	    	    	String sql1="SELECT rcpl_loom_id FROM rcpl_loom where value='"+loomsProdImport.getLoomname()+"'";
	    	    	log.info("SQL LOOMS "+sql1);
	    	    	ResultSet rs1 = stmt1.executeQuery(sql1);
	    	    	
	    	    	String lmpId1="";
	    	    	while(rs1.next()){
	    	    		lmpId1 = rs1.getString("rcpl_loom_id");
	    	    	}
	    	    	if(lmpId1!="" && lmpId1!=null){
	    	    		looms = OBDal.getInstance().get(RCPLLoom.class, lmpId1);
	    	    		//log.info("Inside Looms If condition ");
	    	    	}
	    	    	System.out.println("Looms ID "+lmpId1);
	    	    	
    		}catch(SQLException es){
    				es.printStackTrace();
    		}
    	    	
    			
    		try{
    			
    			
    			Statement stmt2 = conn.createStatement();
    	    	
    	    	String sql2="SELECT rchr_qualitystandard_id FROM rchr_qualitystandard where qualityno='"+loomsProdImport.getRecordSortNo()+"'";
    	    	ResultSet rs2 = stmt2.executeQuery(sql2);
    	    	
    	    	String lmpId2="";
    	    	while(rs2.next()){
    	    		lmpId2 = rs2.getString("rchr_qualitystandard_id");
    	    	}
    	    	if(lmpId2!="" && lmpId2!=null){
    	    		sort = OBDal.getInstance().get(RCHRQualitystandard.class, lmpId2);
    	    		
    	    	}
    	    	System.out.println("Sort ID "+lmpId2);
    	    	
    	    	
    		
    			
    		}catch(SQLException e){
    			e.printStackTrace();
        	}
    		
    		try{
    			
    			
    			Statement stmt3 = conn.createStatement();
    	    	
    	    	String sql3="SELECT rcpr_shift_id FROM rcpr_shift where name='"+loomsProdImport.getShift().concat("-Shift")+"'";
    	    	ResultSet rs3 = stmt3.executeQuery(sql3);
    	    	
    	    	String lmpId3="";
    	    	while(rs3.next()){
    	    		lmpId3 = rs3.getString("rcpr_shift_id");
    	    	}
    	    	if(lmpId3!="" && lmpId3!=null){
    	    		shift = OBDal.getInstance().get(RCPRShift.class, lmpId3);
    	    		
    	    	}
    	    		System.out.println("Shift ID "+lmpId3);
    		}catch(SQLException e){
        		e.printStackTrace();
        		System.out.println("Inside SQLException e ");
        	}
    		
    		if(shift!=null && sort!=null && looms!=null){
    			//System.out.println("In If Condition ====");
    			log.info("In If Condition ====");
    			
    			
    			
    			try{
    				
    				insertLoomsProductions(loomsProdImport,shift,sort,looms);
    				
    			}catch(Exception e){
    				e.printStackTrace();
    				System.out.println("This is black sheep Unique Constraint ");
    			}
    			i++;
    		}
    		
    		
    	}
    	}catch(Exception alle){
    		alle.printStackTrace();
    		System.out.println("Inside Alle ");
    	}
    	System.out.println("Inserted Rows "+i);
    }
   
    public void insertLoomsProductions(RcplLoomsproductionimport loomsProdImport,
    		RCPRShift shift,RCHRQualitystandard sort
    		,RCPLLoom looms){
    	log.info("In Insertion 5646545412121===");
    	RcplLoomsproduction product = OBProvider.getInstance().get(RcplLoomsproduction.class);
		
		product.setOrganization(loomsProdImport.getOrganization());
		//production.setClient(loomsProdImport.getClient());
		product.setRcprShift(shift);
		product.setRchrQualitystandard(sort);
		product.setRcplLoom(looms);
		product.setEfficiency(loomsProdImport.getEfficiency());
		product.setPpi(loomsProdImport.getPpi());
		product.setLoomdate(loomsProdImport.getLoomdate());
		product.setLoommts(loomsProdImport.getLoommts());
		product.setPicks(loomsProdImport.getPicks());
		product.setWeftbreaks(loomsProdImport.getWeftbreaks());
		product.setWarpbreaks(loomsProdImport.getWarpbreaks());
		product.setOtherbreaks(loomsProdImport.getOtherbreaks());
		product.setRpm(loomsProdImport.getRpm());
		product.setValidate(Boolean.TRUE);
		OBDal.getInstance().save(product);
		loomsProdImport.setValidate(Boolean.TRUE);
    }
    
}
