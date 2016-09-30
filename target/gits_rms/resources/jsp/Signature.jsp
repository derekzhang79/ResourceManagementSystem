<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.SignatureVO"%>

<div id="submenu_signature_list_div_id">
<div class="submenu_bg">
		<sj:a href="setUpSignature.action" targets="submenu_signature_list_div_id" indicator="indicatorSubMenuSignatureId_div" cssClass="link"><s:text name="MTIAddSignature" /></sj:a> |
		<sj:a href="getAllSignature.action" targets="submenu_signature_list_div_id" indicator="indicatorSubMenuSignatureId_div" cssClass="link"><s:text name="MTIViewSignature"/></sj:a> |
		<sj:a href="signatureSearchForm.action" targets="submenu_signature_list_div_id" indicator="indicatorSubMenuSignatureId_div" cssClass="link"><s:text name="MTISearchSignature"/></sj:a>
</div>
<br/>
<img id="indicatorSubMenuSignatureId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>

	<div class="informationMessageSingle"><li><span><s:text name="label.title.signature.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.signature.name" var="HSignatureName"></s:text>
	  <s:text name="label.header.signature.preSignature" var="HPreSignature"></s:text>
	  <s:text name="label.header.signature.makePreferred" var="HPreferred"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  		   
	  <div id="display_tag_signatureList_div_id">
		  <display:table class="tableborder" id="signatureListId" name="signatureList" pagesize="${NO_OF_RECORDS}" requestURI="getAllSignature.action" sort="list" defaultsort="1" defaultorder="ascending">
		    <%
		    	try{
		    		String sSignatureId = ((SignatureVO)pageContext.getAttribute("signatureListId")).getHcmoSignatureId().toString();
		        	request.setAttribute("SignatureId", sSignatureId);	
		        	Boolean sPrefSig = ((SignatureVO)pageContext.getAttribute("signatureListId")).isPreSignature();
		        	request.setAttribute("SignatureIsPref", sPrefSig);
		        	String sEmpId = ((SignatureVO)pageContext.getAttribute("signatureListId")).getEmpIdObj().getEmployeeId().toString();
		        	request.setAttribute("SignatureEmpId", sEmpId);	
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="signatureName" title="${HSignatureName}" sortable="true" headerClass="sortable"/>
		    <display:column property="preSignatureValue" title="${HPreSignature}" />
		    
		            <display:column title="${HPreferred}" class="viewedit" media="html">
						<s:if test="#request.SignatureIsPref==true">
							<s:text name="label.signature.length.value.preferred"></s:text>
						</s:if>
						<s:else>
						<s:url var="listMakePreferredSignature" action="makePreferredSignature">
							<s:param name="signature.hcmoSignatureId" value="#request.SignatureId"></s:param>
							<s:param name="signature.empIdObj.employeeId" value="#request.SignatureEmpId"></s:param>
						</s:url>
						<sj:a href="%{listMakePreferredSignature}" title="Make this as Preferred Signature" targets="submenu_signature_list_div_id" indicator="indicatorSubMenuSignatureId_div"><s:text name="Toggle"/></sj:a>
						</s:else>
					</display:column>
					<display:column title="${HView}" class="viewedit" media="html">
						<s:url var="signatureView" action="signatureView">
							<s:param name="signature.hcmoSignatureId" value="#request.SignatureId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_signature_list_div_id','%{signatureView}','');"><s:text name="View"/></s:a>
					</display:column>
					<display:column title="${HEdit}" class="viewedit" media="html">
						<s:url var="setUpSignature" action="setUpSignature">
							<s:param name="signature.hcmoSignatureId" value="#request.SignatureId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_signature_list_div_id','%{setUpSignature}','');"><s:text name="Edit"/></s:a>
					</display:column>
					<display:column title="${HDelete}" class="viewedit" media="html">
						<s:url var="deleteSignature" action="deleteSignature">
							<s:param name="signature.hcmoSignatureId" value="#request.SignatureId"></s:param>
						</s:url>
						<s:a href="#" onclick="doPostCall('submenu_signature_list_div_id','%{deleteSignature}','');"><s:text name="Delete"/></s:a>
					</display:column>
		  </display:table>    
	  </div>
	  
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_signatureList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>  