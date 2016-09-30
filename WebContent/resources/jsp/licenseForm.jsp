<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_LicenseFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.LICENSE_ADD == true">
			<sj:a href="setUpLicense.action" targets="submenu_LicenseFormId_div" indicator="indicatorSubMenuLicenseFormId_div" cssClass="link"><s:text name="MTIAddLicense" /></sj:a> |
		</s:if>
		<s:if test="#session.LICENSE_VIEW == true">
			<sj:a href="getAllLicense.action" targets="submenu_LicenseFormId_div" indicator="indicatorSubMenuLicenseFormId_div" cssClass="link"><s:text name="MTIViewLicense"/></sj:a> |
			<sj:a href="licenseSearchForm.action" targets="submenu_LicenseFormId_div" indicator="indicatorSubMenuLicenseFormId_div" cssClass="link"><s:text name="MTISearchLicense"/></sj:a>
		</s:if>
	</div>
	<br/>
		<img id="indicatorSubMenuLicenseFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>

<jsp:include page="common/messages.jsp" flush="true"/>
    <s:form action="insertOrUpdateLicense">
     <table class="maintable">
      <tr>
        <td align="center" ><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                <td class="formtitle">
        		   	<s:if test="license==null || license.empLicenseId == null">
					 	<s:text name="label.title.license.add"/>
				  	</s:if>
				   	<s:else>
					 	<s:text name="label.title.license.edit"/>
				   	</s:else>
                </td></tr><tr>
        	<td class="forminner"><table class="tablealign">
		
		<s:if test="license==null || license.empLicenseId == null">
			<tr>
            	<td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
	            <td class="employeeinputtd">
	            	<sj:autocompleter  
					    name="license.empIdObj.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="license.empIdObj.employeeId" /></td>
	         </tr>   
	         <tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			 </tr>
		</s:if>
		<s:else>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName"></s:text></td>
				<td class="employeedisplaytd"><s:textfield name="license.empIdObj.empFirstName" readonly="true" cssClass="employeeinput"/></td>
				<s:hidden name="license.empIdObj.employeeId"></s:hidden>
			</tr>
		</s:else>
        <tr>
         	<td class="inputtext"><s:text name="label.form.fields.license.licNumber"/></td>
    	    <td class="employeeinputtd"><s:textfield name="license.licenseNumber" cssClass="employeeinput"/></td>
         	<td class="inputerrormessage"><s:fielderror fieldName="license.licenseNumber" /></td>
         </tr>
         <!--Button image added by, R.Deepika-->
         <tr>
         	<td class="inputtext"><s:text name="label.form.fields.license.licenseDate"/></td>
       	 	<td class="employeeinputtd"><sj:datepicker name="license.licenseDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
         	<td class="inputerrormessage"><s:fielderror fieldName="license.licenseDate" /></td>
         </tr>
         <!-- extra date format removed by, R.Deepika -->
          	<tr>
        	<td class="inputtext"><s:text name="label.form.fields.license.renewalDate"/></td>
       	 	<td class="employeeinputtd"><sj:datepicker name="license.licenseRenewalDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
       	 	<td class="inputerrormessage"><s:fielderror fieldName="license.licenseRenewalDate" /></td>
         </tr>
         <tr>
			<td></td>
			<td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
		 </tr>
         <tr>
         	<td class="inputtext"><s:text name="label.form.fields.license.description"/></td>
       	    <!-- textarea size has been changed : venkat -->
       	    <td class="employeeinputtd"><s:textarea name="license.licenseDesc" cssClass="employeetextarea" rows="4" cols="26"/></td>
     	    <td class="inputerrormessage"><s:fielderror fieldName="license.licenseDesc" /></td>
         </tr>
    </table></td></tr></table></td></tr></table></td></tr></table>
    		 <br/>
    <table align="center"> 
   	     <tr>
   		    <td>
				<img id="indicatorLicenseForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_LicenseFormId_div" indicator="indicatorLicenseForm"/>
   		    </td>
   	        <td>
   	        	<s:if test="license==null || license.empLicenseId==null">
    	        	<s:url var="resetLicenseForm" action="resetLicenseForm"></s:url>
    	            <sj:submit href="%{resetLicenseForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_LicenseFormId_div" indicator="indicatorLicenseForm"/>
    	        </s:if>
    	        <s:else>
    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
    	        </s:else>
    	 	</td>
   		 </tr>
    </table> 	
    	<s:hidden name="license.empLicenseId"/>	 	  		 
    	</s:form>
</div>		