<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_RegionSearchId_div">
<div class="submenu_bg">
	<s:if test="#session.REGION_ADD == true">
		<sj:a href="setUpInsertOrUpdateRegion.action" targets="submenu_RegionSearchId_div" indicator="indicatorSubMenuRegionSearchId_div" cssClass="link"><s:text name="MTIAddRegion" /></sj:a> |
	</s:if>
	<s:if test="#session.REGION_VIEW == true">
		<sj:a href="getAllRegion.action" targets="submenu_RegionSearchId_div" indicator="indicatorSubMenuRegionSearchId_div" cssClass="link"><s:text name="MTIViewRegion"/></sj:a> |
		<sj:a href="regionSearchForm.action" targets="submenu_RegionSearchId_div" indicator="indicatorSubMenuRegionSearchId_div" cssClass="link"><s:text name="MTISearchRegion"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuRegionSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="regionSearchResult">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
							<s:text name="label.title.region.search" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					
					<tr>
						<td class="inputtext"><s:text name="label.header.region.name" /></td>
						<td class="employeeinputtd"><s:textfield name="reg.name" cssClass="employeeinput"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.region.country"/></td>
		           		<td class="employeeinputtd">
		           			<sj:autocompleter  
							    name="reg.country.hcmocountryId"
								list="#request.APPL_COUNTRY_LIST"
								listKey="hcmocountryId"
								listValue="countryName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
		           		</td>
	          	    </tr><!-- country text added by, R.Deepika -->
	          	    <tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.zipCode" /></td>
					  	<td class="employeeinputtd"><s:textfield name="reg.zipCode" cssClass="employeeinput"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.common.city" /></td>
						<td class="employeeinputtd"><s:textfield name="reg.city" cssClass="employeeinput" /></td>
						<td class="inputerrormessage"><s:fielderror fieldName="reg.city" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.region.regionCode" /></td>
						<td class="employeeinputtd"><s:textfield name="reg.regionCode" cssClass="employeeinput"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.region.areaCode" /></td>
						<td class="employeeinputtd"><s:textfield name="reg.areaCode" cssClass="employeeinput"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.region.timeZone" /></td>
						<td class="employeeinputtd"><s:textfield name="reg.timeZone" cssClass="employeeinput"/></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.header.region.latitude" /></td>
						<td class="employeeinputtd"><s:textfield name="reg.latitude" cssClass="employeeinput"/></td>
				  	</tr>
				  	<tr>
				  		<td class="inputtext"><s:text name="label.header.region.longitude" /></td>
						<td class="employeeinputtd"><s:textfield name="reg.longitude" cssClass="employeeinput"/></td>
				 	</tr>
		</table></td></tr></table></td></tr></table></td></tr></table><br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorRegionForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_RegionSearchId_div" indicator="indicatorRegionForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
			
		</table>
	</s:form>
	</div>