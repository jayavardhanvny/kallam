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
package com.redcarpet.epcg.data;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.tax.TaxRate;
/**
 * Entity class for entity Epcg_Proformaline (stored in table Epcg_Proformaline).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EpcgProformaline extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Epcg_Proformaline";
    public static final String ENTITY_NAME = "Epcg_Proformaline";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_EPCGPROFORMA = "epcgProforma";
    public static final String PROPERTY_SALESORDERLINE = "salesOrderLine";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_PROFORMALINEQTY = "proformalineqty";
    public static final String PROPERTY_ORDERQUANTITY = "orderQuantity";
    public static final String PROPERTY_EPCGPACKAGING = "epcgPackaging";
    public static final String PROPERTY_STORAGEBIN = "storageBin";
    public static final String PROPERTY_NOOFPACKAGES = "noofpackages";
    public static final String PROPERTY_UNITPRICE = "unitPrice";
    public static final String PROPERTY_NETRATE = "netrate";
    public static final String PROPERTY_LINENETAMOUNT = "lineNetAmount";
    public static final String PROPERTY_NETLINEAMT = "netlineamt";
    public static final String PROPERTY_NETUNITPRICE = "netUnitPrice";
    public static final String PROPERTY_FREIGHT = "freight";
    public static final String PROPERTY_INSURANCE = "insurance";
    public static final String PROPERTY_PNF = "pnf";
    public static final String PROPERTY_DISCOUNT = "discount";
    public static final String PROPERTY_TAX = "tax";
    public static final String PROPERTY_TAXAMOUNT = "taxAmount";
    public static final String PROPERTY_FRTAX = "frtax";
    public static final String PROPERTY_INSTAX = "instax";
    public static final String PROPERTY_PNFTAX = "pnftax";
    public static final String PROPERTY_FRTAXAPP = "frtaxapp";
    public static final String PROPERTY_INSTAXAPP = "instaxapp";
    public static final String PROPERTY_PNFTAXAPP = "pnftaxapp";
    public static final String PROPERTY_DESCRIPTION = "description";

    public EpcgProformaline() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROFORMALINEQTY, new BigDecimal(0));
        setDefaultValue(PROPERTY_UNITPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_NETRATE, new BigDecimal(0));
        setDefaultValue(PROPERTY_LINENETAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_NETLINEAMT, new BigDecimal(0));
        setDefaultValue(PROPERTY_NETUNITPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_FREIGHT, new BigDecimal(0));
        setDefaultValue(PROPERTY_INSURANCE, new BigDecimal(0));
        setDefaultValue(PROPERTY_PNF, new BigDecimal(0));
        setDefaultValue(PROPERTY_DISCOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_TAXAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_FRTAX, new BigDecimal(0));
        setDefaultValue(PROPERTY_INSTAX, new BigDecimal(0));
        setDefaultValue(PROPERTY_PNFTAX, new BigDecimal(0));
        setDefaultValue(PROPERTY_FRTAXAPP, new BigDecimal(0));
        setDefaultValue(PROPERTY_INSTAXAPP, new BigDecimal(0));
        setDefaultValue(PROPERTY_PNFTAXAPP, new BigDecimal(0));
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

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public EpcgProforma getEpcgProforma() {
        return (EpcgProforma) get(PROPERTY_EPCGPROFORMA);
    }

    public void setEpcgProforma(EpcgProforma epcgProforma) {
        set(PROPERTY_EPCGPROFORMA, epcgProforma);
    }

    public OrderLine getSalesOrderLine() {
        return (OrderLine) get(PROPERTY_SALESORDERLINE);
    }

    public void setSalesOrderLine(OrderLine salesOrderLine) {
        set(PROPERTY_SALESORDERLINE, salesOrderLine);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public BigDecimal getProformalineqty() {
        return (BigDecimal) get(PROPERTY_PROFORMALINEQTY);
    }

    public void setProformalineqty(BigDecimal proformalineqty) {
        set(PROPERTY_PROFORMALINEQTY, proformalineqty);
    }

    public BigDecimal getOrderQuantity() {
        return (BigDecimal) get(PROPERTY_ORDERQUANTITY);
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        set(PROPERTY_ORDERQUANTITY, orderQuantity);
    }

    public EPCG_Packaging getEpcgPackaging() {
        return (EPCG_Packaging) get(PROPERTY_EPCGPACKAGING);
    }

    public void setEpcgPackaging(EPCG_Packaging epcgPackaging) {
        set(PROPERTY_EPCGPACKAGING, epcgPackaging);
    }

    public Locator getStorageBin() {
        return (Locator) get(PROPERTY_STORAGEBIN);
    }

    public void setStorageBin(Locator storageBin) {
        set(PROPERTY_STORAGEBIN, storageBin);
    }

    public Long getNoofpackages() {
        return (Long) get(PROPERTY_NOOFPACKAGES);
    }

    public void setNoofpackages(Long noofpackages) {
        set(PROPERTY_NOOFPACKAGES, noofpackages);
    }

    public BigDecimal getUnitPrice() {
        return (BigDecimal) get(PROPERTY_UNITPRICE);
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        set(PROPERTY_UNITPRICE, unitPrice);
    }

    public BigDecimal getNetrate() {
        return (BigDecimal) get(PROPERTY_NETRATE);
    }

    public void setNetrate(BigDecimal netrate) {
        set(PROPERTY_NETRATE, netrate);
    }

    public BigDecimal getLineNetAmount() {
        return (BigDecimal) get(PROPERTY_LINENETAMOUNT);
    }

    public void setLineNetAmount(BigDecimal lineNetAmount) {
        set(PROPERTY_LINENETAMOUNT, lineNetAmount);
    }

    public BigDecimal getNetlineamt() {
        return (BigDecimal) get(PROPERTY_NETLINEAMT);
    }

    public void setNetlineamt(BigDecimal netlineamt) {
        set(PROPERTY_NETLINEAMT, netlineamt);
    }

    public BigDecimal getNetUnitPrice() {
        return (BigDecimal) get(PROPERTY_NETUNITPRICE);
    }

    public void setNetUnitPrice(BigDecimal netUnitPrice) {
        set(PROPERTY_NETUNITPRICE, netUnitPrice);
    }

    public BigDecimal getFreight() {
        return (BigDecimal) get(PROPERTY_FREIGHT);
    }

    public void setFreight(BigDecimal freight) {
        set(PROPERTY_FREIGHT, freight);
    }

    public BigDecimal getInsurance() {
        return (BigDecimal) get(PROPERTY_INSURANCE);
    }

    public void setInsurance(BigDecimal insurance) {
        set(PROPERTY_INSURANCE, insurance);
    }

    public BigDecimal getPnf() {
        return (BigDecimal) get(PROPERTY_PNF);
    }

    public void setPnf(BigDecimal pnf) {
        set(PROPERTY_PNF, pnf);
    }

    public BigDecimal getDiscount() {
        return (BigDecimal) get(PROPERTY_DISCOUNT);
    }

    public void setDiscount(BigDecimal discount) {
        set(PROPERTY_DISCOUNT, discount);
    }

    public TaxRate getTax() {
        return (TaxRate) get(PROPERTY_TAX);
    }

    public void setTax(TaxRate tax) {
        set(PROPERTY_TAX, tax);
    }

    public BigDecimal getTaxAmount() {
        return (BigDecimal) get(PROPERTY_TAXAMOUNT);
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        set(PROPERTY_TAXAMOUNT, taxAmount);
    }

    public BigDecimal getFrtax() {
        return (BigDecimal) get(PROPERTY_FRTAX);
    }

    public void setFrtax(BigDecimal frtax) {
        set(PROPERTY_FRTAX, frtax);
    }

    public BigDecimal getInstax() {
        return (BigDecimal) get(PROPERTY_INSTAX);
    }

    public void setInstax(BigDecimal instax) {
        set(PROPERTY_INSTAX, instax);
    }

    public BigDecimal getPnftax() {
        return (BigDecimal) get(PROPERTY_PNFTAX);
    }

    public void setPnftax(BigDecimal pnftax) {
        set(PROPERTY_PNFTAX, pnftax);
    }

    public BigDecimal getFrtaxapp() {
        return (BigDecimal) get(PROPERTY_FRTAXAPP);
    }

    public void setFrtaxapp(BigDecimal frtaxapp) {
        set(PROPERTY_FRTAXAPP, frtaxapp);
    }

    public BigDecimal getInstaxapp() {
        return (BigDecimal) get(PROPERTY_INSTAXAPP);
    }

    public void setInstaxapp(BigDecimal instaxapp) {
        set(PROPERTY_INSTAXAPP, instaxapp);
    }

    public BigDecimal getPnftaxapp() {
        return (BigDecimal) get(PROPERTY_PNFTAXAPP);
    }

    public void setPnftaxapp(BigDecimal pnftaxapp) {
        set(PROPERTY_PNFTAXAPP, pnftaxapp);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

}
