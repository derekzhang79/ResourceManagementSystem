<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.SubCategoryVO"%>

<div id="submenu_subCategory_list_div_id">
<div class="submenu_bg">
	<s:if test="#session.PERFORMANCESUBCATEGORY_ADD == true">
		<sj:a href="setUpSubCategory.action" targets="submenu_subCategory_list_div_id" indicator="indicatorSubMenuSubCategoryId_div" cssClass="link"><s:text name="MTIAddSubCategory" /></sj:a> |
	</s:if>
	<s:if test="#session.PERFORMANCESUBCATEGORY_VIEW == true">
		<sj:a href="getAllSubCategory.action" targets="submenu_subCategory_list_div_id" indicator="indicatorSubMenuSubCategoryId_div" cssClass="link"><s:text name="MTIViewSubCategory"/></sj:a> |
		<sj:a href="subCategorySearchForm.action" targets="submenu_subCategory_list_div_id" indicator="indicatorSubMenuSubCategoryId_div" cssClass="link"><s:text name="MTISearchSubCategory"/></sj:a>	
	</s:if>
	
</div>
<br/>
<img id="indicatorSubMenuSubCategoryId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.subCategory.list"/></span></li></div>
	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.category.name" var="HCategory"></s:text>
	  <s:text name="label.header.subCategory.name" var="HSubCategoryName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_subcategoryList_div_id">
		  <display:table class="tableborder" id="subCategoryListId" name="subCategoryList" pagesize="${NO_OF_RECORDS}" requestURI="getAllSubCategory.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sSubCategoryId = ((SubCategoryVO)pageContext.getAttribute("subCategoryListId")).getHcmoSubCategoryId().toString();
		        	request.setAttribute("SubCategoryId", sSubCategoryId);    		
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="hcmoCategoryId.categoryName" title="${HCategory}" sortable="true" headerClass="sortable"/>
		    <display:column property="subCategoryName" title="${HSubCategoryName}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.PERFORMANCESUBCATEGORY_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewSubCategory" action="subCategoryView">
						<s:param name="subCategory.hcmoSubCategoryId" value="#request.SubCategoryId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_subCategory_list_div_id','%{listViewSubCategory}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.PERFORMANCESUBCATEGORY_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpSubCategory" action="setUpSubCategory">
						<s:param name="subCategory.hcmoSubCategoryId" value="#request.SubCategoryId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_subCategory_list_div_id','%{listSetUpSubCategory}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.PERFORMANCESUBCATEGORY_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteSubCategory" action="deleteSubCategory">
						<s:param name="subCategory.hcmoSubCategoryId" value="#request.SubCategoryId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_subCategory_list_div_id','%{listDeleteSubCategory}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="SubCategory.csv"/>
			 <display:setProperty name="export.excel.filename" value="SubCategory.xls"/>
			 <display:setProperty name="export.xml.filename" value="SubCategory.xml"/>
		  </display:table>    
	  </div>
	  
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_subcategoryList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>   