
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EmployeePassportSearchId_div">
	<div class="submenu_bg">
		<s:if test="#session.EMPLOYEEPASSPORT_ADD == true">
			<sj:a href="setUpEmpPassport.action" targets="submenu_EmployeePassportSearchId_div" indicator="indicatorSubMenuEmployeePassportSearchId_div" cssClass="link"><s:text name="MTIAddEmployeePassport" /></sj:a> |
		</s:if>
		<s:if test="#session.EMPLOYEEPASSPORT_VIEW == true">
			<sj:a href="getAllEmpPassport.action" targets="submenu_EmployeePassportSearchId_div" indicator="indicatorSubMenuEmployeePassportSearchId_div" cssClass="link"><s:text name="MTIViewEmployeePassport"/></sj:a> |
			<sj:a href="passportSearchForm.action" targets="submenu_EmployeePassportSearchId_div" indicator="indicatorSubMenuEmployeePassportSearchId_div" cssClass="link"><s:text name="MTISearchEmployeePassport"/></sj:a>
		</s:if>
	</div>
	<br/>
	<img id="indicatorSubMenuEmployeePassportSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="passportSearchResult">
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						 <s:text name="label.title.empPassport.search"/>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
			<tr>
	             <td class="inputtext"><s:text name="label.header.common.empName"/></td>
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
				<td></td>
            </tr>
            
					<!-- autocomplete text added by, R.Deepika-->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
	        <tr><td class="inputtext"><s:text name="label.header.empPassport.PassportNum"/></td>
        	    <td class="employeeinputtd"><s:textfield name="empPass.epPassportNo" cssClass="employeeinput"/></td>
	        </tr>
	        <!--Button image added by, R.Deepika-->
	        <tr><td class="inputtext"><s:text name="label.header.empPassport.PassportIssueDate"/></td>
	         	 <td class="employeeinputtd">
	       	        <sj:datepicker name="empPass.epPassportIssueDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        
	       	        <!-- Extra Date Format Text Remoed by, R.Deepika-->
	         	 </td>
	         	 <td class="employeeinputtd">
					<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="empPass.epPassportIssueDateValue"
					    dataType="json"
						indicator="empPassportSearchFormIssueDate"      
					    cssClass="employeeselect" 
					/>
					<img id="empPassportSearchFormIssueDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
	        </tr>
	        <tr>
				<td></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="empPass.epPassportIssueDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        
	       	          <!-- Extra Date Format Text Remoed by, R.Deepika--></td>
	       	        <td><s:text name="label.common.search.dateInformation"/>
				</td>
			</tr>
	        <tr><td class="inputtext"><s:text name="label.header.empPassport.PassportExpireDate"/></td>
	         	 <td class="employeeinputtd">
	       	        <sj:datepicker name="empPass.epPassportExpireDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	       
	       	        <!-- Extra Date Format Text Remoed by, R.Deepika-->
	         	 </td>
	         	 <td class="employeeinputtd">
					<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="empPass.epPassportExpireDateValue"
					    dataType="json"
						indicator="empPassportSearchFormExpireDate"      
					    cssClass="employeeselect" 
					/>
					<img id="empPassportSearchFormExpireDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
	        </tr>
	        <tr>
				<td></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="empPass.epPassportExpireDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        <br/>
	       	        <s:text name="label.date.format"/></td>
	       	        <td><s:text name="label.common.search.dateInformation"/>
				</td>
			</tr>
	        <tr>
	            <td class="inputtext"><s:text name="label.header.empPassport.countryName"/></td>
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
	        </tr>  
	        
					<!-- autocomplete text added by, R.Deepika-->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
   	     <tr>
   		    <td>
				<img id="indicatorEmployeePassportFormSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeePassportSearchId_div" indicator="indicatorEmployeePassportFormSearchId_div"/>				    
   		    </td>
   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
   		 </tr>
 	   </table> 		  		 
	</s:form>
</div>	