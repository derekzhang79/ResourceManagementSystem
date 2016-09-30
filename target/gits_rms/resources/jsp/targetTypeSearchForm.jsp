<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TargetTypeSearchForm_div">
<div class="submenu_bg">
	<s:if test="#session.TARGETSTYPE_ADD == true">
		<sj:a href="setUpTargetType.action" targets="submenu_TargetTypeSearchForm_div" indicator="indicatorSubMenuTargetSearchForm" cssClass="link"><s:text name="MTIAddTargetType" /></sj:a> |
	</s:if>
	<s:if test="#session.TARGETSTYPE_VIEW == true">
		<sj:a href="getAllTargetType.action" targets="submenu_TargetTypeSearchForm_div" indicator="indicatorSubMenuTargetSearchForm" cssClass="link"><s:text name="MTIViewTargetType"/></sj:a> |
		<sj:a href="targetTypeSearchForm.action" targets="submenu_TargetTypeSearchForm_div" indicator="indicatorSubMenuTargetSearchForm" cssClass="link"><s:text name="MTISearchTargetType"/></sj:a>
	</s:if>
</div><br/>
	<img id="indicatorSubMenuTargetSearchForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="targetTypeSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
             	<td class="formtitle">
					<s:text name="label.title.targetType.search"/>
                </td>
	         </tr>
	     	 <tr>
	         	<td class="forminner"><table class="tablealign"> 
	         <tr>
	         	<td class="inputtext"><s:text name="label.header.targets.type"/></td>
	         	<td class="employeeinputtd"><s:textfield name="targetType.targetTypeName" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage"></td>
	         </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table><br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorTargetTypeSearchForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_TargetTypeSearchForm_div" indicator="indicatorTargetTypeSearchForm"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>