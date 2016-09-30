<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.ChildrenVO"%>

<div id="submenu_children_list_div_id">
		<div class="submenu_bg">
			<s:if test="#session.CHILDREN_ADD == true">
				<sj:a href="setUpChildren.action" targets="submenu_children_list_div_id" indicator="indicatorSubMenuChildrenViewListId" cssClass="link"><s:text name="MTIAddChildren" /></sj:a> |
			</s:if>
			<s:if test="#session.CHILDREN_VIEW == true">
				<sj:a href="getAllChildren.action" targets="submenu_children_list_div_id" indicator="indicatorSubMenuChildrenViewListId" cssClass="link"><s:text name="MTIViewChildren"/></sj:a> |
				<sj:a href="childrenSearchForm.action" targets="submenu_children_list_div_id" indicator="indicatorSubMenuChildrenViewListId" cssClass="link"><s:text name="MTISearchChildren"/></sj:a>
			</s:if>
		</div>
			<br/>
			<img id="indicatorSubMenuChildrenViewListId" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.children.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HChildrenEmpName"></s:text>
	  <s:text name="label.header.children.name" var="HChildrenName"></s:text>
	  <s:text name="label.header.common.dob" var="HChildrenDob"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	
	 <div id="display_tag_childrenList_div_id">
		 <display:table class="tableborder" id="childListId" name="childList" pagesize="${NO_OF_RECORDS}" requestURI="getAllChildren.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sChildId = ((ChildrenVO)pageContext.getAttribute("childListId")).getEmpChildrenId().toString();
		        	request.setAttribute("ChildId", sChildId);    			
		    	}catch(NullPointerException ne){
		        }
		    	
		    %>
		    <display:column property="empIdObj.empFullName" title="${HChildrenEmpName}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.CHILDREN_CHILDNAME_VIEW==true">
		    	<display:column property="childName" title="${HChildrenName}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.CHILDREN_DOB_VIEW==true">
		    	<display:column property="childDOB" title="${HChildrenDob}" format="{0,date,MM-dd-yyyy}"  sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.CHILDREN_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewChildren" action="childrenView">
						<s:param name="child.empChildrenId" value="#request.ChildId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_children_list_div_id','%{listViewChildren}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.CHILDREN_UPDATE==true">
		    	<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpChildren" action="setUpChildren">
						<s:param name="child.empChildrenId" value="#request.ChildId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_children_list_div_id','%{listSetUpChildren}','');"><s:text name="Edit"/></s:a>
		    	</display:column>
			</s:if>
		    <s:if test="#session.CHILDREN_DELETE==true">
		    	<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteChildren" action="deleteChildren">
						<s:param name="child.empChildrenId" value="#request.ChildId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_children_list_div_id','%{listDeleteChildren}','');"><s:text name="Delete"/></s:a>
		    	</display:column>
			</s:if>
			<display:setProperty name="export.csv.filename" value="Children.csv"/>
			 <display:setProperty name="export.excel.filename" value="Children.xls"/>
			 <display:setProperty name="export.xml.filename" value="Children.xml"/>
		  </display:table>
	 </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_childrenList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script> 