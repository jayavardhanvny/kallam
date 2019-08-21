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
package com.redcarpet.production.data;

import com.rcss.humanresource.data.RCHR_Inspectionsheet;
import com.redcarpet.goodsissue.data.RCGIDiLines;
import com.redcarpet.goodsissue.data.RCGI_GoodsIssue_Line;
import com.redcarpet.goodsissue.data.RCGI_MiLines;
import com.redcarpet.goodsissue.data.RCGI_MrLines;
import com.redcarpet.quality.data.RcqaCardingneps;
import com.redcarpet.quality.data.RcqaCombernoil;
import com.redcarpet.quality.data.RcqaCombersu;
import com.redcarpet.quality.data.RcqaOebreakage;
import com.redcarpet.quality.data.RcqaRingframebreak;
import com.redcarpet.quality.data.RcqaRingframeidle;
import com.redcarpet.quality.data.RcqaRsbapercentage;
import com.redcarpet.quality.data.RcqaSimplexstr;
import com.redcarpet.quality.data.RcqaWrapbreak;

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
import org.openbravo.model.procurement.RequisitionLine;
/**
 * Entity class for entity RCPR_Machine (stored in table RCPR_Machine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPRMachine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPR_Machine";
    public static final String ENTITY_NAME = "RCPR_Machine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_MAKEINYEAR = "makeInYear";
    public static final String PROPERTY_MACHINECATEGORY = "machineCategory";
    public static final String PROPERTY_UNIT = "unit";
    public static final String PROPERTY_SHED = "shed";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_MANUFACTURERNAME = "manufacturerName";
    public static final String PROPERTY_TOTALRUN = "totalrun";
    public static final String PROPERTY_SPINDLES = "spindles";
    public static final String PROPERTY_MACHINEPROCESS = "machineProcess";
    public static final String PROPERTY_MACHINEMODEL = "machineModel";
    public static final String PROPERTY_MACHINESERIALNO = "machineSerialNo";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_PROCUREMENTREQUISITIONLINELIST = "procurementRequisitionLineList";
    public static final String PROPERTY_RCGIDILINESLIST = "rCGIDiLinesList";
    public static final String PROPERTY_RCGIGOODSISSUELINELIST = "rCGIGoodsIssueLineList";
    public static final String PROPERTY_RCGIMILINESLIST = "rCGIMiLinesList";
    public static final String PROPERTY_RCGIMRLINESLIST = "rCGIMrLinesList";
    public static final String PROPERTY_RCHRINSPECTIONSHEETLIST = "rCHRInspectionsheetList";
    public static final String PROPERTY_RCPRAUTOCLONELIST = "rCPRAutocloneList";
    public static final String PROPERTY_RCPRCARDINGLIST = "rCPRCardingList";
    public static final String PROPERTY_RCPRCOMBERSLIST = "rCPRCombersList";
    public static final String PROPERTY_RCPRCONSUMABLESLIST = "rCPRConsumablesList";
    public static final String PROPERTY_RCPRDOUBLINGLIST = "rCPRDoublingList";
    public static final String PROPERTY_RCPRDRAWFRAMEBREAKERLIST = "rCPRDrawframebreakerList";
    public static final String PROPERTY_RCPRLAPFORMERLIST = "rCPRLapFormerList";
    public static final String PROPERTY_RCPRMACHINEEMAINTENANCELIST = "rCPRMachineEMaintenanceList";
    public static final String PROPERTY_RCPRMACHINEMMAINTENANCELIST = "rCPRMachineMMaintenanceList";
    public static final String PROPERTY_RCPROELIST = "rCPROeList";
    public static final String PROPERTY_RCPRRSBDRAWFRAMELIST = "rCPRRSBDrawFrameList";
    public static final String PROPERTY_RCPRSIMPLEXLIST = "rCPRSimplexList";
    public static final String PROPERTY_RCPRSPINNINGLIST = "rCPRSpinningList";
    public static final String PROPERTY_RCPRTFOLIST = "rCPRTFOList";
    public static final String PROPERTY_RCPRTASKLIST = "rCPRTaskList";
    public static final String PROPERTY_RCQACARDINGNEPSLIST = "rCQACardingnepsList";
    public static final String PROPERTY_RCQACOMBERNOILLIST = "rCQACombernoilList";
    public static final String PROPERTY_RCQACOMBERSULIST = "rCQACombersuList";
    public static final String PROPERTY_RCQAOEBREAKAGELIST = "rCQAOebreakageList";
    public static final String PROPERTY_RCQARINGFRAMEBREAKLIST = "rCQARingframebreakList";
    public static final String PROPERTY_RCQARINGFRAMEIDLELIST = "rCQARingframeidleList";
    public static final String PROPERTY_RCQARSBAPERCENTAGELIST = "rCQARsbapercentageList";
    public static final String PROPERTY_RCQASIMPLEXSTRLIST = "rCQASimplexstrList";
    public static final String PROPERTY_RCQAWRAPBREAKLIST = "rCQAWrapbreakList";

    public RCPRMachine() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_PROCUREMENTREQUISITIONLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDILINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIGOODSISSUELINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMILINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMRLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRINSPECTIONSHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRAUTOCLONELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRCARDINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRCOMBERSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRCONSUMABLESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRDOUBLINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRDRAWFRAMEBREAKERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRLAPFORMERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRMACHINEEMAINTENANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRMACHINEMMAINTENANCELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPROELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRRSBDRAWFRAMELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRSIMPLEXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRSPINNINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRTFOLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRTASKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQACARDINGNEPSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQACOMBERNOILLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQACOMBERSULIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQAOEBREAKAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQARINGFRAMEBREAKLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQARINGFRAMEIDLELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQARSBAPERCENTAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQASIMPLEXSTRLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCQAWRAPBREAKLIST, new ArrayList<Object>());
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

    public Date getMakeInYear() {
        return (Date) get(PROPERTY_MAKEINYEAR);
    }

    public void setMakeInYear(Date makeInYear) {
        set(PROPERTY_MAKEINYEAR, makeInYear);
    }

    public RCPR_MachineCategory getMachineCategory() {
        return (RCPR_MachineCategory) get(PROPERTY_MACHINECATEGORY);
    }

    public void setMachineCategory(RCPR_MachineCategory machineCategory) {
        set(PROPERTY_MACHINECATEGORY, machineCategory);
    }

    public RCPRUnit getUnit() {
        return (RCPRUnit) get(PROPERTY_UNIT);
    }

    public void setUnit(RCPRUnit unit) {
        set(PROPERTY_UNIT, unit);
    }

    public RCPRShed getShed() {
        return (RCPRShed) get(PROPERTY_SHED);
    }

    public void setShed(RCPRShed shed) {
        set(PROPERTY_SHED, shed);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public String getManufacturerName() {
        return (String) get(PROPERTY_MANUFACTURERNAME);
    }

    public void setManufacturerName(String manufacturerName) {
        set(PROPERTY_MANUFACTURERNAME, manufacturerName);
    }

    public Date getTotalrun() {
        return (Date) get(PROPERTY_TOTALRUN);
    }

    public void setTotalrun(Date totalrun) {
        set(PROPERTY_TOTALRUN, totalrun);
    }

    public BigDecimal getSpindles() {
        return (BigDecimal) get(PROPERTY_SPINDLES);
    }

    public void setSpindles(BigDecimal spindles) {
        set(PROPERTY_SPINDLES, spindles);
    }

    public RCPR_MachineProcess getMachineProcess() {
        return (RCPR_MachineProcess) get(PROPERTY_MACHINEPROCESS);
    }

    public void setMachineProcess(RCPR_MachineProcess machineProcess) {
        set(PROPERTY_MACHINEPROCESS, machineProcess);
    }

    public String getMachineModel() {
        return (String) get(PROPERTY_MACHINEMODEL);
    }

    public void setMachineModel(String machineModel) {
        set(PROPERTY_MACHINEMODEL, machineModel);
    }

    public String getMachineSerialNo() {
        return (String) get(PROPERTY_MACHINESERIALNO);
    }

    public void setMachineSerialNo(String machineSerialNo) {
        set(PROPERTY_MACHINESERIALNO, machineSerialNo);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    @SuppressWarnings("unchecked")
    public List<RequisitionLine> getProcurementRequisitionLineList() {
        return (List<RequisitionLine>) get(PROPERTY_PROCUREMENTREQUISITIONLINELIST);
    }

    public void setProcurementRequisitionLineList(List<RequisitionLine> procurementRequisitionLineList) {
        set(PROPERTY_PROCUREMENTREQUISITIONLINELIST, procurementRequisitionLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDiLines> getRCGIDiLinesList() {
        return (List<RCGIDiLines>) get(PROPERTY_RCGIDILINESLIST);
    }

    public void setRCGIDiLinesList(List<RCGIDiLines> rCGIDiLinesList) {
        set(PROPERTY_RCGIDILINESLIST, rCGIDiLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_GoodsIssue_Line> getRCGIGoodsIssueLineList() {
        return (List<RCGI_GoodsIssue_Line>) get(PROPERTY_RCGIGOODSISSUELINELIST);
    }

    public void setRCGIGoodsIssueLineList(List<RCGI_GoodsIssue_Line> rCGIGoodsIssueLineList) {
        set(PROPERTY_RCGIGOODSISSUELINELIST, rCGIGoodsIssueLineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MiLines> getRCGIMiLinesList() {
        return (List<RCGI_MiLines>) get(PROPERTY_RCGIMILINESLIST);
    }

    public void setRCGIMiLinesList(List<RCGI_MiLines> rCGIMiLinesList) {
        set(PROPERTY_RCGIMILINESLIST, rCGIMiLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGI_MrLines> getRCGIMrLinesList() {
        return (List<RCGI_MrLines>) get(PROPERTY_RCGIMRLINESLIST);
    }

    public void setRCGIMrLinesList(List<RCGI_MrLines> rCGIMrLinesList) {
        set(PROPERTY_RCGIMRLINESLIST, rCGIMrLinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Inspectionsheet> getRCHRInspectionsheetList() {
        return (List<RCHR_Inspectionsheet>) get(PROPERTY_RCHRINSPECTIONSHEETLIST);
    }

    public void setRCHRInspectionsheetList(List<RCHR_Inspectionsheet> rCHRInspectionsheetList) {
        set(PROPERTY_RCHRINSPECTIONSHEETLIST, rCHRInspectionsheetList);
    }

    @SuppressWarnings("unchecked")
    public List<Autoclone> getRCPRAutocloneList() {
        return (List<Autoclone>) get(PROPERTY_RCPRAUTOCLONELIST);
    }

    public void setRCPRAutocloneList(List<Autoclone> rCPRAutocloneList) {
        set(PROPERTY_RCPRAUTOCLONELIST, rCPRAutocloneList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPRCarding> getRCPRCardingList() {
        return (List<RCPRCarding>) get(PROPERTY_RCPRCARDINGLIST);
    }

    public void setRCPRCardingList(List<RCPRCarding> rCPRCardingList) {
        set(PROPERTY_RCPRCARDINGLIST, rCPRCardingList);
    }

    @SuppressWarnings("unchecked")
    public List<Combers> getRCPRCombersList() {
        return (List<Combers>) get(PROPERTY_RCPRCOMBERSLIST);
    }

    public void setRCPRCombersList(List<Combers> rCPRCombersList) {
        set(PROPERTY_RCPRCOMBERSLIST, rCPRCombersList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_Consumables> getRCPRConsumablesList() {
        return (List<RCPR_Consumables>) get(PROPERTY_RCPRCONSUMABLESLIST);
    }

    public void setRCPRConsumablesList(List<RCPR_Consumables> rCPRConsumablesList) {
        set(PROPERTY_RCPRCONSUMABLESLIST, rCPRConsumablesList);
    }

    @SuppressWarnings("unchecked")
    public List<Doubling> getRCPRDoublingList() {
        return (List<Doubling>) get(PROPERTY_RCPRDOUBLINGLIST);
    }

    public void setRCPRDoublingList(List<Doubling> rCPRDoublingList) {
        set(PROPERTY_RCPRDOUBLINGLIST, rCPRDoublingList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_DrawFrameBreaker> getRCPRDrawframebreakerList() {
        return (List<RCPR_DrawFrameBreaker>) get(PROPERTY_RCPRDRAWFRAMEBREAKERLIST);
    }

    public void setRCPRDrawframebreakerList(List<RCPR_DrawFrameBreaker> rCPRDrawframebreakerList) {
        set(PROPERTY_RCPRDRAWFRAMEBREAKERLIST, rCPRDrawframebreakerList);
    }

    @SuppressWarnings("unchecked")
    public List<LapFormer> getRCPRLapFormerList() {
        return (List<LapFormer>) get(PROPERTY_RCPRLAPFORMERLIST);
    }

    public void setRCPRLapFormerList(List<LapFormer> rCPRLapFormerList) {
        set(PROPERTY_RCPRLAPFORMERLIST, rCPRLapFormerList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_Machine_ElectricalMaintenance> getRCPRMachineEMaintenanceList() {
        return (List<RCPR_Machine_ElectricalMaintenance>) get(PROPERTY_RCPRMACHINEEMAINTENANCELIST);
    }

    public void setRCPRMachineEMaintenanceList(List<RCPR_Machine_ElectricalMaintenance> rCPRMachineEMaintenanceList) {
        set(PROPERTY_RCPRMACHINEEMAINTENANCELIST, rCPRMachineEMaintenanceList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_Machine_Mechanical_Maintenance> getRCPRMachineMMaintenanceList() {
        return (List<RCPR_Machine_Mechanical_Maintenance>) get(PROPERTY_RCPRMACHINEMMAINTENANCELIST);
    }

    public void setRCPRMachineMMaintenanceList(List<RCPR_Machine_Mechanical_Maintenance> rCPRMachineMMaintenanceList) {
        set(PROPERTY_RCPRMACHINEMMAINTENANCELIST, rCPRMachineMMaintenanceList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_Oe> getRCPROeList() {
        return (List<RCPR_Oe>) get(PROPERTY_RCPROELIST);
    }

    public void setRCPROeList(List<RCPR_Oe> rCPROeList) {
        set(PROPERTY_RCPROELIST, rCPROeList);
    }

    @SuppressWarnings("unchecked")
    public List<RSBDrawFrame> getRCPRRSBDrawFrameList() {
        return (List<RSBDrawFrame>) get(PROPERTY_RCPRRSBDRAWFRAMELIST);
    }

    public void setRCPRRSBDrawFrameList(List<RSBDrawFrame> rCPRRSBDrawFrameList) {
        set(PROPERTY_RCPRRSBDRAWFRAMELIST, rCPRRSBDrawFrameList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_Simplex> getRCPRSimplexList() {
        return (List<RCPR_Simplex>) get(PROPERTY_RCPRSIMPLEXLIST);
    }

    public void setRCPRSimplexList(List<RCPR_Simplex> rCPRSimplexList) {
        set(PROPERTY_RCPRSIMPLEXLIST, rCPRSimplexList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_Spinning> getRCPRSpinningList() {
        return (List<RCPR_Spinning>) get(PROPERTY_RCPRSPINNINGLIST);
    }

    public void setRCPRSpinningList(List<RCPR_Spinning> rCPRSpinningList) {
        set(PROPERTY_RCPRSPINNINGLIST, rCPRSpinningList);
    }

    @SuppressWarnings("unchecked")
    public List<TFO> getRCPRTFOList() {
        return (List<TFO>) get(PROPERTY_RCPRTFOLIST);
    }

    public void setRCPRTFOList(List<TFO> rCPRTFOList) {
        set(PROPERTY_RCPRTFOLIST, rCPRTFOList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPR_Task> getRCPRTaskList() {
        return (List<RCPR_Task>) get(PROPERTY_RCPRTASKLIST);
    }

    public void setRCPRTaskList(List<RCPR_Task> rCPRTaskList) {
        set(PROPERTY_RCPRTASKLIST, rCPRTaskList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaCardingneps> getRCQACardingnepsList() {
        return (List<RcqaCardingneps>) get(PROPERTY_RCQACARDINGNEPSLIST);
    }

    public void setRCQACardingnepsList(List<RcqaCardingneps> rCQACardingnepsList) {
        set(PROPERTY_RCQACARDINGNEPSLIST, rCQACardingnepsList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaCombernoil> getRCQACombernoilList() {
        return (List<RcqaCombernoil>) get(PROPERTY_RCQACOMBERNOILLIST);
    }

    public void setRCQACombernoilList(List<RcqaCombernoil> rCQACombernoilList) {
        set(PROPERTY_RCQACOMBERNOILLIST, rCQACombernoilList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaCombersu> getRCQACombersuList() {
        return (List<RcqaCombersu>) get(PROPERTY_RCQACOMBERSULIST);
    }

    public void setRCQACombersuList(List<RcqaCombersu> rCQACombersuList) {
        set(PROPERTY_RCQACOMBERSULIST, rCQACombersuList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaOebreakage> getRCQAOebreakageList() {
        return (List<RcqaOebreakage>) get(PROPERTY_RCQAOEBREAKAGELIST);
    }

    public void setRCQAOebreakageList(List<RcqaOebreakage> rCQAOebreakageList) {
        set(PROPERTY_RCQAOEBREAKAGELIST, rCQAOebreakageList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaRingframebreak> getRCQARingframebreakList() {
        return (List<RcqaRingframebreak>) get(PROPERTY_RCQARINGFRAMEBREAKLIST);
    }

    public void setRCQARingframebreakList(List<RcqaRingframebreak> rCQARingframebreakList) {
        set(PROPERTY_RCQARINGFRAMEBREAKLIST, rCQARingframebreakList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaRingframeidle> getRCQARingframeidleList() {
        return (List<RcqaRingframeidle>) get(PROPERTY_RCQARINGFRAMEIDLELIST);
    }

    public void setRCQARingframeidleList(List<RcqaRingframeidle> rCQARingframeidleList) {
        set(PROPERTY_RCQARINGFRAMEIDLELIST, rCQARingframeidleList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaRsbapercentage> getRCQARsbapercentageList() {
        return (List<RcqaRsbapercentage>) get(PROPERTY_RCQARSBAPERCENTAGELIST);
    }

    public void setRCQARsbapercentageList(List<RcqaRsbapercentage> rCQARsbapercentageList) {
        set(PROPERTY_RCQARSBAPERCENTAGELIST, rCQARsbapercentageList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaSimplexstr> getRCQASimplexstrList() {
        return (List<RcqaSimplexstr>) get(PROPERTY_RCQASIMPLEXSTRLIST);
    }

    public void setRCQASimplexstrList(List<RcqaSimplexstr> rCQASimplexstrList) {
        set(PROPERTY_RCQASIMPLEXSTRLIST, rCQASimplexstrList);
    }

    @SuppressWarnings("unchecked")
    public List<RcqaWrapbreak> getRCQAWrapbreakList() {
        return (List<RcqaWrapbreak>) get(PROPERTY_RCQAWRAPBREAKLIST);
    }

    public void setRCQAWrapbreakList(List<RcqaWrapbreak> rCQAWrapbreakList) {
        set(PROPERTY_RCQAWRAPBREAKLIST, rCQAWrapbreakList);
    }

}
