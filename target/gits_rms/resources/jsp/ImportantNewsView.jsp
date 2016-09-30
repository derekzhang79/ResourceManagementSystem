<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>
<div id="submenu_ImportantNewsViewId_div">
<div class="submenu_bg">
		<sj:a href="setUpImportantNews.action" targets="submenu_ImportantNewsViewId_div" indicator="indicatorSubMenuImportantNewsViewId_div" cssClass="link"><s:text name="MTIAddImportantNews" /></sj:a> |
		<sj:a href="getAllImportantNews.action" targets="submenu_ImportantNewsViewId_div" indicator="indicatorSubMenuImportantNewsViewId_div" cssClass="link"><s:text name="MTIViewImportantNews"/></sj:a> |
		<sj:a href="importantNewsSearchForm.action" targets="submenu_ImportantNewsViewId_div" indicator="indicatorSubMenuImportantNewsViewId_div" cssClass="link"><s:text name="MTISearchImportantNews"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuImportantNewsViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>					    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form>
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
		                 <td class="formtitle">
							<s:text name="label.title.importantNews.view" />
		                  </td>
	                </tr>
	     			<tr>
	                	<td class="forminner"><table class="tablealign">
			<tr>
				<td class="inputtext"><s:text name="label.header.importantNews.importantNewsId" /></td>
				<td class="employeedisplaytd"><s:property value="important.importantNewsId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd"><s:property value="important.empIdObj.empFullName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.message.subject" /></td>
				<td class="employeedisplaytd"><s:property value="important.subject"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.message.message" /></td>
				<td class="employeedisplaytd">
					<s:set id="contextPath"  value="#request.get('javax.servlet.forward.context_path')" />
					<sjr:ckeditor
		         			id="richtextViewAlertMessage" 
							name="important.message" 
							rows="10" 
							cols="80" 
							width="600"
							toolbar="MyToolbarView"
							customConfig="%{contextPath}/resources/js/ckeditor/ckeditor.configMessageView.js"
							readonly="true"
	         		></sjr:ckeditor></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.importantNews.show" /></td>
				<td class="employeedisplaytd"><s:checkbox name="important.showMessage" disabled="true"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="important.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="important.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="important.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
	     	<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="important.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="important.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
	</s:form>
</div>