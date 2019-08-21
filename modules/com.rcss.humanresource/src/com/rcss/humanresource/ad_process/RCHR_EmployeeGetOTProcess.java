package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.util.BundleProcess;
import com.redcarpet.payroll.ad_actionbutton.AttendanceUtil;
import org.omg.CORBA.TRANSACTION_MODE;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import java.util.logging.Logger;
import org.hibernate.criterion.Restrictions;


public class RCHR_EmployeeGetOTProcess extends DalBaseProcess implements BundleProcess{
	final private Logger logger = Logger.getLogger("RCHR_EmployeeGetOTProcess.java");
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
		final OBError msg = new OBError();
		try {
			doIt(bundle);
			msg.setType("Success");
			msg.setTitle("Done");
			msg.setMessage("Processed to the Confirmation Successfully");
			OBDal.getInstance().commitAndClose();
		}catch (Exception exception){
			msg.setType("Error");
			msg.setTitle("Error");
			msg.setMessage("Error is : "+exception.getMessage());
			OBDal.getInstance().rollbackAndClose();
			logger.warning("In Exception "+exception.getMessage());
		}
		bundle.setResult(msg);
	}

	@Override
	public void doIt(ProcessBundle bundle) throws Exception {
		final String id = bundle.getParams().get("Rchr_Overtimeprocess_ID").toString();
		RchrOvertimeprocess overTimeProcess = OBDal.getInstance().get(RchrOvertimeprocess.class, id);
		overTimeProcess.setGetotprocess(Boolean.TRUE);
		OBCriteria<RchrAttendanceProod> rchrAttendanceProodOBCriteria= OBDal.getInstance().createCriteria(RchrAttendanceProod.class);
		rchrAttendanceProodOBCriteria.add(Restrictions.eq(RchrAttendanceProod.PROPERTY_FROMDATE,overTimeProcess.getDate()));
		//logger.info("Production Muster Heder Size "+rchrAttendanceProodOBCriteria.list().size());
		for (RchrAttendanceProod rchrAttendanceProod : rchrAttendanceProodOBCriteria.list()){
			OBCriteria<RchrAttendProdLine> rchrAttendProdLineOBCriteria = OBDal.getInstance().createCriteria(RchrAttendProdLine.class);
			rchrAttendProdLineOBCriteria.add(Restrictions.eq(RchrAttendProdLine.PROPERTY_RCHRATTENDANCEPROOD,rchrAttendanceProod));
			rchrAttendProdLineOBCriteria.add(Restrictions.eq(RchrAttendProdLine.PROPERTY_OVERTIME,Boolean.TRUE));
			//logger.info("Production Muster Lines List Size "+rchrAttendProdLineOBCriteria.list().size());
			for(RchrAttendProdLine rchrAttendProdLine : rchrAttendProdLineOBCriteria.list()){
				OBCriteria<RchrAttendanceTimeoff> rchrAttendanceTimeoffOBCriteria = OBDal.getInstance().createCriteria(RchrAttendanceTimeoff.class);
				rchrAttendanceTimeoffOBCriteria.add(Restrictions.eq(RchrAttendanceTimeoff.PROPERTY_FROMDATE,overTimeProcess.getDate()));
				rchrAttendanceTimeoffOBCriteria.add(Restrictions.eq(RchrAttendanceTimeoff.PROPERTY_RCPRSHIFT,rchrAttendanceProod.getRcprShift()));
				//logger.info("Time Office Muster Header Size "+rchrAttendanceTimeoffOBCriteria.list().size());
				for (RchrAttendanceTimeoff rchrAttendanceTimeoff : rchrAttendanceTimeoffOBCriteria.list()){
					OBCriteria<RchrAttendanceToLine> rchrAttendanceToLineOBCriteria = OBDal.getInstance().createCriteria(RchrAttendanceToLine.class);
					rchrAttendanceToLineOBCriteria.add(Restrictions.eq(RchrAttendanceToLine.PROPERTY_RCHRATTENDANCETIMEOFF,rchrAttendanceTimeoff));
					rchrAttendanceToLineOBCriteria.add(Restrictions.eq(RchrAttendanceToLine.PROPERTY_EMPLOYEE,rchrAttendProdLine.getEmployee()));
					rchrAttendanceToLineOBCriteria.setMaxResults(1);
					//logger.info("Time Office Muster Lines List Size "+rchrAttendanceToLineOBCriteria.list().size());
					if(rchrAttendanceToLineOBCriteria.list().size()==1){
						for(RchrAttendanceToLine rchrAttendanceToLine : rchrAttendanceToLineOBCriteria.list()){
							OBCriteria<RchrOvertimeprocessLine> rchrOvertimeprocessLineOBCriteria = OBDal.getInstance().createCriteria(RchrOvertimeprocessLine.class);
							rchrOvertimeprocessLineOBCriteria.add(Restrictions.eq(RchrOvertimeprocessLine.PROPERTY_EMPLOYEE, rchrAttendanceToLine.getEmployee()));
							rchrOvertimeprocessLineOBCriteria.add(Restrictions.eq(RchrOvertimeprocessLine.PROPERTY_RCHROVERTIMEPROCESS,overTimeProcess));
							if(rchrOvertimeprocessLineOBCriteria.list().size()==0){
								RchrOvertimeprocessLine overTimeprocessLine = OBProvider.getInstance().get(RchrOvertimeprocessLine.class);
								//overTimeprocessLine.setWeeklyOff(demo.isWeeklyOff());
								overTimeprocessLine.setRchrOvertimeprocess(overTimeProcess);
								overTimeprocessLine.setOrganization(overTimeProcess.getOrganization());
								overTimeprocessLine.setEmployee(rchrAttendanceToLine.getEmployee());
								//overTimeprocessLine.setOvertime(true);
								overTimeprocessLine.setHeaddate(overTimeProcess.getDate());
								overTimeprocessLine.setPresent(false);
								overTimeprocessLine.setRcprShift(rchrAttendanceProod.getRcprShift());
								overTimeprocessLine.setRchrAttendanceToLine(rchrAttendanceToLine);
								overTimeprocessLine.setRchrAttendProdLine(rchrAttendProdLine);
								OBDal.getInstance().save(overTimeprocessLine);
							}else{

							}

						}
					}else{
						//logger.warning("In Else Condition ");
					}
				}
			}
		}
	}
}
