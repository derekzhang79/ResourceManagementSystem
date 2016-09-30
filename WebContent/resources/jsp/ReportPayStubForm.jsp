<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="report_PayStub_div">
<%-- <div class="submenu_bg">
		<sj:a href="showBirthdayReports.action" targets="report_PayStub_div" indicator="indicatorGeneratePayStubReport" cssClass="link"><s:text name="MTIGenerateBirthdayReport" /></sj:a>
</div> --%>
<img id="indicatorGeneratePayStubReport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="reportsPayStubAction" id="reports_paystubForm_id">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.paystub.report" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	                <s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
					        <tr>
					            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
					             <td class="employeeinputtd">
									<s:select 
										headerKey=""
										list="#request.APPL_EMPLOYEE_LIST"
										listKey="employeeId"
										listValue="empFirstName"
									    name="report.empIdObjList.employeeId"
									    cssClass="employeeselect"
								    />
								</td>
								<td></td>
					        </tr>
					        <!--Button image added by, R.Deepika-->
					        <tr>
								<td class="inputtext"><s:text name="label.common.message.date.mandatory"/></td>
				         		<td class="employeeinputtd">
					       	        <sj:datepicker id="date" name="report.date" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
				         		</td>
				         		<td class="inputerrormessage"><s:fielderror fieldName="employee.empBirthDate" /></td>
				        	</tr>
				        	<!-- Format text Added by, R.Deepika -->
				        	<tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr> 
				       </s:if>
		</table></td></tr></table></td></tr></table></td></tr></table><br />
		<table align="center">
			<tr>
	   		    <td>
		   		    <div class="button-comments">
		   		    	<div class="button-left"></div>
		   		    		<s:submit key="button.label.generatereports" cssClass="button-midle"/>
		   		    	<div class="button-right"></div>
		   		    </div>
	   		    </td>
				<%-- <td>
					<img id="indicatorEmployeePayStubForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					<s:url var="BirthdayReportsPreview" action="getBirthdayReportsPreview"/>				    
	   		    	<sj:submit formIds="reports_paystubForm_id" key="button.label.preview" href="%{#BirthdayReportsPreview}" cssClass="submitbutton117" targets="report_PayStub_div" indicator="indicatorEmployeePayStubForm"/>
				</td> --%>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
		</table>
	</s:form>
</div>

<script type="text/javascript">
	$('#reports_paystubForm_id').submit(function(){
		var date = $('#date').val();
		if((date ==  null)||(date ==  "")){
			alert("Date is a required field");
			return false;
		}

		return true;

	});
</script>