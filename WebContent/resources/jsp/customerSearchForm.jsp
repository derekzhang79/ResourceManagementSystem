<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_CustomerSearchFormId_div">
<div class="submenu_bg">
	<s:if test="#session.CUSTOMER_ADD == true">
		<sj:a href="setUpCustomer.action" targets="submenu_CustomerSearchFormId_div" indicator="indicatorSubMenuCustomerSearchFormId_div" cssClass="link"><s:text name="MTIAddCustomer" /></sj:a> |
	</s:if>
	<s:if test="#session.CUSTOMER_VIEW == true">
		<sj:a href="getAllCustomer.action" targets="submenu_CustomerSearchFormId_div" indicator="indicatorSubMenuCustomerSearchFormId_div" cssClass="link"><s:text name="MTIViewCustomer"/></sj:a> |
		<sj:a href="customerSearchForm.action" targets="submenu_CustomerSearchFormId_div" indicator="indicatorSubMenuCustomerSearchFormId_div" cssClass="link"><s:text name="MTISearchCustomer"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuCustomerSearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="customerSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.customer.search"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	         <tr>
	         	<td class="inputtext">
	         		<s:text name="label.header.customer.name"/>
	         	</td>
	         	<td class="employeeinputtd">
	         		<s:textfield name="cust.customerName" cssClass="employeeinput"/>
	         	</td>
	         	<td class="inputerrormessage"></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorCustomerFormSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_CustomerSearchFormId_div" indicator="indicatorCustomerFormSearchId_div"/>
					</td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>