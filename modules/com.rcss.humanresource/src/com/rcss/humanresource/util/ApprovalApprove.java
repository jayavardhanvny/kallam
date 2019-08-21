package com.rcss.humanresource.util;

import com.rcss.humanresource.data.RchrApprovalHistrory;
import com.rcss.humanresource.data.RchrApprovalStage;
import com.rcss.humanresource.data.RchrApprovalTemplateStage;
import com.rcss.humanresource.data.RchrApprovalTemplate;
import com.rcss.humanresource.data.RchrApprovalStageUser;
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
/**
 *
 * @author SuryaRao
 */
public class ApprovalApprove extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle)  {
        final String strApproveId = (String)bundle.getParams().get("Rchr_Approvalhistrory_ID");
        RchrApprovalHistrory history = OBDal.getInstance().get(RchrApprovalHistrory.class, strApproveId);

        String tabId= history.getTab().getId();
        String userId=bundle.getContext().getUser();
        User u=OBDal.getInstance().get(User.class,userId);
        String recordId=history.getRecordid();
        String orgId=history.getOrganization().getId();
        String clientId=history.getClient().getId();
        OBError msg = new OBError();
        String templateId=history.getRchrApprovaltemplate().getId();
        String templateStageId="",entityName="";
        entityName=getTableEntityName(tabId);
        try{

            msg.setType("Success");
            msg.setTitle("Done");
            msg.setMessage("Leave Approve has been completed");
            templateStageId=getNextTemplateStage(templateId,history.getStagecount()+1);
            RchrApprovalStage historyStage=history.getRchrApprovalstage();

            if(templateStageId.equals("")||templateStageId==""){
                System.out.println("last stage");
                int stageSize=OBDal.getInstance().get(RchrApprovalTemplate.class,templateId).getRCHRApprovalTemplateStageList().size();
                RchrApprovalTemplateStage templateStage=OBDal.getInstance().get(RchrApprovalTemplate.class,templateId).getRCHRApprovalTemplateStageList().get(stageSize-1);
                if(history.getApproveuser().equals(u) || checkSuperUser(templateId,userId) ){
                    if(templateStage.getQuery()==null||executeQuery(templateStage,recordId)){
                        if(history.getUpdatecount()+1==historyStage.getBestoff()){
                            updateRecordStatus(entityName,recordId);
                            updateBestOffUser(tabId,recordId,templateId,u,historyStage,history.getUpdatecount()+1);
                            history.setApprovebyuser(u);
                            history.setDecision("APR");
                        }else{
                            history.setUpdatecount(history.getUpdatecount()+1);
                            history.setApprovebyuser(u);
                            history.setDecision("APR");
                            history.setBestoff(Boolean.TRUE);
                            updateBestOffCount(tabId,recordId,templateId,u,historyStage,history.getUpdatecount());
                        }

                    }  else{
                        msg.setType("Error");
                        msg.setTitle("Error");
                        msg.setMessage("You failed to satisfy stage condition");
                    }
                }
                else{
                    msg.setType("Error");
                    msg.setTitle("Error");
                    msg.setMessage("You don't have permission to approve");
                }
            }
            else
            {
                System.out.println("next stage");
                if(history.getApproveuser().equals(u) || checkSuperUser(templateId,userId)){
                    RchrApprovalTemplateStage templateStage=OBDal.getInstance().get(RchrApprovalTemplateStage.class,getTemplateStageId(history.getRchrApprovalstage().getId()));
                    if(templateStage.getQuery()==null||!executeQuery(templateStage,recordId)){
                                System.out.println("under query");
                        if(historyStage.getBestoff()==1){
                            updateBestOffUser(tabId,recordId,templateId,u,historyStage,history.getUpdatecount()+1);
                            RchrApprovalStage stage=OBDal.getInstance().get(RchrApprovalTemplateStage.class,templateStageId).getRchrApprovalstage();
                            System.out.println("stage user list..."+stage.getRCHRApprovalStageUserList().size());
                            for(RchrApprovalStageUser user:stage.getRCHRApprovalStageUserList()){
                                System.out.println("stage user list");
                                if(user.isActive()){
                                    System.out.println("under insert");
                                    insertIntoApprovalHistory(tabId,history.getUserContact().getId(),orgId,clientId,recordId,user.getUserContact(),history.getRequestdate(),templateId,history.getId(),user.getRchrApprovalstage(),u,history.getStagecount()+1);
                                }
                            }
                            history.setApprovebyuser(u);
                            history.setDecision("APR");
                        }
                        else{
                            if(history.getUpdatecount()+1==historyStage.getBestoff()){
                                updateBestOffUser(tabId,recordId,templateId,u,historyStage,history.getUpdatecount()+1);
                                history.setApprovebyuser(u);
                                history.setDecision("APR");
                                RchrApprovalStage stage=OBDal.getInstance().get(RchrApprovalTemplateStage.class,templateStageId).getRchrApprovalstage();
                                System.out.println("stage user list..."+stage.getRCHRApprovalStageUserList().size());
                                for(RchrApprovalStageUser user:stage.getRCHRApprovalStageUserList()){
                                    System.out.println("stage user list");
                                    if(user.isActive()){
                                        System.out.println("under insert");
                                        insertIntoApprovalHistory(tabId,history.getUserContact().getId(),orgId,clientId,recordId,user.getUserContact(),history.getRequestdate(),templateId,history.getId(),user.getRchrApprovalstage(),u,history.getStagecount()+1);
                                    }
                                }


                        }else{
                                history.setUpdatecount(history.getUpdatecount()+1);
                                history.setApprovebyuser(u);
                                history.setDecision("APR");
                                history.setBestoff(Boolean.TRUE);
                                updateBestOffCount(tabId,recordId,templateId,u,historyStage,history.getUpdatecount());
                        }
                    }
                    }else{
                            updateRecordStatus(entityName,recordId);
                            history.setApprovebyuser(u);
                            history.setDecision("APR");
                            updateBestOffUser(tabId,recordId,templateId,u,historyStage,history.getUpdatecount());

                    }

                }
                else{
                    msg.setType("Error");
                    msg.setTitle("Error");
                    msg.setMessage("You don't have permission to approve");
                }

            }
            OBDal.getInstance().commitAndClose();
        }catch(Exception ex){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage(ex.getMessage());
            ex.printStackTrace();
            OBDal.getInstance().rollbackAndClose();
        }

        bundle.setResult(msg);
    }


    public void updateBestOffCount(String tabId,String recordId,String templateId,User user,RchrApprovalStage stage,long bestoff){
        System.out.println("bestoff count..."+bestoff);
        String hql=" UPDATE RCHR_ApprovalHistrory set updatecount='"+bestoff+"' "
                + " WHERE recordid=:recordId AND tab.id=:tabId AND rchrApprovaltemplate.id=:templateId "
                + " AND rchrApprovalstage.id=:stageId AND approveuser.id!=:userId AND updatecount<'"+bestoff+"' ";
        Query q=OBDal.getInstance().getSession().createQuery(hql);
        q.setParameter("recordId",recordId);
        q.setParameter("tabId",tabId);
        q.setParameter("templateId",templateId);
        q.setParameter("stageId",stage.getId());
        q.setParameter("userId",user.getId());
        q.executeUpdate();
    }

    public void updateBestOffUser(String tabId,String recordId,String templateId,User user,RchrApprovalStage stage,long bestoff){
        System.out.println("bestoffuser count..."+bestoff);
        String hql=" UPDATE RCHR_ApprovalHistrory set approvebyuser.id='"+user.getId()+"',decision='APRBY' "
                + " WHERE recordid=:recordId AND tab.id=:tabId AND rchrApprovaltemplate.id=:templateId "
                + "AND rchrApprovalstage.id=:stageId AND approveuser.id!=:userId AND updatecount<'"+bestoff+"' AND bestoff=false ";
        Query q=OBDal.getInstance().getSession().createQuery(hql);
        q.setParameter("recordId",recordId);
        q.setParameter("tabId",tabId);
        q.setParameter("templateId",templateId);
        q.setParameter("stageId",stage.getId());
        q.setParameter("userId",user.getId());
        q.executeUpdate();
    }

    public String getNextTemplateStage(String templateId,long sequenceNo){
        System.out.println("sequence no..."+sequenceNo);
        String hql=" SELECT stage.id FROM RCHR_ApprovalTemplateStage stage"
                + " WHERE stage.rchrApprovaltemplate.id='"+templateId+"' AND stage.active=true ORDER BY lineNo ASC";
        Query q=OBDal.getInstance().getSession().createQuery(hql);
        q.setFirstResult((int)sequenceNo);
        q.setMaxResults(1);
        List li=q.list();
    //    System.out.println("next stage.."+li.get(0));
        if (li.size() <= 0 || li.isEmpty())
            return "";
        else if (li.get(0) == null || li.get(0).toString() == null)
            return "";
        else
            return li.get(0).toString();

    }

    public long getStageActiveUserCount(String stageId){
        String hql="SELECT COUNT(user.id) FROM RCHR_ApprovalStageUser user "
                + " WHERE user.rchrApprovalstage.id='"+stageId+"' AND user.active=true ";
        Query q=OBDal.getInstance().getSession().createQuery(hql);
        List li=q.list();
        if (li.size() <= 0 || li.isEmpty())
            return 0;
        else if (li.get(0) == null || li.get(0).toString() == null)
            return 0;
        else
            return (long)li.get(0);
    }

    public List<RchrApprovalHistrory> checkIsBestOff(String tabId,String recordId,String templateId){
        String hql=" SELECT his FROM RCHR_ApprovalHistrory his "
                + " WHERE his.recordid='"+recordId+"' AND his.tab.id='"+tabId+"' AND his.rchrApprovaltemplate.id='"+templateId+"' ";
        return OBDal.getInstance().getSession().createQuery(hql).list();

    }

    public void updateLastApproveHistory(RchrApprovalHistrory history,User u){
        RchrApprovalHistrory his=OBDal.getInstance().get(RchrApprovalHistrory.class,history.getId());
        System.out.println("inside last update..."+u.getUsername());
        his.setLastapproved(history.getLastapproved().concat(u.getUsername().concat(",")));
        his.setApproveuser(null);
        his.setDecision("APR");
        his.setUpdatecount(his.getUpdatecount()+1);
    }



    public Boolean executeQuery(RchrApprovalTemplateStage templateStage,String recordId){
        String hqlQuery=templateStage.getQuery();
        String hql=hqlQuery;
        System.out.println("hql query cnt"+hql);
        Query q=OBDal.getInstance().getSession().createQuery(hql);
        q.setParameter(0,recordId);
        System.out.println("query cnt"+q.list().size());
        if(q.list().size()>0)
        return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

    public String getTemplateStageId(String stageId){
        String hql=" SELECT tstage.id FROM RCHR_ApprovalTemplateStage tstage WHERE tstage.rchrApprovalstage.id='"+stageId+"' ";
        return OBDal.getInstance().getSession().createQuery(hql).list().get(0).toString();
    }

   /* public void updateApproveHistory(RchrApprovalHistrory history,User user,String userId,RchrApprovalStage stage){
        RchrApprovalHistrory his=OBDal.getInstance().get(RchrApprovalHistrory.class,history.getId());
        //history.getLastapproved()==null?
        his.setLastapproved(history.getLastapproved().concat(OBDal.getInstance().get(User.class,userId).getUsername().concat(",")));
        his.setApproveuser(user);
        his.setDecision("PE");
        his.setRchrApprovalstage(stage);
        his.setUpdatecount(his.getUpdatecount()+1);
    }*/

    public void updateRecordStatus(String tableEntityName,String recordId){
        String hql=" UPDATE "+tableEntityName+" set documentStatus='AP' WHERE id=:recordId ";
        Query q=OBDal.getInstance().getSession().createQuery(hql);
        q.setParameter("recordId",recordId);
        q.executeUpdate();

    }

    private void insertIntoApprovalHistory(String tabId, String userId, String orgId, String clientId, String recordId,User user,Date requestDate,String templateId,String historyId,RchrApprovalStage stage,User cuurentUser,long stageCount){
        RchrApprovalHistrory hi=OBDal.getInstance().get(RchrApprovalHistrory.class,historyId);
        RchrApprovalHistrory his= OBProvider.getInstance().get(RchrApprovalHistrory.class);
        his.setOrganization(OBDal.getInstance().get(Organization.class,orgId));
        his.setClient(OBDal.getInstance().get(Client.class,clientId));
        his.setRecordid(recordId);
        his.setTab(OBDal.getInstance().get(Tab.class,tabId));
        his.setUserContact(OBDal.getInstance().get(User.class,userId));
        his.setApproveuser(user);       
        his.setLastapproved(hi.getLastapproved().concat(cuurentUser.getName()).concat("-->").toString());
        his.setRequestdate(requestDate);
        his.setUpdatecount((long)0);
        his.setStagecount(stageCount);
        his.setDecision("PE");   
        his.setRchrApprovalstage(stage);
        his.setRchrApprovaltemplate(OBDal.getInstance().get(RchrApprovalTemplate.class,templateId));
        OBDal.getInstance().save(his);
        OBDal.getInstance().flush();
    }

    public String getTableEntityName(String tabId){
        String hql=" SELECT t.name FROM ADTab tab,ADTable t"
                + " WHERE tab.table.id=t.id AND tab.id='"+tabId+"' ";
        return OBDal.getInstance().getSession().createQuery(hql).list().get(0).toString();
    }
    public boolean checkSuperUser(String templateId,String userId){
        String hql=" SELECT spuser.user.id FROM RCHR_ApprovalTemplateSpUser spuser where spuser.rchrApprovaltemplate.id=:templateId AND spuser.user.id=:userId AND active=true ";
        Query query=OBDal.getInstance().getSession().createQuery(hql);
        query.setParameter("templateId",templateId);
        query.setParameter("userId",userId);
        if(query.list().size()>0)return Boolean.TRUE;
        else return Boolean.FALSE;
    }
}

