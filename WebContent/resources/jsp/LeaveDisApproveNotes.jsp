<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="submenu_LeaveDisApproveNotes_App_div">
<s:form action="leaveRequestdisapproved">
<table class="maintable">
      <tr>
        <td align="center" ><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="formtitle">
						<s:text name="label.title.lrapp.disapproveNotes" />
                  </td>
                </tr>
                <tr>
                  <td class="forminner"><table class="tablealign">	 
				<tr>
           			 <td class="inputtext"><s:text name="label.form.fields.common.notes"/></td>
            		<!-- textarea size has been changed : venkat -->
            		 <td class="employeeinputtd"><s:textarea name="lrapp.disApproveNotes" cssClass="employeetextarea" rows="4" cols="26" /></td>
           			 <td class="inputerrormessage"></td>
       		 </tr>
       		 <s:set name="lrapp.empIdObj.employeeId" value="notesEmployeeId"/>
       		 <s:hidden name="lrapp.hcmoLeaveReqsApprovalId"></s:hidden>
       		
	</table></td></tr></table></td></tr></table></td></tr></table>
	<table align="center">
		<tr>
		   	<td class="nowrap">
		   		<img id="indicatorAllSubEmpLeaveReqsDisApprovalImgForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		     <sj:submit cssClass="submitbutton117" indicator="indicatorAllSubEmpLeaveReqsDisApprovalImgForm" targets="submenu_LeaveDisApproveNotes_App_div"></sj:submit>
		     
		     <s:url var="backLeaveForApprovalDisApproveListButton" action="backLeaveForApprovalDisApproveListButton" escapeAmp="false">
		     	<s:param name="lrapp.empIdObj.employeeId" value="lrapp.empIdObj.employeeId"/>
		     	<s:param name="lrapp.hcmoLeaveReqsApprovalId" value="lrapp.hcmoLeaveReqsApprovalId"/>
		     </s:url>
	   		 <sj:submit href="%{backLeaveForApprovalDisApproveListButton}"  key="Back" cssClass="submitbutton117" targets="submenu_LeaveDisApproveNotes_App_div" indicator="indicatorAllSubEmpLeaveReqsDisApprovalImgForm"/>
			</td>
        </tr>  		
	</table>

	
	 <tr></tr>
       		 <tr></tr>
       		 
		<br/>
	</s:form><br><br>
	</div>