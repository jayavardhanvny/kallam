
package org.openbravo.erpCommon.ad_actionButton;


import org.openbravo.erpCommon.utility.*;
import org.openbravo.erpCommon.reference.*;
import org.openbravo.utils.Replace;
import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.scheduling.ProcessRunner;
import org.openbravo.xmlEngine.XmlDocument;
import org.openbravo.database.SessionInfo;
import org.openbravo.erpCommon.obps.ActivationKey;
import org.openbravo.erpCommon.obps.ActivationKey.FeatureRestriction;
import org.openbravo.dal.core.OBContext;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.ad.ui.Process;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;

public class ActionButtonJava_Responser extends HttpSecureAppServlet {
  private static final long serialVersionUID = 1L;
  protected static final String windowId = "ActionButtonResponser";
  
  public void init (ServletConfig config) {
    super.init(config);
    boolHist = false;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    String strProcessId = getProcessId(vars);

    // set process type and id for audit
    SessionInfo.setProcessType("P");
    SessionInfo.setProcessId(strProcessId);
    SessionInfo.setUserId(vars.getSessionValue("#AD_User_ID"));
    SessionInfo.setSessionId(vars.getSessionValue("#AD_Session_ID"));

    try {
      OBContext.setAdminMode();
      Process process = OBDal.getInstance().get(Process.class, strProcessId);
      if (process != null) {
        SessionInfo.setModuleId(process.getModule().getId());
      }
    } finally {
      OBContext.restorePreviousMode();
    }
    super.service(request, response);
  }

  private String getProcessId(VariablesSecureApp vars) throws ServletException {
    String command = vars.getCommand();
    if (command.equals("DEFAULT")) {
      return vars.getRequiredStringParameter("inpadProcessId");
    } else if (command.startsWith("BUTTON")) {
      return command.substring("BUTTON".length());
    } else if (command.startsWith("FRAMES")) {
      return command.substring("FRAMES".length());
    } else if (command.startsWith("SAVE_BUTTONActionButton")) {
      return command.substring("SAVE_BUTTONActionButton".length());
    }
    return null;
  }

  public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
    VariablesSecureApp vars = new VariablesSecureApp(request);
    String strProcessId = getProcessId(vars);
    
    if (vars.getCommand().startsWith("FRAMES")) {
      printPageFrames(response, vars, strProcessId);
    }
   
    if (!vars.commandIn("DEFAULT")) {
      //Check access
      FeatureRestriction featureRestriction = ActivationKey.getInstance().hasLicenseAccess("P",
          strProcessId);
      if (featureRestriction != FeatureRestriction.NO_RESTRICTION) {
        licenseError("P", strProcessId, featureRestriction, response, request, vars, true);
      }
      if (!hasGeneralAccess(vars, "P", strProcessId)) {
        bdErrorGeneralPopUp(request, response,
            Utility.messageBD(this, "Error", vars.getLanguage()), Utility.messageBD(this,
                "AccessTableNoView", vars.getLanguage()));
      }
    }
    
      
    if (vars.commandIn("DEFAULT")) {
      printPageDefault(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9DB4D30BFC5144B9B431CB49DDE9270D")) {
        
        printPageButton9DB4D30BFC5144B9B431CB49DDE9270D(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON7CB6B4D1ECCF4036B3F111D2CF11AADE")) {
        
        printPageButton7CB6B4D1ECCF4036B3F111D2CF11AADE(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON970EAD9B846648A7AB1F0CCA5058356C")) {
        
        printPageButton970EAD9B846648A7AB1F0CCA5058356C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON58763832F5F3485CAD33B8B9FCD6C640")) {
        
        printPageButton58763832F5F3485CAD33B8B9FCD6C640(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON7EDBFEC35BDA4FF4AF05ED516CDAFB90")) {
        
        printPageButton7EDBFEC35BDA4FF4AF05ED516CDAFB90(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONABDFC8131D964936AD2EF7E0CED97FD9")) {
        
        printPageButtonABDFC8131D964936AD2EF7E0CED97FD9(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONEFDBF909811544DAAE4E876AA781E5DC")) {
        
        printPageButtonEFDBF909811544DAAE4E876AA781E5DC(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONA269DCA4DE114E438695B66E166999B4")) {
        
        printPageButtonA269DCA4DE114E438695B66E166999B4(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON107")) {
        
        printPageButton107(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONCD7283DF804B449C97DA09446669EEEF")) {
        
        printPageButtonCD7283DF804B449C97DA09446669EEEF(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON85601427EAEE401FA0250FF0A6DD62EF")) {
        
        printPageButton85601427EAEE401FA0250FF0A6DD62EF(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONA3FE1F9892394386A49FB707AA50A0FA")) {
        
        printPageButtonA3FE1F9892394386A49FB707AA50A0FA(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON136")) {
        
        printPageButton136(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFB740AB61B0E42B198D2C88D3A0D0CE6")) {
        
        printPageButtonFB740AB61B0E42B198D2C88D3A0D0CE6(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON64B3FF29AC174F4B94538BD0A3CE1CD3")) {
        
        printPageButton64B3FF29AC174F4B94538BD0A3CE1CD3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONE264309FF8244A94936502BF51829109")) {
        
        printPageButtonE264309FF8244A94936502BF51829109(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON23D1B163EC0B41F790CE39BF01DA320E")) {
        
        printPageButton23D1B163EC0B41F790CE39BF01DA320E(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6FBD65B0FDB74D1AB07F0EADF18D48AE")) {
        
        printPageButton6FBD65B0FDB74D1AB07F0EADF18D48AE(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9EB2228A60684C0DBEC12D5CD8D85218")) {
        
        printPageButton9EB2228A60684C0DBEC12D5CD8D85218(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND85D5B5E368A49B1A6293BA4AE15F0F9")) {
        
        printPageButtonD85D5B5E368A49B1A6293BA4AE15F0F9(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF80808133362F6A013336781FCE0066")) {
        
        printPageButtonFF80808133362F6A013336781FCE0066(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF8081813219E68E013219ECFE930004")) {
        
        printPageButtonFF8081813219E68E013219ECFE930004(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF808181324D007801324D2AE1130066")) {
        
        printPageButtonFF808181324D007801324D2AE1130066(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF808181326CD80501326CE906D70042")) {
        
        printPageButtonFF808181326CD80501326CE906D70042(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF80818132A4F6AD0132A573DD7A0021")) {
        
        printPageButtonFF80818132A4F6AD0132A573DD7A0021(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON0BDC2164ED3E48539FCEF4D306F29EFD")) {
        
        printPageButton0BDC2164ED3E48539FCEF4D306F29EFD(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON5BE14AA10165490A9ADEFB7532F7FA94")) {
        
        printPageButton5BE14AA10165490A9ADEFB7532F7FA94(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON58A9261BACEF45DDA526F29D8557272D")) {
        
        printPageButton58A9261BACEF45DDA526F29D8557272D(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON15C8708DFC464C2D91286E59624FDD18")) {
        
        printPageButton15C8708DFC464C2D91286E59624FDD18(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON017312F51139438A9665775E3B5392A1")) {
        
        printPageButton017312F51139438A9665775E3B5392A1(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6255BE488882480599C81284B70CD9B3")) {
        
        printPageButton6255BE488882480599C81284B70CD9B3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONF68F2890E96D4D85A1DEF0274D105BCE")) {
        
        printPageButtonF68F2890E96D4D85A1DEF0274D105BCE(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON29D17F515727436DBCE32BC6CA28382B")) {
        
        printPageButton29D17F515727436DBCE32BC6CA28382B(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONDE1B382FDD2540199D223586F6E216D0")) {
        
        printPageButtonDE1B382FDD2540199D223586F6E216D0(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND16966FBF9604A3D91A50DC83C6EA8E3")) {
        
        printPageButtonD16966FBF9604A3D91A50DC83C6EA8E3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF8080812E2F8EAE012E2F94CF470014")) {
        
        printPageButtonFF8080812E2F8EAE012E2F94CF470014(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF8080812F348A97012F349DC24F0007")) {
        
        printPageButtonFF8080812F348A97012F349DC24F0007(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONEFAFE78815E14788B36DB984F4CC8045")) {
        
        printPageButtonEFAFE78815E14788B36DB984F4CC8045(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON186BA5F122DD448E9A36B57E0115266C")) {
        
        printPageButton186BA5F122DD448E9A36B57E0115266C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON2979075A16BA4695ADE3B093725CA876")) {
        
        printPageButton2979075A16BA4695ADE3B093725CA876(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON977F0ED7770440D880A48ECBA456706D")) {
        
        printPageButton977F0ED7770440D880A48ECBA456706D(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON3022A5678A134AD28174B8A9B74B325A")) {
        
        printPageButton3022A5678A134AD28174B8A9B74B325A(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONEEA62DA04DF44D39B9BCA91BEAE81CED")) {
        
        printPageButtonEEA62DA04DF44D39B9BCA91BEAE81CED(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON01A90B910CAD4297A26C6E2594669DB4")) {
        
        printPageButton01A90B910CAD4297A26C6E2594669DB4(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONA9CBB36555F943ABB417ECD0EB1CC52E")) {
        
        printPageButtonA9CBB36555F943ABB417ECD0EB1CC52E(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON219F25ED92D74A40B3AB51973D01F629")) {
        
        printPageButton219F25ED92D74A40B3AB51973D01F629(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONADE9DCB32A484BA29817A385A292496C")) {
        
        printPageButtonADE9DCB32A484BA29817A385A292496C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON68EF40FAB3954363A809F460B81C0C99")) {
        
        printPageButton68EF40FAB3954363A809F460B81C0C99(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON8217C6BBED3740F381CA830CB6B67725")) {
        
        printPageButton8217C6BBED3740F381CA830CB6B67725(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON1DB34C54D0F2443593FAD5FA8197A14C")) {
        
        printPageButton1DB34C54D0F2443593FAD5FA8197A14C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON353F95EFE50146DD8A92EC5CD6B65154")) {
        
        printPageButton353F95EFE50146DD8A92EC5CD6B65154(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6DB70589347647CDB5C8409320947308")) {
        
        printPageButton6DB70589347647CDB5C8409320947308(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON8EB3A5AC31024491981EBD8D1AE23BFF")) {
        
        printPageButton8EB3A5AC31024491981EBD8D1AE23BFF(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB9415D7D6DB749DF89BD7DF32466D10B")) {
        
        printPageButtonB9415D7D6DB749DF89BD7DF32466D10B(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON542C02D5AC5A45338BA692E346D34D23")) {
        
        printPageButton542C02D5AC5A45338BA692E346D34D23(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONDAB5D8627EE5415E9FF2531998E303B6")) {
        
        printPageButtonDAB5D8627EE5415E9FF2531998E303B6(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONE0A6DA47391A4AFDBF738C7F17607DC0")) {
        
        printPageButtonE0A6DA47391A4AFDBF738C7F17607DC0(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON8D701FA22B894C5195C3E672661BF26D")) {
        
        printPageButton8D701FA22B894C5195C3E672661BF26D(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND54C07F4ACF1454B82CAB6C6DBED27E6")) {
        
        printPageButtonD54C07F4ACF1454B82CAB6C6DBED27E6(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON0637AC192E8C45FFA516DCA4A2FD2743")) {
        
        printPageButton0637AC192E8C45FFA516DCA4A2FD2743(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONF5C1F8C2F9B1420B8D12223585BC5798")) {
        
        printPageButtonF5C1F8C2F9B1420B8D12223585BC5798(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON02D6B2441A454C739E825963E3DA4ECD")) {
        
        printPageButton02D6B2441A454C739E825963E3DA4ECD(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON1445812B6F504A9B90BD1630A2A12BA1")) {
        
        printPageButton1445812B6F504A9B90BD1630A2A12BA1(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON20E70A9D32F644C0BFD3AA1C90566AB3")) {
        
        printPageButton20E70A9D32F644C0BFD3AA1C90566AB3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND39B25EADB224BB59E2A4F758A867D44")) {
        
        printPageButtonD39B25EADB224BB59E2A4F758A867D44(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON17B03F4D1F1E4184A8245B6168928C48")) {
        
        printPageButton17B03F4D1F1E4184A8245B6168928C48(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONE802BD1CEBEC4CA5BE4164A328951F61")) {
        
        printPageButtonE802BD1CEBEC4CA5BE4164A328951F61(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON8D6C70A85BCC4DF1AE626425FE6000F2")) {
        
        printPageButton8D6C70A85BCC4DF1AE626425FE6000F2(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON1C79D5FD586F4D2BA8A239A1E9397982")) {
        
        printPageButton1C79D5FD586F4D2BA8A239A1E9397982(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9456BD018F8C41D29E5E6908DD1E9958")) {
        
        printPageButton9456BD018F8C41D29E5E6908DD1E9958(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9FD873E4FCF14633804441249ED4F428")) {
        
        printPageButton9FD873E4FCF14633804441249ED4F428(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONAE0258D576C24072B07A0476B7571A6E")) {
        
        printPageButtonAE0258D576C24072B07A0476B7571A6E(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON74D8317FBB864522B766BDC6DDE8DD6C")) {
        
        printPageButton74D8317FBB864522B766BDC6DDE8DD6C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON7CDE00F02E2946FBB7557E1F14BF9EA1")) {
        
        printPageButton7CDE00F02E2946FBB7557E1F14BF9EA1(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON4ED98E07DD4B4B179982F71786D2A7A5")) {
        
        printPageButton4ED98E07DD4B4B179982F71786D2A7A5(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONCD8EA8F3D08B457CA39A56D4CAFD81AE")) {
        
        printPageButtonCD8EA8F3D08B457CA39A56D4CAFD81AE(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON63194D3CC6114581AA0FA46D3108BA37")) {
        
        printPageButton63194D3CC6114581AA0FA46D3108BA37(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONC0C01D365E174254891E71EC41B099ED")) {
        
        printPageButtonC0C01D365E174254891E71EC41B099ED(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONC19E8C0E72FC415488719F6181C3B5AD")) {
        
        printPageButtonC19E8C0E72FC415488719F6181C3B5AD(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON3A78D32DAFAE4C5EA98BD0A667213369")) {
        
        printPageButton3A78D32DAFAE4C5EA98BD0A667213369(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFA292F6205104423A80106ED7FD297CF")) {
        
        printPageButtonFA292F6205104423A80106ED7FD297CF(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9A1023B8C7B3483680A7780CB745817F")) {
        
        printPageButton9A1023B8C7B3483680A7780CB745817F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON25563A6B6BD24D0E9463D87E035BD3EA")) {
        
        printPageButton25563A6B6BD24D0E9463D87E035BD3EA(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONA4C0B645D60544E3836F437264A4DC79")) {
        
        printPageButtonA4C0B645D60544E3836F437264A4DC79(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON15C798457213481BA271CA63353EFE2F")) {
        
        printPageButton15C798457213481BA271CA63353EFE2F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON1292FC5D518D42758F593EDD4A746766")) {
        
        printPageButton1292FC5D518D42758F593EDD4A746766(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON173F6DB31A1643A985E46CA1F15DBD7A")) {
        
        printPageButton173F6DB31A1643A985E46CA1F15DBD7A(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB683365A463B48BABE7128017C80484B")) {
        
        printPageButtonB683365A463B48BABE7128017C80484B(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB33824E7922E42A2A456CAEDBA6830CA")) {
        
        printPageButtonB33824E7922E42A2A456CAEDBA6830CA(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON3ECC743F78A544B2BD119FBB02D05E97")) {
        
        printPageButton3ECC743F78A544B2BD119FBB02D05E97(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON20D40AFB59CF46D8A0F893D08E85911D")) {
        
        printPageButton20D40AFB59CF46D8A0F893D08E85911D(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONA36A615253064A95B09A9344C218FA74")) {
        
        printPageButtonA36A615253064A95B09A9344C218FA74(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON1454195479614FBFBB8F30755D118B4C")) {
        
        printPageButton1454195479614FBFBB8F30755D118B4C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFFD63BDDDDD94E2991D2AEB30E5E3FDB")) {
        
        printPageButtonFFD63BDDDDD94E2991D2AEB30E5E3FDB(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON5E92917708EF426D9697D88350DCBF14")) {
        
        printPageButton5E92917708EF426D9697D88350DCBF14(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONE865F018CEC7402DB06DB93C6DE30B7F")) {
        
        printPageButtonE865F018CEC7402DB06DB93C6DE30B7F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON8F8FAACEEF9D449C909758519FBD0760")) {
        
        printPageButton8F8FAACEEF9D449C909758519FBD0760(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONED4FB9D909484DB28578C5808CACA796")) {
        
        printPageButtonED4FB9D909484DB28578C5808CACA796(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB76A77639E4B4DAAB097AD11DAE7A1AA")) {
        
        printPageButtonB76A77639E4B4DAAB097AD11DAE7A1AA(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON91ABA870C21D44B1B35717F42AB28F27")) {
        
        printPageButton91ABA870C21D44B1B35717F42AB28F27(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONF20F5B75B85E45B997F3853D5B7607C7")) {
        
        printPageButtonF20F5B75B85E45B997F3853D5B7607C7(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON2CC36FE2C6974AEC87D1FB0930674F51")) {
        
        printPageButton2CC36FE2C6974AEC87D1FB0930674F51(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON11F05217E6C84C3EACB57167B91BA11A")) {
        
        printPageButton11F05217E6C84C3EACB57167B91BA11A(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON1C6BC6797AAE4AA6ABB1DD98BF41F053")) {
        
        printPageButton1C6BC6797AAE4AA6ABB1DD98BF41F053(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONDEC765A7E71445ABB8C7DBDBFB735A13")) {
        
        printPageButtonDEC765A7E71445ABB8C7DBDBFB735A13(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON8E00D1574F384B4A9D95F3ED022F0A47")) {
        
        printPageButton8E00D1574F384B4A9D95F3ED022F0A47(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONBD1693ECFA6944AB86488F6D12E423E4")) {
        
        printPageButtonBD1693ECFA6944AB86488F6D12E423E4(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON1AD75B16E2884E258F3B8EF51039FA49")) {
        
        printPageButton1AD75B16E2884E258F3B8EF51039FA49(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON93876A2A71E14EB7AFEAF1D05F60F832")) {
        
        printPageButton93876A2A71E14EB7AFEAF1D05F60F832(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON80BD458482C842F39419D7D9B9EF00F0")) {
        
        printPageButton80BD458482C842F39419D7D9B9EF00F0(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6EEBD3ADA8D945C8B614C641556CEEAD")) {
        
        printPageButton6EEBD3ADA8D945C8B614C641556CEEAD(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON30A9E010447D4B7A983D4DF2EE4CA314")) {
        
        printPageButton30A9E010447D4B7A983D4DF2EE4CA314(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONC545A77357BB4FD69BB6C8646A3D9AF2")) {
        
        printPageButtonC545A77357BB4FD69BB6C8646A3D9AF2(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON4FD494B6B9C5477FAD6E7AD6F35EB1D4")) {
        
        printPageButton4FD494B6B9C5477FAD6E7AD6F35EB1D4(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONFF1BC80474C0403A98A5E6BCACF6CB3F")) {
        
        printPageButtonFF1BC80474C0403A98A5E6BCACF6CB3F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONF79126E740EA4C6799CC28D3526D016E")) {
        
        printPageButtonF79126E740EA4C6799CC28D3526D016E(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONA49580DFBA994334957B0E398916E97F")) {
        
        printPageButtonA49580DFBA994334957B0E398916E97F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON1A0C98271D5F480B98874D9AFDB92EA6")) {
        
        printPageButton1A0C98271D5F480B98874D9AFDB92EA6(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONA5AB0D40FD7742388B7B17AF8282D2C0")) {
        
        printPageButtonA5AB0D40FD7742388B7B17AF8282D2C0(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON0630AAC03F3A4EA7BDE267AD12637E7F")) {
        
        printPageButton0630AAC03F3A4EA7BDE267AD12637E7F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND189003BD6AF4978B9A8506CB1B843B6")) {
        
        printPageButtonD189003BD6AF4978B9A8506CB1B843B6(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON26EF860F5FDA4C6B885445D0B381BBE1")) {
        
        printPageButton26EF860F5FDA4C6B885445D0B381BBE1(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON2ADA7794EC6B4D159060073A62C550A8")) {
        
        printPageButton2ADA7794EC6B4D159060073A62C550A8(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON8402E2D41EBB45AA8EA418B6F0FCF4DB")) {
        
        printPageButton8402E2D41EBB45AA8EA418B6F0FCF4DB(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONE038F781230D4505AAADDE1EBFD2573E")) {
        
        printPageButtonE038F781230D4505AAADDE1EBFD2573E(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON309A0C522F8B497FB2FC4174C6EE532E")) {
        
        printPageButton309A0C522F8B497FB2FC4174C6EE532E(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONBF4B81BF72F740E7AE729E07D3FB0663")) {
        
        printPageButtonBF4B81BF72F740E7AE729E07D3FB0663(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONADBF99D438B24F299A860D1535AE864A")) {
        
        printPageButtonADBF99D438B24F299A860D1535AE864A(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON84143CAAADE74274B0560AFDE28E7156")) {
        
        printPageButton84143CAAADE74274B0560AFDE28E7156(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON921B8092DE21435192CB8B7C6F5E6961")) {
        
        printPageButton921B8092DE21435192CB8B7C6F5E6961(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB9F945AFAC0A4FEBBF5B0DCF6420C70B")) {
        
        printPageButtonB9F945AFAC0A4FEBBF5B0DCF6420C70B(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB198D7B384724E6BB9A30C54B85361AF")) {
        
        printPageButtonB198D7B384724E6BB9A30C54B85361AF(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTOND7F2D0AE360441B59787E99ED1EFBCC2")) {
        
        printPageButtonD7F2D0AE360441B59787E99ED1EFBCC2(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9E8E9A0881C640A88F0846A961FA6D47")) {
        
        printPageButton9E8E9A0881C640A88F0846A961FA6D47(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON0474D1A57C294E2BABC306A268DFCCC7")) {
        
        printPageButton0474D1A57C294E2BABC306A268DFCCC7(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONF78498E7CB2D442396D26954D97A47D7")) {
        
        printPageButtonF78498E7CB2D442396D26954D97A47D7(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON95DACEED13B34B0280F0740265B38EBB")) {
        
        printPageButton95DACEED13B34B0280F0740265B38EBB(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON2B6ACF25EAFA473D8A5F5F8DCACCDCBC")) {
        
        printPageButton2B6ACF25EAFA473D8A5F5F8DCACCDCBC(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONB31EE95C6F7448848814EDD327E0DC6F")) {
        
        printPageButtonB31EE95C6F7448848814EDD327E0DC6F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON7D846A9CEACA46339BAA5C6A398FD24A")) {
        
        printPageButton7D846A9CEACA46339BAA5C6A398FD24A(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON89AC556D92D84B4FA8598B5D1D6228B6")) {
        
        printPageButton89AC556D92D84B4FA8598B5D1D6228B6(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON186094D8A8E54C0E915072C90186402A")) {
        
        printPageButton186094D8A8E54C0E915072C90186402A(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON47B1DA21D6DB416FBADDB45F09B74080")) {
        
        printPageButton47B1DA21D6DB416FBADDB45F09B74080(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTONC4C54930A2AF46238FF38EF7DA17AF18")) {
        
        printPageButtonC4C54930A2AF46238FF38EF7DA17AF18(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON18F868C1BF9D4F0AB85657C268C84F25")) {
        
        printPageButton18F868C1BF9D4F0AB85657C268C84F25(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON599A7B3AD5334EAFA3DE7A5870517C57")) {
        
        printPageButton599A7B3AD5334EAFA3DE7A5870517C57(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON4D101CF39F0849C891017AB942B9A8B4")) {
        
        printPageButton4D101CF39F0849C891017AB942B9A8B4(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON24C4E55795B741059C4C53C2E2D51CB3")) {
        
        printPageButton24C4E55795B741059C4C53C2E2D51CB3(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON194F2109CB5A4A4E9512FFE9405763AF")) {
        
        printPageButton194F2109CB5A4A4E9512FFE9405763AF(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON50DAA0B5A6954884BF3A028718E8399C")) {
        
        printPageButton50DAA0B5A6954884BF3A028718E8399C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON9C911E091D534F1FA069ECE6E3F0C68C")) {
        
        printPageButton9C911E091D534F1FA069ECE6E3F0C68C(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON2568DC178C5541E4B6194E0C7865F8F1")) {
        
        printPageButton2568DC178C5541E4B6194E0C7865F8F1(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON3078AEC339FA4933900C427A3C5D3759")) {
        
        printPageButton3078AEC339FA4933900C427A3C5D3759(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON3BDC78E8DCB14705B2F74B04AA6C56C2")) {
        
        printPageButton3BDC78E8DCB14705B2F74B04AA6C56C2(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON61711504BCDE4690995B0A938D3BA9D6")) {
        
        printPageButton61711504BCDE4690995B0A938D3BA9D6(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON6E87C327B7C94CA78C2B43B0C40CD74F")) {
        
        printPageButton6E87C327B7C94CA78C2B43B0C40CD74F(response, vars, strProcessId);
    } else if (vars.commandIn("BUTTON096AA9DB1D854D3D875CAB362E6A3CB8")) {
        
        printPageButton096AA9DB1D854D3D875CAB362E6A3CB8(response, vars, strProcessId);

    } else if (vars.commandIn("SAVE_BUTTONActionButton9DB4D30BFC5144B9B431CB49DDE9270D")) {
        process9DB4D30BFC5144B9B431CB49DDE9270D(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton7CB6B4D1ECCF4036B3F111D2CF11AADE")) {
        process7CB6B4D1ECCF4036B3F111D2CF11AADE(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton970EAD9B846648A7AB1F0CCA5058356C")) {
        process970EAD9B846648A7AB1F0CCA5058356C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton58763832F5F3485CAD33B8B9FCD6C640")) {
        process58763832F5F3485CAD33B8B9FCD6C640(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton7EDBFEC35BDA4FF4AF05ED516CDAFB90")) {
        process7EDBFEC35BDA4FF4AF05ED516CDAFB90(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonABDFC8131D964936AD2EF7E0CED97FD9")) {
        processABDFC8131D964936AD2EF7E0CED97FD9(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonEFDBF909811544DAAE4E876AA781E5DC")) {
        processEFDBF909811544DAAE4E876AA781E5DC(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonA269DCA4DE114E438695B66E166999B4")) {
        processA269DCA4DE114E438695B66E166999B4(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton107")) {
        process107(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonCD7283DF804B449C97DA09446669EEEF")) {
        processCD7283DF804B449C97DA09446669EEEF(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton85601427EAEE401FA0250FF0A6DD62EF")) {
        process85601427EAEE401FA0250FF0A6DD62EF(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonA3FE1F9892394386A49FB707AA50A0FA")) {
        processA3FE1F9892394386A49FB707AA50A0FA(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton136")) {
        process136(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFB740AB61B0E42B198D2C88D3A0D0CE6")) {
        processFB740AB61B0E42B198D2C88D3A0D0CE6(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton64B3FF29AC174F4B94538BD0A3CE1CD3")) {
        process64B3FF29AC174F4B94538BD0A3CE1CD3(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonE264309FF8244A94936502BF51829109")) {
        processE264309FF8244A94936502BF51829109(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton23D1B163EC0B41F790CE39BF01DA320E")) {
        process23D1B163EC0B41F790CE39BF01DA320E(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton6FBD65B0FDB74D1AB07F0EADF18D48AE")) {
        process6FBD65B0FDB74D1AB07F0EADF18D48AE(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton9EB2228A60684C0DBEC12D5CD8D85218")) {
        process9EB2228A60684C0DBEC12D5CD8D85218(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD85D5B5E368A49B1A6293BA4AE15F0F9")) {
        processD85D5B5E368A49B1A6293BA4AE15F0F9(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF80808133362F6A013336781FCE0066")) {
        processFF80808133362F6A013336781FCE0066(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF8081813219E68E013219ECFE930004")) {
        processFF8081813219E68E013219ECFE930004(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF808181324D007801324D2AE1130066")) {
        processFF808181324D007801324D2AE1130066(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF808181326CD80501326CE906D70042")) {
        processFF808181326CD80501326CE906D70042(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF80818132A4F6AD0132A573DD7A0021")) {
        processFF80818132A4F6AD0132A573DD7A0021(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton0BDC2164ED3E48539FCEF4D306F29EFD")) {
        process0BDC2164ED3E48539FCEF4D306F29EFD(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton5BE14AA10165490A9ADEFB7532F7FA94")) {
        process5BE14AA10165490A9ADEFB7532F7FA94(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton58A9261BACEF45DDA526F29D8557272D")) {
        process58A9261BACEF45DDA526F29D8557272D(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton15C8708DFC464C2D91286E59624FDD18")) {
        process15C8708DFC464C2D91286E59624FDD18(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton017312F51139438A9665775E3B5392A1")) {
        process017312F51139438A9665775E3B5392A1(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton6255BE488882480599C81284B70CD9B3")) {
        process6255BE488882480599C81284B70CD9B3(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonF68F2890E96D4D85A1DEF0274D105BCE")) {
        processF68F2890E96D4D85A1DEF0274D105BCE(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton29D17F515727436DBCE32BC6CA28382B")) {
        process29D17F515727436DBCE32BC6CA28382B(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonDE1B382FDD2540199D223586F6E216D0")) {
        processDE1B382FDD2540199D223586F6E216D0(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD16966FBF9604A3D91A50DC83C6EA8E3")) {
        processD16966FBF9604A3D91A50DC83C6EA8E3(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF8080812E2F8EAE012E2F94CF470014")) {
        processFF8080812E2F8EAE012E2F94CF470014(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF8080812F348A97012F349DC24F0007")) {
        processFF8080812F348A97012F349DC24F0007(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonEFAFE78815E14788B36DB984F4CC8045")) {
        processEFAFE78815E14788B36DB984F4CC8045(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton186BA5F122DD448E9A36B57E0115266C")) {
        process186BA5F122DD448E9A36B57E0115266C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton2979075A16BA4695ADE3B093725CA876")) {
        process2979075A16BA4695ADE3B093725CA876(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton977F0ED7770440D880A48ECBA456706D")) {
        process977F0ED7770440D880A48ECBA456706D(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton3022A5678A134AD28174B8A9B74B325A")) {
        process3022A5678A134AD28174B8A9B74B325A(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonEEA62DA04DF44D39B9BCA91BEAE81CED")) {
        processEEA62DA04DF44D39B9BCA91BEAE81CED(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton01A90B910CAD4297A26C6E2594669DB4")) {
        process01A90B910CAD4297A26C6E2594669DB4(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonA9CBB36555F943ABB417ECD0EB1CC52E")) {
        processA9CBB36555F943ABB417ECD0EB1CC52E(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton219F25ED92D74A40B3AB51973D01F629")) {
        process219F25ED92D74A40B3AB51973D01F629(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonADE9DCB32A484BA29817A385A292496C")) {
        processADE9DCB32A484BA29817A385A292496C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton68EF40FAB3954363A809F460B81C0C99")) {
        process68EF40FAB3954363A809F460B81C0C99(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton8217C6BBED3740F381CA830CB6B67725")) {
        process8217C6BBED3740F381CA830CB6B67725(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton1DB34C54D0F2443593FAD5FA8197A14C")) {
        process1DB34C54D0F2443593FAD5FA8197A14C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton353F95EFE50146DD8A92EC5CD6B65154")) {
        process353F95EFE50146DD8A92EC5CD6B65154(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton6DB70589347647CDB5C8409320947308")) {
        process6DB70589347647CDB5C8409320947308(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton8EB3A5AC31024491981EBD8D1AE23BFF")) {
        process8EB3A5AC31024491981EBD8D1AE23BFF(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB9415D7D6DB749DF89BD7DF32466D10B")) {
        processB9415D7D6DB749DF89BD7DF32466D10B(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton542C02D5AC5A45338BA692E346D34D23")) {
        process542C02D5AC5A45338BA692E346D34D23(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonDAB5D8627EE5415E9FF2531998E303B6")) {
        processDAB5D8627EE5415E9FF2531998E303B6(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonE0A6DA47391A4AFDBF738C7F17607DC0")) {
        processE0A6DA47391A4AFDBF738C7F17607DC0(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton8D701FA22B894C5195C3E672661BF26D")) {
        process8D701FA22B894C5195C3E672661BF26D(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD54C07F4ACF1454B82CAB6C6DBED27E6")) {
        processD54C07F4ACF1454B82CAB6C6DBED27E6(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton0637AC192E8C45FFA516DCA4A2FD2743")) {
        process0637AC192E8C45FFA516DCA4A2FD2743(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonF5C1F8C2F9B1420B8D12223585BC5798")) {
        processF5C1F8C2F9B1420B8D12223585BC5798(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton02D6B2441A454C739E825963E3DA4ECD")) {
        process02D6B2441A454C739E825963E3DA4ECD(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton1445812B6F504A9B90BD1630A2A12BA1")) {
        process1445812B6F504A9B90BD1630A2A12BA1(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton20E70A9D32F644C0BFD3AA1C90566AB3")) {
        process20E70A9D32F644C0BFD3AA1C90566AB3(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD39B25EADB224BB59E2A4F758A867D44")) {
        processD39B25EADB224BB59E2A4F758A867D44(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton17B03F4D1F1E4184A8245B6168928C48")) {
        process17B03F4D1F1E4184A8245B6168928C48(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonE802BD1CEBEC4CA5BE4164A328951F61")) {
        processE802BD1CEBEC4CA5BE4164A328951F61(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton8D6C70A85BCC4DF1AE626425FE6000F2")) {
        process8D6C70A85BCC4DF1AE626425FE6000F2(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton1C79D5FD586F4D2BA8A239A1E9397982")) {
        process1C79D5FD586F4D2BA8A239A1E9397982(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton9456BD018F8C41D29E5E6908DD1E9958")) {
        process9456BD018F8C41D29E5E6908DD1E9958(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton9FD873E4FCF14633804441249ED4F428")) {
        process9FD873E4FCF14633804441249ED4F428(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonAE0258D576C24072B07A0476B7571A6E")) {
        processAE0258D576C24072B07A0476B7571A6E(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton74D8317FBB864522B766BDC6DDE8DD6C")) {
        process74D8317FBB864522B766BDC6DDE8DD6C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton7CDE00F02E2946FBB7557E1F14BF9EA1")) {
        process7CDE00F02E2946FBB7557E1F14BF9EA1(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton4ED98E07DD4B4B179982F71786D2A7A5")) {
        process4ED98E07DD4B4B179982F71786D2A7A5(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonCD8EA8F3D08B457CA39A56D4CAFD81AE")) {
        processCD8EA8F3D08B457CA39A56D4CAFD81AE(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton63194D3CC6114581AA0FA46D3108BA37")) {
        process63194D3CC6114581AA0FA46D3108BA37(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonC0C01D365E174254891E71EC41B099ED")) {
        processC0C01D365E174254891E71EC41B099ED(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonC19E8C0E72FC415488719F6181C3B5AD")) {
        processC19E8C0E72FC415488719F6181C3B5AD(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton3A78D32DAFAE4C5EA98BD0A667213369")) {
        process3A78D32DAFAE4C5EA98BD0A667213369(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFA292F6205104423A80106ED7FD297CF")) {
        processFA292F6205104423A80106ED7FD297CF(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton9A1023B8C7B3483680A7780CB745817F")) {
        process9A1023B8C7B3483680A7780CB745817F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton25563A6B6BD24D0E9463D87E035BD3EA")) {
        process25563A6B6BD24D0E9463D87E035BD3EA(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonA4C0B645D60544E3836F437264A4DC79")) {
        processA4C0B645D60544E3836F437264A4DC79(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton15C798457213481BA271CA63353EFE2F")) {
        process15C798457213481BA271CA63353EFE2F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton1292FC5D518D42758F593EDD4A746766")) {
        process1292FC5D518D42758F593EDD4A746766(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton173F6DB31A1643A985E46CA1F15DBD7A")) {
        process173F6DB31A1643A985E46CA1F15DBD7A(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB683365A463B48BABE7128017C80484B")) {
        processB683365A463B48BABE7128017C80484B(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB33824E7922E42A2A456CAEDBA6830CA")) {
        processB33824E7922E42A2A456CAEDBA6830CA(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton3ECC743F78A544B2BD119FBB02D05E97")) {
        process3ECC743F78A544B2BD119FBB02D05E97(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton20D40AFB59CF46D8A0F893D08E85911D")) {
        process20D40AFB59CF46D8A0F893D08E85911D(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonA36A615253064A95B09A9344C218FA74")) {
        processA36A615253064A95B09A9344C218FA74(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton1454195479614FBFBB8F30755D118B4C")) {
        process1454195479614FBFBB8F30755D118B4C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFFD63BDDDDD94E2991D2AEB30E5E3FDB")) {
        processFFD63BDDDDD94E2991D2AEB30E5E3FDB(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton5E92917708EF426D9697D88350DCBF14")) {
        process5E92917708EF426D9697D88350DCBF14(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonE865F018CEC7402DB06DB93C6DE30B7F")) {
        processE865F018CEC7402DB06DB93C6DE30B7F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton8F8FAACEEF9D449C909758519FBD0760")) {
        process8F8FAACEEF9D449C909758519FBD0760(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonED4FB9D909484DB28578C5808CACA796")) {
        processED4FB9D909484DB28578C5808CACA796(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB76A77639E4B4DAAB097AD11DAE7A1AA")) {
        processB76A77639E4B4DAAB097AD11DAE7A1AA(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton91ABA870C21D44B1B35717F42AB28F27")) {
        process91ABA870C21D44B1B35717F42AB28F27(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonF20F5B75B85E45B997F3853D5B7607C7")) {
        processF20F5B75B85E45B997F3853D5B7607C7(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton2CC36FE2C6974AEC87D1FB0930674F51")) {
        process2CC36FE2C6974AEC87D1FB0930674F51(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton11F05217E6C84C3EACB57167B91BA11A")) {
        process11F05217E6C84C3EACB57167B91BA11A(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton1C6BC6797AAE4AA6ABB1DD98BF41F053")) {
        process1C6BC6797AAE4AA6ABB1DD98BF41F053(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonDEC765A7E71445ABB8C7DBDBFB735A13")) {
        processDEC765A7E71445ABB8C7DBDBFB735A13(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton8E00D1574F384B4A9D95F3ED022F0A47")) {
        process8E00D1574F384B4A9D95F3ED022F0A47(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonBD1693ECFA6944AB86488F6D12E423E4")) {
        processBD1693ECFA6944AB86488F6D12E423E4(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton1AD75B16E2884E258F3B8EF51039FA49")) {
        process1AD75B16E2884E258F3B8EF51039FA49(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton93876A2A71E14EB7AFEAF1D05F60F832")) {
        process93876A2A71E14EB7AFEAF1D05F60F832(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton80BD458482C842F39419D7D9B9EF00F0")) {
        process80BD458482C842F39419D7D9B9EF00F0(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton6EEBD3ADA8D945C8B614C641556CEEAD")) {
        process6EEBD3ADA8D945C8B614C641556CEEAD(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton30A9E010447D4B7A983D4DF2EE4CA314")) {
        process30A9E010447D4B7A983D4DF2EE4CA314(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonC545A77357BB4FD69BB6C8646A3D9AF2")) {
        processC545A77357BB4FD69BB6C8646A3D9AF2(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton4FD494B6B9C5477FAD6E7AD6F35EB1D4")) {
        process4FD494B6B9C5477FAD6E7AD6F35EB1D4(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonFF1BC80474C0403A98A5E6BCACF6CB3F")) {
        processFF1BC80474C0403A98A5E6BCACF6CB3F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonF79126E740EA4C6799CC28D3526D016E")) {
        processF79126E740EA4C6799CC28D3526D016E(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonA49580DFBA994334957B0E398916E97F")) {
        processA49580DFBA994334957B0E398916E97F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton1A0C98271D5F480B98874D9AFDB92EA6")) {
        process1A0C98271D5F480B98874D9AFDB92EA6(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonA5AB0D40FD7742388B7B17AF8282D2C0")) {
        processA5AB0D40FD7742388B7B17AF8282D2C0(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton0630AAC03F3A4EA7BDE267AD12637E7F")) {
        process0630AAC03F3A4EA7BDE267AD12637E7F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD189003BD6AF4978B9A8506CB1B843B6")) {
        processD189003BD6AF4978B9A8506CB1B843B6(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton26EF860F5FDA4C6B885445D0B381BBE1")) {
        process26EF860F5FDA4C6B885445D0B381BBE1(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton2ADA7794EC6B4D159060073A62C550A8")) {
        process2ADA7794EC6B4D159060073A62C550A8(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton8402E2D41EBB45AA8EA418B6F0FCF4DB")) {
        process8402E2D41EBB45AA8EA418B6F0FCF4DB(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonE038F781230D4505AAADDE1EBFD2573E")) {
        processE038F781230D4505AAADDE1EBFD2573E(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton309A0C522F8B497FB2FC4174C6EE532E")) {
        process309A0C522F8B497FB2FC4174C6EE532E(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonBF4B81BF72F740E7AE729E07D3FB0663")) {
        processBF4B81BF72F740E7AE729E07D3FB0663(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonADBF99D438B24F299A860D1535AE864A")) {
        processADBF99D438B24F299A860D1535AE864A(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton84143CAAADE74274B0560AFDE28E7156")) {
        process84143CAAADE74274B0560AFDE28E7156(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton921B8092DE21435192CB8B7C6F5E6961")) {
        process921B8092DE21435192CB8B7C6F5E6961(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB9F945AFAC0A4FEBBF5B0DCF6420C70B")) {
        processB9F945AFAC0A4FEBBF5B0DCF6420C70B(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB198D7B384724E6BB9A30C54B85361AF")) {
        processB198D7B384724E6BB9A30C54B85361AF(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonD7F2D0AE360441B59787E99ED1EFBCC2")) {
        processD7F2D0AE360441B59787E99ED1EFBCC2(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton9E8E9A0881C640A88F0846A961FA6D47")) {
        process9E8E9A0881C640A88F0846A961FA6D47(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton0474D1A57C294E2BABC306A268DFCCC7")) {
        process0474D1A57C294E2BABC306A268DFCCC7(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonF78498E7CB2D442396D26954D97A47D7")) {
        processF78498E7CB2D442396D26954D97A47D7(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton95DACEED13B34B0280F0740265B38EBB")) {
        process95DACEED13B34B0280F0740265B38EBB(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton2B6ACF25EAFA473D8A5F5F8DCACCDCBC")) {
        process2B6ACF25EAFA473D8A5F5F8DCACCDCBC(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonB31EE95C6F7448848814EDD327E0DC6F")) {
        processB31EE95C6F7448848814EDD327E0DC6F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton7D846A9CEACA46339BAA5C6A398FD24A")) {
        process7D846A9CEACA46339BAA5C6A398FD24A(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton89AC556D92D84B4FA8598B5D1D6228B6")) {
        process89AC556D92D84B4FA8598B5D1D6228B6(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton186094D8A8E54C0E915072C90186402A")) {
        process186094D8A8E54C0E915072C90186402A(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton47B1DA21D6DB416FBADDB45F09B74080")) {
        process47B1DA21D6DB416FBADDB45F09B74080(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButtonC4C54930A2AF46238FF38EF7DA17AF18")) {
        processC4C54930A2AF46238FF38EF7DA17AF18(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton18F868C1BF9D4F0AB85657C268C84F25")) {
        process18F868C1BF9D4F0AB85657C268C84F25(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton599A7B3AD5334EAFA3DE7A5870517C57")) {
        process599A7B3AD5334EAFA3DE7A5870517C57(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton4D101CF39F0849C891017AB942B9A8B4")) {
        process4D101CF39F0849C891017AB942B9A8B4(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton24C4E55795B741059C4C53C2E2D51CB3")) {
        process24C4E55795B741059C4C53C2E2D51CB3(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton194F2109CB5A4A4E9512FFE9405763AF")) {
        process194F2109CB5A4A4E9512FFE9405763AF(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton50DAA0B5A6954884BF3A028718E8399C")) {
        process50DAA0B5A6954884BF3A028718E8399C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton9C911E091D534F1FA069ECE6E3F0C68C")) {
        process9C911E091D534F1FA069ECE6E3F0C68C(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton2568DC178C5541E4B6194E0C7865F8F1")) {
        process2568DC178C5541E4B6194E0C7865F8F1(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton3078AEC339FA4933900C427A3C5D3759")) {
        process3078AEC339FA4933900C427A3C5D3759(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton3BDC78E8DCB14705B2F74B04AA6C56C2")) {
        process3BDC78E8DCB14705B2F74B04AA6C56C2(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton61711504BCDE4690995B0A938D3BA9D6")) {
        process61711504BCDE4690995B0A938D3BA9D6(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton6E87C327B7C94CA78C2B43B0C40CD74F")) {
        process6E87C327B7C94CA78C2B43B0C40CD74F(strProcessId, vars, request, response);
    } else if (vars.commandIn("SAVE_BUTTONActionButton096AA9DB1D854D3D875CAB362E6A3CB8")) {
        process096AA9DB1D854D3D875CAB362E6A3CB8(strProcessId, vars, request, response);

    } else pageErrorPopUp(response);
  }
  
  void printPageFrames(HttpServletResponse response, VariablesSecureApp vars, String strProcessId) throws IOException, ServletException {
    log4j.debug("Output: Default");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDefaultFrames").createXmlDocument();
    xmlDocument.setParameter("processId", strProcessId);
    xmlDocument.setParameter("trlFormType", "PROCESS");
    xmlDocument.setParameter("type", "ActionButtonJava_Responser.html");
    xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
    out.println(xmlDocument.print());
    out.close();
  }

  void printPageDefault(HttpServletResponse response, VariablesSecureApp vars, String strProcessId) throws IOException, ServletException {
    log4j.debug("Output: Default");
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDefault").createXmlDocument();
    xmlDocument.setParameter("processId", strProcessId);
	  xmlDocument.setParameter("trlFormType", "PROCESS");
	  xmlDocument.setParameter("type", "ActionButtonJava_Responser.html");
	  xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
    out.println(xmlDocument.print());
    out.close();
  }

    void printPageButton9DB4D30BFC5144B9B431CB49DDE9270D(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9DB4D30BFC5144B9B431CB49DDE9270D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9DB4D30BFC5144B9B431CB49DDE9270D", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("9DB4D30BFC5144B9B431CB49DDE9270D");
        vars.removeMessage("9DB4D30BFC5144B9B431CB49DDE9270D");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton7CB6B4D1ECCF4036B3F111D2CF11AADE(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7CB6B4D1ECCF4036B3F111D2CF11AADE");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton7CB6B4D1ECCF4036B3F111D2CF11AADE", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("7CB6B4D1ECCF4036B3F111D2CF11AADE");
        vars.removeMessage("7CB6B4D1ECCF4036B3F111D2CF11AADE");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("M_Warehouse_ID", Utility.getContext(this, vars, "#M_Warehouse_ID", windowId));
    comboTableData = new ComboTableData(vars, this, "19", "M_Warehouse_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "#M_Warehouse_ID", windowId));
    xmlDocument.setData("reportM_Warehouse_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton970EAD9B846648A7AB1F0CCA5058356C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 970EAD9B846648A7AB1F0CCA5058356C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton970EAD9B846648A7AB1F0CCA5058356C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("970EAD9B846648A7AB1F0CCA5058356C");
        vars.removeMessage("970EAD9B846648A7AB1F0CCA5058356C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("Name", "");
    xmlDocument.setParameter("ImportAuditInfo", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton58763832F5F3485CAD33B8B9FCD6C640(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 58763832F5F3485CAD33B8B9FCD6C640");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton58763832F5F3485CAD33B8B9FCD6C640", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("58763832F5F3485CAD33B8B9FCD6C640");
        vars.removeMessage("58763832F5F3485CAD33B8B9FCD6C640");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton7EDBFEC35BDA4FF4AF05ED516CDAFB90(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7EDBFEC35BDA4FF4AF05ED516CDAFB90");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton7EDBFEC35BDA4FF4AF05ED516CDAFB90", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("7EDBFEC35BDA4FF4AF05ED516CDAFB90");
        vars.removeMessage("7EDBFEC35BDA4FF4AF05ED516CDAFB90");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonABDFC8131D964936AD2EF7E0CED97FD9(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process ABDFC8131D964936AD2EF7E0CED97FD9");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonABDFC8131D964936AD2EF7E0CED97FD9", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("ABDFC8131D964936AD2EF7E0CED97FD9");
        vars.removeMessage("ABDFC8131D964936AD2EF7E0CED97FD9");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonEFDBF909811544DAAE4E876AA781E5DC(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process EFDBF909811544DAAE4E876AA781E5DC");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonEFDBF909811544DAAE4E876AA781E5DC", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("EFDBF909811544DAAE4E876AA781E5DC");
        vars.removeMessage("EFDBF909811544DAAE4E876AA781E5DC");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonA269DCA4DE114E438695B66E166999B4(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A269DCA4DE114E438695B66E166999B4");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonA269DCA4DE114E438695B66E166999B4", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("A269DCA4DE114E438695B66E166999B4");
        vars.removeMessage("A269DCA4DE114E438695B66E166999B4");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton107(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 107");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton107", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("107");
        vars.removeMessage("107");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonCD7283DF804B449C97DA09446669EEEF(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process CD7283DF804B449C97DA09446669EEEF");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonCD7283DF804B449C97DA09446669EEEF", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("CD7283DF804B449C97DA09446669EEEF");
        vars.removeMessage("CD7283DF804B449C97DA09446669EEEF");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton85601427EAEE401FA0250FF0A6DD62EF(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 85601427EAEE401FA0250FF0A6DD62EF");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton85601427EAEE401FA0250FF0A6DD62EF", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("85601427EAEE401FA0250FF0A6DD62EF");
        vars.removeMessage("85601427EAEE401FA0250FF0A6DD62EF");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonA3FE1F9892394386A49FB707AA50A0FA(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A3FE1F9892394386A49FB707AA50A0FA");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonA3FE1F9892394386A49FB707AA50A0FA", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("A3FE1F9892394386A49FB707AA50A0FA");
        vars.removeMessage("A3FE1F9892394386A49FB707AA50A0FA");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("RecalculatePrices", "Y");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton136(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 136");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton136", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("136");
        vars.removeMessage("136");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFB740AB61B0E42B198D2C88D3A0D0CE6(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FB740AB61B0E42B198D2C88D3A0D0CE6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFB740AB61B0E42B198D2C88D3A0D0CE6", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FB740AB61B0E42B198D2C88D3A0D0CE6");
        vars.removeMessage("FB740AB61B0E42B198D2C88D3A0D0CE6");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("DueDate", Utility.getContext(this, vars, "Duedate", ""));
    xmlDocument.setParameter("DueDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("FIN_Payment_Priority_ID", Utility.getContext(this, vars, "FIN_Payment_Priority_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "FIN_Payment_Priority_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "FIN_Payment_Priority_ID", ""));
    xmlDocument.setData("reportFIN_Payment_Priority_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton64B3FF29AC174F4B94538BD0A3CE1CD3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 64B3FF29AC174F4B94538BD0A3CE1CD3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton64B3FF29AC174F4B94538BD0A3CE1CD3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("64B3FF29AC174F4B94538BD0A3CE1CD3");
        vars.removeMessage("64B3FF29AC174F4B94538BD0A3CE1CD3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonE264309FF8244A94936502BF51829109(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E264309FF8244A94936502BF51829109");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonE264309FF8244A94936502BF51829109", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("E264309FF8244A94936502BF51829109");
        vars.removeMessage("E264309FF8244A94936502BF51829109");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Client_ID", Utility.getContext(this, vars, "AD_Client_ID", ""));
    comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "103", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "AD_Client_ID", ""));
    xmlDocument.setData("reportAD_Client_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("AD_Org_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "AD_Org_ID", "", "4A2C613871134B76899BA0464F3CBF76", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Org_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("AD_Table_ID", "");
    comboTableData = new ComboTableData(vars, this, "18", "AD_Table_ID", "800022", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Table_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("DeletePosting", "");
    xmlDocument.setParameter("datefrom", "");
    xmlDocument.setParameter("datefrom_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("dateto", "");
    xmlDocument.setParameter("dateto_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton23D1B163EC0B41F790CE39BF01DA320E(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 23D1B163EC0B41F790CE39BF01DA320E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton23D1B163EC0B41F790CE39BF01DA320E", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("23D1B163EC0B41F790CE39BF01DA320E");
        vars.removeMessage("23D1B163EC0B41F790CE39BF01DA320E");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("M_Product_ID", "");
    xmlDocument.setParameter("Returned", "");
    xmlDocument.setParameter("PriceStd", "");
    xmlDocument.setParameter("C_Tax_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Tax_ID", "", "299FA667CF374AC5ACC74739C3251134", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Tax_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("C_Return_Reason_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "C_Return_Reason_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportC_Return_Reason_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6FBD65B0FDB74D1AB07F0EADF18D48AE(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6FBD65B0FDB74D1AB07F0EADF18D48AE");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6FBD65B0FDB74D1AB07F0EADF18D48AE", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6FBD65B0FDB74D1AB07F0EADF18D48AE");
        vars.removeMessage("6FBD65B0FDB74D1AB07F0EADF18D48AE");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton9EB2228A60684C0DBEC12D5CD8D85218(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9EB2228A60684C0DBEC12D5CD8D85218");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9EB2228A60684C0DBEC12D5CD8D85218", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("9EB2228A60684C0DBEC12D5CD8D85218");
        vars.removeMessage("9EB2228A60684C0DBEC12D5CD8D85218");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonD85D5B5E368A49B1A6293BA4AE15F0F9(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D85D5B5E368A49B1A6293BA4AE15F0F9");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD85D5B5E368A49B1A6293BA4AE15F0F9", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D85D5B5E368A49B1A6293BA4AE15F0F9");
        vars.removeMessage("D85D5B5E368A49B1A6293BA4AE15F0F9");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("AD_Client_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "AD_Client_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportAD_Client_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("ExportAuditInfo", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFF80808133362F6A013336781FCE0066(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF80808133362F6A013336781FCE0066");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF80808133362F6A013336781FCE0066", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF80808133362F6A013336781FCE0066");
        vars.removeMessage("FF80808133362F6A013336781FCE0066");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFF8081813219E68E013219ECFE930004(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF8081813219E68E013219ECFE930004");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF8081813219E68E013219ECFE930004", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF8081813219E68E013219ECFE930004");
        vars.removeMessage("FF8081813219E68E013219ECFE930004");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("Value", ActionButtonSQLDefaultData.selectActPFF8081813219E68E013219ECFE930004_Value(this, Utility.getContext(this, vars, "MA_SEQUENCEPRODUCT_ID", "")));
    xmlDocument.setParameter("Name", ActionButtonSQLDefaultData.selectActPFF8081813219E68E013219ECFE930004_Name(this, Utility.getContext(this, vars, "MA_SEQUENCEPRODUCT_ID", "")));
    xmlDocument.setParameter("M_Product_Category_ID", "");
    comboTableData = new ComboTableData(vars, this, "19", "M_Product_Category_ID", "", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportM_Product_Category_ID", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("Productiontype", "+");
    comboTableData = new ComboTableData(vars, this, "17", "Productiontype", "800034", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "+");
    xmlDocument.setData("reportProductiontype", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("Qty", "0");
    xmlDocument.setParameter("Copyattribute", "Y");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFF808181324D007801324D2AE1130066(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF808181324D007801324D2AE1130066");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF808181324D007801324D2AE1130066", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF808181324D007801324D2AE1130066");
        vars.removeMessage("FF808181324D007801324D2AE1130066");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("Date", "");
    xmlDocument.setParameter("Date_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    xmlDocument.setParameter("Starttime", "");
    xmlDocument.setParameter("Endtime", "");
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFF808181326CD80501326CE906D70042(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF808181326CD80501326CE906D70042");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF808181326CD80501326CE906D70042", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF808181326CD80501326CE906D70042");
        vars.removeMessage("FF808181326CD80501326CE906D70042");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFF80818132A4F6AD0132A573DD7A0021(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF80818132A4F6AD0132A573DD7A0021");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF80818132A4F6AD0132A573DD7A0021", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF80818132A4F6AD0132A573DD7A0021");
        vars.removeMessage("FF80818132A4F6AD0132A573DD7A0021");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton0BDC2164ED3E48539FCEF4D306F29EFD(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0BDC2164ED3E48539FCEF4D306F29EFD");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton0BDC2164ED3E48539FCEF4D306F29EFD", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("0BDC2164ED3E48539FCEF4D306F29EFD");
        vars.removeMessage("0BDC2164ED3E48539FCEF4D306F29EFD");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", "");
    comboTableData = new ComboTableData(vars, this, "17", "action", "798239EB069F41A9BA8EE040C63DDBBC", "3842B167CA6F44239C3357A721E3BA6A", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton5BE14AA10165490A9ADEFB7532F7FA94(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 5BE14AA10165490A9ADEFB7532F7FA94");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton5BE14AA10165490A9ADEFB7532F7FA94", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("5BE14AA10165490A9ADEFB7532F7FA94");
        vars.removeMessage("5BE14AA10165490A9ADEFB7532F7FA94");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton58A9261BACEF45DDA526F29D8557272D(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 58A9261BACEF45DDA526F29D8557272D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton58A9261BACEF45DDA526F29D8557272D", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("58A9261BACEF45DDA526F29D8557272D");
        vars.removeMessage("58A9261BACEF45DDA526F29D8557272D");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", Utility.getContext(this, vars, "EM_APRM_Process_BS", ""));
    comboTableData = new ComboTableData(vars, this, "17", "action", "EC75B6F5A9504DB6B3F3356EA85F15EE", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "EM_APRM_Process_BS", ""));
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton15C8708DFC464C2D91286E59624FDD18(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 15C8708DFC464C2D91286E59624FDD18");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton15C8708DFC464C2D91286E59624FDD18", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("15C8708DFC464C2D91286E59624FDD18");
        vars.removeMessage("15C8708DFC464C2D91286E59624FDD18");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    xmlDocument.setParameter("C_GLItem_ID", ActionButtonSQLDefaultData.selectActP15C8708DFC464C2D91286E59624FDD18_C_GLItem_ID(this, Utility.getContext(this, vars, "C_GLItem_ID", "")));
    xmlDocument.setParameter("M_Product_ID", Utility.getContext(this, vars, "M_Product_ID", ""));
    xmlDocument.setParameter("C_BPartner_ID", Utility.getContext(this, vars, "C_Bpartner_ID", ""));
    xmlDocument.setParameter("C_Project_ID", Utility.getContext(this, vars, "C_Project_ID", ""));
    xmlDocument.setParameter("C_Campaign_ID", Utility.getContext(this, vars, "C_Campaign_ID", ""));
    xmlDocument.setParameter("C_Activity_ID", Utility.getContext(this, vars, "C_Activity_ID", ""));
    xmlDocument.setParameter("C_SalesRegion_ID", Utility.getContext(this, vars, "C_Salesregion_ID", ""));
    xmlDocument.setParameter("C_Costcenter_ID", Utility.getContext(this, vars, "C_Costcenter_ID", ""));
    xmlDocument.setParameter("User1_ID", Utility.getContext(this, vars, "User1_ID", ""));
    xmlDocument.setParameter("User2_ID", Utility.getContext(this, vars, "User2_ID", ""));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton017312F51139438A9665775E3B5392A1(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 017312F51139438A9665775E3B5392A1");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton017312F51139438A9665775E3B5392A1", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("017312F51139438A9665775E3B5392A1");
        vars.removeMessage("017312F51139438A9665775E3B5392A1");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", "");
    comboTableData = new ComboTableData(vars, this, "17", "action", "798239EB069F41A9BA8EE040C63DDBBC", "3842B167CA6F44239C3357A721E3BA6A", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "");
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6255BE488882480599C81284B70CD9B3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6255BE488882480599C81284B70CD9B3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6255BE488882480599C81284B70CD9B3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6255BE488882480599C81284B70CD9B3");
        vars.removeMessage("6255BE488882480599C81284B70CD9B3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", Utility.getContext(this, vars, "EM_APRM_Process_Payment", ""));
    comboTableData = new ComboTableData(vars, this, "17", "action", "36972531DA994BB38ECB91993058282F", "575E470ABADB4C278132C957A78C47E3", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "EM_APRM_Process_Payment", ""));
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonF68F2890E96D4D85A1DEF0274D105BCE(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F68F2890E96D4D85A1DEF0274D105BCE");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonF68F2890E96D4D85A1DEF0274D105BCE", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("F68F2890E96D4D85A1DEF0274D105BCE");
        vars.removeMessage("F68F2890E96D4D85A1DEF0274D105BCE");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton29D17F515727436DBCE32BC6CA28382B(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 29D17F515727436DBCE32BC6CA28382B");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton29D17F515727436DBCE32BC6CA28382B", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("29D17F515727436DBCE32BC6CA28382B");
        vars.removeMessage("29D17F515727436DBCE32BC6CA28382B");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", "RV");
    comboTableData = new ComboTableData(vars, this, "17", "action", "66F2DCC800A34F94923444C29478E70A", "", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, "RV");
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    xmlDocument.setParameter("paymentDate", "");
    xmlDocument.setParameter("paymentDate_Format", vars.getSessionValue("#AD_SqlDateFormat"));
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonDE1B382FDD2540199D223586F6E216D0(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DE1B382FDD2540199D223586F6E216D0");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDE1B382FDD2540199D223586F6E216D0", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("DE1B382FDD2540199D223586F6E216D0");
        vars.removeMessage("DE1B382FDD2540199D223586F6E216D0");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonD16966FBF9604A3D91A50DC83C6EA8E3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D16966FBF9604A3D91A50DC83C6EA8E3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD16966FBF9604A3D91A50DC83C6EA8E3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D16966FBF9604A3D91A50DC83C6EA8E3");
        vars.removeMessage("D16966FBF9604A3D91A50DC83C6EA8E3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFF8080812E2F8EAE012E2F94CF470014(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF8080812E2F8EAE012E2F94CF470014");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF8080812E2F8EAE012E2F94CF470014", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF8080812E2F8EAE012E2F94CF470014");
        vars.removeMessage("FF8080812E2F8EAE012E2F94CF470014");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    ComboTableData comboTableData = null;
    xmlDocument.setParameter("action", Utility.getContext(this, vars, "Process_Reconciliation", ""));
    comboTableData = new ComboTableData(vars, this, "17", "action", "FF8080812E443491012E443C053A001A", "FF808081332719060133271E5BB1001B", Utility.getContext(this, vars, "#AccessibleOrgTree", ""), Utility.getContext(this, vars, "#User_Client", ""), 0);
    Utility.fillSQLParameters(this, vars, null, comboTableData, windowId, Utility.getContext(this, vars, "Process_Reconciliation", ""));
    xmlDocument.setData("reportaction", "liststructure", comboTableData.select(false));
comboTableData = null;
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFF8080812F348A97012F349DC24F0007(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF8080812F348A97012F349DC24F0007");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF8080812F348A97012F349DC24F0007", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF8080812F348A97012F349DC24F0007");
        vars.removeMessage("FF8080812F348A97012F349DC24F0007");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonEFAFE78815E14788B36DB984F4CC8045(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process EFAFE78815E14788B36DB984F4CC8045");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonEFAFE78815E14788B36DB984F4CC8045", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("EFAFE78815E14788B36DB984F4CC8045");
        vars.removeMessage("EFAFE78815E14788B36DB984F4CC8045");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton186BA5F122DD448E9A36B57E0115266C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 186BA5F122DD448E9A36B57E0115266C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton186BA5F122DD448E9A36B57E0115266C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("186BA5F122DD448E9A36B57E0115266C");
        vars.removeMessage("186BA5F122DD448E9A36B57E0115266C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton2979075A16BA4695ADE3B093725CA876(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 2979075A16BA4695ADE3B093725CA876");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton2979075A16BA4695ADE3B093725CA876", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("2979075A16BA4695ADE3B093725CA876");
        vars.removeMessage("2979075A16BA4695ADE3B093725CA876");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton977F0ED7770440D880A48ECBA456706D(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 977F0ED7770440D880A48ECBA456706D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton977F0ED7770440D880A48ECBA456706D", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("977F0ED7770440D880A48ECBA456706D");
        vars.removeMessage("977F0ED7770440D880A48ECBA456706D");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton3022A5678A134AD28174B8A9B74B325A(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3022A5678A134AD28174B8A9B74B325A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton3022A5678A134AD28174B8A9B74B325A", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("3022A5678A134AD28174B8A9B74B325A");
        vars.removeMessage("3022A5678A134AD28174B8A9B74B325A");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonEEA62DA04DF44D39B9BCA91BEAE81CED(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process EEA62DA04DF44D39B9BCA91BEAE81CED");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonEEA62DA04DF44D39B9BCA91BEAE81CED", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("EEA62DA04DF44D39B9BCA91BEAE81CED");
        vars.removeMessage("EEA62DA04DF44D39B9BCA91BEAE81CED");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton01A90B910CAD4297A26C6E2594669DB4(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 01A90B910CAD4297A26C6E2594669DB4");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton01A90B910CAD4297A26C6E2594669DB4", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("01A90B910CAD4297A26C6E2594669DB4");
        vars.removeMessage("01A90B910CAD4297A26C6E2594669DB4");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonA9CBB36555F943ABB417ECD0EB1CC52E(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A9CBB36555F943ABB417ECD0EB1CC52E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonA9CBB36555F943ABB417ECD0EB1CC52E", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("A9CBB36555F943ABB417ECD0EB1CC52E");
        vars.removeMessage("A9CBB36555F943ABB417ECD0EB1CC52E");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton219F25ED92D74A40B3AB51973D01F629(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 219F25ED92D74A40B3AB51973D01F629");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton219F25ED92D74A40B3AB51973D01F629", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("219F25ED92D74A40B3AB51973D01F629");
        vars.removeMessage("219F25ED92D74A40B3AB51973D01F629");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonADE9DCB32A484BA29817A385A292496C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process ADE9DCB32A484BA29817A385A292496C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonADE9DCB32A484BA29817A385A292496C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("ADE9DCB32A484BA29817A385A292496C");
        vars.removeMessage("ADE9DCB32A484BA29817A385A292496C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton68EF40FAB3954363A809F460B81C0C99(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 68EF40FAB3954363A809F460B81C0C99");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton68EF40FAB3954363A809F460B81C0C99", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("68EF40FAB3954363A809F460B81C0C99");
        vars.removeMessage("68EF40FAB3954363A809F460B81C0C99");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton8217C6BBED3740F381CA830CB6B67725(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8217C6BBED3740F381CA830CB6B67725");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton8217C6BBED3740F381CA830CB6B67725", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("8217C6BBED3740F381CA830CB6B67725");
        vars.removeMessage("8217C6BBED3740F381CA830CB6B67725");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton1DB34C54D0F2443593FAD5FA8197A14C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1DB34C54D0F2443593FAD5FA8197A14C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton1DB34C54D0F2443593FAD5FA8197A14C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("1DB34C54D0F2443593FAD5FA8197A14C");
        vars.removeMessage("1DB34C54D0F2443593FAD5FA8197A14C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton353F95EFE50146DD8A92EC5CD6B65154(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 353F95EFE50146DD8A92EC5CD6B65154");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton353F95EFE50146DD8A92EC5CD6B65154", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("353F95EFE50146DD8A92EC5CD6B65154");
        vars.removeMessage("353F95EFE50146DD8A92EC5CD6B65154");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6DB70589347647CDB5C8409320947308(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6DB70589347647CDB5C8409320947308");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6DB70589347647CDB5C8409320947308", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6DB70589347647CDB5C8409320947308");
        vars.removeMessage("6DB70589347647CDB5C8409320947308");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton8EB3A5AC31024491981EBD8D1AE23BFF(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8EB3A5AC31024491981EBD8D1AE23BFF");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton8EB3A5AC31024491981EBD8D1AE23BFF", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("8EB3A5AC31024491981EBD8D1AE23BFF");
        vars.removeMessage("8EB3A5AC31024491981EBD8D1AE23BFF");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonB9415D7D6DB749DF89BD7DF32466D10B(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B9415D7D6DB749DF89BD7DF32466D10B");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB9415D7D6DB749DF89BD7DF32466D10B", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B9415D7D6DB749DF89BD7DF32466D10B");
        vars.removeMessage("B9415D7D6DB749DF89BD7DF32466D10B");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton542C02D5AC5A45338BA692E346D34D23(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 542C02D5AC5A45338BA692E346D34D23");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton542C02D5AC5A45338BA692E346D34D23", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("542C02D5AC5A45338BA692E346D34D23");
        vars.removeMessage("542C02D5AC5A45338BA692E346D34D23");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonDAB5D8627EE5415E9FF2531998E303B6(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DAB5D8627EE5415E9FF2531998E303B6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDAB5D8627EE5415E9FF2531998E303B6", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("DAB5D8627EE5415E9FF2531998E303B6");
        vars.removeMessage("DAB5D8627EE5415E9FF2531998E303B6");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonE0A6DA47391A4AFDBF738C7F17607DC0(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E0A6DA47391A4AFDBF738C7F17607DC0");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonE0A6DA47391A4AFDBF738C7F17607DC0", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("E0A6DA47391A4AFDBF738C7F17607DC0");
        vars.removeMessage("E0A6DA47391A4AFDBF738C7F17607DC0");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton8D701FA22B894C5195C3E672661BF26D(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8D701FA22B894C5195C3E672661BF26D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton8D701FA22B894C5195C3E672661BF26D", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("8D701FA22B894C5195C3E672661BF26D");
        vars.removeMessage("8D701FA22B894C5195C3E672661BF26D");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonD54C07F4ACF1454B82CAB6C6DBED27E6(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D54C07F4ACF1454B82CAB6C6DBED27E6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD54C07F4ACF1454B82CAB6C6DBED27E6", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D54C07F4ACF1454B82CAB6C6DBED27E6");
        vars.removeMessage("D54C07F4ACF1454B82CAB6C6DBED27E6");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton0637AC192E8C45FFA516DCA4A2FD2743(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0637AC192E8C45FFA516DCA4A2FD2743");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton0637AC192E8C45FFA516DCA4A2FD2743", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("0637AC192E8C45FFA516DCA4A2FD2743");
        vars.removeMessage("0637AC192E8C45FFA516DCA4A2FD2743");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonF5C1F8C2F9B1420B8D12223585BC5798(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F5C1F8C2F9B1420B8D12223585BC5798");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonF5C1F8C2F9B1420B8D12223585BC5798", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("F5C1F8C2F9B1420B8D12223585BC5798");
        vars.removeMessage("F5C1F8C2F9B1420B8D12223585BC5798");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton02D6B2441A454C739E825963E3DA4ECD(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 02D6B2441A454C739E825963E3DA4ECD");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton02D6B2441A454C739E825963E3DA4ECD", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("02D6B2441A454C739E825963E3DA4ECD");
        vars.removeMessage("02D6B2441A454C739E825963E3DA4ECD");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton1445812B6F504A9B90BD1630A2A12BA1(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1445812B6F504A9B90BD1630A2A12BA1");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton1445812B6F504A9B90BD1630A2A12BA1", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("1445812B6F504A9B90BD1630A2A12BA1");
        vars.removeMessage("1445812B6F504A9B90BD1630A2A12BA1");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton20E70A9D32F644C0BFD3AA1C90566AB3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 20E70A9D32F644C0BFD3AA1C90566AB3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton20E70A9D32F644C0BFD3AA1C90566AB3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("20E70A9D32F644C0BFD3AA1C90566AB3");
        vars.removeMessage("20E70A9D32F644C0BFD3AA1C90566AB3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonD39B25EADB224BB59E2A4F758A867D44(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D39B25EADB224BB59E2A4F758A867D44");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD39B25EADB224BB59E2A4F758A867D44", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D39B25EADB224BB59E2A4F758A867D44");
        vars.removeMessage("D39B25EADB224BB59E2A4F758A867D44");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton17B03F4D1F1E4184A8245B6168928C48(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 17B03F4D1F1E4184A8245B6168928C48");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton17B03F4D1F1E4184A8245B6168928C48", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("17B03F4D1F1E4184A8245B6168928C48");
        vars.removeMessage("17B03F4D1F1E4184A8245B6168928C48");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonE802BD1CEBEC4CA5BE4164A328951F61(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E802BD1CEBEC4CA5BE4164A328951F61");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonE802BD1CEBEC4CA5BE4164A328951F61", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("E802BD1CEBEC4CA5BE4164A328951F61");
        vars.removeMessage("E802BD1CEBEC4CA5BE4164A328951F61");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton8D6C70A85BCC4DF1AE626425FE6000F2(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8D6C70A85BCC4DF1AE626425FE6000F2");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton8D6C70A85BCC4DF1AE626425FE6000F2", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("8D6C70A85BCC4DF1AE626425FE6000F2");
        vars.removeMessage("8D6C70A85BCC4DF1AE626425FE6000F2");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton1C79D5FD586F4D2BA8A239A1E9397982(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1C79D5FD586F4D2BA8A239A1E9397982");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton1C79D5FD586F4D2BA8A239A1E9397982", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("1C79D5FD586F4D2BA8A239A1E9397982");
        vars.removeMessage("1C79D5FD586F4D2BA8A239A1E9397982");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton9456BD018F8C41D29E5E6908DD1E9958(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9456BD018F8C41D29E5E6908DD1E9958");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9456BD018F8C41D29E5E6908DD1E9958", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("9456BD018F8C41D29E5E6908DD1E9958");
        vars.removeMessage("9456BD018F8C41D29E5E6908DD1E9958");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton9FD873E4FCF14633804441249ED4F428(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9FD873E4FCF14633804441249ED4F428");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9FD873E4FCF14633804441249ED4F428", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("9FD873E4FCF14633804441249ED4F428");
        vars.removeMessage("9FD873E4FCF14633804441249ED4F428");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonAE0258D576C24072B07A0476B7571A6E(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process AE0258D576C24072B07A0476B7571A6E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonAE0258D576C24072B07A0476B7571A6E", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("AE0258D576C24072B07A0476B7571A6E");
        vars.removeMessage("AE0258D576C24072B07A0476B7571A6E");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton74D8317FBB864522B766BDC6DDE8DD6C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 74D8317FBB864522B766BDC6DDE8DD6C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton74D8317FBB864522B766BDC6DDE8DD6C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("74D8317FBB864522B766BDC6DDE8DD6C");
        vars.removeMessage("74D8317FBB864522B766BDC6DDE8DD6C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton7CDE00F02E2946FBB7557E1F14BF9EA1(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7CDE00F02E2946FBB7557E1F14BF9EA1");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton7CDE00F02E2946FBB7557E1F14BF9EA1", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("7CDE00F02E2946FBB7557E1F14BF9EA1");
        vars.removeMessage("7CDE00F02E2946FBB7557E1F14BF9EA1");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton4ED98E07DD4B4B179982F71786D2A7A5(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 4ED98E07DD4B4B179982F71786D2A7A5");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton4ED98E07DD4B4B179982F71786D2A7A5", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("4ED98E07DD4B4B179982F71786D2A7A5");
        vars.removeMessage("4ED98E07DD4B4B179982F71786D2A7A5");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonCD8EA8F3D08B457CA39A56D4CAFD81AE(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process CD8EA8F3D08B457CA39A56D4CAFD81AE");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonCD8EA8F3D08B457CA39A56D4CAFD81AE", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("CD8EA8F3D08B457CA39A56D4CAFD81AE");
        vars.removeMessage("CD8EA8F3D08B457CA39A56D4CAFD81AE");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton63194D3CC6114581AA0FA46D3108BA37(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 63194D3CC6114581AA0FA46D3108BA37");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton63194D3CC6114581AA0FA46D3108BA37", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("63194D3CC6114581AA0FA46D3108BA37");
        vars.removeMessage("63194D3CC6114581AA0FA46D3108BA37");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonC0C01D365E174254891E71EC41B099ED(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process C0C01D365E174254891E71EC41B099ED");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonC0C01D365E174254891E71EC41B099ED", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("C0C01D365E174254891E71EC41B099ED");
        vars.removeMessage("C0C01D365E174254891E71EC41B099ED");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonC19E8C0E72FC415488719F6181C3B5AD(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process C19E8C0E72FC415488719F6181C3B5AD");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonC19E8C0E72FC415488719F6181C3B5AD", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("C19E8C0E72FC415488719F6181C3B5AD");
        vars.removeMessage("C19E8C0E72FC415488719F6181C3B5AD");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton3A78D32DAFAE4C5EA98BD0A667213369(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3A78D32DAFAE4C5EA98BD0A667213369");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton3A78D32DAFAE4C5EA98BD0A667213369", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("3A78D32DAFAE4C5EA98BD0A667213369");
        vars.removeMessage("3A78D32DAFAE4C5EA98BD0A667213369");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFA292F6205104423A80106ED7FD297CF(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FA292F6205104423A80106ED7FD297CF");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFA292F6205104423A80106ED7FD297CF", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FA292F6205104423A80106ED7FD297CF");
        vars.removeMessage("FA292F6205104423A80106ED7FD297CF");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton9A1023B8C7B3483680A7780CB745817F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9A1023B8C7B3483680A7780CB745817F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9A1023B8C7B3483680A7780CB745817F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("9A1023B8C7B3483680A7780CB745817F");
        vars.removeMessage("9A1023B8C7B3483680A7780CB745817F");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton25563A6B6BD24D0E9463D87E035BD3EA(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 25563A6B6BD24D0E9463D87E035BD3EA");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton25563A6B6BD24D0E9463D87E035BD3EA", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("25563A6B6BD24D0E9463D87E035BD3EA");
        vars.removeMessage("25563A6B6BD24D0E9463D87E035BD3EA");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonA4C0B645D60544E3836F437264A4DC79(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A4C0B645D60544E3836F437264A4DC79");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonA4C0B645D60544E3836F437264A4DC79", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("A4C0B645D60544E3836F437264A4DC79");
        vars.removeMessage("A4C0B645D60544E3836F437264A4DC79");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton15C798457213481BA271CA63353EFE2F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 15C798457213481BA271CA63353EFE2F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton15C798457213481BA271CA63353EFE2F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("15C798457213481BA271CA63353EFE2F");
        vars.removeMessage("15C798457213481BA271CA63353EFE2F");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton1292FC5D518D42758F593EDD4A746766(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1292FC5D518D42758F593EDD4A746766");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton1292FC5D518D42758F593EDD4A746766", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("1292FC5D518D42758F593EDD4A746766");
        vars.removeMessage("1292FC5D518D42758F593EDD4A746766");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton173F6DB31A1643A985E46CA1F15DBD7A(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 173F6DB31A1643A985E46CA1F15DBD7A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton173F6DB31A1643A985E46CA1F15DBD7A", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("173F6DB31A1643A985E46CA1F15DBD7A");
        vars.removeMessage("173F6DB31A1643A985E46CA1F15DBD7A");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonB683365A463B48BABE7128017C80484B(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B683365A463B48BABE7128017C80484B");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB683365A463B48BABE7128017C80484B", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B683365A463B48BABE7128017C80484B");
        vars.removeMessage("B683365A463B48BABE7128017C80484B");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonB33824E7922E42A2A456CAEDBA6830CA(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B33824E7922E42A2A456CAEDBA6830CA");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB33824E7922E42A2A456CAEDBA6830CA", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B33824E7922E42A2A456CAEDBA6830CA");
        vars.removeMessage("B33824E7922E42A2A456CAEDBA6830CA");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton3ECC743F78A544B2BD119FBB02D05E97(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3ECC743F78A544B2BD119FBB02D05E97");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton3ECC743F78A544B2BD119FBB02D05E97", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("3ECC743F78A544B2BD119FBB02D05E97");
        vars.removeMessage("3ECC743F78A544B2BD119FBB02D05E97");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton20D40AFB59CF46D8A0F893D08E85911D(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 20D40AFB59CF46D8A0F893D08E85911D");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton20D40AFB59CF46D8A0F893D08E85911D", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("20D40AFB59CF46D8A0F893D08E85911D");
        vars.removeMessage("20D40AFB59CF46D8A0F893D08E85911D");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonA36A615253064A95B09A9344C218FA74(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A36A615253064A95B09A9344C218FA74");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonA36A615253064A95B09A9344C218FA74", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("A36A615253064A95B09A9344C218FA74");
        vars.removeMessage("A36A615253064A95B09A9344C218FA74");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton1454195479614FBFBB8F30755D118B4C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1454195479614FBFBB8F30755D118B4C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton1454195479614FBFBB8F30755D118B4C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("1454195479614FBFBB8F30755D118B4C");
        vars.removeMessage("1454195479614FBFBB8F30755D118B4C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFFD63BDDDDD94E2991D2AEB30E5E3FDB(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FFD63BDDDDD94E2991D2AEB30E5E3FDB");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFFD63BDDDDD94E2991D2AEB30E5E3FDB", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FFD63BDDDDD94E2991D2AEB30E5E3FDB");
        vars.removeMessage("FFD63BDDDDD94E2991D2AEB30E5E3FDB");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton5E92917708EF426D9697D88350DCBF14(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 5E92917708EF426D9697D88350DCBF14");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton5E92917708EF426D9697D88350DCBF14", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("5E92917708EF426D9697D88350DCBF14");
        vars.removeMessage("5E92917708EF426D9697D88350DCBF14");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonE865F018CEC7402DB06DB93C6DE30B7F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E865F018CEC7402DB06DB93C6DE30B7F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonE865F018CEC7402DB06DB93C6DE30B7F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("E865F018CEC7402DB06DB93C6DE30B7F");
        vars.removeMessage("E865F018CEC7402DB06DB93C6DE30B7F");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton8F8FAACEEF9D449C909758519FBD0760(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8F8FAACEEF9D449C909758519FBD0760");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton8F8FAACEEF9D449C909758519FBD0760", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("8F8FAACEEF9D449C909758519FBD0760");
        vars.removeMessage("8F8FAACEEF9D449C909758519FBD0760");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonED4FB9D909484DB28578C5808CACA796(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process ED4FB9D909484DB28578C5808CACA796");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonED4FB9D909484DB28578C5808CACA796", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("ED4FB9D909484DB28578C5808CACA796");
        vars.removeMessage("ED4FB9D909484DB28578C5808CACA796");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonB76A77639E4B4DAAB097AD11DAE7A1AA(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B76A77639E4B4DAAB097AD11DAE7A1AA");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB76A77639E4B4DAAB097AD11DAE7A1AA", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B76A77639E4B4DAAB097AD11DAE7A1AA");
        vars.removeMessage("B76A77639E4B4DAAB097AD11DAE7A1AA");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton91ABA870C21D44B1B35717F42AB28F27(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 91ABA870C21D44B1B35717F42AB28F27");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton91ABA870C21D44B1B35717F42AB28F27", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("91ABA870C21D44B1B35717F42AB28F27");
        vars.removeMessage("91ABA870C21D44B1B35717F42AB28F27");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonF20F5B75B85E45B997F3853D5B7607C7(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F20F5B75B85E45B997F3853D5B7607C7");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonF20F5B75B85E45B997F3853D5B7607C7", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("F20F5B75B85E45B997F3853D5B7607C7");
        vars.removeMessage("F20F5B75B85E45B997F3853D5B7607C7");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton2CC36FE2C6974AEC87D1FB0930674F51(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 2CC36FE2C6974AEC87D1FB0930674F51");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton2CC36FE2C6974AEC87D1FB0930674F51", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("2CC36FE2C6974AEC87D1FB0930674F51");
        vars.removeMessage("2CC36FE2C6974AEC87D1FB0930674F51");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton11F05217E6C84C3EACB57167B91BA11A(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 11F05217E6C84C3EACB57167B91BA11A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton11F05217E6C84C3EACB57167B91BA11A", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("11F05217E6C84C3EACB57167B91BA11A");
        vars.removeMessage("11F05217E6C84C3EACB57167B91BA11A");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton1C6BC6797AAE4AA6ABB1DD98BF41F053(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1C6BC6797AAE4AA6ABB1DD98BF41F053");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton1C6BC6797AAE4AA6ABB1DD98BF41F053", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("1C6BC6797AAE4AA6ABB1DD98BF41F053");
        vars.removeMessage("1C6BC6797AAE4AA6ABB1DD98BF41F053");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonDEC765A7E71445ABB8C7DBDBFB735A13(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process DEC765A7E71445ABB8C7DBDBFB735A13");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonDEC765A7E71445ABB8C7DBDBFB735A13", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("DEC765A7E71445ABB8C7DBDBFB735A13");
        vars.removeMessage("DEC765A7E71445ABB8C7DBDBFB735A13");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton8E00D1574F384B4A9D95F3ED022F0A47(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8E00D1574F384B4A9D95F3ED022F0A47");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton8E00D1574F384B4A9D95F3ED022F0A47", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("8E00D1574F384B4A9D95F3ED022F0A47");
        vars.removeMessage("8E00D1574F384B4A9D95F3ED022F0A47");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonBD1693ECFA6944AB86488F6D12E423E4(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process BD1693ECFA6944AB86488F6D12E423E4");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonBD1693ECFA6944AB86488F6D12E423E4", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("BD1693ECFA6944AB86488F6D12E423E4");
        vars.removeMessage("BD1693ECFA6944AB86488F6D12E423E4");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton1AD75B16E2884E258F3B8EF51039FA49(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1AD75B16E2884E258F3B8EF51039FA49");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton1AD75B16E2884E258F3B8EF51039FA49", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("1AD75B16E2884E258F3B8EF51039FA49");
        vars.removeMessage("1AD75B16E2884E258F3B8EF51039FA49");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton93876A2A71E14EB7AFEAF1D05F60F832(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 93876A2A71E14EB7AFEAF1D05F60F832");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton93876A2A71E14EB7AFEAF1D05F60F832", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("93876A2A71E14EB7AFEAF1D05F60F832");
        vars.removeMessage("93876A2A71E14EB7AFEAF1D05F60F832");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton80BD458482C842F39419D7D9B9EF00F0(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 80BD458482C842F39419D7D9B9EF00F0");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton80BD458482C842F39419D7D9B9EF00F0", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("80BD458482C842F39419D7D9B9EF00F0");
        vars.removeMessage("80BD458482C842F39419D7D9B9EF00F0");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6EEBD3ADA8D945C8B614C641556CEEAD(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6EEBD3ADA8D945C8B614C641556CEEAD");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6EEBD3ADA8D945C8B614C641556CEEAD", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6EEBD3ADA8D945C8B614C641556CEEAD");
        vars.removeMessage("6EEBD3ADA8D945C8B614C641556CEEAD");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton30A9E010447D4B7A983D4DF2EE4CA314(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 30A9E010447D4B7A983D4DF2EE4CA314");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton30A9E010447D4B7A983D4DF2EE4CA314", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("30A9E010447D4B7A983D4DF2EE4CA314");
        vars.removeMessage("30A9E010447D4B7A983D4DF2EE4CA314");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonC545A77357BB4FD69BB6C8646A3D9AF2(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process C545A77357BB4FD69BB6C8646A3D9AF2");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonC545A77357BB4FD69BB6C8646A3D9AF2", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("C545A77357BB4FD69BB6C8646A3D9AF2");
        vars.removeMessage("C545A77357BB4FD69BB6C8646A3D9AF2");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton4FD494B6B9C5477FAD6E7AD6F35EB1D4(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 4FD494B6B9C5477FAD6E7AD6F35EB1D4");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton4FD494B6B9C5477FAD6E7AD6F35EB1D4", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("4FD494B6B9C5477FAD6E7AD6F35EB1D4");
        vars.removeMessage("4FD494B6B9C5477FAD6E7AD6F35EB1D4");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonFF1BC80474C0403A98A5E6BCACF6CB3F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process FF1BC80474C0403A98A5E6BCACF6CB3F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonFF1BC80474C0403A98A5E6BCACF6CB3F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("FF1BC80474C0403A98A5E6BCACF6CB3F");
        vars.removeMessage("FF1BC80474C0403A98A5E6BCACF6CB3F");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonF79126E740EA4C6799CC28D3526D016E(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F79126E740EA4C6799CC28D3526D016E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonF79126E740EA4C6799CC28D3526D016E", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("F79126E740EA4C6799CC28D3526D016E");
        vars.removeMessage("F79126E740EA4C6799CC28D3526D016E");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonA49580DFBA994334957B0E398916E97F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A49580DFBA994334957B0E398916E97F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonA49580DFBA994334957B0E398916E97F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("A49580DFBA994334957B0E398916E97F");
        vars.removeMessage("A49580DFBA994334957B0E398916E97F");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton1A0C98271D5F480B98874D9AFDB92EA6(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 1A0C98271D5F480B98874D9AFDB92EA6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton1A0C98271D5F480B98874D9AFDB92EA6", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("1A0C98271D5F480B98874D9AFDB92EA6");
        vars.removeMessage("1A0C98271D5F480B98874D9AFDB92EA6");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonA5AB0D40FD7742388B7B17AF8282D2C0(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process A5AB0D40FD7742388B7B17AF8282D2C0");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonA5AB0D40FD7742388B7B17AF8282D2C0", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("A5AB0D40FD7742388B7B17AF8282D2C0");
        vars.removeMessage("A5AB0D40FD7742388B7B17AF8282D2C0");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton0630AAC03F3A4EA7BDE267AD12637E7F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0630AAC03F3A4EA7BDE267AD12637E7F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton0630AAC03F3A4EA7BDE267AD12637E7F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("0630AAC03F3A4EA7BDE267AD12637E7F");
        vars.removeMessage("0630AAC03F3A4EA7BDE267AD12637E7F");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonD189003BD6AF4978B9A8506CB1B843B6(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D189003BD6AF4978B9A8506CB1B843B6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD189003BD6AF4978B9A8506CB1B843B6", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D189003BD6AF4978B9A8506CB1B843B6");
        vars.removeMessage("D189003BD6AF4978B9A8506CB1B843B6");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton26EF860F5FDA4C6B885445D0B381BBE1(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 26EF860F5FDA4C6B885445D0B381BBE1");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton26EF860F5FDA4C6B885445D0B381BBE1", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("26EF860F5FDA4C6B885445D0B381BBE1");
        vars.removeMessage("26EF860F5FDA4C6B885445D0B381BBE1");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton2ADA7794EC6B4D159060073A62C550A8(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 2ADA7794EC6B4D159060073A62C550A8");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton2ADA7794EC6B4D159060073A62C550A8", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("2ADA7794EC6B4D159060073A62C550A8");
        vars.removeMessage("2ADA7794EC6B4D159060073A62C550A8");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton8402E2D41EBB45AA8EA418B6F0FCF4DB(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 8402E2D41EBB45AA8EA418B6F0FCF4DB");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton8402E2D41EBB45AA8EA418B6F0FCF4DB", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("8402E2D41EBB45AA8EA418B6F0FCF4DB");
        vars.removeMessage("8402E2D41EBB45AA8EA418B6F0FCF4DB");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonE038F781230D4505AAADDE1EBFD2573E(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process E038F781230D4505AAADDE1EBFD2573E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonE038F781230D4505AAADDE1EBFD2573E", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("E038F781230D4505AAADDE1EBFD2573E");
        vars.removeMessage("E038F781230D4505AAADDE1EBFD2573E");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton309A0C522F8B497FB2FC4174C6EE532E(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 309A0C522F8B497FB2FC4174C6EE532E");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton309A0C522F8B497FB2FC4174C6EE532E", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("309A0C522F8B497FB2FC4174C6EE532E");
        vars.removeMessage("309A0C522F8B497FB2FC4174C6EE532E");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonBF4B81BF72F740E7AE729E07D3FB0663(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process BF4B81BF72F740E7AE729E07D3FB0663");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonBF4B81BF72F740E7AE729E07D3FB0663", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("BF4B81BF72F740E7AE729E07D3FB0663");
        vars.removeMessage("BF4B81BF72F740E7AE729E07D3FB0663");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonADBF99D438B24F299A860D1535AE864A(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process ADBF99D438B24F299A860D1535AE864A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonADBF99D438B24F299A860D1535AE864A", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("ADBF99D438B24F299A860D1535AE864A");
        vars.removeMessage("ADBF99D438B24F299A860D1535AE864A");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton84143CAAADE74274B0560AFDE28E7156(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 84143CAAADE74274B0560AFDE28E7156");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton84143CAAADE74274B0560AFDE28E7156", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("84143CAAADE74274B0560AFDE28E7156");
        vars.removeMessage("84143CAAADE74274B0560AFDE28E7156");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton921B8092DE21435192CB8B7C6F5E6961(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 921B8092DE21435192CB8B7C6F5E6961");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton921B8092DE21435192CB8B7C6F5E6961", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("921B8092DE21435192CB8B7C6F5E6961");
        vars.removeMessage("921B8092DE21435192CB8B7C6F5E6961");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonB9F945AFAC0A4FEBBF5B0DCF6420C70B(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B9F945AFAC0A4FEBBF5B0DCF6420C70B");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB9F945AFAC0A4FEBBF5B0DCF6420C70B", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B9F945AFAC0A4FEBBF5B0DCF6420C70B");
        vars.removeMessage("B9F945AFAC0A4FEBBF5B0DCF6420C70B");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonB198D7B384724E6BB9A30C54B85361AF(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B198D7B384724E6BB9A30C54B85361AF");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB198D7B384724E6BB9A30C54B85361AF", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B198D7B384724E6BB9A30C54B85361AF");
        vars.removeMessage("B198D7B384724E6BB9A30C54B85361AF");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonD7F2D0AE360441B59787E99ED1EFBCC2(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process D7F2D0AE360441B59787E99ED1EFBCC2");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonD7F2D0AE360441B59787E99ED1EFBCC2", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("D7F2D0AE360441B59787E99ED1EFBCC2");
        vars.removeMessage("D7F2D0AE360441B59787E99ED1EFBCC2");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton9E8E9A0881C640A88F0846A961FA6D47(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9E8E9A0881C640A88F0846A961FA6D47");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9E8E9A0881C640A88F0846A961FA6D47", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("9E8E9A0881C640A88F0846A961FA6D47");
        vars.removeMessage("9E8E9A0881C640A88F0846A961FA6D47");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton0474D1A57C294E2BABC306A268DFCCC7(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 0474D1A57C294E2BABC306A268DFCCC7");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton0474D1A57C294E2BABC306A268DFCCC7", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("0474D1A57C294E2BABC306A268DFCCC7");
        vars.removeMessage("0474D1A57C294E2BABC306A268DFCCC7");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonF78498E7CB2D442396D26954D97A47D7(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process F78498E7CB2D442396D26954D97A47D7");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonF78498E7CB2D442396D26954D97A47D7", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("F78498E7CB2D442396D26954D97A47D7");
        vars.removeMessage("F78498E7CB2D442396D26954D97A47D7");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton95DACEED13B34B0280F0740265B38EBB(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 95DACEED13B34B0280F0740265B38EBB");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton95DACEED13B34B0280F0740265B38EBB", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("95DACEED13B34B0280F0740265B38EBB");
        vars.removeMessage("95DACEED13B34B0280F0740265B38EBB");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton2B6ACF25EAFA473D8A5F5F8DCACCDCBC(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 2B6ACF25EAFA473D8A5F5F8DCACCDCBC");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton2B6ACF25EAFA473D8A5F5F8DCACCDCBC", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("2B6ACF25EAFA473D8A5F5F8DCACCDCBC");
        vars.removeMessage("2B6ACF25EAFA473D8A5F5F8DCACCDCBC");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonB31EE95C6F7448848814EDD327E0DC6F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process B31EE95C6F7448848814EDD327E0DC6F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonB31EE95C6F7448848814EDD327E0DC6F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("B31EE95C6F7448848814EDD327E0DC6F");
        vars.removeMessage("B31EE95C6F7448848814EDD327E0DC6F");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton7D846A9CEACA46339BAA5C6A398FD24A(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 7D846A9CEACA46339BAA5C6A398FD24A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton7D846A9CEACA46339BAA5C6A398FD24A", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("7D846A9CEACA46339BAA5C6A398FD24A");
        vars.removeMessage("7D846A9CEACA46339BAA5C6A398FD24A");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton89AC556D92D84B4FA8598B5D1D6228B6(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 89AC556D92D84B4FA8598B5D1D6228B6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton89AC556D92D84B4FA8598B5D1D6228B6", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("89AC556D92D84B4FA8598B5D1D6228B6");
        vars.removeMessage("89AC556D92D84B4FA8598B5D1D6228B6");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton186094D8A8E54C0E915072C90186402A(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 186094D8A8E54C0E915072C90186402A");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton186094D8A8E54C0E915072C90186402A", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("186094D8A8E54C0E915072C90186402A");
        vars.removeMessage("186094D8A8E54C0E915072C90186402A");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton47B1DA21D6DB416FBADDB45F09B74080(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 47B1DA21D6DB416FBADDB45F09B74080");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton47B1DA21D6DB416FBADDB45F09B74080", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("47B1DA21D6DB416FBADDB45F09B74080");
        vars.removeMessage("47B1DA21D6DB416FBADDB45F09B74080");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButtonC4C54930A2AF46238FF38EF7DA17AF18(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process C4C54930A2AF46238FF38EF7DA17AF18");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButtonC4C54930A2AF46238FF38EF7DA17AF18", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("C4C54930A2AF46238FF38EF7DA17AF18");
        vars.removeMessage("C4C54930A2AF46238FF38EF7DA17AF18");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton18F868C1BF9D4F0AB85657C268C84F25(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 18F868C1BF9D4F0AB85657C268C84F25");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton18F868C1BF9D4F0AB85657C268C84F25", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("18F868C1BF9D4F0AB85657C268C84F25");
        vars.removeMessage("18F868C1BF9D4F0AB85657C268C84F25");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton599A7B3AD5334EAFA3DE7A5870517C57(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 599A7B3AD5334EAFA3DE7A5870517C57");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton599A7B3AD5334EAFA3DE7A5870517C57", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("599A7B3AD5334EAFA3DE7A5870517C57");
        vars.removeMessage("599A7B3AD5334EAFA3DE7A5870517C57");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton4D101CF39F0849C891017AB942B9A8B4(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 4D101CF39F0849C891017AB942B9A8B4");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton4D101CF39F0849C891017AB942B9A8B4", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("4D101CF39F0849C891017AB942B9A8B4");
        vars.removeMessage("4D101CF39F0849C891017AB942B9A8B4");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton24C4E55795B741059C4C53C2E2D51CB3(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 24C4E55795B741059C4C53C2E2D51CB3");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton24C4E55795B741059C4C53C2E2D51CB3", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("24C4E55795B741059C4C53C2E2D51CB3");
        vars.removeMessage("24C4E55795B741059C4C53C2E2D51CB3");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton194F2109CB5A4A4E9512FFE9405763AF(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 194F2109CB5A4A4E9512FFE9405763AF");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton194F2109CB5A4A4E9512FFE9405763AF", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("194F2109CB5A4A4E9512FFE9405763AF");
        vars.removeMessage("194F2109CB5A4A4E9512FFE9405763AF");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton50DAA0B5A6954884BF3A028718E8399C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 50DAA0B5A6954884BF3A028718E8399C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton50DAA0B5A6954884BF3A028718E8399C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("50DAA0B5A6954884BF3A028718E8399C");
        vars.removeMessage("50DAA0B5A6954884BF3A028718E8399C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton9C911E091D534F1FA069ECE6E3F0C68C(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 9C911E091D534F1FA069ECE6E3F0C68C");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton9C911E091D534F1FA069ECE6E3F0C68C", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("9C911E091D534F1FA069ECE6E3F0C68C");
        vars.removeMessage("9C911E091D534F1FA069ECE6E3F0C68C");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton2568DC178C5541E4B6194E0C7865F8F1(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 2568DC178C5541E4B6194E0C7865F8F1");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton2568DC178C5541E4B6194E0C7865F8F1", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("2568DC178C5541E4B6194E0C7865F8F1");
        vars.removeMessage("2568DC178C5541E4B6194E0C7865F8F1");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton3078AEC339FA4933900C427A3C5D3759(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3078AEC339FA4933900C427A3C5D3759");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton3078AEC339FA4933900C427A3C5D3759", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("3078AEC339FA4933900C427A3C5D3759");
        vars.removeMessage("3078AEC339FA4933900C427A3C5D3759");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton3BDC78E8DCB14705B2F74B04AA6C56C2(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 3BDC78E8DCB14705B2F74B04AA6C56C2");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton3BDC78E8DCB14705B2F74B04AA6C56C2", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("3BDC78E8DCB14705B2F74B04AA6C56C2");
        vars.removeMessage("3BDC78E8DCB14705B2F74B04AA6C56C2");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton61711504BCDE4690995B0A938D3BA9D6(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 61711504BCDE4690995B0A938D3BA9D6");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton61711504BCDE4690995B0A938D3BA9D6", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("61711504BCDE4690995B0A938D3BA9D6");
        vars.removeMessage("61711504BCDE4690995B0A938D3BA9D6");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton6E87C327B7C94CA78C2B43B0C40CD74F(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 6E87C327B7C94CA78C2B43B0C40CD74F");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton6E87C327B7C94CA78C2B43B0C40CD74F", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("6E87C327B7C94CA78C2B43B0C40CD74F");
        vars.removeMessage("6E87C327B7C94CA78C2B43B0C40CD74F");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }
    void printPageButton096AA9DB1D854D3D875CAB362E6A3CB8(HttpServletResponse response, VariablesSecureApp vars, String strProcessId)
    throws IOException, ServletException {
      log4j.debug("Output: Button process 096AA9DB1D854D3D875CAB362E6A3CB8");
      String[] discard = {"newDiscard"};
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      XmlDocument xmlDocument = xmlEngine.readXmlTemplate("org/openbravo/erpCommon/ad_actionButton/ActionButton096AA9DB1D854D3D875CAB362E6A3CB8", discard).createXmlDocument();
      xmlDocument.setParameter("processing", "Y");
      xmlDocument.setParameter("form", "ActionButtonJava_Responser.html");
      xmlDocument.setParameter("css", vars.getTheme());
      xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
      xmlDocument.setParameter("language", "defaultLang = \"" + vars.getLanguage() + "\";\n");
      xmlDocument.setParameter("cancel", Utility.messageBD(this, "Cancel", vars.getLanguage()));
      xmlDocument.setParameter("ok", Utility.messageBD(this, "OK", vars.getLanguage()));
      xmlDocument.setParameter("processId", strProcessId);
			xmlDocument.setParameter("trlFormType", "PROCESS");
          
      {
        OBError myMessage = vars.getMessage("096AA9DB1D854D3D875CAB362E6A3CB8");
        vars.removeMessage("096AA9DB1D854D3D875CAB362E6A3CB8");
        if (myMessage!=null) {
          xmlDocument.setParameter("messageType", myMessage.getType());
          xmlDocument.setParameter("messageTitle", myMessage.getTitle());
          xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }
      }

          try {
    } catch (Exception ex) {
      throw new ServletException(ex);
    }

      out.println(xmlDocument.print());
      out.close();
    }


    private void process9DB4D30BFC5144B9B431CB49DDE9270D(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.KillSession().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process7CB6B4D1ECCF4036B3F111D2CF11AADE(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strmWarehouseId = vars.getStringParameter("inpmWarehouseId");
params.put("mWarehouseId", strmWarehouseId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.MRPPurchaseCreateReservations().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process970EAD9B846648A7AB1F0CCA5058356C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strname = vars.getStringParameter("inpname");
params.put("name", strname);
String strimportauditinfo = vars.getStringParameter("inpimportauditinfo", "N");
params.put("importauditinfo", strimportauditinfo);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.service.db.ImportClientProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process58763832F5F3485CAD33B8B9FCD6C640(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.UpdateAuditTrail().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process7EDBFEC35BDA4FF4AF05ED516CDAFB90(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.CreateCustomModule().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processABDFC8131D964936AD2EF7E0CED97FD9(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.UpdateActuals().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processEFDBF909811544DAAE4E876AA781E5DC(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.EndYearClose().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processA269DCA4DE114E438695B66E166999B4(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.costing.CostingRuleProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process107(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.materialmgmt.InventoryCountProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processCD7283DF804B449C97DA09446669EEEF(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.ProcessBatch().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process85601427EAEE401FA0250FF0A6DD62EF(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.assets.AssetLinearDepreciationMethodProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processA3FE1F9892394386A49FB707AA50A0FA(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strrecalculateprices = vars.getStringParameter("inprecalculateprices", "N");
params.put("recalculateprices", strrecalculateprices);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.ConvertQuotationIntoOrder().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process136(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.VerifyBOM().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFB740AB61B0E42B198D2C88D3A0D0CE6(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strduedate = vars.getStringParameter("inpduedate");
params.put("duedate", strduedate);
String strfinPaymentPriorityId = vars.getStringParameter("inpfinPaymentPriorityId");
params.put("finPaymentPriorityId", strfinPaymentPriorityId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.UpdatePaymentPlan().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process64B3FF29AC174F4B94538BD0A3CE1CD3(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.costing.CostingMigrationProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processE264309FF8244A94936502BF51829109(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stradClientId = vars.getStringParameter("inpadClientId");
params.put("adClientId", stradClientId);
String stradOrgId = vars.getStringParameter("inpadOrgId");
params.put("adOrgId", stradOrgId);
String stradTableId = vars.getStringParameter("inpadTableId");
params.put("adTableId", stradTableId);
String strdeleteposting = vars.getStringParameter("inpdeleteposting", "N");
params.put("deleteposting", strdeleteposting);
String strdatefrom = vars.getStringParameter("inpdatefrom");
params.put("datefrom", strdatefrom);
String strdateto = vars.getStringParameter("inpdateto");
params.put("dateto", strdateto);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.ResetAccountingProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process23D1B163EC0B41F790CE39BF01DA320E(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strmProductId = vars.getStringParameter("inpmProductId");
params.put("mProductId", strmProductId);
String strreturned = vars.getNumericParameter("inpreturned");
params.put("returned", strreturned);
String strpricestd = vars.getNumericParameter("inppricestd");
params.put("pricestd", strpricestd);
String strcTaxId = vars.getStringParameter("inpcTaxId");
params.put("cTaxId", strcTaxId);
String strcReturnReasonId = vars.getStringParameter("inpcReturnReasonId");
params.put("cReturnReasonId", strcReturnReasonId);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.RMInsertOrphanLine().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process6FBD65B0FDB74D1AB07F0EADF18D48AE(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.MRPManufacturingPlanProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process9EB2228A60684C0DBEC12D5CD8D85218(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_process.CalculatePromotions().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processD85D5B5E368A49B1A6293BA4AE15F0F9(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String stradClientId = vars.getStringParameter("inpadClientId");
params.put("adClientId", stradClientId);
String strexportauditinfo = vars.getStringParameter("inpexportauditinfo", "N");
params.put("exportauditinfo", strexportauditinfo);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.service.db.ExportClientProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF80808133362F6A013336781FCE0066(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.RMCreateInvoice().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF8081813219E68E013219ECFE930004(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strvalue = vars.getStringParameter("inpvalue");
params.put("value", strvalue);
String strname = vars.getStringParameter("inpname");
params.put("name", strname);
String strmProductCategoryId = vars.getStringParameter("inpmProductCategoryId");
params.put("mProductCategoryId", strmProductCategoryId);
String strproductiontype = vars.getStringParameter("inpproductiontype");
params.put("productiontype", strproductiontype);
String strqty = vars.getNumericParameter("inpqty");
params.put("qty", strqty);
String strcopyattribute = vars.getStringParameter("inpcopyattribute", "N");
params.put("copyattribute", strcopyattribute);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.SequenceProductCreate().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF808181324D007801324D2AE1130066(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strdate = vars.getStringParameter("inpdate");
params.put("date", strdate);
String strstarttime = vars.getStringParameter("inpstarttime");
params.put("starttime", strstarttime);
String strendtime = vars.getStringParameter("inpendtime");
params.put("endtime", strendtime);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.CreateWorkEffort().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF808181326CD80501326CE906D70042(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.ValidateWorkEffort_ProductionRun().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF80818132A4F6AD0132A573DD7A0021(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.erpCommon.ad_actionButton.CreateStandards().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process0BDC2164ED3E48539FCEF4D306F29EFD(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_DoubtfulDebtProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process5BE14AA10165490A9ADEFB7532F7FA94(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_AddPaymentFromJournal().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process58A9261BACEF45DDA526F29D8557272D(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_BankStatementProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process15C8708DFC464C2D91286E59624FDD18(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String strcGlitemId = vars.getStringParameter("inpcGlitemId");
params.put("cGlitemId", strcGlitemId);
String strmProductId = vars.getStringParameter("inpmProductId");
params.put("mProductId", strmProductId);
String strcBpartnerId = vars.getStringParameter("inpcBpartnerId");
params.put("cBpartnerId", strcBpartnerId);
String strcProjectId = vars.getStringParameter("inpcProjectId");
params.put("cProjectId", strcProjectId);
String strcCampaignId = vars.getStringParameter("inpcCampaignId");
params.put("cCampaignId", strcCampaignId);
String strcActivityId = vars.getStringParameter("inpcActivityId");
params.put("cActivityId", strcActivityId);
String strcSalesregionId = vars.getStringParameter("inpcSalesregionId");
params.put("cSalesregionId", strcSalesregionId);
String strcCostcenterId = vars.getStringParameter("inpcCostcenterId");
params.put("cCostcenterId", strcCostcenterId);
String struser1Id = vars.getStringParameter("inpuser1Id");
params.put("user1Id", struser1Id);
String struser2Id = vars.getStringParameter("inpuser2Id");
params.put("user2Id", struser2Id);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_TransactionModify().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process017312F51139438A9665775E3B5392A1(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_DoubtfulDebtRunProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process6255BE488882480599C81284B70CD9B3(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_PaymentProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processF68F2890E96D4D85A1DEF0274D105BCE(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_TransactionProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process29D17F515727436DBCE32BC6CA28382B(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);
String strpaymentdate = vars.getStringParameter("inppaymentdate");
params.put("paymentdate", strpaymentdate);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_PaymentProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processDE1B382FDD2540199D223586F6E216D0(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_AddPaymentFromJournalLine().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processD16966FBF9604A3D91A50DC83C6EA8E3(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_PaymentProposalProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF8080812E2F8EAE012E2F94CF470014(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        String straction = vars.getStringParameter("inpaction");
params.put("action", straction);

        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.process.FIN_ReconciliationProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF8080812F348A97012F349DC24F0007(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new org.openbravo.advpaymentmngt.ad_actionbutton.DeleteTransaction().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processEFAFE78815E14788B36DB984F4CC8045(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.RCGI_Create_GI_From_Indent().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process186BA5F122DD448E9A36B57E0115266C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.quality.ad_process.WrapBreakLines().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process2979075A16BA4695ADE3B093725CA876(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHR_AllotmentProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process977F0ED7770440D880A48ECBA456706D(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHR_Loan_Schedule().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process3022A5678A134AD28174B8A9B74B325A(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHR_DeAllotmentProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processEEA62DA04DF44D39B9BCA91BEAE81CED(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.production.ad_process.RCPR_ProductionRun_Create().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process01A90B910CAD4297A26C6E2594669DB4(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.production.ad_process.RCSS_CostingBackgroundProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processA9CBB36555F943ABB417ECD0EB1CC52E(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.production.ad_process.RCPR_ProductionIssue_Post().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process219F25ED92D74A40B3AB51973D01F629(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.production.ad_process.RCPR_ProductionReceiptProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processADE9DCB32A484BA29817A385A292496C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.production.ad_process.RCPR_Create_BOM_Lines().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process68EF40FAB3954363A809F460B81C0C99(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.lotmanagement.ad_process.RCLO_IssueLots().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process8217C6BBED3740F381CA830CB6B67725(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHR_Create_PayrollPreiods().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process1DB34C54D0F2443593FAD5FA8197A14C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.production.ad_process.RCPR_ProductionReceipt_Posting().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process353F95EFE50146DD8A92EC5CD6B65154(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.payroll.ad_actionbutton.RCPL_PayrollProcessCalculation().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process6DB70589347647CDB5C8409320947308(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.RCGI_Create_GI_Lines().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process8EB3A5AC31024491981EBD8D1AE23BFF(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.AttendanceRelayCalculation().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processB9415D7D6DB749DF89BD7DF32466D10B(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.production.ad_process.RCPR_Finalize_BOM().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process542C02D5AC5A45338BA692E346D34D23(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.production.ad_process.RCPR_Generate_Average_Cost().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processDAB5D8627EE5415E9FF2531998E303B6(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.RCGI_GoodsIssue_Post().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processE0A6DA47391A4AFDBF738C7F17607DC0(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.RCGI_Complete_GI().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process8D701FA22B894C5195C3E672661BF26D(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.production.ad_process.RCPR_Finalize_ProductionIssue().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processD54C07F4ACF1454B82CAB6C6DBED27E6(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.production.ad_process.RCPR_MachineMaintenanceLines_Process().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process0637AC192E8C45FFA516DCA4A2FD2743(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.production.ad_process.RCPR_MaintenanceTaskProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processF5C1F8C2F9B1420B8D12223585BC5798(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHR_Process_Policy_Slab().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process02D6B2441A454C739E825963E3DA4ECD(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.lotmanagement.ad_process.RCLO_GetLotReceipts().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process1445812B6F504A9B90BD1630A2A12BA1(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.TenureMonthsCalculation().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process20E70A9D32F644C0BFD3AA1C90566AB3(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.freight.ad_process.RCFR_Calculate_Additional_Costs().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processD39B25EADB224BB59E2A4F758A867D44(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.RCGI_CompleteNew_GI().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process17B03F4D1F1E4184A8245B6168928C48(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.freight.ad_process.RCFR_Calculate_Additional_Costs_CInvoice().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processE802BD1CEBEC4CA5BE4164A328951F61(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.production.ad_process.RCPR_PreventiveOrderMaintenance().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process8D6C70A85BCC4DF1AE626425FE6000F2(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.rcssob.ad_process.RCOB_Process_AgentCommission().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process1C79D5FD586F4D2BA8A239A1E9397982(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.RCGI_CompleteNew_MR().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process9456BD018F8C41D29E5E6908DD1E9958(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.RCGI_MaterialReceipt_Post().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process9FD873E4FCF14633804441249ED4F428(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.RCGI_CompleteNew_MI().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processAE0258D576C24072B07A0476B7571A6E(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.RCGI_MaterialIssue_Post().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process74D8317FBB864522B766BDC6DDE8DD6C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.PurchaseInvoiceFormTracking().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process7CDE00F02E2946FBB7557E1F14BF9EA1(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.SalesInvoiceFormTracking().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process4ED98E07DD4B4B179982F71786D2A7A5(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.UpdatePurchaseInvoiceFormTracking().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processCD8EA8F3D08B457CA39A56D4CAFD81AE(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.UpdateSalesInvoiceFormTracking().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process63194D3CC6114581AA0FA46D3108BA37(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.payroll.ad_actionbutton.RCPL_BookFines().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processC0C01D365E174254891E71EC41B099ED(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHR_ProcessShiftWiseAttendance().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processC19E8C0E72FC415488719F6181C3B5AD(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.payroll.ad_actionbutton.RCPL_ProcessServiceWeightage().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process3A78D32DAFAE4C5EA98BD0A667213369(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHR_ProcessOverTime().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFA292F6205104423A80106ED7FD297CF(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.ChequeBookLeafsCreation().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process9A1023B8C7B3483680A7780CB745817F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.EPCG_ShipmentOrgUpdate().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process25563A6B6BD24D0E9463D87E035BD3EA(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.UserToManagement().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processA4C0B645D60544E3836F437264A4DC79(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.AttendanceShiftLostTimeCalculations().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process15C798457213481BA271CA63353EFE2F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.JUserToManagement().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process1292FC5D518D42758F593EDD4A746766(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.rcssob.ad_process.IndentLinesProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process173F6DB31A1643A985E46CA1F15DBD7A(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.rcssob.ad_process.PurchaseQuotationLinesProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processB683365A463B48BABE7128017C80484B(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.rcssob.ad_process.PurchaseQuotationStatus().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processB33824E7922E42A2A456CAEDBA6830CA(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.rcssob.ad_process.PurchaseRequisitionStatus().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process3ECC743F78A544B2BD119FBB02D05E97(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.rcssob.ad_process.PurchaserequsitionLinesProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process20D40AFB59CF46D8A0F893D08E85911D(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.KappasUserToManagement().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processA36A615253064A95B09A9344C218FA74(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.StrengthAttendanceProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process1454195479614FBFBB8F30755D118B4C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RCHRLeaveApprove().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFFD63BDDDDD94E2991D2AEB30E5E3FDB(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.payroll.ad_process.CalculateReleiverEfficiency().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process5E92917708EF426D9697D88350DCBF14(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RoomRentProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processE865F018CEC7402DB06DB93C6DE30B7F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.payroll.ad_actionbutton.RCPL_DepartmentalStoreApproveProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process8F8FAACEEF9D449C909758519FBD0760(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHRLoanApprove().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processED4FB9D909484DB28578C5808CACA796(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHR_EmployeeOverTimeProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processB76A77639E4B4DAAB097AD11DAE7A1AA(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHRLoanPaid().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process91ABA870C21D44B1B35717F42AB28F27(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RejectionAndSLrej().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processF20F5B75B85E45B997F3853D5B7607C7(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.Employeesettlement().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process2CC36FE2C6974AEC87D1FB0930674F51(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.PieceNoGenerator().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process11F05217E6C84C3EACB57167B91BA11A(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.Extendpiecenos().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process1C6BC6797AAE4AA6ABB1DD98BF41F053(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHR_GasConnection().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processDEC765A7E71445ABB8C7DBDBFB735A13(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.Payrollpaidapproval().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process8E00D1574F384B4A9D95F3ED022F0A47(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.Payrollpaidlist().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processBD1693ECFA6944AB86488F6D12E423E4(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.GetCOffDate().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process1AD75B16E2884E258F3B8EF51039FA49(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RCHR_MobalizerserviceincGetInc().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process93876A2A71E14EB7AFEAF1D05F60F832(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.OverTimePaidApproval().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process80BD458482C842F39419D7D9B9EF00F0(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHR_SecurityDeposit().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process6EEBD3ADA8D945C8B614C641556CEEAD(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.MendingStatusProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process30A9E010447D4B7A983D4DF2EE4CA314(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.CompleteJobCard().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processC545A77357BB4FD69BB6C8646A3D9AF2(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RCHRLeaveComplete().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process4FD494B6B9C5477FAD6E7AD6F35EB1D4(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RCHRLeaveAccept().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processFF1BC80474C0403A98A5E6BCACF6CB3F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.CreateMonths().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processF79126E740EA4C6799CC28D3526D016E(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.GenerateRollNo().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processA49580DFBA994334957B0E398916E97F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.CompleteStatusButton().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process1A0C98271D5F480B98874D9AFDB92EA6(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_actionbuttons.EPCG_CostConfirmProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processA5AB0D40FD7742388B7B17AF8282D2C0(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.EPCG_CostEnquiryMail().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process0630AAC03F3A4EA7BDE267AD12637E7F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.EPCG_CostEnquiryProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processD189003BD6AF4978B9A8506CB1B843B6(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.EPCG_PPCEnquiryProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process26EF860F5FDA4C6B885445D0B381BBE1(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_actionbuttons.EpcgCostEnquiryManagementApproval().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process2ADA7794EC6B4D159060073A62C550A8(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.epcg.ad_process.Epcg_ProformaComplete().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process8402E2D41EBB45AA8EA418B6F0FCF4DB(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.payroll.ad_actionbutton.RcplPayrollProcessConfirmation().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processE038F781230D4505AAADDE1EBFD2573E(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RchrGrievancesComplete().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process309A0C522F8B497FB2FC4174C6EE532E(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RollDoffingCutBeam().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processBF4B81BF72F740E7AE729E07D3FB0663(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.ManualBreakDownBeam().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processADBF99D438B24F299A860D1535AE864A(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.RCHR_EmployeeGetOTProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process84143CAAADE74274B0560AFDE28E7156(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.LeaveProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process921B8092DE21435192CB8B7C6F5E6961(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.LeaveRequest().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processB9F945AFAC0A4FEBBF5B0DCF6420C70B(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.LeaveCancel().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processB198D7B384724E6BB9A30C54B85361AF(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.LeaveApprove().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processD7F2D0AE360441B59787E99ED1EFBCC2(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.OpeningBalanceProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process9E8E9A0881C640A88F0846A961FA6D47(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.util.ApprovalApprove().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process0474D1A57C294E2BABC306A268DFCCC7(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.GetEmployeesFromDaily().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processF78498E7CB2D442396D26954D97A47D7(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.util.ApprovalReject().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process95DACEED13B34B0280F0740265B38EBB(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RchrJoinRejoinForm().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process2B6ACF25EAFA473D8A5F5F8DCACCDCBC(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.TotalLeaveCancel().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processB31EE95C6F7448848814EDD327E0DC6F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.CompensateApprove().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process7D846A9CEACA46339BAA5C6A398FD24A(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.OnDutyProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process89AC556D92D84B4FA8598B5D1D6228B6(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.IndentComplete().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process186094D8A8E54C0E915072C90186402A(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.MaterialIndentFinal().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process47B1DA21D6DB416FBADDB45F09B74080(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.ManualUncheckOverTimeProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void processC4C54930A2AF46238FF38EF7DA17AF18(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.OnDutyApproval().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process18F868C1BF9D4F0AB85657C268C84F25(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RchrRoomAllocation().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process599A7B3AD5334EAFA3DE7A5870517C57(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RchrRoomDeAllotment().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process4D101CF39F0849C891017AB942B9A8B4(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.DepartmentalStoreReceipt().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process24C4E55795B741059C4C53C2E2D51CB3(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.DepartmentalStoreIssues().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process194F2109CB5A4A4E9512FFE9405763AF(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RchrEmployeePermissionAccept().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process50DAA0B5A6954884BF3A028718E8399C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RchrEmployeePermissionReject().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process9C911E091D534F1FA069ECE6E3F0C68C(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_actionButtons.RchrProcessHalfDay().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process2568DC178C5541E4B6194E0C7865F8F1(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.redcarpet.goodsissue.ad_process.UpdateDepartmentalStoreReceipt().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process3078AEC339FA4933900C427A3C5D3759(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.BankSalaryPaymentsProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process3BDC78E8DCB14705B2F74B04AA6C56C2(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.BankSalaryPaymentsPaid().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process61711504BCDE4690995B0A938D3BA9D6(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.BankSalaryPaymentsReject().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process6E87C327B7C94CA78C2B43B0C40CD74F(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.BankSalaryPaymentsApprove().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }
    private void process096AA9DB1D854D3D875CAB362E6A3CB8(String strProcessId, VariablesSecureApp vars, HttpServletRequest request, HttpServletResponse response) throws IOException,
      ServletException {
        
        
        ProcessBundle pb = new ProcessBundle(strProcessId, vars).init(this);
        HashMap<String, Object> params= new HashMap<String, Object>();
       
        
        
        pb.setParams(params);
        OBError myMessage = null;
        try {
          new com.rcss.humanresource.ad_process.ExBankSalaryPaymentsProcess().execute(pb);
          myMessage = (OBError) pb.getResult();
          myMessage.setMessage(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getMessage()));
          myMessage.setTitle(Utility.parseTranslation(this, vars, vars.getLanguage(), myMessage.getTitle()));
        } catch (Exception ex) {
          myMessage = Utility.translateError(this, vars, vars.getLanguage(), ex.getMessage());
          log4j.error(ex);
          if (!myMessage.isConnectionAvailable()) {
            bdErrorConnection(response);
            return;
          }
        }

        processButtonHelper(request, response, vars, myMessage); 
   }


  public String getServletInfo() {
    return "Servlet ActionButton_Responser. This Servlet was made by Wad constructor";
  } // end of the getServletInfo() method

  private void processButtonHelper(HttpServletRequest request, HttpServletResponse response, VariablesSecureApp vars, OBError myMessage) 
     throws ServletException, IOException {
      advisePopUp(request, response, myMessage.getType(), myMessage.getTitle(), myMessage.getMessage());
  }
}
