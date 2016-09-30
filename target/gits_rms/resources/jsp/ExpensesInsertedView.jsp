<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="BackButtonForExpenseAttachmentDiv">
<div id="myReviewDivExpInsertViewId_div">
<jsp:include page="common/messages.jsp" flush="true"/>
<div class="informationMessageSingle"><li><span><s:text name="label.title.expenses.info"/></span></li></div>	
<table align=center class="borderAll">
    <tr>
        <th><s:text name="label.header.expenseforapproval.empName"/></th>
        <th><s:text name="label.header.expenseforapproval.requestDate"/></th>
        <th><s:text name="label.header.expenseforapproval.totalAmount"/> <s:text name="currencyTypeValue"/></th>
        
    </tr>
        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
        	<td class="nowrap"><s:property value="empExpenses.hcmoEmployeeId.empFirstName"/></td>
            <td class="nowrap"><s:date name="empExpenses.createdDate" format="%{getText('label.date.simpleDateFormat')}"/></td>
          	<td class="nowrap"><s:text name="currencyTypeValue"/> <s:property value="empExpenses.totalAmount"/></td>
         <br/>
         <br/>
         <br/>
    </tr>
    <tr>
		<td colspan="3">
	 	<s:form name="ExpensesAttachForm" action="doExpensesAttachments.action" method="POST" enctype="multipart/form-data" onsubmit="return AIM.submit(this, {'onStart' : startExpensesCallback, 'onComplete' : completeExpensesCallback})">
			<table align="center">
				<tr>
					<td colspan="3">
						<s:text name="label.title.expensesattach.msg"/>
					</td>	
				</tr>
				
				<tr>
					<td colspan="3">
						<s:hidden name="expAttach.hcmoEmployeeId.employeeId" value="%{empExpenses.hcmoEmployeeId.employeeId}" id="expAttach.hcmoEmployeeId.employeeId"></s:hidden>
						<s:hidden name="expAttach.hcmoExpensesId.hcmoExpensesId" value="%{empExpenses.hcmoExpensesId}" id="expAttach.hcmoExpensesId.hcmoExpensesId"></s:hidden>
					    <s:file label="File (1)" name="upload" />
					</td>
	        	 </tr>
	        	 <tr align="left" style="color: black;">
	        	    <td colspan="2"><s:text name="label.form.fields.benefit.fileFormat"/>
        			<s:text name="label.form.fields.benefit.fileTypeToAttachForExpense"/></td>
	        	 </tr>
            		<tr align="center">
            				<img id="indicatorExpAttachTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					<td>
               				<sj:submit key="button.label.submitToFileUpload" cssClass="submitbutton117" targets="MultipleExpensesUploadDiv" indicator="indicatorExpAttachTabs"/>
							<s:reset key="button.label.reset" cssClass="submitbutton117"/>
				   		    <s:url var="setUpInsertOrUpdateEmpExpenses" action="setUpInsertOrUpdateEmpExpenses"></s:url>
				   		    <sj:submit href="%{setUpInsertOrUpdateEmpExpenses}" targets="BackButtonForExpenseAttachmentDiv" indicator="indicatorExpAttachTabs" cssClass="submitbutton117" key="button.label.ExpBackButton"></sj:submit>
					</td>
					</tr>
			</table>
			</s:form>
			</tr> 
	</table>
</div>
<div id="MultipleExpensesUploadDiv">
</div>
 </div>