
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_EmployeeEditEventsId_div">
	
		<br/>
		<img id="indicatorSubMenuEmployeeEditEventsId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="SaveOrUpdateEvent">
	    <s:iterator value="editEvent">
	     <table class="maintable">
	     <tr>
    		<td colspan="10"><s:if test="#request.error==null || #request.error==''"></s:if>
			<s:else>
			<ul class="actionMessageSingle">
			<li><span><s:property value="#request.error"/></span></li>
			</ul>
			</s:else></td>
    	</tr>
    	<tr><td>
	     	<s:url var="calendarMainPage" action="calendarMainPage" namespace="/">
	           		
               	</s:url>
                <sj:a href="%{calendarMainPage}" targets="timesheetCalendar_div">Home</sj:a>
	     
	     </td></tr>
	     <tr>
	     <td>Event Date </td>
	     <td><s:date format="MM/dd/yyyy" name="startDate"></s:date></td>
	     </tr>
	     
	     <tr>
	     <td>Event Time </td>
	     <td><s:select 
			            			headerKey=""
									list="#request.hourList"
									name="eventTime"
								    cssClass="employeeselect"
					    			
		               			 /></td>
	     </tr>
	     
	     
	      <tr>
	      	
	      	<td>Employee Name *</td>
	        <td class="employeeinputtd">
	       						 <s:select 
			            			headerKey=""
									list="#request.employees"
									listKey="employeeId"
									listValue="empFirstName"
								    name="employeeId"
								    cssClass="employeeselect"
					    			multiple="true"
				    				size="3"
		               			 />
	        </td>
	       </tr>
	       
	       <tr>
	      	<td>Event Name *</td>
	        <td class="employeeinputtd"><s:textfield name="eventName" value="%{eventName}"/></td>
	      </tr>
	      
	      <tr>
	      	<td>Description </td>
	      	<!-- textarea size has been changed:venkat -->
	        <td class="employeeinputtd"><s:textarea name="description" cssClass="employeetextarea" rows="4" cols="26" value="%{description}"/></td>
	        
	        </tr>
	        
	        <tr>
	      	
	      	<td>Time Zone </td>
	        <td class="employeeinputtd">
	       						 <s:select 
			            			headerKey="Asia/Culcutta"
									list="#request.timeZoneList"
									name="timeZone"
								    cssClass="employeeselect"
					    			
		               			 />
	        </td>
	       </tr>
	        
	        </table>
	        <s:date name="startDate" format="MM/dd/yyyy" var="formattedVal"/>
	        <s:hidden name="startDate" value="%{#formattedVal}" key="labelkey"></s:hidden>
	        <s:hidden name="eventId" value="%{eventId}"></s:hidden>
	        <s:hidden name="uniqueValue" value="%{groupId}"></s:hidden>
	        <s:hidden name="eventTime" value="%{eventTime}"></s:hidden>
	        
	        </s:iterator>
	   		 <br/>
	    <table align="center"> 
	    	     <tr>


	    		    <td>
	    		    	<img id="editEventsForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	    		    	<sj:submit targets="timesheetCalendar_div" key="button.label.submit" cssClass="submitbutton117" indicator="editEventsForm"/>


	    		    </td>
	    	        <td>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />

		    	     </td>
	    		 </tr>
	    </table>	
	   		 
	    	</s:form>
</div>