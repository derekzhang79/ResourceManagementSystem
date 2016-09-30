<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div class="titleDiv"><s:text name="application.title" /></div>
<div class="informationMessageSingle"><li><span><s:text name="label.leaveReqsApproval" /></span></li></div>


<table align="center" class="borderAll">
	<tr>	
		<th><s:text name="label.header.common.empName" /></th>
		<th><s:text name="label.header.leaveType.name" /></th>
		<th><s:text name="label.header.lrapp.dateApplied" /></th>
		<th><s:text name="label.header.lrapp.noOfDays" /></th>
		<!-- <th><s:text name="label.header.lrapp.dateApprovDisApprov" /></th>-->
		<th><s:text name="label.header.lrapp.status" /></th>
		<th><s:text name="label.header.common.comments" /></th>
		
		 
        
		<th>&nbsp;</th>
	</tr>

	<s:iterator value="lrappList" status="status">
		<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
			
			<td class="nowrap"><s:property value="empIdObj.empFirstName" /></td>
			<td class="nowrap"><s:property value="leaveTypeIdObj.leaveTypeName" /></td>
			<td class="nowrap"><s:property value="dateApplied" /></td>
				<td class="nowrap"><s:property value="noOfDays" /></td>
			<!-- <td class="nowrap"><s:property value="dateApprDisappr" /></td>-->
			<td class="nowrap"><s:property value="leaveReqStatus" /></td>
			<td class="nowrap"><s:property value="comments" /></td>
			
			 <s:if test="#session.ROLE=='Manager'">
			<td class="nowrap">
			
			<!--<s:url var="setUpLeaveReqsApproval" action="setUpLeaveReqsApproval">
				<s:param name="lrapp.hcmoLeaveReqsApprovalId" value="hcmoLeaveReqsApprovalId" />
			</s:url>
			<s:a href="%{setUpLeaveReqsApproval}">Edit</s:a> &nbsp;&nbsp;&nbsp;
			 --> 
			<s:url var="leaveRequestapproved" action="leaveRequestapproved">
				<s:param name="lrapp.hcmoLeaveReqsApprovalId" value="hcmoLeaveReqsApprovalId" />
				<s:param name="lrapp.noOfDays" value="noOfDays" />
			</s:url>
			<s:a href="%{leaveRequestapproved}"><s:text name="label.common.link.approve"/></s:a> &nbsp;&nbsp;&nbsp;
			</td> 
			<td class="nowrap">
			<s:url var="leaveRequestdisapproved" action="leaveRequestdisapproved">
				<s:param name="lrapp.hcmoLeaveReqsApprovalId" value="hcmoLeaveReqsApprovalId" />
			</s:url> 
			<s:a href="%{leaveRequestdisapproved}"><s:text name="label.common.link.disapprove"/></s:a>
			</td>
			</s:if>
		</tr>
	</s:iterator>
</table>