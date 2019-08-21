package com.redcarpet.epcg.ad_callouts;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.data.FieldProvider;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.plm.Product;
import com.rcss.humanresource.data.RCHRWarpyarntype;
import com.redcarpet.epcg.data.EpcgYarncosttemplate;
import com.redcarpet.epcg.data.EpcgYarncosttemplatelines;
import com.rcss.humanresource.data.RCHRQualitystandard;
import javax.servlet.ServletException;
import java.text.DecimalFormat;


public class EnquiryCostingFormSortNo extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		
		String tabId = info.getStringParameter("inpTabId",null);
		String tableId = info.getStringParameter("inpTableId",null);		
		String sortno = info.getStringParameter("inprchrQualitystandardId",null);
		System.out.println("Tab Id "+ tabId+ " Table Id "+tableId);
		RCHRQualitystandard no=OBDal.getInstance().get(RCHRQualitystandard.class, sortno);
		// 
		if(tabId.equals("BF0E644336F04D50B57D31564B507401") && tableId.equals("9E51403242424F40B4871E2AA980C73B")){
			info.addResult("inpqstandard",no.getQstandard());

		} // IN MOF(Manufacturing Order Form ) 
		else if(tabId.equals("5C6DFB8968CE4D89B5A1CB2328BF4640") && tableId.equals("D130372A6E7C4444BDBAF6F7E9F3C454")){
			info.addResult("inpqstandard",no.getQstandard());
			info.addResult("inponcontract",no.getPartyconstruction());
		}
		 		
		
	}
	
}
