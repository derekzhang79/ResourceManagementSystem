<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_LeaveTypeFormId_div">
<div class="submenu_bg">
	<s:if test="#session.LEAVETYPE_ADD == true">
		<sj:a href="setUpLeaveType.action" targets="submenu_LeaveTypeFormId_div" indicator="indicatorSubMenuLeaveTypeFormId_div" cssClass="link"><s:text name="MTIAddLeaveType" /></sj:a> |
	</s:if>
	<s:if test="#session.LEAVETYPE_VIEW == true">
		<sj:a href="getAllLeaveType.action" targets="submenu_LeaveTypeFormId_div" indicator="indicatorSubMenuLeaveTypeFormId_div" cssClass="link"><s:text name="MTIViewLeaveType"/></sj:a> |
		<sj:a href="leaveTypeSearchForm.action" targets="submenu_LeaveTypeFormId_div" indicator="indicatorSubMenuLeaveTypeFormId_div" cssClass="link"><s:text name="MTISearchLeaveType"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuLeaveTypeFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateLeaveType">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
	                  
	                  		<s:if test="leave==null || leave.leaveTypeId == null">
									<s:text name="label.title.leaveType.add" />
								</s:if>
								<s:else>
									<s:text name="label.title.leaveType.edit" />
								</s:else>
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
				<tr>
				
				<td class="inputtext">
					<s:text	name="label.form.fields.leaveType.name" />
				</td>
				<td class="employeeinputtd">
					<s:textfield name="leave.leaveTypeName" cssClass="employeeinput" />
				</td>
				<td class="inputerrormessage">
	                   <s:fielderror fieldName="leave.leaveTypeName" />
	             </td>
				
			</tr>
		</table>
		</td>
		</tr>
		</table>
		</td>
		</tr>
		</table>
		</td>
		</tr>
		</table>
		
		
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorLeaveTypeFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_LeaveTypeFormId_div" indicator="indicatorLeaveTypeFormId_div"/>
				</td>
				<s:if test="leave==null || leave.leaveTypeId==null">
				        <td>
		    		    	<s:url var="resetLeaveType" action="resetLeaveType"></s:url>
		    	            <sj:submit href="%{resetLeaveType}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_LeaveTypeFormId_div" indicator="indicatorLeaveTypeFormId_div"/>
			           </td>
			    </s:if>
			    <s:else>
				       <td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
				</s:else>
			</tr>
			
		</table>
		<s:hidden name="leave.leaveTypeId"/>
	</s:form>
</div>