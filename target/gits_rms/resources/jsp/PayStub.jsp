<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.PayStubVO"%>

<div id="submen_paystub_list_div_id">
	<div class="submenu_bg">
		<s:if test="#session.PAYSTUB_ADD == true">
			<sj:a href="setUpForInsertOrUpdatePayStub.action" targets="submen_paystub_list_div_id" indicator="indicatorSubMenuPaystubId_div" cssClass="link"><s:text name="MTIAddPayStub" /></sj:a> |
		</s:if>
		<s:if test="#session.PAYSTUB_VIEW == true">
			<sj:a href="getAllPayStubs.action" targets="submen_paystub_list_div_id" indicator="indicatorSubMenuPaystubId_div" cssClass="link"><s:text name="MTIViewPayStub"/></sj:a> |
			<sj:a href="payStubSearchForm.action" targets="submen_paystub_list_div_id" indicator="indicatorSubMenuPaystubId_div" cssClass="link"><s:text name="MTISearchPayStub"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuPaystubId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.paystub.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.paystub.empName" var="HEmployeeName"></s:text>
	  <s:text name="label.header.paystub.grossSalary" var="HGrossSalary"></s:text>
	  <s:text name="label.header.paystub.decDate" var="HDeclarationdate"></s:text>
	  <s:text name="label.header.paystub.netSalary" var="HNetSalary"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  		   
	  <div id="display_tag_payStubList_div_id">
		  <display:table class="tableborder" name="payStubList" id="payStubList" pagesize="${NO_OF_RECORDS}" requestURI="getAllPayStubs.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		      <%
		    	try{
		    		String sPayStubId = ((PayStubVO)pageContext.getAttribute("payStubList")).getPayStubId().toString();
		        	request.setAttribute("PayStubId", sPayStubId);   		
		    	}catch(NullPointerException ne){
		        }    	
		      %>
		  
		    <display:column property="employee.empFullName" title="${HEmployeeName}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.PAYSTUB_GROSSSALARY_VIEW == true">
		    	<display:column property="grossSalary" title="${HGrossSalary}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.PAYSTUB_DECDATE_VIEW == true">
		    	<display:column property="declarationDate" title="${HDeclarationdate}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.PAYSTUB_NETSALARY_VIEW == true">
		    	<display:column property="netSalary" title="${HNetSalary}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.PAYSTUB_VIEW==true">
		    	<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="getEmployeePayStub" action="getEmployeePayStub">
						<s:param name="payStub.payStubId" value="#request.PayStubId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submen_paystub_list_div_id','%{getEmployeePayStub}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
			<display:setProperty name="export.csv.filename" value="PayStub.csv"/>
			<display:setProperty name="export.excel.filename" value="PayStub.xls"/>
			<display:setProperty name="export.xml.filename" value="PayStub.xml"/>
		  </display:table>
	  </div>
</div>   
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_payStubList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  