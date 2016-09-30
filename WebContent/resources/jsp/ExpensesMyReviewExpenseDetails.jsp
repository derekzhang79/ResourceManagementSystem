<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:div id="ExpenseDetailDiv">

<s:form>
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
	</s:iterator>
	
	</div>
	</s:else>
</table></br></br>
<div class="informationMessageSingle"><li><span><s:text name="label.title.expensesDetailView"/></span></li></div>
<img id="indicatorSubmitForApprovalReviewExpense" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
<table align=center class="borderAll">
		<tr>
           	<td nowrap="nowrap"><s:text name="label.header.expdetails.totalAmount"/><s:text name="curTypeValForExpenseAmtField"/></td>
				<td><s:textfield id="empExpenses.totalAmount" name="empExpenses.totalAmount" size="10" readonly="true"></s:textfield></td>
				<s:set name="UniqueTotAmount" value="empExpenses.totalAmount"></s:set>
		</tr>
	

    <tr>
        <th><s:text name="label.header.expenseforapproval.expenseDate"/></th>
        <th><s:text name="label.header.expenseforapproval.projectName"/></th>
        <th><s:text name="label.header.expenseforapproval.expenseType"/></th>
        <th><s:text name="label.header.expenseforapproval.amount"/> <s:text name="currencyTypeValue"/></th>
        <th><s:text name="label.header.expenseforapproval.note"/></th>
        <th><s:text name="label.header.expenseforapproval.description"/></th>
        <th><s:text name="label.common.link.edit"/></th>
    </tr>
     <s:iterator value="empExpenseDetailList" status="status">
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
        	<td class="nowrap"><s:date name="expensesDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
            <td class="nowrap"><s:property value="projectId.projectName"/></td>
            <td class="nowrap"><s:property value="hcmoExpensesTypeId.name"/></td>
            <td class="nowrap"><s:text name="currencyTypeValue"/> <s:property value="amount"/></td>
            <td><div style='width: 120px; overflow: hidden;'><s:property value="note" /></div></td>
            <td><div style='width: 120px; overflow: hidden;'><s:property value="description"/></div></td>
            
            <td class="nowrap">
				<s:url var="editExpensesDetails" action="editExpensesDetails" escapeAmp="false">
          	     	<s:param name="expenseDetails.hcmoExpensesId.hcmoExpensesId" value="hcmoExpensesId.hcmoExpensesId"/>
          	     	<s:param name="expenseDetails.hcmoExpensesDetailsId" value="hcmoExpensesDetailsId"/>
           		</s:url> 
				<sj:submit href="%{editExpensesDetails}" targets="ExpenseDetailDiv" indicator="indicatorSubmitForApprovalReviewExpense" key="label.common.link.edit" cssClass="submitbutton117"></sj:submit>
				<s:set name="UniqueExpensesId" value="hcmoExpensesId.hcmoExpensesId"></s:set>
   			</td>
   			<!--<s:textfield id="expenseDetails.hcmoExpensesId.hcmoExpensesId" name="hcmoExpensesId.hcmoExpensesId"></s:textfield>
   			  <td class="nowrap">
				<s:url var="updateEditedExpenses" action="updateEditedExpenses">
          	     	<s:param name="expenseDetails.hcmoExpensesId.hcmoExpensesId" value="hcmoExpensesId.hcmoExpensesId"/>
           		</s:url> 
				<sj:a href="%{updateEditedExpenses}" targets="myReviewDiv">Submit</sj:a>
   			</td>-->
        </tr>  		
     </s:iterator>
     
</table>
<table align="center">
	<tr>
		<td class="nowrap">
			<s:url var="updateEditedExpenses" action="updateEditedExpenses" escapeAmp="false">
				<s:param name="expenseDetails.hcmoExpensesId.hcmoExpensesId" value="#UniqueExpensesId"></s:param>
				<s:param name="total" value="#UniqueTotAmount"></s:param>
			</s:url>
			<div class="button-comments">
				<div class="button-left"></div>
					<sj:submit href="%{updateEditedExpenses}" targets="ExpenseDetailDiv" indicator="indicatorSubmitForApprovalReviewExpense" cssClass="button-midle" key="button.label.submitForApproval"></sj:submit>
				<div class="button-right"></div>
	    	</div>
		</td>
		<td>
		    <img id="indicatorBackBuutonForApprovalReviewExpense" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			<s:url var="myExpensesReview" action="myExpensesReview"></s:url>
			<sj:submit href="%{myExpensesReview}" targets="ExpenseDetailDiv" indicator="indicatorBackBuutonForApprovalReviewExpense" cssClass="submitbutton117" key="button.label.ExpBackButton"></sj:submit>
		</td>
	</tr>
</table>
</s:form>
</s:div>