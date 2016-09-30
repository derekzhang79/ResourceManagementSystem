<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_MessageViewId_div">
<br/>
	<img id="indicatorSubMenuMessageViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<s:form>
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	            		<tr>
	                  		<td class="formtitle"><s:text name="label.title.message.view" /></td>
	                	</tr>
   			<tr>
             	<td class="forminner"><table class="tablealign">
			<tr>
	         	<td class="inputtext"><s:text name="label.header.message.sender" /></td>
	         	<td class="employeeinputtd"><s:textfield name="message.sender.empFullName" readonly="true" cssClass="employeeinput" cssStyle="width: 600px;"></s:textfield></td>
	        </tr>
			<tr>
	         	<td class="inputtext"><s:text name="label.header.message.subject" /></td>
	         	<td class="employeeinputtd"><s:textfield name="message.subject" readonly="true" cssClass="employeeinput" cssStyle="width: 600px;"></s:textfield></td>
	        </tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.message.message" /></td>
				<td class="employeedisplaytd">
				<s:set id="contextPath"  value="#request.get('javax.servlet.forward.context_path')" />
					<sjr:ckeditor
		         			id="richtextViewAlertMessage" 
							name="message.message" 
							rows="10" 
							cols="80" 
							width="600"
							toolbar="MyToolbarView"
							customConfig="%{contextPath}/resources/js/ckeditor/ckeditor.configMessageView.js"
							readonly="true"
	         		></sjr:ckeditor>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>