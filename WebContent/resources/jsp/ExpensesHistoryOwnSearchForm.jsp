<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div class="textContent">
<div id = "HistoryMainTab">
<jsp:include page="common/messages.jsp" flush="true"/>
    <s:form name="expHistoryForm" action="getExpOwnHistorySearchResult">

     <table class="maintable" align="center">
      <tr>
        <td align="center" ><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="formtitle">
				 		<s:text name="label.title.expenseshistory.search"/>
				  </td>
                </tr>
                <!--Button image added by, R.Deepika-->
                <tr>
                  <td class="forminner">
        <table align="center"><!-- Extra text Removed by, R.Deepika -->
        <tr><td class="inputtext"><s:text name="label.form.fields.common.startdate1"/></td>
  	         <td class="employeeinputtd"><sj:datepicker id="expHist.startDate" name="expHist.startDate"
				showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput" maxDate="2"/></td>
         <td class="inputerrormessage"><td>
         </tr>
 
         <tr><td class="inputtext"><s:text name="label.form.fields.common.enddate1"/></td>
  	         <td class="employeeinputtd"><sj:datepicker id="expHist.endDate" name="expHist.endDate"
				showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput" maxDate="2"/><s:text name="label.date.format"/></td>
         </tr>
    </table></td></tr></table></td></tr></table></td></tr></table>
    		 <br/>
    <table align="center"> 
    	     <tr align="center">
    	     	<td class="nowrap">
    	     	     <img id="indicatorExpenseOwnEmpSearchFormTabs" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	                 <sj:submit cssClass="submitbutton117" targets="HistoryMainTab" indicator="indicatorExpenseOwnEmpSearchFormTabs" value="Submit"></sj:submit>
				</td>
    	        <td><s:reset cssClass="resetbutton117" key="button.label.reset"></s:reset></td>
    		 </tr>
    </table> 		  		 
    	</s:form>

</div>
<br/>
	<div id="tabHistResult">
	</div>
	</div>