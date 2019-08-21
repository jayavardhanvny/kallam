package com.rcss.humanresource.ad_actionButtons;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.erpWindows.com.rcss.humanresource.InspectionSheet.InspectionSheet00E6C8C8669F4790AC1E232108CF3DA2;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import com.redcarpet.payroll.util.PayrollUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import com.rcss.humanresource.data.RCHR_Inspcategoryline;
import com.rcss.humanresource.data.RCHR_Inspectionsheet;
import com.rcss.humanresource.data.RchrRollnolist;
import com.rcss.humanresource.data.RchrRollnomonthlist;
import com.rcss.humanresource.data.RchrRollwisedata;

/**
*
* @author K Vinay Kumar...
*/
public class GenerateRollNo extends DalBaseProcess {
	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception {
		String strEmpSet = (String)bundle.getParams().get("Rchr_Inspectionsheet_ID");
		
		RCHR_Inspectionsheet inspsheet = OBDal.getInstance().get(RCHR_Inspectionsheet.class, strEmpSet);
		boolean morethan100 = false;
		
		//System.out.println("inspsheet.getRCHRInspsheetlinesList().size() "+inspsheet.getRCHRInspsheetlinesList().size());
		
		String errorString="";
		if(inspsheet.getRCHRInspcategorylineList().size()==0){
			
			errorString="There are no records are there in Inspection Tab so you cannot generate Roll Number !";
			
			throwError(errorString);
			//System.out.println("in throw condition with There are no records are there in Inspection Tab so you cannot generate Roll Number");
		}else if((inspsheet.getMenidngstatus()!=null)){
			errorString="Mending Status should be Empty!";
			throwError(errorString);
			//System.out.println("in throw condition Mending Status should be Empty");
		}else if(inspsheet.getRCHRInspcategorylineList().size()>1){
			errorString="More than One Lines cannot generate Roll Number!";
			throwError(errorString);
			//System.out.println("In Else condition with More than One Lines cannot generate Roll Number!");
	    }else if(inspsheet.getTotalpoints().intValue()>24){
			errorString="Total Points should not be greater than be 24, so Roll Number cannot generate !";
			throwError(errorString);
			//System.out.println("In Else condition with More than One Lines cannot generate Roll Number!");
	    }else if(inspsheet.getRCHRInspcategorylineList().size()==1){
			
			
				
				for(RCHR_Inspcategoryline catline :inspsheet.getRCHRInspcategorylineList()){
					if((catline.getA().intValue()+catline.getAone().intValue()<100) && (inspsheet.getRchrQualitystandard().getRchrInspclothtype().getSearchKey().equals("Grey")) ){
						 errorString="This is of type "+inspsheet.getRchrQualitystandard().getRchrInspclothtype().getName() +" So it should be more than 100mts!";
				    	 throwError(errorString);
				    	 morethan100 = true;
						//System.out.println("In Else condition with Grey More than 100 !");
					}
				
				}
			 
				 RchrRollwisedata rollwiseProvider = OBProvider.getInstance().get(RchrRollwisedata.class);
					
					rollwiseProvider.setRchrInspectionsheet(inspsheet);
					rollwiseProvider.setOrganization(inspsheet.getOrganization());
					rollwiseProvider.setRchrQualitystandard(inspsheet.getRchrQualitystandard());
					rollwiseProvider.setRollmts(inspsheet.getTotalmtr());
					rollwiseProvider.setRolldate(new Date());
					rollwiseProvider.setRcprShift(inspsheet.getInspshift());
					rollwiseProvider.setEmployee(inspsheet.getDataeop());
					rollwiseProvider.setRcplLoom(inspsheet.getRcplLoom());
					rollwiseProvider.setRchrPiecenolist(inspsheet.getRchrPiecenolist());
					rollwiseProvider.setGlm(inspsheet.getGlm());
					rollwiseProvider.setGrossweight(inspsheet.getGrossweight());
					
					// Getting the inserted Roll Number List tab level Object and setting into the Roll Wise data 
					rollwiseProvider.setRchrRollnolist(this.getinsertedRollNumberObject(inspsheet.getInspdate()));
					//rollwiseProvider.setDocumentNo("1");
					inspsheet.setGeneraterollno(true);
					OBDal.getInstance().save(rollwiseProvider);
					final OBError msg = new OBError();
			        msg.setType("Success");
			        msg.setTitle("Done");
			        msg.setMessage("Roll Number has been generated Successfully");
			        bundle.setResult(msg);
			 
		 }
		
	}
	
	private void throwError(String errorString) throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, errorString, language));
    }
	
	/*public String getRollNumberListId(Date inspdate){
		
		
		//inspdate.getYear()
	}*/
	
	
	
	// Recently inserted Roll number Line object 
	public RchrRollnolist getinsertedRollNumberObject(Date inspdate){
		
		RchrRollnolist numberlist = OBProvider.getInstance().get(RchrRollnolist.class);
		String yearMonth ="";	
		String monthId= "";
		try{
			Connection conn = OBDal.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			
			String sql = "select rchr_rollnomonthlist_id from rchr_rollnomonthlist list WHERE '"+PayrollUtils.getInstance().getDBCompatiableDate(inspdate)+"' BETWEEN list.fromdate and list.todate";
			ResultSet rs = stmt.executeQuery(sql);
			
			
			if(rs.wasNull()){
				throwErrorNull();
			}else{
				
				while(rs.next()){
					monthId = rs.getString("rchr_rollnomonthlist_id");
				}
			}
		}catch(SQLException sqlexception){
		}
			if(monthId!="" || monthId!=null){
				RchrRollnomonthlist months = OBDal.getInstance().get(RchrRollnomonthlist.class, monthId);
				
				
				
				
				//Calendar cal = Calendar.getInstance();
				//cal.setTime(months.geti);
				
				//System.out.println("months.getFromDate().getYear(); "+months.getFromDate().getYear());
				
				Long longvalue = new Long(1001);
				
				
				
				
				yearMonth = this.getYearCode(months.getFromDate().getYear()).concat(months.getMonthchar()).concat("");
				if(months.getRchrRollnolistList().size()==0){
					
					numberlist.setRollno(yearMonth.concat("1001"));
					
					numberlist.setLineNo(longvalue);
					numberlist.setOrganization(months.getOrganization());
					numberlist.setRchrRollnomonthlist(months);
					OBDal.getInstance().save(numberlist);
					System.out.println("In If condition very first");
				}else{
					
					//for(RchrRollnolist rollNumber : months.getRchrRollnolistList().){
					
					
					longvalue = getLineNo(months);
					//System.out.println("Long value string format "+longvalue.toString());
					
					numberlist.setRollno(yearMonth.concat(longvalue.toString()));
					numberlist.setLineNo(longvalue);
					numberlist.setOrganization(months.getOrganization());
					numberlist.setRchrRollnomonthlist(months);
					OBDal.getInstance().save(numberlist);
					//System.out.println("In Else condition ");
			
					}
				//System.out.println("IN IF MONTHID not null condition ");
			}
			return numberlist;
		}
			
		
		
		// Get Maximumb line number of the rows... in Roll number List...
	
	public long getLineNo(RchrRollnomonthlist months){
		
		Long lineno= new Long(0);
		try{
			Connection conn = OBDal.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select max(lineno) as lineno from rchr_rollnolist numberlist ,rchr_rollnomonthlist monthlist " +
					"where numberlist.rchr_rollnomonthlist_id=monthlist.rchr_rollnomonthlist_id " +
					"and monthlist.rchr_rollnomonthlist_id='"+months.getId()+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				lineno = rs.getLong("lineno");
				//System.out.println("long number "+lineno);
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return lineno+1;
	}
	private void throwErrorNull() throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, "This Month Period was empty and not generated in Roll Master Months!", language));
    }
	/*private void throwErrorLines() throws OBException {
        String language = OBContext.getOBContext().getLanguage().getLanguage();
        ConnectionProvider conn = new DalConnectionProvider(false);
        throw new OBException(Utility.messageBD(conn, "", language));
    }
	private void throwErrorGreaterThanOneRow(String) throws OBException{
		 String language = OBContext.getOBContext().getLanguage().getLanguage();
	        ConnectionProvider conn = new DalConnectionProvider(false);
	        throw new OBException(Utility.messageBD(conn, , language));
	}
	private void throwError(String clothType) throws OBException{
		 String language = OBContext.getOBContext().getLanguage().getLanguage();
	        ConnectionProvider conn = new DalConnectionProvider(false);
	        throw new OBException(Utility.messageBD(conn, , language));
	}
	private void throwErrorItsGreyGreaterThan(String clothType) throws OBException{
		 String language = OBContext.getOBContext().getLanguage().getLanguage();
	        ConnectionProvider conn = new DalConnectionProvider(false);
	        throw new OBException(Utility.messageBD(conn, , language));
	}*/
	
	public String getYearCode(Integer yearno){
		String chare="";
		
		String yearnostr =yearno.toString();
	    if(yearnostr.equals("114")){
	    	chare="Z";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("115")){
	    	chare="Y";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("116")){
	    	chare="X";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("117")){
	    	chare="W";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("118")){
	    	chare="V";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("119")){
	    	chare="U";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("120")){
	    	chare="T";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("121")){
	    	chare="S";
	    	 //info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("122")){
	    	chare="R";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("123")){
	    	chare="Q";
	    	// info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("124")){
	    	chare="P";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("125")){
	    	chare="O";
	    	 
	    }else if(yearnostr.equals("126")){
	    	chare="N";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("127")){
	    	chare="M";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("128")){
	    	chare="L";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("129")){
	    	chare="K";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("130")){
	    	chare="I";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("2031")){
	    	chare="H";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("132")){
	    	chare="G";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("133")){
	    	chare="F";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("134")){
	    	chare="E";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("135")){
	    	chare="D";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("136")){
	    	chare="C";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("137")){
	    	chare="B";
	    	 ////info.addResult("inppiecechar", chare);
	    }else if(yearnostr.equals("138")){
	    	chare="A";
	    	 ////info.addResult("inppiecechar", chare);
	    }else{
	    	chare="Enter Char";
	    }
	    
	    return chare;
	}
}
