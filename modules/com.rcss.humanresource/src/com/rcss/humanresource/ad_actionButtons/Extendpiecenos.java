package com.rcss.humanresource.ad_actionButtons;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.hibernate.criterion.Restrictions;
import com.rcss.humanresource.data.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.*;

public class Extendpiecenos extends DalBaseProcess{
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
	
		String id = (String) bundle.getParams().get("Rchr_Piecemaster_ID");		
		RCHRPiecemaster piecemaster = OBDal.getInstance().get(RCHRPiecemaster.class, id);		
		String piecechar = piecemaster.getPiecechar();
		long range = piecemaster.getRange()+1;
		long extend = piecemaster.getIsneeded();
        //Long rangeNew=range;
		long lineno = (range)*10;
			for(int i=1;i<= extend;i++)
			{
				
				RCHRPiecenolist piecenolist = OBProvider.getInstance().get(RCHRPiecenolist.class);
				String genpieceno = piecechar+range;
				piecenolist.setPieceno(genpieceno);
				piecenolist.setLineNo(lineno);
				piecenolist.setRchrPiecemaster(piecemaster);
				piecenolist.setProcess(Boolean.TRUE);
				
				
				OBDal.getInstance().save(piecenolist);
				lineno = lineno+10;
				range++;
			}			
		piecemaster.setExtendpiecenos(Boolean.TRUE);
		piecemaster.setRange(piecemaster.getRange()+extend);
		final OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Generated Extra Piece No's Successfully");
        bundle.setResult(msg);
	
	}
}