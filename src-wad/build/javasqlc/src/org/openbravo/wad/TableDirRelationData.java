//Sqlc generated V1.O00-1
package org.openbravo.wad;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;
import java.util.*;

class TableDirRelationData implements FieldProvider {
static Logger log4j = Logger.getLogger(TableDirRelationData.class);
  private String InitRecordNumber="0";
  public String columnname;
  public String tablename;
  public String columndescriptionname;
  public String code;
  public String adValRuleId;
  public String clause;
  public String parameters;
  public String referenceId;
  public String fromclause;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("columnname"))
      return columnname;
    else if (fieldName.equalsIgnoreCase("tablename"))
      return tablename;
    else if (fieldName.equalsIgnoreCase("columndescriptionname"))
      return columndescriptionname;
    else if (fieldName.equalsIgnoreCase("code"))
      return code;
    else if (fieldName.equalsIgnoreCase("ad_val_rule_id") || fieldName.equals("adValRuleId"))
      return adValRuleId;
    else if (fieldName.equalsIgnoreCase("clause"))
      return clause;
    else if (fieldName.equalsIgnoreCase("parameters"))
      return parameters;
    else if (fieldName.equalsIgnoreCase("reference_id") || fieldName.equals("referenceId"))
      return referenceId;
    else if (fieldName.equalsIgnoreCase("fromclause"))
      return fromclause;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

/**
The table dir references
 */
  public static TableDirRelationData[] select(ConnectionProvider connectionProvider)    throws ServletException {
    return select(connectionProvider, 0, 0);
  }

/**
The table dir references
 */
  public static TableDirRelationData[] select(ConnectionProvider connectionProvider, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT columnname, '' as tablename, '' as columndescriptionname, '' as code, '' as AD_VAL_RULE_ID, " +
      "      '' as clause, '' as parameters, '' as reference_id, '' as fromclause" +
      "      FROM AD_column  " +
      "      WHERE AD_reference_id='19'" +
      "      AND columnname not in ('Node_ID')" +
      "        GROUP BY columnname";

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
        TableDirRelationData objectTableDirRelationData = new TableDirRelationData();
        objectTableDirRelationData.columnname = UtilSql.getValue(result, "columnname");
        objectTableDirRelationData.tablename = UtilSql.getValue(result, "tablename");
        objectTableDirRelationData.columndescriptionname = UtilSql.getValue(result, "columndescriptionname");
        objectTableDirRelationData.code = UtilSql.getValue(result, "code");
        objectTableDirRelationData.adValRuleId = UtilSql.getValue(result, "ad_val_rule_id");
        objectTableDirRelationData.clause = UtilSql.getValue(result, "clause");
        objectTableDirRelationData.parameters = UtilSql.getValue(result, "parameters");
        objectTableDirRelationData.referenceId = UtilSql.getValue(result, "reference_id");
        objectTableDirRelationData.fromclause = UtilSql.getValue(result, "fromclause");
        objectTableDirRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectTableDirRelationData);
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
    TableDirRelationData objectTableDirRelationData[] = new TableDirRelationData[vector.size()];
    vector.copyInto(objectTableDirRelationData);
    return(objectTableDirRelationData);
  }

/**
The table dir references
 */
  public static TableDirRelationData[] selectValidation(ConnectionProvider connectionProvider)    throws ServletException {
    return selectValidation(connectionProvider, 0, 0);
  }

/**
The table dir references
 */
  public static TableDirRelationData[] selectValidation(ConnectionProvider connectionProvider, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT DISTINCT A.COLUMNNAME, A.tablename, A.columndescriptionname, A.code, " +
      "        A.AD_VAL_RULE_ID, A.clause, A.PARAMETERS, A.reference_id, '' as fromclause " +
      "        FROM (" +
      "        SELECT DISTINCT c.COLUMNNAME, '' AS tablename, '' AS columndescriptionname, v.code, " +
      "        v.AD_VAL_RULE_ID, '' AS clause, '' AS PARAMETERS, '' AS reference_id, '' as fromclause " +
      "        FROM AD_COLUMN c, AD_VAL_RULE v, AD_REFERENCE r" +
      "        WHERE c.AD_VAL_RULE_ID = v.AD_VAL_RULE_ID " +
      "        AND c.AD_REFERENCE_ID = r.AD_REFERENCE_ID " +
      "        AND c.AD_VAL_RULE_ID IS NOT NULL " +
      "        AND r.AD_REFERENCE_ID = '19'" +
      "        UNION" +
      "        SELECT DISTINCT c.COLUMNNAME, '' AS tablename, '' AS columndescriptionname, v.code, " +
      "        v.AD_VAL_RULE_ID, '' AS clause, '' AS PARAMETERS, '' AS reference_id, '' as fromclause" +
      "        FROM AD_PROCESS_PARA c, AD_VAL_RULE v, AD_REFERENCE r" +
      "        WHERE c.AD_VAL_RULE_ID = v.AD_VAL_RULE_ID " +
      "        AND c.AD_REFERENCE_ID = r.AD_REFERENCE_ID " +
      "        AND c.AD_VAL_RULE_ID IS NOT NULL " +
      "        AND r.AD_REFERENCE_ID = '19') A";

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
        TableDirRelationData objectTableDirRelationData = new TableDirRelationData();
        objectTableDirRelationData.columnname = UtilSql.getValue(result, "columnname");
        objectTableDirRelationData.tablename = UtilSql.getValue(result, "tablename");
        objectTableDirRelationData.columndescriptionname = UtilSql.getValue(result, "columndescriptionname");
        objectTableDirRelationData.code = UtilSql.getValue(result, "code");
        objectTableDirRelationData.adValRuleId = UtilSql.getValue(result, "ad_val_rule_id");
        objectTableDirRelationData.clause = UtilSql.getValue(result, "clause");
        objectTableDirRelationData.parameters = UtilSql.getValue(result, "parameters");
        objectTableDirRelationData.referenceId = UtilSql.getValue(result, "reference_id");
        objectTableDirRelationData.fromclause = UtilSql.getValue(result, "fromclause");
        objectTableDirRelationData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectTableDirRelationData);
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
    TableDirRelationData objectTableDirRelationData[] = new TableDirRelationData[vector.size()];
    vector.copyInto(objectTableDirRelationData);
    return(objectTableDirRelationData);
  }
}
