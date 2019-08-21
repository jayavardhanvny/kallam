package com.redcarpet.payroll.ad_actionbutton;

import com.redcarpet.payroll.data.RCPL_EmpPayrollProcess;
import com.redcarpet.payroll.data.RCPL_PayrollProcess;
import com.redcarpet.payroll.util.PayrollProcessUpdation;
import org.apache.log4j.Logger;
import org.openbravo.dal.service.OBDal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RchrAttendanceUpdation implements PayrollProcessUpdation{
    public static final Logger logger = Logger.getLogger(PayrollProcessUpdation.class);
    @Override
    public void update(RCPL_PayrollProcess process) {
        for(RCPL_EmpPayrollProcess empPayrollProcess : process.getRCPLEmpPayrollProcessList()){
            Connection conn = OBDal.getInstance().getConnection();
            try{
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("update rchr_attend_temp set ispayroll ='Y' where rchr_employee_id= '"+empPayrollProcess.getEmployee().getId()+
                        "' and attdate between '"+process.getStartingDate()+"' and '"+process.getEndingDate()+"'");
            }catch (SQLException sql){
                logger.error("Error is ",sql);
            }

        }
    }
}
