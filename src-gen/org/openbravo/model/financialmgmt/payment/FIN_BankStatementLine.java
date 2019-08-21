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
package org.openbravo.model.financialmgmt.payment;

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
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.financialmgmt.gl.GLItem;
/**
 * Entity class for entity FIN_BankStatementLine (stored in table FIN_BankStatementLine).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class FIN_BankStatementLine extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "FIN_BankStatementLine";
    public static final String ENTITY_NAME = "FIN_BankStatementLine";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_BANKSTATEMENT = "bankStatement";
    public static final String PROPERTY_LINENO = "lineNo";
    public static final String PROPERTY_BPARTNERNAME = "bpartnername";
    public static final String PROPERTY_BUSINESSPARTNER = "businessPartner";
    public static final String PROPERTY_TRANSACTIONDATE = "transactionDate";
    public static final String PROPERTY_CRAMOUNT = "cramount";
    public static final String PROPERTY_DRAMOUNT = "dramount";
    public static final String PROPERTY_REFERENCENO = "referenceNo";
    public static final String PROPERTY_MATCHINGTYPE = "matchingtype";
    public static final String PROPERTY_FINANCIALACCOUNTTRANSACTION = "financialAccountTransaction";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_GLITEM = "gLItem";
    public static final String PROPERTY_FINRECONCILIATIONLINETEMPLIST = "fINReconciliationLineTempList";
    public static final String PROPERTY_FINRECONCILIATIONLINEVLIST = "fINReconciliationLineVList";

    public FIN_BankStatementLine() {
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CRAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_DRAMOUNT, new BigDecimal(0));
        setDefaultValue(PROPERTY_FINRECONCILIATIONLINETEMPLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINRECONCILIATIONLINEVLIST, new ArrayList<Object>());
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

    public FIN_BankStatement getBankStatement() {
        return (FIN_BankStatement) get(PROPERTY_BANKSTATEMENT);
    }

    public void setBankStatement(FIN_BankStatement bankStatement) {
        set(PROPERTY_BANKSTATEMENT, bankStatement);
    }

    public Long getLineNo() {
        return (Long) get(PROPERTY_LINENO);
    }

    public void setLineNo(Long lineNo) {
        set(PROPERTY_LINENO, lineNo);
    }

    public String getBpartnername() {
        return (String) get(PROPERTY_BPARTNERNAME);
    }

    public void setBpartnername(String bpartnername) {
        set(PROPERTY_BPARTNERNAME, bpartnername);
    }

    public BusinessPartner getBusinessPartner() {
        return (BusinessPartner) get(PROPERTY_BUSINESSPARTNER);
    }

    public void setBusinessPartner(BusinessPartner businessPartner) {
        set(PROPERTY_BUSINESSPARTNER, businessPartner);
    }

    public Date getTransactionDate() {
        return (Date) get(PROPERTY_TRANSACTIONDATE);
    }

    public void setTransactionDate(Date transactionDate) {
        set(PROPERTY_TRANSACTIONDATE, transactionDate);
    }

    public BigDecimal getCramount() {
        return (BigDecimal) get(PROPERTY_CRAMOUNT);
    }

    public void setCramount(BigDecimal cramount) {
        set(PROPERTY_CRAMOUNT, cramount);
    }

    public BigDecimal getDramount() {
        return (BigDecimal) get(PROPERTY_DRAMOUNT);
    }

    public void setDramount(BigDecimal dramount) {
        set(PROPERTY_DRAMOUNT, dramount);
    }

    public String getReferenceNo() {
        return (String) get(PROPERTY_REFERENCENO);
    }

    public void setReferenceNo(String referenceNo) {
        set(PROPERTY_REFERENCENO, referenceNo);
    }

    public String getMatchingtype() {
        return (String) get(PROPERTY_MATCHINGTYPE);
    }

    public void setMatchingtype(String matchingtype) {
        set(PROPERTY_MATCHINGTYPE, matchingtype);
    }

    public FIN_FinaccTransaction getFinancialAccountTransaction() {
        return (FIN_FinaccTransaction) get(PROPERTY_FINANCIALACCOUNTTRANSACTION);
    }

    public void setFinancialAccountTransaction(FIN_FinaccTransaction financialAccountTransaction) {
        set(PROPERTY_FINANCIALACCOUNTTRANSACTION, financialAccountTransaction);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public GLItem getGLItem() {
        return (GLItem) get(PROPERTY_GLITEM);
    }

    public void setGLItem(GLItem gLItem) {
        set(PROPERTY_GLITEM, gLItem);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_ReconciliationLineTemp> getFINReconciliationLineTempList() {
        return (List<FIN_ReconciliationLineTemp>) get(PROPERTY_FINRECONCILIATIONLINETEMPLIST);
    }

    public void setFINReconciliationLineTempList(List<FIN_ReconciliationLineTemp> fINReconciliationLineTempList) {
        set(PROPERTY_FINRECONCILIATIONLINETEMPLIST, fINReconciliationLineTempList);
    }

    @SuppressWarnings("unchecked")
    public List<FIN_ReconciliationLine_v> getFINReconciliationLineVList() {
        return (List<FIN_ReconciliationLine_v>) get(PROPERTY_FINRECONCILIATIONLINEVLIST);
    }

    public void setFINReconciliationLineVList(List<FIN_ReconciliationLine_v> fINReconciliationLineVList) {
        set(PROPERTY_FINRECONCILIATIONLINEVLIST, fINReconciliationLineVList);
    }

}
