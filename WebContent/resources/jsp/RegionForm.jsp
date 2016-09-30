<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_RegionFormId_div">
<div class="submenu_bg">
	<s:if test="#session.REGION_ADD == true">
		<sj:a href="setUpInsertOrUpdateRegion.action" targets="submenu_RegionFormId_div" indicator="indicatorSubMenuRegionFormId_div" cssClass="link"><s:text name="MTIAddRegion" /></sj:a> |
	</s:if>
	<s:if test="#session.REGION_VIEW == true">
		<sj:a href="getAllRegion.action" targets="submenu_RegionFormId_div" indicator="indicatorSubMenuRegionFormId_div" cssClass="link"><s:text name="MTIViewRegion"/></sj:a> |
		<sj:a href="regionSearchForm.action" targets="submenu_RegionFormId_div" indicator="indicatorSubMenuRegionFormId_div" cssClass="link"><s:text name="MTISearchRegion"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuRegionFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateRegion">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
	               		<s:if test="reg==null || reg.hcmoregionId == null">
							<s:text name="label.title.region.add" />
						</s:if> <s:else>
							<s:text name="label.title.region.edit" />
						</s:else>
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					
					<tr>
						 <td class="inputtext"><s:text	name="label.form.fields.region.name" /></td>
						 <td class="employeeinputtd"><s:textfield name="reg.name" cssClass="employeeinput"/></td>
						 <td class="inputerrormessage"><s:fielderror fieldName="reg.name" /></td>
					</tr>
					<tr>
						 <td class="inputtext"><s:text name="label.form.fields.country.name"/></td>
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
		          		<td class="inputerrormessage"><s:fielderror fieldName="reg.country.hcmocountryId" /></td>
		          	</tr>
		          	<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.common.zipCode" /></td>
				  	 	<td class="employeeinputtd"><s:textfield name="reg.zipCode" cssClass="employeeinput"/></td>
						<td class="inputerrormessage"><s:fielderror fieldName="reg.zipCode" /></td>
					</tr>
					<tr>
					 	<td class="inputtext"><s:text name="label.form.fields.common.city" /></td>
					 	<td class="employeeinputtd"><s:textfield name="reg.city" cssClass="employeeinput"/></td>
					 	<td class="inputerrormessage"><s:fielderror fieldName="reg.city" /></td>
					</tr>
					<tr>
					 	<td class="inputtext"><s:text name="label.form.fields.region.countyField" /></td>
					 	<td class="employeeinputtd"><s:textfield name="reg.countyField" cssClass="employeeinput"/></td>
					 	<td class="inputerrormessage"><s:fielderror fieldName="reg.countyField"/></td>
					</tr>
					<tr>
					 	<td class="inputtext"><s:text name="label.form.fields.region.regionCode" /></td>
					 	<td class="employeeinputtd"><s:textfield name="reg.regionCode" cssClass="employeeinput"/></td>
					 	<td class="inputerrormessage"><s:fielderror fieldName="reg.regionCode" /></td>
					</tr>
					<tr>
					 	<td class="inputtext"><s:text name="label.form.fields.region.areaCode" /></td>
					 	<td class="employeeinputtd"><s:textfield name="reg.areaCode" cssClass="employeeinput"/></td>
					 	<td class="inputerrormessage"><s:fielderror fieldName="reg.areaCode" /></td>
					</tr>
					<tr>
					 	<td class="inputtext"><s:text name="label.form.fields.region.timeZone" /></td>
					 	<td class="employeeinputtd"><s:textfield name="reg.timeZone" cssClass="employeeinput"/></td>
					 	<td class="inputerrormessage"><s:fielderror fieldName="reg.timeZone" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.region.latitude" /></td>
						<td class="employeeinputtd"><s:textfield name="reg.latitude" cssClass="employeeinput"/></td>
					    <td class="inputerrormessage"><s:fielderror fieldName="reg.latitude" /></td>
					</tr>
					<tr>
						<td class="inputtext"><s:text name="label.form.fields.region.longitude" /></td>
						<td class="employeeinputtd"><s:textfield name="reg.longitude" cssClass="employeeinput" /></td>
						<td class="inputerrormessage"><s:fielderror fieldName="reg.longitude" /></td>
					</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorRegionFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_RegionFormId_div" indicator="indicatorRegionFormId_div"/>
				</td>
				<s:if test="reg==null || reg.hcmoregionId==null">
		    		<td>
		    		    <s:url var="resetRegion" action="resetRegion"></s:url>
		    	        <sj:submit href="%{resetRegion}" key="button.label.reset" cssClass="submitbutton117" targets="submenu_RegionFormId_div" indicator="indicatorRegionFormId_div"/>
					</td>
				</s:if>
				<s:else>
	    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	    </s:else>
			</tr>
			
		</table>
		<s:hidden name="reg.hcmoregionId"/>
	</s:form>
</div>