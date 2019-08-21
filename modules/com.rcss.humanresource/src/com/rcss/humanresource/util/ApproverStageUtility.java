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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 *
 * @author SuryaRao
 */
public class ApproverStageUtility {

    private String templateId,stageId;

    public ApproverStageUtility(){

    }


    public Boolean checkTemplate(String templateId){

        if(templateId.equals("")||templateId=="")
            return Boolean.FALSE;
        else return Boolean.TRUE;
    }

    public Boolean processRequest(String tabId,String userId,String orgId,String clientId,String recordId,Date requestDate){
       // OBError msg = new OBError();
        String templateId="",stageId="",status="";
        templateId=getTemplateId(tabId,userId);
        Boolean flag=Boolean.FALSE;
        try{
            if(checkTemplate(templateId))
            {
                for(RchrApprovalTemplateStage stage:getStages(templateId)){
                    for(RchrApprovalStageUser user:stage.getRchrApprovalstage().getRCHRApprovalStageUserList()){
                        if(user.isActive())
                        insertIntoApprovalHistory(tabId,userId,orgId,clientId,recordId,user.getUserContact(),requestDate,templateId,user.getRchrApprovalstage());
                    }
                }
                flag=Boolean.TRUE;
            }else{
                flag=Boolean.FALSE;
            }
            OBDal.getInstance().commitAndClose();
            return flag;
        }catch(Exception ex){
           /* msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(ex.getMessage());*/
            ex.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
            return flag;
        }
    }



    private void insertIntoApprovalHistory(String tabId, String userId, String orgId, String clientId, String recordId, User user, Date requestDate, String templateId, RchrApprovalStage stage){
        RchrApprovalHistrory his= OBProvider.getInstance().get(RchrApprovalHistrory.class);
        his.setOrganization(OBDal.getInstance().get(Organization.class,orgId));
        his.setClient(OBDal.getInstance().get(Client.class,clientId));
        his.setRecordid(recordId);
        his.setTab(OBDal.getInstance().get(Tab.class,tabId));
        his.setUserContact(OBDal.getInstance().get(User.class,userId));
        his.setApproveuser(user);
        his.setRequestdate(requestDate);
        his.setDecision("PE");
        his.setRchrApprovalstage(stage);
        his.setUpdatecount((long)0);
        his.setRchrApprovaltemplate(OBDal.getInstance().get(RchrApprovalTemplate.class,templateId));
        OBDal.getInstance().save(his);
        OBDal.getInstance().flush();
    }

    private String getTemplateId(String tabId,String userId) {
        String hql = " SELECT template.id FROM RCHR_ApprovalTemplate template,RCHR_ApprovalTemplateUser user,RCHR_ApprovalTemplateDoc doc"
                + " WHERE user.rchrApprovaltemplate.id=template.id"
                + " AND doc.rchrApprovaltemplate.id=template.id"
                + " AND user.userContact.id='" + userId + "' AND doc.tab.id='" + tabId + "' AND user.active=true ";
        List li = OBDal.getInstance().getSession().createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty())
            return "";
        else if (li.get(0) == null || li.get(0).toString() == null)
            return "";
        else
            return li.get(0).toString();
    }

    public List<RchrApprovalTemplateStage> getStages(String templateId){
        String hql=" SELECT stage FROM RCHR_ApprovalTemplateStage stage"
                + " WHERE stage.rchrApprovaltemplate.id='"+templateId+"' AND stage.active=true ORDER BY stage.lineNo ASC ";
        Query q=OBDal.getInstance().getSession().createQuery(hql);
        q.setFirstResult(0);
        q.setMaxResults(1);
        return q.list();
    }

}
