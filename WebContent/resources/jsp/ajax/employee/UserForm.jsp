<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EssUser_Form_div">
<div class="submenu_bg">
		<s:set name="UniqueUserEmployeeId" value="user.empIdObj.employeeId"></s:set>
		<s:url var="remoteUserForm" value="/setUpEmpUser.action">
			<s:param name="user.empIdObj.employeeId" value="#UniqueUserEmployeeId"></s:param>
		</s:url>
		<s:set name="UniqueUserEmployeeId" value="user.empIdObj.employeeId"></s:set>
		<s:url var="remoteUserView" value="/getEmployeeAllUser.action">
			<s:param name="user.empIdObj.employeeId" value="#UniqueUserEmployeeId"></s:param>
		</s:url>
		 <s:if test="#session.USER_ADD==true">
		    <sj:a href="%{remoteUserForm}" indicator="indicatorUserList" targets="submenu_EssUser_Form_div" cssClass="link"><s:text name="label.header.user.add"/></sj:a> |
		</s:if>
		<s:if test="#session.USER_VIEW==true">
			<sj:a href="%{remoteUserView}" indicator="indicatorUserList" targets="submenu_EssUser_Form_div" cssClass="link"><s:text name="label.header.user.view"/></sj:a>
		</s:if>
	</div>		
<br />		
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

<s:form action="insertOrUpdateUserAjax">
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
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.user.name" /></td>
			<td class="employeeinputtd"><s:textfield name="user.userName" cssClass="employeeinput"/></td>
			<td class="inputerrormessage">
					<s:fielderror fieldName="user.userName"/>
			</td>
		</tr>
		<tr>
			 <td class="inputtext"><s:text name="label.form.fields.user.password"/></td>
             <td class="employeeinputtd"><s:password name="user.password" showPassword="true" cssClass="employeeinput"/></td>
             <td class="inputerrormessage">
					<s:fielderror fieldName="user.password"/>
			</td> 
		</tr>
	</table></td></tr></table></td></tr></table></td></tr></table>
	<br />
	<table align="center">
		<tr>
			<s:hidden name="user.empIdObj.employeeId"></s:hidden>
			<s:hidden name="user.hcmouserId"/>
			<td>	
				<img id="indicatorUserForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    <sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EssUser_Form_div" indicator="indicatorUserForm"/>
			</td>
			<td> <s:if test="user==null || user.hcmouserId == null">
		    	        	<s:url var="resetUserFormAjax" action="setUpEmpUser">
		    	        		<s:param name="user.empIdObj.employeeId" value="user.empIdObj.employeeId"></s:param></s:url>
		    	            <sj:submit href="%{resetUserFormAjax}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EssUser_Form_div" indicator="indicatorUserForm"/>
		    	    </s:if>
		    	    <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	    </s:else>
		    	</td>
		</tr>
		
	</table>
	
</s:form>
</div>