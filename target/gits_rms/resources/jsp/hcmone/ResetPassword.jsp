<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
	<title><s:text name="label.login.title.resetpassword"/></title>

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
<body>

<s:form action="resetPassword" method="POST" validate="true">

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
	                  	    <s:hidden name="sUserName"></s:hidden>
							<s:hidden name="sEmail"></s:hidden>
							<s:hidden name="iUserId"></s:hidden>
	                    	<s:actionerror/>
	                    </td>
	                  </tr>
	                  <tr>
	                    <td class="logintext"><s:text name="label.login.password"/></td>
	                    <td height="28">
	                    	<s:password name="sPassword" size="20"/>
	                    </td>
	                  </tr>
	                  <tr>
	                    <td class="logintext"><s:text name="label.login.confirm.password"/></td>
	                    <td height="28">
	                    	<s:password name="sPassword2" size="20"/>
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
        <td height="49" align="center" valign="middle" class="loginfooter">A Product of RoosterHR</td>
      </tr>
    </table></td>
  </tr>
</table>
</s:form>
</body>


</html>