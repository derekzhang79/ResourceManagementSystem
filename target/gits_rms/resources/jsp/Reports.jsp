<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.action.ReportsAction"%>

<s:form>
	<table align="center" class="borderAll">
		<tr>
			<th><s:text name="label.header.leaveType.name" /></th>
			<th><s:text name="label.header.common.dateApplied" /></th>
			<th><s:text name="label.header.leaveHistory.leaveStatus" /></th>
			<th><s:text name="label.header.common.comments" /></th>
		</tr>
		<s:iterator value="leaveReportList" status="status">
			<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
				<td class="nowrap"><s:property value="leaveTypeIdObj.leaveTypeName" /></td>
				<td class="nowrap"><s:property value="leaveDate" /></td>
				<td class="nowrap"><s:property value="leaveStatus" /></td>
				<td class="nowrap"><s:property value="leaveComments" /></td>
			</tr>
		</s:iterator>
	</table>
	</s:form>