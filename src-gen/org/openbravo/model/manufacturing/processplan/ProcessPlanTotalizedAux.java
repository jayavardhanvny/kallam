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
package org.openbravo.model.manufacturing.processplan;

import java.math.BigDecimal;

import org.openbravo.base.structure.BaseOBObject;
/**
 * Entity class for entity ProcessPlanTotalized (stored in table MA_ProcessPlan_Tot_Aux).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ProcessPlanTotalizedAux extends BaseOBObject  {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "MA_ProcessPlan_Tot_Aux";
    public static final String ENTITY_NAME = "ProcessPlanTotalized";
    public static final String PROPERTY_PROCESSPLAN = "processPlan";
    public static final String PROPERTY_PROCESSPLANVERSION = "processPlanVersion";
    public static final String PROPERTY_PRODUCED = "produced";
    public static final String PROPERTY_PROQTY = "proqty";
    public static final String PROPERTY_DEPENDANTPRODUCT = "dependantproduct";
    public static final String PROPERTY_DEPQTY = "depqty";
    public static final String PROPERTY_ID = "id";

    public ProcessPlanTotalizedAux() {
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public ProcessPlan getProcessPlan() {
        return (ProcessPlan) get(PROPERTY_PROCESSPLAN);
    }

    public void setProcessPlan(ProcessPlan processPlan) {
        set(PROPERTY_PROCESSPLAN, processPlan);
    }

    public Version getProcessPlanVersion() {
        return (Version) get(PROPERTY_PROCESSPLANVERSION);
    }

    public void setProcessPlanVersion(Version processPlanVersion) {
        set(PROPERTY_PROCESSPLANVERSION, processPlanVersion);
    }

    public String getProduced() {
        return (String) get(PROPERTY_PRODUCED);
    }

    public void setProduced(String produced) {
        set(PROPERTY_PRODUCED, produced);
    }

    public BigDecimal getProqty() {
        return (BigDecimal) get(PROPERTY_PROQTY);
    }

    public void setProqty(BigDecimal proqty) {
        set(PROPERTY_PROQTY, proqty);
    }

    public String getDependantproduct() {
        return (String) get(PROPERTY_DEPENDANTPRODUCT);
    }

    public void setDependantproduct(String dependantproduct) {
        set(PROPERTY_DEPENDANTPRODUCT, dependantproduct);
    }

    public BigDecimal getDepqty() {
        return (BigDecimal) get(PROPERTY_DEPQTY);
    }

    public void setDepqty(BigDecimal depqty) {
        set(PROPERTY_DEPQTY, depqty);
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

}
