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

import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;

import com.redcarpet.rcssob.data.*;

public class PurchaseQuotationLinesProcess extends DalBaseProcess {


    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        
         OBContext.setAdminMode();
        OBError error = new OBError();
        error.setType("SUCCESS");
        error.setTitle("SUCCESS");
        error.setMessage("Process Completed Successfully");
        String id = (String) bundle.getParams().get("C_Order_ID");
        System.out.println(id + " is id ");
      // PurchaseQuotation pq = OBDal.getInstance().get(PurchaseQuotation.class, id);
        Order or=OBDal.getInstance().get(Order.class, id);
       // String id1 = pq.getPrrequisition().getId();
        String id1=or.getRcobPurchasequotation().getId();
        System.out.println(id1+ ".... id1 is");
        
      /*  Connection con = OBDal.getInstance().getSession().connection();
  	   String qry = "delete from c_orderline where c_order_id='"+id+"'";
  	   con.createStatement().execute(qry); */
       // Prrequisition req = OBDal.getInstance().get(Prrequisition.class, id1);
        PurchaseQuotation prq=OBDal.getInstance().get(PurchaseQuotation.class, id1);
        String id2=prq.getRcobPrrequisition().getId();
        System.out.println(id2+ ".... id2 is");
        Prrequisition prr=OBDal.getInstance().get(Prrequisition.class, id2);
             
           // List<Prlines> lines = req.getBMPrlinesList();
            List<PurchaseQuotationLines> pqline = prq.getRCOBPurchasequotationlinesList();
            for (PurchaseQuotationLines pqls : pqline) {
               // PurchaseQuotationLines pql = OBProvider.getInstance().get(PurchaseQuotationLines.class);
            	OrderLine ol= OBProvider.getInstance().get(OrderLine.class);
              /*  pql.setPurchasequotation(pq);
                pql.setOrganization(rl.getOrganization());
                pql.setLineNo(rl.getLineNo());
                pql.setProduct(rl.getProduct());
                pql.setQuantity(rl.getQuantity());
                pql.setUOM(rl.getUOM());
                pql.setOrederdate(rl.getNeedByDate());
                pql.setScheduleddeliverydate(rl.getNeedByDate());
                pql.setRequisition(rl.getPrrequisition().getRequisition());*/
            	ol.setOrganization(pqls.getOrganization());
            	ol.setSalesOrder(or);
            	ol.setLineNo(pqls.getLineNo());
            	ol.setProduct(pqls.getProduct());
            	ol.setWarehouse(prq.getWarehouse());
            	ol.setUOM(pqls.getUOM());
            	ol.setOrderedQuantity(pqls.getQuantity());
            	ol.setCurrency(prr.getCurrency());            	
            	ol.setUnitPrice(pqls.getNetUnitPrice());
            	ol.setLineNetAmount(pqls.getLineNetAmount());
               // ol.setGrossListPrice(pqls.getNetUnitPrice());
                ol.setListPrice(pqls.getNetUnitPrice());
            	
            	//ol.setGrossUnitPrice(pqls.getNetUnitPrice());
            	ol.setTax(pqls.getTax());
            	ol.setOrderDate(pqls.getOrederdate());
                ol.setScheduledDeliveryDate(pqls.getScheduleddeliverydate()); 
                OBDal.getInstance().save(ol);
                OBDal.getInstance().flush();
                
                bundle.setResult(error);
            }
            String qry="update c_order set em_rcob_flag = 'Y' where c_order_id='" + id + "'" ;
            Connection con = OBDal.getInstance().getSession().connection(); 
            con.createStatement().executeUpdate(qry);
            System.out.println("query is updated...");
        /* select   seq  from  ADSequence as seq  where seq.name in ('DocumentNo_RCP_Purchasequotation') and seq.client in ('F85C7D36CE4D466194CD9140EA79BF66') and seq.organization in ('49479AA145784571B3D9AA9785AEB0F1')*/
        }
       
    }