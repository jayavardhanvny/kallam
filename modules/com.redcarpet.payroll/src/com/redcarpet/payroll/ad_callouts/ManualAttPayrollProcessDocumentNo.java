package com.redcarpet.payroll.ad_callouts;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import com.rcss.humanresource.data.RCHRDailyattend;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.util.DocumentNumberUtil;
import com.rcss.humanresource.util.HqlUtils;
import com.redcarpet.payroll.data.RcplEmppprocessmanual;
import org.openbravo.base.secureApp.VariablesSecureApp;

import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.data.FieldProvider;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.utils.FormatUtilities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.common.enterprise.DocumentType;




public class ManualAttPayrollProcessDocumentNo extends SimpleCallout {

	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		
        String strDoctypeId = info.getStringParameter("inpcDoctypeId", null);
		String strTabId = info.getStringParameter("inpTabId", null);
        System.out.println("strDoctypeId is " +strDoctypeId);
        String strMovementDate = info.getStringParameter("inpdate", null);
        System.out.println("strMovementDate in String is " +strMovementDate);

		// Grievances Window Line level RcplEmppprocessmanual Table
        if (strTabId.equals("A88CE224AC9E4285B9742794796EC056")){
			executeGrievanceLines(info);
		} else {
			try {

				if ((strDoctypeId != null) && (strMovementDate != null)) {
					doIt(strDoctypeId, strMovementDate, info);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}




    }
	private void doIt(String strDoctypeId, String strMovementDate, CalloutInfo info) {
	    try {
			DocumentNumberUtil documentNumberUtil = new DocumentNumberUtil();
	        info.addResult("inpdocumentno", documentNumberUtil.getDocumentNo(strDoctypeId,strMovementDate));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void executeGrievanceLines(CalloutInfo info){
		String strEmployeeId = info.getStringParameter("inprchrEmployeeId", null);
		String strDate = info.getStringParameter("inpattddate", null);
		if (!strEmployeeId.equals("") && !strDate.equals("")) {
			try {
				Date fromDate = new SimpleDateFormat("dd-MM-yyyy").parse(strDate);
				HqlUtils hqlUtils = new HqlUtils();
				RchrEmployee employee = OBDal.getInstance().get(RchrEmployee.class,strEmployeeId);
				List<RCHRDailyattend> rchrDailyattendList = hqlUtils.getDailyAttendanceObject(fromDate,employee.getDocumentNo());
				if(rchrDailyattendList.size()>0){
					info.addResult("inprchrDailyattendId",rchrDailyattendList.get(0).getId());
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
	}
	
}
