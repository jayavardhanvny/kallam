/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use. this
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

// == OBTextAreaItem and OBPopupTextArea ==
// Input for large strings, contains 2 widgets one for editing in the form
// and one for in the grid.
isc.ClassFactory.defineClass('OBRichTextItem', isc.RichTextItem);
isc.ClassFactory.defineClass('OBRichTextEditor', isc.RichTextEditor);

isc.OBRichTextItem.addProperties({
  operator: 'iContains',
  validateOnExit: true,
  canvasConstructor: 'OBRichTextEditor',
  canvasProperties: {
    canFocus: true,
    editAreaBackgroundColor: 'transparent',

    // "fontControls", "formatControls", "styleControls" and "colorControls"
    initialControlGroups: ["fontControls", "styleControls", "formatControls"],
    controlGroups: ["fontControls", "styleControls", "formatControls"],

    keyDown: function (event, eventInfo) {
      var me = this;
      if (this.parentElement && typeof this.parentElement.handleItemChange === 'function' && (isc.EH.getKey() === 'Backspace' || isc.EH.getKey() === 'Delete') && !isc.EH.altKeyDown()) {
        var oldValue = this.getValue();
        setTimeout(function () {
          var newValue = me.getValue();
          if (oldValue !== newValue) {
            me._hasChanged = true;
            me.parentElement.handleItemChange(me);
          }
        }, 100);
      }
      var response = OB.KeyboardManager.Shortcuts.monitor('OBViewForm');
      if (response !== false) {
        response = this.Super('keyDown', arguments);
      }
      return response;
    },

    handleFocus: function (hasFocus) {
      if (hasFocus) {
        this.setStyleName(this.styleName + 'Focused');
      } else if (this.styleName.endsWith('Focused')) {
        this.setStyleName(this.styleName.substring(0, this.styleName.length - 'Focused'.length));
      }
      if (hasFocus && !this.hasFocus) {
        this.setFocus(true);
      }
    },

    setDisabled: function (disabled) {
      if (disabled) {
        this.previousStyleName = this.styleName;
        this.setStyleName(this.editorStyleName + 'Disabled');
        this.editArea.setStyleName(this.editAreaStyleName + 'Disabled');
        this.toolbar.hide();
      } else {
        this.setStyleName(this.previousStyleName || this.editorStyleName);
        delete this.previousStyleName;
        this.editArea.setStyleName(this.editAreaStyleName);
        this.toolbar.show();
      }
      this.Super('setDisabled', arguments);
    },

    // autochilds
    editAreaProperties: {
      canFocus: true,
      styleName: 'OBFormFieldStatic',

      keyDown: function (event, eventInfo) {
        var response = OB.KeyboardManager.Shortcuts.monitor('OBViewForm');
        if (response !== false) {
          response = this.Super('keyDown', arguments);
        }
        return response;
      },

      focusChanged: function (hasFocus) {
        this.parentElement.handleFocus(hasFocus);
      }
    },
    toolbarProperties: {
      canFocus: true,

      keyDown: function (event, eventInfo) {
        var response = OB.KeyboardManager.Shortcuts.monitor('OBViewForm');
        if (response !== false) {
          response = this.Super('keyDown', arguments);
        }
        return response;
      },

      focusChanged: function (hasFocus) {
        this.parentElement.handleFocus(hasFocus);
      },

      // autochild of the autochild
      buttonProperties: {
        keyDown: function (event, eventInfo) {
          var response = OB.KeyboardManager.Shortcuts.monitor('OBViewForm');
          if (response !== false) {
            response = this.Super('keyDown', arguments);
          }
          return response;
        },

        focusChanged: function (hasFocus) {
          this.parentElement.parentElement.handleFocus(hasFocus);
        }
      }
    }
  },
  selectOnFocus: false,
  showFocused: true,
  showTitle: true,
  rowSpan: 2,
  init: function () {
    if (this.initStyle) {
      this.initStyle();
    }
    this.Super('init', arguments);
  },

  itemHoverHTML: function (item, form) {
    if (this.isDisabled()) {
      return this.getValue();
    }
  }
});

isc.OBRichTextEditor.addProperties({
  fontPrompt: OB.I18N.getLabel('OBUIAPP_SetFont'),
  fontSizePrompt: OB.I18N.getLabel('OBUIAPP_SetFontSize'),
  linkUrlTitle: OB.I18N.getLabel('OBUIAPP_SetHyperlinkURL'),

  initWidget: function () {
    this.boldSelectionDefaults.prompt = OB.I18N.getLabel('OBUIAPP_MakeSelectionBold');

    this.italicSelectionDefaults.prompt = OB.I18N.getLabel('OBUIAPP_MakeSelectionItalic');
    this.underlineSelectionDefaults.prompt = OB.I18N.getLabel('OBUIAPP_MakeSelectionUnderlined');
    this.strikethroughSelectionDefaults.prompt = OB.I18N.getLabel('OBUIAPP_StrikeThroughSelection');

    this.Super('initWidget', arguments);
  }
});

// used in the grid
isc.ClassFactory.defineClass('OBPopUpRichTextItem', isc.PopUpTextAreaItem);