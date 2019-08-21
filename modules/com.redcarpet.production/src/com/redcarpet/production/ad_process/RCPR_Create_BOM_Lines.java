package com.redcarpet.production.ad_process;

import com.redcarpet.production.data.RCPR_PrBOM;
import com.redcarpet.production.data.RCPR_PrBOMLines;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.model.common.plm.ProductBOM;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;

/**
 *
 * @author Mateen
 */
@SuppressWarnings("unchecked")
public class RCPR_Create_BOM_Lines extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
        
        String id = bundle.getParams().get("Rcpr_Prbom_ID").toString();
        
        RCPR_PrBOM bom = OBDal.getInstance().get(RCPR_PrBOM.class, id);
        List<ProductBOM> prdList = OBDal.getInstance().createCriteria(ProductBOM.class).
                add(Restrictions.eq(ProductBOM.PROPERTY_PRODUCT, bom.getProduct())).list();
        
        for(ProductBOM line : prdList){
            RCPR_PrBOMLines bomLine = OBProvider.getInstance().get(RCPR_PrBOMLines.class);
            bomLine.setBOMProduction(bom);
            bomLine.setLineNo(line.getLineNo());
            bomLine.setProduct(line.getBOMProduct());
            bomLine.setUOM(line.getBOMProduct().getUOM());
            bomLine.setStorageBin(bom.getStorageBin());
            bomLine.setRatio(line.getBOMQuantity());
            OBDal.getInstance().save(bomLine);
        
        }
        
        OBError err = new OBError();
        err.setType("Success");
        err.setTitle("Success");
        err.setMessage("Process Completed Successfully");
        bundle.setResult(err);
    }
}
