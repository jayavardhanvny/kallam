package com.rcss.humanresource.util;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.ui.Process;
import org.openbravo.model.ad.ui.ProcessExecution;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class ProcessStatus {
	public static String PREVIOUS_PROCESS_RUNNING = "Previous Process is already Running...!";
    private static final Logger logger = Logger.getLogger(ProcessStatus.class);
    public ProcessBundle processBundle = null;
    public String processing = null;
    public ProcessStatus(ProcessBundle bundle, String processing){
        this.processBundle = bundle;
        this.processing = processing;
    }
    public Boolean isProcessing(){
        OBCriteria<ProcessExecution> processExecutionOBCriteria = OBDal.getInstance().createCriteria(ProcessExecution.class);
        Process currentProcessObject = OBDal.getInstance().get(Process.class, processBundle.getProcessId());
        processExecutionOBCriteria.add(Restrictions.eq(ProcessExecution.PROPERTY_PROCESS, currentProcessObject));
        processExecutionOBCriteria.add(Restrictions.eq(ProcessExecution.PROPERTY_STATUS, DalBaseProcess.PROCESSING));
        logger.info("Size of Current Process "+processExecutionOBCriteria.list().size());
        if (processExecutionOBCriteria.list().size()>1)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }
}
