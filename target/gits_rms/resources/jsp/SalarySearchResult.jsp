<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.SalaryVO"%>

<div id="submenu_SalarySearchResId_div">
	
	<div class="submenu_bg">
		<s:if test="#session.SALARY_ADD == true">
			<sj:a href="setUpSalary.action" targets="submenu_SalarySearchResId_div" indicator="indicatorSubMenuSalarySearchResId_div" cssClass="link"><s:text name="MTIAddSalary" /></sj:a> |
		</s:if>
		<s:if test="#session.SALARY_VIEW == true">
			<sj:a href="getAllSalary.action" targets="submenu_SalarySearchResId_div" indicator="indicatorSubMenuSalarySearchResId_div" cssClass="link"><s:text name="MTIViewSalary"/></sj:a> |
			<sj:a href="salarySearchForm.action" targets="submenu_SalarySearchResId_div" indicator="indicatorSubMenuSalarySearchResId_div" cssClass="link"><s:text name="MTISearchSalary"/></sj:a>
		</s:if>
	</div>
	<br/>

	<img id="indicatorSubMenuSalarySearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
		
	<div class="informationMessageSingle"><li><span><s:text name="label.title.salary.list"/></span></li></div>		   
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.salary.name" var="HSalaryName"></s:text>
	  <s:text name="label.header.common.empName" var="HSalaryEmpName"></s:text>
	  <s:text name="label.header.salary.decDate" var="HSalaryDecDate"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <display:table class="tableborder" id="salaryListId" name="salary" pagesize="${NO_OF_RECORDS}" requestURI="salarySearchResult.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
	    <%
	    	try{
	    		String sSalaryId = ((SalaryVO)pageContext.getAttribute("salaryListId")).getHcmosalaryId().toString();
	        	request.setAttribute("SalaryId", sSalaryId);	
	    	}catch(NullPointerException ne){
	        }    	
	    %>
	    <display:column property="curTypeValueForSalaryField" title="${HSalaryName}" sortable="true" headerClass="sortable" format="$ {0,number,000.00}"/>
	    <display:column property="empIdObj.empFullName" title="${HSalaryEmpName}" sortable="true" headerClass="sortable"/>
	    <display:column property="declarationDate" title="${HSalaryDecDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
	    <s:if test="#session.SALARY_VIEW==true">
			<display:column title="${HView}" class="viewedit" media="html">
				<s:url var="listViewSalary" action="salaryView">
					<s:param name="sal.hcmosalaryId" value="#request.SalaryId"></s:param>
				</s:url>
				<sj:a href="%{listViewSalary}" targets="submenu_SalarySearchResId_div" indicator="indicatorSubMenuSalarySearchResId_div"><s:text name="View"/></sj:a>
			</display:column>
		</s:if>
	    <s:if test="#session.SALARY_UPDATE==true">
			<display:column title="${HEdit}" class="viewedit" media="html">
				<s:url var="listSetUpSalary" action="setUpSalary">
					<s:param name="sal.hcmosalaryId" value="#request.SalaryId"></s:param>
				</s:url>
				<sj:a href="%{listSetUpSalary}" targets="submenu_SalarySearchResId_div" indicator="indicatorSubMenuSalarySearchResId_div"><s:text name="Edit"/></sj:a>
			</display:column>
		</s:if>
	    <s:if test="#session.SALARY_DELETE==true">
			<display:column title="${HDelete}" class="viewedit" media="html">
				<s:url var="listDeleteSalary" action="deleteSalary">
					<s:param name="sal.hcmosalaryId" value="#request.SalaryId"></s:param>
				</s:url>
				<sj:a href="%{listDeleteSalary}" targets="submenu_SalarySearchResId_div" indicator="indicatorSubMenuSalarySearchResId_div"><s:text name="Delete"/></sj:a>
			</display:column>
		</s:if>
		 <display:setProperty name="export.csv.filename" value="Salary.csv"/>
		 <display:setProperty name="export.excel.filename" value="Salary.xls"/>
		 <display:setProperty name="export.xml.filename" value="Salary.xml"/>
	  </display:table>
</div>      