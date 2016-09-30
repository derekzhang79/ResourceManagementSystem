
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EducationSearchFormId_div">
<div class="submenu_bg">
	<s:if test="#session.EDUCATION_ADD == true">
		<sj:a href="setUpEducation.action" targets="submenu_EducationSearchFormId_div" indicator="indicatorSubMenuEducationSearchFormId_div" cssClass="link"><s:text name="MTIAddEducation" /></sj:a> |
	</s:if>
	<s:if test="#session.EDUCATION_VIEW == true">
		<sj:a href="getAllEducation.action" targets="submenu_EducationSearchFormId_div" indicator="indicatorSubMenuEducationSearchFormId_div" cssClass="link"><s:text name="MTIViewEducation"/></sj:a> |
		<sj:a href="educationSearchForm.action" targets="submenu_EducationSearchFormId_div" indicator="indicatorSubMenuEducationSearchFormId_div" cssClass="link"><s:text name="MTISearchEducation"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuEducationSearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="educationSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.education.search"/>
	                </td>
	         </tr>
	          <tr>
                  <td class="forminner"><table class="tablealign">
	     	 <tr>
	            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
	            <td class="employeeinputtd">
					<sj:autocompleter  
					    name="edu.empIdObj.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
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
         	<tr>
         		<td class="inputtext"><s:text name="label.header.education.major"/></td>
	         	<td class="employeeinputtd"><s:textfield name="edu.eduMajor" cssClass="employeeinput"/></td>
         	</tr>
         	<tr>
         		<td class="inputtext"><s:text name="label.header.education.year"/></td>
	         	<td class="employeeinputtd"><s:textfield name="edu.eduYear" cssClass="employeeinput"/></td>
         	</tr>
         	<!--Button image added by, R.Deepika-->
         	<tr>
         		<td class="inputtext"><s:text name="label.header.common.startDate"/></td>
	         	<td class="employeeinputtd">
	       	       <sj:datepicker name="edu.eduStartDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	       
	       	      <!-- Extra Format Text Removed by, R.Deepika-->
	         	</td>
	         	<td class="employeeinputtd">
					<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="edu.eduStartDateValue"
					    dataType="json"
						indicator="educationSearchFormEduStartDate"      
					    cssClass="employeeselect" 
					/>
					<img id="educationSearchFormEduStartDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
         	</tr>
         	<tr>
				<td></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="edu.eduStartDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	       <!-- Extra Format Text Removed by, R.Deepika-->
	       	    </td>
	       	    <td>
	       	    	<s:text name="label.common.search.dateInformation"/>
				</td>
			</tr>
	        <tr>
	        	<td class="inputtext"><s:text name="label.header.common.enddate"/></td>
    	    	<td class="employeeinputtd">
	       	        <sj:datepicker name="edu.eduEndDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	       
	       	        <!-- Extra Format Text Removed by, R.Deepika-->
	         	</td>
	         	<td class="employeeinputtd">
					<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="edu.eduEndDateValue"
					    dataType="json"
						indicator="educationSearchFormEduEndDate"      
					    cssClass="employeeselect" 
					/>
					<img id="educationSearchFormEduEndDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
	         </tr>
	         <tr>
				<td></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="edu.eduEndDateEnds" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        <br/><s:text name="label.date.format"/>
	       	    </td>
	       	    <td>
	       	    	<s:text name="label.common.search.dateInformation"/>
				</td>
			</tr>
	   	    <tr>
	   	    	<td class="inputtext"><s:text name="label.header.education.univ"/></td>
       	        <td class="employeeinputtd"><s:textfield name="edu.eduUniversity" cssClass="employeeinput" /></td>
	        </tr>
    	    <tr>
    	    	<td class="inputtext"><s:text name="label.header.education.degree"/></td>
         	    <td class="employeeinputtd"><s:textfield name="edu.eduDegree" cssClass="employeeinput" /></td>
    	    </tr>
	   	 </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorEducationFormSearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EducationSearchFormId_div" indicator="indicatorEducationFormSearchFormId_div"/>
    		    </td>
    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>