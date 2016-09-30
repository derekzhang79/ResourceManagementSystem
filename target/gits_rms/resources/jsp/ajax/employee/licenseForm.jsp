<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EssLicense_Form_div">
<div class="submenu_bg">
<s:set name="UniqueLicEmployeeId" value="license.empIdObj.employeeId"></s:set>
	<s:url var="remoteLicForm" value="/setUpEmpLicense.action">
		<s:param name="license.empIdObj.employeeId" value="#UniqueLicEmployeeId"></s:param>
	</s:url>
	<s:set name="UniqueLicEmployeeId" value="license.empIdObj.employeeId"></s:set>
	<s:url var="remoteLicView" value="/getEmployeeAllLicense.action">
		<s:param name="license.empIdObj.employeeId" value="#UniqueLicEmployeeId"></s:param>
	</s:url>
	
		<s:if test="#session.LICENSE_ADD==true">
			<sj:a href="%{remoteLicForm}" indicator="indicatorLicList" targets="submenu_EssLicense_Form_div" cssClass="link"><s:text name="label.header.license.add"/></sj:a> |
		</s:if>
		<s:if test="#session.LICENSE_VIEW==true">
			<sj:a href="%{remoteLicView}" indicator="indicatorLicList" targets="submenu_EssLicense_Form_div" cssClass="link"><s:text name="label.header.license.view"/></sj:a>
		</s:if>
	</div>		
<br />
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

    <s:form action="insertOrUpdateLicenseAjax">
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
                  </td>
                </tr>
     			<tr>
                <td class="forminner"><table class="tablealign">
           <tr><td class="inputtext"><s:text name="label.form.fields.license.licNumber"/></td>
         	        <td class="employeeinputtd"><s:textfield name="license.licenseNumber" cssClass="employeeinput"/></td>
         	        <td class="inputerrormessage">
						<s:fielderror fieldName="license.licenseNumber" />
			 		</td>
         </tr>
         <!--Button image added by, R.Deepika-->
         <!-- Date Format Aligned and Removed an extra text by R.Deepika -->
         <tr><td class="inputtext"><s:text name="label.form.fields.license.licenseDate"/></td>
         	 <td class="employeeinputtd">
       	        <sj:datepicker name="license.licenseDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
       	        
         	 </td>
         	 <td class="inputerrormessage">
					<s:fielderror fieldName="license.licenseDate" />
			 </td>
         </tr>
         <tr><td class="inputtext"><s:text name="label.form.fields.license.renewalDate"/></td>
	       	 <td class="employeeinputtd">
       	        <sj:datepicker name="license.licenseRenewalDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
       	        
         	 </td>
         	 <td class="inputerrormessage">
					<s:fielderror fieldName="license.licenseRenewalDate" />
			 </td>
         </tr>
         <tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr> 
         <tr><td class="inputtext"><s:text name="label.form.fields.license.description"/></td>
					<!-- rows and cols size has changed:venkat -->
         	        <td><s:textarea name="license.licenseDesc" cssClass="employeetextarea" rows="4" cols="26" /></td>
         	        <td class="inputerrormessage">
						<s:fielderror fieldName="license.licenseDesc" />
		 			</td>
         </tr>
    </table></td></tr></table></td></tr></table></td></tr></table>
		 <s:hidden name="license.empIdObj.employeeId"/>
         <s:hidden name="license.empLicenseId"/>
            
  
    		 <br/>
    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorLicForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EssLicense_Form_div" indicator="indicatorLicForm"/>
    		    </td>
    	        <td><s:if test="license==null || license.empLicenseId == null">
		    	        	<s:url var="resetLicenseFormAjax" action="setUpEmpLicense">
		    	        		<s:param name="license.empIdObj.employeeId" value="license.empIdObj.employeeId"></s:param></s:url>
		    	            <sj:submit href="%{resetLicenseFormAjax}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EssLicense_Form_div" indicator="indicatorLicForm"/>
		    	    </s:if>
		    	    <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	    </s:else>
		    	</td>
    		 </tr>
    </table> 		  		 
</s:form>
</div>