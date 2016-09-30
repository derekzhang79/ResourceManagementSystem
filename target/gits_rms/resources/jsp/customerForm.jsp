<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_CustomerForm_div">
<div class="submenu_bg">
	<s:if test="#session.CUSTOMER_ADD == true">
		<sj:a href="setUpCustomer.action" targets="submenu_CustomerForm_div" indicator="indicatorSubMenuCustomerFormId_div" cssClass="link"><s:text name="MTIAddCustomer" /></sj:a> |
	</s:if>
	<s:if test="#session.CUSTOMER_VIEW == true">
		<sj:a href="getAllCustomer.action" targets="submenu_CustomerForm_div" indicator="indicatorSubMenuCustomerFormId_div" cssClass="link"><s:text name="MTIViewCustomer"/></sj:a> |
		<sj:a href="customerSearchForm.action" targets="submenu_CustomerForm_div" indicator="indicatorSubMenuCustomerFormId_div" cssClass="link"><s:text name="MTISearchCustomer"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuCustomerFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateCustomer">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		      <s:if test="cust==null || cust.customerId == null">
								 <s:text name="label.title.customer.add"/>
							   </s:if>
							   <s:else>
								 <s:text name="label.title.customer.edit"/>
							   </s:else>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	   
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.customer.name"/></td>
	         	<td class="employeeinputtd"><s:textfield name="cust.customerName" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage">
						<s:fielderror fieldName="cust.customerName"/>
				</td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.customer.contactName"/></td>
	         	<td class="employeeinputtd"><s:textfield name="cust.contactName" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage">
						<s:fielderror fieldName="cust.contactName"/>
				</td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.customer.email"/></td>
	         	<td class="employeeinputtd"><s:textfield name="cust.email" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage">
						<s:fielderror fieldName="cust.email"/>
				</td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.customer.address1"/></td>
	         	<td class="employeeinputtd"><s:textfield name="cust.addr1" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage">
						<s:fielderror fieldName="cust.addr1"/>
				</td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.customer.address2"/></td>
	         	<td class="employeeinputtd"><s:textfield name="cust.addr2" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage">
						<s:fielderror fieldName="cust.addr2"/>
				</td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.customer.phone"/></td>
	         	<td class="employeeinputtd"><s:textfield name="cust.phoneNumber" cssClass="employeeinput"/>
	         	        
	         	</td>
	         	<td class="inputerrormessage">
						<s:fielderror fieldName="cust.phoneNumber"/>
				</td>
	         </tr>
	         <tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.fax"></s:text></td>
					</tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.customer.fax"/></td>
	         	<td class="employeeinputtd"><s:textfield name="cust.fax"  cssClass="employeeinput"/>
	         	      <!-- phone text alligned and added by, R.Deepika  <s:text name="label.common.message.fax"/>-->
	         	</td>
	         	<td class="inputerrormessage">
						<s:fielderror fieldName="cust.fax"/>
				</td>
	         </tr>
	         <tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.fax"></s:text></td>
					</tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.common.description"/></td>
	         	<!-- textarea size has been changed : venkat -->
	         	<td class="employeeinputtd"><s:textarea name="cust.desc" cssClass="employeetextarea" rows="4" cols="26"/></td>
	         </tr>
	            <s:hidden name="cust.customerId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorCustomerFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_CustomerForm_div" indicator="indicatorCustomerFormId_div"/>
					</td>
					<s:if test="cust==null || cust.customerId==null">
				        <td>
		    		    	 <s:url var="resetCustomer" action="resetCustomer"></s:url>
		    	             <sj:submit href="%{resetCustomer}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_CustomerForm_div" indicator="indicatorCustomerFormId_div"/>
			           </td>
			        </s:if>
			        <s:else>
	    	           <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>