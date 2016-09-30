<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ page  language="java" import="java.util.*,java.text.*"%>
<%@page import="com.gits.rms.utils.DateUtils"%>
<%@page import="com.gits.rms.vo.ProjectAssignEmpVO"%>
<%@page import="com.gits.rms.vo.LeaveHistoryVO"%>

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
 SimpleDateFormat dfMySQLDate = new SimpleDateFormat("yyyy-MM-dd");
 int iYear=nullIntconv(request.getAttribute("yearList").toString());

 int iMonth=nullIntconv(request.getAttribute("monthValue").toString());
 

 String startdate=request.getAttribute("startDate").toString();
 String enddate=request.getAttribute("endDate").toString();
 String employeeId=request.getAttribute("employeeId").toString();
 
 	
		
 session.putValue("startDate",startdate) ;
 session.putValue("endDate",enddate) ;
 session.putValue("employeeId",employeeId);

 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
 SimpleDateFormat sdf1=new SimpleDateFormat("MM/dd/yyyy");
 
 	
 request.setAttribute("yearList",iYear) ;
 request.setAttribute("Month",iMonth) ;
 	
 		
 Calendar calendar = Calendar.getInstance();
 calendar.set(iYear, iMonth, 01);


    
 java.util.Date da = new java.util.Date(calendar.getTimeInMillis());
 request.setAttribute("DateMonth",sdf.format(da)) ;
	

 if(iMonth ==0){
 iMonth=12;
 iYear=iYear-1;
 }
 	 
 	
 Calendar ca = new GregorianCalendar();
 int iTDay=ca.get(Calendar.DATE);
 int iTYear=ca.get(Calendar.YEAR);
 int iTMonth=ca.get(Calendar.MONTH);

 if(iYear==0)
 {
    
 iYear=iTYear;
 }
 
 if(iMonth==0)
 {
      
 iMonth=iTMonth;
 }

 GregorianCalendar cal = new GregorianCalendar (iYear, iMonth-1, 1); 

 int days=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
 int weekStartDay=cal.get(Calendar.DAY_OF_WEEK);
 
 cal = new GregorianCalendar (iYear, iMonth-1, days); 
 int iTotalweeks=cal.get(Calendar.WEEK_OF_MONTH);
  
%>



		<style type="text/css">
		.tooltip {
			border-bottom: 1px; color: #000000; outline: none;
			cursor: help; text-decoration: none;
			position: relative;
		}
		.tooltip span {
			margin-left: -999em;
			position: absolute;
		}
		.tooltip:hover span {
			border-radius: 5px 5px; -moz-border-radius: 5px; -webkit-border-radius: 5px; 
			box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.1); -webkit-box-shadow: 5px 5px rgba(0, 0, 0, 0.1); -moz-box-shadow: 5px 5px rgba(0, 0, 0, 0.1);
			font-family: Calibri, Tahoma, Geneva, sans-serif;
			position: absolute; left: 1em; top: 2em; z-index: 99;
			margin-left: 0; width: 250px;
		}
		.tooltip:hover img {
			border: 0; margin: -10px 0 0 -55px;
			float: left; position: absolute;
		}
		.tooltip:hover em {
			font-family: Candara, Tahoma, Geneva, sans-serif; font-size: 1.2em; font-weight: bold;
			display: block; padding: 0.2em 0 0.6em 0;
		}
		.classic { padding: 0.8em 1em; }
		.custom { padding: 0.5em 0.8em 0.8em 2em; }
		* html a:hover { background: transparent; }
		.classic {background: #FFFFAA; border: 1px solid #FFAD33; }
		
		.calendarbox {
			background: -moz-linear-gradient(top, #FAE7D2 5%, #F7CE94 95%);
			
			 /* Safari 4-5, Chrome 1-9 */
			  background: -webkit-gradient(linear, 0% 0%, 5% 95%, from(#FAE7D2), to(#F7CE94));
			  
			  /* Safari 5.1, Chrome 10+ */
			  background: -webkit-linear-gradient(top, #FAE7D2 5%, #F7CE94 95%);
			  
			  /* Firefox 3.6+ */
			  background: -moz-linear-gradient(top, #FAE7D2 5%, #F7CE94 95%);
			  
			  /* IE 10 */
			  background: -ms-linear-gradient(top, #FAE7D2 5%, #F7CE94 95%);
			  
			  /* Opera 11.10+ */
			  background: -o-linear-gradient(top, #FAE7D2 5%, #F7CE94 95%);
			  
			  /* MSIE */
    			filter: progid:DXImageTransform.Microsoft.Gradient(
                StartColorStr='#FAE7D2', EndColorStr='#F7CE94', GradientType=0);
                
                white-space: -moz-pre-wrap !important;  /* Mozilla, since 1999 */
				white-space: -pre-wrap;      /* Opera 4-6 */
				white-space: -o-pre-wrap;    /* Opera 7 */
				white-space: pre-wrap;       /* css-3 */
				word-wrap: break-word;       /* Internet Explorer 5.5+ */
				word-break: break-all;
				white-space: normal;
						
		}
		
		</style>
<script>
function goTo()
{
  document.frm.submit();
}
</script>
<style>
body
{
 font-family:Verdana, Arial, Helvetica, sans-serif
}
.dsb
{
 background-color:#EEEEEE
}

.preppy
{
color:#FF3399;
font-weight:900;
font-style:italic;
}
</style>
<div id="timesheetProjectScheduleCalendar_div">
<s:form name="frm" method="post">
<table>
<tr><td>
<table class="birthdayheadertd">
<tr>
	<td class="fontnewsandbirthday">Employee Schedule</td>
</tr>
</table></td><td>

<table class="birthdayheadertd">
<tr>
	<td class="fontnewsandbirthday">Employee Schedule Details</td>
</tr>
</table>

</td>
</tr>
<tr>
<td width="70%">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      
        <td width="6%">
        <s:url var="dateClick" action="empScheduleDecYear" namespace="/">
	         	<s:param name="dateMonthValue" value="#request.DateMonth"/>	
         </s:url>
         <sj:a href="%{dateClick}" targets="timesheetProjectScheduleCalendar_div">Prev</sj:a>
        
        </td>
        <td width="6%">
        <%=iYear%>
        </td>
        <td width="6%">
         <s:url var="yearClick" action="empScheduleIncYear" namespace="/">
	         	<s:param name="dateMonthValue" value="#request.DateMonth"/>	
         </s:url>
                <sj:a href="%{yearClick}" targets="timesheetProjectScheduleCalendar_div">Next</sj:a>
        
        </td>
        
        <td width="6%">
         <s:url var="dateClick" action="empScheduleDecMonth" namespace="/">
	         <s:param name="dateMonthValue" value="#request.DateMonth"/>		
         </s:url>
               	<sj:a href="%{dateClick}" targets="timesheetProjectScheduleCalendar_div">Prev</sj:a>
                
        </td>
        
        <td width="6%">
		 <%=new SimpleDateFormat("MMMM").format(new Date(iYear,iMonth-1,01))%>
        </td>
        
        <td width="6%">
         <s:url var="dateClick" action="empScheduleIncMonth" namespace="/">
	         	<s:param name="dateMonthValue" value="#request.DateMonth"/>
         </s:url>
                <sj:a href="%{dateClick}" targets="timesheetProjectScheduleCalendar_div">Next</sj:a>
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table align="center" border="1" cellpadding="3" cellspacing="0" width="100%" class="timesheetouter" style="table-layout:fixed">
      <tbody>
        <tr>
          <th>Sun</th>
          <th>Mon</th>
          <th>Tue</th>
          <th>Wed</th>
          <th>Thu</th>
          <th>Fri</th>
          <th>Sat</th>
        </tr>
        <%
        int cnt =1;
        for(int i=1;i<=iTotalweeks;i++)
        {
        %>
        <tr>
          <% 
            for(int j=1;j<=7;j++)
            {
                if(cnt<weekStartDay || (cnt-weekStartDay+1)>days)
                {
                 %>
                <td align="center" height="35" class="dsb">&nbsp;</td>
               <% 
                }
                else
                {
                	Set list = new HashSet();
                	request.setAttribute("dateDisplay",(cnt-weekStartDay+1));
                	Date allDate=sdf.parse(iYear+"-"+(iMonth)+"-"+(cnt-weekStartDay+1));
                	
                	Date start_date=sdf1.parse(startdate);
                	Date end_date=sdf1.parse(enddate);
                	
                	              	
                	if((allDate.equals(start_date) || allDate.after(start_date)) && (allDate.equals(end_date) || allDate.before(end_date)) ){
					%>
                <td class="calendarbox" style="border:1px solid #F5C684;" align="center" width="68" height="58" id="day_<%=(cnt-weekStartDay+1)%>">
              <% 
                            
              %>  
               <%=cnt-weekStartDay+1%>
                	                
                <%
                List<ProjectAssignEmpVO> projectDetailsList=(List<ProjectAssignEmpVO>)request.getAttribute("searchResult");

                List projectList=new ArrayList();
                List projectActivityList=new ArrayList();
                
               
                  for(ProjectAssignEmpVO pro:projectDetailsList){
                	
					
					Date proj_start=sdf.parse(pro.getProjectStartDate().toString());
					Date proj_end=sdf.parse(pro.getProjectEndDate().toString());
					
					
					if((allDate.equals(proj_start) || allDate.after(proj_start)) && (allDate.equals(proj_end) || allDate.before(proj_end))){
						
						String projectname=pro.getProjectName().getProjectName();
						String projectactivity=pro.getProjectActivityId().getActivityName();
						String projectstart=pro.getProjectStartDate().toString();
						String projectend=pro.getProjectEndDate().toString();
						projectList.add(projectname);
						
						projectActivityList.add(projectname+"("+projectactivity+")");
						
						request.setAttribute("Employee",pro.getEmployeeName().getEmpFirstName());
						request.setAttribute("project",pro.getProjectName().getProjectName());
						request.setAttribute("Activity",pro.getProjectActivityId().getActivityName());
						request.setAttribute("start",sdf1.format(pro.getProjectStartDate()));
						request.setAttribute("end",sdf1.format(pro.getProjectEndDate()));
						request.setAttribute("Description",pro.getProjectName().getProjectDesc());
						request.setAttribute("ClientName",pro.getProjectName().getCustomerId().getCustomerName());
						

					}
					  				
    			}
                  request.setAttribute("projectList",projectList);
               
                  request.setAttribute("projectActivityList",projectActivityList);
                  
                  if(projectList.size()>1){
				   %>
						<s:url var="projectClick" value="displayProjectDetails.action" namespace="/" escapeAmp="false">
	           				<s:param name="employeeName" value="#request.Employee"/>
	           				<s:param name="projectName" value="#request.project"/>
	           				<s:param name="activityName" value="#request.Activity"/>
	           				<s:param name="startDate" value="#request.start"/>
	           				<s:param name="endDate" value="#request.end"/>
	           				<s:param name="description" value="#request.Description"/>
	           				<s:param name="clientName" value="#request.ClientName"/>
	           				
               			</s:url>
               			
               			<%
               			
               			if(request.getAttribute("project").toString().length()>15){
               				String pro_name=request.getAttribute("project").toString().substring(0,14);	
               				               			               				
               			%>
               			
               				<sj:a cssClass="tooltip" targets="projectdetailsId_div" href="%{projectClick}"><b><font style="font-family: Georgia;color:#6b3f04"><%=pro_name%></font>..</b><span class="classic">Project Name : <%=request.getAttribute("project") %><br>Project Activity : <%=request.getAttribute("Activity") %><br>Start Date : <%=request.getAttribute("start") %><br>End Date : <%=request.getAttribute("end") %></span></sj:a>
							<s:url var="moreProjectClick" value="displayMoreProjects.action" namespace="/" escapeAmp="false">
	                			
	                			<s:param name="projectActivityList" value="#request.projectActivityList"/>
	                		</s:url>
							<sj:a targets="projectdetailsId_div" href="%{moreProjectClick}"><font color="blue">more>></font></sj:a>	
               			
               			<%	
               				
               			}else{
               			%>
						<sj:a cssClass="tooltip" targets="projectdetailsId_div" href="%{projectClick}"><b><font style="font-family: Georgia;color:#6b3f04"><%=request.getAttribute("project").toString()%></font></b><span class="classic">Project Name : <%=request.getAttribute("project") %><br>Project Activity : <%=request.getAttribute("Activity") %><br>Start Date : <%=request.getAttribute("start") %><br>End Date : <%=request.getAttribute("end") %></span></sj:a>
							<s:url var="moreProjectClick" value="displayMoreProjects.action" namespace="/" escapeAmp="false">
	                			
	                			<s:param name="projectActivityList" value="#request.projectActivityList"/>
	                		</s:url>
							<sj:a targets="projectdetailsId_div" href="%{moreProjectClick}"><font color="blue">more>></font></sj:a>						
						<%
               			}
						
						 }else{
						 if(!projectList.isEmpty()){
							
							 String pro_name=projectList.get(0).toString();	
							 if(pro_name.length()>15){
						%>	
						<s:url var="projectClick" value="displayProjectDetails.action" namespace="/" escapeAmp="false">
	           				<s:param name="employeeName" value="#request.Employee"/>
	           				<s:param name="projectName" value="#request.project"/>
	           				<s:param name="activityName" value="#request.Activity"/>
	           				<s:param name="startDate" value="#request.start"/>
	           				<s:param name="endDate" value="#request.end"/>
	           				<s:param name="description" value="#request.Description"/>
	           				<s:param name="clientName" value="#request.ClientName"/>
	           				
               			</s:url> 
						  <sj:a cssClass="tooltip" targets="projectdetailsId_div" href="%{projectClick}"><b><font style="font-family: Georgia;color:#6b3f04"><%=pro_name.substring(0,14) %></font>..</b><span class="classic">Project Name : <%=request.getAttribute("project") %><br>Project Activity : <%=request.getAttribute("Activity") %><br>Start Date : <%=request.getAttribute("start") %><br>End Date : <%=request.getAttribute("end") %></span></sj:a>
						<%	
							 }else{
								 %>
								 <s:url var="projectClick" value="displayProjectDetails.action" namespace="/" escapeAmp="false">
		           				<s:param name="employeeName" value="#request.Employee"/>
		           				<s:param name="projectName" value="#request.project"/>
		           				<s:param name="activityName" value="#request.Activity"/>
		           				<s:param name="startDate" value="#request.start"/>
		           				<s:param name="endDate" value="#request.end"/>
		           				<s:param name="description" value="#request.Description"/>
		           				<s:param name="clientName" value="#request.ClientName"/>
	           				
		               			</s:url> 
								<sj:a cssClass="tooltip" targets="projectdetailsId_div" href="%{projectClick}"><b><font style="font-family: Georgia;color:#6b3f04"><%=pro_name %></font></b><span class="classic">Project Name : <%=request.getAttribute("project") %><br>Project Activity : <%=request.getAttribute("Activity") %><br>Start Date : <%=request.getAttribute("start") %><br>End Date : <%=request.getAttribute("end") %></span></sj:a>
										 
								 <%
							 }
						 }
						 }
 
                  
                  
             /*Start leave details*/
             	  List<LeaveHistoryVO> empleavedetailsList=(List<LeaveHistoryVO>)request.getAttribute("empleavedetailsList");
                  for(LeaveHistoryVO leave:empleavedetailsList){
    	        	  
                	 
               	  String dt = leave.getLeaveDate().toString();  // Start date
               	  Calendar c = Calendar.getInstance();
               	  c.setTime(sdf.parse(dt));
               	  c.add(Calendar.DATE, leave.getNoOfDays().intValue()-1);  // number of days to add
               	  dt = sdf.format(c.getTime());  // dt is now the new date
                	
					
				  Date leave_start=sdf.parse(leave.getLeaveDate().toString());
				  Date leave_end=sdf.parse(dt);
				
				  String employename=leave.getEmpIdObj().getEmpFirstName();
				  String leave_status=leave.getLeaveStatus();
										
					
					if((allDate.equals(leave_start) || allDate.after(leave_start)) && (allDate.equals(leave_end) || allDate.before(leave_end))){
						request.setAttribute("Employee",employename);
						request.setAttribute("leaveStart",leave_start);
						request.setAttribute("leaveStatus",leave_status);
						request.setAttribute("noOfDays",leave.getNoOfDays().intValue());
						request.setAttribute("Hours",leave.getHours().intValue());
						request.setAttribute("Minutes",leave.getMins().intValue());
						request.setAttribute("ApproveNotes",leave.getApproveNotes());
						%>
						
						<sj:div cssClass="tooltip" cssStyle="border-width: 1px; border-style: solid; border-color: #F0FC08;height:100%;width: 100%;background-color: #FF7077; layer-background-color: #FF7077;"><span class="classic">Employee Name : <%=employename %><br>Leave Start : <%=leave_start %><br>Leave Status : <%=leave_status %><br>No.of Days : <%=leave.getNoOfDays().intValue() %><br></span>
							<s:url var="leaveClick" value="displayLeaveDetails.action" namespace="/" escapeAmp="false">
	           					<s:param name="employeeName" value="#request.Employee"/>
	           					<s:param name="leaveStart" value="#request.leaveStart"/>
	           					<s:param name="leaveStatus" value="#request.leaveStatus"/>
	           					<s:param name="noOfDays" value="#request.noOfDays"/>
	           					<s:param name="Hours" value="#request.Hours"/>
	           					<s:param name="Minutes" value="#request.Minutes"/>
	           					<s:param name="ApproveNotes" value="#request.ApproveNotes"/>
	           				           				
               				</s:url>
							<sj:a targets="projectdetailsId_div" href="%{leaveClick}"><div align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></sj:a>
						</sj:div>
														
						
						<%
                
					}else{
						int hours=leave.getHours().intValue();
						int minutes=leave.getMins().intValue();
						int totalmin=(hours*60)+minutes;
						
							
						String after_end_date = dt;  // Start date
	               	  
               	 	 	Calendar c1 = Calendar.getInstance();
               	  		c1.setTime(sdf.parse(after_end_date));
               	  		c1.add(Calendar.DATE,1);  // number of days to add
               	  		after_end_date = sdf.format(c1.getTime()); 
               	  	
               	  		Date hours_min=sdf.parse(after_end_date);
               	  	
	               	  	request.setAttribute("Employee",employename);
						request.setAttribute("leaveStart",leave_start);
						request.setAttribute("leaveStatus",leave_status);
						request.setAttribute("noOfDays",leave.getNoOfDays().intValue());
						request.setAttribute("Hours",leave.getHours().intValue());
						request.setAttribute("Minutes",leave.getMins().intValue());
						request.setAttribute("ApproveNotes",leave.getApproveNotes());
	               	  	
               	  	
						if(leave.getNoOfDays().intValue()>0 && (hours>0 || minutes>0) && allDate.equals(hours_min)){
							
							if(totalmin<=120){
								%>
							
									<div class="tooltip" style="border-width: 1px; border-style: solid; border-color: #F0FC08;height:75%;width: 100%;background-color: #89C486; layer-background-color: #89C486;">
										<span class="classic">Employee Name : <%=employename %><br>Leave Start : <%=leave_start %><br>Leave Status : <%=leave_status %><br>No.of Days : <%=leave.getNoOfDays().intValue() %><br></span>
											 <s:url var="leaveClick" value="displayLeaveDetails.action" namespace="/" escapeAmp="false">
					           					<s:param name="employeeName" value="#request.Employee"/>
					           					<s:param name="leaveStart" value="#request.leaveStart"/>
					           					<s:param name="leaveStatus" value="#request.leaveStatus"/>
					           					<s:param name="noOfDays" value="#request.noOfDays"/>
					           					<s:param name="Hours" value="#request.Hours"/>
					           					<s:param name="Minutes" value="#request.Minutes"/>
					           					<s:param name="ApproveNotes" value="#request.ApproveNotes"/>
					           					           				
				               				</s:url>
											<sj:a targets="projectdetailsId_div" href="%{leaveClick}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sj:a>
									
									</div>
									
									<div class="tooltip" style="border-width: 1px; border-style: solid; border-color: #F0FC08;height:25%;width:100%;background-color:#FF7077;layer-background-color: #FF7077;" >
										<span class="classic">Employee Name : <%=employename %><br>Leave Start : <%=leave_start %><br>Leave Status : <%=leave_status %><br>No.of Days : <%=leave.getNoOfDays().intValue() %><br></span>
											<s:url var="leaveClick" value="displayLeaveDetails.action" namespace="/" escapeAmp="false">
					           					<s:param name="employeeName" value="#request.Employee"/>
					           					<s:param name="leaveStart" value="#request.leaveStart"/>
					           					<s:param name="leaveStatus" value="#request.leaveStatus"/>
					           					<s:param name="noOfDays" value="#request.noOfDays"/>
					           					<s:param name="Hours" value="#request.Hours"/>
					           					<s:param name="Minutes" value="#request.Minutes"/>
					           					<s:param name="ApproveNotes" value="#request.ApproveNotes"/>
					           					           				
				               				</s:url>
											<sj:a targets="projectdetailsId_div" href="%{leaveClick}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sj:a>
									
									</div>
								
								<%
								
							}else if(totalmin>120 && totalmin<=240){
								
								%>
								
								<div class="tooltip" style="border-width: 1px; border-style: solid; border-color: #F0FC08;height:50%;width: 100%;background-color: #89C486; layer-background-color: #89C486;">
									<span class="classic">Employee Name : <%=employename %><br>Leave Start : <%=leave_start %><br>Leave Status : <%=leave_status %><br>No.of Days : <%=leave.getNoOfDays().intValue() %><br></span>
										<s:url var="leaveClick" value="displayLeaveDetails.action" namespace="/" escapeAmp="false">
				           					<s:param name="employeeName" value="#request.Employee"/>
				           					<s:param name="leaveStart" value="#request.leaveStart"/>
				           					<s:param name="leaveStatus" value="#request.leaveStatus"/>
				           					<s:param name="noOfDays" value="#request.noOfDays"/>
				           					<s:param name="Hours" value="#request.Hours"/>
				           					<s:param name="Minutes" value="#request.Minutes"/>
				           					<s:param name="ApproveNotes" value="#request.ApproveNotes"/>
				           					           				
			               				</s:url>
										<sj:a targets="projectdetailsId_div" href="%{leaveClick}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sj:a>
								
								</div>
								
								<div class="tooltip" style="border-width: 1px; border-style: solid; border-color: #F0FC08;height:50%;width:100%;background-color:#FF7077;layer-background-color: #FF7077;" >
									<span class="classic">Employee Name : <%=employename %><br>Leave Start : <%=leave_start %><br>Leave Status : <%=leave_status %><br>No.of Days : <%=leave.getNoOfDays().intValue() %><br></span>
										<s:url var="leaveClick" value="displayLeaveDetails.action" namespace="/" escapeAmp="false">
				           					<s:param name="employeeName" value="#request.Employee"/>
				           					<s:param name="leaveStart" value="#request.leaveStart"/>
				           					<s:param name="leaveStatus" value="#request.leaveStatus"/>
				           					<s:param name="noOfDays" value="#request.noOfDays"/>
				           					<s:param name="Hours" value="#request.Hours"/>
				           					<s:param name="Minutes" value="#request.Minutes"/>
				           					<s:param name="ApproveNotes" value="#request.ApproveNotes"/>
				           					           				
			               				</s:url>
										<sj:a targets="projectdetailsId_div" href="%{leaveClick}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sj:a>
								</div>
							
								<%
							}else{
								%>
								
								<div class="tooltip" style="border-width: 1px; border-style: solid; border-color: #F0FC08;height:100%;width: 100%;background-color: #FF7077; layer-background-color: #FF7077;">
									<span class="classic">Employee Name : <%=employename %><br>Leave Start : <%=leave_start %><br>Leave Status : <%=leave_status %><br>No.of Days : <%=leave.getNoOfDays().intValue() %><br></span>
										<s:url var="leaveClick" value="displayLeaveDetails.action" namespace="/" escapeAmp="false">
				           					<s:param name="employeeName" value="#request.Employee"/>
				           					<s:param name="leaveStart" value="#request.leaveStart"/>
				           					<s:param name="leaveStatus" value="#request.leaveStatus"/>
				           					<s:param name="noOfDays" value="#request.noOfDays"/>
				           					<s:param name="Hours" value="#request.Hours"/>
				           					<s:param name="Minutes" value="#request.Minutes"/>
				           					<s:param name="ApproveNotes" value="#request.ApproveNotes"/>
				           					           				
			               				</s:url>
										<sj:a targets="projectdetailsId_div" href="%{leaveClick}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sj:a>
								
								</div>
															
								<%
							
							}
							
						}else if(leave.getNoOfDays().intValue()==0 && (hours>0 || minutes>0) && allDate.equals(leave.getLeaveDate())){
							if(totalmin<=120){
								%>
								<div class="tooltip" style="border-width: 1px; border-style: solid; border-color: #F0FC08;height:75%;width: 100%;background-color: #89C486; layer-background-color: #89C486;">
										<span class="classic">Employee Name : <%=employename %><br>Leave Start : <%=leave_start %><br>Leave Status : <%=leave_status %><br>No.of Days : <%=leave.getNoOfDays().intValue() %><br></span>
											<s:url var="leaveClick" value="displayLeaveDetails.action" namespace="/" escapeAmp="false">
					           					<s:param name="employeeName" value="#request.Employee"/>
					           					<s:param name="leaveStart" value="#request.leaveStart"/>
					           					<s:param name="leaveStatus" value="#request.leaveStatus"/>
					           					<s:param name="noOfDays" value="#request.noOfDays"/>
					           					<s:param name="Hours" value="#request.Hours"/>
					           					<s:param name="Minutes" value="#request.Minutes"/>
					           					<s:param name="ApproveNotes" value="#request.ApproveNotes"/>
					           					           				
				               				</s:url>
											<sj:a targets="projectdetailsId_div" href="%{leaveClick}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sj:a>
									
									</div>
									
									<div class="tooltip" style="border-width: 1px; border-style: solid; border-color: #F0FC08;height:25%;width:100%;background-color:#FF7077;layer-background-color: #FF7077;" >
										<span class="classic">Employee Name : <%=employename %><br>Leave Start : <%=leave_start %><br>Leave Status : <%=leave_status %><br>No.of Days : <%=leave.getNoOfDays().intValue() %><br></span>
											<s:url var="leaveClick" value="displayLeaveDetails.action" namespace="/" escapeAmp="false">
					           					<s:param name="employeeName" value="#request.Employee"/>
					           					<s:param name="leaveStart" value="#request.leaveStart"/>
					           					<s:param name="leaveStatus" value="#request.leaveStatus"/>
					           					<s:param name="noOfDays" value="#request.noOfDays"/>
					           					<s:param name="Hours" value="#request.Hours"/>
					           					<s:param name="Minutes" value="#request.Minutes"/>
					           					<s:param name="ApproveNotes" value="#request.ApproveNotes"/>
					           					           				
				               				</s:url>
											<sj:a targets="projectdetailsId_div" href="%{leaveClick}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sj:a>
									</div>
								
								<%
								
							}else if(totalmin>120 && totalmin<=240){
								
								%>
								
								<div class="tooltip" style="border-width: 1px; border-style: solid; border-color: #F0FC08;height:50%;width: 100%;background-color: #89C486; layer-background-color: #89C486;">
									<span class="classic">Employee Name : <%=employename %><br>Leave Start : <%=leave_start %><br>Leave Status : <%=leave_status %><br>No.of Days : <%=leave.getNoOfDays().intValue() %><br></span>
										<s:url var="leaveClick" value="displayLeaveDetails.action" namespace="/" escapeAmp="false">
				           					<s:param name="employeeName" value="#request.Employee"/>
				           					<s:param name="leaveStart" value="#request.leaveStart"/>
				           					<s:param name="leaveStatus" value="#request.leaveStatus"/>
				           					<s:param name="noOfDays" value="#request.noOfDays"/>
				           					<s:param name="Hours" value="#request.Hours"/>
				           					<s:param name="Minutes" value="#request.Minutes"/>
				           					<s:param name="ApproveNotes" value="#request.ApproveNotes"/>
				           					           				
			               				</s:url>
										<sj:a targets="projectdetailsId_div" href="%{leaveClick}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sj:a>
								</div>
								
								<div class="tooltip" style="border-width: 1px; border-style: solid; border-color: #F0FC08;height:50%;width:100%;background-color:#FF7077;layer-background-color: #FF7077;" >
									<span class="classic">Employee Name : <%=employename %><br>Leave Start : <%=leave_start %><br>Leave Status : <%=leave_status %><br>No.of Days : <%=leave.getNoOfDays().intValue() %><br></span>
										<s:url var="leaveClick" value="displayLeaveDetails.action" namespace="/" escapeAmp="false">
				           					<s:param name="employeeName" value="#request.Employee"/>
				           					<s:param name="leaveStart" value="#request.leaveStart"/>
				           					<s:param name="leaveStatus" value="#request.leaveStatus"/>
				           					<s:param name="noOfDays" value="#request.noOfDays"/>
				           					<s:param name="Hours" value="#request.Hours"/>
				           					<s:param name="Minutes" value="#request.Minutes"/>
				           					<s:param name="ApproveNotes" value="#request.ApproveNotes"/>
				           					           				
			               				</s:url>
										<sj:a targets="projectdetailsId_div" href="%{leaveClick}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sj:a>
								</div>
							
								<%
							}else{
								%>
								
								<div class="tooltip" style="border-width: 1px; border-style: solid; border-color: #F0FC08;height:100%;width: 100%;background-color: #FF7077; layer-background-color: #FF7077;">
									<span class="classic">Employee Name : <%=employename %><br>Leave Start : <%=leave_start %><br>Leave Status : <%=leave_status %><br>No.of Days : <%=leave.getNoOfDays().intValue() %><br></span>
										<s:url var="leaveClick" value="displayLeaveDetails.action" namespace="/" escapeAmp="false">
				           					<s:param name="employeeName" value="#request.Employee"/>
				           					<s:param name="leaveStart" value="#request.leaveStart"/>
				           					<s:param name="leaveStatus" value="#request.leaveStatus"/>
				           					<s:param name="noOfDays" value="#request.noOfDays"/>
				           					<s:param name="Hours" value="#request.Hours"/>
				           					<s:param name="Minutes" value="#request.Minutes"/>
				           					<s:param name="ApproveNotes" value="#request.ApproveNotes"/>
				           					           				
			               				</s:url>
										<sj:a targets="projectdetailsId_div" href="%{leaveClick}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sj:a>
								
								</div>
															
								<%
								
							}
											
						}
					}
	  				
    			}
                 
             /*End leave details*/
                  
                                %>
                                
                </td>
               <% 
                	}else{
                		
                	%>
                	<td style="overflow: hidden" align="center" width="70" height="60" id="day_<%=(cnt-weekStartDay+1)%>">
               <s:url var="dateClick" value="createNewEvent.action" namespace="/" escapeAmp="false">
	           		<s:param name="dateInput" value="#request.dateDisplay"/>
	           		
	           		<s:param name="monthInput" value="#request.monthValue"/>
	           		<s:param name="yearInput" value="#request.yearList"/>
               	</s:url>
                <sj:a href="%{dateClick}" targets="timesheetProjectScheduleCalendar_div"><%=cnt-weekStartDay+1%></sj:a>
                </td>
                	<%	
                		
                		
                	}
                	 
                }
                cnt++;
              }
            %>
        </tr>
        <% 
        }
        %>
      </tbody>
    </table></td>
  </tr>
</table></td>

    
  </tr>
  <tr>

 </tr>
</table>


</td>

<td width="30%" class="formouter">

<div id="projectdetailsId_div"></div>


</td>
</tr>
<tr>
<td>
<table align="center">
	<tr>
		<td bgcolor="red" height="10px" width="10px"></td><td>- Leave </td><td bgcolor="green" height="10px" width="10px"></td><td>- Worked</td>
	</tr>
</table>
<table align="center"> 
    	 <tr>
   		    <td>
    		    <img id="indicatorEmpScheduleProjectscheduleId_div" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:url var="getEmpScheduleForm" action="empScheduleForm"></s:url>
	   			 <sj:submit href="%{getEmpScheduleForm}"  key="button.label.ExpBackButton" cssClass="submitbutton117" targets="submenu_EmployeeSchedule_div" indicator="indicatorEmpScheduleProjectscheduleId_div"/>
   		    </td>
   		</tr>
   	  </table>

</td><td></td>
</tr>

</table>

	    
</s:form>
</div>