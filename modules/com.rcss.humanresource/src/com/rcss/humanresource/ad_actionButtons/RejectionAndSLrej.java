package com.rcss.humanresource.ad_actionButtons;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.hibernate.criterion.Restrictions;

import com.rcss.humanresource.data.RCHRQualitystandard;
import com.rcss.humanresource.data.RCHR_Inspcategoryline;
import com.rcss.humanresource.data.RCHR_Inspclothtype;
import com.rcss.humanresource.data.RCHR_Inspectionsheet;
import com.rcss.humanresource.data.RCHR_LeaveOpenBalance;
import com.rcss.humanresource.data.RCHR_LeaveRequisition;
import com.rcss.humanresource.data.RCHR_Leavetype;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrEmployeeleaveBal;
import com.rcss.humanresource.data.RchrLossOfPay;

public class RejectionAndSLrej extends DalBaseProcess {
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		
		String strSheetId = (String)bundle.getParams().get("Rchr_Inspectionsheet_ID");
		
		RCHR_Inspectionsheet sheet = OBDal.getInstance().get(RCHR_Inspectionsheet.class, strSheetId);
	
		BigDecimal actual = sheet.getTotalmtr();
		double slAndBvalue = (sheet.getBtotal().add(sheet.getSltotal())).doubleValue();
		double rejpercent = (slAndBvalue/actual.doubleValue())*100;
		double slRejection = (sheet.getSltotal().doubleValue()/actual.doubleValue())*100;
		MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
		sheet.setRejectioneffi(new BigDecimal(rejpercent, mc));
		sheet.setSlrejectioneffi(new BigDecimal(slRejection, mc));
		//cloth calout
		//MathContext mc = new MathContext(2, RoundingMode.DECIMAL32);
		//String strCloth = standrd.getRchrInspclothtype().getId();
		//MathContext.DECIMAL32
		BigDecimal reaminingqty= new BigDecimal(0);
		RCHRQualitystandard standrd = sheet.getRchrQualitystandard();
		if(standrd.getRemainingquantity().longValue()>0){
		
		 reaminingqty =  standrd.getRemainingquantity().subtract(sheet.getAtotal()).subtract(sheet.getAonetotal());
		}
		
		if(standrd.getRemainingquantity().longValue() <= 0 ){
			sheet.setRemainingquantity(new BigDecimal(0));
			standrd.setRemainingquantity(new BigDecimal(0));
		}else{
			sheet.setRemainingquantity(reaminingqty);
			standrd.setRemainingquantity(reaminingqty);
		}
		standrd.getRchrInspclothtype().getId();
		sheet.setEfficiency(Boolean.TRUE);
		//OBDal.getInstance().save(strii;d);
		OBDal.getInstance().save(sheet);
		final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Calculated Rjection Efiiciency Successfully");
        bundle.setResult(msg);
	}
}