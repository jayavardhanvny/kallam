package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.RCHR_LeaveRequisition;
import com.rcss.humanresource.util.ApproverStageUtility;
import org.hibernate.Query;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class TotalLeaveCancel extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle)  {
        final String strLeaveId = (String)bundle.getParams().get("Rchr_Leaverequisition_ID");
        RCHR_LeaveRequisition rchrLeaveApprove = OBDal.getInstance().get(RCHR_LeaveRequisition.class, strLeaveId);
        final String tabId = (String) bundle.getParams().get("tabId").toString();
        final String userId=bundle.getContext().getUser();
        final String orgId=rchrLeaveApprove.getOrganization().getId();
        final String clientId=rchrLeaveApprove.getClient().getId();
        OBError msg = new OBError();
        try{

            if(!rchrLeaveApprove.getCreatedBy().getId().equals(userId)){
                msg.setType("Error");
                msg.setTitle("Error");
                msg.setMessage("who ever created document only cancel request");
            }else {
                rchrLeaveApprove.setDocumentStatus("CL");
                updateApprovalHistrory(strLeaveId);
                msg.setType("Success");
                msg.setTitle("Done");
                msg.setMessage("Leave Request has been completed");
            }        OBDal.getInstance().commitAndClose();

        }catch(Exception ex){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(ex.getMessage());
            ex.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }

        bundle.setResult(msg);
    }

    public void updateApprovalHistrory(String strLeaveId){
        String hql=" UPDATE RCHR_ApprovalHistrory set decision='CL' "
                + " WHERE recordid=:recordId ";
        Query q=OBDal.getInstance().getSession().createQuery(hql);
        q.setParameter("recordId",strLeaveId);
        q.executeUpdate();
    }
}
