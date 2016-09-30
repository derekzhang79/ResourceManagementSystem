<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.EmpPassportVO"%>

<div id="submenu_employeePassport_list_div_id">
	<div class="submenu_bg">
		<s:if test="#session.EMPLOYEEPASSPORT_ADD == true">
			<sj:a href="setUpEmpPassport.action" targets="submenu_employeePassport_list_div_id" indicator="indicatorSubMenuEmployeePassport" cssClass="link"><s:text name="MTIAddEmployeePassport" /></sj:a> |
		</s:if>
		<s:if test="#session.EMPLOYEEPASSPORT_VIEW == true">
			<sj:a href="getAllEmpPassport.action" targets="submenu_employeePassport_list_div_id" indicator="indicatorSubMenuEmployeePassport" cssClass="link"><s:text name="MTIViewEmployeePassport"/></sj:a> |
			<sj:a href="passportSearchForm.action" targets="submenu_employeePassport_list_div_id" indicator="indicatorSubMenuEmployeePassport" cssClass="link"><s:text name="MTISearchEmployeePassport"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeePassport" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
		
	<div class="informationMessageSingle"><li><span><s:text name="label.title.empPassport.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HPassportName"></s:text>
	  <s:text name="label.header.empPassport.epPassportNo" var="HPassportNo"></s:text>
	  <s:text name="label.header.empPassport.PassportIssueDate" var="HPassportIssueDate"></s:text>
	  <s:text name="label.header.empPassport.PassportExpireDate" var="HPassportExpireDate"></s:text>
	  <s:text name="label.header.empPassport.epComments" var="HPassportComments"></s:text>
	  <s:text name="label.header.empPassport.epPassportTypeFlg" var="HPassportTypeFlag"></s:text>
	  <s:text name="label.header.empPassport.epL9Status" var="HPassportL9Status"></s:text>
	  <s:text name="label.header.empPassport.epL9ReviewDate" var="HPassportReviewDate"></s:text>
	  <s:text name="label.header.empPassport.countryName" var="HPassportCountryName"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_empPassportList_div_id">
		  <display:table class="tableborder" id="employeePassportListId" name="empPassList" pagesize="${NO_OF_RECORDS}" requestURI="getAllEmpPassport.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sEmployeePassportId = ((EmpPassportVO)pageContext.getAttribute("employeePassportListId")).getHcmoEmpPassportId().toString();
		        	request.setAttribute("EmployeePassportId", sEmployeePassportId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="empIdObj.empFullName" title="${HPassportName}" sortable="true" headerClass="sortable"/>
		    
		    <s:if test="#session.EMPLOYEEPASSPORT_PASSPORTNO_VIEW==true">
		    	<display:column property="epPassportNo" title="${HPassportNo}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EMPLOYEEPASSPORT_PASSPORTISSUEDATE_VIEW==true">
		    	<display:column property="epPassportIssueDate" title="${HPassportIssueDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EMPLOYEEPASSPORT_PASSPORTEXPIREDATE_VIEW==true">
		    	<display:column property="epPassportExpireDate" title="${HPassportExpireDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EMPLOYEEPASSPORT_PASSPORTTYPEFLG_VIEW==true">
		    	<display:column property="epComments" title="${HPassportComments}" sortable="true" headerClass="sortable" maxLength="10"/>
		    </s:if>
		    <s:if test="#session.EMPLOYEEPASSPORT_L9STATUS_VIEW==true">
		    	<display:column property="epPassportTypeFlg" title="${HPassportTypeFlag}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EMPLOYEEPASSPORT_L9REVIEWDATE_VIEW==true">
		    	<display:column property="epL9Status" title="${HPassportL9Status}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EMPLOYEEPASSPORT_COUNTRYNAME_VIEW==true">
		    	<display:column property="epL9ReviewDate" title="${HPassportReviewDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.EMPLOYEEPASSPORT_COMMENTS_VIEW==true">
		    	<display:column property="country.countryName" title="${HPassportCountryName}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    
		    <s:if test="#session.EMPLOYEEPASSPORT_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewEmployeePassport" action="empPassportView">
						<s:param name="empPass.hcmoEmpPassportId" value="#request.EmployeePassportId"></s:param>
					</s:url>
						<s:a href="#" onclick="doPostCall('submenu_employeePassport_list_div_id','%{listViewEmployeePassport}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.EMPLOYEEPASSPORT_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpEmployeePassport" action="setUpEmpPassport">
						<s:param name="empPass.hcmoEmpPassportId" value="#request.EmployeePassportId"></s:param>
					</s:url>
						<s:a href="#" onclick="doPostCall('submenu_employeePassport_list_div_id','%{listSetUpEmployeePassport}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.EMPLOYEEPASSPORT_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteEmployeePassport" action="deleteEmpPassport">
						<s:param name="empPass.hcmoEmpPassportId" value="#request.EmployeePassportId"></s:param>
					</s:url>
						<s:a href="#" onclick="doPostCall('submenu_employeePassport_list_div_id','%{listDeleteEmployeePassport}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="EmployeePassport.csv"/>
			 <display:setProperty name="export.excel.filename" value="EmployeePassport.xls"/>
			 <display:setProperty name="export.xml.filename" value="EmployeePassport.xml"/>
		  </display:table>
	  </div>
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_empPassportList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  