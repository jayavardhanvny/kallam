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
package org.openbravo.model.common.enterprise;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.geography.Location;
import org.openbravo.model.financialmgmt.tax.TaxRate;
/**
 * Entity class for entity OrganizationInformation (stored in table AD_OrgInfo).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class OrganizationInformation extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_OrgInfo";
    public static final String ENTITY_NAME = "OrganizationInformation";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_LOCATIONADDRESS = "locationAddress";
    public static final String PROPERTY_DUNS = "dUNS";
    public static final String PROPERTY_TAXID = "taxID";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_NOTTAXDEDUCTABLE = "notTaxDeductable";
    public static final String PROPERTY_SALESTAXEXEMPTRATE = "salesTaxExemptRate";
    public static final String PROPERTY_LOGO = "logo";
    public static final String PROPERTY_REFERENCEORDER = "referenceOrder";
    public static final String PROPERTY_YOURCOMPANYDOCUMENTIMAGE = "yourCompanyDocumentImage";
    public static final String PROPERTY_AUTOMATICWRITEOFFAMT = "automaticWriteoffAmt";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_APRMPAYMENTDESCRIPTION = "aPRMPaymentDescription";
    public static final String PROPERTY_ORGANIZATION = "organization";

    public OrganizationInformation() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_NOTTAXDEDUCTABLE, false);
        setDefaultValue(PROPERTY_REFERENCEORDER, false);
        setDefaultValue(PROPERTY_APRMPAYMENTDESCRIPTION, "Invoice Document Number");
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

    public Location getLocationAddress() {
        return (Location) get(PROPERTY_LOCATIONADDRESS);
    }

    public void setLocationAddress(Location locationAddress) {
        set(PROPERTY_LOCATIONADDRESS, locationAddress);
    }

    public String getDUNS() {
        return (String) get(PROPERTY_DUNS);
    }

    public void setDUNS(String dUNS) {
        set(PROPERTY_DUNS, dUNS);
    }

    public String getTaxID() {
        return (String) get(PROPERTY_TAXID);
    }

    public void setTaxID(String taxID) {
        set(PROPERTY_TAXID, taxID);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Boolean isNotTaxDeductable() {
        return (Boolean) get(PROPERTY_NOTTAXDEDUCTABLE);
    }

    public void setNotTaxDeductable(Boolean notTaxDeductable) {
        set(PROPERTY_NOTTAXDEDUCTABLE, notTaxDeductable);
    }

    public TaxRate getSalesTaxExemptRate() {
        return (TaxRate) get(PROPERTY_SALESTAXEXEMPTRATE);
    }

    public void setSalesTaxExemptRate(TaxRate salesTaxExemptRate) {
        set(PROPERTY_SALESTAXEXEMPTRATE, salesTaxExemptRate);
    }

    public String getLogo() {
        return (String) get(PROPERTY_LOGO);
    }

    public void setLogo(String logo) {
        set(PROPERTY_LOGO, logo);
    }

    public Boolean isReferenceOrder() {
        return (Boolean) get(PROPERTY_REFERENCEORDER);
    }

    public void setReferenceOrder(Boolean referenceOrder) {
        set(PROPERTY_REFERENCEORDER, referenceOrder);
    }

    public Image getYourCompanyDocumentImage() {
        return (Image) get(PROPERTY_YOURCOMPANYDOCUMENTIMAGE);
    }

    public void setYourCompanyDocumentImage(Image yourCompanyDocumentImage) {
        set(PROPERTY_YOURCOMPANYDOCUMENTIMAGE, yourCompanyDocumentImage);
    }

    public BigDecimal getAutomaticWriteoffAmt() {
        return (BigDecimal) get(PROPERTY_AUTOMATICWRITEOFFAMT);
    }

    public void setAutomaticWriteoffAmt(BigDecimal automaticWriteoffAmt) {
        set(PROPERTY_AUTOMATICWRITEOFFAMT, automaticWriteoffAmt);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public String getAPRMPaymentDescription() {
        return (String) get(PROPERTY_APRMPAYMENTDESCRIPTION);
    }

    public void setAPRMPaymentDescription(String aPRMPaymentDescription) {
        set(PROPERTY_APRMPAYMENTDESCRIPTION, aPRMPaymentDescription);
    }

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

}
