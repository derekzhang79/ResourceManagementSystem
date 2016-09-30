<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.UserVO"%>
<%@page import="com.gits.rms.vo.EmployeesVO"%>

<div id="submenu_EssUser_List_div">
<img id="indicatorUserList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

   	<s:set name="UniqueUserEmployeeId" value="user.empIdObj.employeeId"></s:set>
	<s:url var="remoteUserForm" value="/setUpEmpUser.action">
		<s:param name="user.empIdObj.employeeId" value="#UniqueUserEmployeeId"></s:param>
	</s:url>
	<s:set name="UniqueUserEmployeeId" value="user.empIdObj.employeeId"></s:set>
	<s:url var="remoteUserView" value="/getEmployeeAllUser.action">
		<s:param name="user.empIdObj.employeeId" value="#UniqueUserEmployeeId"></s:param>
	</s:url>
	<div class="submenu_bg">
	    <s:if test="#session.USER_ADD==true">
			<sj:a href="%{remoteUserForm}" indicator="indicatorUserList" targets="submenu_EssUser_List_div" cssClass="link"><s:text name="label.header.user.add"/></sj:a> |
		</s:if>
		<s:if test="#session.USER_VIEW==true">
			<sj:a href="%{remoteUserView}" indicator="indicatorUserList" targets="submenu_EssUser_List_div" cssClass="link"><s:text name="label.header.user.view"/></sj:a>
		</s:if>
	</div>		
<br />		

<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

<div class="informationMessageSingle"><li><span><s:text name="label.title.user.list"/></span></li></div>
 
           <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
           <s:text name="label.header.common.empName" var="HEmpName"/>
           <s:text name="label.header.user.name" var="HUserName"/>
          
           <s:if test="#session.USER_VIEW==true">
              <s:text name="label.header.common.view" var="HView"/>
           </s:if>
           
           <s:if test="#session.USER_UPDATE==true">
              <s:text name="label.header.common.edit" var="HEdit"/>
           </s:if>
           <s:if test="#session.USER_DELETE==true">   
              <s:text name="label.header.common.delete" var="HDelete"/>
           </s:if>
           
     <display:table class="tableborder" id="userListId" name="userList" pagesize="${NO_OF_RECORDS}" requestURI="" sort="list" defaultsort="1" defaultorder="ascending">
           <%
		  try
           {
			  String sEmpId=((UserVO)pageContext.getAttribute("userListId")).getEmpIdObj().getEmployeeId().toString();
			  request.setAttribute("sEmployeeId",sEmpId);
			  String sUserId=((UserVO)pageContext.getAttribute("userListId")).getHcmouserId().toString();
			  request.setAttribute("sUserId",sUserId);
		  }
           catch(NullPointerException ne){
		  }
		%>
            <display:column property="empIdObj.empFullName" title="${HEmpName}" sortable="false" headerClass="sortable"/>
            <display:column property="userName" title="${HUserName}" sortable="false" headerClass="sortable"/>
            
        	   <s:if test="#session.USER_VIEW==true">
        	    <display:column title="${HView}" class="viewedit" media="html">
	                	<s:url var="userView" action="viewEmpUser" escapeAmp="false">
							<s:param name="user.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
			       		   	<s:param name="user.hcmouserId" value="#request.sUserId"/>
			       		</s:url> 
		                <sj:a href="%{userView}" targets="submenu_EssUser_List_div" indicator="indicatorUserList"><s:text name="label.common.link.view"/></sj:a>
	           </display:column>
	        </s:if>
        	 
        	 <s:if test="#session.USER_UPDATE==true">
        	    <display:column title="${HEdit}" class="viewedit" media="html">
	                	<s:url var="setUpEmpUserSingle" action="setUpEmpUserSingle" escapeAmp="false">
							<s:param name="user.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
			       		   	<s:param name="user.hcmouserId" value="#request.sUserId"/>
			       		</s:url> 
		                <sj:a href="%{setUpEmpUserSingle}" targets="submenu_EssUser_List_div" indicator="indicatorUserList"><s:text name="label.common.link.edit"/></sj:a>
	           </display:column>
	        </s:if>
            <s:if test="#session.USER_DELETE==true">
            	<display:column title="${HDelete}" class="viewedit" media="html">
		                <s:url var="deleteEmpUser" action="deleteEmpUser" escapeAmp="false">
							<s:param name="user.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
			       		    <s:param name="user.hcmouserId" value="#request.sUserId"/>
			       		</s:url>
	                <sj:a href="%{deleteEmpUser}" targets="submenu_EssUser_List_div" indicator="indicatorUserList"><s:text name="label.common.link.delete"/></sj:a>
	            </display:column>
	        </s:if>       
      </display:table>

<br />
</div>