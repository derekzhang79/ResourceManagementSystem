<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<div id="submenu_deductions_div">
		<div class="submenu_bg">
			<s:if test="#session.DEDUCTION_ADD == true">
				<sj:a href="setUpForInsertOrUpdateDeduction.action" targets="submenu_deductions_div" indicator="indicatorSubMenuDeductionsFormId_div" cssClass="link"><s:text name="MTIAddDeductions" /></sj:a> |
			</s:if>
			<s:if test="#session.DEDUCTION_VIEW == true">
				<sj:a href="getAllDeductions.action" targets="submenu_deductions_div" indicator="indicatorSubMenuDeductionsFormId_div" cssClass="link"><s:text name="MTIViewDeductions"/></sj:a> |
				<sj:a href="deductionSearchForm.action" targets="submenu_deductions_div" indicator="indicatorSubMenuDeductionsFormId_div" cssClass="link"><s:text name="MTISearchDeductions"/></sj:a>
			</s:if>
		</div>
		<br/>
		<img id="indicatorSubMenuDeductionsFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		 	<jsp:include page="common/messages.jsp" flush="true"/>	 	
		  <s:form action="insertOrUpdateDeduction">
		     	<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	      <tr>
	           <td><table class="deductionformiinertable">
	      <tr>
	          <td></td>
	          <td class="formtitle">
	            <s:if test="deduction==null || deduction.deductionId==null">
					 <s:text name="label.title.deduction.add"/>
	  			</s:if>
	   			<s:else>
		 			<s:text name="label.title.deduction.edit"/>
	   			</s:else>
	          </td>
	       </tr>
	       <tr>     <td class="inputerrormessage"></td>
	                <td class="forminner"><table class="tablealign">
			     	   	<tr>
		         		<td class="inputtext"><s:text name="label.form.fields.deduction.name"/></td>
		         		<td class="employeeinputtd"><s:textfield name="deduction.deductionName" cssClass="employeeinput"/></td>
		         		<td class="inputerrormessage">
	                		<s:fielderror fieldName="deduction.deductionName" />
	           		    </td>
		        	</tr>
		         	<tr>
			       		<td class="inputtext"><s:text name="label.form.fields.deduction.type"/></td>
			         	<td><s:radio name="deduction.deductionType" label="deductionType" list="#{'PreTax':'PreTax','Tax':'Tax','PostTax':'PostTax'}"/></td>
			         	<td class="inputerrormessage"><s:fielderror fieldName="deduction.deductionType" /></td>
			        </tr>
		         	<tr>
						<td class="inputtext"><s:text name="label.form.fields.deduction.mode"/></td>
						<td class="employeeinputtd">
							<s:select 
								name="deduction.deductionMode"
	            				headerKey=""
	            				headerValue="-- Please Select --"
								list="#{'Percentage':'Percentage','Actuals':'Actuals'}" 
								cssClass="employeeselect"
							/>
						</td>
		         		<td class="inputerrormessage"><s:fielderror fieldName="deduction.deductionMode" /></td>
					</tr>
			        <s:hidden name="deduction.deductionId"/>
		    </table></td></tr></table></td></tr></table></td></tr></table>
		    		 <br/>
		    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorDeductionAddForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_deductions_div" indicator="indicatorDeductionAddForm"/>
					</td>
					<s:if test="deduction==null || deduction.deductionId==null">
				         <td>
		    		    	<s:url var="resetDeductionForm" action="resetDeductionForm"></s:url>
		    	            <sj:submit href="%{resetDeductionForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_deductions_div" indicator="indicatorDeductionAddForm"/>
			            </td>
			       </s:if>
			       <s:else>
	    	            <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table>    		 
		   			     	
	   	</s:form>
	   </div>