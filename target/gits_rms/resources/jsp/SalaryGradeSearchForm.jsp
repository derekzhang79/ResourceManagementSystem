<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

 <div id="submenu_SalaryGradeSearchId_div">
<div class="submenu_bg">
	<s:if test="#session.SALARYGRADE_ADD == true">
		<sj:a href="setUpSalaryGrade.action" targets="submenu_SalaryGradeSearchId_div" indicator="indicatorSubMenuSalaryGradeSearchId_div" cssClass="link"><s:text name="MTIAddSalaryGrade" /></sj:a> |
	</s:if>
	<s:if test="#session.SALARYGRADE_VIEW == true">
		<sj:a href="getAllSalaryGrade.action" targets="submenu_SalaryGradeSearchId_div" indicator="indicatorSubMenuSalaryGradeSearchId_div" cssClass="link"><s:text name="MTIViewSalaryGrade"/></sj:a> |
		<sj:a href="salaryGradeSearchForm.action" targets="submenu_SalaryGradeSearchId_div" indicator="indicatorSubMenuSalaryGradeSearchId_div" cssClass="link"><s:text name="MTISearchSalaryGrade"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuSalaryGradeSearchId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="salaryGradeSearchResult">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
							<s:text name="label.title.salaryGrade.search" />
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					<tr>
						  <td class="inputtext">
							<s:text	name="label.header.salaryGrade.name" />
						  </td>
						  <td class="employeeinputtd">
							<s:textfield name="salgra.salaryName" cssClass="employeeinput" />
						  </td>
						  <td class="inputerrormessage"></td>
	</tr></table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorSalaryGradeForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_SalaryGradeSearchId_div" indicator="indicatorSalaryGradeForm"/>
				</td>
				<td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
			</tr>
			
		</table>
	</s:form>
</div>