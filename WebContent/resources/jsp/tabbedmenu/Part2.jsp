<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION") ;%></s:if>
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
		<table class="maintable">
			<tr align="center"><td class="formtitle">
				<s:text name="MPart2"/>
			</td></tr>
		</table>		
		<s:if test="#session.COUNTRY_ADD == true ||
					#session.COUNTRY_VIEW == true ||
					#session.NATIONALITY_ADD == true ||
					#session.NATIONALITY_VIEW == true ||
					#session.ETHNICRACE_ADD == true ||
					#session.ETHNICRACE_VIEW == true ||
					#session.JOBTITLE_ADD == true ||
					#session.JOBTITLE_VIEW == true ||
					#session.SALARYGRADE_ADD == true ||
					#session.SALARYGRADE_VIEW == true ||
					#session.ROLE_ADD == true ||
					#session.ROLE_VIEW == true ||
					#session.DEPARTMENT_ADD == true || 
					#session.DEPARTMENT_VIEW == true ||
					#session.TEAM_ADD == true ||
					#session.TEAM_VIEW == true ||
					#session.CURRENCY_ADD == true ||
					#session.CURRENCY_VIEW == true ||
					#session.EMPLOYEESTATUS_ADD == true ||
					#session.EMPLOYEESTATUS_VIEW == true ||
					#session.HOLIDAY_ADD == true ||
					#session.HOLIDAY_VIEW == true ||
					#session.LEAVETYPE_ADD == true ||
					#session.LEAVETYPE_VIEW == true ||
					#session.EXPENSETYPE_ADD == true ||
					#session.EXPENSETYPE_VIEW == true ||
					#session.DEDUCTION_ADD == true ||
					#session.DEDUCTION_VIEW == true">
				
				<s:url var="getMainTabUserInfo" action="MenuPart2UserInfo"/>
				<!--<s:url var="getMainTabProjectInfo" action="MenuPart2MainProjInfo"/>-->
				<s:url var="getMainTabConfigurationInfo" action="MenuPart2GeneralConfigInfo"/>
				<s:url var="getMainTabTaxAndDeductions" action="MenuPart2TaxAndDeductions"/>
				<sj:tabbedpanel id="remotePart2Menu" animate="true">
					<s:if test="#session.COUNTRY_ADD == true ||
								#session.COUNTRY_VIEW == true ||
								#session.NATIONALITY_ADD == true ||
								#session.NATIONALITY_VIEW == true ||
								#session.ETHNICRACE_ADD == true ||
								#session.ETHNICRACE_VIEW == true ||
								#session.JOBTITLE_ADD == true ||
								#session.JOBTITLE_VIEW == true ||
								#session.SALARYGRADE_ADD == true ||
								#session.SALARYGRADE_VIEW == true ||
								#session.ROLE_ADD == true ||
								#session.ROLE_VIEW == true ||
								#session.DEPARTMENT_ADD == true || 
								#session.DEPARTMENT_VIEW == true ||
								#session.TEAM_ADD == true ||
								#session.TEAM_VIEW == true ||
								#session.CURRENCY_ADD == true ||
								#session.CURRENCY_VIEW == true">				
						<sj:tab id="UserInfoMainTab" key="MTUserInformation" href="%{getMainTabUserInfo}"/>
					</s:if>
					
					
					<s:if test="#session.EMPLOYEESTATUS_ADD == true ||
								#session.EMPLOYEESTATUS_VIEW == true ||
								#session.HOLIDAY_ADD == true ||
								#session.HOLIDAY_VIEW == true ||
								#session.LEAVETYPE_ADD == true ||
								#session.LEAVETYPE_VIEW == true ||
								#session.EXPENSETYPE_ADD == true ||
								#session.EXPENSETYPE_VIEW == true ||
								#session.PERFORMANCECATEGORY_ADD == true ||
								#session.PERFORMANCECATEGORY_VIEW == true ||
								#session.PERFORMANCESUBCATEGORY_ADD == true ||
								#session.PERFORMANCESUBCATEGORY_VIEW == true
							">
						<sj:tab id="generalConfigInfoMainTab" key="MTGeneral" href="%{getMainTabConfigurationInfo}"/>
					</s:if>
					
					<s:if test="#session.DEDUCTION_ADD == true ||
								#session.DEDUCTION_VIEW == true">
						<sj:tab id="taxAndDeductionsMainTab" key="MTTaxAndDeductions" href="%{getMainTabTaxAndDeductions}"/>
					</s:if>
				</sj:tabbedpanel>
		</s:if>	