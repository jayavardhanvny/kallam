package com.redcarpet.epcg.ad_process;

import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.financialmgmt.calendar.NonBusinessDay;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.erpCommon.utility.OBError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.lang.String;
import com.redcarpet.epcg.data.EPCG_Bookuser;
import com.redcarpet.epcg.data.EPCGBookmngmnt;

public class UserToManagement extends DalBaseProcess{

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
	try{
		//String id = (String)bundle.getParams().get("EPCG_Bookuser_ID");
		//System.out.println(id +" is id");	
		//String queryd="delete from hrms_bookuser where hrms_bookuser_id='" + id + "'";
		final OBCriteria<EPCG_Bookuser> bookuserList = OBDal.getInstance().createCriteria(EPCG_Bookuser.class);
		//bookuserList.add(Restrictions.eq(EPCG_Bookuser.PROPERTY_ID, id));
		for (EPCG_Bookuser bookuser : bookuserList.list()) {
			EPCGBookmngmnt bookmngmnt = OBProvider.getInstance().get(EPCGBookmngmnt.class);
			bookmngmnt.setDocumentNo(bookuser.getDocumentNo());
			bookmngmnt.setProduct(bookuser.getProduct());
			bookmngmnt.setWayslipno(bookuser.getWayslipno());
			bookmngmnt.setBusinessPartner(bookuser.getBusinessPartner());
			bookmngmnt.setAgentname(bookuser.getAgentname());
			bookmngmnt.setTotalprice(bookuser.getTotalprice());
			bookmngmnt.setBillingprice(bookuser.getBillingprice());
			bookmngmnt.setCashprice(bookuser.getCashprice());
			bookmngmnt.setTruckno(bookuser.getTruckno());
			bookmngmnt.setFreight(bookuser.getFreight());
			bookmngmnt.setNobbb(bookuser.getNobbb());
			bookmngmnt.setCustgrswt(bookuser.getCustgrswt());
			bookmngmnt.setCusttrewt(bookuser.getCusttrewt());
			bookmngmnt.setCustnetwt(bookuser.getCustnetwt());
			bookmngmnt.setMillgrswt(bookuser.getMillgrswt());
			bookmngmnt.setMilltrewt(bookuser.getMilltrewt());
			bookmngmnt.setMillnetwt(bookuser.getMillnetwt());
			bookmngmnt.setBilloncustmill(bookuser.isBilloncustmill());
			bookmngmnt.setBillno(bookuser.getBillno());
			bookmngmnt.setBvassv(bookuser.getBvassv());
			bookmngmnt.setBvtaxv(bookuser.getBvtaxv());
			bookmngmnt.setBvtotlv(bookuser.getBvtotlv());
			bookmngmnt.setNbvtotlv(bookuser.getNbvtotlv());
			bookmngmnt.setGrstotlv(bookuser.getGrstotlv());
			OBDal.getInstance().save(bookmngmnt);
			String id=bookuser.getId();
			Connection con=OBDal.getInstance().getSession().connection();
			String queryd="delete from epcg_bookuser where epcg_bookuser_id='" + id + "'";
			con.createStatement().execute(queryd);
	        System.out.println("executed");
		}
		
        
        final OBError msg = new OBError();
	        msg.setType("Success");
	        msg.setTitle("Done");
	        msg.setMessage("Process completed successfully");
	        bundle.setResult(msg);
	}catch(Exception e){
		e.printStackTrace();
		}
	}
	}