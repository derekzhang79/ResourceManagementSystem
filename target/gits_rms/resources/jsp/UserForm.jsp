<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_UserFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.USER_ADD == true">
			<sj:a href="setUpUser.action" targets="submenu_UserFormId_div" indicator="indicatorSubMenuUserFormId_div" cssClass="link"><s:text name="MTIAddUser" /></sj:a> |
		</s:if>
		
		<s:if test="#session.USER_VIEW == true">
			<sj:a href="getAllUser.action" targets="submenu_UserFormId_div" indicator="indicatorSubMenuUserFormId_div" cssClass="link"><s:text name="MTIViewUser"/></sj:a> |
			<sj:a href="userSearchForm.action" targets="submenu_UserFormId_div" indicator="indicatorSubMenuUserFormId_div" cssClass="link"><s:text name="MTISearchUser"/></sj:a>
		</s:if>
	</div>
	<br/>
	<img id="indicatorSubMenuUserFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>

<jsp:include page="common/messages.jsp" flush="true"/>
<s:form action="insertOrUpdateUser">
<table class="maintable">
         <tr>
        	<td align="center" ><table class="formouter">
         <tr>
            <td><table class="employeeformiinertable">
         <tr>
                <td class="formtitle">
        		      <s:if test="user==null || user.hcmouserId == null">
							<s:text name="label.title.user.add" />
					  </s:if> <s:else>
							<s:text name="label.title.user.edit" />
					  </s:else>
                </td>
         </tr>
     	 <tr>
            <td class="forminner"><table class="tablealign"> 
        	<s:if test="user==null || user.hcmouserId == null">
				<tr>
		            <td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
		            <td class="employeeinputtd">
		            	<sj:autocompleter  
						    name="user.empIdObj.employeeId"
							list="#request.APPL_EMPLOYEE_LIST"
							listKey="employeeId"
							listValue="empFullName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
				    	/>
					</td>
					<td class="inputerrormessage"><s:fielderror fieldName="user.empIdObj.employeeId"/></td>
				</tr>
				<tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr>
			</s:if>
	        <s:else>
	        	<tr>
					<td class="inputtext"><s:text name="label.header.common.empName"></s:text></td>
					<td class="employeedisplaytd"><s:textfield name="user.empIdObj.empFirstName" readonly="true" cssClass="employeeinput"/></td>
					<s:hidden name="user.empIdObj.employeeId"></s:hidden>
				</tr>
			</s:else>
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.user.name" /></td>
			<td class="employeeinputtd"><s:textfield name="user.userName" cssClass="employeeinput"/></td>
			<td class="inputerrormessage"><s:fielderror fieldName="user.userName"/></td>
		</tr>
		<tr>
			 <td class="inputtext"><s:text name="label.form.fields.user.password"/></td>
             <td class="employeeinputtd"><s:password name="user.password" showPassword="true" cssClass="employeeinput"/></td>
             <td class="inputerrormessage"><s:fielderror fieldName="user.password"/></td> 
		</tr>
	</table></td></tr></table></td></tr></table></td></tr></table>
	<br/>
	<table align="center">
		<tr>
			<s:hidden name="user.hcmouserId"/>	
			<td>
				<img id="indicatorUserFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_UserFormId_div" indicator="indicatorUserFormId_div"/>
			</td>
			<td>
			<s:if test="user==null || user.hcmouserId==null">
		    	        	<s:url var="resetUserForm" action="resetUserForm"></s:url>
		    	            <sj:submit href="%{resetUserForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_UserFormId_div" indicator="indicatorUserFormId_div"/>
		    	        </s:if>
		    	        <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	        </s:else></td>
		</tr>
	</table>
	
</s:form>
</div>