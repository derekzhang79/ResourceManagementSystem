<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TimesheetNotesForm_div">
<br/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateTimesheetNotes">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
                <td class="formtitle">
        		   <s:if test="notesObj==null || notesObj.hcmoTimesheetNotesId == null">
					 <s:text name="label.title.timesheetNotes.add"/>
				   </s:if>
				   <s:else>
					 <s:text name="label.title.timesheetNotes.edit"/>
				   </s:else>
                </td>
	         </tr>
	     	 <tr>
               <td class="forminner"><table class="tablealign"> 
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.timesheetNotes.notes"/></td>
	         	<!-- textarea size has been changed : venkat -->
	         	<td class="employeeinputtd"><s:textarea name="notesObj.notes" cssClass="employeetextarea" rows="4" cols="26"/></td>
       	        <td class="inputerrormessage"><s:fielderror fieldName="notesObj.notes" /></td>
	         </tr>
	         	<s:set name="notesObj.date" value="notesDate"></s:set>
	         	<s:set name="notesObj.empIdObj.employeeId" value="#session.EmployeeId"></s:set>
	            <s:hidden name="notesObj.hcmoTimesheetNotesId"/>
	            <s:hidden name="notesObj.empIdObj.employeeId"/>
	            <s:hidden name="notesObj.date"/>
	            <s:hidden name="notesDate"/>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
		    <table align="center"> 
	   	    	<tr>
		    		<td>
						<img id="indicatorTimesheetNotesForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
		    		    <sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_TimesheetNotesForm_div" indicator="indicatorTimesheetNotesForm"/>
	    		    </td>
	   	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
		    </table>
   	</s:form>
</div> 