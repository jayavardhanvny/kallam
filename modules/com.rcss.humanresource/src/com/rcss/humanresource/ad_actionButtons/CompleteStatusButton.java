package com.rcss.humanresource.ad_actionButtons;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;

import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.apache.tools.ant.taskdefs.BUnzip2;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.rcss.humanresource.exceptions.FieldEmptyException;
import com.rcss.humanresource.data.RCHR_Beam;
import com.rcss.humanresource.data.RCHR_Directwarp;
import com.rcss.humanresource.data.RCHR_Jobcard;
import com.rcss.humanresource.data.RCHR_Sizing_Beam;
import com.rcss.humanresource.data.RchrAutodrawing;
import com.rcss.humanresource.data.RchrBeamLines;
import com.rcss.humanresource.data.RchrLoomsdata;
import com.rcss.humanresource.data.RchrLoomsdataLines;
import com.rcss.humanresource.data.RCHR_Sizingsheet;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.*;

import javax.sql.rowset.serial.SerialException;

public class CompleteStatusButton extends DalBaseProcess {
	protected Logger logger = Logger.getLogger("CompleteStatusButton.java");
	@Override
	protected void doExecute(ProcessBundle bundle){
		
		final OBError msg = new OBError();
		try{
			doIt(bundle,logger);
			 msg.setType("Success");
		     msg.setTitle("Done");
		     msg.setMessage("Success");
		     bundle.setResult(msg);
			OBDal.getInstance().commitAndClose();
		}catch(FieldEmptyException e){
			 msg.setType("Error");
		     msg.setTitle("Error");
		     msg.setMessage("Error is : "+e.getMessage());
		     bundle.setResult(msg);
			logger.log(Level.SEVERE,"Status In Exception case");
			OBDal.getInstance().rollbackAndClose();
		}catch(SerialException e){
			 msg.setType("Error");
		     msg.setTitle("Error");
		     msg.setMessage("Error is :"+e.getMessage());
		     bundle.setResult(msg);
			OBDal.getInstance().rollbackAndClose();
			logger.log(Level.SEVERE,"In Exception case");
		}finally {
			OBDal.getInstance().rollbackAndClose();
		      //OBContext.restorePreviousMode();
	    }
		
		
	}
	/*public TabProcessor getTabProcess(String tabId) {
		 TabProcessor tabProcessor ;
		 switch(tabId) {
		 	case "sfitsizingproces":
		 		tabProcessor = new SfitSizingProcessor();
		 		break;
		 	case "autodrawingprocess":
		 		tabProcessor = new AutodrawingProcessor();
		 		break;
		 	default:
		 	    break;
		 }
		 return tabProcessor;
		 //tabProcessor.process();
		  List listArray = new ArrayList();
		   
	}*/
	
	public void doIt(ProcessBundle bundle,Logger logger) throws SerialException,FieldEmptyException{
		String tabId = (String) bundle.getParams().get("tabId");
		String statusCO = "CO";
		String statusIDLE = "IDLE";
		String statusSB = "SB";
		String statusRL = "RL";
		String statusKAD = "KAD";
		String beamTypeK = "K";
		String id="";
		logger.info("tabId Id is "+tabId);
		/*TabProcessor tabProcessor = getTabProcess(tabId);
		tabProcessor.process(customInput);*/
		if(tabId=="D51B7C8348D44320AAC6D0A02AB71D13"){
			id = (String) bundle.getParams().get("Rchr_Sizingsheet_ID");
			RCHR_Sizingsheet sizing = OBDal.getInstance().get(RCHR_Sizingsheet.class, id);
			for (RCHR_Sizing_Beam sizingBeam : sizing.getRCHRSizingBeamList()){ // Sizing Header Complete Process Which will the status in Beams Master header and line level
				OBCriteria<RchrBeamLines> obCriteria = OBDal.getInstance().createCriteria(RchrBeamLines.class);
				obCriteria.add(Restrictions.eq(RchrBeamLines.PROPERTY_RCHRBEAM, sizingBeam.getRchrBeam()));
				obCriteria.add(Restrictions.eq(RchrBeamLines.PROPERTY_RCHRSIZINGBEAM, sizingBeam));
				//obCriteria.add(Restrictions.eq(RchrBeamLines.PROPERTY_ALERTSTATUS,"SIZING"));
				if(obCriteria.list().size()==0){
					insertBeamLines(obCriteria,sizingBeam,statusKAD,beamTypeK);
					sizing.setAlertStatus(statusCO); // Sizing Header status Complete
					sizing.setComplete(Boolean.TRUE); // Settng Compelete button true, Not to diplay button for Lines... 
					sizingBeam.setComplete(Boolean.TRUE);
					sizingBeam.getRchrBeam().setAlertStatus("SB"); // In Header Of Beam Master SB: Sizing Beam
					sizingBeam.setProcess(Boolean.TRUE); // Sizing Beam Lines Process check box...
				}else{
					//logger.info("in Else Condition Header size "+obCriteria.list().size());
				}
			}
		}else if(tabId=="D714ED1289CA455583627EF9D982E3F9"){  // Sizing Lines Complete Process Which will the status in Beams Master header and line level
			id = (String) bundle.getParams().get("Rchr_Sizing_Beam_ID");
			// Getting Sizing Beams 
			RCHR_Sizing_Beam sizingBeam = OBDal.getInstance().get(RCHR_Sizing_Beam.class, id);
			
			// Getting the Beam Lines based on Sized Beam Id and the SIZING status to get  
			OBCriteria<RchrBeamLines> obCriteria = OBDal.getInstance().createCriteria(RchrBeamLines.class);
			obCriteria.add(Restrictions.eq(RchrBeamLines.PROPERTY_RCHRBEAM, sizingBeam.getRchrBeam()));
			obCriteria.add(Restrictions.eq(RchrBeamLines.PROPERTY_RCHRSIZINGBEAM, sizingBeam));
			
			logger.info("Size in lines Beam "+obCriteria.list().size());
			if(obCriteria.list().size()==0){
				logger.info("in Else Condition Header size "+obCriteria.list().size());
				insertBeamLines(obCriteria,sizingBeam,statusKAD,beamTypeK);
				sizingBeam.setComplete(Boolean.TRUE); // Settng Compelete button true, Not to diplay button for Lines... 
				sizingBeam.getRchrBeam().setAlertStatus("SB"); // In Header Of Beam Master
				sizingBeam.setProcess(Boolean.TRUE); // Sizing Beam Lines Process check box...
			}else{
				//logger.info("in Else Condition Header size "+obCriteria.list().size());
			}
		}else if(tabId=="3058BA952B2643F88143EF4E51050CF5"){ // Direct Warping Header Complete Process
			id = (String) bundle.getParams().get("Rchr_Directwarp_ID");
			RCHR_Directwarp direwarp = OBDal.getInstance().get(RCHR_Directwarp.class, id);
			direwarp.setAlertStatus(statusCO);
		}else if(tabId=="31F76F33A65F4206BA7563B1E352BFDA"){ // GATTING Complete Process 
			logger.info("Gatting Process");
			id = (String) bundle.getParams().get("Rchr_Autodrawing_ID");
			RchrAutodrawing autoDrawing = OBDal.getInstance().get(RchrAutodrawing.class, id);
			if(null==autoDrawing.getAlertStatus()){
					logger.info("Null");
					throw new FieldEmptyException("STATUS should not be Null");	
			}else{
				OBCriteria<RchrBeamLines> obCriteria = OBDal.getInstance().createCriteria(RchrBeamLines.class);
				obCriteria.add(Restrictions.eq(RchrBeamLines.PROPERTY_RCHRSIZINGBEAM, autoDrawing.getRchrSizingBeam()));
				obCriteria.list().get(0).setAlertStatus(autoDrawing.getAlertStatus());
				obCriteria.list().get(0).setBeamtype("G");
				autoDrawing.setProcess(Boolean.TRUE);
				autoDrawing.setAlertStatus("GAD");
			}
		}else if(tabId=="E36346A814824004A2386910B3F8BF54"){ // Auto Drawing Complete Process 
			logger.info("Auto Drawing Process");
			id = (String) bundle.getParams().get("Rchr_Autodrawing_ID");
			RchrAutodrawing autoDrawing = OBDal.getInstance().get(RchrAutodrawing.class, id);
			if(null==autoDrawing.getAlertStatus()){
					logger.info("Null");
					throw new FieldEmptyException("STATUS should not be Null");	
			}else{
				OBCriteria<RchrBeamLines> obCriteria = OBDal.getInstance().createCriteria(RchrBeamLines.class);
				obCriteria.add(Restrictions.eq(RchrBeamLines.PROPERTY_RCHRSIZINGBEAM, autoDrawing.getRchrSizingBeam()));
				obCriteria.list().get(0).setAlertStatus("RL"); // RL : Ready to Loom
				
				obCriteria.list().get(0).setAdbeamlength(new BigDecimal(autoDrawing.getBeamlength()-autoDrawing.getReducedbeamlength().longValue()));
				//autoDrawing.setProcess(Boolean.TRUE);
				//autoDrawing.setAutoenddate(new Date());
				autoDrawing.setAlertStatus(statusCO);
			}
		}else if(tabId=="6D7647B9EF4E431CB34FFEACF4CF7979"){// Looms Production Complete Process 
			id = (String) bundle.getParams().get("Rchr_Loomsdata_ID");
			RchrLoomsdata loomsData = OBDal.getInstance().get(RchrLoomsdata.class, id);
			loomsData.setAlertStatus("RUNNING");
			loomsData.getRcplLoom().setAlertStatus("RUNNING");
			loomsData.getRchrBeamLines().setAlertStatus("LOOMSPROCESS");
		}else if(tabId=="090E02F9D0E0474D826F391925578210"){ // Roll Doffing Complete Process 
			//logger.info("Looms Production Process");
			id = (String) bundle.getParams().get("Rchr_Loomsdata_ID");
			RchrLoomsdata loomsData = OBDal.getInstance().get(RchrLoomsdata.class, id);

			int lastPieceCount=0;
			int firstPieceCount=0;
			int regularPiece=0;


			if(loomsData.getRchrLoomsdataLinesList().size()==0){ // Checks wheather lines are there or not
				throw new SerialException("No Lines have been given");	
			}else if(loomsData.getRchrLoomsdataLinesList().size()>0){

				for(RchrLoomsdataLines lines: loomsData.getRchrLoomsdataLinesList()){

					if(lines.getRchrInsprolltype().isLast()){

						lastPieceCount++;
						logger.info("count is "+lastPieceCount);
					}
					if(lines.getRchrInsprolltype().getValidationCode().equals("FP")){
						//parity=Boolean.TRUE;
						firstPieceCount++;
						logger.info("count is "+firstPieceCount);
					}
					if(lines.getRchrInsprolltype().getValidationCode().equals("Regular")){
						//parity=Boolean.TRUE;
						regularPiece++;
						logger.info("count is "+regularPiece);
					}

				}
				logger.info("count 2 is "+lastPieceCount);

				if(firstPieceCount==1){
					if(regularPiece>0){
						if(lastPieceCount==1) {
							Date linesDate = loomsData.getLoomsenddate();
							for (RchrLoomsdataLines lines : loomsData.getRchrLoomsdataLinesList()) {
								logger.info("in for loop");
								lines.setProcess(Boolean.TRUE);
								if(lines.isLast())
									linesDate = lines.getLoomsenddate();
							}
							loomsData.setProcess(Boolean.TRUE);
							loomsData.setAlertStatus(statusCO);
							loomsData.getRcplLoom().setAlertStatus(statusIDLE);
							loomsData.getRchrBeam().setAlertStatus(statusIDLE);
							loomsData.getRchrBeamLines().setAlertStatus(statusCO); //
							loomsData.getRchrBeamLines().setLoomsenddate(linesDate);

							logger.info("End of Looms Production Process");
						}else if(lastPieceCount>1){
							logger.info("Multiple Last Piece Exceptioin ");
							throw new SerialException("Single Last Piece should be there, Multiple Last Pieces is not possible");
						}else if(lastPieceCount==0){
							logger.info("Last Piece else condition Exception");
							throw new SerialException("Last piece is must and should");
						}
					}else if(regularPiece==0){
						throw new SerialException("Single Regular Piece is Mandatory");
					}
				}else if(firstPieceCount==0){
					throw new SerialException("Single First Piece is Mandatory");
				}else if(firstPieceCount>1){
						throw new SerialException("Multiple First Pieces are not Allowed");
				}
			}else{
				logger.info("In Else Exception");
			}
		}else if(tabId=="91667B22D2604FABA8BA8619F6BFA21C"){ // Roll Doffing Lines Complete Process 
			//logger.info("Looms Production Process");
			id = (String) bundle.getParams().get("Rchr_Loomsdata_Lines_ID");
			RchrLoomsdataLines loomsDataLinies = OBDal.getInstance().get(RchrLoomsdataLines.class, id);
			int lastPieceCount=0;
			int firstPieceCount=0;
			int regularPiece=0;

			for(RchrLoomsdataLines rchrLoomsdataLines : loomsDataLinies.getRchrLoomsdata().getRchrLoomsdataLinesList()){

				if(rchrLoomsdataLines.getRchrInsprolltype().isLast()){
					//parity=Boolean.TRUE;
					lastPieceCount++;
					logger.info("count is "+lastPieceCount);
				}
				if(rchrLoomsdataLines.getRchrInsprolltype().getValidationCode().equals("FP")){
					//parity=Boolean.TRUE;
					firstPieceCount++;
					logger.info("count is "+firstPieceCount);
				}
				if(rchrLoomsdataLines.getRchrInsprolltype().getValidationCode().equals("Regular")){
					//parity=Boolean.TRUE;
					regularPiece++;
					logger.info("count is "+regularPiece);
				}
			}


			if(firstPieceCount==1){
				if(regularPiece>0){
					if(lastPieceCount==1){
						for(RchrLoomsdataLines lines : loomsDataLinies.getRchrLoomsdata().getRchrLoomsdataLinesList()){
							logger.info("in for loop");
							lines.setProcess(Boolean.TRUE);
						}
						logger.info("In If Condition");
						loomsDataLinies.setProcess(Boolean.TRUE);
						loomsDataLinies.setComplete(Boolean.TRUE);
						loomsDataLinies.getRchrLoomsdata().setProcess(Boolean.TRUE); // Setting Header Completed state
						loomsDataLinies.getRchrLoomsdata().setAlertStatus(statusCO);
						loomsDataLinies.getRchrLoomsdata().getRchrBeamLines().setAlertStatus(statusCO); // Setting Beam lines Completed state
						loomsDataLinies.getRchrLoomsdata().getRcplLoom().setAlertStatus(statusIDLE); // Setting the Looms available
						loomsDataLinies.getRchrLoomsdata().getRchrBeam().setAlertStatus(statusIDLE); // Setting Beam available
						loomsDataLinies.getRchrLoomsdata().getRchrBeamLines().setLoomsenddate(loomsDataLinies.getLoomsenddate()); // Setting Last End date in Beams Master Lines
						logger.info("End of Looms Production Process");
					}else if(lastPieceCount>1){
						logger.info("Multiple Last Piece Exceptioin ");
						throw new SerialException("Single Last Piece should be there, Multiple Last Pieces is not possible");
					}else if(lastPieceCount==0){
						logger.info("Last Piece else condition Exception");
						throw new SerialException("Last piece is must and should");
					}
				}else if(regularPiece==0){
					throw new SerialException("Single Regular Piece is Mandatory");
				}
			}else if(firstPieceCount==0){
				throw new SerialException("Single First Piece is Mandatory");
			}
			else if(firstPieceCount>1){
				throw new SerialException("Multiple First Pieces are not Allowed");
			}







/*

				if(loomsDataLinies.isLast()){
					logger.info("In If Condition");
					loomsDataLinies.setProcess(Boolean.TRUE);
					loomsDataLinies.setComplete(Boolean.TRUE);
					loomsDataLinies.getRchrLoomsdata().setProcess(Boolean.TRUE); // Setting Header Completed state
					loomsDataLinies.getRchrLoomsdata().setAlertStatus(statusCO);
					loomsDataLinies.getRchrLoomsdata().getRchrBeamLines().setAlertStatus(statusCO); // Setting Beam lines Completed state
					loomsDataLinies.getRchrLoomsdata().getRcplLoom().setAlertStatus(statusIDLE); // Setting the Looms available
					loomsDataLinies.getRchrLoomsdata().getRchrBeam().setAlertStatus(statusIDLE); // Setting Beam available
					loomsDataLinies.getRchrLoomsdata().getRchrBeamLines().setLoomsenddate(loomsDataLinies.getLoomsenddate()); // Setting Last End date in Beams Master Lines
					logger.info("End of Looms Production Process");
				}else{
					loomsDataLinies.setProcess(Boolean.TRUE);
					loomsDataLinies.setComplete(Boolean.TRUE);
					logger.info("In Else Condition");
					//throw new SerialException("Single Last Piece should be there, Multiple Last Pieces is not possible");
				} */
			}else{
				logger.info("In Else Exception");
			}
		
		
	}
	
	private void insertBeamLines(OBCriteria<RchrBeamLines> obCriteria,RCHR_Sizing_Beam sizingBeam,String statusKAD, String beamTypeK){
		Long lineNo = new Long(10);
		RchrBeamLines beamLinesProvider = OBProvider.getInstance().get(RchrBeamLines.class);
		beamLinesProvider.setOrganization(sizingBeam.getOrganization());
		beamLinesProvider.setRchrBeam(sizingBeam.getRchrBeam());
		beamLinesProvider.setRchrSizingBeam(sizingBeam);
		beamLinesProvider.setSizingstartdate(sizingBeam.getBeamdate());
		beamLinesProvider.setAlertStatus(statusKAD);
		beamLinesProvider.setBeamtype(beamTypeK);
		sizingBeam.setProcess(Boolean.TRUE);
		OBCriteria<RchrBeamLines> obCriteriaSizing = OBDal.getInstance().createCriteria(RchrBeamLines.class);
		obCriteriaSizing.add(Restrictions.eq(RchrBeamLines.PROPERTY_RCHRBEAM, sizingBeam.getRchrBeam()));
		obCriteriaSizing.addOrder(Order.desc("lineNo"));
		beamLinesProvider.setLineNo(sizingBeam.getRchrBeam().getRchrBeamLinesList().size()==0 ? lineNo : obCriteriaSizing.list().get(0).getLineNo()+lineNo);
		OBDal.getInstance().save(beamLinesProvider);
	}
}
