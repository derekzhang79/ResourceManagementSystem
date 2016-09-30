<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gits.rms.action.utils.ClientConstants"%>
<% request.setAttribute("APPL_TARGET_TYPE_LIST", ClientConstants.getApplicationConstant("APPL_TARGET_TYPE_LIST")); %>
<s:select 
  cssClass="employeeselect" 
  headerKey=""
  headerValue="-- Please Select --"
  list="#request.APPL_TARGET_TYPE_LIST"  
  name="tsProjAssigned.projTargetType"
  id="ProjTargetType"
  onchange="getTargetMode(this);"
  />