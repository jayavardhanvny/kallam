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

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity Rchr_Warpdyedyarnreq (stored in table Rchr_Warpdyedyarnreq).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrWarpdyedyarnreq extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Warpdyedyarnreq";
    public static final String ENTITY_NAME = "Rchr_Warpdyedyarnreq";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_RCHRDYEDYARN = "rchrDyedyarn";
    public static final String PROPERTY_RCHRALPHABETS = "rchrAlphabets";
    public static final String PROPERTY_RCHRSHADENAMEMASTER = "rchrShadenamemaster";
    public static final String PROPERTY_RCHRSHADENUMBERMASTER = "rchrShadenumbermaster";
    public static final String PROPERTY_ENDSPERREPEAT = "endsperrepeat";
    public static final String PROPERTY_TOTALENDS = "totalends";
    public static final String PROPERTY_FIRSTLOADINGCONES = "firstloadingcones";
    public static final String PROPERTY_SECONDLOADINGCONES = "secondloadingcones";
    public static final String PROPERTY_REQUIREKGS = "requirekgs";
    public static final String PROPERTY_EXTRAINCONES = "extraincones";
    public static final String PROPERTY_TOTALKGSREQUIRED = "totalkgsrequired";
    public static final String PROPERTY_DIFFERENCEVALUE = "differencevalue";
    public static final String PROPERTY_LINENO = "lineNo";

    public RchrWarpdyedyarnreq() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_FIRSTLOADINGCONES, (long) 0);
        setDefaultValue(PROPERTY_SECONDLOADINGCONES, (long) 0);
        setDefaultValue(PROPERTY_REQUIREKGS, new BigDecimal(0));
        setDefaultValue(PROPERTY_EXTRAINCONES, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALKGSREQUIRED, new BigDecimal(0));
        setDefaultValue(PROPERTY_DIFFERENCEVALUE, (long) 0);
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

    public RchrDyedyarn getRchrDyedyarn() {
        return (RchrDyedyarn) get(PROPERTY_RCHRDYEDYARN);
    }

    public void setRchrDyedyarn(RchrDyedyarn rchrDyedyarn) {
        set(PROPERTY_RCHRDYEDYARN, rchrDyedyarn);
    }

    public RchrAlphabets getRchrAlphabets() {
        return (RchrAlphabets) get(PROPERTY_RCHRALPHABETS);
    }

    public void setRchrAlphabets(RchrAlphabets rchrAlphabets) {
        set(PROPERTY_RCHRALPHABETS, rchrAlphabets);
    }

    public RchrShadenamemaster getRchrShadenamemaster() {
        return (RchrShadenamemaster) get(PROPERTY_RCHRSHADENAMEMASTER);
    }

    public void setRchrShadenamemaster(RchrShadenamemaster rchrShadenamemaster) {
        set(PROPERTY_RCHRSHADENAMEMASTER, rchrShadenamemaster);
    }

    public RchrShadenumbermaster getRchrShadenumbermaster() {
        return (RchrShadenumbermaster) get(PROPERTY_RCHRSHADENUMBERMASTER);
    }

    public void setRchrShadenumbermaster(RchrShadenumbermaster rchrShadenumbermaster) {
        set(PROPERTY_RCHRSHADENUMBERMASTER, rchrShadenumbermaster);
    }

    public Long getEndsperrepeat() {
        return (Long) get(PROPERTY_ENDSPERREPEAT);
    }

    public void setEndsperrepeat(Long endsperrepeat) {
        set(PROPERTY_ENDSPERREPEAT, endsperrepeat);
    }

    public Long getTotalends() {
        return (Long) get(PROPERTY_TOTALENDS);
    }

    public void setTotalends(Long totalends) {
        set(PROPERTY_TOTALENDS, totalends);
    }

    public Long getFirstloadingcones() {
        return (Long) get(PROPERTY_FIRSTLOADINGCONES);
    }

    public void setFirstloadingcones(Long firstloadingcones) {
        set(PROPERTY_FIRSTLOADINGCONES, firstloadingcones);
    }

    public Long getSecondloadingcones() {
        return (Long) get(PROPERTY_SECONDLOADINGCONES);
    }

    public void setSecondloadingcones(Long secondloadingcones) {
        set(PROPERTY_SECONDLOADINGCONES, secondloadingcones);
    }

    public BigDecimal getRequirekgs() {
        return (BigDecimal) get(PROPERTY_REQUIREKGS);
    }

    public void setRequirekgs(BigDecimal requirekgs) {
        set(PROPERTY_REQUIREKGS, requirekgs);
    }

    public BigDecimal getExtraincones() {
        return (BigDecimal) get(PROPERTY_EXTRAINCONES);
    }

    public void setExtraincones(BigDecimal extraincones) {
        set(PROPERTY_EXTRAINCONES, extraincones);
    }

    public BigDecimal getTotalkgsrequired() {
        return (BigDecimal) get(PROPERTY_TOTALKGSREQUIRED);
    }

    public void setTotalkgsrequired(BigDecimal totalkgsrequired) {
        set(PROPERTY_TOTALKGSREQUIRED, totalkgsrequired);
    }

    public Long getDifferencevalue() {
        return (Long) get(PROPERTY_DIFFERENCEVALUE);
    }

    public void setDifferencevalue(Long differencevalue) {
        set(PROPERTY_DIFFERENCEVALUE, differencevalue);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

}
