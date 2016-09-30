<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_CategoryFormId_div">
<div class="submenu_bg">
		<s:if test="#session.PERFORMANCECATEGORY_ADD == true">
			<sj:a href="setUpCategory.action" targets="submenu_CategoryFormId_div" indicator="indicatorSubMenuCatergoryForm" cssClass="link"><s:text name="MTIAddCategory" /></sj:a> |
		</s:if>
		<s:if test="#session.PERFORMANCECATEGORY_VIEW == true">
			<sj:a href="getAllCategory.action" targets="submenu_CategoryFormId_div" indicator="indicatorSubMenuCatergoryForm" cssClass="link"><s:text name="MTIViewCategory"/></sj:a> |
			<sj:a href="categorySearchForm.action" targets="submenu_CategoryFormId_div" indicator="indicatorSubMenuCatergoryForm" cssClass="link"><s:text name="MTISearchCategory"/></sj:a>
		</s:if>
</div>
<br/>
	<img id="indicatorSubMenuCatergoryForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateCategory">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
	               		<s:if test="category==null || category.hcmoCategoryId == null">
							<s:text name="label.title.category.add" />
						</s:if> <s:else>
							<s:text name="label.title.category.edit" />
						</s:else>
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					<tr>
					  <td class="inputtext"><s:text	name="label.form.fields.category.name" /></td>
					  <td class="employeeinputtd"><s:textfield name="category.categoryName" cssClass="employeeinput"/></td>
					  <td class="inputerrormessage"><s:fielderror fieldName="category.categoryName" /></td>
					</tr>
		</table></td></tr></table></td></tr></table></td></tr></table><br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorCategoryForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	 		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_CategoryFormId_div" indicator="indicatorCategoryForm"/>
				</td>
				<s:if test="category==null || category.hcmoCategoryId==null">
		    		    <td>
		    		    	<s:url var="resetCategory" action="resetCategory"></s:url>
		    	            <sj:submit href="%{resetCategory}"  key="button.label.reset" cssClass="resetbutton117" targets="submenu_CategoryFormId_div" indicator="indicatorCategoryForm"/>
						</td>
					</s:if>
					<s:else>
	    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
			</tr>
		</table>
		<s:hidden name="category.hcmoCategoryId"/>
	</s:form>
</div>