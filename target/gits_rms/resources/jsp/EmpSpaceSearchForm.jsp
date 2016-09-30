<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EmployeeSpaceSearchForm_div">
	<br/>
	<img id="indicatorSubMenuEmployeeSpaceSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="empspaceSearchResult">
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						 <s:text name="label.title.empSpace.search"/>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	         <tr>
	         	<td class="inputtext"><s:text name="label.title.empSpace.title" /></td>
	         	<td class="employeeinputtd">
	         		<s:textfield name="empSpace.sharedFileTitle" cssClass="employeeinput" ></s:textfield>
	         	</td>
	         	<td class="inputerrormessage"></td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.title.empSpace.description" /></td>
	         	<td class="employeeinputtd">
	         		<s:textarea name="empSpace.sharedFileDesc" cssClass="employeetextarea" rows="4" cols="26"/>
	         	</td>
	         	<td></td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.title.empSpace.fileType" /></td>
	         	<td class="employeeinputtd">
					<s:url var="getEmpSpaceFileTypeJSONList" action="getEmpSpaceFileTypeJSONList"/>   
					<sj:select
						href="%{getEmpSpaceFileTypeJSONList}"
						list="empSpaceFileTypeList"
						name="empSpace.type"
					    dataType="json"
						indicator="empSpaceMSGTypeIndicator"      
					    cssClass="employeeselect" 
					/>
					<img id="empSpaceMSGTypeIndicator" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
         		</td>
         		<td></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    		 <s:hidden name="empSpace.hcmoEmpSpaceId"/>
	    <table align="center"> 
	   	     <tr>
	   		    <td>
					<img id="indicatorEmpSpaceSearchForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeeSpaceSearchForm_div" indicator="indicatorEmpSpaceSearchForm"/>
	   		   	</td>
	   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	   		 </tr>
	    </table> 		  		 
	    </s:form>
   <br/><br/><br/>
</div>