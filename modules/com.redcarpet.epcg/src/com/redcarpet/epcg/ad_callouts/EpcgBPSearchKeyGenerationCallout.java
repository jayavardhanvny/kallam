package com.redcarpet.epcg.ad_callouts;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import org.openbravo.base.secureApp.VariablesSecureApp;

import org.openbravo.dal.service.OBDal;
import org.openbravo.data.FieldProvider;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Category;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.erpCommon.utility.Utility;


import org.openbravo.utils.FormatUtilities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.common.enterprise.DocumentType;




public class EpcgBPSearchKeyGenerationCallout extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		//String strBpId = info.getStringParameter("inpcBpartnerId", null);
		
        String strBpCategoryId = info.getStringParameter("inpcBpGroupId", null);
        
        //BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class,strBpId);
        
        //System.out.println("strBpId "+strBpId);
        //System.out.println("strBpCategoryId "+strBpCategoryId);
        //System.out.println("Description "+bp.getBusinessPartnerCategory().getDescription());
        
        
        Category cat = OBDal.getInstance().get(Category.class,strBpCategoryId);
       
        Long num = cat.getEpcgNextassignnumber()+1;
        
        //if(bp.isEmployee()){
        
        	info.addResult("inpvalue",cat.getDescription().concat(num.toString()));
        	System.out.println("In If condition is ");
       // }
        
	}
}
