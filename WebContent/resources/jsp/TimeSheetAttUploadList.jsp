<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<jsp:include page="common/messages.jsp" flush="true"/>
<div id="timesheeetFileAttachementResultDivid">
	<table align="center">
		<tr><td class="formtitle"><s:text name="label.title.TimesheetFileUplad"></s:text></td></tr>
		<tr align="center"><td class="inputerrormessage">
			<s:form id="timesheetFileAttachementFormId" method="post" enctype="multipart/form-data">
				<div align="center">
					<s:hidden name="employeeId" value="%{#session.EmployeeId}"></s:hidden>
					<s:file name="upload"></s:file>
					<s:text name="label.header.showFormat.timesheet.fileTypeToAttach"/><br></br>
					<s:submit key="button.label.submit" cssClass="submitbutton117" value="Upload" onclick="doPostCallWithFileUpload('timesheeetFileAttachementResultDivid','fileUploadAction.action','timesheetFileAttachementFormId');return false;"/>
				</div>
			</s:form></td>
			<td class="inputerrormessage"><s:fielderror name="upload"></s:fielderror></td>
		</tr>	
	  </table>
</div>  

