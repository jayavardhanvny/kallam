package com.rcss.humanresource.util;

import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.common.enterprise.DocumentType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DocumentNumberUtil {
    public String getDocumentNo(String strDoctypeId, String strMovementDate){
        String docno="";
        try {
            DocumentType doctype = OBDal.getInstance().get(DocumentType.class, strDoctypeId);
            Sequence seq=doctype.getDocumentSequence();

            int currentnext=0;
            String suffix="";
            String prefix="";
            Connection conn = OBDal.getInstance().getConnection();
            Statement stmt1 = conn.createStatement();
            String sqry1 = "select currentnext,prefix,suffix from epcg_seqline where (ad_sequence_id='" + seq.getId() + "') and to_date('" + strMovementDate + "') between startdate and enddate";
            System.out.println("sqry1 is" +sqry1);
            ResultSet rs1 = stmt1.executeQuery(sqry1);
            while (rs1.next()) {
                currentnext = rs1.getInt("currentnext");
                prefix=rs1.getString("prefix");
                suffix=rs1.getString("suffix");
                System.out.println("currentnext is" + currentnext);
                System.out.println("prefix is" + prefix);
                System.out.println("suffix is" + suffix);

            }

            String nextnew=Integer.toString(currentnext);

            if((prefix != null) && (suffix != null))
            {
                docno=prefix.concat(nextnew).concat(suffix);
            }else if((prefix == null) && (suffix == null))
            {
                docno=nextnew;
            }else if(prefix == null)
            {
                docno=nextnew.concat(suffix);
            }
            else if(suffix == null)
            {
                docno=prefix.concat(nextnew);
            }
            System.out.println("docno is" +docno);


            //info.addResult("inpdocumentno", docno);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return docno;
    }
}
