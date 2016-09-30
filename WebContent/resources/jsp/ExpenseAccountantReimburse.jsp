<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@page import="com.gits.rms.vo.ExpensesTypeVO"%>
<%@page import="com.gits.rms.vo.ProjectVO"%>
<%@page import="com.gits.rms.vo.EmployeeExpensesVO"%>

<div id="ExpensesReibursementId">
<jsp:include page="common/messages.jsp" flush="true"/>
 <s:form action="insertReimburse">
 <div class="informationMessageSingle"><li><span><s:text name="label.title.expensesReimbursementView"/></span></li></div>
 <table class="maintable">
      <tr>
        <td align="center" ><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="formtitle">
				  </td>
                </tr>
                  <td class="forminner">
                  <table class="tablealign" align="center">
                  <tr>
					<td class="formtitle1" align="center"><s:text name="select.common.expensedetails.value"/>
 				  </td>
 				   </tr>
				</table>
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
		<display:column property="curTypeValueForSalaryField" title="${Hamount}" sortable="false" headerClass="sortable"/>
		<display:column property="note" title="${HNote}" sortable="false" headerClass="sortable" maxLength="10"/>
		<display:column property="description" title="${HDesc}" sortable="false" headerClass="sortable" maxLength="10"/>
		
   </display:table>
<br/>
<table class="tablealign" align="center">
<tr>
	<td  class="formtitle1" align="center"><s:text name="select.common.expensehistory.value"/>
 	</td>
</tr>
</table>
        
        <s:text name="label.header.empexpenses.approvername" var="HAppName"/>
        <s:text name="label.header.empexpenses.rajectedname" var="HRejName"/>
        <s:text name="label.header.empexpenses.rejectednotes" var="HRejNotes"/>
        <s:text name="label.header.empexpenses.reviewedname" var="HRevName"/>
        <s:text name="label.header.empexpenses.nextlevelname" var="HNxtlvlName"/>
        <s:text name="label.header.empexpenses.accountantname" var="HAcctName"/>
        <s:text name="label.header.empexpenses.status" var="HStatus"/>
        
  
    <display:table id="expenseStatusTrackerListId" name="expenseStatusTrackerList" sort="list" defaultsort="1" defaultorder="ascending" export="false">
    
            <display:column property="approverId.empFirstName" title="${HAppName}" sortable="false" headerClass="sortable"/>
            <display:column property="rejectedId.empFirstName" title="${HRejName}" sortable="false" headerClass="sortable"/>
            <display:column property="rejectedNotes" title="${HRejNotes}" sortable="false" headerClass="sortable"/>
            <display:column property="reviewedId.empFirstName" title="${HRevName}" sortable="false" headerClass="sortable"/>
            <display:column property="nextLevelId.empFirstName" title="${HNxtlvlName}" sortable="false" headerClass="sortable"/>
            <display:column property="accountantId.empFirstName" title="${HAcctName}" sortable="false" headerClass="sortable"/>
            <display:column property="approvalStatus" title="${HStatus}" sortable="false" headerClass="sortable"/>
            
     </display:table>
    
</table>
<br/>
<table align="center">
	<tr>
        <td class="inputtext"><s:text name="label.form.fields.empexpenses.reimbursementAmount"/> <s:property value="#session.CURRENCY_TYPE_VALUE"/> <s:text name="label.form.common.mandatory"/></td>
        <td class="employeeinputtd"><s:textfield name="empExpenses.reimbursementAmount" cssClass="employeeinput"></s:textfield></td>
       <td class="inputerrormessage"><s:fielderror fieldName="empExpenses.reimbursementAmount" /> </td>
    </tr>
    <!--Button image added by, R.Deepika-->
    <tr>
        <td class="inputtext"><s:text name="label.form.fields.empexpenses.reimbursementDate"/></td>
        <td class="employeeinputtd">
       	        <sj:datepicker name="empExpenses.reimbursementDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput" maxDate="2"/><br>
       	         <s:text name="label.date.format"/>
         	 </td>
        <td class="inputerrormessage"><s:fielderror fieldName="empExpenses.reimbursementDate" /> </td>
    </tr>
    <tr>
        <td class="inputtext"><s:text name="label.form.fields.empexpenses.accountingNotes"/></td>
        <td><s:textarea name="empExpenses.accountingNotes" cssClass="employeetextarea" rows="4" cols="26"></s:textarea></td>
    </tr>
    </table></td></tr></table></td></tr></table></td></tr></table>
    <table align="center"> 
    		<s:set name="empExpenses.hcmoExpensesId" value="empExpenses.hcmoExpensesId"></s:set>
    		<s:hidden name="empExpenses.hcmoExpensesId"/>
    	     <tr>
    	        <td class="nowrap">
    	        <img id="indicatorExpReimbursementTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				 <sj:submit targets="ExpensesReibursementId" cssClass="submitbutton117" indicator="indicatorExpReimbursementTabs" key="select.common.expensestatus.reimburse.value"></sj:submit>
   				</td>
   				<td>
			      <s:reset cssClass="submitbutton117" key="button.label.reset"></s:reset> 
   				</td>
   				<td>
			     <img id="indicatorExpReimbursementBackTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			     <s:url var="getAccountantTab" action="getAccountantTab">
                 </s:url> 
				 <sj:submit href="%{getAccountantTab}" targets="ExpensesReibursementId" cssClass="submitbutton117" indicator="indicatorExpReimbursementBackTabs" key="button.label.ExpBackButton"></sj:submit>
   				</td>
    		 </tr>
    </table> 	
</s:form>
</div>