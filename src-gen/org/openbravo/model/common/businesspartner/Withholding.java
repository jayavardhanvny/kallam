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
package org.openbravo.model.common.businesspartner;

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
import org.openbravo.model.financialmgmt.tax.TaxRate;
/**
 * Entity class for entity BusinessPartnerWithholding (stored in table C_BP_Withholding).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Withholding extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_BP_Withholding";
    public static final String ENTITY_NAME = "BusinessPartnerWithholding";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_WITHHOLDING = "withholding";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_MANDATORYWITHHOLDING = "mandatoryWithholding";
    public static final String PROPERTY_TEMPORARILYEXEMPT = "temporarilyExempt";
    public static final String PROPERTY_EXEMPTREASON = "exemptReason";
    public static final String PROPERTY_ISPERCENT = "isPercent";
    public static final String PROPERTY_PERCENTOFBASEAMOUNT = "percentOfBaseAmount";
    public static final String PROPERTY_INCLUDETAX = "includeTax";
    public static final String PROPERTY_TAX = "tax";
    public static final String PROPERTY_DEFAULT = "default";

    public Withholding() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MANDATORYWITHHOLDING, false);
        setDefaultValue(PROPERTY_TEMPORARILYEXEMPT, false);
        setDefaultValue(PROPERTY_ISPERCENT, false);
        setDefaultValue(PROPERTY_INCLUDETAX, false);
        setDefaultValue(PROPERTY_DEFAULT, false);
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

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public org.openbravo.model.financialmgmt.tax.Withholding getWithholding() {
        return (org.openbravo.model.financialmgmt.tax.Withholding) get(PROPERTY_WITHHOLDING);
    }

    public void setWithholding(org.openbravo.model.financialmgmt.tax.Withholding withholding) {
        set(PROPERTY_WITHHOLDING, withholding);
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

    public Boolean isMandatoryWithholding() {
        return (Boolean) get(PROPERTY_MANDATORYWITHHOLDING);
    }

    public void setMandatoryWithholding(Boolean mandatoryWithholding) {
        set(PROPERTY_MANDATORYWITHHOLDING, mandatoryWithholding);
    }

    public Boolean isTemporarilyExempt() {
        return (Boolean) get(PROPERTY_TEMPORARILYEXEMPT);
    }

    public void setTemporarilyExempt(Boolean temporarilyExempt) {
        set(PROPERTY_TEMPORARILYEXEMPT, temporarilyExempt);
    }

    public String getExemptReason() {
        return (String) get(PROPERTY_EXEMPTREASON);
    }

    public void setExemptReason(String exemptReason) {
        set(PROPERTY_EXEMPTREASON, exemptReason);
    }

    public Boolean isPercent() {
        return (Boolean) get(PROPERTY_ISPERCENT);
    }

    public void setPercent(Boolean isPercent) {
        set(PROPERTY_ISPERCENT, isPercent);
    }

    public BigDecimal getPercentOfBaseAmount() {
        return (BigDecimal) get(PROPERTY_PERCENTOFBASEAMOUNT);
    }

    public void setPercentOfBaseAmount(BigDecimal percentOfBaseAmount) {
        set(PROPERTY_PERCENTOFBASEAMOUNT, percentOfBaseAmount);
    }

    public Boolean isIncludeTax() {
        return (Boolean) get(PROPERTY_INCLUDETAX);
    }

    public void setIncludeTax(Boolean includeTax) {
        set(PROPERTY_INCLUDETAX, includeTax);
    }

    public TaxRate getTax() {
        return (TaxRate) get(PROPERTY_TAX);
    }

    public void setTax(TaxRate tax) {
        set(PROPERTY_TAX, tax);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

}
