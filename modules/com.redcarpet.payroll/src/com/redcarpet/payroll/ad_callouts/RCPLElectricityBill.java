package com.redcarpet.payroll.ad_callouts;
import javax.servlet.ServletException;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.plm.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.openbravo.model.common.enterprise.Locator;
import org.openbravo.model.common.enterprise.Warehouse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

/**
 *
 * @author Suneetha
 */
public class RCPLElectricityBill extends SimpleCallout {

	MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	
        String strOrgId = info.getStringParameter("inpadOrgId", null);
        String strEmpId = info.getStringParameter("inprchrEmployeeId", null);
    
        if (strEmpId != null) {
        	String roomid=getRoomId(strOrgId,strEmpId,info);
        	
        	info.addResult("inprchrRoomId",roomid);
        	if(roomid!=null){
        	String strRoomId = info.getStringParameter("inprchrRoomId", null);
        	
        	String mtrno=getMeterNumber(strOrgId,roomid,info);
        	info.addResult("inprcmrMeterId",mtrno);
        	}
        }
      
    
    }
    
  
    
    public String getRoomId(String strOrgId, String strEmpId,CalloutInfo info){
    	 String roomid="";
         try {
         	
             Connection conn = OBDal.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             String qry="select rchr_room_id from rchr_employee  where (rchr_employee_id='"+strEmpId+"') and (AD_Org_ID='"+ strOrgId+"')";
             ResultSet rs1=stmt.executeQuery(qry);
             while(rs1.next()){
             	System.out.println("id"+rs1.getString(1));
             	roomid=rs1.getString(1);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return roomid;
    	
    }
    private String getMeterNumber(String strOrgId, String strRoomId,CalloutInfo info) {
    	
	    String meterno = "";
        try {
          
        	
            Connection conn = OBDal.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String sqry ="select rcmr_meter_id from rchr_room where (rchr_room_id='"+strRoomId+"') and (AD_Org_ID='"+strOrgId+"')";
            ResultSet rs = stmt.executeQuery(sqry);

            while (rs.next()) {
            	 
                 meterno = rs.getString(1);
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return meterno;
        
    }
}
