<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<s:url var="getSubTabDeductions" action="getAllDeductions"/>
	<sj:tabbedpanel id="remoteTabbedPannelDeductions" animate="true">
		<s:if test="#session.DEDUCTION_ADD == true || #session.DEDUCTION_VIEW == true">
			<sj:tab id="DeductionsSub" key="MTDeductions" href="%{getSubTabDeductions}"/>
		</s:if>
	</sj:tabbedpanel>