<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_admin_comments_form_div">
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	
	    <s:form action="updateAdminComments">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign">
	               <s:textfield name="subEmployeeID"></s:textfield>
					<s:textfield name="questionGroupIdentifiId"></s:textfield>
					<s:textfield name="questionGeneralInfoId"></s:textfield>
	     	 
	         <tr><td class="inputtext"><s:text name="label.header.question.approverComments"/></td>
						<!-- rows and cols size has changed:venkat -->
	         	        <td class="employeeinputtd"><s:textarea name="adminComments" rows="4" cols="26" cssClass="employeetextarea" cssStyle="{width:200px;height:auto;}"/></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center" > 
	    	     <tr>
	    		    <td>
						<img id="indicatorAdminCommentsForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_admin_comments_form_div" indicator="indicatorAdminCommentsForm"/>
	    		    </td>
	    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div> 