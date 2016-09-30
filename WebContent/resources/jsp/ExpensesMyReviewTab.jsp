<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="com.gits.rms.vo.EmployeeExpensesVO"%>

<div id="myReviewDiv">
<jsp:include page="common/messages.jsp" flush="true"/>
<div class="informationMessageSingle"><li><span><s:text name="label.title.expensereview.list"/></span></li></div>	
<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  		<s:text name="label.header.expenseforapproval.empName" var="HEmpName"></s:text>
	  		<s:text name="label.header.expenseforapproval.requestDate" var="HReqDate"></s:text>
	  		<s:text name="label.header.expenseforapproval.totalAmount" var="HtotalAmt"></s:text>
	  		<s:text name="label.common.link.edit" var="HEdit"></s:text>
	  		
              <img id="indicatorExpensesMMyReviewTab" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
              <display:table class="tableborder" id="expenseListId" name="expenseList" requestURI="myExpensesReview.action" pagesize="${NO_OF_RECORDS}" sort="list" defaultsort="1" defaultorder="ascending" export="true">
              	<%
		    	try{
		    		String shcmoExpId = ((EmployeeExpensesVO)pageContext.getAttribute("expenseListId")).getHcmoExpensesId().toString();
		        	request.setAttribute("HcmoExpId", shcmoExpId);
		        	
		        	String shcmoExpAttacId = ((EmployeeExpensesVO)pageContext.getAttribute("expenseListId")).getExpAttachId();
		        	request.setAttribute("HcmoExpAttachId", shcmoExpAttacId);
		        	
		        	
		        	}catch(NullPointerException ne){
		        }    	
	    	%>
              
             <display:column property="hcmoEmployeeId.empFirstName" title="${HEmpName}"  headerClass="sortable"/>
             <display:column property="createdDate" title="${HReqDate}"  headerClass="sortable" format="{0,date,MM-dd-yyyy}"/>
             <display:column property="curTypeValueForTotalAmountField" title="${HtotalAmt}" headerClass="sortable" format=" $ {0,number,0.00}"/>
                      
							
			<display:column title="${HEdit}" class="viewedit" media="html">
				<s:url var="getMyReviewExpensesDetail" action="getMyReviewExpensesDetail">
					<s:param name="empExpenses.hcmoExpensesId" value="#request.HcmoExpId"/>
				</s:url>
				<sj:submit href="%{getMyReviewExpensesDetail}" targets="myReviewDiv" cssClass="submitbutton117" indicator="indicatorExpensesMMyReviewTab" key="label.common.link.edit"></sj:submit>
			</display:column>
                         
             <display:setProperty name="export.csv.filename" value="MyExpenseReview.csv"/>
			 <display:setProperty name="export.excel.filename" value="MyExpenseReview.xls"/>
			 <display:setProperty name="export.xml.filename" value="MyExpenseReview.xml"/>
			 
</display:table>
</div>