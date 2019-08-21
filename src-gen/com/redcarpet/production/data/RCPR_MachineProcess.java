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

import com.redcarpet.quality.data.RcqaCardingneps;
import com.redcarpet.quality.data.RcqaCombernoil;
import com.redcarpet.quality.data.RcqaCombersu;
import com.redcarpet.quality.data.RcqaOebreakage;
import com.redcarpet.quality.data.RcqaRingframebreak;
import com.redcarpet.quality.data.RcqaRingframeidle;
import com.redcarpet.quality.data.RcqaRsbapercentage;
import com.redcarpet.quality.data.RcqaSimplexstr;
import com.redcarpet.quality.data.RcqaWrapbreak;

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
 * Entity class for entity RCPR_Machineprocess (stored in table RCPR_Machineprocess).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCPR_MachineProcess extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCPR_Machineprocess";
    public static final String ENTITY_NAME = "RCPR_Machineprocess";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_MACHINEPROCESSNAME = "machineProcessName";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_CARDING = "carding";
    public static final String PROPERTY_DRAWFRAMEBREAKER = "drawFrameBreaker";
    public static final String PROPERTY_RSBDRAWFRAMEBREAKER = "rSBDrawFrameBreaker";
    public static final String PROPERTY_LAPFORMER = "lapFormer";
    public static final String PROPERTY_COMBERS = "combers";
    public static final String PROPERTY_SIMPLEX = "simplex";
    public static final String PROPERTY_SPINNING = "spinning";
    public static final String PROPERTY_AUTOCONE = "autoCone";
    public static final String PROPERTY_TFO = "tFO";
    public static final String PROPERTY_DOUBLING = "doubling";
    public static final String PROPERTY_OE = "oE";
    public static final String PROPERTY_RCPRAUTOCLONELIST = "rCPRAutocloneList";
    public static final String PROPERTY_RCPRCARDINGLIST = "rCPRCardingList";
    public static final String PROPERTY_RCPRCOMBERSLIST = "rCPRCombersList";
    public static final String PROPERTY_RCPRCOUNTLIST = "rCPRCountList";
    public static final String PROPERTY_RCPRDOUBLINGLIST = "rCPRDoublingList";
    public static final String PROPERTY_RCPRDRAWFRAMEBREAKERLIST = "rCPRDrawframebreakerList";
    public static final String PROPERTY_RCPRLAPFORMERLIST = "rCPRLapFormerList";
    public static final String PROPERTY_RCPRMACHINELIST = "rCPRMachineList";
    public static final String PROPERTY_RCPROELIST = "rCPROeList";
    public static final String PROPERTY_RCPRRSBDRAWFRAMELIST = "rCPRRSBDrawFrameList";
    public static final String PROPERTY_RCPRSIMPLEXLIST = "rCPRSimplexList";
    public static final String PROPERTY_RCPRSPINNINGLIST = "rCPRSpinningList";
    public static final String PROPERTY_RCPRTFOLIST = "rCPRTFOList";
    public static final String PROPERTY_RCQACARDINGNEPSLIST = "rCQACardingnepsList";
    public static final String PROPERTY_RCQACOMBERNOILLIST = "rCQACombernoilList";
    public static final String PROPERTY_RCQACOMBERSULIST = "rCQACombersuList";
    public static final String PROPERTY_RCQAOEBREAKAGELIST = "rCQAOebreakageList";
    public static final String PROPERTY_RCQARINGFRAMEBREAKLIST = "rCQARingframebreakList";
    public static final String PROPERTY_RCQARINGFRAMEIDLELIST = "rCQARingframeidleList";
    public static final String PROPERTY_RCQARSBAPERCENTAGELIST = "rCQARsbapercentageList";
    public static final String PROPERTY_RCQASIMPLEXSTRLIST = "rCQASimplexstrList";
    public static final String PROPERTY_RCQAWRAPBREAKLIST = "rCQAWrapbreakList";

    public RCPR_MachineProcess() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CARDING, false);
        setDefaultValue(PROPERTY_DRAWFRAMEBREAKER, false);
        setDefaultValue(PROPERTY_RSBDRAWFRAMEBREAKER, false);
        setDefaultValue(PROPERTY_LAPFORMER, false);
        setDefaultValue(PROPERTY_COMBERS, false);
        setDefaultValue(PROPERTY_SIMPLEX, false);
        setDefaultValue(PROPERTY_SPINNING, false);
        setDefaultValue(PROPERTY_AUTOCONE, false);
        setDefaultValue(PROPERTY_TFO, false);
        setDefaultValue(PROPERTY_DOUBLING, false);
        setDefaultValue(PROPERTY_OE, false);
        setDefaultValue(PROPERTY_RCPRAUTOCLONELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRCARDINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRCOMBERSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRCOUNTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRDOUBLINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRDRAWFRAMEBREAKERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRLAPFORMERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRMACHINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPROELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRRSBDRAWFRAMELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRSIMPLEXLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRSPINNINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPRTFOLIST, new ArrayList<Object>());
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

    public String getMachineProcessName() {
        return (String) get(PROPERTY_MACHINEPROCESSNAME);
    }

    public void setMachineProcessName(String machineProcessName) {
        set(PROPERTY_MACHINEPROCESSNAME, machineProcessName);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public Boolean isCarding() {
        return (Boolean) get(PROPERTY_CARDING);
    }

    public void setCarding(Boolean carding) {
        set(PROPERTY_CARDING, carding);
    }

    public Boolean isDrawFrameBreaker() {
        return (Boolean) get(PROPERTY_DRAWFRAMEBREAKER);
    }

    public void setDrawFrameBreaker(Boolean drawFrameBreaker) {
        set(PROPERTY_DRAWFRAMEBREAKER, drawFrameBreaker);
    }

    public Boolean isRSBDrawFrameBreaker() {
        return (Boolean) get(PROPERTY_RSBDRAWFRAMEBREAKER);
    }

    public void setRSBDrawFrameBreaker(Boolean rSBDrawFrameBreaker) {
        set(PROPERTY_RSBDRAWFRAMEBREAKER, rSBDrawFrameBreaker);
    }

    public Boolean isLapFormer() {
        return (Boolean) get(PROPERTY_LAPFORMER);
    }

    public void setLapFormer(Boolean lapFormer) {
        set(PROPERTY_LAPFORMER, lapFormer);
    }

    public Boolean isCombers() {
        return (Boolean) get(PROPERTY_COMBERS);
    }

    public void setCombers(Boolean combers) {
        set(PROPERTY_COMBERS, combers);
    }

    public Boolean isSimplex() {
        return (Boolean) get(PROPERTY_SIMPLEX);
    }

    public void setSimplex(Boolean simplex) {
        set(PROPERTY_SIMPLEX, simplex);
    }

    public Boolean isSpinning() {
        return (Boolean) get(PROPERTY_SPINNING);
    }

    public void setSpinning(Boolean spinning) {
        set(PROPERTY_SPINNING, spinning);
    }

    public Boolean isAutoCone() {
        return (Boolean) get(PROPERTY_AUTOCONE);
    }

    public void setAutoCone(Boolean autoCone) {
        set(PROPERTY_AUTOCONE, autoCone);
    }

    public Boolean isTFO() {
        return (Boolean) get(PROPERTY_TFO);
    }

    public void setTFO(Boolean tFO) {
        set(PROPERTY_TFO, tFO);
    }

    public Boolean isDoubling() {
        return (Boolean) get(PROPERTY_DOUBLING);
    }

    public void setDoubling(Boolean doubling) {
        set(PROPERTY_DOUBLING, doubling);
    }

    public Boolean isOE() {
        return (Boolean) get(PROPERTY_OE);
    }

    public void setOE(Boolean oE) {
        set(PROPERTY_OE, oE);
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
    public List<RCPRCount> getRCPRCountList() {
        return (List<RCPRCount>) get(PROPERTY_RCPRCOUNTLIST);
    }

    public void setRCPRCountList(List<RCPRCount> rCPRCountList) {
        set(PROPERTY_RCPRCOUNTLIST, rCPRCountList);
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
    public List<RCPRMachine> getRCPRMachineList() {
        return (List<RCPRMachine>) get(PROPERTY_RCPRMACHINELIST);
    }

    public void setRCPRMachineList(List<RCPRMachine> rCPRMachineList) {
        set(PROPERTY_RCPRMACHINELIST, rCPRMachineList);
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
