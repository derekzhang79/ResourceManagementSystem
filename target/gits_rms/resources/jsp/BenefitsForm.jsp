<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EmployeeBenefitsForm_div">
	<div class="submenu_bg">
		<s:if test="#session.BENEFITS_ADD == true">
			<sj:a href="setUpBenefit.action" targets="submenu_EmployeeBenefitsForm_div" indicator="indicatorSubMenuEmployeeBenefitsFormId_div" cssClass="link"><s:text name="MTIAddEmployeeBenefits" /></sj:a> |
		</s:if>
		<s:if test="#session.BENEFITS_VIEW == true">
			<sj:a href="getAllBenefit.action" targets="submenu_EmployeeBenefitsForm_div" indicator="indicatorSubMenuEmployeeBenefitsFormId_div" cssClass="link"><s:text name="MTIViewEmployeeBenefits"/></sj:a> |
			<sj:a href="benefitSearch.action" targets="submenu_EmployeeBenefitsForm_div" indicator="indicatorSubMenuEmployeeBenefitsFormId_div" cssClass="link"><s:text name="MTISearchEmployeeBenefits"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeeBenefitsFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form id="benefitsAddFormId" method="post" enctype="multipart/form-data">
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
					 		<s:if test="benefit==null || benefit.hcmoBenefitsId == null">
							 <s:text name="label.title.benefit.add"/>
						   </s:if>
						   <s:else>
							 <s:text name="label.title.benefit.edit"/>
						   </s:else>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
	         <tr>
	         	 <td class="inputtext"><s:text name="label.form.fields.benefit.year"/></td>
	         	 <td class="employeeinputtd"><s:textfield name="benefit.year" cssClass="employeeinput"/></td>
	         	 <td class="inputerrormessage">
	         	 	<s:fielderror fieldName="benefit.year" />
	         	 </td>
	         </tr>
	         <tr>
	         	 <td class="inputtext"><s:text name="label.form.fields.benefit.benefitName"/></td>
	         	 <td class="employeeinputtd"><s:textfield name="benefit.benefitsName" cssClass="employeeinput"/></td>
	         	 <td class="inputerrormessage">
	         	 	<s:fielderror fieldName="benefit.benefitsName" />
	         	 </td>
	         </tr>
	         <tr>
	          	<td class="inputtext"><s:text name="label.form.fields.benefit.benefitAttachFile"/></td>
	            <td class="employeeinputtd"><s:file name="benefitsAttachFile"></s:file></td>
	            <td class="inputerrormessage">
	            	<s:fielderror fieldName="benefitsAttachFile" />
	            </td>
	         </tr>
	          <tr>
	         	 <td class="inputtext"><s:text name="label.form.fields.benefit.fileFormat"/></td>
	         	 <td class="employeeinputtd"><s:text name="label.form.fields.benefit.fileTypeToAttach"/></td>
	         	 <td></td>
	         </tr>
	        <tr>
	            <td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
	            	 <td class="employeeinputtd">
						<s:select 
								headerKey=""
								list="#request.APPL_EMPLOYEE_LIST"
								listKey="employeeId"
								listValue="empFullName"
							    name="benefit.empIdObjList.employeeId"
							    cssClass="employeeselect"
				    			multiple="true"
			    				size="3"
					    /> <br/><br/>
					    <s:text name="label.header.benefit.selectBoxHint"/>
					</td>
	        </tr>
			<s:hidden name="benefit.hcmoBenefitsId"/>        
	    </table></td></tr></table></td></tr></table></td></tr></table>
	   		 <br/>
	    <table align="center"> 
	    	     <tr>
	    		    <td><s:submit key="button.label.submit" cssClass="submitbutton117" onclick="doPostCallWithFileUpload('submenu_EmployeeBenefitsForm_div','insertOrUpdateBenefit.action','benefitsAddFormId');return false;"/></td>
	    	        <td><s:if test="benefit==null || benefit.hcmoBenefitsId==null">
		    	            <s:submit action="resetEmployeeBenefitsForm" key="button.label.reset" cssClass="submitbutton117"/>
		    	        </s:if>
		    	        <s:else>
		    	        	<s:reset key="button.label.reset" cssClass="resetbutton117" />
		    	        </s:else></td>
	    		 </tr>
	    </table>		  		 
	    	</s:form>
</div>	