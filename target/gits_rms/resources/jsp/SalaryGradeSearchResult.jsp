<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.SalaryGradeVO"%>

<s:form action="skippedPermanently" id="salaryGradeSearchSkip">

<div id="submenu_salaryGrade_searchlist_div_id">
<div class="submenu_bg">
	<s:if test="#session.SALARYGRADE_ADD == true">
		<sj:a href="setUpSalaryGrade.action" targets="submenu_salaryGrade_searchlist_div_id" indicator="indicatorSubMenuSalaryGradeSearchResId_div" cssClass="link"><s:text name="MTIAddSalaryGrade" /></sj:a> |
	</s:if>
	<s:if test="#session.SALARYGRADE_VIEW == true">
		<sj:a href="getAllSalaryGrade.action" targets="submenu_salaryGrade_searchlist_div_id" indicator="indicatorSubMenuSalaryGradeSearchResId_div" cssClass="link"><s:text name="MTIViewSalaryGrade"/></sj:a> |
		<sj:a href="salaryGradeSearchForm.action" targets="submenu_salaryGrade_searchlist_div_id" indicator="indicatorSubMenuSalaryGradeSearchResId_div" cssClass="link"><s:text name="MTISearchSalaryGrade"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuSalaryGradeSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.salaryGrade.list"/></span></li></div>		   
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.salaryGrade.name" var="HSalaryGrade"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  
	  <div id="display_tag_salarygradesearchList_div_id">
		   <display:table class="tableborder" id="salaryGradeListId" name="salarygrade" pagesize="${NO_OF_RECORDS}" requestURI="salaryGradeSearchResult.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sSalaryGradeId = ((SalaryGradeVO)pageContext.getAttribute("salaryGradeListId")).getHcmoSalaryGradeId().toString();
		        	request.setAttribute("SalaryGradeId", sSalaryGradeId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="salaryName" title="${HSalaryGrade}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.SALARYGRADE_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewSalaryGrade" action="salaryGradeView">
						<s:param name="salgra.hcmoSalaryGradeId" value="#request.SalaryGradeId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_salaryGrade_searchlist_div_id','%{listViewSalaryGrade}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.SALARYGRADE_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpSalaryGrade" action="setUpSalaryGrade">
						<s:param name="salgra.hcmoSalaryGradeId" value="#request.SalaryGradeId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_salaryGrade_searchlist_div_id','%{listSetUpSalaryGrade}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.SALARYGRADE_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteSalaryGrade" action="deleteSalaryGrade">
						<s:param name="salgra.hcmoSalaryGradeId" value="#request.SalaryGradeId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_salaryGrade_searchlist_div_id','%{listDeleteSalaryGrade}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="SalaryGrade.csv"/>
			 <display:setProperty name="export.excel.filename" value="SalaryGrade.xls"/>
			 <display:setProperty name="export.xml.filename" value="SalaryGrade.xml"/>
		  </display:table>
	  </div>		   
	 
	  <s:if test="#session.CONFIGURATION == 'CONFIGURATION'">
		  <table align="center">
				<tr>
					<td>
						<s:url var="nextConfigurationAction" action="nextConfigurationAction"/>
						<sj:submit href="%{nextConfigurationAction}" value="Next" targets="submenu_salaryGrade_searchlist_div_id" indicator="indicatorMailConfigurationSalaryGradeSearchResId_div" cssClass="submitbutton117"/>
						<img id="indicatorMailConfigurationSalaryGradeSearchResId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
					</td>
					<td>
						<div class="button-comments">
	   		    		<div class="button-left"></div>
							<s:submit value="Skipped Permanently" cssClass="button-midle"></s:submit>
						<div class="button-right"></div>
	   		    		</div>
					</td>
					<td>
						<s:submit value="Skip" action="skip" cssClass="submitbutton117"></s:submit>
					</td>
				</tr>
		</table>
	</s:if> 
</div>
</s:form>    
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_salarygradesearchList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>