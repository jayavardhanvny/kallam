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
import org.openbravo.utils.FormatUtilities;

public class GinningUserCalloutTwo extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		// TODO Auto-generated method stub
		// String lastChanged =
		// info.getStringParameter("inpLastFieldChanged",null);
		// System.out.println(lastChanged + " lastChanged");
	   
		 BigDecimal candy = info.getBigDecimalParameter("inpcandy");   
		 BigDecimal noofcandy = info.getBigDecimalParameter("inpnoofcandy"); 
		 
		 BigDecimal costoflint=candy.multiply(noofcandy);

		 //System.out.println("costoflint is" +costoflint);
		 
		 info.addResult("inpcostoflint", costoflint);
		
}
}