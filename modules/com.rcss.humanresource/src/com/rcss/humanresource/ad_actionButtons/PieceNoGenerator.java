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

public class PieceNoGenerator extends DalBaseProcess{
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
	
		String id = (String) bundle.getParams().get("Rchr_Piecemaster_ID");		
		RCHRPiecemaster piecemaster = OBDal.getInstance().get(RCHRPiecemaster.class, id);		
		String piecechar = piecemaster.getPiecechar();
		long range = piecemaster.getRange();
		int val = (int) range;	
		long lineno = 10;
	
			for(int i=1;i<=val;i++)
			{
				
				RCHRPiecenolist piecenolist = OBProvider.getInstance().get(RCHRPiecenolist.class);
				String genpieceno = piecechar+i;
				piecenolist.setPieceno(genpieceno);
				piecenolist.setLineNo(lineno);
				piecenolist.setRchrPiecemaster(piecemaster);
				piecenolist.setProcess(Boolean.TRUE);
				
				OBDal.getInstance().save(piecenolist);
				lineno = lineno+10;
			}			
			piecemaster.setProcess(Boolean.TRUE);
			
			final OBError msg = new OBError();
	        msg.setType("Success");
	        msg.setTitle("Done");
	        msg.setMessage("Generated Piece No's Successfully");
	        bundle.setResult(msg);
			
	}
}