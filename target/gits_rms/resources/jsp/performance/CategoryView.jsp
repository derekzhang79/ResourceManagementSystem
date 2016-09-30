<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_CategoryViewId_div">
<div class="submenu_bg">
	<s:if test="#session.PERFORMANCECATEGORY_ADD == true">
		<sj:a href="setUpCategory.action" targets="submenu_CategoryViewId_div" indicator="indicatorSubMenuCategoryView" cssClass="link"><s:text name="MTIAddCategory" /></sj:a> |
	</s:if>
	<s:if test="#session.PERFORMANCECATEGORY_VIEW == true">
		<sj:a href="getAllCategory.action" targets="submenu_CategoryViewId_div" indicator="indicatorSubMenuCategoryView" cssClass="link"><s:text name="MTIViewCategory"/></sj:a> |
		<sj:a href="categorySearchForm.action" targets="submenu_CategoryViewId_div" indicator="indicatorSubMenuCategoryView" cssClass="link"><s:text name="MTISearchCategory"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuCategoryView" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

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
							<s:text name="label.title.category.view" />
	                    </td>
	                </tr>
     			<tr>
	                <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.category.hcmoCategoryId" /></td>
				<td class="employeedisplaytd"><s:property value="category.hcmoCategoryId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.category.name" /></td>
				<td class="employeedisplaytd"><s:property value="category.categoryName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="category.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="category.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="category.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	    	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="category.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="category.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>