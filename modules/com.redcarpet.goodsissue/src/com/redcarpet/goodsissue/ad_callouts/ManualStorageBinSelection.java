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
public class ManualStorageBinSelection extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strOrgId = info.getStringParameter("inpadOrgId", null);
        String strMProductID = info.getStringParameter("inpmProductId", null);
        String strLocatorId = info.getStringParameter("inpmLocatorId", null);
        
        System.out.println("before do it "+strMProductID);
        
        if (strMProductID != null) {
            doIt(strMProductID, strLocatorId, strOrgId, info);
        }


    }

   

    private void doIt(String strMProductID, String strLocatorId, String strOrgId, CalloutInfo info) {
        try {
            if (strLocatorId != null) {
                Locator locator = OBDal.getInstance().get(Locator.class, strLocatorId);
                String strWarehouseId = locator.getWarehouse().getId();
                BigDecimal ucost = new BigDecimal("0");
                Connection conn = OBDal.getInstance().getConnection();
                Statement stmt1 = conn.createStatement();
                String sqry1 = "select cost from m_costing where (m_warehouse_id='" + strWarehouseId + "') and (m_product_id='" + strMProductID + "') order by created desc limit 1";
                ResultSet rs1 = stmt1.executeQuery(sqry1);
                while (rs1.next()) {
                    ucost = rs1.getBigDecimal("cost");
                    System.out.println("ucost is" + ucost);

                }

                int qty = 0;

                Statement stmt2 = conn.createStatement();
                String sqry2 = "select qtyonhand from M_Product_Stock_V where (m_product_id='" + strMProductID + "') and (M_Locator_ID='" + strLocatorId + "')";
                ResultSet rs2 = stmt2.executeQuery(sqry2);

                while (rs2.next()) {
                    qty = rs2.getInt("qtyonhand");
                    System.out.println("qty is" + qty);

                }

                info.addResult("inpunitprice", ucost);
                info.addResult("inpopenqty", qty);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
