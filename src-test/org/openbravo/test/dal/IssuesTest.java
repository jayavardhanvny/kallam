/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html 
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License. 
 * The Original Code is Openbravo ERP. 
 * The Initial Developer of the Original Code is Openbravo SLU 
 * All portions are Copyright (C) 2009-2011 Openbravo SLU 
 * All Rights Reserved. 
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */

package org.openbravo.test.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;
import org.junit.Assert;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.base.model.Reference;
import org.openbravo.base.model.domaintype.LongDomainType;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.IdentifierProvider;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.core.DalThreadHandler;
import org.openbravo.dal.core.DalUtil;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.core.OBInterceptor;
import org.openbravo.dal.security.OrganizationStructureProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.dal.xml.XMLEntityConverter;
import org.openbravo.data.UtilSql;
import org.openbravo.model.ad.access.Role;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.datamodel.Table;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.process.ProcessInstance;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.system.HeartbeatLog;
import org.openbravo.model.ad.system.Language;
import org.openbravo.model.ad.ui.Form;
import org.openbravo.model.ad.ui.FormTrl;
import org.openbravo.model.ad.ui.Message;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Category;
import org.openbravo.model.common.businesspartner.Location;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.invoice.InvoiceLine;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.common.uom.UOM;
import org.openbravo.model.financialmgmt.payment.FIN_Payment;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.CallProcess;
import org.openbravo.service.db.CallStoredProcedure;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import org.openbravo.service.json.DataEntityQueryService;
import org.openbravo.service.json.DataToJsonConverter;
import org.openbravo.test.base.BaseTest;

/**
 * Tests the following issues:
 * 
 * - https://issues.openbravo.com/view.php?id=11461 When saving business object in S/C data level
 * then access level exception is thrown for the child object
 * 
 * - https://issues.openbravo.com/view.php?id=12202 OBQuery does not support list parameter
 * 
 * - https://issues.openbravo.com/view.php?id=12201 OBContext is not using system language as
 * default language
 * 
 * - https://issues.openbravo.com/view.php?id=12143 OBQuery class should add convenience method
 * uniqueResult similar to the OBCriteria class
 * 
 * - https://issues.openbravo.com/view.php?id=12497: Active property should have default value ==
 * true if no explicit default is defined
 * 
 * - https://issues.openbravo.com/view.php?id=12106: record identifier returned from dal uses ' ' as
 * separator of columns, but normal pl-version uses ' - '
 * 
 * - https://issues.openbravo.com/view.php?id=12702: Cycle in parent reference references then DAL
 * throws stack over flow error
 * 
 * - https://issues.openbravo.com/view.php?id=12853: OBQuery count not working with a query with
 * aliases
 * 
 * - https://issues.openbravo.com/view.php?id=12903: Error in OBQuery when using with hql clause
 * having order by but not where part
 * 
 * - https://issues.openbravo.com/view.php?id=12918 DAL: Exception in commitTransaction leaves
 * Postgres connection in illegal state
 * 
 * - https://issues.openbravo.com/view.php?id=13135: OBContext.getLanguage() returns 'wrong'
 * language, if user does not have a default language set
 * 
 * - https://issues.openbravo.com/view.php?id=13136: OBContext.getLanguage does only use users'
 * default language, and does not honor language change in the role change popup
 * 
 * - https://issues.openbravo.com/view.php?id=13281: [REST] when inserting an object through REST
 * allow setting the organization through xml
 * 
 * https://issues.openbravo.com/view.php?id=13283: [REST] use organization of the object to
 * retrieved referenced objects
 * 
 * https://issues.openbravo.com/view.php?id=13509: In a OBCriteria you can't use list() after a
 * count() call
 * 
 * https://issues.openbravo.com/view.php?id=14276: Need feature to disable maintaining audit info
 * via dal for one request/dal-session
 * 
 * https://issues.openbravo.com/view.php?id=15050: OBQuery: whereclause with alias with a comma
 * direct after the alias fails
 * 
 * https://issues.openbravo.com/view.php?id=15218: error when closing transaction
 * 
 * https://issues.openbravo.com/view.php?id=18688: Ability to call database functions from HQL query
 * 
 * https://issues.openbravo.com/view.php?id=20611: OBCriteria doesn't support ScrollabeResults
 * 
 * https://issues.openbravo.com/view.php?id=20733: Json serialization: always the identifier
 * properties of associated entities are serialized also, resulting in extra queries
 * 
 * https://issues.openbravo.com/view.php?id=21360 DalConnectionProvider: getTransactionConnection()
 * should be equal than ConnectionProviderImpl getTransactionConnection()
 * 
 * https://issues.openbravo.com/view.php?id=22235 OBMessageUtils asumes incorrectly that
 * RequestContext is set
 * 
 * https://issues.openbravo.com/view.php?id=23627 DalUtil.copy also reads the
 * non-parent-child-one-to-many associations, this is invalid
 * 
 * @author mtaal
 * @author iperdomo
 */

public class IssuesTest extends BaseTest {
  private static final Logger log = Logger.getLogger(IssuesTest.class);

  /**
   * https://issues.openbravo.com/view.php?id=18688
   */
  public void test18688() {
    final Session session = OBDal.getInstance().getSession();
    OBDal.getInstance().registerSQLFunction("ad_column_identifier_std",
        new StandardSQLFunction("ad_column_identifier_std", StandardBasicTypes.STRING));
    final String qryStr = "select bc.id, ad_column_identifier_std('C_BP_Group', bc.id) from "
        + Category.ENTITY_NAME + " bc";
    final Query qry = session.createQuery(qryStr);
    for (Object o : qry.list()) {
      final Object[] os = (Object[]) o;
      assertTrue(os[1] instanceof String && os[1].toString().length() > 0);
    }
  }

  /**
   * https://issues.openbravo.com/view.php?id=13749
   */
  public void test13749() {
    setTestAdminContext();
    try {
      org.openbravo.model.ad.ui.Process process = OBDal.getInstance().get(
          org.openbravo.model.ad.ui.Process.class, "1004400000"); // Has a Date parameter
      Map<String, Date> params = new HashMap<String, Date>();
      params.put("DateOrdered", new Date());
      ProcessInstance pi = CallProcess.getInstance().callProcess(process, null, params);
      log.info("Result: " + pi.getResult());
      log.info("Error message: " + pi.getErrorMsg());
    } catch (Exception e) {
      log.error("Error testing CallProcess: " + e.getMessage(), e);
    }
  }

  /**
   * https://issues.openbravo.com/view.php?id=12918
   */
  public void test12918() {
    setSystemAdministratorContext();

    // A fail save process is expected
    try {
      Client c = OBDal.getInstance().get(Client.class, "0");
      Role r = OBProvider.getInstance().get(Role.class);
      r.setClient(c);
      r.setName("System Administrator"); // Fails unique name constraint
      r.setUserLevel("S");
      r.setClientList("0");
      r.setOrganizationList("0");
      OBDal.getInstance().save(r);
      OBDal.getInstance().commitAndClose();
    } catch (Exception e) {
      // Expected
      final User u = OBDal.getInstance().get(User.class, "100");
      System.out.println(u);
    }
  }

  /**
   * Tests https://issues.openbravo.com/view.php?id=12702
   */
  public void test12702() {
    final Reference ref1 = new Reference();
    final Reference ref2 = new Reference();
    ref2.setModelImpl("ref2");
    ref1.setParentReference(ref2);
    ref2.setParentReference(ref1);
    ref2.setBaseReference(true);
    assertEquals("ref2", ref1.getModelImplementationClassName());
    ref1.setBaseReference(true);
    assertEquals(null, ref1.getModelImplementationClassName());
    ref1.setBaseReference(false);
    ref2.setBaseReference(false);
    assertEquals(null, ref1.getModelImplementationClassName());
  }

  /**
   * Tests issue: https://issues.openbravo.com/view.php?id=12106
   */
  public void test12106() {
    setSystemAdministratorContext();
    {
      final List<Object> params = new ArrayList<Object>();
      final String orderId = TEST_ORDER_ID;
      params.add("C_ORDER");
      params.add(orderId);
      params.add("en_US");
      final String sqlIdentifier = (String) CallStoredProcedure.getInstance().call(
          "AD_COLUMN_IDENTIFIER", params, null);
      final Order order = OBDal.getInstance().get(Order.class, orderId);
      final String dalIdentifier = IdentifierProvider.getInstance().getIdentifier(order);

      assertEquals(sqlIdentifier, dalIdentifier);
    }
    {
      final List<Object> params = new ArrayList<Object>();
      final String id = TEST_PRODUCT_ID;
      params.add("M_PRODUCT");
      params.add(id);
      params.add("en_US");
      final String sqlIdentifier = (String) CallStoredProcedure.getInstance().call(
          "AD_COLUMN_IDENTIFIER", params, null);
      final String dalIdentifier = IdentifierProvider.getInstance().getIdentifier(
          OBDal.getInstance().get(Product.class, id));
      assertEquals(sqlIdentifier, dalIdentifier);
    }

    final List<Module> modules = OBDal.getInstance().createCriteria(Module.class).list();
    for (Module module : modules) {
      assertTrue(module.getIdentifier().contains(IdentifierProvider.SEPARATOR));
    }
  }

  /**
   * Tests issue: https://issues.openbravo.com/view.php?id=12202
   */
  public void test12202() {
    setSystemAdministratorContext();
    final List<Module> modules = OBDal.getInstance().createCriteria(Module.class).list();

    final OBQuery<Message> messages = OBDal.getInstance().createQuery(Message.class,
        "module in (:modules)");
    messages.setNamedParameter("modules", modules);
    assertFalse(messages.list().isEmpty());

  }

  /**
   * Tests issue: https://issues.openbravo.com/view.php?id=12201
   */
  public void test12201() {
    setSystemAdministratorContext();
    assertEquals("0", OBContext.getOBContext().getUser().getId());
    assertTrue(null == OBContext.getOBContext().getUser().getDefaultLanguage());
    assertTrue(OBContext.getOBContext().getLanguage().isSystemLanguage());
  }

  /**
   * Tests issue: https://issues.openbravo.com/view.php?id=12143
   */
  public void test12143() {
    setSystemAdministratorContext();
    final OBQuery<Message> messages = OBDal.getInstance().createQuery(Message.class, null);
    try {
      messages.uniqueResult();
      fail();
    } catch (Exception e) {
      // should fail as there is more than one result
    }
    final OBQuery<Organization> organizations = OBDal.getInstance().createQuery(Organization.class,
        "id='0'");
    final Organization organization = organizations.uniqueResult();
    assertNotNull(organization);
  }

  /**
   * Tests issue: https://issues.openbravo.com/view.php?id=11812
   */
  public void test11812() {
    assertTrue(24 == (Long) new LongDomainType().createFromString("24.0"));
    try {
      new LongDomainType().createFromString("24.5");
      fail("No exception on 24.5");
    } catch (ArithmeticException e) {
      // expected
    }
  }

  /**
   * Tests issue: https://issues.openbravo.com/view.php?id=11461
   */
  public void test11461() {
    setSystemAdministratorContext();

    Module module = OBDal.getInstance().get(Module.class, "0");
    module.setInDevelopment(true);
    OBDal.getInstance().save(module);
    OBDal.getInstance().flush();

    Form form = OBProvider.getInstance().get(Form.class);
    form.setName("test");
    form.setDataAccessLevel("1");
    form.setDescription("description");
    form.setHelpComment("help");
    form.setModule(module);
    form.setJavaClassName(module.getJavaPackage() + ".test");

    FormTrl formTrl = OBProvider.getInstance().get(FormTrl.class);
    formTrl.setHelpComment("help");
    formTrl.setDescription("description");
    formTrl.setName("name");
    formTrl.setSpecialForm(form);
    formTrl.setLanguage(OBDal.getInstance().createCriteria(Language.class).list().get(0));

    form.getADFormTrlList().add(formTrl);
    OBDal.getInstance().save(form);
    OBDal.getInstance().flush();

    // if we get here then the issue is solved.

    // don't save anything
    OBDal.getInstance().rollbackAndClose();
  }

  /**
   * Tests issue: https://issues.openbravo.com/view.php?id=11681
   */
  public void test11681() {
    setSystemAdministratorContext();

    OBCriteria<Module> obc = OBDal.getInstance().createCriteria(Module.class);
    obc.add(Restrictions.eq(Module.PROPERTY_INDEVELOPMENT, false));

    if (obc.list().size() == 0) {
      // Can't test DAL's connection provider
      return;
    }

    Module module = obc.list().get(0);
    module.setInDevelopment(true);
    OBDal.getInstance().save(module);

    Connection con = OBDal.getInstance().getConnection();

    final String sql = "SELECT isindevelopment FROM ad_module where ad_module_id = ?";

    try {
      PreparedStatement st = con.prepareStatement(sql);
      st.setString(1, module.getId());
      ResultSet result = st.executeQuery();
      result.next();

      String isInDev = UtilSql.getValue(result, "isindevelopment");
      assertTrue(isInDev.equals("Y"));

      result = null;
      st = null;
      con.close();
    } catch (SQLException e) {
      log.error("Error " + e.getMessage(), e);
    }
    OBDal.getInstance().rollbackAndClose();
  }

  /**
   * Tests issue: https://issues.openbravo.com/view.php?id=12497
   */
  public void test12497() {
    final InvoiceLine invoiceLine = OBProvider.getInstance().get(InvoiceLine.class);
    assertTrue(invoiceLine.isActive());
    Location bpLoc = OBProvider.getInstance().get(Location.class);
    assertTrue(bpLoc.isActive());
  }

  /**
   * Tests issue: https://issues.openbravo.com/view.php?id=12853
   */
  public void test12853() {
    setSystemAdministratorContext();
    final OBQuery<Product> products = OBDal.getInstance().createQuery(Product.class,
        " as e where e.name is not null");
    products.setFilterOnReadableOrganization(false);
    products.setFilterOnReadableClients(false);
    assertTrue(products.count() > 0);
  }

  /**
   * Tests issue: https://issues.openbravo.com/view.php?id=12903
   */
  public void test12903() {
    setSystemAdministratorContext();
    OBQuery<Product> products;

    products = OBDal.getInstance().createQuery(Product.class,
        " as e where e.name is not null order by name");
    products.setFilterOnReadableOrganization(false);
    products.setFilterOnReadableClients(false);
    assertTrue(products.count() > 0);

    products = OBDal.getInstance().createQuery(Product.class, " as e order by name");
    products.setFilterOnReadableOrganization(false);
    products.setFilterOnReadableClients(false);
    assertTrue(products.count() > 0);

    products = OBDal.getInstance().createQuery(Product.class, "order by name");
    products.setFilterOnReadableOrganization(false);
    products.setFilterOnReadableClients(false);
    assertTrue(products.count() > 0);

    products = OBDal.getInstance().createQuery(Product.class, " where name is not null");
    products.setFilterOnReadableOrganization(false);
    products.setFilterOnReadableClients(false);
    assertTrue(products.count() > 0);

    products = OBDal.getInstance().createQuery(Product.class, "");
    products.setFilterOnReadableOrganization(false);
    products.setFilterOnReadableClients(false);
    assertTrue(products.count() > 0);
  }

  /**
   * Tests: 13135: OBContext.getLanguage() returns 'wrong' language, if user does not have a default
   * language set
   */
  public void test13135() {
    setSystemAdministratorContext();

    try {
      final User user = OBDal.getInstance().get(User.class, "100");
      user.setDefaultLanguage(null);
      OBDal.getInstance().save(user);
      final Client client = OBDal.getInstance().get(Client.class, "0");
      client.setLanguage(OBDal.getInstance().get(Language.class, "120"));
      OBDal.getInstance().save(client);

      OBContext.setOBContext("100", "0", "0", "0", null);
      assertEquals("120", OBContext.getOBContext().getLanguage().getId());

    } finally {
      // prevent the user to be really changed
      OBDal.getInstance().rollbackAndClose();
    }
  }

  /**
   * Tests: 13136: OBContext.getLanguage does only use users' default language, and does not honor
   * language change in the role change popup
   */
  public void test13136() {
    OBContext.setOBContext("100", "0", "0", "0", "en_IN");
    assertEquals("130", OBContext.getOBContext().getLanguage().getId());
  }

  /**
   * https://issues.openbravo.com/view.php?id=13281 [REST] when inserting an object through REST
   * allow setting the organization through xml
   * 
   * https://issues.openbravo.com/view.php?id=13283: [REST] use organization of the object to
   * retrieved referenced objects
   */
  public void test13281And13283() throws Exception {
    // This test is currently disabled because it didn't work with the new Openbravo demo data
    // More info can be found here: https://issues.openbravo.com/view.php?id=20264
    if (1 == 1)
      return;
    OBContext.setOBContext(TEST_USER_ID, TEST_ROLE_ID, TEST_CLIENT_ID, "0");

    // use the same logic as in the DalWebService
    final XMLEntityConverter xec = XMLEntityConverter.newInstance();
    xec.setClient(OBContext.getOBContext().getCurrentClient());
    xec.setOrganization(OBContext.getOBContext().getCurrentOrganization());

    // for a webservice referenced entities should not be created at all!
    xec.getEntityResolver().setOptionCreateReferencedIfNotFound(false);

    final SAXReader reader = new SAXReader();
    final Document document = reader.read(this.getClass().getResourceAsStream("test_13281.xml"));
    final List<BaseOBObject> result = xec.process(document);
    assertTrue(result.size() == 1);
    assertTrue(result.get(0) instanceof Order);
    final Order order = (Order) result.get(0);
    // The same Organization ID of the test_13281.xml
    assertTrue(order.getOrganization().getId().equals("378AF0EAE1A2479EAAA33436645E9433"));
  }

  /**
   * https://issues.openbravo.com/view.php?id=13509: In a OBCriteria you can't use list() after a
   * count() call
   */
  public void test13509() throws Exception {
    setTestAdminContext();
    final OBCriteria<Organization> orgs = OBDal.getInstance().createCriteria(Organization.class);
    final int cnt = orgs.count();
    assertTrue(cnt > 0);
    final Organization org = orgs.list().get(0);
    assertTrue(null != org);
    assertTrue(cnt == orgs.list().size());
  }

  /**
   * https://issues.openbravo.com/view.php?id=14276: Need feature to disable maintaining audit info
   * via dal for one request/dal-session
   */
  public void test14276() throws Exception {
    setSystemAdministratorContext();
    OBInterceptor.setPreventUpdateInfoChange(true);
    boolean oldIndevelopment = false;
    String oldName = null;
    try {
      Table table = OBDal.getInstance().get(Table.class, "100");
      oldIndevelopment = table.getDataPackage().getModule().isInDevelopment();
      table.getDataPackage().getModule().setInDevelopment(true);
      OBDal.getInstance().save(table.getDataPackage().getModule());
      OBDal.getInstance().flush();
      oldName = table.getName();
      final Date oldUpdated = table.getUpdated();
      table.setName(table.getName() + "t");
      OBDal.getInstance().save(table);

      // test if flush already works fine
      OBDal.getInstance().flush();
      OBDal.getInstance().getSession().clear();

      table = OBDal.getInstance().get(Table.class, "100");
      assertFalse(oldName.equals(table.getName()));
      assertTrue(table.getUpdated().getTime() == oldUpdated.getTime());

      OBDal.getInstance().commitAndClose();
      table = OBDal.getInstance().get(Table.class, "100");
      assertFalse(oldName.equals(table.getName()));
      assertTrue(table.getUpdated().getTime() == oldUpdated.getTime());
    } finally {
      OBInterceptor.setPreventUpdateInfoChange(false);
    }

    // now do the same with preventupdate disabled
    {
      Table table = OBDal.getInstance().get(Table.class, "100");
      final Date oldUpdated = table.getUpdated();
      table.setName(oldName);
      OBDal.getInstance().save(table);
      OBDal.getInstance().flush();
      table.getDataPackage().getModule().setInDevelopment(oldIndevelopment);
      OBDal.getInstance().save(table.getDataPackage().getModule());
      OBDal.getInstance().commitAndClose();
      table = OBDal.getInstance().get(Table.class, "100");
      assertTrue(oldName.equals(table.getName()));
      assertFalse(table.getUpdated().getTime() == oldUpdated.getTime());
    }
  }

  /**
   * https://issues.openbravo.com/view.php?id=15050: OBQuery: whereclause with alias with a comma
   * direct after the alias fails
   */
  public void test15050() throws Exception {
    setSystemAdministratorContext();
    final String whereClause = " as t, ADColumn as c where c.table = t and c.keyColumn=true";
    final OBQuery<Table> tables = OBDal.getInstance().createQuery(Table.class, whereClause);
    assertTrue(tables.list().size() > 0);
  }

  /**
   * https://issues.openbravo.com/view.php?id=15218: error when closing transaction
   */
  public void test15218() throws Exception {
    final OBContext obContext = OBContext.getOBContext();
    final DalThreadHandler dth = new DalThreadHandler() {

      @Override
      protected void doAction() throws Exception {
        OBDal.getInstance().getSession().beginTransaction();
        OBDal.getInstance().getSession().getTransaction().commit();
      }
    };

    dth.run();
    OBContext.setOBContext(obContext);
  }

  /**
   * https://issues.openbravo.com/view.php?id=15360: ModelProvider.getTable(tablename) should not
   * fail
   */
  public void test15360() throws Exception {
    org.openbravo.base.model.Table corder = ModelProvider.getInstance().getTable("C_Order");
    assertFalse(corder == null);
  }

  /**
   * Testing part of code 'used' in the fix of issue 15742. Especially if storing 10000 'ñ'
   * characters in a column of type clob/text works correctly.
   */
  public void test15742() {
    final int logsize = 10000;
    setSystemAdministratorContext();
    HeartbeatLog hbLog = OBProvider.getInstance().get(HeartbeatLog.class);
    StringBuilder logBuffer = new StringBuilder(logsize);
    for (int i = 0; i < logBuffer.capacity(); i++) {
      logBuffer.append('ñ');
    }
    hbLog.setInstalledModules(logBuffer.toString());
    OBDal.getInstance().save(hbLog);
    String id = hbLog.getId();

    HeartbeatLog hbLogRead = OBDal.getInstance().get(HeartbeatLog.class, id);
    assertEquals(logBuffer.toString(), hbLogRead.getInstalledModules());

    OBDal.getInstance().remove(hbLogRead);
  }

  /**
   * Testing issue 0017058. It verifies that the NVARCHAR JDBC type is properly mapped The test SQL
   * query is used in the IDL module.
   */
  public void test17058() {

    setSystemAdministratorContext();

    final Session session = OBDal.getInstance().getSession();
    SQLQuery query = session
        .createSQLQuery("SELECT AD_REF_LIST.VALUE AS VALUE, AD_REF_LIST.NAME AS LISTNAME, TRL.NAME AS TRLNAME "
            + "FROM AD_REF_LIST LEFT OUTER JOIN "
            + "(SELECT AD_REF_LIST_ID, NAME FROM AD_REF_LIST_TRL WHERE AD_REF_LIST_TRL.AD_LANGUAGE = ?) TRL "
            + "ON AD_REF_LIST.AD_REF_LIST_ID = TRL.AD_REF_LIST_ID "
            + "WHERE AD_REF_LIST.AD_REFERENCE_ID = ?");
    query.setString(0, "en_US");
    query.setString(1, "800025");

    @SuppressWarnings("unchecked")
    java.util.List<Object[]> l = query.list();

  }

  /**
   * Testing issue 20129. https://issues.openbravo.com/view.php?id=20129 Tests getChildOrg()
   */
  public void test20129A() {
    setTestAdminContext();
    final String clientId = OBContext.getOBContext().getCurrentClient().getId();
    final OrganizationStructureProvider osp = OBContext.getOBContext()
        .getOrganizationStructureProvider(clientId);
    final Set<String> childOrg = osp.getChildOrg(OBContext.getOBContext().getCurrentOrganization()
        .getId());
    childOrg.removeAll(childOrg);
    final Set<String> childOrg2 = osp.getChildOrg(OBContext.getOBContext().getCurrentOrganization()
        .getId());
    assertFalse(childOrg2.isEmpty());
  }

  /**
   * Tests getNaturalTree()
   */
  public void test20129B() {
    setTestAdminContext();
    final String clientId = OBContext.getOBContext().getCurrentClient().getId();
    final OrganizationStructureProvider osp = OBContext.getOBContext()
        .getOrganizationStructureProvider(clientId);
    final Set<String> naturalTree = osp.getNaturalTree(OBContext.getOBContext()
        .getCurrentOrganization().getId());
    naturalTree.removeAll(naturalTree);
    final Set<String> naturalTree2 = osp.getNaturalTree(OBContext.getOBContext()
        .getCurrentOrganization().getId());
    assertFalse(naturalTree2.isEmpty());
  }

  /**
   * Tests getReadableOrganizations()
   */
  public void test20129C() {
    setTestAdminContext();
    String[] readableOrganizations = OBContext.getOBContext().getReadableOrganizations();
    readableOrganizations[0] = "Test";
    String[] readableOrganizations2 = OBContext.getOBContext().getReadableOrganizations();
    assertFalse("Test".equals(readableOrganizations2[0]));
  }

  /**
   * Tests getReadableClients()
   */
  public void test20129D() {
    setTestAdminContext();
    String[] readableClients = OBContext.getOBContext().getReadableClients();
    readableClients[0] = "Test";
    String[] readableClients2 = OBContext.getOBContext().getReadableClients();
    assertFalse("Test".equals(readableClients2[0]));
  }

  /**
   * Tests getWritableOrganizations()
   */
  public void test20129E() {
    setTestAdminContext();
    Set<String> writableOrganizations = OBContext.getOBContext().getWritableOrganizations();
    writableOrganizations.removeAll(writableOrganizations);
    Set<String> writableOrganizations2 = OBContext.getOBContext().getWritableOrganizations();
    assertFalse(writableOrganizations2.isEmpty());
  }

  public void test20611() {
    OBCriteria<BusinessPartner> c = OBDal.getInstance().createCriteria(BusinessPartner.class);
    ScrollableResults iterator = c.scroll(ScrollMode.FORWARD_ONLY);
    Assert.assertTrue(iterator.next());
  }

  /**
   * Testing issue 0020659. Tests that if an invalid organization id is provided, getChildOrg
   * returns an empty set instead of null.
   */
  public void test20659() {
    setTestAdminContext();
    String nonExistentOrg = "-123ZXY";

    final String clientId = OBContext.getOBContext().getCurrentClient().getId();
    final OrganizationStructureProvider osp = OBContext.getOBContext()
        .getOrganizationStructureProvider(clientId);
    final Set<String> childOrg = osp.getChildOrg(nonExistentOrg);
    assertTrue(childOrg.isEmpty());
  }

  public void test20733() {
    setTestUserContext();
    DataEntityQueryService service = new DataEntityQueryService();
    // Order entity: exceeded oracle maximum columns limit in select statement (No more data to read
    // from socket)
    // Change the entity to FIN_Payment.
    service.setEntityName(FIN_Payment.ENTITY_NAME);
    service.setJoinAssociatedEntities(true);
    service.setCriteria(new JSONObject());
    DataToJsonConverter converter = new DataToJsonConverter();
    for (BaseOBObject bob : service.list()) {
      final FIN_Payment payment = (FIN_Payment) bob;
      converter.toJsonObjects(Collections.singletonList((BaseOBObject) payment));
    }
  }

  public void test21360() throws Exception {
    final DalConnectionProvider connectionProvider = new DalConnectionProvider();
    // test that a new connection is returned
    final Connection connection = connectionProvider.getConnection();
    final Connection otherConnection = connectionProvider.getTransactionConnection();
    final Connection yetAnotherConnection = connectionProvider.getTransactionConnection();
    Assert.assertNotSame(connection, yetAnotherConnection);
    Assert.assertNotSame(connection, otherConnection);
    Assert.assertNotSame(otherConnection, yetAnotherConnection);
  }

  public void test22235() throws Exception {
    final OBContext obContext = OBContext.getOBContext();
    final VariablesSecureApp vars = new VariablesSecureApp(obContext.getUser().getId(), obContext
        .getCurrentClient().getId(), obContext.getCurrentOrganization().getId(), obContext
        .getRole().getId(), obContext.getLanguage().getLanguage());
    final ProcessBundle processBundle = new ProcessBundle("test", vars);
    processBundle.setProcessClass(Test22235.class);

    try {
      RequestContext.get().getVariablesSecureApp();
      fail();
    } catch (OBException e) {
      // fine, should fail at this point
    }

    final Test22235 test22235 = new Test22235();
    // default is true
    Assert.assertTrue(test22235.isErrorOccured());
    test22235.execute(processBundle);
    Assert.assertFalse(test22235.isErrorOccured());

    // this should again fail, to ensure everything is cleaned up
    try {
      RequestContext.get().getVariablesSecureApp();
      fail();
    } catch (OBException e) {
      // fine, should fail at this point
    }
  }

  public void test23627() throws Exception {
    // read one order line, take its uom, copy it and check that the order line list is empty
    final OBQuery<OrderLine> ols = OBDal.getInstance().createQuery(OrderLine.class, null);
    ols.setMaxResult(1);
    final OrderLine ol = ols.list().get(0);
    final UOM uom = ol.getUOM();
    assertTrue(uom.getOrderLineList().size() > 0);
    final UOM copiedUom = (UOM) DalUtil.copy(uom);
    assertTrue(copiedUom.getOrderLineList().isEmpty());
  }

  public void test23743() throws Exception {
    // create a OBQuery where clause with WHERE keyword and see query does not return exception when
    // fetching results or getting count
    String whereClause = " AS orderline WHERE orderDate <=now()";
    final OBQuery<OrderLine> ols = OBDal.getInstance().createQuery(OrderLine.class, whereClause);
    ols.setMaxResult(1);
    assertTrue(ols.list().size() >= 0);
  }

  private static class Test22235 extends DalBaseProcess {

    private boolean errorOccured = true;

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
      RequestContext.get().getVariablesSecureApp();
      errorOccured = false;
    }

    public boolean isErrorOccured() {
      return errorOccured;
    }

    public void setErrorOccured(boolean errorOccured) {
      this.errorOccured = errorOccured;
    }
  }
}