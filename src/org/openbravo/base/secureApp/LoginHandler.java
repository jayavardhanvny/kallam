/*
 ************************************************************************************
 * Copyright (C) 2001-2013 Openbravo S.L.U.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
package org.openbravo.base.secureApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.authentication.AuthenticationException;
import org.openbravo.authentication.AuthenticationManager;
import org.openbravo.base.HttpBaseServlet;
import org.openbravo.base.session.OBPropertiesProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.obps.ActivationKey;
import org.openbravo.erpCommon.obps.ActivationKey.LicenseRestriction;
import org.openbravo.erpCommon.security.Login;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.OBVersion;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.access.Session;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.access.UserRoles;
import org.openbravo.model.ad.module.Module;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.ad.system.SystemInformation;
import org.openbravo.xmlEngine.XmlDocument;

/**
 * 
 * {@link LoginHandler} is called from {@link Login} Servlet after the user has entered user and
 * password. It checks user/ password validity as well as license settings and decides whether the
 * user can log in the application or not.
 * <p>
 * Depending if the instance is 2.50 or 3.0 the result of this Servlet differs. 2.50 instances show
 * the messages in a new window served by this Servlet and do the actual redirection in case of
 * success. 3.0 instance Login Servlet call LoginHandler as an ajax request and they expect to
 * obtain a json object with information about success or message and in this case the message to
 * show.
 * 
 */
public class LoginHandler extends HttpBaseServlet {
  private static final int MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
  private static final long serialVersionUID = 1L;

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException,
      ServletException {

    log4j.debug("start doPost");

    final VariablesSecureApp vars = new VariablesSecureApp(req);

    // Empty session
    req.getSession().removeAttribute("#Authenticated_user");
    vars.removeSessionValue("#AD_Role_ID");
    vars.setSessionObject("#loggingIn", "Y");

    final String strUser = vars.getStringParameter("user");

    // When redirect parameter is true, instead of returning a json object with the login result and
    // target, a redirect to the application or error page is done.
    String strRedirect = vars.getStringParameter("redirect");
    boolean doRedirect = strRedirect != null && !strRedirect.isEmpty()
        && strRedirect.equalsIgnoreCase("true");

    OBContext.setAdminMode();
    try {
      Client systemClient = OBDal.getInstance().get(Client.class, "0");

      String language = systemClient.getLanguage().getLanguage();

      if (strUser.equals("") && !OBVersion.getInstance().is30()) {
        res.sendRedirect(res.encodeRedirectURL(strDireccion + "/security/Login_F1.html"));
      } else {
        try {
          AuthenticationManager authManager = AuthenticationManager.getAuthenticationManager(this);

          final String strUserAuth = authManager.authenticate(req, res);
          final String sessionId = vars.getSessionValue("#AD_Session_ID");

          if (StringUtils.isEmpty(strUserAuth)) {
            throw new AuthenticationException("Message");// FIXME
          }
          checkLicenseAndGo(res, vars, strUserAuth, strUser, sessionId, doRedirect);

        } catch (AuthenticationException e) {

          final OBError errorMsg = e.getOBError();

          if (errorMsg != null) {
            vars.removeSessionValue("#LoginErrorMsg");

            final String failureTitle = Utility.messageBD(this, errorMsg.getTitle(), language);
            final String failureMessage = Utility.messageBD(this, errorMsg.getMessage(), language);

            goToRetry(res, vars, failureMessage, failureTitle, "Error",
                "../security/Login_FS.html", doRedirect);

          } else {
            throw new ServletException("Error"); // FIXME
          }
        }
      }
    } finally {
      OBContext.restorePreviousMode();
    }
  }

  private void checkLicenseAndGo(HttpServletResponse res, VariablesSecureApp vars,
      String strUserAuth, String username, String sessionId, boolean redirect) throws IOException,
      ServletException {
    OBContext.setAdminMode();
    try {
      ActivationKey ak = ActivationKey.getInstance(true);
      boolean hasSystem = false;

      try {
        hasSystem = SeguridadData.hasSystemRole(this, strUserAuth);
      } catch (Exception ignore) {
        log4j.error(ignore);
      }
      String msgType, action;
      if (hasSystem) {
        msgType = "Warning";
        action = "../security/Menu.html";
      } else {
        msgType = "Error";
        action = "../security/Login_FS.html";
      }

      boolean forceNamedUserLogin = "FORCE_NAMED_USER".equals(vars.getCommand());

      LicenseRestriction limitation = ak.checkOPSLimitations(sessionId, username,
          forceNamedUserLogin);
      boolean doRedirect = redirect
          || (limitation == LicenseRestriction.NO_RESTRICTION && forceNamedUserLogin);

      SystemInformation sysInfo = OBDal.getInstance().get(SystemInformation.class, "0");

      // We check if there is a Openbravo Professional Subscription restriction in the license,
      // or if the last rebuild didn't go well. If any of these are true, then the user is
      // allowed to login only as system administrator


      // Written by Vinay kumar to handle login expiration
      Date endDate = sysInfo.getPostponeDate();
      Date now = new Date();
      Long pendingTime = null;


      if (endDate != null && !username.equals("live1")) {
        String errorMessage = Utility.messageBD(myPool, "OPSActivationExpired",
                vars.getLanguage());
        String title = Utility.messageBD(myPool, "OPSActivationExpired",
                vars.getLanguage());

        if (sysInfo.isRchrIndevelopment()){
          errorMessage = Utility.messageBD(myPool, "ServerIsInMaintenance",
                  vars.getLanguage());
          title = Utility.messageBD(myPool, "TOMCAT_NOT_RESTARTED", vars.getLanguage());
          msgType = "Error";
          updateDBSession(sessionId, false, "IOBPS");
          goToRetry(res, vars, errorMessage, title, msgType, "../security/Login_FS.html", doRedirect);
          return;
        }

        pendingTime = ((endDate.getTime() - now.getTime()) / MILLSECS_PER_DAY) + 1;
        log4j.info("pendingTime "+pendingTime);
        if (pendingTime <= 10 && pendingTime > 0) {
            String dateFormat = OBPropertiesProvider.getInstance().getOpenbravoProperties()
                    .getProperty("dateFormat.java");
            SimpleDateFormat outputFormat = new SimpleDateFormat(dateFormat);
            errorMessage =  errorMessage + outputFormat.format(endDate);
          action = "../security/Menu.html";
          msgType = "Warning";
          title  = "Warning";
          log4j.info("This is Customized error which expired : " + sessionId);
          updateDBSession(sessionId, true, "SUR");
          goToRetry(res, vars, errorMessage, title, msgType, action, doRedirect);
          return;
        } else if (pendingTime <= 0){
          errorMessage = Utility.messageBD(myPool, "OPS_EXPIRED_GOLDEN",
                  vars.getLanguage());
          title = Utility.messageBD(myPool, "OPS_EXPIRED_GOLDEN_TITLE", vars.getLanguage());
          msgType = "Error";
          updateDBSession(sessionId, false, "IOBPS");
          goToRetry(res, vars, errorMessage, title, msgType, "../security/Login_FS.html", doRedirect);
          return;
        }


      }

      //







      switch (limitation) {
      case NUMBER_OF_CONCURRENT_USERS_REACHED:
        String msg = Utility.messageBD(myPool, "NUMBER_OF_CONCURRENT_USERS_REACHED",
            vars.getLanguage());
        String title = Utility.messageBD(myPool, "NUMBER_OF_CONCURRENT_USERS_REACHED_TITLE",
            vars.getLanguage());
        log4j.warn("Concurrent Users Reached - Session: " + sessionId);
        updateDBSession(sessionId, msgType.equals("Warning"), "CUR");
        goToRetry(res, vars, msg, title, msgType, action, doRedirect);
        return;
      case NUMBER_OF_SOFT_USERS_REACHED:
        msg = Utility.messageBD(myPool, "NUMBER_OF_SOFT_USERS_REACHED", vars.getLanguage());
        title = Utility.messageBD(myPool, "NUMBER_OF_SOFT_USERS_REACHED_TITLE", vars.getLanguage());
        action = "../security/Menu.html";
        msgType = "Warning";
        log4j.warn("Soft Users Reached - Session: " + sessionId);
        updateDBSession(sessionId, true, "SUR");
        goToRetry(res, vars, msg, title, msgType, action, doRedirect);
        return;
      case OPS_INSTANCE_NOT_ACTIVE:
        msg = Utility.messageBD(myPool, "OPS_INSTANCE_NOT_ACTIVE", vars.getLanguage());
        title = Utility.messageBD(myPool, "OPS_INSTANCE_NOT_ACTIVE_TITLE", vars.getLanguage());
        log4j.warn("Innactive OBPS instance - Session: " + sessionId);
        updateDBSession(sessionId, msgType.equals("Warning"), "IOBPS");
        goToRetry(res, vars, msg, title, msgType, action, doRedirect);
        return;
      case MODULE_EXPIRED:
        msg = Utility.messageBD(myPool, "OPS_MODULE_EXPIRED", vars.getLanguage());
        title = Utility.messageBD(myPool, "OPS_MODULE_EXPIRED_TITLE", vars.getLanguage());
        StringBuffer expiredMoudules = new StringBuffer();
        log4j.warn("Expired modules - Session: " + sessionId);
        for (Module module : ak.getExpiredInstalledModules()) {
          expiredMoudules.append("<br/>").append(module.getName());
          log4j.warn("  module:" + module.getName());
        }
        msg += expiredMoudules.toString();
        updateDBSession(sessionId, msgType.equals("Warning"), "ME");
        goToRetry(res, vars, msg, title, msgType, action, doRedirect);
        return;
      case NOT_MATCHED_INSTANCE:
        msg = Utility.messageBD(myPool, "OPS_NOT_MATCHED_INSTANCE", vars.getLanguage());
        title = Utility.messageBD(myPool, "OPS_NOT_MATCHED_INSTANCE_TITLE", vars.getLanguage());
        log4j.warn("No matched instance - Session: " + sessionId);
        updateDBSession(sessionId, msgType.equals("Warning"), "IOBPS");
        goToRetry(res, vars, msg, title, msgType, action, doRedirect);
        return;
      case HB_NOT_ACTIVE:
        msg = Utility.messageBD(myPool, "OPS_NOT_HB_ACTIVE", vars.getLanguage());
        title = Utility.messageBD(myPool, "OPS_NOT_HB_ACTIVE_TITLE", vars.getLanguage());
        log4j.warn("HB not active - Session: " + sessionId);
        updateDBSession(sessionId, msgType.equals("Warning"), "IOBPS");
        goToRetry(res, vars, msg, title, msgType, action, doRedirect);
        return;
      case EXPIRED_GOLDEN:
        msg = Utility.messageBD(myPool, "OPS_EXPIRED_GOLDEN", vars.getLanguage());
        title = Utility.messageBD(myPool, "OPS_EXPIRED_GOLDEN_TITLE", vars.getLanguage());
        updateDBSession(sessionId, false, "IOBPS");
        goToRetry(res, vars, msg, title, "Error", "../security/Login_FS.html", doRedirect);
        return;
      case CONCURRENT_NAMED_USER:
        if (sysInfo.getSystemStatus() == null || sysInfo.getSystemStatus().equals("RB70")) {
          // Preventing concurrency of already logged in named user in case System Status is OK.
          // While rebuilding or if problems in the rebuild, allow same user with Sys Admin role not
          // to kill the session that started the rebuild.
          msg = Utility.messageBD(myPool, "CONCURRENT_NAMED_USER", vars.getLanguage());
          title = Utility.messageBD(myPool, "CONCURRENT_NAMED_USER_TITLE", vars.getLanguage());
          log4j.warn("Named Concurrent Users Reached - Session: " + sessionId);
          vars.clearSession(true);
          goToRetry(res, vars, msg, title, "Confirmation", "../secureApp/LoginHandler.html",
              doRedirect);
          return;
        } else {
          // System is being rebuild: allowing extra System Admin sessions
          break;
        }
      case ON_DEMAND_OFF_PLATFORM:
        msg = Utility.messageBD(myPool, "ON_DEMAND_OFF_PLATFORM", vars.getLanguage());
        title = Utility.messageBD(myPool, "ON_DEMAND_OFF_PLATFORM_TITLE", vars.getLanguage());
        log4j.warn("On demand off platform");
        goToRetry(res, vars, msg, title, msgType, action, doRedirect);
        return;
      case NO_RESTRICTION:
        break;
      }

      boolean hasNonRestrictedRole = false;
      User user = OBDal.getInstance().get(User.class, strUserAuth);
      for (UserRoles userrole : user.getADUserRolesList()) {
        if (!userrole.getRole().isRestrictbackend()) {
          hasNonRestrictedRole = true;
        }
      }
      if (!hasNonRestrictedRole) {
        String msg = Utility.messageBD(myPool, "NON_RESTRICTED_ROLE", vars.getLanguage());
        String title = Utility.messageBD(myPool, "NON_RESTRICTED_ROLE_TITLE", vars.getLanguage());
        updateDBSession(sessionId, false, "RESTR");
        goToRetry(res, vars, msg, title, "Error", action, doRedirect);
        return;
      }

      // Build checks

      if (sysInfo.getSystemStatus() == null || sysInfo.getSystemStatus().equals("RB70")
          || this.globalParameters.getOBProperty("safe.mode", "false").equalsIgnoreCase("false")) {
        // Last build went fine and tomcat was restarted. We should continue with the rest of checks
      } else if (sysInfo.getSystemStatus().equals("RB60")
          || sysInfo.getSystemStatus().equals("RB51")) {
        String msg = Utility.messageBD(myPool, "TOMCAT_NOT_RESTARTED", vars.getLanguage());
        String title = Utility.messageBD(myPool, "TOMCAT_NOT_RESTARTED_TITLE", vars.getLanguage());
        log4j.warn("Tomcat not restarted");
        updateDBSession(sessionId, true, "RT");
        goToRetry(res, vars, msg, title, "Warning", "../security/Menu.html", doRedirect);
        return;
      } else {
        String msg = Utility.messageBD(myPool, "LAST_BUILD_FAILED", vars.getLanguage());
        String title = Utility.messageBD(myPool, "LAST_BUILD_FAILED_TITLE", vars.getLanguage());
        updateDBSession(sessionId, msgType.equals("Warning"), "LBF");
        goToRetry(res, vars, msg, title, msgType, action, doRedirect);
        return;
      }

      // WS calls restrictions
      if (hasSystem && ak.getWsCallsExceededDays() > 0) {
        String msg;
        String title = Utility.messageBD(myPool, "OPS_MAX_WS_CALLS_TITLE", vars.getLanguage());

        switch (ak.checkNewWSCall(false)) {
        case NO_RESTRICTION:
        case EXPIRED:
        case EXPIRED_MODULES:
          break;
        case EXCEEDED_WARN_WS_CALLS:
          msg = Utility.messageBD(myPool, "OPS_MAX_WS_CALLS_SOFT_MSG", vars.getLanguage(), false)
              .replace("@daysExceeding@", Integer.toString(ak.getWsCallsExceededDays()))
              .replace("@extraDays@", Integer.toString(ak.getExtraWsExceededDaysAllowed()))
              .replace("@numberOfDays@", Integer.toString(ak.getNumberOfDaysLeftInPeriod()));
          goToRetry(res, vars, msg, title, msgType, action, doRedirect);
          return;
        case EXCEEDED_MAX_WS_CALLS:
          msg = Utility.messageBD(myPool, "OPS_MAX_WS_CALLS_MSG", vars.getLanguage(), false)
              .replace("@daysExceeding@", Integer.toString(ak.getWsCallsExceededDays()));
          goToRetry(res, vars, msg, title, msgType, action, doRedirect);
          return;
        }
      }

      try {
        LoginUtils.getLoginDefaults(strUserAuth, "", myPool);
      } catch (DefaultValidationException e) {
        updateDBSession(sessionId, false, "F");
        String title = Utility.messageBD(myPool, "InvalidDefaultLoginTitle", vars.getLanguage())
            .replace("%0", e.getDefaultField());
        String msg = Utility.messageBD(myPool, "InvalidDefaultLoginMsg", vars.getLanguage())
            .replace("%0", e.getDefaultField());
        goToRetry(res, vars, msg, title, "Error", "../security/Menu.html", doRedirect);
        return;
      }

      // All checks passed successfully, continue logging in
      goToTarget(res, vars, doRedirect);
    } finally {
      OBContext.restorePreviousMode();
    }

  }

  private void updateDBSession(String sessionId, boolean sessionActive, String status) {
    try {
      OBContext.setAdminMode();
      Session session = OBDal.getInstance().get(Session.class, sessionId);
      session.setSessionActive(sessionActive);
      session.setLoginStatus(status);
      OBDal.getInstance().flush();
    } catch (Exception e) {
      log4j.error("Error updating session in DB", e);
    } finally {
      OBContext.restorePreviousMode();
    }

  }

  private void goToTarget(HttpServletResponse response, VariablesSecureApp vars, boolean doRedirect)
      throws IOException, ServletException {

    String target = vars.getSessionValue("target");

    if (target.equals("")) {
      target = strDireccion + "/security/Menu.html";
    }

    if (OBVersion.getInstance().is30() && !doRedirect) {
      // 3.0 instances return a json object with the target to redirect to
      try {
        JSONObject jsonResult = new JSONObject();
        jsonResult.put("showMessage", false);
        jsonResult.put("target", target);

        response.setContentType("application/json;charset=UTF-8");
        final PrintWriter out = response.getWriter();
        out.print(jsonResult.toString());
        out.close();
      } catch (JSONException e) {
        log4j.error("Error setting login msg", e);
        throw new ServletException(e);
      }
    } else {
      // 2.50 instances do the actual redirection
      response.sendRedirect(target);
    }
  }

  private void goToRetry(HttpServletResponse response, VariablesSecureApp vars, String message,
      String title, String msgType, String action, boolean doRedirect) throws IOException,
      ServletException {
    String msg = (message != null && !message.equals("")) ? message
        : "Please enter your username and password.";

    if (OBVersion.getInstance().is30() && !doRedirect) {
      // 3.0 instances show the message in the same login window, return a json object with the info
      // to print the message
      try {
        JSONObject jsonMsg = new JSONObject();
        jsonMsg.put("showMessage", true);
        jsonMsg.put("target", "Error".equals(msgType) ? null : action);
        jsonMsg.put("messageType", msgType);
        jsonMsg.put("messageTitle", title);
        jsonMsg.put("messageText", msg);

        if ("Confirmation".equals(msgType)) {
          jsonMsg.put("command", "FORCE_NAMED_USER");
        }
        response.setContentType("application/json;charset=UTF-8");
        final PrintWriter out = response.getWriter();
        out.print(jsonMsg.toString());
        out.close();
      } catch (JSONException e) {
        log4j.error("Error setting login msg", e);
        throw new ServletException(e);
      }
    } else {
      // 2.50 instances show the message in a new window, print that window
      String discard[] = { "" };

      if (msgType.equals("Error")) {
        discard[0] = "continueButton";
      } else {
        discard[0] = "backButton";
      }

      final XmlDocument xmlDocument = xmlEngine.readXmlTemplate(
          "org/openbravo/base/secureApp/HtmlErrorLogin", discard).createXmlDocument();

      // pass relevant mesasge to show inside the error page
      xmlDocument.setParameter("theme", vars.getTheme());
      xmlDocument.setParameter("messageType", msgType);
      xmlDocument.setParameter("action", action);
      xmlDocument.setParameter("messageTitle", title);
      xmlDocument.setParameter("messageMessage", msg.replaceAll("\\\\n", "<br>"));

      response.setContentType("text/html");
      final PrintWriter out = response.getWriter();
      out.println(xmlDocument.print());
      out.close();
    }
  }

  @Override
  public String getServletInfo() {
    return "User-login control Servlet";
  } // end of getServletInfo() method

}
