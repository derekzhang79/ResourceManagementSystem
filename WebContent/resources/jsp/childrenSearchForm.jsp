<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_ChildrenEmpSearchFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.CHILDREN_ADD == true">
			<sj:a href="setUpChildren.action" targets="submenu_ChildrenEmpSearchFormId_div" indicator="indicatorSubMenuChildrenEmpSearchFormId_div" cssClass="link"><s:text name="MTIAddChildren" /></sj:a> |
		</s:if>
		<s:if test="#session.CHILDREN_VIEW == true">
			<sj:a href="getAllChildren.action" targets="submenu_ChildrenEmpSearchFormId_div" indicator="indicatorSubMenuChildrenEmpSearchFormId_div" cssClass="link"><s:text name="MTIViewChildren"/></sj:a> |
			<sj:a href="childrenSearchForm.action" targets="submenu_ChildrenEmpSearchFormId_div" indicator="indicatorSubMenuChildrenEmpSearchFormId_div" cssClass="link"><s:text name="MTISearchChildren"/></sj:a>
		</s:if>
	</div>
	<br/>
	<img id="indicatorSubMenuChildrenEmpSearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="childrenSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
                <td class="formtitle">
					 <s:text name="label.title.children.search"/>
                </td>
	         </tr>
	         <tr>
               	<td class="forminner"><table class="tablealign">    
	     	 <tr>
	            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
	            <td class="employeeinputtd">
	            	<sj:autocompleter  
					    name="child.empIdObj.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
			    	/>
				</td>
         	</tr><!-- Autocomplete text added by,R.Deepika -->
         	<tr>
					<td></td>
					<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
				</tr>
         	<tr>
         		<td class="inputtext"><s:text name="label.header.children.name"/></td>
	         	<td class="employeeinputtd"><s:textfield name="child.childName" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage"></td>	
         	</tr>
	   	</table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	   	     <tr>
	   		    <td>
					<img id="indicatorChildrenForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_ChildrenEmpSearchFormId_div" indicator="indicatorChildrenForm"/>
	   		    </td>
	   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	   		 </tr>
	    </table> 		  		 
   	</s:form>
</div>