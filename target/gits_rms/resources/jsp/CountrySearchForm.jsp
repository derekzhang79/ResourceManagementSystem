<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_CountrySearchId_div">
<div class="submenu_bg">
	<s:if test="#session.COUNTRY_ADD == true">
		<sj:a href="setUpInsertOrUpdateCountry.action" targets="submenu_CountrySearchId_div" indicator="indicatorSubMenuCountrySearchId_div" cssClass="link"><s:text name="MTIAddCountry" /></sj:a> |
	</s:if>
	<s:if test="#session.COUNTRY_VIEW == true">
		<sj:a href="getAllCountry.action" targets="submenu_CountrySearchId_div" indicator="indicatorSubMenuCountrySearchId_div" cssClass="link"><s:text name="MTIViewCountry"/></sj:a> |
		<sj:a href="countrySearchForm.action" targets="submenu_CountrySearchId_div" indicator="indicatorSubMenuCountrySearchId_div" cssClass="link"><s:text name="MTISearchCountry"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuCountrySearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="countrySearchResult">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
							<s:text name="label.title.country.search" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
						<tr>
						  <td class="inputtext">
							<s:text	name="label.header.common.countryName"/>
						  </td>
						  <td class="employeeinputtd">
							<s:textfield name="cou.countryName" cssClass="employeeinput"/>
						  </td>
						  <td class="inputerrormessage"></td>
						</tr>
						<tr>
						  <td class="inputtext">
							<s:text	name="label.header.country.code"/>
						  </td>
						  <td class="employeeinputtd">
							<s:textfield name="cou.countryCode" cssClass="employeeinput"/>
						  </td>
						</tr>
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorCountrySearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	 		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_CountrySearchId_div" indicator="indicatorCountrySearchFormId_div"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
		</table>
	</s:form>
</div>