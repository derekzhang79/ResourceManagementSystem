
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.EmpLocationHistoryVO"%>
<div id="submenu_EmployeeLocationHistorySearchFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.LOCATIONHISTORY_ADD == true">
			<sj:a href="setUpEmpLocationHistory.action" targets="submenu_EmployeeLocationHistorySearchFormId_div" indicator="indicatorSubMenuEmployeeLocationHistorySearchFormId_div" cssClass="link"><s:text name="MTIAddEmployeeLocationHistory" /></sj:a> |
		</s:if>
		<s:if test="#session.LOCATIONHISTORY_VIEW == true">
			<sj:a href="getAllEmpLocationHistory.action" targets="submenu_EmployeeLocationHistorySearchFormId_div" indicator="indicatorSubMenuEmployeeLocationHistorySearchFormId_div" cssClass="link"><s:text name="MTIViewEmployeeLocationHistory"/></sj:a> |
			<sj:a href="empLocHistSearchForm.action" targets="submenu_EmployeeLocationHistorySearchFormId_div" indicator="indicatorSubMenuEmployeeLocationHistorySearchFormId_div" cssClass="link"><s:text name="MTISearchEmployeeLocationHistory"/></sj:a>
		</s:if>
	</div>
	<br/>
		<img id="indicatorSubMenuEmployeeLocationHistorySearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="empLocHistSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.empLocationHistory.search"/>
	                </td>
	         </tr>
	          <tr>
	               <td class="forminner"><table class="tablealign">
	     	 <tr>
	            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
     			<td class="employeeinputtd">
     				<sj:autocompleter  
					    name="elhist.empIdObj.employeeId"
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
				<td class="inputtext"><s:text name="label.header.location.name"/></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
					    name="elhist.locationIdObj.hcmolocationId"
						list="#request.APPL_LOCATION_LIST"
						listKey="hcmolocationId"
						listValue="locationName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
			</tr>
	        <tr>
				<td class="inputtext"><s:text name="label.header.client.name" /></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
					    name="elhist.clientIdObj.hcmoclientId"
						list="#request.APPL_CLIENT_LIST"
						listKey="hcmoclientId"
						listValue="companyName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
			</tr>
			<!-- autocomplete text added by, R.Deepika -->
			<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
					
			<!--Button image added by, R.Deepika-->
	         <tr>
				<td class="inputtext"><s:text name="label.common.startDate" /></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="elhist.startDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	         <!-- Extra Format Text Removed by, R.Deepika-->
	         	 </td>
	         	 <td class="employeeinputtd">
					<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="elhist.startDateValue"
					    dataType="json"
						indicator="empLocHistSearchFormStartDate"      
					    cssClass="employeeselect" 
					/>
					<img id="empLocHistSearchFormStartDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
			</tr>
			<tr>
				<td></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="elhist.startDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        
	       	     <!-- Extra Format Text Removed by, R.Deepika--></td>
	       	        <td><s:text name="label.common.search.dateInformation"/>
				</td>
			</tr>
	        <tr>
				<td class="inputtext"><s:text name="label.common.endDate" /></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="elhist.endDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	       
	       	     <!-- Extra Format Text Removed by, R.Deepika-->
	         	</td>
	         	<td class="employeeinputtd">
					<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="elhist.endDateValue"
					    dataType="json"
						indicator="empLocHistSearchFormEndDate"      
					    cssClass="employeeselect" 
					/>
					<img id="empLocHistSearchFormEndDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="elhist.endDate"/></td>
			</tr>
			<tr>
				<td></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="elhist.endDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        <br/>
	       	        <s:text name="label.date.format"/></td>
	       	        <td><s:text name="label.common.search.dateInformation"/>
				</td>
			</tr>
	   	 </table></td></tr></table></td></tr></table></td></tr></table>
	   	 <br/>
	    <table align="center"> 
   	     <tr>
   		    <td>
				<img id="indicatorEmpLocHistFormSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeeLocationHistorySearchFormId_div" indicator="indicatorEmpLocHistFormSearchId_div"/>
   		    </td>
   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
   		 </tr>
	    </table> 		  		 
   	</s:form>
</div>