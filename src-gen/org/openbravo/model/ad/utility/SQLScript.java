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

import org.openbravo.base.structure.BaseOBObject;
/**
 * Entity class for entity SQLScript (stored in table AD_Script_SQL).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class SQLScript extends BaseOBObject  {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Script_SQL";
    public static final String ENTITY_NAME = "SQLScript";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_STRSQL = "strsql";

    public SQLScript() {
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public Long getId() {
        return (Long) get(PROPERTY_ID);
    }

    public void setId(Long id) {
        set(PROPERTY_ID, id);
    }

    public String getStrsql() {
        return (String) get(PROPERTY_STRSQL);
    }

    public void setStrsql(String strsql) {
        set(PROPERTY_STRSQL, strsql);
    }

}
