<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TsProjectAssignedView_div">
<jsp:include page="common/messages.jsp" flush="true"/>
<s:form action="getEmployeeDataLink">
	<table class="maintable">
      <tr>
        <td align="center"><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="formtitle">
						<s:text name="label.header.timeSheet.view" />
                  </td>
                </tr>
                <tr>
                  <td class="forminner"><table class="tablealign" align="center">
				<tr align="center">
        		   <td class="inputtext"><s:text name="label.header.common.empName"/></td>
         		   <td class="employeeinputtd">
         		   		<sj:autocompleter 
         		   			name="tsProjAssigned.employeeName.employeeId"
          					list="#request.APPL_SUBEMPLOYEE_LIST" 
               				listKey="employeeId" 
              				listValue="empFullName"
              				cssClass="employeeselect"
              				selectBox="true"
							selectBoxIcon="true"
     					  />
          		  </td>
          		  <td class="inputerrormessage"></td>
       		 </tr>
		</table></td></tr>
	
				
		</table></td></tr></table></td></tr></table>
	
	<table align="center">
		<tr>
		   	<td class="nowrap">
			   	<img id="indicatorTimeSheetView" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				 
		     	<sj:submit key="button.label.submit" targets="submenu_TsProjectAssignedView_div" cssClass="submitbutton117" indicator="indicatorTimeSheetView"></sj:submit>
		    </td>
        </tr>  		
	</table>
	
	<br/><br><br>
</s:form><br><br>
<table>
	<th>Employee Name</th>
	
	<s:iterator value="pendingEmpList">
	<tr>
	<td><s:property/></td>
	</tr>
	</s:iterator>
	
	</table>
</div>