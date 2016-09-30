
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_DirectDebitSearchFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.DIRECTDEBIT_ADD == true">
			<sj:a href="setUpDirectDebit.action" targets="submenu_DirectDebitSearchFormId_div" indicator="indicatorSubMenuDirectDebitSearchFormId_div" cssClass="link"><s:text name="MTIAddDirectDebit" /></sj:a> |
		</s:if>
		<s:if test="#session.DIRECTDEBIT_VIEW == true">
			<sj:a href="getAllDirectDebit.action" targets="submenu_DirectDebitSearchFormId_div" indicator="indicatorSubMenuDirectDebitSearchFormId_div" cssClass="link"><s:text name="MTIViewDirectDebit"/></sj:a> |
			<sj:a href="directDebitSearchForm.action" targets="submenu_DirectDebitSearchFormId_div" indicator="indicatorSubMenuDirectDebitSearchFormId_div" cssClass="link"><s:text name="MTISearchDirectDebit"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuDirectDebitSearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		
	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	   
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="directDebitSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	         <tr>
	                <td class="formtitle">
						 <s:text name="label.title.directDebit.search"/>
	                </td>
	         </tr>
	          <tr>
	              <td class="forminner"><table class="tablealign">
	     	 <tr>
	            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
     			<td class="employeeinputtd">
     				<sj:autocompleter  
					    name="dirDebit.empIdObj.employeeId"
						list="#request.APPL_EMPLOYEE_LIST"
						listKey="employeeId"
						listValue="empFullName"
					    selectBox="true"
					    selectBoxIcon="true"
					    cssClass="employeeselect"
		    		/>
				 </td>
				 <td class="inputerrormessage"></td>
         	</tr>
         	<!-- autocomplete text added by, R.Deepika-->
         	<tr>
						<td></td>
						<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
					</tr>
         		
         	<tr><td class="inputtext"><s:text name="label.header.directDebit.routingNo"/></td>
       	        <td class="employeeinputtd"><s:textfield name="dirDebit.routingNum" cssClass="employeeinput"/></td>
         	</tr>
	        <tr><td class="inputtext"><s:text name="label.header.directDebit.account"/></td>
       	        <td class="employeeinputtd"><s:textfield name="dirDebit.account" cssClass="employeeinput"/></td>
         	</tr>
	        <tr><td class="inputtext"><s:text name="label.header.directDebit.amount"/>
         	       	<s:text name="currencyTypeValue"/></td>
	         	    <td class="employeeinputtd">
	         	       	<s:textfield name="dirDebit.amount" cssClass="employeeinput"/>
	         	    </td>
         	</tr>
            <tr><td class="inputtext"><s:text name="label.header.directDebit.accountType"/></td>
       	        <td class="employeeinputtd"><s:textfield name="dirDebit.accountType" cssClass="employeeinput"/></td>
         	</tr>
	        <tr><td class="inputtext"><s:text name="label.header.directDebit.transactionType"/></td>
       	        <td class="employeeinputtd"><s:textfield name="dirDebit.transactionType" cssClass="employeeinput"/></td>
         	</tr>
	   	 </table></td></tr></table></td></tr></table></td></tr></table>
	    		 <br/>
	    <table align="center"> 
   	     <tr>
   		    <td>
				<img id="indicatorDirectDebitSearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_DirectDebitSearchFormId_div" indicator="indicatorDirectDebitSearchFormId_div"/>
   		    </td>
   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
   		 </tr>
	    </table> 		  		 
   	</s:form>
</div>