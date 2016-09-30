<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_SalaryGradeFormId_div">
<div class="submenu_bg">
	<s:if test="#session.SALARYGRADE_ADD == true">
		<sj:a href="setUpSalaryGrade.action" targets="submenu_SalaryGradeFormId_div" indicator="indicatorSubMenuSalaryGradeFormId_div" cssClass="link"><s:text name="MTIAddSalaryGrade" /></sj:a> |
	</s:if>
	<s:if test="#session.SALARYGRADE_VIEW == true">
		<sj:a href="getAllSalaryGrade.action" targets="submenu_SalaryGradeFormId_div" indicator="indicatorSubMenuSalaryGradeFormId_div" cssClass="link"><s:text name="MTIViewSalaryGrade"/></sj:a> |
		<sj:a href="salaryGradeSearchForm.action" targets="submenu_SalaryGradeFormId_div" indicator="indicatorSubMenuSalaryGradeFormId_div" cssClass="link"><s:text name="MTISearchSalaryGrade"/></sj:a>
	</s:if>
</div>
<br/>
<img id="indicatorSubMenuSalaryGradeFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	<s:form action="insertOrUpdateSalaryGrade">
		<table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
	                 	 <s:if test="salgra==null || salgra.hcmoSalaryGradeId == null">
					 	 	<s:text name="label.title.salaryGrade.add" />
					 	 </s:if> <s:else>
							<s:text name="label.title.salaryGrade.edit" />
					 	 </s:else>
	                  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
					<tr>
					  <td class="inputtext"><s:text	name="label.form.fields.salaryGrade.name" /></td>
					  <td class="employeeinputtd"><s:textfield name="salgra.salaryName" cssClass="employeeinput" /></td>
					  <td class="inputerrormessage"><s:fielderror fieldName="salgra.salaryName" /></td>
				    </tr>			
			
		</table></td></tr></table></td></tr></table></td></tr></table>
		<br />
		<table align="center">
			<tr>
				<td>
					<img id="indicatorSalaryGradeFormId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	   		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_SalaryGradeFormId_div" indicator="indicatorSalaryGradeFormId_div"/>
				</td>
				 <s:if test="salgra==null || salgra.hcmoSalaryGradeId==null">
				       <td>
		    		    	<s:url var="resetSalaryGrade" action="resetSalaryGrade"></s:url>
		    	            <sj:submit href="%{resetSalaryGrade}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_SalaryGradeFormId_div" indicator="indicatorSalaryGradeFormId_div"/>
			          </td>
			     </s:if>
			     <s:else>
				      <td><s:reset key="button.label.reset" cssClass="resetbutton117" /></td>
				</s:else>
			</tr>
			
		</table>
		<s:hidden name="salgra.hcmoSalaryGradeId"/>
	</s:form>
</div>