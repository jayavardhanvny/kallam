package com.rcss.humanresource.util;

import com.rcss.humanresource.data.*;
import org.hibernate.Query;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import java.util.Date;
import java.util.List;

public class ApprovalReject extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle)  {
        final String strApproveId = (String)bundle.getParams().get("Rchr_Approvalhistrory_ID");
        RchrApprovalHistrory history = OBDal.getInstance().get(RchrApprovalHistrory.class, strApproveId);
        String userId=bundle.getContext().getUser();
        String tabId= history.getTab().getId();
        String recordId=history.getRecordid();
        User u=OBDal.getInstance().get(User.class,userId);
        OBError msg = new OBError();
        String entityName="";
        entityName=getTableEntityName(tabId);
        try{
            if(history.getApproveuser().equals(u)){
            if(history.getReason()==null||history.getReason().equals("")||history.getReason()==""){
                msg.setType("Error");
                msg.setTitle("Error");
                msg.setMessage("Reason field Should'nt be Empty,please fill before reject");
            }else{
            msg.setType("Success");
            msg.setTitle("Done");
            msg.setMessage("Leave Reject has been completed");
            updateBestOffUser(tabId,recordId,history.getRchrApprovaltemplate().getId(),u,history.getRchrApprovalstage(),history.getUpdatecount());
            updateRecordStatus(entityName,recordId,history.getReason());
            history.setDecision("REJ");
            OBDal.getInstance().commitAndClose();
            }
            }else{
                msg.setType("Error");
                msg.setTitle("Error");
                msg.setMessage("You don't have permission to Reject");
            }
        }catch(Exception ex){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(ex.getMessage());
            ex.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }

        bundle.setResult(msg);
    }




    public void updateRecordStatus(String tableEntityName,String recordId,String reason){
        String hql=" UPDATE "+tableEntityName+" set documentStatus='CJ',remarks='"+reason+"' WHERE id=:recordId ";
        Query q=OBDal.getInstance().getSession().createQuery(hql);
        q.setParameter("recordId",recordId);
        q.executeUpdate();

    }

    public String getTableEntityName(String tabId){
        String hql=" SELECT t.name FROM ADTab tab,ADTable t"
                + " WHERE tab.table.id=t.id AND tab.id='"+tabId+"' ";
        return OBDal.getInstance().getSession().createQuery(hql).list().get(0).toString();
    }


    public void updateBestOffUser(String tabId,String recordId,String templateId,User user,RchrApprovalStage stage,long bestoff){
        System.out.println("bestoffuser count..."+bestoff);
        String hql=" UPDATE RCHR_ApprovalHistrory set approvebyuser.id='"+user.getId()+"',decision='REJBY' "
                + " WHERE recordid=:recordId AND tab.id=:tabId AND rchrApprovaltemplate.id=:templateId "
                + "AND rchrApprovalstage.id=:stageId AND approveuser.id!=:userId ";
        Query q=OBDal.getInstance().getSession().createQuery(hql);
        q.setParameter("recordId",recordId);
        q.setParameter("tabId",tabId);
        q.setParameter("templateId",templateId);
        q.setParameter("stageId",stage.getId());
        q.setParameter("userId",user.getId());
        q.executeUpdate();
    }
}
