<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.EmpSpaceVO"%>


<div id="submenu_EmployeeSpaceShare_div">
	<br/><img id="indicatorSubMenuEmployeeSpaceShareId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
						
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form id="empSpaceShareFormId" action="makeShared">
	<table class="maintable">
     <tr>
       <td align="center" ><table class="formouter">
     <tr>
        <td class="employeedisplaytd"><table class="employeeformiinertable">
      <tr>
         <td class="formtitle">
				<s:text name="label.title.empSpace.edit" />
		  </td>
       </tr>
       <tr>
            <td class="forminner"><table class="tablealign">
		<tr>
			<td class="inputtext">
				<s:text	name="label.title.empSpace.title" />
			</td>
			<td class="employeedisplaytd"><s:textfield name="empSpace.sharedFileTitle" readonly="true"/></td>
		    <td class="inputerrormessage"></td>
		</tr>
		<tr>
			<td class="inputtext">
				<s:text	name="label.title.empSpace.description" />
			</td>
			<td class="employeedisplaytd"><s:textfield name="empSpace.sharedFileDesc" readonly="true"/></td>
		</tr>
		<tr>
			<td class="inputtext">
				<s:text	name="label.title.empSpace.fileView" />
			</td>
			<td class="employeedisplaytd">
				<s:url var="empSpaceFileDownload" action="empSpaceFileDownload">
		      		   <s:param name="empSpace.hcmoEmpSpaceId" value="empSpace.hcmoEmpSpaceId"/>
		   		</s:url>
				<s:a href="%{empSpaceFileDownload}"><s:property value="empSpace.spaceAttachFileName"/></s:a>
			</td>
		</tr>
        <tr>
            <td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
             <td class="employeeinputtd">
				<s:select 
					list="#request.APPL_EMPLOYEE_LIST"
					listKey="employeeId"
					listValue="empFirstName"
				    name="empSpace.empIdObjList.employeeId"
				    cssClass="employeeselect"
				    multiple="true"
				    size="3"
				    />
			</td>
         </tr>
		<s:hidden name="empSpace.hcmoEmpSpaceId"/>
	</table></td></tr></table></td></tr></table></td></tr></table>
 	<table align="center"> 
    	     <tr>
    		    <td>
    		    	<img id="indicatorSubMenuEmployeeSpaceShare" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
    		    	<sj:submit formIds="empSpaceShareFormId" targets="submenu_EmployeeSpaceShare_div" indicator="indicatorSubMenuEmployeeSpaceShare" key="button.label.submit" cssClass="submitbutton117"/>
    		    </td>
    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
    	        <td>
    		    	<img id="indicatorEmpSpaceShare" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					<s:url var="backEmpSpaceShareTabButton" action="backEmpSpaceShareTabButton"></s:url>
	   			 	
	   			 	<sj:submit formIds="empSpaceShareFormId" href="%{backEmpSpaceShareTabButton}"  key="Back" cssClass="submitbutton117" targets="submenu_EmployeeSpaceShare_div" indicator="indicatorEmpSpaceShare"/>
   		    	</td>
    		 </tr>
    </table>
    </s:form>
</div>  