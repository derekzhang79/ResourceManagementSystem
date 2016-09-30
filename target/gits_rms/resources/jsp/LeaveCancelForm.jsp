<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<div id="submenu_LeaveCancel_AppSearchForm_div">
<s:form action="viewCancelList">
<table class="maintable">
      <tr>
        <td align="center" ><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="formtitle">
						<s:text name="label.title.lrapp.cancelLeave"/>
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
       		
	</table></td></tr></table></td></tr></table></td></tr></table>
	<table align="center">
		<tr>
		   	<td class="nowrap">
		   		<img id="indicatorAllCancelLeaveReqsAssignListImgForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		     <sj:submit cssClass="submitbutton117" indicator="indicatorAllCancelLeaveReqsAssignListImgForm" targets="submenu_LeaveCancel_AppSearchForm_div"></sj:submit>
			</td>
        </tr>  		
	</table>

	
	 <tr></tr>
       		 <tr></tr>
       		 
		<br/>
	</s:form><br><br>
	</div>