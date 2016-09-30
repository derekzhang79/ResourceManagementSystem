<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_SignatureSearchId_div">
<div class="submenu_bg">
		<sj:a href="setUpSignature.action" targets="submenu_SignatureSearchId_div" indicator="indicatorSubMenuSignatureSearchId_div" cssClass="link"><s:text name="MTIAddSignature" /></sj:a> |
		<sj:a href="getAllSignature.action" targets="submenu_SignatureSearchId_div" indicator="indicatorSubMenuSignatureSearchId_div" cssClass="link"><s:text name="MTIViewSignature"/></sj:a> |
		<sj:a href="signatureSearchForm.action" targets="submenu_SignatureSearchId_div" indicator="indicatorSubMenuSignatureSearchId_div" cssClass="link"><s:text name="MTISearchSignature"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuSignatureSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="signatureSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.signature.search"/>
	                </td>
	         </tr>
	     	 <tr>
	              <td class="forminner">
	         		<table class="tablealign"> 
         			<tr><td class="inputtext"><s:text name="label.header.signature.name"/></td>
       	         	<td class="employeeinputtd"><s:textfield name="signature.signatureName" cssClass="employeeinput"/></td>
       	         	<td class="inputerrormessage"></td>
	         	</tr>
	    	 </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td>
						<img id="indicatorSignatureForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_SignatureSearchId_div" indicator="indicatorSignatureForm"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
	    </table> 		  		 
   	</s:form>
</div>