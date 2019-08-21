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
package org.openbravo.model.ad.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.ADClientAcctDimension;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.EmailServerConfiguration;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ADClient (stored in table AD_Client).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Client extends BaseOBObject implements Traceable, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Client";
    public static final String ENTITY_NAME = "ADClient";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_SEARCHKEY = "searchKey";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_MAILHOST = "mailHost";
    public static final String PROPERTY_REQUESTEMAIL = "requestEmail";
    public static final String PROPERTY_REQUESTUSER = "requestUser";
    public static final String PROPERTY_REQUESTUSERPASSWORD = "requestUserPassword";
    public static final String PROPERTY_REQUESTFOLDER = "requestFolder";
    public static final String PROPERTY_LANGUAGE = "language";
    public static final String PROPERTY_MULTILINGUALDOCUMENTS = "multilingualDocuments";
    public static final String PROPERTY_SMTPAUTHENTIFICATION = "sMTPAuthentification";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_ACCTDIMCENTRALLYMAINTAINED = "acctdimCentrallyMaintained";
    public static final String PROPERTY_PROJECTACCTDIMISENABLE = "projectAcctdimIsenable";
    public static final String PROPERTY_PROJECTACCTDIMHEADER = "projectAcctdimHeader";
    public static final String PROPERTY_PROJECTACCTDIMLINES = "projectAcctdimLines";
    public static final String PROPERTY_PROJECTACCTDIMBREAKDOWN = "projectAcctdimBreakdown";
    public static final String PROPERTY_BPARTNERACCTDIMISENABLE = "bpartnerAcctdimIsenable";
    public static final String PROPERTY_BPARTNERACCTDIMHEADER = "bpartnerAcctdimHeader";
    public static final String PROPERTY_BPARTNERACCTDIMLINES = "bpartnerAcctdimLines";
    public static final String PROPERTY_BPARTNERACCTDIMBREAKDOWN = "bpartnerAcctdimBreakdown";
    public static final String PROPERTY_PRODUCTACCTDIMISENABLE = "productAcctdimIsenable";
    public static final String PROPERTY_PRODUCTACCTDIMHEADER = "productAcctdimHeader";
    public static final String PROPERTY_PRODUCTACCTDIMLINES = "productAcctdimLines";
    public static final String PROPERTY_PRODUCTACCTDIMBREAKDOWN = "productAcctdimBreakdown";
    public static final String PROPERTY_COSTCENTERACCTDIMHEADER = "costcenterAcctdimHeader";
    public static final String PROPERTY_COSTCENTERACCTDIMLINES = "costcenterAcctdimLines";
    public static final String PROPERTY_COSTCENTERACCTDIMBREAKDOWN = "costcenterAcctdimBreakdown";
    public static final String PROPERTY_USER1ACCTDIMISENABLE = "user1AcctdimIsenable";
    public static final String PROPERTY_USER1ACCTDIMHEADER = "user1AcctdimHeader";
    public static final String PROPERTY_USER1ACCTDIMLINES = "user1AcctdimLines";
    public static final String PROPERTY_USER1ACCTDIMBREAKDOWN = "user1AcctdimBreakdown";
    public static final String PROPERTY_USER2ACCTDIMISENABLE = "user2AcctdimIsenable";
    public static final String PROPERTY_USER2ACCTDIMHEADER = "user2AcctdimHeader";
    public static final String PROPERTY_USER2ACCTDIMLINES = "user2AcctdimLines";
    public static final String PROPERTY_USER2ACCTDIMBREAKDOWN = "user2AcctdimBreakdown";
    public static final String PROPERTY_COSTCENTERACCTDIMISENABLE = "costcenterAcctdimIsenable";
    public static final String PROPERTY_ORGACCTDIMISENABLE = "orgAcctdimIsenable";
    public static final String PROPERTY_ORGACCTDIMHEADER = "orgAcctdimHeader";
    public static final String PROPERTY_ORGACCTDIMLINES = "orgAcctdimLines";
    public static final String PROPERTY_ORGACCTDIMBREAKDOWN = "orgAcctdimBreakdown";
    public static final String PROPERTY_ADCLIENTACCTDIMENSIONLIST = "aDClientAcctDimensionList";
    public static final String PROPERTY_CLIENTINFORMATIONLIST = "clientInformationList";
    public static final String PROPERTY_EMAILSERVERCONFIGURATIONLIST = "emailServerConfigurationList";

    public Client() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_MULTILINGUALDOCUMENTS, false);
        setDefaultValue(PROPERTY_SMTPAUTHENTIFICATION, false);
        setDefaultValue(PROPERTY_ACCTDIMCENTRALLYMAINTAINED, false);
        setDefaultValue(PROPERTY_PROJECTACCTDIMISENABLE, false);
        setDefaultValue(PROPERTY_PROJECTACCTDIMHEADER, false);
        setDefaultValue(PROPERTY_PROJECTACCTDIMLINES, false);
        setDefaultValue(PROPERTY_PROJECTACCTDIMBREAKDOWN, false);
        setDefaultValue(PROPERTY_BPARTNERACCTDIMISENABLE, false);
        setDefaultValue(PROPERTY_BPARTNERACCTDIMHEADER, false);
        setDefaultValue(PROPERTY_BPARTNERACCTDIMLINES, false);
        setDefaultValue(PROPERTY_BPARTNERACCTDIMBREAKDOWN, false);
        setDefaultValue(PROPERTY_PRODUCTACCTDIMISENABLE, false);
        setDefaultValue(PROPERTY_PRODUCTACCTDIMHEADER, false);
        setDefaultValue(PROPERTY_PRODUCTACCTDIMLINES, false);
        setDefaultValue(PROPERTY_PRODUCTACCTDIMBREAKDOWN, false);
        setDefaultValue(PROPERTY_COSTCENTERACCTDIMHEADER, false);
        setDefaultValue(PROPERTY_COSTCENTERACCTDIMLINES, false);
        setDefaultValue(PROPERTY_COSTCENTERACCTDIMBREAKDOWN, false);
        setDefaultValue(PROPERTY_USER1ACCTDIMISENABLE, false);
        setDefaultValue(PROPERTY_USER1ACCTDIMHEADER, false);
        setDefaultValue(PROPERTY_USER1ACCTDIMLINES, false);
        setDefaultValue(PROPERTY_USER1ACCTDIMBREAKDOWN, false);
        setDefaultValue(PROPERTY_USER2ACCTDIMISENABLE, false);
        setDefaultValue(PROPERTY_USER2ACCTDIMHEADER, false);
        setDefaultValue(PROPERTY_USER2ACCTDIMLINES, false);
        setDefaultValue(PROPERTY_USER2ACCTDIMBREAKDOWN, false);
        setDefaultValue(PROPERTY_COSTCENTERACCTDIMISENABLE, false);
        setDefaultValue(PROPERTY_ORGACCTDIMISENABLE, false);
        setDefaultValue(PROPERTY_ORGACCTDIMHEADER, false);
        setDefaultValue(PROPERTY_ORGACCTDIMLINES, false);
        setDefaultValue(PROPERTY_ORGACCTDIMBREAKDOWN, false);
        setDefaultValue(PROPERTY_ADCLIENTACCTDIMENSIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EMAILSERVERCONFIGURATIONLIST, new ArrayList<Object>());
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

    public String getSearchKey() {
        return (String) get(PROPERTY_SEARCHKEY);
    }

    public void setSearchKey(String searchKey) {
        set(PROPERTY_SEARCHKEY, searchKey);
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

    public String getMailHost() {
        return (String) get(PROPERTY_MAILHOST);
    }

    public void setMailHost(String mailHost) {
        set(PROPERTY_MAILHOST, mailHost);
    }

    public String getRequestEmail() {
        return (String) get(PROPERTY_REQUESTEMAIL);
    }

    public void setRequestEmail(String requestEmail) {
        set(PROPERTY_REQUESTEMAIL, requestEmail);
    }

    public String getRequestUser() {
        return (String) get(PROPERTY_REQUESTUSER);
    }

    public void setRequestUser(String requestUser) {
        set(PROPERTY_REQUESTUSER, requestUser);
    }

    public String getRequestUserPassword() {
        return (String) get(PROPERTY_REQUESTUSERPASSWORD);
    }

    public void setRequestUserPassword(String requestUserPassword) {
        set(PROPERTY_REQUESTUSERPASSWORD, requestUserPassword);
    }

    public String getRequestFolder() {
        return (String) get(PROPERTY_REQUESTFOLDER);
    }

    public void setRequestFolder(String requestFolder) {
        set(PROPERTY_REQUESTFOLDER, requestFolder);
    }

    public Language getLanguage() {
        return (Language) get(PROPERTY_LANGUAGE);
    }

    public void setLanguage(Language language) {
        set(PROPERTY_LANGUAGE, language);
    }

    public Boolean isMultilingualDocuments() {
        return (Boolean) get(PROPERTY_MULTILINGUALDOCUMENTS);
    }

    public void setMultilingualDocuments(Boolean multilingualDocuments) {
        set(PROPERTY_MULTILINGUALDOCUMENTS, multilingualDocuments);
    }

    public Boolean isSMTPAuthentification() {
        return (Boolean) get(PROPERTY_SMTPAUTHENTIFICATION);
    }

    public void setSMTPAuthentification(Boolean sMTPAuthentification) {
        set(PROPERTY_SMTPAUTHENTIFICATION, sMTPAuthentification);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public Boolean isAcctdimCentrallyMaintained() {
        return (Boolean) get(PROPERTY_ACCTDIMCENTRALLYMAINTAINED);
    }

    public void setAcctdimCentrallyMaintained(Boolean acctdimCentrallyMaintained) {
        set(PROPERTY_ACCTDIMCENTRALLYMAINTAINED, acctdimCentrallyMaintained);
    }

    public Boolean isProjectAcctdimIsenable() {
        return (Boolean) get(PROPERTY_PROJECTACCTDIMISENABLE);
    }

    public void setProjectAcctdimIsenable(Boolean projectAcctdimIsenable) {
        set(PROPERTY_PROJECTACCTDIMISENABLE, projectAcctdimIsenable);
    }

    public Boolean isProjectAcctdimHeader() {
        return (Boolean) get(PROPERTY_PROJECTACCTDIMHEADER);
    }

    public void setProjectAcctdimHeader(Boolean projectAcctdimHeader) {
        set(PROPERTY_PROJECTACCTDIMHEADER, projectAcctdimHeader);
    }

    public Boolean isProjectAcctdimLines() {
        return (Boolean) get(PROPERTY_PROJECTACCTDIMLINES);
    }

    public void setProjectAcctdimLines(Boolean projectAcctdimLines) {
        set(PROPERTY_PROJECTACCTDIMLINES, projectAcctdimLines);
    }

    public Boolean isProjectAcctdimBreakdown() {
        return (Boolean) get(PROPERTY_PROJECTACCTDIMBREAKDOWN);
    }

    public void setProjectAcctdimBreakdown(Boolean projectAcctdimBreakdown) {
        set(PROPERTY_PROJECTACCTDIMBREAKDOWN, projectAcctdimBreakdown);
    }

    public Boolean isBpartnerAcctdimIsenable() {
        return (Boolean) get(PROPERTY_BPARTNERACCTDIMISENABLE);
    }

    public void setBpartnerAcctdimIsenable(Boolean bpartnerAcctdimIsenable) {
        set(PROPERTY_BPARTNERACCTDIMISENABLE, bpartnerAcctdimIsenable);
    }

    public Boolean isBpartnerAcctdimHeader() {
        return (Boolean) get(PROPERTY_BPARTNERACCTDIMHEADER);
    }

    public void setBpartnerAcctdimHeader(Boolean bpartnerAcctdimHeader) {
        set(PROPERTY_BPARTNERACCTDIMHEADER, bpartnerAcctdimHeader);
    }

    public Boolean isBpartnerAcctdimLines() {
        return (Boolean) get(PROPERTY_BPARTNERACCTDIMLINES);
    }

    public void setBpartnerAcctdimLines(Boolean bpartnerAcctdimLines) {
        set(PROPERTY_BPARTNERACCTDIMLINES, bpartnerAcctdimLines);
    }

    public Boolean isBpartnerAcctdimBreakdown() {
        return (Boolean) get(PROPERTY_BPARTNERACCTDIMBREAKDOWN);
    }

    public void setBpartnerAcctdimBreakdown(Boolean bpartnerAcctdimBreakdown) {
        set(PROPERTY_BPARTNERACCTDIMBREAKDOWN, bpartnerAcctdimBreakdown);
    }

    public Boolean isProductAcctdimIsenable() {
        return (Boolean) get(PROPERTY_PRODUCTACCTDIMISENABLE);
    }

    public void setProductAcctdimIsenable(Boolean productAcctdimIsenable) {
        set(PROPERTY_PRODUCTACCTDIMISENABLE, productAcctdimIsenable);
    }

    public Boolean isProductAcctdimHeader() {
        return (Boolean) get(PROPERTY_PRODUCTACCTDIMHEADER);
    }

    public void setProductAcctdimHeader(Boolean productAcctdimHeader) {
        set(PROPERTY_PRODUCTACCTDIMHEADER, productAcctdimHeader);
    }

    public Boolean isProductAcctdimLines() {
        return (Boolean) get(PROPERTY_PRODUCTACCTDIMLINES);
    }

    public void setProductAcctdimLines(Boolean productAcctdimLines) {
        set(PROPERTY_PRODUCTACCTDIMLINES, productAcctdimLines);
    }

    public Boolean isProductAcctdimBreakdown() {
        return (Boolean) get(PROPERTY_PRODUCTACCTDIMBREAKDOWN);
    }

    public void setProductAcctdimBreakdown(Boolean productAcctdimBreakdown) {
        set(PROPERTY_PRODUCTACCTDIMBREAKDOWN, productAcctdimBreakdown);
    }

    public Boolean isCostcenterAcctdimHeader() {
        return (Boolean) get(PROPERTY_COSTCENTERACCTDIMHEADER);
    }

    public void setCostcenterAcctdimHeader(Boolean costcenterAcctdimHeader) {
        set(PROPERTY_COSTCENTERACCTDIMHEADER, costcenterAcctdimHeader);
    }

    public Boolean isCostcenterAcctdimLines() {
        return (Boolean) get(PROPERTY_COSTCENTERACCTDIMLINES);
    }

    public void setCostcenterAcctdimLines(Boolean costcenterAcctdimLines) {
        set(PROPERTY_COSTCENTERACCTDIMLINES, costcenterAcctdimLines);
    }

    public Boolean isCostcenterAcctdimBreakdown() {
        return (Boolean) get(PROPERTY_COSTCENTERACCTDIMBREAKDOWN);
    }

    public void setCostcenterAcctdimBreakdown(Boolean costcenterAcctdimBreakdown) {
        set(PROPERTY_COSTCENTERACCTDIMBREAKDOWN, costcenterAcctdimBreakdown);
    }

    public Boolean isUser1AcctdimIsenable() {
        return (Boolean) get(PROPERTY_USER1ACCTDIMISENABLE);
    }

    public void setUser1AcctdimIsenable(Boolean user1AcctdimIsenable) {
        set(PROPERTY_USER1ACCTDIMISENABLE, user1AcctdimIsenable);
    }

    public Boolean isUser1AcctdimHeader() {
        return (Boolean) get(PROPERTY_USER1ACCTDIMHEADER);
    }

    public void setUser1AcctdimHeader(Boolean user1AcctdimHeader) {
        set(PROPERTY_USER1ACCTDIMHEADER, user1AcctdimHeader);
    }

    public Boolean isUser1AcctdimLines() {
        return (Boolean) get(PROPERTY_USER1ACCTDIMLINES);
    }

    public void setUser1AcctdimLines(Boolean user1AcctdimLines) {
        set(PROPERTY_USER1ACCTDIMLINES, user1AcctdimLines);
    }

    public Boolean isUser1AcctdimBreakdown() {
        return (Boolean) get(PROPERTY_USER1ACCTDIMBREAKDOWN);
    }

    public void setUser1AcctdimBreakdown(Boolean user1AcctdimBreakdown) {
        set(PROPERTY_USER1ACCTDIMBREAKDOWN, user1AcctdimBreakdown);
    }

    public Boolean isUser2AcctdimIsenable() {
        return (Boolean) get(PROPERTY_USER2ACCTDIMISENABLE);
    }

    public void setUser2AcctdimIsenable(Boolean user2AcctdimIsenable) {
        set(PROPERTY_USER2ACCTDIMISENABLE, user2AcctdimIsenable);
    }

    public Boolean isUser2AcctdimHeader() {
        return (Boolean) get(PROPERTY_USER2ACCTDIMHEADER);
    }

    public void setUser2AcctdimHeader(Boolean user2AcctdimHeader) {
        set(PROPERTY_USER2ACCTDIMHEADER, user2AcctdimHeader);
    }

    public Boolean isUser2AcctdimLines() {
        return (Boolean) get(PROPERTY_USER2ACCTDIMLINES);
    }

    public void setUser2AcctdimLines(Boolean user2AcctdimLines) {
        set(PROPERTY_USER2ACCTDIMLINES, user2AcctdimLines);
    }

    public Boolean isUser2AcctdimBreakdown() {
        return (Boolean) get(PROPERTY_USER2ACCTDIMBREAKDOWN);
    }

    public void setUser2AcctdimBreakdown(Boolean user2AcctdimBreakdown) {
        set(PROPERTY_USER2ACCTDIMBREAKDOWN, user2AcctdimBreakdown);
    }

    public Boolean isCostcenterAcctdimIsenable() {
        return (Boolean) get(PROPERTY_COSTCENTERACCTDIMISENABLE);
    }

    public void setCostcenterAcctdimIsenable(Boolean costcenterAcctdimIsenable) {
        set(PROPERTY_COSTCENTERACCTDIMISENABLE, costcenterAcctdimIsenable);
    }

    public Boolean isOrgAcctdimIsenable() {
        return (Boolean) get(PROPERTY_ORGACCTDIMISENABLE);
    }

    public void setOrgAcctdimIsenable(Boolean orgAcctdimIsenable) {
        set(PROPERTY_ORGACCTDIMISENABLE, orgAcctdimIsenable);
    }

    public Boolean isOrgAcctdimHeader() {
        return (Boolean) get(PROPERTY_ORGACCTDIMHEADER);
    }

    public void setOrgAcctdimHeader(Boolean orgAcctdimHeader) {
        set(PROPERTY_ORGACCTDIMHEADER, orgAcctdimHeader);
    }

    public Boolean isOrgAcctdimLines() {
        return (Boolean) get(PROPERTY_ORGACCTDIMLINES);
    }

    public void setOrgAcctdimLines(Boolean orgAcctdimLines) {
        set(PROPERTY_ORGACCTDIMLINES, orgAcctdimLines);
    }

    public Boolean isOrgAcctdimBreakdown() {
        return (Boolean) get(PROPERTY_ORGACCTDIMBREAKDOWN);
    }

    public void setOrgAcctdimBreakdown(Boolean orgAcctdimBreakdown) {
        set(PROPERTY_ORGACCTDIMBREAKDOWN, orgAcctdimBreakdown);
    }

    @SuppressWarnings("unchecked")
    public List<ADClientAcctDimension> getADClientAcctDimensionList() {
        return (List<ADClientAcctDimension>) get(PROPERTY_ADCLIENTACCTDIMENSIONLIST);
    }

    public void setADClientAcctDimensionList(List<ADClientAcctDimension> aDClientAcctDimensionList) {
        set(PROPERTY_ADCLIENTACCTDIMENSIONLIST, aDClientAcctDimensionList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONLIST);
    }

    public void setClientInformationList(List<ClientInformation> clientInformationList) {
        set(PROPERTY_CLIENTINFORMATIONLIST, clientInformationList);
    }

    @SuppressWarnings("unchecked")
    public List<EmailServerConfiguration> getEmailServerConfigurationList() {
        return (List<EmailServerConfiguration>) get(PROPERTY_EMAILSERVERCONFIGURATIONLIST);
    }

    public void setEmailServerConfigurationList(List<EmailServerConfiguration> emailServerConfigurationList) {
        set(PROPERTY_EMAILSERVERCONFIGURATIONLIST, emailServerConfigurationList);
    }

}
