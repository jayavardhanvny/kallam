package com.redcarpet.goodsissue.ad_process;

import com.redcarpet.goodsissue.data.*;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.ProcessStatus;
import com.redcarpet.goodsissue.util.RCGI_CostingPojo;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.materialmgmt.cost.Costing;
import org.openbravo.model.materialmgmt.cost.CostingAlgorithm;
import org.openbravo.model.materialmgmt.cost.TransactionCost;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

import javax.servlet.ServletException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
public class DepartmentalStoreReceipt extends DalBaseProcess implements BundleProcess {
  public static final Logger logger = Logger.getLogger(DepartmentalStoreReceipt.class);
    private List<RCGI_CostingPojo> list = Collections.EMPTY_LIST;

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
      OBError err = new OBError();
        ProcessStatus processStatus = new ProcessStatus(bundle, DalBaseProcess.PROCESSING);
        if (processStatus.isProcessing()){
            logger.info("In Second Condtion Already this process is in Running state ");
            //throw new SerialException("Already this Process is Running...!");
            err.setMessage(ProcessStatus.PREVIOUS_PROCESS_RUNNING);
            err.setTitle("Error");
            err.setType("Error");
            //bundle.setResult(err);
            bundle.setResult(err);
        } else {
            doIt(bundle);
        }

    }
    @Override
       public void doIt(ProcessBundle bundle) throws Exception {

                 String id = bundle.getParams().get("Rcgi_Departmentreceipt_ID").toString();
                 OBError msg = new OBError();
                 msg.setType("Success");
                 msg.setTitle("Success");
                 msg.setMessage("Process completed successfully");
                 RCGIDepartmentReceipt receipt = OBDal.getInstance().get(RCGIDepartmentReceipt.class, id);
                 if (receipt.getRCGIDrLinesList().size() <= 0) {
                     msg.setType("Error");
                     msg.setTitle("Error");
                     msg.setMessage("Document cannot be booked because it has no lines");
                     OBDal.getInstance().rollbackAndClose();
                     //throw new SerialException("Must Enter Payroll Period.");
                 }
                 try {
                     OBContext.setAdminMode();
                     doIt(receipt);
                     insertIntoFIFOTransactions(receipt);
                     receipt.setProcess(Boolean.TRUE);
                     receipt.setProcessed(Boolean.TRUE);
                     receipt.setDocumentStatus("CO");
         			receipt.setTotalcost(receipt.getTotallineamount().add(receipt.getFreightcost()));
                     OBDal.getInstance().commitAndClose();
                 } catch (Exception e) {
                     msg.setType("Error");
                     msg.setTitle("Error");
                     msg.setMessage(e.getMessage());
                     e.printStackTrace();
                     OBDal.getInstance().rollbackAndClose();
                 }
                 bundle.setResult(msg);
       }

    private void insertIntoFIFOTransactions(RCGIDepartmentReceipt receipt) throws OBException, ServletException, ParseException {
        for(RCGIDrLines line : receipt.getRCGIDrLinesList()){
            if(!line.isFinancialInvoiceLine()){
                RCGITransactions trx= OBProvider.getInstance().get(RCGITransactions.class);
                trx.setClient(line.getClient());
                trx.setOrganization(line.getOrganization());
                trx.setProduct(line.getProduct());
                trx.setCurrency(line.getOrganization().getCurrency());
                trx.setBusinessPartner(line.getRcgiDepartmentreceipt().getBusinessPartner());
                trx.setMovementDate(line.getRcgiDepartmentreceipt().getMovementDate());
                trx.setUOM(line.getProduct().getUOM());
                trx.setMovementType("+P");
                trx.setCostingAlgorithm(OBDal.getInstance().get(CostingAlgorithm.class, "B069080A0AE149A79CF1FA0E24F16AB6"));
                trx.setRcgiDrlines(line);
                trx.setCost(line.getUnitprice());
                trx.setTransactionCost(line.getLineNetAmount());
                trx.setOpenQuantity(line.getOrderedQuantity());
                trx.setOpeninventoruqty(line.getMovementQuantity());
              //  trx.setUnitprice(line.getUnitprice());
                if(line.getStorageBin()!=null)trx.setStorageBin(line.getStorageBin());
                trx.setMovementQuantity(line.getMovementQuantity());
                trx.setOrderQuantity(line.getOrderedQuantity());
                //trx.setInventoryuom(line.getInventoryuom());
                OBDal.getInstance().save(trx);
                OBDal.getInstance().flush();
            }
        }
    }

    private void doIt(RCGIDepartmentReceipt receipt) throws OBException, ServletException, ParseException {
        list = new ArrayList<RCGI_CostingPojo>();
        for (RCGIDrLines line : receipt.getRCGIDrLinesList()) {
            if(!line.isFinancialInvoiceLine()){
                MaterialTransaction trx = OBProvider.getInstance().get(MaterialTransaction.class);
                trx.setProduct(line.getProduct());
                trx.setUOM(line.getProduct().getUOM());
                trx.setOrganization(line.getOrganization());
                trx.setStorageBin(line.getStorageBin());
                trx.setOrderQuantity(line.getOrderedQuantity());
                trx.setMovementQuantity(line.getMovementQuantity());
                if (line.getOrderedQuantity().doubleValue() <= 0) {
                    throw new NullPointerException("Invalid Ordered Quantity ");
                }
                trx.setMovementDate(line.getRcgiDepartmentreceipt().getMovementDate());
                trx.setTransactionProcessDate(new Date());
                trx.setMovementType("P+");
                trx.setCostCalculated(Boolean.TRUE);
                trx.setCostingStatus("CC");
                trx.setCurrency(trx.getOrganization().getCurrency());
                trx.setCostingAlgorithm(OBDal.getInstance().get(CostingAlgorithm.class, "B069080A0AE149A79CF1FA0E24F16AB6"));
                trx.setRCGIDrLines(line);
                trx.setTransactionCost(line.getLineNetAmount());
              //  trx.setInventoryuom(line.getInventoryuom());
                OBDal.getInstance().save(trx);
                insertTransactionCostLine(trx);
                RCGI_CostingPojo pojo = new RCGI_CostingPojo(line.getProduct(), line.getOrganization(), "AVA",
                        line.getUnitprice(), line.getOrderedQuantity(),
                        line.getUnitprice(), line.getOrderedQuantity(),
                        line.getOrganization().getCurrency(), line.getClient().getId(), line.getStorageBin(), trx);
                list.add(pojo);
            }
        }
        insertCosting(list);
    }

    private void insertTransactionCostLine(MaterialTransaction trx) {
        TransactionCost trxCost = OBProvider.getInstance().get(TransactionCost.class);
        trxCost.setInventoryTransaction(trx);
        trxCost.setCost(trx.getTransactionCost());
        trxCost.setCostDate(trx.getMovementDate());
        trxCost.setCurrency(trx.getCurrency());
        OBDal.getInstance().save(trxCost);

    }

    private void insertCosting(List<RCGI_CostingPojo> list) throws ServletException, ParseException {
        for (RCGI_CostingPojo line : list) {
            updateExisting31129999(line);
            Costing costing = OBProvider.getInstance().get(Costing.class);
            costing.setProduct(line.getProduct());
            costing.setOrganization(OBDal.getInstance().get(Organization.class, "256551BD83DF49DB80BCE5691149CA0B"));
            costing.setCostType(line.getCosttype());

            String strWarehouseId = line.getLocator().getWarehouse().getId();
            String strMProductId = line.getProduct().getId();


            BigDecimal movementqtypos = new BigDecimal("0");
            BigDecimal transactioncostpos = new BigDecimal("0");
            BigDecimal movementqtyneg = new BigDecimal("0");
            BigDecimal transactioncostneg = new BigDecimal("0");
            BigDecimal totalmvmtqty = new BigDecimal("0");
            BigDecimal avgcost = new BigDecimal("0");
            BigDecimal totaltranscost = new BigDecimal("0");
            try {
                Connection conn = OBDal.getInstance().getConnection();
                Statement stmt1 = conn.createStatement();
                String sqry1 = "SELECT sum(movementqty) as sumqty1,sum(transactioncost) as sumtcost1 FROM m_transaction where (m_product_id='" + strMProductId + "') and m_transaction.m_locator_id in (select m_locator_id from m_locator where m_locator.m_warehouse_id='" + strWarehouseId + "') and movementqty>0";
                ResultSet rs1 = stmt1.executeQuery(sqry1);

                while (rs1.next()) {
                    movementqtypos = rs1.getBigDecimal("sumqty1");
                    transactioncostpos = rs1.getBigDecimal("sumtcost1");

                }

                Statement stmt2 = conn.createStatement();
                String sqry2 = "SELECT sum(movementqty) as sumqty2,sum(transactioncost) as sumtcost2 FROM m_transaction where (m_product_id='" + strMProductId + "') and m_transaction.m_locator_id in (select m_locator_id from m_locator where m_locator.m_warehouse_id='" + strWarehouseId + "') and movementqty<0";
                ResultSet rs2 = stmt2.executeQuery(sqry2);

                while (rs2.next()) {
                    movementqtyneg = rs2.getBigDecimal("sumqty2");
                    transactioncostneg = rs2.getBigDecimal("sumtcost2");

                }
                if (movementqtyneg != null && transactioncostneg != null) {

                    totalmvmtqty = movementqtypos.add(movementqtyneg);
                    totaltranscost = transactioncostpos.subtract(transactioncostneg);
                } else {
                    totalmvmtqty = movementqtypos;
                    totaltranscost = transactioncostpos;


                }
                avgcost = totaltranscost.divide(totalmvmtqty, 5, BigDecimal.ROUND_HALF_UP);

            } catch (Exception e) {
                e.printStackTrace();
            }

            costing.setCost(avgcost);
            costing.setQuantity(line.getMovementQty());
            costing.setPrice(line.getCost());
            costing.setTotalMovementQuantity(totalmvmtqty);
            costing.setCurrency(line.getCurrency());
            costing.setInventoryTransaction(line.getInventory());
            costing.setStartingDate(line.getInventory().getMovementDate());
            costing.setEndingDate(get31121999());
            costing.setWarehouse(line.getLocator().getWarehouse());
            OBDal.getInstance().save(costing);
        }
    }

    // Recipt + = M_Tra,M_COST ....Query .Prodyuct and Ware house Record,
    // + (CV1,CQ1)
    //- (CV2,CQ2)
    //CV1+(-CV)/CQ1+(-CQ2) = Avg  COsting
    // CQ1+(-CQ2) = total movement COsting
    // rest of the feilds of COsting table will come from M_TRANSACTION
    private Date get31121999() throws ParseException {
        DateFormat frmt = new SimpleDateFormat("dd-MM-yyyy");
        return frmt.parse("31-12-9999");
    }

    private void updateExisting31129999(RCGI_CostingPojo line) throws ServletException {
        String costingId = RCGIProductCostNewOneData.getCostingId(new DalConnectionProvider(), line.getProduct().getId(), line.getLocator().getWarehouse().getId());
        System.out.println(costingId);
        if (costingId != null) {
            OBDal.getInstance().get(Costing.class, costingId).setEndingDate(line.getInventory().getMovementDate());
        }
    }
}
