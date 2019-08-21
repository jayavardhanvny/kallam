/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redcarpet.production.ad_callouts;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author S.A. Mateen
 */
public class BigDecimalUtil extends java.math.BigDecimal{

    public BigDecimalUtil(String val) {
        super(val);
    }
    
    public static String getDecimalPrecisionValue(Object obj){
        DecimalFormat df = new DecimalFormat("##.##");
        return df.format(obj);
    }
    
    public static java.math.BigDecimal getPlainStrNumber(String value) {
        return new BigDecimal(StringUtils.equalsIgnoreCase(value, "") ? "0" : value.replace(",", ""));
    }
}
