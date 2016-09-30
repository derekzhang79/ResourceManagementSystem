<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_client_form_div_Id">
<div class="submenu_bg">
	<s:if test="#session.CLIENT_ADD == true">
		<s:if test="#session.CHECK_CLIENT == 'AVAILABLE'"></s:if>
		<s:elseif test="#session.CHECK_CLIENT == 'NOT_AVAILABLE'">
			<sj:a href="setUpClient.action" targets="submenu_client_form_div_Id" indicator="indicatorSubMenuClientFormId_div" cssClass="link"><s:text name="MTIAddClient" /></sj:a> |
		</s:elseif>
	</s:if>
	<s:if test="#session.CLIENT_VIEW == true">
		<sj:a href="getAllClient.action" targets="submenu_client_form_div_Id" indicator="indicatorSubMenuClientFormId_div" cssClass="link"><s:text name="MTIViewClient"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuClientFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form id="clientAddFormId" method="post" enctype="multipart/form-data">
	<table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		   <s:if test="cli==null || cli.hcmoclientId == null">
								<s:text name="label.title.client.add" />
						   </s:if> <s:else>
								<s:text name="label.title.client.edit" />
						   </s:else>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign">   		
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.client.companyName" /></td>
				<td class="employeeinputtd"><s:textfield name="cli.companyName" cssClass="employeeinput"/></td>
				<td class="inputerrormessage"><s:fielderror fieldName="cli.companyName"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.client.noOfEmp" /></td>
				<td class="employeeinputtd"><s:textfield name="cli.noOfEmp" cssClass="employeeinput"/></td>
				<%-- <td class="inputerrormessage"><s:fielderror fieldName="cli.noOfEmp"/></td> --%>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.client.noOfBranches" /></td>
				<td class="employeeinputtd"><s:textfield name="cli.noOfBranch" cssClass="employeeinput"/></td>
				<td class="inputerrormessage"><s:fielderror fieldName="cli.noOfBranch"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.country.name" /></td>
				<td class="employeeinputtd">
					<sj:autocompleter  
						list="#request.APPL_COUNTRY_LIST"
						listKey="hcmocountryId"
						listValue="countryName"
					    name="cli.country.hcmocountryId"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
				    />
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="cli.country.hcmocountryId"/></td>
			</tr>
			<tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.zipCode" /></td>
				<td class="employeeinputtd"><s:textfield id="cli.zipcode" name="cli.zipcode" cssClass="employeeinput" /></td>
				<td class="inputerrormessage"><s:fielderror fieldName="cli.zipcode"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.city" /></td>
				<td class="employeeinputtd"><s:textfield id="cli.city" name="cli.city" cssClass="employeeinput" /></td>
				<td class="inputerrormessage"><s:fielderror fieldName="cli.city"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.client.state" /></td>
				<td class="employeeinputtd"><s:textfield id="cli.state" name="cli.state" cssClass="employeeinput" /></td>
				<td class="inputerrormessage"><s:fielderror fieldName="cli.state"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.address1" /></td>
				<td class="employeeinputtd"><s:textfield name="cli.address1" cssClass="employeeinput"/></td>
				<td class="inputerrormessage"><s:fielderror fieldName="cli.address1"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.address2" /></td>
				<td class="employeeinputtd"><s:textfield name="cli.address2" cssClass="employeeinput"/></td>
				<td class="inputerrormessage"><s:fielderror fieldName="cli.address2"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.phone" /></td>
				<td class="employeeinputtd"><s:textfield name="cli.phone" cssClass="employeeinput"/></td>
				<td class="inputerrormessage"><s:fielderror fieldName="cli.phone"/></td>
			</tr>
			<tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.phoneExample"></s:text></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.client.fax" /></td>
				<td class="employeeinputtd"><s:textfield id="cli.fax" name="cli.fax" cssClass="employeeinput"/></td>
				<td class="inputerrormessage"><s:fielderror fieldName="cli.fax"/></td>
			</tr>
			<tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.fax"></s:text></td>
			</tr>
			<tr>
			<td class="inputtext"><s:text name="label.form.fields.client.taxId" /></td>
				<td class="employeeinputtd"><s:textfield name="cli.taxId" cssClass="employeeinput"/></td>
				<td class="inputerrormessage"><s:fielderror fieldName="cli.taxId"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.comments" /></td>
				<!-- textarea size has been changed : venkat -->
				<td class="employeeinputtd"><s:textarea name="cli.comments" cssClass="employeetextarea" rows="4" cols="26"/></td>
			</tr>
			<s:if test="#session.MASTER_CLIENT_USERTYPE == 'paid_user'">
				<tr>
	          		<td class="inputtext"><s:text name="label.form.fields.client.logo"/></td>
	            	<td class="employeeinputtd"><s:file name="logoName"></s:file></td>
	            	<td class="inputerrormessage"><s:fielderror fieldName="logoName"/></td>
	         	</tr>
	        </s:if>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorClientForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	  		    	<s:submit key="button.label.submit" cssClass="submitbutton117" onclick="doPostCallWithFileUpload('submenu_client_form_div_Id','insertOrUpdateClient.action','clientAddFormId');return false;"/>
				</td>
				<s:if test="cli==null || cli.hcmoclientId==null">
		    		<td>
		    		    <s:url var="resetClient" action="resetClient"></s:url>
		    	        <sj:submit href="%{resetClient}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_client_form_div_Id" indicator="indicatorClientForm"/>
					</td>
				</s:if>
				<s:else>
	    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	    </s:else>
			</tr>
			
		</table>
		<s:hidden name="cli.hcmoclientId"/>
	</s:form>
</div>