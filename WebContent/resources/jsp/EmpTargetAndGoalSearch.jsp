<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_Emp_TargetGoalSearchFormId_div">
	<img id="indicatorSubMenuEmpTargetandGoalSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

<s:form action="getAssignedTargetSearchList">
	<table class="maintable">
    	<tr>
        <td align="center"><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="forminner"><table class="tablealign">	 
				<tr>
           			 <td class="inputtext"><s:text name="label.header.common.empName"/></td>
            		 <td class="employeeinputtd">
            		 <s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
            		 	<sj:autocompleter  
						    name="employeeId"
							list="#request.APPL_EMPLOYEE_LIST"
							listKey="employeeId"
							listValue="empFullName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
			    		/>
            		 </s:if>
            		 <s:else>
            		 	<sj:autocompleter 
            		 		name="employeeId"
        					list="#request.empsList" 
     						listKey="employeeId" 
       					   	listValue="empFullName"
       					   	selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
              			  />
              		</s:else>
           			 </td>
           			 <td class="inputerrormessage"></td>
       		 </tr>
	</table></td></tr></table></td></tr></table></td></tr></table>
	<table align="center">
		<tr>
		   	<td class="nowrap">
		   	<img id="indicatorEmpTargetandGoalSearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		     <sj:submit cssClass="submitbutton117" indicator="indicatorEmpTargetandGoalSearchFormId_div" targets="submenu_Emp_TargetGoalSearchFormId_div"></sj:submit>
			</td>
        </tr>  		
	</table>
</s:form>
</div>