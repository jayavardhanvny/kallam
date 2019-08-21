package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import com.rcss.humanresource.data.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class SizingDataSheetSetNo extends SimpleCallout{
	/* Sizing Data Sheet Window 
	 * 
	 * Setting the Sort number
	 * Calculate unsizedweight from Gross Weight
	 * Calculate sizepickup from Gross Weight
	 * 
	 * 
	 */
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	String strDirectWarpId = info.getStringParameter("inprchrDirectwarpId", null);
    	
    	
    	
    	RCHR_Directwarp directWarping = OBDal.getInstance().get(RCHR_Directwarp.class, strDirectWarpId);
    	String strQualityStandardId = directWarping.getRchrQualitystandard().getId();
    	String construction = directWarping.getRchrQualitystandard().getQstandard();
    	Long longtotalEnds = directWarping.getRchrQualitystandard().getTotalends();
    	//BigDecimal beamCount = directWarping.getBeamcount();

    	/*if(beamCount==null){
    		beamCount = BigDecimal.ZERO;
    	}
    	*/
    	
    	 
    	
    	
        /*
         //***************************************************************************************
     	 By Vinay
     	 * 
     	 * Getting the Average Beam Count of all the Beams of the selected Direct Warping Row 
     	 * 
     	 */
    	OBCriteria<RCHR_Dirwarp_Creel> creellist = OBDal.getInstance().createCriteria(RCHR_Dirwarp_Creel.class);
    	creellist.add(Restrictions.eq(RCHR_Dirwarp_Creel.PROPERTY_RCHRDIRECTWARP, directWarping));	
    	//creellist.addOrder(Order.asc(RCHR_Dirwarp_Creel.PROPERTY_LINENO));
        
        
        
         BigDecimal beamcount = BigDecimal.ZERO;//beam.getRchrDirwarpCreel().getRchrDirectwarp().getBeamcount();
         System.out.println("creel.getRchrDirectwarp().getRCHRDirwarpCreelList() "+creellist.list().size());
         BigDecimal totalAverage = BigDecimal.ZERO;
         BigDecimal noofBeams = BigDecimal.ZERO;
         
         for(RCHR_Dirwarp_Creel creelfor : creellist.list()){
       	  
       	OBCriteria<RCHR_Dirwarp_Beam> beamlist = OBDal.getInstance().createCriteria(RCHR_Dirwarp_Beam.class);
       	beamlist.add(Restrictions.eq(RCHR_Dirwarp_Beam.PROPERTY_RCHRDIRWARPCREEL, creelfor));
       	
       	//noofBeams.add(new BigDecimal(beamlist.list().size()));
       	System.out.println("creelfor.getRCHRDirwarpBeamList() "+beamlist.list().size());
       	
       	noofBeams = noofBeams.add(new BigDecimal(beamlist.list().size()));
       	System.out.println("noofBeams "+noofBeams);
       	
       	
       	  for(RCHR_Dirwarp_Beam beamfor : beamlist.list()){
       		  beamcount = beamcount.add(beamfor.getBeamCount());
       		  System.out.println("Beaam coiut in for loop "+beamcount);
       	  }
       	  
       	  
       	  
       	  
       	  BigDecimal beamAverage = beamcount.divide(noofBeams, MathContext.DECIMAL32);
       	  
       	  System.out.println("beamAverage "+beamAverage);
       	  totalAverage = totalAverage.add(beamAverage);
       	  System.out.println("totalAverage "+totalAverage);
         }
         
         BigDecimal result = totalAverage.divide(new BigDecimal(creellist.list().size()),MathContext.DECIMAL32);
         
         System.out.println("totalAverage after divide resultant "+result);
         System.out.println("After Vinay process ");
         
         
         
         //*************************************************************************
       
      // Total ends/1693/directWarping.getBeamcount()
     	BigDecimal bdGLM = new BigDecimal(longtotalEnds).divide(new BigDecimal(1693),MathContext.DECIMAL32).divide(result,MathContext.DECIMAL32);
     	 
    	
    	
    	info.addResult("inprchrQualitystandardId", strQualityStandardId);
		info.addResult("inpconstruction", construction);
		info.addResult("inptotalends", new BigDecimal(longtotalEnds));
		//info.addResult("inpyarnsuppliier", directWarping.getYarnsuppliier());
		info.addResult("inpwarperbeams", directWarping.getNoofbeems().multiply(directWarping.getNoofcreels()));
		info.addResult("inpsetlength", directWarping.getSetlength());
		info.addResult("inpbeamcount",result);
		info.addResult("inpglm", bdGLM);
		info.addResult("inprchrJobcardId", directWarping.getRchrJobcard().getId());
		
    }
    
}

