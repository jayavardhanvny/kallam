package com.redcarpet.freight.ad_process;

import com.redcarpet.freight.data.RCFR_AdditionalCostVariables;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

/**
 *
 * @author Mateen
 */
@SuppressWarnings("unchecked")
public class RCFR_Calculate_Additional_Costs extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        String strOrderId = bundle.getParams().get("C_Order_ID").toString();

        OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Success");
        msg.setMessage("Process completed successfully");

        try {
            doIt(strOrderId);
            OBDal.getInstance().get(Order.class, strOrderId).setRcfrCalcdistcost(Boolean.TRUE);
            bundle.setResult(msg);
        } catch (Exception e) {
            msg.setType("ERROR");
            msg.setTitle("ERROR");
            msg.setMessage(e.getMessage());
            OBDal.getInstance().get(Order.class, strOrderId).setRcfrCalcdistcost(Boolean.FALSE);
            bundle.setResult(msg);
        }
    }

    private void doIt(String strOrderId) throws IllegalArgumentException {

        try {
            updateAdditionalCostingLineNoAscOrder(strOrderId);
            updateOrderLineDiscount(strOrderId);
        } catch (Exception ex) {
        }
        Order order = OBDal.getInstance().get(Order.class, strOrderId);

        BigDecimal totalQuantity = getTotalOrderQuanty(order.getOrderLineList());

        String hql = "SELECT var FROM RCFR_AdditionalCostVariables var WHERE var.salesOrder.id ='" + strOrderId + "' ORDER BY var.lineNo";
        Session session = OBDal.getInstance().getSession();
        List<RCFR_AdditionalCostVariables> li = session.createQuery(hql).list();
        if (li.size() == 0) {
            for (OrderLine line : order.getOrderLineList()) {
                updateNetUnitPrice(line, false);
            }
        }
        for (RCFR_AdditionalCostVariables var : li) {
            for (OrderLine line : order.getOrderLineList()) {
                BigDecimal unitDiscount = line.getRcfrDisco().divide(line.getOrderedQuantity(), 3, RoundingMode.HALF_UP);
                BigDecimal baseAmt = calculateBaseAmount(line, unitDiscount);
                BigDecimal iCost = new BigDecimal("0");
                BigDecimal fCost = new BigDecimal("0");
                BigDecimal pCost = new BigDecimal("0");

                if (checkEquals(var.getAdditionalCostVariableType(), RCFR_CostType_Constants.COSTTYE_INSURANCE)) {
                    iCost = calculateInsuranceCost(line, var, totalQuantity, baseAmt, unitDiscount);
                    line.setRcfrInsurance(iCost);
                } else if (checkEquals(var.getAdditionalCostVariableType(), RCFR_CostType_Constants.COSTTYE_FREIGHT)) {
                    fCost = calculateFreightCost(line, var, totalQuantity, baseAmt, unitDiscount);
                    line.setRcfrFreight(fCost);
                } else if (checkEquals(var.getAdditionalCostVariableType(), RCFR_CostType_Constants.COSTTYE_PACKAGING)) {
                    pCost = calculatePackagingCost(line, var, totalQuantity, baseAmt, unitDiscount);
                    line.setRcfrPnf(pCost);
                } else {
                    throw new IllegalArgumentException("Illegal Additional Cost Type");
                }

                updateNetUnitPrice(line, true);
            }
        }
        //updateOrderGrossAmount(order);
    }

    /*
     * BaseAmount = (Qty*UnitRate)-Discount 
     * 
     */
    private BigDecimal calculateBaseAmount(OrderLine line, BigDecimal discount) {

        return (line.getOrderedQuantity().multiply(line.getRcfrPrice())).subtract(discount);

    }

    /*
     * 
     * Distributed_Insurance_Cost = (LineQty/TotalOrderQty) * Insurance Rate
     * Percentage_Insurance_Cost = ((UnitPrice-Discount) * LineQty) * Insurance Rate
     * 
     */
    private BigDecimal calculateInsuranceCost(OrderLine line, RCFR_AdditionalCostVariables var, BigDecimal totalQty, BigDecimal baseAmount, BigDecimal discount) {
        BigDecimal insurance = new BigDecimal(0);
        if (checkEquals(var.getCostRule(), RCFR_CostType_Constants.COST_CALCULATION_TYPE1)) {
            if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISCOUNT_DISTCOST)) {
                insurance = line.getOrderedQuantity().divide(totalQty, 3, RoundingMode.HALF_UP).multiply(var.getRate());
            } else if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISOCUNT_PERCENT)) {
                insurance = ((line.getRcfrPrice().subtract(discount)).multiply(line.getOrderedQuantity())).multiply(var.getRate().multiply(new BigDecimal(0.01)));
            }

        } else if (checkEquals(var.getCostRule(), RCFR_CostType_Constants.COST_CALCULATION_TYPE2)) {
            if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISCOUNT_DISTCOST)) {
            } else if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISOCUNT_PERCENT)) {
                insurance = ((line.getRcfrPrice().subtract(discount)).add(getLine10Amount(var, line).divide(line.getOrderedQuantity(), 3, RoundingMode.HALF_UP)).multiply(line.getOrderedQuantity())).multiply(var.getRate().multiply(new BigDecimal(0.01)));
            }

        } else if (checkEquals(var.getCostRule(), RCFR_CostType_Constants.COST_CALCULATION_TYPE3)) {
            if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISCOUNT_DISTCOST)) {
            } else if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISOCUNT_PERCENT)) {
                insurance = ((line.getRcfrPrice().subtract(discount)).add((getLine10Amount(var, line).add(getLine20Amount(var, line))).divide(line.getOrderedQuantity(), 3, RoundingMode.HALF_UP)).multiply(line.getOrderedQuantity())).multiply(var.getRate().multiply(new BigDecimal(0.01)));
            }
        } else {
            throw new IllegalArgumentException("Illegal Cost Type: Insurance Cost");
        }
        return insurance;
    }

    private BigDecimal calculateFreightCost(OrderLine line, RCFR_AdditionalCostVariables var, BigDecimal totalQty, BigDecimal baseAmount, BigDecimal discount) {
        BigDecimal freight = new BigDecimal(0);

        if (checkEquals(var.getCostRule(), RCFR_CostType_Constants.COST_CALCULATION_TYPE1)) {
            if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISCOUNT_DISTCOST)) {
                freight = line.getOrderedQuantity().divide(totalQty, 3, RoundingMode.HALF_UP).multiply(var.getRate());
            } else if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISOCUNT_PERCENT)) {
                freight = ((line.getRcfrPrice().subtract(discount)).multiply(line.getOrderedQuantity())).multiply(var.getRate().multiply(new BigDecimal(0.01)));
            }
        } else if (checkEquals(var.getCostRule(), RCFR_CostType_Constants.COST_CALCULATION_TYPE2)) {
            if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISCOUNT_DISTCOST)) {
            } else if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISOCUNT_PERCENT)) {
                freight = ((line.getRcfrPrice().subtract(discount)).add(getLine10Amount(var, line).divide(line.getOrderedQuantity(), 3, RoundingMode.HALF_UP)).multiply(line.getOrderedQuantity())).multiply(var.getRate().multiply(new BigDecimal(0.01)));
            }
        } else if (checkEquals(var.getCostRule(), RCFR_CostType_Constants.COST_CALCULATION_TYPE3)) {
            if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISCOUNT_DISTCOST)) {
            } else if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISOCUNT_PERCENT)) {
                freight = ((line.getRcfrPrice().subtract(discount)).add((getLine10Amount(var, line).add(getLine20Amount(var, line))).divide(line.getOrderedQuantity(), 3, RoundingMode.HALF_UP)).multiply(line.getOrderedQuantity())).multiply(var.getRate().multiply(new BigDecimal(0.01)));
            }
        } else {
            throw new IllegalArgumentException("Illegal Cost Type: Freight Cost");
        }
        return freight;
    }

    private BigDecimal calculatePackagingCost(OrderLine line, RCFR_AdditionalCostVariables var, BigDecimal totalQty, BigDecimal baseAmount, BigDecimal discount) {

        BigDecimal packaging = new BigDecimal(0);
        if (checkEquals(var.getCostRule(), RCFR_CostType_Constants.COST_CALCULATION_TYPE1)) {
            if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISCOUNT_DISTCOST)) {
                packaging = line.getOrderedQuantity().divide(totalQty, 3, RoundingMode.HALF_UP).multiply(var.getRate());
            } else if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISOCUNT_PERCENT)) {
                packaging = ((line.getRcfrPrice().subtract(discount)).multiply(line.getOrderedQuantity())).multiply(var.getRate().multiply(new BigDecimal(0.01)));
            }
        } else if (checkEquals(var.getCostRule(), RCFR_CostType_Constants.COST_CALCULATION_TYPE2)) {
            if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISCOUNT_DISTCOST)) {
            } else if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISOCUNT_PERCENT)) {
                packaging = ((line.getRcfrPrice().subtract(discount)).add(getLine10Amount(var, line).divide(line.getOrderedQuantity(), 3, RoundingMode.HALF_UP)).multiply(line.getOrderedQuantity())).multiply(var.getRate().multiply(new BigDecimal(0.01)));
            }
        } else if (checkEquals(var.getCostRule(), RCFR_CostType_Constants.COST_CALCULATION_TYPE3)) {
            if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISCOUNT_DISTCOST)) {
            } else if (checkEquals(var.getDiscountType(), RCFR_CostType_Constants.DISOCUNT_PERCENT)) {
                packaging = ((line.getRcfrPrice().subtract(discount)).add((getLine10Amount(var, line).add(getLine20Amount(var, line))).divide(line.getOrderedQuantity(), 3, RoundingMode.HALF_UP)).multiply(line.getOrderedQuantity())).multiply(var.getRate().multiply(new BigDecimal(0.01)));
            }
        } else {
            throw new IllegalArgumentException("Illegal Cost Type: Freight Cost");
        }
        return packaging;
    }

    /*
     *  Lines commented: Always calculates based on percentage
     */
    private BigDecimal calculateDiscount(Order order, OrderLine line) {
        BigDecimal lineDiscount = new BigDecimal("0");
        if (line.getDiscount().doubleValue() != 0) {
            lineDiscount = (line.getRcfrPrice().multiply(line.getDiscount()).multiply(new BigDecimal(0.01))).multiply(line.getOrderedQuantity());
        }
        return lineDiscount;
    }

    private BigDecimal getTotalOrderQuanty(List<OrderLine> orderLineList) {
        double retVal = 0;
        for (OrderLine line : orderLineList) {
            retVal += line.getOrderedQuantity().doubleValue();
        }
        return new BigDecimal(retVal);
    }

    private boolean checkEquals(String key1, String key2) {
        return StringUtils.equalsIgnoreCase(key1, key2);
    }

    /*
     * 
     * =Price-Discount+Insurance+PNF+Freight
     * 
     */
    private void updateNetUnitPrice(OrderLine line, boolean hasAdditionalLines) {
        if (!hasAdditionalLines) {
            line.setUnitPrice(line.getRcfrPrice().subtract(line.getRcfrDisco().divide(line.getOrderedQuantity(), 3, RoundingMode.HALF_UP)));
            line.setRcfrFreight(BigDecimal.ZERO);
            line.setRcfrInsurance(BigDecimal.ZERO);
            line.setRcfrPnf(BigDecimal.ZERO);
        } else {
            BigDecimal unitDiscount = line.getRcfrDisco().divide(line.getOrderedQuantity(), 3, RoundingMode.HALF_UP);
            BigDecimal unitAdditionalCost = (line.getRcfrFreight().add(line.getRcfrInsurance().add(line.getRcfrPnf()))).divide(line.getOrderedQuantity(), 3, RoundingMode.HALF_UP);
            BigDecimal result = line.getRcfrPrice().subtract(unitDiscount).add(unitAdditionalCost);
            line.setUnitPrice(result);
        }
    }

    private void updateOrderGrossAmount(Order order) {
        double sum = 0;
        for (OrderLine line : order.getOrderLineList()) {
            sum += line.getLineNetAmount().doubleValue();
        }
        order.setGrandTotalAmount(new BigDecimal(sum));
    }

    public BigDecimal getLine10Amount(RCFR_AdditionalCostVariables var, OrderLine line) {
        String hql = "SELECT var FROM RCFR_AdditionalCostVariables var  "
                + " WHERE var.lineNo = 10 "
                + " AND var.salesOrder.id ='" + var.getSalesOrder().getId() + "' "
                + " ORDER BY var.lineNo DESC LIMIT 1 ";
        Session session = OBDal.getInstance().getSession();

        BigDecimal retVal = new BigDecimal(0);

        try {
            RCFR_AdditionalCostVariables line10Var = (RCFR_AdditionalCostVariables) session.createQuery(hql).list().get(0);
            if (checkEquals(line10Var.getAdditionalCostVariableType(), RCFR_CostType_Constants.COSTTYE_INSURANCE)) {
                retVal = line.getRcfrInsurance();
            } else if (checkEquals(line10Var.getAdditionalCostVariableType(), RCFR_CostType_Constants.COSTTYE_FREIGHT)) {
                retVal = line.getRcfrFreight();
            } else if (checkEquals(line10Var.getAdditionalCostVariableType(), RCFR_CostType_Constants.COSTTYE_PACKAGING)) {
                retVal = line.getRcfrPnf();
            } else {
                throw new IllegalArgumentException("Illegal Cost type: " + var.getCostRule());
            }
        } catch (Exception e) {
        }
        return retVal;
    }

    public BigDecimal getLine20Amount(RCFR_AdditionalCostVariables var, OrderLine line) {
        String hql = "SELECT var FROM RCFR_AdditionalCostVariables var  "
                + " WHERE var.lineNo !=" + (var.getLineNo().intValue() - 10) + " and var.lineNo<" + (var.getLineNo().intValue() - 10) + " "
                + " AND var.salesOrder.id ='" + var.getSalesOrder().getId() + "' "
                + " ORDER BY var.lineNo DESC LIMIT 1 ";
        Session session = OBDal.getInstance().getSession();

        BigDecimal retVal = new BigDecimal(0);

        try {
            RCFR_AdditionalCostVariables line20Var = (RCFR_AdditionalCostVariables) session.createQuery(hql).list().get(0);
            if (checkEquals(line20Var.getAdditionalCostVariableType(), RCFR_CostType_Constants.COSTTYE_INSURANCE)) {
                retVal = line.getRcfrInsurance();
            } else if (checkEquals(line20Var.getAdditionalCostVariableType(), RCFR_CostType_Constants.COSTTYE_FREIGHT)) {
                retVal = line.getRcfrFreight();
            } else if (checkEquals(line20Var.getAdditionalCostVariableType(), RCFR_CostType_Constants.COSTTYE_PACKAGING)) {
                retVal = line.getRcfrPnf();
            } else {
                throw new IllegalArgumentException("Illegal Cost type: " + var.getCostRule());
            }
        } catch (Exception e) {
        }
        return retVal;
    }

    private void updateAdditionalCostingLineNoAscOrder(String strOrderId) throws IllegalArgumentException {
        String hql = "SELECT var FROM RCFR_AdditionalCostVariables var WHERE var.salesOrder.id ='" + strOrderId + "' ORDER BY var.lineNo";
        Session session = OBDal.getInstance().getSession();
        List<RCFR_AdditionalCostVariables> li = session.createQuery(hql).list();
        if (li.size() > 3) {
            throw new IllegalArgumentException("Additional cost lines cannot be greater then 3");
        }
        for (int i = 0; i < li.size(); i++) {
            RCFR_AdditionalCostVariables var = li.get(i);
            var.setLineNo(new Long((i + 1) * 10));
        }
    }

    private void updateOrderLineDiscount(String strOrderId) {
        Order order = OBDal.getInstance().get(Order.class, strOrderId);
        if (order.getRcfrDiscount().doubleValue() != 0) {
            for (OrderLine line : order.getOrderLineList()) {
                if (line.getDiscount().doubleValue() == 0) {
                    line.setDiscount(line.getSalesOrder().getRcfrDiscount());
                    BigDecimal discount = calculateDiscount(order, line);
                    line.setRcfrDisco(discount);
                }
            }

        }
    }
}
