<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EssDirectDebit_Form_div">
    <div class="submenu_bg">
	   <s:set name="UniqueDirDebitEmployeeId" value="dirDebit.empIdObj.employeeId"></s:set>
	   <s:url var="remoteDirDebForm" value="/setUpEmpDirectDebit.action">
		 <s:param name="dirDebit.empIdObj.employeeId" value="#UniqueDirDebitEmployeeId"></s:param>
	   </s:url>
	   <s:set name="UniqueDirDebitEmployeeId" value="dirDebit.empIdObj.employeeId"></s:set>
	   <s:url var="remoteDirDebView" value="/getEmployeeAllDirectDebit.action">
		 <s:param name="dirDebit.empIdObj.employeeId" value="#UniqueDirDebitEmployeeId"></s:param>
	    </s:url>
		<s:if test="#session.DIRECTDEBIT_ADD==true">
		 <sj:a href="%{remoteDirDebForm}" indicator="indicatorDirDebList" targets="submenu_EssDirectDebit_Form_div" cssClass="link"><s:text name="label.header.directDebit.add"/></sj:a> |
		</s:if>
		 <s:if test="#session.DIRECTDEBIT_VIEW==true">
			<sj:a href="%{remoteDirDebView}" indicator="indicatorDirDebList" targets="submenu_EssDirectDebit_Form_div" cssClass="link"><s:text name="label.header.directDebit.view"/></sj:a>
		</s:if>
	</div>		
<br />
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

	<div class="informationMessageList">
		<li><span><s:text name="label.header.directDebit.firstDirectDebit"/></span></li>
		<li><span><s:text name="label.header.directDebit.latestDirectDebit"/></span></li>
	</div>
	
    <s:form action="insertOrUpdateDirectDebitAjax">
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
     	 
				
        <tr><td class="inputtext"><s:text name="label.form.fields.directDebit.routingNo"/></td>
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
         <tr><td class="inputtext"><s:text name="label.form.fields.directDebit.amount"/> <s:property value="#session.CURRENCY_TYPE_VALUE"/> <s:text name="label.form.common.mandatory"/></td>
         	        <td class="employeeinputtd">
         	        	<s:textfield name="dirDebit.amount" cssClass="employeeinput"/></td>
         	        	<td class="inputerrormessage">
							<s:fielderror fieldName="dirDebit.amount"/>
						</td>
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
          <s:hidden name="dirDebit.empIdObj.employeeId"/>
    </table>
    </td></tr></table></td></tr></table></td></tr></table>
    		 <br/>
    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorDirDebitForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EssDirectDebit_Form_div" indicator="indicatorDirDebitForm"/>
    		    </td>
    	        <td><s:if test="dirDebit==null || dirDebit.empDirectDebitId == null">
		    	        	<s:url var="resetDirectFormAjax" action="setUpEmpDirectDebit">
		    	        		<s:param name="dirDebit.empIdObj.employeeId" value="dirDebit.empIdObj.employeeId"></s:param></s:url>
		    	            <sj:submit href="%{resetDirectFormAjax}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EssDirectDebit_Form_div" indicator="indicatorDirDebitForm"/>
		    	    </s:if>
		    	    <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	    </s:else>
		    	</td>
    		 </tr>
    </table> 		  		 
</s:form>
</div>