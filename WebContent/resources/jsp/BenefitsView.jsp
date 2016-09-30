<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div id="submenu_EmployeeBenefitsViewId_div">
	<div class="submenu_bg">
		<s:if test="#session.BENEFITS_ADD == true">
			<sj:a href="setUpBenefit.action" targets="submenu_EmployeeBenefitsViewId_div" indicator="indicatorSubMenuEmployeeBenefitsViewId_div" cssClass="link"><s:text name="MTIAddEmployeeBenefits" /></sj:a> |
		</s:if>
		<s:if test="#session.BENEFITS_VIEW == true">
			<sj:a href="getAllBenefit.action" targets="submenu_EmployeeBenefitsViewId_div" indicator="indicatorSubMenuEmployeeBenefitsViewId_div" cssClass="link"><s:text name="MTIViewEmployeeBenefits"/></sj:a> |
			<sj:a href="benefitSearch.action" targets="submenu_EmployeeBenefitsViewId_div" indicator="indicatorSubMenuEmployeeBenefitsViewId_div" cssClass="link"><s:text name="MTISearchEmployeeBenefits"/></sj:a>
		</s:if>
	</div>
		<br/>
		<img id="indicatorSubMenuEmployeeBenefitsViewId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>

	<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
	<jsp:include page="common/messages.jsp" flush="true"/>
	    <s:form>
	     <table class="maintable">
	      <tr>
	        <td align="center" ><table class="formouter">
	          <tr>
	            <td><table class="employeeformiinertable">
	                <tr>
	                  <td class="formtitle">
					 		<s:text name="label.title.benefit.view"/>
					  </td>
	                </tr>
	                <tr>
	                  <td class="forminner"><table class="tablealign">
            <tr>
				<td class="inputtext"><s:text name="label.header.benefit.benefitId" /></td>
				<td class="employeedisplaytd"><s:property value="benefit.hcmoBenefitsId"/></td>
		   </tr>
		   <s:if test="#session.BENEFITS_YEAR_VIEW == true">
		       <tr>
					<td class="inputtext"><s:text name="label.header.benefit.year" /></td>
					<td class="employeedisplaytd"><s:property value="benefit.year"/></td>
			   </tr>
		   </s:if>
		   <s:if test="#session.BENEFITS_BENEFITSNAME_VIEW == true">
			   <tr>
					<td class="inputtext"><s:text name="label.header.benefit.benefitsName" /></td>
					<td class="employeedisplaytd"><s:property value="benefit.benefitsName"/></td>
			   </tr>
		   </s:if>
		   <s:if test="#session.BENEFITS_ATTACHFILE_VIEW == true">
		       <tr>
		         	<td class="inputtext"><s:text name="label.header.benefit.benefitsAttachFile" /></td>
					<td class="employeedisplaytd">
					<s:url var="fileDownload.action" action="fileDownload.action">
			      		   <s:param name="benefit.hcmoBenefitsId" value="benefit.hcmoBenefitsId"/>
			   		</s:url>
					<s:a href="%{fileDownload.action}"><s:property value="benefit.benefitsAttachFile"/></s:a></td>
		       </tr>
		   </s:if>
	       <tr>
				<td class="inputtext"><s:text name="label.header.common.empName" /></td>
				<td class="employeedisplaytd"><s:property value="#session.EMPNAME_LIST"/></td>
				
		   </tr>
	       <tr>
				<td class="inputtext"><s:text name="label.header.common.created" /></td>
				<td class="employeedisplaytd"><s:date name="benefit.created" format="%{getText('label.date.simpleDateFormat')}"/></td>
		   </tr>
		   <tr>
		   		<td class="inputtext"><s:text name="label.header.common.createdBy" /></td>
				<td class="employeedisplaytd"><s:property value="benefit.createdBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updated" /></td>
				<td class="employeedisplaytd"><s:date name="benefit.updated" format="%{getText('label.date.simpleDateFormatWithTime')}" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.updatedBy" /></td>
				<td class="employeedisplaytd"><s:property value="benefit.updatedBy.empFirstName" /></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.common.isActive" /></td>
				<td class="employeedisplaytd"><s:checkbox name="benefit.isActive" label="isActive" disabled="true"></s:checkbox></td>
			</tr>
	         
				<s:hidden name="benefit.hcmoBenefitsId"/>        
	 	</table></td></tr></table></td></tr></table></td></tr></table>
		</s:form>
</div>	