<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@page import="com.gits.rms.vo.PayStubVO"%>
<%@page import="com.gits.rms.vo.PayStubDeductionsVO"%>

<div id="ess_employee_paystub_main_div">
	<img id="indicatorEssPayStubList" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<div id="submen_employee_paystub_div">
		 <s:set name="employeeId" value="payStub.employee.employeeId"></s:set>
	<div class="submenu_bg">
		<s:url var="getEmployeeAllPayStub" action="getEmployeeAllPayStub">
			<s:param name="payStub.employee.employeeId" value="employeeId"></s:param>
		</s:url>
		<sj:a href="%{getEmployeeAllPayStub}" targets="ess_employee_paystub_main_div" indicator="indicatorEssPayStubList" cssClass="link"><s:text name="MTIAddEmployeePayStub"/></sj:a>
	</div>
	<br/>
	<s:div cssClass="helpinformationmessage">
   		<s:text name="label.header.paystubDeduction.msg.info"/>
	</s:div>
					    
		
		<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		<jsp:include page="/resources/jsp/common/messages.jsp" flush="true"/>
	<div class="informationMessageSingle"><li><span><s:text name="label.title.paystub.list"/></span></li></div>
		<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
		  <s:text name="label.header.paystub.empName" var="HEmployeeName"></s:text>
		  <s:text name="label.header.paystub.grossSalary" var="HGrossSalary"></s:text>
		  <s:text name="label.header.paystub.decDate" var="HDeclarationdate"></s:text>
		  <s:text name="label.header.paystub.netSalary" var="HNetSalary"></s:text>
		  <s:text name="label.common.link.view" var="HView"></s:text>
		  <s:text name="label.common.link.edit" var="HEdit"></s:text>
		  <s:text name="label.common.link.delete" var="HDelete"></s:text>
		  		   
		  <display:table class="tableborder" name="payStubList" id="payStubList" export="false">
		      <%
		    	try{
		    		String sPayStubId = ((PayStubVO)pageContext.getAttribute("payStubList")).getPayStubId().toString();
		    		Double str = ((PayStubVO)pageContext.getAttribute("payStubList")).getGrossSalary();
		    		String sPayStubGrossSalary = Double.toString(str);
		        	request.setAttribute("PayStubId", sPayStubId); 
		        	request.setAttribute("sPayStubGrossSalary", sPayStubGrossSalary); 
		        	String sPayStubEmpId = ((PayStubVO)pageContext.getAttribute("payStubList")).getEmployee().getEmployeeId().toString();
		        	request.setAttribute("EmployeeId", sPayStubEmpId);
		    	}catch(NullPointerException ne){
		        }    	
		      %>
		      
		      
		     
		    <display:column property="employee.empFullName" title="${HEmployeeName}" headerClass="sortable"/>
		    <display:column property="grossSalary" title="${HGrossSalary}" headerClass="sortable"/>
		    <display:column property="declarationDate" title="${HDeclarationdate}" headerClass="sortable"/>
		    <display:column property="netSalary" title="${HNetSalary}" headerClass="sortable"/>
		    <display:setProperty name="empId" value="payStub.employee.employeeId"></display:setProperty>
		    
		    
		    
		    <s:if test="#session.PAYSTUB_VIEW==true">
		    	<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="payStubEssView" action="payStubEssView" escapeAmp="false">
						<s:param name="payStub.payStubId" value="#request.PayStubId"></s:param>
						<s:param name="payStub.employee.employeeId" value="#request.EmployeeId"></s:param>
					</s:url>
					<sj:a href="%{payStubEssView}" targets="ess_employee_paystub_main_div" indicator="indicatorEssPayStubList"><s:text name="View"/></sj:a>
				</display:column>
			</s:if>
			<s:if test="#session.PAYSTUB_UPDATE==true">
		    	<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="setUpEmpPayStub" action="setUpEmpPayStub" escapeAmp="false">
						<s:param name="payStub.payStubId" value="#request.PayStubId"></s:param>
						<s:param name="payStub.employee.employeeId" value="#request.EmployeeId"></s:param>
					</s:url>
					<sj:a href="%{setUpEmpPayStub}" targets="ess_employee_paystub_main_div" indicator="indicatorEssPayStubList"><s:text name="Edit"/></sj:a>
				</display:column>
			</s:if>
			<s:if test="#session.PAYSTUB_DELETE==true">
		    	<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="deleteEssPayStub" action="deleteEssPayStub" escapeAmp="false">
						<s:param name="payStub.payStubId" value="#request.PayStubId"></s:param>
						<s:param name="payStub.employee.employeeId" value="#request.EmployeeId"></s:param>
					</s:url>
					<sj:a href="%{deleteEssPayStub}" targets="ess_employee_paystub_main_div" indicator="indicatorEssPayStubList"><s:text name="Delete"/></sj:a>
				</display:column> 
			</s:if>
		  </display:table>
		   
	</div> 
	<s:if test="#session.PAYSTUB_ADD == true"> 
	<div id="employeeEssPayStubDeductionDivId">
		<s:form action="insertOrUpdateEssPayStubDeduction">
			<table class="maintable">
	      		<tr>
			        <td align="center"><table class="formouter">
			      <tr>
	         		<td><table class="employeeformiinertable">
			      <tr>
			          <td class="formtitle">
			       		<s:if test="payStubDeduction==null || payStubDeduction.payStubDeductionId==null">
							 <s:text name="label.title.paystubDeduction.add"/>
			  			</s:if>
			   			<s:else>
				 			<s:text name="label.title.paystubDeduction.edit"/>
			   			</s:else>
			          </td>
			       </tr>
	       		   <tr>
	               		<td class="forminner"><table class="tablealign">
			     	   	<tr>
		         			<td class="inputtext"><s:text name="label.form.fields.deduction.name"/></td>
			         		<td class="employeeinputtd">
				            	<sj:autocompleter  
								    name="payStubDeduction.deduction.deductionId"
								    id="payStubDeduction.deduction.deductionId"
									list="#request.APPL_DEDUCTION_LIST"
									listKey="deductionId"
									listValue="deductionName"
								    selectBox="true"
								    selectBoxIcon="true"
								    cssClass="employeeselect"
						    	/>
							</td>
			         		<td class="inputerrormessage"><s:fielderror fieldName="payStubDeduction.deduction.deductionId" /></td>
		        		</tr>
		        		<tr>
		            		<td class="inputtext"><s:text name="label.header.deduction.type"/></td>
			         		<td class="employeeinputtd"><s:textfield name="payStubDeduction.deduction.deductionType" id="payStubDeduction.deduction.deductionType" readonly="true" onfocus="javascript:getDeduction()" cssClass="employeeinput"/></td>
						</tr>
						<tr>
		            		<td class="inputtext"><s:text name="label.header.deduction.mode"/></td>
			         		<td class="employeeinputtd"><s:textfield name="payStubDeduction.deduction.deductionMode" id="payStubDeduction.deduction.deductionMode" readonly="true" cssClass="employeeinput"/></td>
						</tr>
						<tr>
							<td class="inputtext"><s:text name="label.form.fields.deduction.amount"/></td>
							<td class="employeeinputtd"><s:textfield name="payStubDeduction.deductionAmount" id="payStubDeduction.deductionAmount" cssClass="employeeinput"/></td>
							<td class="inputerrormessage"><s:fielderror fieldName="payStubDeduction.deductionAmount" /></td>
						</tr>
						<tr>
		            		<td class="inputtext"><s:text name="label.header.deduction.amountDeduced"/></td>
			         		<td class="employeeinputtd"><s:textfield name="payStubDeduction.amountDeduced" id="payStubDeduction.amountDeduced" readonly="true" onfocus="javascript:getDeductionAmount()" cssClass="employeeinput"/></td>
						</tr>
						<!--Button image added by, R.Deepika-->
						<tr>
							<td class="inputtext"><s:text name="label.form.fields.deduction.effDate"/></td>
		         			<td class="employeeinputtd">
			       	       		<sj:datepicker name="payStubDeduction.effectiveDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput"/>
				       	       	<s:text name="label.date.format"/>
				         	</td>
				         	<td class="inputerrormessage"><s:fielderror fieldName="payStubDeduction.effectiveDate" /></td>
         				</tr>
         				<s:set var="paystubGrossSalary" value="#request.sPayStubGrossSalary"></s:set>
         				<s:hidden id="paystubGrossSalary" name="paystubGrossSalary"/>
         				<s:hidden id="payStub.payStubId" name="payStub.payStubId"/>
         				<s:hidden id="payStub.employee.employeeId" name="payStub.employee.employeeId"/>
         				<s:hidden id="payStubDeduction.payStubDeductionId" name="payStubDeduction.payStubDeductionId"/>
		    </table></td></tr></table></td></tr></table></td></tr></table><br/>
		    <table align="center">
	    	     <tr>
	    		    <td>
						<img id="indicatorEssPayStubAddForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="ess_employee_paystub_main_div" indicator="indicatorEssPayStubAddForm"/>
					</td>
					<s:if test="payStub==null || payStub.payStubId==null">
				         <td>
		    		    	<s:url var="resetEssPayStubForm" action="resetEssPayStubForm"></s:url>
		    	            <sj:submit href="%{resetEssPayStubForm}"  key="button.label.reset" cssClass="submitbutton117" targets="ess_employee_paystub_main_div" indicator="indicatorEssPayStubAddForm"/>
			            </td>
			       </s:if>
			       <s:else>
	    	            <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table>    		 
	   	</s:form>
	</div> 
</s:if>
	<s:if test="paystubDeductionCount != 0">
	<div id="submen_ess_employee_paystub_deduction_div">
		<img id="indicatorSubMenuPaystubDeduction" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
		<div class="informationMessageSingle"><li><span><s:text name="label.title.paystubDeduction.list"/></span></li></div>
		<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
		<s:text name="label.header.deduction.name" var="HPayStubDeductionName"></s:text>
		<s:text name="label.header.deduction.type" var="HPayStubDeductionType"></s:text>
		<s:text name="label.header.deduction.mode" var="HPayStubDeductionMode"></s:text>
		<s:text name="label.header.deduction.amount" var="HPayStubDeductionAmount"></s:text>
		<s:text name="label.header.deduction.effDate" var="HPayStubDeductionEffectiveDate"></s:text>
		<s:text name="label.common.link.view" var="HView"></s:text>
		<s:text name="label.common.link.edit" var="HEdit"></s:text>
		<s:text name="label.common.link.delete" var="HDelete"></s:text>
		  		   
		<display:table class="tableborder" name="payStubDeductionList" id="payStubDeductionList" export="false">
		    <%
		  		try{
		  			String sPayStubDeductionId = ((PayStubDeductionsVO)pageContext.getAttribute("payStubDeductionList")).getPayStubDeductionId().toString();
		      		request.setAttribute("sPayStubDeductionId", sPayStubDeductionId);   		
		  		}catch(NullPointerException ne){
		  				ne.printStackTrace();
		      	}    	
		    %>
		
			<display:column property="deduction.deductionName" title="${HPayStubDeductionName}" headerClass="sortable"/>
			<display:column property="deduction.deductionType" title="${HPayStubDeductionType}" headerClass="sortable"/>
			<display:column property="deduction.deductionMode" title="${HPayStubDeductionMode}" headerClass="sortable"/>
		  	<display:column property="deductionAmount" title="${HPayStubDeductionAmount}" headerClass="sortable"/>
		  	<display:column property="effectiveDate" title="${HPayStubDeductionEffectiveDate}" format="{0,date,MM-dd-yyyy}" headerClass="sortable"/>
		  	
		  	<s:if test="#session.PAYSTUB_VIEW==true">
		    	<display:column title="${HView}" class="viewedit" media="html">
					<s:url var="payStubDeductionEssView" action="payStubDeductionEssView" escapeAmp="false">
						<s:param name="payStubDeduction.payStubDeductionId" value="#request.sPayStubDeductionId"></s:param>
						<s:param name="payStub.payStubId" value="payStub.payStubId"></s:param>
						<s:param name="payStub.employee.employeeId" value="payStub.employee.employeeId"></s:param>
					</s:url>
					<sj:a href="%{payStubDeductionEssView}" targets="ess_employee_paystub_main_div" indicator="indicatorSubMenuPaystubDeduction"><s:text name="View"/></sj:a>
				</display:column>
			</s:if>
			<s:if test="#session.PAYSTUB_UPDATE==true">
		    	<display:column title="${HEdit}" class="viewedit" media="html">
					<s:url var="setUpEssPayStubDeduction" action="setUpEssPayStubDeduction" escapeAmp="false">
						<s:param name="payStubDeduction.payStubDeductionId" value="#request.sPayStubDeductionId"></s:param>
						<s:param name="payStub.payStubId" value="payStub.payStubId"></s:param>
						<s:param name="payStub.employee.employeeId" value="payStub.employee.employeeId"></s:param>
					</s:url>
					<sj:a href="%{setUpEssPayStubDeduction}" targets="ess_employee_paystub_main_div" indicator="indicatorSubMenuPaystubDeduction"><s:text name="Edit"/></sj:a>
				</display:column>
			</s:if>
		    <s:if test="#session.PAYSTUB_DELETE==true">
		    	<display:column title="${HDelete}" class="viewedit" media="html">
					<s:url var="deleteEssPayStubDeduction" action="deleteEssPayStubDeduction" escapeAmp="false">
						<s:param name="payStubDeduction.payStubDeductionId" value="#request.sPayStubDeductionId"></s:param>
						<s:param name="payStub.payStubId" value="payStub.payStubId"></s:param>
						<s:param name="payStub.employee.employeeId" value="payStub.employee.employeeId"></s:param>
					</s:url>
					<sj:a href="%{deleteEssPayStubDeduction}" targets="ess_employee_paystub_main_div" indicator="indicatorSubMenuPaystubDeduction"><s:text name="Delete"/></sj:a>
				</display:column> 
			</s:if>
		</display:table>
	</div>
	</s:if>
</div>  