package com.rcss.humanresource.ad_callouts;

import com.rcss.humanresource.data.RCHR_Bank;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

/**
 *
 * @author S.A. Mateen
 */
public class RCHR_Bank_Branch_Callout extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        String bankId = info.getStringParameter("inprchrBankId", null);
        RCHR_Bank bank = OBDal.getInstance().get(RCHR_Bank.class, bankId);
        info.addResult("inpbranchname", bank.getBranchName());
        
    }
    
}
