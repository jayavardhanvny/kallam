package com.rcss.humanresource.util;

import com.rcss.humanresource.ad_forms.RCHRAttendanceUploadData;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author K. Vinay
 */
public class LoomsProductionImporter {

    private String row;

    /**
     *
     * @param row: takes a row input from CSV
     */
    public LoomsProductionImporter(String row) {
        this.row = row;
    }

    public ILoomsProductionData getRow() {
    	ILoomsProductionData dataRow = getLoomsProductionDataObj(row.split(","));
    	System.out.println("In Get Row ++++++++...");
        return dataRow;
    }

    private ILoomsProductionData getLoomsProductionDataObj(String[] arr) {
        //String strOrg = "";
        String strDate = "";
        String strEmployee = "";
        String strShift = "";
        String loomName = "";
        String sortNo = "";
        String ppi = "";
        String picks = "";
        String loomMts = "";
        String rpm = "";
        String efficiency = "";
        String warpBreaks = "";
        String weftBreaks = "";
        String otherBreaks = "";
        /*try {
            strOrg = (!StringUtils.isEmpty(arr[0])) ? arr[0] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }*/
        
        try {
            strDate = (!StringUtils.isEmpty(arr[0])) ? arr[0] : "";
            System.out.println("strDate++++++++..."+strDate);
        } catch (ArrayIndexOutOfBoundsException ex) {
        	
        }
        /*try {
            strEmployee = (!StringUtils.isEmpty(arr[2])) ? arr[1] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }*/
        
        try {
            strShift = (!StringUtils.isEmpty(arr[1])) ? arr[1] : "";
            
            System.out.println("strShift++++++++..."+strShift);
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        
        try {
        	loomName = (!StringUtils.isEmpty(arr[2])) ? arr[2] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
        	sortNo = (!StringUtils.isEmpty(arr[3])) ? arr[3] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
        	ppi = (!StringUtils.isEmpty(arr[4])) ? arr[4] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
        	picks = (!StringUtils.isEmpty(arr[5])) ? arr[5] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
        	loomMts = (!StringUtils.isEmpty(arr[6])) ? arr[6] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
        	rpm = (!StringUtils.isEmpty(arr[7])) ? arr[7] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
        	efficiency = (!StringUtils.isEmpty(arr[8])) ? arr[8] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
        	warpBreaks = (!StringUtils.isEmpty(arr[9])) ? arr[9] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
        	weftBreaks = (!StringUtils.isEmpty(arr[10])) ? arr[10] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        try {
        	otherBreaks = (!StringUtils.isEmpty(arr[11])) ? arr[11] : "";
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
        return new ILoomsProductionData(strDate, strShift, loomName, sortNo, ppi, picks, loomMts, rpm, efficiency, warpBreaks, weftBreaks, otherBreaks);
    }
}
