<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EmployeeUSTaxViewId_div">
	<div class="submenu_bg">
		<s:if test="#session.EMPLOYEEUSTAX_ADD == true">
			<sj:a href="setUpEmpUSTax.action" targets="submenu_EmployeeUSTaxViewId_div" indicator="indicatorSubMenuEmployeeUSTaxViewId_div" cssClass="link"><s:text name="MTIAddEmployeeUSTax" /></sj:a> |
		</s:if>
		<s:if test="#session.EMPLOYEEUSTAX_VIEW == true">
			<sj:a href="getAllEmpUSTax.action" targets="submenu_EmployeeUSTaxViewId_div" indicator="indicatorSubMenuEmployeeUSTaxViewId_div" cssClass="link"><s:text name="MTIViewEmployeeUSTax"/></sj:a> |
			<sj:a href="usTaxSearchForm.action" targets="submenu_EmployeeUSTaxViewId_div" indicator="indicatorSubMenuEmployeeUSTaxViewId_div" cssClass="link"><s:text name="MTISearchEmployeeUSTax"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeeUSTaxViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form>
		<table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.empUsTax.view" />
					  </td>
	                </tr>
               <tr>
                 <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.empUSTax.hcmoEmpUsTaxId" /></td>
				<td class="employeedisplaytd"><s:property value="empUSTax.hcmoEmpUsTaxId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td  class="employeedisplaytd"><s:property value="empUSTax.hcmoEmployeeId.empFullName" /></td>
			</tr>
			
			
			<tr>
	         	<th colspan="4" class="formtitle1"> <b><s:text name="label.common.message.federalIncomeTax"/></b></th> 
	        </tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.empUSTax.federalException" /></td>
				<td  class="employeedisplaytd"><s:property value="empUSTax.taxFederalExceptions" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.empUSTax.federalStatus" /></td>
				<td  class="employeedisplaytd"><s:property value="empUSTax.taxFederalStatus" /></td>
			</tr>
			
			<tr>
				<th colspan="4" class="formtitle1"><b><s:text name="label.common.message.stateIncomeTax"/></b></th>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.empUSTax.state" /></td>
				<td  class="employeedisplaytd"><s:property value="empUSTax.taxState.name" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.empUSTax.stateStatus" /></td>
				<td  class="employeedisplaytd"><s:property value="empUSTax.taxStateStatus"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.empUSTax.stateException" /></td>
				<td  class="employeedisplaytd"><s:property value="empUSTax.taxStateExceptions"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.empUSTax.unempState" /></td>
				<td  class="employeedisplaytd"><s:property value="empUSTax.taxUnempState.name"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.empUSTax.workState" /></td>
				<td  class="employeedisplaytd"><s:property value="empUSTax.taxWorkState.name"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="empUSTax.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td  class="employeedisplaytd"><s:property value="empUSTax.createdBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="empUSTax.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
		 	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td  class="employeedisplaytd"><s:property value="empUSTax.updatedBy.empFullName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td  class="employeedisplaytd"><s:checkbox name="empUSTax.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table>
	</td></tr></table></td></tr></table>
	</s:form>
</div>