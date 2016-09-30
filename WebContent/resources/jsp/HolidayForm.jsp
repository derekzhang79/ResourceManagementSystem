<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_HolidayFormId_div">
<div class="submenu_bg">
	<s:if test="#session.HOLIDAY_ADD == true">
		<sj:a href="setUpHoliday.action" targets="submenu_HolidayFormId_div" indicator="indicatorSubMenuHolidayFormId_div" cssClass="link"><s:text name="MTIAddHoliday" /></sj:a> |
	</s:if>
	<s:if test="#session.HOLIDAY_VIEW == true">
		<sj:a href="getAllHoliday.action" targets="submenu_HolidayFormId_div" indicator="indicatorSubMenuHolidayFormId_div" cssClass="link"><s:text name="MTIViewHoliday"/></sj:a> |
		<sj:a href="holidaySearchForm.action" targets="submenu_HolidayFormId_div" indicator="indicatorSubMenuHolidayFormId_div" cssClass="link"><s:text name="MTISearchHoliday"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuHolidayFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateHoliday">
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
					 		<s:if test="holiday==null || holiday.holidayId == null">
							 <s:text name="label.title.holiday.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.holiday.edit"/>
						   </s:else>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">	
	     	 
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.holiday.description"/></td>
	     	    <td class="employeeinputtd"><s:textfield name="holiday.holidayDescription" cssClass="employeeinput"/></td>
	       	    <td class="inputerrormessage"><s:fielderror fieldName="holiday.holidayDescription" /> </td>
	         </tr>
	         <!--Button image added by, R.Deepika-->
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.holiday.date" /></td>
	         	<td class="employeeinputtd">
	       	        <sj:datepicker  name="holiday.holidayDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
	     	        
	         	</td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="holiday.holidayDate" /> </td>
	         </tr>
	         <!-- Format Text Changed by, R.Deepika -->
	         <tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr> 
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.holiday.repeats"/></td>
	         	<td class="employeeinputtd">
	         		<s:checkbox label="holidayrepeat" name="holiday.recurring" cssClass="employeecheckbox"/><br>
	         	</td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="holiday.recurring" /> </td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.holiday.length"/></td>
				<td class="employeeinputtd">
					<s:select label="Select Leave Day" 
		    			name="holiday.length"
		    			id="holiday.length" 
		    			list="#{'8':'Full Day','4':'Half Day'}" cssClass="employeeselect" headerKey="0" headerValue="-- Please Select --" />
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="holiday.length" /> </td>
	         </tr>
	         <s:hidden name="holiday.holidayId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorHolidayFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_HolidayFormId_div" indicator="indicatorHolidayFormId_div"/>
	    		   	</td>
	    		   	<s:if test="holiday==null || holiday.holidayId==null">
				        <td>
		    		    	<s:url var="resetHoliday" action="resetHoliday"></s:url>
		    	            <sj:submit href="%{resetHoliday}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_HolidayFormId_div" indicator="indicatorHolidayFormId_div"/>
			           </td>
			       </s:if>
			       <s:else>
	    	           <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table> 		  		 
	    </s:form>
</div>