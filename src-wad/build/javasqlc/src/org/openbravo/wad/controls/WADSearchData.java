//Sqlc generated V1.O00-1
package org.openbravo.wad.controls;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;
import java.util.*;

class WADSearchData implements FieldProvider {
static Logger log4j = Logger.getLogger(WADSearchData.class);
  private String InitRecordNumber="0";
  public String referenceName;
  public String referenceNameTrl;
  public String mappingname;
  public String columntype;
  public String columnSuffix;
  public String columnname;
  public String name;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("reference_name") || fieldName.equals("referenceName"))
      return referenceName;
    else if (fieldName.equalsIgnoreCase("reference_name_trl") || fieldName.equals("referenceNameTrl"))
      return referenceNameTrl;
    else if (fieldName.equalsIgnoreCase("mappingname"))
      return mappingname;
    else if (fieldName.equalsIgnoreCase("columntype"))
      return columntype;
    else if (fieldName.equalsIgnoreCase("column_suffix") || fieldName.equals("columnSuffix"))
      return columnSuffix;
    else if (fieldName.equalsIgnoreCase("columnname"))
      return columnname;
    else if (fieldName.equalsIgnoreCase("name"))
      return name;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

/**
Get the translate of a text
 */
  public static WADSearchData[] select(ConnectionProvider connectionProvider, String adLanguage, String adReferenceId)    throws ServletException {
    return select(connectionProvider, adLanguage, adReferenceId, 0, 0);
  }

/**
Get the translate of a text
 */
  public static WADSearchData[] select(ConnectionProvider connectionProvider, String adLanguage, String adReferenceId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        select r.name as Reference_Name, coalesce(rt.name, r.name) AS Reference_Name_Trl, " +
      "        mom.MappingName, rfc.columntype, rfc.column_suffix, rfc.columnname, rfc.name " +
      "        from ad_reference r left join ad_reference_trl rt on r.ad_reference_id = rt.ad_reference_id " +
      "                                                          AND rt.ad_language = ?, " +
      "        ad_ref_search rf " +
      "                              left join ad_ref_search_column rfc on rf.ad_ref_search_id = rfc.ad_ref_search_id " +
      "                              left join ad_model_object mo on rf.ad_reference_id = mo.ad_reference_id " +
      "                                               AND mo.isactive = 'Y'" +
      "                                               AND mo.isdefault = 'Y'" +
      "                                               AND mo.action = 'S'" +
      "                              left join ad_model_object_mapping mom on mo.ad_model_object_id = mom.ad_model_object_id" +
      "                                        AND mom.isactive = 'Y'" +
      "                                        AND mom.isdefault = 'Y'" +
      "        where r.ad_reference_id = rf.ad_reference_id " +
      "        and r.ad_reference_id = ?" +
      "        order by rfc.columntype";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adLanguage);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adReferenceId);

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
        WADSearchData objectWADSearchData = new WADSearchData();
        objectWADSearchData.referenceName = UtilSql.getValue(result, "reference_name");
        objectWADSearchData.referenceNameTrl = UtilSql.getValue(result, "reference_name_trl");
        objectWADSearchData.mappingname = UtilSql.getValue(result, "mappingname");
        objectWADSearchData.columntype = UtilSql.getValue(result, "columntype");
        objectWADSearchData.columnSuffix = UtilSql.getValue(result, "column_suffix");
        objectWADSearchData.columnname = UtilSql.getValue(result, "columnname");
        objectWADSearchData.name = UtilSql.getValue(result, "name");
        objectWADSearchData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectWADSearchData);
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
    WADSearchData objectWADSearchData[] = new WADSearchData[vector.size()];
    vector.copyInto(objectWADSearchData);
    return(objectWADSearchData);
  }

  public static String getLinkColumn(ConnectionProvider connectionProvider, String tableName)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "       SELECT C.AD_COLUMN_ID as name" +
      "       FROM AD_TABLE T, AD_COLUMN C " +
      "       WHERE T.AD_TABLE_ID = C.AD_TABLE_ID " +
      "       AND C.ISKEY='Y'" +
      "       AND UPPER(T.TABLENAME) = UPPER(?) ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, tableName);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "name");
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
}
