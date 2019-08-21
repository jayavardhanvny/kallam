package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.RCHRDailyattenddemo;
import com.rcss.humanresource.data.RCHRPermission;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.RchrConstantType;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.access.User;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class RchrEmployeePermissionAccept extends DalBaseProcess implements BundleProcess{
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        try {
            doIt(bundle);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {
        String stringId = bundle.getParams().get("Rchr_Permission_ID").toString();
        RCHRPermission rchrPermission = OBDal.getInstance().get(RCHRPermission.class,stringId);
        rchrPermission.setAlertStatus(RchrConstantType.DOCUMENT_STATUS_APPROVED);
        User user = OBDal.getInstance().get(User.class, bundle.getContext().toVars().getUser());
        rchrPermission.setApprovedby(user.getName());
        if (rchrPermission.getRchrDailyattenddemo() == null){
            OBCriteria<RCHRDailyattenddemo> rchrDailyattenddemoOBCriteria = OBDal.getInstance().createCriteria(RCHRDailyattenddemo.class);
            rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_EMPLOYEE,rchrPermission.getEmployee()));
            rchrDailyattenddemoOBCriteria.add(Restrictions.eq(RCHRDailyattenddemo.PROPERTY_ATTENDANCEDATE,rchrPermission.getPermisindate()));
            for (RCHRDailyattenddemo rchrDailyattenddemo : rchrDailyattenddemoOBCriteria.list()){
                rchrDailyattenddemo.setRchrPermission(rchrPermission);
		        rchrPermission.setRchrDailyattenddemo(rchrDailyattenddemo);
            }
        }
    }
}

