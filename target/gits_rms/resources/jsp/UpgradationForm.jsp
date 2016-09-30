<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>

<div id="upgrade_div">
<s:form action="upgradationMail"> 
	<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	            <tr>
	               <td class="formtitle">
					 <s:text name="label.title.upgradeForm"/>
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
		            <td class="formtitle1" colspan="3">
		   	 		<s:text name="label.support.message.details"/>
		    	 	</td>
		         </tr>
		         <tr>
		         	<td class="inputtext" align="left"><s:text name="label.form.fields.support.subject"/></td>
		         	<td class="employeedisplaytd" align="left"><s:property value="subject"/></td>
		         </tr>
		         <tr>
		         	<td class="inputtext" align="left"><s:text name="label.form.fields.support.message"/></td>
		         	<s:set id="contextPath"  value="#request.get('javax.servlet.forward.context_path')" />
		         	<td class="employeeinputtd" align="left">
		         		<sjr:ckeditor
		         			id="richtextBrodastMessageCustomeEditor" 
							name="suppObject.upgradeMessage" 
							rows="10" 
							cols="80" 
							width="500" 
							uploads="true"
							toolbar="MyToolbar"
							customConfig="%{contextPath}/resources/js/ckeditor/ckeditor.config.js">
		         		</sjr:ckeditor></td>
		      		<td class="inputerrormessage">
			         	 	<s:fielderror fieldName="suppObject.upgradeMessage" />
		         	</td>
		         </tr>
		         </table></td></tr></table></td></tr></table></td></tr></table>
		
		 <table align="center"> 
    	     <tr>
    		    <td>
    		       <img id="indicatorForSupport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
    		       <sj:submit key="button.label.submit" cssClass="submitbutton117" targets="upgrade_div" indicator="indicatorForSupport"/>
    		    </td>
    		    <td>
    		        <s:url var="resetSupportForm" action="resetSupportForm"></s:url>
		    	    <sj:submit href="%{resetSupportForm}" key="button.label.reset" cssClass="submitbutton117" targets="upgrade_div" indicator="indicatorForSupport"/>
		    	</td>
    		 </tr>
	    </table>
</s:form>
</div>