package com.rcss.humanresource.ad_callouts;

import com.rcss.humanresource.data.RCHR_Bank;
import com.rcss.humanresource.data.RCHR_Block;
import com.rcss.humanresource.data.RCHR_BlockLines;
import java.util.List;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

/**
 *
 * @author S.A. Mateen
 */
public class RCHR_Get_BlockLines extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        System.out.println("RCHR_Get_BlockLines inside");
        String strBlockId = info.getStringParameter("inprchrBlockId", null);
        RCHR_Block block = OBDal.getInstance().get(RCHR_Block.class, strBlockId);
        List<RCHR_BlockLines> lines = block.getRCHRBlockLinesList();
        info.addSelect("inprchrBlocklinesId");
        for(RCHR_BlockLines line : lines){
            info.addSelectResult(line.getId(), line.getIdentifier());
        }
        info.endSelect();
        
//info ["inpadOrgId","inpisactive","inproomno","inprchrBlockId","inprchrBlocklinesId","inpelectricalsubsidyunits","inpisvacant"]
        
    }
}
