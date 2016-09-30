<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TeamSearchId_div">
<div class="submenu_bg">
	<s:if test="#session.TEAM_ADD == true">
		<sj:a href="setUpTeam.action" targets="submenu_TeamSearchId_div" indicator="indicatorSubMenuTeamSearchId_div" cssClass="link"><s:text name="MTIAddTeam" /></sj:a> |
	</s:if>
	<s:if test="#session.TEAM_VIEW == true">
		<sj:a href="getAllTeam.action" targets="submenu_TeamSearchId_div" indicator="indicatorSubMenuTeamSearchId_div" cssClass="link"><s:text name="MTIViewTeam"/></sj:a>|
		<sj:a href="teamSearchForm.action" targets="submenu_TeamSearchId_div" indicator="indicatorSubMenuTeamSearchId_div" cssClass="link"><s:text name="MTISearchTeam"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuTeamSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="teamSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.team.search"/>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	     	 
	         <tr><td class="inputtext"><s:text name="label.header.team.name"/></td>
       	         <td class="employeeinputtd"><s:textfield name="team.teamName" cssClass="employeeinput"/></td>
       	         <td class="inputerrormessage"></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorTeamForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_TeamSearchId_div" indicator="indicatorTeamForm"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>