<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_CountryForm_div">
<div class="submenu_bg">
	<s:if test="#session.COUNTRY_ADD == true">
		<sj:a href="setUpInsertOrUpdateCountry.action" targets="submenu_CountryForm_div" indicator="indicatorSubMenuCountryFormId_div" cssClass="link"><s:text name="MTIAddCountry" /></sj:a> |
	</s:if>
	<s:if test="#session.COUNTRY_VIEW == true">
		<sj:a href="getAllCountry.action" targets="submenu_CountryForm_div" indicator="indicatorSubMenuCountryFormId_div" cssClass="link"><s:text name="MTIViewCountry"/></sj:a> |
		<sj:a href="countrySearchForm.action" targets="submenu_CountryForm_div" indicator="indicatorSubMenuCountryFormId_div" cssClass="link"><s:text name="MTISearchCountry"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuCountryFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateCountry">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
	               		<s:if test="cou==null || cou.hcmocountryId == null">
							<s:text name="label.title.country.add" />
						</s:if> <s:else>
							<s:text name="label.title.country.edit" />
						</s:else>
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					<tr>
					  <td class="inputtext">
						<s:text	name="label.form.fields.country.name" />
					  </td>
					  <td class="employeeinputtd">
						<s:textfield name="cou.countryName" cssClass="employeeinput"/>
					  </td>
					  <td class="inputerrormessage">
	                	<s:fielderror fieldName="cou.countryName" />
	           		  </td>
					</tr>
					<tr>
					  <td class="inputtext">
						<s:text	name="label.form.fields.country.code" />
					  </td>
					  <td class="employeeinputtd">
						<s:textfield name="cou.countryCode" cssClass="employeeinput"/>
					  </td>
					  <td class="inputerrormessage">
	                	<s:fielderror fieldName="cou.countryCode" />
	           		  </td>
					</tr>
					<tr>
					  <td class="inputtext"><s:text name="label.header.common.description" /></td>
					  <!-- textarea size has been changed : venkat -->
					  <td class="employeeinputtd"><s:textarea name="cou.description" cssClass="employeetextarea" rows="4" cols="26" cssStyle="{width:200px;height:auto;}"/></td>
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
					<img id="indicatorCountryFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	 		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_CountryForm_div" indicator="indicatorCountryFormId_div"/>
				</td>
				<s:if test="cou==null || cou.hcmocountryId==null">
				   <td>
		    		    <s:url var="resetCountry" action="resetCountry"></s:url>
		    	        <sj:submit href="%{resetCountry}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_CountryForm_div" indicator="indicatorCountryFormId_div"/>
			       </td>
			   </s:if>
			   <s:else>
				    <td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			  </s:else>
			</tr>
		</table>
		<s:hidden name="cou.hcmocountryId"/>
	</s:form>
</div>