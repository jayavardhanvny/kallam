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
package org.openbravo.model.sales;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.order.OrderLine;
/**
 * Entity class for entity SalesCommissionDetail (stored in table C_CommissionDetail).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class CommissionDetail extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_CommissionDetail";
    public static final String ENTITY_NAME = "SalesCommissionDetail";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_COMMISSIONAMOUNT = "commissionAmount";
    public static final String PROPERTY_REFERENCE = "reference";
    public static final String PROPERTY_SALESORDERLINE = "salesOrderLine";
    public static final String PROPERTY_INVOICELINE = "invoiceLine";
    public static final String PROPERTY_COMMENTS = "comments";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_ACTUALAMOUNT = "actualAmount";
    public static final String PROPERTY_CONVERTEDAMOUNT = "convertedAmount";
    public static final String PROPERTY_ACTUALQUANTITY = "actualQuantity";
    public static final String PROPERTY_ISCOSTCALCULATED = "isCostCalculated";

    public CommissionDetail() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ISCOSTCALCULATED, true);
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
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

    public CommissionAmount getCommissionAmount() {
        return (CommissionAmount) get(PROPERTY_COMMISSIONAMOUNT);
    }

    public void setCommissionAmount(CommissionAmount commissionAmount) {
        set(PROPERTY_COMMISSIONAMOUNT, commissionAmount);
    }

    public String getReference() {
        return (String) get(PROPERTY_REFERENCE);
    }

    public void setReference(String reference) {
        set(PROPERTY_REFERENCE, reference);
    }

    public OrderLine getSalesOrderLine() {
        return (OrderLine) get(PROPERTY_SALESORDERLINE);
    }

    public void setSalesOrderLine(OrderLine salesOrderLine) {
        set(PROPERTY_SALESORDERLINE, salesOrderLine);
    }

    public InvoiceLine getInvoiceLine() {
        return (InvoiceLine) get(PROPERTY_INVOICELINE);
    }

    public void setInvoiceLine(InvoiceLine invoiceLine) {
        set(PROPERTY_INVOICELINE, invoiceLine);
    }

    public String getComments() {
        return (String) get(PROPERTY_COMMENTS);
    }

    public void setComments(String comments) {
        set(PROPERTY_COMMENTS, comments);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public BigDecimal getActualAmount() {
        return (BigDecimal) get(PROPERTY_ACTUALAMOUNT);
    }

    public void setActualAmount(BigDecimal actualAmount) {
        set(PROPERTY_ACTUALAMOUNT, actualAmount);
    }

    public BigDecimal getConvertedAmount() {
        return (BigDecimal) get(PROPERTY_CONVERTEDAMOUNT);
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        set(PROPERTY_CONVERTEDAMOUNT, convertedAmount);
    }

    public BigDecimal getActualQuantity() {
        return (BigDecimal) get(PROPERTY_ACTUALQUANTITY);
    }

    public void setActualQuantity(BigDecimal actualQuantity) {
        set(PROPERTY_ACTUALQUANTITY, actualQuantity);
    }

    public Boolean isCostCalculated() {
        return (Boolean) get(PROPERTY_ISCOSTCALCULATED);
    }

    public void setCostCalculated(Boolean isCostCalculated) {
        set(PROPERTY_ISCOSTCALCULATED, isCostCalculated);
    }

}
