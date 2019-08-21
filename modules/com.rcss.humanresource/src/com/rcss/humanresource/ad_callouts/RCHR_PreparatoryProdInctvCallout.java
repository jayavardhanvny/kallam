package com.rcss.humanresource.ad_callouts;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import javax.servlet.ServletException;

import com.rcss.humanresource.data.RchrEmployee;

public class RCHR_PreparatoryProdInctvCallout extends SimpleCallout{
	@Override
    protected void execute(CalloutInfo info) throws ServletException {
		String id=info.getStringParameter("inprchrEmployeeId", null);
		RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class,id);
		info.addResult("inprchrDesignationId", employee.getDesignation().getId());
		
	}
}

