<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.CustomerVO"%>

<div id="submenu_customer_searchList_div_id">
<div class="submenu_bg">
	<s:if test="#session.CUSTOMER_ADD == true">
		<sj:a href="setUpCustomer.action" targets="submenu_customer_searchList_div_id" indicator="indicatorSubMenuCustomerSearchResId_div" cssClass="link"><s:text name="MTIAddCustomer" /></sj:a> |
	</s:if>
	<s:if test="#session.CUSTOMER_VIEW == true">
		<sj:a href="getAllCustomer.action" targets="submenu_customer_searchList_div_id" indicator="indicatorSubMenuCustomerSearchResId_div" cssClass="link"><s:text name="MTIViewCustomer"/></sj:a> |
		<sj:a href="customerSearchForm.action" targets="submenu_customer_searchList_div_id" indicator="indicatorSubMenuCustomerSearchResId_div" cssClass="link"><s:text name="MTISearchCustomer"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuCustomerSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.customer.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.customer.name" var="HCustomerName"></s:text>
	  <s:text name="label.header.common.phone" var="HCustomerPhone"></s:text>
	  <s:text name="label.header.common.fax" var="HCustomerFax"></s:text>
	  <s:text name="label.header.common.address1" var="HCustomerAddress1"></s:text>
	  <s:text name="label.header.common.address2" var="HCustomerAddress2"></s:text>
	  <s:text name="label.header.customer.contactName" var="HCustomerContactName"></s:text>
	  <s:text name="label.header.common.email" var="HCustomerEmail"></s:text>
	  <s:text name="label.header.common.description" var="HCustomerDescription"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_customerSearchList_div_id">
		  <display:table class="tableborder" id="customerListId" name="custList" pagesize="${NO_OF_RECORDS}" requestURI="customerSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sCustomerId = ((CustomerVO)pageContext.getAttribute("customerListId")).getCustomerId().toString();
		        	request.setAttribute("CustomerId", sCustomerId);   		
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="customerName" title="${HCustomerName}" sortable="true" headerClass="sortable"/>
		    <display:column property="phoneNumber" title="${HCustomerPhone}" sortable="true" headerClass="sortable"/>
		    <display:column property="fax" title="${HCustomerFax}" sortable="true" headerClass="sortable"/>
		    <display:column property="addr1" title="${HCustomerAddress1}" sortable="true" headerClass="sortable"/>
		    <display:column property="addr2" title="${HCustomerAddress2}" sortable="true" headerClass="sortable"/>
		    <display:column property="contactName" title="${HCustomerContactName}" sortable="true" headerClass="sortable"/>
		    <display:column property="email" title="${HCustomerEmail}" sortable="true" headerClass="sortable"/>
		    <display:column property="desc" title="${HCustomerDescription}" sortable="true" headerClass="sortable" maxLength="10"/>
		    <s:if test="#session.CUSTOMER_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewCustomer" action="customerView">
						<s:param name="cust.customerId" value="#request.CustomerId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_customer_searchList_div_id','%{listViewCustomer}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.CUSTOMER_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpCustomer" action="setUpCustomer">
						<s:param name="cust.customerId" value="#request.CustomerId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_customer_searchList_div_id','%{listSetUpCustomer}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.CUSTOMER_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteCustomer" action="deleteCustomer">
						<s:param name="cust.customerId" value="#request.CustomerId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_customer_searchList_div_id','%{listDeleteCustomer}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="Customer.csv"/>
			 <display:setProperty name="export.excel.filename" value="Customer.xls"/>
			 <display:setProperty name="export.xml.filename" value="Customer.xml"/>
		  </display:table>
	  </div>
</div>  
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_customerSearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>         