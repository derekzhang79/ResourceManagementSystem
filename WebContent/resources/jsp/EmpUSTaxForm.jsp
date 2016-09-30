
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EmployeeUSTaxFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.EMPLOYEEUSTAX_ADD == true">
			<sj:a href="setUpEmpUSTax.action" targets="submenu_EmployeeUSTaxFormId_div" indicator="indicatorSubMenuEmployeeUSTaxFormId_div" cssClass="link"><s:text name="MTIAddEmployeeUSTax" /></sj:a> |
		</s:if>
		<s:if test="#session.EMPLOYEEUSTAX_VIEW == true">
			<sj:a href="getAllEmpUSTax.action" targets="submenu_EmployeeUSTaxFormId_div" indicator="indicatorSubMenuEmployeeUSTaxFormId_div" cssClass="link"><s:text name="MTIViewEmployeeUSTax"/></sj:a> |
			<sj:a href="usTaxSearchForm.action" targets="submenu_EmployeeUSTaxFormId_div" indicator="indicatorSubMenuEmployeeUSTaxFormId_div" cssClass="link"><s:text name="MTISearchEmployeeUSTax"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeeUSTaxFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateEmpUSTax">
	    <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
					 		<s:if test="empUSTax==null || empUSTax.hcmoEmpUsTaxId == null">
						 		<s:text name="label.title.empUsTax.add"/>
					   		</s:if>
					   		<s:else>
						 		<s:text name="label.title.empUsTax.edit"/>
					  		</s:else>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
		     <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
	            <td class="employeeinputtd">
	             	<sj:autocompleter  
					    name="empUSTax.hcmoEmployeeId.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
		    		/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="empUSTax.hcmoEmployeeId.employeeId" /> </td>
	         </tr>
	         <tr>
	         	<th colspan="4"  class="formtitle1"> <b><s:text name="label.common.message.federalIncomeTax"/></b></th> 
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.empUSTax.federalStatus"/></td>
	         	<td class="employeeinputtd">
	         		<s:select 
	         			name="empUSTax.taxFederalStatus"
		            	headerKey=""
		            	headerValue="-- Please Select --"
						list="{'Married','Non Resident Align','Not Applicable','Single'}"
						cssClass="employeeselect" 
					/>
	         	 </td>
	         	 <td class="inputerrormessage"><s:fielderror fieldName="empUSTax.taxFederalStatus" /> </td>
	         	 <td class="inputtext"><s:text name="label.form.fields.empUSTax.federalException"/></td>
	         	 <td class="employeeinputtd"><s:textfield name="empUSTax.taxFederalExceptions"  cssClass="employeeinput"/></td>
	         	 <td class="inputerrormessage"><s:fielderror fieldName="empUSTax.taxFederalExceptions" /> </td>
			</tr>
			<tr>
				<th colspan="4"  class="formtitle1"><b><s:text name="label.common.message.stateIncomeTax"/></b></th>
			</tr>	
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.empUSTax.state"/></td>
	         	<td class="employeeinputtd">
	         		<sj:autocompleter  
					    name="empUSTax.taxState.hcmoregionId"
						list="#request.APPL_ZIPCODE_LIST"
						listKey="hcmoregionId"
						listValue="name"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
		    		/>
				</td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="empUSTax.taxState.hcmoregionId" /> </td>
				<td class="inputtext"><s:text name="label.form.fields.empUSTax.stateStatus"/></td>
				<td class="employeeinputtd">
					<s:select 
						name="empUSTax.taxStateStatus"
		         	 	headerKey=""
		    			headerValue="-- Please Select --"
						list="{'Married','Non Resident Align','Not Applicable','Single'}"
						cssClass="employeeselect" 
					/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="empUSTax.taxStateStatus" /> </td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.empUSTax.stateException"/></td>
	         	<td class="employeeinputtd"><s:textfield name="empUSTax.taxStateExceptions"  cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="empUSTax.taxStateExceptions" /> </td>
			</tr>
			<tr>	
				<td class="inputtext"><s:text name="label.form.fields.empUSTax.unempState"/></td>
	         	<td class="employeeinputtd">
	         		<sj:autocompleter  
					    name="empUSTax.taxUnempState.hcmoregionId"
						list="#request.APPL_ZIPCODE_LIST"
						listKey="hcmoregionId"
						listValue="name"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
		    		/>
	         	</td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="empUSTax.taxUnempState.hcmoregionId" /> </td>
			</tr>
			<tr>
	  			<td class="inputtext"><s:text name="label.form.fields.empUSTax.workState"/></td>
	         	<td class="employeeinputtd">
	         		<sj:autocompleter  
					    name="empUSTax.taxWorkState.hcmoregionId"
						list="#request.APPL_ZIPCODE_LIST"
						listKey="hcmoregionId"
						listValue="name"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
		    		/>
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="empUSTax.taxWorkState.hcmoregionId" /> </td>
	         </tr>
            <s:hidden name="empUSTax.hcmoEmpUsTaxId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorEmployeeUSTaxFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeeUSTaxFormId_div" indicator="indicatorEmployeeUSTaxFormId_div"/>
    		    </td>
    	        <td><s:if test="empUSTax==null || empUSTax.hcmoEmpUsTaxId==null">
	    	        	<s:url var="resetEmployeeUSTaxForm" action="resetEmployeeUSTaxForm"></s:url>
	    	            <sj:submit href="%{resetEmployeeUSTaxForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EmployeeUSTaxFormId_div" indicator="indicatorEmployeeUSTaxFormId_div"/>
	    	        </s:if>
	    	        <s:else>
	    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
	    	        </s:else></td>
    		 </tr>
	    </table> 		  		 
    </s:form>	
</div>	