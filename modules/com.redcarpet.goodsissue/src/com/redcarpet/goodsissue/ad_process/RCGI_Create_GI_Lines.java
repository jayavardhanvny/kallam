package com.redcarpet.goodsissue.ad_process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue_Line;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.enterprise.Locator;
/**
 *
 * @author S.A. Mateen 
 */
public class RCGI_Create_GI_Lines extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rcgi_Goodsissue_ID").toString();
        OBError success = new OBError();
        success.setType("Success");
        success.setTitle("Success");
        success.setMessage("Process completed successfully");
        RCGI_GoodsIssue gi = OBDal.getInstance().get(RCGI_GoodsIssue.class, id);
        Requisition req = gi.getRequisition();
        long lineNo = 0;
        for(RequisitionLine rLine : req.getProcurementRequisitionLineList()){
        	String strProductId=rLine.getProduct().getId();
        	
        	if (rLine.getStorageBin() == null) {
                throw new OBException("Please check  your indent doesnt have storage bin");
            }
        	String strLocatorId=rLine.getStorageBin().getId();
        	Locator loc=OBDal.getInstance().get(Locator.class, strLocatorId);
        	String strWarehouseId=loc.getWarehouse().getId();
        	BigDecimal ucost=new BigDecimal("0");
        	try {		
                Connection conn = OBDal.getInstance().getConnection();
                Statement stmt1 = conn.createStatement();
                String sqry1 = "select cost from m_costing where (m_warehouse_id='" + strWarehouseId + "') and (m_product_id='" + strProductId + "') order by created desc limit 1";
                ResultSet rs1 = stmt1.executeQuery(sqry1);
                
                while (rs1.next()) {
                     ucost = rs1.getBigDecimal("cost");
                    System.out.println("ucost is" + ucost);

            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        	BigDecimal orderqty=rLine.getEpcgOpenqty();
        	BigDecimal res=orderqty.multiply(ucost);
        	
        	
            lineNo = lineNo+10;
            RCGI_GoodsIssue_Line line = OBProvider.getInstance().get(RCGI_GoodsIssue_Line.class);
            line.setGoodsIssue(gi);
            line.setOrganization(rLine.getOrganization());
            line.setLineNo(lineNo);
            line.setRequisitionLine(rLine);
            line.setProduct(rLine.getProduct());
            line.setOrderedQuantity(rLine.getEpcgOpenqty());
            line.setOpenQuantity(rLine.getRcobMovementqty());
            line.setProcessing(Boolean.TRUE);
            line.setStorageBin(rLine.getStorageBin());
            line.setUnitprice(ucost);
            line.setLineNetAmount(res);
             if(rLine.getRcprMachine() != null)
            {
            line.setRcprMachine(rLine.getRcprMachine());
            }
            else
            {
            	System.out.println("out of if");
            }
            OBDal.getInstance().save(line);
        }
        gi.setCreateLines(Boolean.TRUE);
        bundle.setResult(success);
    }
}