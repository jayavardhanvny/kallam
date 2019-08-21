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
package org.openbravo.model.manufacturing.transaction;

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
import org.openbravo.model.manufacturing.processplan.ProcessPlan;
import org.openbravo.model.mrp.ProductionRunLine;
import org.openbravo.model.mrp.PurchasingRunLine;
/**
 * Entity class for entity ManufacturingWorkRequirement (stored in table MA_WorkRequirement).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class WorkRequirement extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_WorkRequirement";
    public static final String ENTITY_NAME = "ManufacturingWorkRequirement";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_PROCESSPLAN = "processPlan";
    public static final String PROPERTY_QUANTITY = "quantity";
    public static final String PROPERTY_WRCREATIONDATE = "wRCreationDate";
    public static final String PROPERTY_STARTINGDATE = "startingDate";
    public static final String PROPERTY_ENDINGDATE = "endingDate";
    public static final String PROPERTY_CLOSED = "closed";
    public static final String PROPERTY_INSERTPRODUCTSANDORPHASES = "insertProductsAndorPhases";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_INCLUDEPHASESWHENINSERTING = "includePhasesWhenInserting";
    public static final String PROPERTY_PROCESSQUANTITY = "processQuantity";
    public static final String PROPERTY_PROCESSUNIT = "processUnit";
    public static final String PROPERTY_CONVERSIONRATE = "conversionRate";
    public static final String PROPERTY_ESTIMATEDTIME = "estimatedTime";
    public static final String PROPERTY_RUNTIME = "runTime";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_CREATEWORKREQUIREMENT = "createworkrequirement";
    public static final String PROPERTY_CLOSEDSTAT = "closedStat";
    public static final String PROPERTY_MRPPRODUCTIONRUNLINELIST = "mRPProductionRunLineList";
    public static final String PROPERTY_MRPPURCHASINGRUNLINELIST = "mRPPurchasingRunLineList";
    public static final String PROPERTY_MANUFACTURINGWORKREQUIREMENTOPERATIONLIST = "manufacturingWorkRequirementOperationList";

    public WorkRequirement() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CLOSED, false);
        setDefaultValue(PROPERTY_INSERTPRODUCTSANDORPHASES, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_INCLUDEPHASESWHENINSERTING, true);
        setDefaultValue(PROPERTY_ESTIMATEDTIME, new BigDecimal(0));
        setDefaultValue(PROPERTY_RUNTIME, new BigDecimal(0));
        setDefaultValue(PROPERTY_CREATEWORKREQUIREMENT, false);
        setDefaultValue(PROPERTY_CLOSEDSTAT, false);
        setDefaultValue(PROPERTY_MRPPRODUCTIONRUNLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MRPPURCHASINGRUNLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_MANUFACTURINGWORKREQUIREMENTOPERATIONLIST, new ArrayList<Object>());
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

    public ProcessPlan getProcessPlan() {
        return (ProcessPlan) get(PROPERTY_PROCESSPLAN);
    }

    public void setProcessPlan(ProcessPlan processPlan) {
        set(PROPERTY_PROCESSPLAN, processPlan);
    }

    public BigDecimal getQuantity() {
        return (BigDecimal) get(PROPERTY_QUANTITY);
    }

    public void setQuantity(BigDecimal quantity) {
        set(PROPERTY_QUANTITY, quantity);
    }

    public Date getWRCreationDate() {
        return (Date) get(PROPERTY_WRCREATIONDATE);
    }

    public void setWRCreationDate(Date wRCreationDate) {
        set(PROPERTY_WRCREATIONDATE, wRCreationDate);
    }

    public Date getStartingDate() {
        return (Date) get(PROPERTY_STARTINGDATE);
    }

    public void setStartingDate(Date startingDate) {
        set(PROPERTY_STARTINGDATE, startingDate);
    }

    public Date getEndingDate() {
        return (Date) get(PROPERTY_ENDINGDATE);
    }

    public void setEndingDate(Date endingDate) {
        set(PROPERTY_ENDINGDATE, endingDate);
    }

    public Boolean isClosed() {
        return (Boolean) get(PROPERTY_CLOSED);
    }

    public void setClosed(Boolean closed) {
        set(PROPERTY_CLOSED, closed);
    }

    public Boolean isInsertProductsAndorPhases() {
        return (Boolean) get(PROPERTY_INSERTPRODUCTSANDORPHASES);
    }

    public void setInsertProductsAndorPhases(Boolean insertProductsAndorPhases) {
        set(PROPERTY_INSERTPRODUCTSANDORPHASES, insertProductsAndorPhases);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Boolean isIncludePhasesWhenInserting() {
        return (Boolean) get(PROPERTY_INCLUDEPHASESWHENINSERTING);
    }

    public void setIncludePhasesWhenInserting(Boolean includePhasesWhenInserting) {
        set(PROPERTY_INCLUDEPHASESWHENINSERTING, includePhasesWhenInserting);
    }

    public BigDecimal getProcessQuantity() {
        return (BigDecimal) get(PROPERTY_PROCESSQUANTITY);
    }

    public void setProcessQuantity(BigDecimal processQuantity) {
        set(PROPERTY_PROCESSQUANTITY, processQuantity);
    }

    public String getProcessUnit() {
        return (String) get(PROPERTY_PROCESSUNIT);
    }

    public void setProcessUnit(String processUnit) {
        set(PROPERTY_PROCESSUNIT, processUnit);
    }

    public BigDecimal getConversionRate() {
        return (BigDecimal) get(PROPERTY_CONVERSIONRATE);
    }

    public void setConversionRate(BigDecimal conversionRate) {
        set(PROPERTY_CONVERSIONRATE, conversionRate);
    }

    public BigDecimal getEstimatedTime() {
        return (BigDecimal) get(PROPERTY_ESTIMATEDTIME);
    }

    public void setEstimatedTime(BigDecimal estimatedTime) {
        set(PROPERTY_ESTIMATEDTIME, estimatedTime);
    }

    public BigDecimal getRunTime() {
        return (BigDecimal) get(PROPERTY_RUNTIME);
    }

    public void setRunTime(BigDecimal runTime) {
        set(PROPERTY_RUNTIME, runTime);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public Boolean isCreateworkrequirement() {
        return (Boolean) get(PROPERTY_CREATEWORKREQUIREMENT);
    }

    public void setCreateworkrequirement(Boolean createworkrequirement) {
        set(PROPERTY_CREATEWORKREQUIREMENT, createworkrequirement);
    }

    public Boolean isClosedStat() {
        return (Boolean) get(PROPERTY_CLOSEDSTAT);
    }

    public void setClosedStat(Boolean closedStat) {
        set(PROPERTY_CLOSEDSTAT, closedStat);
    }

    @SuppressWarnings("unchecked")
    public List<ProductionRunLine> getMRPProductionRunLineList() {
        return (List<ProductionRunLine>) get(PROPERTY_MRPPRODUCTIONRUNLINELIST);
    }

    public void setMRPProductionRunLineList(List<ProductionRunLine> mRPProductionRunLineList) {
        set(PROPERTY_MRPPRODUCTIONRUNLINELIST, mRPProductionRunLineList);
    }

    @SuppressWarnings("unchecked")
    public List<PurchasingRunLine> getMRPPurchasingRunLineList() {
        return (List<PurchasingRunLine>) get(PROPERTY_MRPPURCHASINGRUNLINELIST);
    }

    public void setMRPPurchasingRunLineList(List<PurchasingRunLine> mRPPurchasingRunLineList) {
        set(PROPERTY_MRPPURCHASINGRUNLINELIST, mRPPurchasingRunLineList);
    }

    @SuppressWarnings("unchecked")
    public List<WorkRequirementOperation> getManufacturingWorkRequirementOperationList() {
        return (List<WorkRequirementOperation>) get(PROPERTY_MANUFACTURINGWORKREQUIREMENTOPERATIONLIST);
    }

    public void setManufacturingWorkRequirementOperationList(List<WorkRequirementOperation> manufacturingWorkRequirementOperationList) {
        set(PROPERTY_MANUFACTURINGWORKREQUIREMENTOPERATIONLIST, manufacturingWorkRequirementOperationList);
    }

}
