package com.redcarpet.payroll.ad_callouts;

import com.rcss.humanresource.data.RchrAttdProcess;
import com.redcarpet.payroll.util.PayrollUtils;
import java.util.Enumeration;
import javax.servlet.ServletException;
import org.apache.commons.lang.StringUtils;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

/**
 *
 * @author S.A. Mateen
 * @email mateen.syed@rcssgroup.biz
 * @company RedCarpet Software Solutions
 */
public class RCPL_PayrollPeriodSelector extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strPeriodId = info.getStringParameter("inprchrAttdProcessId", null);
        if (!StringUtils.isEmpty(strPeriodId)) {
            
            RchrAttdProcess period = OBDal.getInstance().get(RchrAttdProcess.class, strPeriodId);
            info.addResult("inpstartdate", PayrollUtils.getInstance().getCalloutCompatibleDate(period.getStartingDate()));
            info.addResult("inpenddate", PayrollUtils.getInstance().getCalloutCompatibleDate(period.getEndingDate()));
            int diff = PayrollUtils.getDaysDifference(period.getStartingDate(), period.getEndingDate());
            info.addResult("inpprocessingdays", diff + "");
            
        }
    }
}
