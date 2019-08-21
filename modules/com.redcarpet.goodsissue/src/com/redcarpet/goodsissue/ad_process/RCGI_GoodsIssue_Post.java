package com.redcarpet.goodsissue.ad_process;

import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
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
public class RCGI_GoodsIssue_Post extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rcgi_Goodsissue_ID").toString();
        RCGI_GoodsIssue gi = OBDal.getInstance().get(RCGI_GoodsIssue.class, id);


        String groupId1 = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        long seqNo = 10;
        String client = gi.getClient().getId();
        String org = gi.getOrganization().getId();

        RCGISelectAcctData[] data1 = RCGISelectAcctData.getProductAssetAccountData(new DalConnectionProvider(), client, client, org, id);

        for (RCGISelectAcctData t1 : data1) {
            insertProductionIssues2(t1.cAcctschemaId, t1.cElementvalueId, t1.cPeriodId, t1.asTableId, gi.getId(), t1.cCurrencyId,
                    "00", getLineNetAmt(client, t1.mProductId, t1.qty, t1.locatorid), seqNo + "",
                    t1.qty, groupId1, t1.value, gi.getName() + gi.getDocumentNo() + " #Product Goods Issue", t1.mProductId);
            seqNo = seqNo + 10;
        }

        RCGISelectAcctData[] data2 = RCGISelectAcctData.getProductExpenseAccountData(new DalConnectionProvider(), client, client, org, id);
        for (RCGISelectAcctData t1 : data2) {
            insertProductionIssues2(t1.cAcctschemaId, t1.cElementvalueId, t1.cPeriodId, t1.asTableId, gi.getId(), t1.cCurrencyId,
                    getLineNetAmt(client, t1.mProductId, t1.qty, t1.locatorid), "00", seqNo + "",
                    t1.qty, groupId1, t1.value, gi.getName() + gi.getDocumentNo() + " #Product Goods Issue", t1.mProductId);
            seqNo = seqNo + 10;
        }

        OBError error = new OBError();
        error.setTitle("Success");
        error.setType("Success");
        error.setMessage("Process completed successfully");
        bundle.setResult(error);
        gi.setPostx(Boolean.TRUE);
    }

    private void insertProductionIssues2(String accountSchemaId,
            String validCombinationId, String periodId, String tableId, String recordId,
            String currId, String debit, String credit, String seqNo, String qty,
            String groupId, String value, String description, String product) throws NumberFormatException {
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

    private String getLineNetAmt(String strClientId, String mProductId, String strQty, String strLocatorId) throws ServletException {

        BigDecimal qty = new BigDecimal(strQty);
        Locator locator = OBDal.getInstance().get(Locator.class, strLocatorId);
        String strWarehouseId=locator.getWarehouse().getId();

        String strCost = RCGIProductCostData.getCostingLines(new DalConnectionProvider(), strClientId, mProductId, strWarehouseId);
        boolean isStrCostNull = StringUtils.equalsIgnoreCase(strCost, "");
        if (isStrCostNull) {
            String strPriceListCost = RCGIProductCostData.getPriceListCost(new DalConnectionProvider(), mProductId);
            if (StringUtils.equalsIgnoreCase(strPriceListCost, "")) {
                throw new ServletException("No purchase pricelist defined: cost cannnot be calculated");
            } else {
                return new BigDecimal(strPriceListCost).multiply(qty).toString();
            }
        } else {
            return new BigDecimal(strCost).multiply(qty).toString();
        }
    }
}