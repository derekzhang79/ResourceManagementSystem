<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="picUpload">
<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form method="post" enctype="multipart/form-data" action="userImageUpload" onsubmit="return AIM.submit(this, {'onStart' : startCallbackForEmployeePicUpload, 'onComplete' : completeCallbackForemployeeFilePicResponse})">
			<table align="center" class="borderAll">
			 <tr align="center">
			 		<td class="inputtext"><s:text name="label.form.fields.common.picture" /></td>
		        	<td class="employeeinputtd"><s:file name="userImage" cssClass="employeeinput"/></td>
		        	<td class="inputerrormessage">
	         	 		<s:fielderror fieldName="userImage" />
	         	 	</td>
       		 </tr>
       		 
       		 </table>
       		 <table align="center">
		     <tr>	   	     
	   		    <td><s:submit key="button.label.upload" cssClass="submitbutton117"/></td>
	   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	   		 </tr>
	   		</table>
   		<s:hidden name="employee.employeeId"/>
	</s:form>
</div>	
 <script type="text/javascript">
//Employee Module File Upload
function startCallbackForEmployeePicUpload() {
	// make something useful before submit (onStart)
	return true;
}

// Employee Module File Upload Response
function completeCallbackForemployeeFilePicResponse(response) {
	// make something useful after (onComplete)
	// document.getElementById('nr').innerHTML = parseInt(document.getElementById('nr').innerHTML) + 1;
	document.getElementById('EmployeePictureUploadDiv').innerHTML = response;
}
</script>