<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="com.gits.rms.action.utils.ClientConstants"%>

<% request.setAttribute("APPL_SUB_CATEGORY_LIST", ClientConstants.getApplicationConstant("APPL_SUB_CATEGORY_LIST")); %>

<s:select 
  headerKey=""
  headerValue="-- Please Select --"
  list="#request.APPL_SUB_CATEGORY_LIST"
  listKey="hcmoSubCategoryId"
  listValue="subCategoryName"
  name="question.hcmoSubCategoryId.hcmoSubCategoryId"
  />
  <td class="inputerrormessage"><s:fielderror fieldName="question.hcmoSubCategoryId.hcmoSubCategoryId" /></td>