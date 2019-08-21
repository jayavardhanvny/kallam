package com.rcss.humanresource.ad_actionButtons;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.rcss.humanresource.data.*;
import com.rcss.humanresource.util.HqlUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.hibernate.criterion.Restrictions;

public class LeaveProcess extends DalBaseProcess {

	@Override
	protected void doExecute(ProcessBundle bundle)  {
		String strLeaveId = (String)bundle.getParams().get("Rchr_Leaverequisition_ID");
		OBError msg = new OBError();
		try{

			RCHR_LeaveRequisition rchrLeaveApprove = OBDal.getInstance().get(RCHR_LeaveRequisition.class, strLeaveId);
			if(rchrLeaveApprove.getEmployee().getRchrLeavetemplate()==null){
				msg.setType("Error");
				msg.setTitle("Error");
				msg.setMessage("This Empployee does'nt have LeaveTemplate,please add to Employee Records if eligible");
			}else{


				HqlUtils hqlUtils = new HqlUtils();
				if (hqlUtils.getPayrollProcessCompletedList(rchrLeaveApprove.getFromDate()).size()>0) {
					msg.setType("Error");
					msg.setTitle("Error");
					msg.setMessage("Leave Cancellation cannot possible After Payroll(Salaries) was generated");
					OBDal.getInstance().rollbackAndClose();
				} else {
					deleteCurrentLines(strLeaveId);
					//updateCurrentHeader(strLeaveId);
					List<RCHRLeaveTemplateLine> templateLine=rchrLeaveApprove.getEmployee().getRchrLeavetemplate().getRCHRLeaveTemplateLineList();
					Calendar c1 = Calendar.getInstance();
					c1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(rchrLeaveApprove.getFromDate().toString()));

					Calendar c2 = Calendar.getInstance();
					c2.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(rchrLeaveApprove.getToDate().toString()));

					Date fromDate=new SimpleDateFormat("yyyy-MM-dd").parse(rchrLeaveApprove.getFromDate().toString());
					proccessLeaves(rchrLeaveApprove,c1,c2,fromDate);
					rchrLeaveApprove.setProcess(Boolean.TRUE);
					msg.setType("Success");
					msg.setTitle("Done");
					msg.setMessage("Leave Process has been completed");
					OBDal.getInstance().commitAndClose();
				}




			}
		}catch(Exception ex){
			msg.setType("Error");
			msg.setTitle("Error");
			msg.setMessage(ex.getMessage());
			ex.printStackTrace();
			OBDal.getInstance().rollbackAndClose();
		}

		bundle.setResult(msg);
	}

	private void proccessLeaves(RCHR_LeaveRequisition request,Calendar c1,Calendar c2,Date fromDate)  {

		//OBError msg = new OBError();
		long totalBalanceLeaves=getCurrentTotalBalanceLeaves(request.getEmployee().getId(),fromDate);
		long totalDays=getTotalDays(c1,c2);
		long weekOffs=getWeekoffs(c1,c2,request.getEmployee());
		insertLinesIntoRequistionLines(request,c1,c2,totalDays,weekOffs,totalBalanceLeaves,fromDate);

	}
	public void deleteCurrentLines(String leaveId){
		String hql=" DELETE FROM RCHR_LeaveRequisitionLine line WHERE line.leaveRequisition.id=? ";
		Query q=OBDal.getInstance().getSession().createQuery(hql);
		q.setParameter(0,leaveId);
		q.executeUpdate();
		//OBDal.getInstance().commitAndClose();
	}

	public void updateCurrentHeader(String leaveId){

		String hql=" UPDATE RCHR_LeaveRequisition head SET coffs=0,el=0,cl=0,totalleaves=0,weekoffleaves=0,lopleaves=0 WHERE head.id=? ";
		Query q=OBDal.getInstance().getSession().createQuery(hql);
		q.setParameter(0,leaveId);
		q.executeUpdate();
		//System.out.println("lines updated.."+q.executeUpdate());
		//OBDal.getInstance().commitAndClose();
	}


	private void insertLinesIntoRequistionLines(RCHR_LeaveRequisition head,Calendar c1,Calendar c2,long totalDays,long weekOffs,long totalBalanceLeaves,Date fromDate) {
		head.setTotalleaves((long)0);
		head.setCoffs((long)0);
		head.setEl((long)0);
		head.setCl((long)0);
		head.setLopleaves((long)0);
		head.setWeekoffleaves((long)0);
		long cl=0,sl=0,lop=0,leaves=0,coff=0,cofflag=0,total=0;
		boolean flag=true;
		cl=getCurrentCLLeaves(head.getEmployee().getId(),fromDate);
		sl=getCurrentELLeaves(head.getEmployee().getId(),fromDate);

		coff=getTotalCompensatePending(head.getEmployee().getId());
		if(totalBalanceLeaves>0){
			lop=totalDays-weekOffs-coff-totalBalanceLeaves;
			if(lop<=0){
				leaves=totalDays-weekOffs-coff;
				lop=0;
			}else{
				leaves=totalDays-weekOffs-coff-lop;
			}

		}else{
			lop=totalDays-weekOffs-coff;
		}

		//head.setLopleaves(lop<0?0:lop);
		//head.setCoffs(coff);
		//head.setTotalleaves(totalDays);
		//head.setWeekoffleaves(new BigDecimal(weekOffs));

		Calendar c3=Calendar.getInstance();
		c3.setTime(c1.getTime());

		Calendar c4=Calendar.getInstance();
		c4.setTime(c2.getTime());

		long start=0;
		long diff = (c4.getTimeInMillis()-c3.getTimeInMillis())/(1000*60*60*24);
		//System.out.println(cl+" "+el+" "+lop+" "+leaves);
		System.out.println("update values"+head.getTotalleaves());
		while(start<(diff+1)){
			RCHRLeaveRequisitionLine line=OBProvider.getInstance().get(RCHRLeaveRequisitionLine.class);
			line.setOrganization(head.getOrganization());
			line.setClient(head.getClient());
			line.setLeaveRequisition(head);
			if(head.getEmployee().isWeekOffApplicable()&&(c3.get(Calendar.DAY_OF_WEEK)==getDay(head.getEmployee().getWeeklyOff()))) {
				//System.out.println("week");
				head.setWeekoffleaves(head.getWeekoffleaves()+1);
				line.setLeaveType(OBDal.getInstance().get(RCHR_Leavetype.class, getLeaveType("994709A5025D48789BBBA5DBED0232A2")));
			}else if(coff>0){
				line.setLeaveType(OBDal.getInstance().get(RCHR_Leavetype.class, getLeaveType("4956B05EE98349829332FA7F4FCC381E")));
				line.setRchrCompensateoff(OBDal.getInstance().get(RchrCompensateOff.class,getCompensateId(head.getEmployee().getId(),cofflag)));
				head.setCoffs(head.getCoffs()+1);
				coff=coff-1;
				cofflag=cofflag+1;
			}
			else if(leaves>0){
			//	System.out.println("inside if condition");
				if(leaves<=sl&&sl!=0){
					line.setLeaveType(OBDal.getInstance().get(RCHR_Leavetype.class, getLeaveType("3963E0F3BA314420A7EA2DA537917460")));
					sl=sl-1;
					leaves=leaves-1;
					head.setEl(head.getEl()+1);
				}else if(leaves>sl&&sl!=0){
					line.setLeaveType(OBDal.getInstance().get(RCHR_Leavetype.class, getLeaveType("3963E0F3BA314420A7EA2DA537917460")));
					sl=sl-1;
					leaves=leaves-1;
					head.setEl(head.getEl()+1);
				} else if(leaves<=cl&&cl!=0){
					line.setLeaveType(OBDal.getInstance().get(RCHR_Leavetype.class, getLeaveType("0A4A7C4967784E67B231E2567960C413")));
					leaves=leaves-1;
					cl=cl-1;
					head.setCl(head.getCl()+1);
				}else if(leaves>cl&&cl!=0){
					line.setLeaveType(OBDal.getInstance().get(RCHR_Leavetype.class, getLeaveType("0A4A7C4967784E67B231E2567960C413")));
					cl=cl-1;
					leaves=leaves-1;
					head.setCl(head.getCl()+1);
				}
			}else if(lop>0){
				line.setLeaveType(OBDal.getInstance().get(RCHR_Leavetype.class, getLeaveType("262B24DFD2474A16ABD9EF06A127FCB0")));
				lop=lop-1;
				//System.out.println("lop");
				head.setLopleaves(head.getLopleaves()+1);
			}
			head.setTotalleaves(head.getTotalleaves()+1);
			line.setLeavedate(c3.getTime());
			OBDal.getInstance().save(line);
			OBDal.getInstance().flush();
			c3.add(Calendar.DATE,1);
			start++;
		}


	}


	private void insertLeaveIntoBalanceHistroy(RCHR_LeaveRequisition head,Date date,String leaveTypeId){
		RCHRLeaveBalanceHistory obj= OBProvider.getInstance().get(RCHRLeaveBalanceHistory.class);
		obj.setOrganization(head.getOrganization());
		obj.setClient(head.getClient());
		obj.setEmployee(head.getEmployee());
		obj.setLeavedate(date);
		obj.setLeavecount((long)-1);
		obj.setLeaveType(OBDal.getInstance().get(RCHR_Leavetype.class,leaveTypeId));
		obj.setLeavedoctype("LR");
		OBDal.getInstance().save(obj);
		OBDal.getInstance().flush();
	}


	private int getTotalDays(Calendar c1,Calendar c2){

		Calendar c3=Calendar.getInstance();
		c3.setTime(c1.getTime());

		Calendar c4=Calendar.getInstance();
		c4.setTime(c2.getTime());

		long diff = (c4.getTimeInMillis()-c3.getTimeInMillis())/(1000*60*60*24);
		//System.out.println("total days.."+(diff+1));
		return (int)(diff+1);
	}

	private int getWeekoffs(Calendar c1,Calendar c2,RchrEmployee e) {
		Calendar c3=Calendar.getInstance();
		c3.setTime(c1.getTime());

		Calendar c4=Calendar.getInstance();
		c4.setTime(c2.getTime());

		int weekdays = 0;
		long start=0;
		long diff = (c4.getTimeInMillis()-c3.getTimeInMillis())/(1000*60*60*24);
		while(start++<(diff+1)){

			if(c3.get(Calendar.DAY_OF_WEEK)==getDay(e.getWeeklyOff()))
				weekdays++;
			c3.add(Calendar.DATE,1);
		}
	//	System.out.println("week days.."+(weekdays));
		return weekdays;
	}

	private String getLeaveType(String leaveId){
		String hql=" SELECT type.id FROM RCHR_Leavetype type"
				+" WHERE type.id='"+leaveId+"' ";
		//System.out.println("leave type.."+(OBDal.getInstance().getSession().createQuery(hql).list().get(0).toString()));
		return OBDal.getInstance().getSession().createQuery(hql).list().get(0).toString();
	}

	/*private long getCurrentELLeaves(String employeeId){
		String hql=" SELECT sum(info.leavebalance) FROM RCHR_Emp_LeaveBalance info"
				+" WHERE info.employee.id='"+employeeId+"' AND info.leaveType.id='3963E0F3BA314420A7EA2DA537917460' ";
		System.out.println("EL leaves..."+OBDal.getInstance().getSession().createQuery(hql).list().get(0));
		return (long)OBDal.getInstance().getSession().createQuery(hql).list().get(0);
	}*/

	private long getCurrentELLeaves(String employeeId,Date fromDate){
		String hql=" SELECT COALESCE(sum(info.leavecount),0) FROM RCHR_LeaveBalanceHistory info"
				+" WHERE info.employee.id='"+employeeId+"' AND info.leavedate<='"+fromDate+"' AND info.leaveType.id='3963E0F3BA314420A7EA2DA537917460' ";
		//System.out.println("EL leaves..."+OBDal.getInstance().getSession().createQuery(hql).list().get(0));
		int size=OBDal.getInstance().getSession().createQuery(hql).list().size();
		return size>0?(long)OBDal.getInstance().getSession().createQuery(hql).list().get(0):0;

	}

	private long getTotalCompensatePending(String employeeId){
		String hql=" SELECT count(info.id) FROM RCHR_CompensateOff info"
				+" WHERE info.employee.id='"+employeeId+"' AND info.alertStatus='ACC' ";
		//System.out.println("Compensate Offs..."+OBDal.getInstance().getSession().createQuery(hql).list().get(0));
		return (long)OBDal.getInstance().getSession().createQuery(hql).list().get(0);
	}

	private String getCompensateId(String employeeId,long coff){
	//	System.out.println("coff"+coff);
		/*String hql=" SELECT info.id FROM RCHR_CompensateOff info"
				+" WHERE info.employee.id='"+employeeId+"' AND info.alertStatus='ACC' order by acuraldate asc limit 1 offset '"+coff+"' ";*/
		String hql=" SELECT info.id FROM RCHR_CompensateOff info"
				+" WHERE info.employee.id='"+employeeId+"' AND info.alertStatus='ACC' order by acuraldate asc ";
		Query q=OBDal.getInstance().getSession().createQuery(hql);
		q.setFirstResult((int)coff);
		q.setMaxResults(1);
		//System.out.println("Compensate Offs..."+q.list().get(0).toString());
		//return OBDal.getInstance().getSession().createQuery(hql).list().get(0).toString();
		return q.list().get(0).toString();
	}

	/*private long getCurrentCLLeaves(String employeeId){
		String hql=" SELECT sum(info.leavebalance) FROM RCHR_Emp_LeaveBalance info"
				+" WHERE info.employee.id='"+employeeId+"' AND info.leaveType.id='0A4A7C4967784E67B231E2567960C413' ";
		System.out.println("CL leaves..."+OBDal.getInstance().getSession().createQuery(hql).list().get(0));
		return (long)OBDal.getInstance().getSession().createQuery(hql).list().get(0);
	}*/

	private long getCurrentCLLeaves(String employeeId,Date fromDate){
		String hql=" SELECT COALESCE(sum(info.leavecount),0) FROM RCHR_LeaveBalanceHistory info"
				+" WHERE info.employee.id='"+employeeId+"' AND info.leavedate<='"+fromDate+"' AND info.leaveType.id='0A4A7C4967784E67B231E2567960C413' ";
		//System.out.println("CL leaves..."+OBDal.getInstance().getSession().createQuery(hql).list().get(0));
		int size=OBDal.getInstance().getSession().createQuery(hql).list().size();
		return size>0?(long)OBDal.getInstance().getSession().createQuery(hql).list().get(0):0;

	}

	/*private long getCurrentTotalBalanceLeaves(String employeeId,Date fromDate){
		String hql=" SELECT sum(info.leavebalance) FROM RCHR_Emp_LeaveBalance info"
				+" WHERE info.employee.id='"+employeeId+"' AND info.info.leaveType!='9517E6C535FE41CFBD4A45DC03570D12' ";
		//System.out.println("leaves..."+OBDal.getInstance().getSession().createQuery(hql).list().get(0));
		return (long)OBDal.getInstance().getSession().createQuery(hql).list().get(0);
	}*/

	private long getCurrentTotalBalanceLeaves(String employeeId,Date fromDate){
		String hql=" SELECT COALESCE(sum(info.leavecount),0) FROM RCHR_LeaveBalanceHistory info"
				+" WHERE info.employee.id='"+employeeId+"' AND info.leavedate<='"+fromDate+"' AND info.leaveType!='9517E6C535FE41CFBD4A45DC03570D12' ";
		//System.out.println("leaves..."+OBDal.getInstance().getSession().createQuery(hql).list().get(0));
		int size=OBDal.getInstance().getSession().createQuery(hql).list().size();
		//System.out.println("size..."+size);
		return size>0?(long)OBDal.getInstance().getSession().createQuery(hql).list().get(0):0;
	}

	private int getDay(String day) {

		int weekOff = -1;

		if (StringUtils.equalsIgnoreCase(day, "SUNDAY")) {
			weekOff = 1;
		} else if (StringUtils.equalsIgnoreCase(day, "MONDAY")) {
			weekOff = 2;
		} else if (StringUtils.equalsIgnoreCase(day, "TUESDAY")) {
			weekOff = 3;
		} else if (StringUtils.equalsIgnoreCase(day, "WEDNESDAY")) {
			weekOff = 4;
		} else if (StringUtils.equalsIgnoreCase(day, "THURSDAY")) {
			weekOff = 5;
		} else if (StringUtils.equalsIgnoreCase(day, "FRIDAY")) {
			weekOff = 6;
		} else if (StringUtils.equalsIgnoreCase(day, "SATURDAY")) {
			weekOff = 7;
		}else{
			weekOff=-1;
		}
		return weekOff;
	}


}

