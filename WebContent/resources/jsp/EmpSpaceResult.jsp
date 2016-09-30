<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="com.gits.rms.vo.EmpSpaceVO"%>

<div id="submenu_EmployeeSpaceSearchResId_div">
	<div class="submenu_bg">
		<sj:a href="setUpFileUpload.action" targets="submenu_EmployeeSpaceSearchResId_div" indicator="indicatorSubMenuEmployeeSpaceSearchResId_div" cssClass="link"><s:text name="MTIAddEmployeeSpace"/></sj:a> |
		<sj:a href="getAllEmpSpace.action" targets="submenu_EmployeeSpaceSearchResId_div" indicator="indicatorSubMenuEmployeeSpaceSearchResId_div" cssClass="link"><s:text name="MTIViewEmployeeSpace"/></sj:a> |
		<sj:a href="getAllSharedEmpSpace.action" targets="submenu_EmployeeSpaceSearchResId_div" indicator="indicatorSubMenuEmployeeSpaceSearchResId_div" cssClass="link"><s:text name="MTIViewSharedEmployeeSpace"/></sj:a> |
		<sj:a href="empSpaceSearchForm.action" targets="submenu_EmployeeSpaceSearchResId_div" indicator="indicatorSubMenuEmployeeSpaceSearchResId_div" cssClass="link"><s:text name="MTIViewSearchEmployeeSpace"/></sj:a>
		<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
				|<sj:a href="sharedAndUploadedEmpSpace.action" targets="submenu_EmployeeSpaceSearchResId_div" indicator="indicatorSubMenuEmployeeSpaceSearchResId_div" cssClass="link"><s:text name="MTIViewSharedAndUploadEmployeeSpace"/></sj:a>
		</s:if> 
	</div>
	<br/><img id="indicatorSubMenuEmployeeSpaceSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<s:if test="#session.WARNINGMESSAGE!=null">
		<%try{session.getAttribute("WARNINGMESSAGE");}catch(Exception e){e.printStackTrace();}%>
		<s:property value="WARNINGMESSAGE"/>
	</s:if>
		
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.empSpace.uploadedFileList"/></span></li></div>

<table align="center" class="borderAll">
	<tr>
        <th><s:text name="label.title.empSpace.fileName"/></th>
        <th><s:text name="label.title.empSpace.fileView"/></th>
        <!--<th><s:text name="label.common.link.delete"/></th>
    --></tr>
	<s:iterator value="uploadFileName" status="stat">
		<tr>
			<td><s:property value="%{uploadFileName[#stat.index]}" /></td>
			<td>		
				<s:url var="empSpaceFileDownload" action="empSpaceFileDownload">
		      		   <s:param name="empSpace.hcmoEmpSpaceId" value="empSpace.hcmoEmpSpaceId"/>
		   		</s:url>
				<s:a href="%{empSpaceFileDownload}"><s:property value="%{uploadFileName[#stat.index]}"/></s:a>
			</td>
			<!--<td>
				<s:url var="deleteEmpSpace" action="deleteEmpSpace">
					 <s:param name="empSpace.hcmoEmpSpaceId" value="empSpace.hcmoEmpSpaceId"/>
				</s:url>
				<s:a href="%{deleteEmpSpace}"><s:text name="Delete"/></s:a>
			</td>
		--></tr>
	</s:iterator>
</table>

</div>