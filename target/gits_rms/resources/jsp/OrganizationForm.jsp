<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_OrganizationFormId_div">
<div class="submenu_bg">
	<s:if test="#session.ORGANIZATION_ADD == true">
		<sj:a href="setUpOrganization.action" targets="submenu_OrganizationFormId_div" indicator="indicatorSubMenuOrganizationFormId_div" cssClass="link"><s:text name="MTIAddOrganization" /></sj:a> |
	</s:if>
	<s:if test="#session.ORGANIZATION_VIEW == true">
		<sj:a href="getAllOrganization.action" targets="submenu_OrganizationFormId_div" indicator="indicatorSubMenuOrganizationFormId_div" cssClass="link"><s:text name="MTIViewOrganization"/></sj:a> |
		<sj:a href="organizationSearchForm.action" targets="submenu_OrganizationFormId_div" indicator="indicatorSubMenuOrganizationFormId_div" cssClass="link"><s:text name="MTISearchOrganization"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuOrganizationFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<s:if test="org==null || org.orgId == null">
	  <s:if test="#session.ORGANIZATIONTYPE_ADD == true &&  test=#session.LOCATION_ADD == true">
	     <s:div cssClass="helpinformationmessage">
	    	<s:text name="label.header.org.msg.orgtype.loc" />
		 </s:div>
	   </s:if>
	</s:if>
	
	<s:form action="insertOrUpdateOrganization">
		<table class="maintable">
	      <tr>
	        <td align="center"><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
              <tr>
                <td class="formtitle">
	               	<s:if test="org==null || org.orgId == null">
						<s:text name="label.title.organization.add" />
					</s:if>
					<s:else>
						<s:text name="label.title.organization.edit" />
					</s:else>
                 </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					<tr>
					  <td class="inputtext">
						<s:text	name="label.form.fields.org.name" />
				 	 </td>
					 <td class="employeeinputtd">
						<s:textfield name="org.orgName" cssClass="employeeinput"/>
					</td>
					 <td class="inputerrormessage">
	                	<s:fielderror fieldName="org.orgName" />
	           		  </td>
				  </tr>
				  <tr>
					<td class="inputtext">
						<s:text	name="label.form.fields.org.parentOrgId" />
					</td>
					<td class="employeeinputtd">
						<s:select 
							headerKey=""
							headerValue="-- Please Select --"
							list="#request.APPL_ORGANIZATION_LIST"
							listKey="orgId"
							listValue="orgName"
					    	name="org.parentOrgId"
					    	cssClass="employeeselect"
					   	 />
					</td>
			
				</tr>
				<tr>
					<td class="inputtext">
						<s:text name="label.form.fields.location.name"/></td>
					<td class="employeeinputtd">
						<s:select 
							headerKey=""
							headerValue="-- Please Select --"
							list="#request.APPL_LOCATION_LIST"
							listKey="hcmolocationId"
							listValue="locationName"
					   	    name="org.location.hcmolocationId"
					    	cssClass="employeeselect" 
					  	  />
					</td>
					<td class="inputerrormessage">
	                	<s:fielderror fieldName="org.location.hcmolocationId" />
	           		  </td>
	           
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.orgType.name"/></td>
					<td class="employeeinputtd">
						<s:select 
							headerKey=""
							headerValue="-- Please Select --"
							list="#request.APPL_ORGANIZATIONTYPE_LIST"
							listKey="hcmoorgTypeId"
							listValue="orgTypeName"
					   		name="org.orgtype.hcmoorgTypeId"
					    	cssClass="employeeselect"  
					    	/>
					</td>
					<td class="inputerrormessage">
	                	<s:fielderror fieldName="org.orgtype.hcmoorgTypeId" />
	           		  </td>
				</tr>
				<tr>
					<td class="inputtext"><s:text name="label.form.fields.common.description" /></td>
					<!-- text area size has changed:venkat -->
					<td class="employeeinputtd"><s:textarea name="org.description" cssClass="employeetextarea" rows="4" cols="26"/></td>
				</tr>
			
		</table>
		</td>
		</tr>
		</table>
		</td>
		</tr>
		</table>
		</td>
		</tr>
		</table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorOrganizationFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_OrganizationFormId_div" indicator="indicatorOrganizationFormId_div"/>
				</td>
				<s:if test="org==null || org.orgId==null">
		    		<td>
		    		    <s:url var="resetOrganization" action="resetOrganization"></s:url>
		    	        <sj:submit href="%{resetOrganization}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_OrganizationFormId_div" indicator="indicatorOrganizationFormId_div"/>
					</td>
				</s:if>
				<s:else>
	    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	    </s:else>
			</tr>
			
		</table>
		<s:hidden name="org.orgId"/>
	</s:form>
</div>