<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_TimesheetCategoryEmp_div">
	<div class="submenu_bg">
			<sj:a href="setUpInsertOrUpdateTimesheetCategoryEmp.action" targets="submenu_TimesheetCategoryEmp_div" indicator="indicatorSubMenuTimesheetCategoryEmpSearchId_div" cssClass="link"><s:text name="MTAddTimeSheetCategoryEmp" /></sj:a> |
			<sj:a href="getAllTimeSheetCategoryEmp.action" targets="submenu_TimesheetCategoryEmp_div" indicator="indicatorSubMenuTimesheetCategoryEmpSearchId_div" cssClass="link"><s:text name="MTViewTimeSheetCategoryEmp"/></sj:a> |
			<sj:a href="TimesheetCategorySearchEmp.action" targets="submenu_TimesheetCategoryEmp_div" indicator="indicatorSubMenuTimesheetCategoryEmpSearchId_div" cssClass="link"><s:text name="MTSearchTimeSheetCategoryEmp"/></sj:a>
		
	</div>
		<br/>
		<img id="indicatorSubMenuTimesheetCategoryEmpSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="timesheetCategoryEmpSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.TimesheetCategoryEmp.search"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	         <tr>
				<td class="inputtext"><s:text name="label.form.fields.TimesheetCategoryEmp.Empname" /></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
					    name="timesheetCategoryEmp.employeeName.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="timesheetCategoryEmp.employeeName.employeeId" /> </td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.TimesheetCategoryEmp.TimecategorynameList" /></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
					    name="timesheetCategoryEmp.timesheetCategoryName.hcmoTimesheetCategoryId"
						list="#request.TIMESHEET_CATEGORY"
						listKey="hcmoTimesheetCategoryId"
						listValue="name"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="timesheetCategoryEmp.timesheetCategoryName.hcmoTimesheetCategoryId" /> </td>
			</tr>
			
					<!-- autocomplete text added by, R.Deepika-->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorSubMenuTimeSheetCategorySearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_TimesheetCategoryEmp_div" indicator="indicatorSubMenuTimeSheetCategorySearchId_div"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>