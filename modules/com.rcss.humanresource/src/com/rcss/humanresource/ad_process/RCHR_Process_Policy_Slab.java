package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.RCHR_Emp_Policy;
import com.rcss.humanresource.data.RCHR_PolicySlab;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import org.apache.commons.lang.StringUtils;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
 *
 * @author S.A. Mateen
 * @description Calculates Policy slab
 */
public class RCHR_Process_Policy_Slab extends DalBaseProcess {

    private final String PAYMENT_MONTHLY = "Monthly";
    private final String PAYMENT_QUATERLY = "Quaterly";
    private final String PAYMENT_HALFYEARLY = "Half Yearly";
    private final String PAYMENT_YEARLY = "Yearly";

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

        String id = bundle.getParams().get("RCHR_Emp_Policy_ID").toString();
        System.out.println("id is " + id);
        RCHR_Emp_Policy emp_Policy = OBDal.getInstance().get(RCHR_Emp_Policy.class, id);
        if (StringUtils.equalsIgnoreCase(emp_Policy.getPaymentType(), PAYMENT_MONTHLY)) {
            insertPolicySlabs(emp_Policy, 12);
        } else if (StringUtils.equalsIgnoreCase(emp_Policy.getPaymentType(), PAYMENT_HALFYEARLY)) {
            insertPolicySlabs(emp_Policy, 2);
        } else if (StringUtils.equalsIgnoreCase(emp_Policy.getPaymentType(), PAYMENT_QUATERLY)) {
            insertPolicySlabs(emp_Policy, 3);
        } else if (StringUtils.equalsIgnoreCase(emp_Policy.getPaymentType(), PAYMENT_YEARLY)) {
            insertPolicySlabs(emp_Policy, 1);
        }
        emp_Policy.setProcess(Boolean.TRUE);
    }

    public void insertPolicySlabs(RCHR_Emp_Policy emp_Policy, int payterm) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(emp_Policy.getPolicyIssueDate());
        for (int i = 1; i <= payterm; i++) {
            if (payterm == 12) {
                RCHR_PolicySlab line = OBProvider.getInstance().get(RCHR_PolicySlab.class);
                line.setEmployee(emp_Policy.getEmployee());
                line.setPolicyName(emp_Policy.getPolicyName());
                line.setFromDate(cal.getTime());
                cal.add(Calendar.MONTH, +1);
                line.setToDate(cal.getTime());
                line.setPermium(emp_Policy.getPermiumPerYear().divide(new BigDecimal(payterm), MathContext.DECIMAL32));
                OBDal.getInstance().save(line);
            } else if (payterm == 2) {
                RCHR_PolicySlab line = OBProvider.getInstance().get(RCHR_PolicySlab.class);
                line.setEmployee(emp_Policy.getEmployee());
                line.setPolicyName(emp_Policy.getPolicyName());
                line.setFromDate(cal.getTime());
                cal.add(Calendar.MONTH, +6);
                line.setToDate(cal.getTime());
                line.setPermium(emp_Policy.getPermiumPerYear().divide(new BigDecimal(payterm), MathContext.DECIMAL32));
                OBDal.getInstance().save(line);
            } else if (payterm == 3) {
                RCHR_PolicySlab line = OBProvider.getInstance().get(RCHR_PolicySlab.class);
                line.setEmployee(emp_Policy.getEmployee());
                line.setPolicyName(emp_Policy.getPolicyName());
                line.setFromDate(cal.getTime());
                cal.add(Calendar.MONTH, +3);
                line.setToDate(cal.getTime());
                line.setPermium(emp_Policy.getPermiumPerYear().divide(new BigDecimal(payterm), MathContext.DECIMAL32));
                OBDal.getInstance().save(line);
            } else {
                RCHR_PolicySlab line = OBProvider.getInstance().get(RCHR_PolicySlab.class);
                line.setEmployee(emp_Policy.getEmployee());
                line.setPolicyName(emp_Policy.getPolicyName());
                line.setFromDate(emp_Policy.getPolicyIssueDate());
                line.setToDate(emp_Policy.getPolicyLapseDate());
                line.setPermium(emp_Policy.getPermiumPerYear().divide(new BigDecimal(payterm), MathContext.DECIMAL32));
                OBDal.getInstance().save(line);
            }

        }
    }
}
