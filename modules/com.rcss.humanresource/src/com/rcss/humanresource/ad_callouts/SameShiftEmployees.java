package com.rcss.humanresource.ad_callouts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import javax.servlet.ServletException;

import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;

import com.rcss.humanresource.data.RCHR_Sizingsheet;
import com.redcarpet.production.data.RCPRShift;
import com.rcss.humanresource.data.RCHR_Sizing_Beam;

public class SameShiftEmployees extends SimpleCallout{
	/* Sizing Data Sheet Window 
	 * 
	 * Beam Shift Selection in Sizing Beam
	 * Same Employees should be selected automatically if same shift is selected...
	 * 
	 * 
	 * 
	 */
    @Override
    protected void execute(CalloutInfo info) throws ServletException {
    	String strShiftId = info.getStringParameter("inprcprShiftId", null);
    	String strSizingId = info.getStringParameter("inprchrSizingsheetId", null);
    	
    	RCHR_Sizingsheet sizing = OBDal.getInstance().get(RCHR_Sizingsheet.class, strSizingId);
    	RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, strShiftId);
    	System.out.println("strSizingId "+strSizingId);
    	
    	OBCriteria<RCHR_Sizing_Beam> beamlist = OBDal.getInstance().createCriteria(RCHR_Sizing_Beam.class);
       	beamlist.add(Restrictions.eq(RCHR_Sizing_Beam.PROPERTY_RCHRSIZINGSHEET, sizing));
       	beamlist.add(Restrictions.eq(RCHR_Sizing_Beam.PROPERTY_RCPRSHIFT, shift));
       	
    	
    	
    	System.out.println("beamlist.size "+beamlist.list().size());
    	String backsizer = null;
    	String mixer = null;
    	String helper = null;
    	String employee = null;
    	
       	if(beamlist.list().size()!=0){
       		try{
       		Connection conn = OBDal.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select backsizer ,mixer,helper,rchr_employee_id as employee from rchr_sizing_beam,rchr_sizingsheet" 
            		+" where rchr_sizingsheet.rchr_sizingsheet_id=rchr_sizing_beam.rchr_sizingsheet_id"
            		+" and rchr_sizingsheet.rchr_sizingsheet_id='"+strSizingId+"'"
            		+" and rchr_sizing_beam.rcpr_shift_id='"+strShiftId+"' limit 1");
       		while(rs.next()){
       			backsizer = rs.getString("backsizer");
       			mixer = rs.getString("mixer");
       			helper = rs.getString("helper");
       			employee = rs.getString("employee");
       			
       			System.out.println("employee.size "+beamlist.list().size());
       			
       		}
       			
       		}catch(Exception e)
			{
				e.printStackTrace();
			}
       		info.addResult("inpbacksizer", backsizer);
           	info.addResult("inpmixer", mixer);
           	info.addResult("inphelper", helper);
           	info.addResult("inprchrEmployeeId", employee);
       	}
       	
       	
    }
}
    	
