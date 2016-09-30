<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
		 <div id="PayStubFormDiv">
		 
		<div id="submen_paystubFormSearchId_div">
	<div class="submenu_bg">
		<s:if test="#session.PAYSTUB_ADD == true">
			<sj:a href="setUpForInsertOrUpdatePayStub.action" targets="submen_paystubFormSearchId_div" indicator="indicatorSubMenuPaystubSearchId_div" cssClass="link"><s:text name="MTIAddPayStub" /></sj:a> |
		</s:if>
		<s:if test="#session.PAYSTUB_VIEW == true">
			<sj:a href="getAllPayStubs.action" targets="submen_paystubFormSearchId_div" indicator="indicatorSubMenuPaystubSearchId_div" cssClass="link"><s:text name="MTIViewPayStub"/></sj:a> |
			<sj:a href="deductionSearchForm.action" targets="submen_paystubFormSearchId_div" indicator="indicatorSubMenuPaystubSearchId_div" cssClass="link"><s:text name="MTISearchPayStub"/></sj:a>
		</s:if>
	</div>

		<br/>
		<img id="indicatorSubMenuPaystubSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		 		 	
		  <s:form action="payStubSearchResult">
		     	<table class="maintable">
			      <tr>
			        <td align="center" ><table class="formouter">
			      <tr>
			          <td><table class="employeeformiinertable">
	      		  <tr>
			          <td class="formtitle">
						 <s:text name="label.title.paystub.search"/>
			          </td>
	       		  </tr>
	       		  <tr>
                		<td class="forminner"><table class="tablealign">
                		<tr>
			         		<td class="inputtext"><s:text name="label.header.common.employeeName"/></td>
			         		<td class="employeeinputtd">
				            	<sj:autocompleter  
								    name="payStub.employee.empFullName"
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
		    </table></td></tr></table></td></tr></table></td></tr></table><br/>
		    <table align="center"> 
	    	     <tr>
	    	     <td>
	    	     	<img id="indicatorPayStubFormSearchFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	    		    <sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submen_paystubFormSearchId_div" indicator="indicatorPayStubFormSearchFormId_div"/>
	    		    </td>
	    	        <td><s:reset key="button.label.reset" cssClass="resetbutton117"/></td>
	    		 </tr>
		    </table>
	   	</s:form>
</div></div>   	