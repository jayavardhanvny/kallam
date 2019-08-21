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

import com.rcss.humanresource.data.RCHRInspweave;
import com.rcss.humanresource.data.RCHRVisitorgatepass;
import com.rcss.humanresource.data.RCHR_Emp_Loan;
import com.rcss.humanresource.data.RCHR_OverTime;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrJoinRejoiningform;
import com.redcarpet.goodsissue.data.RCGIDepartmentIssue;
import com.redcarpet.goodsissue.data.RCGIDiFamilyList;
import com.redcarpet.payroll.data.RCPLPayrollpaidprocline;

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
import org.openbravo.model.ad.system.ClientInformation;
import org.openbravo.model.ad.system.SystemInformation;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.ad.ui.Window;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.enterprise.OrganizationInformation;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.plm.ProductCategory;
/**
 * Entity class for entity ADImage (stored in table AD_Image).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Image extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Image";
    public static final String ENTITY_NAME = "ADImage";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_IMAGEURL = "imageURL";
    public static final String PROPERTY_BINDARYDATA = "bindaryData";
    public static final String PROPERTY_WIDTH = "width";
    public static final String PROPERTY_HEIGHT = "height";
    public static final String PROPERTY_MIMETYPE = "mimetype";
    public static final String PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYLOGINIMAGELIST = "aDSystemInformationYourCompanyLoginImageList";
    public static final String PROPERTY_ADSYSTEMINFORMATIONYOURITSERVICELOGINIMAGELIST = "aDSystemInformationYourItServiceLoginImageList";
    public static final String PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYMENUIMAGELIST = "aDSystemInformationYourCompanyMenuImageList";
    public static final String PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYBIGIMAGELIST = "aDSystemInformationYourCompanyBigImageList";
    public static final String PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYDOCUMENTIMAGELIST = "aDSystemInformationYourCompanyDocumentImageList";
    public static final String PROPERTY_ADSYSTEMINFORMATIONPRODUCTIONBANNERIMAGEIDLIST = "aDSystemInformationProductionBannerImageIDList";
    public static final String PROPERTY_ADTABLIST = "aDTabList";
    public static final String PROPERTY_ADUSERLIST = "aDUserList";
    public static final String PROPERTY_ADWINDOWLIST = "aDWindowList";
    public static final String PROPERTY_CLIENTINFORMATIONYOURCOMPANYMENUIMAGELIST = "clientInformationYourCompanyMenuImageList";
    public static final String PROPERTY_CLIENTINFORMATIONYOURCOMPANYBIGIMAGELIST = "clientInformationYourCompanyBigImageList";
    public static final String PROPERTY_CLIENTINFORMATIONYOURCOMPANYDOCUMENTIMAGELIST = "clientInformationYourCompanyDocumentImageList";
    public static final String PROPERTY_ORGANIZATIONINFORMATIONYOURCOMPANYDOCUMENTIMAGELIST = "organizationInformationYourCompanyDocumentImageList";
    public static final String PROPERTY_PRODUCTLIST = "productList";
    public static final String PROPERTY_PRODUCTCATEGORYLIST = "productCategoryList";
    public static final String PROPERTY_RCGIDEPARTMENTISSUELIST = "rCGIDepartmentIssueList";
    public static final String PROPERTY_RCGIDIFAMILYLISTLIST = "rCGIDiFamilyListList";
    public static final String PROPERTY_RCHREMPLOANLIST = "rCHREmpLoanList";
    public static final String PROPERTY_RCHRINSPWEAVELIST = "rCHRInspweaveList";
    public static final String PROPERTY_RCHROVERTIMELIST = "rCHROverTimeList";
    public static final String PROPERTY_RCHRVISITORGATEPASSLIST = "rCHRVisitorgatepassList";
    public static final String PROPERTY_RCPLPAYROLLPAIDPROCLINELIST = "rCPLPayrollpaidproclineList";
    public static final String PROPERTY_RCHREMPLOYEELIST = "rchrEmployeeList";
    public static final String PROPERTY_RCHRJOINREJOININGFORMLIST = "rchrJoinRejoiningformList";

    public Image() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYLOGINIMAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADSYSTEMINFORMATIONYOURITSERVICELOGINIMAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYMENUIMAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYBIGIMAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYDOCUMENTIMAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADSYSTEMINFORMATIONPRODUCTIONBANNERIMAGEIDLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADUSERLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADWINDOWLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONYOURCOMPANYMENUIMAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONYOURCOMPANYBIGIMAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CLIENTINFORMATIONYOURCOMPANYDOCUMENTIMAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ORGANIZATIONINFORMATIONYOURCOMPANYDOCUMENTIMAGELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTCATEGORYLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDEPARTMENTISSUELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCGIDIFAMILYLISTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOANLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRINSPWEAVELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHROVERTIMELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRVISITORGATEPASSLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCPLPAYROLLPAIDPROCLINELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHREMPLOYEELIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_RCHRJOINREJOININGFORMLIST, new ArrayList<Object>());
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

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getImageURL() {
        return (String) get(PROPERTY_IMAGEURL);
    }

    public void setImageURL(String imageURL) {
        set(PROPERTY_IMAGEURL, imageURL);
    }

    public byte[] getBindaryData() {
        return (byte[]) get(PROPERTY_BINDARYDATA);
    }

    public void setBindaryData(byte[] bindaryData) {
        set(PROPERTY_BINDARYDATA, bindaryData);
    }

    public Long getWidth() {
        return (Long) get(PROPERTY_WIDTH);
    }

    public void setWidth(Long width) {
        set(PROPERTY_WIDTH, width);
    }

    public Long getHeight() {
        return (Long) get(PROPERTY_HEIGHT);
    }

    public void setHeight(Long height) {
        set(PROPERTY_HEIGHT, height);
    }

    public String getMimetype() {
        return (String) get(PROPERTY_MIMETYPE);
    }

    public void setMimetype(String mimetype) {
        set(PROPERTY_MIMETYPE, mimetype);
    }

    @SuppressWarnings("unchecked")
    public List<SystemInformation> getADSystemInformationYourCompanyLoginImageList() {
        return (List<SystemInformation>) get(PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYLOGINIMAGELIST);
    }

    public void setADSystemInformationYourCompanyLoginImageList(List<SystemInformation> aDSystemInformationYourCompanyLoginImageList) {
        set(PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYLOGINIMAGELIST, aDSystemInformationYourCompanyLoginImageList);
    }

    @SuppressWarnings("unchecked")
    public List<SystemInformation> getADSystemInformationYourItServiceLoginImageList() {
        return (List<SystemInformation>) get(PROPERTY_ADSYSTEMINFORMATIONYOURITSERVICELOGINIMAGELIST);
    }

    public void setADSystemInformationYourItServiceLoginImageList(List<SystemInformation> aDSystemInformationYourItServiceLoginImageList) {
        set(PROPERTY_ADSYSTEMINFORMATIONYOURITSERVICELOGINIMAGELIST, aDSystemInformationYourItServiceLoginImageList);
    }

    @SuppressWarnings("unchecked")
    public List<SystemInformation> getADSystemInformationYourCompanyMenuImageList() {
        return (List<SystemInformation>) get(PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYMENUIMAGELIST);
    }

    public void setADSystemInformationYourCompanyMenuImageList(List<SystemInformation> aDSystemInformationYourCompanyMenuImageList) {
        set(PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYMENUIMAGELIST, aDSystemInformationYourCompanyMenuImageList);
    }

    @SuppressWarnings("unchecked")
    public List<SystemInformation> getADSystemInformationYourCompanyBigImageList() {
        return (List<SystemInformation>) get(PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYBIGIMAGELIST);
    }

    public void setADSystemInformationYourCompanyBigImageList(List<SystemInformation> aDSystemInformationYourCompanyBigImageList) {
        set(PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYBIGIMAGELIST, aDSystemInformationYourCompanyBigImageList);
    }

    @SuppressWarnings("unchecked")
    public List<SystemInformation> getADSystemInformationYourCompanyDocumentImageList() {
        return (List<SystemInformation>) get(PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYDOCUMENTIMAGELIST);
    }

    public void setADSystemInformationYourCompanyDocumentImageList(List<SystemInformation> aDSystemInformationYourCompanyDocumentImageList) {
        set(PROPERTY_ADSYSTEMINFORMATIONYOURCOMPANYDOCUMENTIMAGELIST, aDSystemInformationYourCompanyDocumentImageList);
    }

    @SuppressWarnings("unchecked")
    public List<SystemInformation> getADSystemInformationProductionBannerImageIDList() {
        return (List<SystemInformation>) get(PROPERTY_ADSYSTEMINFORMATIONPRODUCTIONBANNERIMAGEIDLIST);
    }

    public void setADSystemInformationProductionBannerImageIDList(List<SystemInformation> aDSystemInformationProductionBannerImageIDList) {
        set(PROPERTY_ADSYSTEMINFORMATIONPRODUCTIONBANNERIMAGEIDLIST, aDSystemInformationProductionBannerImageIDList);
    }

    @SuppressWarnings("unchecked")
    public List<Tab> getADTabList() {
        return (List<Tab>) get(PROPERTY_ADTABLIST);
    }

    public void setADTabList(List<Tab> aDTabList) {
        set(PROPERTY_ADTABLIST, aDTabList);
    }

    @SuppressWarnings("unchecked")
    public List<User> getADUserList() {
        return (List<User>) get(PROPERTY_ADUSERLIST);
    }

    public void setADUserList(List<User> aDUserList) {
        set(PROPERTY_ADUSERLIST, aDUserList);
    }

    @SuppressWarnings("unchecked")
    public List<Window> getADWindowList() {
        return (List<Window>) get(PROPERTY_ADWINDOWLIST);
    }

    public void setADWindowList(List<Window> aDWindowList) {
        set(PROPERTY_ADWINDOWLIST, aDWindowList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationYourCompanyMenuImageList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONYOURCOMPANYMENUIMAGELIST);
    }

    public void setClientInformationYourCompanyMenuImageList(List<ClientInformation> clientInformationYourCompanyMenuImageList) {
        set(PROPERTY_CLIENTINFORMATIONYOURCOMPANYMENUIMAGELIST, clientInformationYourCompanyMenuImageList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationYourCompanyBigImageList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONYOURCOMPANYBIGIMAGELIST);
    }

    public void setClientInformationYourCompanyBigImageList(List<ClientInformation> clientInformationYourCompanyBigImageList) {
        set(PROPERTY_CLIENTINFORMATIONYOURCOMPANYBIGIMAGELIST, clientInformationYourCompanyBigImageList);
    }

    @SuppressWarnings("unchecked")
    public List<ClientInformation> getClientInformationYourCompanyDocumentImageList() {
        return (List<ClientInformation>) get(PROPERTY_CLIENTINFORMATIONYOURCOMPANYDOCUMENTIMAGELIST);
    }

    public void setClientInformationYourCompanyDocumentImageList(List<ClientInformation> clientInformationYourCompanyDocumentImageList) {
        set(PROPERTY_CLIENTINFORMATIONYOURCOMPANYDOCUMENTIMAGELIST, clientInformationYourCompanyDocumentImageList);
    }

    @SuppressWarnings("unchecked")
    public List<OrganizationInformation> getOrganizationInformationYourCompanyDocumentImageList() {
        return (List<OrganizationInformation>) get(PROPERTY_ORGANIZATIONINFORMATIONYOURCOMPANYDOCUMENTIMAGELIST);
    }

    public void setOrganizationInformationYourCompanyDocumentImageList(List<OrganizationInformation> organizationInformationYourCompanyDocumentImageList) {
        set(PROPERTY_ORGANIZATIONINFORMATIONYOURCOMPANYDOCUMENTIMAGELIST, organizationInformationYourCompanyDocumentImageList);
    }

    @SuppressWarnings("unchecked")
    public List<Product> getProductList() {
        return (List<Product>) get(PROPERTY_PRODUCTLIST);
    }

    public void setProductList(List<Product> productList) {
        set(PROPERTY_PRODUCTLIST, productList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductCategory> getProductCategoryList() {
        return (List<ProductCategory>) get(PROPERTY_PRODUCTCATEGORYLIST);
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        set(PROPERTY_PRODUCTCATEGORYLIST, productCategoryList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDepartmentIssue> getRCGIDepartmentIssueList() {
        return (List<RCGIDepartmentIssue>) get(PROPERTY_RCGIDEPARTMENTISSUELIST);
    }

    public void setRCGIDepartmentIssueList(List<RCGIDepartmentIssue> rCGIDepartmentIssueList) {
        set(PROPERTY_RCGIDEPARTMENTISSUELIST, rCGIDepartmentIssueList);
    }

    @SuppressWarnings("unchecked")
    public List<RCGIDiFamilyList> getRCGIDiFamilyListList() {
        return (List<RCGIDiFamilyList>) get(PROPERTY_RCGIDIFAMILYLISTLIST);
    }

    public void setRCGIDiFamilyListList(List<RCGIDiFamilyList> rCGIDiFamilyListList) {
        set(PROPERTY_RCGIDIFAMILYLISTLIST, rCGIDiFamilyListList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_Emp_Loan> getRCHREmpLoanList() {
        return (List<RCHR_Emp_Loan>) get(PROPERTY_RCHREMPLOANLIST);
    }

    public void setRCHREmpLoanList(List<RCHR_Emp_Loan> rCHREmpLoanList) {
        set(PROPERTY_RCHREMPLOANLIST, rCHREmpLoanList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRInspweave> getRCHRInspweaveList() {
        return (List<RCHRInspweave>) get(PROPERTY_RCHRINSPWEAVELIST);
    }

    public void setRCHRInspweaveList(List<RCHRInspweave> rCHRInspweaveList) {
        set(PROPERTY_RCHRINSPWEAVELIST, rCHRInspweaveList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHR_OverTime> getRCHROverTimeList() {
        return (List<RCHR_OverTime>) get(PROPERTY_RCHROVERTIMELIST);
    }

    public void setRCHROverTimeList(List<RCHR_OverTime> rCHROverTimeList) {
        set(PROPERTY_RCHROVERTIMELIST, rCHROverTimeList);
    }

    @SuppressWarnings("unchecked")
    public List<RCHRVisitorgatepass> getRCHRVisitorgatepassList() {
        return (List<RCHRVisitorgatepass>) get(PROPERTY_RCHRVISITORGATEPASSLIST);
    }

    public void setRCHRVisitorgatepassList(List<RCHRVisitorgatepass> rCHRVisitorgatepassList) {
        set(PROPERTY_RCHRVISITORGATEPASSLIST, rCHRVisitorgatepassList);
    }

    @SuppressWarnings("unchecked")
    public List<RCPLPayrollpaidprocline> getRCPLPayrollpaidproclineList() {
        return (List<RCPLPayrollpaidprocline>) get(PROPERTY_RCPLPAYROLLPAIDPROCLINELIST);
    }

    public void setRCPLPayrollpaidproclineList(List<RCPLPayrollpaidprocline> rCPLPayrollpaidproclineList) {
        set(PROPERTY_RCPLPAYROLLPAIDPROCLINELIST, rCPLPayrollpaidproclineList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrEmployee> getRchrEmployeeList() {
        return (List<RchrEmployee>) get(PROPERTY_RCHREMPLOYEELIST);
    }

    public void setRchrEmployeeList(List<RchrEmployee> rchrEmployeeList) {
        set(PROPERTY_RCHREMPLOYEELIST, rchrEmployeeList);
    }

    @SuppressWarnings("unchecked")
    public List<RchrJoinRejoiningform> getRchrJoinRejoiningformList() {
        return (List<RchrJoinRejoiningform>) get(PROPERTY_RCHRJOINREJOININGFORMLIST);
    }

    public void setRchrJoinRejoiningformList(List<RchrJoinRejoiningform> rchrJoinRejoiningformList) {
        set(PROPERTY_RCHRJOINREJOININGFORMLIST, rchrJoinRejoiningformList);
    }

}
