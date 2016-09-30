<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TeamFormId_div">
<div class="submenu_bg">
	<s:if test="#session.TEAM_ADD == true">
		<sj:a href="setUpTeam.action" targets="submenu_TeamFormId_div" indicator="indicatorSubMenuTeamFormId_div" cssClass="link"><s:text name="MTIAddTeam" /></sj:a> |
	</s:if>
	<s:if test="#session.TEAM_VIEW == true">
		<sj:a href="getAllTeam.action" targets="submenu_TeamFormId_div" indicator="indicatorSubMenuTeamFormId_div" cssClass="link"><s:text name="MTIViewTeam"/></sj:a> |
		<sj:a href="teamSearchForm.action" targets="submenu_TeamFormId_div" indicator="indicatorSubMenuTeamFormId_div" cssClass="link"><s:text name="MTISearchTeam"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuTeamFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateTeam">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		   <s:if test="team==null || team.hcmoTeamId == null">
							 <s:text name="label.title.team.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.team.edit"/>
						   </s:else>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	     	 
	         <tr><td class="inputtext"><s:text name="label.form.fields.team.name"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="team.teamName" cssClass="employeeinput"/></td>
	         	        <td class="inputerrormessage">
							<s:fielderror fieldName="team.teamName" />
				 		</td>
	         </tr>
	            <s:hidden name="team.hcmoTeamId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorTeamFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_TeamFormId_div" indicator="indicatorTeamFormId_div"/>
	    		    </td>
	    		    <s:if test="team==null || team.hcmoTeamId==null">
		    		    <td>
		    		    	<s:url var="resetTeam" action="resetTeam"></s:url>
		    	            <sj:submit href="%{resetTeam}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_TeamFormId_div" indicator="indicatorTeamFormId_div"/>
						</td>
					</s:if>
					<s:else>
	    	               <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>