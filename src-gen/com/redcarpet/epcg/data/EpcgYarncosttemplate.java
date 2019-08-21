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
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity Epcg_Yarncosttemplate (stored in table Epcg_Yarncosttemplate).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EpcgYarncosttemplate extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Epcg_Yarncosttemplate";
    public static final String ENTITY_NAME = "Epcg_Yarncosttemplate";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_COSTINGDATE = "costingdate";
    public static final String PROPERTY_COSTINGFROMDATE = "costingfromdate";
    public static final String PROPERTY_COSTINGTODATE = "costingtodate";
    public static final String PROPERTY_COPYLINES = "copylines";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_WARPWASTEPERCENTAGE = "warpwastepercentage";
    public static final String PROPERTY_WEFTWASTEPERCENTAGE = "weftwastepercentage";
    public static final String PROPERTY_FRINGEWIDTH = "fringewidth";
    public static final String PROPERTY_NOOFDAYS = "noofdays";
    public static final String PROPERTY_PACKNGMATRLCNSMPTN = "packngmatrlcnsmptn";
    public static final String PROPERTY_EPCGCOLORTEMPLATELINESLIST = "epcgColortemplatelinesList";
    public static final String PROPERTY_EPCGYARNCOSTTEMPLATELINESLIST = "epcgYarncosttemplatelinesList";

    public EpcgYarncosttemplate() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_COPYLINES, false);
        setDefaultValue(PROPERTY_ALERTSTATUS, "DR");
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_WARPWASTEPERCENTAGE, new BigDecimal(0));
        setDefaultValue(PROPERTY_WEFTWASTEPERCENTAGE, new BigDecimal(0));
        setDefaultValue(PROPERTY_FRINGEWIDTH, new BigDecimal(0));
        setDefaultValue(PROPERTY_NOOFDAYS, new BigDecimal(0));
        setDefaultValue(PROPERTY_PACKNGMATRLCNSMPTN, new BigDecimal(0));
        setDefaultValue(PROPERTY_EPCGCOLORTEMPLATELINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGYARNCOSTTEMPLATELINESLIST, new ArrayList<Object>());
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

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Date getCostingdate() {
        return (Date) get(PROPERTY_COSTINGDATE);
    }

    public void setCostingdate(Date costingdate) {
        set(PROPERTY_COSTINGDATE, costingdate);
    }

    public Date getCostingfromdate() {
        return (Date) get(PROPERTY_COSTINGFROMDATE);
    }

    public void setCostingfromdate(Date costingfromdate) {
        set(PROPERTY_COSTINGFROMDATE, costingfromdate);
    }

    public Date getCostingtodate() {
        return (Date) get(PROPERTY_COSTINGTODATE);
    }

    public void setCostingtodate(Date costingtodate) {
        set(PROPERTY_COSTINGTODATE, costingtodate);
    }

    public Boolean isCopylines() {
        return (Boolean) get(PROPERTY_COPYLINES);
    }

    public void setCopylines(Boolean copylines) {
        set(PROPERTY_COPYLINES, copylines);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public BigDecimal getWarpwastepercentage() {
        return (BigDecimal) get(PROPERTY_WARPWASTEPERCENTAGE);
    }

    public void setWarpwastepercentage(BigDecimal warpwastepercentage) {
        set(PROPERTY_WARPWASTEPERCENTAGE, warpwastepercentage);
    }

    public BigDecimal getWeftwastepercentage() {
        return (BigDecimal) get(PROPERTY_WEFTWASTEPERCENTAGE);
    }

    public void setWeftwastepercentage(BigDecimal weftwastepercentage) {
        set(PROPERTY_WEFTWASTEPERCENTAGE, weftwastepercentage);
    }

    public BigDecimal getFringewidth() {
        return (BigDecimal) get(PROPERTY_FRINGEWIDTH);
    }

    public void setFringewidth(BigDecimal fringewidth) {
        set(PROPERTY_FRINGEWIDTH, fringewidth);
    }

    public BigDecimal getNoofdays() {
        return (BigDecimal) get(PROPERTY_NOOFDAYS);
    }

    public void setNoofdays(BigDecimal noofdays) {
        set(PROPERTY_NOOFDAYS, noofdays);
    }

    public BigDecimal getPackngmatrlcnsmptn() {
        return (BigDecimal) get(PROPERTY_PACKNGMATRLCNSMPTN);
    }

    public void setPackngmatrlcnsmptn(BigDecimal packngmatrlcnsmptn) {
        set(PROPERTY_PACKNGMATRLCNSMPTN, packngmatrlcnsmptn);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgColortemplatelines> getEpcgColortemplatelinesList() {
        return (List<EpcgColortemplatelines>) get(PROPERTY_EPCGCOLORTEMPLATELINESLIST);
    }

    public void setEpcgColortemplatelinesList(List<EpcgColortemplatelines> epcgColortemplatelinesList) {
        set(PROPERTY_EPCGCOLORTEMPLATELINESLIST, epcgColortemplatelinesList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgYarncosttemplatelines> getEpcgYarncosttemplatelinesList() {
        return (List<EpcgYarncosttemplatelines>) get(PROPERTY_EPCGYARNCOSTTEMPLATELINESLIST);
    }

    public void setEpcgYarncosttemplatelinesList(List<EpcgYarncosttemplatelines> epcgYarncosttemplatelinesList) {
        set(PROPERTY_EPCGYARNCOSTTEMPLATELINESLIST, epcgYarncosttemplatelinesList);
    }

}
