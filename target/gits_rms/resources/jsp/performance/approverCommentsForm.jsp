<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_approver_comments_form_div">
<div class="submenu_bg">
	<s:if test="#session.NATIONALITY_ADD == true">
		<sj:a href="setUpNationality.action" targets="submenu_Nationality_div" indicator="indicatorSubMenuNationality_approvercommentsFormId_div" cssClass="link"><s:text name="MTIAddNationality" /></sj:a> |
	</s:if>
	<s:if test="#session.NATIONALITY_VIEW == true">
		<sj:a href="getAllNationality.action" targets="submenu_Nationality_div" indicator="indicatorSubMenuNationality_approvercommentsFormId_div" cssClass="link"><s:text name="MTIViewNationality"/></sj:a> |
		<sj:a href="nationalitySearchForm.action" targets="submenu_Nationality_div" indicator="indicatorSubMenuNationality_approvercommentsFormId_div" cssClass="link"><s:text name="MTISearchNationality"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuNationality_approvercommentsFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	    <s:form action="updateAdminComments">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		   <s:if test="nati==null || nati.nationalityId == null">
							 <s:text name="label.title.nationality.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.nationality.edit"/>
						   </s:else>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign">
	               <s:textfield name="employeeId"></s:textfield> 
	               <s:textfield name="subEmployeeID"></s:textfield>
					<s:textfield name="questionGroupIdentifiId"></s:textfield>
					<s:textfield name="quesBankGeneralInfo.hcmoQuestionGeneralInfoId"></s:textfield>
					<s:textfield name="questionGeneralInfoGroupIdListId"></s:textfield>
	     	 
	         <tr><td class="inputtext"><s:text name="label.header.question.approverComments"/></td>
	         	        <!-- textarea size has been changed ;venkat -->
	         	        <td class="employeeinputtd"><s:textarea name="approverComments" cssClass="employeetextarea" rows="4" cols="26"/></td>
	         	        <td class="inputerrormessage">
							<s:fielderror fieldName="nati.nationalityName" />
				 		</td>
	         </tr>
	            <s:hidden name="nati.nationalityId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center" > 
	    	     <tr>
	    		    <td>
						<img id="indicatorApproverCommentsForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_approver_comments_form_div" indicator="indicatorApproverCommentsForm"/>
	    		    </td>
	    		     <s:if test="nati==null || nati.nationalityId==null">
		    		    <td>
		    		    	<s:url var="resetNationality" action="resetNationality"></s:url>
		    	            <sj:submit href="%{resetNationality}"  key="button.label.reset" cssClass="resetbutton117" targets="submenu_Nationality_div" indicator="indicatorNationalityForm"/>
						</td>
					</s:if>
					<s:else>
	    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div> 