<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_MailConfigFormId_div">
<div class="submenu_bg">
	<%--<s:if test="#session.NATIONALITY_ADD == true">--%>
		<sj:a href="setUpMailConfig.action" targets="submenu_MailConfigFormId_div" indicator="indicatorSubMenuMailConfigFormId_div" cssClass="link"><s:text name="MTIAddMailConfiguration" /></sj:a> |
	<%--</s:if>
	<s:if test="#session.NATIONALITY_VIEW == true">--%>
		<sj:a href="getAllMailConfig.action" targets="submenu_MailConfigFormId_div" indicator="indicatorSubMenuMailConfigFormId_div" cssClass="link"><s:text name="MTIViewMailConfiguration"/></sj:a> 
	<%--</s:if>--%>
</div>
<br/>
<img id="indicatorSubMenuMailConfigFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateMailConfiguration">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		   <s:if test="mailConfig == null || mailConfig.hcmoMailConfigurationId == null">
							 <s:text name="label.title.mailConfig.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.mailConfig.edit"/>
						   </s:else>
	                </td>
	         </tr>
	     	 <tr>
             	 <td class="forminner"><table class="tablealign"> 
		         <tr><td class="inputtext"><s:text name="label.form.fields.mailConfig.smtpHost"/></td>
	        	        <td class="employeeinputtd"><s:textfield name="mailConfig.smtpHost" cssClass="employeeinput"/></td>
	        	        <td class="inputerrormessage">
						<s:fielderror fieldName="mailConfig.smtpHost" />
			 		</td>
		         </tr>
		         <tr><td class="inputtext"><s:text name="label.form.fields.mailConfig.username"/></td>
	        	        <td class="employeeinputtd"><s:textfield name="mailConfig.username" cssClass="employeeinput"/></td>
	        	        <td class="inputerrormessage">
						<s:fielderror fieldName="mailConfig.username" />
			 		</td>
		         </tr>
		         <tr><td class="inputtext"><s:text name="label.form.fields.mailConfig.password"/></td>
	        	        <td class="employeeinputtd"><s:password name="mailConfig.password" showPassword="true" cssClass="employeeinput"/></td>
	        	        <td class="inputerrormessage">
						<s:fielderror fieldName="mailConfig.password" />
			 		</td>
		         </tr>
	            <s:hidden name="mailConfig.hcmoMailConfigurationId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorMailConfigForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_MailConfigFormId_div" indicator="indicatorMailConfigForm"/>
	    		    </td>
	    		    <s:if test="mailConfig==null || mailConfig.hcmoMailConfigurationId==null">
		    		    <td>
		    		    	<s:url var="resetMailConfiguration" action="resetMailConfiguration"></s:url>
		    	            <sj:submit href="%{resetMailConfiguration}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_MailConfigFormId_div" indicator="indicatorMailConfigForm"/>
						</td>
					</s:if>
					<s:else>
	    	        	<td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>