package com.rcss.humanresource.ad_callouts;

import javax.servlet.ServletException;

import com.redcarpet.epcg.data.EpcgVariant;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.ad_callouts.SimpleCallout;
import org.openbravo.model.common.plm.Product;
import com.rcss.humanresource.data.*;
import com.redcarpet.production.data.RCPRShift;
import java.math.BigDecimal;

public class GenerateStandardCode extends SimpleCallout{

    @Override
    protected void execute(CalloutInfo info) throws ServletException {

        String strProductId = info.getStringParameter("inpmProductId",null);
        String strWeftProductId = info.getStringParameter("inpweftProduct",null);

        Product warpProduct = null;
        Product weftProduct = null;
        BigDecimal warpBigDecimal = BigDecimal.ZERO,weftBigDecimal=BigDecimal.ZERO;
        if((strProductId!=null || (!strProductId.equals("")))
            || (strWeftProductId!=null || (!strWeftProductId.equals("")))){
            try{
                warpProduct =  OBDal.getInstance().get(Product.class, strProductId);
                weftProduct =  OBDal.getInstance().get(Product.class, strWeftProductId);
                if(warpProduct!=null)
                    warpBigDecimal = new BigDecimal(Integer.parseInt(warpProduct.getName()));
                if(weftProduct!=null)
                    weftBigDecimal = new BigDecimal(Integer.parseInt(weftProduct.getName()));
            }catch (NumberFormatException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        info.addResult("inpwarpcount",warpBigDecimal);
        info.addResult("inpweftcount",weftBigDecimal);

    	//BigDecimal strwarpCount = info.getBigDecimalParameter("inpwarpcount"); //("inpwarpcount", null);
        String warpTypeId = info.getStringParameter("inprchrWarpyarntypeId", null);
        String variantTypeId = info.getStringParameter("inpepcgVariantId", null);

        //BigDecimal weftcount = info.getBigDecimalParameter("inpweftcount");
        String weftTypeId = info.getStringParameter("inpwarpyarntype", null);
        String weftvariantId = info.getStringParameter("inpweftvariant", null);


        EpcgVariant epcgVariant = OBDal.getInstance().get(EpcgVariant.class,variantTypeId);

        EpcgVariant weftEpcgVariant = OBDal.getInstance().get(EpcgVariant.class,weftvariantId);


        BigDecimal ordrdQuanty = info.getBigDecimalParameter("inporderquantity"); 
        BigDecimal epi = info.getBigDecimalParameter("inpepi");
        BigDecimal ppi = info.getBigDecimalParameter("inpppi");
        String weaveId = info.getStringParameter("inprchrInspweaveId", null);
        String greigeId = info.getStringParameter("inprchrInspgreigewidthId", null);
        
        RCHRWarpyarntype warp = OBDal.getInstance().get(RCHRWarpyarntype.class, warpTypeId);
        
        RCHRWarpyarntype weft = OBDal.getInstance().get(RCHRWarpyarntype.class, weftTypeId);
        RCHRInspweave weave = OBDal.getInstance().get(RCHRInspweave.class, weaveId);
        RchrInspgreigewidth greige = OBDal.getInstance().get(RchrInspgreigewidth.class, greigeId);
        
        String warpName="",weftName="",weaveName="",greigeName="",warpVariant="",weftVariant="";

        if(epcgVariant!=null && !epcgVariant.getCommercialName().isEmpty() ){
            warpVariant = epcgVariant.getCommercialName();
        }
        if(weftEpcgVariant!=null && !weftEpcgVariant.getCommercialName().isEmpty()){
            weftVariant = weftEpcgVariant.getCommercialName();
        }




        if(!warp.getName().isEmpty()){
        	warpName=warp.getName();
        } 								
        
        if(!weft.getName().isEmpty()){
        	weftName=weft.getName();
        }
        
        if(!weave.getName().isEmpty()){
        	weaveName=weave.getName();
        }
        
        if(!greige.getName().isEmpty()){
        	greigeName=greige.getName();
        }
        
        String qstandard = warpBigDecimal.toString()+warpName+" "+warpVariant.toString()+" X "+weftBigDecimal.toString()+weftName+" "+weftVariant.toString()+" / "+epi.toString()+" X "+ppi.toString()+" -   "+greigeName+" '' "+weaveName;
        //System.out.println(qstandard);
        info.addResult("inpqstandard", qstandard);
        info.addResult("inpremainingquantity", ordrdQuanty);
        //new String(" X ").concat(weftCount.toString())
        
    }
    
}

