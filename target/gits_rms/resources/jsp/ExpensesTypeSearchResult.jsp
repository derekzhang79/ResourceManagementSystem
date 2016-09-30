<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.ExpensesTypeVO"%>

<div id="submenu_expenseType_searchList_div_id">
<div class="submenu_bg">
	<s:if test="#session.EXPENSETYPE_ADD == true">
		<sj:a href="setUpInsertOrUpdateExpType.action" targets="submenu_expenseType_searchList_div_id" indicator="indicatorSubMenuExpenseTypeSearchResId_div" cssClass="link"><s:text name="MTIAddExpenseType" /></sj:a> |
	</s:if>
	<s:if test="#session.EXPENSETYPE_VIEW == true">
		<sj:a href="getAllExpType.action" targets="submenu_expenseType_searchList_div_id" indicator="indicatorSubMenuExpenseTypeSearchResId_div" cssClass="link"><s:text name="MTIViewExpenseType"/></sj:a> |
		<sj:a href="expTypeSearchForm.action" targets="submenu_expenseType_searchList_div_id" indicator="indicatorSubMenuExpenseTypeSearchResId_div" cssClass="link"><s:text name="MTISearchExpenseType"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuExpenseTypeSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.expenseType.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.expenseType.name" var="HExpensesType"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_expenseTypeSearchList_div_id">
		   <display:table class="tableborder" id="expenseTypeListId" name="expTypeList" pagesize="${NO_OF_RECORDS}" requestURI="expTypeSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sExpenseTypeId = ((ExpensesTypeVO)pageContext.getAttribute("expenseTypeListId")).getHcmoExpensesTypeId().toString();
		        	request.setAttribute("ExpenseTypeId", sExpenseTypeId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="name" title="${HExpensesType}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.EXPENSETYPE_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewExpenseType" action="expensesTypeView">
						<s:param name="expType.hcmoExpensesTypeId" value="#request.ExpenseTypeId"></s:param>
					</s:url>
				  <s:a href="#" onclick="doPostCall('submenu_expenseType_searchList_div_id','%{listViewExpenseType}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.EXPENSETYPE_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpExpenseType" action="setUpInsertOrUpdateExpType">
						<s:param name="expType.hcmoExpensesTypeId" value="#request.ExpenseTypeId"></s:param>
					</s:url>
				  <s:a href="#" onclick="doPostCall('submenu_expenseType_searchList_div_id','%{listSetUpExpenseType}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.EXPENSETYPE_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteExpenseType" action="deleteExpType">
						<s:param name="expType.hcmoExpensesTypeId" value="#request.ExpenseTypeId"></s:param>
					</s:url>
				  <s:a href="#" onclick="doPostCall('submenu_expenseType_searchList_div_id','%{listDeleteExpenseType}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			<display:setProperty name="export.csv.filename" value="ExpensesType.csv"/>
			<display:setProperty name="export.excel.filename" value="ExpensesType.xls"/>
			<display:setProperty name="export.xml.filename" value="ExpensesType.xml"/>
		  </display:table>
	  </div>
	 
</div>   
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_expenseTypeSearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     