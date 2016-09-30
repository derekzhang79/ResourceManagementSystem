
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>
   
<div id="submenu_MessageBroadcastId_div">
<br/><img id="indicatorSubMenuMessageBroadcastId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateBroadCastMessage">
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
					 		<s:if test="message==null || message.hcmoMessageId == null">
							 <s:text name="label.title.message.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.message.edit"/>
						   </s:else>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	     	 
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.message.subject" /></td>
	         	<td class="employeeinputtd"><s:textfield name="message.subject" cssClass="employeeinput" cssStyle="width: 600px;"></s:textfield></td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="message.subject" /> </td>
	         </tr>
	         <tr>
	         	<td class="employeeinputtd"><s:hidden id="signatureId" name="sSignature"></s:hidden></td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.message.message" /></td>
	         	<td class="employeeinputtd">
	         		<s:set id="contextPath"  value="#request.get('javax.servlet.forward.context_path')" />
	         		<sjr:ckeditor
	         			id="richtextBrodastMessageCustomeEditor" 
						name="message.message" 
						rows="10" 
						cols="80" 
						width="600" 
						uploads="true"
						toolbar="MyToolbar"
						customConfig="%{contextPath}/resources/js/ckeditor/ckeditor.config.js"
	         		></sjr:ckeditor>
	         	</td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="message.message" /> </td>
	         </tr>
	         <s:hidden name="message.hcmoMessageId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorMessageFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_MessageBroadcastId_div" indicator="indicatorMessageFormId_div"/>
	    		   	</td>
		    		    <td>
		    		    	<s:url var="resetBroadCastMessage" action="resetBroadCastMessage"></s:url>
		    	            <sj:submit href="%{resetBroadCastMessage}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_MessageBroadcastId_div" indicator="indicatorMessageFormId_div"/>
						</td>
	    		 </tr>
	    </table> 		  		 
	    </s:form>
</div>
 <script type="text/javascript">
	    $(document).ready(function(){
			var x = $("#signatureId").val();
			$("#richtextBrodastMessageCustomeEditor").val('Type Your Text Here'+'<div style=\'padding-top:40px;\'>'+x+'</div>');
		});
	</script>
