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

public class PurchaseRequisitionStatus extends DalBaseProcess {
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        OBContext.setAdminMode();
        OBError error = new OBError();
        error.setType("SUCCESS");
        error.setTitle("SUCCESS");
        error.setMessage("Process Completed Successfully");
        String id = (String) bundle.getParams().get("Rcob_Prrequisition_ID");
        Prrequisition pr = OBDal.getInstance().get(Prrequisition.class, id);
        String status="CO";
     //  String id2=pr.get
       pr.setDocumentStatus(status);
       pr.setComplete(Boolean.TRUE);
        OBDal.getInstance().save(pr);
       final OBCriteria<Prlines> prl = OBDal.getInstance().createCriteria(Prlines.class);
       // prl.add(Restrictions.eq(Prrequisition.PROPERTY_ID,id));
      // prl.add(Restrictions.eq(Prlines.PROPERTY_PRREQUISITION,id));
        for( Prlines pl : prl.list())
        {
        	pl.setComplete(Boolean.TRUE);
        }
      /*  String qry="update bm_purchasequotation set quotstatus = 'RJ' where bm_purchasequotation_id !='" + id + "' AND bm_prrequisition_id='"+id2+"'" ;
        Connection con = OBDal.getInstance().getSession().connection(); 
        con.createStatement().executeUpdate(qry);
        System.out.println("query is updated...");*/
        bundle.setResult(error); 

    }
    private OBError setErrorMessage(OBError error, String strMsg) {
        error.setTitle("ERROR");
        error.setType("ERROR");
        error.setMessage("Please Enter Required fields " + strMsg);
        return error;
    }
     
 
}
