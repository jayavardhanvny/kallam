package com.redcarpet.payroll.ad_actionbutton;

import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import com.redcarpet.payroll.data.RcplDeprtmtstorerequsition;

public class RCPL_DepartmentalStoreApproveProcess extends DalBaseProcess{
	@Override
	public void doExecute(ProcessBundle bundle) throws Exception {
		String strProcessId = (String)bundle.getParams().get("Rcpl_Deprtmtstorerequsition_ID");
		System.out.println("strProcessId "+strProcessId);
		
		RcplDeprtmtstorerequsition rcplDeprtReq = OBDal.getInstance().get(RcplDeprtmtstorerequsition.class, strProcessId);
		rcplDeprtReq.setAlertStatus("Approved");
		rcplDeprtReq.setProcess(true);
		
		
	}
	
}