package com.redcarpet.epcg.ad_actionbuttons;

import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import org.openbravo.erpCommon.utility.OBError;
import org.hibernate.criterion.Restrictions;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

import com.redcarpet.epcg.data.EpcgYarncosttemplate;
import com.redcarpet.epcg.data.EpcgYarncosttemplatelines;



public class EPCG_CostConfirmProcess extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	String id = (String) bundle.getParams().get("Epcg_Yarncosttemplate_ID");
        
    	EpcgYarncosttemplate ppc=OBDal.getInstance().get(EpcgYarncosttemplate.class, id);
    	
    	if(ppc.getEpcgYarncosttemplatelinesList().size()!=0){
    		Calendar cal = Calendar.getInstance();
            cal.setTime(ppc.getCostingfromdate()); 
            cal.add(Calendar.DATE, -1);
            
            OBCriteria<EpcgYarncosttemplate> criteria = OBDal.getInstance().createCriteria(EpcgYarncosttemplate.class);
            criteria.add(Restrictions.eq(EpcgYarncosttemplate.PROPERTY_ALERTSTATUS,"OP"));
            
            //System.out.println("List Size of Opened "+criteria.list().size());
            
            for(EpcgYarncosttemplate cost : criteria.list()){
            	cost.setAlertStatus("CL");
            	
            	cost.setCostingtodate(cal.getTime());
            	for(EpcgYarncosttemplatelines lines : cost.getEpcgYarncosttemplatelinesList()){
            		lines.setProcessed(Boolean.TRUE);
            	}
            }
            
        	ppc.setAlertStatus("OP");
        	
        	ppc.setProcess(Boolean.TRUE);
        	final OBError msg = new OBError();
            msg.setType("Success");
            msg.setTitle("Done");
            msg.setMessage(ppc.getDocumentNo()+" was Successfully Authorized");
            bundle.setResult(msg);
        	
    	}else{
    		throwError();
    	}
        
    }

    private void throwError() throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, "Lines are not created", language));
    }

}
