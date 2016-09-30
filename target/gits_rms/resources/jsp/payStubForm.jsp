<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<div id="submenu_paystub_div">
		<div class="submenu_bg">
			<s:if test="payStub==null || payStub.payStubId==null">
				<s:if test="#session.PAYSTUB_ADD == true">
				</s:if>
				<s:if test="#session.PAYSTUB_VIEW == true">
					<sj:a href="getAllPayStubs.action" targets="submenu_paystub_div" indicator="indicatorSubMenuPayStubFormId_div" cssClass="link"><s:text name="MTIViewPayStub"/></sj:a> |
					<sj:a href="payStubSearchForm.action" targets="submenu_paystub_div" indicator="indicatorSubMenuPaystub" cssClass="link"><s:text name="MTISearchPayStub"/></sj:a>
				</s:if>
			</s:if>
			<s:else>
				<s:url var="employeePayStubViewList" action="getEmployeePayStub">
					<s:param name="payStub.payStubId" value="payStub.payStubId"></s:param>
				</s:url>
				<sj:a href="%{employeePayStubViewList}" targets="submenu_paystub_div" indicator="indicatorSubMenuPayStubFormId_div" cssClass="link"><s:text name="MTIAddEmployeePayStub"/></sj:a>
			</s:else>
		</div><br/>
		<img id="indicatorSubMenuPayStubFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	 	<jsp:include page="common/messages.jsp" flush="true"/>	 	
	 	
		<s:form action="insertOrUpdatePayStub">
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
			     	   	<s:if test="payStub==null || payStub.payStubId == null">
			 		    	<tr>
			        			<td class="inputtext"><s:text name="label.form.fields.paystub.empName"/></td>
					            <td class="employeeinputtd">
					            	<sj:autocompleter  
									    name="payStub.employee.employeeId"
										list="#request.APPL_EMPLOYEE_LIST"
										listKey="employeeId"
										listValue="empFullName"
									    selectBox="true"
									    selectBoxIcon="true"
									    cssClass="employeeselect"
							    	 />
								</td>
								<td class="inputerrormessage"><s:fielderror fieldName="payStub.employee.employeeId" /></td>
							</tr>
							<tr>
								<td></td>
								<td class="employeeinputtd"><s:text name="label.common.message.autocompleteSelect"></s:text></td>
							</tr>
			       		</s:if>
				        <s:else>
			        		<tr>
			        			<td class="inputtext"><s:text name="label.form.fields.paystub.empName"/></td>
								<td class="employeedisplaytd"><s:textfield name="payStub.employee.empFirstName" readonly="true" cssClass="employeeinput"/></td>
								<s:hidden name="payStub.employee.employeeId" />
							</tr>
			        	</s:else>
			        	<tr>
				            <td class="inputtext"><s:text name="label.form.fields.paystub.grossSalary"/></td>
			         		<td class="employeeinputtd"><s:textfield name="payStub.grossSalary" cssClass="employeeinput"/></td>
			         		<td class="inputerrormessage"><s:fielderror fieldName="payStub.grossSalary" /></td>
						</tr>
						<!--Button image added by, R.Deepika-->
						<!-- Added Format Date text by,R.Deepika -->
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
						<s:hidden name="payStub.payStubId"/>
		    </table></td></tr></table></td></tr></table></td></tr></table><br/>
		    <table align="center">
	    		<tr>
	    		    <td>
						<img id="indicatorPayStubAddForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	    		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_paystub_div" indicator="indicatorPayStubAddForm"/>
					</td>
					<s:if test="payStub==null || payStub.payStubId==null">
				         <td>
		    		    	<s:url var="resetPayStubForm" action="resetPayStubForm"></s:url>
		    	            <sj:submit href="%{resetPayStubForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_paystub_div" indicator="indicatorPayStubAddForm"/>
			            </td>
			       </s:if>
			       <s:else>
	    	            <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    	        </s:else>
	    		 </tr>
	    </table>    		 
	</s:form></div> 