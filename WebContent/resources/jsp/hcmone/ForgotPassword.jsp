<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.Calendar"%>
<html>
<head>
	<title><s:text name="label.login.title.forgotpassword"/></title>

	<link href="<s:url value="/resources/css/default/black/login.css"/>" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		<!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			background-color: #C0C0C0;
		}
		-->
	</style>
</head>

<%
	Calendar c = Calendar.getInstance();
	String sMillis = String.valueOf(c.getTimeInMillis());
%>

<s:form action="checkUser" method="POST" validate="true">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="top" class="loginbodybg"><table width="100%"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="117" align="center" valign="top"><table width="742" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center" valign="top" class="forgotpassheader"><div class="logotext"><s:text name="application.title" /></div></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="410" align="center" valign="top"><table width="742" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="428" align="center" valign="top" class="loginleftimage">&nbsp;</td>
                <td width="314" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	                  <tr>
	                  	<td colspan="2" class="loginerror">
	                    	<s:fielderror fieldName="captchacode"/>
	                    	<s:actionerror/>
	                    </td>
	                  </tr>
	                  <tr>
	                    <td class="logintext"><s:text name="label.login.username" /></td>
	                    <td height="28">
	                    	<s:textfield name="userName" label="label.login.username" cssClass="logininput"/>
	                    </td>
	                  </tr>
	                  <tr>
	                    <td class="logintext"><s:text name="label.header.common.email"/></td>
	                    <td height="28">
	                    	<s:textfield name="emailId" size="20" cssClass="logininputpassword"/>
	                    </td>
	                  </tr>
	                  
	                  <tr>
	                  	<td></td>
	                  	<td><img src="getCaptchaImage.action?millis=<%=sMillis %>" alt="captcha image"/></td>
	                  </tr>
	                  <tr>
	                    <td class="logintext"><s:text name="label.commom.imageverification"/></td>
	                    <td>
	                    	<input type="text" name="captchacode" class="logininputpassword"/> 
	                    </td>
	                  </tr>
	                  <tr>
	                    <td>&nbsp;</td>
	                    <td align="left" valign="middle"><table width="80%" border="0" cellspacing="0" cellpadding="0">
	                      <tr>
	                        <td align="center" valign="middle">
	                        	<s:submit value="Submit" name="button.label.submit" cssClass="submitbutton117"/>
	                        </td>
	                        <td align="center" valign="middle">
	                        	<s:reset value="Reset" name="button.label.reset" cssClass="resetbutton117"/>
	                        </td>
	                      </tr>
	                    </table></td>
	                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="49" align="center" valign="middle" class="loginfooter">Roosterhr.com All rights reserved</td>
      </tr>
    </table></td>
  </tr>
</table>
</s:form>
</body>


</html>