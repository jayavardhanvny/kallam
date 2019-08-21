package com.redcarpet.epcg.ad_process;

import org.hibernate.criterion.Restrictions;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import java.math.RoundingMode;
import java.util.Date;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.utils.CryptoUtility;

import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.epcg.data.epcg_ppcenquiry;


public class EPCG_CostEnquiryProcess extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	
    	
    	
    	try{
    		doIt(bundle);
    	}catch(Exception e){
    		
    	}
 
    }
   public void  doIt(ProcessBundle bundle){
	   String id = (String) bundle.getParams().get("Epcg_Costenquiry_ID");
    	EpcgCostenquiry cost=OBDal.getInstance().get(EpcgCostenquiry.class, id);
        epcg_ppcenquiry ppc=OBProvider.getInstance().get(epcg_ppcenquiry.class);
        ppc.setOrganization(cost.getOrganization());
        ppc.setDocumentNo(cost.getDocumentNo());
        ppc.setWidthinches(cost.getWidthinches());
        ppc.setTotalvariablecostmts(cost.getTotalvariablecostmts());
        ppc.setCreditperiodper30(cost.getCreditperiodper30());
        ppc.setContributionrsploom(cost.getContributionrsploom());
        ppc.setEnquirydate(cost.getEnquirydate());
        ppc.setEpcgCostenquiry(cost);
        ppc.setExmillpricerspermts(cost.getExmillpricerspermts());
        ppc.setSalescommission(cost.getSalescommission());
        ppc.setYarncostmts(cost.getYarncostmts());
        ppc.setCommission(cost.getCommission());
        ppc.setTc(cost.getTc());
        ppc.setProduct(cost.getProduct());
        ppc.setDeliverdate(cost.getDeliverdate());
        ppc.setProcess(Boolean.TRUE);
        ppc.setOncontract(cost.getQstandard());
        ppc.setOrderquantity(cost.getOrderquantity());
        ppc.setAlertStatus("DR");
        ppc.setUserid(cost.getUserid());
        cost.setAlertStatus("PN");
        cost.setProcess(Boolean.TRUE);
        OBDal.getInstance().save(ppc);
        
        final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Processed to the PPC Confirmation Successfully");
        bundle.setResult(msg);
    }
    

}
