<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.EmployeesVO"%>
<%@page import="com.gits.rms.vo.SalaryVO"%>

<%@page import="org.displaytag.export.CsvView"%>
<%@page import="org.displaytag.export.ExcelView"%>
<%@page import="org.displaytag.export.XmlView"%>

<div id="submenu_EssSalary_List_div">
<img id="indicatorSalList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:set name="UniqueSalEmployeeId" value="sal.empIdObj.employeeId"></s:set>
	<s:url var="remoteSalForm" value="/setUpEmpSalary.action">
		<s:param name="sal.empIdObj.employeeId" value="#UniqueSalEmployeeId"></s:param>
	</s:url>
	<div class="submenu_bg">
		<s:if test="#session.SALARY_ADD==true">
			<sj:a href="%{remoteSalForm}" indicator="indicatorSalList" targets="submenu_EssSalary_List_div" cssClass="link"><s:text name="label.header.salaryGrade.add"/></sj:a>
		</s:if>		
	</div>
<br />	

<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
<div class="informationMessageSingle"><li><span><s:text name="label.title.salary.list"/></span></li></div>

        <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
        <s:text name="label.header.salary.name" var="HName"/>
        <s:text name="label.header.common.empName" var="HEmpname"/>
        <s:text name="label.header.salary.decDate" var="HDecdate"/>
        <s:if test="#session.SALARY_UPDATE==true">
        	<s:text name="label.header.common.edit" var="HEdit"/>
        </s:if>
        <s:if test="#session.SALARY_DELETE==true">
        	<s:text name="label.header.common.delete" var="HDelete"/>
        </s:if>
        
 <display:table class="tableborder" id="salaryId" name="salary" pagesize="${NO_OF_RECORDS}" requestURI="" sort="list" defaultsort="1" defaultorder="ascending">
           <%
		  try
           {
			  String sEmpId=((SalaryVO)pageContext.getAttribute("salaryId")).getEmpIdObj().getEmployeeId().toString();
			  request.setAttribute("sEmployeeId",sEmpId);
			  String sSalId=((SalaryVO)pageContext.getAttribute("salaryId")).getHcmosalaryId().toString();
			  request.setAttribute("sSalaryId",sSalId);
		  }
           catch(NullPointerException ne){
		  }
		%>
		 
		    
		    <display:column property="curTypeValueForSalaryField" title="${HName}" sortable="false" headerClass="sortable" format="$ {0,number,000.00}"/>
            <display:column property="empIdObj.empFullName" title="${HEmpname}" sortable="false" headerClass="sortable"/>
            <display:column property="declarationDate" title="${HDecdate}" format="{0,date,MM-dd-yyyy}" sortable="false" headerClass="sortable"/>
                               
            
           <s:if test="#session.SALARY_UPDATE==true">
	            <display:column title="${HEdit}" class="viewedit" media="html">
	                	<s:url var="setUpEmpSalarySingle" action="setUpEmpSalarySingle" escapeAmp="false">
							<s:param name="sal.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
			       		   <s:param name="sal.hcmosalaryId" value="#request.sSalaryId"/>
			       		</s:url> 
	                <sj:a href="%{setUpEmpSalarySingle}" targets="submenu_EssSalary_List_div" indicator="indicatorSalList"><s:text name="label.common.link.edit"/></sj:a>
	           </display:column>
           </s:if>
           <s:if test="#session.SALARY_DELETE==true">
              <display:column title="${HDelete}" class="viewedit" media="html">
                    <s:url var="deleteEmpSalary" action="deleteEmpSalary" escapeAmp="false">
					      <s:param name="sal.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
		       		      <s:param name="sal.hcmosalaryId" value="#request.sSalaryId"/>
		            </s:url>
                <sj:a href="%{deleteEmpSalary}" targets="submenu_EssSalary_List_div" indicator="indicatorSalList"><s:text name="label.common.link.delete"/></sj:a>
             </display:column>
           </s:if>
           
  </display:table>
<br />
</div>