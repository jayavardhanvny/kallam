package com.redcarpet.goodsissue.ad_callouts;

import com.rcss.humanresource.data.RCHR_Jobcard;
import com.redcarpet.goodsissue.data.RcgiMaterialIndent;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;

import javax.servlet.ServletException;

public class SelectQtyFromJobCard extends SimpleCallout

    {

        @Override
        protected void execute(CalloutInfo info) throws ServletException {
            try{
            String indentId=info.getStringParameter("inprcgiMaterialindentId",null);
            String jopCardNo=info.getStringParameter("inprchrJobcardId",null);
            RcgiMaterialIndent indent=OBDal.getInstance().get(RcgiMaterialIndent.class,indentId);
            System.out.println("indentId"+indentId+" "+indent.getIndenttype());
            if("Warp".equals(indent.getIndenttype())){
                info.addResult("inprequiredqty", OBDal.getInstance().get(RCHR_Jobcard.class,jopCardNo).getWarpyarnreq().subtract(OBDal.getInstance().get(RCHR_Jobcard.class,jopCardNo).getWarpissued()));
                info.addResult("inprchrQualitystandardId",OBDal.getInstance().get(RCHR_Jobcard.class,jopCardNo).getRchrQualitystandard().getId());
                info.addResult("inpconstruction",OBDal.getInstance().get(RCHR_Jobcard.class,jopCardNo).getConstruction());
                info.addResult("inprchrWarpyarntypeId",OBDal.getInstance().get(RCHR_Jobcard.class,jopCardNo).getRchrQualitystandard().getRchrWarpyarntype().getId());
                info.addResult("inpepcgVariantId",OBDal.getInstance().get(RCHR_Jobcard.class,jopCardNo).getRchrQualitystandard().getEpcgVariant().getId());
            }else {
                info.addResult("inprequiredqty", OBDal.getInstance().get(RCHR_Jobcard.class,jopCardNo).getWeftyarnreq().subtract(OBDal.getInstance().get(RCHR_Jobcard.class,jopCardNo).getWeftissued()));
                info.addResult("inprchrQualitystandardId",OBDal.getInstance().get(RCHR_Jobcard.class,jopCardNo).getRchrQualitystandard().getId());
                info.addResult("inpconstruction",OBDal.getInstance().get(RCHR_Jobcard.class,jopCardNo).getConstruction());
                info.addResult("inprchrWarpyarntypeId",OBDal.getInstance().get(RCHR_Jobcard.class,jopCardNo).getRchrQualitystandard().getWarpyarntype().getId());
                info.addResult("inpepcgVariantId",OBDal.getInstance().get(RCHR_Jobcard.class,jopCardNo).getRchrQualitystandard().getWeftvariant().getId());

            }
            }catch(Exception e){
                e.printStackTrace();
            }
    }
}

