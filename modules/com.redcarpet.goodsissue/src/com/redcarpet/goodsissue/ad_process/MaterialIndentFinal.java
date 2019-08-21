package com.redcarpet.goodsissue.ad_process;

import com.redcarpet.epcg.data.EpcgSeqline;
import com.redcarpet.goodsissue.data.RCGI_MaterialIssue;
import com.redcarpet.goodsissue.data.RcgiMaterialIndent;
import com.redcarpet.goodsissue.data.RcgiMaterialIndentLines;
import org.hibernate.Query;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import java.util.List;

public class MaterialIndentFinal extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rcgi_Materialindent_ID").toString();
        RcgiMaterialIndent indent = OBDal.getInstance().get(RcgiMaterialIndent.class, id);
        String doctype = "637E0755F7D643AF892C512C8639EAAE";
        OBError success = new OBError();
        try {
            insertDataIntoMaterialIssue(indent, doctype);
            indent.setDocumentStatus("AP");
	    indent.setIndentfinal(Boolean.TRUE);
            updateSequenceNo(getSequenceId(doctype));
            OBDal.getInstance().commitAndClose();
            success.setType("Success");
            success.setTitle("Success");
            success.setMessage("Yarn Indent Finalized completed successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            success.setType("Error");
            success.setTitle("Error");
            success.setMessage(ex.getMessage());
        }
        bundle.setResult(success);
    }

    private void insertDataIntoMaterialIssue(RcgiMaterialIndent indent, String doctype) {
        for (RcgiMaterialIndentLines line : indent.getRCGIMaterialIndentLinesList()) {
            RCGI_MaterialIssue issue = OBProvider.getInstance().get(RCGI_MaterialIssue.class);
            issue.setOrganization(indent.getOrganization());
            issue.setClient(indent.getClient());
            issue.setRchrJobcard(line.getRchrJobcard());
            issue.setMovementDate(indent.getMovementDate());
            issue.setDocumentStatus("AP");
            issue.setDocumentType(OBDal.getInstance().get(DocumentType.class, doctype));
            issue.setRcgiMaterialindentlines(line);
            issue.setDocumentNo(getDocumentNo(doctype));
            if(line.getRchrWarpyarntype()!=null)issue.setRchrWarpyarntype(line.getRchrWarpyarntype());
            if(line.getEpcgVariant()!=null)issue.setEpcgVariant(line.getEpcgVariant());
            OBDal.getInstance().save(issue);
        }
    }

    private void updateSequenceNo(String sequenceId) {
        System.out.println(sequenceId);
        EpcgSeqline seq = OBDal.getInstance().get(EpcgSeqline.class, sequenceId);
        System.out.println(seq.getNextAssignedNumber());
        seq.setNextAssignedNumber(seq.getNextAssignedNumber() + 1);
    }

    private String getSequenceId(String doctype) {
        String hql = " SELECT seqline.id FROM ADSequence seq,Epcg_Seqline seqline,DocumentType doctype " +
                "  WHERE  seqline.sequence.id=seq.id " +
                "  AND  doctype.documentSequence.id=seq.id " +
                "  AND doctype.id=:docid ";
        Query q = OBDal.getInstance().getSession().createQuery(hql);
        q.setParameter("docid", doctype);
        List<String> li = q.list();
        if (li.size() <= 0 || li.isEmpty())
            return "";
        else if (li.get(0) == null || li.get(0).toString() == null)
            return "";
        else
            return li.get(0).toString();
    }

    public String getDocumentNo(String doctype) {
        String hql = " SELECT concat(seqline.prefix,seqline.nextAssignedNumber,seqline.suffix) FROM ADSequence seq,Epcg_Seqline seqline,DocumentType doctype " +
                "  WHERE  seqline.sequence.id=seq.id " +
                "  AND  doctype.documentSequence.id=seq.id " +
                "  AND doctype.id=:docid ";
        Query q = OBDal.getInstance().getSession().createQuery(hql);
        q.setParameter("docid", doctype);
        List<String> li = q.list();
        System.out.println(li.size());
        if (li.size() <= 0 || li.isEmpty())
            return "";
        else if (li.get(0) == null || li.get(0).toString() == null)
            return "";
        else
            return li.get(0).toString();

    }
}
