<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.EmployeesVO"%>
<%@page import="com.gits.rms.vo.ChildrenVO"%>

<div id="submenu_EssChildren_List_div">
<img id="indicatorChiList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

   	<s:set name="UniqueChiEmployeeId" value="child.empIdObj.employeeId"></s:set>
	<s:url var="remoteChiForm" value="/setUpEmpChildren.action">
		<s:param name="child.empIdObj.employeeId" value="#UniqueChiEmployeeId"></s:param>
	</s:url>
		<s:set name="UniqueChiEmployeeId" value="child.empIdObj.employeeId"></s:set>
	<s:url var="remoteChiView" value="/getEmployeeAllChildren.action">
		<s:param name="child.empIdObj.employeeId" value="#UniqueChiEmployeeId"></s:param>
	</s:url>
	<div class="submenu_bg">
	    <s:if test="#session.CHILDREN_ADD==true">
			<sj:a href="%{remoteChiForm}" indicator="indicatorChiList" targets="submenu_EssChildren_List_div" cssClass="link"><s:text name="label.header.children.add"/></sj:a> |
		</s:if>
		<s:if test="#session.CHILDREN_ADD==true">
			<sj:a href="%{remoteChiView}" indicator="indicatorChiList" targets="submenu_EssChildren_List_div" cssClass="link"><s:text name="label.header.children.view"/></sj:a>
		</s:if>
	</div>		
<br />

<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

<div class="informationMessageSingle"><li><span><s:text name="label.title.children.list"/></span></li></div>

       <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>  
       <s:text name="label.header.common.empName" var="HEmpname"/>
       <s:text name="label.header.children.name" var="HChildrenName"/>
       <s:text name="label.header.common.dob" var="Hdob"/>
       
       <s:if test="#session.CHILDREN_VIEW==true">
	        <s:text name="label.header.common.view" var="HView"/>
	   </s:if>
       <s:if test="#session.CHILDREN_UPDATE==true">
	        <s:text name="label.header.common.edit" var="HEdit"/>
	   </s:if>
       <s:if test="#session.CHILDREN_DELETE==true">
        	<s:text name="label.header.common.delete" var="HDelete"/>
       </s:if>
   <display:table class="tableborder" id="childListId" name="childList" pagesize="${NO_OF_RECORDS}" requestURI="" sort="list" defaultsort="1" defaultorder="ascending">
           <%
		  try
           {
			  String sEmpId=((ChildrenVO)pageContext.getAttribute("childListId")).getEmpIdObj().getEmployeeId().toString();
			  request.setAttribute("sEmployeeId",sEmpId);
			  String sChildId=((ChildrenVO)pageContext.getAttribute("childListId")).getEmpChildrenId().toString();
			  request.setAttribute("sChildrenId",sChildId);
		  }
           catch(NullPointerException ne){
		  }
		%>
            <display:column property="empIdObj.empFullName" title="${HEmpname}" sortable="false" headerClass="sortable"/>
            <display:column property="childName" title="${HChildrenName}" sortable="false" headerClass="sortable"/>  
            <display:column property="childDOB" title="${Hdob}" format="{0,date,MM-dd-yyyy}" sortable="false" headerClass="sortable"/>
           
           	  	<s:if test="#session.CHILDREN_VIEW==true">
	            <display:column title="${HView}" class="viewedit" media="html">
	                	<s:url var="childrenView" action="viewEmpChildren" escapeAmp="false">
							<s:param name="child.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
			       		   	<s:param name="child.empChildrenId" value="#request.sChildrenId"/>
			       		</s:url> 
	                <sj:a href="%{childrenView}" targets="submenu_EssChildren_List_div" indicator="indicatorChiList"><s:text name="label.common.link.view"/></sj:a>
	          </display:column>
           </s:if>
           	
           	<s:if test="#session.CHILDREN_UPDATE==true">
	            <display:column title="${HEdit}" class="viewedit" media="html">
	                	<s:url var="setUpEmpChildrenSingle" action="setUpEmpChildrenSingle" escapeAmp="false">
							<s:param name="child.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
			       		   	<s:param name="child.empChildrenId" value="#request.sChildrenId"/>
			       		</s:url> 
	                <sj:a href="%{setUpEmpChildrenSingle}" targets="submenu_EssChildren_List_div" indicator="indicatorChiList"><s:text name="label.common.link.edit"/></sj:a>
	          </display:column>
           </s:if>
           <s:if test="#session.CHILDREN_DELETE==true">
	          <display:column title="${HDelete}" class="viewedit" media="html">
		                <s:url var="deleteEmpChildren" action="deleteEmpChildren" escapeAmp="false">
							<s:param name="child.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
			       		   <s:param name="child.empChildrenId" value="#request.sChildrenId"/>
			       		</s:url>
	                <sj:a href="%{deleteEmpChildren}" targets="submenu_EssChildren_List_div" indicator="indicatorChiList"><s:text name="label.common.link.delete"/></sj:a>
	          </display:column>
           </s:if>
           
    </display:table>
    </div>
<br/>