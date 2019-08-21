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

import com.rcss.humanresource.data.RCHR_Bank;
import com.rcss.humanresource.data.RCHR_Block;
import com.rcss.humanresource.data.RCHR_EmpAddress;
import com.redcarpet.epcg.data.EpcgBranchaddr;
import com.redcarpet.epcg.data.EpcgCommissionAgent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.advpaymentmngt.FinAccTransactionAccounting;
import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.bank.Bank;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.OrganizationInformation;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.financialmgmt.accounting.AccountingFact;
import org.openbravo.model.financialmgmt.accounting.coa.AccountingCombination;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchemaElement;
import org.openbravo.model.financialmgmt.assetmgmt.Asset;
import org.openbravo.model.financialmgmt.payment.FIN_FinancialAccount;
import org.openbravo.model.project.Project;
/**
 * Entity class for entity Location (stored in table C_Location).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Location extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Location";
    public static final String ENTITY_NAME = "Location";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ADDRESSLINE1 = "addressLine1";
    public static final String PROPERTY_ADDRESSLINE2 = "addressLine2";
    public static final String PROPERTY_CITYNAME = "cityName";
    public static final String PROPERTY_POSTALCODE = "postalCode";
    public static final String PROPERTY_POSTALADD = "postalAdd";
    public static final String PROPERTY_COUNTRY = "country";
    public static final String PROPERTY_REGION = "region";
    public static final String PROPERTY_CITY = "city";
    public static final String PROPERTY_REGIONNAME = "regionName";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONFROMADDRESSLIST = "aPRMFinAccTransactionAcctVLocationFromAddressList";
    public static final String PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONTOADDRESSLIST = "aPRMFinAccTransactionAcctVLocationToAddressList";
    public static final String PROPERTY_BANKLIST = "bankList";
    public static final String PROPERTY_BUSINESSPARTNERLOCATIONLIST = "businessPartnerLocationList";
    public static final String PROPERTY_EPCGBRANCHADDRLIST = "epcgBranchaddrList";
    public static final String PROPERTY_EPCGCOMMISSIONAGENTLIST = "epcgCommissionAgentList";
    public static final String PROPERTY_FINFINANCIALACCOUNTLIST = "fINFinancialAccountList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONFROMADDRESSLIST = "financialMgmtAccountingCombinationLocationFromAddressList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONTOADDRESSLIST = "financialMgmtAccountingCombinationLocationToAddressList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONFROMADDRESSLIST = "financialMgmtAccountingFactLocationFromAddressList";
    public static final String PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONTOADDRESSLIST = "financialMgmtAccountingFactLocationToAddressList";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST = "financialMgmtAcctSchemaElementList";
    public static final String PROPERTY_FINANCIALMGMTASSETLIST = "financialMgmtAssetList";
    public static final String PROPERTY_ORGANIZATIONINFORMATIONLIST = "organizationInformationList";
    public static final String PROPERTY_PROJECTLIST = "projectList";
    public static final String PROPERTY_RCHRBANKLIST = "rCHRBankList";
    public static final String PROPERTY_RCHRBLOCKLIST = "rCHRBlockList";
    public static final String PROPERTY_RCHREMPADDRESSLIST = "rCHREmpAddressList";
    public static final String PROPERTY_WAREHOUSELIST = "warehouseList";

    public Location() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONFROMADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONTOADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BANKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_BUSINESSPARTNERLOCATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGBRANCHADDRLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGCOMMISSIONAGENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINFINANCIALACCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONFROMADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONTOADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONFROMADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONTOADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONINFORMATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROJECTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBANKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRBLOCKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPADDRESSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_WAREHOUSELIST, new ArrayList<Object>());
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

    public String getAddressLine1() {
        return (String) get(PROPERTY_ADDRESSLINE1);
    }

    public void setAddressLine1(String addressLine1) {
        set(PROPERTY_ADDRESSLINE1, addressLine1);
    }

    public String getAddressLine2() {
        return (String) get(PROPERTY_ADDRESSLINE2);
    }

    public void setAddressLine2(String addressLine2) {
        set(PROPERTY_ADDRESSLINE2, addressLine2);
    }

    public String getCityName() {
        return (String) get(PROPERTY_CITYNAME);
    }

    public void setCityName(String cityName) {
        set(PROPERTY_CITYNAME, cityName);
    }

    public String getPostalCode() {
        return (String) get(PROPERTY_POSTALCODE);
    }

    public void setPostalCode(String postalCode) {
        set(PROPERTY_POSTALCODE, postalCode);
    }

    public String getPostalAdd() {
        return (String) get(PROPERTY_POSTALADD);
    }

    public void setPostalAdd(String postalAdd) {
        set(PROPERTY_POSTALADD, postalAdd);
    }

    public Country getCountry() {
        return (Country) get(PROPERTY_COUNTRY);
    }

    public void setCountry(Country country) {
        set(PROPERTY_COUNTRY, country);
    }

    public Region getRegion() {
        return (Region) get(PROPERTY_REGION);
    }

    public void setRegion(Region region) {
        set(PROPERTY_REGION, region);
    }

    public City getCity() {
        return (City) get(PROPERTY_CITY);
    }

    public void setCity(City city) {
        set(PROPERTY_CITY, city);
    }

    public String getRegionName() {
        return (String) get(PROPERTY_REGIONNAME);
    }

    public void setRegionName(String regionName) {
        set(PROPERTY_REGIONNAME, regionName);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVLocationFromAddressList() {
        return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONFROMADDRESSLIST);
    }

    public void setAPRMFinAccTransactionAcctVLocationFromAddressList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVLocationFromAddressList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONFROMADDRESSLIST, aPRMFinAccTransactionAcctVLocationFromAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<FinAccTransactionAccounting> getAPRMFinAccTransactionAcctVLocationToAddressList() {
        return (List<FinAccTransactionAccounting>) get(PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONTOADDRESSLIST);
    }

    public void setAPRMFinAccTransactionAcctVLocationToAddressList(List<FinAccTransactionAccounting> aPRMFinAccTransactionAcctVLocationToAddressList) {
        set(PROPERTY_APRMFINACCTRANSACTIONACCTVLOCATIONTOADDRESSLIST, aPRMFinAccTransactionAcctVLocationToAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<Bank> getBankList() {
        return (List<Bank>) get(PROPERTY_BANKLIST);
    }

    public void setBankList(List<Bank> bankList) {
        set(PROPERTY_BANKLIST, bankList);
    }

    @SuppressWarnings("unchecked")
    public List<org.openbravo.model.common.businesspartner.Location> getBusinessPartnerLocationList() {
        return (List<org.openbravo.model.common.businesspartner.Location>) get(PROPERTY_BUSINESSPARTNERLOCATIONLIST);
    }

    public void setBusinessPartnerLocationList(List<org.openbravo.model.common.businesspartner.Location> businessPartnerLocationList) {
        set(PROPERTY_BUSINESSPARTNERLOCATIONLIST, businessPartnerLocationList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgBranchaddr> getEpcgBranchaddrList() {
        return (List<EpcgBranchaddr>) get(PROPERTY_EPCGBRANCHADDRLIST);
    }

    public void setEpcgBranchaddrList(List<EpcgBranchaddr> epcgBranchaddrList) {
        set(PROPERTY_EPCGBRANCHADDRLIST, epcgBranchaddrList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCommissionAgent> getEpcgCommissionAgentList() {
        return (List<EpcgCommissionAgent>) get(PROPERTY_EPCGCOMMISSIONAGENTLIST);
    }

    public void setEpcgCommissionAgentList(List<EpcgCommissionAgent> epcgCommissionAgentList) {
        set(PROPERTY_EPCGCOMMISSIONAGENTLIST, epcgCommissionAgentList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_FinancialAccount> getFINFinancialAccountList() {
        return (List<FIN_FinancialAccount>) get(PROPERTY_FINFINANCIALACCOUNTLIST);
    }

    public void setFINFinancialAccountList(List<FIN_FinancialAccount> fINFinancialAccountList) {
        set(PROPERTY_FINFINANCIALACCOUNTLIST, fINFinancialAccountList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingCombination> getFinancialMgmtAccountingCombinationLocationFromAddressList() {
        return (List<AccountingCombination>) get(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONFROMADDRESSLIST);
    }

    public void setFinancialMgmtAccountingCombinationLocationFromAddressList(List<AccountingCombination> financialMgmtAccountingCombinationLocationFromAddressList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONFROMADDRESSLIST, financialMgmtAccountingCombinationLocationFromAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingCombination> getFinancialMgmtAccountingCombinationLocationToAddressList() {
        return (List<AccountingCombination>) get(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONTOADDRESSLIST);
    }

    public void setFinancialMgmtAccountingCombinationLocationToAddressList(List<AccountingCombination> financialMgmtAccountingCombinationLocationToAddressList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGCOMBINATIONLOCATIONTOADDRESSLIST, financialMgmtAccountingCombinationLocationToAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactLocationFromAddressList() {
        return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONFROMADDRESSLIST);
    }

    public void setFinancialMgmtAccountingFactLocationFromAddressList(List<AccountingFact> financialMgmtAccountingFactLocationFromAddressList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONFROMADDRESSLIST, financialMgmtAccountingFactLocationFromAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<AccountingFact> getFinancialMgmtAccountingFactLocationToAddressList() {
        return (List<AccountingFact>) get(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONTOADDRESSLIST);
    }

    public void setFinancialMgmtAccountingFactLocationToAddressList(List<AccountingFact> financialMgmtAccountingFactLocationToAddressList) {
        set(PROPERTY_FINANCIALMGMTACCOUNTINGFACTLOCATIONTOADDRESSLIST, financialMgmtAccountingFactLocationToAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaElement> getFinancialMgmtAcctSchemaElementList() {
        return (List<AcctSchemaElement>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST);
    }

    public void setFinancialMgmtAcctSchemaElementList(List<AcctSchemaElement> financialMgmtAcctSchemaElementList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, financialMgmtAcctSchemaElementList);
    }

    @SuppressWarnings("unchecked")
    public List<Asset> getFinancialMgmtAssetList() {
        return (List<Asset>) get(PROPERTY_FINANCIALMGMTASSETLIST);
    }

    public void setFinancialMgmtAssetList(List<Asset> financialMgmtAssetList) {
        set(PROPERTY_FINANCIALMGMTASSETLIST, financialMgmtAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationInformation> getOrganizationInformationList() {
        return (List<OrganizationInformation>) get(PROPERTY_ORGANIZATIONINFORMATIONLIST);
    }

    public void setOrganizationInformationList(List<OrganizationInformation> organizationInformationList) {
        set(PROPERTY_ORGANIZATIONINFORMATIONLIST, organizationInformationList);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjectList() {
        return (List<Project>) get(PROPERTY_PROJECTLIST);
    }

    public void setProjectList(List<Project> projectList) {
        set(PROPERTY_PROJECTLIST, projectList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Bank> getRCHRBankList() {
        return (List<RCHR_Bank>) get(PROPERTY_RCHRBANKLIST);
    }

    public void setRCHRBankList(List<RCHR_Bank> rCHRBankList) {
        set(PROPERTY_RCHRBANKLIST, rCHRBankList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Block> getRCHRBlockList() {
        return (List<RCHR_Block>) get(PROPERTY_RCHRBLOCKLIST);
    }

    public void setRCHRBlockList(List<RCHR_Block> rCHRBlockList) {
        set(PROPERTY_RCHRBLOCKLIST, rCHRBlockList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_EmpAddress> getRCHREmpAddressList() {
        return (List<RCHR_EmpAddress>) get(PROPERTY_RCHREMPADDRESSLIST);
    }

    public void setRCHREmpAddressList(List<RCHR_EmpAddress> rCHREmpAddressList) {
        set(PROPERTY_RCHREMPADDRESSLIST, rCHREmpAddressList);
    }

    @SuppressWarnings("unchecked")
    public List<Warehouse> getWarehouseList() {
        return (List<Warehouse>) get(PROPERTY_WAREHOUSELIST);
    }

    public void setWarehouseList(List<Warehouse> warehouseList) {
        set(PROPERTY_WAREHOUSELIST, warehouseList);
    }

}
