<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gits.rms.action.utils.ClientConstants"%>

<% request.setAttribute("APPL_ACTIVITY_LIST", ClientConstants.getApplicationConstant("APPL_ACTIVITY_LIST")); %>

<s:select 
   headerKey=""
   headerValue="-- Please Select --"
   list="#request.APPL_ACTIVITY_LIST"  
   listKey="projectActivityId"
   listValue="activityName"
   name="activity"
  />
 