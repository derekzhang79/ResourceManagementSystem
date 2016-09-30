<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	
		<sj:head />


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center" valign="top">
		<img id="indicatorMainMenuSkiipmenuId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	
	<div id="mainmenuSkipId_div">
			<table class="formouterbirthdayandnews">
				<tr class="empbirthdayandlatestnews">
					<td class="calendartd">
					
						<s:url var="getTabEmpCalendar" action="calendarMainPage.action"/>
						<s:url var="getTabEmpSchedulerList" action="empScheduleForm"/>
						<sj:tabbedpanel id="remoteMenuPart1" animate="true">
							<sj:tab id="empCalendarTab" href="%{getTabEmpCalendar}" label="Calendar" />
							<s:if test="#session.TIMESHEETAPPROVER_ADD == true || #session.TIMESHEETAPPROVER_VIEW == true">
								<sj:tab id="EmpSchedulerMainTab" key="MTEmployeeScheduler" href="%{getTabEmpSchedulerList}"/>
							</s:if>
						</sj:tabbedpanel>
						
						
					</td>
					<td class="birthdaytd">
	  					<table class="birthdayheadertd">
							<tr>
								<td class="birthdayheadericontd"></td>
								<td class="fontnewsandbirthday"><s:text name="label.header.birthday"/></td>
							</tr>
						</table>
						<div id="eventDetails_skipmenuId_div">
						<table class="birthdaynewsdatatd">
								<tr>
									<td>
										<marquee direction=up loop=true behavior="scroll" scrollamount="1">
											<s:iterator value="empBirthdayList">
												<br/><s:property value="empFirstName"/>&nbsp;<s:property value="empLastName"/>&nbsp;-&nbsp;<s:property value="empBirthDate"/><br/>
											</s:iterator>
											
											<s:iterator value="thisYearEvents">
												<br/><b>Event - </b><s:property value="eventName"/>&nbsp;<s:property value="StartDate"/>&nbsp;<br/>
											</s:iterator>
											
										</marquee>
									</td>
								</tr>
						</table>
						</div>
					</td>
				</tr>
			</table><br/><br/><br/>
			
			<table width="100%">
				<tr>
					<td style="width:70%">
						<s:url var="getleaveForApprovalCount" action="leaveCount"></s:url>
						<s:url var="getExpenseForApprovalCount" action="expenseCount"></s:url>
						<s:url var="getTimesheetForApprovalCount" action="timeSheetCount"></s:url>
						
						<sj:tabbedpanel id="homepageLeaveExpenseTimesheetCount">
							<sj:tab id="homePageLeaveCount" key="MLeave" href="%{getleaveForApprovalCount}" />
							<sj:tab id="homePageExpenseCount" key="MExpense" href="%{getExpenseForApprovalCount}" />
							<sj:tab id="homePageTimesheetCount" key="MTimesheet" href="%{getTimesheetForApprovalCount}" />
						</sj:tabbedpanel>
					</td>
					<td class="lastnewstd">
						<table class="lastnewsheadertd">
							<tr>
								<td class="headerlastnewicontd"></td>
								<td class="fontnewsandbirthday"><s:text name="label.header.latestNews"/></td>
							</tr>
						</table>
						<table class="lastnewsdatatd">
							<tr>
								<td>
									<div class="lastnewsdatatd">
										<ul id="ma_liste">
									        <s:iterator value="impList">
									            <li><b><u><s:text name="subject"></s:text></u></b>
									            <s:if test="empIdObj.empFirstName!=null">
									             : (By <s:text name="empIdObj.empFirstName"/>)</br>&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="message"></s:text></li><br/>
									            </s:if>
									         </s:iterator>
										</ul>
									</div>
									<div id="ma_liste-menu_skipmenu" class="wslide-menu"></div>	
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</div> 
		</td>
	</tr>
</table>		