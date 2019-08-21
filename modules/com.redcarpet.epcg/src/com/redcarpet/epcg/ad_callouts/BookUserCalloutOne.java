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

public class BookUserCalloutOne extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		// TODO Auto-generated method stub
		// String lastChanged =
		// info.getStringParameter("inpLastFieldChanged",null);
		// System.out.println(lastChanged + " lastChanged");

		
		 BigDecimal totalprice = info.getBigDecimalParameter("inptotalprice");
		 BigDecimal billingprice = info.getBigDecimalParameter("inpbillingprice");
		 BigDecimal rescashprice = totalprice.subtract(billingprice);
		 
		 BigDecimal custgrswt = info.getBigDecimalParameter("inpcustgrswt");
		 BigDecimal custtrewt = info.getBigDecimalParameter("inpcusttrewt");
		 BigDecimal rescusynetwt = custgrswt.subtract(custtrewt);
		 
		 
		 BigDecimal millgrswt = info.getBigDecimalParameter("inpmillgrswt");
		 BigDecimal milltrewt = info.getBigDecimalParameter("inpmilltrewt");
		 BigDecimal resmillnetwt = millgrswt.subtract(milltrewt);
		 
		 
		 
		 System.out.println("rescashprice is" +rescashprice);
		 System.out.println("rescusynetwt is" +rescusynetwt);
		 System.out.println("resmillnetwt is" +resmillnetwt);
		 
		 info.addResult("inpcashprice", rescashprice);
		 
		 info.addResult("inpcustnetwt", rescusynetwt);
		 
		 info.addResult("inpmillnetwt", resmillnetwt);

		
}
}