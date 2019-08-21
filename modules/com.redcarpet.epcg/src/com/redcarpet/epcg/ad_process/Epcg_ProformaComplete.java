package com.redcarpet.epcg.ad_process;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.openbravo.model.ad.access.User;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessContext;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import com.redcarpet.epcg.data.EpcgProforma;
import com.redcarpet.epcg.data.EpcgProformaline;
import org.openbravo.utils.CryptoUtility;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.order.Order;
import org.openbravo.erpCommon.utility.OBMessageUtils;




public class Epcg_ProformaComplete extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	final ProcessContext processContext = bundle.getContext();
    	VariablesSecureApp vars = processContext.toVars();
    	System.out.println("Variable user ==== "+vars.getUser());
    	//RequestContext.get().setVariableSecureApp(processContext.toVars());
    	try{
    		
    		String id = (String)bundle.getParams().get("Epcg_Proforma_ID");
    		
    		EpcgProforma prid=OBDal.getInstance().get(EpcgProforma.class,id);
    		OBError myError = new OBError();
    	      
    		int i=0;
    	      
    	     
    	      
    	      List <EpcgProformaline> prformlines=prid.getEpcgProformalineList();
    	      for( EpcgProformaline prformline:prformlines)
    	      {
    	    	  OrderLine ordline=prformline.getSalesOrderLine();
    	    	  BigDecimal prqty=ordline.getEpcgProformqty();
    	    	  if(ordline.getOrderedQuantity().longValue()<(prqty.add(prformline.getProformalineqty())).longValue())
    	    	  {
    	    		  i++;
    	    		  break;
    	    	  }
    	    	  
    	    	  
    	    	  ordline.setEpcgProformqty(prqty.add(prformline.getProformalineqty()));
    	    	  OBDal.getInstance().save(ordline);
    	    	  
    	      }
    	      if(i>0)
    	      {
    	    		myError = new OBError();
        	        myError.setType("Error");
        	        myError.setTitle(OBMessageUtils.messageBD("Error"));
        	        myError.setMessage(OBMessageUtils.messageBD("Proforma quantity greter than Order quantity ") );
    	            bundle.setResult(myError);

    	      }
    	      else
    	      {
    	            myError.setType("Success");
    	            myError.setTitle(OBMessageUtils.messageBD("Success"));
    	            myError.setMessage(OBMessageUtils.messageBD("Proforma Completed") );
    	            bundle.setResult(myError);

    	            prid.setProcessed(true);
    	      }
    	      
    		//ConfigParameters config = bundle.getConfig();
    		//System.out.println("config "+config.get);
    	
    	}catch(Exception e){
    		
    	}
    }


}
