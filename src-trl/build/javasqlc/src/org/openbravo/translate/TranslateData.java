//Sqlc generated V1.O00-1
package org.openbravo.translate;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;
import java.util.*;

class TranslateData implements FieldProvider {
static Logger log4j = Logger.getLogger(TranslateData.class);
  private String InitRecordNumber="0";
  public String adTextinterfacesId;
  public String tr;
  public String name;
  public String total;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("ad_textinterfaces_id") || fieldName.equals("adTextinterfacesId"))
      return adTextinterfacesId;
    else if (fieldName.equalsIgnoreCase("tr"))
      return tr;
    else if (fieldName.equalsIgnoreCase("name"))
      return name;
    else if (fieldName.equalsIgnoreCase("total"))
      return total;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

/**
Return the translation of a text
 */
  public static TranslateData[] select(ConnectionProvider connectionProvider, String string, String filename, String languajeFin)    throws ServletException {
    return select(connectionProvider, string, filename, languajeFin, 0, 0);
  }

/**
Return the translation of a text
 */
  public static TranslateData[] select(ConnectionProvider connectionProvider, String string, String filename, String languajeFin, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      select A.ad_textinterfaces_id, A.tr, '' AS NAME, '' AS TOTAL" +
      "        from (select tt.ad_textinterfaces_id AS ad_textinterfaces_id, trim(tt.text) AS tr, 1 AS o" +
      "                from ad_textinterfaces_trl tt" +
      "               where tt.ad_textinterfaces_id =  (select min(ad_textinterfaces_id)" +
      "                                                   from ad_textinterfaces t" +
      "                                                  where t.text     = ?" +
      "                                                    and t.filename = ?)" +
      "                 and tt.ad_language = ?" +
      "               union" +
      "              select tt.ad_textinterfaces_id AS id, trim(tt.text) AS tr, 2 AS o" +
      "                from ad_textinterfaces_trl tt" +
      "               where tt.ad_textinterfaces_id =  (select min(ad_textinterfaces_id)" +
      "                                                   from ad_textinterfaces t" +
      "                                                  where t.text =  ?" +
      "                                                    and t.filename is null)" +
      "                 and tt.ad_language = ?" +
      "               order by o) A" +
      "      group by A.ad_textinterfaces_id, A.tr, A.o" +
      "      order by A.o";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, string);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, filename);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, languajeFin);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, string);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, languajeFin);

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
        TranslateData objectTranslateData = new TranslateData();
        objectTranslateData.adTextinterfacesId = UtilSql.getValue(result, "ad_textinterfaces_id");
        objectTranslateData.tr = UtilSql.getValue(result, "tr");
        objectTranslateData.name = UtilSql.getValue(result, "name");
        objectTranslateData.total = UtilSql.getValue(result, "total");
        objectTranslateData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectTranslateData);
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
    TranslateData objectTranslateData[] = new TranslateData[vector.size()];
    vector.copyInto(objectTranslateData);
    return(objectTranslateData);
  }

/**
Return the translation of a text
 */
  public static int existsExpresionModFile(ConnectionProvider connectionProvider, String text, String filename, String moduleLang)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "         update ad_textinterfaces" +
      "           set isUsed = 'Y'" +
      "         where ad_textinterfaces_id = ( select min(A.ad_textinterfaces_id)" +
      "                                          from (select B.*" +
      "                                                from (select min(ad_textinterfaces_id) AS ad_textinterfaces_id, 1 AS o" +
      "                                                          from ad_textinterfaces t," +
      "                                                               ad_module m" +
      "                                                         where text     = ?" +
      "                                                           and filename = ?" +
      "                                                           and m.ad_module_id = t.ad_module_id" +
      "                                                           and m.ad_language = ?" +
      "                                                         order by o) B" +
      "                                                 where ad_textinterfaces_id is not null) A)";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, text);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, filename);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, moduleLang);

      updateCount = st.executeUpdate();
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
    return(updateCount);
  }

/**
Return the translation of a text
 */
  public static int existsExpresionNoModFile(ConnectionProvider connectionProvider, String text, String filename, String moduleLang)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "         update ad_textinterfaces" +
      "           set isUsed = 'Y'" +
      "         where ad_textinterfaces_id = ( select min(A.ad_textinterfaces_id)" +
      "                                          from (select B.*" +
      "                                                from (select min(t.ad_textinterfaces_id) AS ad_textinterfaces_id, 2 AS o" +
      "                                                          from ad_textinterfaces t, ad_textinterfaces_trl trl" +
      "                                                         where trl.text     = ?" +
      "                                                           and t.filename = ?" +
      "                                                           and trl.ad_textinterfaces_id = t.ad_textinterfaces_id" +
      "                                                           and trl.ad_language = ?" +
      "                                                         order by o) B" +
      "                                                 where ad_textinterfaces_id is not null) A)";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, text);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, filename);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, moduleLang);

      updateCount = st.executeUpdate();
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
    return(updateCount);
  }

/**
Return the translation of a text
 */
  public static int existsExpresionModNoFile(ConnectionProvider connectionProvider, String text, String moduleLang)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "         update ad_textinterfaces" +
      "           set isUsed = 'Y'" +
      "         where ad_textinterfaces_id = ( select min(A.ad_textinterfaces_id)" +
      "                                          from (select B.*" +
      "                                                from (select min(ad_textinterfaces_id) AS ad_textinterfaces_id, 3 AS o" +
      "                                                          from ad_textinterfaces t," +
      "                                                               ad_module m" +
      "                                                         where text = ?" +
      "                                                           and filename is null" +
      "                                                           and m.ad_module_id = t.ad_module_id" +
      "                                                           and m.ad_language = ?" +
      "                                                         order by o) B" +
      "                                                 where ad_textinterfaces_id is not null) A)";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, text);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, moduleLang);

      updateCount = st.executeUpdate();
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
    return(updateCount);
  }

/**
Return the translation of a text
 */
  public static int existsExpresionNoModNoFile(ConnectionProvider connectionProvider, String text, String moduleLang)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "         update ad_textinterfaces" +
      "           set isUsed = 'Y'" +
      "         where ad_textinterfaces_id = ( select min(A.ad_textinterfaces_id)" +
      "                                          from (select B.*" +
      "                                                from (select min(t.ad_textinterfaces_id) AS ad_textinterfaces_id, 4 AS o" +
      "                                                          from ad_textinterfaces t, ad_textinterfaces_trl trl" +
      "                                                         where trl.text = ?" +
      "                                                           and t.filename is null" +
      "                                                           and trl.ad_textinterfaces_id = t.ad_textinterfaces_id" +
      "                                                           and trl.ad_language = ?" +
      "                                                         order by o) B" +
      "                                                 where ad_textinterfaces_id is not null) A)";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, text);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, moduleLang);

      updateCount = st.executeUpdate();
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
    return(updateCount);
  }

/**
Return the translation of a text
 */
  public static String getModuleID(ConnectionProvider connectionProvider, String name)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT AD_MODULE_ID" +
      "        FROM AD_MODULE" +
      "       WHERE JavaPackage = ?";

    ResultSet result;
    String strReturn = null;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, name);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "ad_module_id");
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
Return the translation of a text
 */
  public static String getModuleLang(ConnectionProvider connectionProvider, String module_id)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT AD_LANGUAGE" +
      "        FROM AD_MODULE" +
      "       WHERE AD_MODULE_ID = ?";

    ResultSet result;
    String strReturn = null;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, module_id);

      result = st.executeQuery();
      if(result.next()) {
        strReturn = UtilSql.getValue(result, "ad_language");
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
Return the translation of a text
 */
  public static boolean isInDevelopmentModule(ConnectionProvider connectionProvider, String name)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT COUNT(*) AS TOTAL" +
      "        FROM AD_MODULE" +
      "       WHERE AD_MODULE_ID = ?" +
      "         AND ISINDEVELOPMENT = 'Y'";

    ResultSet result;
    boolean boolReturn = false;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, name);

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
Return the translation of a text
 */
  public static boolean isInDevelopmentModulePack(ConnectionProvider connectionProvider, String pack)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT COUNT(*) AS TOTAL" +
      "        FROM AD_MODULE" +
      "       WHERE JAVAPACKAGE = ?" +
      "         AND ISINDEVELOPMENT = 'Y'";

    ResultSet result;
    boolean boolReturn = false;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, pack);

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
Return the translation of a text
 */
  public static int insert(ConnectionProvider connectionProvider, String text, String filename, String module)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      insert into ad_textinterfaces" +
      "        (AD_TEXTINTERFACES_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, UPDATED, UPDATEDBY, TEXT, FILENAME, ISUSED, AD_MODULE_ID)" +
      "      values" +
      "        (get_uuid(),'0' ,'0', 'Y', now(), '0', now(), '0', ?, ?, 'Y', ?)";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, text);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, filename);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, module);

      updateCount = st.executeUpdate();
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
    return(updateCount);
  }

/**
Return the translation of a text
 */
  public static int update(ConnectionProvider connectionProvider, String adTextinterfacesId)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      update ad_textinterfaces t" +
      "         set isUsed='Y'" +
      "       where ad_textinterfaces_id = ?" +
      "         and exists (select 1 " +
      "                       from ad_module m" +
      "                      where m.ad_module_id = t.ad_module_id" +
      "                        and m.isInDevelopment = 'Y')";

    int updateCount = 0;
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, adTextinterfacesId);

      updateCount = st.executeUpdate();
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
    return(updateCount);
  }

/**
Return the translation of a text
 */
  public static TranslateData[] systemLanguage(ConnectionProvider connectionProvider)    throws ServletException {
    return systemLanguage(connectionProvider, 0, 0);
  }

/**
Return the translation of a text
 */
  public static TranslateData[] systemLanguage(ConnectionProvider connectionProvider, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      SELECT AD_LANGUAGE AS NAME FROM AD_LANGUAGE WHERE ISACTIVE='Y' AND ISSYSTEMLANGUAGE='Y' AND ISBASELANGUAGE='N'";

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
        TranslateData objectTranslateData = new TranslateData();
        objectTranslateData.name = UtilSql.getValue(result, "name");
        objectTranslateData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectTranslateData);
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
    TranslateData objectTranslateData[] = new TranslateData[vector.size()];
    vector.copyInto(objectTranslateData);
    return(objectTranslateData);
  }

/**
Return the translation of a text
 */
  public static int clean(ConnectionProvider connectionProvider)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      update ad_textinterfaces t" +
      "         set isUsed='N'" +
      "       where exists (select 1 " +
      "                       from ad_module m" +
      "                      where m.ad_module_id = t.ad_module_id" +
      "                        and m.isInDevelopment = 'Y')  ";

    int updateCount = 0;
    PreparedStatement st = null;

    try {
    st = connectionProvider.getPreparedStatement(strSql);

      updateCount = st.executeUpdate();
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
    return(updateCount);
  }

/**
Return the translation of a text
 */
  public static int remove(ConnectionProvider connectionProvider)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      delete from ad_textinterfaces t" +
      "       where isUsed='N'" +
      "         and exists (select 1 " +
      "                       from ad_module m" +
      "                      where m.ad_module_id = t.ad_module_id" +
      "                        and m.isInDevelopment = 'Y')  ";

    int updateCount = 0;
    PreparedStatement st = null;

    try {
    st = connectionProvider.getPreparedStatement(strSql);

      updateCount = st.executeUpdate();
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
    return(updateCount);
  }
}
