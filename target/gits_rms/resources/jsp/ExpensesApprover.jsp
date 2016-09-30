<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.ExpensesApproverVO"%>

<div id="submenu_expenseApprover_list_div_id">
	<div class="submenu_bg">
		<s:if test="#session.EXPENSESAPPROVER_ADD == true">
			<sj:a href="setUpInsertOrUpdateExpApprover.action" targets="submenu_expenseApprover_list_div_id" indicator="indicatorSubMenuExpenseApproverId_div" cssClass="link"><s:text name="MTIAddExpenseApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.EXPENSESAPPROVER_VIEW == true">
			<sj:a href="getAllExpApprover.action" targets="submenu_expenseApprover_list_div_id" indicator="indicatorSubMenuExpenseApproverId_div" cssClass="link"><s:text name="MTIViewExpenseApprover"/></sj:a> |
			<sj:a href="expAppSearchForm.action" targets="submenu_expenseApprover_list_div_id" indicator="indicatorSubMenuExpenseApproverId_div" cssClass="link"><s:text name="MTISearchExpenseApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuExpenseApproverId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
						
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.expapprover.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.expapprover.EmployeeName" var="HExpensesApproveEmpName"></s:text>
	  <s:text name="label.header.expapprover.approvingEmployee" var="HExpensesApprovingName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	 <div id="display_tag_expenseApproverList_div_id">
		 <display:table class="tableborder" id="expensesApproverListId" name="expApproverList" pagesize="${NO_OF_RECORDS}" requestURI="getAllExpApprover.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sExpensesApproverId = ((ExpensesApproverVO)pageContext.getAttribute("expensesApproverListId")).getApproverId().toString();
		        	request.setAttribute("ExpensesApproverId", sExpensesApproverId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="hcmoEmployeeId.empFullName" title="${HExpensesApproveEmpName}" sortable="true" headerClass="sortable"/>
		    <display:column property="approvingEmployeeId.empFullName" title="${HExpensesApprovingName}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.EXPENSESAPPROVER_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewExpensesApprover" action="expensesApproverView">
						<s:param name="expApprover.approverId" value="#request.ExpensesApproverId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_expenseApprover_list_div_id','%{listViewExpensesApprover}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.EXPENSESAPPROVER_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpExpensesApprover" action="setUpInsertOrUpdateExpApprover">
						<s:param name="expApprover.approverId" value="#request.ExpensesApproverId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_expenseApprover_list_div_id','%{listSetUpExpensesApprover}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.EXPENSESAPPROVER_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteExpensesApprover" action="deleteExpApprover">
						<s:param name="expApprover.approverId" value="#request.ExpensesApproverId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_expenseApprover_list_div_id','%{listDeleteExpensesApprover}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="ExpenseApprover.csv"/>
			 <display:setProperty name="export.excel.filename" value="ExpenseApprover.xls"/>
			 <display:setProperty name="export.xml.filename" value="ExpenseApprover.xml"/>
		  </display:table>
	 </div>
</div>   
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_expenseApproverList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script> 