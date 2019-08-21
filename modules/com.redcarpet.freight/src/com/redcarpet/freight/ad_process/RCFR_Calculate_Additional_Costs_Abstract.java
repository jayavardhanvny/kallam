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
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.financialmgmt.gl.GLItem;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author Mateen
 */
@SuppressWarnings("unchecked")
public class RCFR_Calculate_Additional_Costs_Abstract {

    public RCFR_Calculate_Additional_Costs_Abstract() {
    }

    public void doExecute(Invoice invoice) throws Exception {
        OBContext.setAdminMode();
        try {
            doIt(invoice);
            OBDal.getInstance().flush();
            deleteNInsertFinancialAcctLines(invoice);
            insertSubBusinessAgent(invoice.getId());
            invoice.setRcfrCalcdistcosth(Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
            invoice.setRcfrCalcdistcosth(Boolean.FALSE);
            throw new Exception(e);
        }
        OBContext.restorePreviousMode();
    }

    private void doIt(Invoice invoice) throws ServletException {
        RCFRAdditionalCostUtilData[] data = RCFRAdditionalCostUtilData.selectInvoiceLineId(new DalConnectionProvider(), invoice.getDocumentNo());
        for (RCFRAdditionalCostUtilData dataLine : data) {
            InvoiceLine line = OBDal.getInstance().get(InvoiceLine.class, dataLine.cInvoicelineId);
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
                line.setLineNetAmount((line.getGoodsShipmentLine().getRcfrNetunitrate().multiply(line.getInvoicedQuantity())).subtract(line.getTaxAmount()));
                line.setRcfrFreight(calculateFreightCost(line));
                line.setRcfrInsurance(calculateInsuranceCost(line, false));
                line.setUnitPrice((line.getLineNetAmount().subtract(line.getRcfrFreight()).subtract(line.getRcfrInsurance())).divide(line.getInvoicedQuantity(), 3, RoundingMode.HALF_UP));
                line.setRcfrNetunitrate(line.getGoodsShipmentLine().getRcfrNetunitrate());
                line.setRcfrNetrate(line.getGoodsShipmentLine().getRcfrNetunitrate().multiply(line.getInvoicedQuantity()));
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
            BigDecimal numerator = line.getGoodsShipmentLine().getRcfrNetunitrate().multiply(line.getInvoicedQuantity());
            BigDecimal taxRate = line.getTax().getRate().multiply(new BigDecimal(0.01));
            BigDecimal denominator = new BigDecimal(BigInteger.ONE).add(taxRate);
            line.setTaxAmount(numerator.divide(denominator, 4, RoundingMode.UP).multiply(taxRate));
        }
    }

    private void deleteNInsertFinancialAcctLines(Invoice invoice) throws Exception {

        RCFRAdditionalCostUtilData.deleteLine(new DalConnectionProvider(), invoice.getDocumentNo());
        String strFreightId = RCFRAdditionalCostUtilData.getFreightAccount(new DalConnectionProvider());
        String strInsuranceId = RCFRAdditionalCostUtilData.getInsuranceAccount(new DalConnectionProvider());
        BigDecimal freightSum = getSumLineFreight(invoice);
        BigDecimal insuranceSum = getSumLineInsurance(invoice);
        String strTaxRateId = getLine10TaxRateId(invoice);
        if (freightSum.doubleValue() > 0) {
            insertAccountingLine(invoice, freightSum, strFreightId, strTaxRateId);
        }
        if (insuranceSum.doubleValue() > 0) {
            insertAccountingLine(invoice, insuranceSum, strInsuranceId, strTaxRateId);
        }
    }

    private BigDecimal getSumLineFreight(Invoice invoice) throws Exception{
        String strFreight = RCFRAdditionalCostUtilData.getFreightPaidAmount(new DalConnectionProvider(), invoice.getDocumentNo());
        return new BigDecimal(strFreight);
    }

    private BigDecimal getSumLineInsurance(Invoice invoice) throws ServletException {
        RCFRAdditionalCostUtilData[] data = RCFRAdditionalCostUtilData.selectInvoiceLineId(new DalConnectionProvider(), invoice.getDocumentNo());
        double retVal = 0;
        for (RCFRAdditionalCostUtilData dataLine : data) {
            InvoiceLine line = OBDal.getInstance().get(InvoiceLine.class, dataLine.cInvoicelineId);
            retVal += line.getRcfrInsurance().doubleValue();
        }
        return new BigDecimal(retVal);
    }

    private void insertAccountingLine(Invoice invoice, BigDecimal unitPrice, String strAccountId, String strTaxRateId) throws ServletException {
        InvoiceLine invoiceLine = OBProvider.getInstance().get(InvoiceLine.class);
        invoiceLine.setInvoice(invoice);
        invoiceLine.setLineNo(getLineNo(invoice.getDocumentNo()));
        invoiceLine.setUnitPrice(unitPrice);
        invoiceLine.setLineNetAmount(unitPrice);
        invoiceLine.setFinancialInvoiceLine(Boolean.TRUE);
        invoiceLine.setAccount(OBDal.getInstance().get(GLItem.class, strAccountId));
        invoiceLine.setTax(OBDal.getInstance().get(TaxRate.class, strTaxRateId));
        OBDal.getInstance().save(invoiceLine);
    }

    private long getLineNo(String documentno) throws ServletException {
        String strLineNo = RCFRAdditionalCostUtilData.selectLineNo(new DalConnectionProvider(), documentno);
        return Long.valueOf(strLineNo);
    }

    private String getLine10TaxRateId(Invoice invoice) throws Exception {
        String strCTaxId = "";
        try {
            strCTaxId = RCFRAdditionalCostUtilData.selectTaxId(new DalConnectionProvider(), invoice.getDocumentNo());
        } catch (ServletException ex) {
            ex.printStackTrace();
            throw new Exception("Line 10 Tax Id Missing");
        }
        return strCTaxId;
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
