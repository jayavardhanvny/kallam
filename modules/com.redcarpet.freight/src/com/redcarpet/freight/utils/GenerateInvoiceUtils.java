/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.freight.utils;

import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.common.invoice.Invoice;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author S.A. Mateen
 */
public class GenerateInvoiceUtils {

    public static String getInvoiceDocumentNo(ShipmentInOut shipment, String documentType, VariablesSecureApp vars) {
        return Utility.getDocumentNo(new DalConnectionProvider(), vars, "167", Invoice.TABLE_NAME, documentType, documentType, false, true);
    }
}
