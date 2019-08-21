//Sqlc generated V1.O00-1
package org.openbravo.wad;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;

class WadUtilityData implements FieldProvider {
static Logger log4j = Logger.getLogger(WadUtilityData.class);
  private String InitRecordNumber="0";
  public String text;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("text"))
      return text;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

  public static String getReferenceClassName(ConnectionProvider connectionProvider, String subReference, String parentReference)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT COALESCE(R2.WAD_IMPL, R1.WAD_IMPL) AS TEXT" +
      "          FROM AD_REFERENCE R1 LEFT JOIN AD_REFERENCE R2 " +
      "                                 ON R2.PARENTREFERENCE_ID = R1.AD_REFERENCE_ID  " +
      "                                AND R2.AD_REFERENCE_ID = ? " +
      "         WHERE R1.AD_REFERENCE_ID = ?" +
      "           AND R1.ISBASEREFERENCE = 'Y'";

    ResultSet result;
    String strReturn = null;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, subReference);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, parentReference);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "text");
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
