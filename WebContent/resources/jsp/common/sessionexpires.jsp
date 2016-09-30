<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<sj:head/>
	<div>HCMOne Application</div>
	<p>
		<font size=4 color=black>
			your session has expired.please login again.
		</font>
	</p>
	<s:hidden id="serverName" name="#request.serverName"></s:hidden>
	
	<script type="text/javascript">
		jQuery(document).ready(function () {
			alert("your session has expired. you will be redirected to login page");
		    window.top.location.href="showLogin.action";
		 });  
	</script>