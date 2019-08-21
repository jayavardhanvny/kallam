package com.redcarpet.epcg.ad_callouts;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.RoundingMode;
import java.math.MathContext;

import com.rcss.humanresource.data.RCHRQualitystandard;
import com.rcss.humanresource.util.HqlUtils;
import com.rcss.humanresource.util.RchrConstantType;
import com.redcarpet.epcg.data.EpcgVariant;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.data.FieldProvider;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.financialmgmt.payment.PaymentTerm;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import com.rcss.humanresource.data.RCHRInspweave;
import com.rcss.humanresource.data.RCHRWarpyarntype;
import com.redcarpet.epcg.data.EpcgYarncosttemplate;
import com.redcarpet.epcg.data.EpcgYarncosttemplatelines;
import org.openbravo.model.common.businesspartner.Discount;



import javax.servlet.ServletException;
import java.text.DecimalFormat;


public class Epcg_EnquiryCostingForm extends SimpleCallout {

	final static Logger log = Logger.getLogger("Epcg_EnquiryCostingForm");
	@Override
	protected void execute(CalloutInfo info) throws ServletException {
		String strSortMasterId = info.getStringParameter("inprchrQualitystandardId",null);
		String message = "";
		DecimalFormat decimalFormat = new DecimalFormat("#.###");
		if(strSortMasterId!=null && !strSortMasterId.equals("")) {
				String strBpartnerId = info.getStringParameter("inpcBpartnerId", null);
			BigDecimal salescommission = info.getBigDecimalParameter("inpsalescommission");
			BigDecimal pickInsert = info.getBigDecimalParameter("inppickinsert");
			//BigDecimal fabricLossPercentage = info.getBigDecimalParameter("inplossoffabricpercent");
			String stringBCI = info.getStringParameter("inpbci", null);
			String stringCF = info.getStringParameter("inpcf", null);
			String stringGOLD = info.getStringParameter("inpgold", null);
			String stringSlubYarnWarp = info.getStringParameter("inpslubWarp", null);
			String stringSlubYarnWeft = info.getStringParameter("inpslubWeft", null);
			BigDecimal orderquantity = info.getBigDecimalParameter("inporderquantity");
			BigDecimal spilit = info.getBigDecimalParameter("inpspilit");
			BigDecimal creditPeriod = info.getBigDecimalParameter("inpcreditperiod");
			BigDecimal actualSpeed = info.getBigDecimalParameter("inpactualspeed");
			BigDecimal actualEfficiency = info.getBigDecimalParameter("inpactualefficiency");
			String stringWarpVariantId = info.getStringParameter("inpepcgVariantId", null);
			String stringWeftVariantId = info.getStringParameter("inpweftvariant", null);
			//Cash Discount
			BigDecimal cashdiscount = info.getBigDecimalParameter("inpcashdiscount");

			// Getting Selling Price...
			BigDecimal exmillpricerspermts = info.getBigDecimalParameter("inpexmillpricerspermts");
			//Calculating Cash Discount percentage to Value...
			FabEnquiryCallout fabEnquiryCallout = new FabEnquiryCallout();

			BigDecimal epi, ppi, totalWidth, tc, warpCrimp, warpRate = BigDecimal.ZERO,
					totalSalesCommission = BigDecimal.ZERO, weftRate = BigDecimal.ZERO, warpWasteWtkgspermts, warpwtkgspermts,
					weftWasteWtkgspermts, weftwtkgspermts,
					warpWastePercentage = new BigDecimal(1.04),
					weftWastePercentage = new BigDecimal(1.01),
					cashDiscountValue = BigDecimal.ZERO,
					fabricLossPercentage,
					creditPeriodPer = BigDecimal.ZERO, sizing = BigDecimal.ZERO,
					commission = BigDecimal.ZERO, interestcost = BigDecimal.ZERO,
					speed = BigDecimal.ZERO,
					efficiency = BigDecimal.ZERO, netPrice = BigDecimal.ZERO,
					productionperdayperloommtsonefficincy = BigDecimal.ZERO,
					warpWasteWtkgsPerMtsWithSizing = BigDecimal.ZERO,
					yarncostmts = BigDecimal.ZERO,
					totalvariablecostmts = BigDecimal.ZERO, fringeWidth = BigDecimal.ZERO,
					totalVariableCostWithLoss = BigDecimal.ZERO, perpickcontributioin = BigDecimal.ZERO, contributionrsploom = BigDecimal.ZERO, noOfDays = BigDecimal.ZERO, totalwtwithkgs, glmwith,
					pckgmatrlconsumptionmts = new BigDecimal(1);
			Integer warpcountInt = 0;
			Integer weftcountInt = 0;

			double productionperdayperloommts = 0;
			//Integer warpCrimp = 0;


			RCHRQualitystandard rchrQualitystandard = OBDal.getInstance().get(RCHRQualitystandard.class, strSortMasterId);
			epi = rchrQualitystandard.getEpi();
			ppi = rchrQualitystandard.getPpi();
			totalWidth = rchrQualitystandard.getReedspace(); // Reedspace...

			tc = fabEnquiryCallout.getTCValue(rchrQualitystandard, pickInsert);
			warpCrimp = rchrQualitystandard.getWarpcrimp();
			if (strBpartnerId != null && !strBpartnerId.equals("")) {
				totalSalesCommission = salescommission.add(fabEnquiryCallout.getExtraDiscountCommission(strBpartnerId));
			}
			//log.info("salescommission "+totalSalesCommission);
			if (rchrQualitystandard.getProduct() != null && !rchrQualitystandard.equals("")) {
				if (rchrQualitystandard.getWeftProduct() != null && !rchrQualitystandard.equals("")) {
					try {
						warpcountInt = Integer.parseInt(rchrQualitystandard.getProduct().getName());
						weftcountInt = Integer.parseInt(rchrQualitystandard.getWeftProduct().getName());
						//warpCrimp =  Integer.parseInt(warpCrimpPercentage);
					} catch (NumberFormatException e) {

						e.printStackTrace();
					}
				} else {
					message = "Weft Product is not Given in Sort Master";
					info.addResult("ERROR",message);
				}

			speed = rchrQualitystandard.getSpeed();
			efficiency = rchrQualitystandard.getEfficiency();
			sizing = rchrQualitystandard.getSizing();
			fabricLossPercentage = rchrQualitystandard.getValueloss();

			List<EpcgYarncosttemplatelines> warpYarnCostTemplateLineList = fabEnquiryCallout.getYarnCostTemplateLineList(rchrQualitystandard.getProduct(),
					rchrQualitystandard.getRchrWarpyarntype(),
					rchrQualitystandard.getEpcgVariant());

			List<EpcgYarncosttemplatelines> weftYarnCostTemplateLineList = fabEnquiryCallout.getYarnCostTemplateLineList(rchrQualitystandard.getWeftProduct(),
					rchrQualitystandard.getWarpyarntype(),
					rchrQualitystandard.getWeftvariant());
			if (warpYarnCostTemplateLineList.size() > 0) {
				warpRate = warpYarnCostTemplateLineList.get(0).getRate();
				//sizing=new BigDecimal(warpYarnCostTemplateLineList.get(0).getSizing());
				info.addResult("inpepcgYarncosttemplatelinesId", warpYarnCostTemplateLineList.get(0).getId());
				warpWastePercentage = warpYarnCostTemplateLineList.get(0).getEpcgYarncosttemplate().getWarpwastepercentage();
				weftWastePercentage = warpYarnCostTemplateLineList.get(0).getEpcgYarncosttemplate().getWeftwastepercentage();
				noOfDays = warpYarnCostTemplateLineList.get(0).getEpcgYarncosttemplate().getNoofdays();
				fringeWidth = warpYarnCostTemplateLineList.get(0).getEpcgYarncosttemplate().getFringewidth();
				//pckgmatrlconsumptionmts =
			} else {
				message = "Warp Rate is Not Created with this Product, WarpType in Costing Template";
				info.addResult("ERROR",message);
			}
			if (weftYarnCostTemplateLineList.size() > 0) {
				weftRate = weftYarnCostTemplateLineList.get(0).getRate();
				//speed=new BigDecimal(weftYarnCostTemplateLineList.get(0).getSpeed());
				//efficiency=new BigDecimal(weftYarnCostTemplateLineList.get(0).getEfficiency());
				info.addResult("inpepcgYarncosttemplatelinesW",
						weftYarnCostTemplateLineList.get(0).getId());
			} else {
				message = "Weft Rate is Not Created with this Product, WarpType in Costing Template";
				info.addResult("ERROR",message);
			}


			if ((!stringWarpVariantId.equals("")) && (stringWarpVariantId != null)) {
				EpcgVariant epcgWarpVariant = OBDal.getInstance().get(EpcgVariant.class, stringWarpVariantId);
				warpRate = warpRate.add(epcgWarpVariant.getRate());
			} else {
				info.addResult("WARNIG","Check Warp Variant is not selected");
			}
			if ((!stringWeftVariantId.equals("")) && (stringWeftVariantId != null)) {
				EpcgVariant epcgWeftVariant = OBDal.getInstance().get(EpcgVariant.class, stringWeftVariantId);
				weftRate = weftRate.add(epcgWeftVariant.getRate());
				//EpcgVariant epcgVariant =
				//OBCriteria<EpcgVariant> epcgVariantOBCriteriaCF = OBDal.getInstance().createCriteria(EpcgVariant.class);
				//epcgVariantOBCriteriaCF.add(Restrictions.eq(EpcgVariant.PROPERTY_SEARCHKEY, RchrConstantType.VARIANT_TYPE_CF));

				//warpRate = warpRate.add(epcgVariantOBCriteriaCF.list().get(0).getRate());
				//weftRate = weftRate.add(epcgVariantOBCriteriaCF.list().get(0).getRate());
			} else {
				message = "Check Weft Variant is not selected";
				info.addResult("inpdescription", message);
				info.addResult("WARNIG",message);
			}

			warpwtkgspermts = fabEnquiryCallout.getWarpWeigthPerMts(totalWidth, epi, warpcountInt, warpCrimp);
			//log.info("warpwtkgspermts "+warpwtkgspermts);

			weftwtkgspermts = fabEnquiryCallout.getWeftWeigthPerMts(totalWidth, ppi, weftcountInt, pickInsert,
					fringeWidth);
			//log.info("weftwtkgspermts "+weftwtkgspermts);
			cashDiscountValue = fabEnquiryCallout.getCashDiscountValue(exmillpricerspermts, cashdiscount);
			//log.info("cashDiscountValue "+cashDiscountValue);
			creditPeriodPer = fabEnquiryCallout.getCreditPeriodPer(creditPeriod, noOfDays.intValue());
			//log.info("creditPeriodPer "+creditPeriodPer);
			commission = fabEnquiryCallout.getCommission(exmillpricerspermts, totalSalesCommission);
			//log.info("commission "+commission);
			interestcost = fabEnquiryCallout.getInterest(exmillpricerspermts, creditPeriodPer);
			//log.info("interestcost "+interestcost);
			if (cashdiscount.doubleValue() > 0) {
				interestcost = new BigDecimal(0);
				//log.info("in cashdiscount");
				info.addResult("inpcreditperiod", new BigDecimal(0));
			}
			if (creditPeriod.doubleValue() > 0) {
				//log.info("in creditperiod");
				cashDiscountValue = new BigDecimal(0);
				info.addResult("inpcashdiscount", new BigDecimal(0));
			}


			//speed=actualSpeed.doubleValue();
			//efficiency = actualEfficiency.doubleValue();


			warpWasteWtkgspermts = fabEnquiryCallout.getWasteWtkgspermts(warpwtkgspermts, warpWastePercentage);
			weftWasteWtkgspermts = fabEnquiryCallout.getWasteWtkgspermts(weftwtkgspermts, weftWastePercentage);
			//log.info("warpWasteWtkgspermts "+warpWasteWtkgspermts);
			//log.info("weftWasteWtkgspermts "+weftWasteWtkgspermts);

			productionperdayperloommts = fabEnquiryCallout.getProductionPerDayPerLoomMts(speed, ppi, spilit);
			//log.info("productionperdayperloommts "+productionperdayperloommts);

			productionperdayperloommtsonefficincy = fabEnquiryCallout.
					getProductionPerDayPerLoomMtsOnEfficincy(productionperdayperloommts, efficiency);
			//log.info("productionperdayperloommtsonefficincy "+productionperdayperloommtsonefficincy);
			warpWasteWtkgsPerMtsWithSizing = fabEnquiryCallout.
					getWarpWasteWtkgsPerMtsWithSizing(warpWasteWtkgspermts, sizing);

			//log.info("warpWasteWtkgsPerMtsWithSizing "+warpWasteWtkgsPerMtsWithSizing);
			yarncostmts = fabEnquiryCallout.getTotalYarnCostMts(warpWasteWtkgspermts, warpRate,
					weftWasteWtkgspermts, weftRate);
			//log.info("yarncostmts "+yarncostmts);
			totalvariablecostmts = yarncostmts.add(warpWasteWtkgsPerMtsWithSizing).add(pckgmatrlconsumptionmts);
			//log.info("totalvariablecostmts "+totalvariablecostmts);
			totalVariableCostWithLoss = fabEnquiryCallout.
					getTotalVariableCostWithLoss(totalvariablecostmts, fabricLossPercentage);
			//log.info("totalVariableCostWithLoss "+totalVariableCostWithLoss);

			netPrice = fabEnquiryCallout.getNetPrice(exmillpricerspermts,
					commission, interestcost, totalVariableCostWithLoss);
			//log.info(" netprice "+netPrice);

			perpickcontributioin = fabEnquiryCallout.
					getPerPickContribution(exmillpricerspermts, totalVariableCostWithLoss, ppi);

			//log.info(" perpickcontributioin "+perpickcontributioin);

			contributionrsploom = fabEnquiryCallout.
					getContributionAmount(netPrice, productionperdayperloommtsonefficincy);
			//log.info("Contribution "+contributionrsploom);


			totalwtwithkgs = warpwtkgspermts.add(weftwtkgspermts);
			glmwith = totalwtwithkgs.multiply(warpWastePercentage);
			double gsmwo = (totalwtwithkgs.doubleValue() / totalWidth.doubleValue()) * 39.37;

			//double gsmwo=(totalwtwithkgs/totalWidth.doubleValue())*39.37;
			//double glmwith=totalwtwithkgs*1.04;
			double gsmwith = (glmwith.doubleValue() / totalWidth.doubleValue()) * 39.37;

			//double pro_day_loom_hun=((24*60*speed)/(39.37*ppi.doubleValue()))*spilit.doubleValue();

			info.addResult("inptc", tc);
			info.addResult("inpwidthinches", totalWidth);
			info.addResult("inpfabricwidth", fabEnquiryCallout.getWidth(rchrQualitystandard));
			info.addResult("inprchrInspweaveId", rchrQualitystandard.getRchrInspweave().getId());
			info.addResult("inpqstandard", rchrQualitystandard.getQstandard().toString());
			info.addResult("inponcontract", rchrQualitystandard.getPartyconstruction().toString());
			info.addResult("inpepi", epi);
			info.addResult("inponloomepi", rchrQualitystandard.getOnloomepi());
			info.addResult("inpreed", rchrQualitystandard.getReed());
			info.addResult("inpppi", ppi);
			info.addResult("inpcUomId", rchrQualitystandard.getProduct().getUOM().getId());
			info.addResult("inpwarpcrimpvalues", warpCrimp.toString());
			info.addResult("inpwarpratekgs", warpRate);
			info.addResult("inpweftratekgs", weftRate);
			info.addResult("inpwarpwtkgspermts", warpwtkgspermts.setScale(3, RoundingMode.HALF_EVEN));
			info.addResult("inpweftwtkgspermts", weftwtkgspermts.setScale(3, RoundingMode.HALF_EVEN));
			info.addResult("inptotalwtkgspermts", warpwtkgspermts.add(weftwtkgspermts).
					setScale(3, RoundingMode.HALF_EVEN));
			info.addResult("inpwarpwtkgswithwaste", warpWasteWtkgspermts.setScale(3, RoundingMode.HALF_EVEN));
			info.addResult("inpweftwtkgswithwaste", weftWasteWtkgspermts.setScale(3, RoundingMode.HALF_EVEN));
			info.addResult("inptotalwtkgswithwaste", warpWasteWtkgspermts.add(weftWasteWtkgspermts).
					setScale(3, RoundingMode.HALF_EVEN));
			//log.info("tc...    "+tc);
			//log.info("totalWidth is "+totalWidth);
			info.addResult("inpnetprice", netPrice);
			info.addResult("inpyeildpdayploom", productionperdayperloommts);
			info.addResult("inpyeildpdayploomoneffcny", productionperdayperloommtsonefficincy);
			//info.addResult("inptotalproductionmts",new BigDecimal(totalproductionmts).setScale(0, RoundingMode.HALF_EVEN));
			info.addResult("inpcommission", commission);
			info.addResult("inpinterestcost", interestcost);
			info.addResult("inppeyurpickcontributioin", perpickcontributioin);
			info.addResult("inpcontributionrsploom", contributionrsploom.setScale(0, RoundingMode.HALF_EVEN));
			info.addResult("inpfabricloss", totalVariableCostWithLoss.setScale(2, RoundingMode.HALF_EVEN));
			info.addResult("inpsizingchemicalmts", warpWasteWtkgsPerMtsWithSizing.setScale(2, RoundingMode.HALF_EVEN));
			info.addResult("inpspeed", speed);
			info.addResult("inpefficiency", efficiency);
			info.addResult("inpactualspeed", speed);
			info.addResult("inpactualefficiency", efficiency);
			info.addResult("inplossoffabricpercent", fabricLossPercentage);
			info.addResult("inptypeofsort", rchrQualitystandard.getTypeofsort());
			//info.addResult("inpglmwith",decimalFormat.format(glmwith));
			//info.addResult("inpgsmwith",new BigDecimal(decimalFormat.format(gsmwith)));
			//info.addResult("inpgsmwithout",new BigDecimal(decimalFormat.format(gsmwo)));
			info.addResult("inpgsmwith", new BigDecimal(gsmwith).setScale(3, RoundingMode.HALF_EVEN));
			info.addResult("inpgsmwithout", new BigDecimal(gsmwo).setScale(3, RoundingMode.HALF_EVEN));
			info.addResult("inpglmwith", glmwith.setScale(3, RoundingMode.HALF_EVEN));
			message =  "Success";
			info.addResult("Info",message);
			} else {
				message = "Warp Product is not Given in Sort Master";
				info.addResult("ERROR",message);
			}
		}else{
			message = "Sort Number is Empty";
			info.addResult("WARNING",message);
		}
	}




	public String getSortNumber(String construction){
		String sortno="";
		String sql = "SELECT rchr_qualitystandard_id as sortId FROM rchr_qualitystandard WHERE qstandard SIMILAR TO '"+construction+"'";
		//log.info("sql "+sql);
		//System.out.println("construction "+construction);
		try{
			Connection conn = OBDal.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					//System.out.println("In Result next "+rs.getString("sortId"));
					sortno = rs.getString("sortId");
				}
			
		}catch(SQLException e){
			
		}
			
			
		return sortno;
	}
	
}

