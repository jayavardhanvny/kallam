package com.redcarpet.rcssob.ad_callouts;


import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.plm.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Suneetha
 */
public class RequisitionBinQtySelection extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	String strOrgId = info.getStringParameter("inpadOrgId", null);
    	String strMProductID = info.getStringParameter("inpmProductId", null);
    	
        
        Product product = OBDal.getInstance().get(Product.class, strMProductID);
        
        String strLocatorId=getLocatorOfProduct(strMProductID,strOrgId);
		String strUomId=product.getUOM().getId();
        System.out.println("UOM of Product Is:" + strUomId);
		
        		int qty=0;
        try {		
        Connection conn = OBDal.getInstance().getConnection();
        Statement stmt1 = conn.createStatement();
        String sqry1 = "select qtyonhand from M_Product_Stock_V where (m_product_id='" + strMProductID + "') and (M_Locator_ID='" + strLocatorId + "')";
        ResultSet rs1 = stmt1.executeQuery(sqry1);
        
        while (rs1.next()) {
             qty = rs1.getInt("qtyonhand");
            System.out.println("qty is" + qty);

    }
    } catch (Exception e) {
        e.printStackTrace();
    }
	    info.addResult("inpcUomId", strUomId);
        info.addResult("inpemRcobMovementqty", qty);
        info.addResult("inpmLocatorId", getLocatorOfProduct(strMProductID,strOrgId));
        
    }
    
    protected String getLocatorOfProduct(String strMProductID,String strOrgId) {
  	  String locatorid="";
  	    try {

  	    Connection conn = OBDal.getInstance().getConnection();
          Statement stmt = conn.createStatement();
          String sqry = "select m_locator_id from rcob_storagebins  where (m_product_id='" + strMProductID + "') and (AD_Org_ID='" + strOrgId + "')";
          ResultSet rs = stmt.executeQuery(sqry);
         
          while (rs.next()) {
                  locatorid = rs.getString("M_LOCATOR_ID");
          }
  	    } catch (Exception e) {
              e.printStackTrace();
          }

  	    return locatorid;
  	  }

  
}
