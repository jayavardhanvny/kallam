package com.redcarpet.goodsissue.ad_callouts;


import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.plm.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Warehouse;
import java.math.BigDecimal;
/**
 *
 * @author Suneetha
 */
public class StorageBinSelectStock extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	String strOrgId = info.getStringParameter("inpadOrgId", null);
    	String strMProductID = info.getStringParameter("inpmProductId", null);
    	String strLocatorId=info.getStringParameter("inpmLocatorId", null);
		
    	int qty=0;
        try {		
        Connection conn = OBDal.getInstance().getConnection();
        Statement stmt2 = conn.createStatement();
        String sqry2 = "select qtyonhand from M_Product_Stock_V where (m_product_id='" + strMProductID + "') and (M_Locator_ID='" + strLocatorId + "')";
        ResultSet rs2 = stmt2.executeQuery(sqry2);
        
        while (rs2.next()) {
             qty = rs2.getInt("qtyonhand");
            System.out.println("qty is" + qty);

    }
    } catch (Exception e) {
        e.printStackTrace();
    }
      
       
        info.addResult("inpemRcobMovementqty", qty);
        
        
        
    }
    
   
  
}
