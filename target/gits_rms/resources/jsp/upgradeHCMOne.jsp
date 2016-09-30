<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div>
<br/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="upgradeHCMOne">
		<table class="maintable">
	      <tr>
	        <td align="center"><table class="formouter" width="100%">
	          <tr>
	            <td><table class="employeeformiinertable">
		                <tr>
			                 <td class="formtitle">
								
			                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner" width="100%"><table class="tablealign">
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>