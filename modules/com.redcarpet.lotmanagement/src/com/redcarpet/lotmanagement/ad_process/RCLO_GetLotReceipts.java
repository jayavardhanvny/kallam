package com.redcarpet.lotmanagement.ad_process;

import com.redcarpet.lotmanagement.data.RCLO_LotReceipt;
import com.redcarpet.lotmanagement.data.RCLO_LotWiseQC;
import java.math.RoundingMode;
import java.util.Date;
import javax.servlet.ServletException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author Mateen
 */
public class RCLO_GetLotReceipts extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        try {
            doIt();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void doIt() throws ServletException {
        OBContext.setAdminMode(true);

        RCLOGetTrxIdData[] data = RCLOGetTrxIdData.getTransactionIds(new DalConnectionProvider());
        for (RCLOGetTrxIdData trx : data) {
            MaterialTransaction mtx1 = OBDal.getInstance().get(MaterialTransaction.class, trx.mTransactionId);
            RCLO_LotReceipt lotReceipt = OBProvider.getInstance().get(RCLO_LotReceipt.class);
            lotReceipt.setClient(mtx1.getClient());
            lotReceipt.setOrganization(mtx1.getOrganization());
            lotReceipt.setDocumentNo(getDocumentNo(mtx1.getOrganization().getId()));
            lotReceipt.setMovementDate(mtx1.getTransactionProcessDate());
            lotReceipt.setGoodsShipmentLine(mtx1.getGoodsShipmentLine());
            lotReceipt.setRCPRProductionLine(mtx1.getRcprPrproduct());
            lotReceipt.setTotalQtyPerLot(mtx1.getMovementQuantity());
            if (mtx1.getGoodsShipmentLine() != null) {
                lotReceipt.setNoOfBales(mtx1.getGoodsShipmentLine().getRcloNoofbales());
            } else {
                lotReceipt.setNoOfBales(mtx1.getRcprPrproduct().getNoOfBales());
            }
            if (lotReceipt.getNoOfBales().doubleValue() != 0) {
                lotReceipt.setAverageQtyPerBale(lotReceipt.getTotalQtyPerLot().divide(lotReceipt.getNoOfBales(), 5, RoundingMode.HALF_UP));
            }
            lotReceipt.setOpenQuantity(lotReceipt.getTotalQtyPerLot());
            lotReceipt.setOpenBales(lotReceipt.getNoOfBales());
            OBDal.getInstance().save(lotReceipt);

            RCLO_LotWiseQC qc = OBProvider.getInstance().get(RCLO_LotWiseQC.class);
            qc.setOrganization(lotReceipt.getOrganization());
            qc.setDocumentNo(getDocumentNo(lotReceipt.getOrganization().getId(), 0));
            qc.setMovementDate(new Date());
            qc.setLotReceipt(lotReceipt);
            OBDal.getInstance().save(qc);
        }
        OBContext.setAdminMode(false);
    }

    private String getDocumentNo(String clientId, int x) {

        final String documentNo = Utility.getDocumentNo(new DalConnectionProvider(), clientId,
                RCLO_LotWiseQC.TABLE_NAME, true);
        return documentNo;
    }
    private String getDocumentNo(String strOrgId) {
        String strDocumentNo = "";
        int i = 0;
        try {
            RCLOGetTrxIdData[] data = RCLOGetTrxIdData.getDocumentNumber(new DalConnectionProvider(), strOrgId, strOrgId);
            strDocumentNo = data[i].documentno;
            Sequence sequence = OBDal.getInstance().get(Sequence.class, data[i].adSequenceId);
            sequence.setNextAssignedNumber(sequence.getNextAssignedNumber()+1);
        } catch (Exception ex) {
            System.out.println("Please check documentno configuration: Prefix has been hardcoded in XSQL");
            ex.printStackTrace();
        }

        return strDocumentNo;
    }
}
