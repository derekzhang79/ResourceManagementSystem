<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://code.google.com/p/jcaptcha4struts2/taglib/1.0" prefix="jcaptcha" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="java.util.Calendar"%>

<html>
<head>
	<title><s:text name="label.login.title.welcome"/></title>
	<link href="<s:url value="/resources/css/default/black/login.css"/>" rel="stylesheet" type="text/css"/>
	
	<style type="text/css">
		body {
			background-color: #C0C0C0;
			margin: 0px
		}
	</style>
</head>
<body>
	<%
		Calendar c = Calendar.getInstance();
		String sMillis = String.valueOf(c.getTimeInMillis());
	%>

<s:form action="doLogin" method="POST" validate="true">
<div class="loginPageMainDivClass">
	<table height="100%" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="top" class="loginbodybg"><table width="100%"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="117" align="center" valign="top"><table width="742" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center" valign="top" class="loginheader"><div class="logotext"><s:text name="application.title" /></div></td>
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
	                    <td class="logintext"><s:text name="label.login.username" /></td>
	                    <td height="28">
	                    	<s:textfield name="username" label="label.login.username" cssClass="logininput"/>
	                    </td>
	                  </tr>
	                  <tr>
	                  	<td colspan="2" class="loginerror">
	                    	<s:fielderror fieldName="username"/>
	                    </td>
	                  </tr>
	                  <tr>
	                    <td class="logintext"><s:text name="label.login.password"/></td>
	                    <td height="28">
	                    	<s:password name="password" label="label.login.password" cssClass="logininputpassword"/>
	                    </td>
	                  </tr>
	                  <tr>
	                  	<td colspan="2" class="loginerror">
	                    	<s:fielderror fieldName="password"/>
	                    </td>
	                  </tr>
	                  <tr>
	                  	<td></td>
	                  	<td><img src="getCaptchaImage.action?millis=<%=sMillis %>" alt="captcha image"/></td>
	                  </tr>
	                  <tr>
	                    <td class="logintext"><s:text name="label.commom.imageverification"/></td>
	                    <td>
	                    	<input type="text" name="captchacode" class="logininputpassword" autocomplete="off"/> 
	                    </td>
	                  </tr>
	                  <tr>
	                  	<td colspan="2" class="loginerror">
	                    	<s:fielderror fieldName="captchacode"/>
	                    	<s:actionerror/>
	                    </td>
	                  </tr>
	                  <tr>
	                    <td>&nbsp;</td>
	                    <td align="left" valign="middle"><table width="80%" border="0" cellspacing="0" cellpadding="0">
	                      <tr>
	                        <td align="center" valign="middle">
	                        	<s:submit value="Login" name="button.label.login" cssClass="submitbutton117"/>
	                        </td>
	                        <td align="center" valign="middle">
	                        	<s:reset value="Reset" name="button.label.reset" cssClass="resetbutton117"/>
	                        </td>
	                      </tr>
	                    </table></td>
	                  </tr>
	                  <tr>
	                    <td>&nbsp;</td>
	                    <td class="forgetpassword">
	                    	<a href="forgotPassword.action">Forgot Your Password?</a>
	                    </td>
                  </tr>
                  <tr>
                  	<td>&nbsp;</td>
                  	<td><a href="signup.action">Signup for an account !! </a></td>
                  </tr>                  	
                </table></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>

</div>
<div class="loginfooter" >
	A Product of RoosterHR
</div>
</s:form>
</body>
</html>