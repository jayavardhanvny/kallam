package com.redcarpet.payroll.ad_callouts;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;

import com.rcss.humanresource.data.*;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class RCPL_PresentsForStoreRequisition extends SimpleCallout{
	@Override
    protected void execute(CalloutInfo info) throws ServletException {
		String id=info.getStringParameter("inprchrEmployeeId", null);
		String date = info.getStringParameter("inprequisitondate", null);
		RchrEmployee emp=OBDal.getInstance().get(RchrEmployee.class,id);
		double count=0;
		//System.out.println("id " +id);
		///System.out.println("date "+date);
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date presentDate = sdf.parse(date);
			//System.out.println("presentDate "+presentDate);
			Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(presentDate); 
	    	calendar.set(Calendar.DAY_OF_MONTH,1);
	        Date startDate = calendar.getTime();
	        //SimpleDateFormat sdf = SimpleDateFormat("yyyy/MM/dd");
	        sdf.format(startDate);
	        sdf.format(presentDate);

	       // System.out.println("startDate "+startDate);
	       // System.out.println("presentDate "+presentDate);
		String hql = "SELECT COUNT(dailyattnd.id) FROM RCHR_Dailyattenddemo AS dailyattnd "
    			+ "WHERE dailyattnd.employee.id = '" + emp.getId() + "' "
    			+ " AND dailyattnd.attendanceDate BETWEEN '" + startDate + "' AND  '" + presentDate + "' "
    			+ " AND present = true and overtime=false"
    			+ " AND 1 = 1";
        Session session = OBDal.getInstance().getSession();
        List li = session.createQuery(hql).list();
        if (li.size() <= 0 || li.isEmpty()) {
            count = 0;
        }
        if (li.get(0) == null || li.get(0).toString() == null) {
        	count = 0;
        }
        count = Double.valueOf(li.get(0).toString());
        //System.out.println("count " +count);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		OBCriteria<RchrEmployee> rchrEmployeeCriteria = OBDal.getInstance().createCriteria(RchrEmployee.class);
		rchrEmployeeCriteria.add(Restrictions.eq(RchrEmployee.PROPERTY_ROOM,emp.getRoom()));
		rchrEmployeeCriteria.add(Restrictions.eq(RchrEmployee.PROPERTY_ACTIVE,true));
		double noOfEmployees = rchrEmployeeCriteria.list().size();		
		//System.out.println("noOfEmployees "+noOfEmployees);
		//System.out.println("emp.getRoom() "+emp.getRoom());
		//RCHR_Room rchrRoom = OBDal.getInstance().get(RCHR_Room.class, emp.getRoom());
		info.addResult("inpnumberofpresents", count);
		if(emp.isRoomAllotted())
		{	
		info.addResult("inprchrRoomId", emp.getRoom().getId());
		}else{
			info.addResult("inprchrRoomId", null);
		}
		info.addResult("inpnoofpersonsinroom", noOfEmployees);
		
		if(emp.getEmployeeType().equals("Operator")){
		info.addResult("inpamount", new BigDecimal(count).multiply(new BigDecimal(50)));
		}else if(emp.getEmployeeType().equals("Staff")){
			info.addResult("inpamount", new BigDecimal(count).multiply(new BigDecimal(100)));
			}else{
				info.addResult("inpamount", new BigDecimal(count).multiply(new BigDecimal(75)));
			}
	
	}

}