//Sqlc generated V1.O00-1
package org.openbravo.wad;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;
import java.util.*;

class ActionButtonRelationData implements FieldProvider {
static Logger log4j = Logger.getLogger(ActionButtonRelationData.class);
  private String InitRecordNumber="0";
  public String realname;
  public String columnname;
  public String adProcessId;
  public String adModuleId;
  public String htmlfields;
  public String htmltext;
  public String htmlfieldsHeader;
  public String adTableId;
  public String javacode;
  public String adReferenceValueId;
  public String processParams;
  public String processCode;
  public String additionalCode;
  public String processParamsCode;
  public String xmlid;
  public String classname;
  public String mappingname;
  public String isjasper;
  public String total;
  public String comboparacode;
  public String setsession;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("realname"))
      return realname;
    else if (fieldName.equalsIgnoreCase("columnname"))
      return columnname;
    else if (fieldName.equalsIgnoreCase("ad_process_id") || fieldName.equals("adProcessId"))
      return adProcessId;
    else if (fieldName.equalsIgnoreCase("ad_module_id") || fieldName.equals("adModuleId"))
      return adModuleId;
    else if (fieldName.equalsIgnoreCase("htmlfields"))
      return htmlfields;
    else if (fieldName.equalsIgnoreCase("htmltext"))
      return htmltext;
    else if (fieldName.equalsIgnoreCase("htmlfields_header") || fieldName.equals("htmlfieldsHeader"))
      return htmlfieldsHeader;
    else if (fieldName.equalsIgnoreCase("ad_table_id") || fieldName.equals("adTableId"))
      return adTableId;
    else if (fieldName.equalsIgnoreCase("javacode"))
      return javacode;
    else if (fieldName.equalsIgnoreCase("ad_reference_value_id") || fieldName.equals("adReferenceValueId"))
      return adReferenceValueId;
    else if (fieldName.equalsIgnoreCase("process_params") || fieldName.equals("processParams"))
      return processParams;
    else if (fieldName.equalsIgnoreCase("process_code") || fieldName.equals("processCode"))
      return processCode;
    else if (fieldName.equalsIgnoreCase("additional_code") || fieldName.equals("additionalCode"))
      return additionalCode;
    else if (fieldName.equalsIgnoreCase("process_params_code") || fieldName.equals("processParamsCode"))
      return processParamsCode;
    else if (fieldName.equalsIgnoreCase("xmlid"))
      return xmlid;
    else if (fieldName.equalsIgnoreCase("classname"))
      return classname;
    else if (fieldName.equalsIgnoreCase("mappingname"))
      return mappingname;
    else if (fieldName.equalsIgnoreCase("isjasper"))
      return isjasper;
    else if (fieldName.equalsIgnoreCase("total"))
      return total;
    else if (fieldName.equalsIgnoreCase("comboparacode"))
      return comboparacode;
    else if (fieldName.equalsIgnoreCase("setsession"))
      return setsession;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

/**
The table references in dictionary
 */
  public static ActionButtonRelationData[] select(ConnectionProvider connectionProvider, String tabId)    throws ServletException {
    return select(connectionProvider, tabId, 0, 0);
  }

/**
The table references in dictionary
 */
  public static ActionButtonRelationData[] select(ConnectionProvider connectionProvider, String tabId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT AD_COLUMN.columnname AS realname, AD_COLUMN.columnname, AD_COLUMN.AD_PROCESS_ID, AD_PROCESS.AD_MODULE_ID," +
      "      '' AS htmlfields, '' AS htmltext, '' AS htmlfields_Header, AD_COLUMN.AD_TABLE_ID, '' AS javacode, " +
      "      AD_COLUMN.ad_reference_value_id, '' AS process_Params, '' AS process_code, '' AS additional_code, " +
      "      '' AS process_Params_Code, AD_COLUMN.AD_PROCESS_ID AS xmlid, mo.classname, mom.mappingname," +
      "      '' AS isjasper, '' as total, '' as comboparacode, '' as  setsession" +
      "      FROM AD_FIELD, AD_COLUMN" +
      "            left join ad_model_object mo on ad_column.ad_process_id = mo.ad_process_id " +
      "                          and mo.isactive = 'Y' " +
      "                          and mo.action = 'P' " +
      "                          and mo.isdefault = 'Y' " +
      "            left join ad_model_object_mapping mom on mo.ad_model_object_id = mom.ad_model_object_id " +
      "                          and mom.isactive = 'Y'" +
      "                          and mom.isdefault = 'Y', " +
      "      AD_TABLE, AD_PROCESS" +
      "      WHERE AD_FIELD.ad_column_id = AD_COLUMN.ad_column_id" +
      "      AND AD_COLUMN.AD_TABLE_ID = AD_TABLE.AD_TABLE_ID " +
      "      AND ad_field.ad_tab_id = ? " +
      "      AND AD_PROCESS.AD_PROCESS_ID = AD_COLUMN.AD_PROCESS_ID" +
      "      AND AD_PROCESS.PROCEDURENAME IS NOT NULL " +
      "      AND AD_COLUMN.ad_reference_id = '28'" +
      "      AND (AD_COLUMN.COLUMNNAME <> 'CreateFrom' " +
      "      OR AD_COLUMN.AD_PROCESS_ID IS NOT NULL )" +
      "      AND (AD_COLUMN.COLUMNNAME <> 'Posted' " +
      "      OR AD_COLUMN.AD_PROCESS_ID IS NOT NULL )" +
      "      AND AD_FIELD.ISDISPLAYED = 'Y'" +
      "      ORDER BY AD_FIELD.SEQNO";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, tabId);

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
        ActionButtonRelationData objectActionButtonRelationData = new ActionButtonRelationData();
        objectActionButtonRelationData.realname = UtilSql.getValue(result, "realname");
        objectActionButtonRelationData.columnname = UtilSql.getValue(result, "columnname");
        objectActionButtonRelationData.adProcessId = UtilSql.getValue(result, "ad_process_id");
        objectActionButtonRelationData.adModuleId = UtilSql.getValue(result, "ad_module_id");
        objectActionButtonRelationData.htmlfields = UtilSql.getValue(result, "htmlfields");
        objectActionButtonRelationData.htmltext = UtilSql.getValue(result, "htmltext");
        objectActionButtonRelationData.htmlfieldsHeader = UtilSql.getValue(result, "htmlfields_header");
        objectActionButtonRelationData.adTableId = UtilSql.getValue(result, "ad_table_id");
        objectActionButtonRelationData.javacode = UtilSql.getValue(result, "javacode");
        objectActionButtonRelationData.adReferenceValueId = UtilSql.getValue(result, "ad_reference_value_id");
        objectActionButtonRelationData.processParams = UtilSql.getValue(result, "process_params");
        objectActionButtonRelationData.processCode = UtilSql.getValue(result, "process_code");
        objectActionButtonRelationData.additionalCode = UtilSql.getValue(result, "additional_code");
        objectActionButtonRelationData.processParamsCode = UtilSql.getValue(result, "process_params_code");
        objectActionButtonRelationData.xmlid = UtilSql.getValue(result, "xmlid");
        objectActionButtonRelationData.classname = UtilSql.getValue(result, "classname");
        objectActionButtonRelationData.mappingname = UtilSql.getValue(result, "mappingname");
        objectActionButtonRelationData.isjasper = UtilSql.getValue(result, "isjasper");
        objectActionButtonRelationData.total = UtilSql.getValue(result, "total");
        objectActionButtonRelationData.comboparacode = UtilSql.getValue(result, "comboparacode");
        objectActionButtonRelationData.setsession = UtilSql.getValue(result, "setsession");
        objectActionButtonRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectActionButtonRelationData);
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
    ActionButtonRelationData objectActionButtonRelationData[] = new ActionButtonRelationData[vector.size()];
    vector.copyInto(objectActionButtonRelationData);
    return(objectActionButtonRelationData);
  }

/**
The table references in dictionary
 */
  public static ActionButtonRelationData[] selectJava(ConnectionProvider connectionProvider, String tabId)    throws ServletException {
    return selectJava(connectionProvider, tabId, 0, 0);
  }

/**
The table references in dictionary
 */
  public static ActionButtonRelationData[] selectJava(ConnectionProvider connectionProvider, String tabId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT AD_COLUMN.columnname AS realname, AD_COLUMN.columnname, AD_COLUMN.AD_PROCESS_ID, AD_PROCESS.AD_MODULE_ID," +
      "      AD_COLUMN.AD_TABLE_ID,  AD_COLUMN.ad_reference_value_id, " +
      "      AD_COLUMN.AD_PROCESS_ID AS xmlid, mo.classname" +
      "      FROM AD_FIELD, AD_COLUMN, ad_model_object mo," +
      "      AD_TABLE, AD_PROCESS" +
      "      WHERE AD_FIELD.ad_column_id = AD_COLUMN.ad_column_id" +
      "      AND AD_COLUMN.AD_TABLE_ID = AD_TABLE.AD_TABLE_ID " +
      "      AND ad_field.ad_tab_id = ? " +
      "      AND AD_PROCESS.AD_PROCESS_ID = AD_COLUMN.AD_PROCESS_ID" +
      "      AND AD_PROCESS.PROCEDURENAME IS NULL" +
      "      AND UIPattern='S' " +
      "      AND ad_column.ad_process_id = mo.ad_process_id " +
      "      and mo.isactive = 'Y' " +
      "      and mo.action = 'P' " +
      "      and mo.isdefault = 'Y'" +
      "      AND AD_COLUMN.ad_reference_id = '28'" +
      "      AND (AD_COLUMN.COLUMNNAME <> 'CreateFrom' " +
      "      OR AD_COLUMN.AD_PROCESS_ID IS NOT NULL )" +
      "      AND (AD_COLUMN.COLUMNNAME <> 'Posted' " +
      "      OR AD_COLUMN.AD_PROCESS_ID IS NOT NULL )" +
      "      AND AD_FIELD.ISDISPLAYED = 'Y'" +
      "      ORDER BY AD_FIELD.SEQNO";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, tabId);

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
        ActionButtonRelationData objectActionButtonRelationData = new ActionButtonRelationData();
        objectActionButtonRelationData.realname = UtilSql.getValue(result, "realname");
        objectActionButtonRelationData.columnname = UtilSql.getValue(result, "columnname");
        objectActionButtonRelationData.adProcessId = UtilSql.getValue(result, "ad_process_id");
        objectActionButtonRelationData.adModuleId = UtilSql.getValue(result, "ad_module_id");
        objectActionButtonRelationData.adTableId = UtilSql.getValue(result, "ad_table_id");
        objectActionButtonRelationData.adReferenceValueId = UtilSql.getValue(result, "ad_reference_value_id");
        objectActionButtonRelationData.xmlid = UtilSql.getValue(result, "xmlid");
        objectActionButtonRelationData.classname = UtilSql.getValue(result, "classname");
        objectActionButtonRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectActionButtonRelationData);
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
    ActionButtonRelationData objectActionButtonRelationData[] = new ActionButtonRelationData[vector.size()];
    vector.copyInto(objectActionButtonRelationData);
    return(objectActionButtonRelationData);
  }

/**
The table references in dictionary
 */
  public static ActionButtonRelationData[] selectGenerics(ConnectionProvider connectionProvider)    throws ServletException {
    return selectGenerics(connectionProvider, 0, 0);
  }

/**
The table references in dictionary
 */
  public static ActionButtonRelationData[] selectGenerics(ConnectionProvider connectionProvider, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT '' AS realname, '' AS columnname, ad_process.AD_PROCESS_ID, " +
      "        '' AS htmlfields, '' AS htmltext, '' AS htmlfields_Header, '' AS AD_TABLE_ID, '' AS javacode, " +
      "        '' AS ad_reference_value_id, '' AS process_Params, '' AS process_code, '' AS additional_code, " +
      "        '' AS process_Params_Code, ad_process.AD_PROCESS_ID AS xmlid, mo.classname, mom.mappingname, " +
      "        ad_process.isjasper" +
      "        FROM AD_PROCESS" +
      "            left join ad_model_object mo on ad_process.ad_process_id = mo.ad_process_id " +
      "                          and mo.isactive = 'Y' " +
      "                          and mo.action = 'P' " +
      "                          and mo.isdefault = 'Y' " +
      "            left join ad_model_object_mapping mom on mo.ad_model_object_id = mom.ad_model_object_id " +
      "                          and mom.isactive = 'Y'" +
      "                          and mom.isdefault = 'Y' " +
      "        WHERE ad_process.isactive='Y' " +
      "        AND (ad_process.PROCEDURENAME IS NOT NULL " +
      "        OR ad_process.IsJasper = 'Y') " +
      "        and ad_process.ad_process_id in (select ad_process_id from ad_menu)" +
      "        ORDER BY ad_process.PROCEDURENAME";

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
        ActionButtonRelationData objectActionButtonRelationData = new ActionButtonRelationData();
        objectActionButtonRelationData.realname = UtilSql.getValue(result, "realname");
        objectActionButtonRelationData.columnname = UtilSql.getValue(result, "columnname");
        objectActionButtonRelationData.adProcessId = UtilSql.getValue(result, "ad_process_id");
        objectActionButtonRelationData.htmlfields = UtilSql.getValue(result, "htmlfields");
        objectActionButtonRelationData.htmltext = UtilSql.getValue(result, "htmltext");
        objectActionButtonRelationData.htmlfieldsHeader = UtilSql.getValue(result, "htmlfields_header");
        objectActionButtonRelationData.adTableId = UtilSql.getValue(result, "ad_table_id");
        objectActionButtonRelationData.javacode = UtilSql.getValue(result, "javacode");
        objectActionButtonRelationData.adReferenceValueId = UtilSql.getValue(result, "ad_reference_value_id");
        objectActionButtonRelationData.processParams = UtilSql.getValue(result, "process_params");
        objectActionButtonRelationData.processCode = UtilSql.getValue(result, "process_code");
        objectActionButtonRelationData.additionalCode = UtilSql.getValue(result, "additional_code");
        objectActionButtonRelationData.processParamsCode = UtilSql.getValue(result, "process_params_code");
        objectActionButtonRelationData.xmlid = UtilSql.getValue(result, "xmlid");
        objectActionButtonRelationData.classname = UtilSql.getValue(result, "classname");
        objectActionButtonRelationData.mappingname = UtilSql.getValue(result, "mappingname");
        objectActionButtonRelationData.isjasper = UtilSql.getValue(result, "isjasper");
        objectActionButtonRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectActionButtonRelationData);
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
    ActionButtonRelationData objectActionButtonRelationData[] = new ActionButtonRelationData[vector.size()];
    vector.copyInto(objectActionButtonRelationData);
    return(objectActionButtonRelationData);
  }

/**
The table references in dictionary
 */
  public static ActionButtonRelationData[] selectGenericsJava(ConnectionProvider connectionProvider)    throws ServletException {
    return selectGenericsJava(connectionProvider, 0, 0);
  }

/**
The table references in dictionary
 */
  public static ActionButtonRelationData[] selectGenericsJava(ConnectionProvider connectionProvider, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT '' AS realname, '' AS columnname, ad_process.AD_PROCESS_ID, " +
      "        '' AS htmlfields, '' AS htmltext, '' AS htmlfields_Header, '' AS AD_TABLE_ID, '' AS javacode, " +
      "        '' AS ad_reference_value_id, '' AS process_Params, '' AS process_code, '' AS additional_code, " +
      "        '' AS process_Params_Code, ad_process.AD_PROCESS_ID AS xmlid, mo.classname" +
      "        FROM AD_PROCESS" +
      "            left join ad_model_object mo on ad_process.ad_process_id = mo.ad_process_id " +
      "                          and mo.isactive = 'Y' " +
      "                          and mo.action = 'P' " +
      "                          and mo.isdefault = 'Y' " +
      "        WHERE ad_process.isactive='Y' " +
      "        AND AD_PROCESS.UIPattern = 'S'" +
      "        AND (ad_process.IsJasper = 'N' AND ad_process.PROCEDURENAME IS NULL) ";

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
        ActionButtonRelationData objectActionButtonRelationData = new ActionButtonRelationData();
        objectActionButtonRelationData.realname = UtilSql.getValue(result, "realname");
        objectActionButtonRelationData.columnname = UtilSql.getValue(result, "columnname");
        objectActionButtonRelationData.adProcessId = UtilSql.getValue(result, "ad_process_id");
        objectActionButtonRelationData.htmlfields = UtilSql.getValue(result, "htmlfields");
        objectActionButtonRelationData.htmltext = UtilSql.getValue(result, "htmltext");
        objectActionButtonRelationData.htmlfieldsHeader = UtilSql.getValue(result, "htmlfields_header");
        objectActionButtonRelationData.adTableId = UtilSql.getValue(result, "ad_table_id");
        objectActionButtonRelationData.javacode = UtilSql.getValue(result, "javacode");
        objectActionButtonRelationData.adReferenceValueId = UtilSql.getValue(result, "ad_reference_value_id");
        objectActionButtonRelationData.processParams = UtilSql.getValue(result, "process_params");
        objectActionButtonRelationData.processCode = UtilSql.getValue(result, "process_code");
        objectActionButtonRelationData.additionalCode = UtilSql.getValue(result, "additional_code");
        objectActionButtonRelationData.processParamsCode = UtilSql.getValue(result, "process_params_code");
        objectActionButtonRelationData.xmlid = UtilSql.getValue(result, "xmlid");
        objectActionButtonRelationData.classname = UtilSql.getValue(result, "classname");
        objectActionButtonRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectActionButtonRelationData);
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
    ActionButtonRelationData objectActionButtonRelationData[] = new ActionButtonRelationData[vector.size()];
    vector.copyInto(objectActionButtonRelationData);
    return(objectActionButtonRelationData);
  }

  public static boolean buildGenerics(ConnectionProvider connectionProvider)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT count(*) as total" +
      "          FROM AD_PROCESS P," +
      "            AD_SYSTEM_INFO SI" +
      "          WHERE p.UPDATED>SI.LAST_BUILD" +
      "            or exists (Select 1 " +
      "                         from AD_Process_Para pp" +
      "                        where pp.AD_Process_ID = p.AD_Process_ID" +
      "                          and pp.updated > si.last_build" +
      "                         )";

    ResultSet result;
    boolean boolReturn = false;
    PreparedStatement st = null;

    try {
    st = connectionProvider.getPreparedStatement(strSql);

      result = st.executeQuery();
      if(result.next()) {
        boolReturn = !UtilSql.getValue(result, "total").equals("0");
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
  public static ActionButtonRelationData[] selectDocAction(ConnectionProvider connectionProvider, String tabId)    throws ServletException {
    return selectDocAction(connectionProvider, tabId, 0, 0);
  }

/**
The table references in dictionary
 */
  public static ActionButtonRelationData[] selectDocAction(ConnectionProvider connectionProvider, String tabId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT ad_column.columnname as realname, ad_column.columnname" +
      "      FROM ad_field, ad_column, ad_table" +
      "      WHERE ad_field.ad_column_id = ad_column.ad_column_id" +
      "      AND ad_column.AD_TABLE_ID = ad_table.AD_TABLE_ID " +
      "      AND ad_tab_id = ? " +
      "      AND UPPER(ad_column.columnname) IN ('DOCACTION', 'CHANGEPROJECTSTATUS') " +
      "      AND ad_column.ad_reference_id = '28'" +
      "      AND ad_field.ISDISPLAYED = 'Y'" +
      "      GROUP BY ad_column.columnname";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, tabId);

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
        ActionButtonRelationData objectActionButtonRelationData = new ActionButtonRelationData();
        objectActionButtonRelationData.realname = UtilSql.getValue(result, "realname");
        objectActionButtonRelationData.columnname = UtilSql.getValue(result, "columnname");
        objectActionButtonRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectActionButtonRelationData);
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
    ActionButtonRelationData objectActionButtonRelationData[] = new ActionButtonRelationData[vector.size()];
    vector.copyInto(objectActionButtonRelationData);
    return(objectActionButtonRelationData);
  }

/**
The table references in dictionary
 */
  public static String processDescription(ConnectionProvider connectionProvider, String adProcessId)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT DESCRIPTION FROM AD_PROCESS WHERE AD_PROCESS_ID = ?";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adProcessId);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "description");
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
    return(strReturn);
  }

/**
The table references in dictionary
 */
  public static ActionButtonRelationData[] selectComboParams(ConnectionProvider connectionProvider, String tabId, String processId)    throws ServletException {
    return selectComboParams(connectionProvider, tabId, processId, 0, 0);
  }

/**
The table references in dictionary
 */
  public static ActionButtonRelationData[] selectComboParams(ConnectionProvider connectionProvider, String tabId, String processId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "              select substr(code, instr(code,'@')+1, instr(code,'@',1,2)-instr(code,'@')-1) as columnname" +
      "            from ad_process_para p, ad_val_rule v, ad_column c, ad_table t, ad_tab tb" +
      "            where p.ad_val_rule_id is not null" +
      "            and t.ad_table_id = c.ad_table_id" +
      "            and c.ad_process_id = p.ad_process_id" +
      "            and v.ad_val_rule_id = p.ad_val_rule_id" +
      "            and code like '%@%'" +
      "            and t.ad_table_id = tb.ad_table_id" +
      "            and tb.ad_tab_id = ?" +
      "            and p.ad_process_id = ? " +
      "            and exists (select 1 " +
      "                          from ad_column c1, ad_field f" +
      "                        where t.ad_table_id = c1.ad_table_id" +
      "                          and upper(c1.columnname)=upper(substr(code, instr(code,'@')+1, instr(code,'@',1,2)-instr(code,'@')-1))" +
      "                          and f.ad_column_id = c1.ad_column_id)";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, tabId);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, processId);

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
        ActionButtonRelationData objectActionButtonRelationData = new ActionButtonRelationData();
        objectActionButtonRelationData.columnname = UtilSql.getValue(result, "columnname");
        objectActionButtonRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectActionButtonRelationData);
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
    ActionButtonRelationData objectActionButtonRelationData[] = new ActionButtonRelationData[vector.size()];
    vector.copyInto(objectActionButtonRelationData);
    return(objectActionButtonRelationData);
  }
}
