<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		
		<s:set name="OrgChartEmployeeId" value="orgChartEmployeeID"></s:set>
		<s:url var="orgChartEmployeeExpenseApproverNewTab" action="orgChartEmployeeExpenseApproverNewTab">
			<s:param name="employee.employeeId" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		<s:url var="orgChartSubEmployeeExpensesApproverNewTab" action="orgChartSubEmployeeExpensesApproverNewTab">
			<s:param name="employee.employeeId" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		
		
		<sj:tabbedpanel id="orgChartExpenseApproverNewTabbedpannel" animate="true">
			<sj:tab id="OrgChartExpenseApproverNewSubTab" key="Approver" href="%{orgChartEmployeeExpenseApproverNewTab}"/>
			<s:if test="expenseSelfApp!=null || expenseAppDis!=null">
				<sj:tab id="OrgChartExpenseApproverSubEmployeeNewSubTab" key="Sub Employee" href="%{orgChartSubEmployeeExpensesApproverNewTab}"/>
			</s:if>
		</sj:tabbedpanel>