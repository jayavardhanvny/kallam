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
public class UnitWiseStorageBinSelection extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strOrgId = info.getStringParameter("inpadOrgId", null);
        String strMProductID = info.getStringParameter("inpmProductId", null);
        
        System.out.println("before do it "+strMProductID);
        
        if (strMProductID != null) {
            doIt(strMProductID, strOrgId, info);
        }


    }

    public String getLocatorOfProduct(String strMProductID, String strOrgId) {
        String locatorid = "";
        try {
            Connection conn = OBDal.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sqry = "select m_locator_id from rcob_storagebins  where (m_product_id='" + strMProductID + "') and (AD_Org_ID='" + strOrgId + "')";
            System.out.println("inisde getLocator "+sqry);
            ResultSet rs = stmt.executeQuery(sqry);

            while (rs.next()) {
                locatorid = rs.getString("M_LOCATOR_ID");
            }
            System.out.println("return "+locatorid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return locatorid;
    }

    private void doIt(String strMProductID, String strOrgId, CalloutInfo info) {
        try {
            String strLocatorId = getLocatorOfProduct(strMProductID, strOrgId);
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

                info.addResult("inpmLocatorId", getLocatorOfProduct(strMProductID, strOrgId));
                info.addResult("inpunitprice", ucost);
                info.addResult("inpopenqty", qty);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
