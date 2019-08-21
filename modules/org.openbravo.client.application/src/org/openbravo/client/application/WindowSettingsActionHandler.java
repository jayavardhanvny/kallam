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
 * All portions are Copyright (C) 2011-2012 Openbravo SLU 
 * All Rights Reserved. 
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */
package org.openbravo.client.application;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.client.application.personalization.PersonalizationHandler;
import org.openbravo.client.kernel.BaseActionHandler;
import org.openbravo.client.kernel.KernelUtils;
import org.openbravo.client.kernel.StaticResourceComponent;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.dal.service.OBQuery;
import org.openbravo.erpCommon.businessUtility.Preferences;
import org.openbravo.erpCommon.utility.PropertyException;
import org.openbravo.model.ad.access.FieldAccess;
import org.openbravo.model.ad.access.TabAccess;
import org.openbravo.model.ad.access.WindowAccess;
import org.openbravo.model.ad.ui.Field;
import org.openbravo.model.ad.ui.Tab;
import org.openbravo.model.ad.ui.Window;
import org.openbravo.service.db.DalConnectionProvider;

/**
 * Computes different settings which may be user/role specific for a certain window.
 * 
 * @author mtaal
 * @see StaticResourceComponent
 */
@ApplicationScoped
public class WindowSettingsActionHandler extends BaseActionHandler {
  private static final Logger log4j = Logger.getLogger(WindowSettingsActionHandler.class);

  @Inject
  private PersonalizationHandler personalizationHandler;

  protected JSONObject execute(Map<String, Object> parameters, String data) {

    try {
      OBContext.setAdminMode();
      final String windowId = (String) parameters.get("windowId");
      final Window window = OBDal.getInstance().get(Window.class, windowId);
      final String roleId = OBContext.getOBContext().getRole().getId();
      final DalConnectionProvider dalConnectionProvider = new DalConnectionProvider();
      final JSONObject jsonUIPattern = new JSONObject();
      for (Tab tab : window.getADTabList()) {
        final boolean readOnlyAccess = org.openbravo.erpCommon.utility.WindowAccessData
            .hasReadOnlyAccess(dalConnectionProvider, roleId, tab.getId());
        String uiPattern = readOnlyAccess ? "RO" : tab.getUIPattern();
        jsonUIPattern.put(tab.getId(), uiPattern);
      }
      final JSONObject json = new JSONObject();
      json.put("uiPattern", jsonUIPattern);
      final String autoSaveStr = Preferences.getPreferenceValue("Autosave", false, OBContext
          .getOBContext().getCurrentClient(), OBContext.getOBContext().getCurrentOrganization(),
          OBContext.getOBContext().getUser(), OBContext.getOBContext().getRole(), window);
      json.put("autoSave", "Y".equals(autoSaveStr));

      try {
        json.put("personalization", personalizationHandler.getPersonalizationForWindow(window));
      } catch (Throwable t) {
        // be robust about errors in the personalization settings
        log4j.error("Error for window " + window, t);
      }

      final String showConfirmationStr = Preferences.getPreferenceValue("ShowConfirmationDefault",
          false, OBContext.getOBContext().getCurrentClient(), OBContext.getOBContext()
              .getCurrentOrganization(), OBContext.getOBContext().getUser(), OBContext
              .getOBContext().getRole(), window);
      json.put("showAutoSaveConfirmation", "Y".equals(showConfirmationStr));

      // Field Level Roles
      final JSONArray tabs = new JSONArray();
      json.put("tabs", tabs);
      for (WindowAccess winAccess : window.getADWindowAccessList()) {
        if (winAccess.getRole().getId().equals(roleId)) {
          for (TabAccess tabAccess : winAccess.getADTabAccessList()) {
            boolean tabEditable = tabAccess.isEditableField();
            final Entity entity = ModelProvider.getInstance().getEntityByTableId(
                tabAccess.getTab().getTable().getId());
            final JSONObject jTab = new JSONObject();
            tabs.put(jTab);
            jTab.put("tabId", tabAccess.getTab().getId());
            jTab.put("updatable", tabEditable);
            final JSONObject jFields = new JSONObject();
            jTab.put("fields", jFields);
            final Set<String> fields = new TreeSet<String>();
            for (Field field : tabAccess.getTab().getADFieldList()) {
              fields.add(KernelUtils.getProperty(entity, field).getName());
            }
            for (FieldAccess fieldAccess : tabAccess.getADFieldAccessList()) {
              final String name = KernelUtils.getProperty(entity, fieldAccess.getField()).getName();
              jFields.put(name, fieldAccess.isEditableField());
              fields.remove(name);
            }
            for (String name : fields) {
              jFields.put(name, tabEditable);
            }
          }
        }
      }

      // Processes without access
      boolean securedProcess = false;
      try {
        securedProcess = "Y".equals(Preferences.getPreferenceValue("SecuredProcess", true,
            OBContext.getOBContext().getCurrentClient(), OBContext.getOBContext()
                .getCurrentOrganization(), OBContext.getOBContext().getUser(), OBContext
                .getOBContext().getRole(), window));
      } catch (PropertyException e) {
        // do nothing, property is not set so securedProcess is false
      }
      if (securedProcess) {
        OBQuery<Field> q = OBDal
            .getInstance()
            .createQuery(
                Field.class,
                " as f where  tab.window = :window "
                    + "and ( (column.oBUIAPPProcess is not null"
                    + " and not exists (select 1 from "
                    + " OBUIAPP_Process_Access a"
                    + " where a.obuiappProcess = f.column.oBUIAPPProcess"
                    + " and a.role.id = :role and a.active=true))"
                    + "or (column.process is not null and "
                    + " not exists (select 1 from ADProcessAccess a where a.process = f.column.process and "
                    + " a.role.id = :role and a.active=true)))" + " order by f.tab");

        q.setNamedParameter("window", window);
        q.setNamedParameter("role", OBContext.getOBContext().getRole().getId());

        final JSONArray processes = new JSONArray();
        json.put("notAccessibleProcesses", processes);
        Tab tab = null;
        JSONObject t;
        JSONArray ps = null;
        for (Field f : q.list()) {
          if (tab == null || !tab.getId().equals(f.getTab().getId())) {
            t = new JSONObject();
            tab = f.getTab();
            ps = new JSONArray();
            t.put("tabId", tab.getId());
            t.put("processes", ps);
            processes.put(t);
          }
          final Entity entity = ModelProvider.getInstance().getEntityByTableId(
              f.getTab().getTable().getId());
          ps.put(KernelUtils.getProperty(f).getName());
        }
      }

      return json;
    } catch (Exception e) {
      throw new OBException(e);
    } finally {
      OBContext.restorePreviousMode();
    }
  }
}
