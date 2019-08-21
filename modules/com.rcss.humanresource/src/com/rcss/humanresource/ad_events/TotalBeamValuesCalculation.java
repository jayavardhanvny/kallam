 package com.rcss.humanresource.ad_events;

 import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import com.timeutils.sam.TimeDiffUtil;
import java.text.ParseException;
import com.rcss.humanresource.data.*;
//import com.redcarpet.goodsissue.data.RCGI_MiLines;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
//import com.sun.imageio.plugins.common.BogusColorSpace;

import javax.enterprise.event.Observes;

import oracle.net.aso.b;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.ast.tree.OrderByClause;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.kernel.event.EntityDeleteEvent;
import org.openbravo.client.kernel.event.EntityNewEvent;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.service.db.DalConnectionProvider;


public class TotalBeamValuesCalculation extends EntityPersistenceEventObserver {
    private static Entity[] entities = {ModelProvider.getInstance().getEntity(RCHR_Dirwarp_Beam.ENTITY_NAME)};  
    protected Logger logger = Logger.getLogger(RCHR_Dirwarp_Beam.ENTITY_NAME);
	protected Entity[] getObservedEntities() {
	return entities;
	   }
	
	public void onSave(@Observes EntityNewEvent event){
		if(!isValidEvent(event))
		{
			return;
		}
      final RCHR_Dirwarp_Beam beam = (RCHR_Dirwarp_Beam)event.getTargetInstance();
      RCHR_Dirwarp_Creel creel = beam.getRchrDirwarpCreel();
      //RCHRQualitystandard standard = inspSheet.getRchrQualitystandard();
      
      
     /*
      //***************************************************************************************
  	 By Vinay
  	 * 
  	 * Getting the Average Beam Count of all the Beams of the selected Direct Warping Row 
  	 * 
  	  
      
      BigDecimal beamcount = BigDecimal.ZERO;//beam.getRchrDirwarpCreel().getRchrDirectwarp().getBeamcount();
      System.out.println("creel.getRchrDirectwarp().getRCHRDirwarpCreelList() "+creel.getRchrDirectwarp().getRCHRDirwarpCreelList().size());
      BigDecimal totalAverage = BigDecimal.ZERO;
      
      for(RCHR_Dirwarp_Creel creelfor : creel.getRchrDirectwarp().getRCHRDirwarpCreelList()){
    	  BigDecimal noofBeams = BigDecimal.ZERO;
    	  noofBeams =new BigDecimal(creelfor.getRCHRDirwarpBeamList().size());
    	  System.out.println("creelfor.getRCHRDirwarpBeamList() "+creelfor.getRCHRDirwarpBeamList().size());
    	  
    	  for(RCHR_Dirwarp_Beam beamfor : creelfor.getRCHRDirwarpBeamList()){
    		  beamcount = beamcount.add(beamfor.getBeamCount());
    		  System.out.println("Beaam coiut in for loop "+beamcount);
    	  }
    	  beamcount.divide(noofBeams);
    	  System.out.println("beamcount "+beamcount);
    	  totalAverage= totalAverage.add(beamcount);
    	  System.out.println("totalAverage "+totalAverage);
      }
      totalAverage.divide(new BigDecimal(creel.getRchrDirectwarp().getRCHRDirwarpCreelList().size()));
      System.out.println("totalAverage after divide resultant "+totalAverage);
      
      beam.getRchrDirwarpCreel().getRchrDirectwarp().setBeamcount(totalAverage);
      System.out.println("After Vinay process ");
      
      
      
      //*************************************************************************
      */
      
      //long deduction = lines.getA()+lines.getAone()+lines.getB()+lines.getSl();
      OBCriteria<RCHR_Dirwarp_Beam> beamlist = OBDal.getInstance().createCriteria(RCHR_Dirwarp_Beam.class);
      beamlist.add(Restrictions.eq(RCHR_Dirwarp_Beam.PROPERTY_RCHRDIRWARPCREEL, creel));	
      beamlist.addOrder(Order.asc(RCHR_Dirwarp_Beam.PROPERTY_LINENO));
		
      //System.out.println("list size is..."+beamlist.list().size());
       BigDecimal totalBreaks = beam.getTotalbreaks();
       BigDecimal length =beam.getBeamlength();
       BigDecimal bpm =beam.getBreaksPer();
       BigDecimal netWeight =beam.getNetweight();
       BigDecimal speed =beam.getSpeed();
       Timestamp toTime=beam.getTotime();
       
       
	  for(RCHR_Dirwarp_Beam beamLines : beamlist.list())
	  {
		  
		  //toTime=beamLines.getTotime();
		  totalBreaks=totalBreaks.add(beamLines.getTotalbreaks());
		  length=length.add(beamLines.getBeamlength());
		  bpm=bpm.add(beamLines.getBreaksPer());
		  netWeight=netWeight.add(beamLines.getNetweight());
		  speed= speed.add(beamLines.getSpeed());
		  	  
	  }
	  
	  // creel utilization calculation..
	  
	  
	  creel.setTotalbreaks(totalBreaks);
	  creel.setCreellength(length);
	  if(beamlist.list().size()==0){
	  creel.setBreaksPer(bpm);
	  
	  }else{
		  creel.setBreaksPer(bpm.divide(new BigDecimal(beamlist.list().size()+1), MathContext.DECIMAL32));
		  speed= speed.divide(new BigDecimal(beamlist.list().size()+1), MathContext.DECIMAL32);
		  }
	  //creel.setYarnWeight(netWeight);
	  
	  creel.setWarpyarnWeight(netWeight);
	  creel.setTotime(toTime); 
	  String duration= getTimeDuration(new Date(creel.getFromtime().getTime()), new Date(toTime.getTime()));
	  creel.setTimedifference(duration);
	  
	  creel.setShiftinmins(getTimeDurationMins(duration));
	  BigDecimal actualPrdn= speed.multiply(new BigDecimal(getTimeDurationMins(duration)));
	  creel.setUtilization((length.divide(actualPrdn,MathContext.DECIMAL32)).multiply(new BigDecimal(100)));
	  
	  //BigDecimal remWeight=netWeight.subtract(creel.getWarpyarnWeight());
	  BigDecimal remWeight= creel.getYarnWeight().subtract(netWeight) ;
	  
	  creel.setRemtarnWeight(remWeight);
	  if(netWeight.longValue()>0){
	  creel.setRemimentPer(remWeight.divide(netWeight,MathContext.DECIMAL32).multiply(new BigDecimal(100)));
	  } //creel.
	  
	  
		}
	public void onUpdate(@Observes EntityUpdateEvent event) {
		if(!isValidEvent(event))
		{
			return;
		}
		final RCHR_Dirwarp_Beam beam = (RCHR_Dirwarp_Beam)event.getTargetInstance();
	      RCHR_Dirwarp_Creel creel = beam.getRchrDirwarpCreel();
	      //RCHRQualitystandard standard = inspSheet.getRchrQualitystandard();
	      
	      //long deduction = lines.getA()+lines.getAone()+lines.getB()+lines.getSl();
	      OBCriteria<RCHR_Dirwarp_Beam> beamlist = OBDal.getInstance().createCriteria(RCHR_Dirwarp_Beam.class);
	      beamlist.add(Restrictions.eq(RCHR_Dirwarp_Beam.PROPERTY_RCHRDIRWARPCREEL, creel));	
	      beamlist.addOrder(Order.asc(RCHR_Dirwarp_Beam.PROPERTY_LINENO));
			
	      /*
	      
	    //***************************************************************************************
	    	 By Vinay
	    	 * 
	    	 * Getting the Average Beam Count of all the Beams of the selected Direct Warping Row 
	    	 * 
	    	  
	        
	        BigDecimal beamcount = beam.getRchrDirwarpCreel().getRchrDirectwarp().getBeamcount();
	        System.out.println("creel.getRchrDirectwarp().getRCHRDirwarpCreelList() "+creel.getRchrDirectwarp().getRCHRDirwarpCreelList().size());
	        BigDecimal totalAverage = BigDecimal.ZERO;
	        
	        for(RCHR_Dirwarp_Creel creelfor : creel.getRchrDirectwarp().getRCHRDirwarpCreelList()){
	      	  BigDecimal noofBeams = BigDecimal.ZERO;
	      	  noofBeams =new BigDecimal(creelfor.getRCHRDirwarpBeamList().size());
	      	  System.out.println("creelfor.getRCHRDirwarpBeamList() "+creelfor.getRCHRDirwarpBeamList().size());
	      	  
	      	  for(RCHR_Dirwarp_Beam beamfor : creelfor.getRCHRDirwarpBeamList()){
	      		  beamcount = beamcount.add(beamfor.getBeamCount());
	      		  System.out.println("Beaam coiut in for loop "+beamcount);
	      	  }
	      	  beamcount.divide(noofBeams);
	      	  System.out.println("beamcount "+beamcount);
	      	  totalAverage= totalAverage.add(beamcount);
	      	  System.out.println("totalAverage "+totalAverage);
	        }
	        totalAverage.divide(new BigDecimal(creel.getRchrDirectwarp().getRCHRDirwarpCreelList().size()));
	        System.out.println("totalAverage after divide resultant "+totalAverage);
	        
	        beam.getRchrDirwarpCreel().getRchrDirectwarp().setBeamcount(totalAverage);
	        System.out.println("After Vinay process ");
	        
	        
	        
	        //*************************************************************************
	        
	      
	      */
	      
	      
	      
		//long sum = 0;

	       BigDecimal totalBreaks = new BigDecimal(0);
	       BigDecimal length =new BigDecimal(0);
	       BigDecimal bpm =new BigDecimal(0);
	       BigDecimal netWeight =new BigDecimal(0);
	       BigDecimal speed =new BigDecimal(0);
	       Timestamp toTime=beam.getTotime();
	  
	       for(RCHR_Dirwarp_Beam beamLines : beamlist.list())
	 	  {
	 		  
	 		  toTime=beamLines.getTotime();
	 		  totalBreaks=totalBreaks.add(beamLines.getTotalbreaks());
	 		  length=length.add(beamLines.getBeamlength());
	 		  bpm=bpm.add(beamLines.getBreaksPer());
	 		 speed= speed.add(beamLines.getSpeed());
	 		  netWeight=netWeight.add(beamLines.getNetweight());
	 		  
	 		  	  
	 	  }
	 	  
	       
	       
	 	  creel.setTotalbreaks(totalBreaks);
	 	  creel.setCreellength(length);
	 	 // System.out.println("list size is..."+beamlist.list().size());
	 	 if(beamlist.list().size()==0){
	 		  creel.setBreaksPer(bpm);   
	 		  }else{
	 		  creel.setBreaksPer(bpm.divide(new BigDecimal(beamlist.list().size()), MathContext.DECIMAL32));
	 		  speed= speed.divide(new BigDecimal(beamlist.list().size()), MathContext.DECIMAL32);
	 		  }
	 	 //creel.setYarnWeight(netWeight);
		  creel.setWarpyarnWeight(netWeight);
	 	  creel.setTotime(toTime); 
	 	 String duration= getTimeDuration(new Date(creel.getFromtime().getTime()), new Date(toTime.getTime()));
		  creel.setTimedifference(duration);
		  
		  creel.setShiftinmins(getTimeDurationMins(duration));
		  BigDecimal actualPrdn= speed.multiply(new BigDecimal(getTimeDurationMins(duration)));
		  creel.setUtilization((length.divide(actualPrdn,MathContext.DECIMAL32)).multiply(new BigDecimal(100)));
		  
		  BigDecimal remWeight= creel.getYarnWeight().subtract(netWeight) ;
		  creel.setRemtarnWeight(remWeight);
		  if(netWeight.longValue()>0){
			  creel.setRemimentPer(remWeight.divide(netWeight,MathContext.DECIMAL32).multiply(new BigDecimal(100)));
			  }
		  }
		public void onDelete(@Observes EntityDeleteEvent event) {
		    if (!isValidEvent(event)) {
		      return;
		    }
		    final RCHR_Dirwarp_Beam beam = (RCHR_Dirwarp_Beam)event.getTargetInstance();
		      RCHR_Dirwarp_Creel creel = beam.getRchrDirwarpCreel();
		      //RCHRQualitystandard standard = inspSheet.getRchrQualitystandard();
		      
		      //long deduction = lines.getA()+lines.getAone()+lines.getB()+lines.getSl();
		      OBCriteria<RCHR_Dirwarp_Beam> beamlist = OBDal.getInstance().createCriteria(RCHR_Dirwarp_Beam.class);
		      beamlist.add(Restrictions.eq(RCHR_Dirwarp_Beam.PROPERTY_RCHRDIRWARPCREEL, creel));	
		      beamlist.addOrder(Order.asc(RCHR_Dirwarp_Beam.PROPERTY_LINENO));
				
			  //long sum = 0;
		      // System.out.println("list size is..."+beamlist.list().size());
		       BigDecimal totalBreaks = new BigDecimal(0);
		       BigDecimal length =new BigDecimal(0);
		       BigDecimal bpm =new BigDecimal(0);
		       BigDecimal speed =new BigDecimal(0);
		       BigDecimal netWeight =new BigDecimal(0);
		       Timestamp toTime= new Timestamp(new Date().getTime());
		       for(RCHR_Dirwarp_Beam beamLines : beamlist.list())
			  {   
		    	   if(!beamLines.getId().equals(beam.getId())){
				   toTime=beamLines.getTotime();
		 		   totalBreaks=totalBreaks.add(beamLines.getTotalbreaks());
		 		   length=length.add(beamLines.getBeamlength());
		 		   bpm=bpm.add(beamLines.getBreaksPer());
		 		   speed= speed.add(beamLines.getSpeed());
		 		   netWeight=netWeight.add(beamLines.getNetweight());
		    	   }  
			  }
			  
		       
		       creel.setTotalbreaks(totalBreaks);
			   creel.setCreellength(length);
			   //creel.setYarnWeight(netWeight);
				  creel.setWarpyarnWeight(netWeight);
			   // bpm = bpm.subtract(beam.getBreaksPer());
		       
		 	 if(beamlist.list().size()<=1){
		 		  creel.setBreaksPer(bpm);   
		 		}else{
		 	      creel.setBreaksPer(bpm.divide(new BigDecimal(beamlist.list().size()-1), MathContext.DECIMAL32));
		 	     speed= speed.divide(new BigDecimal(beamlist.list().size()-1), MathContext.DECIMAL32); 
		 		}
		 	  
		 	  creel.setTotime(toTime); 
		 	 String duration= getTimeDuration(new Date(creel.getFromtime().getTime()), new Date(toTime.getTime()));
			  creel.setTimedifference(duration);
			  
			  creel.setShiftinmins(getTimeDurationMins(duration));
			  BigDecimal actualPrdn= speed.multiply(new BigDecimal(getTimeDurationMins(duration)));
			  if(actualPrdn.longValue() >0){
			  creel.setUtilization((length.divide(actualPrdn,MathContext.DECIMAL32)).multiply(new BigDecimal(100)));
			  }
			  BigDecimal remWeight= creel.getYarnWeight().subtract(netWeight);
			  creel.setRemtarnWeight(remWeight);
			  if(netWeight.longValue()>0){
				  creel.setRemimentPer(remWeight.divide(netWeight,MathContext.DECIMAL32).multiply(new BigDecimal(100)));
				  }
	 }
		
		
	public String getTimeDuration(Date dateIn , Date dateOut){
		String res="00:00:00";
        try {
            //String strpunchin = info.getStringParameter("inpfromtime", null);
           // String strpunchout = info.getStringParameter("inptotime", null);
        	 SimpleDateFormat sdftNew = new SimpleDateFormat("HH:mm:ss");
             //Date punchoutOT=sdftNew.parse(shiftOut);
             String strpunchin=sdftNew.format(dateIn);
             String strpunchout=sdftNew.format(dateOut);
        	
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date dateTo = sdf.parse(strpunchin);
           // System.out.println("dateTo  is ..."+dateTo);
            Date dateFrom = sdf.parse(strpunchout);
           // System.out.println("Date to ..."+dateTo);
           // System.out.println("dateFrom  is ..."+dateFrom);
            String str[] = strpunchin.split(":");
            String str1[] = strpunchout.split(":");

            if (str.length == 0 || str1.length == 0) {
                throw new NullPointerException("In time is null");
            }
            int hours = new Integer(str[0]).intValue();
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
            String out = sdf.format(new Date(calFrom.getTimeInMillis()));
            String result = TimeDiffUtil.getworkedhours("13-11-2013", in, out);
            res = new String(result.replaceAll("-", ""));
             //res = new String(result.replaceAll("-", ""));
            //info.addResult("inptimedifference", res);
//            info.addResult("inpshiftinmins", getShiftInMinsByInp(info));
            //info.addResult("inpshiftinmins", getShiftInMins(res));
            System.out.println("result duration is..."+result);
        } catch (ParseException ex) {
            //ex.printStackTrace();
        }
       return res;
    }
  public long getTimeDurationMins(String shftHrs){
	  shftHrs=new String(shftHrs.replaceAll("-", ""));
      String[] str = shftHrs.split(":");
      int hours = 0;
      int mins = 0;
      int seconds = 0;
      
      hours = str[0] == null ? 0 : Integer.valueOf(str[0]);
      mins = str[1] == null ? 0 : Integer.valueOf(str[1]);
      seconds = str[2] == null ? 0 : Integer.valueOf(str[2]);
      
      long result = (hours *60) + (mins * 1) + (seconds/60);
      return result;
  }
		
	}


