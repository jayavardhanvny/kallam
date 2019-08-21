package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.RchrAttendLine;
import com.rcss.humanresource.data.RchrAttendance;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.data.RchrRelay;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class AttendanceShiftLostTimeCalculations extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

        String id = (String) bundle.getParams().get("Rchr_Attendance_ID");
        RchrAttendance attendance = OBDal.getInstance().get(RchrAttendance.class, id);
        String shiftlosttime=attendance.getLostduration();
        List<RchrAttendLine> linesList = attendance.getRchrAttendLineList();
        for (RchrAttendLine line : linesList) {
            line.setLostduration(attendance.getLostduration());
            BigDecimal latetime=line.getLatetime();
            BigDecimal losttime=new BigDecimal(shiftlosttime);
            BigDecimal duration=line.getShift().getShiftInMins();
            BigDecimal resultone=latetime.add(losttime);
            BigDecimal resulttwo=duration.subtract(resultone);
            line.setDuration(resulttwo.toString());
            OBDal.getInstance().save(line);
            
        }
        OBError msg = new OBError();
        msg.setType("Success");
        msg.setTitle("Done");
        msg.setMessage("Process completed successfully");
        bundle.setResult(msg);
    }

   
}