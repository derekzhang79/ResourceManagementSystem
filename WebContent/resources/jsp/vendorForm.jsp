<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_VendorFormId_div">
<div class="submenu_bg">
	<sj:a href="setUpForInsertOrUpdateVendor.action" targets="submenu_VendorFormId_div" indicator="indicatorSubMenuVendorFormId_div" cssClass="link"><s:text name="MTIAddVendor" /></sj:a> |
	<sj:a href="getAllVendor.action" targets="submenu_VendorFormId_div" indicator="indicatorSubMenuVendorFormId_div" cssClass="link"><s:text name="MTIViewVendor"/></sj:a> |
	<sj:a href="vendorSearchForm.action" targets="submenu_VendorFormId_div" indicator="indicatorSubMenuVendorFormId_div" cssClass="link"><s:text name="MTISearchVendor"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuVendorFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateVendor">
		<table class="maintable">
	        <tr>
	        	<td align="center" ><table class="formouter">
	        <tr>
	        <tr>
                <td class="formtitle">
	        		   <s:if test="vendor==null || vendor.vendorId == null">
						 <s:text name="label.title.vendor.add"/>
					   </s:if>
					   <s:else>
						 <s:text name="label.title.vendor.edit"/>
					   </s:else>
                </td>
	        </tr>
	        <tr align="left">
	               <td class="forminner"><table class="tablealign"> 
	                 <tr>
		         		<td class="inputtext"><s:text name="label.form.fields.Vendor.taxId"/></td>
		         		<td class="employeeinputtd"><s:textfield name="vendor.taxId" cssClass="employeeinput"/></td>
		         		<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.taxId" />
	           		    </td>
		   			</tr>
		         	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.vendorName"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.vendorName" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.vendorName" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.vendorShName"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.vendorShName" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.vendorShName" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.contactPerson1"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.contactPerson1" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.contactPerson1" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.emailAddress1"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.emailAddress1" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.emailAddress1" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.contactPerson2"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.contactPerson2" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.contactPerson2" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.emailAddress2"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.emailAddress2" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.emailAddress2" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.userName"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.userName" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.userName" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.password"/></td>
		         	 	<td class="employeeinputtd"><s:password name="vendor.password" showPassword="true" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.password" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.companyName"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.companyName" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.companyName" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.address1"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.address1" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.address1" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.address2"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.address2" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.address2" />
	           		    </td>
		        	</tr>
		        	<tr>
						<td class="inputtext"><s:text name="label.form.fields.country.name" /></td>
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
						<td class="inputerrormessage"><s:fielderror fieldName="vendor.country.hcmocountryId"/></td>
					</tr>
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.state"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.state" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.state" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.city"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.city" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.city" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.zipCode"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.zipCode" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.zipCode" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.phone"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.phone" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.phone" />
	           		    </td>
		        	</tr>
		        	<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.phoneExample"></s:text></td>
					</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.extension"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.extension" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.extension" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.fax"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.fax" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.fax" />
	           		    </td>
		        	</tr>
		        	<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.phoneExample"></s:text></td>
					</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.website"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.webSite" cssClass="employeeinput"/></td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.custom1"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.custom1" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.custom1" />
	           		    </td>
		        	</tr>
		        	<tr>
		         	 	<td class="inputtext"><s:text name="label.form.fields.Vendor.custom2"/></td>
		         	 	<td class="employeeinputtd"><s:textfield name="vendor.custom2" cssClass="employeeinput"/></td>
		         	 	<td class="inputerrormessage">
	                		<s:fielderror fieldName="vendor.custom2" />
	           		    </td>
		        	</tr>
	                <s:hidden name="vendor.vendorId"/>
	        
	     	</table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorVendorFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_VendorFormId_div" indicator="indicatorVendorFormId_div"/>
					</td>
					<s:if test="vendor==null || vendor.vendorId==null">
				         <td>
		    		    	<s:url var="resetVendor" action="resetVendor"></s:url>
		    	            <sj:submit href="%{resetVendor}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_VendorFormId_div" indicator="indicatorVendorFormId_div"/>
			            </td>
			       </s:if>
			       <s:else>
	    	            <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table> 		  		 
	</s:form>
</div>