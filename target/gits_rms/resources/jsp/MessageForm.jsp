<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>
   
<div id="submenu_MessageFormId_div">
	<br/><img id="indicatorSubMenuMessageFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateMessage">
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
	         	<td class="inputtext"><s:text name="label.form.fields.message.receiver"/></td>
	     	    <td class="employeeinputtd"><s:textfield name="message.receiver" cssClass="employeeinput" cssStyle="width: 600px;"></s:textfield></td>
	       	    <td class="inputerrormessage"><s:fielderror fieldName="message.receiver" /> </td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.message.cc" /></td>
	         	<td class="employeeinputtd"><s:textfield name="message.cc" cssClass="employeeinput" cssStyle="width: 600px;"></s:textfield></td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="message.cc" /> </td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.message.bcc" /></td>
	         	<td class="employeeinputtd"><s:textfield name="message.bcc" cssClass="employeeinput" cssStyle="width: 600px;"></s:textfield></td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="message.bcc" /></td>
	         </tr>
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
					<!--text area size has changed:venkat -->
	         		<%-- <s:textarea name="message.message" cssClass="employeetextarea" rows="4" cols="26" /> --%>
	         		<s:set id="contextPath"  value="#request.get('javax.servlet.forward.context_path')" />
	         		<sjr:ckeditor
	         			id="richtextComposeMessageCustomeEditor" 
						name="message.message" 
						rows="10" 
						cols="80" 
						width="600" 
						uploads="true"
						toolbar="MyToolbar"
						customConfig="%{contextPath}/resources/js/ckeditor/ckeditor.config.js"
	         		    ></sjr:ckeditor>
	         	</td>
	         	<td class="inputerrormessage"><s:fielderror fieldName="message.message" /></td>
	         </tr>
	         <s:hidden name="message.hcmoMessageId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorMessageForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_MessageFormId_div" indicator="indicatorMessageForm"/>
	    		   	</td>
	    		    <td>
	    		    	<s:url var="resetComposeMessage" action="resetComposeMessage"></s:url>
	    	            <sj:submit href="%{resetComposeMessage}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_MessageFormId_div" indicator="indicatorMessageForm"/>
					</td>
	    		 </tr>
	    </table> 		  		 
	    </s:form>
</div>

  <script type="text/javascript">
    $(document).ready(function(){
			var x = $("#signatureId").val();
			$("#richtextComposeMessageCustomeEditor").val('Type Your Text Here'+'<div style=\'padding-top:40px;\'>'+x+'</div>');
		});
	</script>