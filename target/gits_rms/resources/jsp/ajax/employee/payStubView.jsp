<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submen_Esspaystub_div">
<div class="submenu_bg">
	<s:url var="getEssEmpPayStub" action="getEssEmpPayStub" escapeAmp="false">
		<s:param name="payStub.employee.employeeId" value="payStub.employee.employeeId"></s:param>
		<s:param name="payStub.payStubId" value="payStub.payStubId"></s:param>
	</s:url>
	<sj:a href="%{getEssEmpPayStub}" targets="submen_Esspaystub_div" indicator="indicatorSubMenuPaystubView" cssClass="link"><s:text name="MTIAddEmployeePayStub"/></sj:a>
</div>
<br/><img id="indicatorSubMenuPaystubView" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	<s:form>
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
		                <tr>
			                 <td class="formtitle">
								<s:text name="label.title.paystub.view" />
			                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
            <tr>
				<td class="inputtext"><s:text name="label.header.paystub.payStubId" /></td>
				<td class="employeedisplaytd"><s:property value="payStub.payStubId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.paystub.empName" /></td>
				<td class="employeedisplaytd"><s:property value="payStub.employee.empFirstName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.paystub.grossSalary" /></td>
				<td class="employeedisplaytd"><s:property value="payStub.grossSalary"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.paystub.decDate" /></td>
				<td class="employeedisplaytd"><s:property value="payStub.declarationDate"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.paystub.netSalary" /></td>
				<td class="employeedisplaytd"><s:property value="payStub.netSalary"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="payStub.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="payStub.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="payStub.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	     	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="payStub.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="payStub.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
	</s:form>
</div>