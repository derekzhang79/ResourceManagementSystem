<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.EmployeesVO"%>
<%@page import="com.gits.rms.vo.PayStubVO"%>

<div id="submenu_EssPayStub_List_div_Id">

<img id="indicatorPayStubList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

   	<s:set name="UniquePayStubEmployeeId" value="payStub.employee.employeeId"></s:set>
	<s:url var="remotePayStubForm" value="/setUpEmpPayStub.action">
		<s:param name="payStub.employee.employeeId" value="#UniquePayStubEmployeeId"></s:param>
	</s:url>
	<s:if test="#session.PAYSTUB_ADD == true"> 
		<div class="submenu_bg">
		    <s:if test="#session.PAYSTUB_ADD==true">
				<sj:a href="%{remotePayStubForm}" indicator="indicatorPayStubList" targets="submenu_EssPayStub_List_div_Id" cssClass="link"><s:text name="label.title.paystub.add"/></sj:a>
			</s:if>
		</div>		
	</s:if>
<br />

<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

<div class="informationMessageSingle"><li><span><s:text name="label.title.paystub.list"/></span></li></div>

       <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.paystub.empName" var="HEmployeeName"></s:text>
	  <s:text name="label.header.paystub.grossSalary" var="HGrossSalary"></s:text>
	  <s:text name="label.header.paystub.decDate" var="HDeclarationdate"></s:text>
	  <s:text name="label.header.paystub.netSalary" var="HNetSalary"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  		   
	  <div id="display_tag_payStub_List_div_id">
		   <display:table class="tableborder" name="payStubList" id="payStubList" pagesize="${NO_OF_RECORDS}" requestURI="getEmployeeAllPayStub.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		      <%
		    	try{
		    		String sPayStubId = ((PayStubVO)pageContext.getAttribute("payStubList")).getPayStubId().toString();
		        	request.setAttribute("PayStubId", sPayStubId);
		        	String sPayStubEmpId = ((PayStubVO)pageContext.getAttribute("payStubList")).getEmployee().getEmployeeId().toString();
		        	request.setAttribute("EmployeeId", sPayStubEmpId);
		    	}catch(NullPointerException ne){
		        }    	
		      %>
		  
		    <display:column property="employee.empFullName" title="${HEmployeeName}" sortable="true" headerClass="sortable"/>
		    <display:column property="grossSalary" title="${HGrossSalary}" sortable="true" headerClass="sortable"/>
		    <display:column property="declarationDate" title="${HDeclarationdate}" sortable="true" headerClass="sortable"/>
		    <display:column property="netSalary" title="${HNetSalary}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.PAYSTUB_VIEW==true">
		    	<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="getEssEmpPayStub" action="getEssEmpPayStub" escapeAmp="false">
						<s:param name="payStub.employee.employeeId" value="#request.EmployeeId"></s:param>  
						<s:param name="payStub.payStubId" value="#request.PayStubId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_EssPayStub_List_div_Id','%{getEssEmpPayStub}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
			<%-- <s:if test="#session.PAYSTUB_UPDATE==true">
		    	<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listEditPayStub" action="setUpForInsertOrUpdateDeduction">
						<s:param name="payStub.payStubId" value="#request.PayStubId"></s:param>
					</s:url>
					<sj:a href="%{listEditPayStub}" targets="submen_paystub_div" indicator="indicatorSubMenuPaystub"><s:text name="Edit"/></sj:a>
				</display:column>
			</s:if>
			<s:if test="#session.PAYSTUB_DELETE==true">
		    	<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeletePayStub" action="deletePayStub">
						<s:param name="payStub.payStubId" value="#request.PayStubId"></s:param>
					</s:url>
					<sj:a href="%{listDeletePayStub}" targets="submen_paystub_div" indicator="indicatorSubMenuPaystub"><s:text name="Delete"/></sj:a>
				</display:column>
			</s:if> --%>
			
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
   		 	jQuery("#display_tag_payStub_List_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  