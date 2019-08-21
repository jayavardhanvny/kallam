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

import com.rcss.humanresource.data.RCHRInspweave;
import com.rcss.humanresource.data.RCHRQualitystandard;
import com.rcss.humanresource.data.RCHR_Orderstatus;
import com.rcss.humanresource.data.RchrEmployee;

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
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.payment.FIN_PaymentMethod;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
import org.openbravo.model.financialmgmt.tax.TaxRate;
import org.openbravo.model.pricing.pricelist.PriceList;
/**
 * Entity class for entity Epcg_Costenquiry (stored in table Epcg_Costenquiry).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class EpcgCostenquiry extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "Epcg_Costenquiry";
    public static final String ENTITY_NAME = "Epcg_Costenquiry";
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
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_AGENT = "agent";
    public static final String PROPERTY_SELVEDGECOUNT = "selvedgecount";
    public static final String PROPERTY_EPI = "epi";
    public static final String PROPERTY_PPI = "ppi";
    public static final String PROPERTY_PICKINSERT = "pickinsert";
    public static final String PROPERTY_TC = "tc";
    public static final String PROPERTY_SPILIT = "spilit";
    public static final String PROPERTY_WIDTHINCHES = "widthinches";
    public static final String PROPERTY_SPEED = "speed";
    public static final String PROPERTY_EFFICIENCY = "efficiency";
    public static final String PROPERTY_WARPRATEKGS = "warpratekgs";
    public static final String PROPERTY_WEFTRATEKGS = "weftratekgs";
    public static final String PROPERTY_SELVEDGERATEKGS = "selvedgeratekgs";
    public static final String PROPERTY_WARPWTKGSPERMTS = "warpwtkgspermts";
    public static final String PROPERTY_WEFTWTKGSPERMTS = "weftwtkgspermts";
    public static final String PROPERTY_TOTALWTKGSPERMTS = "totalwtkgspermts";
    public static final String PROPERTY_WARPWTKGSWITHWASTE = "warpwtkgswithwaste";
    public static final String PROPERTY_WEFTWTKGSWITHWASTE = "weftwtkgswithwaste";
    public static final String PROPERTY_TOTALWTKGSWITHWASTE = "totalwtkgswithwaste";
    public static final String PROPERTY_NOOFLOOMSWORKED = "noofloomsworked";
    public static final String PROPERTY_YEILDPDAYPLOOM = "yeildpdayploom";
    public static final String PROPERTY_YEILDPDAYPLOOMONEFFCNY = "yeildpdayploomoneffcny";
    public static final String PROPERTY_WORKINGDAYS = "workingdays";
    public static final String PROPERTY_TOTALPRODUCTIONMTS = "totalproductionmts";
    public static final String PROPERTY_SALESCOMMISSION = "salescommission";
    public static final String PROPERTY_CREDITPERIOD = "creditperiod";
    public static final String PROPERTY_CREDITPERIODPER30 = "creditperiodper30";
    public static final String PROPERTY_YARNCOSTMTS = "yarncostmts";
    public static final String PROPERTY_SIZINGFROMMASTER = "sizingfrommaster";
    public static final String PROPERTY_SIZINGCHEMICALMTS = "sizingchemicalmts";
    public static final String PROPERTY_PCKGMATRLCONSUMPTIONMTS = "pckgmatrlconsumptionmts";
    public static final String PROPERTY_TOTALVARIABLECOSTMTS = "totalvariablecostmts";
    public static final String PROPERTY_EXMILLPRICERSPERMTS = "exmillpricerspermts";
    public static final String PROPERTY_COMMISSION = "commission";
    public static final String PROPERTY_INTERESTCOST = "interestcost";
    public static final String PROPERTY_NETPRICE = "netprice";
    public static final String PROPERTY_PERPICKCONTRIBUTIOIN = "perpickcontributioin";
    public static final String PROPERTY_CONTRIBUTIONRSPLOOM = "contributionrsploom";
    public static final String PROPERTY_ORDERQUANTITY = "orderquantity";
    public static final String PROPERTY_ORDERQTYWARPWTWITHWASTE = "orderqtywarpwtwithwaste";
    public static final String PROPERTY_ORDERQTYWEFTWTWITHWASTE = "orderqtyweftwtwithwaste";
    public static final String PROPERTY_LESSDEPB = "lessdepb";
    public static final String PROPERTY_LESSDEPTBCAL = "lessdeptbcal";
    public static final String PROPERTY_EXMILLPRICEUSDPERYD = "exmillpriceusdperyd";
    public static final String PROPERTY_EXMILLPRICEUSDPERYDCAL = "exmillpriceusdperydcal";
    public static final String PROPERTY_EXPORTFREIGHTUSDPERYD = "exportfreightusdperyd";
    public static final String PROPERTY_DOCUMENTTYPE = "documentType";
    public static final String PROPERTY_ALERTSTATUS = "alertStatus";
    public static final String PROPERTY_EPCGVARIANT = "epcgVariant";
    public static final String PROPERTY_GSMWITH = "gsmwith";
    public static final String PROPERTY_GLMWITH = "glmwith";
    public static final String PROPERTY_GSMWITHOUT = "gsmwithout";
    public static final String PROPERTY_PRODDAYLOOMHUN = "prodDayLoomHun";
    public static final String PROPERTY_PRODDAYLOOMEFF = "prodDayLoomEff";
    public static final String PROPERTY_PRODUCT = "product";
    public static final String PROPERTY_WEFTPRODUCT = "weftproduct";
    public static final String PROPERTY_FINALPRICE = "finalprice";
    public static final String PROPERTY_INTERESTCOSTNEW = "interestcostnew";
    public static final String PROPERTY_PERYD = "peryd";
    public static final String PROPERTY_EPCGYARNCOSTTEMPLATELINES = "epcgYarncosttemplatelines";
    public static final String PROPERTY_EPCGYARNCOSTTEMPLATELINESW = "epcgYarncosttemplatelinesW";
    public static final String PROPERTY_QSTANDARD = "qstandard";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_COMPLETE = "complete";
    public static final String PROPERTY_DELIVERDATE = "deliverdate";
    public static final String PROPERTY_PAYMENTTERMS = "paymentTerms";
    public static final String PROPERTY_PAYMENTMETHOD = "paymentMethod";
    public static final String PROPERTY_EXMILLPRICEORLANDED = "exmillpriceorlanded";
    public static final String PROPERTY_TRANSPORTATIONCOST = "transportationcost";
    public static final String PROPERTY_RCHRINSPWEAVE = "rchrInspweave";
    public static final String PROPERTY_ADDITIONALCOST = "additionalcost";
    public static final String PROPERTY_DELIVERYTYPECHARGES = "deliverytypecharges";
    public static final String PROPERTY_RCHREMPLOYEE = "rchrEmployee";
    public static final String PROPERTY_RCHRORDERSTATUS = "rchrOrderstatus";
    public static final String PROPERTY_USERID = "userid";
    public static final String PROPERTY_RCHRQUALITYSTANDARD = "rchrQualitystandard";
    public static final String PROPERTY_EPCGINSURANCETYPE = "epcgInsurancetype";
    public static final String PROPERTY_APPROVAL = "approval";
    public static final String PROPERTY_ONCONTRACT = "oncontract";
    public static final String PROPERTY_EPCGORDERTYPE = "epcgOrdertype";
    public static final String PROPERTY_CASHDISCOUNT = "cashdiscount";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_EPCGSALESPACKING = "epcgSalespacking";
    public static final String PROPERTY_ACTUALSPEED = "actualspeed";
    public static final String PROPERTY_ACTUALEFFICIENCY = "actualefficiency";
    public static final String PROPERTY_UOM = "uOM";
    public static final String PROPERTY_PRICELIST = "priceList";
    public static final String PROPERTY_CURRENCY = "currency";
    public static final String PROPERTY_INVOICETERMS = "invoiceTerms";
    public static final String PROPERTY_EPCGRATETYPE = "epcgRatetype";
    public static final String PROPERTY_EPCGDELIVERMODE = "epcgDelivermode";
    public static final String PROPERTY_TAX = "tax";
    public static final String PROPERTY_INSURANCE = "insurance";
    public static final String PROPERTY_WARPCRIMPVALUES = "warpcrimpvalues";
    public static final String PROPERTY_WARPCRIMP = "warpcrimp";
    public static final String PROPERTY_LOSSOFFABRICPERCENT = "lossoffabricpercent";
    public static final String PROPERTY_FABRICLOSS = "fabricloss";
    public static final String PROPERTY_STOCK = "stock";
    public static final String PROPERTY_FABRICWIDTH = "fabricwidth";
    public static final String PROPERTY_ONESIDESELVEDGEWIDTH = "onesideselvedgewidth";
    public static final String PROPERTY_PARTNERADDRESS = "partnerAddress";
    public static final String PROPERTY_POREFERENCENO = "poreferenceno";
    public static final String PROPERTY_WEFTVARIANT = "weftvariant";
    public static final String PROPERTY_REED = "reed";
    public static final String PROPERTY_ONLOOMEPI = "onloomepi";
    public static final String PROPERTY_TYPEOFSORT = "typeofsort";
    public static final String PROPERTY_EPCGMOFLIST = "epcgMofList";
    public static final String PROPERTY_EPCGWARPCOSTINGLIST = "epcgWarpcostingList";
    public static final String PROPERTY_EPCGWEFTCOSTINGLIST = "epcgWeftcostingList";
    public static final String PROPERTY_ORDERLIST = "orderList";
    public static final String PROPERTY_ORDERLINELIST = "orderLineList";
    public static final String PROPERTY_EPCGPPCENQUIRYLIST = "epcgPpcenquiryList";

    public EpcgCostenquiry() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_SELVEDGECOUNT, "0");
        setDefaultValue(PROPERTY_EPI, new BigDecimal(0));
        setDefaultValue(PROPERTY_PPI, new BigDecimal(0));
        setDefaultValue(PROPERTY_PICKINSERT, new BigDecimal(1));
        setDefaultValue(PROPERTY_TC, new BigDecimal(0));
        setDefaultValue(PROPERTY_SPILIT, new BigDecimal(1));
        setDefaultValue(PROPERTY_WIDTHINCHES, new BigDecimal(0));
        setDefaultValue(PROPERTY_SPEED, new BigDecimal(0));
        setDefaultValue(PROPERTY_EFFICIENCY, new BigDecimal(0));
        setDefaultValue(PROPERTY_WARPRATEKGS, new BigDecimal(0));
        setDefaultValue(PROPERTY_WEFTRATEKGS, new BigDecimal(0));
        setDefaultValue(PROPERTY_SELVEDGERATEKGS, new BigDecimal(0));
        setDefaultValue(PROPERTY_WARPWTKGSPERMTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_WEFTWTKGSPERMTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALWTKGSPERMTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_WARPWTKGSWITHWASTE, new BigDecimal(0));
        setDefaultValue(PROPERTY_WEFTWTKGSWITHWASTE, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALWTKGSWITHWASTE, new BigDecimal(0));
        setDefaultValue(PROPERTY_NOOFLOOMSWORKED, new BigDecimal(1));
        setDefaultValue(PROPERTY_YEILDPDAYPLOOM, new BigDecimal(0));
        setDefaultValue(PROPERTY_YEILDPDAYPLOOMONEFFCNY, new BigDecimal(0));
        setDefaultValue(PROPERTY_WORKINGDAYS, new BigDecimal(0));
        setDefaultValue(PROPERTY_TOTALPRODUCTIONMTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_SALESCOMMISSION, new BigDecimal(0));
        setDefaultValue(PROPERTY_CREDITPERIODPER30, new BigDecimal(0));
        setDefaultValue(PROPERTY_YARNCOSTMTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_SIZINGFROMMASTER, new BigDecimal(0));
        setDefaultValue(PROPERTY_SIZINGCHEMICALMTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_PCKGMATRLCONSUMPTIONMTS, new BigDecimal(1));
        setDefaultValue(PROPERTY_TOTALVARIABLECOSTMTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_EXMILLPRICERSPERMTS, new BigDecimal(0));
        setDefaultValue(PROPERTY_COMMISSION, new BigDecimal(0));
        setDefaultValue(PROPERTY_INTERESTCOST, new BigDecimal(0));
        setDefaultValue(PROPERTY_NETPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_PERPICKCONTRIBUTIOIN, new BigDecimal(0));
        setDefaultValue(PROPERTY_CONTRIBUTIONRSPLOOM, new BigDecimal(0));
        setDefaultValue(PROPERTY_ORDERQUANTITY, new BigDecimal(0));
        setDefaultValue(PROPERTY_ORDERQTYWARPWTWITHWASTE, new BigDecimal(0));
        setDefaultValue(PROPERTY_ORDERQTYWEFTWTWITHWASTE, new BigDecimal(0));
        setDefaultValue(PROPERTY_LESSDEPB, new BigDecimal(0));
        setDefaultValue(PROPERTY_LESSDEPTBCAL, new BigDecimal(0));
        setDefaultValue(PROPERTY_EXMILLPRICEUSDPERYD, new BigDecimal(0));
        setDefaultValue(PROPERTY_EXMILLPRICEUSDPERYDCAL, new BigDecimal(0));
        setDefaultValue(PROPERTY_EXPORTFREIGHTUSDPERYD, new BigDecimal(0));
        setDefaultValue(PROPERTY_ALERTSTATUS, "DR");
        setDefaultValue(PROPERTY_GSMWITH, new BigDecimal(0));
        setDefaultValue(PROPERTY_GLMWITH, new BigDecimal(0));
        setDefaultValue(PROPERTY_GSMWITHOUT, new BigDecimal(0));
        setDefaultValue(PROPERTY_PRODDAYLOOMHUN, new BigDecimal(0));
        setDefaultValue(PROPERTY_PRODDAYLOOMEFF, new BigDecimal(0));
        setDefaultValue(PROPERTY_FINALPRICE, new BigDecimal(0));
        setDefaultValue(PROPERTY_INTERESTCOSTNEW, new BigDecimal(0));
        setDefaultValue(PROPERTY_PERYD, new BigDecimal(0));
        setDefaultValue(PROPERTY_PROCESS, false);
        setDefaultValue(PROPERTY_COMPLETE, false);
        setDefaultValue(PROPERTY_EXMILLPRICEORLANDED, new BigDecimal(0));
        setDefaultValue(PROPERTY_TRANSPORTATIONCOST, new BigDecimal(0));
        setDefaultValue(PROPERTY_ADDITIONALCOST, new BigDecimal(0));
        setDefaultValue(PROPERTY_APPROVAL, false);
        setDefaultValue(PROPERTY_EPCGORDERTYPE, "SO");
        setDefaultValue(PROPERTY_CASHDISCOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_ACTUALSPEED, new BigDecimal(0));
        setDefaultValue(PROPERTY_ACTUALEFFICIENCY, new BigDecimal(0));
        setDefaultValue(PROPERTY_INVOICETERMS, "D");
        setDefaultValue(PROPERTY_INSURANCE, new BigDecimal(0));
        setDefaultValue(PROPERTY_LOSSOFFABRICPERCENT, new BigDecimal(1));
        setDefaultValue(PROPERTY_FABRICWIDTH, new BigDecimal(0));
        setDefaultValue(PROPERTY_EPCGMOFLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGWARPCOSTINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_EPCGWEFTCOSTINGLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORDERLINELIST, new ArrayList<Object>());
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

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public BusinessPartner getAgent() {
        return (BusinessPartner) get(PROPERTY_AGENT);
    }

    public void setAgent(BusinessPartner agent) {
        set(PROPERTY_AGENT, agent);
    }

    public String getSelvedgecount() {
        return (String) get(PROPERTY_SELVEDGECOUNT);
    }

    public void setSelvedgecount(String selvedgecount) {
        set(PROPERTY_SELVEDGECOUNT, selvedgecount);
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

    public BigDecimal getPickinsert() {
        return (BigDecimal) get(PROPERTY_PICKINSERT);
    }

    public void setPickinsert(BigDecimal pickinsert) {
        set(PROPERTY_PICKINSERT, pickinsert);
    }

    public BigDecimal getTc() {
        return (BigDecimal) get(PROPERTY_TC);
    }

    public void setTc(BigDecimal tc) {
        set(PROPERTY_TC, tc);
    }

    public BigDecimal getSpilit() {
        return (BigDecimal) get(PROPERTY_SPILIT);
    }

    public void setSpilit(BigDecimal spilit) {
        set(PROPERTY_SPILIT, spilit);
    }

    public BigDecimal getWidthinches() {
        return (BigDecimal) get(PROPERTY_WIDTHINCHES);
    }

    public void setWidthinches(BigDecimal widthinches) {
        set(PROPERTY_WIDTHINCHES, widthinches);
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

    public BigDecimal getWarpratekgs() {
        return (BigDecimal) get(PROPERTY_WARPRATEKGS);
    }

    public void setWarpratekgs(BigDecimal warpratekgs) {
        set(PROPERTY_WARPRATEKGS, warpratekgs);
    }

    public BigDecimal getWeftratekgs() {
        return (BigDecimal) get(PROPERTY_WEFTRATEKGS);
    }

    public void setWeftratekgs(BigDecimal weftratekgs) {
        set(PROPERTY_WEFTRATEKGS, weftratekgs);
    }

    public BigDecimal getSelvedgeratekgs() {
        return (BigDecimal) get(PROPERTY_SELVEDGERATEKGS);
    }

    public void setSelvedgeratekgs(BigDecimal selvedgeratekgs) {
        set(PROPERTY_SELVEDGERATEKGS, selvedgeratekgs);
    }

    public BigDecimal getWarpwtkgspermts() {
        return (BigDecimal) get(PROPERTY_WARPWTKGSPERMTS);
    }

    public void setWarpwtkgspermts(BigDecimal warpwtkgspermts) {
        set(PROPERTY_WARPWTKGSPERMTS, warpwtkgspermts);
    }

    public BigDecimal getWeftwtkgspermts() {
        return (BigDecimal) get(PROPERTY_WEFTWTKGSPERMTS);
    }

    public void setWeftwtkgspermts(BigDecimal weftwtkgspermts) {
        set(PROPERTY_WEFTWTKGSPERMTS, weftwtkgspermts);
    }

    public BigDecimal getTotalwtkgspermts() {
        return (BigDecimal) get(PROPERTY_TOTALWTKGSPERMTS);
    }

    public void setTotalwtkgspermts(BigDecimal totalwtkgspermts) {
        set(PROPERTY_TOTALWTKGSPERMTS, totalwtkgspermts);
    }

    public BigDecimal getWarpwtkgswithwaste() {
        return (BigDecimal) get(PROPERTY_WARPWTKGSWITHWASTE);
    }

    public void setWarpwtkgswithwaste(BigDecimal warpwtkgswithwaste) {
        set(PROPERTY_WARPWTKGSWITHWASTE, warpwtkgswithwaste);
    }

    public BigDecimal getWeftwtkgswithwaste() {
        return (BigDecimal) get(PROPERTY_WEFTWTKGSWITHWASTE);
    }

    public void setWeftwtkgswithwaste(BigDecimal weftwtkgswithwaste) {
        set(PROPERTY_WEFTWTKGSWITHWASTE, weftwtkgswithwaste);
    }

    public BigDecimal getTotalwtkgswithwaste() {
        return (BigDecimal) get(PROPERTY_TOTALWTKGSWITHWASTE);
    }

    public void setTotalwtkgswithwaste(BigDecimal totalwtkgswithwaste) {
        set(PROPERTY_TOTALWTKGSWITHWASTE, totalwtkgswithwaste);
    }

    public BigDecimal getNoofloomsworked() {
        return (BigDecimal) get(PROPERTY_NOOFLOOMSWORKED);
    }

    public void setNoofloomsworked(BigDecimal noofloomsworked) {
        set(PROPERTY_NOOFLOOMSWORKED, noofloomsworked);
    }

    public BigDecimal getYeildpdayploom() {
        return (BigDecimal) get(PROPERTY_YEILDPDAYPLOOM);
    }

    public void setYeildpdayploom(BigDecimal yeildpdayploom) {
        set(PROPERTY_YEILDPDAYPLOOM, yeildpdayploom);
    }

    public BigDecimal getYeildpdayploomoneffcny() {
        return (BigDecimal) get(PROPERTY_YEILDPDAYPLOOMONEFFCNY);
    }

    public void setYeildpdayploomoneffcny(BigDecimal yeildpdayploomoneffcny) {
        set(PROPERTY_YEILDPDAYPLOOMONEFFCNY, yeildpdayploomoneffcny);
    }

    public BigDecimal getWorkingdays() {
        return (BigDecimal) get(PROPERTY_WORKINGDAYS);
    }

    public void setWorkingdays(BigDecimal workingdays) {
        set(PROPERTY_WORKINGDAYS, workingdays);
    }

    public BigDecimal getTotalproductionmts() {
        return (BigDecimal) get(PROPERTY_TOTALPRODUCTIONMTS);
    }

    public void setTotalproductionmts(BigDecimal totalproductionmts) {
        set(PROPERTY_TOTALPRODUCTIONMTS, totalproductionmts);
    }

    public BigDecimal getSalescommission() {
        return (BigDecimal) get(PROPERTY_SALESCOMMISSION);
    }

    public void setSalescommission(BigDecimal salescommission) {
        set(PROPERTY_SALESCOMMISSION, salescommission);
    }

    public BigDecimal getCreditperiod() {
        return (BigDecimal) get(PROPERTY_CREDITPERIOD);
    }

    public void setCreditperiod(BigDecimal creditperiod) {
        set(PROPERTY_CREDITPERIOD, creditperiod);
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

    public BigDecimal getSizingfrommaster() {
        return (BigDecimal) get(PROPERTY_SIZINGFROMMASTER);
    }

    public void setSizingfrommaster(BigDecimal sizingfrommaster) {
        set(PROPERTY_SIZINGFROMMASTER, sizingfrommaster);
    }

    public BigDecimal getSizingchemicalmts() {
        return (BigDecimal) get(PROPERTY_SIZINGCHEMICALMTS);
    }

    public void setSizingchemicalmts(BigDecimal sizingchemicalmts) {
        set(PROPERTY_SIZINGCHEMICALMTS, sizingchemicalmts);
    }

    public BigDecimal getPckgmatrlconsumptionmts() {
        return (BigDecimal) get(PROPERTY_PCKGMATRLCONSUMPTIONMTS);
    }

    public void setPckgmatrlconsumptionmts(BigDecimal pckgmatrlconsumptionmts) {
        set(PROPERTY_PCKGMATRLCONSUMPTIONMTS, pckgmatrlconsumptionmts);
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

    public BigDecimal getInterestcost() {
        return (BigDecimal) get(PROPERTY_INTERESTCOST);
    }

    public void setInterestcost(BigDecimal interestcost) {
        set(PROPERTY_INTERESTCOST, interestcost);
    }

    public BigDecimal getNetprice() {
        return (BigDecimal) get(PROPERTY_NETPRICE);
    }

    public void setNetprice(BigDecimal netprice) {
        set(PROPERTY_NETPRICE, netprice);
    }

    public BigDecimal getPerpickcontributioin() {
        return (BigDecimal) get(PROPERTY_PERPICKCONTRIBUTIOIN);
    }

    public void setPerpickcontributioin(BigDecimal perpickcontributioin) {
        set(PROPERTY_PERPICKCONTRIBUTIOIN, perpickcontributioin);
    }

    public BigDecimal getContributionrsploom() {
        return (BigDecimal) get(PROPERTY_CONTRIBUTIONRSPLOOM);
    }

    public void setContributionrsploom(BigDecimal contributionrsploom) {
        set(PROPERTY_CONTRIBUTIONRSPLOOM, contributionrsploom);
    }

    public BigDecimal getOrderquantity() {
        return (BigDecimal) get(PROPERTY_ORDERQUANTITY);
    }

    public void setOrderquantity(BigDecimal orderquantity) {
        set(PROPERTY_ORDERQUANTITY, orderquantity);
    }

    public BigDecimal getOrderqtywarpwtwithwaste() {
        return (BigDecimal) get(PROPERTY_ORDERQTYWARPWTWITHWASTE);
    }

    public void setOrderqtywarpwtwithwaste(BigDecimal orderqtywarpwtwithwaste) {
        set(PROPERTY_ORDERQTYWARPWTWITHWASTE, orderqtywarpwtwithwaste);
    }

    public BigDecimal getOrderqtyweftwtwithwaste() {
        return (BigDecimal) get(PROPERTY_ORDERQTYWEFTWTWITHWASTE);
    }

    public void setOrderqtyweftwtwithwaste(BigDecimal orderqtyweftwtwithwaste) {
        set(PROPERTY_ORDERQTYWEFTWTWITHWASTE, orderqtyweftwtwithwaste);
    }

    public BigDecimal getLessdepb() {
        return (BigDecimal) get(PROPERTY_LESSDEPB);
    }

    public void setLessdepb(BigDecimal lessdepb) {
        set(PROPERTY_LESSDEPB, lessdepb);
    }

    public BigDecimal getLessdeptbcal() {
        return (BigDecimal) get(PROPERTY_LESSDEPTBCAL);
    }

    public void setLessdeptbcal(BigDecimal lessdeptbcal) {
        set(PROPERTY_LESSDEPTBCAL, lessdeptbcal);
    }

    public BigDecimal getExmillpriceusdperyd() {
        return (BigDecimal) get(PROPERTY_EXMILLPRICEUSDPERYD);
    }

    public void setExmillpriceusdperyd(BigDecimal exmillpriceusdperyd) {
        set(PROPERTY_EXMILLPRICEUSDPERYD, exmillpriceusdperyd);
    }

    public BigDecimal getExmillpriceusdperydcal() {
        return (BigDecimal) get(PROPERTY_EXMILLPRICEUSDPERYDCAL);
    }

    public void setExmillpriceusdperydcal(BigDecimal exmillpriceusdperydcal) {
        set(PROPERTY_EXMILLPRICEUSDPERYDCAL, exmillpriceusdperydcal);
    }

    public BigDecimal getExportfreightusdperyd() {
        return (BigDecimal) get(PROPERTY_EXPORTFREIGHTUSDPERYD);
    }

    public void setExportfreightusdperyd(BigDecimal exportfreightusdperyd) {
        set(PROPERTY_EXPORTFREIGHTUSDPERYD, exportfreightusdperyd);
    }

    public DocumentType getDocumentType() {
        return (DocumentType) get(PROPERTY_DOCUMENTTYPE);
    }

    public void setDocumentType(DocumentType documentType) {
        set(PROPERTY_DOCUMENTTYPE, documentType);
    }

    public String getAlertStatus() {
        return (String) get(PROPERTY_ALERTSTATUS);
    }

    public void setAlertStatus(String alertStatus) {
        set(PROPERTY_ALERTSTATUS, alertStatus);
    }

    public EpcgVariant getEpcgVariant() {
        return (EpcgVariant) get(PROPERTY_EPCGVARIANT);
    }

    public void setEpcgVariant(EpcgVariant epcgVariant) {
        set(PROPERTY_EPCGVARIANT, epcgVariant);
    }

    public BigDecimal getGsmwith() {
        return (BigDecimal) get(PROPERTY_GSMWITH);
    }

    public void setGsmwith(BigDecimal gsmwith) {
        set(PROPERTY_GSMWITH, gsmwith);
    }

    public BigDecimal getGlmwith() {
        return (BigDecimal) get(PROPERTY_GLMWITH);
    }

    public void setGlmwith(BigDecimal glmwith) {
        set(PROPERTY_GLMWITH, glmwith);
    }

    public BigDecimal getGsmwithout() {
        return (BigDecimal) get(PROPERTY_GSMWITHOUT);
    }

    public void setGsmwithout(BigDecimal gsmwithout) {
        set(PROPERTY_GSMWITHOUT, gsmwithout);
    }

    public BigDecimal getProdDayLoomHun() {
        return (BigDecimal) get(PROPERTY_PRODDAYLOOMHUN);
    }

    public void setProdDayLoomHun(BigDecimal prodDayLoomHun) {
        set(PROPERTY_PRODDAYLOOMHUN, prodDayLoomHun);
    }

    public BigDecimal getProdDayLoomEff() {
        return (BigDecimal) get(PROPERTY_PRODDAYLOOMEFF);
    }

    public void setProdDayLoomEff(BigDecimal prodDayLoomEff) {
        set(PROPERTY_PRODDAYLOOMEFF, prodDayLoomEff);
    }

    public Product getProduct() {
        return (Product) get(PROPERTY_PRODUCT);
    }

    public void setProduct(Product product) {
        set(PROPERTY_PRODUCT, product);
    }

    public Product getWeftproduct() {
        return (Product) get(PROPERTY_WEFTPRODUCT);
    }

    public void setWeftproduct(Product weftproduct) {
        set(PROPERTY_WEFTPRODUCT, weftproduct);
    }

    public BigDecimal getFinalprice() {
        return (BigDecimal) get(PROPERTY_FINALPRICE);
    }

    public void setFinalprice(BigDecimal finalprice) {
        set(PROPERTY_FINALPRICE, finalprice);
    }

    public BigDecimal getInterestcostnew() {
        return (BigDecimal) get(PROPERTY_INTERESTCOSTNEW);
    }

    public void setInterestcostnew(BigDecimal interestcostnew) {
        set(PROPERTY_INTERESTCOSTNEW, interestcostnew);
    }

    public BigDecimal getPeryd() {
        return (BigDecimal) get(PROPERTY_PERYD);
    }

    public void setPeryd(BigDecimal peryd) {
        set(PROPERTY_PERYD, peryd);
    }

    public EpcgYarncosttemplatelines getEpcgYarncosttemplatelines() {
        return (EpcgYarncosttemplatelines) get(PROPERTY_EPCGYARNCOSTTEMPLATELINES);
    }

    public void setEpcgYarncosttemplatelines(EpcgYarncosttemplatelines epcgYarncosttemplatelines) {
        set(PROPERTY_EPCGYARNCOSTTEMPLATELINES, epcgYarncosttemplatelines);
    }

    public EpcgYarncosttemplatelines getEpcgYarncosttemplatelinesW() {
        return (EpcgYarncosttemplatelines) get(PROPERTY_EPCGYARNCOSTTEMPLATELINESW);
    }

    public void setEpcgYarncosttemplatelinesW(EpcgYarncosttemplatelines epcgYarncosttemplatelinesW) {
        set(PROPERTY_EPCGYARNCOSTTEMPLATELINESW, epcgYarncosttemplatelinesW);
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

    public Boolean isComplete() {
        return (Boolean) get(PROPERTY_COMPLETE);
    }

    public void setComplete(Boolean complete) {
        set(PROPERTY_COMPLETE, complete);
    }

    public Date getDeliverdate() {
        return (Date) get(PROPERTY_DELIVERDATE);
    }

    public void setDeliverdate(Date deliverdate) {
        set(PROPERTY_DELIVERDATE, deliverdate);
    }

    public PaymentTerm getPaymentTerms() {
        return (PaymentTerm) get(PROPERTY_PAYMENTTERMS);
    }

    public void setPaymentTerms(PaymentTerm paymentTerms) {
        set(PROPERTY_PAYMENTTERMS, paymentTerms);
    }

    public FIN_PaymentMethod getPaymentMethod() {
        return (FIN_PaymentMethod) get(PROPERTY_PAYMENTMETHOD);
    }

    public void setPaymentMethod(FIN_PaymentMethod paymentMethod) {
        set(PROPERTY_PAYMENTMETHOD, paymentMethod);
    }

    public BigDecimal getExmillpriceorlanded() {
        return (BigDecimal) get(PROPERTY_EXMILLPRICEORLANDED);
    }

    public void setExmillpriceorlanded(BigDecimal exmillpriceorlanded) {
        set(PROPERTY_EXMILLPRICEORLANDED, exmillpriceorlanded);
    }

    public BigDecimal getTransportationcost() {
        return (BigDecimal) get(PROPERTY_TRANSPORTATIONCOST);
    }

    public void setTransportationcost(BigDecimal transportationcost) {
        set(PROPERTY_TRANSPORTATIONCOST, transportationcost);
    }

    public RCHRInspweave getRchrInspweave() {
        return (RCHRInspweave) get(PROPERTY_RCHRINSPWEAVE);
    }

    public void setRchrInspweave(RCHRInspweave rchrInspweave) {
        set(PROPERTY_RCHRINSPWEAVE, rchrInspweave);
    }

    public BigDecimal getAdditionalcost() {
        return (BigDecimal) get(PROPERTY_ADDITIONALCOST);
    }

    public void setAdditionalcost(BigDecimal additionalcost) {
        set(PROPERTY_ADDITIONALCOST, additionalcost);
    }

    public String getDeliverytypecharges() {
        return (String) get(PROPERTY_DELIVERYTYPECHARGES);
    }

    public void setDeliverytypecharges(String deliverytypecharges) {
        set(PROPERTY_DELIVERYTYPECHARGES, deliverytypecharges);
    }

    public RchrEmployee getRchrEmployee() {
        return (RchrEmployee) get(PROPERTY_RCHREMPLOYEE);
    }

    public void setRchrEmployee(RchrEmployee rchrEmployee) {
        set(PROPERTY_RCHREMPLOYEE, rchrEmployee);
    }

    public RCHR_Orderstatus getRchrOrderstatus() {
        return (RCHR_Orderstatus) get(PROPERTY_RCHRORDERSTATUS);
    }

    public void setRchrOrderstatus(RCHR_Orderstatus rchrOrderstatus) {
        set(PROPERTY_RCHRORDERSTATUS, rchrOrderstatus);
    }

    public User getUserid() {
        return (User) get(PROPERTY_USERID);
    }

    public void setUserid(User userid) {
        set(PROPERTY_USERID, userid);
    }

    public RCHRQualitystandard getRchrQualitystandard() {
        return (RCHRQualitystandard) get(PROPERTY_RCHRQUALITYSTANDARD);
    }

    public void setRchrQualitystandard(RCHRQualitystandard rchrQualitystandard) {
        set(PROPERTY_RCHRQUALITYSTANDARD, rchrQualitystandard);
    }

    public EpcgInsurancetype getEpcgInsurancetype() {
        return (EpcgInsurancetype) get(PROPERTY_EPCGINSURANCETYPE);
    }

    public void setEpcgInsurancetype(EpcgInsurancetype epcgInsurancetype) {
        set(PROPERTY_EPCGINSURANCETYPE, epcgInsurancetype);
    }

    public Boolean isApproval() {
        return (Boolean) get(PROPERTY_APPROVAL);
    }

    public void setApproval(Boolean approval) {
        set(PROPERTY_APPROVAL, approval);
    }

    public String getOncontract() {
        return (String) get(PROPERTY_ONCONTRACT);
    }

    public void setOncontract(String oncontract) {
        set(PROPERTY_ONCONTRACT, oncontract);
    }

    public String getEpcgOrdertype() {
        return (String) get(PROPERTY_EPCGORDERTYPE);
    }

    public void setEpcgOrdertype(String epcgOrdertype) {
        set(PROPERTY_EPCGORDERTYPE, epcgOrdertype);
    }

    public BigDecimal getCashdiscount() {
        return (BigDecimal) get(PROPERTY_CASHDISCOUNT);
    }

    public void setCashdiscount(BigDecimal cashdiscount) {
        set(PROPERTY_CASHDISCOUNT, cashdiscount);
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

    public BigDecimal getActualspeed() {
        return (BigDecimal) get(PROPERTY_ACTUALSPEED);
    }

    public void setActualspeed(BigDecimal actualspeed) {
        set(PROPERTY_ACTUALSPEED, actualspeed);
    }

    public BigDecimal getActualefficiency() {
        return (BigDecimal) get(PROPERTY_ACTUALEFFICIENCY);
    }

    public void setActualefficiency(BigDecimal actualefficiency) {
        set(PROPERTY_ACTUALEFFICIENCY, actualefficiency);
    }

    public UOM getUOM() {
        return (UOM) get(PROPERTY_UOM);
    }

    public void setUOM(UOM uOM) {
        set(PROPERTY_UOM, uOM);
    }

    public PriceList getPriceList() {
        return (PriceList) get(PROPERTY_PRICELIST);
    }

    public void setPriceList(PriceList priceList) {
        set(PROPERTY_PRICELIST, priceList);
    }

    public Currency getCurrency() {
        return (Currency) get(PROPERTY_CURRENCY);
    }

    public void setCurrency(Currency currency) {
        set(PROPERTY_CURRENCY, currency);
    }

    public String getInvoiceTerms() {
        return (String) get(PROPERTY_INVOICETERMS);
    }

    public void setInvoiceTerms(String invoiceTerms) {
        set(PROPERTY_INVOICETERMS, invoiceTerms);
    }

    public String getEpcgRatetype() {
        return (String) get(PROPERTY_EPCGRATETYPE);
    }

    public void setEpcgRatetype(String epcgRatetype) {
        set(PROPERTY_EPCGRATETYPE, epcgRatetype);
    }

    public EpcgDelivermode getEpcgDelivermode() {
        return (EpcgDelivermode) get(PROPERTY_EPCGDELIVERMODE);
    }

    public void setEpcgDelivermode(EpcgDelivermode epcgDelivermode) {
        set(PROPERTY_EPCGDELIVERMODE, epcgDelivermode);
    }

    public TaxRate getTax() {
        return (TaxRate) get(PROPERTY_TAX);
    }

    public void setTax(TaxRate tax) {
        set(PROPERTY_TAX, tax);
    }

    public BigDecimal getInsurance() {
        return (BigDecimal) get(PROPERTY_INSURANCE);
    }

    public void setInsurance(BigDecimal insurance) {
        set(PROPERTY_INSURANCE, insurance);
    }

    public String getWarpcrimpvalues() {
        return (String) get(PROPERTY_WARPCRIMPVALUES);
    }

    public void setWarpcrimpvalues(String warpcrimpvalues) {
        set(PROPERTY_WARPCRIMPVALUES, warpcrimpvalues);
    }

    public BigDecimal getWarpcrimp() {
        return (BigDecimal) get(PROPERTY_WARPCRIMP);
    }

    public void setWarpcrimp(BigDecimal warpcrimp) {
        set(PROPERTY_WARPCRIMP, warpcrimp);
    }

    public BigDecimal getLossoffabricpercent() {
        return (BigDecimal) get(PROPERTY_LOSSOFFABRICPERCENT);
    }

    public void setLossoffabricpercent(BigDecimal lossoffabricpercent) {
        set(PROPERTY_LOSSOFFABRICPERCENT, lossoffabricpercent);
    }

    public BigDecimal getFabricloss() {
        return (BigDecimal) get(PROPERTY_FABRICLOSS);
    }

    public void setFabricloss(BigDecimal fabricloss) {
        set(PROPERTY_FABRICLOSS, fabricloss);
    }

    public BigDecimal getStock() {
        return (BigDecimal) get(PROPERTY_STOCK);
    }

    public void setStock(BigDecimal stock) {
        set(PROPERTY_STOCK, stock);
    }

    public BigDecimal getFabricwidth() {
        return (BigDecimal) get(PROPERTY_FABRICWIDTH);
    }

    public void setFabricwidth(BigDecimal fabricwidth) {
        set(PROPERTY_FABRICWIDTH, fabricwidth);
    }

    public BigDecimal getOnesideselvedgewidth() {
        return (BigDecimal) get(PROPERTY_ONESIDESELVEDGEWIDTH);
    }

    public void setOnesideselvedgewidth(BigDecimal onesideselvedgewidth) {
        set(PROPERTY_ONESIDESELVEDGEWIDTH, onesideselvedgewidth);
    }

    public Location getPartnerAddress() {
        return (Location) get(PROPERTY_PARTNERADDRESS);
    }

    public void setPartnerAddress(Location partnerAddress) {
        set(PROPERTY_PARTNERADDRESS, partnerAddress);
    }

    public String getPoreferenceno() {
        return (String) get(PROPERTY_POREFERENCENO);
    }

    public void setPoreferenceno(String poreferenceno) {
        set(PROPERTY_POREFERENCENO, poreferenceno);
    }

    public EpcgVariant getWeftvariant() {
        return (EpcgVariant) get(PROPERTY_WEFTVARIANT);
    }

    public void setWeftvariant(EpcgVariant weftvariant) {
        set(PROPERTY_WEFTVARIANT, weftvariant);
    }

    public String getReed() {
        return (String) get(PROPERTY_REED);
    }

    public void setReed(String reed) {
        set(PROPERTY_REED, reed);
    }

    public BigDecimal getOnloomepi() {
        return (BigDecimal) get(PROPERTY_ONLOOMEPI);
    }

    public void setOnloomepi(BigDecimal onloomepi) {
        set(PROPERTY_ONLOOMEPI, onloomepi);
    }

    public String getTypeofsort() {
        return (String) get(PROPERTY_TYPEOFSORT);
    }

    public void setTypeofsort(String typeofsort) {
        set(PROPERTY_TYPEOFSORT, typeofsort);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgMof> getEpcgMofList() {
        return (List<EpcgMof>) get(PROPERTY_EPCGMOFLIST);
    }

    public void setEpcgMofList(List<EpcgMof> epcgMofList) {
        set(PROPERTY_EPCGMOFLIST, epcgMofList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgWarpcosting> getEpcgWarpcostingList() {
        return (List<EpcgWarpcosting>) get(PROPERTY_EPCGWARPCOSTINGLIST);
    }

    public void setEpcgWarpcostingList(List<EpcgWarpcosting> epcgWarpcostingList) {
        set(PROPERTY_EPCGWARPCOSTINGLIST, epcgWarpcostingList);
    }

    @SuppressWarnings("unchecked")
    public List<EpcgWeftcosting> getEpcgWeftcostingList() {
        return (List<EpcgWeftcosting>) get(PROPERTY_EPCGWEFTCOSTINGLIST);
    }

    public void setEpcgWeftcostingList(List<EpcgWeftcosting> epcgWeftcostingList) {
        set(PROPERTY_EPCGWEFTCOSTINGLIST, epcgWeftcostingList);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrderList() {
        return (List<Order>) get(PROPERTY_ORDERLIST);
    }

    public void setOrderList(List<Order> orderList) {
        set(PROPERTY_ORDERLIST, orderList);
    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> getOrderLineList() {
        return (List<OrderLine>) get(PROPERTY_ORDERLINELIST);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        set(PROPERTY_ORDERLINELIST, orderLineList);
    }

    @SuppressWarnings("unchecked")
    public List<epcg_ppcenquiry> getEpcgPpcenquiryList() {
        return (List<epcg_ppcenquiry>) get(PROPERTY_EPCGPPCENQUIRYLIST);
    }

    public void setEpcgPpcenquiryList(List<epcg_ppcenquiry> epcgPpcenquiryList) {
        set(PROPERTY_EPCGPPCENQUIRYLIST, epcgPpcenquiryList);
    }

}
