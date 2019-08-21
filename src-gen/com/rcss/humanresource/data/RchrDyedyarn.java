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
 * Entity class for entity Rchr_Dyedyarn (stored in table Rchr_Dyedyarn).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class RchrDyedyarn extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Rchr_Dyedyarn";
    public static final String ENTITY_NAME = "Rchr_Dyedyarn";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DATE = "date";
    public static final String PROPERTY_RCHRQUALITYSTANDARD = "rchrQualitystandard";
    public static final String PROPERTY_RCHRINSPCLOTHTYPE = "rchrInspclothtype";
    public static final String PROPERTY_NOOFSECTIONS = "noofsections";
    public static final String PROPERTY_TOTALENDSPERREPEAT = "totalendsperrepeat";
    public static final String PROPERTY_WPGLENGHT = "wpglenght";
    public static final String PROPERTY_TOTALDIFFERENCE = "totaldifference";
    public static final String PROPERTY_FIRSTAVGCONELENGTH = "firstavgconelength";
    public static final String PROPERTY_SECONDAVGCONELENGTH = "secondavgconelength";
    public static final String PROPERTY_FIRSTAVGCONEWEIGHT = "firstavgconeweight";
    public static final String PROPERTY_SECONDAVGCONEWEIGHT = "secondavgconeweight";
    public static final String PROPERTY_TOTALPICKSPERREPEAT = "totalpicksperrepeat";
    public static final String PROPERTY_TOTALPICKSREQUIREDKGS = "totalpicksrequiredkgs";
    public static final String PROPERTY_TOTALENDS = "totalends";
    public static final String PROPERTY_RCHRWARPDYEDYARNREQLIST = "rchrWarpdyedyarnreqList";
    public static final String PROPERTY_RCHRWEFTDYEDYARNREQLIST = "rchrWeftdyedyarnreqList";

    public RchrDyedyarn() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_NOOFSECTIONS, (long) 0);
        setDefaultValue(PROPERTY_TOTALENDSPERREPEAT, (long) 0);
        setDefaultValue(PROPERTY_WPGLENGHT, (long) 0);
        setDefaultValue(PROPERTY_TOTALDIFFERENCE, (long) 0);
        setDefaultValue(PROPERTY_FIRSTAVGCONELENGTH, (long) 0);
        setDefaultValue(PROPERTY_SECONDAVGCONELENGTH, (long) 0);
        setDefaultValue(PROPERTY_FIRSTAVGCONEWEIGHT, new BigDecimal(0));
        setDefaultValue(PROPERTY_SECONDAVGCONEWEIGHT, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALPICKSPERREPEAT, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALPICKSREQUIREDKGS, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALENDS, (long) 0);
        setDefaultValue(PROPERTY_RCHRWARPDYEDYARNREQLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRWEFTDYEDYARNREQLIST, new ArrayList<Object>());
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

    public Date getDate() {
        return (Date) get(PROPERTY_DATE);
    }

    public void setDate(Date date) {
        set(PROPERTY_DATE, date);
    }

    public RCHRQualitystandard getRchrQualitystandard() {
        return (RCHRQualitystandard) get(PROPERTY_RCHRQUALITYSTANDARD);
    }

    public void setRchrQualitystandard(RCHRQualitystandard rchrQualitystandard) {
        set(PROPERTY_RCHRQUALITYSTANDARD, rchrQualitystandard);
    }

    public RCHR_Inspclothtype getRchrInspclothtype() {
        return (RCHR_Inspclothtype) get(PROPERTY_RCHRINSPCLOTHTYPE);
    }

    public void setRchrInspclothtype(RCHR_Inspclothtype rchrInspclothtype) {
        set(PROPERTY_RCHRINSPCLOTHTYPE, rchrInspclothtype);
    }

    public Long getNoofsections() {
        return (Long) get(PROPERTY_NOOFSECTIONS);
    }

    public void setNoofsections(Long noofsections) {
        set(PROPERTY_NOOFSECTIONS, noofsections);
    }

    public Long getTotalendsperrepeat() {
        return (Long) get(PROPERTY_TOTALENDSPERREPEAT);
    }

    public void setTotalendsperrepeat(Long totalendsperrepeat) {
        set(PROPERTY_TOTALENDSPERREPEAT, totalendsperrepeat);
    }

    public Long getWpglenght() {
        return (Long) get(PROPERTY_WPGLENGHT);
    }

    public void setWpglenght(Long wpglenght) {
        set(PROPERTY_WPGLENGHT, wpglenght);
    }

    public Long getTotaldifference() {
        return (Long) get(PROPERTY_TOTALDIFFERENCE);
    }

    public void setTotaldifference(Long totaldifference) {
        set(PROPERTY_TOTALDIFFERENCE, totaldifference);
    }

    public Long getFirstavgconelength() {
        return (Long) get(PROPERTY_FIRSTAVGCONELENGTH);
    }

    public void setFirstavgconelength(Long firstavgconelength) {
        set(PROPERTY_FIRSTAVGCONELENGTH, firstavgconelength);
    }

    public Long getSecondavgconelength() {
        return (Long) get(PROPERTY_SECONDAVGCONELENGTH);
    }

    public void setSecondavgconelength(Long secondavgconelength) {
        set(PROPERTY_SECONDAVGCONELENGTH, secondavgconelength);
    }

    public BigDecimal getFirstavgconeweight() {
        return (BigDecimal) get(PROPERTY_FIRSTAVGCONEWEIGHT);
    }

    public void setFirstavgconeweight(BigDecimal firstavgconeweight) {
        set(PROPERTY_FIRSTAVGCONEWEIGHT, firstavgconeweight);
    }

    public BigDecimal getSecondavgconeweight() {
        return (BigDecimal) get(PROPERTY_SECONDAVGCONEWEIGHT);
    }

    public void setSecondavgconeweight(BigDecimal secondavgconeweight) {
        set(PROPERTY_SECONDAVGCONEWEIGHT, secondavgconeweight);
    }

    public BigDecimal getTotalpicksperrepeat() {
        return (BigDecimal) get(PROPERTY_TOTALPICKSPERREPEAT);
    }

    public void setTotalpicksperrepeat(BigDecimal totalpicksperrepeat) {
        set(PROPERTY_TOTALPICKSPERREPEAT, totalpicksperrepeat);
    }

    public BigDecimal getTotalpicksrequiredkgs() {
        return (BigDecimal) get(PROPERTY_TOTALPICKSREQUIREDKGS);
    }

    public void setTotalpicksrequiredkgs(BigDecimal totalpicksrequiredkgs) {
        set(PROPERTY_TOTALPICKSREQUIREDKGS, totalpicksrequiredkgs);
    }

    public Long getTotalends() {
        return (Long) get(PROPERTY_TOTALENDS);
    }

    public void setTotalends(Long totalends) {
        set(PROPERTY_TOTALENDS, totalends);
    }

    @SuppressWarnings("unchecked")
    public List<RchrWarpdyedyarnreq> getRchrWarpdyedyarnreqList() {
        return (List<RchrWarpdyedyarnreq>) get(PROPERTY_RCHRWARPDYEDYARNREQLIST);
    }

    public void setRchrWarpdyedyarnreqList(List<RchrWarpdyedyarnreq> rchrWarpdyedyarnreqList) {
        set(PROPERTY_RCHRWARPDYEDYARNREQLIST, rchrWarpdyedyarnreqList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrWeftdyedyarnreq> getRchrWeftdyedyarnreqList() {
        return (List<RchrWeftdyedyarnreq>) get(PROPERTY_RCHRWEFTDYEDYARNREQLIST);
    }

    public void setRchrWeftdyedyarnreqList(List<RchrWeftdyedyarnreq> rchrWeftdyedyarnreqList) {
        set(PROPERTY_RCHRWEFTDYEDYARNREQLIST, rchrWeftdyedyarnreqList);
    }

}
