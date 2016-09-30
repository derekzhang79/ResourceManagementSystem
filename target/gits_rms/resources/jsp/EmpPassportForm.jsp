
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EmployeePassportFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.EMPLOYEEPASSPORT_ADD == true">
			<sj:a href="setUpEmpPassport.action" targets="submenu_EmployeePassportFormId_div" indicator="indicatorSubMenuEmployeePassportFormId_div" cssClass="link"><s:text name="MTIAddEmployeePassport" /></sj:a> |
		</s:if>
		<s:if test="#session.EMPLOYEEPASSPORT_VIEW == true">
			<sj:a href="getAllEmpPassport.action" targets="submenu_EmployeePassportFormId_div" indicator="indicatorSubMenuEmployeePassportFormId_div" cssClass="link"><s:text name="MTIViewEmployeePassport"/></sj:a> |
			<sj:a href="passportSearchForm.action" targets="submenu_EmployeePassportFormId_div" indicator="indicatorSubMenuEmployeePassportFormId_div" cssClass="link"><s:text name="MTISearchEmployeePassport"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeePassportFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateEmpPassport">
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
				 		<s:if test="empPass==null || empPass.hcmoEmpPassportId == null">
						 <s:text name="label.title.empPassport.add"/>
					   </s:if>
					   <s:else>
						 <s:text name="label.title.empPassport.edit"/>
					   </s:else>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	                  <s:if test="empPass==null || empPass.hcmoEmpPassportId == null">
					<tr>
		            	<td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
		             	<td class="employeeinputtd">
		             		<sj:autocompleter  
							    name="empPass.empIdObj.employeeId"
								list="#request.APPL_EMPLOYEE_LIST"
								listKey="employeeId"
								listValue="empFullName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
						<td class="inputerrormessage"><s:fielderror fieldName="empPass.empIdObj.employeeId" /></td>
					</tr>
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
				 </s:if>
				 <s:else>
				 	<tr>
					 	<td class="inputtext"><s:text name="label.header.common.empName"></s:text></td>
						<td class="employeedisplaytd"><s:textfield name="empPass.empIdObj.empFirstName" readonly="true" cssClass="employeeinput"/></td>
						<s:hidden name="empPass.empIdObj.employeeId" />
					</tr>
			 	</s:else>
				<tr>
	         		<td class="inputtext"><s:text name="label.form.fields.empPassport.epPassportNo"/></td>
	         	    <td class="employeeinputtd"><s:textfield name="empPass.epPassportNo" cssClass="employeeinput"/></td>
	         	    <td class="inputerrormessage"><s:fielderror fieldName="empPass.epPassportNo" /> </td>
	         	</tr>
	         	<!--Button image added by, R.Deepika-->
	         	<!-- Format Date text Changed by, R.Deepika -->
	                  <tr>
	         		<td class="inputtext"><s:text name="label.form.fields.empPassport.PassportIssueDate"/></td>
	         		<td class="employeeinputtd"><sj:datepicker name="empPass.epPassportIssueDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        	
	         	 	</td>
	         	 	<td class="inputerrormessage"><s:fielderror fieldName="empPass.epPassportIssueDate" /> </td>
	         	</tr>
	         	<tr>
	         		<td class="inputtext"><s:text name="label.form.fields.empPassport.PassportExpireDate"/></td>
	         		<td class="employeeinputtd"><sj:datepicker name="empPass.epPassportExpireDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        	
	         	 	</td>
	         	 	<td class="inputerrormessage"><s:fielderror fieldName="empPass.epPassportExpireDate" /> </td>
	         	</tr> 
	         	<tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr>   
	         	<tr>
	         		<td class="inputtext"><s:text name="label.form.fields.empPassport.epComments"/></td>
	         	    <!-- textarea size has been changed : venkat -->
	         	    <td class="employeeinputtd"><s:textarea name="empPass.epComments" cssClass="employeetextarea" rows="4" cols="26"/></td>
	         	    <td class="inputerrormessage"><s:fielderror fieldName="empPass.epComments" /> </td>
	         	</tr>
				
	         	<tr>
	         		<td class="inputtext"><s:text name="label.form.fields.empPassport.epPassportTypeFlg"/></td>
        	     	<td class="employeeinputtd"><s:radio name="empPass.epPassportTypeFlg" label="passport or Visa" list="#{'0':'Passport','1':'Visa'}" cssClass="employeeradiobut" /></td>
	         	 	<td class="inputerrormessage"><s:fielderror fieldName="empPass.epPassportTypeFlg" /> </td>
	         	</tr>
				
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.empPassport.epL9Status"/></td>
       	        <td class="employeeinputtd"><s:textfield name="empPass.epL9Status" cssClass="employeeinput" /></td>
       	        <td class="inputerrormessage"><s:fielderror fieldName="empPass.epL9Status" /> </td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.empPassport.epL9ReviewDate"/></td>
	          	<td class="employeeinputtd"><sj:datepicker name="empPass.epL9ReviewDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
       	       	</td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="empPass.epL9ReviewDate" /></td>
	         </tr>
			 <tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr>  
	         <tr>
	            <td class="inputtext"><s:text name="label.form.fields.empPassport.countryName"/></td>
	            <td class="employeeinputtd">
	            	<sj:autocompleter  
					    name="empPass.country.hcmocountryId"
						list="#request.APPL_COUNTRY_LIST"
						listKey="hcmocountryId"
						listValue="countryName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
	            </td>
	            <td class="inputerrormessage"><s:fielderror fieldName="empPass.country.hcmocountryId" /></td>
	            <s:hidden name="empPass.hcmoEmpPassportId"/>
	        </tr>  
			
			
	        <tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>				
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorEmployeePassportFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeePassportFormId_div" indicator="indicatorEmployeePassportFormId_div"/>
	    		    </td>
	    	        <td><s:if test="empPass==null || empPass.hcmoEmpPassportId==null">
		    	        	<s:url var="resetEmployeePassportForm" action="resetEmployeePassportForm"></s:url>
		    	            <sj:submit href="%{resetEmployeePassportForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EmployeePassportFormId_div" indicator="indicatorEmployeePassportFormId_div"/>
		    	        </s:if>
		    	        <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	        </s:else></td>
	    		 </tr>
	    </table> 		  		 
	    	</s:form>
</div>