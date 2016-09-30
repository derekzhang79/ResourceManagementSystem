<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_SignatureViewId_div">
<div class="submenu_bg">
		<sj:a href="setUpSignature.action" targets="submenu_SignatureViewId_div" indicator="indicatorSubMenuSignatureViewId_div" cssClass="link"><s:text name="MTIAddSignature" /></sj:a> |
		<sj:a href="getAllSignature.action" targets="submenu_SignatureViewId_div" indicator="indicatorSubMenuSignatureViewId_div" cssClass="link"><s:text name="MTIViewSignature"/></sj:a> |
		<sj:a href="signatureSearchForm.action" targets="submenu_SignatureViewId_div" indicator="indicatorSubMenuSignatureViewId_div" cssClass="link"><s:text name="MTISearchSignature"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuSignatureViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form>
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
		                 <td class="formtitle">
							<s:text name="label.title.signature.view" />
		                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.signature.signatureId" /></td>
				<td class="employeedisplaytd"><s:property value="signature.hcmoSignatureId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.signature.name" /></td>
				<td class="employeedisplaytd"><s:property value="signature.signatureName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.signature.preSignature" /></td>
				<td class="employeedisplaytd"><s:property value="signature.preSignatureValue"/></td>
			</tr>	
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="signature.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="signature.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="signature.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	     	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="signature.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="signature.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>