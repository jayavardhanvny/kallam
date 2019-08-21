package com.redcarpet.epcg.ad_process;

import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import java.math.RoundingMode;
import java.util.Date;
import javax.servlet.ServletException;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.epcg.data.epcg_ppcenquiry;


public class EPCG_PPCEnquiryProcess extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	
        try{
        	doIt(bundle);
        }catch(Exception e){
        	
        }
    }

    public void doIt(ProcessBundle bundle){
    	
    	
    	String id = (String) bundle.getParams().get("Epcg_Ppcenquiry_ID");
    	epcg_ppcenquiry ppc=OBDal.getInstance().get(epcg_ppcenquiry.class, id);
    	if((ppc.getRchrQualitystandard()!=null) || (!ppc.getRchrQualitystandard().equals(""))){
    		ppc.getEpcgCostenquiry().setAlertStatus("CO");
        	ppc.getEpcgCostenquiry().setRchrQualitystandard(ppc.getRchrQualitystandard());
        	//ppc.getEpcgCostenquiry().setQstandard(ppc.getQstandard());
        	ppc.setAlertStatus("CO");
        	ppc.setProcess(Boolean.TRUE);
    	}else{
    		String language = OBContext.getOBContext().getLanguage().getLanguage();
		    ConnectionProvider conn = new DalConnectionProvider(false);
		    throw new OBException(Utility.messageBD(conn, "Sort No. should not be empty", language));
    	}
    	
    	
    	
    	
    	
    	 final OBError msg = new OBError();
         msg.setType("Success");
         msg.setTitle("Done");
         msg.setMessage("Confirmed Successfully ");
         bundle.setResult(msg);
    }

}
