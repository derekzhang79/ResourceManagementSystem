
package com.gits.rms.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.vo.ThemeVO;

public class JSONAction extends ActionSupport {
    private static final long serialVersionUID = -999102550053700224L;
    private List<String> searchProcessList;
    private List<String> maritalStatusList;
    private List<String> msgTypeList;
    private List<String> leaveStatusList;
    private List<String> timesheetStatusList;
    private List<String> empSpaceFileTypeList;
    private Map<String, String> themes;
    private ThemeVO themeObj;

    // To get All Search Process List
    @SkipValidation
    public String getSearchProcessJSONList() {
        searchProcessList = new ArrayList<String>();
        searchProcessList.add("EqualTo");
        searchProcessList.add("Between");
        searchProcessList.add("Greater,Equals");
        searchProcessList.add("Lesser,Equals");
        return SUCCESS;
    }
    
 // To get All File Type List
    @SkipValidation
    public String getEmpSpaceFileTypeJSONList() {
        empSpaceFileTypeList = new ArrayList<String>();
        empSpaceFileTypeList.add("My File");
        empSpaceFileTypeList.add("Shared By Me");
        empSpaceFileTypeList.add("Shared To Me");
        return SUCCESS;
    }

    // To get All Marital Status List
    @SkipValidation
    public String getMaritalStatusJSONList() {
        maritalStatusList = new ArrayList<String>();
        maritalStatusList.add("Annulled");
        maritalStatusList.add("Cohabitating");
        maritalStatusList.add("Divorced");
        maritalStatusList.add("Engaged");
        maritalStatusList.add("Married");
        maritalStatusList.add("Single");
        maritalStatusList.add("Separated");
        maritalStatusList.add("Widowed");
        return SUCCESS;
    }

    // To get All MSG Type List
    @SkipValidation
    public String getMSGTypeJSONList() {
        msgTypeList = new ArrayList<String>();
        msgTypeList.add("Alert");
        msgTypeList.add("Broadcast");
        msgTypeList.add("Message");
        return SUCCESS;
    }

    // To get All Leave Assign Status List
    @SkipValidation
    public String getLeaveAssignStatusJSONList() {
        leaveStatusList = new ArrayList<String>();
        leaveStatusList.add("Assigned");
        leaveStatusList.add("Approved");
        leaveStatusList.add("Cancel");
        leaveStatusList.add("DisApproved");
        leaveStatusList.add("For Approval");
        return SUCCESS;
    }

    // To get All Timesheet Status List
    @SkipValidation
    public String getTimesheetStatusJSONList() {
        timesheetStatusList = new ArrayList<String>();
        timesheetStatusList.add("Approved");
        timesheetStatusList.add("Rejected");
        timesheetStatusList.add("Rework");
        return SUCCESS;
    }

    // To get All MSG Type List
    @SkipValidation
    public String getSelectedTheme() {
        themes = new HashMap<String, String>();
        themes.put("cupertino", "The cupertino Theme");
        themes.put("ui-darkness", "The darkness Theme");
        themes.put("ui-lightness", "The lightness Theme");
        themes.put("redmond", "The redmond Theme");
        themes.put("smoothness", "The smoothness Theme");
        themes.put("black-tie", "The black-tie Theme");
        themes.put("blitzer", "The blitzer Theme");
        themes.put("dark-hive", "The dark-hive Theme");
        themes.put("dot-luv", "The dot-luv Theme");
        themes.put("eggplant", "The eggplant Theme");
        themes.put("excite-bike", "The excite-bike Theme");
        themes.put("flick", "The flick Theme");
        themes.put("hot-sneaks", "The hot-sneaks Theme");
        themes.put("humanity", "The humanity Theme");
        themes.put("le-frog", "The le-frog Theme");
        themes.put("mint-choc", "The mint-choc Theme");
        themes.put("overcast", "The overcast Theme");
        themes.put("pepper-grinder", "The pepper-grinder Theme");
        themes.put("south-street", "The south-street Theme");
        themes.put("start", "The start Theme");
        themes.put("sunny", "The sunny Theme");
        themes.put("swanky-purse", "The swanky-purse Theme");
        themes.put("trontastic", "The trontastic Theme");
        themes.put("vader", "The vader Theme");

        return SUCCESS;
    }

    // To get All MSG Type List
    @SkipValidation
    public String applyJqueryTheme() {
        Map session = ActionContext.getContext().getSession();
        session.put("JQUERY_THEME", themeObj.getThemeName());
        return SUCCESS;

    }

    public List<String> getSearchProcessList() {
        return searchProcessList;
    }

    public void setSearchProcessList(List<String> searchProcessList) {
        this.searchProcessList = searchProcessList;
    }

    public List<String> getMaritalStatusList() {
        return maritalStatusList;
    }

    public void setMaritalStatusList(List<String> maritalStatusList) {
        this.maritalStatusList = maritalStatusList;
    }

    public List<String> getMsgTypeList() {
        return msgTypeList;
    }

    public void setMsgTypeList(List<String> msgTypeList) {
        this.msgTypeList = msgTypeList;
    }

    public List<String> getLeaveStatusList() {
        return leaveStatusList;
    }

    public void setLeaveStatusList(List<String> leaveStatusList) {
        this.leaveStatusList = leaveStatusList;
    }

    public List<String> getTimesheetStatusList() {
        return timesheetStatusList;
    }

    public void setTimesheetStatusList(List<String> timesheetStatusList) {
        this.timesheetStatusList = timesheetStatusList;
    }

    public Map<String, String> getThemes() {
        return themes;
    }

    public void setThemes(Map<String, String> themes) {
        this.themes = themes;
    }

    public ThemeVO getThemeObj() {
        return themeObj;
    }

    public void setThemeObj(ThemeVO themeObj) {
        this.themeObj = themeObj;
    }

    public List<String> getEmpSpaceFileTypeList() {
        return empSpaceFileTypeList;
    }

    public void setEmpSpaceFileTypeList(List<String> empSpaceFileTypeList) {
        this.empSpaceFileTypeList = empSpaceFileTypeList;
    }
    
}
