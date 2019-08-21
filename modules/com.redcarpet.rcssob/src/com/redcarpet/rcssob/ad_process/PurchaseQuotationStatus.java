package com.redcarpet.rcssob.ad_process;

import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;

//import org.openbravo.model.procurement.Requisition;
//import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;

import com.redcarpet.rcssob.data.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import org.apache.log4j.Logger;

//import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class PurchaseQuotationStatus extends DalBaseProcess {
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        OBContext.setAdminMode();
        OBError error = new OBError();
        error.setType("SUCCESS");
        error.setTitle("SUCCESS");
        error.setMessage("Process Completed Successfully");
        String id = (String) bundle.getParams().get("Rcob_Purchasequotation_ID");
        PurchaseQuotation pq = OBDal.getInstance().get(PurchaseQuotation.class, id);
        String status="AP";
        String id2=pq.getRcobPrrequisition().getId();
      //  final OBCriteria<PurchaseQuotation> pqs = OBDal.getInstance().createCriteria(PurchaseQuotation.class);
       // pqs.add(Restrictions.eq(PurchaseQuotation.PROPERTY_ID,id));
      //  for( PurchaseQuotation pq : pqs.list())
       // {
        	pq.setQuotstatus(status);
        	pq.setProcessed(Boolean.TRUE);
        	OBDal.getInstance().save(pq);
     //   }
        
        String qry="update rcob_purchasequotation set quotstatus = 'RJ' where rcob_purchasequotation_id !='" + id + "' AND rcob_prrequisition_id='"+id2+"'" ;
        String qry1="update rcob_prrequisition set flagquot = 'Y' where rcob_prrequisition_id='" + id2 + "'" ;
        Connection con = OBDal.getInstance().getSession().connection(); 
        con.createStatement().executeUpdate(qry);
        con.createStatement().executeUpdate(qry1);
        System.out.println("query is updated...");
        bundle.setResult(error);

    }
    private OBError setErrorMessage(OBError error, String strMsg) {
        error.setTitle("ERROR");
        error.setType("ERROR");
        error.setMessage("Please Enter Required fields " + strMsg);
        return error;
    }
     
 
}
