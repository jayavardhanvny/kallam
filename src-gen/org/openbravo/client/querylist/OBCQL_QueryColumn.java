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
package org.openbravo.client.querylist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.domain.Reference;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity OBCQL_QueryColumn (stored in table OBCQL_Query_Column).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class OBCQL_QueryColumn extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "OBCQL_Query_Column";
    public static final String ENTITY_NAME = "OBCQL_QueryColumn";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_WIDGETQUERY = "widgetQuery";
    public static final String PROPERTY_DISPLAYEXPRESSION = "displayExpression";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_SUMMARIZETYPE = "summarizeType";
    public static final String PROPERTY_WIDTH = "width";
    public static final String PROPERTY_LINKEXPRESSION = "linkExpression";
    public static final String PROPERTY_SEQUENCENUMBER = "sequenceNumber";
    public static final String PROPERTY_REFERENCE = "reference";
    public static final String PROPERTY_REFERENCESEARCHKEY = "referenceSearchKey";
    public static final String PROPERTY_INCLUDEIN = "includeIn";
    public static final String PROPERTY_HASLINK = "hasLink";
    public static final String PROPERTY_TAB = "tab";
    public static final String PROPERTY_CANBEFILTERED = "canBeFiltered";
    public static final String PROPERTY_WHERECLAUSELEFTPART = "whereClauseLeftPart";
    public static final String PROPERTY_CLIENTCLASS = "clientClass";
    public static final String PROPERTY_OBCQLQUERYCOLUMNTRLLIST = "oBCQLQueryColumnTrlList";

    public OBCQL_QueryColumn() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_INCLUDEIN, "W");
        setDefaultValue(PROPERTY_HASLINK, false);
        setDefaultValue(PROPERTY_CANBEFILTERED, false);
        setDefaultValue(PROPERTY_OBCQLQUERYCOLUMNTRLLIST, new ArrayList<Object>());
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

    public OBCQL_WidgetQuery getWidgetQuery() {
        return (OBCQL_WidgetQuery) get(PROPERTY_WIDGETQUERY);
    }

    public void setWidgetQuery(OBCQL_WidgetQuery widgetQuery) {
        set(PROPERTY_WIDGETQUERY, widgetQuery);
    }

    public String getDisplayExpression() {
        return (String) get(PROPERTY_DISPLAYEXPRESSION);
    }

    public void setDisplayExpression(String displayExpression) {
        set(PROPERTY_DISPLAYEXPRESSION, displayExpression);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getSummarizeType() {
        return (String) get(PROPERTY_SUMMARIZETYPE);
    }

    public void setSummarizeType(String summarizeType) {
        set(PROPERTY_SUMMARIZETYPE, summarizeType);
    }

    public Long getWidth() {
        return (Long) get(PROPERTY_WIDTH);
    }

    public void setWidth(Long width) {
        set(PROPERTY_WIDTH, width);
    }

    public String getLinkExpression() {
        return (String) get(PROPERTY_LINKEXPRESSION);
    }

    public void setLinkExpression(String linkExpression) {
        set(PROPERTY_LINKEXPRESSION, linkExpression);
    }

    public Long getSequenceNumber() {
        return (Long) get(PROPERTY_SEQUENCENUMBER);
    }

    public void setSequenceNumber(Long sequenceNumber) {
        set(PROPERTY_SEQUENCENUMBER, sequenceNumber);
    }

    public Reference getReference() {
        return (Reference) get(PROPERTY_REFERENCE);
    }

    public void setReference(Reference reference) {
        set(PROPERTY_REFERENCE, reference);
    }

    public Reference getReferenceSearchKey() {
        return (Reference) get(PROPERTY_REFERENCESEARCHKEY);
    }

    public void setReferenceSearchKey(Reference referenceSearchKey) {
        set(PROPERTY_REFERENCESEARCHKEY, referenceSearchKey);
    }

    public String getIncludeIn() {
        return (String) get(PROPERTY_INCLUDEIN);
    }

    public void setIncludeIn(String includeIn) {
        set(PROPERTY_INCLUDEIN, includeIn);
    }

    public Boolean isHasLink() {
        return (Boolean) get(PROPERTY_HASLINK);
    }

    public void setHasLink(Boolean hasLink) {
        set(PROPERTY_HASLINK, hasLink);
    }

    public Tab getTab() {
        return (Tab) get(PROPERTY_TAB);
    }

    public void setTab(Tab tab) {
        set(PROPERTY_TAB, tab);
    }

    public Boolean isCanBeFiltered() {
        return (Boolean) get(PROPERTY_CANBEFILTERED);
    }

    public void setCanBeFiltered(Boolean canBeFiltered) {
        set(PROPERTY_CANBEFILTERED, canBeFiltered);
    }

    public String getWhereClauseLeftPart() {
        return (String) get(PROPERTY_WHERECLAUSELEFTPART);
    }

    public void setWhereClauseLeftPart(String whereClauseLeftPart) {
        set(PROPERTY_WHERECLAUSELEFTPART, whereClauseLeftPart);
    }

    public String getClientClass() {
        return (String) get(PROPERTY_CLIENTCLASS);
    }

    public void setClientClass(String clientClass) {
        set(PROPERTY_CLIENTCLASS, clientClass);
    }

    @SuppressWarnings("unchecked")
    public List<QueryColumnTrl> getOBCQLQueryColumnTrlList() {
        return (List<QueryColumnTrl>) get(PROPERTY_OBCQLQUERYCOLUMNTRLLIST);
    }

    public void setOBCQLQueryColumnTrlList(List<QueryColumnTrl> oBCQLQueryColumnTrlList) {
        set(PROPERTY_OBCQLQUERYCOLUMNTRLLIST, oBCQLQueryColumnTrlList);
    }

}
