<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_LocationSearchId_div">
<div class="submenu_bg">
	<s:if test="#session.LOCATION_ADD == true">
		<sj:a href="setUpLocation.action" targets="submenu_LocationSearchId_div" indicator="indicatorSubMenuLocationSearchId_div" cssClass="link"><s:text name="MTIAddLocation" /></sj:a> |
	</s:if>
	<s:if test="#session.LOCATION_VIEW == true">
		<sj:a href="getAllLocation.action" targets="submenu_LocationSearchId_div" indicator="indicatorSubMenuLocationSearchId_div" cssClass="link"><s:text name="MTIViewLocation"/></sj:a> |
		<sj:a href="locationSearchForm.action" targets="submenu_LocationSearchId_div" indicator="indicatorSubMenuLocationSearchId_div" cssClass="link"><s:text name="MTISearchLocation"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuLocationSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="locationSearchResult">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
							<s:text name="label.title.location.search" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					<tr>
				    	<td class="inputtext"><s:text name="label.header.location.name" /></td>
						<td class="employeeinputtd"><s:textfield name="loc.locationName" cssClass="employeeinput" /></td>
						<td class="inputerrormessage"></td>
					</tr>
					<tr>
					  	<td class="inputtext"><s:text	name="label.header.country.name" /></td>
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
					</tr><!-- autocomplete text added by, R.Deepika -->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.zipCode" /></td>
						<td class="employeeinputtd"><s:textfield name="loc.zipcode" cssClass="employeeinput" /></td>
					</tr>		
					<tr>
						<td class="inputtext"><s:text name="label.header.common.city"/></td>
						<td class="employeeinputtd"><s:textfield name="loc.city" cssClass="employeeinput" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.region.name"/></td>
			            <td class="employeeinputtd"><s:textfield name="loc.region" cssClass="employeeinput" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.address1" /></td>
						<td class="employeeinputtd"><s:textfield name="loc.address1" cssClass="employeeinput" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.phone" /></td>
						<td class="employeeinputtd"><s:textfield name="loc.phone" cssClass="employeeinput"/></td>
					</tr>
					<!-- Phone text added by, R.Deepika -->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.phoneExample"></s:text></td>
					</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorLocationForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_LocationSearchId_div" indicator="indicatorLocationForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
			
		</table>
	</s:form>
</div>