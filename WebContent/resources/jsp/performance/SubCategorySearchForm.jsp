<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_SubCategorySearchId_div">
<div class="submenu_bg">
	<s:if test="#session.PERFORMANCESUBCATEGORY_ADD == true">
		<sj:a href="setUpSubCategory.action" targets="submenu_SubCategorySearchId_div" indicator="indicatorSubMenuSubCategorySearchId_div" cssClass="link"><s:text name="MTIAddSubCategory" /></sj:a> |
	</s:if>
	<s:if test="#session.PERFORMANCESUBCATEGORY_VIEW == true">
		<sj:a href="getAllSubCategory.action" targets="submenu_SubCategorySearchId_div" indicator="indicatorSubMenuSubCategorySearchId_div" cssClass="link"><s:text name="MTIViewSubCategory"/></sj:a> |
		<sj:a href="subCategorySearchForm.action" targets="submenu_SubCategorySearchId_div" indicator="indicatorSubMenuSubCategorySearchId_div" cssClass="link"><s:text name="MTISearchSubCategory"/></sj:a>	
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuSubCategorySearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	    <s:form action="subCategorySearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.subCategory.search"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign">
	               
	               <tr>
	            	<td class="inputtext"><s:text name="label.header.category.name"/></td>
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
	        	</tr> 
	     	 
					<!-- autocomplete text added by, R.Deepika-->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
		         <tr><td class="inputtext"><s:text name="label.header.subCategory.name"/></td>
		         	        <td class="employeeinputtd"><s:textfield name="subCategory.subCategoryName" cssClass="employeeinput"/></td>
		         	        <td class="inputerrormessage"></td>
		         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorSubCategoryForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_SubCategorySearchId_div" indicator="indicatorSubCategoryForm"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>