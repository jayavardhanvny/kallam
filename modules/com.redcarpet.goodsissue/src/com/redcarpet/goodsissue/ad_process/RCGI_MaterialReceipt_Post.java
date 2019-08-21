package com.redcarpet.goodsissue.ad_process;

import com.redcarpet.goodsissue.data.RCGI_MaterialReceipt;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.accounting.coa.ElementValue;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author S.A. Mateen
 */
public class RCGI_MaterialReceipt_Post extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

        OBError error = new OBError();
        error.setTitle("Success");
        error.setType("Success");
        error.setMessage("Process completed successfully");

        String id = bundle.getParams().get("Rcgi_Materialreceipt_ID").toString();
        RCGI_MaterialReceipt gi = OBDal.getInstance().get(RCGI_MaterialReceipt.class, id);

        String groupId1 = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        long seqNo = 10;
        String client = gi.getClient().getId();
        String org = gi.getOrganization().getId();

        RCGISelectAcctNewData[] data1 = RCGISelectAcctNewData.getProductCreditAccountData(new DalConnectionProvider(), client, client, org, id);
        RCGISelectAcctNewData[] data2 = RCGISelectAcctNewData.getProductDebitAccountData(new DalConnectionProvider(), client, client, org, id);
        if (data1.length == 0 || data2.length == 0) {
            gi.setPostx(Boolean.FALSE);
            error.setTitle("Error");
            error.setType("Error");
            error.setMessage("Please check Product Accounts");
            throw new NullPointerException("Please check Product Accounts");
        }

        //credit
        for (RCGISelectAcctNewData t1 : data1) {
            insertProductionIssues2(t1.cAcctschemaId, t1.cElementvalueId, t1.cPeriodId, t1.asTableId, gi.getId(), t1.cCurrencyId,
                    "00", getLineNetAmt(client, t1.mProductId, t1.qty, t1.locatorid, t1.unitprice), seqNo + "",
                    t1.qty, groupId1, t1.value, gi.getDocumentNo() + "#Product Material Receipt", t1.mProductId);
            seqNo = seqNo + 10;
        }

        //debit
        for (RCGISelectAcctNewData t1 : data2) {
            insertProductionIssues2(t1.cAcctschemaId, t1.cElementvalueId, t1.cPeriodId, t1.asTableId, gi.getId(), t1.cCurrencyId,
                    getLineNetAmt(client, t1.mProductId, t1.qty, t1.locatorid, t1.unitprice), "00", seqNo + "",
                    t1.qty, groupId1, t1.value, gi.getDocumentNo() + " #Product Material Receipt", t1.mProductId);
            seqNo = seqNo + 10;
        }

        gi.setPostx(Boolean.TRUE);
        bundle.setResult(error);

    }

    private void insertProductionIssues2(String accountSchemaId,
            String validCombinationId, String periodId, String tableId, String recordId,
            String currId, String debit, String credit, String seqNo, String qty,
            String groupId, String value, String description, String product) throws NumberFormatException {

        System.out.println("inserting posting line " + accountSchemaId);

        AccountingFact acc = OBProvider.getInstance().get(AccountingFact.class);
        acc.setAccountingSchema(OBDal.getInstance().get(AcctSchema.class, accountSchemaId));
        ElementValue eValue = OBDal.getInstance().get(ElementValue.class, validCombinationId);
        acc.setAccount(eValue);
        acc.setTransactionDate(new Date());
        acc.setAccountingDate(new Date());
        Period period = OBDal.getInstance().get(Period.class, periodId);
        acc.setPeriod(period);
        acc.setTable(OBDal.getInstance().get(Table.class, tableId));
        acc.setRecordID(recordId);
        acc.setPostingType("A");
        acc.setCurrency(OBDal.getInstance().get(Currency.class, currId));
        acc.setDebit(new BigDecimal(debit));
        acc.setCredit(new BigDecimal(credit));
        acc.setSequenceNumber(new Long(seqNo));
        acc.setQuantity(new BigDecimal(qty));
        acc.setGroupID(groupId);
        acc.setType("N");
        acc.setValue(value);
        acc.setAccountingEntryDescription(description);
        acc.setProduct(OBDal.getInstance().get(Product.class, product));
        OBDal.getInstance().save(acc);
    }

    private String getLineNetAmt(String strClientId, String mProductId, String strQty, String strLocatorId, String strUnitprice) throws ServletException {

        BigDecimal qty = new BigDecimal(strQty);
        Locator locator = OBDal.getInstance().get(Locator.class, strLocatorId);
        String strWarehouseId = locator.getWarehouse().getId();

        String strCost = RCGIProductCostNewData.getCostingLines(new DalConnectionProvider(), strClientId, mProductId, strWarehouseId);
        boolean isStrCostNull = StringUtils.equalsIgnoreCase(strCost, "");
        if (isStrCostNull) {
            String strPriceListCost = RCGIProductCostNewData.getPriceListCost(new DalConnectionProvider(), mProductId);
            if (StringUtils.equalsIgnoreCase(strPriceListCost, "")) {
                throw new ServletException("No purchase pricelist defined: cost cannnot be calculated");
            } else {
                return new BigDecimal(strPriceListCost).multiply(qty).toString();
            }
        } else {
            return new BigDecimal(strUnitprice).multiply(qty).toString();
        }
    }
}
