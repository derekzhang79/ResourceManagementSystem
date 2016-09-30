<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.UserVO"%>

<div id="submenu_user_list_div_id">
	<div class="submenu_bg">
		<s:if test="#session.USER_ADD == true">
			<sj:a href="setUpUser.action" targets="submenu_user_list_div_id" indicator="indicatorSubMenuUserId_div" cssClass="link"><s:text name="MTIAddUser" /></sj:a> |
		</s:if>
		
		<s:if test="#session.USER_VIEW == true">
			<sj:a href="getAllUser.action" targets="submenu_user_list_div_id" indicator="indicatorSubMenuUserId_div" cssClass="link"><s:text name="MTIViewUser"/></sj:a> |
			<sj:a href="userSearchForm.action" targets="submenu_user_list_div_id" indicator="indicatorSubMenuUserId_div" cssClass="link"><s:text name="MTISearchUser"/></sj:a>
		</s:if>
	</div>
	<br/>
	<img id="indicatorSubMenuUserId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.user.list"/></span></li></div>		   
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HUserEmpName"></s:text>
	  <s:text name="label.header.user.userName" var="HUserName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	 <div id="display_tag_userList_div_id">
		 <display:table class="tableborder" id="userListId" name="userList" pagesize="${NO_OF_RECORDS}" requestURI="getAllUser.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sUserId = ((UserVO)pageContext.getAttribute("userListId")).getHcmouserId().toString();
		        	request.setAttribute("UserId", sUserId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="empIdObj.empFullName" title="${HUserEmpName}" sortable="true" headerClass="sortable"/>
		    <display:column property="userName" title="${HUserName}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.USER_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewUser" action="userView">
						<s:param name="user.hcmouserId" value="#request.UserId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_user_list_div_id','%{listViewUser}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.USER_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpUser" action="setUpUser">
						<s:param name="user.hcmouserId" value="#request.UserId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_user_list_div_id','%{listSetUpUser}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.USER_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteUser" action="deleteUser">
						<s:param name="user.hcmouserId" value="#request.UserId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_user_list_div_id','%{listDeleteUser}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="User.csv"/>
			 <display:setProperty name="export.excel.filename" value="User.xls"/>
			 <display:setProperty name="export.xml.filename" value="User.xml"/>
		  </display:table>
	 </div>
	  
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_userList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     