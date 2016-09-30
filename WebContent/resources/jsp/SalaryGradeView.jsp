<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_SalaryGradeViewId_div">
<div class="submenu_bg">
	<s:if test="#session.SALARYGRADE_ADD == true">
		<sj:a href="setUpSalaryGrade.action" targets="submenu_SalaryGradeViewId_div" indicator="indicatorSubMenuSalaryGradeViewId_div" cssClass="link"><s:text name="MTIAddSalaryGrade" /></sj:a> |
	</s:if>
	<s:if test="#session.SALARYGRADE_VIEW == true">
		<sj:a href="getAllSalaryGrade.action" targets="submenu_SalaryGradeViewId_div" indicator="indicatorSubMenuSalaryGradeViewId_div" cssClass="link"><s:text name="MTIViewSalaryGrade"/></sj:a> |
		<sj:a href="salaryGradeSearchForm.action" targets="submenu_SalaryGradeViewId_div" indicator="indicatorSubMenuSalaryGradeViewId_div" cssClass="link"><s:text name="MTISearchSalaryGrade"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuSalaryGradeViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
							<s:text name="label.title.salaryGrade.view" />
		                 </td>
	                </tr>
     			<tr>
                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text	name="label.form.fields.salaryGrade.hcmoSalaryGradeId" /></td>
				<td class="employeedisplaytd"><s:property value="salgra.hcmoSalaryGradeId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.salaryGrade.name" /></td>
				<td class="employeedisplaytd"><s:property value="salgra.salaryName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text	name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="salgra.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text	name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="salgra.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text	name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="salgra.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	        <tr>
				<td class="inputtext"><s:text	name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="salgra.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="salgra.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>