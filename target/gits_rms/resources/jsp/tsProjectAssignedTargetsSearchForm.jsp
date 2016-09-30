<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<div id="submenu_Emp_TargetGoal_div">
	
		<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		<jsp:include page="common/messages.jsp" flush="true"/>
		
		<s:form action="getAllEmpTargetAndGoal">
			<table class="maintable">
			      <tr>
			        <td align="center" ><table class="formouter">
			          <tr>
			            <td><table class="employeeformiinertable">
			                <tr>
			                  <td class="forminner"><table class="tablealign">	 
							<tr>
			           			 <td class="inputtext"><s:text name="label.header.common.empName"/></td>
			            		 <td class="employeeinputtd">
			            		 	<sj:autocompleter 
			            		 		name="hcmoApprovingEmpId.employeeId"
			        					list="#request.timesheetsubemplist" 
			     						listKey="hcmoApprovingEmpId.employeeId" 
			       					   	listValue="hcmoApprovingEmpId.empFullName"
			       					   	selectBox="true"
									    selectBoxIcon="true"
									    cssClass="employeeselect"
			              			  />
			           			 </td>
			       		 </tr>
			</table></td></tr></table></td></tr></table></td></tr></table>
			<table align="center">
				<tr>
				   	<td class="nowrap">
				   	<img id="indicatorEmpTGSearchForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				     <sj:submit cssClass="submitbutton117" indicator="indicatorEmpTGSearchForm" targets="submenu_Emp_TargetGoal_div"></sj:submit>
					</td>
		        </tr>  		
			</table>
		</s:form>
	</div>