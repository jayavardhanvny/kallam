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
package com.redcarpet.goodsissue.data;

import com.rcss.humanresource.data.RchrEmpDept;
import com.redcarpet.epcg.data.Vehicle;

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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
/**
 * Entity class for entity RCGI_MaterialReceipt (stored in table RCGI_MaterialReceipt).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCGI_MaterialReceipt extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCGI_MaterialReceipt";
    public static final String ENTITY_NAME = "RCGI_MaterialReceipt";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_MOVEMENTDATE = "movementDate";
    public static final String PROPERTY_EMPLOYEEDEPARTMENT = "employeeDepartment";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CREATEFROMINDENT = "createFromIndent";
    public static final String PROPERTY_CREATELINES = "createLines";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_POSTX = "postx";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_SUMMEDLINEAMOUNT = "summedLineAmount";
    public static final String PROPERTY_COMPLETENEW = "completenew";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_PARTNERADDRESS = "partnerAddress";
    public static final String PROPERTY_EPCGVEHICLE = "epcgVehicle";
    public static final String PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST = "materialMgmtMaterialTransactionList";
    public static final String PROPERTY_RCGIMRLINESLIST = "rCGIMrLinesList";

    public RCGI_MaterialReceipt() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATEFROMINDENT, false);
        setDefaultValue(PROPERTY_CREATELINES, false);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_POSTX, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_SUMMEDLINEAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_COMPLETENEW, false);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMRLINESLIST, new ArrayList<Object>());
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

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Date getMovementDate() {
        return (Date) get(PROPERTY_MOVEMENTDATE);
    }

    public void setMovementDate(Date movementDate) {
        set(PROPERTY_MOVEMENTDATE, movementDate);
    }

    public RchrEmpDept getEmployeeDepartment() {
        return (RchrEmpDept) get(PROPERTY_EMPLOYEEDEPARTMENT);
    }

    public void setEmployeeDepartment(RchrEmpDept employeeDepartment) {
        set(PROPERTY_EMPLOYEEDEPARTMENT, employeeDepartment);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isCreateFromIndent() {
        return (Boolean) get(PROPERTY_CREATEFROMINDENT);
    }

    public void setCreateFromIndent(Boolean createFromIndent) {
        set(PROPERTY_CREATEFROMINDENT, createFromIndent);
    }

    public Boolean isCreateLines() {
        return (Boolean) get(PROPERTY_CREATELINES);
    }

    public void setCreateLines(Boolean createLines) {
        set(PROPERTY_CREATELINES, createLines);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public Boolean isPostx() {
        return (Boolean) get(PROPERTY_POSTX);
    }

    public void setPostx(Boolean postx) {
        set(PROPERTY_POSTX, postx);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public BigDecimal getSummedLineAmount() {
        return (BigDecimal) get(PROPERTY_SUMMEDLINEAMOUNT);
    }

    public void setSummedLineAmount(BigDecimal summedLineAmount) {
        set(PROPERTY_SUMMEDLINEAMOUNT, summedLineAmount);
    }

    public Boolean isCompletenew() {
        return (Boolean) get(PROPERTY_COMPLETENEW);
    }

    public void setCompletenew(Boolean completenew) {
        set(PROPERTY_COMPLETENEW, completenew);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Location getPartnerAddress() {
        return (Location) get(PROPERTY_PARTNERADDRESS);
    }

    public void setPartnerAddress(Location partnerAddress) {
        set(PROPERTY_PARTNERADDRESS, partnerAddress);
    }

    public Vehicle getEpcgVehicle() {
        return (Vehicle) get(PROPERTY_EPCGVEHICLE);
    }

    public void setEpcgVehicle(Vehicle epcgVehicle) {
        set(PROPERTY_EPCGVEHICLE, epcgVehicle);
    }

    @SuppressWarnings("unchecked")
    public List<MaterialTransaction> getMaterialMgmtMaterialTransactionList() {
        return (List<MaterialTransaction>) get(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST);
    }

    public void setMaterialMgmtMaterialTransactionList(List<MaterialTransaction> materialMgmtMaterialTransactionList) {
        set(PROPERTY_MATERIALMGMTMATERIALTRANSACTIONLIST, materialMgmtMaterialTransactionList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MrLines> getRCGIMrLinesList() {
        return (List<RCGI_MrLines>) get(PROPERTY_RCGIMRLINESLIST);
    }

    public void setRCGIMrLinesList(List<RCGI_MrLines> rCGIMrLinesList) {
        set(PROPERTY_RCGIMRLINESLIST, rCGIMrLinesList);
    }

}
