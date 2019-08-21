package com.redcarpet.epcg.ad_callouts;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
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
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.utils.FormatUtilities;
import com.redcarpet.epcg.data.EPCGGinninguser;

public class GinningUserCalloutFour extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		// TODO Auto-generated method stub
		// String lastChanged =
		// info.getStringParameter("inpLastFieldChanged",null);
		// System.out.println(lastChanged + " lastChanged");
	   
		  try
	        {      	
	        Connection conn = OBDal.getInstance().getConnection();
	        
	        Statement stmt1 = conn.createStatement();
	        String s2 = "select count(*) as total from epcg_ginningmgmt";
	               System.out.println("count s2" + s2);
	               ResultSet rs1 = stmt1.executeQuery(s2);
	               System.out.println("count value:" + rs1);
	               while (rs1.next()) {
	                   int r = rs1.getInt("total");
	                   if (r == 0) {                   
	                         System.out.println("inside if");
	                             } else
	                             {
	                            	 
	                            	  Statement stmt = conn.createStatement();
	                                  String sqlqry = "select addedot,seedprice,gngexp from epcg_ginningmgmt order by created desc limit 1";
	                                        
	                                         ResultSet rs = stmt.executeQuery(sqlqry);
	                                       
	                                         BigDecimal addedot=new BigDecimal("0");
	                                         BigDecimal seedprice=new BigDecimal("0");
	                                         BigDecimal gngexp=new BigDecimal("0");
	                                         
	                                         while (rs.next()) {
	                                              addedot=rs.getBigDecimal("addedot");
	                                              seedprice=rs.getBigDecimal("seedprice");
	                                              gngexp=rs.getBigDecimal("gngexp");
	                                         }
	                                         info.addResult("inpaddedot", addedot);
	                                         info.addResult("inpseedprice", seedprice);
	                                         info.addResult("inpgngexp", gngexp);
	                                                     	
	                             }
	               }
	   
	        } catch(Exception e){
	System.out.println(e);
	}
	}
	}