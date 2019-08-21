package com.redcarpet.quality.ad_process;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.financialmgmt.calendar.NonBusinessDay;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.erpCommon.utility.OBError;

import com.redcarpet.quality.data.RcqaWrapbreak;
import com.redcarpet.quality.data.RcqaWrapbreakline;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class WrapBreakLines extends DalBaseProcess{

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		
		System.out.println("isnide do execute");
		System.out.println(bundle.getParams() +"are params");
		
		String id = (String)bundle.getParams().get("Rcqa_Wrapbreak_ID");
		System.out.println(id +" is id ");
		RcqaWrapbreak wrap = OBDal.getInstance().get(RcqaWrapbreak.class, id);
        long drums=wrap.getNoofdrums();
        
        String wrapid=wrap.getId();
        
        wrap.setButton(true);
       
       
         System.out.println(drums +" is no of drums");
        
         
        int drumsone=(int)drums;
        System.out.println("drumnsone value is:" +drumsone);
     
         for(int i=0;i<drumsone;i++)
         {
        	
        	RcqaWrapbreakline wrapline = OBProvider.getInstance().get(RcqaWrapbreakline.class);
        	
        	wrapline.setRcqaWrapbreak(wrap);
        	wrapline.setDrumno(drums);
        	long newvar=0;
        	wrapline.setNoofbreaks(newvar);
		
        	drums=drums-1;
        	
        	// RcqaWrapbreak wrapnewone = OBProvider.getInstance().get(RcqaWrapbreak.class);
            // wrapnewone.setButton(true);
			
		        final OBError msg = new OBError();
		        msg.setType("Success");
		        msg.setTitle("Done");
		        msg.setMessage("Process completed successfully");
		        bundle.setResult(msg);
		
			OBDal.getInstance().save(wrapline);
			
         
     	}
         
         
        
		System.out.println("done --- exiting");
		
		
	}
}