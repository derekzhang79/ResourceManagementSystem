<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_MessageBroadcastSearchId_div">
<br/><img id="indicatorSubMenuMessageBroadcastSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="broadcastSearchResult">
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
						 <s:text name="label.title.message.search"/>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.message.subject" /></td>
	         	<td class="employeeinputtd">
	         		<s:textfield name="message.subject" cssClass="employeeinput" ></s:textfield>
	         	</td>
	         	<td></td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.message.message" /></td>
	         	<td class="employeeinputtd">
	         		<!-- textarea size has been changed : venkat -->
	         		<s:textarea name="message.message" cssClass="employeetextarea" rows="4" cols="26" />
	         	</td>
	         	<td></td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.message.type" /></td>
	         	<td class="employeeinputtd">
	         	
					<s:url var="getMSGTypeJSONList" action="getMSGTypeJSONList"/> 
					<sj:select 
					    indicator="indicatorMsgSearchType" 
					    href="%{getMSGTypeJSONList}" 
					    list="msgTypeList"
						name="message.type"
					    dataType="json"
					    cssClass="employeeselect"					  
					/>					
					<img id="indicatorMsgSearchType" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
         		</td>
         		<td></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	   	     <tr>
	   		    <td>
					<img id="indicatorMessageFormBroadcastSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_MessageBroadcastSearchId_div" indicator="indicatorMessageFormBroadcastSearchId_div"/>
	   		   	</td>
	   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	   		 </tr>
	    </table> 		  		 
	    </s:form>
   <br/><br/><br/>
</div>