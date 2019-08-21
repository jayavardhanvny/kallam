package com.rcss.humanresource.ad_actionButtons;

import com.rcss.humanresource.data.RCHR_Beam;
import com.rcss.humanresource.data.RchrBeamLines;
import com.rcss.humanresource.util.BundleProcess;
import com.rcss.humanresource.util.RchrConstantType;
import org.openbravo.dal.service.OBDal;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

public class ManualBreakDownBeam extends DalBaseProcess implements BundleProcess{
    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        try{

            doIt(bundle);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void doIt(ProcessBundle bundle) throws Exception {
       String id = (String) bundle.getParams().get("Rchr_Beam_ID");
        RCHR_Beam rchrBeam = OBDal.getInstance().get(RCHR_Beam.class,id);
        rchrBeam.setAlertStatus(RchrConstantType.BEAM_STATUS_IDLE);
        for(RchrBeamLines rchrBeamLines : rchrBeam.getRchrBeamLinesList()){
            if(!rchrBeamLines.getAlertStatus().equals(RchrConstantType.DAY_TYPE_CO)){
                rchrBeamLines.setAlertStatus(RchrConstantType.BEAM_LINES_STATUS_MN);
            }
        }
    }
}

