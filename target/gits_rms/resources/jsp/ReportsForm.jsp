<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_Reports_div">

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	    <s:form action="generateReports" id="reports_form_id">
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
							 <s:text name="label.title.reports.form"/>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	         			<!--Button image added by, R.Deepika-->
	         			<tr>
	         				<td class="inputtext"><s:text name="label.form.fields.reports.fromDate"/></td>
				         	<td class="employeeinputtd">
				       	       <sj:datepicker name="report.fromDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
				       	       <s:text name="label.date.format"/>
				         	</td>
				         	<td class="inputerrormessage">
								<s:fielderror fieldName="report.fromDate" />
							</td>
				        </tr>
				        <tr>
	         				<td class="inputtext"><s:text name="label.form.fields.reports.toDate"/></td>
				         	<td class="employeeinputtd">
				       	       <sj:datepicker name="report.toDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
				       	       <s:text name="label.date.format"/>
				         	</td>
				         	<td class="inputerrormessage">
								<s:fielderror fieldName="report.toDate" />
							</td>
				        </tr>
				        <tr>
				            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
				             <td class="employeeinputtd">
								<s:select 
									headerKey=""
									headerValue="-- Please Select --"
									list="#request.APPL_EMPLOYEE_LIST"
									listKey="employeeId"
									listValue="empFirstName"
								    name="report.empObj.employeeId"
								    cssClass="employeeselect"
								    />
							</td>
							<td class="inputerrormessage">
									<s:fielderror fieldName="report.empObj.employeeId" />
							 </td>
				        </tr>
				        <tr>
			  				<td class="inputtext"><s:text name="Leave module"/></td>
			         		<td class="employeeinputtd">
			         			<s:checkbox name="report.leaveModule" label="leaveModule" cssClass="employeecheckbox"></s:checkbox>
			         		</td>
			         	</tr>
			         	<!--<tr>
			  				<td class="inputtext"><s:text name="ESS"/></td>
			         		<td class="employeeinputtd">
			         			<s:checkbox name="report.ESS" label="ESS" cssClass="employeecheckbox"></s:checkbox>
			         		</td>
			         	</tr>-->        
	        
				</table></td></tr></table></td></tr></table></td></tr></table>	    
	   		 <br/>

	        <s:url var="getAllReports" action="getAllReports"/>
	        <s:url var="generateReports" action="generateReports"/>
			<img id="indicatorReportsForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   	        
		    <table align="center"> 
	    	     <tr>
	    	        <td>
						<s:reset key="button.label.reset" cssClass="resetbutton117"/>&nbsp;
	    	        </td>
	    		    <td>
			  	        <s:submit key="button.label.generatereports" cssClass="resetbutton117"/>&nbsp;
	    		    </td>
	    	        <td>
			        	<sj:submit formId="reports_form_id" targets="preview_reports_div" href="%{#getAllReports}" key="button.label.preview" cssClass="resetbutton117" indicator="indicatorReportsForm"/>
	    	        </td>
	    		 </tr>
	    	</table>
    	</s:form>

	<div id="preview_reports_div">
	</div>

</div>	    	