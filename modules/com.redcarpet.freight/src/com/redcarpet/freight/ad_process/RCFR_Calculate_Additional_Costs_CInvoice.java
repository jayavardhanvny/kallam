package com.redcarpet.freight.ad_process;

import com.redcarpet.rcssob.data.RCOB_AgentLine;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.financialmgmt.gl.GLItem;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author Mateen
 */
@SuppressWarnings("unchecked")
public class RCFR_Calculate_Additional_Costs_CInvoice extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

        String strInvoiceId = bundle.getParams().get("C_Invoice_ID").toString();

        OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Success");
        msg.setMessage("Process completed successfully");
        OBContext.setAdminMode();
        try {
            doIt(strInvoiceId);
            OBDal.getInstance().flush();
            deleteNInsertFinancialAcctLines(strInvoiceId);
            insertSubBusinessAgent(strInvoiceId);
            OBDal.getInstance().get(Invoice.class, strInvoiceId).setRcfrCalcdistcosth(Boolean.TRUE);
            bundle.setResult(msg);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setType("ERROR");
            msg.setTitle("ERROR");
            msg.setMessage(e.getMessage());
            OBDal.getInstance().get(Invoice.class, strInvoiceId).setRcfrCalcdistcosth(Boolean.FALSE);
            bundle.setResult(msg);
        }
        OBContext.restorePreviousMode();
    }

    private void doIt(String strInvoiceId) {

        Invoice invoice = OBDal.getInstance().get(Invoice.class, strInvoiceId);
        for (InvoiceLine line : invoice.getInvoiceLineList()) {
            if (StringUtils.equalsIgnoreCase("ExMill Rate", invoice.getRcfrRatetype())) {
                line.setRcfrFreight(calculateFreightCost(line));
                line.setRcfrInsurance(calculateInsuranceCost(line, true));
                line.setLineNetAmount(line.getRcfrFreight().add(line.getRcfrInsurance()).add((line.getUnitPrice().multiply(line.getInvoicedQuantity()))));
                calculateTaxAmount(line, true);
                line.setRcfrNetrate(line.getLineNetAmount().add(line.getTaxAmount()));
                line.setRcfrNetunitrate(line.getRcfrNetrate().divide(line.getInvoicedQuantity(), 3, RoundingMode.HALF_UP));
                line.setLineNetAmount(line.getUnitPrice().multiply(line.getInvoicedQuantity()));
            } else {
                calculateTaxAmount(line, false);
                line.setLineNetAmount((line.getRcfrNetunitrate().multiply(line.getInvoicedQuantity())).subtract(new BigDecimal(line.getTaxAmount().doubleValue())));
                line.setRcfrFreight(calculateFreightCost(line));
                line.setRcfrInsurance(calculateInsuranceCost(line, false));
                line.setUnitPrice((line.getLineNetAmount().subtract(line.getRcfrFreight()).subtract(line.getRcfrInsurance())).divide(line.getInvoicedQuantity(), 3, RoundingMode.HALF_UP));
                line.setRcfrNetrate(line.getRcfrNetunitrate().multiply(line.getInvoicedQuantity()));
                line.setLineNetAmount(line.getUnitPrice().multiply(line.getInvoicedQuantity()));
            }
        }
    }

    private BigDecimal calculateInsuranceCost(InvoiceLine line, boolean isExMillRate) {
        if (isExMillRate) {
            BigDecimal insuranceRate = line.getInvoice().getRcfrInsuranceh().multiply(new BigDecimal(0.01));
            return line.getUnitPrice().multiply(line.getInvoicedQuantity()).multiply(insuranceRate);
        } else {
            BigDecimal temp1 = line.getInvoice().getRcfrInsuranceh().multiply(new BigDecimal(0.01));
            BigDecimal numerator = line.getLineNetAmount().subtract(line.getRcfrFreight());
            BigDecimal denominator = (new BigDecimal(BigInteger.ONE).add(temp1));
            return numerator.divide(denominator, 3, RoundingMode.UP).multiply(temp1);
        }
    }

    private BigDecimal calculateFreightCost(InvoiceLine line) {
        return line.getInvoicedQuantity().divide(getTotalQuantity(line), 3, RoundingMode.HALF_UP).multiply(line.getInvoice().getRcfrFreighth());
    }

    private void calculateTaxAmount(InvoiceLine line, boolean isExmillRare) {
        if (isExmillRare) {
            BigDecimal taxRate = line.getTax().getRate().multiply(new BigDecimal(0.01));
            line.setTaxAmount(line.getLineNetAmount().multiply(taxRate));
        } else {
            BigDecimal numerator = line.getRcfrNetunitrate().multiply(line.getInvoicedQuantity());
            BigDecimal taxRate = line.getTax().getRate().multiply(new BigDecimal(0.01));
            BigDecimal denominator = new BigDecimal(BigInteger.ONE).add(taxRate);
            line.setTaxAmount(numerator.divide(denominator, 4, RoundingMode.UP).multiply(taxRate));
        }
    }

    private void deleteNInsertFinancialAcctLines(String strInvoiceId) throws ServletException {

        RCFRAdditionalCostUtilData.deleteLine(new DalConnectionProvider(), strInvoiceId);

        BigDecimal freightSum = getSumLineFreight(strInvoiceId);
        BigDecimal insuranceSum = getSumLineInsurance(strInvoiceId);
        String strTaxRateId = getLine10TaxRateId(strInvoiceId);
        String strFreightId = RCFRAdditionalCostUtilData.getFreightAccount(new DalConnectionProvider());
        String strInsuranceId = RCFRAdditionalCostUtilData.getInsuranceAccount(new DalConnectionProvider());
        if (freightSum.doubleValue() > 0) {
            insertAccountingLine(strInvoiceId, freightSum, strFreightId, strTaxRateId);
        }
        if (insuranceSum.doubleValue()>0) {
            insertAccountingLine(strInvoiceId, insuranceSum, strInsuranceId, strTaxRateId);
        }
    }

    private BigDecimal getSumLineFreight(String strInvoiceId) {
        return OBDal.getInstance().get(Invoice.class, strInvoiceId).getRcfrFreightpaid();
    }

    private BigDecimal getSumLineInsurance(String strInvoiceId) {
        double retVal = 0;
        for (InvoiceLine line : OBDal.getInstance().get(Invoice.class, strInvoiceId).getInvoiceLineList()) {
            retVal += line.getRcfrInsurance().doubleValue();
        }
        return new BigDecimal(retVal);
    }

    private void insertAccountingLine(String strInvoiceId, BigDecimal unitPrice, String strAccountId, String strTaxRateId) throws ServletException {
        InvoiceLine invoiceLine = OBProvider.getInstance().get(InvoiceLine.class);
        invoiceLine.setInvoice(OBDal.getInstance().get(Invoice.class, strInvoiceId));
        invoiceLine.setLineNo(getLineNo(strInvoiceId));
        invoiceLine.setUnitPrice(unitPrice);
        invoiceLine.setLineNetAmount(unitPrice);
        invoiceLine.setFinancialInvoiceLine(Boolean.TRUE);
        invoiceLine.setAccount(OBDal.getInstance().get(GLItem.class, strAccountId));
        invoiceLine.setTax(OBDal.getInstance().get(TaxRate.class, strTaxRateId));
        OBDal.getInstance().save(invoiceLine);
    }

    private long getLineNo(String strInvoiceId) throws ServletException {
        String strLineNo = RCFRAdditionalCostUtilData.selectLineNo(new DalConnectionProvider(), OBDal.getInstance().get(Invoice.class, strInvoiceId).getDocumentNo());
        return Long.valueOf(strLineNo);
    }

    private String getLine10TaxRateId(String strInvoiceId) {
        return OBDal.getInstance().get(Invoice.class, strInvoiceId).getInvoiceLineList().get(0).getTax().getId();
    }

    private void insertSubBusinessAgent(String strInvoiceId) {
        Invoice invoice = OBDal.getInstance().get(Invoice.class, strInvoiceId);
        RCOB_AgentLine agent = OBProvider.getInstance().get(RCOB_AgentLine.class);
        agent.setBusinessPartner(invoice.getEpcgAgent());
        agent.setInvoice(invoice);
        agent.setCommission(invoice.getRCOBCommission());
        agent.setLineNo(new Long(10));
        OBDal.getInstance().save(agent);
    }
	
	private BigDecimal getTotalQuantity(InvoiceLine line) {
        double retVal = 0;
        List<InvoiceLine> invoiceLineList = line.getInvoice().getInvoiceLineList();
        for (InvoiceLine lineX : invoiceLineList) {
            retVal += lineX.getInvoicedQuantity().doubleValue();
        }
        return new BigDecimal(retVal);
    }
}