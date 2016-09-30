<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://code.google.com/p/jcaptcha4struts2/taglib/1.0" prefix="jcaptcha" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title><s:text name="Sign Up for 60 Day Free Trial"/></title>
	<link href="<s:url value="/resources/css/default/black/login.css"/>" rel="stylesheet" type="text/css"/>
	
	<style type="text/css">
		body {
			background-color: #C0C0C0;
			margin: 0px
		}
	</style>					
</head>

<body bgcolor="C0C0C0" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

	<div>
		<div id="">

			<h1 align="center">Sign Up</h1>
				<!-- <h4 align="center">* Indicates Required</h4>-->

			
				<s:form id="clientEnterFrom" name="clientEnterFrom" method="post" action="addModule">
				
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
						<td class="tbltdhead"><s:text
								name="lable.form.field.client.firstName" /></td>
						<td class="tbltdfld"><s:textfield name="client.firstName"
								 cssClass="txtfld" /></td>
					</tr>


					<tr>
						<td class="tbltdhead"><s:text
								name="lable.form.field.client.lastName" /></td>
						<td class="tbltdfld"><s:textfield name="client.lastName"
								id="client.lastName" cssClass="txtfld" /></td>
					</tr>
					
					<tr>
						<td class="tbltdhead"><s:text
								name="lable.form.field.client.companyName" /></td>
						<td class="tbltdfld"><s:textfield name="client.companyName"
								id="client.companyName" cssClass="txtfld" /></td>

					</tr>
					
					<tr>
						<td class="tbltdhead"><s:text
								name="lable.form.field.client.emailId" /></td>
						<td class="tbltdfld"><s:textfield name="client.contactMail"
								id="client.contactMail" cssClass="txtfld" autocomplete="off" /></td>

					</tr> 
					
					<tr>
						<td class="tbltdhead"><s:text
								name="lable.form.field.client.phone" /></td>
						<td class="tbltdfld"><s:textfield name="client.phone"
								id="client.phone" cssClass="txtfld" /></td>

					</tr>
					
					<tr>
						<td class="tbltdhead"><s:text
								name="lable.form.field.client.password" /></td>
						<td class="tbltdfld"><s:password name="client.password"
								id="client.password" cssClass="txtfld" /></td>

					</tr>
					
					<tr>
						
						<td class="tbltdhead"><s:checkbox name="acceptlicense"
								id="acceptlicense" cssClass="txtfld" />
						<s:text name="lable.form.field.acceptlicense" /></td>
					</tr>
					
										<tr>
                            <td class="" colspan="2"></td>
                    </tr>
					<tr>
                                     
                            <td class="">
	                            <img id="createindicator" border="0"
								style="display: none;"
								src="${pageContext.request.contextPath}/resources/images/indicator.gif">
								<s:submit align="center" name="regbtn" id="regbtn"  value="Sign up" cssClass="create-account" cssStyle="width:120px;" onclick="indicator();"/>
                                &nbsp;
                                </td>
                             <td class="">
	                            <img id="createindicator" border="0"
								style="display: none;"
								src="${pageContext.request.contextPath}/resources/images/indicator.gif">
								<s:submit align="center" name="cancelbtn" id="cancelbtn"  value="Cancel" action="showLogin" cssClass="create-account" cssStyle="width:120px;" onclick="indicator();"/>
                                </td>   
                                
                    </tr>
					
				</table>
			</s:form>
				
				

			<div style="clear: both;"></div>
		</div>
		<br/><br/>
		<script type="text/javascript">
			function indicator() {
				jQuery("#createindicator").show();
			}
		</script>
		
	</div>
	
	
</body>
</html>