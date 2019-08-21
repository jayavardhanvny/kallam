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
import com.redcarpet.epcg.data.EPCGChequebook;
import com.redcarpet.epcg.data.EPCGCblines;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;

public class DisplayCheque extends SimpleCallout {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	String strPayMethodId = info.getStringParameter("inpfinPaymentmethodId", null);
    	String strFinAccountId = info.getStringParameter("inpfinFinancialAccountId", null);

        //String strCblinesId = info.getStringParameter("inpepcgCblinesId", null);
       
        //System.out.println(strPayMethodId + " is strPayMethodId");
        //System.out.println(strFinAccountId + " is strFinAccountId");
        FIN_FinancialAccount finaccount = OBDal.getInstance().get(FIN_FinancialAccount.class, strFinAccountId);
        String checktype=finaccount.getType();
        info.addResult("inpemEpcgChecktype", checktype);
        
        DisplayChequeData[] data = DisplayChequeData.chequeLines(this, strPayMethodId, strFinAccountId);
        info.addSelect("inpepcgCblinesId");
        for (int i = 0; i < data.length; i++) {
            String id = data[i].id;
            String cheque = data[i].cheque;
            //System.out.println("id is " + id + " cheque is " + cheque);
            info.addSelectResult(id, cheque);
        }
        info.endSelect();
        
    }
}
