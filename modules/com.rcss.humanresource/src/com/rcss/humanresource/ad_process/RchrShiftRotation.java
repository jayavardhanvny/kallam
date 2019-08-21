package com.rcss.humanresource.ad_process;

import com.rcss.humanresource.data.RchrMRelay;
import com.rcss.humanresource.data.RchrRelay;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.HqlUtils;
import com.redcarpet.payroll.util.PayrollUtils;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RchrShiftRotation extends DalBaseProcess implements BundleProcess {
    final private Logger logger = LoggerFactory.getLogger(RchrShiftRotation.class);

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        final OBError msg = new OBError();
        try {
            doIt(bundle);
        }catch(IllegalArgumentException e){
            msg.setType("Error");
            msg.setTitle("Error");
            msg.setMessage("Error is : "+e.getMessage());
        }
        bundle.setResult(msg);
    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {
        //OBCriteria<RchrRelay> rchrRelay = OBDal.getInstance().createCriteria(RchrRelay.class);
        //rchrRelay.add(Restrictions.eq)
        Date date = new Date();
        Calendar caltStart = Calendar.getInstance();
        Calendar caltEnd = Calendar.getInstance();

        //caltStart.set(Calendar.MONTH,-1);
        logger.info("Start Date is 1 {}",caltStart.getTime());
        caltStart.set(Calendar.DATE,caltStart.getActualMinimum(caltStart.DATE));
        //caltEnd.add(Calendar.MONTH,1);
        caltEnd.set(Calendar.DATE,caltStart.getActualMaximum(caltStart.DATE));
        logger.info("Start Date is 2 {}",caltStart.getTime());
        logger.info("End Date is {}",caltEnd.getTime());
        HqlUtils hqlUtils = new HqlUtils();
        List<RchrRelay> rlyList = hqlUtils.getShiftRotation(caltStart.getTime(),caltEnd.getTime());
        logger.info("Relay size is {}",rlyList.size());
        Calendar calendarTo = Calendar.getInstance();
        Calendar calendarFrom = Calendar.getInstance();
        for(RchrRelay rly : rlyList){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(caltEnd.getTime());
            calendarFrom.setTime(rly.getFromdate());
            calendarFrom.add(Calendar.MONTH,1);
            calendarTo.setTime(rly.getTodate());
            calendarTo.add(Calendar.MONTH,1);
            logger.info("rly.getTodate() {} and {} ",PayrollUtils.getInstance().getParsedDate(rly.getTodate()), PayrollUtils.getInstance().getParsedDate(caltEnd.getTime()));
            if (PayrollUtils.getInstance().getParsedDate(rly.getTodate()).compareTo(PayrollUtils.getInstance().getParsedDate(caltEnd.getTime()))==0){
                calendar.add(Calendar.DATE,1);
                calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
                calendarTo.setTime(calendar.getTime());
                logger.info("In If calEnd "+calendar.getTime());
            }
            //calendarTo.setTime(caltEnd.getTime());
            logger.info("In for Loop {} and {}  ",calendarFrom.getTime(), calendarTo.getTime());
            logger.info("In for Loop name {} ", rly.getRchrMrelay().getCommercialName());
            OBCriteria<RchrRelay> rchrRelayOBCriteria = OBDal.getInstance().createCriteria(RchrRelay.class);
            rchrRelayOBCriteria.add(Restrictions.eq(RchrRelay.PROPERTY_FROMDATE,PayrollUtils.getInstance().getParsedDate(calendarFrom.getTime())));
            rchrRelayOBCriteria.add(Restrictions.eq(RchrRelay.PROPERTY_TODATE,PayrollUtils.getInstance().getParsedDate(calendarTo.getTime())));
            rchrRelayOBCriteria.add(Restrictions.eq(RchrRelay.PROPERTY_RCHRMRELAY,rly.getRchrMrelay()));
            rchrRelayOBCriteria.add(Restrictions.eq(RchrRelay.PROPERTY_SHIFT,rly.getShift()));
            if (rchrRelayOBCriteria.list().size()==0){
                RchrRelay rchrRelay = OBProvider.getInstance().get(RchrRelay.class);
                rchrRelay.setOrganization(rly.getOrganization());
                rchrRelay.setFromdate(PayrollUtils.getInstance().getParsedDate(calendarFrom.getTime()));
                rchrRelay.setTodate(PayrollUtils.getInstance().getParsedDate(calendarTo.getTime()));
                rchrRelay.setRchrMrelay(rly.getRchrMrelay());
                rchrRelay.setShift(rly.getShift());
                OBDal.getInstance().save(rchrRelay);
            } else {
                logger.info("In Else Condition already there");
            }

        }

    }
}

