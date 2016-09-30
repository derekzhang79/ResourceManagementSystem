<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="refresh" content="6;URL=sessionExpires.action">
<%@ page isErrorPage="true" %>	
<html>
<head>
    <title>Error Page</title>
    <link href="resources/css/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="titleDiv">HCMOne Application</div>	
<p>
<font size=4 color=orange>Your are seeing the error page</font><br><br> <a href="showLogin.action">return to the site</a><br><br>
In order that the development team can address this error, please report what you were doing that caused this error.
<br/><br/>
The following information can help the development
team find where the error happened and what can be done to prevent it from
happening in the future. You will be redirected to the main page in 6 seconds.
<br/>
<%
if(null == exception){
    exception = (Throwable)request.getAttribute("org.apache.struts.action.EXCEPTION");
}
%>
<pre style="font-size:12px"><%
if(null == exception){
    out.write("Source of error is unknown.");
}else{
    java.io.StringWriter sw = new java.io.StringWriter();
    java.io.PrintWriter pw = new java.io.PrintWriter(sw);

    exception.printStackTrace(pw);
    out.write(sw.getBuffer().toString());

}
%></pre>
</body>
</html>
