<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_JobTitleSearchId_div">
<div class="submenu_bg">
	<s:if test="#session.JOBTITLE_ADD == true">
		<sj:a href="setUpJobTitle.action" targets="submenu_JobTitleSearchId_div" indicator="indicatorSubMenuJobTitleSearchId_div" cssClass="link"><s:text name="MTIAddJobTitle" /></sj:a> |
	</s:if>
	<s:if test="#session.JOBTITLE_VIEW == true">
		<sj:a href="getAllJobTitle.action" targets="submenu_JobTitleSearchId_div" indicator="indicatorSubMenuJobTitleSearchId_div" cssClass="link"><s:text name="MTIViewJobTitle"/></sj:a> |
		<sj:a href="jobTitleSearchForm.action" targets="submenu_JobTitleSearchId_div" indicator="indicatorSubMenuJobTitleSearchId_div" cssClass="link"><s:text name="MTISearchJobTitle"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuJobTitleSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	    <s:form action="jobTitleSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center"><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.jobTitle.search"/>
	                </td>
	         </tr>
	     	 <tr><td class="forminner"><table class="tablealign">
				
				<tr>
	            	<td class="inputtext"><s:text name="label.header.salaryGrade.name"/></td>
	            	<td class="employeeinputtd">
	            		<sj:autocompleter  
						    name="jobTitle.salaryGradeIdObj.hcmoSalaryGradeId"
							list="#request.APPL_SALARYGRADE_LIST"
							listKey="hcmoSalaryGradeId"
							listValue="salaryName"
						    selectBox="true"
						    selectBoxIcon="true"
						    cssClass="employeeselect"
				    	/>
					</td>
	        	</tr>
	        	<!-- Autocomplete text added by, R.Deepika -->
	        	<tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr>
		        <tr>
		        	<td class="inputtext"><s:text name="label.header.jobTitle.name"/></td>
		         	<td class="employeeinputtd"><s:textfield name="jobTitle.jobTitleName" cssClass="employeeinput"/></td>
		         	<td class="inputerrormessage"><s:fielderror fieldName="jobTitle.jobTitleName"/></td>
		        </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorJobTitleSearchForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_JobTitleSearchId_div" indicator="indicatorJobTitleSearchForm"/>
	    		   	</td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
   	<br/><br/><br/>
	<div id="tabJobTitleSearchResult">
	</div>
</div>