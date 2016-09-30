<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.EmployeesVO"%>

<s:form action="skippedPermanently" id="employeeSkip">

<div id="submenu_employees_searchList_div_id">
	<div class="submenu_bg">
		<s:if test="#session.EMPLOYEE_ADD == true">
			<sj:a href="setUpEmployees.action" targets="submenu_employees_searchList_div_id" indicator="indicatorSubMenuEmployeesSearchResId_div" cssClass="link"><s:text name="MTIAddEmployee" /></sj:a> |
		</s:if>
		<s:if test="#session.EMPLOYEE_VIEW == true">
			<sj:a href="getAllEmp.action" targets="submenu_employees_searchList_div_id" indicator="indicatorSubMenuEmployeesSearchResId_div" cssClass="link"><s:text name="MTIViewEmployee"/></sj:a> |
			<sj:a href="employeeSearchForm.action" targets="submenu_employees_searchList_div_id" indicator="indicatorSubMenuEmployeesSearchResId_div" cssClass="link"><s:text name="MTISearchEmployee"/></sj:a>
		</s:if>
	</div>
	<br/>
	<img id="indicatorSubMenuEmployeesSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.employee.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.employee.firstName" var="HEmployeeFirstName"></s:text>
	  <s:text name="label.header.employee.lastName" var="HEmployeeLastName"></s:text>
	  <s:text name="label.header.employee.birthDate" var="HEmployeeBirthDate"></s:text>
	  <s:text name="label.header.employee.empStatus" var="HEmployeeStatus"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_employeesSearchList_div_id">
		  <display:table class="tableborder" name="emploList" id="emploList" pagesize="${NO_OF_RECORDS}" requestURI="employeeSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		      <%
		    	try{
		    		String sEmployeeId = ((EmployeesVO)pageContext.getAttribute("emploList")).getEmployeeId().toString();
		        	request.setAttribute("EmployeeId", sEmployeeId);   		
		    	}catch(NullPointerException ne){
		        }    	
		      %>
		  
		    <display:column property="empFirstName" title="${HEmployeeFirstName}" sortable="true" headerClass="sortable"/>
		    <display:column property="empLastName" title="${HEmployeeLastName}" sortable="true" headerClass="sortable"/>
		    <display:column property="empBirthDate" title="${HEmployeeBirthDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    <display:column property="empStatusIdObj.statusName" title="${HEmployeeStatus}" sortable="true" headerClass="sortable"/>
		    
		    <s:if test="#session.EMPLOYEE_VIEW==true">
		    	<display:column title="${HView}" class="viewedit" media="html"> 
					<s:url var="listViewEmployee" action="employeeView">
						<s:param name="employee.employeeId" value="#request.EmployeeId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employees_searchList_div_id','%{listViewEmployee}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
			
			<s:if test="#session.EMPLOYEE_UPDATE==true">
		    	<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listEditEmployee" action="setUpEmployees">
						<s:param name="employee.employeeId" value="#request.EmployeeId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employees_searchList_div_id','%{listEditEmployee}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
			
			<s:if test="#session.EMPLOYEE_DELETE==true">
		    	<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteEmployee" action="deleteEmployees">
						<s:param name="employee.employeeId" value="#request.EmployeeId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_employees_searchList_div_id','%{listDeleteEmployee}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="Employee.csv"/>
			 <display:setProperty name="export.excel.filename" value="Employee.xls"/>
			 <display:setProperty name="export.xml.filename" value="Employee.xml"/>
		  </display:table>
	  </div>
	  
	  <s:if test="#session.CONFIGURATION == 'CONFIGURATION'">
		  <table align="center">
				<tr>
					<td>
						<div class="button-comments">
	   		    		<div class="button-left"></div>
							<s:submit value="Skipped Permanently" cssClass="button-midle"></s:submit>
						<div class="button-right"></div>
	   		    		</div>
					</td>
					<td>
						<s:submit value="Skip" action="skip" cssClass="submitbutton117"></s:submit>
					</td>
				</tr>
		</table>
	</s:if> 
	</div>
</s:form> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_employeesSearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>   