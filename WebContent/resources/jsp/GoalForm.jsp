<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
   
<div id="submenu_Goal_div">
<div class="submenu_bg">
	<sj:a href="setUpGoalForm.action" targets="submenu_Goal_div" indicator="indicatorSubMenuGoal" cssClass="link"><s:text name="MTIAddGoal" /></sj:a> |
	<sj:a href="getAllGoal.action" targets="submenu_Goal_div" indicator="indicatorSubMenuGoal" cssClass="link"><s:text name="MTIViewGoal"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuGoal" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:if test="goal==null || goal.hcmoGoalId == null">
		<div id="insertsuccessMsg"></div>
	</s:if>
	<s:else>
		<div id="updatesuccessMsg"></div>
	</s:else>
	<s:form action="insertOrUpdateGoal">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
              <td class="formtitle">
       		   <s:if test="goal==null || goal.hcmoGoalId == null">
				 <s:text name="label.title.goal.add"/>
			   </s:if>
			   <s:else>
				 <s:text name="label.title.goal.edit"/>
			   </s:else>
              </td>
	        </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign">
	        <tr>
	        	<td class="inputtext"><s:text name="label.form.fields.goal.name"/></td>
	        	<td class="employeeinputtd">
	        		<s:textfield name="goal.goalName" id="GoalName" cssClass="employeeinput"/>
	        	</td>
	        	<td class="inputerrormessage">
					<div id="FieldError" style="visibility: hidden;">Goal Name is required.</div>
				</td>
	        </tr>
	    <s:hidden name="goal.hcmoGoalId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    <br/>
	    <table align="center"> 
   	     <tr>
   		    <td>
				<img id="indicatorGoalFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_Goal_div" indicator="indicatorGoalFormId_div" onBeforeTopics="goalNameFieldValidate"/>
   		    </td>
   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
   		 </tr>
	    </table> 		  		 
   	</s:form>
</div>
  <script type="text/javascript">
    $.subscribe('goalNameFieldValidate', function(event,data) {
        if ($('#GoalName').val() == null || $('#GoalName').val() == '') {
        	$('#FieldError').css('visibility', 'visible');
        	event.originalEvent.options.submit = false;
		}else{
			$('#FieldError').css('visibility', 'hidden');
		}
    });
    </script>
 