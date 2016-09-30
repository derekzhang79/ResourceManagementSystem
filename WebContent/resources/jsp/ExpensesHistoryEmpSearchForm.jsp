<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div class="textContent">
<div id = "HistoryMainEmpTab">

<jsp:include page="common/messages.jsp" flush="true"/>
    <s:form name="expHistoryEmpForm" action="getExpEmployeeHistorySearchResult">
     <table class="maintable">
      <tr>
        <td align="center" ><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="formtitle">
				 		<s:text name="label.title.expenseshistory.search"/>
				  </td>
                </tr>
                <tr>
                  <td class="forminner"><table class="tablealign">
     	 
		 <tr>
            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
             <td class="employeeinputtd">
             <s:if test="#session.EXPENSES_APPROVER=='BOTH' && #session.EXPENSES_ACCOUNTANT=='ACCOUNTANT'">
                    <sj:autocompleter  
							    name="empExpense.hcmoEmployeeId.employeeId"
								list="#request.APPL_EMPLOYEE_LIST"
								listKey="employeeId"
								listValue="empFullName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					/>
             </s:if>
             <s:elseif test="#session.EXPENSES_APPROVER=='BOTH'">
             		<sj:autocompleter  
							    name="empExpense.hcmoEmployeeId.employeeId"
								list="#session.SESSION_EXPENSESEMPLOYEE_LIST"
								listKey="hcmoEmployeeId.employeeId"
								listValue="hcmoEmployeeId.empFullName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					/>
			</s:elseif>
			<s:else>
			        <sj:autocompleter  
							    name="empExpense.hcmoEmployeeId.employeeId"
								list="#request.APPL_EMPLOYEE_LIST"
								listKey="employeeId"
								listValue="empFullName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					/>
			</s:else>
			</td>
			<td class="inputerrormessage"></td>
        </tr> 
        <tr>
		<td></td>
		<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
		</tr>
		<!--Button image added by, R.Deepika-->
		 <!-- Extra Text format Removed and aligned by R.Deepika -->
        <tr><td class="inputtext"><s:text name="label.form.fields.common.startdate1"/></td>
  	         <td class="employeeinputtd"><sj:datepicker id="expHist.startDate" name="expHist.startDate"
				showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput" maxDate="2"/></td>
		   </tr>
        
         <tr><td class="inputtext"><s:text name="label.form.fields.common.enddate1"/></td>
  	         <td class="employeeinputtd"><sj:datepicker id="expHist.endDate" name="expHist.endDate"
				showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput" maxDate="2"/></td>
         </tr>
         <tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr> 
    </table></td></tr></table></td></tr></table></td></tr></table>
    		 <br/>
    <table align="center"> 
    	     <tr align="center">
    	     	<td class="nowrap">
    	     	    <img id="indicatorEmpExpenseSearchFormSubmitTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	                <sj:submit cssClass="submitbutton117" targets="HistoryMainEmpTab" indicator="indicatorEmpExpenseSearchFormSubmitTabs" value="Submit"></sj:submit>
				</td>
				<td><s:reset cssClass="resetbutton117" key="button.label.reset"></s:reset></td>
    		 </tr>
    </table> 		  		 
    	</s:form>
</div>
<br/>
	<div id="tabHistEmpResult">
	</div>
	</div>