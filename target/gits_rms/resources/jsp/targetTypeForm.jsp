<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_TargetTypeForm_div">
<div class="submenu_bg">
	<s:if test="#session.TARGETSTYPE_ADD == true">
		<sj:a href="setUpTargetType.action" targets="submenu_TargetTypeForm_div" indicator="indicatorSubMenuTargetTypeList" cssClass="link"><s:text name="MTIAddTargetType" /></sj:a> |
	</s:if>
	<s:if test="#session.TARGETSTYPE_VIEW == true">
		<sj:a href="getAllTargetType.action" targets="submenu_TargetTypeForm_div" indicator="indicatorSubMenuTargetTypeList" cssClass="link"><s:text name="MTIViewTargetType"/></sj:a> |
		<sj:a href="targetTypeSearchForm.action" targets="submenu_TargetTypeForm_div" indicator="indicatorSubMenuTargetTypeList" cssClass="link"><s:text name="MTISearchTargetType"/></sj:a>
	</s:if>
</div><br/>

	<img id="indicatorSubMenuTargetTypeList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateTargetType">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		   <s:if test="targetType==null || targetType.hcmoTargetTypeId == null">
							 <s:text name="label.title.targetType.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.targetType.edit"/>
						   </s:else>
	                </td>
	         </tr>
	     	 <tr>
	         	<td class="forminner"><table class="tablealign"> 
	     	 
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.targets.type"/></td>
	         	<td class="employeeinputtd"><s:textfield name="targetType.targetTypeName" cssClass="employeeinput"/></td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="targetType.targetTypeName" /></td>
	         </tr>
	            
	            <s:hidden name="targetType.hcmoTargetTypeId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center" > 
	    	     <tr>
	    		    <td>
						<img id="indicatorTargetTypeForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_TargetTypeForm_div" indicator="indicatorTargetTypeForm"/>
	    		    </td>
	    		     <s:if test="targetType==null || targetType.hcmoTargetTypeId == null">
		    		    <td>
		    		    	<s:url var="resetTargetType" action="resetTargetType"></s:url>
		    	            <sj:submit href="%{resetTargetType}"  key="button.label.reset" cssClass="resetbutton117" targets="submenu_TargetTypeForm_div" indicator="indicatorTargetTypeForm"/>
						</td>
					</s:if>
					<s:else>
	    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div> 