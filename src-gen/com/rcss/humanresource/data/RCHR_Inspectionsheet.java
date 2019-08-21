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

import com.redcarpet.payroll.data.RCPLLoom;
import com.redcarpet.payroll.data.RCPL_Insploomctgry;
import com.redcarpet.production.data.RCPRMachine;
import com.redcarpet.production.data.RCPRShift;

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
 * Entity class for entity RCHR_Inspectionsheet (stored in table RCHR_Inspectionsheet).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHR_Inspectionsheet extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Inspectionsheet";
    public static final String ENTITY_NAME = "RCHR_Inspectionsheet";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRQUALITYSTANDARD = "rchrQualitystandard";
    public static final String PROPERTY_QUALITYNO = "qualityno";
    public static final String PROPERTY_DOFFDATE = "doffdate";
    public static final String PROPERTY_RCPRSHIFT = "rcprShift";
    public static final String PROPERTY_INSPDATE = "inspdate";
    public static final String PROPERTY_INSPSHIFT = "inspshift";
    public static final String PROPERTY_PIECENO = "pieceno";
    public static final String PROPERTY_WARPWEFTCOUNT = "warpweftcount";
    public static final String PROPERTY_WIDTHININCHES = "widthininches";
    public static final String PROPERTY_SELVEDGEWIDTHR = "selvedgewidthr";
    public static final String PROPERTY_SELVEDGEWIDTHL = "selvedgewidthl";
    public static final String PROPERTY_FRINGELENTHR = "fringelenthr";
    public static final String PROPERTY_FRINGELENTHL = "fringelenthl";
    public static final String PROPERTY_LOOMMTR = "loommtr";
    public static final String PROPERTY_TOTALMTR = "totalmtr";
    public static final String PROPERTY_TWILLDRILL = "twilldrill";
    public static final String PROPERTY_STAINDIR = "staindir";
    public static final String PROPERTY_SELVEDGE = "selvedge";
    public static final String PROPERTY_INSPECTION = "inspection";
    public static final String PROPERTY_EMPLOYEE = "employee";
    public static final String PROPERTY_RCPLLOOM = "rcplLoom";
    public static final String PROPERTY_CUTLOOKINGVALUE = "cutlookingvalue";
    public static final String PROPERTY_ATOTAL = "atotal";
    public static final String PROPERTY_AONETOTAL = "aonetotal";
    public static final String PROPERTY_BTOTAL = "btotal";
    public static final String PROPERTY_SLTOTAL = "sltotal";
    public static final String PROPERTY_REJECTIONEFFI = "rejectioneffi";
    public static final String PROPERTY_SLREJECTIONEFFI = "slrejectioneffi";
    public static final String PROPERTY_ORDERQUANTITY = "orderquantity";
    public static final String PROPERTY_REMAININGQUANTITY = "remainingquantity";
    public static final String PROPERTY_EFFICIENCY = "efficiency";
    public static final String PROPERTY_REMARK = "remark";
    public static final String PROPERTY_REMARKS = "remarks";
    public static final String PROPERTY_EPI = "epi";
    public static final String PROPERTY_PPI = "ppi";
    public static final String PROPERTY_RCHRINSPWEAVE = "rchrInspweave";
    public static final String PROPERTY_RCPLINSPLOOMCTGRY = "rcplInsploomctgry";
    public static final String PROPERTY_GROSSWEIGHT = "grossweight";
    public static final String PROPERTY_GLM = "glm";
    public static final String PROPERTY_DATAEOP = "dataeop";
    public static final String PROPERTY_REMAINGLM = "remainglm";
    public static final String PROPERTY_RCHRPIECENOLIST = "rchrPiecenolist";
    public static final String PROPERTY_TOTALFOUR = "totalfour";
    public static final String PROPERTY_TOTALTWO = "totaltwo";
    public static final String PROPERTY_TOTALPOINTS = "totalpoints";
    public static final String PROPERTY_RCPRMACHINE = "rcprMachine";
    public static final String PROPERTY_RCHRINSPROLLTYPE = "rchrInsprolltype";
    public static final String PROPERTY_RCHRINSPCLOTHTYPE = "rchrInspclothtype";
    public static final String PROPERTY_RCHRPIECESTATUS = "rchrPiecestatus";
    public static final String PROPERTY_MENIDNGSTATUS = "menidngstatus";
    public static final String PROPERTY_MENIDNGPROCESS = "menidngprocess";
    public static final String PROPERTY_GENERATEROLLNO = "generaterollno";
    public static final String PROPERTY_RCHRJOBCARD = "rchrJobcard";
    public static final String PROPERTY_TOWRAPMTRS = "towrapmtrs";
    public static final String PROPERTY_WRAPDONEMTRS = "wrapdonemtrs";
    public static final String PROPERTY_REMAININGWRAPMTRS = "remainingwrapmtrs";
    public static final String PROPERTY_RCHRINSPCATEGORYLINELIST = "rCHRInspcategorylineList";
    public static final String PROPERTY_RCHRINSPSHEETLINESLIST = "rCHRInspsheetlinesList";
    public static final String PROPERTY_RCHRROLLWISEDATALIST = "rchrRollwisedataList";

    public RCHR_Inspectionsheet() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CUTLOOKINGVALUE, new BigDecimal(0));
        setDefaultValue(PROPERTY_ATOTAL, new BigDecimal(0));
        setDefaultValue(PROPERTY_AONETOTAL, new BigDecimal(0));
        setDefaultValue(PROPERTY_BTOTAL, new BigDecimal(0));
        setDefaultValue(PROPERTY_SLTOTAL, new BigDecimal(0));
        setDefaultValue(PROPERTY_REJECTIONEFFI, new BigDecimal(0));
        setDefaultValue(PROPERTY_SLREJECTIONEFFI, new BigDecimal(0));
        setDefaultValue(PROPERTY_ORDERQUANTITY, new BigDecimal(0));
        setDefaultValue(PROPERTY_REMAININGQUANTITY, new BigDecimal(0));
        setDefaultValue(PROPERTY_EFFICIENCY, false);
        setDefaultValue(PROPERTY_REMARK, false);
        setDefaultValue(PROPERTY_GROSSWEIGHT, new BigDecimal(0));
        setDefaultValue(PROPERTY_GLM, new BigDecimal(0));
        setDefaultValue(PROPERTY_REMAINGLM, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALFOUR, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALTWO, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALPOINTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_MENIDNGPROCESS, false);
        setDefaultValue(PROPERTY_GENERATEROLLNO, false);
        setDefaultValue(PROPERTY_RCHRINSPCATEGORYLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRINSPSHEETLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROLLWISEDATALIST, new ArrayList<Object>());
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

    public String getQualityno() {
        return (String) get(PROPERTY_QUALITYNO);
    }

    public void setQualityno(String qualityno) {
        set(PROPERTY_QUALITYNO, qualityno);
    }

    public Date getDoffdate() {
        return (Date) get(PROPERTY_DOFFDATE);
    }

    public void setDoffdate(Date doffdate) {
        set(PROPERTY_DOFFDATE, doffdate);
    }

    public RCPRShift getRcprShift() {
        return (RCPRShift) get(PROPERTY_RCPRSHIFT);
    }

    public void setRcprShift(RCPRShift rcprShift) {
        set(PROPERTY_RCPRSHIFT, rcprShift);
    }

    public Date getInspdate() {
        return (Date) get(PROPERTY_INSPDATE);
    }

    public void setInspdate(Date inspdate) {
        set(PROPERTY_INSPDATE, inspdate);
    }

    public RCPRShift getInspshift() {
        return (RCPRShift) get(PROPERTY_INSPSHIFT);
    }

    public void setInspshift(RCPRShift inspshift) {
        set(PROPERTY_INSPSHIFT, inspshift);
    }

    public String getPieceno() {
        return (String) get(PROPERTY_PIECENO);
    }

    public void setPieceno(String pieceno) {
        set(PROPERTY_PIECENO, pieceno);
    }

    public String getWarpweftcount() {
        return (String) get(PROPERTY_WARPWEFTCOUNT);
    }

    public void setWarpweftcount(String warpweftcount) {
        set(PROPERTY_WARPWEFTCOUNT, warpweftcount);
    }

    public BigDecimal getWidthininches() {
        return (BigDecimal) get(PROPERTY_WIDTHININCHES);
    }

    public void setWidthininches(BigDecimal widthininches) {
        set(PROPERTY_WIDTHININCHES, widthininches);
    }

    public String getSelvedgewidthr() {
        return (String) get(PROPERTY_SELVEDGEWIDTHR);
    }

    public void setSelvedgewidthr(String selvedgewidthr) {
        set(PROPERTY_SELVEDGEWIDTHR, selvedgewidthr);
    }

    public String getSelvedgewidthl() {
        return (String) get(PROPERTY_SELVEDGEWIDTHL);
    }

    public void setSelvedgewidthl(String selvedgewidthl) {
        set(PROPERTY_SELVEDGEWIDTHL, selvedgewidthl);
    }

    public String getFringelenthr() {
        return (String) get(PROPERTY_FRINGELENTHR);
    }

    public void setFringelenthr(String fringelenthr) {
        set(PROPERTY_FRINGELENTHR, fringelenthr);
    }

    public String getFringelenthl() {
        return (String) get(PROPERTY_FRINGELENTHL);
    }

    public void setFringelenthl(String fringelenthl) {
        set(PROPERTY_FRINGELENTHL, fringelenthl);
    }

    public Long getLoommtr() {
        return (Long) get(PROPERTY_LOOMMTR);
    }

    public void setLoommtr(Long loommtr) {
        set(PROPERTY_LOOMMTR, loommtr);
    }

    public BigDecimal getTotalmtr() {
        return (BigDecimal) get(PROPERTY_TOTALMTR);
    }

    public void setTotalmtr(BigDecimal totalmtr) {
        set(PROPERTY_TOTALMTR, totalmtr);
    }

    public String getTwilldrill() {
        return (String) get(PROPERTY_TWILLDRILL);
    }

    public void setTwilldrill(String twilldrill) {
        set(PROPERTY_TWILLDRILL, twilldrill);
    }

    public String getStaindir() {
        return (String) get(PROPERTY_STAINDIR);
    }

    public void setStaindir(String staindir) {
        set(PROPERTY_STAINDIR, staindir);
    }

    public String getSelvedge() {
        return (String) get(PROPERTY_SELVEDGE);
    }

    public void setSelvedge(String selvedge) {
        set(PROPERTY_SELVEDGE, selvedge);
    }

    public String getInspection() {
        return (String) get(PROPERTY_INSPECTION);
    }

    public void setInspection(String inspection) {
        set(PROPERTY_INSPECTION, inspection);
    }

    public RchrEmployee getEmployee() {
        return (RchrEmployee) get(PROPERTY_EMPLOYEE);
    }

    public void setEmployee(RchrEmployee employee) {
        set(PROPERTY_EMPLOYEE, employee);
    }

    public RCPLLoom getRcplLoom() {
        return (RCPLLoom) get(PROPERTY_RCPLLOOM);
    }

    public void setRcplLoom(RCPLLoom rcplLoom) {
        set(PROPERTY_RCPLLOOM, rcplLoom);
    }

    public BigDecimal getCutlookingvalue() {
        return (BigDecimal) get(PROPERTY_CUTLOOKINGVALUE);
    }

    public void setCutlookingvalue(BigDecimal cutlookingvalue) {
        set(PROPERTY_CUTLOOKINGVALUE, cutlookingvalue);
    }

    public BigDecimal getAtotal() {
        return (BigDecimal) get(PROPERTY_ATOTAL);
    }

    public void setAtotal(BigDecimal atotal) {
        set(PROPERTY_ATOTAL, atotal);
    }

    public BigDecimal getAonetotal() {
        return (BigDecimal) get(PROPERTY_AONETOTAL);
    }

    public void setAonetotal(BigDecimal aonetotal) {
        set(PROPERTY_AONETOTAL, aonetotal);
    }

    public BigDecimal getBtotal() {
        return (BigDecimal) get(PROPERTY_BTOTAL);
    }

    public void setBtotal(BigDecimal btotal) {
        set(PROPERTY_BTOTAL, btotal);
    }

    public BigDecimal getSltotal() {
        return (BigDecimal) get(PROPERTY_SLTOTAL);
    }

    public void setSltotal(BigDecimal sltotal) {
        set(PROPERTY_SLTOTAL, sltotal);
    }

    public BigDecimal getRejectioneffi() {
        return (BigDecimal) get(PROPERTY_REJECTIONEFFI);
    }

    public void setRejectioneffi(BigDecimal rejectioneffi) {
        set(PROPERTY_REJECTIONEFFI, rejectioneffi);
    }

    public BigDecimal getSlrejectioneffi() {
        return (BigDecimal) get(PROPERTY_SLREJECTIONEFFI);
    }

    public void setSlrejectioneffi(BigDecimal slrejectioneffi) {
        set(PROPERTY_SLREJECTIONEFFI, slrejectioneffi);
    }

    public BigDecimal getOrderquantity() {
        return (BigDecimal) get(PROPERTY_ORDERQUANTITY);
    }

    public void setOrderquantity(BigDecimal orderquantity) {
        set(PROPERTY_ORDERQUANTITY, orderquantity);
    }

    public BigDecimal getRemainingquantity() {
        return (BigDecimal) get(PROPERTY_REMAININGQUANTITY);
    }

    public void setRemainingquantity(BigDecimal remainingquantity) {
        set(PROPERTY_REMAININGQUANTITY, remainingquantity);
    }

    public Boolean isEfficiency() {
        return (Boolean) get(PROPERTY_EFFICIENCY);
    }

    public void setEfficiency(Boolean efficiency) {
        set(PROPERTY_EFFICIENCY, efficiency);
    }

    public Boolean isRemark() {
        return (Boolean) get(PROPERTY_REMARK);
    }

    public void setRemark(Boolean remark) {
        set(PROPERTY_REMARK, remark);
    }

    public String getRemarks() {
        return (String) get(PROPERTY_REMARKS);
    }

    public void setRemarks(String remarks) {
        set(PROPERTY_REMARKS, remarks);
    }

    public BigDecimal getEpi() {
        return (BigDecimal) get(PROPERTY_EPI);
    }

    public void setEpi(BigDecimal epi) {
        set(PROPERTY_EPI, epi);
    }

    public BigDecimal getPpi() {
        return (BigDecimal) get(PROPERTY_PPI);
    }

    public void setPpi(BigDecimal ppi) {
        set(PROPERTY_PPI, ppi);
    }

    public RCHRInspweave getRchrInspweave() {
        return (RCHRInspweave) get(PROPERTY_RCHRINSPWEAVE);
    }

    public void setRchrInspweave(RCHRInspweave rchrInspweave) {
        set(PROPERTY_RCHRINSPWEAVE, rchrInspweave);
    }

    public RCPL_Insploomctgry getRcplInsploomctgry() {
        return (RCPL_Insploomctgry) get(PROPERTY_RCPLINSPLOOMCTGRY);
    }

    public void setRcplInsploomctgry(RCPL_Insploomctgry rcplInsploomctgry) {
        set(PROPERTY_RCPLINSPLOOMCTGRY, rcplInsploomctgry);
    }

    public BigDecimal getGrossweight() {
        return (BigDecimal) get(PROPERTY_GROSSWEIGHT);
    }

    public void setGrossweight(BigDecimal grossweight) {
        set(PROPERTY_GROSSWEIGHT, grossweight);
    }

    public BigDecimal getGlm() {
        return (BigDecimal) get(PROPERTY_GLM);
    }

    public void setGlm(BigDecimal glm) {
        set(PROPERTY_GLM, glm);
    }

    public RchrEmployee getDataeop() {
        return (RchrEmployee) get(PROPERTY_DATAEOP);
    }

    public void setDataeop(RchrEmployee dataeop) {
        set(PROPERTY_DATAEOP, dataeop);
    }

    public BigDecimal getRemainglm() {
        return (BigDecimal) get(PROPERTY_REMAINGLM);
    }

    public void setRemainglm(BigDecimal remainglm) {
        set(PROPERTY_REMAINGLM, remainglm);
    }

    public RCHRPiecenolist getRchrPiecenolist() {
        return (RCHRPiecenolist) get(PROPERTY_RCHRPIECENOLIST);
    }

    public void setRchrPiecenolist(RCHRPiecenolist rchrPiecenolist) {
        set(PROPERTY_RCHRPIECENOLIST, rchrPiecenolist);
    }

    public BigDecimal getTotalfour() {
        return (BigDecimal) get(PROPERTY_TOTALFOUR);
    }

    public void setTotalfour(BigDecimal totalfour) {
        set(PROPERTY_TOTALFOUR, totalfour);
    }

    public BigDecimal getTotaltwo() {
        return (BigDecimal) get(PROPERTY_TOTALTWO);
    }

    public void setTotaltwo(BigDecimal totaltwo) {
        set(PROPERTY_TOTALTWO, totaltwo);
    }

    public BigDecimal getTotalpoints() {
        return (BigDecimal) get(PROPERTY_TOTALPOINTS);
    }

    public void setTotalpoints(BigDecimal totalpoints) {
        set(PROPERTY_TOTALPOINTS, totalpoints);
    }

    public RCPRMachine getRcprMachine() {
        return (RCPRMachine) get(PROPERTY_RCPRMACHINE);
    }

    public void setRcprMachine(RCPRMachine rcprMachine) {
        set(PROPERTY_RCPRMACHINE, rcprMachine);
    }

    public RCHRInsprolltype getRchrInsprolltype() {
        return (RCHRInsprolltype) get(PROPERTY_RCHRINSPROLLTYPE);
    }

    public void setRchrInsprolltype(RCHRInsprolltype rchrInsprolltype) {
        set(PROPERTY_RCHRINSPROLLTYPE, rchrInsprolltype);
    }

    public RCHR_Inspclothtype getRchrInspclothtype() {
        return (RCHR_Inspclothtype) get(PROPERTY_RCHRINSPCLOTHTYPE);
    }

    public void setRchrInspclothtype(RCHR_Inspclothtype rchrInspclothtype) {
        set(PROPERTY_RCHRINSPCLOTHTYPE, rchrInspclothtype);
    }

    public RCHRPiecestatus getRchrPiecestatus() {
        return (RCHRPiecestatus) get(PROPERTY_RCHRPIECESTATUS);
    }

    public void setRchrPiecestatus(RCHRPiecestatus rchrPiecestatus) {
        set(PROPERTY_RCHRPIECESTATUS, rchrPiecestatus);
    }

    public String getMenidngstatus() {
        return (String) get(PROPERTY_MENIDNGSTATUS);
    }

    public void setMenidngstatus(String menidngstatus) {
        set(PROPERTY_MENIDNGSTATUS, menidngstatus);
    }

    public Boolean isMenidngprocess() {
        return (Boolean) get(PROPERTY_MENIDNGPROCESS);
    }

    public void setMenidngprocess(Boolean menidngprocess) {
        set(PROPERTY_MENIDNGPROCESS, menidngprocess);
    }

    public Boolean isGeneraterollno() {
        return (Boolean) get(PROPERTY_GENERATEROLLNO);
    }

    public void setGeneraterollno(Boolean generaterollno) {
        set(PROPERTY_GENERATEROLLNO, generaterollno);
    }

    public RCHR_Jobcard getRchrJobcard() {
        return (RCHR_Jobcard) get(PROPERTY_RCHRJOBCARD);
    }

    public void setRchrJobcard(RCHR_Jobcard rchrJobcard) {
        set(PROPERTY_RCHRJOBCARD, rchrJobcard);
    }

    public BigDecimal getTowrapmtrs() {
        return (BigDecimal) get(PROPERTY_TOWRAPMTRS);
    }

    public void setTowrapmtrs(BigDecimal towrapmtrs) {
        set(PROPERTY_TOWRAPMTRS, towrapmtrs);
    }

    public BigDecimal getWrapdonemtrs() {
        return (BigDecimal) get(PROPERTY_WRAPDONEMTRS);
    }

    public void setWrapdonemtrs(BigDecimal wrapdonemtrs) {
        set(PROPERTY_WRAPDONEMTRS, wrapdonemtrs);
    }

    public BigDecimal getRemainingwrapmtrs() {
        return (BigDecimal) get(PROPERTY_REMAININGWRAPMTRS);
    }

    public void setRemainingwrapmtrs(BigDecimal remainingwrapmtrs) {
        set(PROPERTY_REMAININGWRAPMTRS, remainingwrapmtrs);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Inspcategoryline> getRCHRInspcategorylineList() {
        return (List<RCHR_Inspcategoryline>) get(PROPERTY_RCHRINSPCATEGORYLINELIST);
    }

    public void setRCHRInspcategorylineList(List<RCHR_Inspcategoryline> rCHRInspcategorylineList) {
        set(PROPERTY_RCHRINSPCATEGORYLINELIST, rCHRInspcategorylineList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Inspsheetlines> getRCHRInspsheetlinesList() {
        return (List<RCHR_Inspsheetlines>) get(PROPERTY_RCHRINSPSHEETLINESLIST);
    }

    public void setRCHRInspsheetlinesList(List<RCHR_Inspsheetlines> rCHRInspsheetlinesList) {
        set(PROPERTY_RCHRINSPSHEETLINESLIST, rCHRInspsheetlinesList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRollwisedata> getRchrRollwisedataList() {
        return (List<RchrRollwisedata>) get(PROPERTY_RCHRROLLWISEDATALIST);
    }

    public void setRchrRollwisedataList(List<RchrRollwisedata> rchrRollwisedataList) {
        set(PROPERTY_RCHRROLLWISEDATALIST, rchrRollwisedataList);
    }

}
