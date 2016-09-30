<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<%@ page language="java" import="java.util.*,java.text.*"%>
<%@page import="com.gits.rms.utils.DateUtils"%>
<%@page import="com.gits.rms.vo.TimeSheetProjectAssignVO"%>
<%@page import="com.gits.rms.vo.TimeSheetCategoryAssignVO"%>
<%@page import="com.gits.rms.vo.ReportTimeSheetDislayVO"%>
<%@page import="com.gits.rms.vo.LeaveHistoryVO"%>
<%@page import="com.gits.rms.vo.ExpenseStatusTrackerVO"%>
<%@page import="com.gits.rms.vo.ReportsVO"%>
<%@page import="com.gits.rms.vo.EmployeesVO"%>
<%@page import="com.gits.rms.service.EmployeesService"%>
<%@page import="com.gits.rms.service.EmployeesDaoService"%>
<%@page import="com.gits.rms.service.TimeSheetApproverService"%>
<%@page import="com.gits.rms.service.TimeSheetApproverDaoService"%>
<%@page import="com.gits.rms.service.ExpensesTypeService"%>
<%@page import="com.gits.rms.service.ExpensesTypeDaoService"%>

<%@page import="com.gits.rms.vo.TimeSheetApproverVO"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="com.gits.rms.vo.ExpensesTypeVO"%>
<%@page import="com.gits.rms.service.TimeSheetProjeectAssignedDaoService"%>
<%@page import="com.gits.rms.service.TimeSheetProjeectAssignedService"%>

<div id="report_timesheetReportPreview_div_id">
	<div class="submenu_bg">
	<img id="indicatorGenerateTimeSheetReportPreview" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		<sj:a href="showTimeSheetReports.action" targets="report_timesheetReportPreview_div_id" indicator="indicatorGenerateTimeSheetReportPreview" cssClass="link"><s:text name="MTIGenerateTimesheetReport" /></sj:a> |
		<sj:a href="showTimeEstimationReports.action" targets="report_timesheetReportPreview_div_id" indicator="indicatorGenerateTimeSheetReportPreview" cssClass="link"><s:text name="MTIGenerateTimeEstimationReport" /></sj:a>
		
		<s:if test="#session.ROLE == 'Admin' || #session.ROLE == 'admin' || #session.ROLE == 'ADMIN'">
		| <sj:a href="showSummationTimeSheetReports.action" targets="report_timesheetReportPreview_div_id" indicator="indicatorGenerateTimeSheetReportPreview" cssClass="link"><s:text name="MTIGenerateSummationTimesheetReport" /></sj:a> |
      	  <sj:a href="showTimeSheetProjectSummationReports.action" targets="report_timesheetReportPreview_div_id" indicator="indicatorGenerateTimeSheetReportPreview" cssClass="link"><s:text name="MTIGenerateTimesheetProjectSummationReport" /></sj:a>
		</s:if>
	</div>

<%!
private EmployeesService employeeServive=new EmployeesDaoService();
TimeSheetApproverService timeSheetApproverService = new TimeSheetApproverDaoService();
private ExpensesTypeService expTypeService = new ExpensesTypeDaoService();
private TimeSheetProjeectAssignedService tsProjAssService = new TimeSheetProjeectAssignedDaoService();
%>


<%!
public int nullIntconv(String inv)
{   
    int conv=0;
    try{
        conv=Integer.parseInt(inv);
    }
    catch(Exception e)
    {}
    return conv;
}
%>


<%

ArrayList<Date> fulllist = new ArrayList<Date>();
ArrayList<Date> dates = new ArrayList<Date>();
ArrayList<Date> dates1 = new ArrayList<Date>();
ArrayList<Date> dates2 = new ArrayList<Date>();
ArrayList<ArrayList> sublist =new ArrayList<ArrayList>();
List<EmployeesVO> employeelist;


 String str_date =request.getAttribute("startDate").toString();
 String end_date =request.getAttribute("endDate").toString();
 ReportsVO reports = (ReportsVO)request.getAttribute("report");

 DateFormat formatter ; 

 formatter = new SimpleDateFormat("dd/MM/yyyy");
 Date  startDate = (Date)formatter.parse(str_date); 
 Date  endDate = (Date)formatter.parse(end_date);
 long interval = 24*1000 * 60 * 60; // 1 hour in millis
 long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
 long curTime = startDate.getTime();
 while (curTime <= endTime) {
   fulllist.add(new Date(curTime));
   dates.add(new Date(curTime));
   dates1.add(new Date(curTime));
   curTime += interval;
   
   while(dates.size()==7){
   dates = new ArrayList<Date>();
   sublist.add(dates);
   dates.removeAll(dates);
   }
   
 }
 if(dates1.size()>=6){
 	for(int i=0;i<=6;i++){
    dates2.add(dates1.get(i));
    }
 }else{
 	for(int i=0;i<=dates1.size()-1;i++){
    dates2.add(dates1.get(i));
    }
 }
 
 sublist.add(0,dates2);
 
%>
<style type="text/css">
 	 	    #stats_tbl tbody tr:nth-child(odd) {
    background-color:#edecec;
}
</style>
<script>
function goTo()
{
  document.frm.submit();
}
</script>
<style>
body {
	font-family: Verdana, Arial, Helvetica, sans-serif
}

.dsb {
	background-color: #EEEEEE
}

.preppy {
	color: #FF3399;
	font-weight: 900;
	font-style: italic;
}

</style>



<div id="timesheetCalendarTsReportId_div"><s:form name="frm" method="post">

	<%
List<TimeSheetProjectAssignVO> timeSheetProjectAssignList=(List<TimeSheetProjectAssignVO>)request.getAttribute("timeSheetProjectAssignList");
List<TimeSheetCategoryAssignVO> timeSheetCategoryAssignList=(List<TimeSheetCategoryAssignVO>)request.getAttribute("timeSheetCategoryAssignList");
List<LeaveHistoryVO> leaveHistorylist=(List<LeaveHistoryVO>)request.getAttribute("leaveList");
List<ExpenseStatusTrackerVO> expenseHistorylist=(List<ExpenseStatusTrackerVO>)request.getAttribute("expensesList");


	
Set timesheetProjects=new HashSet();
timesheetProjects=(HashSet)request.getAttribute("timesheetProjectList");

Set timesheetcategory=new HashSet();
timesheetcategory=(HashSet)request.getAttribute("timesheetCategoryList");

Map mSession = ActionContext.getContext().getSession();
Integer clientId = (Integer)mSession.get("CLIENT_INFO");
String timesheetRole = String.valueOf(mSession.get("TIMESHEET_APPROVER"));
EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
if (reports.getEmpIdObjList().size() == 0) {

	 if ((oEmp.getRoleObj().getRoleName().equals("Admin"))
            || (oEmp.getRoleObj().getRoleName().equals("admin"))
            || (oEmp.getRoleObj().getRoleName().equals("ADMIN"))) {

	     employeelist = employeeServive.getAllEmployees(clientId);
        reports.setEmpIdObjList(employeelist);
    }else if(timesheetRole.equals("BOTH")){
        employeelist = tsProjAssService.getCurrentSubEmployeeForTimeSheet();
        reports.setEmpIdObjList(employeelist);
    }else {
   	 List<EmployeesVO> empList = new LinkedList<EmployeesVO>();
   	 EmployeesVO empObj = new EmployeesVO();

   	 empObj = employeeServive.getEmployeeById(oEmp.getEmployeeId());
   	 empList.add(empObj);
        reports.setEmpIdObjList(empList);
    }
}
	 employeelist=(List<EmployeesVO>)reports.getEmpIdObjList();
	for(EmployeesVO employees:employeelist){
		EmployeesVO emp=employeeServive.getEmployeeById(employees.getEmployeeId());
		TimeSheetApproverVO tsApprover = timeSheetApproverService.getEmployeeTimeSheetApproverDetails(emp.getEmployeeId());
		String approverName="--";
		if(tsApprover!=null){
		approverName=tsApprover.getHcmoApprovingEmpId().getEmpFirstName();
		}
%>
	<br>
	<br>
	<table id="stats_tbl" class="borderAll">
	<tr><td class="nowrap">Employee Name</td><td><%=emp.getEmpFirstName() %></td></tr>
	<tr><td class="nowrap">Approver Name</td><td><%=approverName %></td></tr>
	<tr><td class="nowrap">Start Date</td><td><%=str_date %></td></tr>
	<tr><td class="nowrap">End Date</td><td><%=end_date %></td></tr>
	</table>

	<br>


	<table id="stats_tbl" class="borderAll">
		<%
for(int j=0;j<=sublist.size()-1;j++){
	 List display=new ArrayList();
	 display=sublist.get(j);
%>
		<tr>
			<th><b>Project Name</b></th>
			<th><b>Category Name</b></th>
			<%for(int k=0;k<=display.size()-1;k++) { %>

			<th><b><%=formatter.format(display.get(k)) %></b></th>

			<% } %>
		</tr>

		<%
	 Iterator it=timesheetProjects.iterator();	
	 	while(it.hasNext()){
		String projectname=it.next().toString();
	%>
		<tr>
			<td><%=projectname %></td>
			<td>&nbsp;</td>
			<%for(int k=0;k<=display.size()-1;k++) { %>

			<td>
			<%
				for(TimeSheetProjectAssignVO projectlist:timeSheetProjectAssignList){
					String projAndActivity=projectlist.getProjectName().getProjectName()+"("+projectlist.getProjectActivity().getActivityName()+")";
					if(projectlist.getEnterDate().equals(display.get(k)) && projAndActivity.equals(projectname) && projectlist.getEmployeeName().getEmployeeId().equals(emp.getEmployeeId())){
					out.println(projectlist.getEnterTime());
					}
				}
		
		%>
			</td>

			<% } %>
		</tr>
		<tr>
		
		<td colspan="2">Approval Status</td>
		 
			<%for(int k=0;k<=display.size()-1;k++) { %>

			<td>
			<%
				for(TimeSheetProjectAssignVO projectlist:timeSheetProjectAssignList){
					
					String projAndActivity=projectlist.getProjectName().getProjectName()+"("+projectlist.getProjectActivity().getActivityName()+")";
					if(projectlist.getEnterDate().equals(display.get(k)) && projAndActivity.equals(projectname) && projectlist.getEmployeeName().getEmployeeId().equals(emp.getEmployeeId())){
						
						if(projectlist.getApprove()!=0){
							out.println("Approved");
			    		}else if(projectlist.getRejected()!=0){
			    			out.println("Rejected");
			    		}else if(projectlist.getRework()!=0){
			    			out.println("Rework");
			    		}else{
			    			out.println("Not Reviewed");
			    		}
					}
				}
		
		%>
			</td>

			<% } %>
		
		</tr>

		<%} %>
		
		
	<%Iterator itr=timesheetcategory.iterator();	
	 	while(itr.hasNext()){
		String categoryname=itr.next().toString();
	%>
		
		<tr>
			<td>&nbsp;</td>
			<td><%=categoryname %></td>
			<%for(int k=0;k<=display.size()-1;k++) { %>

			<td>
			<%
				for(TimeSheetCategoryAssignVO categorylist:timeSheetCategoryAssignList){
					if(categorylist.getTimesheetCategoryName()!=null){
				
						if(categorylist.getEnterDate().equals(display.get(k)) && categorylist.getTimesheetCategoryName().getName().equals(categoryname) && categorylist.getEmployeeName().getEmployeeId().equals(emp.getEmployeeId())){
							out.println(categorylist.getEnterTime());
						}
					}
				}
		
		%>
			</td>

			<% } %>
		</tr>
		
		<tr>
			<td colspan="2">Approval Status</td>
			
			<%for(int k=0;k<=display.size()-1;k++) { %>

			<td>
			<%
				for(TimeSheetCategoryAssignVO categorylist:timeSheetCategoryAssignList){
					if(categorylist.getTimesheetCategoryName()!=null){
				
						if(categorylist.getEnterDate().equals(display.get(k)) && categorylist.getTimesheetCategoryName().getName().equals(categoryname) && categorylist.getEmployeeName().getEmployeeId().equals(emp.getEmployeeId())){
							if(categorylist.getApprove()!=0){
								out.println("Approved");
				    		}else if(categorylist.getRejected()!=0){
				    			out.println("Rejected");
				    		}else if(categorylist.getRework()!=0){
				    			out.println("Rework");
				    		}else{
				    			out.println("Not Reviewed");
				    		}
						}
					}
				}
		
		%>
			</td>

			<% } %>
		</tr>
		
		<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>

		<%} %>



		<%} %>
		

	</table>
	
	<br>
	<table id="stats_tbl" class="borderAll">
	<th>Project Details</th>
	<th>Total</th>
	<%
	 Iterator it=timesheetProjects.iterator();	
 	while(it.hasNext()){
	String projectname=it.next().toString();
	%>
	<tr>
		<td><%=projectname %></td>
		<td>
			<%
				double projTotal=0.0;
				for(TimeSheetProjectAssignVO projectlist:timeSheetProjectAssignList){
					String projAndActivity=projectlist.getProjectName().getProjectName()+"("+projectlist.getProjectActivity().getActivityName()+")";
					if(projAndActivity.equals(projectname) && projectlist.getEmployeeName().getEmployeeId().equals(emp.getEmployeeId())){
						projTotal=projTotal+projectlist.getEnterTime();
					}
				}
				out.println(projTotal);
		%>
			</td>
	
	</tr>
	
	<%} %>
	
		<%Iterator itr=timesheetcategory.iterator();	
	 	while(itr.hasNext()){
		String categoryname=itr.next().toString();
	%>
		
		
		
		<tr>
			
			<td><%=categoryname %></td>
			<td>
			<%
				double catTotal=0.0;
				for(TimeSheetCategoryAssignVO categorylist:timeSheetCategoryAssignList){
					if(categorylist.getTimesheetCategoryName()!=null){
						if(categorylist.getTimesheetCategoryName().getName().equals(categoryname) && categorylist.getEmployeeName().getEmployeeId().equals(emp.getEmployeeId())){
							catTotal=catTotal+categorylist.getEnterTime();
						}
					}
				}
				out.println(catTotal);
		
		%>
			</td>

			
		</tr>

		<%} %>
	
	
	</table>
	
	
	<br>
	<table id="stats_tbl" class="borderAll">
	<th>Leave Type</th>
	<th>Date Applied</th>
	<th>Status</th>
	<%
	for(LeaveHistoryVO leaveHistoryList:leaveHistorylist){
		if(leaveHistoryList.getEmpIdObj().getEmployeeId().equals(emp.getEmployeeId())){
		%>
		<tr><td><%=leaveHistoryList.getLeaveTypeIdObj().getLeaveTypeName() %></td><td><%=leaveHistoryList.getLeaveDate() %></td><td><%=leaveHistoryList.getLeaveStatus() %></td></tr>
		<%
		}
	}
	%>
	<tr></tr>
	</table>
	
	<br>
	<table id="stats_tbl" class="borderAll">
	<th>Expense Type</th>
	<th>Date Applied</th>
	<th>Amount</th>
	<th>Status</th>
	<th>Attachment</th>
	<th>Remarks</th>
	<%
	for(ExpenseStatusTrackerVO expenseHistoryList:expenseHistorylist){
		
		if(expenseHistoryList.getHcmoExpensesId().getHcmoEmployeeId().getEmployeeId().equals(emp.getEmployeeId())){
			
			String attachment="--";
			if(expenseHistoryList.getHcmoExpensesId().getExpAttachFileName()!=null){
				attachment=expenseHistoryList.getHcmoExpensesId().getExpAttachFileName();
			}
			ExpensesTypeVO expType = new ExpensesTypeVO();
			expType = expTypeService.getExpensesType(expenseHistoryList.getHcmoExpensesId().getHcmoExpensesId());
			String expenseName="";
			if(expType!=null){
				expenseName=expType.getName();
			}
		%>
		<tr><td><%=expenseName %></td><td><%=expenseHistoryList.getCreated() %></td><td><%=expenseHistoryList.getHcmoExpensesId().getTotalAmount() %></td><td><%=expenseHistoryList.getApprovalStatus() %></td><td><%=attachment %></td><td></td></tr>
		<%
		}
	}
	%>
	<tr></tr>
	</table>
  <%} %>
</s:form></div>
</div>