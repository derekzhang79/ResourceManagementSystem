
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_DepartmentFormId_div">
<div class="submenu_bg">
	<s:if test="#session.DEPARTMENT_ADD == true">
		<sj:a href="setUpDepartment.action" targets="submenu_DepartmentFormId_div" indicator="indicatorSubMenuDepartmentFormId_div" cssClass="link"><s:text name="MTIAddDepartment" /></sj:a> |
	</s:if>
	<s:if test="#session.DEPARTMENT_VIEW == true">
		<sj:a href="getAllDepartment.action" targets="submenu_DepartmentFormId_div" indicator="indicatorSubMenuDepartmentFormId_div" cssClass="link"><s:text name="MTIViewDepartment"/></sj:a> |
		<sj:a href="deptSearchForm.action" targets="submenu_DepartmentFormId_div" indicator="indicatorSubMenuDepartmentFormId_div" cssClass="link"><s:text name="MTISearchDepartment"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuDepartmentFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateDepartment">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		   <s:if test="dept==null || dept.hcmoDepartmentId == null">
							 <s:text name="label.title.department.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.department.edit"/>
						   </s:else>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	     	 
	         <tr><td class="inputtext"><s:text name="label.form.fields.department.name"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="dept.deptName" cssClass="employeeinput"/></td>
	         	        <td class="inputerrormessage">
							<s:fielderror fieldName="dept.deptName" />
				 		</td>
	         </tr>
	            <s:hidden name="dept.hcmoDepartmentId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorDepartmentFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_DepartmentFormId_div" indicator="indicatorDepartmentFormId_div"/>
	    		    </td>
	    		    <s:if test="dept==null || dept.hcmoDepartmentId==null">
				        <td>
		    		    	<s:url var="resetDepartment" action="resetDepartment"></s:url>
		    	            <sj:submit href="%{resetDepartment}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_DepartmentFormId_div" indicator="indicatorDepartmentFormId_div"/>
			           </td>
			       </s:if>
			       <s:else>
	    	           <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	       </s:else>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>