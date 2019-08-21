package com.redcarpet.epcg.ad_callouts;

import com.rcss.humanresource.data.RCHRQualitystandard;
import com.rcss.humanresource.data.RCHRWarpyarntype;
import com.rcss.humanresource.util.HqlUtils;
import com.redcarpet.epcg.data.EpcgVariant;
import com.redcarpet.epcg.data.EpcgYarncosttemplatelines;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Discount;
import org.openbravo.model.common.plm.Product;

import javax.servlet.ServletException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class FabEnquiryCallout {
    public List<EpcgYarncosttemplatelines> getYarnCostTemplateLineList(Product product, RCHRWarpyarntype rchrWarpyarntype,
                                                                       EpcgVariant epcgVariant){
        HqlUtils hqlUtils = new HqlUtils();
        List<EpcgYarncosttemplatelines> epcgYarncosttemplatelinesList =
                hqlUtils.getYarnCostTemplateLines(product,
                        rchrWarpyarntype,epcgVariant);
        return epcgYarncosttemplatelinesList;
    }
    public BigDecimal getWidth(RCHRQualitystandard rchrQualitystandard){
        BigDecimal totalWidth = BigDecimal.ZERO;
        try{
            totalWidth = new BigDecimal(Double.parseDouble(rchrQualitystandard.getRchrInspgreigewidth().getName()));
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return totalWidth;
    }
    public BigDecimal getTCValue(RCHRQualitystandard rchrQualitystandard,BigDecimal pickInsert){
        return rchrQualitystandard.getEpi().add(rchrQualitystandard.getPpi()).multiply(pickInsert);
    }



    void tempMetho(){


/*


        DecimalFormat f = new DecimalFormat("#.###");
       // String strProductId = info.getStringParameter("inpmProductId",null);
        String strBpartnerId = info.getStringParameter("inpcBpartnerId", null);
        String warpCrimpPercentage = info.getStringParameter("inpwarpcrimpvalues", null);
        //getting which column is changed
        String changedColumn = info.getStringParameter("CHANGED_COLUMN",null);
        log.info("Changed column is "+changedColumn);

        //Assigning Unit Of Measurement... by getting the Product

        //String strWeftCountId = info.getStringParameter("inpweftproduct",null);

        String strCostingtempwarp = info.getStringParameter("inpepcgYarncosttemplatelinesId",null);
        String strCostingtempweft = info.getStringParameter("inpepcgYarncosttemplatelinesW",null);
        BigDecimal epi = info.getBigDecimalParameter("inpepi");
        BigDecimal ppi = info.getBigDecimalParameter("inpppi");
        BigDecimal pickInsert = info.getBigDecimalParameter("inppickinsert");
        BigDecimal tc = epi.add(ppi).multiply(pickInsert);
        info.addResult("inptc",tc);
        log.info("tc...    "+tc);
        BigDecimal spilit = info.getBigDecimalParameter("inpspilit");
        BigDecimal fabricWidthInches = info.getBigDecimalParameter("inpfabricwidth");
        BigDecimal oneSidedSelvedgeWidth = info.getBigDecimalParameter("inponesideselvedgewidth");
        BigDecimal salescommission = info.getBigDecimalParameter("inpsalescommission");
        BigDecimal fabricLossPercentage = info.getBigDecimalParameter("inplossoffabricpercent");
        extraCommission.add(salescommission);

        BigDecimal totalWidth = fabricWidthInches.add(oneSidedSelvedgeWidth.multiply(new BigDecimal(2)));
        // Adding total width inches
        log.info("totalWidth is "+totalWidth);
        info.addResult("inpwidthinches",totalWidth);

        //log.info("Agent salescommission is "+salescommission);
        //log.info("Total Commission is "+extraCommission);

        BigDecimal creditperiod = info.getBigDecimalParameter("inpcreditperiod");
        //Cash Discount
        BigDecimal cashdiscount = info.getBigDecimalParameter("inpcashdiscount");

        // Getting Selling Price...
        BigDecimal exmillpricerspermts = info.getBigDecimalParameter("inpexmillpricerspermts");
        //Calculating Cash Discount percentage to Value...
        double cashDiscountValue = cashdiscount.multiply(exmillpricerspermts).doubleValue()/100;
        //log.info("cashDiscountValue "+cashDiscountValue);
        BigDecimal orderquantity = info.getBigDecimalParameter("inporderquantity");
        //String weaveId = info.getStringParameter("inprchrInspweaveId", null);
        EpcgYarncosttemplatelines warptype = null;
        EpcgYarncosttemplatelines wefttype = null;
        RCHRInspweave weave  = null;
        try{
            //weave = OBDal.getInstance().get(RCHRInspweave.class, weaveId);
            warptype=OBDal.getInstance().get(EpcgYarncosttemplatelines.class, strCostingtempwarp);
            wefttype=OBDal.getInstance().get(EpcgYarncosttemplatelines.class, strCostingtempweft);

        }catch(Exception e){

        }

        double speed=wefttype.getSpeed().doubleValue();
        double efficiency=wefttype.getEfficiency().doubleValue();

        double sizing=warptype.getSizing().doubleValue();

        double warpRate=warptype.getRate().doubleValue();
        double weftRate=wefttype.getRate().doubleValue();
        if((changedColumn.equals("inpactualspeed")) | (changedColumn.equals("inpactualefficiency"))){
            BigDecimal actualSpeed = info.getBigDecimalParameter("inpactualspeed");
            BigDecimal actualEfficiency = info.getBigDecimalParameter("inpactualefficiency");
            speed=actualSpeed.doubleValue();
            efficiency = actualEfficiency.doubleValue();
        }

        //log.info("spped..."+speed);


        Integer warpcountInt = 0;
        Integer weftcountInt = 0;
        Integer warpCrimp = 0;
        if(warptype!=null||wefttype!=null){
            try{
                warpcountInt = Integer.parseInt(warptype.getProduct().getName());
                weftcountInt = Integer.parseInt(wefttype.getProduct().getName());
                warpCrimp =  Integer.parseInt(warpCrimpPercentage);
            }catch(NumberFormatException e){
                e.printStackTrace();
            }
        }





        if((!epi.equals(BigDecimal.ZERO))&
                (!ppi.equals(BigDecimal.ZERO)) &
                (!fabricWidthInches.equals(BigDecimal.ZERO))&
                (!exmillpricerspermts.equals(BigDecimal.ZERO))){


            // Warp Crimp is calculated as (((warpcountInt * EPI )/TotalFabricWidth/1.693)* ((warpCrimp+100)/100))/1000
            // 8 + 100 =
            log.info("warpCrimp "+warpCrimp);
            log.info("(1+warpCrimp)/100 "+((1+warpCrimp)/100));
            //BigDecimal warpwtkgspermts = getWarpWeigthPerMts(totalWidth,epi,warpcountInt,warpCrimp);
    //log.info("warpwtkgspermts "+new BigDecimal(warpwtkgspermts).setScale(3, RoundingMode.HALF_EVEN));
            //info.addResult("inpwarpwtkgspermts",warpwtkgspermts.setScale(3, RoundingMode.HALF_EVEN));

            //info.addResult("inpweftwtkgspermts",new BigDecimal(weftwtkgspermts).setScale(3, RoundingMode.HALF_EVEN));
            double totalwtwithkgs =  warpwtkgspermts.doubleValue()+weftwtkgspermts;
            info.addResult("inpqstandard",sbf.toString());
            //info.addResult("inponcontract",sbf.toString());



            double noofloomsworked = 1;

            double productionperdayperloommts = ((24*60*speed)/(39.37*ppi.doubleValue()))*spilit.doubleValue();
            double productionperdayperloommtsonefficincy = (productionperdayperloommts*efficiency)/100;
            double workingdays = 1;

            //double totalproductionmts = workingdays*productionperdayperloommtsonefficincy*noofloomsworked;
            double creditperiodper30 = creditperiod.doubleValue()/30;
            //BigDecimal yarncostmts = (new BigDecimal(warpwtwithwaste).setScale(3, RoundingMode.HALF_EVEN).
            multiply(new BigDecimal(warpRate))).add(new BigDecimal(weftwtwithwaste).setScale(3, RoundingMode.HALF_EVEN).
            multiply(new BigDecimal(weftRate)));
            double yarncostmts = (warpwtwithwaste*warpRate)+(weftwtwithwaste*weftRate);
            log.info("yarn cost..."+yarncostmts);
            double sizingCal = warpwtwithwaste*sizing;
            double pckgmatrlconsumptionmts = 1;
            double totalvariablecostmts = yarncostmts + sizingCal + pckgmatrlconsumptionmts;

            // Loss of Fabric = (totalvariablecostmts * fabricLossPercentage/100 ) + totalvariablecostmts
            double totalVariableCostWithLoss = (totalvariablecostmts * (fabricLossPercentage.doubleValue()/100))+
                    totalvariablecostmts;
            log.info("fabricLossPercentage.doubleValue()/100 "+fabricLossPercentage.doubleValue()/100);
            log.info("totalvariablecostmts * (fabricLossPercentage.doubleValue()/100) "+totalvariablecostmts * (fabricLossPercentage.doubleValue()/100));
            log.info("totalVariableCostWithLoss "+totalVariableCostWithLoss);
            double commission = (exmillpricerspermts.doubleValue()*salescommission.doubleValue())/100;
            double interestcost =  (exmillpricerspermts.doubleValue()*creditperiodper30)/100;


            if(cashdiscount.doubleValue()>0){
                interestcost=0;
                //log.info("in cashdiscount");
                info.addResult("inpcreditperiod",new BigDecimal(0));
            }
            if(creditperiod.doubleValue()>0){
                //log.info("in creditperiod");
                cashDiscountValue=0;
                info.addResult("inpcashdiscount",new BigDecimal(0));
            }

            double netprice = exmillpricerspermts.doubleValue()-commission-interestcost-cashDiscountValue;
            log.info(" netprice "+netprice);

            double perpickcontributioin = (exmillpricerspermts.doubleValue()- totalVariableCostWithLoss)/ppi.doubleValue();
            log.info(" perpickcontributioin "+perpickcontributioin);
            double contributionrsploom = (exmillpricerspermts.doubleValue()-totalVariableCostWithLoss-
                    commission-interestcost-cashDiscountValue)*(productionperdayperloommtsonefficincy);
            log.info("Contribution "+contributionrsploom);
            log.info("loom contri... "+new BigDecimal(exmillpricerspermts.doubleValue()-
                    totalVariableCostWithLoss-commission-interestcost).setScale(2, RoundingMode.HALF_EVEN).doubleValue());
            log.info("prod_eff "+productionperdayperloommtsonefficincy+" lommm...... "+contributionrsploom);

            double orderqtywarpwtwithwaste = orderquantity.doubleValue()*warpwtwithwaste;
            double orderqtyweftwtwithwaste = orderquantity.doubleValue()*weftwtwithwaste;
            double gsmwo=(totalwtwithkgs/totalWidth.doubleValue())*39.37;
            double glmwith=totalwtwithkgs*1.04;
            double gsmwith=(glmwith/totalWidth.doubleValue())*39.37;

            double pro_day_loom_hun=((24*60*speed)/(39.37*ppi.doubleValue()))*spilit.doubleValue();


            double pro_day_loom_eff=pro_day_loom_hun*(efficiency/100);

            double totalproductionmts = workingdays*pro_day_loom_eff*noofloomsworked;

            double peryd=exmillpricerspermts.doubleValue()*0.914;
            double lessdepb=peryd*0.045;
            double exmillpriceperyd=(peryd-lessdepb)/48;
            log.info("ex mill... "+exmillpriceperyd);
            double exportfright=2500/((13000/(glmwith*0.91))*0.98);
            log.info("exportfright price... "+exportfright);
            double interestcostnew=((0.1/360)*60)*(exmillpriceperyd+exportfright);
            double finalprice=exmillpriceperyd+exportfright+interestcostnew;






            log.info("final price... "+finalprice);
            info.addResult("inpspeed",wefttype.getSpeed());
            info.addResult("inpefficiency",wefttype.getEfficiency());
            info.addResult("inpactualspeed",new BigDecimal(speed));
            info.addResult("inpactualefficiency",new BigDecimal(efficiency));

            info.addResult("inpsizingfrommaster",wefttype.getSizing());

            info.addResult("inpnoofloomsworked",new BigDecimal(noofloomsworked));
            //info.addResult("inpyeildpdayploom",new BigDecimal(productionperdayperloommts));
            info.addResult("inpyeildpdayploomoneffcny",new BigDecimal(productionperdayperloommtsonefficincy));
            info.addResult("inpworkingdays",new BigDecimal(workingdays));
            info.addResult("inptotalproductionmts",new BigDecimal(totalproductionmts).setScale(0, RoundingMode.HALF_EVEN));
            info.addResult("inpcreditperiodper30",new BigDecimal(creditperiodper30));
            info.addResult("inpyarncostmts",new BigDecimal(yarncostmts).setScale(2, RoundingMode.HALF_EVEN));
            info.addResult("inpsizingchemicalmts",new BigDecimal(sizingCal).setScale(2, RoundingMode.HALF_EVEN));
            info.addResult("inppckgmatrlconsumptionmts",new BigDecimal(pckgmatrlconsumptionmts));
            info.addResult("inptotalvariablecostmts",new BigDecimal(totalvariablecostmts).setScale(2, RoundingMode.HALF_EVEN));

            //info.addResult("inpfabricloss",new BigDecimal(totalVariableCostWithLoss).setScale(2, RoundingMode.HALF_EVEN));

            //info.addResult("inpcommission",new BigDecimal(commission));
            //info.addResult("inpinterestcost",new BigDecimal(interestcost));
            //info.addResult("inpnetprice",new BigDecimal(netprice));
            //info.addResult("inppeyurpickcontributioin",new BigDecimal(perpickcontributioin));
            //info.addResult("inpcontributionrsploom",new BigDecimal(contributionrsploom).setScale(0, RoundingMode.HALF_EVEN));
            info.addResult("inpgsmwith",new BigDecimal(f.format(gsmwith)));
            info.addResult("inpgsmwithout",new BigDecimal(f.format(gsmwo)));
            info.addResult("inpglmwith",new BigDecimal(f.format(glmwith)));

            info.addResult("inpgsmwith",new BigDecimal(gsmwith).setScale(3, RoundingMode.HALF_EVEN));
            info.addResult("inpgsmwithout",new BigDecimal(gsmwo).setScale(3, RoundingMode.HALF_EVEN));
            info.addResult("inpglmwith",new BigDecimal(glmwith).setScale(3, RoundingMode.HALF_EVEN));

            info.addResult("inpperyd",new BigDecimal(peryd).setScale(3, RoundingMode.HALF_EVEN));
            info.addResult("inporderqtywarpwtwithwaste",new BigDecimal(orderqtywarpwtwithwaste).setScale(0, RoundingMode.HALF_EVEN));
            info.addResult("inporderqtyweftwtwithwaste",new BigDecimal(orderqtyweftwtwithwaste).setScale(0, RoundingMode.HALF_EVEN));
            info.addResult("inpprodDayLoomHun",new BigDecimal(pro_day_loom_hun).setScale(0, RoundingMode.HALF_EVEN));
            info.addResult("inpprodDayLoomEff",new BigDecimal(pro_day_loom_eff).setScale(0, RoundingMode.HALF_EVEN));
            info.addResult("inplessdepb",new BigDecimal(lessdepb).setScale(2, RoundingMode.HALF_EVEN));
            info.addResult("inpexmillpriceusdperyd",new BigDecimal(exmillpriceperyd).setScale(3, RoundingMode.HALF_EVEN));
            info.addResult("inpexportfreightusdperyd",new BigDecimal(exportfright).setScale(3, RoundingMode.HALF_EVEN));
            info.addResult("inpinterestcostnew",new BigDecimal(interestcostnew).setScale(3, RoundingMode.HALF_EVEN));

            log.info("interest cost new...."+new BigDecimal(interestcostnew).setScale(3, RoundingMode.HALF_EVEN)+"........"+interestcostnew);


            info.addResult("inpfinalprice",new BigDecimal(finalprice).setScale(3, RoundingMode.HALF_EVEN));



        }*/

    }
    public BigDecimal getContributionAmount(BigDecimal netPrice,BigDecimal productionperdayperloommtsonefficincy){
        return netPrice.multiply(productionperdayperloommtsonefficincy).setScale(3,RoundingMode.HALF_UP);
    }
    public BigDecimal getPerPickContribution(BigDecimal netPrice,BigDecimal totalVariableCostWithLoss,
                                             BigDecimal ppi){
        return new BigDecimal((netPrice.doubleValue()/ppi.doubleValue())*100).setScale(3,RoundingMode.HALF_UP);
    }
    public BigDecimal getTotalVariableCostWithLoss(BigDecimal totalvariablecostmts,BigDecimal fabricLossPercentage){
        return getMultiplyDivide100(totalvariablecostmts,fabricLossPercentage).add(totalvariablecostmts);

    }
    public BigDecimal getWarpWasteWtkgsPerMtsWithSizing(BigDecimal warpWasteWtkgspermts,BigDecimal sizing){
        return warpWasteWtkgspermts.multiply(sizing).setScale(3,RoundingMode.HALF_UP);
    }

    public BigDecimal getTotalYarnCostMts(BigDecimal warpWasteWtkgspermts,
                                          BigDecimal warpRate,BigDecimal weftWasteWtkgspermts,BigDecimal weftRate){
        return (warpWasteWtkgspermts.multiply(warpRate).
                add(weftWasteWtkgspermts.multiply(weftRate))).setScale(3,RoundingMode.HALF_UP);
    }

    public double getProductionPerDayPerLoomMts(BigDecimal speed,BigDecimal ppi,BigDecimal spilit){
        return ((24*60*speed.doubleValue())/
                (39.37*ppi.doubleValue()))*spilit.doubleValue();
    }
    public BigDecimal getProductionPerDayPerLoomMtsOnEfficincy(double productionperdayperloommts,
                                                               BigDecimal efficiency){
        return getMultiplyDivide100(productionperdayperloommts,efficiency).setScale(3,RoundingMode.HALF_EVEN);
    }

    public BigDecimal getInterest(BigDecimal exmillpricerspermts,BigDecimal creditPeriodPer){
        return getMultiplyDivide100(exmillpricerspermts,creditPeriodPer);
    }
    public BigDecimal getNetPrice(BigDecimal exmillpricerspermts,BigDecimal commission,
                                  BigDecimal interestcost,BigDecimal cashDiscountValue){
        return new BigDecimal(exmillpricerspermts.doubleValue()-commission.doubleValue()-
                interestcost.doubleValue()-cashDiscountValue.doubleValue());
    }
    public BigDecimal getCommission(BigDecimal exmillpricerspermts,BigDecimal totalSalesCommission){
        return getMultiplyDivide100(exmillpricerspermts,totalSalesCommission);
    }


    public BigDecimal getCreditPeriodPer(BigDecimal creditPeriod, int noOfDays){
       return creditPeriod.divide(new BigDecimal(noOfDays),2,BigDecimal.ROUND_HALF_EVEN);
    }

    public BigDecimal getCashDiscountValue(BigDecimal exmillpricerspermts,BigDecimal cashdiscount){
        return getMultiplyDivide100(exmillpricerspermts,cashdiscount);
    }
    public BigDecimal getWasteWtkgspermts(BigDecimal wtkgspermts,BigDecimal wastePercentage){
        return new BigDecimal(wtkgspermts.doubleValue()*wastePercentage.doubleValue()).
                setScale(3,RoundingMode.HALF_UP);
    }

    public BigDecimal getWeftWeigthPerMts(BigDecimal totalWidth,BigDecimal ppi,
                                          int weftcountInt,BigDecimal pickInsert){
        // What is this 1.03 value
        double weftwtkgspermts = ((((totalWidth.doubleValue()*ppi.doubleValue()*1.03)/weftcountInt)/1.693)/1000)*
                pickInsert.doubleValue();
        return new BigDecimal(weftwtkgspermts);
        //
    }

    public BigDecimal getWeftWeigthPerMts(BigDecimal reedSpace,BigDecimal ppi,
                                          int weftcountInt,BigDecimal pickInsert,BigDecimal fringeWidth){



        System.out.println("reedSpace ,fringeWidth  are "+reedSpace +" and "+fringeWidth);
        System.out.println("reedSpace.add(fringeWidth) "+(reedSpace.add(fringeWidth)));
        BigDecimal weftWeigthPerMts1 = (ppi.
                multiply(reedSpace.add(fringeWidth))).setScale(3,RoundingMode.HALF_EVEN);

        System.out.println("weftWeigthPerMts1 "+weftWeigthPerMts1);

        BigDecimal oneDivideByCount = new BigDecimal(1.000).
                divide(new BigDecimal(weftcountInt),3,RoundingMode.HALF_EVEN);

        Double divideBy1693 = oneDivideByCount.doubleValue()/1.693;
        System.out.println("divideBy1693 "+divideBy1693);


        BigDecimal weftWeigthPerMts = weftWeigthPerMts1.multiply(new BigDecimal(divideBy1693)).
                            setScale(3,RoundingMode.HALF_EVEN).divide(new BigDecimal(1000)).
                multiply(pickInsert).setScale(3, RoundingMode.HALF_EVEN);



        /*BigDecimal weftWeigthPerMts = (((ppi.multiply(reedSpace.add(fringeWidth)).setScale(3,RoundingMode.HALF_EVEN)).
                multiply(new BigDecimal(1).divide(new BigDecimal(weftcountInt).setScale(3,RoundingMode.HALF_EVEN).
                        divide(new BigDecimal(1.693).
                                setScale(3,RoundingMode.HALF_EVEN)
                        )).divide(new BigDecimal(1000)).
                                setScale(3,RoundingMode.HALF_EVEN)))
                .multiply(pickInsert));*/
                //((1/weftcountInt)/1.693))/1000)*pickInsert.doubleValue();
        System.out.println("Big weftWeigthPerMts "+weftWeigthPerMts);
        return weftWeigthPerMts;
    }

    public BigDecimal getWarpWeigthPerMts(BigDecimal totalWidth,BigDecimal epi,
                                          int warpcountInt,BigDecimal warpCrimp){

        System.out.println("totalWidth "+totalWidth);
        BigDecimal totalWidthEPI = totalWidth.multiply(epi);
        System.out.println("totalWidthEPI "+totalWidthEPI);
        BigDecimal totalWidthEPIDivideWarpCount = totalWidthEPI.divide(new BigDecimal(warpcountInt),3,RoundingMode.HALF_EVEN);
        System.out.println("totalWidthEPIDivideWarpCount "+totalWidthEPIDivideWarpCount);
        Double divideBy1693 = totalWidthEPIDivideWarpCount.doubleValue()/1.693;
        System.out.println("divideBy1693 "+divideBy1693);
        //BigDecimal totalWidthEPIDivideWarpCount1693 = totalWidthEPIDivideWarpCount.divide(new BigDecimal(1.693));
        BigDecimal totalWidthEPIDivideWarpCount1693WarpCrimp = (new BigDecimal(divideBy1693).
                multiply(warpCrimp.add(new BigDecimal(100)).divide(new BigDecimal(100)))).divide(new BigDecimal(1000));
        System.out.println("totalWidthEPIDivideWarpCount1693WarpCrimp "+totalWidthEPIDivideWarpCount1693WarpCrimp);
        //double warpwtkgspermts = ((((totalWidth.doubleValue()*epi.doubleValue())/warpcountInt)/1.693)*
          //     ((warpCrimp.doubleValue()+100)/100))/1000;
        return totalWidthEPIDivideWarpCount1693WarpCrimp.setScale(3,RoundingMode.HALF_UP);
    }


    public BigDecimal getExtraDiscountCommission(String strBpartnerId){
        BigDecimal extraCommission = BigDecimal.ZERO;
        BusinessPartner bp = OBDal.getInstance().get(BusinessPartner.class,strBpartnerId);
        System.out.println("Discount size is "+bp.getBusinessPartnerDiscountList().size());
        if(bp.getBusinessPartnerDiscountList().size()>0){
            for(Discount discount : bp.getBusinessPartnerDiscountList()){
                //org.openbravo.model.pricing.discount.Discount dicountPrice= discount.getDiscount().getDiscount();
                extraCommission = extraCommission.add(discount.getDiscount().getDiscount());
                System.out.println("Commissions are "+extraCommission);

            }
        }

        return extraCommission.setScale(3,RoundingMode.HALF_UP);
    }
    private BigDecimal getMultiplyDivide100(BigDecimal parameterOne,BigDecimal parameterTwo){
        return new BigDecimal((parameterOne.doubleValue()*parameterTwo.doubleValue())/100).setScale(3,RoundingMode.HALF_UP);
    }
    private BigDecimal getMultiplyDivide100(double parameterOne,BigDecimal parameterTwo){
        return new BigDecimal((parameterOne*parameterTwo.doubleValue())/100).setScale(3,RoundingMode.HALF_UP);
    }
    private BigDecimal getMultiplyDivide100(double parameterOne,double parameterTwo){
        return new BigDecimal((parameterOne*parameterTwo)/100);
    }
    private BigDecimal getMultiplyDivide100(BigDecimal parameterOne,double parameterTwo){
        return new BigDecimal((parameterOne.doubleValue()*parameterTwo)/100);
    }
}
