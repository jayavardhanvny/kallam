package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.RchrConstantType;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class OnDutyApproval extends DalBaseProcess implements BundleProcess {
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        final OBError msg = new OBError();
        try{
            doIt(bundle);
            msg.setType("Success");
            msg.setTitle("Done");
            msg.setMessage("Approved Process Successfully");
            OBDal.getInstance().commitAndClose();
        }catch (Exception e){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(e.getMessage());
            OBDal.getInstance().rollbackAndClose();
        }
        bundle.setResult(msg);

    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {

        String strOnDutyId = (String)bundle.getParams().get("Rchr_Onduty_ID");
        RchrOnduty rchrOnduty = OBDal.getInstance().get(RchrOnduty.class,strOnDutyId);
        rchrOnduty.setApprove(Boolean.TRUE);
        for(RchrOndutylines rchrOndutylines : rchrOnduty.getRchrOndutylinesList()){

            updateDailyAttendanceDemo(rchrOndutylines);
/*

            OBCriteria<RCHRDailyattenddemo> rchrDailyattenddemoOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattenddemo.class);
            rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_ATTENDANCEDATE,rchrOndutylines.getOddate()));
            rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_EMPLOYEE,rchrOnduty.getEmployee()));

            if(rchrDailyattenddemoOBCriteria.list().size()>0 ){
                for(RCHRDailyattenddemo rchrDailyattenddemo: rchrDailyattenddemoOBCriteria.list()){
                    // If Punch Records are Null...
                    //if(rchrDailyattenddemo.getErrorLog().trim().length()==0) {
                    rchrDailyattenddemo.setDaytype(RchrConstantType.DAY_TYPE_OD);
                    //rchrDailyattenddemo.getRchrDailyattend().setLeave(Boolean.TRUE);
                    rchrDailyattenddemo.getRchrDailyattend().setDaytype(RchrConstantType.DAY_TYPE_OD);
                    rchrDailyattenddemo.getRchrDailyattend().setReasonForLeave("OD Applied");
                    //}
                    rchrDailyattenddemo.setPresent(Boolean.TRUE);
                    if(rchrDailyattenddemo.isWeeklyOff())
                        rchrDailyattenddemo.setPresent(Boolean.FALSE);
                }
            }else{
                RCHRDailyattenddemo rchrDailyattenddemo = OBProvider.getInstance().get(RCHRDailyattenddemo.class);


                rchrDailyattenddemo.setPresent(Boolean.TRUE);



                if(rchrOndutylines.isWeeklyOff())
                    rchrDailyattenddemo.setPresent(Boolean.FALSE);

                rchrDailyattenddemo.setOrganization(rchrOnduty.getOrganization());
                rchrDailyattenddemo.setDaytype(RchrConstantType.DAY_TYPE_OD);
                rchrDailyattenddemo.setAttendanceDate(rchrOndutylines.getOddate());
                rchrDailyattenddemo.setEmployee(rchrOnduty.getEmployee());
                //rchrDailyattenddemo.setGrievance(Boolean.TRUE);
                //rchrDailyattenddemo.setGrievance(Boolean.TRUE);
                rchrDailyattenddemo.setEmployeeId(rchrOnduty.getEmployee().getDocumentNo());
                OBDal.getInstance().save(rchrDailyattenddemo);
            }

            */
        }
    }

    private void updateDailyAttendanceDemo(RchrOndutylines obj){
        OBCriteria<RCHRDailyattenddemo> rchrDailyattenddemoOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattenddemo.class);
        rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_ATTENDANCEDATE,obj.getOddate()));
        rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_EMPLOYEE,obj.getRchrOnduty().getEmployee()));

        if(rchrDailyattenddemoOBCriteria.list().size()>0 ){
            for(RCHRDailyattenddemo rchrDailyattenddemo: rchrDailyattenddemoOBCriteria.list()){
                // If Punch Records are Null...
                //if(rchrDailyattenddemo.getErrorLog().trim().length()==0) {
                rchrDailyattenddemo.setDaytype(RchrConstantType.DAY_TYPE_OD);
                //rchrDailyattenddemo.getRchrDailyattend().setLeave(Boolean.TRUE);
                rchrDailyattenddemo.getRchrDailyattend().setDaytype(RchrConstantType.DAY_TYPE_OD);
                rchrDailyattenddemo.getRchrDailyattend().setReasonForLeave("OD Applied");
                rchrDailyattenddemo.setPresent(Boolean.TRUE);
                if(rchrDailyattenddemo.isWeeklyOff())
                    rchrDailyattenddemo.setPresent(Boolean.FALSE);
                //}
            }
        }else{
            OBCriteria<RCHRDailyattend> rchrDailyattendOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattend.class);
            rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_ATTENDANCEDATE,obj.getOddate()));
            //rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_EMPLOYEE,obj.getRchrOnduty().getEmployee()));
            rchrDailyattendOBCriteria.add(Restrictions.eq(RCHRDailyattend.PROPERTY_EMPLOYEEID,obj.getRchrOnduty().getEmployee().getDocumentNo()));
            // Post-Dated...
            System.out.println("size of Od "+rchrDailyattendOBCriteria.list().size());
            if(rchrDailyattendOBCriteria.list().size()>0){
                System.out.println("Post dated"+rchrDailyattendOBCriteria.list().size());
                for (RCHRDailyattend rchrDailyattend : rchrDailyattendOBCriteria.list()) {
                    rchrDailyattend.setValidate(Boolean.FALSE);
                    //rchrDailyattend.setLeave(Boolean.TRUE);
                    rchrDailyattend.setDaytype(RchrConstantType.DAY_TYPE_OD);
                    rchrDailyattend.setPresent(Boolean.TRUE);
                    rchrDailyattend.setReasonForLeave("OD Applied");
                }
            }else{
                // Pre-Dated
                System.out.println("Predated"+rchrDailyattendOBCriteria.list().size());
                RCHRDailyattend rchrDailyattend = OBProvider.getInstance().get(RCHRDailyattend.class);
                rchrDailyattend.setAttendanceDate(obj.getOddate());
                rchrDailyattend.setOrganization(obj.getOrganization());
                rchrDailyattend.setEmployee(obj.getRchrOnduty().getEmployee());
                rchrDailyattend.setEmployeeId(obj.getRchrOnduty().getEmployee().getPunchno());
                rchrDailyattend.setEmployeeName(obj.getRchrOnduty().getEmployee().getEmployeeName());
                rchrDailyattend.setPresent(Boolean.TRUE);
                if(obj.isWeeklyOff())
                    rchrDailyattend.setPresent(Boolean.FALSE);
                rchrDailyattend.setValidate(Boolean.FALSE);
                //rchrDailyattend.setLeave(Boolean.TRUE);
                rchrDailyattend.setReasonForLeave("OD Applied");
                rchrDailyattend.setDaytype(RchrConstantType.DAY_TYPE_OD);
                OBDal.getInstance().save(rchrDailyattend);
            }
        }

    }
}

