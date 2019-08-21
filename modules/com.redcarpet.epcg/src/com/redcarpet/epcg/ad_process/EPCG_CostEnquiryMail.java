package com.redcarpet.epcg.ad_process;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOut;
import org.openbravo.model.materialmgmt.transaction.ShipmentInOutLine;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.openbravo.model.ad.access.User;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import org.openbravo.base.provider.OBProvider;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.client.kernel.RequestContext;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.Utility;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.utility.Sequence;
import org.openbravo.model.materialmgmt.transaction.MaterialTransaction;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessContext;
import org.openbravo.service.db.DalBaseProcess;
import org.openbravo.service.db.DalConnectionProvider;
import com.redcarpet.epcg.data.EpcgCostenquiry;
import com.redcarpet.epcg.data.epcg_ppcenquiry;
import org.openbravo.utils.CryptoUtility;

public class EPCG_CostEnquiryMail extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {
    	final ProcessContext processContext = bundle.getContext();
    	VariablesSecureApp vars = processContext.toVars();
    	System.out.println("Variable user ==== "+vars.getUser());
    	//RequestContext.get().setVariableSecureApp(processContext.toVars());
    	try{
    		
    		
    		//ConfigParameters config = bundle.getConfig();
    		//System.out.println("config "+config.get);
    		doIt(bundle);
    	}catch(Exception e){
    		
    	}
    }
 public void doIt(ProcessBundle bundle){
	OBError msg = new OBError();
	String id = (String) bundle.getParams().get("Epcg_Costenquiry_ID");
	Connection con=OBDal.getInstance().getConnection();
	
	String sql="select " +
			"(select name from c_bpartner where c_bpartner_id=enq.c_bpartner_id) as partner, " +
			"(select name from c_bpartner where c_bpartner_id=enq.agent) as partner, " +
			"enq.qstandard as actquality, " +
			"enq.oncontract as oncontract, " +
			"(select Qualityno from rchr_qualitystandard where rchr_qualitystandard_id=enq.rchr_qualitystandard_id) as sortno, " +
			"enq.orderquantity as quantity,'' as stock,enq.Exmillpricerspermts as fp, " +
			"enq.noofloomsworked as runningloom,enq.warpratekgs as warprice,enq.weftratekgs as weftprice,enq.sizingfrommaster as size, " +
			"enq.actualspeed as speed,to_char(enq.deliverdate,'dd-mm-YYYY')  as delivery,enq.Salescommission as commission, " +
			"enq.creditperiod as credit,enq.cashdiscount as cd, " +
			"enq.contributionrsploom as contribution, " +
			"enq.actualefficiency as eff," +
			"enq.description as note " +
			"from epcg_costenquiry enq " +
			"where " +
			"enq.epcg_costenquiry_id='"+id+"' ";
	
	
	
	StringBuilder sb = new StringBuilder();
	String agent="Direct";
	String note="";
	String customer,acutal,oncontract,sortno,orderqty,stock,finalprice,running,warpprice,weftprice,sizing,speed,efficiency,delivery,
	commission,creditperiod,cd,contribution;
	
				try{
					Statement stmt=con.createStatement();
					
					ResultSet rs=stmt.executeQuery(sql);
					
					while(rs.next()){
						customer=rs.getString(1);
						agent = rs.getString(2)==null ? agent :  rs.getString(2);
						note = rs.getString(20)==null ? note :  rs.getString(20);
						//agent = rs.getString(2);
						acutal = rs.getString(3);
						oncontract = rs.getString(4);
						sortno = rs.getString(5);
						orderqty = rs.getString(6);
						stock = rs.getString(7);
						finalprice = rs.getString(8);
						running = rs.getString(9);
						warpprice = rs.getString(10);
						weftprice = rs.getString(11);
						sizing = rs.getString(12);
						speed = rs.getString(13);
						efficiency = rs.getString(19);
						delivery = rs.getString(14);
						commission = rs.getString(15);
						creditperiod = rs.getString(16);
						cd =rs.getString(17); 
						contribution =rs.getString(18);
						
						
						
						
						sb.append("<html><head><style>table {border-collapse: collapse;}table, th, td {border: 1px solid black;}" +
			    				"#background{position:absolute;z-index:0;background:white;display:block;min-height:10%; min-width:10%;color:yellow;}" +
			    				"#content{position:absolute;z-index:1;}" +
			    				"#bg-text{color:lightgrey;font-size:40px;transform:rotate(300deg);-webkit-transform:rotate(300deg);}" +
			    				"</style></head><body><div id=\"background\"><p id=\"bg-text\">System</p></div>" +
			    				"<div id=\"content\"><table><tbody>" +
			    				"<tr><td>Customer</td><td>"+rs.getString(1)+"</td></tr>" +
			    				"<tr><td>Agent</td><td>"+agent+"</td></tr>" +
			    				"<tr><td>Actual Quality</td><td>"+rs.getString(3)+"</td></tr>" +
			    				"<tr><td>On Contract</td><td>"+rs.getString(4)+"</td></tr>" +
			    				"<tr><td>Sort No</td><td>"+rs.getString(5)+"</td></tr>" +
			    				"<tr><td>Order Qty Mts.</td><td>"+rs.getString(6)+"</td></tr>" +
			    				"<tr><td>Stock Mts.</td><td>"+rs.getString(7)+"</td></tr>" +
			    				"<tr><td>Final Price Rs.</td><td>"+rs.getString(8)+"</td></tr>" +
			    				"<tr><td>Running Loom</td><td>"+rs.getString(9)+"</td></tr>" +
			    				"<tr><td>Warp Price Rs.</td><td>"+rs.getString(10)+"</td></tr>" +
			    				"<tr><td>Weft Price Rs.</td><td>"+rs.getString(11)+"</td></tr>" +
			    				"<tr><td>Sizing Cost Rs.</td><td>"+rs.getString(12)+"</td></tr>" +
			    				"<tr><td>Speed RPM</td><td>"+rs.getString(13)+"</td></tr>" +
			    				"<tr><td>Efficiency %</td><td>"+rs.getString(19)+"</td></tr>" +
			    				"<tr><td>Delivery Date</td><td>"+rs.getString(14)+"</td></tr>" +
			    				"<tr><td>Agent Commission %</td><td>"+rs.getString(15)+"</td></tr>" +
			    				"<tr><td>Credit Period Days</td><td>"+rs.getString(16)+"</td></tr>" +
			    				"<tr><td>CD %</td><td>"+rs.getString(17)+"</td></tr>" +
			    				"<tr><td>Contribution Rs.</td><td>"+rs.getString(18)+"</td></tr>" +
			    				"<tr><td>Note :</td><td>"+note+"</td></tr>" +
			    				"</tbody></table></div></body></html>");
			    		
			    	}

				}catch(Exception e){
					
				}
	
	//System.out.println("sql..."+sql);
	
	
	EpcgCostenquiry cost=OBDal.getInstance().get(EpcgCostenquiry.class, id);
	try{	
		//System.out.println(cost.getUserid().getEmailServerUsername()+" and "+CryptoUtility.decrypt(cost.getUserid().getEmailServerPassword()));
		msg = send(cost.getUserid().getEmailServerUsername(),CryptoUtility.decrypt(cost.getUserid().getEmailServerPassword()),
				"pgr@ksml.in","costing checking",sb.toString(),cost);
		 	//msg.setType("Success");
		    //msg.setTitle("Done");
		    //msg.setMessage("Mail Sent Successfully");
		    bundle.setResult(msg);
		    
	}catch(javax.servlet.ServletException e){
		
	}
}
       
    public OBError send(final String from,final String password,String to,String sub,String msg,EpcgCostenquiry cost){  
    	OBError msg1 = new OBError();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        try {
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
          });
        
        	

            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(from));
          
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("jmd@ksml.in"));
            
            message= getCC(cost,message);
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(to));
            
            message.setSubject("Order Approval");
            message.setContent(msg, "text/html");

           // Transport.send(message);

            Transport transport = session.getTransport("smtp");
            transport.send(message);
            transport.close();
            
            
            
            System.out.println("Done");
            msg1.setType("Success");
		    msg1.setTitle("Done");
		    msg1.setMessage("Mail Sent Successfully");
		    cost.setComplete(Boolean.TRUE);
        } catch (javax.mail.AuthenticationFailedException  e) 
        {
        	e.printStackTrace();
            // throw new RuntimeException(e);
            //System.out.println("Username or Password are incorrect ... exiting !");
        	msg1.setMessage(e.toString());
        	msg1.setType("Error");
    	    msg1.setTitle("Not Complete");
    	    msg1.setMessage("Authentication Exception: Your Credentials Username or Password given are incorrect ");
        } catch (MessagingException  e) 
        {
        	e.printStackTrace();
            // throw new RuntimeException(e);
            //System.out.println("Username or Password are incorrect ... exiting !");
        	
        	msg1.setType("Error");
    	    msg1.setTitle("Not Complete");
    	    msg1.setMessage("Internet Error: Please Check the Internet Connection");
        }
		
		return msg1;
	}
    public Message getCC(EpcgCostenquiry cost,Message message){
    	
    	 OBCriteria<User> user = OBDal.getInstance().createCriteria(User.class);
         user.add(Restrictions.eq(User.PROPERTY_EPCGISEMAIL, Boolean.TRUE));
         System.out.println("List Size of Users "+user.list().size());
         for(User u : user.list()){
        	// if(u.getId().equals(cost.getUserid().getId())){
        		 
        		 
        	 //}else{
        		 if((!u.getEmailServerUsername().equals("")) || (u.getEmailServerUsername()!=null)){
        			 try{
        			 message.addRecipient(Message.RecipientType.CC, new InternetAddress(
                             u.getEmailServerUsername()));
            		 //System.out.println("in Else Condition If ");
            		 
        			 } 
        	        catch (MessagingException e) 
        	        {
        	        	e.printStackTrace();
        	            // throw new RuntimeException(e);
        	            //System.out.println("Username or Password are incorrect CC ... exiting !");
        	        }
        		 }
        		 
        	 //}
         }
         
    	return message;
    }
    

}
