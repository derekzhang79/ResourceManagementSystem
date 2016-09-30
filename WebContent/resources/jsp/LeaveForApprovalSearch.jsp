<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<div id="submenu_LeaveForApproval_SearchForm_App_div">
<jsp:include page="common/messages.jsp" flush="true"/>
<s:form action="getAllSubEmpLeaveReqsApproval">
<table class="maintable">
      <tr>
        <td align="center" ><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="formtitle">
						<s:text name="label.title.lrapp.forApproval" />
                  </td>
                </tr>
                <tr>
                  <td class="forminner"><table class="tablealign">	 
				<tr>
           			 <td class="inputtext"><s:text name="label.header.common.empName"/></td>
            		 <td class="employeeinputtd">
            		 	<s:select 
            		 		name="lrapp.empIdObj.employeeId"
        					list="#request.empsList" 
     						listKey="employeeId" 
       					   	listValue="empFullName"
       					   	cssClass="employeeselect"
              			  />
           			 </td>
           			 <td class="inputerrormessage"></td>
       		 </tr>
       		 <tr>
        		    <s:text name="label.header.leaveapprover.EmployeeName" var="HEmployeeName"></s:text>
         		    <display:table class="tableborder" id="leaveReqsApprovalListId" name="lrappList" requestURI="getAllLeaveForApprovalTab.action" defaultorder="ascending">
         		     	<display:column property="empIdObj.empFirstName" title="${HEmployeeName}" sortable="false" headerClass="sortable"/>
         		    </display:table>
       		 </tr>
       		
	</table></td></tr></table></td></tr></table></td></tr></table>
	<table align="center">
		<tr>
		   	<td class="nowrap">
		   		<img id="indicatorAllSubEmpLeaveReqsApprovalImgForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		     <sj:submit cssClass="submitbutton117" indicator="indicatorAllSubEmpLeaveReqsApprovalImgForm" targets="submenu_LeaveForApproval_SearchForm_App_div"></sj:submit>
			</td>
        </tr>  		
	</table>

		 <tr></tr>
       		 <tr></tr>
       		 
		<br/>
	</s:form><br><br>
	</div>