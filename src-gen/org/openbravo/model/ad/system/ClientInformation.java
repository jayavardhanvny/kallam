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
package org.openbravo.model.ad.system;

import java.util.Date;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.utility.Image;
import org.openbravo.model.ad.utility.Tree;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.accounting.coa.AcctSchema;
import org.openbravo.model.financialmgmt.calendar.Calendar;
import org.openbravo.model.pricing.pricelist.PriceList;
/**
 * Entity class for entity ClientInformation (stored in table AD_ClientInfo).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ClientInformation extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_ClientInfo";
    public static final String ENTITY_NAME = "ClientInformation";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_DISCOUNTCALCULATEDFROMLINEAMOUNTS = "discountCalculatedFromLineAmounts";
    public static final String PROPERTY_CALENDAR = "calendar";
    public static final String PROPERTY_PRIMARYACCOUNTINGSCHEMA = "primaryAccountingSchema";
    public static final String PROPERTY_ENABLESECONDACCOUNTINGSCHEMA = "enableSecondAccountingSchema";
    public static final String PROPERTY_SECONDACCOUNTINGSCHEMA = "secondAccountingSchema";
    public static final String PROPERTY_ENABLETHIRDACCOUNTINGSCHEMA = "enableThirdAccountingSchema";
    public static final String PROPERTY_THIRDACCOUNTINGSCHEMA = "thirdAccountingSchema";
    public static final String PROPERTY_UOMFORVOLUME = "uOMForVolume";
    public static final String PROPERTY_UOMFORWEIGHT = "uOMForWeight";
    public static final String PROPERTY_UOMFORLENGTH = "uOMForLength";
    public static final String PROPERTY_UOMFORTIME = "uOMForTime";
    public static final String PROPERTY_PRIMARYTREEMENU = "primaryTreeMenu";
    public static final String PROPERTY_PRIMARYTREEORGANIZATION = "primaryTreeOrganization";
    public static final String PROPERTY_PRIMARYTREEBPARTNER = "primaryTreeBPartner";
    public static final String PROPERTY_PRIMARYTREEPROJECT = "primaryTreeProject";
    public static final String PROPERTY_PRIMARYTREESALESREGION = "primaryTreeSalesRegion";
    public static final String PROPERTY_PRIMARYTREEPRODUCT = "primaryTreeProduct";
    public static final String PROPERTY_PRODUCTFORFREIGHT = "productForFreight";
    public static final String PROPERTY_TEMPLATEBPARTNER = "templateBPartner";
    public static final String PROPERTY_PRICELIST = "priceList";
    public static final String PROPERTY_ALLOWNEGATIVESTOCK = "allowNegativeStock";
    public static final String PROPERTY_CHECKORDERORGANIZATION = "checkOrderOrganization";
    public static final String PROPERTY_CHECKSHIPMENTORGANIZATION = "checkShipmentOrganization";
    public static final String PROPERTY_GROUPINVOICELINESINACCOUNTING = "groupInvoiceLinesInAccounting";
    public static final String PROPERTY_YOURCOMPANYMENUIMAGE = "yourCompanyMenuImage";
    public static final String PROPERTY_YOURCOMPANYBIGIMAGE = "yourCompanyBigImage";
    public static final String PROPERTY_YOURCOMPANYDOCUMENTIMAGE = "yourCompanyDocumentImage";
    public static final String PROPERTY_TREECAMPAIGN = "treeCampaign";
    public static final String PROPERTY_PRIMARYTREEASSET = "primaryTreeAsset";
    public static final String PROPERTY_PRIMARYTREEPRODUCTCATEGORY = "primaryTreeProductCategory";
    public static final String PROPERTY_PRIMARYTREECOSTCENTER = "primaryTreeCostCenter";
    public static final String PROPERTY_PRIMARYTREERESOURCECATEGORY = "primaryTreeResourceCategory";
    public static final String PROPERTY_PRIMARYUSERDIMENSION1 = "primaryUserDimension1";
    public static final String PROPERTY_PRIMARYUSERDIMENSION2 = "primaryUserDimension2";
    public static final String PROPERTY_CLIENT = "client";

    public ClientInformation() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_DISCOUNTCALCULATEDFROMLINEAMOUNTS, false);
        setDefaultValue(PROPERTY_ENABLESECONDACCOUNTINGSCHEMA, false);
        setDefaultValue(PROPERTY_ENABLETHIRDACCOUNTINGSCHEMA, false);
        setDefaultValue(PROPERTY_ALLOWNEGATIVESTOCK, false);
        setDefaultValue(PROPERTY_CHECKORDERORGANIZATION, true);
        setDefaultValue(PROPERTY_CHECKSHIPMENTORGANIZATION, true);
        setDefaultValue(PROPERTY_GROUPINVOICELINESINACCOUNTING, false);
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

    public Boolean isDiscountCalculatedFromLineAmounts() {
        return (Boolean) get(PROPERTY_DISCOUNTCALCULATEDFROMLINEAMOUNTS);
    }

    public void setDiscountCalculatedFromLineAmounts(Boolean discountCalculatedFromLineAmounts) {
        set(PROPERTY_DISCOUNTCALCULATEDFROMLINEAMOUNTS, discountCalculatedFromLineAmounts);
    }

    public Calendar getCalendar() {
        return (Calendar) get(PROPERTY_CALENDAR);
    }

    public void setCalendar(Calendar calendar) {
        set(PROPERTY_CALENDAR, calendar);
    }

    public AcctSchema getPrimaryAccountingSchema() {
        return (AcctSchema) get(PROPERTY_PRIMARYACCOUNTINGSCHEMA);
    }

    public void setPrimaryAccountingSchema(AcctSchema primaryAccountingSchema) {
        set(PROPERTY_PRIMARYACCOUNTINGSCHEMA, primaryAccountingSchema);
    }

    public Boolean isEnableSecondAccountingSchema() {
        return (Boolean) get(PROPERTY_ENABLESECONDACCOUNTINGSCHEMA);
    }

    public void setEnableSecondAccountingSchema(Boolean enableSecondAccountingSchema) {
        set(PROPERTY_ENABLESECONDACCOUNTINGSCHEMA, enableSecondAccountingSchema);
    }

    public AcctSchema getSecondAccountingSchema() {
        return (AcctSchema) get(PROPERTY_SECONDACCOUNTINGSCHEMA);
    }

    public void setSecondAccountingSchema(AcctSchema secondAccountingSchema) {
        set(PROPERTY_SECONDACCOUNTINGSCHEMA, secondAccountingSchema);
    }

    public Boolean isEnableThirdAccountingSchema() {
        return (Boolean) get(PROPERTY_ENABLETHIRDACCOUNTINGSCHEMA);
    }

    public void setEnableThirdAccountingSchema(Boolean enableThirdAccountingSchema) {
        set(PROPERTY_ENABLETHIRDACCOUNTINGSCHEMA, enableThirdAccountingSchema);
    }

    public AcctSchema getThirdAccountingSchema() {
        return (AcctSchema) get(PROPERTY_THIRDACCOUNTINGSCHEMA);
    }

    public void setThirdAccountingSchema(AcctSchema thirdAccountingSchema) {
        set(PROPERTY_THIRDACCOUNTINGSCHEMA, thirdAccountingSchema);
    }

    public UOM getUOMForVolume() {
        return (UOM) get(PROPERTY_UOMFORVOLUME);
    }

    public void setUOMForVolume(UOM uOMForVolume) {
        set(PROPERTY_UOMFORVOLUME, uOMForVolume);
    }

    public UOM getUOMForWeight() {
        return (UOM) get(PROPERTY_UOMFORWEIGHT);
    }

    public void setUOMForWeight(UOM uOMForWeight) {
        set(PROPERTY_UOMFORWEIGHT, uOMForWeight);
    }

    public UOM getUOMForLength() {
        return (UOM) get(PROPERTY_UOMFORLENGTH);
    }

    public void setUOMForLength(UOM uOMForLength) {
        set(PROPERTY_UOMFORLENGTH, uOMForLength);
    }

    public UOM getUOMForTime() {
        return (UOM) get(PROPERTY_UOMFORTIME);
    }

    public void setUOMForTime(UOM uOMForTime) {
        set(PROPERTY_UOMFORTIME, uOMForTime);
    }

    public Tree getPrimaryTreeMenu() {
        return (Tree) get(PROPERTY_PRIMARYTREEMENU);
    }

    public void setPrimaryTreeMenu(Tree primaryTreeMenu) {
        set(PROPERTY_PRIMARYTREEMENU, primaryTreeMenu);
    }

    public Tree getPrimaryTreeOrganization() {
        return (Tree) get(PROPERTY_PRIMARYTREEORGANIZATION);
    }

    public void setPrimaryTreeOrganization(Tree primaryTreeOrganization) {
        set(PROPERTY_PRIMARYTREEORGANIZATION, primaryTreeOrganization);
    }

    public Tree getPrimaryTreeBPartner() {
        return (Tree) get(PROPERTY_PRIMARYTREEBPARTNER);
    }

    public void setPrimaryTreeBPartner(Tree primaryTreeBPartner) {
        set(PROPERTY_PRIMARYTREEBPARTNER, primaryTreeBPartner);
    }

    public Tree getPrimaryTreeProject() {
        return (Tree) get(PROPERTY_PRIMARYTREEPROJECT);
    }

    public void setPrimaryTreeProject(Tree primaryTreeProject) {
        set(PROPERTY_PRIMARYTREEPROJECT, primaryTreeProject);
    }

    public Tree getPrimaryTreeSalesRegion() {
        return (Tree) get(PROPERTY_PRIMARYTREESALESREGION);
    }

    public void setPrimaryTreeSalesRegion(Tree primaryTreeSalesRegion) {
        set(PROPERTY_PRIMARYTREESALESREGION, primaryTreeSalesRegion);
    }

    public Tree getPrimaryTreeProduct() {
        return (Tree) get(PROPERTY_PRIMARYTREEPRODUCT);
    }

    public void setPrimaryTreeProduct(Tree primaryTreeProduct) {
        set(PROPERTY_PRIMARYTREEPRODUCT, primaryTreeProduct);
    }

    public Product getProductForFreight() {
        return (Product) get(PROPERTY_PRODUCTFORFREIGHT);
    }

    public void setProductForFreight(Product productForFreight) {
        set(PROPERTY_PRODUCTFORFREIGHT, productForFreight);
    }

    public BusinessPartner getTemplateBPartner() {
        return (BusinessPartner) get(PROPERTY_TEMPLATEBPARTNER);
    }

    public void setTemplateBPartner(BusinessPartner templateBPartner) {
        set(PROPERTY_TEMPLATEBPARTNER, templateBPartner);
    }

    public PriceList getPriceList() {
        return (PriceList) get(PROPERTY_PRICELIST);
    }

    public void setPriceList(PriceList priceList) {
        set(PROPERTY_PRICELIST, priceList);
    }

    public Boolean isAllowNegativeStock() {
        return (Boolean) get(PROPERTY_ALLOWNEGATIVESTOCK);
    }

    public void setAllowNegativeStock(Boolean allowNegativeStock) {
        set(PROPERTY_ALLOWNEGATIVESTOCK, allowNegativeStock);
    }

    public Boolean isCheckOrderOrganization() {
        return (Boolean) get(PROPERTY_CHECKORDERORGANIZATION);
    }

    public void setCheckOrderOrganization(Boolean checkOrderOrganization) {
        set(PROPERTY_CHECKORDERORGANIZATION, checkOrderOrganization);
    }

    public Boolean isCheckShipmentOrganization() {
        return (Boolean) get(PROPERTY_CHECKSHIPMENTORGANIZATION);
    }

    public void setCheckShipmentOrganization(Boolean checkShipmentOrganization) {
        set(PROPERTY_CHECKSHIPMENTORGANIZATION, checkShipmentOrganization);
    }

    public Boolean isGroupInvoiceLinesInAccounting() {
        return (Boolean) get(PROPERTY_GROUPINVOICELINESINACCOUNTING);
    }

    public void setGroupInvoiceLinesInAccounting(Boolean groupInvoiceLinesInAccounting) {
        set(PROPERTY_GROUPINVOICELINESINACCOUNTING, groupInvoiceLinesInAccounting);
    }

    public Image getYourCompanyMenuImage() {
        return (Image) get(PROPERTY_YOURCOMPANYMENUIMAGE);
    }

    public void setYourCompanyMenuImage(Image yourCompanyMenuImage) {
        set(PROPERTY_YOURCOMPANYMENUIMAGE, yourCompanyMenuImage);
    }

    public Image getYourCompanyBigImage() {
        return (Image) get(PROPERTY_YOURCOMPANYBIGIMAGE);
    }

    public void setYourCompanyBigImage(Image yourCompanyBigImage) {
        set(PROPERTY_YOURCOMPANYBIGIMAGE, yourCompanyBigImage);
    }

    public Image getYourCompanyDocumentImage() {
        return (Image) get(PROPERTY_YOURCOMPANYDOCUMENTIMAGE);
    }

    public void setYourCompanyDocumentImage(Image yourCompanyDocumentImage) {
        set(PROPERTY_YOURCOMPANYDOCUMENTIMAGE, yourCompanyDocumentImage);
    }

    public Tree getTreeCampaign() {
        return (Tree) get(PROPERTY_TREECAMPAIGN);
    }

    public void setTreeCampaign(Tree treeCampaign) {
        set(PROPERTY_TREECAMPAIGN, treeCampaign);
    }

    public Tree getPrimaryTreeAsset() {
        return (Tree) get(PROPERTY_PRIMARYTREEASSET);
    }

    public void setPrimaryTreeAsset(Tree primaryTreeAsset) {
        set(PROPERTY_PRIMARYTREEASSET, primaryTreeAsset);
    }

    public Tree getPrimaryTreeProductCategory() {
        return (Tree) get(PROPERTY_PRIMARYTREEPRODUCTCATEGORY);
    }

    public void setPrimaryTreeProductCategory(Tree primaryTreeProductCategory) {
        set(PROPERTY_PRIMARYTREEPRODUCTCATEGORY, primaryTreeProductCategory);
    }

    public Tree getPrimaryTreeCostCenter() {
        return (Tree) get(PROPERTY_PRIMARYTREECOSTCENTER);
    }

    public void setPrimaryTreeCostCenter(Tree primaryTreeCostCenter) {
        set(PROPERTY_PRIMARYTREECOSTCENTER, primaryTreeCostCenter);
    }

    public Tree getPrimaryTreeResourceCategory() {
        return (Tree) get(PROPERTY_PRIMARYTREERESOURCECATEGORY);
    }

    public void setPrimaryTreeResourceCategory(Tree primaryTreeResourceCategory) {
        set(PROPERTY_PRIMARYTREERESOURCECATEGORY, primaryTreeResourceCategory);
    }

    public Tree getPrimaryUserDimension1() {
        return (Tree) get(PROPERTY_PRIMARYUSERDIMENSION1);
    }

    public void setPrimaryUserDimension1(Tree primaryUserDimension1) {
        set(PROPERTY_PRIMARYUSERDIMENSION1, primaryUserDimension1);
    }

    public Tree getPrimaryUserDimension2() {
        return (Tree) get(PROPERTY_PRIMARYUSERDIMENSION2);
    }

    public void setPrimaryUserDimension2(Tree primaryUserDimension2) {
        set(PROPERTY_PRIMARYUSERDIMENSION2, primaryUserDimension2);
    }

    public Client getClient() {
        return (Client) get(PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
        set(PROPERTY_CLIENT, client);
    }

}
