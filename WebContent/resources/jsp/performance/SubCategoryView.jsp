<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_SubCategoryViewId_div">
<div class="submenu_bg">
	<s:if test="#session.PERFORMANCESUBCATEGORY_ADD == true">
		<sj:a href="setUpSubCategory.action" targets="submenu_SubCategoryViewId_div" indicator="indicatorSubMenuSubCategoryViewId_div" cssClass="link"><s:text name="MTIAddSubCategory" /></sj:a> |
	</s:if>
	<s:if test="#session.PERFORMANCESUBCATEGORY_VIEW == true">
		<sj:a href="getAllSubCategory.action" targets="submenu_SubCategoryViewId_div" indicator="indicatorSubMenuSubCategoryViewId_div" cssClass="link"><s:text name="MTIViewSubCategory"/></sj:a> |
		<sj:a href="subCategorySearchForm.action" targets="submenu_SubCategoryViewId_div" indicator="indicatorSubMenuSubCategoryViewId_div" cssClass="link"><s:text name="MTISearchSubCategory"/></sj:a>	
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuSubCategoryViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

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
							<s:text name="label.title.subCategory.view" />
	                    </td>
	                </tr>
     			<tr>
	                <td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.subCategory.hcmosubCategoryId" /></td>
				<td class="employeedisplaytd"><s:property value="subCategory.hcmoSubCategoryId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.subCategory.name" /></td>
				<td class="employeedisplaytd"><s:property value="subCategory.subCategoryName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="subCategory.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="subCategory.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="subCategory.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	    	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="subCategory.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="subCategory.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>