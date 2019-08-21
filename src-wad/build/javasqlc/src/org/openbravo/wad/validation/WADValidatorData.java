//Sqlc generated V1.O00-1
package org.openbravo.wad.validation;

import java.sql.*;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.openbravo.data.FieldProvider;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.data.UtilSql;
import java.util.*;

class WADValidatorData implements FieldProvider {
static Logger log4j = Logger.getLogger(WADValidatorData.class);
  private String InitRecordNumber="0";
  public String objectname;
  public String modulename;
  public String moduleid;
  public String objecttype;
  public String currentvalue;
  public String expectedvalue;
  public String fieldname;
  public String tabid;
  public String tabname;
  public String windowname;
  public String columnname;
  public String columnid;

  public String getInitRecordNumber() {
    return InitRecordNumber;
  }

  public String getField(String fieldName) {
    if (fieldName.equalsIgnoreCase("objectname"))
      return objectname;
    else if (fieldName.equalsIgnoreCase("modulename"))
      return modulename;
    else if (fieldName.equalsIgnoreCase("moduleid"))
      return moduleid;
    else if (fieldName.equals("objecttype"))
      return objecttype;
    else if (fieldName.equals("currentvalue"))
      return currentvalue;
    else if (fieldName.equals("expectedvalue"))
      return expectedvalue;
    else if (fieldName.equals("fieldname"))
      return fieldname;
    else if (fieldName.equals("tabid"))
      return tabid;
    else if (fieldName.equals("tabname"))
      return tabname;
    else if (fieldName.equals("windowname"))
      return windowname;
    else if (fieldName.equals("columnname"))
      return columnname;
    else if (fieldName.equals("columnid"))
      return columnid;
   else {
     log4j.debug("Field does not exist: " + fieldName);
     return null;
   }
 }

  public static WADValidatorData[] checkIdentifier(ConnectionProvider connectionProvider, String module, String checkAll)    throws ServletException {
    return checkIdentifier(connectionProvider, module, checkAll, 0, 0);
  }

  public static WADValidatorData[] checkIdentifier(ConnectionProvider connectionProvider, String module, String checkAll, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "          select t.tablename as objectName, m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_table t, ad_module m, ad_package p" +
      "         where not exists (select 1" +
      "                             from ad_column c" +
      "                            where c.ad_table_id = t.ad_table_id" +
      "                              and c.isidentifier = 'Y')" +
      "           and t.ad_package_id = p.ad_package_id" +
      "           and m.ad_module_id = p.ad_module_id" +
      "           and (m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);

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
        WADValidatorData objectWADValidatorData = new WADValidatorData();
        objectWADValidatorData.objectname = UtilSql.getValue(result, "objectname");
        objectWADValidatorData.modulename = UtilSql.getValue(result, "modulename");
        objectWADValidatorData.moduleid = UtilSql.getValue(result, "moduleid");
        objectWADValidatorData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectWADValidatorData);
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
    WADValidatorData objectWADValidatorData[] = new WADValidatorData[vector.size()];
    vector.copyInto(objectWADValidatorData);
    return(objectWADValidatorData);
  }

  public static WADValidatorData[] checkKey(ConnectionProvider connectionProvider, String module, String checkAll)    throws ServletException {
    return checkKey(connectionProvider, module, checkAll, 0, 0);
  }

  public static WADValidatorData[] checkKey(ConnectionProvider connectionProvider, String module, String checkAll, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        select t.tablename as objectName, m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_table t, ad_module m, ad_package p" +
      "         where not exists (select 1" +
      "                             from ad_column c" +
      "                            where c.ad_table_id = t.ad_table_id" +
      "                              and c.iskey = 'Y')" +
      "           and t.ad_package_id = p.ad_package_id" +
      "           and m.ad_module_id = p.ad_module_id" +
      "           and t.isview='N'" +
      "           and (m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);

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
        WADValidatorData objectWADValidatorData = new WADValidatorData();
        objectWADValidatorData.objectname = UtilSql.getValue(result, "objectname");
        objectWADValidatorData.modulename = UtilSql.getValue(result, "modulename");
        objectWADValidatorData.moduleid = UtilSql.getValue(result, "moduleid");
        objectWADValidatorData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectWADValidatorData);
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
    WADValidatorData objectWADValidatorData[] = new WADValidatorData[vector.size()];
    vector.copyInto(objectWADValidatorData);
    return(objectWADValidatorData);
  }

  public static WADValidatorData[] checkMultipleKey(ConnectionProvider connectionProvider, String module, String checkAll)    throws ServletException {
    return checkMultipleKey(connectionProvider, module, checkAll, 0, 0);
  }

  public static WADValidatorData[] checkMultipleKey(ConnectionProvider connectionProvider, String module, String checkAll, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        select t.tablename as objectName, m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_table t, ad_module m, ad_package p" +
      "         where exists (select 1" +
      "                         from ad_column c" +
      "                        where c.ad_table_id = t.ad_table_id" +
      "                          and c.iskey = 'Y'" +
      "                          group by ad_table_id" +
      "                          having count(*)>1)" +
      "           and t.ad_package_id = p.ad_package_id" +
      "           and m.ad_module_id = p.ad_module_id" +
      "           and t.isview='N'" +
      "           and (m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);

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
        WADValidatorData objectWADValidatorData = new WADValidatorData();
        objectWADValidatorData.objectname = UtilSql.getValue(result, "objectname");
        objectWADValidatorData.modulename = UtilSql.getValue(result, "modulename");
        objectWADValidatorData.moduleid = UtilSql.getValue(result, "moduleid");
        objectWADValidatorData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectWADValidatorData);
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
    WADValidatorData objectWADValidatorData[] = new WADValidatorData[vector.size()];
    vector.copyInto(objectWADValidatorData);
    return(objectWADValidatorData);
  }

  public static WADValidatorData[] checkModelObject(ConnectionProvider connectionProvider, String module, String checkAll)    throws ServletException {
    return checkModelObject(connectionProvider, module, checkAll, 0, 0);
  }

  public static WADValidatorData[] checkModelObject(ConnectionProvider connectionProvider, String module, String checkAll, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        select to_char(p.name) as objectname, 'Process' as objectType, o.classname as currentvalue, m.javapackage as expectedvalue, m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_model_object o, ad_process p, ad_module m" +
      "         where o.ad_process_id = p.ad_process_id" +
      "           and p.ad_module_id = m.ad_module_id" +
      "           and o.classname not like m.javapackage||'.%'" +
      "           and (1=1 and m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "         union" +
      "        select to_char(f.name), 'Form' as objectType, o.classname as currentvalue, m.javapackage as expectedvalue , m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_model_object o, ad_form f, ad_module m" +
      "         where o.ad_form_id = f.ad_form_id" +
      "           and f.ad_module_id = m.ad_module_id" +
      "           and o.classname not like m.javapackage||'.%'" +
      "           and (2=2 and m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "         union" +
      "        select to_char(c.name), 'Callout' as objectType, o.classname as currentvalue, m.javapackage as expectedvalue , m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_model_object o, ad_callout c, ad_module m" +
      "         where o.ad_callout_id = c.ad_callout_id" +
      "           and c.ad_module_id = m.ad_module_id" +
      "           and o.classname not like m.javapackage||'.%'" +
      "           and (3=3 and m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "         union" +
      "        select to_char(r.name), 'Reference' as objectType, o.classname as currentvalue, m.javapackage as expectedvalue , m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_model_object o, ad_reference r, ad_module m" +
      "         where o.ad_reference_id = r.ad_reference_id" +
      "           and r.ad_module_id = m.ad_module_id" +
      "           and o.classname not like m.javapackage||'.%'" +
      "           and (4=4 and m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "         union" +
      "        select  to_char(w.name||' >> '||t.name), 'Tab' as objectType, o.classname as currentvalue, (case when m2.ad_module_id='0' then 'org.openbravo.erpWindows.*' else 'org.openbravo.erpWindows.'||m2.javapackage||'.%' end) as expectedvalue, m.name as moduleName, m.ad_module_id as moduleId " +
      "          from ad_model_object o, ad_window w, ad_tab t, ad_module m, ad_module m2" +
      "         where o.ad_tab_id = t.ad_tab_id" +
      "           and t.ad_window_id = w.ad_window_id" +
      "           and t.ad_module_id = m.ad_module_id" +
      "           and w.ad_module_id = m2.ad_module_id" +
      "           and o.classname not like (case when m2.ad_module_id='0' then 'org.openbravo.erpWindows.%' else 'org.openbravo.erpWindows.'||m2.javapackage||'.%' end)" +
      "           and o.classname not like 'org.openbravo.erpCommon.ad_callouts.ComboReloads%'" +
      "           and (5=5 and m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "         order by 4, 2, 1";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);

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
        WADValidatorData objectWADValidatorData = new WADValidatorData();
        objectWADValidatorData.objectname = UtilSql.getValue(result, "objectname");
        objectWADValidatorData.objecttype = UtilSql.getValue(result, "objecttype");
        objectWADValidatorData.currentvalue = UtilSql.getValue(result, "currentvalue");
        objectWADValidatorData.expectedvalue = UtilSql.getValue(result, "expectedvalue");
        objectWADValidatorData.modulename = UtilSql.getValue(result, "modulename");
        objectWADValidatorData.moduleid = UtilSql.getValue(result, "moduleid");
        objectWADValidatorData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectWADValidatorData);
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
    WADValidatorData objectWADValidatorData[] = new WADValidatorData[vector.size()];
    vector.copyInto(objectWADValidatorData);
    return(objectWADValidatorData);
  }

  public static WADValidatorData[] checkModelObjectMapping(ConnectionProvider connectionProvider, String module, String checkAll)    throws ServletException {
    return checkModelObjectMapping(connectionProvider, module, checkAll, 0, 0);
  }

  public static WADValidatorData[] checkModelObjectMapping(ConnectionProvider connectionProvider, String module, String checkAll, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        select to_char(p.name) as objectname, 'Process' as objectType, om.mappingname as currentvalue, m.javapackage as expectedvalue , m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_model_object o, ad_process p, ad_module m, ad_model_object_mapping om" +
      "         where o.ad_process_id = p.ad_process_id" +
      "           and p.ad_module_id = m.ad_module_id" +
      "           and om.ad_model_object_id = o.ad_model_object_id" +
      "           and om.mappingname not like '/'||m.javapackage||'.%'" +
      "           and om.mappingname not like '/'||m.javapackage||'/%'" +
      "           and m.ad_module_id !='0'" +
      "           and (1=1 and m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "         union" +
      "        select to_char(f.name), 'Form' as objectType, om.mappingname as currentvalue, m.javapackage as expectedvalue , m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_model_object o, ad_form f, ad_module m, ad_model_object_mapping om" +
      "         where o.ad_form_id = f.ad_form_id" +
      "           and f.ad_module_id = m.ad_module_id" +
      "           and om.ad_model_object_id = o.ad_model_object_id" +
      "           and om.mappingname not like '/'||m.javapackage||'.%'" +
      "           and om.mappingname not like '/'||m.javapackage||'/%'" +
      "           and m.ad_module_id !='0'" +
      "           and (2=2 and m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "         union" +
      "        select to_char(c.name), 'Callout' as objectType, om.mappingname as currentvalue, m.javapackage as expectedvalue , m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_model_object o, ad_callout c, ad_module m, ad_model_object_mapping om" +
      "         where o.ad_callout_id = c.ad_callout_id" +
      "           and c.ad_module_id = m.ad_module_id" +
      "           and om.ad_model_object_id = o.ad_model_object_id" +
      "           and om.mappingname not like '/'||m.javapackage||'.%'" +
      "           and om.mappingname not like '/'||m.javapackage||'/%'" +
      "           and m.ad_module_id !='0'" +
      "           and (3=3 and m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "         union" +
      "        select to_char(r.name), 'Reference' as objectType, om.mappingname as currentvalue, m.javapackage as expectedvalue , m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_model_object o, ad_reference r, ad_module m, ad_model_object_mapping om" +
      "         where o.ad_reference_id = r.ad_reference_id" +
      "           and r.ad_module_id = m.ad_module_id" +
      "           and om.ad_model_object_id = o.ad_model_object_id" +
      "           and om.mappingname not like '/'||m.javapackage||'.%'" +
      "           and om.mappingname not like '/'||m.javapackage||'/%'" +
      "           and m.ad_module_id !='0'" +
      "           and (4=4 and m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "         union" +
      "        select to_char(w.name||' >> '||t.name), 'Tab' as objectType, om.mappingname as currentvalue, m.javapackage as expectedvalue , m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_model_object o, ad_window w, ad_tab t, ad_module m, ad_model_object_mapping om" +
      "         where o.ad_tab_id = t.ad_tab_id" +
      "           and t.ad_window_id = w.ad_window_id" +
      "           and t.ad_module_id = m.ad_module_id" +
      "           and w.ad_module_id = t.ad_module_id" +
      "           and om.ad_model_object_id = o.ad_model_object_id" +
      "           and om.mappingname not like '/'||m.javapackage||'.%'" +
      "           and om.mappingname not like '/'||m.javapackage||'/%'" +
      "           and om.mappingname not like '/ad_callouts/ComboReloads%'" +
      "           and m.ad_module_id !='0'" +
      "           and (5=5 and m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "         order by 4, 2, 1";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);

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
        WADValidatorData objectWADValidatorData = new WADValidatorData();
        objectWADValidatorData.objectname = UtilSql.getValue(result, "objectname");
        objectWADValidatorData.objecttype = UtilSql.getValue(result, "objecttype");
        objectWADValidatorData.currentvalue = UtilSql.getValue(result, "currentvalue");
        objectWADValidatorData.expectedvalue = UtilSql.getValue(result, "expectedvalue");
        objectWADValidatorData.modulename = UtilSql.getValue(result, "modulename");
        objectWADValidatorData.moduleid = UtilSql.getValue(result, "moduleid");
        objectWADValidatorData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectWADValidatorData);
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
    WADValidatorData objectWADValidatorData[] = new WADValidatorData[vector.size()];
    vector.copyInto(objectWADValidatorData);
    return(objectWADValidatorData);
  }

  public static WADValidatorData[] checkColumnName(ConnectionProvider connectionProvider, String module, String checkAll)    throws ServletException {
    return checkColumnName(connectionProvider, module, checkAll, 0, 0);
  }

  public static WADValidatorData[] checkColumnName(ConnectionProvider connectionProvider, String module, String checkAll, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "           select t.tablename||'.'||c.columnname as objectName, 'DB Column Name' as objectType, c.columnname as currentValue, " +
      "           (select max(name) " +
      "              from AD_Module_DBPrefix dbp" +
      "             where dbp.AD_Module_ID = c.AD_Module_ID) as expectedvalue, " +
      "           m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_table t, ad_module m, ad_package p, ad_column c" +
      "         where t.ad_package_id = p.ad_package_id" +
      "           and t.ad_table_id = c.ad_table_id" +
      "           and c.ad_module_id != p.ad_module_id" +
      "           and m.ad_module_id = c.ad_module_id" +
      "           and not exists (select 1 " +
      "                             from ad_module_dbprefix dbp" +
      "                            where instr(upper(c.columnname), 'EM_'||upper(dbp.name)||'_') = 1" +
      "                              and dbp.ad_module_id = c.ad_module_id)" +
      "           AND NOT EXISTS( SELECT 1" +
      "                             FROM AD_EXCEPTIONS" +
      "                             WHERE TYPE='COLUMN'" +
      "                             AND UPPER(NAME2)=UPPER(T.Tablename)" +
      "                             AND UPPER(NAME1)=UPPER(c.Columnname)) " +
      "           and (1=1 and m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "    union" +
      "        select t.tablename||'.'||c.columnname as objectName, 'Name' as objectType, to_char(c.name) as currentValue, " +
      "        (select max(name) " +
      "              from AD_Module_DBPrefix dbp" +
      "             where dbp.AD_Module_ID = c.AD_Module_ID) as expectedvalue, " +
      "        m.name as moduleName, m.ad_module_id as moduleId" +
      "          from ad_table t, ad_module m, ad_package p, ad_column c" +
      "         where t.ad_package_id = p.ad_package_id" +
      "           and t.ad_table_id = c.ad_table_id" +
      "           and c.ad_module_id != p.ad_module_id" +
      "           and m.ad_module_id = c.ad_module_id" +
      "           and not exists (select 1 " +
      "                             from ad_module_dbprefix dbp" +
      "                            where instr(upper(c.name), 'EM_'||upper(dbp.name)||'_') = 1" +
      "                              and dbp.ad_module_id = c.ad_module_id)" +
      "           AND NOT EXISTS( SELECT 1" +
      "                             FROM AD_EXCEPTIONS" +
      "                             WHERE TYPE='COLUMN'" +
      "                             AND UPPER(NAME2)=UPPER(T.Tablename)" +
      "                             AND UPPER(NAME1)=UPPER(c.Columnname)) " +
      "           and (2=2 and m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "          order by 2,1";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);

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
        WADValidatorData objectWADValidatorData = new WADValidatorData();
        objectWADValidatorData.objectname = UtilSql.getValue(result, "objectname");
        objectWADValidatorData.objecttype = UtilSql.getValue(result, "objecttype");
        objectWADValidatorData.currentvalue = UtilSql.getValue(result, "currentvalue");
        objectWADValidatorData.expectedvalue = UtilSql.getValue(result, "expectedvalue");
        objectWADValidatorData.modulename = UtilSql.getValue(result, "modulename");
        objectWADValidatorData.moduleid = UtilSql.getValue(result, "moduleid");
        objectWADValidatorData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectWADValidatorData);
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
    WADValidatorData objectWADValidatorData[] = new WADValidatorData[vector.size()];
    vector.copyInto(objectWADValidatorData);
    return(objectWADValidatorData);
  }

  public static WADValidatorData[] checkAuxiliarInput(ConnectionProvider connectionProvider, String module, String checkAll)    throws ServletException {
    return checkAuxiliarInput(connectionProvider, module, checkAll, 0, 0);
  }

  public static WADValidatorData[] checkAuxiliarInput(ConnectionProvider connectionProvider, String module, String checkAll, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        select i.name as objectName, (select max(name) " +
      "                                   from AD_Module_DBPrefix p" +
      "                                  where p.AD_Module_ID = i.AD_Module_ID) as expectedvalue" +
      "        from ad_auxiliarInput i, AD_Module m, AD_Tab t" +
      "        where i.ad_module_id != '0'" +
      "        and m.ad_module_id = i.ad_module_id" +
      "        and t.ad_tab_id = i.ad_tab_id" +
      "        and t.ad_module_id <> i.ad_module_id" +
      "        and not exists (select 1 " +
      "                     from AD_Module_DBPrefix p" +
      "                    where p.AD_Module_ID = i.AD_Module_ID" +
      "                      and instr(upper(i.NAME), upper(p.name)||'_') = 1)" +
      "        and (m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);

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
        WADValidatorData objectWADValidatorData = new WADValidatorData();
        objectWADValidatorData.objectname = UtilSql.getValue(result, "objectname");
        objectWADValidatorData.expectedvalue = UtilSql.getValue(result, "expectedvalue");
        objectWADValidatorData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectWADValidatorData);
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
    WADValidatorData objectWADValidatorData[] = new WADValidatorData[vector.size()];
    vector.copyInto(objectWADValidatorData);
    return(objectWADValidatorData);
  }

  public static WADValidatorData[] checkBaseReferenceWithParent(ConnectionProvider connectionProvider, String module, String checkAll)    throws ServletException {
    return checkBaseReferenceWithParent(connectionProvider, module, checkAll, 0, 0);
  }

  public static WADValidatorData[] checkBaseReferenceWithParent(ConnectionProvider connectionProvider, String module, String checkAll, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "        select r.name as objectName, m.name as moduleName, r2.name as currentvalue, m.ad_module_id as moduleId" +
      "          from ad_reference r, ad_module m, ad_reference r2" +
      "         where r.isBaseReference = 'Y'" +
      "           and r.parentReference_ID is not null" +
      "           and r2.ad_reference_id = r.parentReference_ID" +
      "           and m.ad_module_id = r.ad_module_id" +
      "           and (m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);

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
        WADValidatorData objectWADValidatorData = new WADValidatorData();
        objectWADValidatorData.objectname = UtilSql.getValue(result, "objectname");
        objectWADValidatorData.modulename = UtilSql.getValue(result, "modulename");
        objectWADValidatorData.currentvalue = UtilSql.getValue(result, "currentvalue");
        objectWADValidatorData.moduleid = UtilSql.getValue(result, "moduleid");
        objectWADValidatorData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectWADValidatorData);
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
    WADValidatorData objectWADValidatorData[] = new WADValidatorData[vector.size()];
    vector.copyInto(objectWADValidatorData);
    return(objectWADValidatorData);
  }

  public static WADValidatorData[] checkProcessClasses(ConnectionProvider connectionProvider, String module, String checkAll)    throws ServletException {
    return checkProcessClasses(connectionProvider, module, checkAll, 0, 0);
  }

  public static WADValidatorData[] checkProcessClasses(ConnectionProvider connectionProvider, String module, String checkAll, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "       select p.name as objectName, m.name as moduleName, m.ad_module_id as moduleId" +
      "         from ad_process p, ad_module m" +
      "        where p.isactive='Y' " +
      "          and p.UIPattern = 'S'" +
      "          and (p.IsJasper = 'N' AND p.PROCEDURENAME IS NULL)" +
      "          and m.ad_module_id = p.ad_module_id" +
      "          and (m.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "          and not exists (select 1  " +
      "                         from ad_model_object mo " +
      "                         where p.ad_process_id = mo.ad_process_id " +
      "                          and mo.isactive = 'Y' " +
      "                          and mo.action = 'P' " +
      "                          and mo.isdefault = 'Y')";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);

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
        WADValidatorData objectWADValidatorData = new WADValidatorData();
        objectWADValidatorData.objectname = UtilSql.getValue(result, "objectname");
        objectWADValidatorData.modulename = UtilSql.getValue(result, "modulename");
        objectWADValidatorData.moduleid = UtilSql.getValue(result, "moduleid");
        objectWADValidatorData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectWADValidatorData);
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
    WADValidatorData objectWADValidatorData[] = new WADValidatorData[vector.size()];
    vector.copyInto(objectWADValidatorData);
    return(objectWADValidatorData);
  }

  public static WADValidatorData[] checkTabsWithMultipleFieldsForSameColumn(ConnectionProvider connectionProvider, String module, String checkAll)    throws ServletException {
    return checkTabsWithMultipleFieldsForSameColumn(connectionProvider, module, checkAll, 0, 0);
  }

  public static WADValidatorData[] checkTabsWithMultipleFieldsForSameColumn(ConnectionProvider connectionProvider, String module, String checkAll, int firstRegister, int numberRegisters)    throws ServletException {
    String strSql = "";
    strSql = strSql + 
      "      select ad_tab.ad_tab_id as tabid, ad_column.ad_column_id as columnid, ad_window.name as windowname, ad_tab.name as tabname," +
      "             ad_field.name as fieldname,ad_column.name as columnname, ad_field.ad_module_id as moduleid, ad_module.name as modulename" +
      "      from ad_window, ad_tab, ad_field, ad_column, ad_module" +
      "      where ad_field.ad_column_id = ad_column.ad_column_id" +
      "      and ad_field.ad_module_id = ad_module.ad_module_id" +
      "      and ad_tab.ad_tab_id = ad_field.ad_tab_id" +
      "      and ad_window.ad_window_id = ad_tab.ad_window_id" +
      "      and ad_field.property is null" +
      "      and (ad_module.javapackage in (";
    strSql = strSql + ((module==null || module.equals(""))?"":module);
    strSql = strSql + 
      ") or to_char('Y')=to_char(?))" +
      "      and exists (select * from ad_field subFld where" +
      "            subFld.ad_tab_id = ad_field.ad_tab_id" +
      "            and subFld.ad_column_id = ad_field.ad_column_id" +
      "            and subFld.property is null" +
      "            and ad_field.ad_field_id <> subFld.ad_field_id)" +
      "      order by ad_tab.ad_tab_id, ad_column.ad_column_id";

    ResultSet result;
    Vector<java.lang.Object> vector = new Vector<java.lang.Object>(0);
    PreparedStatement st = null;

    int iParameter = 0;
    try {
    st = connectionProvider.getPreparedStatement(strSql);
      if (module != null && !(module.equals(""))) {
        }
      iParameter++; UtilSql.setValue(st, iParameter, 12, null, checkAll);

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
        WADValidatorData objectWADValidatorData = new WADValidatorData();
        objectWADValidatorData.tabid = UtilSql.getValue(result, "tabid");
        objectWADValidatorData.columnid = UtilSql.getValue(result, "columnid");
        objectWADValidatorData.windowname = UtilSql.getValue(result, "windowname");
        objectWADValidatorData.tabname = UtilSql.getValue(result, "tabname");
        objectWADValidatorData.fieldname = UtilSql.getValue(result, "fieldname");
        objectWADValidatorData.columnname = UtilSql.getValue(result, "columnname");
        objectWADValidatorData.moduleid = UtilSql.getValue(result, "moduleid");
        objectWADValidatorData.modulename = UtilSql.getValue(result, "modulename");
        objectWADValidatorData.InitRecordNumber = Integer.toString(firstRegister);
        vector.addElement(objectWADValidatorData);
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
    WADValidatorData objectWADValidatorData[] = new WADValidatorData[vector.size()];
    vector.copyInto(objectWADValidatorData);
    return(objectWADValidatorData);
  }
}
