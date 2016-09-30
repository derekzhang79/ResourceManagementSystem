<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.DirectDebitVO"%>

<div id="submenu_directDebit_list_div_id">
	<div class="submenu_bg">
		<s:if test="#session.DIRECTDEBIT_ADD == true">
			<sj:a href="setUpDirectDebit.action" targets="submenu_directDebit_list_div_id" indicator="indicatorSubMenuDirectDebit" cssClass="link"><s:text name="MTIAddDirectDebit" /></sj:a> |
		</s:if>
		<s:if test="#session.DIRECTDEBIT_VIEW == true">
			<sj:a href="getAllDirectDebit.action" targets="submenu_directDebit_list_div_id" indicator="indicatorSubMenuDirectDebit" cssClass="link"><s:text name="MTIViewDirectDebit"/></sj:a> |
			<sj:a href="directDebitSearchForm.action" targets="submenu_directDebit_list_div_id" indicator="indicatorSubMenuDirectDebit" cssClass="link"><s:text name="MTISearchDirectDebit"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuDirectDebit" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<div class="informationMessageSingle"><li><span><s:text name="label.title.directDebit.list"/></span></li></div>
	<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
	  <s:text name="label.header.common.empName" var="HDDEmployeeName"></s:text>
	  <s:text name="label.header.directDebit.routingNo" var="HDDRoutingName"></s:text>
	  <s:text name="label.header.directDebit.account" var="HDDAccount"></s:text>
	  <s:text name="label.header.directDebit.amount" var="HDDAmount"></s:text>
	  <s:text name="label.header.directDebit.accountType" var="HDDAccountType"></s:text>
	  <s:text name="label.header.directDebit.transactionType" var="HDDTransType"></s:text>
	  <s:text name="label.header.directDebit.preAccount" var="HDDpreAccount"></s:text>
	  <s:text name="label.common.link.view" var="HView"></s:text>
	  <s:text name="label.common.link.edit" var="HEdit"></s:text>
	  <s:text name="label.common.link.delete" var="HDelete"></s:text>
	  <s:text name="label.title.directDebit.makePreferred" var="HPreferred"></s:text>
	  		   
	  <div id="display_tag_directDebitList_div_id">
		   <display:table class="tableborder" id="directDebitListId" name="dirDebitList" pagesize="${NO_OF_RECORDS}" requestURI="getAllDirectDebit.action" sort="list" defaultsort="1" defaultorder="ascending"  export="true">
		    <%
		    	try{
		    		String sDirectDebitId = ((DirectDebitVO)pageContext.getAttribute("directDebitListId")).getEmpDirectDebitId().toString();
		    		String sEmpId = ((DirectDebitVO)pageContext.getAttribute("directDebitListId")).getEmpIdObj().getEmployeeId().toString();
		    		Boolean sPrefAcc = ((DirectDebitVO)pageContext.getAttribute("directDebitListId")).isPreAccount();
		        	request.setAttribute("DirectDebitId", sDirectDebitId);	
		        	request.setAttribute("DirectDebitEmpId", sEmpId);	
		        	request.setAttribute("DirectDebitIsPref", sPrefAcc);
		    	}catch(NullPointerException ne){
		        }    	
		    %>
		    <display:column property="empIdObj.empFullName" title="${HDDEmployeeName}" sortable="true" headerClass="sortable"/>
		    
		    <s:if test="#session.DIRECTDEBIT_ROUTINGNUM_VIEW == true">
		    	<display:column property="routingNum" title="${HDDRoutingName}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.DIRECTDEBIT_ACCOUNT_VIEW == true">
		    	<display:column property="account" title="${HDDAccount}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.DIRECTDEBIT_AMOUNT_VIEW == true">
		    	<display:column property="curTypeValueForAmountField" title="${HDDAmount}" sortable="true" headerClass="sortable" format="$ {0,number,000.00}" style="width:130px;"/>
		    </s:if>
		    <s:if test="#session.DIRECTDEBIT_ACCOUNTTYPE_VIEW == true">
		    	<display:column property="accountType" title="${HDDAccountType}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.DIRECTDEBIT_TRANSACTIONTYPE_VIEW == true">
		    	<display:column property="transactionType" title="${HDDTransType}" sortable="true" headerClass="sortable"/>
		    </s:if>
		    <s:if test="#session.DIRECTDEBIT_PREACCOUNTVALUE_VIEW == true">
		    	<display:column property="preAccountValue" title="${HDDpreAccount}" />
		    </s:if>
		    
		    <display:column title="${HPreferred}" class="viewedit" media="html">
				<s:if test="#request.DirectDebitIsPref==true">
					<s:text name="label.title.directDebit.preferred"></s:text>
				</s:if>
				<s:else>
					<s:url var="listMakePreferredDirectDebit" action="makePreferred">
						<s:param name="dirDebit.empDirectDebitId" value="#request.DirectDebitId"></s:param>
						<s:param name="dirDebit.empIdObj.employeeId" value="#request.DirectDebitEmpId"></s:param>
					</s:url>
					<sj:a href="%{listMakePreferredDirectDebit}" title="Make this as Preferred Account" targets="submenu_directDebit_list_div_id" indicator="indicatorSubMenuDirectDebit"><s:text name="Toggle"/></sj:a>
				</s:else>
					
			</display:column>
		    <s:if test="#session.DIRECTDEBIT_VIEW==true">
				<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="listViewDirectDebit" action="directDebitView">
						<s:param name="dirDebit.empDirectDebitId" value="#request.DirectDebitId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_directDebit_list_div_id','%{listViewDirectDebit}','');"><s:text name="View"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.DIRECTDEBIT_UPDATE==true">
				<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="listSetUpDirectDebit" action="setUpDirectDebit">
						<s:param name="dirDebit.empDirectDebitId" value="#request.DirectDebitId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_directDebit_list_div_id','%{listSetUpDirectDebit}','');"><s:text name="Edit"/></s:a>
				</display:column>
			</s:if>
		    <s:if test="#session.DIRECTDEBIT_DELETE==true">
				<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="listDeleteDirectDebit" action="deleteDirectDebit">
						<s:param name="dirDebit.empDirectDebitId" value="#request.DirectDebitId"></s:param>
						<s:param name="dirDebit.empIdObj.employeeId" value="#request.DirectDebitEmpId"></s:param>
					</s:url>
					<s:a href="#" onclick="doPostCall('submenu_directDebit_list_div_id','%{listDeleteDirectDebit}','');"><s:text name="Delete"/></s:a>
				</display:column>
			</s:if>
		 	 <display:setProperty name="export.csv.filename" value="DirectDebit.csv"/>
			 <display:setProperty name="export.excel.filename" value="DirectDebit.xls"/>
			 <display:setProperty name="export.xml.filename" value="DirectDebit.xml"/>
		  </display:table>
	  </div>
</div>
<script type="text/javascript">
	//To submit the Sorting and Pagination of DisplayTag in a Div
   	jQuery(document).ready(function() {
   		try{
   		 	jQuery("#display_tag_directDebitList_div_id").displayTagAjax();
   		}catch(e){
   		}
  	});
</script>    