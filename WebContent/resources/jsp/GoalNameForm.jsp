<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
    
	<s:form action="insertGoal">
	     <table class="maintable">
	        <tr>
              <td style="text-align: center;font-weight: bold;">
       		   <s:if test="goal==null || goal.hcmoGoalId == null">
				 <s:text name="label.title.goal.add"/>
			   </s:if>
			   <s:else>
				 <s:text name="label.title.goal.edit"/>
			   </s:else>
              </td>
	        </tr>
	        <tr>
	        	<td>
	        		<s:text name="label.form.fields.goal.name"/>
	        		<s:textfield name="goal.goalName" id="goalName" cssClass="employeeinput"/>
	        		<div id="FieldError" style="visibility: hidden;color: red;">Goal Name is required.</div>
	        	</td>
	        </tr>
	    <s:hidden name="goal.hcmoGoalId"/>
	    <s:hidden name="goalNameDiv" value="YES"></s:hidden>
	    </table>
	    <br/>
	    <table align="center"> 
   	     <tr>
   		    <td>
				<img id="indicatorGoalForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="subtag_Goal_div" indicator="indicatorGoalForm" onBeforeTopics="goalFieldValidate" onSuccessTopics="insertSuccess"></sj:submit>
   		    </td>
   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
   		 </tr>
	    </table> 		  		 
   	</s:form>
   	 <script type="text/javascript">
    $.subscribe('goalFieldValidate', function(event,data) {
        if ($('#goalName').val() == null || $('#goalName').val() == '') {
        	$('#FieldError').css('visibility', 'visible');
        	event.originalEvent.options.submit = false;
		}else{
			$('#FieldError').css('visibility', 'hidden');
		}
    });
    $.subscribe('insertSuccess', function(event,data) {
    	$('#goalName').val('');
   	});
    </script>