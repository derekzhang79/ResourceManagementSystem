<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.ExpensesApproverVO"%>

<div id="submenu_expenseOrgApprover_list_div_id">
		<br/>
		<img id="indicatorSubMenuExpenseApprover" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
						
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.expapprover.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.expapprover.approvingEmployee" var="HExpensesApprovingName"></s:text>
	  		   
	      <div id="display_tag_orgChartExpenseApproverList_div_id">
		      <display:table class="tableborder" id="expensesApproverNewListId" name="expApproverList" pagesize="${NO_OF_RECORDS}" requestURI="orgChartEmployeeExpenseApproverNewTab.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sExpensesApproverId = ((ExpensesApproverVO)pageContext.getAttribute("expensesApproverListId")).getApproverId().toString();
		        	request.setAttribute("ExpensesApproverId", sExpensesApproverId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="approvingEmployeeId.empFullName" title="${HExpensesApprovingName}" sortable="true" headerClass="sortable"/>
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
   		 	jQuery("#display_tag_orgChartExpenseApproverList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>        