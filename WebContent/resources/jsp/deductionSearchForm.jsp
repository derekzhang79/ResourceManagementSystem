<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div id="submenu_Deductions_div">
		<div class="submenu_bg">
			<s:if test="#session.DEDUCTION_ADD == true">
				<sj:a href="setUpForInsertOrUpdateDeduction.action" targets="submenu_Deductions_div" indicator="indicatorSubMenuDeductionsSearchId_div" cssClass="link"><s:text name="MTIAddDeductions" /></sj:a> |
			</s:if>
			<s:if test="#session.DEDUCTION_VIEW == true">
				<sj:a href="getAllDeductions.action" targets="submenu_Deductions_div" indicator="indicatorSubMenuDeductionsSearchId_div" cssClass="link"><s:text name="MTIViewDeductions"/></sj:a> |
				<sj:a href="deductionSearchForm.action" targets="submenu_Deductions_div" indicator="indicatorSubMenuDeductionsSearchId_div" cssClass="link"><s:text name="MTISearchDeductions"/></sj:a>
			</s:if>
		</div>
		<br/>
		<img id="indicatorSubMenuDeductionsSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		 		 	
		  <s:form action="deductionSearchResult">
		     	<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	      <tr>
	         <td><table class="employeeformiinertable">
	      <tr>
	          <td class="formtitle">
				 <s:text name="label.title.deduction.search"/>
	          </td>
	       </tr>
	       <tr>
                <td class="forminner"><table class="tablealign">
     		    	<tr>
		         		<td class="inputtext"><s:text name="label.header.deduction.name"/></td>
		         		<td class="employeeinputtd"><s:textfield name="deduction.deductionName" cssClass="employeeinput"/></td>		         		
		        	</tr>
		           <tr>
			       		<td class="inputtext"><s:text name="label.header.deduction.type"/></td>
			         	<td class="employeeinputtd"><s:radio name="deduction.deductionType" label="deductionType" list="#{'PreTax':'PreTax','PostTax':'PostTax'}" cssClass="employeeradiobut" /></td>			         	
			        
			           <td class="inputerrormessage"></td>
			        </tr>
		         	<tr>
						<td class="inputtext"><s:text name="label.header.deduction.mode"/></td>
						<td class="employeeinputtd">
							<s:select 
								name="deduction.deductionMode"
	            				headerKey=""
	            				headerValue="-- Please Select --"
								list="#{'Percentage':'Percentage','Actuals':'Actuals'}" 
								cssClass="employeeselect"
							/>
						</td>		         		
					</tr>
		    </table></td></tr></table></td></tr></table></td></tr></table><br/>
		    <table align="center"> 
	    	     <tr>
	    	     <td>
	    	     	<img id="indicatorDeductionForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	    		    <sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_Deductions_div" indicator="indicatorDeductionForm"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
		    </table>
	   	</s:form>
</div>  	