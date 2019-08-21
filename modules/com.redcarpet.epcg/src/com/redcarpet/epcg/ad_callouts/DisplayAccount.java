package com.redcarpet.epcg.ad_callouts;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import org.openbravo.base.secureApp.VariablesSecureApp;

import org.openbravo.dal.service.OBDal;
import org.openbravo.data.FieldProvider;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.utils.FormatUtilities;
import com.redcarpet.epcg.data.EPCGAcctmaster;
import com.redcarpet.epcg.data.EPCGJaccount;

public class DisplayAccount extends SimpleCallout {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	String strMAccountId = info.getStringParameter("inpepcgAcctmasterId", null);
    	System.out.println("strMAccountId is" +strMAccountId);
       
    	DisplayAccountData[] data = DisplayAccountData.accountLines(this, strMAccountId);
        info.addSelect("inpepcgJaccountId");
        for (int i = 0; i < data.length; i++) {
            String id = data[i].id;
            String account = data[i].account;
            System.out.println("id is " + id + " account is " + account);
            info.addSelectResult(id, account);
        }
        info.endSelect();
        
    }
}
