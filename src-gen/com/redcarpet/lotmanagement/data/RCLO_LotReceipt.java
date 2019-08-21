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
package com.redcarpet.lotmanagement.data;

import com.redcarpet.production.data.RCPR_PrProduct;

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
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
/**
 * Entity class for entity RCLO_LotReceipt (stored in table RCLO_LotReceipt).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCLO_LotReceipt extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCLO_LotReceipt";
    public static final String ENTITY_NAME = "RCLO_LotReceipt";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_MOVEMENTDATE = "movementDate";
    public static final String PROPERTY_GOODSSHIPMENTLINE = "goodsShipmentLine";
    public static final String PROPERTY_RCPRPRODUCTIONLINE = "rCPRProductionLine";
    public static final String PROPERTY_TOTALQTYPERLOT = "totalQtyPerLot";
    public static final String PROPERTY_NOOFBALES = "noOfBales";
    public static final String PROPERTY_AVERAGEQTYPERBALE = "averageQtyPerBale";
    public static final String PROPERTY_OPENQUANTITY = "openQuantity";
    public static final String PROPERTY_OPENBALES = "openBales";
    public static final String PROPERTY_RECORDSCREATED = "recordsCreated";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PROCESSED = "processed";
    public static final String PROPERTY_RCLOLOTISSUELIST = "rCLOLotIssueList";
    public static final String PROPERTY_RCLOLOTISSUELINELIST = "rCLOLotIssueLineList";
    public static final String PROPERTY_RCLOLOTWISEQCLIST = "rCLOLotWiseQCList";

    public RCLO_LotReceipt() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TOTALQTYPERLOT, new BigDecimal(0));
        setDefaultValue(PROPERTY_NOOFBALES, new BigDecimal(0));
        setDefaultValue(PROPERTY_AVERAGEQTYPERBALE, new BigDecimal(0));
        setDefaultValue(PROPERTY_OPENQUANTITY, new BigDecimal(0));
        setDefaultValue(PROPERTY_OPENBALES, new BigDecimal(0));
        setDefaultValue(PROPERTY_RECORDSCREATED, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_PROCESSED, false);
        setDefaultValue(PROPERTY_RCLOLOTISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCLOLOTISSUELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCLOLOTWISEQCLIST, new ArrayList<Object>());
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

    public Date getMovementDate() {
        return (Date) get(PROPERTY_MOVEMENTDATE);
    }

    public void setMovementDate(Date movementDate) {
        set(PROPERTY_MOVEMENTDATE, movementDate);
    }

    public ShipmentInOutLine getGoodsShipmentLine() {
        return (ShipmentInOutLine) get(PROPERTY_GOODSSHIPMENTLINE);
    }

    public void setGoodsShipmentLine(ShipmentInOutLine goodsShipmentLine) {
        set(PROPERTY_GOODSSHIPMENTLINE, goodsShipmentLine);
    }

    public RCPR_PrProduct getRCPRProductionLine() {
        return (RCPR_PrProduct) get(PROPERTY_RCPRPRODUCTIONLINE);
    }

    public void setRCPRProductionLine(RCPR_PrProduct rCPRProductionLine) {
        set(PROPERTY_RCPRPRODUCTIONLINE, rCPRProductionLine);
    }

    public BigDecimal getTotalQtyPerLot() {
        return (BigDecimal) get(PROPERTY_TOTALQTYPERLOT);
    }

    public void setTotalQtyPerLot(BigDecimal totalQtyPerLot) {
        set(PROPERTY_TOTALQTYPERLOT, totalQtyPerLot);
    }

    public BigDecimal getNoOfBales() {
        return (BigDecimal) get(PROPERTY_NOOFBALES);
    }

    public void setNoOfBales(BigDecimal noOfBales) {
        set(PROPERTY_NOOFBALES, noOfBales);
    }

    public BigDecimal getAverageQtyPerBale() {
        return (BigDecimal) get(PROPERTY_AVERAGEQTYPERBALE);
    }

    public void setAverageQtyPerBale(BigDecimal averageQtyPerBale) {
        set(PROPERTY_AVERAGEQTYPERBALE, averageQtyPerBale);
    }

    public BigDecimal getOpenQuantity() {
        return (BigDecimal) get(PROPERTY_OPENQUANTITY);
    }

    public void setOpenQuantity(BigDecimal openQuantity) {
        set(PROPERTY_OPENQUANTITY, openQuantity);
    }

    public BigDecimal getOpenBales() {
        return (BigDecimal) get(PROPERTY_OPENBALES);
    }

    public void setOpenBales(BigDecimal openBales) {
        set(PROPERTY_OPENBALES, openBales);
    }

    public Boolean isRecordsCreated() {
        return (Boolean) get(PROPERTY_RECORDSCREATED);
    }

    public void setRecordsCreated(Boolean recordsCreated) {
        set(PROPERTY_RECORDSCREATED, recordsCreated);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Boolean isProcessed() {
        return (Boolean) get(PROPERTY_PROCESSED);
    }

    public void setProcessed(Boolean processed) {
        set(PROPERTY_PROCESSED, processed);
    }

    @SuppressWarnings("unchecked")
    public List<RCLO_LotIssue> getRCLOLotIssueList() {
        return (List<RCLO_LotIssue>) get(PROPERTY_RCLOLOTISSUELIST);
    }

    public void setRCLOLotIssueList(List<RCLO_LotIssue> rCLOLotIssueList) {
        set(PROPERTY_RCLOLOTISSUELIST, rCLOLotIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RCLO_LotIssueLine> getRCLOLotIssueLineList() {
        return (List<RCLO_LotIssueLine>) get(PROPERTY_RCLOLOTISSUELINELIST);
    }

    public void setRCLOLotIssueLineList(List<RCLO_LotIssueLine> rCLOLotIssueLineList) {
        set(PROPERTY_RCLOLOTISSUELINELIST, rCLOLotIssueLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCLO_LotWiseQC> getRCLOLotWiseQCList() {
        return (List<RCLO_LotWiseQC>) get(PROPERTY_RCLOLOTWISEQCLIST);
    }

    public void setRCLOLotWiseQCList(List<RCLO_LotWiseQC> rCLOLotWiseQCList) {
        set(PROPERTY_RCLOLOTWISEQCLIST, rCLOLotWiseQCList);
    }

}
