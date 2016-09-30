<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
<div class="informationMessageSingle"><li><span><s:text name="label.title.newexpenses.list"/></span></li></div>		   
	<table width=600 align=center>
	    <tr>
	    <td><a href="homePage.action">Home</a></td>
	     <s:url var="setUpEmployees" action="setUpEmployees"/>
	         <td><s:a href="%{setUpEmployees}"> Add Employee</s:a></td>
	     </tr>
	</table><br/>

