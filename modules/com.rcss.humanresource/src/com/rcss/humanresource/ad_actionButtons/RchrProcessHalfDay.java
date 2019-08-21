package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.RCHRDailyattenddemo;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrHolidaysList;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.MessageBody;
import com.rcss.humanresource.util.RchrConstantType;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class RchrProcessHalfDay extends DalBaseProcess implements BundleProcess{
    private static final Logger logger = Logger.getLogger(RchrProcessHalfDay.class);
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        OBError obError ;
        MessageBody messageBody = new MessageBody();
        try {
            doIt(bundle);
            obError = messageBody.getSuccessMessage();
            OBDal.getInstance().commitAndClose();
        } catch (Exception e){
            obError = messageBody.getErrorMessage(e.getMessage());
            logger.error("Occored Error is : "+ e.getMessage());
            e.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }
        bundle.setResult(obError);
    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {
        String stringId = (String)bundle.getParams().get("Rchr_Holidays_List_ID");
        RchrHolidaysList rchrHolidaysList = OBDal.getInstance().get(RchrHolidaysList.class, stringId);
        OBCriteria<RchrEmployee> rchrEmployeeOBCriteria = OBDal.getInstance().createCriteria(RchrEmployee.class);
        rchrEmployeeOBCriteria.add(Restrictions.eq(RchrEmployee.PROPERTY_HALFDAYAPPLICABLE,Boolean.TRUE));
        for (RchrEmployee rchrEmployee : rchrEmployeeOBCriteria.list()){
            OBCriteria<RCHRDailyattenddemo> rchrDailyattenddemoOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattenddemo.class);
            rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_EMPLOYEE,rchrEmployee));
            rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_ATTENDANCEDATE,rchrHolidaysList.getHolidaydate()));
            logger.info("Before PunchIn and PunchOut Criteria "+rchrDailyattenddemoOBCriteria.list().size());
            rchrDailyattenddemoOBCriteria.add(Restrictions.isNotNull(RCHRDailyattenddemo.PROPERTY_PUNCHIN));
            rchrDailyattenddemoOBCriteria.add(Restrictions.isNotNull(RCHRDailyattenddemo.PROPERTY_PUNCHOUT));
            logger.info("Before Presenet Criteria "+rchrDailyattenddemoOBCriteria.list().size());
            rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_PRESENT,Boolean.FALSE));
            for (RCHRDailyattenddemo rchrDailyattenddemo : rchrDailyattenddemoOBCriteria.list()){
                rchrDailyattenddemo.setPresent(Boolean.TRUE);
                rchrDailyattenddemo.setDaytype(RchrConstantType.DAY_TYPE_PRESENT);
                rchrDailyattenddemo.getRchrDailyattend().setReasonForLeave(rchrDailyattenddemo.getRchrDailyattend().getReasonForLeave().concat("Given Present Based on Half Day"));
            }
            logger.info("Wheather it is came or not "+rchrDailyattenddemoOBCriteria.list().size());
        }
        logger.info("No Of Employees Half Day Applicable "+rchrEmployeeOBCriteria.list().size());
        rchrHolidaysList.setAttendanceprocess(Boolean.TRUE);
    }
}
