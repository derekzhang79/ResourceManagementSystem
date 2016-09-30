<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Success</title>
</head>

<body>

<p align="center"><font color="#000080" size="5"><s:text name="label.common.message.ActivationSuccesful"/></font></p>

<s:url action="showLogin.action" var="aHome" />
<s:a href="%{aHome}">Home</s:a>

</body>

</html>