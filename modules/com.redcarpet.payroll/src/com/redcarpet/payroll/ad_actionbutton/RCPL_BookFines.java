/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.payroll.ad_actionbutton;

import com.redcarpet.payroll.data.RCPL_Fine;
import com.redcarpet.payroll.data.RCPL_FineLine;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
 *
 * @author mateen
 */
public class RCPL_BookFines extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

        OBContext.setAdminMode();
        OBError err = new OBError();
        err.setMessage("Process Completed Successfully");
        err.setTitle("Success");
        err.setType("Success");

        String id = bundle.getParams().get("Rcpl_Fine_ID").toString();
        RCPL_Fine fine = OBDal.getInstance().get(RCPL_Fine.class, id);
        try {
            fine.setBooked(Boolean.TRUE);
            for(RCPL_FineLine line:fine.getRCPLFinelineList()){
                line.setBooked(Boolean.TRUE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            err.setMessage(e.getMessage());
            err.setTitle("Error");
            err.setType("Error");
            fine.setBooked(Boolean.FALSE);
        }
        fine.setBooked(Boolean.TRUE);
        bundle.setResult(err);
        OBContext.restorePreviousMode();
    }

}
