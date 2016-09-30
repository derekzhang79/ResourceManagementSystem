<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="com.gits.rms.vo.ExpensesTypeVO"%>
<%@page import="com.gits.rms.vo.ProjectVO"%>
<%@page import="com.gits.rms.vo.ExpensesDetailsVO"%>

<div id="expenseAprovalEmpViewDivId">
<jsp:include page="common/messages.jsp" flush="true"/>
<table class="borderAllForExpense">
	<s:if test="expAttachIdList.size()==0">
	</s:if>
	<s:else>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.expenseAttachment.list"/></span></li>
	<tr><td class="employeeinputtd"><s:text name="label.title.timesheetattach.upload.msg"></s:text></td></tr>
	
    <s:iterator value="expAttachIdList">
    <tr align="center">
    <td>
    <s:url var="expensesFileDownload" action="expensesFileDownload">
		<s:param name="expAttach.hcmoExpensesAttachId" value="hcmoExpensesAttachId"/>
	</s:url>
	<s:a href="%{expensesFileDownload}"><s:property value="expensesAttachFileName"/></s:a></td>
	</tr>
	</s:iterator></div>
	</s:else>
</table></br></br>
	
	    <img id="indicatorExpAppAndSubmitTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
        <div class="informationMessageSingle"><li><span><s:text name="label.title.expensesApprovalView"/></span></li></div>
        <s:text name="label.header.expenseforapproval.expenseDate" var="HExpDate"/>
        <s:text name="label.header.expenseforapproval.projectName" var="HProname"/>
        <s:text name="label.header.expenseforapproval.expenseType" var="HExptype"/>
        <s:text name="label.header.expenseforapproval.amount" var="Hamount"/>
        <s:text name="label.header.expenseforapproval.note" var="HNote"/>
        <s:text name="label.header.expenseforapproval.description" var="HDesc"/>
        

   <display:table class="tableborder" id="empExpenseDetailListId" name="empExpenseDetailList"  requestURI="" sort="list" defaultsort="1" defaultorder="ascending" export="false">
		<display:column property="expensesDate" title="${HExpDate}" format="{0,date,MM/dd/yyyy}" sortable="false" headerClass="sortable"/>
		<display:column property="projectId.projectName" title="${HProname}" sortable="false" headerClass="sortable"/>
		<display:column property="hcmoExpensesTypeId.name" title="${HExptype}" sortable="false" headerClass="sortable"/>
		<display:column property="curTypeValueForAmountField" title="${Hamount}" sortable="false" headerClass="sortable"/>
		<display:column property="note" title="${HNote}" sortable="false" headerClass="sortable" maxLength="10"/>
		<display:column property="description" title="${HDesc}" sortable="false" headerClass="sortable" maxLength="10"/>
    </display:table>
    </br></br>
    <table align="center">
		<tr>
		</tr><s:set name="UniqueExpensesId" value="empExpenses.hcmoExpensesId"></s:set>
		<tr>
		<td class="nowrap">
		<s:url var="approveAndSubmitToAccount" action="approveAndSubmitToAccount">
               <s:param name="empExpenses.hcmoExpensesId" value="#UniqueExpensesId"/>
           </s:url> 
           <div class="button-comments">
	   		    <div class="button-left"></div>
	   		    	<sj:submit href="%{approveAndSubmitToAccount}" cssClass="button-midle" targets="expenseAprovalEmpViewDivId"  indicator="indicatorExpAppAndSubmitTabs" key="button.label.expAppAndToAccountant"></sj:submit>
	   		    <div class="button-right"></div>
   		    </div>
   		</td>
   		<td class="nowrap">
   		<img id="indicatorExpRejectTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		<s:url var="enterRejectedNotes" action="enterRejectedNotes">
               <s:param name="empExpenses.hcmoExpensesId" value="#UniqueExpensesId"/>
           </s:url> 
				 <sj:submit href="%{enterRejectedNotes}" targets="expenseAprovalEmpViewDivId" cssClass="submitbutton117" indicator="indicatorExpRejectTabs" key="button.label.reject.submit"></sj:submit>
   		</td>
   		<td class="nowrap">
   		<img id="indicatorExpReviewTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			<s:url var="reviewEmployeeExpense" action="reviewEmployeeExpense">
               <s:param name="empExpenses.hcmoExpensesId" value="#UniqueExpensesId"/>
           </s:url> 
                <sj:submit href="%{reviewEmployeeExpense}" targets="expenseAprovalEmpViewDivId" cssClass="submitbutton117" indicator="indicatorExpReviewTabs" key="button.label.review"></sj:submit>
		</td>
		<td class="nowrap">
		    <img id="indicatorForBackButtonExpApprovalPageTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			<s:url var="getAllExpensesForApproval" action="getAllExpensesForApproval">
				<s:param name="empExpenses.hcmoExpensesId" value="#UniqueExpensesId"/>
           </s:url> 
                <sj:submit href="%{getAllExpensesForApproval}" cssClass="submitbutton117" targets="expenseAprovalEmpViewDivId" indicator="indicatorForBackButtonExpApprovalPageTabs" key="button.label.ExpBackButton"></sj:submit>
		</td>
		<tr>
   		 <!-- <td class="nowrap">
   		<s:url var="approveAndSubmitToNextLevel" action="approveAndSubmitToNextLevel">
   			<s:param name="empExpenses.hcmoExpensesId" value="#UniqueExpensesId"></s:param>
  		</s:url>
                <s:a href="%{approveAndSubmitToNextLevel}">Approve And Submit To Next Level</s:a>
   		</td>-->
   		<img id="indicatorExpNextLevelTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	 	<s:if test="#session.EXPENSES_NEXT_LEVEL_APPROVER=='APPROVER'">

   		<td colspan="4">
   		<table align="center">
   		<tr><td>
			<s:url var="nextLevelApprover" action="nextLevelApprover">
               <s:param name="empExpenses.hcmoExpensesId" value="#UniqueExpensesId"/>
            </s:url> 
           <div class="button-comments">
	   		    <div class="button-left"></div>
	   		    	 <sj:submit href="%{nextLevelApprover}" targets="expenseAprovalEmpViewDivId" indicator="indicatorExpNextLevelTabs" cssClass="button-midle" key="button.label.ExpNextLevel"></sj:submit>
	   		    <div class="button-right"></div>
   		    </div>
   		    </td>
   		    </tr>
   		    </table>
		</td>
	   </tr>
	   </s:if>
   	   </table>
   		
   		
</div>