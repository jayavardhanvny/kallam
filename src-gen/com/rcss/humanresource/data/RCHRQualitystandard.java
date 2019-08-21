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

import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.epcg.data.EpcgMof;
import com.redcarpet.epcg.data.EpcgVariant;
import com.redcarpet.epcg.data.epcg_ppcenquiry;
import com.redcarpet.goodsissue.data.RcgiMaterialIndentLines;
import com.redcarpet.payroll.data.RcplLoomsproduction;

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
import org.openbravo.model.common.plm.Product;
/**
 * Entity class for entity RCHR_Qualitystandard (stored in table RCHR_Qualitystandard).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RCHRQualitystandard extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RCHR_Qualitystandard";
    public static final String ENTITY_NAME = "RCHR_Qualitystandard";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_QUALITYNO = "qualityno";
    public static final String PROPERTY_QSTANDARD = "qstandard";
    public static final String PROPERTY_WARPCOUNT = "warpcount";
    public static final String PROPERTY_WEFTCOUNT = "weftcount";
    public static final String PROPERTY_EPI = "epi";
    public static final String PROPERTY_PPI = "ppi";
    public static final String PROPERTY_RCHRWARPYARNTYPE = "rchrWarpyarntype";
    public static final String PROPERTY_WARPYARNTYPE = "warpyarntype";
    public static final String PROPERTY_RCHRINSPWEAVE = "rchrInspweave";
    public static final String PROPERTY_RCHRINSPGREIGEWIDTH = "rchrInspgreigewidth";
    public static final String PROPERTY_ORDERQUANTITY = "orderquantity";
    public static final String PROPERTY_REMAININGQUANTITY = "remainingquantity";
    public static final String PROPERTY_RCHRINSPCLOTHTYPE = "rchrInspclothtype";
    public static final String PROPERTY_ATOTAL = "atotal";
    public static final String PROPERTY_AONETOTAL = "aonetotal";
    public static final String PROPERTY_BTOTAL = "btotal";
    public static final String PROPERTY_SLTOTAL = "sltotal";
    public static final String PROPERTY_AVGGLM = "avgglm";
    public static final String PROPERTY_ORDERDATE = "orderdate";
    public static final String PROPERTY_DELIVERYDATE = "deliverydate";
    public static final String PROPERTY_UPDATEREMQTY = "updateremqty";
    public static final String PROPERTY_RCHRORDERSTATUS = "rchrOrderstatus";
    public static final String PROPERTY_ORDERNO = "orderNo";
    public static final String PROPERTY_MOFNO = "mofno";
    public static final String PROPERTY_EPITOLERENCE = "epitolerence";
    public static final String PROPERTY_PPITOLERENCE = "ppitolerence";
    public static final String PROPERTY_TOTALENDS = "totalends";
    public static final String PROPERTY_REED = "reed";
    public static final String PROPERTY_REEDSPACE = "reedspace";
    public static final String PROPERTY_WARPCRIMP = "warpcrimp";
    public static final String PROPERTY_WEFTCRIMP = "weftcrimp";
    public static final String PROPERTY_SIZEPICKUP = "sizepickup";
    public static final String PROPERTY_WARPGLM = "warpglm";
    public static final String PROPERTY_WEFTGLM = "weftglm";
    public static final String PROPERTY_GLM = "glm";
    public static final String PROPERTY_TOWRAPMTRS = "towrapmtrs";
    public static final String PROPERTY_WARPYARNREQ = "warpyarnreq";
    public static final String PROPERTY_WEFTYARNREQ = "weftyarnreq";
    public static final String PROPERTY_WIDTHCMS = "widthcms";
    public static final String PROPERTY_WIDTHMTRS = "widthmtrs";
    public static final String PROPERTY_GSM = "gsm";
    public static final String PROPERTY_GWTOLERENCE = "gwtolerence";
    public static final String PROPERTY_SELVEDENTS = "selvedents";
    public static final String PROPERTY_SELVEENDS = "selveends";
    public static final String PROPERTY_FULLLENGTHPER = "fulllengthper";
    public static final String PROPERTY_FULLLENGTHMTR = "fulllengthmtr";
    public static final String PROPERTY_SHORTLENGTHPER = "shortlengthper";
    public static final String PROPERTY_SHORTLENGTHMTR = "shortlengthmtr";
    public static final String PROPERTY_BALEORROLE = "baleorrole";
    public static final String PROPERTY_BALESIZE = "balesize";
    public static final String PROPERTY_WARPTEARINGSTRNGTH = "warptearingstrngth";
    public static final String PROPERTY_WEFTTEARINGSTRNGTH = "wefttearingstrngth";
    public static final String PROPERTY_TEARINGTESTMETHOD = "tearingtestmethod";
    public static final String PROPERTY_WARPTENSIBLESTRNGTH = "warptensiblestrngth";
    public static final String PROPERTY_WEFTTENSIBLESTRNGTH = "wefttensiblestrngth";
    public static final String PROPERTY_TENSIBLETESTMETHOD = "tensibletestmethod";
    public static final String PROPERTY_PARTYCONSTRUCTION = "partyconstruction";
    public static final String PROPERTY_COVERFACTOR = "coverfactor";
    public static final String PROPERTY_RCHRWARPWEFTCLOLOR = "rchrWarpweftclolor";
    public static final String PROPERTY_REASONFORLEAVE = "reasonForLeave";
    public static final String PROPERTY_WEFTCOLOR = "weftcolor";
    public static final String PROPERTY_NOOFRAMES = "nooframes";
    public static final String PROPERTY_SELVWEAVE = "selvweave";
    public static final String PROPERTY_SELVEDGE = "selvedge";
    public static final String PROPERTY_SKILLGRADE = "skillGrade";
    public static final String PROPERTY_HSNCODE = "hsncode";
    public static final String PROPERTY_EPCGVARIANT = "epcgVariant";
    public static final String PROPERTY_WEFTVARIANT = "weftvariant";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_WEFTPRODUCT = "weftProduct";
    public static final String PROPERTY_SPEED = "speed";
    public static final String PROPERTY_EFFICIENCY = "efficiency";
    public static final String PROPERTY_SIZING = "sizing";
    public static final String PROPERTY_VALUELOSS = "valueloss";
    public static final String PROPERTY_TYPEOFSORT = "typeofsort";
    public static final String PROPERTY_ONLOOMEPI = "onloomepi";
    public static final String PROPERTY_EPCGCOSTENQUIRYLIST = "epcgCostenquiryList";
    public static final String PROPERTY_EPCGMOFLIST = "epcgMofList";
    public static final String PROPERTY_RCGIMATERIALINDENTLINESLIST = "rCGIMaterialIndentLinesList";
    public static final String PROPERTY_RCHRDIRECTWARPLIST = "rCHRDirectwarpList";
    public static final String PROPERTY_RCHRINSPECTIONSHEETLIST = "rCHRInspectionsheetList";
    public static final String PROPERTY_RCHRJOBCARDLIST = "rCHRJobcardList";
    public static final String PROPERTY_RCHRSIZINGSHEETLIST = "rCHRSizingsheetList";
    public static final String PROPERTY_RCHRAUTODRAWINGLIST = "rchrAutodrawingList";
    public static final String PROPERTY_RCHRDYEDYARNLIST = "rchrDyedyarnList";
    public static final String PROPERTY_RCHRLOOMSDATALIST = "rchrLoomsdataList";
    public static final String PROPERTY_RCHRROLLWISEDATALIST = "rchrRollwisedataList";
    public static final String PROPERTY_RCPLLOOMSPRODUCTIONLIST = "rcplLoomsproductionList";
    public static final String PROPERTY_EPCGPPCENQUIRYLIST = "epcgPpcenquiryList";

    public RCHRQualitystandard() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ORDERQUANTITY, new BigDecimal(0));
        setDefaultValue(PROPERTY_REMAININGQUANTITY, new BigDecimal(0));
        setDefaultValue(PROPERTY_AVGGLM, new BigDecimal(0));
        setDefaultValue(PROPERTY_UPDATEREMQTY, false);
        setDefaultValue(PROPERTY_EPITOLERENCE, new BigDecimal(2));
        setDefaultValue(PROPERTY_PPITOLERENCE, new BigDecimal(2));
        setDefaultValue(PROPERTY_GWTOLERENCE, new BigDecimal(0.5));
        setDefaultValue(PROPERTY_SPEED, new BigDecimal(0));
        setDefaultValue(PROPERTY_EFFICIENCY, new BigDecimal(0));
        setDefaultValue(PROPERTY_SIZING, new BigDecimal(0));
        setDefaultValue(PROPERTY_VALUELOSS, new BigDecimal(0));
        setDefaultValue(PROPERTY_EPCGCOSTENQUIRYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGMOFLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIMATERIALINDENTLINESLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDIRECTWARPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRINSPECTIONSHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOBCARDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRSIZINGSHEETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRAUTODRAWINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRDYEDYARNLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRLOOMSDATALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRROLLWISEDATALIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLLOOMSPRODUCTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGPPCENQUIRYLIST, new ArrayList<Object>());
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

    public String getQualityno() {
        return (String) get(PROPERTY_QUALITYNO);
    }

    public void setQualityno(String qualityno) {
        set(PROPERTY_QUALITYNO, qualityno);
    }

    public String getQstandard() {
        return (String) get(PROPERTY_QSTANDARD);
    }

    public void setQstandard(String qstandard) {
        set(PROPERTY_QSTANDARD, qstandard);
    }

    public BigDecimal getWarpcount() {
        return (BigDecimal) get(PROPERTY_WARPCOUNT);
    }

    public void setWarpcount(BigDecimal warpcount) {
        set(PROPERTY_WARPCOUNT, warpcount);
    }

    public BigDecimal getWeftcount() {
        return (BigDecimal) get(PROPERTY_WEFTCOUNT);
    }

    public void setWeftcount(BigDecimal weftcount) {
        set(PROPERTY_WEFTCOUNT, weftcount);
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

    public RCHRWarpyarntype getRchrWarpyarntype() {
        return (RCHRWarpyarntype) get(PROPERTY_RCHRWARPYARNTYPE);
    }

    public void setRchrWarpyarntype(RCHRWarpyarntype rchrWarpyarntype) {
        set(PROPERTY_RCHRWARPYARNTYPE, rchrWarpyarntype);
    }

    public RCHRWarpyarntype getWarpyarntype() {
        return (RCHRWarpyarntype) get(PROPERTY_WARPYARNTYPE);
    }

    public void setWarpyarntype(RCHRWarpyarntype warpyarntype) {
        set(PROPERTY_WARPYARNTYPE, warpyarntype);
    }

    public RCHRInspweave getRchrInspweave() {
        return (RCHRInspweave) get(PROPERTY_RCHRINSPWEAVE);
    }

    public void setRchrInspweave(RCHRInspweave rchrInspweave) {
        set(PROPERTY_RCHRINSPWEAVE, rchrInspweave);
    }

    public RchrInspgreigewidth getRchrInspgreigewidth() {
        return (RchrInspgreigewidth) get(PROPERTY_RCHRINSPGREIGEWIDTH);
    }

    public void setRchrInspgreigewidth(RchrInspgreigewidth rchrInspgreigewidth) {
        set(PROPERTY_RCHRINSPGREIGEWIDTH, rchrInspgreigewidth);
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

    public RCHR_Inspclothtype getRchrInspclothtype() {
        return (RCHR_Inspclothtype) get(PROPERTY_RCHRINSPCLOTHTYPE);
    }

    public void setRchrInspclothtype(RCHR_Inspclothtype rchrInspclothtype) {
        set(PROPERTY_RCHRINSPCLOTHTYPE, rchrInspclothtype);
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

    public BigDecimal getAvgglm() {
        return (BigDecimal) get(PROPERTY_AVGGLM);
    }

    public void setAvgglm(BigDecimal avgglm) {
        set(PROPERTY_AVGGLM, avgglm);
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

    public Boolean isUpdateremqty() {
        return (Boolean) get(PROPERTY_UPDATEREMQTY);
    }

    public void setUpdateremqty(Boolean updateremqty) {
        set(PROPERTY_UPDATEREMQTY, updateremqty);
    }

    public RCHR_Orderstatus getRchrOrderstatus() {
        return (RCHR_Orderstatus) get(PROPERTY_RCHRORDERSTATUS);
    }

    public void setRchrOrderstatus(RCHR_Orderstatus rchrOrderstatus) {
        set(PROPERTY_RCHRORDERSTATUS, rchrOrderstatus);
    }

    public String getOrderNo() {
        return (String) get(PROPERTY_ORDERNO);
    }

    public void setOrderNo(String orderNo) {
        set(PROPERTY_ORDERNO, orderNo);
    }

    public String getMofno() {
        return (String) get(PROPERTY_MOFNO);
    }

    public void setMofno(String mofno) {
        set(PROPERTY_MOFNO, mofno);
    }

    public BigDecimal getEpitolerence() {
        return (BigDecimal) get(PROPERTY_EPITOLERENCE);
    }

    public void setEpitolerence(BigDecimal epitolerence) {
        set(PROPERTY_EPITOLERENCE, epitolerence);
    }

    public BigDecimal getPpitolerence() {
        return (BigDecimal) get(PROPERTY_PPITOLERENCE);
    }

    public void setPpitolerence(BigDecimal ppitolerence) {
        set(PROPERTY_PPITOLERENCE, ppitolerence);
    }

    public Long getTotalends() {
        return (Long) get(PROPERTY_TOTALENDS);
    }

    public void setTotalends(Long totalends) {
        set(PROPERTY_TOTALENDS, totalends);
    }

    public String getReed() {
        return (String) get(PROPERTY_REED);
    }

    public void setReed(String reed) {
        set(PROPERTY_REED, reed);
    }

    public BigDecimal getReedspace() {
        return (BigDecimal) get(PROPERTY_REEDSPACE);
    }

    public void setReedspace(BigDecimal reedspace) {
        set(PROPERTY_REEDSPACE, reedspace);
    }

    public BigDecimal getWarpcrimp() {
        return (BigDecimal) get(PROPERTY_WARPCRIMP);
    }

    public void setWarpcrimp(BigDecimal warpcrimp) {
        set(PROPERTY_WARPCRIMP, warpcrimp);
    }

    public BigDecimal getWeftcrimp() {
        return (BigDecimal) get(PROPERTY_WEFTCRIMP);
    }

    public void setWeftcrimp(BigDecimal weftcrimp) {
        set(PROPERTY_WEFTCRIMP, weftcrimp);
    }

    public Long getSizepickup() {
        return (Long) get(PROPERTY_SIZEPICKUP);
    }

    public void setSizepickup(Long sizepickup) {
        set(PROPERTY_SIZEPICKUP, sizepickup);
    }

    public BigDecimal getWarpglm() {
        return (BigDecimal) get(PROPERTY_WARPGLM);
    }

    public void setWarpglm(BigDecimal warpglm) {
        set(PROPERTY_WARPGLM, warpglm);
    }

    public BigDecimal getWeftglm() {
        return (BigDecimal) get(PROPERTY_WEFTGLM);
    }

    public void setWeftglm(BigDecimal weftglm) {
        set(PROPERTY_WEFTGLM, weftglm);
    }

    public BigDecimal getGlm() {
        return (BigDecimal) get(PROPERTY_GLM);
    }

    public void setGlm(BigDecimal glm) {
        set(PROPERTY_GLM, glm);
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

    public BigDecimal getWidthcms() {
        return (BigDecimal) get(PROPERTY_WIDTHCMS);
    }

    public void setWidthcms(BigDecimal widthcms) {
        set(PROPERTY_WIDTHCMS, widthcms);
    }

    public BigDecimal getWidthmtrs() {
        return (BigDecimal) get(PROPERTY_WIDTHMTRS);
    }

    public void setWidthmtrs(BigDecimal widthmtrs) {
        set(PROPERTY_WIDTHMTRS, widthmtrs);
    }

    public BigDecimal getGsm() {
        return (BigDecimal) get(PROPERTY_GSM);
    }

    public void setGsm(BigDecimal gsm) {
        set(PROPERTY_GSM, gsm);
    }

    public BigDecimal getGwtolerence() {
        return (BigDecimal) get(PROPERTY_GWTOLERENCE);
    }

    public void setGwtolerence(BigDecimal gwtolerence) {
        set(PROPERTY_GWTOLERENCE, gwtolerence);
    }

    public Long getSelvedents() {
        return (Long) get(PROPERTY_SELVEDENTS);
    }

    public void setSelvedents(Long selvedents) {
        set(PROPERTY_SELVEDENTS, selvedents);
    }

    public Long getSelveends() {
        return (Long) get(PROPERTY_SELVEENDS);
    }

    public void setSelveends(Long selveends) {
        set(PROPERTY_SELVEENDS, selveends);
    }

    public Long getFulllengthper() {
        return (Long) get(PROPERTY_FULLLENGTHPER);
    }

    public void setFulllengthper(Long fulllengthper) {
        set(PROPERTY_FULLLENGTHPER, fulllengthper);
    }

    public Long getFulllengthmtr() {
        return (Long) get(PROPERTY_FULLLENGTHMTR);
    }

    public void setFulllengthmtr(Long fulllengthmtr) {
        set(PROPERTY_FULLLENGTHMTR, fulllengthmtr);
    }

    public Long getShortlengthper() {
        return (Long) get(PROPERTY_SHORTLENGTHPER);
    }

    public void setShortlengthper(Long shortlengthper) {
        set(PROPERTY_SHORTLENGTHPER, shortlengthper);
    }

    public Long getShortlengthmtr() {
        return (Long) get(PROPERTY_SHORTLENGTHMTR);
    }

    public void setShortlengthmtr(Long shortlengthmtr) {
        set(PROPERTY_SHORTLENGTHMTR, shortlengthmtr);
    }

    public Long getBaleorrole() {
        return (Long) get(PROPERTY_BALEORROLE);
    }

    public void setBaleorrole(Long baleorrole) {
        set(PROPERTY_BALEORROLE, baleorrole);
    }

    public Long getBalesize() {
        return (Long) get(PROPERTY_BALESIZE);
    }

    public void setBalesize(Long balesize) {
        set(PROPERTY_BALESIZE, balesize);
    }

    public Long getWarptearingstrngth() {
        return (Long) get(PROPERTY_WARPTEARINGSTRNGTH);
    }

    public void setWarptearingstrngth(Long warptearingstrngth) {
        set(PROPERTY_WARPTEARINGSTRNGTH, warptearingstrngth);
    }

    public Long getWefttearingstrngth() {
        return (Long) get(PROPERTY_WEFTTEARINGSTRNGTH);
    }

    public void setWefttearingstrngth(Long wefttearingstrngth) {
        set(PROPERTY_WEFTTEARINGSTRNGTH, wefttearingstrngth);
    }

    public String getTearingtestmethod() {
        return (String) get(PROPERTY_TEARINGTESTMETHOD);
    }

    public void setTearingtestmethod(String tearingtestmethod) {
        set(PROPERTY_TEARINGTESTMETHOD, tearingtestmethod);
    }

    public Long getWarptensiblestrngth() {
        return (Long) get(PROPERTY_WARPTENSIBLESTRNGTH);
    }

    public void setWarptensiblestrngth(Long warptensiblestrngth) {
        set(PROPERTY_WARPTENSIBLESTRNGTH, warptensiblestrngth);
    }

    public Long getWefttensiblestrngth() {
        return (Long) get(PROPERTY_WEFTTENSIBLESTRNGTH);
    }

    public void setWefttensiblestrngth(Long wefttensiblestrngth) {
        set(PROPERTY_WEFTTENSIBLESTRNGTH, wefttensiblestrngth);
    }

    public String getTensibletestmethod() {
        return (String) get(PROPERTY_TENSIBLETESTMETHOD);
    }

    public void setTensibletestmethod(String tensibletestmethod) {
        set(PROPERTY_TENSIBLETESTMETHOD, tensibletestmethod);
    }

    public String getPartyconstruction() {
        return (String) get(PROPERTY_PARTYCONSTRUCTION);
    }

    public void setPartyconstruction(String partyconstruction) {
        set(PROPERTY_PARTYCONSTRUCTION, partyconstruction);
    }

    public BigDecimal getCoverfactor() {
        return (BigDecimal) get(PROPERTY_COVERFACTOR);
    }

    public void setCoverfactor(BigDecimal coverfactor) {
        set(PROPERTY_COVERFACTOR, coverfactor);
    }

    public RCHR_Warpweftclolor getRchrWarpweftclolor() {
        return (RCHR_Warpweftclolor) get(PROPERTY_RCHRWARPWEFTCLOLOR);
    }

    public void setRchrWarpweftclolor(RCHR_Warpweftclolor rchrWarpweftclolor) {
        set(PROPERTY_RCHRWARPWEFTCLOLOR, rchrWarpweftclolor);
    }

    public String getReasonForLeave() {
        return (String) get(PROPERTY_REASONFORLEAVE);
    }

    public void setReasonForLeave(String reasonForLeave) {
        set(PROPERTY_REASONFORLEAVE, reasonForLeave);
    }

    public RCHR_Warpweftclolor getWeftcolor() {
        return (RCHR_Warpweftclolor) get(PROPERTY_WEFTCOLOR);
    }

    public void setWeftcolor(RCHR_Warpweftclolor weftcolor) {
        set(PROPERTY_WEFTCOLOR, weftcolor);
    }

    public Long getNooframes() {
        return (Long) get(PROPERTY_NOOFRAMES);
    }

    public void setNooframes(Long nooframes) {
        set(PROPERTY_NOOFRAMES, nooframes);
    }

    public String getSelvweave() {
        return (String) get(PROPERTY_SELVWEAVE);
    }

    public void setSelvweave(String selvweave) {
        set(PROPERTY_SELVWEAVE, selvweave);
    }

    public Long getSelvedge() {
        return (Long) get(PROPERTY_SELVEDGE);
    }

    public void setSelvedge(Long selvedge) {
        set(PROPERTY_SELVEDGE, selvedge);
    }

    public RCHR_SkillGrade getSkillGrade() {
        return (RCHR_SkillGrade) get(PROPERTY_SKILLGRADE);
    }

    public void setSkillGrade(RCHR_SkillGrade skillGrade) {
        set(PROPERTY_SKILLGRADE, skillGrade);
    }

    public Long getHsncode() {
        return (Long) get(PROPERTY_HSNCODE);
    }

    public void setHsncode(Long hsncode) {
        set(PROPERTY_HSNCODE, hsncode);
    }

    public EpcgVariant getEpcgVariant() {
        return (EpcgVariant) get(PROPERTY_EPCGVARIANT);
    }

    public void setEpcgVariant(EpcgVariant epcgVariant) {
        set(PROPERTY_EPCGVARIANT, epcgVariant);
    }

    public EpcgVariant getWeftvariant() {
        return (EpcgVariant) get(PROPERTY_WEFTVARIANT);
    }

    public void setWeftvariant(EpcgVariant weftvariant) {
        set(PROPERTY_WEFTVARIANT, weftvariant);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public Product getWeftProduct() {
        return (Product) get(PROPERTY_WEFTPRODUCT);
    }

    public void setWeftProduct(Product weftProduct) {
        set(PROPERTY_WEFTPRODUCT, weftProduct);
    }

    public BigDecimal getSpeed() {
        return (BigDecimal) get(PROPERTY_SPEED);
    }

    public void setSpeed(BigDecimal speed) {
        set(PROPERTY_SPEED, speed);
    }

    public BigDecimal getEfficiency() {
        return (BigDecimal) get(PROPERTY_EFFICIENCY);
    }

    public void setEfficiency(BigDecimal efficiency) {
        set(PROPERTY_EFFICIENCY, efficiency);
    }

    public BigDecimal getSizing() {
        return (BigDecimal) get(PROPERTY_SIZING);
    }

    public void setSizing(BigDecimal sizing) {
        set(PROPERTY_SIZING, sizing);
    }

    public BigDecimal getValueloss() {
        return (BigDecimal) get(PROPERTY_VALUELOSS);
    }

    public void setValueloss(BigDecimal valueloss) {
        set(PROPERTY_VALUELOSS, valueloss);
    }

    public String getTypeofsort() {
        return (String) get(PROPERTY_TYPEOFSORT);
    }

    public void setTypeofsort(String typeofsort) {
        set(PROPERTY_TYPEOFSORT, typeofsort);
    }

    public Long getOnloomepi() {
        return (Long) get(PROPERTY_ONLOOMEPI);
    }

    public void setOnloomepi(Long onloomepi) {
        set(PROPERTY_ONLOOMEPI, onloomepi);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgCostenquiry> getEpcgCostenquiryList() {
        return (List<EpcgCostenquiry>) get(PROPERTY_EPCGCOSTENQUIRYLIST);
    }

    public void setEpcgCostenquiryList(List<EpcgCostenquiry> epcgCostenquiryList) {
        set(PROPERTY_EPCGCOSTENQUIRYLIST, epcgCostenquiryList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgMof> getEpcgMofList() {
        return (List<EpcgMof>) get(PROPERTY_EPCGMOFLIST);
    }

    public void setEpcgMofList(List<EpcgMof> epcgMofList) {
        set(PROPERTY_EPCGMOFLIST, epcgMofList);
    }

    @SuppressWarnings("unchecked")
    public List<RcgiMaterialIndentLines> getRCGIMaterialIndentLinesList() {
        return (List<RcgiMaterialIndentLines>) get(PROPERTY_RCGIMATERIALINDENTLINESLIST);
    }

    public void setRCGIMaterialIndentLinesList(List<RcgiMaterialIndentLines> rCGIMaterialIndentLinesList) {
        set(PROPERTY_RCGIMATERIALINDENTLINESLIST, rCGIMaterialIndentLinesList);
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
    public List<RCHR_Jobcard> getRCHRJobcardList() {
        return (List<RCHR_Jobcard>) get(PROPERTY_RCHRJOBCARDLIST);
    }

    public void setRCHRJobcardList(List<RCHR_Jobcard> rCHRJobcardList) {
        set(PROPERTY_RCHRJOBCARDLIST, rCHRJobcardList);
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
    public List<RchrDyedyarn> getRchrDyedyarnList() {
        return (List<RchrDyedyarn>) get(PROPERTY_RCHRDYEDYARNLIST);
    }

    public void setRchrDyedyarnList(List<RchrDyedyarn> rchrDyedyarnList) {
        set(PROPERTY_RCHRDYEDYARNLIST, rchrDyedyarnList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrLoomsdata> getRchrLoomsdataList() {
        return (List<RchrLoomsdata>) get(PROPERTY_RCHRLOOMSDATALIST);
    }

    public void setRchrLoomsdataList(List<RchrLoomsdata> rchrLoomsdataList) {
        set(PROPERTY_RCHRLOOMSDATALIST, rchrLoomsdataList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrRollwisedata> getRchrRollwisedataList() {
        return (List<RchrRollwisedata>) get(PROPERTY_RCHRROLLWISEDATALIST);
    }

    public void setRchrRollwisedataList(List<RchrRollwisedata> rchrRollwisedataList) {
        set(PROPERTY_RCHRROLLWISEDATALIST, rchrRollwisedataList);
    }

    @SuppressWarnings("unchecked")
    public List<RcplLoomsproduction> getRcplLoomsproductionList() {
        return (List<RcplLoomsproduction>) get(PROPERTY_RCPLLOOMSPRODUCTIONLIST);
    }

    public void setRcplLoomsproductionList(List<RcplLoomsproduction> rcplLoomsproductionList) {
        set(PROPERTY_RCPLLOOMSPRODUCTIONLIST, rcplLoomsproductionList);
    }

    @SuppressWarnings("unchecked")
    public List<epcg_ppcenquiry> getEpcgPpcenquiryList() {
        return (List<epcg_ppcenquiry>) get(PROPERTY_EPCGPPCENQUIRYLIST);
    }

    public void setEpcgPpcenquiryList(List<epcg_ppcenquiry> epcgPpcenquiryList) {
        set(PROPERTY_EPCGPPCENQUIRYLIST, epcgPpcenquiryList);
    }

}
