package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import com.rcss.humanresource.data.*;
import com.redcarpet.payroll.data.RCPLLoom;
import com.redcarpet.payroll.data.RCPLLoomCategory;
import com.redcarpet.payroll.data.RCPL_Insploomctgry;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;

public class SetInspLoomcategory extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	
        String strndId = info.getStringParameter("inprcplLoomId", null);
        
        RCPLLoom loom = OBDal.getInstance().get(RCPLLoom.class, strndId);
        
        info.addResult("inprcplInsploomctgryId",loom.getRcplInsploomctgry().getId());
       
    }
    
}
