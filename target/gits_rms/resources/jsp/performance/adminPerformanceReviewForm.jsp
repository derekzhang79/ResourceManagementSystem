<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_Admin_Performance_Form_div">
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	    <s:form action="adminPerformance">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
                <td class="formtitle">
					 <s:text name="label.header.performace.review"/>
                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign">    
		 		<tr>
		        	<td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
		            <td class="employeeinputtd">
		            	<s:select  
						    name="employeeId"
							list="#request.APPL_EMPLOYEE_LIST"
							listKey="employeeId"
							listValue="empFullName"
						    cssClass="employeeselect"
				    	/>
					</td>
					<td class="inputerrormessage"></td>	
				</tr>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center"> 
	   	     <tr>
	   		    <td>
					<img id="indicatorperformanceAdminCommentsTab" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_Admin_Performance_Form_div" indicator="indicatorperformanceAdminCommentsTab"/>
	   		    </td>
	   		 </tr>
	    </table> 	
	    </s:form>
</div>	