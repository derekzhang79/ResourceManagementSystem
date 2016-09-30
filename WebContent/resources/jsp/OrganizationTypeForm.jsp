<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_OrganizationTypeFormId_div">
<div class="submenu_bg">
	<s:if test="#session.ORGANIZATIONTYPE_ADD == true">
		<sj:a href="setUpOrganizationType.action" targets="submenu_OrganizationTypeFormId_div" indicator="indicatorSubMenuOrganizationTypeFormId_div" cssClass="link"><s:text name="MTIAddOrganizationType" /></sj:a> |
	</s:if>
	<s:if test="#session.ORGANIZATIONTYPE_VIEW == true">
		<sj:a href="getAllOrganizationType.action" targets="submenu_OrganizationTypeFormId_div" indicator="indicatorSubMenuOrganizationTypeFormId_div" cssClass="link"><s:text name="MTIViewOrganizationType"/></sj:a> |
		<sj:a href="organizationTypeSearchForm.action" targets="submenu_OrganizationTypeFormId_div" indicator="indicatorSubMenuOrganizationTypeFormId_div" cssClass="link"><s:text name="MTISearchOrganizationType"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuOrganizationTypeFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<s:form action="insertOrUpdateOrganizationType">
		<table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		      <s:if test="orgtype==null || orgtype.hcmoorgTypeId == null">
								<s:text name="label.title.orgType.add" />
							</s:if> <s:else>
								<s:text name="label.title.orgType.edit" />
							</s:else>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
			<tr>
				<td class="inputtext"><s:text	name="label.form.fields.orgType.name" /></td>
				<td class="employeeinputtd"><s:textfield name="orgtype.orgTypeName" cssClass="employeeinput"/></td>
				<td class="inputerrormessage">
						<s:fielderror fieldName="orgtype.orgTypeName"/>
				</td>
			</tr>
				<tr><td class="inputtext"><s:text name="label.form.fields.common.description" /></td>
				<!-- text area size has changed:venkat -->
				<td class="employeeinputtd"><s:textarea name="orgtype.description" cssClass="employeetextarea" rows="4" cols="26"/></td>
			</tr>
			
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorOrganizationTypeFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_OrganizationTypeFormId_div" indicator="indicatorOrganizationTypeFormId_div"/>
				</td>
				<s:if test="orgtype==null || orgtype.hcmoorgTypeId==null">
		    		<td>
		    		    <s:url var="resetOrganizationType" action="resetOrganizationType"></s:url>
		    	        <sj:submit href="%{resetOrganizationType}" key="button.label.reset" cssClass="submitbutton117" targets="submenu_OrganizationTypeFormId_div" indicator="indicatorOrganizationTypeFormId_div"/>
					</td>
				</s:if>
				<s:else>
	    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	    </s:else>
			</tr>
			
		</table>
		<s:hidden name="orgtype.hcmoorgTypeId"/>
	</s:form>
</div>