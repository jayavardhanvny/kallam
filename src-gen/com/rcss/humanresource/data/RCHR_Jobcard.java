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
package com.rcss.humanresource.data;

import com.redcarpet.epcg.data.EpcgMof;
import com.redcarpet.goodsissue.data.RCGI_MaterialIssue;
import com.redcarpet.goodsissue.data.RcgiMaterialIndent;
import com.redcarpet.goodsissue.data.RcgiMaterialIndentLines;

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
/**
 * Entity class for entity RCHR_Jobcard (stored in table RCHR_Jobcard).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Jobcard extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Jobcard";
    public static final String ENTITY_NAME = "RCHR_Jobcard";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRQUALITYSTANDARD = "rchrQualitystandard";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_ORDERNO = "orderNo";
    public static final String PROPERTY_ORDERDATE = "orderdate";
    public static final String PROPERTY_DELIVERYDATE = "deliverydate";
    public static final String PROPERTY_ORDERQUANTITY = "orderquantity";
    public static final String PROPERTY_TOWRAPMTRS = "towrapmtrs";
    public static final String PROPERTY_WARPYARNREQ = "warpyarnreq";
    public static final String PROPERTY_WEFTYARNREQ = "weftyarnreq";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_DOCUMENTSTATUS = "documentStatus";
    public static final String PROPERTY_MOFDATE = "mofdate";
    public static final String PROPERTY_TOWARPDATE = "towarpdate";
    public static final String PROPERTY_TOLOOMDATE = "toloomdate";
    public static final String PROPERTY_LOOMPRDCTN = "loomprdctn";
    public static final String PROPERTY_NOOFLLOMS = "nooflloms";
    public static final String PROPERTY_PPI = "ppi";
    public static final String PROPERTY_CONSTRUCTION = "construction";
    public static final String PROPERTY_SIZINGDATE = "sizingdate";
    public static final String PROPERTY_REASONFORLEAVE = "reasonForLeave";
    public static final String PROPERTY_RCHRORDERSTATUS = "rchrOrderstatus";
    public static final String PROPERTY_WARPCUSTOMER = "warpcustomer";
    public static final String PROPERTY_WEFTCUSTOMER = "weftcustomer";
    public static final String PROPERTY_EPCGMOF = "epcgMof";
    public static final String PROPERTY_WARPISSUED = "warpissued";
    public static final String PROPERTY_WEFTISSUED = "weftissued";
    public static final String PROPERTY_WARPREMAININGQTY = "warpremainingqty";
    public static final String PROPERTY_WEFTREMAININGQTY = "weftremainingqty";
    public static final String PROPERTY_RCGIMATERIALINDENTLIST = "rCGIMaterialIndentList";
    public static final String PROPERTY_RCGIMATERIALINDENTLINESLIST = "rCGIMaterialIndentLinesList";
    public static final String PROPERTY_RCGIMATERIALISSUELIST = "rCGIMaterialIssueList";
    public static final String PROPERTY_RCHRDIRECTWARPLIST = "rCHRDirectwarpList";
    public static final String PROPERTY_RCHRINSPECTIONSHEETLIST = "rCHRInspectionsheetList";
    public static final String PROPERTY_RCHRSIZINGSHEETLIST = "rCHRSizingsheetList";
    public static final String PROPERTY_RCHRAUTODRAWINGLIST = "rchrAutodrawingList";
    public static final String PROPERTY_RCHRLOOMSDATALIST = "rchrLoomsdataList";

    public RCHR_Jobcard() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_DOCUMENTSTATUS, "DR");
        setDefaultValue(PROPERTY_LOOMPRDCTN, new BigDecimal(0));
        setDefaultValue(PROPERTY_WARPISSUED, new BigDecimal(0));
        setDefaultValue(PROPERTY_WEFTISSUED, new BigDecimal(0));
        setDefaultValue(PROPERTY_WARPREMAININGQTY, new BigDecimal(0));
        setDefaultValue(PROPERTY_WEFTREMAININGQTY, new BigDecimal(0));
        setDefaultValue(PROPERTY_RCGIMATERIALINDENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMATERIALINDENTLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMATERIALISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDIRECTWARPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRINSPECTIONSHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGSHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAUTODRAWINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLOOMSDATALIST, new ArrayList<Object>());
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

    public RCHRQualitystandard getRchrQualitystandard() {
        return (RCHRQualitystandard) get(PROPERTY_RCHRQUALITYSTANDARD);
    }

    public void setRchrQualitystandard(RCHRQualitystandard rchrQualitystandard) {
        set(PROPERTY_RCHRQUALITYSTANDARD, rchrQualitystandard);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public String getOrderNo() {
        return (String) get(PROPERTY_ORDERNO);
    }

    public void setOrderNo(String orderNo) {
        set(PROPERTY_ORDERNO, orderNo);
    }

    public Date getOrderdate() {
        return (Date) get(PROPERTY_ORDERDATE);
    }

    public void setOrderdate(Date orderdate) {
        set(PROPERTY_ORDERDATE, orderdate);
    }

    public Date getDeliverydate() {
        return (Date) get(PROPERTY_DELIVERYDATE);
    }

    public void setDeliverydate(Date deliverydate) {
        set(PROPERTY_DELIVERYDATE, deliverydate);
    }

    public BigDecimal getOrderquantity() {
        return (BigDecimal) get(PROPERTY_ORDERQUANTITY);
    }

    public void setOrderquantity(BigDecimal orderquantity) {
        set(PROPERTY_ORDERQUANTITY, orderquantity);
    }

    public BigDecimal getTowrapmtrs() {
        return (BigDecimal) get(PROPERTY_TOWRAPMTRS);
    }

    public void setTowrapmtrs(BigDecimal towrapmtrs) {
        set(PROPERTY_TOWRAPMTRS, towrapmtrs);
    }

    public BigDecimal getWarpyarnreq() {
        return (BigDecimal) get(PROPERTY_WARPYARNREQ);
    }

    public void setWarpyarnreq(BigDecimal warpyarnreq) {
        set(PROPERTY_WARPYARNREQ, warpyarnreq);
    }

    public BigDecimal getWeftyarnreq() {
        return (BigDecimal) get(PROPERTY_WEFTYARNREQ);
    }

    public void setWeftyarnreq(BigDecimal weftyarnreq) {
        set(PROPERTY_WEFTYARNREQ, weftyarnreq);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public String getDocumentStatus() {
        return (String) get(PROPERTY_DOCUMENTSTATUS);
    }

    public void setDocumentStatus(String documentStatus) {
        set(PROPERTY_DOCUMENTSTATUS, documentStatus);
    }

    public Date getMofdate() {
        return (Date) get(PROPERTY_MOFDATE);
    }

    public void setMofdate(Date mofdate) {
        set(PROPERTY_MOFDATE, mofdate);
    }

    public Date getTowarpdate() {
        return (Date) get(PROPERTY_TOWARPDATE);
    }

    public void setTowarpdate(Date towarpdate) {
        set(PROPERTY_TOWARPDATE, towarpdate);
    }

    public Date getToloomdate() {
        return (Date) get(PROPERTY_TOLOOMDATE);
    }

    public void setToloomdate(Date toloomdate) {
        set(PROPERTY_TOLOOMDATE, toloomdate);
    }

    public BigDecimal getLoomprdctn() {
        return (BigDecimal) get(PROPERTY_LOOMPRDCTN);
    }

    public void setLoomprdctn(BigDecimal loomprdctn) {
        set(PROPERTY_LOOMPRDCTN, loomprdctn);
    }

    public BigDecimal getNooflloms() {
        return (BigDecimal) get(PROPERTY_NOOFLLOMS);
    }

    public void setNooflloms(BigDecimal nooflloms) {
        set(PROPERTY_NOOFLLOMS, nooflloms);
    }

    public BigDecimal getPpi() {
        return (BigDecimal) get(PROPERTY_PPI);
    }

    public void setPpi(BigDecimal ppi) {
        set(PROPERTY_PPI, ppi);
    }

    public String getConstruction() {
        return (String) get(PROPERTY_CONSTRUCTION);
    }

    public void setConstruction(String construction) {
        set(PROPERTY_CONSTRUCTION, construction);
    }

    public Date getSizingdate() {
        return (Date) get(PROPERTY_SIZINGDATE);
    }

    public void setSizingdate(Date sizingdate) {
        set(PROPERTY_SIZINGDATE, sizingdate);
    }

    public String getReasonForLeave() {
        return (String) get(PROPERTY_REASONFORLEAVE);
    }

    public void setReasonForLeave(String reasonForLeave) {
        set(PROPERTY_REASONFORLEAVE, reasonForLeave);
    }

    public RCHR_Orderstatus getRchrOrderstatus() {
        return (RCHR_Orderstatus) get(PROPERTY_RCHRORDERSTATUS);
    }

    public void setRchrOrderstatus(RCHR_Orderstatus rchrOrderstatus) {
        set(PROPERTY_RCHRORDERSTATUS, rchrOrderstatus);
    }

    public String getWarpcustomer() {
        return (String) get(PROPERTY_WARPCUSTOMER);
    }

    public void setWarpcustomer(String warpcustomer) {
        set(PROPERTY_WARPCUSTOMER, warpcustomer);
    }

    public String getWeftcustomer() {
        return (String) get(PROPERTY_WEFTCUSTOMER);
    }

    public void setWeftcustomer(String weftcustomer) {
        set(PROPERTY_WEFTCUSTOMER, weftcustomer);
    }

    public EpcgMof getEpcgMof() {
        return (EpcgMof) get(PROPERTY_EPCGMOF);
    }

    public void setEpcgMof(EpcgMof epcgMof) {
        set(PROPERTY_EPCGMOF, epcgMof);
    }

    public BigDecimal getWarpissued() {
        return (BigDecimal) get(PROPERTY_WARPISSUED);
    }

    public void setWarpissued(BigDecimal warpissued) {
        set(PROPERTY_WARPISSUED, warpissued);
    }

    public BigDecimal getWeftissued() {
        return (BigDecimal) get(PROPERTY_WEFTISSUED);
    }

    public void setWeftissued(BigDecimal weftissued) {
        set(PROPERTY_WEFTISSUED, weftissued);
    }

    public BigDecimal getWarpremainingqty() {
        return (BigDecimal) get(PROPERTY_WARPREMAININGQTY);
    }

    public void setWarpremainingqty(BigDecimal warpremainingqty) {
        set(PROPERTY_WARPREMAININGQTY, warpremainingqty);
    }

    public BigDecimal getWeftremainingqty() {
        return (BigDecimal) get(PROPERTY_WEFTREMAININGQTY);
    }

    public void setWeftremainingqty(BigDecimal weftremainingqty) {
        set(PROPERTY_WEFTREMAININGQTY, weftremainingqty);
    }

    @SuppressWarnings("unchecked")
    public List<RcgiMaterialIndent> getRCGIMaterialIndentList() {
        return (List<RcgiMaterialIndent>) get(PROPERTY_RCGIMATERIALINDENTLIST);
    }

    public void setRCGIMaterialIndentList(List<RcgiMaterialIndent> rCGIMaterialIndentList) {
        set(PROPERTY_RCGIMATERIALINDENTLIST, rCGIMaterialIndentList);
    }

    @SuppressWarnings("unchecked")
    public List<RcgiMaterialIndentLines> getRCGIMaterialIndentLinesList() {
        return (List<RcgiMaterialIndentLines>) get(PROPERTY_RCGIMATERIALINDENTLINESLIST);
    }

    public void setRCGIMaterialIndentLinesList(List<RcgiMaterialIndentLines> rCGIMaterialIndentLinesList) {
        set(PROPERTY_RCGIMATERIALINDENTLINESLIST, rCGIMaterialIndentLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MaterialIssue> getRCGIMaterialIssueList() {
        return (List<RCGI_MaterialIssue>) get(PROPERTY_RCGIMATERIALISSUELIST);
    }

    public void setRCGIMaterialIssueList(List<RCGI_MaterialIssue> rCGIMaterialIssueList) {
        set(PROPERTY_RCGIMATERIALISSUELIST, rCGIMaterialIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Directwarp> getRCHRDirectwarpList() {
        return (List<RCHR_Directwarp>) get(PROPERTY_RCHRDIRECTWARPLIST);
    }

    public void setRCHRDirectwarpList(List<RCHR_Directwarp> rCHRDirectwarpList) {
        set(PROPERTY_RCHRDIRECTWARPLIST, rCHRDirectwarpList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Inspectionsheet> getRCHRInspectionsheetList() {
        return (List<RCHR_Inspectionsheet>) get(PROPERTY_RCHRINSPECTIONSHEETLIST);
    }

    public void setRCHRInspectionsheetList(List<RCHR_Inspectionsheet> rCHRInspectionsheetList) {
        set(PROPERTY_RCHRINSPECTIONSHEETLIST, rCHRInspectionsheetList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Sizingsheet> getRCHRSizingsheetList() {
        return (List<RCHR_Sizingsheet>) get(PROPERTY_RCHRSIZINGSHEETLIST);
    }

    public void setRCHRSizingsheetList(List<RCHR_Sizingsheet> rCHRSizingsheetList) {
        set(PROPERTY_RCHRSIZINGSHEETLIST, rCHRSizingsheetList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrAutodrawing> getRchrAutodrawingList() {
        return (List<RchrAutodrawing>) get(PROPERTY_RCHRAUTODRAWINGLIST);
    }

    public void setRchrAutodrawingList(List<RchrAutodrawing> rchrAutodrawingList) {
        set(PROPERTY_RCHRAUTODRAWINGLIST, rchrAutodrawingList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrLoomsdata> getRchrLoomsdataList() {
        return (List<RchrLoomsdata>) get(PROPERTY_RCHRLOOMSDATALIST);
    }

    public void setRchrLoomsdataList(List<RchrLoomsdata> rchrLoomsdataList) {
        set(PROPERTY_RCHRLOOMSDATALIST, rchrLoomsdataList);
    }

}
