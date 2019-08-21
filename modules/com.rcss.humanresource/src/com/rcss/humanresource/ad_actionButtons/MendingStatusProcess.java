package com.rcss.humanresource.ad_actionButtons;

import java.math.BigDecimal;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.hibernate.criterion.Restrictions;

import com.rcss.humanresource.data.RCHR_Inspectionsheet;
import com.rcss.humanresource.data.RCHR_LeaveOpenBalance;
import com.rcss.humanresource.data.RCHR_LeaveRequisition;
import com.rcss.humanresource.data.RCHR_Leavetype;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrEmployeeleaveBal;
import com.rcss.humanresource.data.RchrLossOfPay;

public class MendingStatusProcess extends DalBaseProcess {
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		String sheetId = (String)bundle.getParams().get("Rchr_Inspectionsheet_ID");
		RCHR_Inspectionsheet sheet = OBDal.getInstance().get(RCHR_Inspectionsheet.class, sheetId);
		sheet.setMenidngstatus("MNP");
		sheet.setMenidngprocess(Boolean.TRUE);
		final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Mending Roll Passed Successfully");
        bundle.setResult(msg);
	}
}
