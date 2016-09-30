
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_ListEventsId_div">
	
		<br/>
		<img id="indicatorSubMenuEmployeeBenefitsListEventsId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="viewCalendar">
	    
	    <div align="left">
	     	<s:url var="calendarMainPage" action="calendarMainPage" namespace="/">
	           		
               	</s:url>
                <sj:a href="%{calendarMainPage}" targets="timesheetCalendar_div"><b>Home</b></sj:a>
	     		
	    </div>
	    <br>
	    
	     <table width="100%" class="timesheetouter">
	     <tr>
	     <th>Event Name</th>
	     <th>Description</th>
	     <th>Event Date</th>
	     <th>Modify</th>
	     <th>Remove</th>
	     
	     </tr>
	     
	     
		     <s:if test="eventsList.size()<=0">
			
				<tr>
					<td>
						No Events
					</td>
				</tr>
			
		</s:if>
		<s:else>
	     
	     <s:iterator value="eventsList">
	     <tr>
	     <td><s:property value="eventName"></s:property></td>
	     <td><s:property value="description"></s:property></td>
	     <td><s:property value="startDate"></s:property></td>
	     <td>
	     <s:url var="editClick" action="editEvent" namespace="/">
	           	<s:param name="eventId" value="%{eventId}"/>
	      </s:url>
	     <sj:a href="%{editClick}" targets="timesheetCalendar_div">Edit</sj:a>
	     </td>
	     <td>
	     <s:url var="deleteClick" action="deleteEvent" namespace="/">
	           	<s:param name="eventId" value="%{eventId}"/>
	           	<s:param name="uniqueValue" value="%{groupId}"/>
	     </s:url>
	     <sj:a href="%{deleteClick}" targets="timesheetCalendar_div">Delete</sj:a>
	     </td>
	     </tr>
	     </s:iterator>
	     </s:else>
	    </table>	
	   		 
	    	</s:form>
</div>	    	