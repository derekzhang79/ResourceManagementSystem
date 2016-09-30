<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
	<title><s:text name="label.login.title.resetlinksent"/></title>

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
                <td width="742" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	                  <tr>
	                    <td>&nbsp;</td>
	                    <td align="center" valign="bottom">
	                    	<br/><br/><br/><br/><br/><br/>
							<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
							Go <a href="showLogin.action">Home</a>
	                    </td>
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