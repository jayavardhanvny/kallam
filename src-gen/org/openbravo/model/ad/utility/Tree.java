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
package org.openbravo.model.ad.utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.Role;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.system.ClientInformation;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.accounting.coa.Element;
/**
 * Entity class for entity ADTree (stored in table AD_Tree).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Tree extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Tree";
    public static final String ENTITY_NAME = "ADTree";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_TYPEAREA = "typeArea";
    public static final String PROPERTY_ALLNODES = "allNodes";
    public static final String PROPERTY_ADROLEPRIMARYTREEMENULIST = "aDRolePrimaryTreeMenuList";
    public static final String PROPERTY_ADTREENODELIST = "aDTreeNodeList";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYTREEMENULIST = "clientInformationPrimaryTreeMenuList";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYTREEORGANIZATIONLIST = "clientInformationPrimaryTreeOrganizationList";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYTREEBPARTNERLIST = "clientInformationPrimaryTreeBPartnerList";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYTREEPROJECTLIST = "clientInformationPrimaryTreeProjectList";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYTREESALESREGIONLIST = "clientInformationPrimaryTreeSalesRegionList";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYTREEPRODUCTLIST = "clientInformationPrimaryTreeProductList";
    public static final String PROPERTY_CLIENTINFORMATIONADTREECAMPAIGNIDLIST = "clientInformationADTreeCampaignIDList";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYTREEASSETLIST = "clientInformationPrimaryTreeAssetList";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYTREEPRODUCTCATEGORYLIST = "clientInformationPrimaryTreeProductCategoryList";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYTREECOSTCENTERLIST = "clientInformationPrimaryTreeCostCenterList";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYTREERESOURCECATEGORYLIST = "clientInformationPrimaryTreeResourceCategoryList";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYUSERDIMENSION1LIST = "clientInformationPrimaryUserDimension1List";
    public static final String PROPERTY_CLIENTINFORMATIONPRIMARYUSERDIMENSION2LIST = "clientInformationPrimaryUserDimension2List";
    public static final String PROPERTY_FINANCIALMGMTELEMENTLIST = "financialMgmtElementList";
    public static final String PROPERTY_FINANCIALMGMTELEMENTADDITIONALTREE2LIST = "financialMgmtElementAdditionalTree2List";
    public static final String PROPERTY_FINANCIALMGMTELEMENTADDITIONALTREE1LIST = "financialMgmtElementAdditionalTree1List";

    public Tree() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ALLNODES, false);
        setDefaultValue(PROPERTY_ADROLEPRIMARYTREEMENULIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTREENODELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYTREEMENULIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYTREEORGANIZATIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYTREEBPARTNERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYTREEPROJECTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYTREESALESREGIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYTREEPRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONADTREECAMPAIGNIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYTREEASSETLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYTREEPRODUCTCATEGORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYTREECOSTCENTERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYTREERESOURCECATEGORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYUSERDIMENSION1LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONPRIMARYUSERDIMENSION2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTELEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTELEMENTADDITIONALTREE2LIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTELEMENTADDITIONALTREE1LIST, new ArrayList<Object>());
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

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getTypeArea() {
        return (String) get(PROPERTY_TYPEAREA);
    }

    public void setTypeArea(String typeArea) {
        set(PROPERTY_TYPEAREA, typeArea);
    }

    public Boolean isAllNodes() {
        return (Boolean) get(PROPERTY_ALLNODES);
    }

    public void setAllNodes(Boolean allNodes) {
        set(PROPERTY_ALLNODES, allNodes);
    }

    @SuppressWarnings("unchecked")
    public List<Role> getADRolePrimaryTreeMenuList() {
        return (List<Role>) get(PROPERTY_ADROLEPRIMARYTREEMENULIST);
    }

    public void setADRolePrimaryTreeMenuList(List<Role> aDRolePrimaryTreeMenuList) {
        set(PROPERTY_ADROLEPRIMARYTREEMENULIST, aDRolePrimaryTreeMenuList);
    }

    @SuppressWarnings("unchecked")
    public List<TreeNode> getADTreeNodeList() {
        return (List<TreeNode>) get(PROPERTY_ADTREENODELIST);
    }

    public void setADTreeNodeList(List<TreeNode> aDTreeNodeList) {
        set(PROPERTY_ADTREENODELIST, aDTreeNodeList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryTreeMenuList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYTREEMENULIST);
    }

    public void setClientInformationPrimaryTreeMenuList(List<ClientInformation> clientInformationPrimaryTreeMenuList) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYTREEMENULIST, clientInformationPrimaryTreeMenuList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryTreeOrganizationList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYTREEORGANIZATIONLIST);
    }

    public void setClientInformationPrimaryTreeOrganizationList(List<ClientInformation> clientInformationPrimaryTreeOrganizationList) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYTREEORGANIZATIONLIST, clientInformationPrimaryTreeOrganizationList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryTreeBPartnerList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYTREEBPARTNERLIST);
    }

    public void setClientInformationPrimaryTreeBPartnerList(List<ClientInformation> clientInformationPrimaryTreeBPartnerList) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYTREEBPARTNERLIST, clientInformationPrimaryTreeBPartnerList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryTreeProjectList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYTREEPROJECTLIST);
    }

    public void setClientInformationPrimaryTreeProjectList(List<ClientInformation> clientInformationPrimaryTreeProjectList) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYTREEPROJECTLIST, clientInformationPrimaryTreeProjectList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryTreeSalesRegionList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYTREESALESREGIONLIST);
    }

    public void setClientInformationPrimaryTreeSalesRegionList(List<ClientInformation> clientInformationPrimaryTreeSalesRegionList) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYTREESALESREGIONLIST, clientInformationPrimaryTreeSalesRegionList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryTreeProductList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYTREEPRODUCTLIST);
    }

    public void setClientInformationPrimaryTreeProductList(List<ClientInformation> clientInformationPrimaryTreeProductList) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYTREEPRODUCTLIST, clientInformationPrimaryTreeProductList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationADTreeCampaignIDList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONADTREECAMPAIGNIDLIST);
    }

    public void setClientInformationADTreeCampaignIDList(List<ClientInformation> clientInformationADTreeCampaignIDList) {
        set(PROPERTY_CLIENTINFORMATIONADTREECAMPAIGNIDLIST, clientInformationADTreeCampaignIDList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryTreeAssetList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYTREEASSETLIST);
    }

    public void setClientInformationPrimaryTreeAssetList(List<ClientInformation> clientInformationPrimaryTreeAssetList) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYTREEASSETLIST, clientInformationPrimaryTreeAssetList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryTreeProductCategoryList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYTREEPRODUCTCATEGORYLIST);
    }

    public void setClientInformationPrimaryTreeProductCategoryList(List<ClientInformation> clientInformationPrimaryTreeProductCategoryList) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYTREEPRODUCTCATEGORYLIST, clientInformationPrimaryTreeProductCategoryList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryTreeCostCenterList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYTREECOSTCENTERLIST);
    }

    public void setClientInformationPrimaryTreeCostCenterList(List<ClientInformation> clientInformationPrimaryTreeCostCenterList) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYTREECOSTCENTERLIST, clientInformationPrimaryTreeCostCenterList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryTreeResourceCategoryList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYTREERESOURCECATEGORYLIST);
    }

    public void setClientInformationPrimaryTreeResourceCategoryList(List<ClientInformation> clientInformationPrimaryTreeResourceCategoryList) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYTREERESOURCECATEGORYLIST, clientInformationPrimaryTreeResourceCategoryList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryUserDimension1List() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYUSERDIMENSION1LIST);
    }

    public void setClientInformationPrimaryUserDimension1List(List<ClientInformation> clientInformationPrimaryUserDimension1List) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYUSERDIMENSION1LIST, clientInformationPrimaryUserDimension1List);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationPrimaryUserDimension2List() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONPRIMARYUSERDIMENSION2LIST);
    }

    public void setClientInformationPrimaryUserDimension2List(List<ClientInformation> clientInformationPrimaryUserDimension2List) {
        set(PROPERTY_CLIENTINFORMATIONPRIMARYUSERDIMENSION2LIST, clientInformationPrimaryUserDimension2List);
    }

    @SuppressWarnings("unchecked")
    public List<Element> getFinancialMgmtElementList() {
        return (List<Element>) get(PROPERTY_FINANCIALMGMTELEMENTLIST);
    }

    public void setFinancialMgmtElementList(List<Element> financialMgmtElementList) {
        set(PROPERTY_FINANCIALMGMTELEMENTLIST, financialMgmtElementList);
    }

    @SuppressWarnings("unchecked")
    public List<Element> getFinancialMgmtElementAdditionalTree2List() {
        return (List<Element>) get(PROPERTY_FINANCIALMGMTELEMENTADDITIONALTREE2LIST);
    }

    public void setFinancialMgmtElementAdditionalTree2List(List<Element> financialMgmtElementAdditionalTree2List) {
        set(PROPERTY_FINANCIALMGMTELEMENTADDITIONALTREE2LIST, financialMgmtElementAdditionalTree2List);
    }

    @SuppressWarnings("unchecked")
    public List<Element> getFinancialMgmtElementAdditionalTree1List() {
        return (List<Element>) get(PROPERTY_FINANCIALMGMTELEMENTADDITIONALTREE1LIST);
    }

    public void setFinancialMgmtElementAdditionalTree1List(List<Element> financialMgmtElementAdditionalTree1List) {
        set(PROPERTY_FINANCIALMGMTELEMENTADDITIONALTREE1LIST, financialMgmtElementAdditionalTree1List);
    }

}
