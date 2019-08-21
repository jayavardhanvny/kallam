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
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.utils.FormatUtilities;

public class OpenStatusDisplay extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		// TODO Auto-generated method stub
		// String lastChanged =
		// info.getStringParameter("inpLastFieldChanged",null);
		// System.out.println(lastChanged + " lastChanged");

		
		 BigDecimal orderopenqty = info.getBigDecimalParameter("inpemEpcgOpenqty");
         System.out.println("orderopenqty    " + orderopenqty);
	    
	     BigDecimal orderqty = info.getBigDecimalParameter("inpqty");
		 BigDecimal qtyzero=new BigDecimal("0");
		
        System.out.println("orderqty    " + orderqty);
	    
	  int res = orderopenqty.compareTo(qtyzero);
	  String s1="";
	  if(res == 1)
	  {
		  s1="Open"; 
	  }else{
			 s1="Completed";
		}
		info.addResult("inpemEpcgStatus", s1);
	  
		
	
		/*int quant =orderopenqty.intValue();
		System.out.println("after converting:" +quant);

		String s1="";
		if (quant > 0) {
			 s1="Open";
		}else{
			 s1="Completed";
		}
		info.addResult("inpemEpcgStatus", s1);
*/


}
}