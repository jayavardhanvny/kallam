package com.rcss.humanresource.util;

import com.redcarpet.epcg.data.EpcgSeqline;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.exception.OBException;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.database.ConnectionProvider;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.common.enterprise.DocumentType;
import org.openbravo.model.financialmgmt.calendar.Period;
import org.openbravo.service.db.DalConnectionProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DocumentNumberEventUtil {
    public void saveDocument(DocumentType doctype,Date date){

        Sequence seq=doctype.getDocumentSequence();
        Date gidate=date;

        String status="";

        Date aDate=date;
        //System.out.println(aDate);
        SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal=Calendar.getInstance();
        cal.setTime(aDate);
        cal.set(Calendar.DATE,cal.getActualMinimum(Calendar.DATE));
        java.util.Date fromDate=cal.getTime();
        //System.out.println(cal.getTime());
        cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DATE));
        java.util.Date toDate=cal.getTime();
        //System.out.println(cal.getTime());

        //  Period p=OBDal.getInstance()
        OBCriteria<Period> p= OBDal.getInstance().createCriteria(Period.class);
        p.add(Restrictions.eq(Period.PROPERTY_STARTINGDATE,fromDate)).add(Restrictions.eq(Period.PROPERTY_ENDINGDATE,toDate));
        //System.out.println("size..."+p.list().size());
        if(p.list().size()!=0){
            for(Period pobj:p.list()){
                Period p1=OBDal.getInstance().get(Period.class,pobj.getId());


                status=p1.getStatus();
                //System.out.println("status"+status);
            }
            if(status.equals("O")){
                //System.out.println("In if ");
            } else
            {

                String language = OBContext.getOBContext().getLanguage().getLanguage();
                ConnectionProvider conn = new DalConnectionProvider(false);
                throw new OBException(Utility.messageBD(conn, "RCGI_SequenceLineDate", language));

            }
        }else{

            String language = OBContext.getOBContext().getLanguage().getLanguage();
            ConnectionProvider conn = new DalConnectionProvider(false);
            throw new OBException(Utility.messageBD(conn, "RCGI_SequenceLineDate", language));

        }


        OBCriteria<EpcgSeqline> seqlinelist=OBDal.getInstance().createCriteria(EpcgSeqline.class);
        seqlinelist.add(Restrictions.eq(EpcgSeqline.PROPERTY_SEQUENCE,seq));
		/*seqlinelist.add(Restrictions.ge(EpcgSeqline.PROPERTY_STARTINGDATE,gidate));
		seqlinelist.add(Restrictions.lt(EpcgSeqline.PROPERTY_ENDINGDATE,gidate));*/

        //System.out.println("size of lines is" +seqlinelist.list().size());
        //System.out.println(".......");
        for (EpcgSeqline seqline : seqlinelist.list())
        {
            Date stdate=seqline.getStartingDate();
            Date endate=seqline.getEndingDate();

            if(((gidate.before(endate))&&(gidate.after(stdate))) || (gidate.equals(stdate)) || (gidate.equals(endate)))
            {
                //System.out.println("inside for loop == if");

                long oldnum=seqline.getNextAssignedNumber();
                long incnum=seqline.getIncrementBy();
                long newnum=oldnum+incnum;
                // System.out.println("newnum is:" + newnum);

                seqline.setNextAssignedNumber(newnum);
            }

        }

    }
}
