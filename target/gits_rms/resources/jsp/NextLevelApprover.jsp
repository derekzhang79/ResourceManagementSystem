<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<jsp:include page="common/messages.jsp" flush="true"/>
<div id="expenseNextLevelDivId">
    <s:form action="approveAndSubmitToNextLevel">
    <table class="maintable">
      <tr>
        <td align="center" ><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="formtitle">
				 		<s:text name="label.title.expensesnextlevel.approver"/>
				  </td>
                </tr>
                <tr>
                  <td class="forminner"><table class="tablealign">
     
			  <tr><td class="inputtext"><s:text name="label.form.fields.expenseapprover.nextlevelapprover"/></td>
			  
			  <td class="employeeinputtd">
				<s:select 
					headerKey="0"
					headerValue="-- Please Select --"
					list="#session.SESSION_EXPENSESAPPROVER_LIST"
					listKey="approvingEmployeeId.employeeId"
					listValue="approvingEmployeeId.empFirstName"
				    name="expenseStatusStracker.nextLevelId.employeeId"
				    cssClass="employeeselect" 
				    />
			</td>
			<td class="inputerrormessage"><s:fielderror fieldName="expenseStatusStracker.nextLevelId.employeeId" /> </td>	
         </tr> 
    </table></td></tr></table></td></tr></table></td></tr></table>
    		 <br/>
    		 <s:hidden id="empExpenses.hcmoExpensesId" name="empExpenses.hcmoExpensesId"></s:hidden>
    <table align="center"> 
    		<s:set name="UniqueExpensesId" value="empExpenses.hcmoExpensesId"></s:set>
    	     <tr>
    	        <td class="nowrap">
    	         <img id="indicatorExpenseNextLevelTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		         <s:url var="approveAndSubmitToNextLevel" action="approveAndSubmitToNextLevel">
                   <s:param name="empExpenses.hcmoExpensesId" value="#UniqueExpensesId"/>
                </s:url> 
				<sj:submit targets="expenseNextLevelDivId" indicator="indicatorExpenseNextLevelTabs" cssClass="submitbutton117"></sj:submit>
   		    </td>
   		    <td>
   		         <s:reset cssClass="submitbutton117" key="button.label.reset"></s:reset> 
   		    </td>
   		    <td>
			     <img id="indicatorExpNextLevelBackTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
			     <s:url var="getExpensesForApprovalEmployee" action="getExpensesForApprovalEmployee">
			     <s:param name="empExpenses.hcmoExpensesId" value="#UniqueExpensesId"/>
                 </s:url> 
				 <sj:submit href="%{getExpensesForApprovalEmployee}" targets="expenseNextLevelDivId" cssClass="submitbutton117" indicator="indicatorExpNextLevelBackTabs" key="button.label.ExpBackButton"></sj:submit>
 			</td>
    		</tr>
    </table> 		  		 
    	</s:form>
    	</div>