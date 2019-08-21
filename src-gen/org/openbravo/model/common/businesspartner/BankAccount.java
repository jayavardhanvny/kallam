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
import org.openbravo.model.common.geography.Country;
import org.openbravo.model.common.invoice.Invoice;
/**
 * Entity class for entity BusinessPartnerBankAccount (stored in table C_BP_BankAccount).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class BankAccount extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_BP_BankAccount";
    public static final String ENTITY_NAME = "BusinessPartnerBankAccount";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_ACCOUNTTYPE = "accountType";
    public static final String PROPERTY_ROUTINGNO = "routingNo";
    public static final String PROPERTY_ACCOUNTNO = "accountNo";
    public static final String PROPERTY_CCTYPE = "cCType";
    public static final String PROPERTY_CREDITCARDNO = "creditCardNo";
    public static final String PROPERTY_EXPIRYMONTH = "expiryMonth";
    public static final String PROPERTY_EXPIRYYEAR = "expiryYear";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_STREET = "street";
    public static final String PROPERTY_CITY = "city";
    public static final String PROPERTY_STATE = "state";
    public static final String PROPERTY_POSTALCODE = "postalCode";
    public static final String PROPERTY_DRIVERSLICENSENO = "driversLicenseNo";
    public static final String PROPERTY_EMAIL = "email";
    public static final String PROPERTY_SOCIALSECURITYNO = "socialSecurityNo";
    public static final String PROPERTY_ZIPVERIFIED = "zipVerified";
    public static final String PROPERTY_COUNTRYNAME = "countryName";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_BANKNAME = "bankName";
    public static final String PROPERTY_IBAN = "iBAN";
    public static final String PROPERTY_SHOWGENERIC = "showGeneric";
    public static final String PROPERTY_SHOWIBAN = "showIBAN";
    public static final String PROPERTY_DISPLAYEDACCOUNT = "displayedAccount";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_SWIFTCODE = "swiftCode";
    public static final String PROPERTY_BANKFORMAT = "bankFormat";
    public static final String PROPERTY_INVOICELIST = "invoiceList";

    public BankAccount() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_EXPIRYYEAR, (long) 2000);
        setDefaultValue(PROPERTY_SHOWGENERIC, false);
        setDefaultValue(PROPERTY_SHOWIBAN, false);
        setDefaultValue(PROPERTY_BANKFORMAT, "GENERIC");
        setDefaultValue(PROPERTY_INVOICELIST, new ArrayList<Object>());
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

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getAccountType() {
        return (String) get(PROPERTY_ACCOUNTTYPE);
    }

    public void setAccountType(String accountType) {
        set(PROPERTY_ACCOUNTTYPE, accountType);
    }

    public String getRoutingNo() {
        return (String) get(PROPERTY_ROUTINGNO);
    }

    public void setRoutingNo(String routingNo) {
        set(PROPERTY_ROUTINGNO, routingNo);
    }

    public String getAccountNo() {
        return (String) get(PROPERTY_ACCOUNTNO);
    }

    public void setAccountNo(String accountNo) {
        set(PROPERTY_ACCOUNTNO, accountNo);
    }

    public String getCCType() {
        return (String) get(PROPERTY_CCTYPE);
    }

    public void setCCType(String cCType) {
        set(PROPERTY_CCTYPE, cCType);
    }

    public String getCreditCardNo() {
        return (String) get(PROPERTY_CREDITCARDNO);
    }

    public void setCreditCardNo(String creditCardNo) {
        set(PROPERTY_CREDITCARDNO, creditCardNo);
    }

    public Long getExpiryMonth() {
        return (Long) get(PROPERTY_EXPIRYMONTH);
    }

    public void setExpiryMonth(Long expiryMonth) {
        set(PROPERTY_EXPIRYMONTH, expiryMonth);
    }

    public Long getExpiryYear() {
        return (Long) get(PROPERTY_EXPIRYYEAR);
    }

    public void setExpiryYear(Long expiryYear) {
        set(PROPERTY_EXPIRYYEAR, expiryYear);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getStreet() {
        return (String) get(PROPERTY_STREET);
    }

    public void setStreet(String street) {
        set(PROPERTY_STREET, street);
    }

    public String getCity() {
        return (String) get(PROPERTY_CITY);
    }

    public void setCity(String city) {
        set(PROPERTY_CITY, city);
    }

    public String getState() {
        return (String) get(PROPERTY_STATE);
    }

    public void setState(String state) {
        set(PROPERTY_STATE, state);
    }

    public String getPostalCode() {
        return (String) get(PROPERTY_POSTALCODE);
    }

    public void setPostalCode(String postalCode) {
        set(PROPERTY_POSTALCODE, postalCode);
    }

    public String getDriversLicenseNo() {
        return (String) get(PROPERTY_DRIVERSLICENSENO);
    }

    public void setDriversLicenseNo(String driversLicenseNo) {
        set(PROPERTY_DRIVERSLICENSENO, driversLicenseNo);
    }

    public String getEmail() {
        return (String) get(PROPERTY_EMAIL);
    }

    public void setEmail(String email) {
        set(PROPERTY_EMAIL, email);
    }

    public String getSocialSecurityNo() {
        return (String) get(PROPERTY_SOCIALSECURITYNO);
    }

    public void setSocialSecurityNo(String socialSecurityNo) {
        set(PROPERTY_SOCIALSECURITYNO, socialSecurityNo);
    }

    public String getZipVerified() {
        return (String) get(PROPERTY_ZIPVERIFIED);
    }

    public void setZipVerified(String zipVerified) {
        set(PROPERTY_ZIPVERIFIED, zipVerified);
    }

    public String getCountryName() {
        return (String) get(PROPERTY_COUNTRYNAME);
    }

    public void setCountryName(String countryName) {
        set(PROPERTY_COUNTRYNAME, countryName);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public String getBankName() {
        return (String) get(PROPERTY_BANKNAME);
    }

    public void setBankName(String bankName) {
        set(PROPERTY_BANKNAME, bankName);
    }

    public String getIBAN() {
        return (String) get(PROPERTY_IBAN);
    }

    public void setIBAN(String iBAN) {
        set(PROPERTY_IBAN, iBAN);
    }

    public Boolean isShowGeneric() {
        return (Boolean) get(PROPERTY_SHOWGENERIC);
    }

    public void setShowGeneric(Boolean showGeneric) {
        set(PROPERTY_SHOWGENERIC, showGeneric);
    }

    public Boolean isShowIBAN() {
        return (Boolean) get(PROPERTY_SHOWIBAN);
    }

    public void setShowIBAN(Boolean showIBAN) {
        set(PROPERTY_SHOWIBAN, showIBAN);
    }

    public String getDisplayedAccount() {
        return (String) get(PROPERTY_DISPLAYEDACCOUNT);
    }

    public void setDisplayedAccount(String displayedAccount) {
        set(PROPERTY_DISPLAYEDACCOUNT, displayedAccount);
    }

    public Country getCountry() {
        return (Country) get(PROPERTY_COUNTRY);
    }

    public void setCountry(Country country) {
        set(PROPERTY_COUNTRY, country);
    }

    public String getSwiftCode() {
        return (String) get(PROPERTY_SWIFTCODE);
    }

    public void setSwiftCode(String swiftCode) {
        set(PROPERTY_SWIFTCODE, swiftCode);
    }

    public String getBankFormat() {
        return (String) get(PROPERTY_BANKFORMAT);
    }

    public void setBankFormat(String bankFormat) {
        set(PROPERTY_BANKFORMAT, bankFormat);
    }

    @SuppressWarnings("unchecked")
    public List<Invoice> getInvoiceList() {
        return (List<Invoice>) get(PROPERTY_INVOICELIST);
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        set(PROPERTY_INVOICELIST, invoiceList);
    }

}
