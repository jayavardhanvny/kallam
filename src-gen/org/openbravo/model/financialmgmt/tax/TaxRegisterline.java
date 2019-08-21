/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2008-2011 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
*/
package org.openbravo.model.financialmgmt.tax;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceTax;
/**
 * Entity class for entity FinancialMgmtTaxRegisterline (stored in table C_TaxRegisterline).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class TaxRegisterline extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_TaxRegisterline";
    public static final String ENTITY_NAME = "FinancialMgmtTaxRegisterline";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_TAXREGISTER = "taxRegister";
    public static final String PROPERTY_INVOICETAX = "invoiceTax";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_TAX = "tax";
    public static final String PROPERTY_TAXABLEAMOUNT = "taxableAmount";
    public static final String PROPERTY_TAXAMOUNT = "taxAmount";
    public static final String PROPERTY_TOTALAMOUNT = "totalAmount";
    public static final String PROPERTY_UNDEDUCTABLEAMOUNT = "undeductableAmount";
    public static final String PROPERTY_INVOICEDATE = "invoiceDate";
    public static final String PROPERTY_EXEMPTAMOUNT = "exemptAmount";
    public static final String PROPERTY_NOVATAMOUNT = "noVATAmount";
    public static final String PROPERTY_NAME = "name";

    public TaxRegisterline() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public Client getClient() {
        return (Client) get(PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
        set(PROPERTY_CLIENT, client);
    }

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    public TaxRegister getTaxRegister() {
        return (TaxRegister) get(PROPERTY_TAXREGISTER);
    }

    public void setTaxRegister(TaxRegister taxRegister) {
        set(PROPERTY_TAXREGISTER, taxRegister);
    }

    public InvoiceTax getInvoiceTax() {
        return (InvoiceTax) get(PROPERTY_INVOICETAX);
    }

    public void setInvoiceTax(InvoiceTax invoiceTax) {
        set(PROPERTY_INVOICETAX, invoiceTax);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public TaxRate getTax() {
        return (TaxRate) get(PROPERTY_TAX);
    }

    public void setTax(TaxRate tax) {
        set(PROPERTY_TAX, tax);
    }

    public BigDecimal getTaxableAmount() {
        return (BigDecimal) get(PROPERTY_TAXABLEAMOUNT);
    }

    public void setTaxableAmount(BigDecimal taxableAmount) {
        set(PROPERTY_TAXABLEAMOUNT, taxableAmount);
    }

    public BigDecimal getTaxAmount() {
        return (BigDecimal) get(PROPERTY_TAXAMOUNT);
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        set(PROPERTY_TAXAMOUNT, taxAmount);
    }

    public BigDecimal getTotalAmount() {
        return (BigDecimal) get(PROPERTY_TOTALAMOUNT);
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        set(PROPERTY_TOTALAMOUNT, totalAmount);
    }

    public BigDecimal getUndeductableAmount() {
        return (BigDecimal) get(PROPERTY_UNDEDUCTABLEAMOUNT);
    }

    public void setUndeductableAmount(BigDecimal undeductableAmount) {
        set(PROPERTY_UNDEDUCTABLEAMOUNT, undeductableAmount);
    }

    public Date getInvoiceDate() {
        return (Date) get(PROPERTY_INVOICEDATE);
    }

    public void setInvoiceDate(Date invoiceDate) {
        set(PROPERTY_INVOICEDATE, invoiceDate);
    }

    public BigDecimal getExemptAmount() {
        return (BigDecimal) get(PROPERTY_EXEMPTAMOUNT);
    }

    public void setExemptAmount(BigDecimal exemptAmount) {
        set(PROPERTY_EXEMPTAMOUNT, exemptAmount);
    }

    public BigDecimal getNoVATAmount() {
        return (BigDecimal) get(PROPERTY_NOVATAMOUNT);
    }

    public void setNoVATAmount(BigDecimal noVATAmount) {
        set(PROPERTY_NOVATAMOUNT, noVATAmount);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

}
