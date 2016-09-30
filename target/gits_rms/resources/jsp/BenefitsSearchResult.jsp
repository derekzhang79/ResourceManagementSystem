<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.BenefitsVO"%>

<div id="submenu_employeeBenefits_searchList_div_id">
	<div class="submenu_bg">
		<s:if test="#session.BENEFITS_ADD == true">
			<sj:a href="setUpBenefit.action" targets="submenu_employeeBenefits_searchList_div_id" indicator="indicatorSubMenuEmployeeBenefitsSearchResId_div" cssClass="link"><s:text name="MTIAddEmployeeBenefits" /></sj:a> |
		</s:if>
		<s:if test="#session.BENEFITS_VIEW == true">
			<sj:a href="getAllBenefit.action" targets="submenu_employeeBenefits_searchList_div_id" indicator="indicatorSubMenuEmployeeBenefitsSearchResId_div" cssClass="link"><s:text name="MTIViewEmployeeBenefits"/></sj:a> |
			<sj:a href="benefitSearch.action" targets="submenu_employeeBenefits_searchList_div_id" indicator="indicatorSubMenuEmployeeBenefitsSearchResId_div" cssClass="link"><s:text name="MTISearchEmployeeBenefits"/></sj:a>
		</s:if>
	</div>
	<br/>
	<img id="indicatorSubMenuEmployeeBenefitsSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.benefit.list"/></span></li></div>	
	
	  <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.benefit.year" var="HBenefitYear"></s:text>
	  <s:text name="label.header.benefit.benefitsName" var="HBenefitName"></s:text>
	  <s:text name="label.header.benefit.benefitsAttachFile" var="HBenefitAttach"></s:text>
	   <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  
	  <div id="display_tag_benefitsSearchList_div_id">
		  <display:table class="tableborder" id="benefitListId" name="benefitList" pagesize="${NO_OF_RECORDS}" requestURI="getAllBenefitYearSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
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
		     <s:if test="#session.BENEFITS_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listBenefitView" action="benefitView">
						<s:param name="benefit.hcmoBenefitsId" value="#request.HcmoBenefitsId"></s:param>
					</s:url>
						<s:a href="#" onclick="doPostCall('submenu_employeeBenefits_searchList_div_id','%{listBenefitView}','');"><s:text name="View"/></s:a>
				</display:column>	    	
			</s:if>
			<s:if test="#session.BENEFITS_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="deleteBenefit" action="deleteBenefit">
						<s:param name="benefit.hcmoBenefitsId" value="#request.HcmoBenefitsId"></s:param>
					</s:url>
						<s:a href="#" onclick="doPostCall('submenu_employeeBenefits_searchList_div_id','%{deleteBenefit}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
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
   		 	jQuery("#display_tag_benefitsSearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  