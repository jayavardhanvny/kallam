package com.rcss.humanresource.ad_actionButtons;

import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.util.HqlUtils;
import com.redcarpet.payroll.util.PayrollDBSessionUtil;
import com.redcarpet.payroll.util.PayrollUtils;
import com.redcarpet.payroll.util.RoomPojo;
import oracle.sql.DATE;

import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.hibernate.criterion.Restrictions;
import org.openbravo.erpCommon.utility.OBMessageUtils;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import com.redcarpet.payroll.ad_actionbutton.AttendanceUtil;
import com.rcss.humanresource.data.RchrRoomRentLines;
import org.apache.log4j.Logger;

public class RoomRentProcess extends DalBaseProcess{
	private static final Logger log4j = Logger.getLogger(RoomRentProcess.class);



	@Override
	protected void doExecute(ProcessBundle bundle) throws Exception{
		final OBError msg = new OBError();
		OBMessageUtils obMessageUtils = new OBMessageUtils();
		msg.setType("Success");
		msg.setTitle("Done");
		msg.setMessage("Calculated Room RentProcess Successfully");
		bundle.setResult(msg);

		log4j.info("inProcess ");
		String strRoomrentId = (String)bundle.getParams().get("Rchr_Roomrent_ID");
		log4j.info("strRoomrentId "+strRoomrentId);
		RchrRoomrent rchrRoomrent = OBDal.getInstance().get(RchrRoomrent.class, strRoomrentId);

		///String strStartdate = (String)bundle.getParams().get("fromdate");
		//BigDecimal firstperson = rchrRoomrent.getFrstattdprcnt();
		//BigDecimal secondperson = rchrRoomrent.getScndattdprcnt();
		Date startDate = rchrRoomrent.getFromDate();
		Date endDate = rchrRoomrent.getToDate();
		//System.out.println("firstperson "+firstperson);
		//System.out.println("secondperson "+secondperson);
		//System.out.println("startDate "+startDate);
		//System.out.println("endDate "+endDate);
		OBCriteria<RCHR_Room> roomCriteria = OBDal.getInstance().createCriteria(RCHR_Room.class);
		roomCriteria.add(Restrictions.eq(RCHR_Room.PROPERTY_ISRENTED,true));
		roomCriteria.add(Restrictions.eq(RCHR_Room.PROPERTY_VACANT,false));
		log4j.info("no of rooms "+roomCriteria.list().size());
		HashMap<RCHR_Room, RoomPojo> rchrRoomRoomPojoHashMap = new HashMap<>();
		for(RCHR_Room rchrRoom : roomCriteria.list()){
			BigDecimal rentamount = BigDecimal.ZERO;


			for (RchrRoomEmployee rchrRoomEmployee : rchrRoom.getRchrRoomEmployeeList()) {
				AttendanceUtil attutil = new AttendanceUtil(startDate,endDate,rchrRoomEmployee.getEmployee().getId());
				//OBCriteria<RchrRoomRentLines> rchrRoomRentLinesCriteria = OBDal.getInstance().createCriteria(RchrRoomRentLines.class);
				if (rchrRoomEmployee.getEmployee().isRoomAllotted() && rchrRoomEmployee.isVacating().equals(Boolean.FALSE)){
					RchrRoomRentLines rchrRoomRentLines = OBProvider.getInstance().get(RchrRoomRentLines.class);
					rchrRoomRentLines.setOrganization(rchrRoomrent.getOrganization());
					rchrRoomRentLines.setEmployee(rchrRoomEmployee.getEmployee());
					rentamount = this.getRoomRentAmount(rchrRoom, rchrRoomRoomPojoHashMap, attutil.getNoOfDaysPresent(), startDate, endDate, rchrRoomEmployee.getEmployee());
					rchrRoomRentLines.setRchrRoomrent(rchrRoomrent);
					rchrRoomRentLines.setRoom(rchrRoom);
					rchrRoomRentLines.setPresentdays(new BigDecimal(attutil.getNoOfDaysPresent()));
					rchrRoomRentLines.setRentamount(rentamount);
					OBDal.getInstance().save(rchrRoomRentLines);
				}

			}







		}

	}

	private BigDecimal getRoomRentAmount(RCHR_Room rchrRoom, HashMap<RCHR_Room, RoomPojo> rchrRoomRoomPojoHashMap,
										 double employeePresents, Date startDate, Date endDate, RchrEmployee rchrEmployee){
		PayrollDBSessionUtil payrollDBSessionUtil = new PayrollDBSessionUtil(rchrEmployee.getId(), startDate, endDate);

		BigDecimal rentamount = BigDecimal.ZERO;
		if (employeePresents == 0){
			return rentamount;
		} else {
			RoomPojo roomPojo = this.getRoomPojoObject(rchrRoom, rchrRoomRoomPojoHashMap, startDate, endDate, rchrEmployee.getId());
			if(roomPojo.isFirstPersonApplicable() && roomPojo.isSecondPersonApplicable()) {
				log4j.info("In Not Deducted Condition");
			} else if (roomPojo.getTotalEmployeesPresents() == 0) { // If No one is present in room then roomRent should be zero
				log4j.info("Complete Absent ");
			} else if (roomPojo.getTotalEmployeesPresents() > 0){
				// Proportionate case
				double noOfPersonsWithOutNewJoiners = this.getNoOfPersonsJoined(rchrRoom.getId(), startDate);
				if (rchrEmployee.getDate().compareTo(startDate)>=0){ // For New Joiners
					log4j.info("Yes he is New Joiner");
					rentamount = BigDecimal.ZERO;
				} else if (roomPojo.isHasNewJoiners()) { // If has new Joiner then it should be shared as per the proportionate of family's Attendance...
					double totalNoOfPresentsExcludingNewJoiners = this.getTotalPresentsEmployeesInRoomExcludingNewJoiners(rchrRoom.getId(), startDate, endDate);
					log4j.info("No Of New Joining persons "+noOfPersonsWithOutNewJoiners +" AND Room No is "+rchrRoom.getRoomNo() +"No of Persons Excluding New Joiners "+totalNoOfPresentsExcludingNewJoiners);
					rentamount = new BigDecimal(payrollDBSessionUtil.getProportionateAmount(employeePresents, totalNoOfPresentsExcludingNewJoiners, roomPojo.getRoomRent()));
				} else {
					rentamount = new BigDecimal(payrollDBSessionUtil.getProportionateAmount(employeePresents, roomPojo.getTotalEmployeesPresents(), roomPojo.getRoomRent()));
				}



				//if (noOfPersonsWithOutNewJoiners != 0)
				//rentamount = rchrRoom.getBlock().getRentamount().divide(new BigDecimal(noOfPersonsWithOutNewJoiners), 3, RoundingMode.HALF_EVEN);
			}
			return rentamount;
		}

	}

	public RoomPojo getRoomPojoObject(RCHR_Room rchrRoom, HashMap<RCHR_Room, RoomPojo> rchrRoomRoomPojoHashMap, Date startDate, Date endDate, String employeeId){
		RoomPojo roomPojo = null;
		RCHR_Block rchrBlock = rchrRoom.getBlock();
		BigDecimal firstPersonPresents = rchrBlock.getFrstattdprcnt();
		BigDecimal secondPersonPresents = rchrBlock.getScndattdprcnt();
		if (rchrRoomRoomPojoHashMap.containsKey(rchrRoom)){
			roomPojo = rchrRoomRoomPojoHashMap.get(rchrRoom);
		} else {


			PayrollDBSessionUtil payrollDBSessionUtil = new PayrollDBSessionUtil(employeeId, startDate, endDate);


			BigDecimal bigDecimalFirstPerson, bigDecimalSecondPerson = BigDecimal.ZERO;
			roomPojo = new RoomPojo(rchrRoom.getId(), payrollDBSessionUtil.getTotalPresentsEmployeesInRoom(rchrRoom.getId()),
					rchrRoom.getNoofemployees().doubleValue(), rchrRoom.getBlock().getRentamount().doubleValue());

			double noOfPersonsWithOutNewJoiners = this.getNoOfPersonsJoined(rchrRoom.getId(), startDate);
			if (noOfPersonsWithOutNewJoiners >0 ){
				roomPojo.setHasNewJoiners(Boolean.TRUE);
			}


			List<Long> listPresents = this.getRoomPresents(rchrRoom.getId(), startDate, endDate);
			log4j.info("List is "+listPresents);
			if (listPresents!=null && listPresents.size()>0) {
				log4j.info("List Size in If "+listPresents.size());
				if (listPresents.size() == 1) {
					log4j.info("Log 1 ");
				} else if (listPresents.size() >= 2) {
					log4j.info("Log 2 ");
					bigDecimalFirstPerson = new BigDecimal(listPresents.get(0));
					bigDecimalSecondPerson = new BigDecimal(listPresents.get(1)) ;
					if (bigDecimalFirstPerson.compareTo(firstPersonPresents)>=0 && bigDecimalSecondPerson.compareTo(secondPersonPresents)>=0){
						roomPojo.setFirstPersonApplicable(Boolean.TRUE);
						roomPojo.setSecondPersonApplicable(Boolean.TRUE);
					} else if (bigDecimalFirstPerson==BigDecimal.ZERO){
						log4j.info("Log 3 ");
						roomPojo.setCompleteAbsent(Boolean.TRUE);
					} else {

					}
				}

			}
			rchrRoomRoomPojoHashMap.put(rchrRoom, roomPojo);
		}
		/*
		if (employee.isRoomAllotted() && attandancePojo.getNoOfDaysPresent() > 0) {
							if (employee.getRoom().getBlock().getBlockNo().equals("FQ")) {
								if (roomRoomUtilHashMap.containsKey(employee.getRoom())) {
									RoomPojo roomPojo = roomRoomUtilHashMap.get(employee.getRoom());
									welfareAmount = this.getProportionateAmount(attandancePojo.getNoOfDaysPresent(), roomPojo.getTotalEmployeesPresents(), employee.getRoom().getBlock().getWelfarefund().doubleValue());
								}
							} else {
								welfareAmount = employee.getRoom().getBlock().getWelfarefund().doubleValue();
							}

							logger.info("welfareAmount " + welfareAmount);
							//return employee.getRoom().getBlock().getWelfarefund().doubleValue();
						}
		 */
		return roomPojo;
	}

	public List<Long> getRoomPresents(String roomId, Date startDate, Date endDate){
		String hql = " SELECT COALESCE(count(demo.present),0) AS presents FROM RCHR_Dailyattenddemo AS demo " +
				" WHERE demo.attendanceDate BETWEEN '"+ PayrollUtils.getInstance().getDBCompatiableDate(startDate)+ "' AND '" +PayrollUtils.getInstance().getDBCompatiableDate(endDate)+ "' " +
				" AND demo.present='Y' AND demo.overtime='N' " +
				" AND demo.employee.id IN (SELECT emproom.employee.id FROM Rchr_Room_Employee AS emproom " +
				" WHERE " +
				//" emproom.employee.date<'"+PayrollUtils.getInstance().getDBCompatiableDate(startDate)+"' AND " +
				" emproom.isvacating ='N' AND emproom.allocate='Y' AND emproom.room.id='"+roomId+"') GROUP BY demo.employee.id ORDER BY presents DESC ";
		List<Long> list = OBDal.getInstance().getSession().createQuery(hql).list();
		return list;
	}
	public double getTotalPresentsEmployeesInRoom(String roomId, Date startDate, Date endDate){
		String hql = " SELECT COALESCE(count(demo.present),0) AS presents FROM RCHR_Dailyattenddemo AS demo, Rchr_Room_Employee AS emproom " +
				" WHERE demo.employee.id=emproom.employee.id " +
				" AND demo.attendanceDate BETWEEN '"+ PayrollUtils.getInstance().getDBCompatiableDate(startDate)+ "' AND '" +PayrollUtils.getInstance().getDBCompatiableDate(endDate)+ "' " +
				" AND demo.present='Y' AND demo.overtime='N' " +
				" AND emproom.isvacating ='N' AND emproom.allocate='Y' " +
				" AND emproom.room.id= '"+roomId+"' GROUP BY emproom.room.id ";
		//System.out.println("Electricity Bill is hql "+hql);
		HqlUtils hqlUtils = new HqlUtils();
		return hqlUtils.getFirstRecordValue(hql);
	}

	public double getTotalPresentsEmployeesInRoomExcludingNewJoiners(String roomId, Date startDate, Date endDate) {
		String hql = " SELECT COALESCE(count(demo.present),0) AS presents FROM RCHR_Dailyattenddemo AS demo, Rchr_Room_Employee AS emproom " +
				" WHERE demo.employee.id=emproom.employee.id " +
				" AND emproom.employee.date < '"+PayrollUtils.getInstance().getDBCompatiableDate(startDate)+"' " +
				" AND demo.attendanceDate BETWEEN '"+ PayrollUtils.getInstance().getDBCompatiableDate(startDate)+ "' AND '" +PayrollUtils.getInstance().getDBCompatiableDate(endDate)+ "' " +
				" AND demo.present='Y' AND demo.overtime='N' " +
				" AND emproom.isvacating ='N' AND emproom.allocate='Y' " +
				" AND emproom.room.id= '"+roomId+"' GROUP BY emproom.room.id ";
		//System.out.println("Electricity Bill is hql "+hql);
		HqlUtils hqlUtils = new HqlUtils();
		return hqlUtils.getFirstRecordValue(hql);
	}


	public double getNoOfPersonsJoined(String roomId, Date startDate){
		String hql = " SELECT COALESCE(count(emproom.id),0) AS persons FROM Rchr_Room_Employee AS emproom " +
				" WHERE emproom.employee.date >= '"+PayrollUtils.getInstance().getDBCompatiableDate(startDate)+"' " +
				//" AND emproom.attendanceDate BETWEEN '"+ PayrollUtils.getInstance().getDBCompatiableDate(startDate)+ "' AND '" +PayrollUtils.getInstance().getDBCompatiableDate(endDate)+ "' " +
				" AND emproom.isvacating ='N' AND emproom.allocate='Y' " +
				" AND emproom.room.id= '"+roomId+"' GROUP BY emproom.room.id ";
		//System.out.println("Electricity Bill is hql "+hql);
		HqlUtils hqlUtils = new HqlUtils();
		return hqlUtils.getFirstRecordValue(hql);
	}
}