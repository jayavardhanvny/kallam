package com.rcss.humanresource.ad_forms;

import com.rcss.humanresource.util.AttendanceImporter;
import com.rcss.humanresource.util.IAttendanceFileData;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openbravo.base.secureApp.HttpSecureAppServlet;
import org.openbravo.base.secureApp.VariablesSecureApp;
import org.openbravo.erpCommon.businessUtility.WindowTabs;
import org.openbravo.erpCommon.utility.LeftTabsBar;
import org.openbravo.erpCommon.utility.NavigationBar;
import org.openbravo.erpCommon.utility.OBError;
import org.openbravo.erpCommon.utility.ToolBar;
import org.openbravo.xmlEngine.XmlDocument;

public class RCHR_Attendance_Upload extends HttpSecureAppServlet {

    private static final long serialVersionUID = 2L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        VariablesSecureApp vars = new VariablesSecureApp(request);

        if (vars.commandIn("DEFAULT")) {
            String strFirstLineHeader = vars.getStringParameter("inpFirstLineHeader");
            org.apache.commons.fileupload.FileItem filePath = vars.getMultiFile("inpFile");
            printPage(response, vars, strFirstLineHeader, vars.getCommand(), "", null);

        } else if (vars.commandIn("FIND")) {
            String strFirstLineHeader = vars.getStringParameter("inpFirstLineHeader");
            org.apache.commons.fileupload.FileItem filePath = vars.getMultiFile("inpFile");
            Scanner scan = new Scanner(filePath.getInputStream());
            scan.useDelimiter("\n");
            scan.next();
            int rowCount = 0;
            int totalRows = 0;
            while (scan.hasNext()) {
                totalRows++;
                IAttendanceFileData dataRow = new AttendanceImporter(scan.next()).getRow();

                try {
                    rowCount += RCHRAttendanceUploadData.insert(this, vars.getClient(), vars.getOrg(),
                            dataRow.getAttendanceDate(), dataRow.getIsPresent(), dataRow.getEmployee(), dataRow.getIsLate(),
                            dataRow.getWeekOff(), dataRow.getIsShiftChangeAbsent(), dataRow.getOverTimeShift(),
                            dataRow.getNoWork(), dataRow.getNightShift(), dataRow.getShiftId(), dataRow.getLateTimeinMins());
                } catch (Exception e) {
                    OBError error = new OBError();
                    error.setTitle("Error");
                    error.setType("Error");
                    error.setMessage("Please check for errors in csv file");
                    printPage(response, vars, strFirstLineHeader, vars.getCommand(), "", error);
                }
            }
            OBError error = new OBError();
            error.setTitle("Success");
            error.setType("Success");
            error.setMessage("No. of rows imported =" + rowCount);
            printPage(response, vars, strFirstLineHeader, vars.getCommand(), "", error);
        } else {
            pageError(response);
        }

    }

    private void printPage(HttpServletResponse response, VariablesSecureApp vars,
            String strFirstLineHeader, String strCommand, String strFile, OBError myMessage)
            throws IOException, ServletException {

        XmlDocument xmlDocument = null;
        xmlDocument = xmlEngine.readXmlTemplate("com/rcss/humanresource/ad_forms/RCHR_Attendance_Upload")
                .createXmlDocument();
        ToolBar toolbar = new ToolBar(this, vars.getLanguage(), "Attendance Process", false, "", "", "", false,
                "ad_forms", strReplaceWith, false, true);
        toolbar.prepareSimpleToolBarTemplate();
        toolbar.removeElement("REFRESH");
        xmlDocument.setParameter("toolbar", toolbar.toString());

        try {
            WindowTabs tabs = new WindowTabs(this, vars, "com.rcss.humanresource.ad_forms.RCHR_Attendance_Upload");
            xmlDocument.setParameter("parentTabContainer", tabs.parentTabs());
            xmlDocument.setParameter("mainTabContainer", tabs.mainTabs());
            xmlDocument.setParameter("childTabContainer", tabs.childTabs());
            NavigationBar nav = new NavigationBar(this, vars.getLanguage(), "RCHR_Attendance_Upload.html",
                    classInfo.id, classInfo.type, strReplaceWith, tabs.breadcrumb());
            xmlDocument.setParameter("navigationBar", nav.toString());
            LeftTabsBar lBar = new LeftTabsBar(this, vars.getLanguage(), "RCHR_Attendance_Upload.html",
                    strReplaceWith);
            xmlDocument.setParameter("leftTabs", lBar.manualTemplate());
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

        xmlDocument.setParameter("theme", vars.getTheme());

        if (myMessage != null) {
            xmlDocument.setParameter("messageType", myMessage.getType());
            xmlDocument.setParameter("messageTitle", myMessage.getTitle());
            xmlDocument.setParameter("messageMessage", myMessage.getMessage());
        }

        xmlDocument.setParameter("directory", "var baseDirectory = \"" + strReplaceWith + "/\";\n");
        xmlDocument.setParameter("language", "defaultLang=\"" + vars.getLanguage() + "\";");
        xmlDocument.setParameter("firstLineHeader", strFirstLineHeader);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(xmlDocument.print());
        out.close();
    }

    public String getServletInfo() {
        return "Servlet that presents the files-importing process";
    }

}
