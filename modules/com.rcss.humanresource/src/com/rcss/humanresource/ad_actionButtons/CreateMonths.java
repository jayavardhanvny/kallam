package com.rcss.humanresource.ad_actionButtons;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import java.util.Date;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import com.rcss.humanresource.data.RCHR_Inspectionsheet;
import com.rcss.humanresource.data.RchrRollmaster;
import com.rcss.humanresource.data.RchrRollnomonthlist;

public class CreateMonths extends DalBaseProcess {
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		String strRollMasterId = (String)bundle.getParams().get("Rchr_Rollmaster_ID");
		RchrRollmaster master = OBDal.getInstance().get(RchrRollmaster.class, strRollMasterId);
		master.getYear().getFiscalYear();
		//System.out.println("master.getYear().getFiscalYear() "+master.getYear().getFiscalYear());
		for(Period period : master.getYear().getFinancialMgmtPeriodList()){
			RchrRollnomonthlist monthlist = OBProvider.getInstance().get(RchrRollnomonthlist.class);
			monthlist.setOrganization(master.getOrganization());
			monthlist.setPeriod(period);
			monthlist.setFromDate(period.getStartingDate());
			monthlist.setToDate(period.getEndingDate());
			
			monthlist.setMonthchar(this.getMonthchar(period.getStartingDate()));
			monthlist.setRchrRollmaster(master);
			OBDal.getInstance().save(monthlist);
			
		}
		
		 //org.openbravo.model.financialmgmt.calendar.Year year = 
		//RCHR_Inspectionsheet sheet = OBDal.getInstance().get(RCHR_Inspectionsheet.class, id)	 
	}
	
	public String getMonthchar(Date startdate){
		int monthno =startdate.getMonth();
		//System.out.println("startdate.getMonth(); "+startdate.getMonth());
		String chare = "";
	    if(monthno==0){
	    	chare="A";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==1){
	    	chare="B";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==2){
	    	chare="C";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(monthno==3){
	    	chare="D";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(monthno==4){
	    	chare="E";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==5){
	    	chare="F";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==6){
	    	chare="G";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==7){
	    	chare="H";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(monthno==8){
	    	chare="I";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==9){
	    	chare="J";
	    	// info.addResult("inppiecechar", chare);
	    }else if(monthno==10){
	    	chare="K";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(monthno==11){
	    	chare="L";
	    	 
	    }else{
	    	chare="Enter Char";
	    }
	    
	    return chare;
	}
}