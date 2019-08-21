package com.redcarpet.payroll.ad_process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rcss.humanresource.data.RchrDesignation;
import com.rcss.humanresource.exceptions.ExceedException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.StringTokenizer;
import com.rcss.humanresource.util.HqlUtils;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.redcarpet.payroll.ad_process.ProductionIncentiveBean;
import com.redcarpet.payroll.util.PayrollTypesConstants;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.hql.ast.HqlParser;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.impl.SessionImpl;
import org.hibernate.loader.criteria.CriteriaLoader;
import org.hibernate.proxy.pojo.cglib.CGLIBProxyFactory;
import org.openbravo.base.exception.OBException;
import org.openbravo.base.filter.IsPositiveIntFilter;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
//import org.openbravo.erpCommon.ad_actionButton.CopyFromOrderData;
import org.openbravo.erpCommon.utility.DateTimeData;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.erpCommon.utility.SequenceIdData;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.utils.Replace;
import org.openbravo.xmlEngine.XmlDocument;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.openbravo.erpCommon.utility.ComboTableData;
import com.redcarpet.payroll.data.RCPLLoom;
import java.util.Date;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.text.*;
import  java.lang.reflect.Field;
import com.redcarpet.payroll.data.RCPLProdIncentive;
import com.redcarpet.payroll.data.RCPLProdIncentiveLine;
import com.redcarpet.payroll.data.RCPLLoomCategory;
import com.redcarpet.payroll.data.RCPLEfficiency;
import com.redcarpet.payroll.data.RCPLEfficiencyLine;
import com.redcarpet.payroll.data.RcplLoomcategorylines;
import com.redcarpet.payroll.data.RCPLLoomCategory;
/**
 * This is the Calcuation for Looms Incentive.
 * 
 * @author Vinay K
 */
public class LoomIncentiveCalculation{
	final static Logger loggerLIC = LoggerFactory.getLogger(LoomIncentiveCalculation.class);
	protected java.util.logging.Logger log = java.util.logging.Logger.getLogger("LoomIncentiveCalculation.java");
	public CopyLooms copyLoomsClass = null;
	public LoomIncentiveCalculation(CopyLooms callableClass){
		copyLoomsClass = callableClass;
	}
	public double avg1=0;
	
	
	public OBError getCalculation(VariablesSecureApp vars, String strRownums, String strKey,String strClient,String strLoomcg1,
			String date1,String date2,String strShiftId1,int count,Connection conn) throws ExceedException,IOException,SQLException, ServletException {
		OBError myError = null;
			 try {
			 StringTokenizer st = new StringTokenizer(strRownums, ",", false);		
			 RCPLProdIncentive obj = OBDal.getInstance().get(RCPLProdIncentive.class, strKey);
		   	 String strOrg=obj.getOrganization().getId();     	
		   	 String strKey1=strKey;     
		   	 String date3=date1;
		   	 String date4=date1;
		  	 String date5=date1;
		  	 String strShiftId2=strShiftId1;
		  	 String strShiftId3=strShiftId1;
		  	 String strShiftId4=strShiftId1;
		  	 String strShiftId5=strShiftId1;
		  	 CopyLoomsData[] orderData;
		  	 RCPLLoomCategory category=OBDal.getInstance().get(RCPLLoomCategory.class,strLoomcg1);
		  	 if(category.isAll()){
		  		 orderData = CopyLoomsData.selectAll(copyLoomsClass, strKey1, date1, date2, strShiftId1, date3, strShiftId2, strShiftId4, date5, strShiftId5, strShiftId3, date4);
		  	 }else{
		  		 orderData = CopyLoomsData.select(copyLoomsClass, strKey1, strLoomcg1, date1, date2, strShiftId1, date3, strShiftId2, strShiftId4, date5, strShiftId5, strShiftId3, date4);
		  	 }
		  	 log.info("order length.... "+orderData.length);
		  	 int i=0,k=0;
		  	 double total=0;
		  	 int greyCount=0,dyedCount=0;
		  	 int airjetLooms,rapierLooms = 0;
		  	 double totalLoomIncentiveAmount=0;
		  	 double avgloomIncentiveAmount=0;
		  	 double dyedTotalPicks = 0;
		  	 double greyTotalPicks = 0;
		  	 // Initializing the ArrayList to hold the ProductionIncentive data members...
		  	 List<ProductionIncentiveBean> listProductionIncentives = new ArrayList<ProductionIncentiveBean>();
		  	 while (st.hasMoreTokens()) {
		  		 String strRownum = st.nextToken().trim();
		  		 String strName = vars.getStringParameter("inprcplLoomId" + strRownum);
		  		 int cnt=Integer.parseInt(strRownum);
		  		 String strQty = vars.getNumericParameter("inpquantity" + strRownum);
		  		 String strIncentiveType = vars.getStringParameter("incentiveType" + strRownum);
		  		 String greyType="N";
		  		 String dyedType="N";
		  		 Boolean greyBoolean = Boolean.FALSE;
		  		 Boolean dyedBoolean = Boolean.FALSE;
		  		log.info("Log 1");
		  		Double temp=Double.parseDouble(strQty);
		  		log.info("Log 2 "+temp);
		  		RCPLLoom loom=OBDal.getInstance().get(RCPLLoom.class,orderData[cnt-1].rcplLoomId);
		  		//if(temp>=10){
		  		log.info("Log 3");
		  			 total=total+temp;
		  			log.info("Log 4 "+total);
		  			 k++;
		  		 //}
		  		 if(strIncentiveType.equals("G")){
		  			 greyType="Y";
		  			 greyBoolean=Boolean.TRUE;
		  			 greyTotalPicks = greyTotalPicks+temp;
		  			 greyCount++;
		  		 }else if(strIncentiveType.equals("D")){
		  			 dyedType="Y";
		  			 dyedBoolean=Boolean.TRUE;
		  			 dyedTotalPicks = dyedTotalPicks+temp;
		  			 dyedCount++;
		  			totalLoomIncentiveAmount = totalLoomIncentiveAmount+loom.getRcplLoomtype().getIncentiveamount().doubleValue(); // Yarn Dyed Incentive
		  		 }
		  		
		  		log.info("Log 5");
		  		 //String strProdincentiveLineId=SequenceIdData.getUUID();
		  			 //log.info("strProdincentiveLineId "+strProdincentiveLineId);
		  			 //CopyLoomsData.insertProdIncentiveLine(conn, copyLoomsClass,strProdincentiveLineId,orderData[cnt-1].adClientId,
		  					 //orderData[cnt-1].adOrgId, vars.getUser(),orderData[cnt-1].rcplLoomId,strKey,date2,strShiftId1,strQty,
		  					 //dyedType,greyType);
		  		 
		  		ProductionIncentiveBean productionIncentiveObjects = new ProductionIncentiveBean();
		  		productionIncentiveObjects.setClient(obj.getClient());
		  		
		  		productionIncentiveObjects.setOrganization(obj.getOrganization());
		  		productionIncentiveObjects.setEfficiency(strQty);
		  		productionIncentiveObjects.setDyed(dyedBoolean);
		  		productionIncentiveObjects.setGrey(greyBoolean);
		  		productionIncentiveObjects.setLoomObject(loom);
		  		productionIncentiveObjects.setProductionIncentiveHeaderObject(obj);
		  		listProductionIncentives.add(productionIncentiveObjects);
		  		log.info("Log 6");
		  		i++;      
		  		count++;
		  	 }
		  	log.info("Log 7");
		  	 avg1=total/k;
		  	double averageDyedPicks=0;
		  	double averageGreyPicks=0;
		  	 if(dyedCount>0){
		  		avgloomIncentiveAmount =totalLoomIncentiveAmount/Double.valueOf(dyedCount) ; // Average Loom Incentive for yarn dyed calculation...
		  	 	averageDyedPicks = dyedTotalPicks/dyedCount; // Average Dyed Picks to calculate effeciency amount...
			 }
		  	 if(greyCount>0)
		  		 averageGreyPicks = greyTotalPicks/greyCount; // Average Grey Picks to calculate effeciency amount... 
		  	
		  	/*
		  	 * Total Production Incentive = Efficiency Incentive + Looms Incentive;
		  	 * 1. efficiencyIncentive = Average of Dyed and Grey Incentive amount; 
		  	 */
		  	BigDecimal efficiencyIncentive= BigDecimal.ZERO;
		  	if(dyedCount>0 && greyCount>0){
		  		efficiencyIncentive= new BigDecimal((getEfficiencyIncentiveAmount(obj,averageDyedPicks,PayrollTypesConstants.DYEDFABRICTYPE).add(getEfficiencyIncentiveAmount(obj,averageGreyPicks,PayrollTypesConstants.GREYFABRICTYPE)).
		  			 doubleValue())/2, new MathContext(4));
		  	}else if(dyedCount==0 && greyCount>0){
		  		efficiencyIncentive = getEfficiencyIncentiveAmount(obj,averageGreyPicks,PayrollTypesConstants.GREYFABRICTYPE);
		  	}else if(dyedCount>0 && greyCount==0){
		  		efficiencyIncentive = getEfficiencyIncentiveAmount(obj,averageDyedPicks,PayrollTypesConstants.DYEDFABRICTYPE);
		  	}
		  	log.info("efficiencyIncentive "+efficiencyIncentive);
		  	
		  	// 2. Loom Incentive Amount...
		  	BigDecimal loomsIncentive = BigDecimal.ZERO;
		  	loomsIncentive = getLoomsIncentiveAmount(obj,averageGreyPicks,averageDyedPicks,greyCount,dyedCount);
		  	// Total Production Incentive
		  	BigDecimal prodamount = efficiencyIncentive.add(loomsIncentive);
		  	 if(dyedCount>=7 & avg1>=0){
		  		 obj.setYarndyedincentive(new BigDecimal(avgloomIncentiveAmount, new MathContext(4)));
		  	 }else{
		  		 obj.setYarndyedincentive(new BigDecimal(0));
		  	 }
		  	 obj.setEffavg(new BigDecimal(avg1));
		  	 obj.setAmount(prodamount);
		  	 obj.setSelectlooms(Boolean.FALSE);
		  	 Boolean isInserted = insertLines(listProductionIncentives,obj);
		  	 myError = new OBError();
       	     myError.setType("Success");
       	     myError.setTitle(OBMessageUtils.messageBD("Success"));
       	     myError.setMessage(OBMessageUtils.messageBD("RecordsCopied") + " " + count);
			}catch (NumberFormatException e) {
		    	  	myError = OBMessageUtils.translateError(copyLoomsClass, vars, vars.getLanguage(), "ProcessRunError 2 "+e.getMessage());
		    	  	log.log(Level.SEVERE,"Error is "+e.getMessage());
		    }
		  	 return myError;
	}
	
	private Boolean insertLines(List<ProductionIncentiveBean> productionIncentiveObjects,RCPLProdIncentive obj){
		Boolean parity = Boolean.FALSE;
		try{
			
			//log.info("In Try "+productionIncentiveObjects.size());
			int i=productionIncentiveObjects.size();
			ListIterator listIt = productionIncentiveObjects.listIterator();
			for(ProductionIncentiveBean productionIncentiveObject : productionIncentiveObjects){
				RCPLProdIncentiveLine productionIncentiveLines = OBProvider.getInstance().get(RCPLProdIncentiveLine.class);
				productionIncentiveLines.setDate(obj.getDate());
				productionIncentiveLines.setRcprShift(obj.getRcprShift());
				productionIncentiveLines.setClient(productionIncentiveObject.getClient());
				productionIncentiveLines.setOrganization(productionIncentiveObject.getOrganization());
				productionIncentiveLines.setEfficiency(productionIncentiveObject.getEfficiency());
				productionIncentiveLines.setDyed(productionIncentiveObject.getDyed());
				productionIncentiveLines.setGrey(productionIncentiveObject.getGrey());
				productionIncentiveLines.setRcplLoom(productionIncentiveObject.getLoomObject());
				productionIncentiveLines.setRcplProdincentive(productionIncentiveObject.getProductionIncentiveHeaderObject());
				OBDal.getInstance().save(productionIncentiveLines);
			}
			parity = Boolean.TRUE;
		}catch(Exception e){
			log.log(Level.SEVERE, "Not Inserted", e.getMessage());
		}
		return parity;
	}
	
	private BigDecimal getEfficiencyIncentiveAmount(RCPLProdIncentive obj,double avg1,String fabricType){
		BigDecimal efficiencytIncentiveAmount = getIncentiveAmount(obj,avg1,PayrollTypesConstants.EFFICIENCYINCENTIVETYPE,fabricType);
		return efficiencytIncentiveAmount;
	}
	
	private BigDecimal getLoomsIncentiveAmount(RCPLProdIncentive obj,double avgerageGreyPicks,double averageDyedPicks, int greyCount,int dyedCount){
		BigDecimal loomsIncentiveAmount= BigDecimal.ZERO;
		Integer minLooms=0;
		Integer maxCutOffLooms = 10;
		double incentiveAmount = 0; 
		Integer greyMinimumLooms = getMinimumLooms(obj,PayrollTypesConstants.GREYFABRICTYPE);
		Integer dyedMinimumlooms = getMinimumLooms(obj,PayrollTypesConstants.DYEDFABRICTYPE);
		
		log.info("greyMinimumLooms  "+greyMinimumLooms);
		log.info("dyedMinimumlooms  "+dyedMinimumlooms);
		
		int prodlooms = obj.getNooflooms().intValue();
		
		 if((prodlooms==greyCount) || (greyCount>=dyedCount)){
			  minLooms=greyMinimumLooms;
			  incentiveAmount = getIncentiveAmountHQL(obj,avgerageGreyPicks,PayrollTypesConstants.LOOMINCENTIVETYPE,PayrollTypesConstants.GREYFABRICTYPE);
			  log.info("loomsIncentiveAmount 1 "+incentiveAmount);
		  }
		  
		  if(prodlooms==dyedCount || (greyCount<dyedCount)){
			  minLooms=dyedMinimumlooms;
			  incentiveAmount = getIncentiveAmountHQL(obj,averageDyedPicks,PayrollTypesConstants.LOOMINCENTIVETYPE,PayrollTypesConstants.DYEDFABRICTYPE);
			  log.info("loomsIncentiveAmount 2 "+incentiveAmount);
		  }
		  if(prodlooms>=maxCutOffLooms){
			  log.info("prodlooms>=maxCutOffLooms "+(maxCutOffLooms-minLooms));
			  prodlooms=maxCutOffLooms;
		  }
		  if(prodlooms<minLooms){
			  loomsIncentiveAmount= BigDecimal.ZERO;
			  log.info("prodlooms<minLooms "+loomsIncentiveAmount);
		  }else if(prodlooms>=minLooms){
			  log.info("prodlooms>=minLooms "+(prodlooms-minLooms));
			  loomsIncentiveAmount = new BigDecimal(prodlooms-minLooms).multiply(new BigDecimal(incentiveAmount));
		  }
		  
		 /* if(prodlooms>maxCutOffLooms){
			  loomsIncentiveAmount = new BigDecimal(maxCutOffLooms-minLooms).multiply(new BigDecimal(incentiveAmount));
			  
		  } else if(prodlooms=maxCutOffLooms){
			  loomsIncentiveAmount = new BigDecimal(prodlooms-minLooms).multiply(new BigDecimal(incentiveAmount));
			  log.info("prodlooms-minLooms.intValue()  "+(prodlooms-minLooms));
		  }*/
		return loomsIncentiveAmount;
	}
	
	private BigDecimal getIncentiveAmount(RCPLProdIncentive obj,double avg1,String inceniveType, String fabricType){
		 BigDecimal incentiveAmount = BigDecimal.ZERO;
		 OBCriteria<RCPLEfficiency> eff=OBDal.getInstance().createCriteria(RCPLEfficiency.class);
		 eff.add(Restrictions.le(RCPLEfficiency.PROPERTY_FROMRANGE,new BigDecimal(avg1,new MathContext(6))));
		 eff.add(Restrictions.ge(RCPLEfficiency.PROPERTY_TORANGE,new BigDecimal(avg1,new MathContext(6))));
		 eff.add(Restrictions.eq(RCPLEfficiency.PROPERTY_ISRELEIVER,Boolean.FALSE));
		 eff.add(Restrictions.eq(RCPLEfficiency.PROPERTY_FABRICTYPE,fabricType));
		 eff.add(Restrictions.eq(RCPLEfficiency.PROPERTY_INCENTIVETYPE,inceniveType));
		 
		 for(RCPLEfficiency ef:eff.list()){
			  //RCPLEfficiency effci = OBDal.getInstance().get(RCPLEfficiency.class,ef.getId());
			  final OBCriteria<RCPLEfficiencyLine> efline=OBDal.getInstance().createCriteria(RCPLEfficiencyLine.class);
			  efline.add(Restrictions.eq(RCPLEfficiencyLine.PROPERTY_RCPLEFFICIENCY,ef));
			  
			  efline.add(Restrictions.eq(RCPLEfficiencyLine.PROPERTY_RCPLLOOMCATEGORY,obj.getRcplLoomcategory()));
			  for(RCPLEfficiencyLine line:efline.list()){
				  incentiveAmount = line.getNetUnitPrice();
			  }
		  }
		 return incentiveAmount;
	}
	
	private double getIncentiveAmountHQL(RCPLProdIncentive obj,double avg1,String inceniveType, String fabricType){
		String hql = "SELECT aline.netUnitPrice FROM RCPL_EfficiencyLine AS aline " +
				" JOIN aline.rcplEfficiency AS head " +
				" WHERE head.fromrange<= '" + avg1 + "' AND head.torange>= '" + avg1 + "' "+
				" AND head.incentivetype='"+inceniveType+"' AND head.fabrictype='"+fabricType+"' " +
				" AND aline.rcplLoomcategory.id='"+obj.getRcplLoomcategory().getId()+"' " +
				" AND 1 = 1" ;
		log.info("hql "+hql);
		HqlUtils hqlUtils = new HqlUtils();
		return hqlUtils.getFirstRecordValue(hql);
	}
	
	
	private Integer getMinimumLooms(RCPLProdIncentive obj,String fabricType){
		Integer minLooms;
		Integer greyMinLooms=obj.getRcplLoomcategory().getMinlooms().intValue();
		Integer dyedMinLooms=obj.getRcplLoomcategory().getStarminlooms().intValue();
		minLooms=getValue(obj.getEmployee().getDesignation().getId(),obj.getRcplLoomcategory().getId(),fabricType,"minlooms");
		if(minLooms==0){
			if(fabricType.equals(PayrollTypesConstants.GREYFABRICTYPE)){
				minLooms=greyMinLooms;
			}else if(fabricType.equals(PayrollTypesConstants.DYEDFABRICTYPE)){
				minLooms=dyedMinLooms;
			}
		}
			
		return minLooms;
	}
	/*
	private BigDecimal getAmount(RCPLProdIncentive obj,double avg1,Long greyCount,Long dyedCount) throws NumberFormatException{
		  BigDecimal efprice=new BigDecimal(0);
		  BigDecimal dyedefprice=new BigDecimal(0);
		  //BigDecimal efprice=new BigDecimal(0);
		  
		  BigDecimal cgprice=new BigDecimal(0);
		  BigDecimal prodamount=new BigDecimal(0);
		  long eflooms=0,minLooms=0,prodlooms=0,starlooms=0;
		  Integer greyMinLooms,dyedMinLooms,greyEfficiency,dyedEfficiency=0;
		  System.out.println("avg1."+avg1);
		  
		  prodlooms=obj.getNooflooms();// Getting the Actual Production Looms from Loom Production Incentive Header. 
		  
		  //RCPLLoomCategory loomCategory = OBDal.getInstance().get(RCPLLoomCategory.class,obj.getRcplLoomcategory().getId());
		  
		  OBCriteria<RCPLEfficiency> eff=OBDal.getInstance().createCriteria(RCPLEfficiency.class);
		  eff.add(Restrictions.le(RCPLEfficiency.PROPERTY_FROMRANGE,new BigDecimal(avg1,new MathContext(4))));
		  eff.add(Restrictions.ge(RCPLEfficiency.PROPERTY_TORANGE,new BigDecimal(avg1,new MathContext(4))));
		  eff.add(Restrictions.eq(RCPLEfficiency.PROPERTY_ISRELEIVER,Boolean.FALSE));
		  
		  System.out.println("size1..."+eff.list().size());
		  
		  for(RCPLEfficiency ef:eff.list()){
			  RCPLEfficiency effci = OBDal.getInstance().get(RCPLEfficiency.class,ef.getId());
			  final OBCriteria<RCPLEfficiencyLine> efline=OBDal.getInstance().createCriteria(RCPLEfficiencyLine.class);
			  efline.add(Restrictions.eq(RCPLEfficiencyLine.PROPERTY_RCPLEFFICIENCY,effci));
			  efline.add(Restrictions.eq(RCPLEfficiencyLine.PROPERTY_RCPLLOOMCATEGORY,obj.getRcplLoomcategory()));
			  
			  
			  System.out.println("size2.."+efline.list().size());
			  for(RCPLEfficiencyLine line:efline.list()){
				//  System.out.println("under list");
				  
				  
				  if((prodlooms==greyCount) || (greyCount>=dyedCount)){
					  efprice = efprice=line.getNetUnitPrice();
					  eflooms=line.getMinlooms();
					  
					  //minLooms=greyMinLooms.longValue();
					  //cgprice=new BigDecimal(greyEfficiency);
					  loggerLIC.debug("Log Efficiency 1... {}",efprice);
					  
				  }
				  
				  if(prodlooms==dyedCount || (greyCount<dyedCount)){
					  
					  efprice = line.getDyedefficeincyprice();
					  eflooms=line.getDyedminlooms();
					  loggerLIC.debug("Log Efficiency 2... {}",efprice);
					  
				  }
				  
				  //System.out.println("eff price.."+line.getNetUnitPrice());
				  
				  
				  
				  System.out.println("eff looms.."+line.getMinlooms());
			  }
		  }
		  
		  
		  
		  minLooms=obj.getRcplLoomcategory().getMinlooms();// Getting the Actual Minimum Looms from Loom Category for non assigned designation in Looms Category  
		  
		 
		  cgprice=obj.getRcplLoomcategory().getExtraefficiency(); // Getting the ExctraEfficeincy from Loom Category for non assigned designation in Looms Category  
		  
		  String designId=obj.getEmployee().getDesignation().getId();   
		  OBCriteria<RcplLoomcategorylines> loomLinesCriteria=OBDal.getInstance().createCriteria(RcplLoomcategorylines.class);
		  //Here i am getting NaN error..... 
		  loomLinesCriteria.add(Restrictions.eq(RcplLoomcategorylines.PROPERTY_RCPLLOOMCATEGORY,obj.getRcplLoomcategory()));
		  loomLinesCriteria.add(Restrictions.eq(RcplLoomcategorylines.PROPERTY_DESIGNATION,obj.getEmployee().getDesignation()));
		  
		  System.out.println("list is "+loomLinesCriteria.list().size());
		  
		  
		  // Criteria for LoomsCategoryLines to get 
		 
		  for(RcplLoomcategorylines lines : loomLinesCriteria.list()) {
			  
			  /* OBCriteria<RcplLoomcatlinsclothtype> clothType =OBDal.getInstance().createCriteria(RcplLoomcatlinsclothtype.class);
			  clothType.add(Restrictions.eq(RcplLoomcatlinsclothtype.PROPERTY_RCPLLOOMCATEGORYLINES,lines));
			  
			  for(RcplLoomcatlinsclothtype typeOfCloth : clothType.list()){
				  minLooms=typeOfCloth.getMinlooms().longValue();
				  cgprice=typeOfCloth.getExtraefficiency();
			  }
			  
			   // Getting the Designation from 
			  
			  if(obj.getEmployee().getDesignation().getId().equals(lines.getDesignation().getId())){
				  
				  greyMinLooms=getValue(designId,obj.getRcplLoomcategory().getId(),"G","minlooms");
				  dyedMinLooms=getValue(designId,obj.getRcplLoomcategory().getId(),"D","minlooms");
				  greyEfficiency= getValue(designId,obj.getRcplLoomcategory().getId(),"G","extraefficiency");
				  dyedEfficiency= getValue(designId,obj.getRcplLoomcategory().getId(),"D","extraefficiency");
				  
				  designId=lines.getDesignation().getId();
				  
				  
				  if((prodlooms==greyCount) || (greyCount>=dyedCount)){
					  minLooms=greyMinLooms.longValue();
					  cgprice=new BigDecimal(greyEfficiency);
					  System.out.println("Log 1... "+minLooms+" and "+cgprice);
					  
				  }
				  
				  if(prodlooms==dyedCount || (greyCount<dyedCount)){
					  minLooms=dyedMinLooms.longValue();
					  cgprice=new BigDecimal(dyedEfficiency);
					  System.out.println("Log 2... "+minLooms+" and "+cgprice);
					  
				  }
			  }
		  }

		 
		 
		 
		  
		  if(obj.getEmployee().getDesignation().getId().equals(designId)){
			  
			  if(prodlooms>=minLooms){ //  prodlooms is No of Looms given and minLooms is 
				  
				  prodamount=efprice; // Efficiency Added...
				  System.out.println("prodamount 1... "+prodamount);
			  }
			  
			  if(prodlooms>minLooms){
				  prodamount=prodamount.add((new BigDecimal(prodlooms-minLooms)).multiply(cgprice));
				  System.out.println("prodamount 2... "+prodamount);
			  }
			  
		  }else if(prodlooms>=minLooms){
				  prodamount=efprice.add((new BigDecimal(prodlooms-minLooms)).multiply(cgprice));
				  System.out.println("prodamount else if ... "+prodamount);
			 // }
		  }else{
			  prodamount=new BigDecimal(0);
		  }
		  
		  
		  
		  
		  //obj.setEffavg(effavg);
		  BigDecimal avg2=new BigDecimal(avg1,new MathContext(4));
		  
		  System.out.println("Average "+avg2);
		  
		  
		  return prodamount;
	  }
	
	*/
	 public Integer getValue(String DesignationId, String loomCategoryId,String type,String columnName){
		 Integer value=0;
		 String sql="select "+columnName+" from rcpl_loomcatlinsclothtype clothtype "+
		  "where clothtype.rcpl_loomcategorylines_id=(select catlines.rcpl_loomcategorylines_id from rcpl_loomcategorylines catlines where "+ 
		   " catlines.rcpl_loomcategory_id=(select rcpl_loomcategory_id from rcpl_loomcategory where rcpl_loomcategory_id='"+loomCategoryId+"') and catlines.rchr_designation_id='"+DesignationId+"' ) and clothtype.yarntype='"+type+"' ";
		   try {		
	            Connection conn = OBDal.getInstance().getConnection();
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(sql);
	            //System.out.println("rs is " + rs);
	            while (rs.next()) {
	            	value=rs.getInt(1);
	        }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 
		  return value;
	  }
	  
}
