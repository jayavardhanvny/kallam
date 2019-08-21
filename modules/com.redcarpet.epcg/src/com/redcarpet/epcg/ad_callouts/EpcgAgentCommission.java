package com.redcarpet.epcg.ad_callouts;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import org.openbravo.base.secureApp.VariablesSecureApp;

import org.openbravo.dal.service.OBDal;
import org.openbravo.data.FieldProvider;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.erpCommon.utility.Utility;


import org.openbravo.utils.FormatUtilities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.common.enterprise.DocumentType;




public class EpcgAgentCommission extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		
        String strAgentId = info.getStringParameter("inpagent", null);
        String strBpartnerId = info.getStringParameter("inpcBpartnerId", null);
        //By using Business Parnter Id we can get Payment Method, Currency, Prices list,
        System.out.println("Business Partner ID 1 "+strBpartnerId);
        if(!strBpartnerId.equals("")){
        	BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class,strBpartnerId);
        	
        	//System.out.println("Price List 1 " +bp.getPriceList().getId());
        	//System.out.println("Currence 2 " +bp.getPriceList().getCurrency().getId());
        	//System.out.println("Payment Method 3 " +bp.getPaymentMethod());
        	//System.out.println("Payment Term 4 " +bp.getPaymentTerms());
        	
        	
        	info.addResult("inpmPricelistId",bp.getPriceList()==null ? "FA26A2CD001F4DA79117A24022CBC32F" : bp.getPriceList().getId());
        	info.addResult("inpcCurrencyId",bp.getPriceList().getCurrency()==null ? "304" : bp.getPriceList().getCurrency().getId());
        	//info.addResult("inpfinPaymentmethodId",bp.getPaymentMethod()==null ? "B147F9E2F6874A63BA63187C9297E929" : bp.getPaymentMethod().getId());
        	//info.addResult("inpcPaymenttermId",bp.getPaymentTerms()==null ? "99BAAA114EC349139B456C23CF8365F5" : bp.getPaymentTerms().getId());
        	
        	//info.addResult("inpepcgInsurancetypeId",bp.getPaymentTerms().getId());
        }
        
        
        if(!strAgentId.equals("")){
        	BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class,strAgentId);
            if(bp.isEmployee()){
            	info.addResult("inpsalescommission",bp.getRcobCommission());
            	System.out.println("In If condition is ");
            }
        }
        
        if(strAgentId.equals("")){
        	
            
            	info.addResult("inpsalescommission",new BigDecimal(0));
            
            
        }
	}
}
