<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_newEvent_div">
	
		<br/>
		<img id="indicatorSubMenuEmployeeBenefitsNewEventId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="viewCalendar">
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
                <sj:a href="%{calendarMainPage}" targets="submenu_newEvent_div">Home</sj:a>
	     
	     </td></tr>
	     <tr>
	     <td>Event Date </td>
	     <td><s:property value="selectedDate"></s:property></td>
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
	      	
	      	<td>Employee Name * </td>
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
	        <td class="employeeinputtd"><s:textfield name="eventName"/></td>
	      </tr>
	      
	      <tr>
	      	<td>Description </td>
			<!-- text area size has changed:venkat-->
	        <td class="employeeinputtd"><s:textarea name="description" cssClass="employeetextarea" rows="4" cols="26"/></td>
	        
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
	   		 <br/>
	    <table align="center"> 
	    	     <tr>


	    		    <td>
		    		    <img id="newEventsForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		    		    <sj:submit targets="submenu_newEvent_div" key="button.label.submit" cssClass="submitbutton117" indicator="newEventsForm"/>


	    		    </td>
	    	        <td>
	    	        	
	    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />

		    	     </td>
	    		 </tr>
	    </table>	
	    <s:hidden name="hiddenDate" value="%{selectedDate}"></s:hidden>	  	
	    
	    	</s:form>
</div>	    	