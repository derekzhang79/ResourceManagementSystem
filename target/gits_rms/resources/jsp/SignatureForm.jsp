<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>

<div id="submenu_SignatureFormId_div">
<div class="submenu_bg">
		<sj:a href="setUpSignature.action" targets="submenu_SignatureFormId_div" indicator="indicatorSubMenuSignatureFormId_div" cssClass="link"><s:text name="MTIAddSignature" /></sj:a> |
		<sj:a href="getAllSignature.action" targets="submenu_SignatureFormId_div" indicator="indicatorSubMenuSignatureFormId_div" cssClass="link"><s:text name="MTIViewSignature"/></sj:a> |
		<sj:a href="signatureSearchForm.action" targets="submenu_SignatureFormId_div" indicator="indicatorSubMenuSignatureFormId_div" cssClass="link"><s:text name="MTISearchSignature"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuSignatureFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:if test="signature==null || signature.hcmoSignatureId == null">
		<div class="informationMessageList">
			<li><span><s:text name="label.header.signature.firstSignature"/></span></li>
			<li><span><s:text name="label.header.signature.latestSignature"/></span></li>
		</div>
	</s:if>
	    <s:form action="insertOrUpdateSignature">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
		        		   <s:if test="signature==null || signature.hcmoSignatureId == null">
							 <s:text name="label.title.signature.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.signature.edit"/>
						   </s:else>
	                </td>
	         </tr>
	     	 <tr>
	               <td class="forminner">
	               <table class="tablealign"> 
	           		<tr>
	        	        <td class="inputtext"><s:text name="label.form.fields.signature.name"/></td>
	         	        <td class="employeeinputtd">
		         	        <s:set id="contextPath"  value="#request.get('javax.servlet.forward.context_path')" />
			         		<sjr:ckeditor
			         			id="richtextComposeMessageCustomeEditor" 
								name="signature.signatureName" 
								rows="10" 
								cols="80" 
								width="600" 
								uploads="true"
								toolbar="MyToolbar"
								customConfig="%{contextPath}/resources/js/ckeditor/ckeditor.config.js"
			         		></sjr:ckeditor>
	         	        <td class="inputerrormessage">
							<s:fielderror fieldName="signature.signatureName" />
 			 			</td>
	          	   </tr>
	          	   
	          	   <tr><td class="inputtext"><s:text name="label.form.fields.signature.preSignature"/></td>
         			   <td class="employeeinputtd"><s:checkbox name="signature.preSignature" label="preSignature" cssClass="employeeinput"/></td>
         		   </tr>
	            <s:hidden name="signature.hcmoSignatureId"/>
	    		</table></td></tr></table></td></tr></table></td></tr></table>
	    		<br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorSignatureFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_SignatureFormId_div" indicator="indicatorSignatureFormId_div"/>
	    		    </td>
	    		    <s:if test="signature==null || signature.hcmoSignatureId==null">
		    		    <td>
		    		    	<s:url var="resetSignature" action="resetSignature"></s:url>
		    	            <sj:submit href="%{resetSignature}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_SignatureFormId_div" indicator="indicatorSignatureFormId_div"/>
						</td>
					</s:if>
					<s:else>
	    	               <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>