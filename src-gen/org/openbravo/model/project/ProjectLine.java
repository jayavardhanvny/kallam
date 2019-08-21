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
package org.openbravo.model.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductCategory;
import org.openbravo.model.financialmgmt.tax.TaxRate;
/**
 * Entity class for entity ProjectLine (stored in table C_ProjectLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ProjectLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_ProjectLine";
    public static final String ENTITY_NAME = "ProjectLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PROJECT = "project";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_PLANNEDQUANTITY = "plannedQuantity";
    public static final String PROPERTY_PLANNEDPRICE = "plannedPrice";
    public static final String PROPERTY_PLANNEDAMOUNT = "plannedAmount";
    public static final String PROPERTY_PLANNEDMARGIN = "plannedMargin";
    public static final String PROPERTY_CONTRACTAMOUNT = "contractAmount";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_PRODUCTCATEGORY = "productCategory";
    public static final String PROPERTY_INVOICEAMOUNT = "invoiceAmount";
    public static final String PROPERTY_INVOICEQUANTITY = "invoiceQuantity";
    public static final String PROPERTY_CONTRACTQUANTITY = "contractQuantity";
    public static final String PROPERTY_PROJECTISSUE = "projectIssue";
    public static final String PROPERTY_SALESORDER = "salesOrder";
    public static final String PROPERTY_PURCHASEORDER = "purchaseOrder";
    public static final String PROPERTY_PRINT = "print";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_PRICING = "pricing";
    public static final String PROPERTY_PLANNEDPURCHASEPRICE = "plannedPurchasePrice";
    public static final String PROPERTY_TAX = "tax";
    public static final String PROPERTY_PRODUCTDESCRIPTION = "productDescription";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_IDENTIFIER = "identifier";
    public static final String PROPERTY_INVOICELINELIST = "invoiceLineList";

    public ProjectLine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PLANNEDQUANTITY, new BigDecimal(1));
        setDefaultValue(PROPERTY_PLANNEDAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PLANNEDMARGIN, new BigDecimal(0));
        setDefaultValue(PROPERTY_CONTRACTAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_INVOICEAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_INVOICEQUANTITY, new BigDecimal(0));
        setDefaultValue(PROPERTY_CONTRACTQUANTITY, new BigDecimal(0));
        setDefaultValue(PROPERTY_PRINT, true);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_PRICING, false);
        setDefaultValue(PROPERTY_INVOICELINELIST, new ArrayList<Object>());
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

    public Project getProject() {
        return (Project) get(PROPERTY_PROJECT);
    }

    public void setProject(Project project) {
        set(PROPERTY_PROJECT, project);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public BigDecimal getPlannedQuantity() {
        return (BigDecimal) get(PROPERTY_PLANNEDQUANTITY);
    }

    public void setPlannedQuantity(BigDecimal plannedQuantity) {
        set(PROPERTY_PLANNEDQUANTITY, plannedQuantity);
    }

    public BigDecimal getPlannedPrice() {
        return (BigDecimal) get(PROPERTY_PLANNEDPRICE);
    }

    public void setPlannedPrice(BigDecimal plannedPrice) {
        set(PROPERTY_PLANNEDPRICE, plannedPrice);
    }

    public BigDecimal getPlannedAmount() {
        return (BigDecimal) get(PROPERTY_PLANNEDAMOUNT);
    }

    public void setPlannedAmount(BigDecimal plannedAmount) {
        set(PROPERTY_PLANNEDAMOUNT, plannedAmount);
    }

    public BigDecimal getPlannedMargin() {
        return (BigDecimal) get(PROPERTY_PLANNEDMARGIN);
    }

    public void setPlannedMargin(BigDecimal plannedMargin) {
        set(PROPERTY_PLANNEDMARGIN, plannedMargin);
    }

    public BigDecimal getContractAmount() {
        return (BigDecimal) get(PROPERTY_CONTRACTAMOUNT);
    }

    public void setContractAmount(BigDecimal contractAmount) {
        set(PROPERTY_CONTRACTAMOUNT, contractAmount);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public ProductCategory getProductCategory() {
        return (ProductCategory) get(PROPERTY_PRODUCTCATEGORY);
    }

    public void setProductCategory(ProductCategory productCategory) {
        set(PROPERTY_PRODUCTCATEGORY, productCategory);
    }

    public BigDecimal getInvoiceAmount() {
        return (BigDecimal) get(PROPERTY_INVOICEAMOUNT);
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        set(PROPERTY_INVOICEAMOUNT, invoiceAmount);
    }

    public BigDecimal getInvoiceQuantity() {
        return (BigDecimal) get(PROPERTY_INVOICEQUANTITY);
    }

    public void setInvoiceQuantity(BigDecimal invoiceQuantity) {
        set(PROPERTY_INVOICEQUANTITY, invoiceQuantity);
    }

    public BigDecimal getContractQuantity() {
        return (BigDecimal) get(PROPERTY_CONTRACTQUANTITY);
    }

    public void setContractQuantity(BigDecimal contractQuantity) {
        set(PROPERTY_CONTRACTQUANTITY, contractQuantity);
    }

    public ProjectIssue getProjectIssue() {
        return (ProjectIssue) get(PROPERTY_PROJECTISSUE);
    }

    public void setProjectIssue(ProjectIssue projectIssue) {
        set(PROPERTY_PROJECTISSUE, projectIssue);
    }

    public Order getSalesOrder() {
        return (Order) get(PROPERTY_SALESORDER);
    }

    public void setSalesOrder(Order salesOrder) {
        set(PROPERTY_SALESORDER, salesOrder);
    }

    public Order getPurchaseOrder() {
        return (Order) get(PROPERTY_PURCHASEORDER);
    }

    public void setPurchaseOrder(Order purchaseOrder) {
        set(PROPERTY_PURCHASEORDER, purchaseOrder);
    }

    public Boolean isPrint() {
        return (Boolean) get(PROPERTY_PRINT);
    }

    public void setPrint(Boolean print) {
        set(PROPERTY_PRINT, print);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public Boolean isPricing() {
        return (Boolean) get(PROPERTY_PRICING);
    }

    public void setPricing(Boolean pricing) {
        set(PROPERTY_PRICING, pricing);
    }

    public BigDecimal getPlannedPurchasePrice() {
        return (BigDecimal) get(PROPERTY_PLANNEDPURCHASEPRICE);
    }

    public void setPlannedPurchasePrice(BigDecimal plannedPurchasePrice) {
        set(PROPERTY_PLANNEDPURCHASEPRICE, plannedPurchasePrice);
    }

    public TaxRate getTax() {
        return (TaxRate) get(PROPERTY_TAX);
    }

    public void setTax(TaxRate tax) {
        set(PROPERTY_TAX, tax);
    }

    public String getProductDescription() {
        return (String) get(PROPERTY_PRODUCTDESCRIPTION);
    }

    public void setProductDescription(String productDescription) {
        set(PROPERTY_PRODUCTDESCRIPTION, productDescription);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getIdentifier() {
        return (String) get(PROPERTY_IDENTIFIER);
    }

    public void setIdentifier(String identifier) {
        set(PROPERTY_IDENTIFIER, identifier);
    }

    @SuppressWarnings("unchecked")
    public List<InvoiceLine> getInvoiceLineList() {
        return (List<InvoiceLine>) get(PROPERTY_INVOICELINELIST);
    }

    public void setInvoiceLineList(List<InvoiceLine> invoiceLineList) {
        set(PROPERTY_INVOICELINELIST, invoiceLineList);
    }

}
