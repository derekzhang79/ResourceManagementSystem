<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_SalaryViewId_div">
	<div class="submenu_bg">
		<s:if test="#session.SALARY_ADD == true">
			<sj:a href="setUpSalary.action" targets="submenu_SalaryViewId_div" indicator="indicatorSubMenuSalaryViewId_div" cssClass="link"><s:text name="MTIAddSalary" /></sj:a> |
		</s:if>
		<s:if test="#session.SALARY_VIEW == true">
			<sj:a href="getAllSalary.action" targets="submenu_SalaryViewId_div" indicator="indicatorSubMenuSalaryViewId_div" cssClass="link"><s:text name="MTIViewSalary"/></sj:a> |
			<sj:a href="salarySearchForm.action" targets="submenu_SalaryViewId_div" indicator="indicatorSubMenuSalaryViewId_div" cssClass="link"><s:text name="MTISearchSalary"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuSalaryViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

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
				<s:text name="label.title.salary.view" />
			  </td>
	       </tr>
	       <tr>
	            <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.salary.hcmosalaryId" /></td>
				<td class="employeedisplaytd"><s:property value="sal.hcmosalaryId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd"><s:property value="sal.empIdObj.empFullName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.salary.name" /> <s:text name="currencyTypeValue"/></td>
				<td class="employeedisplaytd"><s:property value="sal.newSalary1"/></td>
			</tr>	
			<tr>
				<td class="inputtext"><s:text name="label.header.salary.decDate" /></td>
				<td class="employeedisplaytd"><s:date name="sal.declarationDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>	
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="sal.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="sal.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="sal.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
		 	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="sal.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="sal.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>