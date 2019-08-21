package com.redcarpet.production.ad_process;

import com.redcarpet.production.data.RCPR_ProductionRun;
import org.openbravo.model.ad.datamodel.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.coa.AccountingCombination;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.accounting.coa.ElementValue;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author Mateen
 */
public class RCPR_ProductionIssue_Post extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

        String id = bundle.getParams().get("Rcpr_Productionrun_ID").toString();
        RCPR_ProductionRun run = OBDal.getInstance().get(RCPR_ProductionRun.class, id);

        String groupId1 = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        long seqNo = 10;
        String client = run.getClient().getId();
        String org = run.getOrganization().getId();
        String product = run.getProduct().getId();

        RCPRSelectAcctData[] data1 = RCPRSelectAcctData.getRMAssetAccountData1(new DalConnectionProvider(),
                run.getClient().getId(), run.getClient().getId(), run.getOrganization().getId(), run.getProduct().getId(), id);
        RCPRSelectAcctData t1 = data1[0];
        insertProductionIssues2(t1.cAcctschemaId, t1.cElementvalueId, t1.cPeriodId, t1.asTableId, run.getId(), t1.cCurrencyId,
                "00", t1.linenetamt, seqNo + "",
                t1.qty, groupId1, t1.value, run.getDocumentNo() + " #RM Production Issue", product);


        RCPRSelectAcctData[] data2 = RCPRSelectAcctData.getRMProductionTypeData2(new DalConnectionProvider(), client, client, org, product, id);;
        RCPRSelectAcctData t2 = data2[0];
        insertProductionIssues2(t2.cAcctschemaId, t2.cElementvalueId, t2.cPeriodId, t2.asTableId, run.getId(), t2.cCurrencyId,
                t2.linenetamt, "0", (seqNo + 10) + "",
                "0", groupId1, t2.value, run.getDocumentNo() + " #Production Process", product);

        run.setProductionIssuePost(Boolean.TRUE);
        
        OBError error = new OBError();
        error.setType("Success");
        error.setTitle("Success");
        error.setMessage("Process Completed Successfully");
        bundle.setResult(error);
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
}
