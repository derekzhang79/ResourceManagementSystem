<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_Children_div">
		<div class="submenu_bg">
			<s:if test="#session.CHILDREN_ADD == true">
				<sj:a href="setUpChildren.action" targets="submenu_Children_div" indicator="indicatorSubMenuChildren" cssClass="link"><s:text name="MTIAddChildren" /></sj:a> |
			</s:if>
			<s:if test="#session.CHILDREN_VIEW == true">
				<sj:a href="getAllChildren.action" targets="submenu_Children_div" indicator="indicatorSubMenuChildren" cssClass="link"><s:text name="MTIViewChildren"/></sj:a> |
				<sj:a href="childrenSearchForm.action" targets="submenu_Children_div" indicator="indicatorSubMenuChildren" cssClass="link"><s:text name="MTISearchChildren"/></sj:a>
			</s:if>
		</div>
			<br/>
			<img id="indicatorSubMenuChildren" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

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
						<s:text name="label.title.children.view" />
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	            <tr>
					<td class="inputtext"><s:text name="label.form.fields.children.empChildrenId" /></td>
					<td class="employeedisplaytd"><s:property value="child.empChildrenId"/></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.common.empName" /></td>
					<td class="employeedisplaytd"><s:property value="child.empIdObj.empFullName" /></td>
				</tr>
				<s:if test="#session.CHILDREN_CHILDNAME_VIEW==true">
					<tr>
						<td class="inputtext"><s:text name="label.header.children.name" /></td>
						<td class="employeedisplaytd"><s:property value="child.childName"/></td>
					</tr>
				</s:if>
				<s:if test="#session.CHILDREN_DOB_VIEW==true">
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.common.dob" /></td>
						<td class="employeedisplaytd"><s:date name="child.childDOB" format="%{getText('label.date.simpleDateFormat')}"/></td>
					</tr>
				</s:if>
				<tr>
					<td class="inputtext"><s:text name="label.header.common.created" /></td>
					<td class="employeedisplaytd"><s:date name="child.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
					<td class="employeedisplaytd"><s:property value="child.createdBy.empFullName" /></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.common.updated" /></td>
					<td class="employeedisplaytd"><s:date name="child.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
				</tr>
				 <tr>
					<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
					<td class="employeedisplaytd"><s:property value="child.updatedBy.empFullName" /></td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
					<td class="employeedisplaytd"><s:checkbox name="child.isActive" label="isActive" disabled="true"></s:checkbox></td>
				</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>