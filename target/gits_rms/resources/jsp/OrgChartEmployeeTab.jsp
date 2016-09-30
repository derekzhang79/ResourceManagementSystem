<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<div id="submenu_OrgEmployeesTab_div">
		<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
    	<s:set name="OrgChartEmployeeId" value="employee.employeeId"></s:set>
	    	
		<s:url var="orgChartEmpInformationTab" action="orgChartEmpView">
			<s:param name="employee.employeeId" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		<s:url var="orgChartApproverNewTab" action="orgChartNewApproverTab">
			<s:param name="orgChartEmployeeID" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		<s:url var="orgChartLeaveQuotaNewTab" action="orgChartLeaveQuotaNewTab">
			<s:param name="orgChartEmployeeID" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		<s:url var="orgChartBenefitNewTab" action="orgChartBenefitNewTab">
			<s:param name="orgChartEmployeeID" value="#OrgChartEmployeeId"></s:param>
		</s:url>
		
		<frameset cols="20%,80%">
			<frameset rows="5%,75%">
				<frame src="orgChartRowFrameContent.action" frameborder="1" name="mainframe"></frame>
				<frame>
					<sj:tabbedpanel id="menuOrgChartMainTabbedpannel" animate="true">
						<sj:tab id="orgChartEmployeeInformationTab" key="Org View" href="%{orgChartEmpInformationTab}"/>
						<sj:tab id="orgChartApproverNewTab" key="Approver" href="%{orgChartApproverNewTab}"/>
						<sj:tab id="orgChartLeaveQuotaMainNewTab" key="Leave Quota" href="%{orgChartLeaveQuotaNewTab}"/>
						<sj:tab id="orgChartBenefitMainNewTab" key="Benefits" href="%{orgChartBenefitNewTab}"/>
						<!--<sj:div  href="%{getOrgChartEmployeeBenefitTab}" id="benefitTab" key="MTBenefits">
					 </sj:div>
				  	 -->
				   </sj:tabbedpanel>
				</frame>
			</frameset>
		</frameset>	
	</div>