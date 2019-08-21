package com.redcarpet.epcg.ad_process;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.financialmgmt.calendar.NonBusinessDay;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.erpCommon.utility.OBError;

import com.redcarpet.epcg.data.EPCGChequebook;
import com.redcarpet.epcg.data.EPCGCblines;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChequeBookLeafsCreation extends DalBaseProcess{

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		
		//System.out.println("isnide do execute");
		//System.out.println(bundle.getParams() +"are params");
		
		String id = (String)bundle.getParams().get("Epcg_Chequebook_ID");
		//System.out.println(id +" is id ");
		EPCGChequebook cb = OBDal.getInstance().get(EPCGChequebook.class, id);
		String cbid=cb.getId();
		BigDecimal noleafs=cb.getNoofleafs();
		int noofleafs=noleafs.intValue();
		String chenos=cb.getStartcqno();
        cb.setProcessed(true);
        BigDecimal chenosnew=new BigDecimal(chenos);
        //System.out.println(chenosnew +" is chenosnew after convert into bigdecimal");
        
int lenone=chenos.length();
//System.out.println("before converting length is" + lenone);
String sone=chenosnew.toString();
int lentwo=sone.length();
//System.out.println("after converting length is" + lentwo);
int lendiff=lenone-lentwo;
//System.out.println("length diff is" + lendiff);
String spad="";

for(int k=1;k<=lendiff;k++)
{
	String snewone="0";
	spad=spad+snewone;
//	System.out.println("spad is" +spad);
}


     
         for(int i=1;i<=noofleafs;i++)
         {
        	
        	 EPCGCblines cblines = OBProvider.getInstance().get(EPCGCblines.class);
        	 cblines.setEpcgChequebook(cb);
        	 cblines.setOrganization(cb.getOrganization());

        	
        	 String ssone=chenosnew.toString();
        	// System.out.println("ssone is" + ssone);

        	 int ione =Integer.parseInt(ssone);
        	 //System.out.println("ione is" + ione);

        	 String sstwo=spad.concat(ssone);
        	 
        	 //System.out.println("sstwo is" + sstwo);
        	 
        	 cblines.setChequeno(sstwo);
        	 
             BigDecimal addvar=new BigDecimal("1");
             chenosnew=chenosnew.add(addvar);
        	 //System.out.println("after adding checkno is" + chenosnew);
        	 
        	final OBError msg = new OBError();
 	        msg.setType("Success");
 	        msg.setTitle("Done");
 	        msg.setMessage("Process completed successfully");
 	        bundle.setResult(msg);
 	
          

			OBDal.getInstance().save(cblines);
			
         
     	}
        
         
        
		//System.out.println("done --- exiting");
		
		
	}
}