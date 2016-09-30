<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.gits.rms.action.utils.ClientConstants" %>
<% request.setAttribute("APPL_GOALNAME_LIST", ClientConstants.getApplicationConstant("APPL_GOALNAME_LIST")); %>
<jsp:include page="common/messages.jsp" flush="true"/>
<s:optiontransferselect 
		cssClass="optionselectcss" 
		id="goalNameList" 
		name="goalNameList" 
		list="#request.APPL_GOALNAME_LIST" 
		doubleCssClass="optionselectcss" 
		doubleList="selectedGoalList" 
   		doubleName="selectedGoalList" 
   		doubleId="selectedGoalList" 
   		addAllToLeftLabel="Remove All" 
   		addAllToRightLabel="Add All"
   		addToRightLabel="Add" 
   		addToLeftLabel="Remove" 
   		rightTitle="Selected Goal(s)" 
   		leftTitle="Available Goal(s)" 
   		allowSelectAll="false" 
   		allowUpDownOnLeft="false" 
   		allowUpDownOnRight="false" 
   		buttonCssClass="optionselectbuttoncss">
</s:optiontransferselect>
