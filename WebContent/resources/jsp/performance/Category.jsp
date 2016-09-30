<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.CategoryVO"%>

<div id="submenu_category_list_div_id">
<div class="submenu_bg">
	<s:if test="#session.PERFORMANCECATEGORY_ADD == true">
		<sj:a href="setUpCategory.action" targets="submenu_category_list_div_id" indicator="indicatorSubMenuCategory" cssClass="link"><s:text name="MTIAddCategory" /></sj:a> |
	</s:if>
	<s:if test="#session.PERFORMANCECATEGORY_VIEW == true">
		<sj:a href="getAllCategory.action" targets="submenu_category_list_div_id" indicator="indicatorSubMenuCategory" cssClass="link"><s:text name="MTIViewCategory"/></sj:a> |
		<sj:a href="categorySearchForm.action" targets="submenu_category_list_div_id" indicator="indicatorSubMenuCategory" cssClass="link"><s:text name="MTISearchCategory"/></sj:a>
	</s:if>
</div>
<br/>
	<img id="indicatorSubMenuCategory" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.category.list"/></span></li></div>
	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.category.name" var="HCategory"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_categoryList_div_id">
	  		<display:table class="tableborder" id="categoryListId" name="categoryList" pagesize="${NO_OF_RECORDS}" requestURI="getAllCategory.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
			    <%
			    	try{
			    		String sCategoryId = ((CategoryVO)pageContext.getAttribute("categoryListId")).getHcmoCategoryId().toString();
			        	request.setAttribute("CategoryId", sCategoryId);    		
			    	}catch(NullPointerException ne){
			        }    	
			    %>
			    <display:column property="categoryName" title="${HCategory}" sortable="true" headerClass="sortable"/>
			    <s:if test="#session.PERFORMANCECATEGORY_VIEW==true">
					<display:column title="${HView}" class="viewedit" media="html">
						<s:url var="listViewCategory" action="categoryView">
							<s:param name="category.hcmoCategoryId" value="#request.CategoryId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_category_list_div_id','%{listViewCategory}','');"><s:text name="View"/></s:a>
					</display:column>
				</s:if>
			    <s:if test="#session.PERFORMANCECATEGORY_UPDATE==true">
					<display:column title="${HEdit}" class="viewedit" media="html">
						<s:url var="listSetUpCategory" action="setUpCategory">
							<s:param name="category.hcmoCategoryId" value="#request.CategoryId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_category_list_div_id','%{listSetUpCategory}','');"><s:text name="Edit"/></s:a>
					</display:column>
				</s:if>
			    <s:if test="#session.PERFORMANCECATEGORY_DELETE==true">
					<display:column title="${HDelete}" class="viewedit" media="html">
						<s:url var="listDeleteCategory" action="deleteCategory">
							<s:param name="category.hcmoCategoryId" value="#request.CategoryId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_category_list_div_id','%{listDeleteCategory}','');"><s:text name="Delete"/></s:a>
					</display:column>
				</s:if>
				 <display:setProperty name="export.csv.filename" value="Category.csv"/>
				 <display:setProperty name="export.excel.filename" value="Category.xls"/>
				 <display:setProperty name="export.xml.filename" value="Category.xml"/>
		  </display:table>
	  </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_categoryList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>   