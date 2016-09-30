<%@page import="com.gits.rms.utils.TimesheetUtil"%>
<%@page import="com.gits.rms.action.TimesheetViewAction"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ page language="java" contentType="text/html;charset=windows-1252" import="java.text.DateFormat,java.util.*,java.util.StringTokenizer,java.sql.SQLException,java.text.SimpleDateFormat" %>


<%@page import="com.gits.rms.vo.TimeSheetAttachmentVO"%>
<%@page import="com.gits.rms.vo.TimeTrackVO"%>



<style>
body
{
 font-family:Verdana, Arial, Helvetica, sans-serif
}
.dsb
{
 color:#EEEEEE
}
.todaytext
{
color:RED
}
.rejecttext
{
color:ORANGE
}
.enteredtext
{
color:GREEN
}
.reworktext
{
color:GRAY
}
.approvetext
{
color:BLUE
}

</style>
<script type="text/javascript" src="<s:url value="/resources/js/timeSheetCalendar/timeSheetCalendar.js"/>"></script>
<!--<script type="text/javascript" src="<s:url value="/resources/js/timesheet/show_timesheet.js"/>"></script>-->

<div id="timesheeetid">
<jsp:include page="common/messages.jsp" flush="true"/>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td align="center" valign="top" width="100%">
		<table class="formouter2" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr><td align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr></tr>
				<tr><td class="formtitle">
					<s:if test="%{#session.EMPLOYEE_OBJECT.employeeId != #session.EmployeeId}">
						<div class="informationMessageSingle"><li><span>Timesheet View</span></li></div>
						<div align="left"><br><font size="1">Employee Name :<%=session.getValue("EmployeeName") %></font></div>
					</s:if>
					<s:else>
						Timesheet Add Form
					</s:else>
					</td>
				</tr><tr><td align="center" valign="top"><!--centerpart-->
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr><td colspan="15">
					<s:if test="#request.error==null || #request.error==''"></s:if>
					<s:else>
						<ul class="actionMessageSingle">
							<li><span><s:property value="#request.error"/></span></li>
						</ul>
					</s:else>
				</td></tr>
				<tr>
					<td align="center" colspan="20">
						<s:if test="%{#session.EMPLOYEE_OBJECT.employeeId == #session.EmployeeId}">
							<b>Current Date and Time : <s:property value="today" /><br></b>
						</s:if>
					</td>
				</tr>
				<tr><td colspan="20">
					<div><br><br>
						<s:form name="CheckInOut" action="timeTrackCheckInOut">
							<s:if test="%{#session.EMPLOYEE_OBJECT.employeeId == #session.EmployeeId}">
								Select the Category :
								<s:select 
									id="timecatselect" 
									headerKey="" 
									headerValue="-- Please Select --"
									list="timesheetCategoryList"
									listKey="hcmoTimesheetCategoryId" 
									listValue="name"
									name="timesheetCategory.hcmoTimesheetCategoryId"
									cssClass="employeeselect" 
								/>&nbsp;&nbsp;
                                Select the Project :
                                <s:select 
                                	id="proselect" 
                                 	headerKey="" 
                                 	headerValue="-- Please Select --"
                                    list="projectDropDownList"
                                    listKey="projectAssignEmpId" 
                                    listValue="projectWithProActivity" 
                                    name="projectAssignEmpId"
                                    cssClass="employeeselect" 
                                 />	
								<img id="indicatorTimeTrack" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    	
								<s:if test="chktimetrackInOutList.size==0">
									<s:url var="timeTrackCheckIn" action="insertTimeTrackValues"></s:url>
									<sj:submit name="CheckIn" onclick="javascript:cate('Check In')" cssClass="submitbutton117" value="Check In" targets="timesheeetid" indicator="indicatorTimeTrack"></sj:submit>
								</s:if>
								<s:else>
									<s:url var="timeTrackCheckOut" action="updateTimeTrackDetails"></s:url>
									<sj:submit name="CheckOut" onclick="javascript:cate('Check Out')" cssClass="submitbutton117" value="Check Out" targets="timesheeetid" indicator="indicatorTimeTrack"></sj:submit>
								</s:else><br>
									<s:hidden name="dateInput" value="%{dateInput}"></s:hidden>
									<s:hidden name="monthInput" value="%{monthInput}"></s:hidden>
									<s:hidden name="yearInput" value="%{yearInput}"></s:hidden>
									<s:hidden name="employeeId" value="%{#session.EmployeeId}"></s:hidden>
									<s:hidden name="checkedLists" id="checkedList"></s:hidden>
									<s:hidden name="buttonValue" id="buttonValue"></s:hidden>
	
									<br>
								<s:if test="timetrackHistoryList.size()>0">
									<table class="timesheetouter" width="100%" align="center">
										<th>Category</th>
										<th>Start Time</th>
										<th>End Time</th>
										<th>Duration (Hr:Min)</th>
										<s:iterator value="timetrackHistoryList">
											<tr>
												<td><s:property value="categoryName" /></td>
												<td><s:property value="start" /></td>
												<td><s:property value="stop" /></td>
												<td><s:property value="duration" /></td>
											</tr>
										</s:iterator>
									</table>
								</s:if>
							</s:if>
						</s:form><br><br>
					</div>
				</td></tr>
			<tr><td></td>
				<%
			 		ArrayList<String> weekArrayList = new ArrayList<String>();
			 		weekArrayList = (ArrayList)request.getAttribute("weekList");
			 		request.setAttribute("weekDaysList",weekArrayList);
			 		
			 		for(int i = 0;i<weekArrayList.size();i++){
			 			String [] dateSplit = weekArrayList.get(i).split("-");
			 			String sDate = dateSplit[1]+"-"+dateSplit[0]+"-"+dateSplit[2];
			 	%>
					<td colspan="2" class="headermenubg">
						<%=sDate%>
					</td>
				<%} %>
		 	<td class="headermenubg">Week Total</td>
			<!-- <td class="headermenubg">Activity Worked Hours</td>
		 	<td class="headermenubg">Activity Estimated Hours</td>  -->
			</tr>
			<s:if test="%{#session.EMPLOYEE_OBJECT.employeeId == #session.EmployeeId}">
				<tr>
					<td></td>
					<%
				 		ArrayList<String> weekNotesArrayList = new ArrayList<String>();
						weekNotesArrayList = (ArrayList)request.getAttribute("weekList");
				 		request.setAttribute("weekDaysList",weekNotesArrayList);
				 		for(int i = 0;i<weekNotesArrayList.size();i++){
				 			String [] dateSplit = weekNotesArrayList.get(i).split("-");
				 			String sDate = dateSplit[0]+"/"+dateSplit[1]+"/"+dateSplit[2];
				 			request.setAttribute("sDate", dateSplit[0]+"/"+dateSplit[1]+"/"+dateSplit[2]);
				 			String stringDate = dateSplit[0]+"/"+dateSplit[1]+"/"+dateSplit[2];
				 			TimesheetUtil time = new TimesheetUtil();
				 			boolean checkGreaterDate = time.checkTimesheetNotesIsAfterToday(sDate);
				 			
				 			if(checkGreaterDate == false){
				 				boolean empDateAvail = time.checkTimesheetNotes(sDate,String.valueOf(session.getAttribute("EmployeeId")));
				 				%>
				 					<td colspan="2">
										<s:url var="timesheetNotesLink" action="timesheetNotes">
											<s:param name="notesDate" value="#request.sDate"></s:param>
										</s:url>
								<% if(empDateAvail){%>
									<div class="timesheetnotesaddedicon">
							                <sj:a 
										    	openDialog="timesheetNotesDialog"
										    	href="%{timesheetNotesLink}"
										    />
									</div>
								<%	}else{	%>
										<div class="timesheetnotesicon">
							                <sj:a 
										    	openDialog="timesheetNotesDialog"
										    	href="%{timesheetNotesLink}"
										    />
										</div>
								<%	}  %>		
									    <sj:dialog 
									    	id="timesheetNotesDialog" 
									    	autoOpen="false" 
									    	modal="true"
									    	height="300"
									    	width="800" 
									    />
							<%	
								}
				 					}
				 		%>
				</tr>
			</s:if>
	
	<%
		TreeMap projectMap = (TreeMap)request.getAttribute("projectMap");
		
		List activityTotalHrs=(List)request.getAttribute("activityTotalHrs");
		Set projectset = projectMap.entrySet();
		Iterator itr2 = projectset.iterator();
		int count=0;
		while(itr2.hasNext()){
			Map.Entry projectme = (Map.Entry)itr2.next();
			String textAreaId1 = "";
	%>
	<tr>
		<td>
			<%= projectme.getKey()%>
		</td>	
		<%
			textAreaId1 = projectme.getKey().toString();
			TreeMap dateDetailMap1 = (TreeMap)projectme.getValue();
			Set dateDetailset1 = dateDetailMap1.entrySet();
			
			Iterator itr3 = dateDetailset1.iterator();
			
			while(itr3.hasNext()){
				Map.Entry dateDetailsetme1 = (Map.Entry)itr3.next();
		%>
		<td colspan="2">
			<% 		
			String [] dateSplit =dateDetailsetme1.getKey().toString().split("-");
			String sDate = dateSplit[1]+"-"+dateSplit[0]+"-"+dateSplit[2];
					String sDatevalue1 = textAreaId1.concat("$$$"+sDate);
			
					TimesheetUtil time = new TimesheetUtil();
					String sDateFinal = dateSplit[0]+"/"+dateSplit[1]+"/"+dateSplit[2]; 
					boolean checkGreaterDateOne = time.checkTimesheetNotesIsAfterToday(sDateFinal);
					if(checkGreaterDateOne == false){
					
					ArrayList arl1 = (ArrayList)dateDetailsetme1.getValue();
					request.setAttribute("enterValue1",arl1.get(0));
					request.setAttribute("CategotyName1",sDatevalue1);
					request.setAttribute("isEntered1",arl1.get(1));
			%>
			<s:if test="%{#session.EMPLOYEE_OBJECT.employeeId != #session.EmployeeId}">
				<s:if test="%{#request.isEntered1 == 'Entered' || #request.isEntered1 == 'Rework'}">
					<s:if test="%{#request.isEntered1 == 'Rework'}">
						<s:textfield id="%{#request.CategotyName1}" name="protimeEnter1" value="%{#request.enterValue1}" cssClass="reworktext" onfocus="clears(this);" disabled="true" size="2" onblur="checkProjectEntered(this)"></s:textfield>
					</s:if><s:else>
						<s:textfield id="%{#request.CategotyName1}" name="protimeEnter" value="%{#request.enterValue1}" cssClass="enteredtext" onfocus="clears(this);" disabled="true" size="2" onblur="checkProject(this)"></s:textfield>
						<s:checkbox id="%{#request.CategotyName1}" name="checkedList" value="false"></s:checkbox>
					</s:else>
				</s:if>
				<s:elseif test="%{#request.isEntered1 == 'Empty'}">
					<s:text name=""></s:text>				
				</s:elseif>
				<s:else>
						<s:if test="%{#request.isEntered1 == 'Rejected'}">
							<s:textfield id="%{#request.CategotyName1}" name="timeEnter1" value="%{#request.enterValue1}" onfocus="clears(this);" cssClass="rejecttext" disabled="true" size="2" onblur="checkCategoryEntered(this)"></s:textfield>
						</s:if>
						<s:elseif test="%{#request.isEntered1 == 'Empty'}">
							<s:text name=""></s:text>				
						</s:elseif>
						<s:else>
							<s:textfield id="%{#request.CategotyName1}" name="timeEnter1" value="%{#request.enterValue1}" onfocus="clears(this);" cssClass="approvetext" disabled="true" size="2" onblur="checkCategoryEntered(this)"></s:textfield>
						</s:else>
				</s:else>
			</s:if>
			<s:else>
				<s:if test="%{#request.isEntered1 == 'Empty'}">
					<s:text name=""></s:text>				
				</s:if>
				<s:elseif test="%{#request.enterValue1==0 || #request.isEntered1 == 'Rework'}">
					<s:if test="%{#request.enterValue1==0}">
						<s:textfield id="%{#request.CategotyName1}" name="protimeEnter" value="%{#request.enterValue1}" cssClass="enteredtext" onfocus="clears(this);" size="2" onblur="checkProject(this)"></s:textfield>
					</s:if>
					<s:else>
						<s:textfield id="%{#request.CategotyName1}" name="protimeEnter" value="%{#request.enterValue1}" cssClass="reworktext" onfocus="clears(this);" size="2" onblur="checkProject(this)"></s:textfield>
					</s:else>
				</s:elseif>
				<s:elseif test="%{#request.isEntered1 == 'Empty'}">
					<s:text name=""></s:text>				
				</s:elseif>
				<s:else>
					<s:if test="%{#request.isEntered1 == 'Rejected'}">
						<s:textfield id="%{#request.CategotyName1}" name="timeEnter1" value="%{#request.enterValue1}" onfocus="clears(this);" cssClass="rejecttext" disabled="true" size="2" onblur="checkCategoryEntered(this)"></s:textfield>			
					</s:if><s:elseif test="%{#request.isEntered1 == 'Approve'}">
						<s:textfield id="%{#request.CategotyName1}" name="timeEnter1" value="%{#request.enterValue1}" onfocus="clears(this);" cssClass="approvetext" disabled="true" size="2" onblur="checkCategoryEntered(this)"></s:textfield>				
					</s:elseif><s:else>
						<s:textfield id="%{#request.CategotyName1}" name="timeEnter1" value="%{#request.enterValue1}" onfocus="clears(this);" cssClass="enteredtext"  size="2" onblur="checkCategoryEntered(this)"></s:textfield>
					</s:else>
				</s:else>
			</s:else>
		</td>	
		<% 
				}
					} %>
		<td>
			<%=activityTotalHrs.get(count) %>
		</td>
			<% 
				//TimesheetViewAction timeEnter = new TimesheetViewAction();
				//String getIndex = String.valueOf(projectme.getKey());
				//String activityName = getIndex.substring(getIndex.indexOf("(")+1, getIndex.lastIndexOf(")")).trim();
				
				//Double projActivityTotalHrs = timeEnter.getProjActivityEstHrs(activityName);
				//Double totalWorkedHrs = timeEnter.getProjActivTotalWorkedHrs(activityName);
				
			%>
		
		<%
			//if(totalWorkedHrs > projActivityTotalHrs){
		%>
			<td style="color: red;">
				<%//=totalWorkedHrs %>
			</td>
		<%		
			//}else{
		
		%>	
			<td>
				<%//=totalWorkedHrs %>
			</td>			
		<%				
			//}
		%>
		<td>
			<%//=projActivityTotalHrs %>
		</td>
	</tr>
	<%
		count++;
		} 
			TreeMap timeSheetCategoryMap = (TreeMap)request.getAttribute("timeSheetCategoryMap");
			List categoryTotalHrs=(List)request.getAttribute("categoryTotalHrs");
			List dateTotalHrs=(List)request.getAttribute("dateTotalHrs");
			
			Set timeSheetCategoryset = timeSheetCategoryMap.entrySet();
			Iterator itr = timeSheetCategoryset.iterator();
			int i=0;
			while(itr.hasNext()){
				Map.Entry timeSheetCategoryme = (Map.Entry)itr.next();
				String textAreaId = "";
	%>
	<tr>
		<td>
			<%= timeSheetCategoryme.getKey()%>
		</td>	
		<%
			textAreaId = timeSheetCategoryme.getKey().toString();
			TreeMap dateDetailMap = (TreeMap)timeSheetCategoryme.getValue();
			
						
			Set dateDetailset = dateDetailMap.entrySet();
			Iterator itr1 = dateDetailset.iterator();
			while(itr1.hasNext()){
				Map.Entry dateDetailsetme = (Map.Entry)itr1.next();
		%>
		<td colspan="2">
			<% 	
			String [] dateSplit =dateDetailsetme.getKey().toString().split("-");
			String sDate = dateSplit[1]+"-"+dateSplit[0]+"-"+dateSplit[2];
					String sDatevalue = textAreaId.concat("$$$"+sDate);
					ArrayList arl = (ArrayList)dateDetailsetme.getValue();
					
					
					request.setAttribute("enterValue",arl.get(0));
					request.setAttribute("CategotyName",sDatevalue);
					request.setAttribute("isEntered",arl.get(1));
					
					TimesheetUtil time = new TimesheetUtil();
					String sDateFinalOne = dateSplit[0]+"/"+dateSplit[1]+"/"+dateSplit[2];
					boolean checkGreaterDateTwo = time.checkTimesheetNotesIsAfterToday(sDateFinalOne);
					if(checkGreaterDateTwo == false){
			%>
			
			<s:if test="%{#session.EMPLOYEE_OBJECT.employeeId != #session.EmployeeId}">
				
				<s:if test="%{#request.isEntered == 'Entered' || #request.isEntered == 'Rework'}">
					<s:if test="%{#request.isEntered == 'Rework'}">
						<s:textfield id="%{#request.CategotyName}" name="timeEnter1" value="%{#request.enterValue}" cssClass="reworktext" onfocus="clears(this);" disabled="true" size="2" onblur="checkCategoryEntered(this)"></s:textfield>
					</s:if><s:else>
						<s:textfield id="%{#request.CategotyName}" name="timeEnter" value="%{#request.enterValue}" cssClass="enteredtext" onfocus="clears(this);" disabled="true" size="2" onblur="checkCategory(this)"></s:textfield>
						<s:checkbox id="%{#request.CategotyName}" name="checkedList" value="false"></s:checkbox>
					</s:else>
						
				</s:if>
				<s:else>
						<s:if test="%{#request.isEntered == 'Rejected'}">
						<s:textfield id="%{#request.CategotyName}" name="timeEnter1" value="%{#request.enterValue}" onfocus="clears(this);" cssClass="rejecttext" disabled="true" size="2" onblur="checkCategoryEntered(this)"></s:textfield>
						</s:if>
						<s:else>
						<s:textfield id="%{#request.CategotyName}" name="timeEnter1" value="%{#request.enterValue}" onfocus="clears(this);" cssClass="approvetext" disabled="true" size="2" onblur="checkCategoryEntered(this)"></s:textfield>
						</s:else>
				</s:else>
			</s:if><s:else>
				<s:if test="%{#request.enterValue==0 || #request.isEntered == 'Rework'}">
					<s:if test="%{#request.isEntered == 'Rework'}">
						<s:textfield id="%{#request.CategotyName}" name="timeEnter" value="%{#request.enterValue}" cssClass="reworktext" onfocus="clears(this);" size="2" onblur="checkCategory(this)"></s:textfield>
					</s:if><s:else>
						<s:textfield id="%{#request.CategotyName}" name="timeEnter" value="%{#request.enterValue}" cssClass="enteredtext" onfocus="clears(this);" size="2" onblur="checkCategory(this)"></s:textfield>
					</s:else>
				</s:if><s:else>
					<s:if test="%{#request.isEntered == 'Rejected'}">
						<s:textfield id="%{#request.CategotyName}" name="timeEnter1" value="%{#request.enterValue}" cssClass="rejecttext" onfocus="clears(this);" disabled="true" size="2" onblur="checkCategoryEntered(this)"></s:textfield>
					</s:if><s:elseif test="%{#request.isEntered == 'Approve'}">
						<s:textfield id="%{#request.CategotyName}" name="timeEnter1" value="%{#request.enterValue}" cssClass="approvetext" onfocus="clears(this);" disabled="true" size="2" onblur="checkCategoryEntered(this)"></s:textfield>
					</s:elseif><s:else>
						<s:textfield id="%{#request.CategotyName}" name="timeEnter" value="%{#request.enterValue}" cssClass="enteredtext" onfocus="clears(this);" size="2" onblur="checkCategory(this)"></s:textfield>			
					</s:else>
				</s:else>
			</s:else>
		</td>	
		
		
		<%
		}
			}
		%>
		
		<td>
		 <%=categoryTotalHrs.get(i) %>
		</td>
		
	</tr>
	
	
	<%
	i++;
	} %>
	
	 <tr>
	<td>Total</td>
	
	<%for(int j=0;j<=dateTotalHrs.size()-1;j++){%>
		<td colspan="2"><b><%=dateTotalHrs.get(j) %></b><br/></td>
	<%}%>
	</tr> 

	<s:hidden id="weekDaysListId" value="%{#request.weekDaysList}" name="weekDaysListId"></s:hidden>
	<s:hidden name="proEnteredValue" id="proEnteredValue"></s:hidden>
	<s:hidden name="catEnteredValue" id="catEnteredValue"></s:hidden>
	<s:hidden name="projectValue" id="projectValue"></s:hidden>
	<s:hidden name="categoryValue" id="categoryValue"></s:hidden>
	<s:hidden name="enteredTotalhoursValue" id="enteredTotalhoursValue"></s:hidden>
	
	<tr><td></td><td colspan="14">
	<s:if test="%{#session.EMPLOYEE_OBJECT.employeeId == #session.EmployeeId}">
	<s:form name="insert" action="insertTimesheetDetails">
	<div align="center">
	<img id="indicatorTimesheet" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	<s:hidden name="dateInput" value="%{dateInput}"></s:hidden>
	<s:hidden name="monthInput" value="%{monthInput}"></s:hidden>
	<s:hidden name="yearInput" value="%{yearInput}"></s:hidden>
	<s:hidden name="employeeId" value="%{#session.EmployeeId}"></s:hidden>
	<s:hidden name="sbTimeSheetDetails" id="timeSheetDetails"></s:hidden>
	<s:hidden name="proTimeSheetDetails" id="protimeSheetDetails"></s:hidden>
	<s:hidden name="checkedLists" id = "checkedList"></s:hidden>
	<s:hidden name="buttonValue" id = "buttonValue"></s:hidden>
	<s:hidden name="weekDaysList" value="%{#request.weekDaysList}"></s:hidden>
	<s:hidden name="timeSheetEnteredDetails" id="timeSheetEnteredDetails"></s:hidden>
	<s:hidden name="protimeSheetEnteredDetails" id="protimeSheetEnteredDetails"></s:hidden>
	<s:hidden name="enteredTotalhoursMessageValue" id="enteredTotalhoursMessageValue"></s:hidden>
	<s:hidden name="enteredTotalhoursFalseValue" id="enteredTotalhoursFalseValue"></s:hidden>
	
	<sj:submit name="submit" onclick="javascript:insertTimesheet('Save All')" targets="timesheeetid" cssClass="submitbutton117" value="Save All" indicator="indicatorTimesheet"></sj:submit>
	<sj:submit cssClass="submitbutton117" targets="targetsidforTimesheetFileupload" href="fileUploadListAction.action" value="File Upload"></sj:submit>
    <sj:submit name="submit" onclick="javascript:insertTimesheet('Back')" targets="timesheeetid" cssClass="submitbutton117" indicator="indicatorTimesheet" key="button.label.ExpBackButton"></sj:submit>
	</div>
	</s:form>
	
	<div id="targetsidforTimesheetFileupload"></div>
	</s:if>
	<s:else>
	<s:form name="AppRejForm" action="insertTimesheetDetails">
	
	<s:hidden name="dateInput" value="%{dateInput}"></s:hidden>
	<s:hidden name="monthInput" value="%{monthInput}"></s:hidden>
	<s:hidden name="yearInput" value="%{yearInput}"></s:hidden>
	<s:hidden name="employeeId" value="%{#session.EmployeeId}"></s:hidden>
	<s:hidden name="proTimeSheetDetails" id="protimeSheetDetails"></s:hidden>
	<s:hidden name="sbTimeSheetDetails" id="timeSheetDetails"></s:hidden>
	<s:hidden name="checkedLists" id = "checkedList"></s:hidden>
	<s:hidden name="buttonValue" id = "buttonValue"></s:hidden>
	
	<img id="indicatorTimeSheetViewEnterPageId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
	<s:url var="getEmployeeDataLink" action="getEmployeeDataLink">
	</s:url> 
	<sj:submit name="submit" onclick="javascript:timesheet('Approve')" targets="timesheeetid" cssClass="submitbutton117" indicator="indicatorTimeSheetViewEnterPageId_div" value="Approve"></sj:submit>
	<sj:submit name="submit" onclick="javascript:timesheet('Reject')" targets="timesheeetid" cssClass="submitbutton117" indicator="indicatorTimeSheetViewEnterPageId_div" value="Reject"></sj:submit>
	<sj:submit name="submit" onclick="javascript:timesheet('Rework')" targets="timesheeetid" cssClass="submitbutton117" indicator="indicatorTimeSheetViewEnterPageId_div" value="Rework"></sj:submit>
	
	<s:url var="getEmployeeDataLink" action="getEmployeeDataLink">
		<s:param name="tsProjAssigned.employeeName.employeeId" value="#session.EmployeeId"/>
			
    </s:url> 
	<sj:submit name="submit" href="%{getEmployeeDataLink}" onclick="javascript:timesheet('Back')" targets="timesheeetid" cssClass="submitbutton117" indicator="indicatorTimeSheetViewEnterPageId_div" key="button.label.ExpBackButton"></sj:submit>
	
	<sj:submit name="submit" onclick="javascript:checkAll(this)" cssClass="submitbutton117" value="Select All"></sj:submit>
	<sj:submit name="submit" onclick="javascript:checkAll(this)" cssClass="submitbutton117" value="Deselect All"></sj:submit>
	
	</s:form>
	</s:else>
	</td></tr> 
	<tr>
	<td></td>
	<td colspan="8">
	<s:if test="%{#session.EMPLOYEE_OBJECT.employeeId == #session.EmployeeId}">
		<s:form method="post" enctype="multipart/form-data" action="fileUploadAction">
		<s:hidden name="employeeId" value="%{#session.EmployeeId}"></s:hidden>
	</s:form>
	</s:if>
	<s:else>
		<div class="informationMessageSingle"><li><span><s:text name="label.title.empSpace.list"/></span></li></div>
		<s:set name="NO_OF_RECORDS" value="#session.NO_OF_RECORDS"></s:set>
		<s:text name="label.form.fields.lhist.empName" var="HTimeSheetFileUploadEmpName"></s:text>
		<s:text name="label.title.empSpace.fileView" var="HTimeSheetFileName"></s:text>
		<s:text name="label.title.TimesheetFileUpladDate" var="HTimeSheetFileDate"></s:text>
		<s:text name="label.common.message.date" var="HDate"></s:text>
		<s:text name="label.form.fields.common.notes" var="HNotes"></s:text>
  	  	
  		<display:table class="tableborder" id="TimeSheetAttachListId" name="timeSheetAttachList" requestURI="getTimeSheetDetails.action" export="false" sort="list" defaultsort="1" defaultorder="ascending">
   			<%
				try{
					String sTimeSheetAttachId = ((TimeSheetAttachmentVO)pageContext.getAttribute("TimeSheetAttachListId")).getHcmoTsAttachmentId().toString();
					request.setAttribute("HcmoBenefitsId", sTimeSheetAttachId);
				}catch(NullPointerException ne){
				}
    		%>
	    	<display:column property="hcmoEmployeeId.empFirstName" title="${HTimeSheetFileUploadEmpName}"/>
	    	<display:column property="fileName" href="timesheetFileDownload.action" paramId="tsAttach.hcmoTsAttachmentId" paramProperty="hcmoTsAttachmentId" title="${HTimeSheetFileName}"/>
	    	<display:column property="created" title="${HTimeSheetFileDate}" format="{0,date,MM-dd-yyyy}"/>
	  </display:table>
	  <br/><br/><br/>
	  <div class="informationMessageSingle"><li><span><s:text name="label.title.timesheet.notes"/></span></li></div>
	  <display:table class="tableborder" id="timesheetListId" name="notesList" sort="list" defaultsort="1" defaultorder="ascending" export="false">
            <display:column property="date" title="${HDate}" sortable="false" headerClass="sortable"/>
    	    <display:column property="notes" title="${HNotes}" sortable="false" headerClass="sortable" maxLength="100"/>
  	  </display:table>
	</s:else>
	</td>
	</tr>
</table>

</td></tr>		
		<tr>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<div id="fileDisplay"></div>
</div>

<script type="text/javascript">
	var useBSNns = true;

	$(document).ready(function() { 
        $('#proselect').click(function() { 
            return $("#timecatselect").attr('selectedIndex', '0');  
        });
        $('#timecatselect').click(function() { 
            return $("#proselect").attr('selectedIndex', '0');
        });
      });
    
		function starttsCallback() {
			// make something useful before submit (onStart)
			return true;
		}
 
		function completetsCallback(response) {
			// make something useful after (onComplete)
			// document.getElementById('nr').innerHTML = parseInt(document.getElementById('nr').innerHTML) + 1;
			document.timesheetAttachForm.reset();
			document.getElementById("test").style.display="block";
			document.getElementById('MultipleTimesheetFileUploadDiv').innerHTML = response;
		}

		function startCallbackForTimeSheetDelete() {
			// make something useful before submit (onStart)
			return true;
		}
 
		function completeCallbackForTimeSheetDelete(response) {
			// make something useful after (onComplete)
			// document.getElementById('nr').innerHTML = parseInt(document.getElementById('nr').innerHTML) + 1;
			document.getElementById("showUploadFile").style.display="none";
			document.getElementById("test").style.display="block";
			document.getElementById('test').innerHTML = response;
		}

		function startCallbackTSDelete() {
			// make something useful before submit (onStart)
			return true;
		}
 
		function completeCallbackTSDelete(response) {
			// make something useful after (onComplete)
			// document.getElementById('nr').innerHTML = parseInt(document.getElementById('nr').innerHTML) + 1;
			document.forms.timesheetAttachForm.reset();
			document.getElementById("test").style.display="block";
			document.getElementById('test').innerHTML = response;
		}
	</script>