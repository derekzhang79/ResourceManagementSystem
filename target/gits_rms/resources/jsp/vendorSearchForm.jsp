<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_VendorSearchId_div">
<div class="submenu_bg">
	<sj:a href="setUpForInsertOrUpdateVendor.action" targets="submenu_VendorSearchId_div" indicator="indicatorSubMenuVendorSearchId_div" cssClass="link"><s:text name="MTIAddVendor" /></sj:a> |
	<sj:a href="getAllVendor.action" targets="submenu_VendorSearchId_div" indicator="indicatorSubMenuVendorSearchId_div" cssClass="link"><s:text name="MTIViewVendor"/></sj:a> |
	<sj:a href="vendorSearchForm.action" targets="submenu_VendorSearchId_div" indicator="indicatorSubMenuVendorSearchId_div" cssClass="link"><s:text name="MTISearchVendor"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuVendorSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="vendorSearchResult">
		<table class="maintable">
	        <tr>
	        	<td align="center" ><table class="formouter">
	        <tr>
	                <td class="formtitle" align="center">
						 <s:text name="label.title.vendor.search"/>
	                </td>
	        </tr>
	     	<tr>
	               <td class="forminner"><table align="center">     	 
	        <tr>
		         		<td class="inputtextforvendor"><s:text name="label.header.vendor.taxId"/></td>
		         		<td class="employeeinputtd"><s:textfield name="vendor.taxId" cssClass="employeeinput"/></td>
		    </tr>
		    <tr>
		         		<td class="inputtextforvendor"><s:text name="label.header.vendor.vendorName"/></td>
		         		<td class="employeeinputtd"><s:textfield name="vendor.vendorName" cssClass="employeeinput"/></td>
		    </tr>
		    <tr>
		         		<td class="inputtextforvendor"><s:text name="label.header.vendor.contactPerson1"/></td>
		         		<td class="employeeinputtd"><s:textfield name="vendor.contactPerson1" cssClass="employeeinput"/></td>
		    </tr>
		    <tr>
		         		<td class="inputtextforvendor"><s:text name="label.header.vendor.emailAddress1"/></td>
		         		<td class="employeeinputtd"><s:textfield name="vendor.emailAddress1" cssClass="employeeinput"/></td>
		    </tr>
		    <tr>
		         		<td class="inputtextforvendor"><s:text name="label.header.vendor.contactPerson2"/></td>
		         		<td class="employeeinputtd"><s:textfield name="vendor.contactPerson2" cssClass="employeeinput"/></td>
		    </tr>
		    <tr>
		         		<td class="inputtextforvendor"><s:text name="label.header.vendor.emailAddress2"/></td>
		         		<td class="employeeinputtd"><s:textfield name="vendor.emailAddress2" cssClass="employeeinput"/></td>
		    </tr>
<!--		    <tr>-->
<!--		         		<td class="inputtext"><s:text name="label.header.vendor.userName"/></td>-->
<!--		         		<td class="employeeinputtd"><s:textfield name="vendor.userName" cssClass="employeeinput"/></td>-->
<!--		    </tr>-->
<!--		    <tr>-->
<!--		         		<td class="inputtext"><s:text name="label.header.vendor.password"/></td>-->
<!--		         		<td class="employeeinputtd"><s:textfield name="vendor.password" cssClass="employeeinput"/></td>-->
<!--		    </tr>-->
		    <tr>
		         		<td class="inputtextforvendor"><s:text name="label.header.vendor.companyName"/></td>
		         		<td class="employeeinputtd"><s:textfield name="vendor.companyName" cssClass="employeeinput"/></td>
		    </tr>
		    
		    <tr>
						<td class="inputtextforvendor"><s:text name="label.header.common.countryName" /></td>
						<td class="employeeinputtd">
							<sj:autocompleter  
							    name="vendor.country.hcmocountryId"
								list="#request.APPL_COUNTRY_LIST"
								listKey="hcmocountryId"
								listValue="countryName"
							    selectBox="true"
							    selectBoxIcon="true"
							    cssClass="employeeselect"
					    	/>
						</td>
		    </tr>
		    <tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>
		    
	    </table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr><td width="100px"></td><td></td>
	    		    <td>
						<img id="indicatorVendorForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_VendorSearchId_div" indicator="indicatorVendorForm"/>
					</td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
	</s:form>
</div>