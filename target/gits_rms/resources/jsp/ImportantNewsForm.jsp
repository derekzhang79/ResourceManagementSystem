<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>
<div id="submenu_ImportantNewsFormId_div">
<div class="submenu_bg">
		<sj:a href="setUpImportantNews.action" targets="submenu_ImportantNewsFormId_div" indicator="indicatorSubMenuImportantNewsFormId_div" cssClass="link"><s:text name="MTIAddImportantNews" /></sj:a> |
		<sj:a href="getAllImportantNews.action" targets="submenu_ImportantNewsFormId_div" indicator="indicatorSubMenuImportantNewsFormId_div" cssClass="link"><s:text name="MTIViewImportantNews"/></sj:a> |
		<sj:a href="importantNewsSearchForm.action" targets="submenu_ImportantNewsFormId_div" indicator="indicatorSubMenuImportantNewsFormId_div" cssClass="link"><s:text name="MTISearchImportantNews"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuImportantNewsFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="insertOrUpdateImportantNews">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		   <s:if test="important==null || important.importantNewsId == null">
							 <s:text name="label.title.importantNews.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.importantNews.edit"/>
						   </s:else>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
	     	 
	         <tr><td class="inputtext"><s:text name="label.form.fields.message.subject"/></td>
	         	        <td class="employeeinputtd"><s:textfield name="important.subject" cssClass="employeeinput" cssStyle="width: 600px;"/></td>
	         	        <td class="inputerrormessage">
							<s:fielderror fieldName="important.subject" />
				 		</td>
	         </tr>
	         <tr><td class="inputtext"><s:text name="label.form.fields.message.message"/></td>
	         	        <td class="employeeinputtd">
	         	        <s:set id="contextPath"  value="#request.get('javax.servlet.forward.context_path')" />
		         		<sjr:ckeditor
		         			id="richtextBrodastMessageCustomeEditor" 
							name="important.message" 
							rows="10" 
							cols="80" 
							width="600" 
							uploads="true"
							toolbar="MyToolbar"
							customConfig="%{contextPath}/resources/js/ckeditor/ckeditor.config.js"
		         		></sjr:ckeditor>
	         	        <td class="inputerrormessage">
							<s:fielderror fieldName="important.message" />
				 		</td>
	         </tr>
	         <tr>
	         	<td class="inputtext"><s:text name="label.form.fields.importantNews.show"/></td>
	         	<td class="employeeinputtd">
	         		<s:checkbox label="holidayrepeat" name="important.showMessage" cssClass="employeecheckbox"/><br>
	         	</td>
	         </tr>
	            <s:hidden name="important.importantNewsId"/>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorSubMenuImportantNewsForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_ImportantNewsFormId_div" indicator="indicatorSubMenuImportantNewsForm"/>
	    		    </td>
	    		    <s:if test="important==null || important.importantNewsId == null">
				        <td>
		    		    	<s:url var="resetImportantNews" action="resetImportantNews"></s:url>
		    	            <sj:submit href="%{resetImportantNews}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_ImportantNewsFormId_div" indicator="indicatorSubMenuImportantNewsForm"/>
			           </td>
			       </s:if>
			       <s:else>
	    	           <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	       </s:else>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>