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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
/**
 * Entity class for entity EPCG_Bookuser (stored in table EPCG_Bookuser).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EPCG_Bookuser extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "EPCG_Bookuser";
    public static final String ENTITY_NAME = "EPCG_Bookuser";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DOCUMENTNO = "documentNo";
    public static final String PROPERTY_BOOKDATE = "bookdate";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_WAYSLIPNO = "wayslipno";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_AGENTNAME = "agentname";
    public static final String PROPERTY_TOTALPRICE = "totalprice";
    public static final String PROPERTY_BILLINGPRICE = "billingprice";
    public static final String PROPERTY_CASHPRICE = "cashprice";
    public static final String PROPERTY_FREIGHT = "freight";
    public static final String PROPERTY_NOBBB = "nobbb";
    public static final String PROPERTY_CUSTGRSWT = "custgrswt";
    public static final String PROPERTY_CUSTTREWT = "custtrewt";
    public static final String PROPERTY_CUSTNETWT = "custnetwt";
    public static final String PROPERTY_MILLGRSWT = "millgrswt";
    public static final String PROPERTY_MILLTREWT = "milltrewt";
    public static final String PROPERTY_MILLNETWT = "millnetwt";
    public static final String PROPERTY_BILLONCUSTMILL = "billoncustmill";
    public static final String PROPERTY_BILLNO = "billno";
    public static final String PROPERTY_BVASSV = "bvassv";
    public static final String PROPERTY_BVTAXV = "bvtaxv";
    public static final String PROPERTY_BVTOTLV = "bvtotlv";
    public static final String PROPERTY_NBVTOTLV = "nbvtotlv";
    public static final String PROPERTY_GRSTOTLV = "grstotlv";
    public static final String PROPERTY_PRMNGMNT = "prmngmnt";
    public static final String PROPERTY_TRUCKNO = "truckno";

    public EPCG_Bookuser() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TOTALPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_BILLINGPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_CASHPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_NOBBB, new BigDecimal(0));
        setDefaultValue(PROPERTY_CUSTGRSWT, new BigDecimal(0));
        setDefaultValue(PROPERTY_CUSTTREWT, new BigDecimal(0));
        setDefaultValue(PROPERTY_CUSTNETWT, new BigDecimal(0));
        setDefaultValue(PROPERTY_MILLGRSWT, new BigDecimal(0));
        setDefaultValue(PROPERTY_MILLTREWT, new BigDecimal(0));
        setDefaultValue(PROPERTY_MILLNETWT, new BigDecimal(0));
        setDefaultValue(PROPERTY_BILLONCUSTMILL, false);
        setDefaultValue(PROPERTY_BVASSV, new BigDecimal(0));
        setDefaultValue(PROPERTY_BVTAXV, new BigDecimal(0));
        setDefaultValue(PROPERTY_BVTOTLV, new BigDecimal(0));
        setDefaultValue(PROPERTY_NBVTOTLV, new BigDecimal(0));
        setDefaultValue(PROPERTY_GRSTOTLV, new BigDecimal(0));
        setDefaultValue(PROPERTY_PRMNGMNT, false);
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

    public Date getBookdate() {
        return (Date) get(PROPERTY_BOOKDATE);
    }

    public void setBookdate(Date bookdate) {
        set(PROPERTY_BOOKDATE, bookdate);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public String getWayslipno() {
        return (String) get(PROPERTY_WAYSLIPNO);
    }

    public void setWayslipno(String wayslipno) {
        set(PROPERTY_WAYSLIPNO, wayslipno);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public BusinessPartner getAgentname() {
        return (BusinessPartner) get(PROPERTY_AGENTNAME);
    }

    public void setAgentname(BusinessPartner agentname) {
        set(PROPERTY_AGENTNAME, agentname);
    }

    public BigDecimal getTotalprice() {
        return (BigDecimal) get(PROPERTY_TOTALPRICE);
    }

    public void setTotalprice(BigDecimal totalprice) {
        set(PROPERTY_TOTALPRICE, totalprice);
    }

    public BigDecimal getBillingprice() {
        return (BigDecimal) get(PROPERTY_BILLINGPRICE);
    }

    public void setBillingprice(BigDecimal billingprice) {
        set(PROPERTY_BILLINGPRICE, billingprice);
    }

    public BigDecimal getCashprice() {
        return (BigDecimal) get(PROPERTY_CASHPRICE);
    }

    public void setCashprice(BigDecimal cashprice) {
        set(PROPERTY_CASHPRICE, cashprice);
    }

    public BigDecimal getFreight() {
        return (BigDecimal) get(PROPERTY_FREIGHT);
    }

    public void setFreight(BigDecimal freight) {
        set(PROPERTY_FREIGHT, freight);
    }

    public BigDecimal getNobbb() {
        return (BigDecimal) get(PROPERTY_NOBBB);
    }

    public void setNobbb(BigDecimal nobbb) {
        set(PROPERTY_NOBBB, nobbb);
    }

    public BigDecimal getCustgrswt() {
        return (BigDecimal) get(PROPERTY_CUSTGRSWT);
    }

    public void setCustgrswt(BigDecimal custgrswt) {
        set(PROPERTY_CUSTGRSWT, custgrswt);
    }

    public BigDecimal getCusttrewt() {
        return (BigDecimal) get(PROPERTY_CUSTTREWT);
    }

    public void setCusttrewt(BigDecimal custtrewt) {
        set(PROPERTY_CUSTTREWT, custtrewt);
    }

    public BigDecimal getCustnetwt() {
        return (BigDecimal) get(PROPERTY_CUSTNETWT);
    }

    public void setCustnetwt(BigDecimal custnetwt) {
        set(PROPERTY_CUSTNETWT, custnetwt);
    }

    public BigDecimal getMillgrswt() {
        return (BigDecimal) get(PROPERTY_MILLGRSWT);
    }

    public void setMillgrswt(BigDecimal millgrswt) {
        set(PROPERTY_MILLGRSWT, millgrswt);
    }

    public BigDecimal getMilltrewt() {
        return (BigDecimal) get(PROPERTY_MILLTREWT);
    }

    public void setMilltrewt(BigDecimal milltrewt) {
        set(PROPERTY_MILLTREWT, milltrewt);
    }

    public BigDecimal getMillnetwt() {
        return (BigDecimal) get(PROPERTY_MILLNETWT);
    }

    public void setMillnetwt(BigDecimal millnetwt) {
        set(PROPERTY_MILLNETWT, millnetwt);
    }

    public Boolean isBilloncustmill() {
        return (Boolean) get(PROPERTY_BILLONCUSTMILL);
    }

    public void setBilloncustmill(Boolean billoncustmill) {
        set(PROPERTY_BILLONCUSTMILL, billoncustmill);
    }

    public String getBillno() {
        return (String) get(PROPERTY_BILLNO);
    }

    public void setBillno(String billno) {
        set(PROPERTY_BILLNO, billno);
    }

    public BigDecimal getBvassv() {
        return (BigDecimal) get(PROPERTY_BVASSV);
    }

    public void setBvassv(BigDecimal bvassv) {
        set(PROPERTY_BVASSV, bvassv);
    }

    public BigDecimal getBvtaxv() {
        return (BigDecimal) get(PROPERTY_BVTAXV);
    }

    public void setBvtaxv(BigDecimal bvtaxv) {
        set(PROPERTY_BVTAXV, bvtaxv);
    }

    public BigDecimal getBvtotlv() {
        return (BigDecimal) get(PROPERTY_BVTOTLV);
    }

    public void setBvtotlv(BigDecimal bvtotlv) {
        set(PROPERTY_BVTOTLV, bvtotlv);
    }

    public BigDecimal getNbvtotlv() {
        return (BigDecimal) get(PROPERTY_NBVTOTLV);
    }

    public void setNbvtotlv(BigDecimal nbvtotlv) {
        set(PROPERTY_NBVTOTLV, nbvtotlv);
    }

    public BigDecimal getGrstotlv() {
        return (BigDecimal) get(PROPERTY_GRSTOTLV);
    }

    public void setGrstotlv(BigDecimal grstotlv) {
        set(PROPERTY_GRSTOTLV, grstotlv);
    }

    public Boolean isPrmngmnt() {
        return (Boolean) get(PROPERTY_PRMNGMNT);
    }

    public void setPrmngmnt(Boolean prmngmnt) {
        set(PROPERTY_PRMNGMNT, prmngmnt);
    }

    public String getTruckno() {
        return (String) get(PROPERTY_TRUCKNO);
    }

    public void setTruckno(String truckno) {
        set(PROPERTY_TRUCKNO, truckno);
    }

}
