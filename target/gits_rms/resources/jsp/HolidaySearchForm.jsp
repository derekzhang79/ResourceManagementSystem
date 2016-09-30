<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_HolidaySearchId_div">
<div class="submenu_bg">
	<s:if test="#session.HOLIDAY_ADD == true">
		<sj:a href="setUpHoliday.action" targets="submenu_HolidaySearchId_div" indicator="indicatorSubMenuHolidaySearchId_div" cssClass="link"><s:text name="MTIAddHoliday" /></sj:a> |
	</s:if>
	<s:if test="#session.HOLIDAY_VIEW == true">
		<sj:a href="getAllHoliday.action" targets="submenu_HolidaySearchId_div" indicator="indicatorSubMenuHolidaySearchId_div" cssClass="link"><s:text name="MTIViewHoliday"/></sj:a> |
		<sj:a href="holidaySearchForm.action" targets="submenu_HolidaySearchId_div" indicator="indicatorSubMenuHolidaySearchId_div" cssClass="link"><s:text name="MTISearchHoliday"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuHolidaySearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="holidaySearchResult">
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
							 <s:text name="label.title.holiday.search"/>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	     	 
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.holiday.description"/></td>
	     	    <td class="employeeinputtd"><s:textfield name="holiday.holidayDescription" cssClass="employeeinput"/></td>
					<img id="holidatSearchFormHolidatName" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
	         </tr>
	         <!--Button image added by, R.Deepika-->
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.holiday.date" /></td>
	         	<td class="employeeinputtd">
	       	        <sj:datepicker name="holiday.holidayDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	       
	     	       <!-- Extra text changed by, R.Deepika  -->
	         	</td>
	         	<td class="employeeinputtd">
					<s:url var="getSearchProcessJSONList" action="getSearchProcessJSONList"/>
					<sj:select
						href="%{getSearchProcessJSONList}"
						list="searchProcessList"
						name="holiday.holidayDateValue"
					    dataType="json"
						indicator="holidaySearchFormDateCheckOperation"      
					    cssClass="employeeselect" 
					/>
					<img id="holidaySearchFormDateCheckOperation" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	       	    </td>
	         </tr>
	         <tr>
				<td></td>
				<td class="employeeinputtd">
	       	        <sj:datepicker name="holiday.holidayEndDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	       	        <br/>
	       	        <s:text name="label.date.format"/></td>
	       	        <td><s:text name="label.common.search.dateInformation"/>
				</td>
			</tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorHolidayForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_HolidaySearchId_div" indicator="indicatorHolidayForm"></sj:submit>
	    		   	</td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
	    </s:form>
	</div>