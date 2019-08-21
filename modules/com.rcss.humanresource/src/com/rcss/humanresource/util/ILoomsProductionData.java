package com.rcss.humanresource.util;

/**
 *
 * @author K. Vinay
 */
public class ILoomsProductionData {
 
    
    private String org;
    private String loomsDate;
    private String shift;
    private String loomName;
    private String sortNo;
    private String ppi;
    private String picks;
    private String loomMts;
    private String rpm;
    private String efficiency;
    private String warpBreaks;
    private String weftBreaks;
    private String otherBreaks;
    
    
    public ILoomsProductionData(String loomsDate, String shift, String loomName, String sortNo, String ppi, String picks, String loomMts,
    		String rpm, String efficiency, String warpBreaks, String weftBreaks,String otherBreaks) {
        //this.org = org;
        this.loomsDate = loomsDate;
        this.shift = shift;
        this.loomName = loomName;
        this.sortNo = sortNo;
        this.ppi = ppi;
        this.picks = picks;
        this.loomMts = loomMts;
        this.rpm = rpm;
        this.efficiency = efficiency;
        this.warpBreaks = warpBreaks;
        this.weftBreaks = weftBreaks;
        this.otherBreaks = otherBreaks;
    }

    /*public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }
*/
    public String getLoomsDate() {
        return loomsDate;
    }

    public void setLoomsDate(String loomsDate) {
        this.loomsDate = loomsDate;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getLoomName() {
        return loomName;
    }

    public void setLoomName(String loomName) {
        this.loomName = loomName;
    }

    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }
    
    public String getPPI() {
        return ppi;
    }

    public void setPPI(String ppi) {
        this.ppi = ppi;
    }
    
  
    public String getPicks() {
        return picks;
    }

    public void setPicks(String picks) {
        this.picks = picks;
    }
    
    
    public String getLoomMts() {
        return loomMts;
    }

    public void setLoomMts(String loomMts) {
        this.loomMts = loomMts;
    }
    
    
    public String getRPM() {
        return rpm;
    }

    public void setRPM(String rpm) {
        this.rpm = rpm;
    }
    
    
    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }
    
    
    public String getWarpBreaks() {
        return warpBreaks;
    }

    public void setWarpBreaks(String warpBreaks) {
        this.warpBreaks = warpBreaks;
    }
    
    
    public String getWeftBreaks() {
        return weftBreaks;
    }

    public void setWeftBreaks(String weftBreaks) {
        this.weftBreaks = weftBreaks;
    }
    
    
    public String getOtherBreaks() {
        return otherBreaks;
    }

    public void setOtherBreaks(String otherBreaks) {
        this.otherBreaks = otherBreaks;
    }
    
          

    @Override
    public String toString() {
        return "ILoomsProductionData{" + "loomsDate=" + loomsDate + ", shift=" + shift + ", loomName=" + loomName + ", sortNo=" + sortNo + ", ppi=" + ppi + ", picks=" + picks + ", loomMts=" + loomMts + ", rpm=" + rpm + ", efficiency=" + efficiency + ", warpBreaks=" + warpBreaks + ", weftBreaks=" + weftBreaks + ", otherBreaks=" + otherBreaks + '}';
    }


    
}
