
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
 	
<div id="submenu_EmployeeScheduleFormId_div">

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<s:if test="!errText.equals('')">
	<ul class="actionMessageSingle">
            <li>
                <span><s:property value="errText"/></span>
            </li>    
        </ul>
	
	
	</s:if>
	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<s:form action="insertOrUpdateEmpSchedule">
			<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						<s:text name="label.title.employeeSchedule.add" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
		<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">		   
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.common.empName" /></td>
					 <td class="employeeinputtd">
					 	<sj:autocompleter  
						    name="employeeId"
							list="#request.APPL_EMPLOYEE_LIST"
							listKey="employeeId"
							listValue="empFullName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
			    		/>
					</td>
					<td class="inputerrormessage"></td>
				</tr>
				
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.scheduleType" /></td>
					 <td class="employeeinputtd">
					 	<sj:autocompleter  
						    name="scheduleType"
							list="scheduleTypeList"
							selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
			    		/>
					</td>
					<td class="inputerrormessage"></td>
				</tr>
				
		</s:if>
		<s:elseif test="(#session.ROLE != 'Admin' || #session.ROLE != 'admin' || #session.ROLE != 'ADMIN') && (#session.LEAVE_APPROVER=='BOTH' || #session.TIMESHEET_APPROVER=='BOTH' )">
			
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.scheduleType" /></td>
					 <td class="employeeinputtd">
					 	<s:select
					 		headerKey=""
							headerValue="-- Please Select --"  
						    name="scheduleType"
							list="scheduleTypeList"
							cssClass="employeeselect"
						    onchange="getsubEmployee(this)"
			    		/>
			    		
			    		
			    		
					</td>
					<td class="inputerrormessage"></td>
				</tr>
			
			<tr>
					<td class="inputtext"><s:text name="label.form.fields.common.empName" /></td>
					 <td class="employeeinputtd">
					 	
			    		
			    		<div id="resultSubEmp">
	     	 			<s:select  
		   					 headerKey=""
							headerValue="-- Please Select --"
							list="emptylist"
							cssClass="employeeselect"
						/>
	     	 	
	     	 	   	 	</div>
			    		
					</td>
					<td class="inputerrormessage"></td>
				</tr>
			
				
		</s:elseif>	
		<s:else>
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.scheduleType" /></td>
					 <td class="employeeinputtd">
					 	<sj:autocompleter  
						    name="scheduleType"
							list="scheduleTypeList"
							selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
			    		/>
					</td>
					<td class="inputerrormessage"></td>
				</tr>
		
		</s:else>	
				

			
			<tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>
			<!--Button image added by, R.Deepika-->
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.startDate" /></td>
				<td class="employeeinputtd"><sj:datepicker name="startDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage"></td>
			</tr>
			<!-- Extra text Removed by, R.Deepika -->
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.enddate" /></td>
				<td class="employeeinputtd"><sj:datepicker name="endDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage"></td>
			</tr>
			<tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
			</tr>
			
		 </table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorEmpScheduleForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeeScheduleFormId_div" indicator="indicatorEmpScheduleForm"/>
				</td>
				<td>
					
	    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
	    	       
	    	    </td>
			</tr>
		</table>
	</s:form>
</div>
 <script type="text/javascript">
    function getsubEmployee(scheduleType)
    { 
        var url = 'getSchedSubEmployees.action';
        jQuery.ajax({
             type: "POST",
             dataType: "html",
             url: url,
             data: "scheduleType="+scheduleType.value,
             success: function(data){       
             jQuery("#resultSubEmp").html(data);
             }      
           });
     
    }

    function sample(scheduleType){
    }
    
    </script>
 