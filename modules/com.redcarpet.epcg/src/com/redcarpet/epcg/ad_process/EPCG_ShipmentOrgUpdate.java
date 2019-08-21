package com.redcarpet.epcg.ad_process;

import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
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


public class EPCG_ShipmentOrgUpdate extends DalBaseProcess {

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

        EPCGShipmentOrgUpdateData[] data = EPCGShipmentOrgUpdateData.getOrgIds(new DalConnectionProvider());
        for (EPCGShipmentOrgUpdateData line : data) {
        	ShipmentInOutLine lines = OBDal.getInstance().get(ShipmentInOutLine.class, line.mInoutlineId);
        	String lineOrg=lines.getOrganization().getId();
        	String headOrg=lines.getShipmentReceipt().getOrganization().getId();
        	if(lineOrg != headOrg)
        	{
        		System.out.println("inside if, orgs are not same");

        		lines.setOrganization(lines.getShipmentReceipt().getOrganization());
        	} else
        	{
        		//System.out.println("out og if, orgs are same");
        	}
        }
        OBContext.setAdminMode(false);
    }

}
