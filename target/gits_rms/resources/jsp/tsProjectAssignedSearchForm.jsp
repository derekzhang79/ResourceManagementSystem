<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TsProjectAssignedSearchId_div">
<div class="submenu_bg">
	<sj:a href="setUpTsProjectAssigned.action" targets="submenu_TsProjectAssignedSearchId_div" indicator="indicatorSubMenuTsProjectAssignedSearchForm" cssClass="link"><s:text name="MTIAddAssignProject" /></sj:a> |
	<sj:a href="getAllTsProjectAssigned.action" targets="submenu_TsProjectAssignedSearchId_div" indicator="indicatorSubMenuTsProjectAssignedSearchForm" cssClass="link"><s:text name="MTIViewAssignProject"/></sj:a>|
	<sj:a href="tsProjectAssignSearchForm.action" targets="submenu_TsProjectAssignedSearchId_div" indicator="indicatorSubMenuTsProjectAssignedSearchForm" cssClass="link"><s:text name="MTISearchAssignProject"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuTsProjectAssignedSearchForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="tsProjectAssignSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.assignProject.search"/>
	                </td>
	         </tr>
	          <tr>
	                  <td class="forminner"><table class="tablealign">
	     	<tr>
	            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
	            <td class="employeeinputtd">
					    <sj:autocompleter 
      					list="subEmpList" 
      					listKey="employeeId"
      					listValue="empFirstName"
        				name="tsProjAssigned.employeeName.employeeId"
         				cssClass="employeeselect"
         				selectBox="true"
						selectBoxIcon="true"
        			 />
				</td>
         </tr>
         	<!-- autocomplete text added by, R.Deepika-->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
          <tr><td class="inputtext"><s:text name="label.header.project.projectName"/></td>
	     	    
	     	     <td class="employeeinputtd">
					<s:select 
						headerKey=""
						headerValue="-- Please Select --"
						list="#request.APPL_PROJECT_LIST"
						listKey="projectId"
						listValue="projectName"
					    name="tsProjAssigned.projectName.projectId"
					   cssClass="employeeselect" 
					    />
				</td>
	     	 </tr>
	     	 <!--Button image added by, R.Deepika-->
         <tr><td class="inputtext"><s:text name="label.header.common.startDate"/></td>
		         	<td class="employeeinputtd">
		       	       <sj:datepicker name="tsProjAssigned.projectStartDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
		       	       
		       	    <!-- Extra text Removed by, R.Deepika-->
		         	</td>
		         	<td class="employeeinputtd">
						<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
						<sj:select 
							indicator="tsProjAssignedSearchFormProjectStartDate" 
						    href="%{getSearchProcessJSONList}"
						    list="searchProcessList"
						    dataType="json"
						    cssClass="employeeselect" 
						    name="tsProjAssigned.projectStartDateValue"/>
						<img id="tsProjAssignedSearchFormProjectStartDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		       	    </td>
         	</tr>
         	<tr>
				<td></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="tsProjAssigned.projectStartDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        
	       	       	    <!-- Extra text Removed by, R.Deepika --></td>
	       	        <td><s:text name="label.common.search.dateInformation"/>
				</td>
			</tr>
	        <tr><td class="inputtext"><s:text name="label.header.common.enddate"/></td>
		    	    <td class="employeeinputtd">
		       	        <sj:datepicker name="tsProjAssigned.projectEndDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
		       	        
		       	       	    <!-- Extra text Removed by, R.Deepika-->
		         	</td>
		         	<td class="employeeinputtd">
		         	<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="tsProjAssigned.projectEndDateValue"
					    dataType="json"
						indicator="tsProjAssignedSearchFormProjectEndDate"      
					    cssClass="employeeselect" 
					/>
					<img id="tsProjAssignedSearchFormProjectEndDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		       	    </td>
	         </tr>
	         <tr>
				<td></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="tsProjAssigned.projectEndDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
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
					<img id="indicatorSubMenuTsProjectAssSearchResult" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_TsProjectAssignedSearchId_div" indicator="indicatorSubMenuTsProjectAssSearchResult"/>
    		    </td>
    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>