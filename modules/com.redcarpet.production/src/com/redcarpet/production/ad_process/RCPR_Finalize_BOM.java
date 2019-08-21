/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.production.ad_process;

import java.util.Iterator;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.materialmgmt.transaction.ProductionTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
 *
 * @author icm
 */
public class RCPR_Finalize_BOM extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

        String id = bundle.getParams().get("Rcpr_Prbom_ID").toString();
        ProductionTransaction trx = OBDal.getInstance().get(ProductionTransaction.class, id);
        //trx.setRcprCreate(Boolean.TRUE);
        OBError err = new OBError();
        err.setType("Success");
        err.setTitle("Success");
        err.setMessage("Process Completed Successfully");
        bundle.setResult(err);
    }
}