
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EmployeeStatusSearchId_div">
<div class="submenu_bg">
	<s:if test="#session.EMPLOYEESTATUS_ADD == true">
		<sj:a href="setUpForInsertOrUpdateEmpStatus.action" targets="submenu_EmployeeStatusSearchId_div" indicator="indicatorSubMenuEmployeeStatusSearchId_div" cssClass="link"><s:text name="MTIAddEmployeeStatus" /></sj:a> |
	</s:if>
	<s:if test="#session.EMPLOYEESTATUS_VIEW == true">
		<sj:a href="getAllEmployeeStatus.action" targets="submenu_EmployeeStatusSearchId_div" indicator="indicatorSubMenuEmployeeStatusSearchId_div" cssClass="link"><s:text name="MTIViewEmployeeStatus"/></sj:a> |
		<sj:a href="employeeStatusSearchForm.action" targets="submenu_EmployeeStatusSearchId_div" indicator="indicatorSubMenuEmployeeStatusSearchId_div" cssClass="link"><s:text name="MTISearchEmployeeStatus"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuEmployeeStatusSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="employeeStatusSearchResult">
		<table class="maintable">
	        <tr>
	        	<td align="center" ><table class="formouter">
	        <tr>
	            <td><table class="employeeformiinertable">
	        <tr>
	                <td class="formtitle">
						 <s:text name="label.title.empStatus.search"/>
	                </td>
	        </tr>
	     	<tr>
	               <td class="forminner"><table class="tablealign">     	 
	        <tr>
	        	<td class="inputtext">
	        		<s:text name="label.header.empStatus.name"/>
	        	</td>
	         	<td class="employeeinputtd">
	         		<s:textfield name="empStatus.statusName" cssClass="employeeinput"/>
	         	</td>
	         	<td class="inputerrormessage"></td>
	        </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorEmployeeStatusForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeeStatusSearchId_div" indicator="indicatorEmployeeStatusForm"/>
					</td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
	</s:form>
</div>