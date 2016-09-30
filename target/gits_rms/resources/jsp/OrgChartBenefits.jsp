<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.BenefitsVO"%>

<div id="submenu_orgChartemployeeBenefits_list_div_id">
		<br/>
		<img id="indicatorSubMenuEmployeeBenefitsOrgchartId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
						
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.benefit.list"/></span></li></div>
	
	<br/>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.benefit.year" var="HBenefitYear"></s:text>
	  <s:text name="label.header.benefit.benefitsName" var="HBenefitName"></s:text>
	  <s:text name="label.header.benefit.benefitsAttachFile" var="HBenefitAttach"></s:text>
	  <s:text name="label.header.common.empName" var="HEmployeeName"></s:text>
	  	
	  <div id="display_tag_orgChartBenefitsList_div_id">
		  <display:table class="tableborder" id="benefitListId" name="benefitList" pagesize="${NO_OF_RECORDS}" requestURI="orgChartBenefitNewTab.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		   <%
		   	  try{
		   		String sBenefitsId = ((BenefitsVO)pageContext.getAttribute("benefitListId")).getHcmoBenefitsId().toString();
		    	request.setAttribute("HcmoBenefitsId", sBenefitsId);   	
		   	  }catch(NullPointerException ne){
		      }
		    	
		    %>
		    <display:column property="year" title="${HBenefitYear}" sortable="true" headerClass="sortable"/>
		    <display:column property="benefitsName" title="${HBenefitName}" sortable="true" headerClass="sortable"/>
		    <display:column property="benefitsAttachFile" href="fileDownload.action" paramId="benefit.hcmoBenefitsId" paramProperty="hcmoBenefitsId" title="${HBenefitAttach}" sortable="true" headerClass="sortable"/>
		    
			 <display:setProperty name="export.csv.filename" value="EmployeeBenefits.csv"/>
			 <display:setProperty name="export.excel.filename" value="EmployeeBenefits.xls"/>
			 <display:setProperty name="export.xml.filename" value="EmployeeBenefits.xml"/>
		  </display:table>
	  </div>
</div> 
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_orgChartBenefitsList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>       