//Sqlc generated V1.O00-1
package org.openbravo.wad;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;
import java.util.*;

class TableLinkData implements FieldProvider {
static Logger log4j = Logger.getLogger(TableLinkData.class);
  private String InitRecordNumber="0";
  public String adWindowId;
  public String poWindowId;
  public String windowname;
  public String tabname;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("ad_window_id") || fieldName.equals("adWindowId"))
      return adWindowId;
    else if (fieldName.equalsIgnoreCase("po_window_id") || fieldName.equals("poWindowId"))
      return poWindowId;
    else if (fieldName.equalsIgnoreCase("windowname"))
      return windowname;
    else if (fieldName.equalsIgnoreCase("tabname"))
      return tabname;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

/**
Names of the columns and name of the fields of a tab
 */
  public static TableLinkData[] select(ConnectionProvider connectionProvider, String adWindowId, String adTableId)    throws ServletException {
    return select(connectionProvider, adWindowId, adTableId, 0, 0);
  }

/**
Names of the columns and name of the fields of a tab
 */
  public static TableLinkData[] select(ConnectionProvider connectionProvider, String adWindowId, String adTableId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT W.AD_WINDOW_ID, '' AS PO_WINDOW_ID, W.NAME AS WINDOWNAME, T.NAME AS TABNAME " +
      "      FROM AD_WINDOW W, AD_TAB T " +
      "      WHERE W.AD_WINDOW_ID = T.AD_WINDOW_ID " +
      "      AND W.ISACTIVE = 'Y' " +
      "      AND T.ISACTIVE = 'Y' " +
      "      AND W.AD_WINDOW_ID = ? " +
      "      AND T.AD_TABLE_ID = ? ";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adWindowId);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adTableId);

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
        TableLinkData objectTableLinkData = new TableLinkData();
        objectTableLinkData.adWindowId = UtilSql.getValue(result, "ad_window_id");
        objectTableLinkData.poWindowId = UtilSql.getValue(result, "po_window_id");
        objectTableLinkData.windowname = UtilSql.getValue(result, "windowname");
        objectTableLinkData.tabname = UtilSql.getValue(result, "tabname");
        objectTableLinkData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectTableLinkData);
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
    TableLinkData objectTableLinkData[] = new TableLinkData[vector.size()];
    vector.copyInto(objectTableLinkData);
    return(objectTableLinkData);
  }

/**
Names of the columns and name of the fields of a tab
 */
  public static TableLinkData[] selectParent(ConnectionProvider connectionProvider, String adWindowId)    throws ServletException {
    return selectParent(connectionProvider, adWindowId, 0, 0);
  }

/**
Names of the columns and name of the fields of a tab
 */
  public static TableLinkData[] selectParent(ConnectionProvider connectionProvider, String adWindowId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT W.AD_WINDOW_ID, '' AS PO_WINDOW_ID, W.NAME AS WINDOWNAME, T.NAME AS TABNAME " +
      "      FROM AD_WINDOW W, AD_TAB T " +
      "      WHERE W.AD_WINDOW_ID = T.AD_WINDOW_ID " +
      "      AND W.ISACTIVE = 'Y' " +
      "      AND T.ISACTIVE = 'Y' " +
      "      AND T.tablevel = 0 " +
      "      AND W.AD_WINDOW_ID = ?" +
      "      ORDER BY T.SEQNO ";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adWindowId);

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
        TableLinkData objectTableLinkData = new TableLinkData();
        objectTableLinkData.adWindowId = UtilSql.getValue(result, "ad_window_id");
        objectTableLinkData.poWindowId = UtilSql.getValue(result, "po_window_id");
        objectTableLinkData.windowname = UtilSql.getValue(result, "windowname");
        objectTableLinkData.tabname = UtilSql.getValue(result, "tabname");
        objectTableLinkData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectTableLinkData);
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
    TableLinkData objectTableLinkData[] = new TableLinkData[vector.size()];
    vector.copyInto(objectTableLinkData);
    return(objectTableLinkData);
  }

/**
Names of the columns and name of the fields of a tab
 */
  public static TableLinkData[] selectWindow(ConnectionProvider connectionProvider, String adTableId)    throws ServletException {
    return selectWindow(connectionProvider, adTableId, 0, 0);
  }

/**
Names of the columns and name of the fields of a tab
 */
  public static TableLinkData[] selectWindow(ConnectionProvider connectionProvider, String adTableId, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT AD_WINDOW_ID, PO_WINDOW_ID " +
      "      FROM AD_TABLE " +
      "      WHERE AD_TABLE_ID=?";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adTableId);

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
        TableLinkData objectTableLinkData = new TableLinkData();
        objectTableLinkData.adWindowId = UtilSql.getValue(result, "ad_window_id");
        objectTableLinkData.poWindowId = UtilSql.getValue(result, "po_window_id");
        objectTableLinkData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectTableLinkData);
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
    TableLinkData objectTableLinkData[] = new TableLinkData[vector.size()];
    vector.copyInto(objectTableLinkData);
    return(objectTableLinkData);
  }

  public static String tableId(ConnectionProvider connectionProvider, String adColumnId)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT AD_TABLE_ID " +
      "      FROM AD_COLUMN" +
      "      WHERE AD_COLUMN_ID = ?";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adColumnId);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "ad_table_id");
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
Names of the columns and name of the fields of a tab
 */
  public static String tableNameId(ConnectionProvider connectionProvider, String tablename)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT AD_TABLE_ID " +
      "      FROM AD_TABLE " +
      "      WHERE UPPER(TABLENAME) = UPPER(?)" +
      "      AND ISACTIVE = 'Y'";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, tablename);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "ad_table_id");
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
Names of the columns and name of the fields of a tab
 */
  public static String columnName(ConnectionProvider connectionProvider, String adColumnId)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT C.COLUMNNAME " +
      "      FROM AD_COLUMN C " +
      "      WHERE C.AD_COLUMN_ID = ? ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adColumnId);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "columnname");
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
Names of the columns and name of the fields of a tab
 */
  public static String hasTree(ConnectionProvider connectionProvider, String adTabId)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT HASTREE FROM AD_TAB WHERE AD_TAB_ID=?";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adTabId);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "hastree");
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
