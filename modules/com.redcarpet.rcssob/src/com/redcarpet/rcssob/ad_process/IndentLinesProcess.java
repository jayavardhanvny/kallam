package com.redcarpet.rcssob.ad_process;

import org.openbravo.dal.core.OBContext;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import java.util.List;
import java.sql.Connection;
import org.openbravo.dal.service.OBDal;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.service.db.DalConnectionProvider;
import com.redcarpet.rcssob.data.*;


import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;

public class IndentLinesProcess extends DalBaseProcess {


    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        
         OBContext.setAdminMode();
        OBError error = new OBError();
        error.setType("SUCCESS");
        error.setTitle("SUCCESS");
        error.setMessage("Process Completed Successfully");
        String id = (String) bundle.getParams().get("Rcob_Prrequisition_ID");
        System.out.println(id + " is id ");
        Prrequisition pq = OBDal.getInstance().get(Prrequisition.class, id);
      //  String id1 = pq.getRequisition().getId();
        String id1 = pq.getRequisition().getId();
        System.out.println(id1+ ".... id1 is");
        
        Connection con = OBDal.getInstance().getSession().connection();
  	   String qry = "delete from rcob_prlines where rcob_prrequisition_id='"+id+"'";
  	   con.createStatement().execute(qry);
        Requisition req = OBDal.getInstance().get(Requisition.class, id1);
             
            List<RequisitionLine> lines = req.getProcurementRequisitionLineList();
            for (RequisitionLine rl : lines) {
                Prlines pql = OBProvider.getInstance().get(Prlines.class);
              
               // pql.setPurchasequotation(pq);
               // pql.setPrrequisition(pq);
                pql.setRcobPrrequisition(pq);
                pql.setOrganization(rl.getOrganization());
                pql.setLineNo(rl.getLineNo());
                pql.setProduct(rl.getProduct());
                pql.setQuantity(rl.getQuantity());
                pql.setUOM(rl.getUOM());
                pql.setDescription(rl.getDescription());
                pql.setNeedByDate(rl.getNeedByDate());
                pql.setPlannedDate(rl.getNeedByDate());
              //  pql.setOrederdate(rl.getNeedByDate());
              //  pql.setScheduleddeliverydate(rl.getNeedByDate());
              //  pql.setRequisition(rl.getPrrequisition().getRequisition());
                OBDal.getInstance().save(pql);
                OBDal.getInstance().flush();
                
                bundle.setResult(error);
            }
       
        /* select   seq  from  ADSequence as seq  where seq.name in ('DocumentNo_RCP_Purchasequotation') and seq.client in ('F85C7D36CE4D466194CD9140EA79BF66') and seq.organization in ('49479AA145784571B3D9AA9785AEB0F1')*/
        }
       
    }