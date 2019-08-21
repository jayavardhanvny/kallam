package com.redcarpet.epcg.ad_process;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.financialmgmt.calendar.NonBusinessDay;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.erpCommon.utility.OBError;

import com.redcarpet.epcg.data.Epcg_EpcgNew;
import com.redcarpet.epcg.data.EpcgMacSpare;
import com.redcarpet.epcg.data.EpcgTarget;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

public class TenureMonthsCalculation extends DalBaseProcess{

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		
		System.out.println("isnide do execute");
		System.out.println(bundle.getParams() +"are params");
		
		String id = (String)bundle.getParams().get("Epcg_Epcgnew_ID");
		System.out.println(id +" is id ");
		Epcg_EpcgNew epcg = OBDal.getInstance().get(Epcg_EpcgNew.class, id);
        BigDecimal tmonths=epcg.getTenureYears();
        int tmonthsin=tmonths.intValue();
         System.out.println(tmonths +" is Tenure Months");
        
         Date sdate=epcg.getPermitFromDate();
         Date edate=epcg.getPermitToDate();
         
         System.out.println(sdate +" is Starting Date");
         System.out.println(edate +" is Ending Date");
       
        
         BigDecimal lamount=epcg.getTotalTarget();
         System.out.println(lamount +" is Target Amount");
         
         
         BigDecimal dueamt=(lamount.divide(tmonths));
         System.out.println(dueamt +" is Due calculated Amount");
         BigDecimal outamt=epcg.getTotalTarget();
        
      	
       
        	 SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
     			
                  	try{
                  		
                  	  for(int i=0;i<tmonthsin;i++)
                      {
                  		
                  		EpcgTarget targetline = OBProvider.getInstance().get(EpcgTarget.class);
                        
                       	targetline.setTargetAmount(dueamt);
                    	targetline.setEpcgEpcgnew(epcg);
                    	targetline.setFromDate(sdate);
                    	
      		   			
     		             Calendar c = Calendar.getInstance(); 
     		             c.setTime(sdate); 
     		             c.add(Calendar.DAY_OF_MONTH, -1);
     		             c.add(Calendar.YEAR,1);
     		             
     		             sdate =c.getTime(); 
     		             
     		             System.out.println("date1 after inc" + sdate);
     		             
        	             targetline.setToDate(sdate);
        	             
        	            
     		             c.setTime(sdate); 
     		             c.add(Calendar.DAY_OF_MONTH, 1);
     		             sdate =c.getTime(); 
     		             
                	
		
			    final OBError msg = new OBError();
		        msg.setType("Success");
		        msg.setTitle("Done");
		        msg.setMessage("Process completed successfully");
		        bundle.setResult(msg);
                  
		
			   OBDal.getInstance().save(targetline);
		    
                  
                 }   
     	}
         
         catch(Exception e){
				System.out.println(e);
				
		      	}


		System.out.println("done --- exiting");
		
		
	}
}