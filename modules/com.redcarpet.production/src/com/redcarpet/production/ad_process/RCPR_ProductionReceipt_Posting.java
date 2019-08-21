/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.production.ad_process;

import com.redcarpet.production.data.RCPR_ProductionRun;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.accounting.coa.ElementValue;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author mateen
 */
public class RCPR_ProductionReceipt_Posting extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rcpr_Productionrun_ID").toString();
        RCPR_ProductionRun run = OBDal.getInstance().get(RCPR_ProductionRun.class, id);

        String groupId2 = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        long seqNo = 10;
        String client = run.getClient().getId();
        String org = run.getOrganization().getId();
        String product = run.getProduct().getId();
        OBError error = new OBError();

        try {

            RCPRSelectAcctData[] data3 = RCPRSelectAcctData.getFGProductAcctData3(new DalConnectionProvider(), client, client, org, id);
            for (int i = 0; i < data3.length; i++) {
                RCPRSelectAcctData t3 = data3[i];
                insertProductionIssues2(t3.cAcctschemaId, t3.cElementvalueId, t3.cPeriodId, t3.asTableId, run.getId(), t3.cCurrencyId,
                        t3.linenetamt, "00", (seqNo + 20) + "",
                        t3.qty, groupId2, t3.value, run.getDocumentNo() + " #FG from Production");
            }

            RCPRSelectAcctData[] data4 = RCPRSelectAcctData.getExpenseTypeAcctData4(new DalConnectionProvider(), client, client, org, client, org, id);
            for (int i = 0; i < data4.length; i++) {
                RCPRSelectAcctData t4 = data4[i];
                insertProductionIssues2(t4.cAcctschemaId, t4.cElementvalueId, t4.cPeriodId, t4.asTableId, run.getId(), t4.cCurrencyId,
                        "00", t4.linenetamt, (seqNo + 30) + "",
                        "00", groupId2, t4.value, run.getDocumentNo() + " #Expenses Production");
            }

            RCPRSelectAcctData[] data5 = RCPRSelectAcctData.getWIPWarehouseAcct5(new DalConnectionProvider(), client, client, org, id);;
            RCPRSelectAcctData t5 = data5[0];
            insertProductionIssues2(t5.cAcctschemaId, t5.cElementvalueId, t5.cPeriodId, t5.asTableId, run.getId(),
                    t5.cCurrencyId, "0", t5.linenetamt, (seqNo + 40) + "", "0", groupId2, t5.value, run.getDocumentNo() + " #Production Process");

            run.setProductionReceiptPost(Boolean.TRUE);

            error.setTitle("Success");
            error.setType("Success");
            error.setMessage("Process completed successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            error.setTitle("Error");
            error.setType("Error");
            error.setMessage(ex.getMessage());
        }
        bundle.setResult(error);
    }

    private void insertProductionIssues2(String accountSchemaId,
            String validCombinationId, String periodId, String tableId, String recordId,
            String currId, String debit, String credit, String seqNo, String qty,
            String groupId, String value, String description) throws NumberFormatException {
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
        acc.setRecordID2(recordId);
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
        OBDal.getInstance().save(acc);
    }
}