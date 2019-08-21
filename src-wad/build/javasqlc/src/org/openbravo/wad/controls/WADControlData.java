//Sqlc generated V1.O00-1
package org.openbravo.wad.controls;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;

class WADControlData implements FieldProvider {
static Logger log4j = Logger.getLogger(WADControlData.class);
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

  public static String getMessage(ConnectionProvider connectionProvider, String adLanguage, String value)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        SELECT COALESCE(mt.msgtext, m.msgtext) as text " +
      "        from ad_message m left join ad_message_trl mt on m.ad_message_id = mt.ad_message_id " +
      "                            and mt.ad_language = ?" +
      "        where m.value = ?";

    ResultSet result;
    String strReturn = "";
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adLanguage);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, value);

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
