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
package org.openbravo.model.financialmgmt.accounting.coa;

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
import org.openbravo.model.ad.utility.Tree;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity FinancialMgmtElement (stored in table C_Element).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Element extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "C_Element";
    public static final String ENTITY_NAME = "FinancialMgmtElement";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_VALUEFORMAT = "valueFormat";
    public static final String PROPERTY_TYPE = "type";
    public static final String PROPERTY_BALANCING = "balancing";
    public static final String PROPERTY_NATURALACCOUNT = "naturalAccount";
    public static final String PROPERTY_TREE = "tree";
    public static final String PROPERTY_ADDITIONALTREE2 = "additionalTree2";
    public static final String PROPERTY_ADDITIONALTREE1 = "additionalTree1";
    public static final String PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST = "financialMgmtAcctSchemaElementList";
    public static final String PROPERTY_FINANCIALMGMTELEMENTVALUELIST = "financialMgmtElementValueList";

    public Element() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_TYPE, "A");
        setDefaultValue(PROPERTY_BALANCING, false);
        setDefaultValue(PROPERTY_NATURALACCOUNT, false);
        setDefaultValue(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTELEMENTVALUELIST, new ArrayList<Object>());
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

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public String getValueFormat() {
        return (String) get(PROPERTY_VALUEFORMAT);
    }

    public void setValueFormat(String valueFormat) {
        set(PROPERTY_VALUEFORMAT, valueFormat);
    }

    public String getType() {
        return (String) get(PROPERTY_TYPE);
    }

    public void setType(String type) {
        set(PROPERTY_TYPE, type);
    }

    public Boolean isBalancing() {
        return (Boolean) get(PROPERTY_BALANCING);
    }

    public void setBalancing(Boolean balancing) {
        set(PROPERTY_BALANCING, balancing);
    }

    public Boolean isNaturalAccount() {
        return (Boolean) get(PROPERTY_NATURALACCOUNT);
    }

    public void setNaturalAccount(Boolean naturalAccount) {
        set(PROPERTY_NATURALACCOUNT, naturalAccount);
    }

    public Tree getTree() {
        return (Tree) get(PROPERTY_TREE);
    }

    public void setTree(Tree tree) {
        set(PROPERTY_TREE, tree);
    }

    public Tree getAdditionalTree2() {
        return (Tree) get(PROPERTY_ADDITIONALTREE2);
    }

    public void setAdditionalTree2(Tree additionalTree2) {
        set(PROPERTY_ADDITIONALTREE2, additionalTree2);
    }

    public Tree getAdditionalTree1() {
        return (Tree) get(PROPERTY_ADDITIONALTREE1);
    }

    public void setAdditionalTree1(Tree additionalTree1) {
        set(PROPERTY_ADDITIONALTREE1, additionalTree1);
    }

    @SuppressWarnings("unchecked")
    public List<AcctSchemaElement> getFinancialMgmtAcctSchemaElementList() {
        return (List<AcctSchemaElement>) get(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST);
    }

    public void setFinancialMgmtAcctSchemaElementList(List<AcctSchemaElement> financialMgmtAcctSchemaElementList) {
        set(PROPERTY_FINANCIALMGMTACCTSCHEMAELEMENTLIST, financialMgmtAcctSchemaElementList);
    }

    @SuppressWarnings("unchecked")
    public List<ElementValue> getFinancialMgmtElementValueList() {
        return (List<ElementValue>) get(PROPERTY_FINANCIALMGMTELEMENTVALUELIST);
    }

    public void setFinancialMgmtElementValueList(List<ElementValue> financialMgmtElementValueList) {
        set(PROPERTY_FINANCIALMGMTELEMENTVALUELIST, financialMgmtElementValueList);
    }

}
