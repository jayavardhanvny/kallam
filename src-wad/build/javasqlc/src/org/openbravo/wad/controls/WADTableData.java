//Sqlc generated V1.O00-1
package org.openbravo.wad.controls;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;

class WADTableData implements FieldProvider {
static Logger log4j = Logger.getLogger(WADTableData.class);
  private String InitRecordNumber="0";
  public String adColumnId;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("ad_column_id") || fieldName.equals("adColumnId"))
      return adColumnId;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

  public static String getLinkColumn(ConnectionProvider connectionProvider, String adReferenceId)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT C.AD_COLUMN_ID" +
      "      FROM AD_REF_TABLE R, AD_COLUMN C " +
      "      WHERE R.AD_KEY = C.AD_COLUMN_ID " +
      "      AND R.AD_REFERENCE_ID = ? ";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adReferenceId);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "ad_column_id");
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
