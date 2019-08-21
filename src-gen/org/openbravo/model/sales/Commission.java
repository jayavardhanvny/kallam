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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
/**
 * Entity class for entity SalesCommission (stored in table C_Commission).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Commission extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Commission";
    public static final String ENTITY_NAME = "SalesCommission";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_FREQUENCYTYPE = "frequencyType";
    public static final String PROPERTY_CALCULATIONBASIS = "calculationBasis";
    public static final String PROPERTY_LISTDETAILS = "listDetails";
    public static final String PROPERTY_LASTRUNDATE = "lastRunDate";
    public static final String PROPERTY_CREATELINESFROM = "createLinesFrom";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_BASISSTATUS = "basisstatus";
    public static final String PROPERTY_BASISAMT = "basisamt";
    public static final String PROPERTY_CASCADE = "cascade";
    public static final String PROPERTY_SALESCOMMISSIONLINELIST = "salesCommissionLineList";
    public static final String PROPERTY_SALESCOMMISSIONRUNLIST = "salesCommissionRunList";

    public Commission() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_FREQUENCYTYPE, "M");
        setDefaultValue(PROPERTY_CALCULATIONBASIS, "I");
        setDefaultValue(PROPERTY_LISTDETAILS, true);
        setDefaultValue(PROPERTY_CREATELINESFROM, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_CASCADE, false);
        setDefaultValue(PROPERTY_SALESCOMMISSIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SALESCOMMISSIONRUNLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public String getFrequencyType() {
        return (String) get(PROPERTY_FREQUENCYTYPE);
    }

    public void setFrequencyType(String frequencyType) {
        set(PROPERTY_FREQUENCYTYPE, frequencyType);
    }

    public String getCalculationBasis() {
        return (String) get(PROPERTY_CALCULATIONBASIS);
    }

    public void setCalculationBasis(String calculationBasis) {
        set(PROPERTY_CALCULATIONBASIS, calculationBasis);
    }

    public Boolean isListDetails() {
        return (Boolean) get(PROPERTY_LISTDETAILS);
    }

    public void setListDetails(Boolean listDetails) {
        set(PROPERTY_LISTDETAILS, listDetails);
    }

    public Date getLastRunDate() {
        return (Date) get(PROPERTY_LASTRUNDATE);
    }

    public void setLastRunDate(Date lastRunDate) {
        set(PROPERTY_LASTRUNDATE, lastRunDate);
    }

    public Boolean isCreateLinesFrom() {
        return (Boolean) get(PROPERTY_CREATELINESFROM);
    }

    public void setCreateLinesFrom(Boolean createLinesFrom) {
        set(PROPERTY_CREATELINESFROM, createLinesFrom);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public String getBasisstatus() {
        return (String) get(PROPERTY_BASISSTATUS);
    }

    public void setBasisstatus(String basisstatus) {
        set(PROPERTY_BASISSTATUS, basisstatus);
    }

    public String getBasisamt() {
        return (String) get(PROPERTY_BASISAMT);
    }

    public void setBasisamt(String basisamt) {
        set(PROPERTY_BASISAMT, basisamt);
    }

    public Boolean isCascade() {
        return (Boolean) get(PROPERTY_CASCADE);
    }

    public void setCascade(Boolean cascade) {
        set(PROPERTY_CASCADE, cascade);
    }

    @SuppressWarnings("unchecked")
    public List<CommissionLine> getSalesCommissionLineList() {
        return (List<CommissionLine>) get(PROPERTY_SALESCOMMISSIONLINELIST);
    }

    public void setSalesCommissionLineList(List<CommissionLine> salesCommissionLineList) {
        set(PROPERTY_SALESCOMMISSIONLINELIST, salesCommissionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<CommissionRun> getSalesCommissionRunList() {
        return (List<CommissionRun>) get(PROPERTY_SALESCOMMISSIONRUNLIST);
    }

    public void setSalesCommissionRunList(List<CommissionRun> salesCommissionRunList) {
        set(PROPERTY_SALESCOMMISSIONRUNLIST, salesCommissionRunList);
    }

}
