<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form>
		<table class="maintable">
	      <tr>
	        <td align="center"><table class="formouter" width="100%">
	          <tr>
	            <td><table class="employeeformiinertable">
		                <tr>
			                 <td class="formtitle">
								<s:text name="label.about.hcmone" />
			                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner" width="100%"><table class="tablealign">
	                	<tr>
							<td class="inputtext"><s:text name="Version"/></td>
							<td class="employeeinputtd"><s:text name="1.1"/></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.common.saasContract" /></td>
							<td class="employeeinputtd">
								<s:url var="saasContractedDownloadWindow" action="downloadSaasContract">
									<s:param name="saasFileName" value="%{#session.SAAS_FILE_NAME}"></s:param>
									<s:param name="fileSize" value="%{#session.SAAS_SIZE}"></s:param>
									<s:param name="fileType" value="%{#session.SAAS_TYPE}"></s:param>
								</s:url>							
								<s:a href="%{saasContractedDownloadWindow}" title="Saas Contract Agreement" tooltip="Saas Contract Agreement"><span class="saascontract"></span></s:a>
							</td> 
						</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>