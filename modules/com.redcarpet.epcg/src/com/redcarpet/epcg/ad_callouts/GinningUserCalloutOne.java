package com.redcarpet.epcg.ad_callouts;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

public class GinningUserCalloutOne extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		// TODO Auto-generated method stub
		// String lastChanged =
		// info.getStringParameter("inpLastFieldChanged",null);
		// System.out.println(lastChanged + " lastChanged");

		
		 BigDecimal ot = info.getBigDecimalParameter("inpot");
		 BigDecimal addedot = info.getBigDecimalParameter("inpaddedot");
		 BigDecimal rate = info.getBigDecimalParameter("inprate");
		 BigDecimal seedprice = info.getBigDecimalParameter("inpseedprice");
		 BigDecimal gngexp = info.getBigDecimalParameter("inpgngexp");
		 BigDecimal bridge = info.getBigDecimalParameter("inpbridge");
		 BigDecimal tarewt = info.getBigDecimalParameter("inptarewt");
		 
		 BigDecimal resnew=bridge.subtract(tarewt);
		 info.addResult("inpnetwt", resnew);
		 
		 BigDecimal res1=ot.add(addedot);
         BigDecimal res2=rate.multiply(res1);
		 BigDecimal exvarone=new BigDecimal("100");
		 BigDecimal res3=res2.divide(exvarone, 6, RoundingMode.UP);
		 BigDecimal exvartwo=new BigDecimal("376");
		 BigDecimal res4=res1.subtract(exvartwo);
		 BigDecimal res5=res4.multiply(seedprice);
		 BigDecimal res6=gngexp.subtract(res5);
		 
		 BigDecimal finres=res3.add(res6);
		// System.out.println("finres is" +finres);
		 
		 BigDecimal cpresone=rate.divide(exvarone, 6, RoundingMode.UP);
		 BigDecimal fincpres=resnew.multiply(cpresone);
		// System.out.println("fincpres is" +fincpres);
		 
		 BigDecimal exvarthree=new BigDecimal("356");
		 BigDecimal resnone=new BigDecimal("0");
		 BigDecimal finalresn=new BigDecimal("0");
		 if(res1.compareTo( BigDecimal.ZERO) == 0)
		 {
			 //System.out.println("inside if");
		 } else
		 {
			 resnone=exvarthree.divide(res1, 6, RoundingMode.UP);
			 BigDecimal resntwo=resnone.divide(exvarone);
			 BigDecimal resnthree=resnew.multiply(resntwo);
			 BigDecimal resnfour=resnthree.multiply(exvarone); 
			 finalresn=resnfour.divide(exvarthree, 6, RoundingMode.UP);
			 //System.out.println("finalresn is" +finalresn);
		 }
		
		 info.addResult("inpcandy", finres);
		 info.addResult("inpcottonpay", fincpres);
		 info.addResult("inpnoofcandy", finalresn);
		 
		
}
}