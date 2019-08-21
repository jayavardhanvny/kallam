package com.rcss.humanresource.ad_callouts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
/*
 * @author S.A. Mateen
 * 
 */
public class RCHR_TokenIssueCallout extends SimpleCallout {

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strtokenid = info.getStringParameter("inprchrTokenId", null);
        System.out.println(strtokenid + " is strtokenid ");
        String strQuantity = info.getStringParameter("inpquantity", null);
        System.out.println("strQuantity    " + strQuantity);
        try {
            Connection conn = OBDal.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sqry = "select Rchr_Token.Tokenrate from Rchr_Token where Rchr_Token_Id='" + strtokenid + "'";
            System.out.println("query is" + sqry);
            ResultSet rs = stmt.executeQuery(sqry);
            while (rs.next()) {
                int trate = rs.getInt("tokenrate");
                System.out.println(trate + "is trate");
                info.addResult("inprate", trate);
                int quant = Integer.parseInt(strQuantity);
                int res = trate * quant;
                info.addResult("inptotal", res);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
