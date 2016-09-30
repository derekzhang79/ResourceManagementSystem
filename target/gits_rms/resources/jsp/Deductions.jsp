<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.DeductionsVO"%>

<div id="submenu_deductions_list_div_id">
	<div class="submenu_bg">
		<s:if test="#session.DEDUCTION_ADD == true">
			<sj:a href="setUpForInsertOrUpdateDeduction.action" targets="submenu_deductions_list_div_id" indicator="indicatorSubMenuDeductions" cssClass="link"><s:text name="MTIAddDeductions" /></sj:a> |
		</s:if>
		<s:if test="#session.DEDUCTION_VIEW == true">
			<sj:a href="getAllDeductions.action" targets="submenu_deductions_list_div_id" indicator="indicatorSubMenuDeductions" cssClass="link"><s:text name="MTIViewDeductions"/></sj:a> |
			<sj:a href="deductionSearchForm.action" targets="submenu_deductions_list_div_id" indicator="indicatorSubMenuDeductions" cssClass="link"><s:text name="MTISearchDeductions"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuDeductions" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.deduction.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.deduction.name" var="HDeductionName"></s:text>
	  <s:text name="label.header.deduction.type" var="HDeductionType"></s:text>
	  <s:text name="label.header.deduction.mode" var="HDeductionMode"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_deductionsList_div_id">
		  <display:table class="tableborder" name="dedList" id="dedList" pagesize="${NO_OF_RECORDS}" requestURI="getAllDeductions.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		      <%
		    	try{
		    		String sDeductionId = ((DeductionsVO)pageContext.getAttribute("dedList")).getDeductionId().toString();
		        	request.setAttribute("DeductionId", sDeductionId);   		
		    	}catch(NullPointerException ne){
		        }    	
		      %>
		  
		    <display:column property="deductionName" title="${HDeductionName}" sortable="true" headerClass="sortable"/>
		    <display:column property="deductionType" title="${HDeductionType}" sortable="true" headerClass="sortable"/>
		    <display:column property="deductionMode" title="${HDeductionMode}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.DEDUCTION_VIEW==true">
		    	<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewDeduction" action="deductionView">
						<s:param name="deduction.deductionId" value="#request.DeductionId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_deductions_list_div_id','%{listViewDeduction}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
			<s:if test="#session.DEDUCTION_UPDATE==true">
		    	<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listEditDeduction" action="setUpForInsertOrUpdateDeduction">
						<s:param name="deduction.deductionId" value="#request.DeductionId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_deductions_list_div_id','%{listEditDeduction}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
			<s:if test="#session.DEDUCTION_DELETE==true">
		    	<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteDeduction" action="deleteDeduction">
						<s:param name="deduction.deductionId" value="#request.DeductionId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_deductions_list_div_id','%{listEditDeduction}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="Deductions.csv"/>
			 <display:setProperty name="export.excel.filename" value="Deductions.xls"/>
			 <display:setProperty name="export.xml.filename" value="Deductions.xml"/>
		  </display:table>
	  </div> 
	</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_deductionsList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     