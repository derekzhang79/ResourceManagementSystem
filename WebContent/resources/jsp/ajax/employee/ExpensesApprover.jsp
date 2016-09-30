<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.EmployeesVO"%>
<%@page import="com.gits.rms.vo.ExpensesApproverVO"%>

<div id="submenu_EssExpensesApprover_List_div">
<img id="indicatorExpenseApprList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:set name="UniqueExpApprEmployeeId" value="expApprover.hcmoEmployeeId.employeeId"></s:set>
	<s:url var="remoteExpApprForm" value="/setUpEmpInsertOrUpdateExpApprover.action">
		<s:param name="expApprover.hcmoEmployeeId.employeeId" value="#UniqueExpApprEmployeeId"></s:param>
	</s:url>
	<s:set name="UniqueExpApprEmployeeId" value="expApprover.hcmoEmployeeId.employeeId"></s:set>
	<s:url var="remoteExpApprView" value="/getEmployeeAllExpApprover.action">
		<s:param name="expApprover.hcmoEmployeeId.employeeId" value="#UniqueExpApprEmployeeId"></s:param>
	</s:url>
	<div class="submenu_bg">
		<s:if test="#session.EXPENSESAPPROVER_ADD==true">
			<sj:a href="%{remoteExpApprForm}" indicator="indicatorExpenseApprList" targets="submenu_EssExpensesApprover_List_div" cssClass="link"><s:text name="label.header.expenses.add"/></sj:a> |
		</s:if>
		<s:if test="#session.EXPENSESAPPROVER_VIEW==true">
			<sj:a href="%{remoteExpApprView}" indicator="indicatorExpenseApprList" targets="submenu_EssExpensesApprover_List_div" cssClass="link"><s:text name="label.header.expenses.view"/></sj:a>
		</s:if>
	</div>		

<br />		

<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
<div class="informationMessageSingle"><li><span><s:text name="label.title.expapprover.list"/></span></li></div>

            <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
            <s:text name="label.header.expapprover.EmployeeName" var="HEmpname"/>
		    <s:text name="label.header.expapprover.approvingEmployee" var="HApprovingemp"/>
		
		<s:if test="#session.EXPENSESAPPROVER_VIEW==true">
			<s:text name="label.header.common.view" var="HView"/>
		</s:if>
		<s:if test="#session.EXPENSESAPPROVER_UPDATE==true">
			<s:text name="label.header.common.edit" var="HEdit"/>
		</s:if>
		<s:if test="#session.EXPENSESAPPROVER_DELETE==true">
			<s:text name="label.header.common.delete" var="HDelete"/>
		</s:if>
		
	<display:table class="tableborder" id="expApproverListId" name="expApproverList" pagesize="${NO_OF_RECORDS}" requestURI="" sort="list" defaultsort="1" defaultorder="ascending">
	    <%
	    	try
	    {
	    		String sEmpId = ((ExpensesApproverVO)pageContext.getAttribute("expApproverListId")).getHcmoEmployeeId().getEmployeeId().toString();
	        	request.setAttribute("shcmoEmployeeId", sEmpId);
	        	String sExpAppId=((ExpensesApproverVO)pageContext.getAttribute("expApproverListId")).getApproverId().toString();
				request.setAttribute("sExpensesApproverId",sExpAppId);
	    }
	    catch(NullPointerException ne)
	    {
	    }    	
	    %>
	    
    <display:column property="hcmoEmployeeId.empFullName" title="${HEmpname}" sortable="false" headerClass="sortable"/>
    <display:column property="approvingEmployeeId.empFullName" title="${HApprovingemp}" sortable="false" headerClass="sortable"/>
	
				
			<s:if test="#session.EXPENSESAPPROVER_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="expensesApproverView" action="viewEmpExpenseApprover" escapeAmp="false">
						<s:param name="expApprover.hcmoEmployeeId.employeeId" value="#request.shcmoEmployeeId"></s:param>
						<s:param name="expApprover.approverId" value="#request.sExpensesApproverId" />
					</s:url> 
					<sj:a href="%{expensesApproverView}" targets="submenu_EssExpensesApprover_List_div" indicator="indicatorExpenseApprList"><s:text name="label.common.link.view"/></sj:a>
			   </display:column>
			</s:if>

			<s:if test="#session.EXPENSESAPPROVER_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="setUpEmpInsertOrUpdateExpApproverSingle" action="setUpEmpInsertOrUpdateExpApproverSingle" escapeAmp="false">
						<s:param name="expApprover.approverId" value="#request.sExpensesApproverId" />
						<s:param name="expApprover.hcmoEmployeeId.employeeId" value="#request.shcmoEmployeeId"></s:param>
					</s:url> 
					<sj:a href="%{setUpEmpInsertOrUpdateExpApproverSingle}" targets="submenu_EssExpensesApprover_List_div" indicator="indicatorExpenseApprList"><s:text name="label.common.link.edit"/></sj:a>
				</display:column>
			</s:if>
			<s:if test="#session.EXPENSESAPPROVER_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="deleteEmpExpApprover" action="deleteEmpExpApprover" escapeAmp="false">
					   <s:param name="expApprover.hcmoEmployeeId.employeeId" value="#request.shcmoEmployeeId"></s:param>
					   <s:param name="expApprover.approverId" value="#request.sExpensesApproverId" />
					</s:url> 
					<sj:a href="%{deleteEmpExpApprover}" targets="submenu_EssExpensesApprover_List_div" indicator="indicatorExpenseApprList"><s:text name="label.common.link.delete"/></sj:a>
				</display:column>
			</s:if>
	</display:table>
<br />
</div>