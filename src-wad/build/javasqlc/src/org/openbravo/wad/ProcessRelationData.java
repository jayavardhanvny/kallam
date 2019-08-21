//Sqlc generated V1.O00-1
package org.openbravo.wad;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;
import java.util.*;

class ProcessRelationData implements FieldProvider {
static Logger log4j = Logger.getLogger(ProcessRelationData.class);
  private String InitRecordNumber="0";
  public String id;
  public String name;
  public String columnname;
  public String adReferenceId;
  public String reference;
  public String adReferenceValueId;
  public String referencevalue;
  public String adValRuleId;
  public String whereclause;
  public String code;
  public String fieldlength;
  public String displaysize;
  public String required;
  public String defaultvalue;
  public String seqno;
  public String isdisplayed;
  public String xmltext;
  public String xmlFormat;
  public String htmltext;
  public String issameline;
  public String isupdateable;
  public String isparent;
  public String adProcessId;
  public String searchname;
  public String referenceName;
  public String referenceNameTrl;
  public String istranslated;
  public String isjasper;
  public String adCalloutId;
  public String calloutname;
  public String classnameCallout;
  public String mappingCallout;
  public String isreadonly;
  public String displaylogic;
  public String isencrypted;
  public String fieldgroup;
  public String tabid;
  public String valuemax;
  public String valuemin;
  public String javaClassName;
  public String iscolumnencrypted;
  public String isdesencryptable;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("id"))
      return id;
    else if (fieldName.equalsIgnoreCase("name"))
      return name;
    else if (fieldName.equalsIgnoreCase("columnname"))
      return columnname;
    else if (fieldName.equalsIgnoreCase("ad_reference_id") || fieldName.equals("adReferenceId"))
      return adReferenceId;
    else if (fieldName.equalsIgnoreCase("reference"))
      return reference;
    else if (fieldName.equalsIgnoreCase("ad_reference_value_id") || fieldName.equals("adReferenceValueId"))
      return adReferenceValueId;
    else if (fieldName.equalsIgnoreCase("referencevalue"))
      return referencevalue;
    else if (fieldName.equalsIgnoreCase("ad_val_rule_id") || fieldName.equals("adValRuleId"))
      return adValRuleId;
    else if (fieldName.equalsIgnoreCase("whereclause"))
      return whereclause;
    else if (fieldName.equalsIgnoreCase("code"))
      return code;
    else if (fieldName.equalsIgnoreCase("fieldlength"))
      return fieldlength;
    else if (fieldName.equalsIgnoreCase("displaysize"))
      return displaysize;
    else if (fieldName.equalsIgnoreCase("required"))
      return required;
    else if (fieldName.equalsIgnoreCase("defaultvalue"))
      return defaultvalue;
    else if (fieldName.equalsIgnoreCase("seqno"))
      return seqno;
    else if (fieldName.equalsIgnoreCase("isdisplayed"))
      return isdisplayed;
    else if (fieldName.equalsIgnoreCase("xmltext"))
      return xmltext;
    else if (fieldName.equalsIgnoreCase("xml_format") || fieldName.equals("xmlFormat"))
      return xmlFormat;
    else if (fieldName.equalsIgnoreCase("htmltext"))
      return htmltext;
    else if (fieldName.equalsIgnoreCase("issameline"))
      return issameline;
    else if (fieldName.equalsIgnoreCase("isupdateable"))
      return isupdateable;
    else if (fieldName.equalsIgnoreCase("isparent"))
      return isparent;
    else if (fieldName.equalsIgnoreCase("ad_process_id") || fieldName.equals("adProcessId"))
      return adProcessId;
    else if (fieldName.equalsIgnoreCase("searchname"))
      return searchname;
    else if (fieldName.equalsIgnoreCase("reference_name") || fieldName.equals("referenceName"))
      return referenceName;
    else if (fieldName.equalsIgnoreCase("reference_name_trl") || fieldName.equals("referenceNameTrl"))
      return referenceNameTrl;
    else if (fieldName.equalsIgnoreCase("istranslated"))
      return istranslated;
    else if (fieldName.equalsIgnoreCase("isjasper"))
      return isjasper;
    else if (fieldName.equalsIgnoreCase("ad_callout_id") || fieldName.equals("adCalloutId"))
      return adCalloutId;
    else if (fieldName.equalsIgnoreCase("calloutname"))
      return calloutname;
    else if (fieldName.equalsIgnoreCase("classname_callout") || fieldName.equals("classnameCallout"))
      return classnameCallout;
    else if (fieldName.equalsIgnoreCase("mapping_callout") || fieldName.equals("mappingCallout"))
      return mappingCallout;
    else if (fieldName.equalsIgnoreCase("isreadonly"))
      return isreadonly;
    else if (fieldName.equalsIgnoreCase("displaylogic"))
      return displaylogic;
    else if (fieldName.equalsIgnoreCase("isencrypted"))
      return isencrypted;
    else if (fieldName.equalsIgnoreCase("fieldgroup"))
      return fieldgroup;
    else if (fieldName.equalsIgnoreCase("tabid"))
      return tabid;
    else if (fieldName.equalsIgnoreCase("valuemax"))
      return valuemax;
    else if (fieldName.equalsIgnoreCase("valuemin"))
      return valuemin;
    else if (fieldName.equalsIgnoreCase("java_class_name") || fieldName.equals("javaClassName"))
      return javaClassName;
    else if (fieldName.equalsIgnoreCase("iscolumnencrypted"))
      return iscolumnencrypted;
    else if (fieldName.equalsIgnoreCase("isdesencryptable"))
      return isdesencryptable;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

/**
The table references in dictionary
 */
  public static ProcessRelationData[] selectParameters(ConnectionProvider connectionProvider, String adLanguage, String adProcessId)    throws ServletException {
    return selectParameters(connectionProvider, adLanguage, adProcessId, 0, 0);
  }

/**
The table references in dictionary
 */
  public static ProcessRelationData[] selectParameters(ConnectionProvider connectionProvider, String adLanguage, String adProcessId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      select app.AD_PROCESS_ID AS ID, COALESCE(appt.name, app.name) AS NAME, app.COLUMNNAME, app.AD_REFERENCE_ID, app.AD_REFERENCE_ID AS reference, " +
      "      app.AD_REFERENCE_VALUE_ID, app.AD_REFERENCE_VALUE_ID AS referenceValue, app.AD_VAL_RULE_ID, art.WHERECLAUSE, avr.CODE, " +
      "      app.FIELDLENGTH, app.FIELDLENGTH AS DISPLAYSIZE, app.ISMANDATORY AS required, app.DEFAULTVALUE, app.SEQNO, 'Y' as isdisplayed, " +
      "      '' as xmltext, '' as xml_Format, '' as htmltext, 'N' AS IsSameLine, 'Y' AS IsUpdateable, 'N' AS IsParent, '' AS AD_Process_ID, " +
      "      (CASE WHEN (app.AD_REFERENCE_ID<>'30' OR app.AD_REFERENCE_VALUE_ID IS NULL) THEN REPLACE(REPLACE(REPLACE(e.name, 'Substitute', 'Product'), 'BOM', ''), 'M_LocatorTo_ID', 'M_Locator_ID') ELSE (SELECT NAME FROM AD_REFERENCE WHERE AD_REFERENCE.AD_REFERENCE_ID=app.AD_REFERENCE_VALUE_ID) END) as searchName, " +
      "      r.name as reference_name, rt.name as reference_Name_Trl, 'Y' AS istranslated, '' AS ISJASPER, '' AS AD_CallOut_ID, " +
      "      '' AS CallOutName, '' AS ClassName_CallOut, '' AS Mapping_CallOut, 'N' AS IsReadOnly, '' AS DisplayLogic, " +
      "      'N' AS IsEncrypted, '' AS FieldGroup, app.AD_Process_ID AS tabid, app.valuemax, app.valuemin, " +
      "      '' AS Java_Class_Name, 'N' AS IsColumnEncrypted, 'Y' AS IsDesencryptable" +
      "      from ad_process_para app left join ad_ref_table art on app.AD_REFERENCE_VALUE_ID = art.AD_REFERENCE_ID" +
      "                               left join ad_element e on app.ad_element_id = e.ad_element_id" +
      "                               left join AD_PROCESS_PARA_TRL appt on app.AD_PROCESS_PARA_ID = appt.AD_PROCESS_PARA_ID " +
      "                                                                 AND appt.AD_LANGUAGE  = ?" +
      "                               left join ad_val_rule avr on app.AD_VAL_RULE_ID = avr.AD_VAL_RULE_ID, " +
      "          ad_reference r left join ad_reference_trl rt on r.ad_reference_id = rt.ad_reference_id " +
      "                                                      and rt.ad_language = ?" +
      "      where app.AD_PROCESS_ID = ? " +
      "      and app.ad_reference_id = r.ad_reference_id  " +
      "      and app.ISACTIVE = 'Y' " +
      "      ORDER BY app.SEQNO";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adLanguage);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adLanguage);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adProcessId);

      result = st.executeQuery();
      long countRecord = 0;
      long countRecordSkip = 1;
      boolean continueResult = true;
      while(countRecordSkip < firstRegister && continueResult) {
        continueResult = result.next();
        countRecordSkip++;
      }
      while(continueResult && result.next()) {
        countRecord++;
        ProcessRelationData objectProcessRelationData = new ProcessRelationData();
        objectProcessRelationData.id = UtilSql.getValue(result, "id");
        objectProcessRelationData.name = UtilSql.getValue(result, "name");
        objectProcessRelationData.columnname = UtilSql.getValue(result, "columnname");
        objectProcessRelationData.adReferenceId = UtilSql.getValue(result, "ad_reference_id");
        objectProcessRelationData.reference = UtilSql.getValue(result, "reference");
        objectProcessRelationData.adReferenceValueId = UtilSql.getValue(result, "ad_reference_value_id");
        objectProcessRelationData.referencevalue = UtilSql.getValue(result, "referencevalue");
        objectProcessRelationData.adValRuleId = UtilSql.getValue(result, "ad_val_rule_id");
        objectProcessRelationData.whereclause = UtilSql.getValue(result, "whereclause");
        objectProcessRelationData.code = UtilSql.getValue(result, "code");
        objectProcessRelationData.fieldlength = UtilSql.getValue(result, "fieldlength");
        objectProcessRelationData.displaysize = UtilSql.getValue(result, "displaysize");
        objectProcessRelationData.required = UtilSql.getValue(result, "required");
        objectProcessRelationData.defaultvalue = UtilSql.getValue(result, "defaultvalue");
        objectProcessRelationData.seqno = UtilSql.getValue(result, "seqno");
        objectProcessRelationData.isdisplayed = UtilSql.getValue(result, "isdisplayed");
        objectProcessRelationData.xmltext = UtilSql.getValue(result, "xmltext");
        objectProcessRelationData.xmlFormat = UtilSql.getValue(result, "xml_format");
        objectProcessRelationData.htmltext = UtilSql.getValue(result, "htmltext");
        objectProcessRelationData.issameline = UtilSql.getValue(result, "issameline");
        objectProcessRelationData.isupdateable = UtilSql.getValue(result, "isupdateable");
        objectProcessRelationData.isparent = UtilSql.getValue(result, "isparent");
        objectProcessRelationData.adProcessId = UtilSql.getValue(result, "ad_process_id");
        objectProcessRelationData.searchname = UtilSql.getValue(result, "searchname");
        objectProcessRelationData.referenceName = UtilSql.getValue(result, "reference_name");
        objectProcessRelationData.referenceNameTrl = UtilSql.getValue(result, "reference_name_trl");
        objectProcessRelationData.istranslated = UtilSql.getValue(result, "istranslated");
        objectProcessRelationData.isjasper = UtilSql.getValue(result, "isjasper");
        objectProcessRelationData.adCalloutId = UtilSql.getValue(result, "ad_callout_id");
        objectProcessRelationData.calloutname = UtilSql.getValue(result, "calloutname");
        objectProcessRelationData.classnameCallout = UtilSql.getValue(result, "classname_callout");
        objectProcessRelationData.mappingCallout = UtilSql.getValue(result, "mapping_callout");
        objectProcessRelationData.isreadonly = UtilSql.getValue(result, "isreadonly");
        objectProcessRelationData.displaylogic = UtilSql.getValue(result, "displaylogic");
        objectProcessRelationData.isencrypted = UtilSql.getValue(result, "isencrypted");
        objectProcessRelationData.fieldgroup = UtilSql.getValue(result, "fieldgroup");
        objectProcessRelationData.tabid = UtilSql.getValue(result, "tabid");
        objectProcessRelationData.valuemax = UtilSql.getValue(result, "valuemax");
        objectProcessRelationData.valuemin = UtilSql.getValue(result, "valuemin");
        objectProcessRelationData.javaClassName = UtilSql.getValue(result, "java_class_name");
        objectProcessRelationData.iscolumnencrypted = UtilSql.getValue(result, "iscolumnencrypted");
        objectProcessRelationData.isdesencryptable = UtilSql.getValue(result, "isdesencryptable");
        objectProcessRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectProcessRelationData);
        if (countRecord >= numberRegisters && numberRegisters != 0) {
          continueResult = false;
        }
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    ProcessRelationData objectProcessRelationData[] = new ProcessRelationData[vector.size()];
    vector.copyInto(objectProcessRelationData);
    return(objectProcessRelationData);
  }

/**
The table references in dictionary
 */
  public static ProcessRelationData[] select(ConnectionProvider connectionProvider)    throws ServletException {
    return select(connectionProvider, 0, 0);
  }

/**
The table references in dictionary
 */
  public static ProcessRelationData[] select(ConnectionProvider connectionProvider, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT AD_PROCESS_ID, PROCEDURENAME AS NAME, ISJASPER " +
      "      FROM AD_PROCESS " +
      "      WHERE ISACTIVE = 'Y' " +
      "      AND (PROCEDURENAME IS NOT NULL " +
      "      OR ISJASPER = 'Y')" +
      "      ORDER BY AD_PROCESS_ID";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    try {
    st = connectionProvider.getPreparedStatement(strSql);

      result = st.executeQuery();
      long countRecord = 0;
      long countRecordSkip = 1;
      boolean continueResult = true;
      while(countRecordSkip < firstRegister && continueResult) {
        continueResult = result.next();
        countRecordSkip++;
      }
      while(continueResult && result.next()) {
        countRecord++;
        ProcessRelationData objectProcessRelationData = new ProcessRelationData();
        objectProcessRelationData.adProcessId = UtilSql.getValue(result, "ad_process_id");
        objectProcessRelationData.name = UtilSql.getValue(result, "name");
        objectProcessRelationData.isjasper = UtilSql.getValue(result, "isjasper");
        objectProcessRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectProcessRelationData);
        if (countRecord >= numberRegisters && numberRegisters != 0) {
          continueResult = false;
        }
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    ProcessRelationData objectProcessRelationData[] = new ProcessRelationData[vector.size()];
    vector.copyInto(objectProcessRelationData);
    return(objectProcessRelationData);
  }

/**
The table references in dictionary
 */
  public static boolean generateActionButton(ConnectionProvider connectionProvider)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT (CASE WHEN COUNT(*)=0 THEN 0 ELSE 1 END) AS AD_PROCESS_ID" +
      "      FROM AD_PROCESS P, AD_SYSTEM_INFO SI" +
      "      WHERE P.ISACTIVE = 'Y' " +
      "      AND (PROCEDURENAME IS NOT NULL " +
      "           OR ISJASPER = 'Y')" +
      "      AND P.UPDATED > SI.LAST_BUILD" +
      "      ORDER BY AD_PROCESS_ID";

    ResultSet result;
    boolean boolReturn = false;
    PreparedStatement st = null;

    try {
    st = connectionProvider.getPreparedStatement(strSql);

      result = st.executeQuery();
      if(result.next()) {
        boolReturn = !UtilSql.getValue(result, "ad_process_id").equals("0");
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    return(boolReturn);
  }

/**
The table references in dictionary
 */
  public static ProcessRelationData[] selectXSQL(ConnectionProvider connectionProvider, String adTabId)    throws ServletException {
    return selectXSQL(connectionProvider, adTabId, 0, 0);
  }

/**
The table references in dictionary
 */
  public static ProcessRelationData[] selectXSQL(ConnectionProvider connectionProvider, String adTabId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      select app.columnname as name, " +
      "            (CASE WHEN (app.ad_reference_id<>'30' OR app.ad_reference_value_id is null) " +
      "              THEN app.columnname " +
      "              ELSE c.columnname END) as searchName, '' as WHERECLAUSE, app.AD_REFERENCE_ID, ad_column.istranslated " +
      "      from ad_process_para app left join ad_ref_search r on app.ad_reference_value_id = r.ad_reference_id" +
      "                               left join ad_column c on r.ad_column_id = c.ad_column_id, " +
      "      ad_field, ad_column " +
      "      where ad_field.ad_column_id = ad_column.ad_column_id " +
      "      and ad_column.ad_process_id = app.ad_process_id " +
      "      AND ad_field.ignoreinwad='N'" +
      "      and app.ISACTIVE = 'Y' " +
      "      and app.AD_REFERENCE_ID IN ('30', '31', '35', '25') " +
      "      and ad_field.ad_tab_id = ? " +
      "      GROUP BY app.columnname, (CASE WHEN (app.ad_reference_id<>'30' OR app.ad_reference_value_id is null) " +
      "              THEN app.columnname " +
      "              ELSE c.columnname END), app.AD_REFERENCE_ID, ad_column.istranslated";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adTabId);

      result = st.executeQuery();
      long countRecord = 0;
      long countRecordSkip = 1;
      boolean continueResult = true;
      while(countRecordSkip < firstRegister && continueResult) {
        continueResult = result.next();
        countRecordSkip++;
      }
      while(continueResult && result.next()) {
        countRecord++;
        ProcessRelationData objectProcessRelationData = new ProcessRelationData();
        objectProcessRelationData.name = UtilSql.getValue(result, "name");
        objectProcessRelationData.searchname = UtilSql.getValue(result, "searchname");
        objectProcessRelationData.whereclause = UtilSql.getValue(result, "whereclause");
        objectProcessRelationData.adReferenceId = UtilSql.getValue(result, "ad_reference_id");
        objectProcessRelationData.istranslated = UtilSql.getValue(result, "istranslated");
        objectProcessRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectProcessRelationData);
        if (countRecord >= numberRegisters && numberRegisters != 0) {
          continueResult = false;
        }
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    ProcessRelationData objectProcessRelationData[] = new ProcessRelationData[vector.size()];
    vector.copyInto(objectProcessRelationData);
    return(objectProcessRelationData);
  }

/**
The table references in dictionary
 */
  public static ProcessRelationData[] selectXSQLParams(ConnectionProvider connectionProvider, String adTabId)    throws ServletException {
    return selectXSQLParams(connectionProvider, adTabId, 0, 0);
  }

/**
The table references in dictionary
 */
  public static ProcessRelationData[] selectXSQLParams(ConnectionProvider connectionProvider, String adTabId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      select c.ad_process_id, p.columnname, p.DEFAULTVALUE, '' as WHERECLAUSE, '' as reference, c.istranslated " +
      "      from ad_column c, ad_field f, ad_process_para p " +
      "      where c.AD_COLUMN_ID = f.AD_COLUMN_ID " +
      "      and c.AD_PROCESS_ID = p.AD_PROCESS_ID  " +
      "      and f.ignoreinwad='N'" +
      "      and p.DEFAULTVALUE LIKE '@SQL=%'" +
      "      and f.AD_TAB_ID = ?";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adTabId);

      result = st.executeQuery();
      long countRecord = 0;
      long countRecordSkip = 1;
      boolean continueResult = true;
      while(countRecordSkip < firstRegister && continueResult) {
        continueResult = result.next();
        countRecordSkip++;
      }
      while(continueResult && result.next()) {
        countRecord++;
        ProcessRelationData objectProcessRelationData = new ProcessRelationData();
        objectProcessRelationData.adProcessId = UtilSql.getValue(result, "ad_process_id");
        objectProcessRelationData.columnname = UtilSql.getValue(result, "columnname");
        objectProcessRelationData.defaultvalue = UtilSql.getValue(result, "defaultvalue");
        objectProcessRelationData.whereclause = UtilSql.getValue(result, "whereclause");
        objectProcessRelationData.reference = UtilSql.getValue(result, "reference");
        objectProcessRelationData.istranslated = UtilSql.getValue(result, "istranslated");
        objectProcessRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectProcessRelationData);
        if (countRecord >= numberRegisters && numberRegisters != 0) {
          continueResult = false;
        }
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    ProcessRelationData objectProcessRelationData[] = new ProcessRelationData[vector.size()];
    vector.copyInto(objectProcessRelationData);
    return(objectProcessRelationData);
  }

/**
The table references in dictionary
 */
  public static ProcessRelationData[] selectXSQLGenericsParams(ConnectionProvider connectionProvider)    throws ServletException {
    return selectXSQLGenericsParams(connectionProvider, 0, 0);
  }

/**
The table references in dictionary
 */
  public static ProcessRelationData[] selectXSQLGenericsParams(ConnectionProvider connectionProvider, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      select p.ad_process_id, p.columnname, p.DEFAULTVALUE, '' as WHERECLAUSE, '' as reference " +
      "      from ad_process_para p, ad_process pr " +
      "      where p.DEFAULTVALUE LIKE '@SQL=%'" +
      "      and pr.isactive='Y' " +
      "      and pr.UIPattern = 'S'" +
      "      and p.ad_process_id = pr.ad_process_id";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    try {
    st = connectionProvider.getPreparedStatement(strSql);

      result = st.executeQuery();
      long countRecord = 0;
      long countRecordSkip = 1;
      boolean continueResult = true;
      while(countRecordSkip < firstRegister && continueResult) {
        continueResult = result.next();
        countRecordSkip++;
      }
      while(continueResult && result.next()) {
        countRecord++;
        ProcessRelationData objectProcessRelationData = new ProcessRelationData();
        objectProcessRelationData.adProcessId = UtilSql.getValue(result, "ad_process_id");
        objectProcessRelationData.columnname = UtilSql.getValue(result, "columnname");
        objectProcessRelationData.defaultvalue = UtilSql.getValue(result, "defaultvalue");
        objectProcessRelationData.whereclause = UtilSql.getValue(result, "whereclause");
        objectProcessRelationData.reference = UtilSql.getValue(result, "reference");
        objectProcessRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectProcessRelationData);
        if (countRecord >= numberRegisters && numberRegisters != 0) {
          continueResult = false;
        }
      }
      result.close();
    } catch(SQLException e){
      log4j.error("SQL error in query: " + strSql + "Exception:"+ e);
      throw new ServletException("@CODE=" + Integer.toString(e.getErrorCode()) + "@" + e.getMessage());
    } catch(Exception ex){
      log4j.error("Exception in query: " + strSql + "Exception:"+ ex);
      throw new ServletException("@CODE=@" + ex.getMessage());
    } finally {
      try {
        connectionProvider.releasePreparedStatement(st);
      } catch(Exception ignore){
        ignore.printStackTrace();
      }
    }
    ProcessRelationData objectProcessRelationData[] = new ProcessRelationData[vector.size()];
    vector.copyInto(objectProcessRelationData);
    return(objectProcessRelationData);
  }
}
