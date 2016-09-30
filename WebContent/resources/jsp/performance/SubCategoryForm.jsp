<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_SubCategoryFormId_div">
<div class="submenu_bg">
	<s:if test="#session.PERFORMANCESUBCATEGORY_ADD == true">
		<sj:a href="setUpSubCategory.action" targets="submenu_SubCategoryFormId_div" indicator="indicatorSubMenuSubCategoryFormId_div" cssClass="link"><s:text name="MTIAddSubCategory" /></sj:a> |
	</s:if>
	<s:if test="#session.PERFORMANCESUBCATEGORY_VIEW == true">
		<sj:a href="getAllSubCategory.action" targets="submenu_SubCategoryFormId_div" indicator="indicatorSubMenuSubCategoryFormId_div" cssClass="link"><s:text name="MTIViewSubCategory"/></sj:a> |
		<sj:a href="subCategorySearchForm.action" targets="submenu_SubCategoryFormId_div" indicator="indicatorSubMenuSubCategoryFormId_div" cssClass="link"><s:text name="MTISearchSubCategory"/></sj:a>	
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuSubCategoryFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateSubCategory">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
	               		<s:if test="subCategory==null || subCategory.hcmoSubCategoryId == null">
							<s:text name="label.title.category.add" />
						</s:if> <s:else>
							<s:text name="label.title.category.edit" />
						</s:else>
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	                  <tr>
			        	<td class="inputtext"><s:text name="label.form.fields.category.name"/></td>
			            <td class="employeeinputtd">
			            	<sj:autocompleter  
							    name="subCategory.hcmoCategoryId.hcmoCategoryId"
								list="#request.APPL_CATEGORY_LIST"
								listKey="hcmoCategoryId"
								listValue="categoryName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
						<td class="inputerrormessage"><s:fielderror fieldName="subCategory.hcmoCategoryId.hcmoCategoryId"/></td>
					</tr>
					
					<!-- autocomplete text added by, R.Deepika-->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
					<tr>
					  <td class="inputtext">
						<s:text	name="label.form.fields.subCategory.name" />
					  </td>
					  <td class="employeeinputtd">
						<s:textfield name="subCategory.subCategoryName" cssClass="employeeinput"/>
					  </td>
					  <td class="inputerrormessage">
	                	<s:fielderror fieldName="subCategory.subCategoryName" />
	           		  </td>
					</tr>
			
		</table>
		</td>
		</tr>
		</table>
		</td>
		</tr>
		</table>
		</td>
		</tr>
		</table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorSubCategoryFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	 		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_SubCategoryFormId_div" indicator="indicatorSubCategoryFormId_div"/>
				</td>
				<s:if test="subCategory==null || subCategory.hcmoSubCategoryId==null">
				   <td>
		    		    <s:url var="resetSubCategory" action="resetSubCategory"></s:url>
		    	        <sj:submit href="%{resetSubCategory}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_SubCategoryFormId_div" indicator="indicatorSubCategoryFormId_div"/>
			       </td>
			   </s:if>
			   <s:else>
				    <td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			  </s:else>
			</tr>
		</table>
		<s:hidden name="subCategory.hcmoSubCategoryId"/>
	</s:form>
</div>