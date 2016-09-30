
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div id="submenu_DirectDebitFormId_div">
	<div class="submenu_bg">
		<s:if test="#session.DIRECTDEBIT_ADD == true">
			<sj:a href="setUpDirectDebit.action" targets="submenu_DirectDebitFormId_div" indicator="indicatorSubMenuDirectDebitFormId_div" cssClass="link"><s:text name="MTIAddDirectDebit" /></sj:a> |
		</s:if>
		<s:if test="#session.DIRECTDEBIT_VIEW == true">
			<sj:a href="getAllDirectDebit.action" targets="submenu_DirectDebitFormId_div" indicator="indicatorSubMenuDirectDebitFormId_div" cssClass="link"><s:text name="MTIViewDirectDebit"/></sj:a> |
			<sj:a href="directDebitSearchForm.action" targets="submenu_DirectDebitFormId_div" indicator="indicatorSubMenuDirectDebitFormId_div" cssClass="link"><s:text name="MTISearchDirectDebit"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuDirectDebitFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	
	<s:if test="dirDebit==null || dirDebit.empDirectDebitId == null">
		<div class="informationMessageList">
			<li><span><s:text name="label.header.directDebit.firstDirectDebit"/></span></li>
			<li><span><s:text name="label.header.directDebit.latestDirectDebit"/></span></li>
		</div>
	</s:if>
		
    <s:form action="insertOrUpdateDirectDebit">
    <table class="maintable">
         <tr>
        	<td align="center" ><table class="formouter">
         <tr>
            <td><table class="employeeformiinertable">
         <tr>
                <td class="formtitle">
       		       <s:if test="dirDebit==null || dirDebit.empDirectDebitId == null">
					 <s:text name="label.title.directDebit.add"/>
				   </s:if>
				   <s:else>
					 <s:text name="label.title.directDebit.edit"/>
				   </s:else>
                </td>
         </tr>
     	 <tr>
               <td class="forminner"><table class="tablealign"> 
     	 
     	<s:if test="dirDebit==null || dirDebit.empDirectDebitId == null"> 
			<tr>
				 <td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
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
				 <td class="inputerrormessage"><s:fielderror fieldName="dirDebit.empIdObj.employeeId"/></td>
	 		</tr>
	 		<tr>
				<td></td>
				<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
			</tr>
	    </s:if>
	    <s:else>
	    	<tr>
	     		<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd">
			 		<s:textfield name="dirDebit.empIdObj.empFirstName" readonly="true" cssClass="employeeinput"/>
			 		<s:hidden name="dirDebit.empIdObj.employeeId"/>
				</td>
			</tr>
		</s:else>
         <tr>
         	<td class="inputtext"><s:text name="label.form.fields.directDebit.routingNo"/></td>
         	<td class="employeeinputtd"><s:textfield name="dirDebit.routingNum" cssClass="employeeinput"/></td>
         	<td class="inputerrormessage">
				<s:fielderror fieldName="dirDebit.routingNum"/>
			</td>
         </tr>
         <tr><td class="inputtext"><s:text name="label.form.fields.directDebit.account"/></td>
         	        <td class="employeeinputtd"><s:textfield name="dirDebit.account" cssClass="employeeinput"/></td>
         	        <td class="inputerrormessage">
						<s:fielderror fieldName="dirDebit.account"/>
					</td>
         </tr>
         <tr><td class="inputtext"><s:text name="label.form.fields.directDebit.amount"/>
         						   <s:property value="#session.CURRENCY_TYPE_VALUE"/>
         						   <s:text name="label.form.common.mandatory"/> </td>
       	     <td class="employeeinputtd"><s:textfield name="dirDebit.amount" cssClass="employeeinput"/></td>
        	 <td class="inputerrormessage"><s:fielderror fieldName="dirDebit.amount"/></td>
         </tr>
         <tr><td class="inputtext"><s:text name="label.form.fields.directDebit.amountType"/></td>
         	        <td class="employeeinputtd"><s:textfield name="dirDebit.accountType" cssClass="employeeinput"/></td>
         	        <td class="inputerrormessage">
						<s:fielderror fieldName="dirDebit.accountType"/>
					</td>
         </tr>
         <tr><td class="inputtext"><s:text name="label.form.fields.directDebit.transactionType"/></td>
         	        <td class="employeeinputtd"><s:textfield name="dirDebit.transactionType" cssClass="employeeinput"/></td>
         	        <td class="inputerrormessage">
						<s:fielderror fieldName="dirDebit.transactionType"/>
					</td>
         </tr>
         <tr><td class="inputtext"><s:text name="label.form.fields.directDebit.preAccount"/></td>
         	 <td class="employeeinputtd"><s:checkbox name="dirDebit.preAccount" label="preAccount" cssClass="employeeinput"/></td>
         	 <td class="inputerrormessage">
				<s:fielderror fieldName="dirDebit.preAccount"/>
			 </td>
         </tr>
          <s:hidden name="dirDebit.empDirectDebitId"/>
    </table></td></tr></table></td></tr></table></td></tr></table>
    		 <br/>
    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorDirectDebitFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_DirectDebitFormId_div" indicator="indicatorDirectDebitFormId_div"/>
    		    </td>
    	        <td><s:if test="dirDebit==null || dirDebit.empDirectDebitId==null">
		    	        	<s:url var="resetDirectDebitForm" action="resetDirectDebitForm"></s:url>
		    	            <sj:submit href="%{resetDirectDebitForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_DirectDebitFormId_div" indicator="indicatorDirectDebitFormId_div"/>
		    	        </s:if>
		    	        <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	        </s:else></td>
    		 </tr>
    </table> 		  		 
    	</s:form>
</div>	