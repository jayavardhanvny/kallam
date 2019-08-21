package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;
import com.redcarpet.payroll.util.PayrollUtils;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import java.util.Calendar;
import java.util.logging.Logger;

import com.rcss.humanresource.data.*;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;

public class RchrAutodrawingCallout extends SimpleCallout{
	protected Logger logger = Logger.getLogger("RchrAutodrawingCallout.java");
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	String tabId = info.getStringParameter("inpTabId", null);
    	String tableId = info.getStringParameter("inpTableId", null);
    	if(tabId.equals("6D7647B9EF4E431CB34FFEACF4CF7979")){ // Looms Production Header by Selection the Beam get the Details
    		String strndId = info.getStringParameter("inprchrBeamLinesId", null);
    		/*RCHR_Beam beams = OBDal.getInstance().get(RCHR_Beam.class, strndId);
    		OBCriteria<RchrBeamLines> obCriteria = OBDal.getInstance().createCriteria(RchrBeamLines.class);
    		obCriteria.add(Restrictions.eq(RchrBeamLines.PROPERTY_RCHRBEAM, beams));
    		String[] arrayString = {"KAD","GAD","RL"};
    		obCriteria.add(Restrictions.in(RchrBeamLines.PROPERTY_ALERTSTATUS,arrayString));
    		*/
    		
    		RchrBeamLines beamLines = OBDal.getInstance().get(RchrBeamLines.class,strndId);
			BigDecimal actualBeamLenght = BigDecimal.ZERO;
			if(beamLines.getBeamtype().equals("G")){
				//RchrBeamLines beamLines = OBDal.getInstance().get(RchrBeamLines.class,strndId);
				OBCriteria<RchrAutodrawing> autoDrawingCriteria = OBDal.getInstance().createCriteria(RchrAutodrawing.class);
				autoDrawingCriteria.add(Restrictions.eq(RchrAutodrawing.PROPERTY_RCHRSIZINGBEAM, beamLines.getRchrSizingBeam()));

				if(autoDrawingCriteria.list().size()==1){
					actualBeamLenght = new BigDecimal(autoDrawingCriteria.list().get(0).getBeamlength()).subtract(autoDrawingCriteria.list().get(0).getReducedbeamlength());
				}else{
					logger.warning("Sized Beam Id should not be greater than 1 ");
				}
			}else if(beamLines.getBeamtype().equals("K")){
				actualBeamLenght =  beamLines.getRchrSizingBeam().getBeamlength();
			}else {
				logger.warning("Sized Beam Else Condition ");
			}

			
    		//reducedBeamLenght = 
    		info.addResult("inpbeamlength", actualBeamLenght);
    		info.addResult("inpgrossWeight", beamLines.getRchrSizingBeam().getGrossWeight());
    		info.addResult("inptareWeight",beamLines.getRchrSizingBeam().getTareWeight());
    		info.addResult("inpnetweight", beamLines.getRchrSizingBeam().getNetweight());
    		info.addResult("inprchrJobcardId", beamLines.getRchrSizingBeam().getRchrSizingsheet().getRchrJobcard().getId());
    		info.addResult("inprchrQualitystandardId", beamLines.getRchrSizingBeam().getRchrSizingsheet().getRchrQualitystandard().getId());
    		info.addResult("inprchrBeamId", beamLines.getRchrBeam().getId());
    		//info.addResult("inprchrBeamLinesId", beamLines.getRchrSizingBeam().getNetweight());
    		System.out.println("Size is ");
    	}else if(tabId.equals("91667B22D2604FABA8BA8619F6BFA21C")){ // Roll Doffer Lines by selecting the Last piece roll type it enable the isLast checkbox...
    		// Creating Piece No.
    		Calendar cal = Calendar.getInstance();
    		Integer intYear = cal.getWeekYear();
    		String strYear = new String(intYear.toString());
    		StringBuffer finalPieceNo = new StringBuffer(strYear.substring(2, 4));
    		logger.info("finalPieceNo 1 "+finalPieceNo);
    		String headeId = info.getStringParameter("inprchrLoomsdataId", null);
    		RchrLoomsdata loomsData = OBDal.getInstance().get(RchrLoomsdata.class, headeId);
    		String sizingSetNo = loomsData.getRchrBeamLines().getRchrSizingBeam().getRchrSizingsheet().getDocumentNo();
    		
			String sizingLineBeamNo = loomsData.getRchrBeamLines().getRchrSizingBeam().getLineNo().toString();
			finalPieceNo.append(sizingSetNo);
		
			
			
			
			
			if(sizingLineBeamNo.length()==1){
				finalPieceNo.append("0").append(sizingLineBeamNo);
			}else{
				finalPieceNo.append(sizingLineBeamNo);
			}
		
			String loomNo = loomsData.getRcplLoom().getName();
			finalPieceNo.append(loomNo);
	
			BigDecimal lineNo = info.getBigDecimalParameter("inplineno");
			Integer loomDateLinesPieceNo =  lineNo.intValue();
			
			if(loomDateLinesPieceNo.toString().length()==1){
				finalPieceNo.append("0").append(loomDateLinesPieceNo);
			}else{
				finalPieceNo.append(loomDateLinesPieceNo.toString());
			}

			
			info.addResult("inpdocumentno", finalPieceNo.toString());
    		if(loomsData.getRchrLoomsdataLinesList().size()==0){
    			info.addResult("inprchrInsprolltypeId","AF257523CAC94DD3A98275B79FDD034B");
    		}
    		String lastPieceId = info.getStringParameter("inprchrInsprolltypeId", null);
    		
    		if(lastPieceId.equals("5982F8B26BA8416791972E0CD26B2053")){ // Checks the Id 
    			info.addResult("inpislast", Boolean.TRUE);
    		}else{
    			info.addResult("inpislast", Boolean.FALSE);
    		}
    		
    	}else{ // Else Goes to the Gaiting Process
    		String strndId = info.getStringParameter("inprchrBeamId", null);
    		String[] arrayString = {"KAD","RL"};
            RCHR_Beam beams = OBDal.getInstance().get(RCHR_Beam.class, strndId);
            
            OBCriteria<RchrBeamLines> obCriteria = OBDal.getInstance().createCriteria(RchrBeamLines.class);
    		obCriteria.add(Restrictions.eq(RchrBeamLines.PROPERTY_RCHRBEAM, beams));
    		obCriteria.add(Restrictions.in(RchrBeamLines.PROPERTY_ALERTSTATUS,arrayString));
    		
    		info.addResult("inpsizingstartdate", PayrollUtils.getInstance().getCalloutCompatibleDate(obCriteria.list().get(0).getSizingstartdate()));
    		info.addResult("inprchrSizingBeamId", obCriteria.list().get(0).getRchrSizingBeam().getId());
    		info.addResult("inprchrDirectwarpId", obCriteria.list().get(0).getRchrSizingBeam().getRchrSizingsheet().getRchrDirectwarp().getId());
    		info.addResult("inprchrQualitystandardId", obCriteria.list().get(0).getRchrSizingBeam().getRchrSizingsheet().getRchrQualitystandard().getId());
    		info.addResult("inprchrJobcardId", obCriteria.list().get(0).getRchrSizingBeam().getRchrSizingsheet().getRchrJobcard().getId());
    		info.addResult("inpbeamlength", obCriteria.list().get(0).getRchrSizingBeam().getBeamlength());
    		info.addResult("inpgrossWeight", obCriteria.list().get(0).getRchrSizingBeam().getGrossWeight());
    		info.addResult("inptareWeight",obCriteria.list().get(0).getRchrSizingBeam().getTareWeight());
    		info.addResult("inpnetweight", obCriteria.list().get(0).getRchrSizingBeam().getNetweight());
    		System.out.println("Size of Sized Beams is "+obCriteria.list().size());
    	}
    	
    	
        
    }
}    