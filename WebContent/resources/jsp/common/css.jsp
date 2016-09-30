<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gits.rms.action.utils.ClientConstants"%>

<%
	if(session.getAttribute(Constants.APPL_THEME)==null){
		session.setAttribute(Constants.APPL_THEME,"black");
	}

	if(session.getAttribute(Constants.APPL_THEME).equals(new String("blue"))){
%>

<%@page import="com.gits.rms.constants.Constants"%><link href="<s:url value="/resources/css/default/blue/main.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="/resources/css/default/blue/main.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="/resources/css/default/blue/sidemenu.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="/resources/css/default/blue/component.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="/resources/css/default/blue/component_2.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="/resources/css/default/blue/base.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="/resources/css/default/blue/screen.css"/>" rel="stylesheet" type="text/css"/>

<%
	}
	if(session.getAttribute(Constants.APPL_THEME).equals(new String("black"))){
%>
<link href="<s:url value="/resources/css/default/black/main.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="/resources/css/default/black/sidemenu.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="/resources/css/default/black/component.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="/resources/css/default/black/component_2.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="/resources/css/default/black/base.css"/>" rel="stylesheet" type="text/css"/>
<link href="<s:url value="/resources/css/default/black/screen.css"/>" rel="stylesheet" type="text/css"/>

 <style>
	.ui-autocomplete {
		max-height: 100px;
		overflow-y: auto;
		/* prevent horizontal scrollbar */
		overflow-x: hidden;
		/* add padding to account for vertical scrollbar */
		padding-right: 20px;
		font: 11px Verdana, Geneva, Arial, Helvetica, sans-serif;
		color: #102132;
	}
	/* IE 6 doesn't support max-height
	 * we use height instead, but this forces the menu to always be this tall
	 */
	* html .ui-autocomplete {
		height: 100px;
	}
	
	.ui-autocomplete-input{
		width: 160px;
	}
	
	</style>

<%
	}
%>