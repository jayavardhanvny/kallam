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
 * All portions are Copyright (C) 2008-2011 Openbravo SLU 
 * All Rights Reserved. 
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */

package org.openbravo.base.session;

import java.sql.Types;

import org.apache.log4j.Logger;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

/**
 * Extends the Oracle10Dialect to replace some java-oracle type mappings to support the current rdb
 * schema of OpenBravo. Is used in the {@link SessionFactoryController}.
 * 
 * @author mtaal
 */

public class OBOracle10gDialect extends Oracle10gDialect {
  private static final Logger log = Logger.getLogger(OBOracle10gDialect.class);

  public OBOracle10gDialect() {
    super();

    registerHibernateType(Types.NUMERIC, StandardBasicTypes.LONG.getName());
    registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());
    registerHibernateType(Types.NCHAR, StandardBasicTypes.STRING.getName());

    registerColumnType(Types.VARCHAR, 4000, "nvarchar2($l)");
    registerColumnType(Types.VARCHAR, 100, "varchar2($l)");
    registerColumnType(Types.VARCHAR, 5, "char($l)");
    registerFunction("to_number", new StandardSQLFunction("to_number",
        StandardBasicTypes.BIG_DECIMAL));

    log.debug("Created Openbravo specific Oracle DIalect");
  }

}
