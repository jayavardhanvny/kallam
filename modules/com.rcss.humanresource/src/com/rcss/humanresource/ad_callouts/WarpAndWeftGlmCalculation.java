package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import com.rcss.humanresource.data.*;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class WarpAndWeftGlmCalculation extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	//getting Warp GLM Parametrs
    	BigDecimal totalEnds = info.getBigDecimalParameter("inptotalends");
    	BigDecimal warpCount = info.getBigDecimalParameter("inpwarpcount");
    	BigDecimal warpCrimp = info.getBigDecimalParameter("inpwarpcrimp");
    	BigDecimal sizePickup = info.getBigDecimalParameter("inpsizepickup");
    	
    	//getting Weft GLM Parametrs
    	BigDecimal epi = info.getBigDecimalParameter("inpepi");
    	BigDecimal weftPPI = info.getBigDecimalParameter("inpppi");
    	BigDecimal weftReedSpace = info.getBigDecimalParameter("inpreedspace");
    	BigDecimal weftCount = info.getBigDecimalParameter("inpweftcount");
    	BigDecimal weftCrimp = info.getBigDecimalParameter("inpweftcrimp"); 
    	
    	// 
    	BigDecimal orderQty = info.getBigDecimalParameter("inporderquantity");
    	String widthId = info.getStringParameter("inprchrInspgreigewidthId", null);
    	RchrInspgreigewidth greige = OBDal.getInstance().get(RchrInspgreigewidth.class, widthId);
    	BigDecimal width = new BigDecimal(greige.getName());
    	
    	// Calculate Warp GLM
    	double warpGlm = (totalEnds.doubleValue()*((((1+warpCrimp.doubleValue()/100)/1693)/warpCount.doubleValue()))*(1+sizePickup.doubleValue()/100))*1000;
    	
    	// Calculate Weft GLM
    	double weftGlm = (weftReedSpace.doubleValue()*weftPPI.doubleValue()*(((1+weftCrimp.doubleValue()/100)/1693)/weftCount.doubleValue()))*1000;
    	
    	//to Wrap Mtrs
    	double warpMtrs = orderQty.doubleValue()*(1+warpCrimp.doubleValue()/100);
    	 
    	// Calculate GLM
    	//double glm = warpGlm+weftGlm;
    	//BigDecimal warp_glm = info.getBigDecimalParameter("inpwarpglm");
    	//BigDecimal weft_glm = info.getBigDecimalParameter("inpweftglm"); 
    	
    	//BigDecimal glm=warp_glm.add(weft_glm);//+weft_glm.doubleValue();
    	 
    	
    	
    	//Modified made by Vinay...
    	//To Calculate GLM 
    	double glmDouble = warpGlm+weftGlm;
    	BigDecimal glm = new BigDecimal(glmDouble);
    	
    	
    	
    	
    	// Warp yarn requisition
    	double warpyrnReq = (orderQty.doubleValue()*warpGlm)/1000;
    	
    	// weft yarn requisition
    	double weftyrnReq = orderQty.doubleValue()*weftGlm/1000;
    	
    	double widthCms = width.doubleValue()*2.54;
    	
    	double widthMtrs = (width.doubleValue()*2.54)/100;
    	
    	double gsm = glm.doubleValue()/widthMtrs;
    	
    	//BigDecimal coverFactWarp = (epi.divide(Math.sqrt(warpCount),MathContext.DECIMAL32));
    	//BigDecimal coverFactWeft = (weftPPI.divide(Math.sqrt(weftCount),MathContext.DECIMAL32));
    	
    	double coverFactor = epi.doubleValue()/Math.sqrt(warpCount.doubleValue())+weftPPI.doubleValue()/Math.sqrt(weftCount.doubleValue());
    	
    	//MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
    	//MathContext mcsec = new MathContext(3, RoundingMode.HALF_UP);
    	System.out.println("glm is... "+glm);
    	
    	info.addResult("inpwarpglm", new BigDecimal(warpGlm));
    	info.addResult("inpweftglm", new BigDecimal(weftGlm));
    	
    	
    	info.addResult("inptowrapmtrs", new BigDecimal(warpMtrs));
    	info.addResult("inpcoverfactor", new BigDecimal(coverFactor));
    	//info.addResult("inpglm", new BigDecimal(glm));
    	info.addResult("inpglm", glm);
    	info.addResult("inpwarpyarnreq", new BigDecimal(warpyrnReq));
    	info.addResult("inpweftyarnreq", new BigDecimal(weftyrnReq));
    	info.addResult("inpwidthcms", new BigDecimal(widthCms));
    	info.addResult("inpwidthmtrs", new BigDecimal(widthMtrs));
    	info.addResult("inpgsm", new BigDecimal(gsm));
    	
        //info.addResult("inpremainingquantity",orderquantity);
        //info.addResult("inprchrInspclothtypeId",strCloth);
       
    }
    
}
