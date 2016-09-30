
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.EmpUSTaxVO"%>
<div id="submenu_EmployeeUSTaxId_div">
	<div class="submenu_bg">
		<s:if test="#session.EMPLOYEEUSTAX_ADD == true">
			<sj:a href="setUpEmpUSTax.action" targets="submenu_EmployeeUSTaxId_div" indicator="indicatorSubMenuEmployeeUSTaxId_div" cssClass="link"><s:text name="MTIAddEmployeeUSTax" /></sj:a> |
		</s:if>
		<s:if test="#session.EMPLOYEEUSTAX_VIEW == true">
			<sj:a href="getAllEmpUSTax.action" targets="submenu_EmployeeUSTaxId_div" indicator="indicatorSubMenuEmployeeUSTaxId_div" cssClass="link"><s:text name="MTIViewEmployeeUSTax"/></sj:a> |
			<sj:a href="usTaxSearchForm.action" targets="submenu_EmployeeUSTaxId_div" indicator="indicatorSubMenuEmployeeUSTaxId_div" cssClass="link"><s:text name="MTISearchEmployeeUSTax"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeeUSTaxId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
				
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
		
	<div class="informationMessageSingle"><li><span><s:text name="label.title.empUsTax.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HUsTaxEmpName"></s:text>
	  <s:text name="label.header.empUSTax.federalStatus" var="HUsTaxFederalStatus"></s:text>
	  <s:text name="label.header.empUSTax.federalException" var="HUsTaxFederalExc"></s:text>
	  <s:text name="label.header.empUSTax.state" var="HUsTaxState"></s:text>
	  <s:text name="label.header.empUSTax.stateStatus" var="HUsTaxStateStatus"></s:text>
	  <s:text name="label.header.empUSTax.stateException" var="HUsTaxStateExc"></s:text>
	  <s:text name="label.header.empUSTax.unempState" var="HUsTaxUmEmpState"></s:text>
	  <s:text name="label.header.empUSTax.workState" var="HUsTaxWorkState"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <display:table class="tableborder" id="empUSTaxListId" name="empUSTaxList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEmpUSTax.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
	    <%
	    	try{
	    		String sEmployeeUSTaxId = ((EmpUSTaxVO)pageContext.getAttribute("empUSTaxListId")).getHcmoEmpUsTaxId().toString();
	        	request.setAttribute("EmployeeUSTaxId", sEmployeeUSTaxId);	
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="hcmoEmployeeId.empFullName" title="${HUsTaxEmpName}" sortable="true" headerClass="sortable"/>
	    <display:column property="taxFederalStatus" title="${HUsTaxFederalStatus}" sortable="true" headerClass="sortable"/>
	    <display:column property="taxFederalExceptions" title="${HUsTaxFederalExc}" sortable="true" headerClass="sortable"/>
	    <display:column property="taxState.name" title="${HUsTaxState}" sortable="true" headerClass="sortable"/>
	    <display:column property="taxStateStatus" title="${HUsTaxStateStatus}" sortable="true" headerClass="sortable"/>
	    <display:column property="taxStateExceptions" title="${HUsTaxStateExc}" sortable="true" headerClass="sortable"/>
	    <display:column property="taxUnempState.name" title="${HUsTaxUmEmpState}" sortable="true" headerClass="sortable"/>
	    <display:column property="taxWorkState.name" title="${HUsTaxWorkState}" sortable="true" headerClass="sortable"/>
	    <s:if test="#session.EMPLOYEEUSTAX_VIEW==true">
			<display:column title="${HView}" class="viewedit" media="html">
				<s:url var="listViewEmployeeUSTax" action="empUSTaxView">
					<s:param name="empUSTax.hcmoEmpUsTaxId" value="#request.EmployeeUSTaxId"></s:param>
				</s:url>
				<sj:a href="%{listViewEmployeeUSTax}" targets="submenu_EmployeeUSTaxId_div" indicator="indicatorSubMenuEmployeeUSTaxId_div"><s:text name="View"/></sj:a>
			</display:column>
		</s:if>
	    <s:if test="#session.EMPLOYEEUSTAX_UPDATE==true">
			<display:column title="${HEdit}" class="viewedit" media="html">
				<s:url var="listSetUpEmployeeUSTax" action="setUpEmpUSTax">
					<s:param name="empUSTax.hcmoEmpUsTaxId" value="#request.EmployeeUSTaxId"></s:param>
				</s:url>
				<sj:a href="%{listSetUpEmployeeUSTax}" targets="submenu_EmployeeUSTaxId_div" indicator="indicatorSubMenuEmployeeUSTaxId_div"><s:text name="Edit"/></sj:a>
			</display:column>
		</s:if>
	    <s:if test="#session.EMPLOYEEUSTAX_DELETE==true">
			<display:column title="${HDelete}" class="viewedit" media="html">
				<s:url var="listDeleteEmployeeUSTax" action="deleteEmpUSTax">
					<s:param name="empUSTax.hcmoEmpUsTaxId" value="#request.EmployeeUSTaxId"></s:param>
				</s:url>
				<sj:a href="%{listDeleteEmployeeUSTax}" targets="submenu_EmployeeUSTaxId_div" indicator="indicatorSubMenuEmployeeUSTaxId_div"><s:text name="Delete"/></sj:a>
			</display:column>
		</s:if>
		 <display:setProperty name="export.csv.filename" value="EmployeeUSTax.csv"/>
		 <display:setProperty name="export.excel.filename" value="EmployeeUSTax.xls"/>
		 <display:setProperty name="export.xml.filename" value="EmployeeUSTax.xml"/>
	  </display:table>
</div>