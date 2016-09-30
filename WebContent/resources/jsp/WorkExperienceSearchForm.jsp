<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_WorkExperienceSearchId_div">
	<div class="submenu_bg">
		<s:if test="#session.WORKEXPERIENCE_ADD == true">
			<sj:a href="setUpWorkExperience.action" targets="submenu_WorkExperienceSearchId_div" indicator="indicatorSubMenuWorkExperienceSearchId_div" cssClass="link"><s:text name="MTIAddWorkExperience" /></sj:a> |
		</s:if>
		<s:if test="#session.WORKEXPERIENCE_VIEW == true">
			<sj:a href="getAllWorkExperience.action" targets="submenu_WorkExperienceSearchId_div" indicator="indicatorSubMenuWorkExperienceSearchId_div" cssClass="link"><s:text name="MTIViewWorkExperience"/></sj:a> |
			<sj:a href="workExpSearchForm.action" targets="submenu_WorkExperienceSearchId_div" indicator="indicatorSubMenuWorkExperienceSearchId_div" cssClass="link"><s:text name="MTISearchWorkExperience"/></sj:a>
		</s:if>
	</div>
	<br/>
	<img id="indicatorSubMenuWorkExperienceSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="workExpSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
                <td class="formtitle">
					 <s:text name="label.title.workExperience.search"/>
                </td>
	         </tr>
	         <tr>
                 <td class="forminner"><table class="tablealign">
	     	 <tr>
	            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
     			<td class="employeeinputtd">
     				<sj:autocompleter  
					    name="workexp.empIdObj.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
         	</tr><!-- text added by, R.Deepika -->
         	<tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr>
         	<tr>
         		<td class="inputtext"><s:text name="label.header.empExperience.employerName"/></td>
       	        <td class="employeeinputtd"><s:textfield name="workexp.employeerName" cssClass="employeeinput"/></td>
	        </tr>
	        <tr>
	        	<td class="inputtext"><s:text name="label.header.empExperience.jobTitle"/></td>
       	        <td class="employeeinputtd"><s:textfield name="workexp.empJobTitle" cssClass="employeeinput"/></td>
	        </tr>
	        <!--Button image added by, R.Deepika-->
	        <tr>
	        	<td class="inputtext"><s:text name="label.header.empExperience.fromDate"/></td>
         	    <td class="employeeinputtd"><sj:datepicker name="workexp.fromDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
         	    	<br/>
       	         	<!-- Extra Format Text Removed by, R.Deepika <s:text name="label.date.format"/>-->
       	        </td>
       	        <td class="employeeinputtd">
					<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="workexp.fromDateValue"
					    dataType="json"
						indicator="workExpSearchFormDate"      
					    cssClass="employeeselect" 
					/>
					<img id="workExpSearchFormDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
	         </tr>
	         <tr>
				<td></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="workexp.fromDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        <br/>
	       	     <!-- Extra Format Text Removed by, R.Deepika    <s:text name="label.date.format"/>--></td>
	       	        <td><s:text name="label.common.search.dateInformation"/>
				</td>
			 </tr>
	         <tr><td class="inputtext"><s:text name="label.header.empExperience.toDate"/></td>
	         	 <td class="employeeinputtd">
	       	        <sj:datepicker name="workexp.toDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        <br/>
	       	       <!-- Extra Format Text Removed by, R.Deepika  <s:text name="label.date.format"/>-->
	       	     </td>
	       	     <td class="employeeinputtd">
					<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="workexp.toDateValue"
					    dataType="json"
						indicator="workExpSearchFormToDateValue"      
					    cssClass="employeeselect" 
					/>
					<img id="workExpSearchFormToDateValue" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
	         </tr>
	         <tr>
				<td></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="workexp.toDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        <br/>
	       	        <s:text name="label.date.format"/></td>
	       	        <td><s:text name="label.common.search.dateInformation"/>
				</td>
			 </tr>
	         <tr><td class="inputtext"><s:text name="label.header.common.comments"/></td>
	         	 <!-- textarea size has been changed : venkat -->
	         	 <td class="employeeinputtd"><s:textarea name="workexp.comments" cssClass="employeetextarea" rows="4" cols="26"/></td>
	         </tr>
	   	 </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
   	     <tr>
   		    <td>
				<img id="indicatorWorkExpFormSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_WorkExperienceSearchId_div" indicator="indicatorWorkExpFormSearchId_div"/>
   		    </td>
   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
   		 </tr>
	    </table> 		  		 
   	</s:form>
</div>