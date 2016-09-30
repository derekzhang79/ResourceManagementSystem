<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_LicenseSearchId_div">
	<div class="submenu_bg">
		<s:if test="#session.LICENSE_ADD == true">
			<sj:a href="setUpLicense.action" targets="submenu_LicenseSearchId_div" indicator="indicatorSubMenuLicenseSearchId_div" cssClass="link"><s:text name="MTIAddLicense" /></sj:a> |
		</s:if>
		<s:if test="#session.LICENSE_VIEW == true">
			<sj:a href="getAllLicense.action" targets="submenu_LicenseSearchId_div" indicator="indicatorSubMenuLicenseSearchId_div" cssClass="link"><s:text name="MTIViewLicense"/></sj:a> |
			<sj:a href="licenseSearchForm.action" targets="submenu_LicenseSearchId_div" indicator="indicatorSubMenuLicenseSearchId_div" cssClass="link"><s:text name="MTISearchLicense"/></sj:a>
		</s:if>
	</div>
	<br/>
	<img id="indicatorSubMenuLicenseSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="licenseSearchResult">
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
							 <s:text name="label.title.license.search"/>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	     	 
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.common.empName"/></td>
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
	         </tr>
	         <!-- text added by, R.Deepika -->
	         <tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.license.licNumber" /></td>
       	        <td class="employeeinputtd"><s:textfield name="license.licenseNumber" cssClass="employeeinput"/></td>
	         </tr>
	         <!--Button image added by, R.Deepika-->
	          <!--  Extra Date Format Text Removed by, R.Deepika-->
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.license.licenseDate" /></td>
       	        <td class="employeeinputtd"><sj:datepicker name="license.licenseDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
       	        	<br/>
         	 	</td>
         	 	<td class="employeeinputtd">
					<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="license.licenseDateValue"
					    dataType="json"
						indicator="licenseSearchFormLicenseDate"      
					    cssClass="employeeselect" 
					/>
					<img id="licenseSearchFormLicenseDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
	         </tr>
	         <tr>
				<td></td>
				<td class="employeeinputtd"><sj:datepicker name="license.licenseEndDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        <br/>
	       	    </td>
      	        <td><s:text name="label.common.search.dateInformation"/></td>
			</tr>
	        <tr>
	         	<td class="inputtext"><s:text name="label.header.license.renewalDate" /></td>
       	        <td class="employeeinputtd"><sj:datepicker name="license.licenseRenewalDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
       	        	<br/>
         	 	</td>
         	 	<td class="employeeinputtd">
					<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="license.licenseRenewalDateValue"
					    dataType="json"
						indicator="licenseSearchFormRenewalDate"      
					    cssClass="employeeselect" 
					/>
					<img id="licenseSearchFormRenewalDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
	         </tr>
	         <tr>
				<td></td>
				<td class="employeeinputtd"><sj:datepicker name="license.licenseRenewalEndDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        <br/><s:text name="label.date.format"/></td>
       	        <td><s:text name="label.common.search.dateInformation"/></td>
			 </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.common.description" /></td>
       	        <!-- textarea size has been changed : venkat -->
       	        <td class="employeeinputtd"><s:textarea name="license.licenseDesc" cssClass="employeetextarea" rows="4" cols="26" /></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorLicenseSearchForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_LicenseSearchId_div" indicator="indicatorLicenseSearchForm"/>
    		   	</td>
    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
    		 </tr>
	    </table> 		  		 
	    </s:form>
	</div>