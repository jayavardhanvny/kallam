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
import com.redcarpet.epcg.data.EPCG_Bookuser;

public class BookUserCalloutTwo extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		// TODO Auto-generated method stub
		// String lastChanged =
		// info.getStringParameter("inpLastFieldChanged",null);
		// System.out.println(lastChanged + " lastChanged");

		
		 String billoncustmill = info.getStringParameter("inpbilloncustmill", null);
		 if(billoncustmill.equals("Y"))
		 {
		 BigDecimal billingprice = info.getBigDecimalParameter("inpbillingprice");
		 System.out.println("billingprice is" +billingprice);
		 BigDecimal custnetwt = info.getBigDecimalParameter("inpcustnetwt");
		 System.out.println("custnetwt is" +custnetwt);
         BigDecimal resassessvalone = billingprice.multiply(custnetwt);
		 System.out.println("resassessvalone is" +resassessvalone);
		 BigDecimal cashprice = info.getBigDecimalParameter("inpcashprice");
		 BigDecimal restwo=cashprice.multiply(custnetwt);

         info.addResult("inpbvassv", resassessvalone);
         info.addResult("inpnbvtotlv", restwo);
		 } else
		 {
			 BigDecimal billingprice = info.getBigDecimalParameter("inpbillingprice");
			 BigDecimal millnetwt = info.getBigDecimalParameter("inpmillnetwt");
	         BigDecimal resassessvaltwo = billingprice.multiply(millnetwt);
			 System.out.println("resassessvaltwo is" +resassessvaltwo);
			 
			 BigDecimal cashprice = info.getBigDecimalParameter("inpcashprice");
			 BigDecimal restwoo=cashprice.multiply(millnetwt);


	         info.addResult("inpbvassv", resassessvaltwo);
	         
	         info.addResult("inpnbvtotlv", restwoo);

		 }

		
}
}