<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submen_Ess_paystub_div">
<div class="submenu_bg">
	<s:set var="payStub.payStubId" value="payStubDeduction.payStub.payStubId.toString()"></s:set>
	<s:url var="getEssEmpPayStub" action="getEssEmpPayStub" escapeAmp="false">
		<s:param name="payStub.payStubId" value="payStub.payStubId"></s:param>
		<s:param name="payStub.employee.employeeId" value="payStub.employee.employeeId"></s:param>
	</s:url>
	<sj:a href="%{getEssEmpPayStub}" targets="submen_Ess_paystub_div" indicator="indicatorSubMenuPaystubDeductionViewId_div" cssClass="link"><s:text name="MTIAddEmployeePayStub"/></sj:a>
</div>
<br/><img id="indicatorSubMenuPaystubDeductionViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<s:form>
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
		                <tr>
			                 <td class="formtitle">
								<s:text name="label.title.paystubDeduction.view" />
			                  </td>
		                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
            <tr>
				<td class="inputtext"><s:text name="label.header.paystubDeduction.payStubDeductionId" /></td>
				<td class="employeedisplaytd"><s:property value="payStubDeduction.payStubDeductionId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.paystub.payStubId" /></td>
				<td class="employeedisplaytd"><s:property value="payStubDeduction.payStub.payStubId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.deduction.name" /></td>
				<td class="employeedisplaytd"><s:property value="payStubDeduction.deduction.deductionName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.deduction.type" /></td>
				<td class="employeedisplaytd"><s:property value="payStubDeduction.deduction.deductionType"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.deduction.mode" /></td>
				<td class="employeedisplaytd"><s:property value="payStubDeduction.deduction.deductionMode"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.paystubDeduction.deductionAmount" /></td>
				<td class="employeedisplaytd"><s:property value="payStubDeduction.deductionAmount"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.paystubDeduction.effectiveDate" /></td>
				<td class="employeedisplaytd"><s:property value="payStubDeduction.effectiveDate"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="payStubDeduction.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="payStubDeduction.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="payStubDeduction.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	     	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="payStubDeduction.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="payStubDeduction.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
	</s:form>
</div>