package com.rcss.humanresource.ad_callouts;

import com.rcss.humanresource.data.RCHRDesigBasic;
import com.rcss.humanresource.data.RchrEmployee;
import com.rcss.humanresource.util.RchrConstantType;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import com.rcss.humanresource.util.DocumentNumberUtil;
import javax.servlet.ServletException;
import com.redcarpet.payroll.util.PayrollUtils;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
public class RchrJoiningRejoiningCallout extends SimpleCallout {
    @Override
    protected void pageErrorCallOut(HttpServletResponse response) throws IOException {
        super.pageErrorCallOut(response);
    }

    @Override
    protected void execute(CalloutInfo info) throws ServletException {
        String changedColumn = info.getStringParameter("CHANGED_COLUMN",null);
        String currentDate = info.getStringParameter("inpdate", null);
        String strDocType = "6F6AA28BFBE14E0B9D999FFA4E9A9AF4";
	String salaryStructureLineId = info.getStringParameter("inprchrDesigbasicId",null);
        //switch(changedColumn) {
            //case "inpjointype":
                updateDocumentNo(info,currentDate,strDocType);
                //break;
           // case "inprchrEmployeeId":
                String strRchrEmployeeId = info.getStringParameter("inprchrEmployeeId", null);
               if(strRchrEmployeeId != null && !strRchrEmployeeId.equals("")){
                    updateEmployeeDetails(strRchrEmployeeId,info);
                }
	if (salaryStructureLineId != null && !salaryStructureLineId.equals("")){
            updateOperatorSalaryStructureDetails(salaryStructureLineId, info);
        }
                //break;
       // }
    }
    private void updateDocumentNo(CalloutInfo info,String currentDate,String strDocType){
        DocumentNumberUtil util = new DocumentNumberUtil();
        String joinType = info.getStringParameter("inpjointype",null);
        String strEmployeeDocumnentId = "908745C15A4B45C0A1B157BE27236909";
        String uniqueEmployeeId = "";
        if(joinType.equals("J")){
            strDocType="301FC8A01CD046C19E8ABA95381422F4";
            uniqueEmployeeId = util.getDocumentNo(strEmployeeDocumnentId, currentDate);
        }else if(joinType.equals("RJ")){
            strDocType="4B28237B58B7499BB8B05895DBE6E88E";
        }
        info.addResult("inpemployeeid", uniqueEmployeeId);
        info.addResult("inpcDoctypeId", strDocType);
        info.addResult("inpdocumentno", util.getDocumentNo(strDocType, currentDate));

        //info.addResult("inpemployeeid", util.getDocumentNo(strEmployeeNextId, currentDate));

    }
    private void updateEmployeeDetails(String strRchrEmployeeId,CalloutInfo info){
	String message = "";
        RchrEmployee employeeObject = OBDal.getInstance().get(RchrEmployee.class,strRchrEmployeeId);
        //RCHRDesigBasic
        log4j.info("Emploee Object "+employeeObject.getDocumentNo());
        info.addResult("inprchrEmpDeptId", employeeObject.getEmployeeDepartment().getId());
        info.addResult("inprchrDesignationId", employeeObject.getDesignation().getId());
        info.addResult("inprchrDesignationId", employeeObject.getDesignation().getId());
        info.addResult("inpemployeename", employeeObject.getEmployeeName());
        info.addResult("inppunchno", employeeObject.getPunchno());
        info.addResult("inpemployeetype", employeeObject.getEmployeeType());
        info.addResult("inpgender", employeeObject.getGender());
        info.addResult("inpmaritalstatus", employeeObject.getMaritalStatus());
        info.addResult("inprchrSubdepartmentId", employeeObject.getSubDepartment().getId());
        info.addResult("inpemployeeid", employeeObject.getDocumentNo());
	if (employeeObject.getImage() == null){
	    message = "Image is not Present in Employee Records";
            info.addResult("WARNING",message);
	}
        else {
            info.addResult("inpadImageId", employeeObject.getImage() == null ? null : employeeObject.getImage().getId());
        }

	
        //info.addResult("inprcplPayrolltemplateId", employeeObject.getSubDepartment().getId());
	if (employeeObject.getRCPLEmpSalSetupList().size()>0)
            info.addResult("inprcplPayrolltemplateId", employeeObject.getRCPLEmpSalSetupList().get(0).getPayrollTemplate().getId());
        else {
            message = "Employee Salary Setup is not assigned";
            info.addResult("WARNING",message);
        }
	log4j.info("Emploee getDate "+employeeObject.getDate());
	info.addResult("inppreviousservicedays", employeeObject.getActualservicedays());
	info.addResult("inppreviousjoindate", PayrollUtils.getInstance().getCalloutCompatibleDate(employeeObject.getDate()));
    }
	private void updateOperatorSalaryStructureDetails(String salaryStructureLineDesigBasicId, CalloutInfo info){
        RCHRDesigBasic rchrDesigBasic = OBDal.getInstance().get(RCHRDesigBasic.class, salaryStructureLineDesigBasicId);
        info.addResult("inpbasic", new BigDecimal(rchrDesigBasic.getBasicamount()));
        info.addResult("inpserviceincentive", new BigDecimal(rchrDesigBasic.getServiceincentive()));
        info.addResult("inpdeclareservicedays",new BigDecimal(rchrDesigBasic.getFromdays()));
    }
}

