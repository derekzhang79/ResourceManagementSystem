<%@ taglib prefix="s" uri="/struts-tags"%>


<table>
	<tr><td><s:text name="label.header.dashboardToday"></s:text>(<s:property value="forApprovalToday"/>)</td></tr>
	<tr><td><s:text name="label.header.dashboardInLastThreeDays"></s:text>(<s:property value="forApprovalThreeDays"/>)</td></tr>
	<tr><td><s:text name="label.header.dashboardInLastOneWeek"></s:text>(<s:property value="forApprovalOneWeek"/>)</td></tr>
	<tr><td><s:text name="label.header.dashboardTotal"></s:text>(<s:property value="forApprovalCount"/>)</td></tr>
</table>