<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.HolidayVO"%>

<div id="submenu_holiday_list_div_id">
<div class="submenu_bg">
	<s:if test="#session.HOLIDAY_ADD == true">
		<sj:a href="setUpHoliday.action" targets="submenu_holiday_list_div_id" indicator="indicatorSubMenuHoliday" cssClass="link"><s:text name="MTIAddHoliday" /></sj:a> |
	</s:if>
	<s:if test="#session.HOLIDAY_VIEW == true">
		<sj:a href="getAllHoliday.action" targets="submenu_holiday_list_div_id" indicator="indicatorSubMenuHoliday" cssClass="link"><s:text name="MTIViewHoliday"/></sj:a> |
		<sj:a href="holidaySearchForm.action" targets="submenu_holiday_list_div_id" indicator="indicatorSubMenuHoliday" cssClass="link"><s:text name="MTISearchHoliday"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuHoliday" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>	
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.holiday.list"/></span></li></div>	
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.holiday.description" var="HHolidayDescription"></s:text>
	  <s:text name="label.header.holiday.date" var="HHolidayDate"></s:text>
	  <s:text name="label.header.holiday.repeats" var="HHolidayRepeats"></s:text>
	  <s:text name="label.header.holiday.length" var="HHolidayLength"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_holidayList_div_id">
		  <display:table class="tableborder" id="holidayListId" name="holidayList" pagesize="${NO_OF_RECORDS}" requestURI="getAllHoliday.action" sort="list" defaultsort="1" defaultorder="ascending" export="true">
		    <%
		    	try{
		    		String sHolidayId = ((HolidayVO)pageContext.getAttribute("holidayListId")).getHolidayId().toString();
		        	request.setAttribute("HolidayId", sHolidayId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="holidayDescription" title="${HHolidayDescription}" sortable="true" headerClass="sortable"/>
		    <display:column property="holidayDate" title="${HHolidayDate}" format="{0,date,MM-dd-yyyy}" sortable="true" headerClass="sortable"/>
		    <display:column property="recurring" title="${HHolidayRepeats}" sortable="true" headerClass="sortable"/>
		    <display:column property="lengthValue" title="${HHolidayLength}" sortable="true" headerClass="sortable"/>
		    <s:if test="#session.HOLIDAY_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewHoliday" action="holidayView">
						<s:param name="holiday.holidayId" value="#request.HolidayId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_holiday_list_div_id','%{listViewHoliday}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.HOLIDAY_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpHoliday" action="setUpHoliday">
						<s:param name="holiday.holidayId" value="#request.HolidayId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_holiday_list_div_id','%{listSetUpHoliday}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.HOLIDAY_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteHoliday" action="deleteHoliday">
						<s:param name="holiday.holidayId" value="#request.HolidayId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_holiday_list_div_id','%{listDeleteHoliday}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
			 <display:setProperty name="export.csv.filename" value="Holiday.csv"/>
			 <display:setProperty name="export.excel.filename" value="Holiday.xls"/>
			 <display:setProperty name="export.xml.filename" value="Holiday.xml"/>
		  </display:table>
	  </div>
	  
</div>  
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_holidayList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>     