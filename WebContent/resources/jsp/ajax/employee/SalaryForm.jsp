<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EssSalary_Form_div">
<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>

<s:form action="insertOrUpdateSalaryAjax">
	<table class="maintable">
      <tr>
        <td align="center" ><table class="formouter">
          <tr>
            <td><table class="employeeformiinertable">
                <tr>
                  <td class="formtitle">
                  
                  		<s:if test="sal==null || sal.hcmosalaryId == null">
						<s:text name="label.title.salary.add" />
						</s:if> <s:else>
						<s:text name="label.title.salary.edit" />
						</s:else>
                  </td>
                </tr>
                <tr>
                  <td class="forminner"><table class="tablealign">
			
		<tr>
			<td class="inputtext">
				<s:text	name="label.form.fields.salary.name" />
				<s:property value="#session.CURRENCY_TYPE_VALUE"/>
				<s:text name="label.form.common.mandatory"/>
			</td>
			<td class="employeeinputtd">
				<s:textfield name="sal.salary" cssClass="employeeinput" />
			</td>
			<td class="inputerrormessage">
                <s:fielderror fieldName="sal.salary" />
            </td>
		</tr>
		<!--Button image added by, R.Deepika-->
		<tr>
			<td class="inputtext"><s:text name="label.form.fields.salary.decDate" /></td>
			<td class="employeeinputtd">
       	        <sj:datepicker name="sal.declarationDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
       	       <s:text name="label.date.format"/>
			</td>
			<td class="inputerrormessage">
                <s:fielderror fieldName="sal.declarationDate" />
            </td>
		</tr>
		
	</table>
	</td>
	</tr>
	</table>
	</td>
	</tr>
	</table>
	</td>
	</tr>
	</table>
	<br />
	<table align="center">
		<tr>
			<td>
				<img id="indicatorSalForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
				<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EssSalary_Form_div" indicator="indicatorSalForm"/>
			</td>
			<td><s:if test="sal==null || sal.hcmosalaryId == null">
		    	        	<s:url var="resetSalaryFormAjax" action="setUpEmpSalary">
		    	        		<s:param name="sal.empIdObj.employeeId" value="sal.empIdObj.employeeId"></s:param></s:url>
		    	            <sj:submit href="%{resetSalaryFormAjax}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_EssSalary_Form_div" indicator="indicatorSalForm"/>
		    	    </s:if>
		    	    <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	    </s:else>
		    	</td>
		</tr>
		
	</table>
		<s:hidden name="sal.empIdObj.employeeId" />
		<s:hidden name="sal.hcmosalaryId"/>
</s:form>
</div>