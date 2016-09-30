<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	
<div id="MultipleUploadDiv">test</div>
<s:form action="doMultipleUploadUsingList.action" method="POST" enctype="multipart/form-data" onsubmit="return AIM.submit(this, {'onStart' : startCallback, 'onComplete' : completeCallback})">
	<s:file label="File (1)" name="upload" />
	<s:file label="File (2)" name="upload" />
   	<s:submit key="button.label.submit" cssClass="submitbutton117" />
</s:form>

 <script type="text/javascript">
		function startCallback() {
			// make something useful before submit (onStart)
			return true;
		}
 
		function completeCallback(response) {
			// make something useful after (onComplete)
			// document.getElementById('nr').innerHTML = parseInt(document.getElementById('nr').innerHTML) + 1;
			document.getElementById('MultipleUploadDiv').innerHTML = response;
		}
	</script>