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
 * All portions are Copyright (C) 2013 Openbravo SLU 
 * All Rights Reserved. 
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */
package org.openbravo.portal;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.client.application.process.BaseProcessActionHandler;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.email.EmailEventManager;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.model.ad.access.Role;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.access.UserRoles;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.utils.CryptoSHA1BASE64;

/**
 * This process grants the user the given role and resets her password
 * 
 * @author alostale
 * 
 */
public class GrantPortalAccessProcess extends BaseProcessActionHandler {

  private static final Logger log = Logger.getLogger(GrantPortalAccessProcess.class);
  public static final String EVT_NEW_USER = "newUser";
  private static final int PASSWORD_LENGHT = 6;

  @Inject
  private EmailEventManager emailManager;

  @Override
  protected JSONObject doExecute(Map<String, Object> parameters, String content) {
    OBContext.setAdminMode(true);
    try {
      JSONObject context = new JSONObject(content);
      JSONObject params = context.getJSONObject("_params");

      Role role = OBDal.getInstance().get(Role.class, params.getString("portalRole"));
      User user = OBDal.getInstance().get(User.class, context.getString("AD_User_ID"));

      if (StringUtils.isEmpty(user.getEmail())) {
        JSONObject msg = new JSONObject();
        JSONObject result = new JSONObject();
        msg.put("severity", "error");
        msg.put("text", OBMessageUtils.getI18NMessage("Portal_UserWithoutEmail", null));
        result.put("message", msg);
        return result;
      }

      OBCriteria<UserRoles> checkRoleIsPresent = OBDal.getInstance()
          .createCriteria(UserRoles.class);
      checkRoleIsPresent.add(Restrictions.eq(UserRoles.PROPERTY_USERCONTACT, user));
      checkRoleIsPresent.add(Restrictions.eq(UserRoles.PROPERTY_ROLE, role));
      if (checkRoleIsPresent.count() == 0) {
        UserRoles newUserRole = OBProvider.getInstance().get(UserRoles.class);
        newUserRole.setUserContact(user);
        newUserRole.setRole(role);

        Organization org = OBDal.getInstance().get(Organization.class, "0");
        newUserRole.setOrganization(org);
        OBDal.getInstance().save(newUserRole);
        log.info(user + " is granted to " + role);
      } else {
        log.info(user + " already is granted to role " + role);
      }

      user.setDefaultRole(role);

      if (user.getUsername() == null) {
        user.setUsername(user.getEmail());
        log.info("Setting " + user.getEmail() + " username to " + user);
      }

      String newPassword = RandomStringUtils.randomAlphanumeric(PASSWORD_LENGHT);
      user.setPassword(CryptoSHA1BASE64.hash(newPassword));

      // flushing changes in admin mode
      OBDal.getInstance().flush();

      Map<String, Object> emailData = new HashMap<String, Object>();
      emailData.put("user", user);
      emailData.put("password", newPassword);
      JSONObject msg = new JSONObject();
      try {
        boolean emailSent = emailManager.sendEmail(EVT_NEW_USER, user.getEmail(), emailData);

        if (emailSent) {
          msg.put("severity", "success");
          msg.put("text", OBMessageUtils.getI18NMessage("Portal_UserGranted", null));
          user.setGrantPortalAccess(true);
        } else {
          msg.put("severity", "warning");
          msg.put("text", OBMessageUtils.getI18NMessage("Portal_UserGrantedNoEmail", null));
        }
      } catch (Exception e) {
        log.error("Error sending email", e);
        msg.put("severity", "warning");
        msg.put("text",
            OBMessageUtils.getI18NMessage("ErrorInEmail", new String[] { e.getMessage() }));
      }

      JSONObject result = new JSONObject();
      result.put("message", msg);
      return result;

    } catch (Exception e) {
      log.error("Error granting access to portal", e);
      JSONObject result = new JSONObject();
      JSONObject msg = new JSONObject();
      try {
        OBDal.getInstance().rollbackAndClose();
        msg.put("severity", "error");
        msg.put(
            "text",
            OBMessageUtils.getI18NMessage("Portal_ErrorGrantingPortalAccess",
                new String[] { e.getMessage() }));
        result.put("message", msg);
      } catch (JSONException e1) {
        log.error("Couldn't genereate error msg", e1);
      }
      return result;
    }
  }
}
