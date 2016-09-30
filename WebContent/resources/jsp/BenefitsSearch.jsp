<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EmployeeBenefitsSearchId_div">
	<div class="submenu_bg">
		<s:if test="#session.BENEFITS_ADD == true">
			<sj:a href="setUpBenefit.action" targets="submenu_EmployeeBenefitsSearchId_div" indicator="indicatorSubMenuEmployeeBenefitsSearchId_div" cssClass="link"><s:text name="MTIAddEmployeeBenefits" /></sj:a> |
		</s:if>
		<s:if test="#session.BENEFITS_VIEW == true">
			<sj:a href="getAllBenefit.action" targets="submenu_EmployeeBenefitsSearchId_div" indicator="indicatorSubMenuEmployeeBenefitsSearchId_div" cssClass="link"><s:text name="MTIViewEmployeeBenefits"/></sj:a> |
			<sj:a href="benefitSearch.action" targets="submenu_EmployeeBenefitsSearchId_div" indicator="indicatorSubMenuEmployeeBenefitsSearchId_div" cssClass="link"><s:text name="MTISearchEmployeeBenefits"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeeBenefitsSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form action="getAllBenefitYearSearchResult">
	     <table class="maintable">
	         <tr>
	        	<td align="center" ><table class="formouter">
	         <tr>
	            <td><table class="employeeformiinertable">
	             <tr>
	                <td class="formtitle">
						 <s:text name="label.title.benefit.search"/>
	                </td>
	       		</tr>
	     	 <tr>
	               <td class="forminner"><table class="tablealign"> 
				        <tr>
				            <td class="inputtext"><s:text name="label.header.benefit.year"/></td>
				             <td class="employeeinputtd">
								<s:select 
									headerKey=""
									headerValue="-- Please Select --"
									list="#request.APPL_BENEFITS_YEAR_LIST"
									listKey="year"
									listValue="year"
								    name="benefit.year"
								    cssClass="employeeselect"  
								    />
							</td>
							<td class="inputerrormessage">
									<s:fielderror fieldName="benefit.year"/>
							</td>
				        </tr>
	        <tr>
	            <td class="inputtext"><s:text name="label.header.common.empName"/></td>
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
					    /><br/><br/>
					    <s:text name="label.header.benefit.selectBoxHint"/>
				</td>
	        </tr>
	    </table></td></tr></table></td></tr></table></td></tr></table>
	   		 <br/>
	    <table align="center"> 
   	     <tr>
    	     <td>
    	     	<img id="indicatorBenefitForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
   		     	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_EmployeeBenefitsSearchId_div" indicator="indicatorBenefitForm"/>
   		    </td>
   	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
   		 </tr>
	    </table> 		  		 
   	</s:form>
</div>