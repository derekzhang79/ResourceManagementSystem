<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.ExpensesAccountantApproverVO"%>

<div id="submenu_expenseAccountantApprover_searchList_div_id">
	<div class="submenu_bg">
		<s:if test="#session.EXPENSESACCOUNTANT_ADD == true">
			<sj:a href="setUpInsertOrUpdateExpAccountantApprover.action" targets="submenu_expenseAccountantApprover_searchList_div_id" indicator="indicatorSubMenuExpenseAccountantApproverSearchResId_div" cssClass="link"><s:text name="MTIAddExpenseAccountantApprover" /></sj:a> |
		</s:if>
		<s:if test="#session.EXPENSESACCOUNTANT_VIEW == true">
			<sj:a href="getAllExpAccountantApprover.action" targets="submenu_expenseAccountantApprover_searchList_div_id" indicator="indicatorSubMenuExpenseAccountantApproverSearchResId_div" cssClass="link"><s:text name="MTIViewExpenseAccountantApprover"/></sj:a> |
			<sj:a href="accountantSearchForm.action" targets="submenu_expenseAccountantApprover_searchList_div_id" indicator="indicatorSubMenuExpenseAccountantApproverSearchResId_div" cssClass="link"><s:text name="MTISearchExpenseAccountantApprover"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuExpenseAccountantApproverSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
						
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.expaccountapprover.list" /></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.expaccountantapprover.accountantEmployee" var="HExpensesAccountant"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_expenseAccountantApproverSearchList_div_id">
		   <display:table class="tableborder" id="expAccountantApproverListId" name="expAccountantApproverList" pagesize="${NO_OF_RECORDS}" requestURI="accountantSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sExpAccountantApprId = ((ExpensesAccountantApproverVO)pageContext.getAttribute("expAccountantApproverListId")).getHcmoExpensesAccountantId().toString();
		        	request.setAttribute("ExpensesAccountantApprId", sExpAccountantApprId);	
		    	}catch(NullPointerException ne){
		        }
		    	
		    %>
		    <display:column property="expensesAccountantId.empFullName" title="${HExpensesAccountant}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.EXPENSESACCOUNTANT_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewExpAccAppr" action="expAccountantApproverView">
						<s:param name="expAccountantApprover.hcmoExpensesAccountantId" value="#request.ExpensesAccountantApprId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_expenseAccountantApprover_searchList_div_id','%{listViewExpAccAppr}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.EXPENSESACCOUNTANT_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpExpAccAppr" action="setUpInsertOrUpdateExpAccountantApprover">
						<s:param name="expAccountantApprover.hcmoExpensesAccountantId" value="#request.ExpensesAccountantApprId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_expenseAccountantApprover_searchList_div_id','%{listSetUpExpAccAppr}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.EXPENSESACCOUNTANT_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html"> 
					<s:url var="listDeleteExpAccAppr" action="deleteExpAccountantApprover">
						<s:param name="expAccountantApprover.hcmoExpensesAccountantId" value="#request.ExpensesAccountantApprId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_expenseAccountantApprover_searchList_div_id','%{listDeleteExpAccAppr}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="Accountant.csv"/>
			 <display:setProperty name="export.excel.filename" value="Accountant.xls"/>
			 <display:setProperty name="export.xml.filename" value="Accountant.xml"/>
		  </display:table>
	  </div>
</div>  
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_expenseAccountantApproverSearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>