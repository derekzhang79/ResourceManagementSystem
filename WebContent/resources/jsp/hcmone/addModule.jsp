<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://code.google.com/p/jcaptcha4struts2/taglib/1.0" prefix="jcaptcha" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title><s:text name="Module selection for signup client"/></title>
						
</head>

<body bgcolor="C0C0C0" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<div id="">

			<s:form id="moduleForm" name="moduleForm" method="post" action="addClient">
			<table align="center" >
			<s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
	                    	<tr>
	                    		<td colspan="2">
	                    			
									<s:if test="hasFieldErrors()"><s:fielderror cssClass="inputerrormessage"/></s:if>
									<s:if test="hasActionErrors()"> <s:actionerror cssClass="actionMessageSingle"/></s:if>
									<s:if test="hasActionMessages()"><s:actionmessage cssClass="successMessageSingle"/></s:if> 
	                    		</td>
	                    	</tr> 
                    	</s:if>
			        <tr>
						<td class="tbltdhead"><s:text name="lable.form.field.module" /></td>
						<td class="tbltdhead"><s:text name="lable.form.field.price" /></td>
					</tr>
					 <tr>
						<td class="tbltdhead"><s:text name="lable.form.field.hcmname"/>
						<s:checkbox name="checkhcm" id="checkhcm" cssClass="txtfld" value="true" onchange="getModuleId();"/></td>
						<td class="tbltdhead"><s:text name="lable.form.field.userprice"/></td>
					</tr>
					
					<tr>
						<td class="tbltdhead"><s:text name="lable.form.field.recruitname"/>
						<s:checkbox name="checkrecruit" id="checkrecruit" cssClass="txtfld" value="true" onchange="getModuleId();"/></td>
						<td class="tbltdhead"><s:text name="lable.form.field.userprice"/></td>
					</tr>
					
					<tr>
                            <td class="" colspan="1"></td>
                            <td class="tbltdhead"><s:text name="lable.form.field.trial"/></td>
                    </tr>
                    <tr>
                           <td class="" colspan="1"></td>
                           <td class="tbltdhead"><s:text name="lable.form.field.Free"/></td>
                    </tr>
					<tr>
                                     
                            <td class="">
	                            <img id="createindicator" border="0"
								style="display: none;"
								src="${pageContext.request.contextPath}/resources/images/indicator.gif">
								<s:submit align="center" name="savebtn" id="savebtn"  value="Save" cssClass="create-account" cssStyle="width:120px;" onclick="indicator();"/>
                                &nbsp;
                                </td>
                              
                                
                    </tr>
					<s:hidden name="client.moduleId" id="client.moduleId" value="2"></s:hidden>
			</table>
			</s:form>
			<div style="clear: both;"></div>
		</div>
		<br/><br/>
		<script type="text/javascript">
			function indicator() {
				jQuery("#createindicator").show();
			}
			function getModuleId() {
				if(document.getElementById("checkhcm").checked && document.getElementById("checkrecruit").checked) {
					alert('checked');
					document.getElementById("client.moduleId").value=3;
				}
				else if(document.getElementById("checkhcm").checked){
					document.getElementById("client.moduleId").value=1;
				}
				else if(document.getElementById("checkrecruit").checked) {
					document.getElementById("client.moduleId").value=2;
				}
			}
		</script>
		

</body>
</html>