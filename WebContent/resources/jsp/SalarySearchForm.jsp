<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.SalaryVO"%>

<div id="submenu_SalarySearchId_div">
	<div class="submenu_bg">
		<s:if test="#session.SALARY_ADD == true">
			<sj:a href="setUpSalary.action" targets="submenu_SalarySearchId_div" indicator="indicatorSubMenuSalarySearchId_div" cssClass="link"><s:text name="MTIAddSalary" /></sj:a> |
		</s:if>
		<s:if test="#session.SALARY_VIEW == true">
			<sj:a href="getAllSalary.action" targets="submenu_SalarySearchId_div" indicator="indicatorSubMenuSalarySearchId_div" cssClass="link"><s:text name="MTIViewSalary"/></sj:a> |
			<sj:a href="salarySearchForm.action" targets="submenu_SalarySearchId_div" indicator="indicatorSubMenuSalarySearchId_div" cssClass="link"><s:text name="MTISearchSalary"/></sj:a>
		</s:if>
	</div>
	<br/>
		<img id="indicatorSubMenuSalarySearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="salarySearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.salary.search"/>
	                </td>
	         </tr>
	          <tr>
	              <td class="forminner"><table class="tablealign">
	     	 <tr>
	            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
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
         	</tr>
         	<tr>
				<td class="inputtext"><s:text name="label.header.salary.name" />	
					<s:text name="currencyTypeValue"/></td>
				<td class="employeeinputtd"><s:textfield name="sal.salary" cssClass="employeeinput" /></td>
			</tr>
			<!--Button image added by, R.Deepika-->
	        <tr>
				<td class="inputtext"><s:text name="label.header.salary.decDate" /></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="sal.declarationDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
					<br/>	       	       
	       	        <s:text name="label.date.format"/>
				</td>
	     	    <td class="employeeinputtd">
					<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="sal.dateValue"
					    dataType="json"
						indicator="salarySearchFormDate"      
					    cssClass="employeeselect" 
					/>
					<img id="salarySearchFormDate" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
	         </tr>
			<tr>
				<td></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="sal.declarationEndDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
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
				<img id="indicatorSalaryForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_SalarySearchId_div" indicator="indicatorSalaryForm"/>
   		    </td>
   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
   		 </tr>
	    </table> 		  		 
   	</s:form>
</div>