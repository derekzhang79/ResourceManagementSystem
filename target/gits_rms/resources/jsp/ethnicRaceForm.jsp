<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EthnicRaceFormId_div">
<div class="submenu_bg">
	<s:if test="#session.ETHNICRACE_ADD == true">
		<sj:a href="setUpEthnicRace.action" targets="submenu_EthnicRaceFormId_div" indicator="indicatorSubMenuEthnicRaceFormId_div" cssClass="link"><s:text name="MTIAddEthnicRace" /></sj:a> |
	</s:if>
	<s:if test="#session.ETHNICRACE_VIEW == true">
		<sj:a href="getAllEthnicRace.action" targets="submenu_EthnicRaceFormId_div" indicator="indicatorSubMenuEthnicRaceFormId_div" cssClass="link"><s:text name="MTIViewEthnicRace"/></sj:a> |
		<sj:a href="ethnicRaceSearchForm.action" targets="submenu_EthnicRaceFormId_div" indicator="indicatorSubMenuEthnicRaceFormId_div" cssClass="link"><s:text name="MTISearchEthnicRace"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuEthnicRaceFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	    <s:form action="insertOrUpdateEthnicRace">
		<table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		   <s:if test="ethRace==null || ethRace.ethnicRaceId == null">
							 <s:text name="label.title.ethnicRace.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.ethnicRace.edit"/>
						   </s:else>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign">      	 
	         <tr><td class="inputtext"><s:text name="label.form.fields.ethnicRace.name"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="ethRace.ethnicRaceDesc" cssClass="employeeinput"/></td>
	         	        <td class="inputerrormessage">
							<s:fielderror fieldName="ethRace.ethnicRaceDesc" />
				 		</td>
	         </tr>
	            <s:hidden name="ethRace.ethnicRaceId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorEthnicRaceFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EthnicRaceFormId_div" indicator="indicatorEthnicRaceFormId_div"/>
	    		   	</td>
	    		   	<s:if test="ethRace==null || ethRace.ethnicRaceId==null">
				       <td>
		    		    	<s:url var="resetEthnicRace" action="resetEthnicRace"></s:url>
		    	            <sj:submit href="%{resetEthnicRace}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EthnicRaceFormId_div" indicator="indicatorEthnicRaceFormId_div"/>
			          </td>
			       </s:if>
			       <s:else>
	    	          <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	       </s:else>    
	    	</tr>
	    </table> 		  		 
	    	</s:form>
</div>