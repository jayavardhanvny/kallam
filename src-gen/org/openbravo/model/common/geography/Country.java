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
package org.openbravo.model.common.geography;

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
import org.openbravo.model.ad.system.Language;
import org.openbravo.model.common.bank.Bank;
import org.openbravo.model.common.businesspartner.BankAccount;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.financialmgmt.tax.TaxZone;
import org.openbravo.model.shipping.ShippingCompanyFreight;
/**
 * Entity class for entity Country (stored in table C_Country).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Country extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Country";
    public static final String ENTITY_NAME = "Country";
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
    public static final String PROPERTY_ISOCOUNTRYCODE = "iSOCountryCode";
    public static final String PROPERTY_HASREGIONS = "hasRegions";
    public static final String PROPERTY_REGIONNAME = "regionName";
    public static final String PROPERTY_PHONENOFORMAT = "phoneNoFormat";
    public static final String PROPERTY_ADDRESSPRINTFORMAT = "addressPrintFormat";
    public static final String PROPERTY_POSTALCODEFORMAT = "postalCodeFormat";
    public static final String PROPERTY_ADDITIONALPOSTALCODE = "additionalPostalCode";
    public static final String PROPERTY_ADDITIONALPOSTALFORMAT = "additionalPostalFormat";
    public static final String PROPERTY_DEFAULT = "default";
    public static final String PROPERTY_LANGUAGE = "language";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_IBANLENGTH = "iBANLength";
    public static final String PROPERTY_IBANCODE = "iBANCode";
    public static final String PROPERTY_DECIMALSEPARATOR = "decimalseparator";
    public static final String PROPERTY_GROUPINGSEPARATOR = "groupingseparator";
    public static final String PROPERTY_NUMERICMASK = "numericmask";
    public static final String PROPERTY_DATEFORMAT = "dateformat";
    public static final String PROPERTY_DATETIMEFORMAT = "datetimeformat";
    public static final String PROPERTY_BANKLIST = "bankList";
    public static final String PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST = "businessPartnerBankAccountList";
    public static final String PROPERTY_CITYLIST = "cityList";
    public static final String PROPERTY_COUNTRYTRLLIST = "countryTrlList";
    public static final String PROPERTY_FINFINANCIALACCOUNTLIST = "fINFinancialAccountList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATELIST = "financialMgmtTaxRateList";
    public static final String PROPERTY_FINANCIALMGMTTAXRATEDESTINATIONCOUNTRYLIST = "financialMgmtTaxRateDestinationCountryList";
    public static final String PROPERTY_FINANCIALMGMTTAXZONEFROMCOUNTRYLIST = "financialMgmtTaxZoneFromCountryList";
    public static final String PROPERTY_FINANCIALMGMTTAXZONEDESTINATIONCOUNTRYLIST = "financialMgmtTaxZoneDestinationCountryList";
    public static final String PROPERTY_LOCATIONLIST = "locationList";
    public static final String PROPERTY_REGIONLIST = "regionList";
    public static final String PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTLIST = "shippingShippingCompanyFreightList";
    public static final String PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTDESTINATIONCOUNTRYLIST = "shippingShippingCompanyFreightDestinationCountryList";

    public Country() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_HASREGIONS, false);
        setDefaultValue(PROPERTY_REGIONNAME, "State");
        setDefaultValue(PROPERTY_ADDITIONALPOSTALCODE, false);
        setDefaultValue(PROPERTY_DEFAULT, false);
        setDefaultValue(PROPERTY_BANKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CITYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_COUNTRYTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXRATEDESTINATIONCOUNTRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXZONEFROMCOUNTRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXZONEDESTINATIONCOUNTRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_LOCATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_REGIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTDESTINATIONCOUNTRYLIST, new ArrayList<Object>());
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

    public String getISOCountryCode() {
        return (String) get(PROPERTY_ISOCOUNTRYCODE);
    }

    public void setISOCountryCode(String iSOCountryCode) {
        set(PROPERTY_ISOCOUNTRYCODE, iSOCountryCode);
    }

    public Boolean isHasRegions() {
        return (Boolean) get(PROPERTY_HASREGIONS);
    }

    public void setHasRegions(Boolean hasRegions) {
        set(PROPERTY_HASREGIONS, hasRegions);
    }

    public String getRegionName() {
        return (String) get(PROPERTY_REGIONNAME);
    }

    public void setRegionName(String regionName) {
        set(PROPERTY_REGIONNAME, regionName);
    }

    public String getPhoneNoFormat() {
        return (String) get(PROPERTY_PHONENOFORMAT);
    }

    public void setPhoneNoFormat(String phoneNoFormat) {
        set(PROPERTY_PHONENOFORMAT, phoneNoFormat);
    }

    public String getAddressPrintFormat() {
        return (String) get(PROPERTY_ADDRESSPRINTFORMAT);
    }

    public void setAddressPrintFormat(String addressPrintFormat) {
        set(PROPERTY_ADDRESSPRINTFORMAT, addressPrintFormat);
    }

    public String getPostalCodeFormat() {
        return (String) get(PROPERTY_POSTALCODEFORMAT);
    }

    public void setPostalCodeFormat(String postalCodeFormat) {
        set(PROPERTY_POSTALCODEFORMAT, postalCodeFormat);
    }

    public Boolean isAdditionalPostalCode() {
        return (Boolean) get(PROPERTY_ADDITIONALPOSTALCODE);
    }

    public void setAdditionalPostalCode(Boolean additionalPostalCode) {
        set(PROPERTY_ADDITIONALPOSTALCODE, additionalPostalCode);
    }

    public String getAdditionalPostalFormat() {
        return (String) get(PROPERTY_ADDITIONALPOSTALFORMAT);
    }

    public void setAdditionalPostalFormat(String additionalPostalFormat) {
        set(PROPERTY_ADDITIONALPOSTALFORMAT, additionalPostalFormat);
    }

    public Boolean isDefault() {
        return (Boolean) get(PROPERTY_DEFAULT);
    }

    public void setDefault(Boolean deflt) {
        set(PROPERTY_DEFAULT, deflt);
    }

    public Language getLanguage() {
        return (Language) get(PROPERTY_LANGUAGE);
    }

    public void setLanguage(Language language) {
        set(PROPERTY_LANGUAGE, language);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public Long getIBANLength() {
        return (Long) get(PROPERTY_IBANLENGTH);
    }

    public void setIBANLength(Long iBANLength) {
        set(PROPERTY_IBANLENGTH, iBANLength);
    }

    public String getIBANCode() {
        return (String) get(PROPERTY_IBANCODE);
    }

    public void setIBANCode(String iBANCode) {
        set(PROPERTY_IBANCODE, iBANCode);
    }

    public String getDecimalseparator() {
        return (String) get(PROPERTY_DECIMALSEPARATOR);
    }

    public void setDecimalseparator(String decimalseparator) {
        set(PROPERTY_DECIMALSEPARATOR, decimalseparator);
    }

    public String getGroupingseparator() {
        return (String) get(PROPERTY_GROUPINGSEPARATOR);
    }

    public void setGroupingseparator(String groupingseparator) {
        set(PROPERTY_GROUPINGSEPARATOR, groupingseparator);
    }

    public String getNumericmask() {
        return (String) get(PROPERTY_NUMERICMASK);
    }

    public void setNumericmask(String numericmask) {
        set(PROPERTY_NUMERICMASK, numericmask);
    }

    public String getDateformat() {
        return (String) get(PROPERTY_DATEFORMAT);
    }

    public void setDateformat(String dateformat) {
        set(PROPERTY_DATEFORMAT, dateformat);
    }

    public String getDatetimeformat() {
        return (String) get(PROPERTY_DATETIMEFORMAT);
    }

    public void setDatetimeformat(String datetimeformat) {
        set(PROPERTY_DATETIMEFORMAT, datetimeformat);
    }

    @SuppressWarnings("unchecked")
    public List<Bank> getBankList() {
        return (List<Bank>) get(PROPERTY_BANKLIST);
    }

    public void setBankList(List<Bank> bankList) {
        set(PROPERTY_BANKLIST, bankList);
    }

    @SuppressWarnings("unchecked")
    public List<BankAccount> getBusinessPartnerBankAccountList() {
        return (List<BankAccount>) get(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST);
    }

    public void setBusinessPartnerBankAccountList(List<BankAccount> businessPartnerBankAccountList) {
        set(PROPERTY_BUSINESSPARTNERBANKACCOUNTLIST, businessPartnerBankAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<City> getCityList() {
        return (List<City>) get(PROPERTY_CITYLIST);
    }

    public void setCityList(List<City> cityList) {
        set(PROPERTY_CITYLIST, cityList);
    }

    @SuppressWarnings("unchecked")
    public List<CountryTrl> getCountryTrlList() {
        return (List<CountryTrl>) get(PROPERTY_COUNTRYTRLLIST);
    }

    public void setCountryTrlList(List<CountryTrl> countryTrlList) {
        set(PROPERTY_COUNTRYTRLLIST, countryTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccount> getFINFinancialAccountList() {
        return (List<FIN_FinancialAccount>) get(PROPERTY_FINFINANCIALACCOUNTLIST);
    }

    public void setFINFinancialAccountList(List<FIN_FinancialAccount> fINFinancialAccountList) {
        set(PROPERTY_FINFINANCIALACCOUNTLIST, fINFinancialAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRate> getFinancialMgmtTaxRateList() {
        return (List<TaxRate>) get(PROPERTY_FINANCIALMGMTTAXRATELIST);
    }

    public void setFinancialMgmtTaxRateList(List<TaxRate> financialMgmtTaxRateList) {
        set(PROPERTY_FINANCIALMGMTTAXRATELIST, financialMgmtTaxRateList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxRate> getFinancialMgmtTaxRateDestinationCountryList() {
        return (List<TaxRate>) get(PROPERTY_FINANCIALMGMTTAXRATEDESTINATIONCOUNTRYLIST);
    }

    public void setFinancialMgmtTaxRateDestinationCountryList(List<TaxRate> financialMgmtTaxRateDestinationCountryList) {
        set(PROPERTY_FINANCIALMGMTTAXRATEDESTINATIONCOUNTRYLIST, financialMgmtTaxRateDestinationCountryList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxZone> getFinancialMgmtTaxZoneFromCountryList() {
        return (List<TaxZone>) get(PROPERTY_FINANCIALMGMTTAXZONEFROMCOUNTRYLIST);
    }

    public void setFinancialMgmtTaxZoneFromCountryList(List<TaxZone> financialMgmtTaxZoneFromCountryList) {
        set(PROPERTY_FINANCIALMGMTTAXZONEFROMCOUNTRYLIST, financialMgmtTaxZoneFromCountryList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxZone> getFinancialMgmtTaxZoneDestinationCountryList() {
        return (List<TaxZone>) get(PROPERTY_FINANCIALMGMTTAXZONEDESTINATIONCOUNTRYLIST);
    }

    public void setFinancialMgmtTaxZoneDestinationCountryList(List<TaxZone> financialMgmtTaxZoneDestinationCountryList) {
        set(PROPERTY_FINANCIALMGMTTAXZONEDESTINATIONCOUNTRYLIST, financialMgmtTaxZoneDestinationCountryList);
    }

    @SuppressWarnings("unchecked")
    public List<Location> getLocationList() {
        return (List<Location>) get(PROPERTY_LOCATIONLIST);
    }

    public void setLocationList(List<Location> locationList) {
        set(PROPERTY_LOCATIONLIST, locationList);
    }

    @SuppressWarnings("unchecked")
    public List<Region> getRegionList() {
        return (List<Region>) get(PROPERTY_REGIONLIST);
    }

    public void setRegionList(List<Region> regionList) {
        set(PROPERTY_REGIONLIST, regionList);
    }

    @SuppressWarnings("unchecked")
    public List<ShippingCompanyFreight> getShippingShippingCompanyFreightList() {
        return (List<ShippingCompanyFreight>) get(PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTLIST);
    }

    public void setShippingShippingCompanyFreightList(List<ShippingCompanyFreight> shippingShippingCompanyFreightList) {
        set(PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTLIST, shippingShippingCompanyFreightList);
    }

    @SuppressWarnings("unchecked")
    public List<ShippingCompanyFreight> getShippingShippingCompanyFreightDestinationCountryList() {
        return (List<ShippingCompanyFreight>) get(PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTDESTINATIONCOUNTRYLIST);
    }

    public void setShippingShippingCompanyFreightDestinationCountryList(List<ShippingCompanyFreight> shippingShippingCompanyFreightDestinationCountryList) {
        set(PROPERTY_SHIPPINGSHIPPINGCOMPANYFREIGHTDESTINATIONCOUNTRYLIST, shippingShippingCompanyFreightDestinationCountryList);
    }

}
