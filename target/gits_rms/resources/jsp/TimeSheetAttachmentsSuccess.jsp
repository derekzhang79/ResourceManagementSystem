<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="timeSheetAttachList.size > 0">
<s:iterator value="timeSheetAttachList" status="status">
<s:form action="deleteTimeSheetAttachments" method="POST" onsubmit="return AIM.submit(this, {'onStart' : startCallbackForTimeSheetDelete, 'onComplete' : completeCallbackForTimeSheetDelete})">
<table >

	<tr>
		<td class="tsinputth">
			<s:text name="label.title.timesheetattach.upload.msg"/>
		</td>
	</tr>
	<tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		<td>
		<s:url var="timesheetFileDownload" action="timesheetFileDownload">
               <s:param name="tsAttach.hcmoTsAttachmentId" value="hcmoTsAttachmentId"/>
           </s:url>
                <s:a href="%{timesheetFileDownload}" ><s:property value="fileName" /></s:a>
		</td>
		<td><s:hidden name="tsAttach.hcmoTsAttachmentId" value="%{hcmoTsAttachmentId}" id="tsAttach.hcmoTsAttachmentId" ></s:hidden>
		
		<s:submit key="button.label.submit" cssClass="submitbutton117" value="Delete"/></td>
	</tr>

</table>
</s:form>	
	</s:iterator>
</s:if>
<s:else>
<s:form name="timesheetAttachForm" action="doTimesheetAttachments.action" method="POST" enctype="multipart/form-data" onsubmit="return AIM.submit(this, {'onStart' : startCallbackForTimeSheetDelete, 'onComplete' : completeCallbackForTimeSheetDelete})">
		<table align="center">
			<tr>
				<td class="tsinputth">
					<s:text name="label.title.timesheetattach.msg"/>
				</td>
			</tr>
			<tr>
				<td>
					<jsp:include page="common/messages.jsp" flush="true"/>
				</td>
			</tr>
			<tr>
				<td>
		<s:hidden name="month" value="%{tsAttach.month}" id="month"></s:hidden>
		<s:hidden name="year" value="%{tsAttach.year}" id="year"></s:hidden>
		<s:hidden name="week_of_month" value="%{tsAttach.week}" id="week_of_month"></s:hidden>
		<s:hidden name="employee_id" value="%{tsAttach.hcmoEmployeeId.employeeId}" id="eemployee_id"></s:hidden>
						<s:file label="File (1)" name="upload" />
					   	<s:submit key="button.label.submit" cssClass="submitbutton117" />
					   	
					   	
				</td>
			</tr>
		</table>
		</s:form>
</s:else>