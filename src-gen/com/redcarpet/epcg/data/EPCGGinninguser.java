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
package com.redcarpet.epcg.data;

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
 * Entity class for entity EPCG_Ginninguser (stored in table EPCG_Ginninguser).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EPCGGinninguser extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "EPCG_Ginninguser";
    public static final String ENTITY_NAME = "EPCG_Ginninguser";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_GINNINGDATE = "ginningdate";
    public static final String PROPERTY_UNLOADAT = "unloadat";
    public static final String PROPERTY_LORRYNO = "lorryno";
    public static final String PROPERTY_BRIDGE = "bridge";
    public static final String PROPERTY_NETWT = "netwt";
    public static final String PROPERTY_RATE = "rate";
    public static final String PROPERTY_OT = "ot";
    public static final String PROPERTY_ADDEDOT = "addedot";
    public static final String PROPERTY_SEEDPRICE = "seedprice";
    public static final String PROPERTY_GNGEXP = "gngexp";
    public static final String PROPERTY_CANDY = "candy";
    public static final String PROPERTY_SPACE = "space";
    public static final String PROPERTY_COTTONPAY = "cottonpay";
    public static final String PROPERTY_NOOFCANDY = "noofcandy";
    public static final String PROPERTY_COSTOFLINT = "costoflint";
    public static final String PROPERTY_COLTWO = "coltwo";
    public static final String PROPERTY_WAYBSLIPNO = "waybslipno";
    public static final String PROPERTY_PASSNGBUYER = "passngbuyer";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_HEEPS = "heeps";
    public static final String PROPERTY_TAREWT = "tarewt";
    public static final String PROPERTY_EPCGKAPPASAGENT = "epcgKappasagent";
    public static final String PROPERTY_EPCGKAPPASBUYER = "epcgKappasbuyer";

    public EPCGGinninguser() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_UNLOADAT, "ICM");
        setDefaultValue(PROPERTY_BRIDGE, new BigDecimal(0));
        setDefaultValue(PROPERTY_NETWT, new BigDecimal(0));
        setDefaultValue(PROPERTY_RATE, new BigDecimal(0));
        setDefaultValue(PROPERTY_OT, new BigDecimal(0));
        setDefaultValue(PROPERTY_ADDEDOT, new BigDecimal(0));
        setDefaultValue(PROPERTY_SEEDPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_GNGEXP, new BigDecimal(0));
        setDefaultValue(PROPERTY_CANDY, new BigDecimal(0));
        setDefaultValue(PROPERTY_COTTONPAY, new BigDecimal(0));
        setDefaultValue(PROPERTY_NOOFCANDY, new BigDecimal(0));
        setDefaultValue(PROPERTY_COSTOFLINT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_TAREWT, new BigDecimal(0));
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

    public Date getGinningdate() {
        return (Date) get(PROPERTY_GINNINGDATE);
    }

    public void setGinningdate(Date ginningdate) {
        set(PROPERTY_GINNINGDATE, ginningdate);
    }

    public String getUnloadat() {
        return (String) get(PROPERTY_UNLOADAT);
    }

    public void setUnloadat(String unloadat) {
        set(PROPERTY_UNLOADAT, unloadat);
    }

    public String getLorryno() {
        return (String) get(PROPERTY_LORRYNO);
    }

    public void setLorryno(String lorryno) {
        set(PROPERTY_LORRYNO, lorryno);
    }

    public BigDecimal getBridge() {
        return (BigDecimal) get(PROPERTY_BRIDGE);
    }

    public void setBridge(BigDecimal bridge) {
        set(PROPERTY_BRIDGE, bridge);
    }

    public BigDecimal getNetwt() {
        return (BigDecimal) get(PROPERTY_NETWT);
    }

    public void setNetwt(BigDecimal netwt) {
        set(PROPERTY_NETWT, netwt);
    }

    public BigDecimal getRate() {
        return (BigDecimal) get(PROPERTY_RATE);
    }

    public void setRate(BigDecimal rate) {
        set(PROPERTY_RATE, rate);
    }

    public BigDecimal getOt() {
        return (BigDecimal) get(PROPERTY_OT);
    }

    public void setOt(BigDecimal ot) {
        set(PROPERTY_OT, ot);
    }

    public BigDecimal getAddedot() {
        return (BigDecimal) get(PROPERTY_ADDEDOT);
    }

    public void setAddedot(BigDecimal addedot) {
        set(PROPERTY_ADDEDOT, addedot);
    }

    public BigDecimal getSeedprice() {
        return (BigDecimal) get(PROPERTY_SEEDPRICE);
    }

    public void setSeedprice(BigDecimal seedprice) {
        set(PROPERTY_SEEDPRICE, seedprice);
    }

    public BigDecimal getGngexp() {
        return (BigDecimal) get(PROPERTY_GNGEXP);
    }

    public void setGngexp(BigDecimal gngexp) {
        set(PROPERTY_GNGEXP, gngexp);
    }

    public BigDecimal getCandy() {
        return (BigDecimal) get(PROPERTY_CANDY);
    }

    public void setCandy(BigDecimal candy) {
        set(PROPERTY_CANDY, candy);
    }

    public String getSpace() {
        return (String) get(PROPERTY_SPACE);
    }

    public void setSpace(String space) {
        set(PROPERTY_SPACE, space);
    }

    public BigDecimal getCottonpay() {
        return (BigDecimal) get(PROPERTY_COTTONPAY);
    }

    public void setCottonpay(BigDecimal cottonpay) {
        set(PROPERTY_COTTONPAY, cottonpay);
    }

    public BigDecimal getNoofcandy() {
        return (BigDecimal) get(PROPERTY_NOOFCANDY);
    }

    public void setNoofcandy(BigDecimal noofcandy) {
        set(PROPERTY_NOOFCANDY, noofcandy);
    }

    public BigDecimal getCostoflint() {
        return (BigDecimal) get(PROPERTY_COSTOFLINT);
    }

    public void setCostoflint(BigDecimal costoflint) {
        set(PROPERTY_COSTOFLINT, costoflint);
    }

    public String getColtwo() {
        return (String) get(PROPERTY_COLTWO);
    }

    public void setColtwo(String coltwo) {
        set(PROPERTY_COLTWO, coltwo);
    }

    public String getWaybslipno() {
        return (String) get(PROPERTY_WAYBSLIPNO);
    }

    public void setWaybslipno(String waybslipno) {
        set(PROPERTY_WAYBSLIPNO, waybslipno);
    }

    public String getPassngbuyer() {
        return (String) get(PROPERTY_PASSNGBUYER);
    }

    public void setPassngbuyer(String passngbuyer) {
        set(PROPERTY_PASSNGBUYER, passngbuyer);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public String getHeeps() {
        return (String) get(PROPERTY_HEEPS);
    }

    public void setHeeps(String heeps) {
        set(PROPERTY_HEEPS, heeps);
    }

    public BigDecimal getTarewt() {
        return (BigDecimal) get(PROPERTY_TAREWT);
    }

    public void setTarewt(BigDecimal tarewt) {
        set(PROPERTY_TAREWT, tarewt);
    }

    public EPCGKappasagent getEpcgKappasagent() {
        return (EPCGKappasagent) get(PROPERTY_EPCGKAPPASAGENT);
    }

    public void setEpcgKappasagent(EPCGKappasagent epcgKappasagent) {
        set(PROPERTY_EPCGKAPPASAGENT, epcgKappasagent);
    }

    public EPCGKappasbuyer getEpcgKappasbuyer() {
        return (EPCGKappasbuyer) get(PROPERTY_EPCGKAPPASBUYER);
    }

    public void setEpcgKappasbuyer(EPCGKappasbuyer epcgKappasbuyer) {
        set(PROPERTY_EPCGKAPPASBUYER, epcgKappasbuyer);
    }

}
