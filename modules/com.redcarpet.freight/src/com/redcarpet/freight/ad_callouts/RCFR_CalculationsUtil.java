/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.freight.ad_callouts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.plm.Product;

/**
 *
 * @author Administrator
 */
public class RCFR_CalculationsUtil {

    private String strProduct;
    private BigDecimal unitPrice;
    private BigDecimal freight;
    private BigDecimal insurance;
    private BigDecimal packaging;
    private BigDecimal obDiscount;
    private BigDecimal qtyOrdered;

    public RCFR_CalculationsUtil() {
    }

    public RCFR_CalculationsUtil(String strProduct, BigDecimal unitPrice, BigDecimal unitFreight, BigDecimal unitInsurance, BigDecimal unitPackaging, BigDecimal obDiscount, BigDecimal qtyOrdered) {
        this.strProduct = strProduct;
        this.unitPrice = unitPrice;
        this.freight = unitFreight;
        this.insurance = unitInsurance;
        this.packaging = unitPackaging;
        this.obDiscount = obDiscount;
        this.qtyOrdered = qtyOrdered;
    }

    public String getStrProduct() {
        return strProduct;
    }

    public void setStrProduct(String strProduct) {
        this.strProduct = strProduct;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getInsurance() {
        return insurance;
    }

    public void setInsurance(BigDecimal insurance) {
        this.insurance = insurance;
    }

    public BigDecimal getPackaging() {
        return packaging;
    }

    public void setPackaging(BigDecimal packaging) {
        this.packaging = packaging;
    }

    public BigDecimal getObDiscount() {
        return obDiscount;
    }

    public void setObDiscount(BigDecimal obDiscount) {
        this.obDiscount = obDiscount;
    }

    public BigDecimal getQtyOrdered() {
        return qtyOrdered;
    }

    public void setQtyOrdered(BigDecimal qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    public BigDecimal calculateCottonLintProductPrice() {
        BigDecimal netUnitPrice = BigDecimal.ZERO;
        BigDecimal candyRate = getCandyRate(getStrProduct());
        if (candyRate.doubleValue() != 0) {
            if (getObDiscount().doubleValue() != 0) {
                netUnitPrice = (getUnitPrice().multiply(getCandyRate(getStrProduct()))).add(getSumAdditionalCosts());
            } else {
                BigDecimal temp = getUnitPrice().multiply(getObDiscount()).multiply(new BigDecimal(0.01)).divide(getQtyOrdered(), 4, RoundingMode.HALF_UP);
                BigDecimal temp2 = getUnitPrice().subtract(temp);
                netUnitPrice = (temp2.multiply(getCandyRate(getStrProduct()))).add(getSumAdditionalCosts());
            }

        }
        return netUnitPrice;
    }

    public boolean isCottonLintProduct(String strProduct) {
        return OBDal.getInstance().get(Product.class, strProduct).isRcloIslot();
    }

    public BigDecimal getCandyRate(String strProduct) {
        BigDecimal weight = OBDal.getInstance().get(Product.class, strProduct).getWeight();
        if (weight.doubleValue() == 0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(1).divide(weight, 6, RoundingMode.HALF_UP);
    }

    public BigDecimal getSumAdditionalCosts() {
        return getFreight().add(getInsurance()).add(getPackaging());
    }

    public BigDecimal calculateProductPrice() {
        BigDecimal temp1 = calculateDiscount();
        BigDecimal temp2 = getSumAdditionalCosts().divide(getQtyOrdered(), 4, RoundingMode.HALF_UP);
        return getUnitPrice().subtract(temp1).add(temp2);
    }

    public BigDecimal calculateDiscount() {
        return getObDiscount().multiply(new BigDecimal(0.01)).multiply(getUnitPrice());
    }

}
