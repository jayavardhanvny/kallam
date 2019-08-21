package com.redcarpet.production.ad_process;

import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
/*import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
*/
/**
 *
 * @author Mateen
 */
public class RCSS_CostingBackgroundProcess extends DalBaseProcess {

    String filename = "";
    
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
  /*      try {
            runTransformation(filename);
        } catch (KettleException ex) {
            ex.printStackTrace();
        }
   */
    }

/*
    public static void runTransformation(String filename) throws KettleException {
        KettleEnvironment.init();

        TransMeta transMeta = new TransMeta(filename);
        Trans trans = new Trans(transMeta);

        trans.execute(null); // You can pass arguments instead of null.
        trans.waitUntilFinished();
        if (trans.getErrors() > 0) {
            throw new RuntimeException("There were errors during transformation execution.");
        }

    }
*/
}
