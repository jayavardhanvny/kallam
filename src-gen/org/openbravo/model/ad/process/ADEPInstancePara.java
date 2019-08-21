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
package org.openbravo.model.ad.process;

import java.math.BigDecimal;
import java.util.Date;

import org.openbravo.base.structure.BaseOBObject;
/**
 * Entity class for entity ADEPInstancePara (stored in table AD_EP_Instance_Para).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ADEPInstancePara extends BaseOBObject  {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_EP_Instance_Para";
    public static final String ENTITY_NAME = "ADEPInstancePara";
    public static final String PROPERTY_EPINSTANCE = "epInstance";
    public static final String PROPERTY_EXTENSIONPOINTS = "extensionPoints";
    public static final String PROPERTY_PARAMETERNAME = "parameterName";
    public static final String PROPERTY_STRING = "string";
    public static final String PROPERTY_STRINGTO = "stringTo";
    public static final String PROPERTY_PROCESSNUMBER = "processNumber";
    public static final String PROPERTY_PROCESSNUMBERTO = "processNumberTo";
    public static final String PROPERTY_PROCESSDATE = "processDate";
    public static final String PROPERTY_PROCESSDATETO = "processDateTo";
    public static final String PROPERTY_TEXT = "text";
    public static final String PROPERTY_ID = "id";

    public ADEPInstancePara() {
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public String getEpInstance() {
        return (String) get(PROPERTY_EPINSTANCE);
    }

    public void setEpInstance(String epInstance) {
        set(PROPERTY_EPINSTANCE, epInstance);
    }

    public ExtensionPoints getExtensionPoints() {
        return (ExtensionPoints) get(PROPERTY_EXTENSIONPOINTS);
    }

    public void setExtensionPoints(ExtensionPoints extensionPoints) {
        set(PROPERTY_EXTENSIONPOINTS, extensionPoints);
    }

    public String getParameterName() {
        return (String) get(PROPERTY_PARAMETERNAME);
    }

    public void setParameterName(String parameterName) {
        set(PROPERTY_PARAMETERNAME, parameterName);
    }

    public String getString() {
        return (String) get(PROPERTY_STRING);
    }

    public void setString(String string) {
        set(PROPERTY_STRING, string);
    }

    public String getStringTo() {
        return (String) get(PROPERTY_STRINGTO);
    }

    public void setStringTo(String stringTo) {
        set(PROPERTY_STRINGTO, stringTo);
    }

    public BigDecimal getProcessNumber() {
        return (BigDecimal) get(PROPERTY_PROCESSNUMBER);
    }

    public void setProcessNumber(BigDecimal processNumber) {
        set(PROPERTY_PROCESSNUMBER, processNumber);
    }

    public BigDecimal getProcessNumberTo() {
        return (BigDecimal) get(PROPERTY_PROCESSNUMBERTO);
    }

    public void setProcessNumberTo(BigDecimal processNumberTo) {
        set(PROPERTY_PROCESSNUMBERTO, processNumberTo);
    }

    public Date getProcessDate() {
        return (Date) get(PROPERTY_PROCESSDATE);
    }

    public void setProcessDate(Date processDate) {
        set(PROPERTY_PROCESSDATE, processDate);
    }

    public Date getProcessDateTo() {
        return (Date) get(PROPERTY_PROCESSDATETO);
    }

    public void setProcessDateTo(Date processDateTo) {
        set(PROPERTY_PROCESSDATETO, processDateTo);
    }

    public String getText() {
        return (String) get(PROPERTY_TEXT);
    }

    public void setText(String text) {
        set(PROPERTY_TEXT, text);
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

}
