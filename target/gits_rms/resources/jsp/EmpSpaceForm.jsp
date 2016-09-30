<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EmployeeSpaceFormId_div">
			<br/>
	<img id="indicatorSubMenuEmployeeSpaceFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<s:form action="empSpaceupload" method="POST" enctype="multipart/form-data" onsubmit="return AIM.submit(this, {'onStart' : startCallbackForEmpSpace, 'onComplete' : completeCallbackForEmpSpace})"> 
	<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	            <div class="informationMessageSingle">
					<li><span><s:text name="label.title.empSpace.folderSize"/><s:property value="#session.EMPLOYEE_SPACE_MAXIMUM_SIZE"/> <s:text name="label.title.empSpace.allotedSpace"/></span></li>
				</div>
	                <tr>
	                  <td class="formtitle">
					 		<s:if test="empSpace==null || empSpace.hcmoEmpSpaceId == null">
							 <s:text name="label.title.empSpace.add"/>
						   </s:if>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">

		 <tr>
         	 <td class="inputtext"><s:text name="label.form.fields.empSpace.title"/></td>
         	 <td class="employeeinputtd"><s:textfield name="empSpace.sharedFileTitle" cssClass="employeeinput"/></td>
         	 <td class="inputerrormessage">
	         	 	<s:fielderror fieldName="empSpace.sharedFileTitle" />
         	 </td>
         </tr>
         <tr>
         	<td class="inputtext"><s:text name="label.form.fields.empSpace.description"/></td>
         	<!-- textarea size has been changed : venkat -->
         	<td class="employeeinputtd"><s:textarea name="empSpace.sharedFileDesc" cssClass="employeetextarea" rows="4" cols="26"/></td>
      		<td class="inputerrormessage">
	         	 	<s:fielderror fieldName="empSpace.sharedFileDesc" />
         	</td>
         </tr>
         <tr>
            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
             <td class="employeeinputtd">
				<s:select 
					headerKey=""
					list="#request.APPL_EMPLOYEE_LIST"
					listKey="employeeId"
					listValue="empFirstName"
				    name="empSpace.empIdObjList.employeeId"
				    cssClass="employeeselect"
				    multiple="true"
				    size="3"
				    />
			</td>
         </tr>
    	 <tr>
	    	<td class="employeeinputtd"><s:file label="File (1)" name="upload" /></td>
	     </tr>
	     <tr>
			<td class="employeeinputtd"><s:file label="File (2)" name="upload" /></td>
		 </tr>
		 <tr>
			<td class="employeeinputtd"><s:file label="File (3)" name="upload" /></td>
		 </tr>
		 <tr>
			<td class="employeeinputtd"><s:file label="File (4)" name="upload" /></td>
		 </tr>
		 <tr>
			<td class="employeeinputtd"><s:file label="File (5)" name="upload" /></td>
		 </tr>
		 </table></td></tr></table></td></tr></table></td></tr></table>
		
		 <table align="center"> 
    	     <tr>
    		    <td>
    		    	<indicatorEmpSpaceFormId_div id="indicatorEmpSpaceFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	 		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeeSpaceFormId_div" indicator="indicatorEmpSpaceFormId_div"/>
	 		    </td>
    		    <td>
    		    	<s:submit action="resetEmpSpaceForm" key="button.label.reset" cssClass="submitbutton117" targets="submenu_EmployeeSpaceFormId_div" indicator="indicatorSubMenuEmployeeSpaceFormId_div"/>
				</td>
    		 </tr>
	    </table>
   		
</s:form>
</div>