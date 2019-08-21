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
 * All portions are Copyright (C) 2011-2012 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */


// == OBTextItem ==
// Input for normal strings
isc.ClassFactory.defineClass('OBTextItem', isc.TextItem);

isc.OBTextItem.addProperties({
  operator: 'iContains',
  validateOnExit: true,
  maskSaveLiterals: true,

  validateAgainstMask: true,

  init: function () {

    if (this.mask && this.validateAgainstMask) {
      this.resetMaskValidator(true);
    }

    this.Super('init', arguments);
  },

  resetMaskValidator: function (createNew) {
    if (this.maskValidator && this.validators) {
      this.validators.remove(this.maskValidator);
      delete this.maskValidator;
    }
    if (createNew && this.mask && this.validateAgainstMask) {
      this.maskValidator = isc.clone(isc.Validator.getValidatorDefinition('mask'));
      this.maskValidator.mask = this.createRegExpFromMask(this.mask);
      this.validators = this.validators || [];
      this.validators.push(this.maskValidator);
    }
  },

  createRegExpFromMask: function (mask) {
    var split, i, regexp = '',
        escaped = false;
    if (!mask) {
      return null;
    }
    split = mask.split('');
    for (i = 0; i < split.length; i++) {
      if (escaped) {
        regexp = regexp + '\\' + split[i];
        escaped = false;
        continue;
      }
      if (split[i] === '\\') {
        escaped = true;
        continue;
      } else if (split[i] === '<' || split[i] === '>') {
        // ignore
        continue;
      } else if (split[i] === '0') {
        regexp = regexp + '[0-9-+]';
      } else if (split[i] === '9') {
        regexp = regexp + '[0-9\\s]';
      } else if (split[i] === '#') {
        regexp = regexp + '[\\d]';
      } else if (split[i] === 'L') {
        regexp = regexp + '[A-Za-z]';
      } else if (split[i] === '?') {
        regexp = regexp + '[A-Za-z\\s]';
      } else if (split[i] === 'A') {
        regexp = regexp + '[A-Za-z0-9]';
      } else if (split[i] === 'a') {
        regexp = regexp + '[A-Za-z0-9]';
      } else if (split[i] === 'C') {
        regexp = regexp + '[A-Za-z0-9\\s]';
      } else {
        regexp = regexp + split[i];
      }
    }
    return regexp;
  },

  itemHoverHTML: function (item, form) {
    if (this.isDisabled()) {
      return this.getValue();
    } else if (this.mask) {
      return this.mask;
    }
  },

  setMask: function (mask) {
    this.Super('setMask', arguments);
    this.resetMaskValidator(mask);
  }

});

isc.ClassFactory.defineClass('OBTextFilterItem', isc.OBTextItem);

isc.OBTextFilterItem.addProperties({
  allowExpressions: true,
  validateAgainstMask: false,

  // solve a small bug in the value expressions
  buildValueExpressions: function () {
    var ret = this.Super('buildValueExpressions', arguments);
    if (isc.isA.String(ret) && ret.contains('undefined')) {
      return ret.replace('undefined', '');
    }
    return ret;
  }
});