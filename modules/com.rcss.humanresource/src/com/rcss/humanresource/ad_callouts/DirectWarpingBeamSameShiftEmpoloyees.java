package com.rcss.humanresource.ad_callouts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import javax.servlet.ServletException;

import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;

import com.rcss.humanresource.data.RCHR_Dirwarp_Beam;
import com.redcarpet.production.data.RCPRShift;
import com.rcss.humanresource.data.RCHR_Dirwarp_Creel;

public class DirectWarpingBeamSameShiftEmpoloyees extends SimpleCallout{
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
    	String strDirectCreelId = info.getStringParameter("inprchrDirwarpCreelId", null);
    	
    	RCHR_Dirwarp_Creel creel = OBDal.getInstance().get(RCHR_Dirwarp_Creel.class, strDirectCreelId);
    	RCPRShift shift = OBDal.getInstance().get(RCPRShift.class, strShiftId);
    	
    	//System.out.println("strDirectCreelId "+strDirectCreelId);
    	
    	OBCriteria<RCHR_Dirwarp_Beam> beamlist = OBDal.getInstance().createCriteria(RCHR_Dirwarp_Beam.class);
       	beamlist.add(Restrictions.eq(RCHR_Dirwarp_Beam.PROPERTY_RCHRDIRWARPCREEL, creel));
       	beamlist.add(Restrictions.eq(RCHR_Dirwarp_Beam.PROPERTY_RCPRSHIFT, shift));
       	
    	
    	
    	//System.out.println("beamlist.size "+beamlist.list().size());
    	String asstwarpemployee = null;
    	String asstcreelemployee = null;
    	//String helper = null;
    	String employee = null;
    	
       	if(beamlist.list().size()!=0){
       		try{
       		Connection conn = OBDal.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select asstwarpemployee ,asstcreelemployee,rchr_employee_id as employee from rchr_dirwarp_beam,rchr_dirwarp_creel" 
            		+" where rchr_dirwarp_creel.rchr_dirwarp_creel_id=rchr_dirwarp_beam.rchr_dirwarp_creel_id"
            		+" and rchr_dirwarp_beam.rchr_dirwarp_creel_id='"+strDirectCreelId+"'"
            		+" and rchr_dirwarp_beam.rcpr_shift_id='"+strShiftId+"' limit 1");
       		while(rs.next()){
       			asstwarpemployee = rs.getString("asstwarpemployee");
       			asstcreelemployee = rs.getString("asstcreelemployee");
       			//helper = rs.getString("helper");
       			employee = rs.getString("employee");
       			
       			//System.out.println("employee.size "+beamlist.list().size());
       			
       		}
       			
       		}catch(Exception e)
			{
				e.printStackTrace();
			}
       		info.addResult("inpasstwarpemployee", asstwarpemployee);
           	info.addResult("inpasstcreelemployee", asstcreelemployee);
           //	info.addResult("inphelper", helper);
           	info.addResult("inprchrEmployeeId", employee);
       	}
       	
       	
    }
}
    	
