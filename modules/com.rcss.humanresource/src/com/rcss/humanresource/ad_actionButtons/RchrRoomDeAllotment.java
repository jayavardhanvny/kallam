package com.rcss.humanresource.ad_actionButtons;
import com.rcss.humanresource.data.RchrRoomEmployee;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.HqlUtils;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RchrRoomDeAllotment extends DalBaseProcess implements BundleProcess {
    protected Logger logger = Logger.getLogger("RchrRoomAllocation.java");
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        final OBError msg = new OBError();
        try{
            doIt(bundle);
            msg.setType("Success");
            msg.setTitle("Done");
            msg.setMessage("Success");
            bundle.setResult(msg);
            OBDal.getInstance().commitAndClose();
        }catch(Exception exception){
            exception.printStackTrace();
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage("Error is : "+exception.getMessage());
            bundle.setResult(msg);
            OBDal.getInstance().rollbackAndClose();
            logger.log(Level.SEVERE,exception.getMessage());
        }
    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {
        String id = (String) bundle.getParams().get("Rchr_Room_Employee_ID");
        RchrRoomEmployee rchrRoomEmployee = OBDal.getInstance().get(RchrRoomEmployee.class,id);
        OBCriteria<RchrRoomEmployee> rchrRoomEmployeeOBCriteria = OBDal.getInstance().createCriteria(RchrRoomEmployee.class);
        rchrRoomEmployeeOBCriteria.add(Restrictions.eq(RchrRoomEmployee.PROPERTY_ROOM,rchrRoomEmployee.getRoom()));
        rchrRoomEmployeeOBCriteria.add(Restrictions.eq(RchrRoomEmployee.PROPERTY_ISVACATING,Boolean.FALSE));


        //if(rchrRoomEmployee.getRoom().getRchrRoomEmployeeList().size()==0){
        rchrRoomEmployee.getEmployee().setRoom(null);
        rchrRoomEmployee.getEmployee().setRoomAllotted(Boolean.FALSE);
        rchrRoomEmployee.setDeallocate(Boolean.TRUE);
        rchrRoomEmployee.getRoom().setNoofemployees(new BigDecimal(getRoomElectricityBill(rchrRoomEmployee.getRoom().getId())));
        if(rchrRoomEmployeeOBCriteria.list().size()==0){
            rchrRoomEmployee.getRoom().setVacant(Boolean.TRUE);
            //rchrRoomEmployee.getRoom().setNoofemployees(new BigDecimal(0));
        } else if (rchrRoomEmployeeOBCriteria.list().size()>0){
            //rchrRoomEmployee.getRoom().setNoofemployees(new BigDecimal(rchrRoomEmployeeOBCriteria.list().size()-1));
        }

        //}
    }

    public double getRoomElectricityBill(String roomId){
        HqlUtils hqlUtils = new HqlUtils();
        String hql = "SELECT coalesce(count(*),0) FROM Rchr_Room_Employee AS emproom "
                + " WHERE emproom.allocate = 'Y' AND emproom.isvacating='N' AND emproom.room.id='"+roomId+"'" ;
        //System.out.println("HQl is Electric Bill "+hql);
        return hqlUtils.getFirstRecordValue(hql);
    }
}


