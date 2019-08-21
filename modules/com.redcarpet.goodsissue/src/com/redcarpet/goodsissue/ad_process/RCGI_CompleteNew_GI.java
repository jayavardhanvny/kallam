package com.redcarpet.goodsissue.ad_process;

import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue_Line;
import com.redcarpet.goodsissue.data.RCGI_MaterialIssue;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.materialmgmt.cost.CostingAlgorithm;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.core.OBContext;
import org.openbravo.model.materialmgmt.cost.TransactionCost;
import javax.servlet.ServletException;

/**
 *
 * @author Suneetha
 */
public class RCGI_CompleteNew_GI extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rcgi_Goodsissue_ID").toString();
        OBError success = new OBError();
        success.setType("Success");
        success.setTitle("Success");
        success.setMessage("Process completed successfully");
        RCGI_GoodsIssue gi = OBDal.getInstance().get(RCGI_GoodsIssue.class, id);

        Connection conn = OBDal.getInstance().getConnection();
        Statement stmt = conn.createStatement();
        String s2 = "select count(*) as total from rcgi_goodsissue_line where rcgi_goodsissue_id='" + id + "'";
        ResultSet rs = stmt.executeQuery("select count(*) as total from rcgi_goodsissue_line where rcgi_goodsissue_id='" + id + "'");
        while (rs.next()) {
            int r = rs.getInt("total");
            if (r == 0) {
                throw new OBException("This process cannot create without lines");
            }
        }
        
        try {
            OBContext.setAdminMode();
            doIt(gi);
            gi.setProcess(Boolean.TRUE);
            gi.setProcessed(Boolean.TRUE);
            gi.setDocumentStatus("CO");
            

            OBContext.restorePreviousMode();
        } catch (Exception e) {
        	success.setType("Error");
        	success.setTitle("Error");
        	success.setMessage(e.getMessage());
        }

       
        bundle.setResult(success);
    }
  private void doIt(RCGI_GoodsIssue gi) throws ServletException {
        for (RCGI_GoodsIssue_Line line : gi.getRCGIGoodsIssueLineList()) {
            if (line.getStorageBin() == null) {
                throw new OBException("Please check all lines have storage bins");
            }
			 if (line.getOrderedQuantity().doubleValue() <= 0) {
                throw new NullPointerException("Invalid Ordered Quantity ");
            }
            
            String strMProductID=line.getProduct().getId();
            String strLocatorId=line.getStorageBin().getId();
            BigDecimal movementcurrent = new BigDecimal("0");
            try {		
            Connection conn = OBDal.getInstance().getConnection();
            Statement stmt2 = conn.createStatement();
            String sqry2 = "select qtyonhand from M_Product_Stock_V where (m_product_id='" + strMProductID + "') and (M_Locator_ID='" + strLocatorId + "')";
            ResultSet rs2 = stmt2.executeQuery(sqry2);
            
            while (rs2.next()) {
            	movementcurrent = rs2.getBigDecimal("qtyonhand");
                System.out.println("movementcurrent is" + movementcurrent);

        }
        } catch (Exception e) {
            e.printStackTrace();
        }
 
            BigDecimal orderedqty = line.getOrderedQuantity();
            BigDecimal newmvntqty = movementcurrent.subtract(orderedqty);

            int res = orderedqty.compareTo(movementcurrent);
            if (res == 1) {
                throw new OBException("Order Quantity Exceeds Stock Quantity");

            }
            MaterialTransaction trx = OBProvider.getInstance().get(MaterialTransaction.class);
            trx.setProduct(line.getProduct());
            trx.setUOM(line.getProduct().getUOM());
            trx.setOrganization(line.getOrganization());
            trx.setStorageBin(line.getStorageBin());
            BigDecimal orderqty = line.getOrderedQuantity();
            BigDecimal negorderqty = orderqty.negate();
            trx.setMovementQuantity(negorderqty);
            trx.setMovementDate(line.getGoodsIssue().getMovementDate());
            trx.setTransactionProcessDate(new Date());
            trx.setMovementType("P-");
            trx.setCostCalculated(Boolean.TRUE);
            trx.setCostingStatus("CC");
            trx.setCurrency(trx.getOrganization().getCurrency());
            trx.setCostingAlgorithm(OBDal.getInstance().get(CostingAlgorithm.class, "B069080A0AE149A79CF1FA0E24F16AB6"));
            trx.setGoodsIssue(line.getGoodsIssue());
            trx.setGoodsIssueLine(line);
            trx.setTransactionCost(line.getLineNetAmount());
            OBDal.getInstance().save(trx);
            insertTransactionCostLine(trx);
            line.setProcessed(Boolean.TRUE);
        }
        gi.setProcess(Boolean.TRUE);
        gi.setProcessed(Boolean.TRUE);
        gi.setDocumentStatus("CO");
  }
  
      

    private void insertTransactionCostLine(MaterialTransaction trx) {
        OBContext.setAdminMode();
        TransactionCost trxCost = OBProvider.getInstance().get(TransactionCost.class);
        trxCost.setInventoryTransaction(trx);
        trxCost.setCost(trx.getTransactionCost());
        trxCost.setCostDate(new Date());
        trxCost.setCurrency(trx.getCurrency());
        OBDal.getInstance().save(trxCost);
        //OBDal.getInstance().commitAndClose();
        OBContext.restorePreviousMode();
    }
}