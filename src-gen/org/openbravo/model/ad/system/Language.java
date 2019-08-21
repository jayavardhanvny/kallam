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
package org.openbravo.model.ad.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.alert.AlertRuleTrl;
import org.openbravo.model.ad.domain.ListTrl;
import org.openbravo.model.ad.domain.ReferenceTrl;
import org.openbravo.model.ad.ui.ElementTrl;
import org.openbravo.model.ad.ui.FieldGroupTrl;
import org.openbravo.model.ad.ui.FieldTrl;
import org.openbravo.model.ad.ui.FormTrl;
import org.openbravo.model.ad.ui.MenuTrl;
import org.openbravo.model.ad.ui.MessageTrl;
import org.openbravo.model.ad.ui.ProcessParameterTrl;
import org.openbravo.model.ad.ui.ProcessTrl;
import org.openbravo.model.ad.ui.TabTrl;
import org.openbravo.model.ad.ui.TextInterfaceTrl;
import org.openbravo.model.ad.ui.WindowTrl;
import org.openbravo.model.common.businesspartner.GreetingTrl;
import org.openbravo.model.common.currency.CurrencyTrl;
import org.openbravo.model.common.enterprise.ADMonthTrl;
import org.openbravo.model.common.enterprise.DocumentTypeTrl;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.geography.CountryTrl;
import org.openbravo.model.common.plm.ProductTrl;
import org.openbravo.model.common.uom.UOMTrl;
import org.openbravo.model.financialmgmt.accounting.coa.ElementValueTrl;
import org.openbravo.model.financialmgmt.payment.PaymentTermTrl;
import org.openbravo.model.financialmgmt.tax.TaxCategoryTrl;
import org.openbravo.model.financialmgmt.tax.TaxTrl;
import org.openbravo.userinterface.selector.SelectorFieldTrl;
import org.openbravo.userinterface.selector.SelectorTrl;
/**
 * Entity class for entity ADLanguage (stored in table AD_Language).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class Language extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Language";
    public static final String ENTITY_NAME = "ADLanguage";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_LANGUAGE = "language";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_NAME = "name";
    public static final String PROPERTY_ISOLANGUAGECODE = "iSOLanguageCode";
    public static final String PROPERTY_ISOCOUNTRYCODE = "iSOCountryCode";
    public static final String PROPERTY_BASELANGUAGE = "baseLanguage";
    public static final String PROPERTY_SYSTEMLANGUAGE = "systemLanguage";
    public static final String PROPERTY_PROCESSNOW = "processNow";
    public static final String PROPERTY_PIXELSIZE = "pixelSize";
    public static final String PROPERTY_TRANSLATEDBY = "translatedBy";
    public static final String PROPERTY_RTLLANGUAGE = "rTLLanguage";
    public static final String PROPERTY_ADALERTRULETRLLIST = "aDAlertRuleTrlList";
    public static final String PROPERTY_ADELEMENTTRLLIST = "aDElementTrlList";
    public static final String PROPERTY_ADFIELDGROUPTRLLIST = "aDFieldGroupTrlList";
    public static final String PROPERTY_ADFIELDTRLLIST = "aDFieldTrlList";
    public static final String PROPERTY_ADFORMTRLLIST = "aDFormTrlList";
    public static final String PROPERTY_ADLISTTRLLIST = "aDListTrlList";
    public static final String PROPERTY_ADMENUTRLLIST = "aDMenuTrlList";
    public static final String PROPERTY_ADMESSAGETRLLIST = "aDMessageTrlList";
    public static final String PROPERTY_ADMONTHTRLLIST = "aDMonthTrlList";
    public static final String PROPERTY_ADPROCESSPARAMETERTRLLIST = "aDProcessParameterTrlList";
    public static final String PROPERTY_ADPROCESSTRLLIST = "aDProcessTrlList";
    public static final String PROPERTY_ADREFERENCETRLLIST = "aDReferenceTrlList";
    public static final String PROPERTY_ADTABTRLLIST = "aDTabTrlList";
    public static final String PROPERTY_ADTEXTINTERFACETRLLIST = "aDTextInterfaceTrlList";
    public static final String PROPERTY_ADWINDOWTRLLIST = "aDWindowTrlList";
    public static final String PROPERTY_COUNTRYTRLLIST = "countryTrlList";
    public static final String PROPERTY_CURRENCYTRLLIST = "currencyTrlList";
    public static final String PROPERTY_DOCUMENTTYPETRLLIST = "documentTypeTrlList";
    public static final String PROPERTY_FINANCIALMGMTELEMENTVALUETRLLIST = "financialMgmtElementValueTrlList";
    public static final String PROPERTY_FINANCIALMGMTPAYMENTTERMTRLLIST = "financialMgmtPaymentTermTrlList";
    public static final String PROPERTY_FINANCIALMGMTTAXCATEGORYTRLLIST = "financialMgmtTaxCategoryTrlList";
    public static final String PROPERTY_FINANCIALMGMTTAXTRLLIST = "financialMgmtTaxTrlList";
    public static final String PROPERTY_GREETINGTRLLIST = "greetingTrlList";
    public static final String PROPERTY_OBUISELSELECTORFIELDTRLLIST = "oBUISELSelectorFieldTrlList";
    public static final String PROPERTY_OBUISELSELECTORTRLLIST = "oBUISELSelectorTrlList";
    public static final String PROPERTY_PRODUCTTRLLIST = "productTrlList";
    public static final String PROPERTY_UOMTRLLIST = "uOMTrlList";

    public Language() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_BASELANGUAGE, false);
        setDefaultValue(PROPERTY_SYSTEMLANGUAGE, false);
        setDefaultValue(PROPERTY_PROCESSNOW, false);
        setDefaultValue(PROPERTY_RTLLANGUAGE, false);
        setDefaultValue(PROPERTY_ADALERTRULETRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADELEMENTTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADFIELDGROUPTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADFIELDTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADFORMTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADLISTTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADMENUTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADMESSAGETRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADMONTHTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPROCESSPARAMETERTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADPROCESSTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADREFERENCETRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTABTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADTEXTINTERFACETRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_ADWINDOWTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_COUNTRYTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_CURRENCYTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_DOCUMENTTYPETRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTELEMENTVALUETRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTPAYMENTTERMTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXCATEGORYTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_FINANCIALMGMTTAXTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_GREETINGTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUISELSELECTORFIELDTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_OBUISELSELECTORTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PRODUCTTRLLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_UOMTRLLIST, new ArrayList<Object>());
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    public String getLanguage() {
        return (String) get(PROPERTY_LANGUAGE);
    }

    public void setLanguage(String language) {
        set(PROPERTY_LANGUAGE, language);
    }

    public Client getClient() {
        return (Client) get(PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
        set(PROPERTY_CLIENT, client);
    }

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public String getName() {
        return (String) get(PROPERTY_NAME);
    }

    public void setName(String name) {
        set(PROPERTY_NAME, name);
    }

    public String getISOLanguageCode() {
        return (String) get(PROPERTY_ISOLANGUAGECODE);
    }

    public void setISOLanguageCode(String iSOLanguageCode) {
        set(PROPERTY_ISOLANGUAGECODE, iSOLanguageCode);
    }

    public String getISOCountryCode() {
        return (String) get(PROPERTY_ISOCOUNTRYCODE);
    }

    public void setISOCountryCode(String iSOCountryCode) {
        set(PROPERTY_ISOCOUNTRYCODE, iSOCountryCode);
    }

    public Boolean isBaseLanguage() {
        return (Boolean) get(PROPERTY_BASELANGUAGE);
    }

    public void setBaseLanguage(Boolean baseLanguage) {
        set(PROPERTY_BASELANGUAGE, baseLanguage);
    }

    public Boolean isSystemLanguage() {
        return (Boolean) get(PROPERTY_SYSTEMLANGUAGE);
    }

    public void setSystemLanguage(Boolean systemLanguage) {
        set(PROPERTY_SYSTEMLANGUAGE, systemLanguage);
    }

    public Boolean isProcessNow() {
        return (Boolean) get(PROPERTY_PROCESSNOW);
    }

    public void setProcessNow(Boolean processNow) {
        set(PROPERTY_PROCESSNOW, processNow);
    }

    public Long getPixelSize() {
        return (Long) get(PROPERTY_PIXELSIZE);
    }

    public void setPixelSize(Long pixelSize) {
        set(PROPERTY_PIXELSIZE, pixelSize);
    }

    public String getTranslatedBy() {
        return (String) get(PROPERTY_TRANSLATEDBY);
    }

    public void setTranslatedBy(String translatedBy) {
        set(PROPERTY_TRANSLATEDBY, translatedBy);
    }

    public Boolean isRTLLanguage() {
        return (Boolean) get(PROPERTY_RTLLANGUAGE);
    }

    public void setRTLLanguage(Boolean rTLLanguage) {
        set(PROPERTY_RTLLANGUAGE, rTLLanguage);
    }

    @SuppressWarnings("unchecked")
    public List<AlertRuleTrl> getADAlertRuleTrlList() {
        return (List<AlertRuleTrl>) get(PROPERTY_ADALERTRULETRLLIST);
    }

    public void setADAlertRuleTrlList(List<AlertRuleTrl> aDAlertRuleTrlList) {
        set(PROPERTY_ADALERTRULETRLLIST, aDAlertRuleTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<ElementTrl> getADElementTrlList() {
        return (List<ElementTrl>) get(PROPERTY_ADELEMENTTRLLIST);
    }

    public void setADElementTrlList(List<ElementTrl> aDElementTrlList) {
        set(PROPERTY_ADELEMENTTRLLIST, aDElementTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<FieldGroupTrl> getADFieldGroupTrlList() {
        return (List<FieldGroupTrl>) get(PROPERTY_ADFIELDGROUPTRLLIST);
    }

    public void setADFieldGroupTrlList(List<FieldGroupTrl> aDFieldGroupTrlList) {
        set(PROPERTY_ADFIELDGROUPTRLLIST, aDFieldGroupTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<FieldTrl> getADFieldTrlList() {
        return (List<FieldTrl>) get(PROPERTY_ADFIELDTRLLIST);
    }

    public void setADFieldTrlList(List<FieldTrl> aDFieldTrlList) {
        set(PROPERTY_ADFIELDTRLLIST, aDFieldTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<FormTrl> getADFormTrlList() {
        return (List<FormTrl>) get(PROPERTY_ADFORMTRLLIST);
    }

    public void setADFormTrlList(List<FormTrl> aDFormTrlList) {
        set(PROPERTY_ADFORMTRLLIST, aDFormTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<ListTrl> getADListTrlList() {
        return (List<ListTrl>) get(PROPERTY_ADLISTTRLLIST);
    }

    public void setADListTrlList(List<ListTrl> aDListTrlList) {
        set(PROPERTY_ADLISTTRLLIST, aDListTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<MenuTrl> getADMenuTrlList() {
        return (List<MenuTrl>) get(PROPERTY_ADMENUTRLLIST);
    }

    public void setADMenuTrlList(List<MenuTrl> aDMenuTrlList) {
        set(PROPERTY_ADMENUTRLLIST, aDMenuTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<MessageTrl> getADMessageTrlList() {
        return (List<MessageTrl>) get(PROPERTY_ADMESSAGETRLLIST);
    }

    public void setADMessageTrlList(List<MessageTrl> aDMessageTrlList) {
        set(PROPERTY_ADMESSAGETRLLIST, aDMessageTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<ADMonthTrl> getADMonthTrlList() {
        return (List<ADMonthTrl>) get(PROPERTY_ADMONTHTRLLIST);
    }

    public void setADMonthTrlList(List<ADMonthTrl> aDMonthTrlList) {
        set(PROPERTY_ADMONTHTRLLIST, aDMonthTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessParameterTrl> getADProcessParameterTrlList() {
        return (List<ProcessParameterTrl>) get(PROPERTY_ADPROCESSPARAMETERTRLLIST);
    }

    public void setADProcessParameterTrlList(List<ProcessParameterTrl> aDProcessParameterTrlList) {
        set(PROPERTY_ADPROCESSPARAMETERTRLLIST, aDProcessParameterTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessTrl> getADProcessTrlList() {
        return (List<ProcessTrl>) get(PROPERTY_ADPROCESSTRLLIST);
    }

    public void setADProcessTrlList(List<ProcessTrl> aDProcessTrlList) {
        set(PROPERTY_ADPROCESSTRLLIST, aDProcessTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<ReferenceTrl> getADReferenceTrlList() {
        return (List<ReferenceTrl>) get(PROPERTY_ADREFERENCETRLLIST);
    }

    public void setADReferenceTrlList(List<ReferenceTrl> aDReferenceTrlList) {
        set(PROPERTY_ADREFERENCETRLLIST, aDReferenceTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<TabTrl> getADTabTrlList() {
        return (List<TabTrl>) get(PROPERTY_ADTABTRLLIST);
    }

    public void setADTabTrlList(List<TabTrl> aDTabTrlList) {
        set(PROPERTY_ADTABTRLLIST, aDTabTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<TextInterfaceTrl> getADTextInterfaceTrlList() {
        return (List<TextInterfaceTrl>) get(PROPERTY_ADTEXTINTERFACETRLLIST);
    }

    public void setADTextInterfaceTrlList(List<TextInterfaceTrl> aDTextInterfaceTrlList) {
        set(PROPERTY_ADTEXTINTERFACETRLLIST, aDTextInterfaceTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<WindowTrl> getADWindowTrlList() {
        return (List<WindowTrl>) get(PROPERTY_ADWINDOWTRLLIST);
    }

    public void setADWindowTrlList(List<WindowTrl> aDWindowTrlList) {
        set(PROPERTY_ADWINDOWTRLLIST, aDWindowTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<CountryTrl> getCountryTrlList() {
        return (List<CountryTrl>) get(PROPERTY_COUNTRYTRLLIST);
    }

    public void setCountryTrlList(List<CountryTrl> countryTrlList) {
        set(PROPERTY_COUNTRYTRLLIST, countryTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<CurrencyTrl> getCurrencyTrlList() {
        return (List<CurrencyTrl>) get(PROPERTY_CURRENCYTRLLIST);
    }

    public void setCurrencyTrlList(List<CurrencyTrl> currencyTrlList) {
        set(PROPERTY_CURRENCYTRLLIST, currencyTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<DocumentTypeTrl> getDocumentTypeTrlList() {
        return (List<DocumentTypeTrl>) get(PROPERTY_DOCUMENTTYPETRLLIST);
    }

    public void setDocumentTypeTrlList(List<DocumentTypeTrl> documentTypeTrlList) {
        set(PROPERTY_DOCUMENTTYPETRLLIST, documentTypeTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<ElementValueTrl> getFinancialMgmtElementValueTrlList() {
        return (List<ElementValueTrl>) get(PROPERTY_FINANCIALMGMTELEMENTVALUETRLLIST);
    }

    public void setFinancialMgmtElementValueTrlList(List<ElementValueTrl> financialMgmtElementValueTrlList) {
        set(PROPERTY_FINANCIALMGMTELEMENTVALUETRLLIST, financialMgmtElementValueTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<PaymentTermTrl> getFinancialMgmtPaymentTermTrlList() {
        return (List<PaymentTermTrl>) get(PROPERTY_FINANCIALMGMTPAYMENTTERMTRLLIST);
    }

    public void setFinancialMgmtPaymentTermTrlList(List<PaymentTermTrl> financialMgmtPaymentTermTrlList) {
        set(PROPERTY_FINANCIALMGMTPAYMENTTERMTRLLIST, financialMgmtPaymentTermTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxCategoryTrl> getFinancialMgmtTaxCategoryTrlList() {
        return (List<TaxCategoryTrl>) get(PROPERTY_FINANCIALMGMTTAXCATEGORYTRLLIST);
    }

    public void setFinancialMgmtTaxCategoryTrlList(List<TaxCategoryTrl> financialMgmtTaxCategoryTrlList) {
        set(PROPERTY_FINANCIALMGMTTAXCATEGORYTRLLIST, financialMgmtTaxCategoryTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<TaxTrl> getFinancialMgmtTaxTrlList() {
        return (List<TaxTrl>) get(PROPERTY_FINANCIALMGMTTAXTRLLIST);
    }

    public void setFinancialMgmtTaxTrlList(List<TaxTrl> financialMgmtTaxTrlList) {
        set(PROPERTY_FINANCIALMGMTTAXTRLLIST, financialMgmtTaxTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<GreetingTrl> getGreetingTrlList() {
        return (List<GreetingTrl>) get(PROPERTY_GREETINGTRLLIST);
    }

    public void setGreetingTrlList(List<GreetingTrl> greetingTrlList) {
        set(PROPERTY_GREETINGTRLLIST, greetingTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<SelectorFieldTrl> getOBUISELSelectorFieldTrlList() {
        return (List<SelectorFieldTrl>) get(PROPERTY_OBUISELSELECTORFIELDTRLLIST);
    }

    public void setOBUISELSelectorFieldTrlList(List<SelectorFieldTrl> oBUISELSelectorFieldTrlList) {
        set(PROPERTY_OBUISELSELECTORFIELDTRLLIST, oBUISELSelectorFieldTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<SelectorTrl> getOBUISELSelectorTrlList() {
        return (List<SelectorTrl>) get(PROPERTY_OBUISELSELECTORTRLLIST);
    }

    public void setOBUISELSelectorTrlList(List<SelectorTrl> oBUISELSelectorTrlList) {
        set(PROPERTY_OBUISELSELECTORTRLLIST, oBUISELSelectorTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<ProductTrl> getProductTrlList() {
        return (List<ProductTrl>) get(PROPERTY_PRODUCTTRLLIST);
    }

    public void setProductTrlList(List<ProductTrl> productTrlList) {
        set(PROPERTY_PRODUCTTRLLIST, productTrlList);
    }

    @SuppressWarnings("unchecked")
    public List<UOMTrl> getUOMTrlList() {
        return (List<UOMTrl>) get(PROPERTY_UOMTRLLIST);
    }

    public void setUOMTrlList(List<UOMTrl> uOMTrlList) {
        set(PROPERTY_UOMTRLLIST, uOMTrlList);
    }

}
