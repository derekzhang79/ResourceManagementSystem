<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_LocationFormId_div">
<div class="submenu_bg">
	<s:if test="#session.LOCATION_ADD == true">
		<sj:a href="setUpLocation.action" targets="submenu_LocationFormId_div" indicator="indicatorSubMenuLocationFormId_div" cssClass="link"><s:text name="MTIAddLocation" /></sj:a> |
	</s:if>
	<s:if test="#session.LOCATION_VIEW == true">
		<sj:a href="getAllLocation.action" targets="submenu_LocationFormId_div" indicator="indicatorSubMenuLocationFormId_div" cssClass="link"><s:text name="MTIViewLocation"/></sj:a> |
		<sj:a href="locationSearchForm.action" targets="submenu_LocationFormId_div" indicator="indicatorSubMenuLocationFormId_div" cssClass="link"><s:text name="MTISearchLocation"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuLocationFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateLocation">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
	               		<s:if test="loc==null || loc.hcmolocationId == null">
							<s:text name="label.title.location.add" />
						</s:if>
						<s:else>
							<s:text name="label.title.location.edit" />
						</s:else>
	                  </td>
	                </tr>
					<tr>
                  		<td class="forminner"><table class="tablealign">
					<tr>
				  		<td class="inputtext"><s:text	name="label.form.fields.location.name" /></td>
					  	<td class="employeeinputtd"><s:textfield name="loc.locationName" cssClass="employeeinput"/></td>
					  	<td class="inputerrormessage"><s:fielderror fieldName="loc.locationName" /></td>
					</tr>
					<tr>
					  	<td class="inputtext"><s:text name="label.form.fields.country.name" /></td>
				  	  	<td class="employeeinputtd">
		  	  	  			<sj:autocompleter  
							    name="loc.country.hcmocountryId"
								list="#request.APPL_COUNTRY_LIST"
								listKey="hcmocountryId"
								listValue="countryName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
						<td class="inputerrormessage"><s:fielderror fieldName="loc.country.hcmocountryId" /></td>
					</tr>
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.common.zipCode" /></td>
						<td class="employeeinputtd"><s:textfield id="loc.zipcode" name="loc.zipcode" cssClass="employeeinput"/></td>
						<td class="inputerrormessage"><s:fielderror fieldName="loc.zipcode" /></td>
					</tr>		
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.common.city" /></td>
						<td class="employeeinputtd"><s:textfield id="loc.city" name="loc.city" cssClass="employeeinput"/></td>
						<td class="inputerrormessage"><s:fielderror fieldName="loc.city" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.region.name"/></td>
	            		<td class="employeeinputtd"><s:textfield id="loc.region" name="loc.region" cssClass="employeeinput" /></td>
	            		<td class="inputerrormessage"><s:fielderror fieldName="loc.region" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.common.address1" /></td>
						<td class="employeeinputtd"><s:textfield name="loc.address1" cssClass="employeeinput" /></td>
						<td class="inputerrormessage"><s:fielderror fieldName="loc.address1" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.common.address2" /></td>
						<td class="employeeinputtd"><s:textfield name="loc.address2" cssClass="employeeinput" /></td>
					    <td class="inputerrormessage"><s:fielderror fieldName="loc.address2" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.common.phone" /></td>
						<td class="employeeinputtd"><s:textfield name="loc.phone" cssClass="employeeinput" /></td>
						<td class="inputerrormessage"><s:fielderror fieldName="loc.phone" /></td>
					</tr>
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.phoneExample"></s:text></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.location.fax" /></td>
						<td class="employeeinputtd"><s:textfield id="loc.fax" name="loc.fax" cssClass="employeeinput" /></td>
						<td class="inputerrormessage"><s:fielderror fieldName="loc.fax" /></td>
					</tr>
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.fax"></s:text></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.common.comments" /></td>
						<!-- textarea size has been changed : venkat -->
						<td class="employeeinputtd"><s:textarea name="loc.comments" cssClass="employeetextarea" rows="4" cols="26"/></td>
					</tr>
			</table></td></tr></table></td></tr></table></td></tr></table>
			<br />
			<table align="center">
				<tr>
					<td>
						<img id="indicatorLocationFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
		   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_LocationFormId_div" indicator="indicatorLocationFormId_div"/>
					</td>
					<s:if test="loc==null || loc.hcmolocationId==null">
			    		<td>
			    		    <s:url var="resetLocation" action="resetLocation"></s:url>
			    	        <sj:submit href="%{resetLocation}" key="button.label.reset" cssClass="submitbutton117" targets="submenu_LocationFormId_div" indicator="indicatorLocationFormId_div"/>
						</td>
					</s:if>
					<s:else>
		    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
		    	    </s:else>
				</tr>
				
			</table>
			<s:hidden name="loc.hcmolocationId"/>
	</s:form>
</div>