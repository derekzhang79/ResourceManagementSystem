<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<div id="submenu_SalaryFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.SALARY_ADD == true">
			<sj:a href="setUpSalary.action" targets="submenu_SalaryFormId_div" indicator="indicatorSubMenuSalaryFormId_div" cssClass="link"><s:text name="MTIAddSalary" /></sj:a> |
		</s:if>
		<s:if test="#session.SALARY_VIEW == true">
			<sj:a href="getAllSalary.action" targets="submenu_SalaryFormId_div" indicator="indicatorSubMenuSalaryFormId_div" cssClass="link"><s:text name="MTIViewSalary"/></sj:a> |
			<sj:a href="salarySearchForm.action" targets="submenu_SalaryFormId_div" indicator="indicatorSubMenuSalaryFormId_div" cssClass="link"><s:text name="MTISearchSalary"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuSalaryFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateSalary">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
                  		<s:if test="sal==null || sal.hcmosalaryId == null">
							<s:text name="label.title.salary.add" />
						</s:if> <s:else>
							<s:text name="label.title.salary.edit" />
						</s:else>
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<s:if test="sal==null || sal.hcmosalaryId == null">
				<tr>
		     		<td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
					 <td class="employeeinputtd">
					 	<sj:autocompleter  
						    name="sal.empIdObj.employeeId"
							list="#request.APPL_EMPLOYEE_LIST"
							listKey="employeeId"
							listValue="empFullName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
			    		/>
					</td>
					<td class="inputerrormessage">
		                <s:fielderror fieldName="sal.empIdObj.employeeId" />
		            </td>
		    	</tr>    
		    	<tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr> 
            </s:if>
            <s:else>
            	<tr>
	            	<td class="inputtext"><s:text name="label.header.common.empName"></s:text></td>
					<td class="employeedisplaytd"><s:textfield name="sal.empIdObj.empFirstName" readonly="true" cssClass="employeeinput"/></td>
					<s:hidden name="sal.empIdObj.employeeId" />
				</tr>
			</s:else>			
			<tr>
				<td class="inputtext">
					<s:text	name="label.form.fields.salary.name" />	
					<s:property value="#session.CURRENCY_TYPE_VALUE"/>
					<s:text name="label.form.common.mandatory"/>
				</td>
				<td class="employeeinputtd"><s:textfield name="sal.salary" cssClass="employeeinput" /></td>
				<td class="inputerrormessage"><s:fielderror fieldName="sal.salary" /></td>
			</tr>
			<!--Button image added by, R.Deepika-->
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.salary.decDate" /></td>
				<td class="employeeinputtd"><sj:datepicker name="sal.declarationDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/></td>
				<td class="inputerrormessage"><s:fielderror fieldName="sal.declarationDate" /></td>
			</tr>
			<tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.date.format"></s:text></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorSalaryFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_SalaryFormId_div" indicator="indicatorSalaryFormId_div"/>
				</td>
				<td>
					<s:if test="sal==null || sal.hcmosalaryId==null">
	    	        	<s:url var="resetSalaryForm" action="resetSalaryForm"></s:url>
	    	            <sj:submit href="%{resetSalaryForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_SalaryFormId_div" indicator="indicatorSalaryFormId_div"/>
	    	        </s:if>
	    	        <s:else>
	    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
	    	        </s:else>
	    	    </td>
			</tr>
			
		</table>
		<s:hidden name="sal.hcmosalaryId"/>
	</s:form>
</div>