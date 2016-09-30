<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EssPayStub_Form_div">
<s:if test="payStub!=null || payStub.payStubId!=null">
<div class="submenu_bg">
	<s:url var="getEmployeeAllPayStub" action="getEmployeeAllPayStub">
		<s:param name="payStub.employee.employeeId" value="payStub.employee.employeeId"></s:param>
	</s:url>
	<sj:a href="%{getEmployeeAllPayStub}" targets="submenu_EssPayStub_Form_div" indicator="indicatorPayStubForm" cssClass="link"><s:text name="MTIAddEmployeePayStub"/></sj:a>
</div>
</s:if>
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
    <s:form action="insertOrUpdatePayStubAjax">
      <table class="maintable">
         <tr>
        	<td align="center" ><table class="formouter">
         <tr>
            <td><table class="employeeformiinertable">
         <tr>
                <td class="formtitle">
	        		<s:if test="payStub==null || payStub.payStubId==null">
						 <s:text name="label.title.paystub.add"/>
		  			</s:if>
		   			<s:else>
			 			<s:text name="label.title.paystub.edit"/>
		   			</s:else>
                </td>
         </tr>
		<tr>
            <td class="forminner"><table class="tablealign">  
         	<tr>
	            <td class="inputtext"><s:text name="label.form.fields.paystub.grossSalary"/></td>
         		<td class="employeeinputtd"><s:textfield name="payStub.grossSalary" cssClass="employeeinput"/></td>
         		<td class="inputerrormessage"><s:fielderror fieldName="payStub.grossSalary" /></td>
			</tr>
			<!--Button image added by, R.Deepika-->
			 <!-- Date format text added by R.Deepika -->
			<tr>
	            <td class="inputtext"><s:text name="label.form.fields.paystub.decDate"/></td>
         		<td class="employeeinputtd">
         			<sj:datepicker name="payStub.declarationDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
         		</td>
         		<td class="inputerrormessage"><s:fielderror fieldName="payStub.declarationDate" /></td>
			</tr>
			<tr>
	         	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	         	</tr> 
			<s:hidden name="payStub.employee.employeeId"/>
            <s:hidden name="payStub.payStubId"/>
    </table>
    </td></tr></table></td></tr></table></td></tr></table>
    		 <br/>
    <table align="center"> 
    	     <tr>
    		    <td>
					<img id="indicatorPayStubForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>	
    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EssPayStub_Form_div" indicator="indicatorPayStubForm"/>
    		    </td>
    		    <td><s:if test="payStub==null || payStub.payStubId==null">
		    	        	<s:url var="resetPayStubAjax" action="setUpEmpPayStub">
		    	        		<s:param name="payStub.employee.employeeId" value="payStub.employee.employeeId"></s:param>
		    	        	</s:url>	
		    	            <sj:submit href="%{resetPayStubAjax}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EssPayStub_Form_div" indicator="indicatorPayStubForm"/>
		    	    </s:if>
		    	    <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	    </s:else>
		    	</td>
    </table> 		  		 
    	</s:form>
</div>    