
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<div id="submenu_deductionsViewId_div">
		<div class="submenu_bg">
			<s:if test="#session.DEDUCTION_ADD == true">
				<sj:a href="setUpForInsertOrUpdateDeduction.action" targets="submenu_deductionsViewId_div" indicator="indicatorSubMenuDeductionsViewId_div" cssClass="link"><s:text name="MTIAddDeductions" /></sj:a> |
			</s:if>
			<s:if test="#session.DEDUCTION_VIEW == true">
				<sj:a href="getAllDeductions.action" targets="submenu_deductionsViewId_div" indicator="indicatorSubMenuDeductionsViewId_div" cssClass="link"><s:text name="MTIViewDeductions"/></sj:a> |
				<sj:a href="deductionSearchForm.action" targets="submenu_deductionsViewId_div" indicator="indicatorSubMenuDeductionsViewId_div" cssClass="link"><s:text name="MTISearchDeductions"/></sj:a>
			</s:if>
	</div><br/>
	<img id="indicatorSubMenuDeductionsViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form>
		<table class="maintable">
	     <tr>
	       <td align="center" ><table class="formouter">
	     <tr>
	        <td class="employeedisplaytd"><table class="employeeformiinertable">
	      <tr>
	         <td class="formtitle">
				<s:text name="label.title.deduction.view" />
			  </td>
	       </tr>
           <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.deduction.deductionId" /></td>
				<td class="employeedisplaytd"><s:property value="deduction.deductionId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.deduction.name" /></td>
				<td class="employeedisplaytd"><s:property value="deduction.deductionName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.deduction.type" /></td>
				<td class="employeedisplaytd"><s:property value="deduction.deductionType"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.deduction.mode" /></td>
				<td class="employeedisplaytd"><s:property value="deduction.deductionMode"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="deduction.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="deduction.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="deduction.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="deduction.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="deduction.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>