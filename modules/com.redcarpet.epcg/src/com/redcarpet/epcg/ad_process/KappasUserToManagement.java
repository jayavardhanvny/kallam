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

import com.redcarpet.epcg.data.EPCGGinninguser;
import com.redcarpet.epcg.data.EPCGGinningmgmt;

public class KappasUserToManagement extends DalBaseProcess{

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
	try{
		final OBCriteria<EPCGGinninguser> epcgCbuser = OBDal.getInstance().createCriteria(EPCGGinninguser.class);
		for (EPCGGinninguser user : epcgCbuser.list()) {
			EPCGGinningmgmt book = OBProvider.getInstance().get(EPCGGinningmgmt.class);
			book.setOrganization(user.getOrganization());
			book.setDocumentNo(user.getDocumentNo());
			book.setGinningdate(user.getGinningdate());
			book.setEpcgKappasagent(user.getEpcgKappasagent());
			book.setUnloadat(user.getUnloadat());
			book.setHeeps(user.getHeeps());
			book.setLorryno(user.getLorryno());
			book.setBridge(user.getBridge());
			book.setNetwt(user.getNetwt());
			book.setTarewt(user.getTarewt());
			book.setRate(user.getRate());
			book.setOt(user.getOt());
			book.setAddedot(user.getAddedot());
			book.setSeedprice(user.getSeedprice());
			book.setGngexp(user.getGngexp());
			book.setCandy(user.getCandy());
			book.setSpace(user.getSpace());
			book.setCottonpay(user.getCottonpay());
			book.setNoofcandy(user.getNoofcandy());
			book.setCostoflint(user.getCostoflint());
			book.setColtwo(user.getColtwo());
			book.setWaybslipno(user.getWaybslipno());
			book.setEpcgKappasbuyer(user.getEpcgKappasbuyer());
			OBDal.getInstance().save(book);
		}
		 final OBError msg = new OBError();
	        msg.setType("Success");
	        msg.setTitle("Done");
	        msg.setMessage("Process completed successfully");
	        bundle.setResult(msg);
			Connection con=OBDal.getInstance().getSession().connection();
	String queryd="delete from epcg_ginninguser";
	con.createStatement().execute(queryd);
    System.out.println("executed");
}catch(Exception e){
	e.printStackTrace();
	}
}
}
