package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.RCHR_LeaveRequisition;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import com.rcss.humanresource.util.ApproverStageUtility;

public class LeaveRequest  extends DalBaseProcess {

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
                    msg.setMessage("Leave Request possible for only who are created document");
                }else {
                    ApproverStageUtility utility = new ApproverStageUtility();

                    if (utility.processRequest(tabId, userId, orgId, clientId, strLeaveId, rchrLeaveApprove.getUpdated())) {
                        RCHR_LeaveRequisition newRchrLeaveApprove = OBDal.getInstance().get(RCHR_LeaveRequisition.class, strLeaveId);
                        newRchrLeaveApprove.setDocumentStatus("PN");
                        System.out.println("yes template");
                        newRchrLeaveApprove.setPost(Boolean.TRUE);
                    } else {
                        RCHR_LeaveRequisition newRchrLeaveApprove = OBDal.getInstance().get(RCHR_LeaveRequisition.class, strLeaveId);
                        newRchrLeaveApprove.setDocumentStatus("AP");
                        newRchrLeaveApprove.setPost(Boolean.TRUE);
                        System.out.println("No template");
                    }
                    //  rchrLeaveApprove.setDocumentStatus("PN");
                    rchrLeaveApprove.setPost(Boolean.TRUE);
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
}
