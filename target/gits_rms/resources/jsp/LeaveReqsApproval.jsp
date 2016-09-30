<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>

<jsp:include page="common/messages.jsp" flush="true"/>
<center>
<div class="informationMessageSingle"><li><span><s:text name="label.leaveReqsApproval" /></span></li></div>

<table align="center" class="borderAll">
	<tr>	
		<th><s:text name="label.header.common.empName" /></th>
		<th><s:text name="label.header.leaveType.name" /></th>
		<th><s:text name="label.header.common.dateApplied" /></th>
		<th><s:text name="label.header.common.noOfDays" /></th>
		<th><s:text name="label.header.lrapp.dateApprovDisApprov" /></th> 
		<th><s:text name="label.header.lrapp.status" /></th>
		<th><s:text name="label.header.common.comments" /></th>
	</tr>

	<s:iterator value="lrappList" status="status">
		<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
			
			<td class="nowrap"><s:property value="empIdObj.empFullName" /></td>
			<td class="nowrap"><s:property value="leaveTypeIdObj.leaveTypeName" /></td>
			<td class="nowrap"><s:property value="dateApplied" /></td>
				<td class="nowrap"><s:property value="noOfDays" /></td>
			<td class="nowrap"><s:property value="dateApprDisappr" /></td>
			<td class="nowrap"><s:property value="leaveReqStatus" /></td>
			<td class="nowrap"><s:property value="comments" /></td>

		</tr>
	</s:iterator>
</table>