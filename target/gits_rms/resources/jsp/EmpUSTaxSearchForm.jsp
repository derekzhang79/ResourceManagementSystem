<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EmployeeUSTaxSearchId_div">
	<div class="submenu_bg">
		<s:if test="#session.EMPLOYEEUSTAX_ADD == true">
			<sj:a href="setUpEmpUSTax.action" targets="submenu_EmployeeUSTaxSearchId_div" indicator="indicatorSubMenuEmployeeUSTaxSearchId_div" cssClass="link"><s:text name="MTIAddEmployeeUSTax" /></sj:a> |
		</s:if>
		<s:if test="#session.EMPLOYEEUSTAX_VIEW == true">
			<sj:a href="getAllEmpUSTax.action" targets="submenu_EmployeeUSTaxSearchId_div" indicator="indicatorSubMenuEmployeeUSTaxSearchId_div" cssClass="link"><s:text name="MTIViewEmployeeUSTax"/></sj:a> |
			<sj:a href="usTaxSearchForm.action" targets="submenu_EmployeeUSTaxSearchId_div" indicator="indicatorSubMenuEmployeeUSTaxSearchId_div" cssClass="link"><s:text name="MTISearchEmployeeUSTax"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeeUSTaxSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="usTaxSearchResult">
	    <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
					 	<s:text name="label.title.empUsTax.search"/>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
		     <tr>
	            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
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
	         </tr>
	         <tr>
	         	<th colspan="4"  class="formtitle1">
	         		<b><s:text name="label.common.message.federalIncomeTax"/></b>
	         	</th> 
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.empUSTax.federalStatus"/></td>
	         	<td class="employeeinputtd">
	         	 	<s:select 
	         	 		name="empUSTax.taxFederalStatus"
		            	headerKey=""
		            	headerValue="-- Please Select --"
						list="{'Married','Non Resident Align','Not Applicable','Single'}"
						cssClass="employeeselect" 
					/>
	         	 </td>
	         	 
	         	 <td class="inputtext"><s:text name="label.header.empUSTax.federalException"/></td>
	         	 <td class="employeeinputtd"><s:textfield name="empUSTax.taxFederalExceptions"  cssClass="employeeinput"/></td>
			</tr>
			<tr>
				<th colspan="4"  class="formtitle1"><b>
					<s:text name="label.common.message.stateIncomeTax"/></b>
				</th>
			</tr>	
			<tr>
				<td class="inputtext"><s:text name="label.header.empUSTax.state"/></td>
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
				
				<td class="inputtext"><s:text name="label.header.empUSTax.stateStatus"/></td>
				<td class="employeeinputtd">
					<s:select 
						name="empUSTax.taxStateStatus"
		         	 	headerKey=""
		    			headerValue="-- Please Select --"
						list="{'Married','Non Resident Align','Not Applicable','Single'}"
						cssClass="employeeselect" />
				</td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.empUSTax.stateException"/></td>
	         	<td class="employeeinputtd">
	         		<s:textfield name="empUSTax.taxStateExceptions"  cssClass="employeeinput"/>
	         	</td>
			</tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
   	     <tr>
   		    <td>
				<img id="indicatorEmployeeUSTaxFormSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeeUSTaxSearchId_div" indicator="indicatorEmployeeUSTaxFormSearchId_div"/>
   		    </td>
   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
   		 </tr>
	    </table> 		  		 
    </s:form>
</div>	 