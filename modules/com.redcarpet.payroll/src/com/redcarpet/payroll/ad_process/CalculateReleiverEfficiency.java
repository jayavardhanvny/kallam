package com.redcarpet.payroll.ad_process;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import com.redcarpet.payroll.data.RCPLEfficiency;
import com.redcarpet.payroll.data.RCPLEfficiencyLine;
//import com.redcarpet.goodsissue.data.RCGI_GoodsIssue;
//import com.redcarpet.goodsissue.data.RCGI_GoodsIssue_Line;
import com.redcarpet.payroll.data.RCPLProdIncentive;
import com.redcarpet.payroll.data.RCPLProdIncentiveLine;
import com.redcarpet.payroll.data.RCPLLoom;
import com.redcarpet.payroll.data.RCPLLoomCategory;
import com.redcarpet.payroll.data.RCPLRelievereffcncy;
import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;

import org.openbravo.base.exception.OBException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.model.procurement.Requisition;
import org.openbravo.model.procurement.RequisitionLine;
import org.openbravo.model.common.enterprise.Warehouse;
import org.openbravo.model.common.enterprise.Locator;
/**
 *
 * @author Tirumal
 * 
 */
public class CalculateReleiverEfficiency extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String id = bundle.getParams().get("Rcpl_Prodincentive_ID").toString();
        OBError success = new OBError();
        success.setType("Success");
        success.setTitle("Success");
        success.setMessage("Reliever Efficiency Calculated successfully");
        RCPLProdIncentive prodIncentive = OBDal.getInstance().get(RCPLProdIncentive.class, id);
		OBCriteria<RCPLProdIncentiveLine> incetLines=OBDal.getInstance().createCriteria(RCPLProdIncentiveLine.class);
		incetLines.add(Restrictions.eq(RCPLProdIncentiveLine.PROPERTY_DATE,prodIncentive.getDate()));
		incetLines.add(Restrictions.eq(RCPLProdIncentiveLine.PROPERTY_RCPRSHIFT,prodIncentive.getRcprShift()));
		double effcny=0;
		double avgEffcny=0;
		long length=incetLines.list().size();
		long count=0;
		//Long count = new Long("0");
		if(length==0){
			throw new OBException("No lines for date: "+prodIncentive.getDate()+"and shift: "+prodIncentive.getRcprShift().getEntityName()+".");
		}else{
        for(RCPLProdIncentiveLine rLine : incetLines.list()){
        		if(Boolean.TRUE.equals(prodIncentive.getRcplLoomcategory().isToyota())){
        			if(Boolean.TRUE.equals(rLine.getRcplLoom().getRcplLoomcategory().isToyota())){
        		double lineEffciency = Double.parseDouble(rLine.getEfficiency());
            	effcny = effcny+lineEffciency;
            	count++;
            	}
        	}else if(rLine.getRcplLoom().getRcplLoomcategory().equals(prodIncentive.getRcplLoomcategory())){
        	Double lineEffciency = Double.parseDouble(rLine.getEfficiency());
        	effcny = effcny+lineEffciency;
        	
        	count++;
        	}//if
        	}//for
        if(count==0){
        	throw new OBException("There is no lines for that date: "+prodIncentive.getDate()+"and loom category: "+prodIncentive.getRcplLoomcategory()+".");
        }
         avgEffcny=effcny/count;
        // System.out.println("effcny is.."+effcny);
        System.out.println("avg effcny is.."+avgEffcny);
		}
        BigDecimal effcnyPrize = getLoomEfficiency(avgEffcny, prodIncentive);
        //prodIncentive.setEffavg(BigDecimal.valueOf(effcnyPrize));
        prodIncentive.setEffavg(BigDecimal.valueOf(avgEffcny));
        prodIncentive.setAmount(effcnyPrize);
        prodIncentive.setSetreleiver(Boolean.TRUE);
        OBDal.getInstance().save(prodIncentive);
        bundle.setResult(success);
       
    }
    
    public BigDecimal getLoomEfficiency(double avgEffcny, RCPLProdIncentive prodIncentive){
      BigDecimal efprice=new BigDecimal(0);
  	  BigDecimal cgprice=new BigDecimal(0);
  	  BigDecimal prodamount=new BigDecimal(0);
    	OBCriteria<RCPLEfficiency> eff=OBDal.getInstance().createCriteria(RCPLEfficiency.class);
  	  eff.add(Restrictions.le(RCPLEfficiency.PROPERTY_FROMRANGE,new BigDecimal(avgEffcny,new MathContext(4))));
  	  eff.add(Restrictions.ge(RCPLEfficiency.PROPERTY_TORANGE,new BigDecimal(avgEffcny,new MathContext(4))));
  	  eff.add(Restrictions.eq(RCPLEfficiency.PROPERTY_ISRELEIVER,Boolean.TRUE));
  	 // System.out.println("size1..."+eff.list().size());
  	  for(RCPLEfficiency ef : eff.list()){
  		  
  		  RCPLEfficiency effci = OBDal.getInstance().get(RCPLEfficiency.class, ef.getId());
  		  final OBCriteria<RCPLRelievereffcncy> efline=OBDal.getInstance().createCriteria(RCPLRelievereffcncy.class);
  		  efline.add(Restrictions.eq(RCPLRelievereffcncy.PROPERTY_RCPLEFFICIENCY,effci));
  		  efline.add(Restrictions.eq(RCPLRelievereffcncy.PROPERTY_RCPLLOOMCATEGORY, prodIncentive.getRcplLoomcategory()));
  		  //efline.add(Restrictions.eq(RCPLEfficiencyLine.PROPERTY_RCPLLOOMCATEGORY,OBDal.getInstance().get(RCPLLoomCategory.class, prodIncentive.getRcplLoomcategory().getId())));
  		 // System.out.println("size2.."+efline.list().size());
  		  for(RCPLRelievereffcncy line:efline.list()){
  			  //System.out.println("under list");
  			  efprice=line.getNetUnitPrice();
  			  
  		  }  
  	  }
    	return efprice;
    }
}