package com.redcarpet.goodsissue.ad_process;

import com.redcarpet.epcg.data.EpcgSeqline;
import com.redcarpet.goodsissue.data.RCGI_MaterialIssue;
import com.redcarpet.goodsissue.data.RcgiMaterialIndent;
import com.redcarpet.goodsissue.data.RcgiMaterialIndentLines;
import org.hibernate.Query;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBModulePrefixRequired;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import java.util.List;

public class IndentComplete extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rcgi_Materialindent_ID").toString();
        RcgiMaterialIndent indent = OBDal.getInstance().get(RcgiMaterialIndent.class, id);
        OBError success = new OBError();
        try {
            indent.setDocumentStatus("CO");
	    indent.setComplete(Boolean.TRUE);
            OBDal.getInstance().commitAndClose();
            success.setType("Success");
            success.setTitle("Success");
            success.setMessage("Yarn Indent Completed successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            success.setType("Error");
            success.setTitle("Error");
            success.setMessage(ex.getMessage());
        }
        bundle.setResult(success);
    }
}

