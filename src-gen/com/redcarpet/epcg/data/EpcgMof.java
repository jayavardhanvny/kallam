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
import com.rcss.humanresource.data.RCHR_Jobcard;

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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.Product;
/**
 * Entity class for entity Epcg_Mof (stored in table Epcg_Mof).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EpcgMof extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Epcg_Mof";
    public static final String ENTITY_NAME = "Epcg_Mof";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_CONTRACTDATE = "contractdate";
    public static final String PROPERTY_EPCGCOSTENQUIRY = "epcgCostenquiry";
    public static final String PROPERTY_DELIVERDATE = "deliverdate";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_RCHRQUALITYSTANDARD = "rchrQualitystandard";
    public static final String PROPERTY_ONCONTRACT = "oncontract";
    public static final String PROPERTY_QSTANDARD = "qstandard";
    public static final String PROPERTY_ORDERQUANTITY = "orderquantity";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_COPYLINES = "copylines";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_SALESORDERLINE = "salesOrderLine";
    public static final String PROPERTY_EPCGPACKAGTEMPLINES = "epcgPackagtemplines";
    public static final String PROPERTY_EPCGPACKAGINGTEMPLATE = "epcgPackagingtemplate";
    public static final String PROPERTY_AGENT = "agent";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_EPCGSALESPACKING = "epcgSalespacking";
    public static final String PROPERTY_LCNUMBER = "lcnumber";
    public static final String PROPERTY_LCDATE = "lcdate";
    public static final String PROPERTY_RCHRJOBCARDLIST = "rCHRJobcardList";

    public EpcgMof() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ORDERQUANTITY, (long) 0);
        setDefaultValue(PROPERTY_ALERTSTATUS, "OP");
        setDefaultValue(PROPERTY_COPYLINES, false);
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_RCHRJOBCARDLIST, new ArrayList<Object>());
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

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getDocumentNo() {
        return (String) get(PROPERTY_DOCUMENTNO);
    }

    public void setDocumentNo(String documentNo) {
        set(PROPERTY_DOCUMENTNO, documentNo);
    }

    public Date getContractdate() {
        return (Date) get(PROPERTY_CONTRACTDATE);
    }

    public void setContractdate(Date contractdate) {
        set(PROPERTY_CONTRACTDATE, contractdate);
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

    public RCHRQualitystandard getRchrQualitystandard() {
        return (RCHRQualitystandard) get(PROPERTY_RCHRQUALITYSTANDARD);
    }

    public void setRchrQualitystandard(RCHRQualitystandard rchrQualitystandard) {
        set(PROPERTY_RCHRQUALITYSTANDARD, rchrQualitystandard);
    }

    public String getOncontract() {
        return (String) get(PROPERTY_ONCONTRACT);
    }

    public void setOncontract(String oncontract) {
        set(PROPERTY_ONCONTRACT, oncontract);
    }

    public String getQstandard() {
        return (String) get(PROPERTY_QSTANDARD);
    }

    public void setQstandard(String qstandard) {
        set(PROPERTY_QSTANDARD, qstandard);
    }

    public Long getOrderquantity() {
        return (Long) get(PROPERTY_ORDERQUANTITY);
    }

    public void setOrderquantity(Long orderquantity) {
        set(PROPERTY_ORDERQUANTITY, orderquantity);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public Boolean isCopylines() {
        return (Boolean) get(PROPERTY_COPYLINES);
    }

    public void setCopylines(Boolean copylines) {
        set(PROPERTY_COPYLINES, copylines);
    }

    public Boolean isProcess() {
        return (Boolean) get(PROPERTY_PROCESS);
    }

    public void setProcess(Boolean process) {
        set(PROPERTY_PROCESS, process);
    }

    public OrderLine getSalesOrderLine() {
        return (OrderLine) get(PROPERTY_SALESORDERLINE);
    }

    public void setSalesOrderLine(OrderLine salesOrderLine) {
        set(PROPERTY_SALESORDERLINE, salesOrderLine);
    }

    public EpcgPackagtemplines getEpcgPackagtemplines() {
        return (EpcgPackagtemplines) get(PROPERTY_EPCGPACKAGTEMPLINES);
    }

    public void setEpcgPackagtemplines(EpcgPackagtemplines epcgPackagtemplines) {
        set(PROPERTY_EPCGPACKAGTEMPLINES, epcgPackagtemplines);
    }

    public EpcgPackagingtemplate getEpcgPackagingtemplate() {
        return (EpcgPackagingtemplate) get(PROPERTY_EPCGPACKAGINGTEMPLATE);
    }

    public void setEpcgPackagingtemplate(EpcgPackagingtemplate epcgPackagingtemplate) {
        set(PROPERTY_EPCGPACKAGINGTEMPLATE, epcgPackagingtemplate);
    }

    public String getAgent() {
        return (String) get(PROPERTY_AGENT);
    }

    public void setAgent(String agent) {
        set(PROPERTY_AGENT, agent);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public EpcgSalespacking getEpcgSalespacking() {
        return (EpcgSalespacking) get(PROPERTY_EPCGSALESPACKING);
    }

    public void setEpcgSalespacking(EpcgSalespacking epcgSalespacking) {
        set(PROPERTY_EPCGSALESPACKING, epcgSalespacking);
    }

    public String getLcnumber() {
        return (String) get(PROPERTY_LCNUMBER);
    }

    public void setLcnumber(String lcnumber) {
        set(PROPERTY_LCNUMBER, lcnumber);
    }

    public Date getLcdate() {
        return (Date) get(PROPERTY_LCDATE);
    }

    public void setLcdate(Date lcdate) {
        set(PROPERTY_LCDATE, lcdate);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Jobcard> getRCHRJobcardList() {
        return (List<RCHR_Jobcard>) get(PROPERTY_RCHRJOBCARDLIST);
    }

    public void setRCHRJobcardList(List<RCHR_Jobcard> rCHRJobcardList) {
        set(PROPERTY_RCHRJOBCARDLIST, rCHRJobcardList);
    }

}
