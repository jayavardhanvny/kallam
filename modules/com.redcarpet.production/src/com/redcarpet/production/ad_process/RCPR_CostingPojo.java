/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.production.ad_process;

import java.math.BigDecimal;
import java.util.Date;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.currency.Currency;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.model.common.plm.Product;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;

/**
 *
 * @author Mateen
 */
public class RCPR_CostingPojo {

    private Product product;
    private Organization org;
    private String costtype;
    private BigDecimal cost;
    private BigDecimal quantity;
    private BigDecimal netunitprice;
    private BigDecimal movementQty;
    private Currency currency;
    private MaterialTransaction inventory;
    
    public RCPR_CostingPojo(Product product, Organization org, String costtype, BigDecimal cost, 
            BigDecimal quantity, BigDecimal netunitprice, BigDecimal movementQty, Currency currency, 
            MaterialTransaction inventory) {
        this.product = product;
        this.org = org;
        this.costtype = costtype;
        this.cost = cost;
        this.quantity = quantity;
        this.netunitprice = netunitprice;
        this.movementQty = movementQty;
        this.currency = currency;
        this.inventory = inventory;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public String getCosttype() {
        return costtype;
    }

    public void setCosttype(String costtype) {
        this.costtype = costtype;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getNetunitprice() {
        return netunitprice;
    }

    public void setNetunitprice(BigDecimal netunitprice) {
        this.netunitprice = netunitprice;
    }

    public BigDecimal getMovementQty() {
        return movementQty;
    }

    public void setMovementQty(BigDecimal movementQty) {
        this.movementQty = movementQty;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public MaterialTransaction getInventory() {
        return inventory;
    }

    public void setInventory(MaterialTransaction inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "RCPR_CostingPojo{" + "client=" + product + ", org=" + org + ", costtype=" + costtype + 
                ", cost=" + cost + ", quantity=" + quantity + ", netunitprice=" + netunitprice + ", "
                + "movementQty=" + movementQty + ", currency=" + currency + ", inventory=" + inventory + '}';
    }
    
    
}
