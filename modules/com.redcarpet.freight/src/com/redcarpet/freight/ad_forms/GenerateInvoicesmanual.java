package com.redcarpet.freight.ad_forms;

import com.redcarpet.freight.ad_process.RCFR_Calculate_Additional_Costs_Abstract;
import com.redcarpet.freight.utils.BpartnerMiscData;
import com.redcarpet.freight.utils.GenerateInvoiceUtils;
import com.redcarpet.freight.utils.SEOrderBPartnerData;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.openbravo.base.filter.IsIDFilter;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.businessUtility.WindowTabs;
import org.openbravo.erpCommon.utility.ComboTableData;
import org.openbravo.erpCommon.utility.LeftTabsBar;
import org.openbravo.erpCommon.utility.NavigationBar;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.ToolBar;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import org.openbravo.model.pricing.pricelist.PriceList;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.xmlEngine.XmlDocument;
import com.redcarpet.freight.ad_process.RCFRAdditionalCostUtilData;

@SuppressWarnings("unchecked")
public class GenerateInvoicesmanual extends HttpSecureAppServlet {

    private static final long serialVersionUID = 1L;
    private String query;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        VariablesSecureApp vars = new VariablesSecureApp(request);
        if (vars.commandIn("DEFAULT")) {
            String strDateFrom = vars.getGlobalVariable("inpDateFrom", "GenerateInvoicesmanual|DateFrom", "");
            String strC_BPartner_ID = vars.getGlobalVariable("inpcBpartnerId", "GenerateInvoicesmanual|C_BPartner_ID", "");
            String strAD_Org_ID = vars.getGlobalVariable("inpadOrgId", "GenerateInvoicesmanual|Ad_Org_ID", vars.getOrg());
            printPageDataSheet(response, vars, strC_BPartner_ID, strAD_Org_ID, strDateFrom);
        } else if (vars.commandIn("FIND")) {
            String strDateFrom = vars.getRequestGlobalVariable("inpDateFrom", "GenerateInvoicesmanual|DateFrom");
            String strC_BPartner_ID = vars.getRequestGlobalVariable("inpcBpartnerId", "GenerateInvoicesmanual|C_BPartner_ID");
            String strAD_Org_ID = vars.getRequestGlobalVariable("inpadOrgId", "GenerateInvoicesmanual|Ad_Org_ID");
            printPageDataSheet(response, vars, strC_BPartner_ID, strAD_Org_ID, strDateFrom);
        } else if (vars.commandIn("GENERATE")) {

            String strDocumentType = vars.getRequestGlobalVariable("cDocumentTypeId", "GenerateInvoicesmanual|documentType");
            String strMInOutLineId = vars.getInStringParameter("inpOrder", IsIDFilter.instance);
            OBContext.setAdminMode();
            OBError myMessage = new OBError();

            try {
                List<Invoice> invoiceList = getCreatedInvoiceHeader(strMInOutLineId, strDocumentType, vars);
                List<InvoiceLine> invoiceLineList = getCreatedInvoiceLines(invoiceList, strMInOutLineId);
                for (Invoice invoice : invoiceList) {
                    reAdjustFreightAmounts(invoice);
                    new RCFR_Calculate_Additional_Costs_Abstract().doExecute(invoice);
                }
                myMessage.setType("Success");
                myMessage.setTitle("Process completed successfully");
                StringBuilder builder = new StringBuilder();
                builder.append("Invoices generated successfully: ");
                for (Invoice invoice : invoiceList) {
                    builder.append(invoice.getDocumentNo()).append(", ");
                }
                myMessage.setMessage(builder.toString());

            } catch (Exception ex) {
                ex.printStackTrace();
                myMessage.setType("Error");
                myMessage.setTitle("Error");
                myMessage.setMessage(ex.getMessage()); 
            }
            OBContext.restorePreviousMode();
            vars.setMessage("GenerateInvoicesmanual", myMessage);
            response.sendRedirect(strDireccion + request.getServletPath());
        } else {
            pageError(response);
        }
    }

    private void printPageDataSheet(HttpServletResponse response, VariablesSecureApp vars, String strC_BPartner_ID, String strAD_Org_ID, String strDateFrom) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String discard[] = {"sectionDetail"};
        XmlDocument xmlDocument = null;
        GenerateInvoicesmanualData[] data = null;

        if (strC_BPartner_ID.equals("") && strAD_Org_ID.equals("")) {
            xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/freight/ad_forms/GenerateInvoicesmanual", discard).createXmlDocument();
            data = GenerateInvoicesmanualData.set();
        } else {
            xmlDocument = xmlEngine.readXmlTemplate("com/redcarpet/freight/ad_forms/GenerateInvoicesmanual").createXmlDocument();
            data = GenerateInvoicesmanualData.select(this, strC_BPartner_ID, strDateFrom, strAD_Org_ID);
        }

        ToolBar toolbar = new ToolBar(this, vars.getLanguage(), "GenerateInvoicesmanual", false, "", "", "", false, "ad_forms", strReplaceWith, false, true);
        toolbar.prepareSimpleToolBarTemplate();
        xmlDocument.setParameter("toolbar", toolbar.toString());
        try {
            WindowTabs tabs = new WindowTabs(this, vars, "com.redcarpet.freight.ad_forms.GenerateInvoicesmanual");
            xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
            xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
            xmlDocument.setParameter("childTabContainer", tabs.childTabs());
            xmlDocument.setParameter("theme", vars.getTheme());
            NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "GenerateInvoicesmanual.html", classInfo.id, classInfo.type, strReplaceWith, tabs.breadcrumb());
            xmlDocument.setParameter("navigationBar", nav.toString());
            LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "GenerateInvoicesmanual.html", strReplaceWith);
            xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        }
        OBError myMessage = vars.getMessage("GenerateInvoicesmanual");
        vars.removeMessage("GenerateInvoicesmanual");
        if (myMessage != null) {
            xmlDocument.setParameter("messageType", myMessage.getType());
            xmlDocument.setParameter("messageTitle", myMessage.getTitle());
            xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
        xmlDocument.setParameter("calendar", vars.getLanguage().substring(0, 2));
        xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
        xmlDocument.setParameter("paramLanguage", "defaultLang=\"" + vars.getLanguage() + "\";");
        xmlDocument.setParameter("paramBPartnerId", strC_BPartner_ID);
        xmlDocument.setParameter("paramADOrgID", strAD_Org_ID);
        xmlDocument.setParameter("dateFrom", strDateFrom);
        xmlDocument.setParameter("dateFromdisplayFormat", vars.getSessionValue("#AD_SqlDateFormat"));
        xmlDocument.setParameter("dateFromsaveFormat", vars.getSessionValue("#AD_SqlDateFormat"));
        xmlDocument.setParameter("dateTodisplayFormat", vars.getSessionValue("#AD_SqlDateFormat"));
        xmlDocument.setParameter("dateTosaveFormat", vars.getSessionValue("#AD_SqlDateFormat"));
        xmlDocument.setParameter("invDatedisplayFormat", vars.getSessionValue("#AD_SqlDateFormat"));
        xmlDocument.setParameter("invDatesaveFormat", vars.getSessionValue("#AD_SqlDateFormat"));
        xmlDocument.setParameter("paramBPartnerDescription",
                GenerateInvoicesmanualData.bPartnerDescription(this, strC_BPartner_ID));

        xmlDocument.setParameter("cDocumentTypeId", "");
        try {
            ComboTableData comboTableData
                    = new ComboTableData(vars, this, "18", "C_DocType_ID", "78694A64FA324A2F97F5FB8966C5A1AC", "", //"22F546D49D3A48E1B2B4F50446A8DE58", "",
                            Utility.getContext(this, vars, "#AccessibleOrgTree", "Sales Invoice"),
                            Utility.getContext(this, vars, "#User_Client", "Sales Invoice"), 0);
            Utility.fillSQLParameters(this, vars, null, comboTableData, "Sales Invoice", "");
            xmlDocument.setData("reportC_DOCUMENTTYPE_ID", "liststructure", comboTableData.select(false));
            comboTableData = null;

        } catch (Exception ex) {
            throw new ServletException(ex);
        }

        try {
            ComboTableData comboTableData = new ComboTableData(vars, this, "TABLEDIR", "AD_Org_ID", "", "AD_Org Security validation", Utility.getContext(this, vars, "#User_Org", "GenerateInvoicesmanual"), Utility.getContext(this, vars, "#User_Client", "GenerateInvoicesmanual"), 0);
            Utility.fillSQLParameters(this, vars, null, comboTableData, "GenerateInvoicesmanual", strAD_Org_ID);
            xmlDocument.setData("reportAD_Org_ID", "liststructure", comboTableData.select(false));
            comboTableData = null;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        xmlDocument.setData("structure1", data);
        out.println(xmlDocument.print());
        out.close();
    }

    private List<Invoice> getCreatedInvoiceHeader(String strMInOutLineId, String documentType, VariablesSecureApp vars) throws Exception {

        //String query = "SELECT distinct iol.businessPartner.id FROM MaterialMgmtShipmentInOutLine iol  where iol.id in " + strMInOutLineId;
        String query = "SELECT iol.businessPartner.id FROM MaterialMgmtShipmentInOutLine iol  where iol.id in " + strMInOutLineId + " order by iol.lineNo";
        Session session = OBDal.getInstance().getSession();
        List<String> liQ = (List<String>) session.createQuery(query).list();
        List<Invoice> invoiceList = new ArrayList<Invoice>();
        for (String strCBPartnerId : liQ) {
            BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class, strCBPartnerId);
            Invoice invoice = createInvoiceHeader(getShipmentObject(strMInOutLineId, strCBPartnerId), bp, documentType, vars);
            invoiceList.add(invoice);
        }
        return invoiceList;
    }

    private Invoice createInvoiceHeader(ShipmentInOut shipment, BusinessPartner bPartner, String strDocumentType, VariablesSecureApp vars) throws Exception {
        Invoice invoice = OBProvider.getInstance().get(Invoice.class);
        try {
            invoice.setOrganization(shipment.getOrganization());
            invoice.setDocumentNo(GenerateInvoiceUtils.getInvoiceDocumentNo(shipment, strDocumentType, vars));
            invoice.setDocumentStatus("DR");
            invoice.setDocumentAction("CO");
            invoice.setDocumentType(OBDal.getInstance().get(DocumentType.class, "0"));
            invoice.setTransactionDocument(OBDal.getInstance().get(DocumentType.class, strDocumentType));
            invoice.setInvoiceDate(shipment.getMovementDate());
            invoice.setDatePrinted(shipment.getMovementDate());
            invoice.setAccountingDate(shipment.getMovementDate());
            invoice.setBusinessPartner(bPartner);
            invoice.setPartnerAddress(bPartner.getBusinessPartnerLocationList().get(0));
            invoice.setEpcgAgent(shipment.getBusinessPartner());
            invoice.setPrintDiscount(true);
            invoice.setCurrency(shipment.getOrganization().getCurrency());
            String strPaymentTerms = getBusinessPartnerPaymentTerms(shipment.getBusinessPartner().getId(), shipment.getOrganization().getId(), shipment.getClient().getId());
            invoice.setPaymentTerms(OBDal.getInstance().get(PaymentTerm.class, strPaymentTerms));
            String strPaymentMethod = getBusinessPartnerPaymentMethod(shipment.getBusinessPartner().getId(), shipment.getOrganization().getId(), shipment.getClient().getId());
            invoice.setPaymentMethod(OBDal.getInstance().get(FIN_PaymentMethod.class, strPaymentMethod));
            String strPriceList = getBusinessPartnerPriceList(shipment.getBusinessPartner().getId(), shipment.getOrganization().getId(), shipment.getClient().getId());
            invoice.setPriceList(OBDal.getInstance().get(PriceList.class, strPriceList));
            invoice.setCreateLinesFrom(true);
            invoice.setPriceIncludesTax(OBDal.getInstance().get(PriceList.class, strPriceList).isPriceIncludesTax());
            invoice.setGrandTotalAmount(BigDecimal.ZERO);
            invoice.setSummedLineAmount(BigDecimal.ZERO);
            invoice.setRcfrRatetype(shipment.getRcfrRatetype());
            BigDecimal noOfLines = new BigDecimal(shipment.getMaterialMgmtShipmentInOutLineList().size());
            BigDecimal agentCount = getNoOfAgentsInGRN(shipment.getId(), bPartner.getId());
            invoice.setRcfrFreighth((shipment.getRcfrFreighth().divide(noOfLines, 3, RoundingMode.HALF_UP)).multiply(agentCount));
            invoice.setRcfrInsuranceh(shipment.getRcfrInsuranceh());
            OBDal.getInstance().save(invoice);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return invoice;
    }

    private String getBusinessPartnerPaymentTerms(String strBPartner, String strOrgId, String strClientId) throws Exception {
        String paymentTerm = null;
        try {
            BpartnerMiscData[] data = BpartnerMiscData.select(new DalConnectionProvider(), strBPartner);
            paymentTerm = data.length != 0 ? data[0].cPaymenttermId : "";
            if (paymentTerm.equalsIgnoreCase("")) {
                BpartnerMiscData[] data2 = BpartnerMiscData.selectPaymentTerm(this, strOrgId, strClientId);
                if (data2.length != 0) {
                    paymentTerm = data2[0].cPaymenttermId;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("null Payment Term for Business Partner");
        }
        return paymentTerm;
    }

    private String getBusinessPartnerPaymentMethod(String strBPartner, String strOrgId, String strClientId) throws Exception {
        String paymentMethod = null;
        try {
            BpartnerMiscData[] data = BpartnerMiscData.select(new DalConnectionProvider(), strBPartner);
            paymentMethod = data.length != 0 ? data[0].finPaymentmethodId : "";
            if (paymentMethod.equalsIgnoreCase("")) {
                paymentMethod = BpartnerMiscData.selectPaymentMethod(new DalConnectionProvider(), strOrgId, strClientId);
                if (StringUtils.equalsIgnoreCase("", paymentMethod)) {
                    throw new IllegalArgumentException("Payment Method is Mandatory");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("null Payment Method for Business Partner");
        }
        return paymentMethod;
    }

    private String getBusinessPartnerPriceList(String strBPartner, String strOrgId, String strClientId) throws Exception {
        String strPriceList = null;
        try {
            BpartnerMiscData[] data = BpartnerMiscData.select(new DalConnectionProvider(), strBPartner);
            strPriceList = data.length != 0 ? data[0].mPricelistId : "";
            if (StringUtils.equalsIgnoreCase("", strPriceList)) {
                strPriceList = SEOrderBPartnerData.defaultPriceList(this, "Y", strClientId);
                if (StringUtils.equalsIgnoreCase("", strPriceList)) {
                    throw new IllegalArgumentException("Default price list doesnot exists");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("null Price List for Business Partner");
        }
        return strPriceList;
    }

    private List<String> GetShipmentList(String strMInOutId) throws HibernateException {
        String shipmentHql = "SELECT io.id FROM MaterialMgmtShipmentInOut io "
                + "join io.materialMgmtShipmentInOutLineList iol "
                + "where iol.id in " + strMInOutId;
        return (List<String>) OBDal.getInstance().getSession().createQuery(shipmentHql).list();
    }

    private InvoiceLine createInvoiceLine(String strMInOutLineId, Invoice invoice) throws Exception{
        InvoiceLine invoiceLine = OBProvider.getInstance().get(InvoiceLine.class);
        try {
            ShipmentInOutLine line = OBDal.getInstance().get(ShipmentInOutLine.class, strMInOutLineId);
            invoiceLine.setInvoice(invoice);
            invoiceLine.setGoodsShipmentLine(line);
            invoiceLine.setLineNo(getLineNo(invoice.getId()));
            invoiceLine.setProduct(line.getProduct());
            invoiceLine.setUOM(line.getUOM());
            invoiceLine.setInvoicedQuantity(line.getMovementQuantity().subtract(getDeliveredQuantity(strMInOutLineId)));
            invoiceLine.setUnitPrice(line.getSalesOrderLine().getUnitPrice());
            invoiceLine.setLineNetAmount(line.getSalesOrderLine().getLineNetAmount());
            invoiceLine.setTax(line.getSalesOrderLine().getTax());
            invoiceLine.setEpcgPackaging(line.getEpcgPackaging());
            invoiceLine.setEpcgNoofpackages(line.getEpcgNoofpackages());
            invoiceLine.setRcfrNetunitrate(line.getRcfrNetunitrate());
            invoiceLine.setUnitPrice(line.getRcfrPriceactual());
            OBDal.getInstance().save(invoiceLine);
            OBDal.getInstance().commitAndClose();
            OBDal.getInstance().flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return invoiceLine;
    }

    private Long getLineNo(String invoiceId) {
        String hql = " SELECT COALESCE(MAX(lineNo),0)+10 AS defaultValue FROM InvoiceLine WHERE invoice.id = '" + invoiceId + "' ";
        Session session = OBDal.getInstance().getSession();
        List list = session.createQuery(hql).list();
        return list.size() != 0 ? Long.parseLong(list.get(0).toString()) : (long) 10;
    }

    private List<InvoiceLine> getCreatedInvoiceLines(List<Invoice> invoiceList, String strMInOutLineId) throws Exception {
        List<InvoiceLine> invoiceLineList = new ArrayList<InvoiceLine>();
        for (Invoice invoice : invoiceList) {
            String queryInvoice = "SELECT iol.id FROM MaterialMgmtShipmentInOut io "
                    + " join io.materialMgmtShipmentInOutLineList iol "
                    + " where iol.id in " + strMInOutLineId + " and iol.businessPartner.id = '" + invoice.getBusinessPartner().getId() + "' ";
            Session session = OBDal.getInstance().getSession();
            List<String> strShipmentLinelist = session.createQuery(queryInvoice).list();
            for (String strMInOutLine : strShipmentLinelist) {
                InvoiceLine line = createInvoiceLine(strMInOutLine, invoice);
                invoiceLineList.add(line);
            }
        }
        return invoiceLineList;

    }

    private ShipmentInOut getShipmentObject(String strMInOutLineId, String strCBPartnerId) {
        String getShipmentHQL = "SELECT io.id FROM MaterialMgmtShipmentInOut io "
                + " join io.materialMgmtShipmentInOutLineList iol "
                + " where iol.id in " + strMInOutLineId + " and iol.businessPartner.id = '" + strCBPartnerId + "' ";
        String strShipmentId = ((List<String>) OBDal.getInstance().getSession().createQuery(getShipmentHQL).list()).get(0);
        return OBDal.getInstance().get(ShipmentInOut.class, strShipmentId);
    }

    private BigDecimal getNoOfAgentsInGRN(String shipmentId, String agentId) throws Exception{
        String strAgentCount = null;
        try {
            strAgentCount = GenerateInvoicesmanualData.getAgentCount(new DalConnectionProvider(), shipmentId, agentId);
        } catch (ServletException ex) {
            ex.printStackTrace();
            throw new Exception(ex);
        }
        return new BigDecimal(strAgentCount);
    }

    private BigDecimal getDeliveredQuantity(String strShipmentLineId) throws Exception {
        String strQty = GenerateInvoicesmanualData.getSumDeliveredQty(new DalConnectionProvider(), strShipmentLineId);
        return new BigDecimal(strQty);
    }

    private void reAdjustFreightAmounts(Invoice invoice) throws Exception {
        try {
            RCFRAdditionalCostUtilData[] data = RCFRAdditionalCostUtilData.selectInvoiceLineId(new DalConnectionProvider(), invoice.getDocumentNo());
            for (RCFRAdditionalCostUtilData dataLine : data) {
                InvoiceLine line = OBDal.getInstance().get(InvoiceLine.class, dataLine.cInvoicelineId);
                BigDecimal freight = line.getGoodsShipmentLine().getShipmentReceipt().getRcfrFreighth();
                BigDecimal totalQty = getTotalQtyShipped(line.getGoodsShipmentLine().getShipmentReceipt());
                BigDecimal unitFreight = freight.divide(totalQty, 6, RoundingMode.HALF_UP);
                BigDecimal sumInvoicedQty = getSumQuantitiesInInvoice(line.getInvoice());
                line.getInvoice().setRcfrFreighth(sumInvoicedQty.multiply(unitFreight));
                BigDecimal freightPaid = line.getGoodsShipmentLine().getShipmentReceipt().getRcfrFreightpaid();
                BigDecimal unitFreightPaid = freightPaid.divide(totalQty, 6, RoundingMode.HALF_UP);
                line.getInvoice().setRcfrFreightpaid(sumInvoicedQty.multiply(unitFreightPaid));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

    }

    private BigDecimal getTotalQtyShipped(ShipmentInOut shipmentReceipt) {
        double qty = 0;
        for (ShipmentInOutLine line : shipmentReceipt.getMaterialMgmtShipmentInOutLineList()) {
            qty += line.getMovementQuantity().doubleValue();
        }
        return new BigDecimal(qty);
    }

    private BigDecimal getSumQuantitiesInInvoice(Invoice inv) {
        double qty = 0;
        for (InvoiceLine line : inv.getInvoiceLineList()) {
            qty += line.getInvoicedQuantity().doubleValue();
        }
        return new BigDecimal(qty);
    }
}
