<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page import="com.gits.rms.vo.EmployeesVO"%>
<%@page import="com.gits.rms.vo.DirectDebitVO"%>

<div id="submenu_EssDirectDebit_List_div">
<img id="indicatorDirDebList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	 <s:set name="UniqueDirDebitEmployeeId" value="dirDebit.empIdObj.employeeId"></s:set>
	<s:url var="remoteDirDebForm" value="/setUpEmpDirectDebit.action">
		<s:param name="dirDebit.empIdObj.employeeId" value="#UniqueDirDebitEmployeeId"></s:param>
	</s:url>
	<s:set name="UniqueDirDebitEmployeeId" value="dirDebit.empIdObj.employeeId"></s:set>
	<s:url var="remoteDirDebView" value="/getEmployeeAllDirectDebit.action">
		<s:param name="dirDebit.empIdObj.employeeId" value="#UniqueDirDebitEmployeeId"></s:param>
	</s:url>
	<div class="submenu_bg">
	    <s:if test="#session.DIRECTDEBIT_ADD==true">
			<sj:a href="%{remoteDirDebForm}" indicator="indicatorDirDebList" targets="submenu_EssDirectDebit_List_div" cssClass="link"><s:text name="label.header.directDebit.add"/></sj:a> |
		</s:if>
		 <s:if test="#session.DIRECTDEBIT_VIEW==true">
			<sj:a href="%{remoteDirDebView}" indicator="indicatorDirDebList" targets="submenu_EssDirectDebit_List_div" cssClass="link"><s:text name="label.header.directDebit.view"/></sj:a>
		</s:if>
	</div>		

<br />
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
<div class="informationMessageSingle"><li><span><s:text name="label.title.directDebit.list"/></span></li></div>

        <s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
        <s:text name="label.header.common.empName" var="HEmpname"/>
        <s:text name="label.header.directDebit.routingNo" var="HRoutingno"/>
        <s:text name="label.header.directDebit.account" var="HAccount"/>
        <s:text name="label.header.directDebit.amount" var="HAmount"/>
        <s:text name="label.header.directDebit.accountType" var="HAccounttype"/>
        <s:text name="label.header.directDebit.transactionType" var="HTransactiontype"/>
        <s:text name="label.header.directDebit.preAccount" var="HPreaccount"/>
       
       <s:if test="#session.DIRECTDEBIT_VIEW==true">
	       <s:text name="label.header.common.view" var="HView"/>
	    </s:if>
        
        <s:if test="#session.DIRECTDEBIT_UPDATE==true">
	       <s:text name="label.header.common.edit" var="HEdit"/>
	    </s:if>
        <s:if test="#session.DIRECTDEBIT_DELETE==true">
	       <s:text name="label.header.common.delete" var="HDelete"/>
	    </s:if>
      
    <display:table class="tableborder" id="dirDebitListId" name="dirDebitList" pagesize="${NO_OF_RECORDS}" requestURI="" sort="list" defaultsort="1" defaultorder="ascending">
           <%
		  try
           {
			  String sEmpId=((DirectDebitVO)pageContext.getAttribute("dirDebitListId")).getEmpIdObj().getEmployeeId().toString();
			  request.setAttribute("sEmployeeId",sEmpId);
			  String sDebitId=((DirectDebitVO)pageContext.getAttribute("dirDebitListId")).getEmpDirectDebitId().toString();
			  request.setAttribute("sDirectDebitId",sDebitId);
		  }
           catch(NullPointerException ne){
		  }
		%>
		
            <display:column property="empIdObj.empFullName" title="${HEmpname}" sortable="false" headerClass="sortable"/>
            <display:column property="routingNum" title="${HRoutingno}" sortable="false" headerClass="sortable"/>
            <display:column property="account" title="${HAccount}" sortable="false" headerClass="sortable"/>
            <display:column property="curTypeValueForAmountField" title="${HAmount}" sortable="false" headerClass="sortable" format="$ {0,number,000.00}" style="width:130px;"/>
            <display:column property="accountType" title="${HAccounttype}" sortable="false" headerClass="sortable"/>
            <display:column property="transactionType" title="${HTransactiontype}" sortable="false" headerClass="sortable"/>
            <display:column property="preAccount" title="${HPreaccount}" sortable="false" headerClass="sortable"/>
            
            <s:if test="#session.DIRECTDEBIT_VIEW==true">
		        <display:column title="${HView}" class="viewedit" media="html">
	                    	<s:url var="directDebitView" action="vieEmpDirectDebit" escapeAmp="false">
					           	<s:param name="dirDebit.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
		       		         	<s:param name="dirDebit.empDirectDebitId" value="#request.sDirectDebitId"/>
		       		        </s:url> 
	                <sj:a href="%{directDebitView}" targets="submenu_EssDirectDebit_List_div" indicator="indicatorDirDebList"><s:text name="label.common.link.view"/></sj:a>
	     	    </display:column>
	     	</s:if>
           
           
    	    <s:if test="#session.DIRECTDEBIT_UPDATE==true">
		        <display:column title="${HEdit}" class="viewedit" media="html">
	                    	<s:url var="setUpEmpDirectDebit" action="setUpEmpDirectDebit" escapeAmp="false">
					           	<s:param name="dirDebit.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
		       		         	<s:param name="dirDebit.empDirectDebitId" value="#request.sDirectDebitId"/>
		       		        </s:url> 
	                <sj:a href="%{setUpEmpDirectDebit}" targets="submenu_EssDirectDebit_List_div" indicator="indicatorDirDebList"><s:text name="label.common.link.edit"/></sj:a>
	     	    </display:column>
	     	</s:if>
	        <s:if test="#session.DIRECTDEBIT_DELETE==true">
	     		<display:column title="${HDelete}" class="viewedit" media="html">
	                        <s:url var="deleteEmpDirectDebit" action="deleteEmpDirectDebit" escapeAmp="false">
						       <s:param name="dirDebit.empIdObj.employeeId" value="#request.sEmployeeId"></s:param>
			       		       <s:param name="dirDebit.empDirectDebitId" value="#request.sDirectDebitId"/>
		       		        </s:url>
	                <sj:a href="%{deleteEmpDirectDebit}" targets="submenu_EssDirectDebit_List_div" indicator="indicatorDirDebList"><s:text name="label.common.link.delete"/></sj:a>
	           </display:column>
	        </s:if>
	        
 </display:table>
 </div>