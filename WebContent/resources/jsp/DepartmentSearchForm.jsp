
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_DepartmentSearchFormId_div">
<div class="submenu_bg">
	<s:if test="#session.DEPARTMENT_ADD == true">	
		<sj:a href="setUpDepartment.action" targets="submenu_DepartmentSearchFormId_div" indicator="indicatorSubMenuDepartmentSearchFormId_div" cssClass="link"><s:text name="MTIAddDepartment" /></sj:a> |
	</s:if>
	<s:if test="#session.DEPARTMENT_VIEW == true">
		<sj:a href="getAllDepartment.action" targets="submenu_DepartmentSearchFormId_div" indicator="indicatorSubMenuDepartmentSearchFormId_div" cssClass="link"><s:text name="MTIViewDepartment"/></sj:a> |
		<sj:a href="deptSearchForm.action" targets="submenu_DepartmentSearchFormId_div" indicator="indicatorSubMenuDepartmentSearchFormId_div" cssClass="link"><s:text name="MTISearchDepartment"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuDepartmentSearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="departmentSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.department.search"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	     	 
	         <tr><td class="inputtext"><s:text name="label.header.department.name"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="dept.deptName" cssClass="employeeinput"/></td>
	         	        <td class="inputerrormessage"></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorDepartmentSearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_DepartmentSearchFormId_div" indicator="indicatorDepartmentSearchFormId_div"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>