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

import com.redcarpet.epcg.data.EpcgCbuser;
import com.redcarpet.epcg.data.EpcgCbmngmnt;

public class JUserToManagement extends DalBaseProcess{

	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
	try{
		final OBCriteria<EpcgCbuser> epcgCbuser = OBDal.getInstance().createCriteria(EpcgCbuser.class);
		for (EpcgCbuser user : epcgCbuser.list()) {
			EpcgCbmngmnt book = OBProvider.getInstance().get(EpcgCbmngmnt.class);
			book.setSdate(user.getSdate());
			book.setEpcgDivision(user.getEpcgDivision());
			book.setEpcgAcctmaster(user.getEpcgAcctmaster());
			book.setEpcgJaccount(user.getEpcgJaccount());
			book.setDescription(user.getDescription());
			book.setVarone(user.getVarone());
			book.setVartwo(user.getVartwo());
			book.setVarthree(user.getVarthree());
			book.setDebit(user.getDebit());
			book.setCredit(user.getCredit());
			OBDal.getInstance().save(book);
		}
		 final OBError msg = new OBError();
	        msg.setType("Success");
	        msg.setTitle("Done");
	        msg.setMessage("Process completed successfully");
	        bundle.setResult(msg);
			Connection con=OBDal.getInstance().getSession().connection();
	String queryd="delete from epcg_cbuser";
	
	con.createStatement().execute(queryd);
    System.out.println("executed");
}catch(Exception e){
	e.printStackTrace();
	}
}
}
