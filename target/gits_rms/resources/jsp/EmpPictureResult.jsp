<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="picUploadResultId_div">
<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form>
	<table align="center" class="borderAll">
	    <tr>
	        <td><s:text name="label.form.fields.common.picture"/></td>
	        <td><s:text name="label.common.link.delete"/></td>
	    </tr>
		<tr>
			<td>
               	<s:url var="userImageDownload" action="userImageDownload">
	       		   <s:param name="employee.employeeId" value="employee.employeeId"/>
	       		</s:url> 
                <s:a href="%{userImageDownload}"><s:property value="employee.empPicturePath"/></s:a>
            </td>
	        <td>
				<s:url var="deleteEmployeesPic" action="deleteEmployeesPic">
					<s:param name="employee.employeeId" value="employee.employeeId"/>
				</s:url>
				<s:a href="%{deleteEmployeesPic}"><s:text name="Delete"/></s:a>
 			</td>				
		</tr>				
	</table>
	</s:form>
</div>	