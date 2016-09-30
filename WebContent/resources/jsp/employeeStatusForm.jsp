<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EmployeeStatusFormId_div">
<div class="submenu_bg">
	<s:if test="#session.EMPLOYEESTATUS_ADD == true">
	<sj:a href="setUpForInsertOrUpdateEmpStatus.action" targets="submenu_EmployeeStatusFormId_div" indicator="indicatorSubMenuEmployeeStatusFormId_div" cssClass="link"><s:text name="MTIAddEmployeeStatus" /></sj:a> |
</s:if>
<s:if test="#session.EMPLOYEESTATUS_VIEW == true">
	<sj:a href="getAllEmployeeStatus.action" targets="submenu_EmployeeStatusFormId_div" indicator="indicatorSubMenuEmployeeStatusFormId_div" cssClass="link"><s:text name="MTIViewEmployeeStatus"/></sj:a> |
	<sj:a href="employeeStatusSearchForm.action" targets="submenu_EmployeeStatusFormId_div" indicator="indicatorSubMenuEmployeeStatusFormId_div" cssClass="link"><s:text name="MTISearchEmployeeStatus"/></sj:a>
</s:if>
</div>
<br/>
<img id="indicatorSubMenuEmployeeStatusFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateEmployeeStatus">
		<table class="maintable">
	        <tr>
	        	<td align="center" ><table class="formouter">
	        <tr>
	            <td><table class="employeeformiinertable">
	        <tr>
	                <td class="formtitle">
		        		   <s:if test="empStatus==null || empStatus.empStatusId == null">
							 <s:text name="label.title.empStatus.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.empStatus.edit"/>
						   </s:else>
	                </td>
	        </tr>
	     	<tr>
	               <td class="forminner"><table class="tablealign">     	 
	        <tr><td class="inputtext"><s:text name="label.form.fields.empStatus.name"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="empStatus.statusName" cssClass="employeeinput"/></td>
	         	        <td class="inputerrormessage">
							<s:fielderror fieldName="empStatus.statusName" />
				 		</td>
	        </tr>
	            <s:hidden name="empStatus.empStatusId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorEmployeeStatusFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeeStatusFormId_div" indicator="indicatorEmployeeStatusFormId_div"/>
					</td>
					<s:if test="empStatus==null || empStatus.empStatusId==null">
				         <td>
		    		    	<s:url var="resetEmployeeStatus" action="resetEmployeeStatus"></s:url>
		    	            <sj:submit href="%{resetEmployeeStatus}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EmployeeStatusFormId_div" indicator="indicatorEmployeeStatusFormId_div"/>
			            </td>
			       </s:if>
			       <s:else>
	    	            <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table> 		  		 
	</s:form>
</div>