<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TargetsSearchId_div">
<div class="submenu_bg">
	<s:if test="#session.TARGETS_ADD == true">
		<sj:a href="setUpTargets.action" targets="submenu_TargetsSearchId_div" indicator="indicatorSubMenuTargetsSearchId_div" cssClass="link"><s:text name="MTIAddTargets" /></sj:a> |
	</s:if>
	<s:if test="#session.TARGETS_VIEW == true">
		<sj:a href="getAllTargets.action" targets="submenu_TargetsSearchId_div" indicator="indicatorSubMenuTargetsSearchId_div" cssClass="link"><s:text name="MTIViewTargets"/></sj:a> |
		<sj:a href="targetsSearchForm.action" targets="submenu_TargetsSearchId_div" indicator="indicatorSubMenuTargetsSearchId_div" cssClass="link"><s:text name="MTISearchTargets"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuTargetsSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="targetsSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
              <td class="formtitle">
				 <s:text name="label.title.targets.search"/>
              </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	         <tr>
	         	 <td class="inputtext"><s:text name="label.header.targets.name"/></td>
        	     <td class="employeeinputtd"><s:textfield name="target.targetName" cssClass="employeeinput"/></td>
	             <td class="inputerrormessage"></td>
	         </tr>
	         <tr><td class="inputtext"><s:text name="label.header.targets.type"/></td>
	     	     <td class="employeeinputtd">
					<s:select
						id="target.targetTypeObj.hcmoTargetTypeId"
						name="target.targetTypeObj.hcmoTargetTypeId" 
						headerKey=""
						headerValue="-- Please Select --"
						list="#request.APPL_TARGETTYPE_LIST"
						listKey="hcmoTargetTypeId"
						listValue="targetTypeName"
					    cssClass="employeeselect" 
				    />
				</td>
			</tr>
	         <tr>
	         	 <td class="inputtext"><s:text name="label.header.targets.mode"/></td>
        	     <td class="employeeinputtd">
        	     	<sj:autocompleter
						selectBox="true"
						selectBoxIcon="true"
						list="#{'Daily':'Daily','Weekly':'Weekly','Monthly':'Monthly'}"
						name="target.targetMode"
						id="target.targetMode"
        	     	/>
        	     </td>
	         </tr>
	         	<!-- autocomplete text added by, R.Deepika-->
					<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center" > 
	    	     <tr>
	    		    <td>
						<img id="indicatorTargetForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_TargetsSearchId_div" indicator="indicatorTargetForm"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div> 