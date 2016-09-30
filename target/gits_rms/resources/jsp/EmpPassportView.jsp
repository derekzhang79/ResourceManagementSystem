
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EmployeePassportViewId_div">
	<div class="submenu_bg">
		<s:if test="#session.EMPLOYEEPASSPORT_ADD == true">
			<sj:a href="setUpEmpPassport.action" targets="submenu_EmployeePassportViewId_div" indicator="indicatorSubMenuEmployeePassportViewId_div" cssClass="link"><s:text name="MTIAddEmployeePassport" /></sj:a> |
		</s:if>
		<s:if test="#session.EMPLOYEEPASSPORT_VIEW == true">
			<sj:a href="getAllEmpPassport.action" targets="submenu_EmployeePassportViewId_div" indicator="indicatorSubMenuEmployeePassportViewId_div" cssClass="link"><s:text name="MTIViewEmployeePassport"/></sj:a> |
			<sj:a href="passportSearchForm.action" targets="submenu_EmployeePassportViewId_div" indicator="indicatorSubMenuEmployeePassportViewId_div" cssClass="link"><s:text name="MTISearchEmployeePassport"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeePassportViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

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
						<s:text name="label.title.empPassport.view" />
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.empPassport.hcmoEmpPassportId" /></td>
				<td class="employeedisplaytd"><s:property value="empPass.hcmoEmpPassportId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd"><s:property value="empPass.empIdObj.empFullName" /></td>
			</tr>
			<s:if test="#session.EMPLOYEEPASSPORT_PASSPORTNO_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.empPassport.epPassportNo" /></td>
					<td class="employeedisplaytd"><s:property value="empPass.epPassportNo" /></td>
				</tr>
			</s:if>
			<s:if test="#session.EMPLOYEEPASSPORT_PASSPORTISSUEDATE_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.empPassport.PassportIssueDate" /></td>
					<td class="employeedisplaytd"><s:date name="empPass.epPassportIssueDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
				</tr>
			</s:if>
			<s:if test="#session.EMPLOYEEPASSPORT_PASSPORTEXPIREDATE_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.empPassport.PassportExpireDate" /></td>
					<td class="employeedisplaytd"><s:date name="empPass.epPassportExpireDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
				</tr>
			</s:if>
			<s:if test="#session.EMPLOYEEPASSPORT_PASSPORTTYPEFLG_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.empPassport.epPassportTypeFlg" /></td>
					<td class="employeedisplaytd"><s:property value="empPass.epPassportTypeFlg"/></td>
				</tr>
			</s:if>
			<s:if test="#session.EMPLOYEEPASSPORT_L9STATUS_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.empPassport.epL9Status" /></td>
					<td class="employeedisplaytd"><s:property value="empPass.epL9Status"/></td>
				</tr>
			</s:if>
			<s:if test="#session.EMPLOYEEPASSPORT_L9REVIEWDATE_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.empPassport.epL9ReviewDate" /></td>
					<td class="employeedisplaytd"><s:date name="empPass.epL9ReviewDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
				</tr>
			</s:if>
			<s:if test="#session.EMPLOYEEPASSPORT_COUNTRYNAME_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.empPassport.countryName" /></td>
					<td class="employeedisplaytd"><s:property value="empPass.country.countryName"/></td>
				</tr>
			</s:if>
			<s:if test="#session.EMPLOYEEPASSPORT_COMMENTS_VIEW==true">
				<tr>
					<td class="inputtext"><s:text name="label.header.empPassport.epComments" /></td>
					<td class="employeedisplaytd"><s:property value="empPass.epComments"/></td>
				</tr>
			</s:if>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="empPass.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="empPass.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="empPass.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			 <tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="empPass.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="empPass.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>