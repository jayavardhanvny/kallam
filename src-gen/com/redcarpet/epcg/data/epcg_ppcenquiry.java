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

import com.rcss.humanresource.data.RCHRQualitystandard;
import com.rcss.humanresource.data.RchrEmployee;

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
import org.openbravo.model.common.plm.Product;
/**
 * Entity class for entity epcg_ppcenquiry (stored in table epcg_ppcenquiry).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class epcg_ppcenquiry extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "epcg_ppcenquiry";
    public static final String ENTITY_NAME = "epcg_ppcenquiry";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_ENQUIRYDATE = "enquirydate";
    public static final String PROPERTY_TC = "tc";
    public static final String PROPERTY_WIDTHINCHES = "widthinches";
    public static final String PROPERTY_SALESCOMMISSION = "salescommission";
    public static final String PROPERTY_CREDITPERIODPER30 = "creditperiodper30";
    public static final String PROPERTY_YARNCOSTMTS = "yarncostmts";
    public static final String PROPERTY_CONTRIBUTIONRSPLOOM = "contributionrsploom";
    public static final String PROPERTY_TOTALVARIABLECOSTMTS = "totalvariablecostmts";
    public static final String PROPERTY_EXMILLPRICERSPERMTS = "exmillpricerspermts";
    public static final String PROPERTY_COMMISSION = "commission";
    public static final String PROPERTY_RCHRQUALITYSTANDARD = "rchrQualitystandard";
    public static final String PROPERTY_QSTANDARD = "qstandard";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_EPCGCOSTENQUIRY = "epcgCostenquiry";
    public static final String PROPERTY_DELIVERDATE = "deliverdate";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_RCHREMPLOYEE = "rchrEmployee";
    public static final String PROPERTY_USERID = "userid";
    public static final String PROPERTY_ONCONTRACT = "oncontract";
    public static final String PROPERTY_ORDERQUANTITY = "orderquantity";
    public static final String PROPERTY_REMARKS = "remarks";
    public static final String PROPERTY_YARNAVAILIBILITY = "yarnavailibility";
    public static final String PROPERTY_REEDAVAILIBILITY = "reedavailibility";
    public static final String PROPERTY_CAMAVAILIBILITY = "camavailibility";
    public static final String PROPERTY_MINWARPLENGTH = "minwarplength";

    public epcg_ppcenquiry() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TC, new BigDecimal(0));
        setDefaultValue(PROPERTY_WIDTHINCHES, new BigDecimal(0));
        setDefaultValue(PROPERTY_CONTRIBUTIONRSPLOOM, new BigDecimal(0));
        setDefaultValue(PROPERTY_EXMILLPRICERSPERMTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_COMMISSION, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_ALERTSTATUS, "DR");
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

    public Date getEnquirydate() {
        return (Date) get(PROPERTY_ENQUIRYDATE);
    }

    public void setEnquirydate(Date enquirydate) {
        set(PROPERTY_ENQUIRYDATE, enquirydate);
    }

    public BigDecimal getTc() {
        return (BigDecimal) get(PROPERTY_TC);
    }

    public void setTc(BigDecimal tc) {
        set(PROPERTY_TC, tc);
    }

    public BigDecimal getWidthinches() {
        return (BigDecimal) get(PROPERTY_WIDTHINCHES);
    }

    public void setWidthinches(BigDecimal widthinches) {
        set(PROPERTY_WIDTHINCHES, widthinches);
    }

    public BigDecimal getSalescommission() {
        return (BigDecimal) get(PROPERTY_SALESCOMMISSION);
    }

    public void setSalescommission(BigDecimal salescommission) {
        set(PROPERTY_SALESCOMMISSION, salescommission);
    }

    public BigDecimal getCreditperiodper30() {
        return (BigDecimal) get(PROPERTY_CREDITPERIODPER30);
    }

    public void setCreditperiodper30(BigDecimal creditperiodper30) {
        set(PROPERTY_CREDITPERIODPER30, creditperiodper30);
    }

    public BigDecimal getYarncostmts() {
        return (BigDecimal) get(PROPERTY_YARNCOSTMTS);
    }

    public void setYarncostmts(BigDecimal yarncostmts) {
        set(PROPERTY_YARNCOSTMTS, yarncostmts);
    }

    public BigDecimal getContributionrsploom() {
        return (BigDecimal) get(PROPERTY_CONTRIBUTIONRSPLOOM);
    }

    public void setContributionrsploom(BigDecimal contributionrsploom) {
        set(PROPERTY_CONTRIBUTIONRSPLOOM, contributionrsploom);
    }

    public BigDecimal getTotalvariablecostmts() {
        return (BigDecimal) get(PROPERTY_TOTALVARIABLECOSTMTS);
    }

    public void setTotalvariablecostmts(BigDecimal totalvariablecostmts) {
        set(PROPERTY_TOTALVARIABLECOSTMTS, totalvariablecostmts);
    }

    public BigDecimal getExmillpricerspermts() {
        return (BigDecimal) get(PROPERTY_EXMILLPRICERSPERMTS);
    }

    public void setExmillpricerspermts(BigDecimal exmillpricerspermts) {
        set(PROPERTY_EXMILLPRICERSPERMTS, exmillpricerspermts);
    }

    public BigDecimal getCommission() {
        return (BigDecimal) get(PROPERTY_COMMISSION);
    }

    public void setCommission(BigDecimal commission) {
        set(PROPERTY_COMMISSION, commission);
    }

    public RCHRQualitystandard getRchrQualitystandard() {
        return (RCHRQualitystandard) get(PROPERTY_RCHRQUALITYSTANDARD);
    }

    public void setRchrQualitystandard(RCHRQualitystandard rchrQualitystandard) {
        set(PROPERTY_RCHRQUALITYSTANDARD, rchrQualitystandard);
    }

    public String getQstandard() {
        return (String) get(PROPERTY_QSTANDARD);
    }

    public void setQstandard(String qstandard) {
        set(PROPERTY_QSTANDARD, qstandard);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public EpcgCostenquiry getEpcgCostenquiry() {
        return (EpcgCostenquiry) get(PROPERTY_EPCGCOSTENQUIRY);
    }

    public void setEpcgCostenquiry(EpcgCostenquiry epcgCostenquiry) {
        set(PROPERTY_EPCGCOSTENQUIRY, epcgCostenquiry);
    }

    public Date getDeliverdate() {
        return (Date) get(PROPERTY_DELIVERDATE);
    }

    public void setDeliverdate(Date deliverdate) {
        set(PROPERTY_DELIVERDATE, deliverdate);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public RchrEmployee getRchrEmployee() {
        return (RchrEmployee) get(PROPERTY_RCHREMPLOYEE);
    }

    public void setRchrEmployee(RchrEmployee rchrEmployee) {
        set(PROPERTY_RCHREMPLOYEE, rchrEmployee);
    }

    public User getUserid() {
        return (User) get(PROPERTY_USERID);
    }

    public void setUserid(User userid) {
        set(PROPERTY_USERID, userid);
    }

    public String getOncontract() {
        return (String) get(PROPERTY_ONCONTRACT);
    }

    public void setOncontract(String oncontract) {
        set(PROPERTY_ONCONTRACT, oncontract);
    }

    public BigDecimal getOrderquantity() {
        return (BigDecimal) get(PROPERTY_ORDERQUANTITY);
    }

    public void setOrderquantity(BigDecimal orderquantity) {
        set(PROPERTY_ORDERQUANTITY, orderquantity);
    }

    public String getRemarks() {
        return (String) get(PROPERTY_REMARKS);
    }

    public void setRemarks(String remarks) {
        set(PROPERTY_REMARKS, remarks);
    }

    public String getYarnavailibility() {
        return (String) get(PROPERTY_YARNAVAILIBILITY);
    }

    public void setYarnavailibility(String yarnavailibility) {
        set(PROPERTY_YARNAVAILIBILITY, yarnavailibility);
    }

    public String getReedavailibility() {
        return (String) get(PROPERTY_REEDAVAILIBILITY);
    }

    public void setReedavailibility(String reedavailibility) {
        set(PROPERTY_REEDAVAILIBILITY, reedavailibility);
    }

    public String getCamavailibility() {
        return (String) get(PROPERTY_CAMAVAILIBILITY);
    }

    public void setCamavailibility(String camavailibility) {
        set(PROPERTY_CAMAVAILIBILITY, camavailibility);
    }

    public Long getMinwarplength() {
        return (Long) get(PROPERTY_MINWARPLENGTH);
    }

    public void setMinwarplength(Long minwarplength) {
        set(PROPERTY_MINWARPLENGTH, minwarplength);
    }

}
