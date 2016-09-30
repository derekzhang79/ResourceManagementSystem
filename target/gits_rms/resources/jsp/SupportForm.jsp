<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>

<div id="supportFormId_div">
	<br/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<s:form action="supportUpload" method="POST" enctype="multipart/form-data" onsubmit="return AIM.submit(this, {'onStart' : startCallbackForEmpSpace, 'onComplete' : completeCallbackForEmpSpace})"> 
	<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	            <tr>
	               <td class="formtitle">
					 <s:text name="label.title.supportForm.name"/>
			       </td>
			    </tr>
	            <!--<div class="informationMessageSingle">
					<li><span><s:text name="label.title.empSpace.folderSize"/><s:property value="#session.EMPLOYEE_SPACE_MAXIMUM_SIZE"/> <s:text name="label.title.empSpace.allotedSpace"/></span></li>
				</div> -->
          <tr>
                 <td class="forminner"><table class="tablealign">
          <tr>
                 <td class="formtitle1" colspan="3">
   	 		      <s:text name="label.common.message.generalInformation"/>
    	 	     </td>
    	 </tr>
         <tr>
         	<td class="inputtext" align="left"><s:text name="label.header.support.from"/></td>
         	<td class="employeedisplaytd" align="left"><s:property value="fromEmployee"/></td>
         </tr>
         <tr>
         	<td class="inputtext" align="left"><s:text name="label.header.support.client"/></td>
         	<td class="employeedisplaytd" align="left"><s:property value="client"/></td>
         </tr>
         <tr>
         	<td class="inputtext" align="left"><s:text name="label.header.employee.firstName"/></td>
         	<td class="employeedisplaytd" align="left"><s:property value="empFirstName"/></td>
         </tr>
         <tr>
         	<td class="inputtext" align="left"><s:text name="label.header.employee.lastName"/></td>
         	<td class="employeedisplaytd" align="left"><s:property value="empLastName"/></td>
         </tr>
         <tr>
         	<td class="inputtext" align="left"><s:text name="label.header.support.client"/></td>
         	<td class="employeedisplaytd" align="left"><s:property value="client"/></td>
         </tr>
         <tr>
         	<td class="inputtext" align="left"><s:text name="label.header.jobTitle.name"/></td>
         	<td class="employeedisplaytd" align="left"><s:property value="empJobTitle"/></td>
         </tr>
         <tr>
         	<td class="inputtext" align="left"><s:text name="label.form.fields.support.priority"/></td>
         	<td class="employeeinputtd" align="left">
				<s:select 
				name="supportObj.priority"
         		headerKey=""
         		headerValue="-- Please Select --"
				list="#{'General Support':'General Support','Technical Issue':'Technical Issue','Urgent-Outage':'Urgent-Outage'}" 
				cssClass="employeeselect" 
			/>
			</td>
      		<td class="inputerrormessage">
	         	 	<s:fielderror fieldName="supportObj.priority" />
         	</td>
         </tr>
         <tr>
            <td class="formtitle1" colspan="3">
   	 		<s:text name="label.support.message.details"/>
    	 	</td>
         </tr>
         <tr>
         	<td class="inputtext" align="left"><s:text name="label.form.fields.support.subject"/></td>
         	<td class="employeeinputtd" align="left"><s:textfield name="supportObj.supportMailSubject" cssClass="employeeinput" cssStyle="width: 500px;"/></td>
      		<td class="inputerrormessage">
	         	 	<s:fielderror fieldName="supportObj.supportMailSubject" />
         	</td>
         </tr>
         <tr>
         	<td class="inputtext" align="left"><s:text name="label.form.fields.support.message"/></td>
         	<s:set id="contextPath"  value="#request.get('javax.servlet.forward.context_path')" />
         	<td class="employeeinputtd" align="left">
         		<sjr:ckeditor
         			id="richtextBrodastMessageCustomeEditor" 
					name="supportObj.supportMailMessage" 
					rows="10" 
					cols="80" 
					width="500" 
					uploads="true"
					toolbar="MyToolbar"
					customConfig="%{contextPath}/resources/js/ckeditor/ckeditor.config.js">
         		</sjr:ckeditor></td>
      		<td class="inputerrormessage">
	         	 	<s:fielderror fieldName="supportObj.supportMailMessage" />
         	</td>
         </tr>
         <tr>
            <td class="formtitle1" colspan="3">
   	 		<s:text name="label.support.uploadFiles.details"/>
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
		 </table></td></tr></table></td></tr></table></td></tr></table>
		
		 <table align="center"> 
    	     <tr>
    		    <td>
    		       <img id="indicatorForSupportFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
    		       <sj:submit key="button.label.submit" cssClass="submitbutton117" targets="supportFormId_div" indicator="indicatorForSupportFormId_div"/>
    		    </td>
    		    <td>
    		        <s:url var="resetSupportForm" action="resetSupportForm"></s:url>
		    	    <sj:submit href="%{resetSupportForm}" key="button.label.reset" cssClass="submitbutton117" targets="supportFormId_div" indicator="indicatorForSupportFormId_div"/>
		    	</td>
    		 </tr>
	    </table>
</s:form>
</div>