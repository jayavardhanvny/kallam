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

isc.OBCalendarTabSet.addProperties({
  tabBarProperties: {
    simpleTabBaseStyle: 'OBTabBarButtonMain',
    paneContainerClassName: 'OBTabSetMainContainer',
    buttonConstructor: isc.OBTabBarButton,

    buttonProperties: {
      // prevent the orange hats
      customState: 'Inactive',

      src: '',
      capSize: 14,
      titleStyle: 'OBTabBarButtonMainTitle'
    }
  },
  tabBarPosition: 'top',
  tabBarAlign: 'left',
  width: '100%',
  height: '100%',
  overflow: 'hidden',

  showTabPicker: false,

  // get rid of the margin around the content of a pane
  paneMargin: 0,
  paneContainerMargin: 0,
  paneContainerPadding: 0,
  showPaneContainerEdges: false,

  useSimpleTabs: true,
  tabBarThickness: 30,
  styleName: 'OBTabSetMain',
  simpleTabBaseStyle: 'OBTabBarButtonMain',
  paneContainerClassName: 'OBTabSetMainContainer',

  scrollerSrc: OB.Styles.skinsPath + 'Default/org.openbravo.client.application/images/tab/tabBarButtonMain_OverflowIcon.png',
  pickerButtonSrc: OB.Styles.skinsPath + 'Default/org.openbravo.client.application/images/tab/tabBarButtonMain_OverflowIconPicker.png'
});

OB.Styles.OBCalendar = {
  controlsTopMarging: 6,
  eventWindowStyle: 'OBEventWindow',
  datePickerButton: {
    src: OB.Styles.skinsPath + 'Default/org.openbravo.client.application/images/form/date_control.png',
    width: 21,
    height: 21
  },
  addEventButton: {
    src: OB.Styles.skinsPath + 'Default/org.openbravo.client.application/images/form/add_icon.png',
    // It doesn't exist yet
    width: 21,
    height: 21
  },
  previousButton: {
    src: OB.Styles.skinsPath + 'Default/org.openbravo.client.application/images/statusbar/iconButton-previous.png',
    width: 20,
    height: 20
  },
  nextButton: {
    src: OB.Styles.skinsPath + 'Default/org.openbravo.client.application/images/statusbar/iconButton-next.png',
    width: 20,
    height: 20
  },
  workdayBaseStyle: 'OBCalendarGridCellWorkday',
  selectedCellStyle: 'OBCalendarGridCellSelected',
  dayView_baseStyle: 'OBCalendarGridCell',
  weekView_baseStyle: 'OBCalendarGridCell',
  weekView_headerBaseStyle: 'OBCalendarGridHeaderCell',
  monthView_baseStyle: 'OBCalendarGridCell',
  monthView_headerBaseStyle: 'OBCalendarGridHeaderCell'
};