package com.rcss.humanresource.util;

import com.rcss.humanresource.ad_forms.RCHRAttendanceUploadData;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author S.A. Mateen
 */
public class AttendanceImporter {

    private String row;

    /**
     *
     * @param row: takes a row input from CSV
     */
    public AttendanceImporter(String row) {
        this.row = row;
    }

    public IAttendanceFileData getRow() {
        IAttendanceFileData dataRow = getAttendanceFileDataObj(row.split(";"));
        return dataRow;
    }

    private IAttendanceFileData getAttendanceFileDataObj(String[] arr) {
        String strOrg = "";
        String strDate = "";
        String strEmployee = "";
        String strShift = "";
        String strLateInMins = "0";
        String strPresent = "N";
        String strLate = "N";
        String strWeekOff = "N";
        String strSCA = "N";
        String strOTS = "N";
        String strNoWork = "N";
        String strNightShift = "N";
        try {
            strOrg = (!StringUtils.isEmpty(arr[0])) ? arr[0] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            strDate = (!StringUtils.isEmpty(arr[1])) ? arr[1] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            strEmployee = (!StringUtils.isEmpty(arr[2])) ? arr[2] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            strShift = (!StringUtils.isEmpty(arr[3])) ? arr[3] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            strLateInMins = (!StringUtils.isEmpty(arr[4])) ? arr[4] : "0";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            strPresent = (!StringUtils.isEmpty(arr[5])) ? arr[5] : "N";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            strLate = (!StringUtils.isEmpty(arr[6])) ? arr[6] : "N";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            strWeekOff = (!StringUtils.isEmpty(arr[7])) ? arr[7] : "N";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            strSCA = (!StringUtils.isEmpty(arr[8])) ? arr[8] : "N";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            strOTS = (!StringUtils.isEmpty(arr[9])) ? arr[9] : "N";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            strNoWork = (!StringUtils.isEmpty(arr[10])) ? arr[10] : "N";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
            strNightShift = (!StringUtils.isEmpty(arr[11])) ? arr[11] : "N";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        return new IAttendanceFileData(strOrg, strDate, strEmployee, strShift, strLateInMins, strPresent, strLate, strWeekOff, strSCA, strOTS, strNoWork, strNightShift);
    }
}
