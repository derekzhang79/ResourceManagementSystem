<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>


<%@page language="java" import="java.util.*,java.text.*"%>
<%@page import="com.gits.rms.utils.DateUtils"%>
<%@page import="com.gits.rms.vo.TimeSheetProjectAssignVO"%>
<%@page import="com.gits.rms.vo.TimeSheetCategoryAssignVO"%>
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
GregorianCalendar cal = new GregorianCalendar(iYear,iMonth-1,1); 
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
			background: -moz-linear-gradient(top, #b3d496 5%, #7ABD3E 95%);
			
			 /* Safari 4-5, Chrome 1-9 */
			  background: -webkit-gradient(linear, 0% 0%, 5% 95%, from(#b3d496), to(#7ABD3E));
			  
			  /* Safari 5.1, Chrome 10+ */
			  background: -webkit-linear-gradient(top, #b3d496 5%, #7ABD3E 95%);
			  
			  /* Firefox 3.6+ */
			  background: -moz-linear-gradient(top, #C4EBA2 5%, #A3CC7E 95%);
			  
			  /* IE 10 */
			  background: -ms-linear-gradient(top, #b3d496 5%, #7ABD3E 95%);
			  
			  /* Opera 11.10+ */
			  background: -o-linear-gradient(top, #b3d496 5%, #7ABD3E 95%);
			  
			  /* MSIE */
    			filter: progid:DXImageTransform.Microsoft.Gradient(
                StartColorStr='#b3d496', EndColorStr='#7ABD3E', GradientType=0);
                
                
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



<div id="timesheetCalendar_employeeScheduler_div">
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
        <s:url var="dateClick" action="empScheduleTsDecYear" namespace="/">

	         	<s:param name="dateMonthValue" value="#request.DateMonth"/>	
         </s:url>
         <sj:a href="%{dateClick}" targets="timesheetCalendar_employeeScheduler_div">Prev</sj:a>
        
        </td>
        <td width="6%">
        <%=iYear%>
        </td>
        <td width="6%">
         <s:url var="dateClick" action="empScheduleTsIncYear" namespace="/">
	         	<s:param name="dateMonthValue" value="#request.DateMonth"/>	
         </s:url>
                <sj:a href="%{dateClick}" targets="timesheetCalendar_employeeScheduler_div">Next</sj:a>
        
        
        </td>
        
        <td width="6%">
         <s:url var="dateClick" action="empScheduleTsDecMonth" namespace="/">
	        <s:param name="dateMonthValue" value="#request.DateMonth"/>		
         </s:url>
               	
                <sj:a href="%{dateClick}" targets="timesheetCalendar_employeeScheduler_div">Prev</sj:a>
                
        </td>
        
        <td width="6%">



        <%=new SimpleDateFormat("MMMM").format(new Date(iYear,iMonth-1,01))%>
        </td>
        
        <td width="6%">
         <s:url var="dateClick" action="empScheduleTsIncMonth" namespace="/">
	         	<s:param name="dateMonthValue" value="#request.DateMonth"/>	
         </s:url>
               	
                <sj:a href="%{dateClick}" targets="timesheetCalendar_employeeScheduler_div">Next</sj:a>
        
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
                
                <td class="calendarbox" style="border:1px solid #A1CC7A;" align="center" width="68" height="58" id="day_<%=(cnt-weekStartDay+1)%>">
              <% 
              
              
              %>  
                <%=cnt-weekStartDay+1%>
                	
                
                <%
                List<TimeSheetProjectAssignVO> timesheetDetailsList=(List<TimeSheetProjectAssignVO>)request.getAttribute("emptimesheetList");
                List<TimeSheetCategoryAssignVO> timesheetCategoryDetailsList=(List<TimeSheetCategoryAssignVO>)request.getAttribute("empCategorytimesheetList");
                
                List projectList=new ArrayList();
                List projectActivityList=new ArrayList();

                  for(TimeSheetProjectAssignVO pro:timesheetDetailsList){
					Date time_start=sdf.parse(pro.getEnterDate().toString());
					if(allDate.equals(time_start)){
						
						String projectname=pro.getProjectName().getProjectName();
						String projectactivity=pro.getProjectActivity().getActivityName();
						Double entervalue=pro.getEnterTime();
						
						projectList.add(projectname);
						projectActivityList.add(projectname+"("+projectactivity+")");
						
						request.setAttribute("Employee",pro.getEmployeeName().getEmpFirstName());
						request.setAttribute("project",pro.getProjectName().getProjectName());
						request.setAttribute("Activity",pro.getProjectActivity().getActivityName());
						request.setAttribute("enterValue",pro.getEnterTime());
						request.setAttribute("projectActivityList",projectActivityList);
						
						
						String approval_status="Not Entered";
						if(entervalue>0 && pro.getApprove()>0){
							approval_status="Approved";
						}
						if(entervalue>0 && pro.getRejected()>0){
							approval_status="Rejected";
						}
						if(entervalue>0 && pro.getRework()>0){
							approval_status="Rework";
						}
						if(entervalue==0){
							approval_status="Not Entered";
						}
						if(entervalue!=0 && pro.getApprove()==0 && pro.getRejected()==0 && pro.getRework()==0){
							approval_status="For Approval";
						}
						request.setAttribute("status",approval_status);
					}
                  }
                  
						if(projectList.size()>1){
						String project_name=projectList.get(0).toString();
						if(request.getAttribute("project").toString().length()>15){
						
						%>
						<s:url var="projectClick" value="displayTimesheetDetails.action" namespace="/" escapeAmp="false">
	           				<s:param name="employeeName" value="#request.Employee"/>
	           				<s:param name="projectName" value="#request.project"/>
	           				<s:param name="activityName" value="#request.Activity"/>
	           				<s:param name="enteredVal" value="#request.enterValue"/>
	           				<s:param name="timesheetStatus" value="#request.status"/>
	           				
               			</s:url>
						<sj:a cssClass="tooltip" targets="projectdetails_div" href="%{projectClick}"><b><font style="font-family: Georgia;color:#4d5602"><%=request.getAttribute("project").toString().substring(0,14) %>..</font></b><span class="classic">Project Name : <%=request.getAttribute("project") %><br>Project Activity : <%=request.getAttribute("Activity") %><br>Entered Value : <%=request.getAttribute("enterValue") %><br>Status : <%=request.getAttribute("status") %></span></sj:a>
						<s:url var="moreProjectClick" value="displayMoreProjects.action" namespace="/" escapeAmp="false">
	                			<s:param name="projectActivityList" value="#request.projectActivityList"/>
	                	</s:url>
						<sj:a targets="projectdetails_div" href="%{moreProjectClick}"><font color="blue">more>></font></sj:a>
						
						<%
							}else{
								%>
								<s:url var="projectClick" value="displayTimesheetDetails.action" namespace="/" escapeAmp="false">
	           				
			           				<s:param name="employeeName" value="#request.Employee"/>
			           				<s:param name="projectName" value="#request.project"/>
			           				<s:param name="activityName" value="#request.Activity"/>
			           				<s:param name="enteredVal" value="#request.enterValue"/>
			           				<s:param name="timesheetStatus" value="#request.status"/>
	           				
               					</s:url>
								<sj:a cssClass="tooltip" targets="projectdetails_div" href="%{projectClick}"><b><font style="font-family: Georgia;color:#4d5602"><%=request.getAttribute("project").toString() %></font></b><span class="classic">Project Name : <%=request.getAttribute("project") %><br>Project Activity : <%=request.getAttribute("Activity") %><br>Entered Value : <%=request.getAttribute("enterValue") %><br>Status : <%=request.getAttribute("status") %></span></sj:a>
								<s:url var="moreProjectClick" value="displayMoreProjects.action" namespace="/" escapeAmp="false">
			                			<s:param name="projectActivityList" value="#request.projectActivityList"/>
			                	</s:url>
								<sj:a targets="projectdetails_div" href="%{moreProjectClick}"><font color="blue">more>></font></sj:a>
								<%
								
							}
                
						}else{
							
							if(!projectList.isEmpty()){
							String project_name=projectList.get(0).toString();
							if(projectList.get(0).toString().length()>15){
						%>
							<s:url var="projectClick" value="displayTimesheetDetails.action" namespace="/" escapeAmp="false">
	           				<s:param name="employeeName" value="#request.Employee"/>
	           				<s:param name="projectName" value="#request.project"/>
	           				<s:param name="activityName" value="#request.Activity"/>
	           				<s:param name="enteredVal" value="#request.enterValue"/>
	           				<s:param name="timesheetStatus" value="#request.status"/>
	           				
               				</s:url>
							<sj:a cssClass="tooltip" targets="projectdetails_div" href="%{projectClick}"><b><font style="font-family: Georgia;color:#4d5602"><%=projectList.get(0).toString().substring(0,14) %></font>..</b><span class="classic">Project Name : <%=request.getAttribute("project") %><br>Project Activity : <%=request.getAttribute("Activity") %><br>Entered Value : <%=request.getAttribute("enterValue") %><br>Status : <%=request.getAttribute("status") %></span></sj:a>
							
						<%
								}else{
						%>
									<s:url var="projectClick" value="displayTimesheetDetails.action" namespace="/" escapeAmp="false">
	           				
			           				<s:param name="employeeName" value="#request.Employee"/>
			           				<s:param name="projectName" value="#request.project"/>
			           				<s:param name="activityName" value="#request.Activity"/>
			           				<s:param name="enteredVal" value="#request.enterValue"/>
			           				<s:param name="timesheetStatus" value="#request.status"/>
			           				
		               				</s:url>
									<sj:a cssClass="tooltip" targets="projectdetails_div" href="%{projectClick}"><b><font style="font-family: Georgia;color:#4d5602"><%=project_name%></font></b><span class="classic">Project Name : <%=request.getAttribute("project") %><br>Project Activity : <%=request.getAttribute("Activity") %><br>Entered Value : <%=request.getAttribute("enterValue") %><br>Status : <%=request.getAttribute("status") %></span></sj:a>
									
									
									<%
								}
							}
							
						}
					
                  for(TimeSheetCategoryAssignVO cat:timesheetCategoryDetailsList){
  					Date time_start=sdf.parse(cat.getEnterDate().toString());
  					if(allDate.equals(time_start)){
        					String categoryName="";
  						if(cat.getTimesheetCategoryName()==null ){
  							categoryName="";
  						}else{
  							categoryName=cat.getTimesheetCategoryName().getName();
  						}
  						Double entervalue=cat.getEnterTime();
  						
  						request.setAttribute("Employee",cat.getEmployeeName().getEmpFirstName());
  						request.setAttribute("category",categoryName);
  						request.setAttribute("enterValue",cat.getEnterTime());
  						String cat_approval_status="Not Entered";
  						
  						if(entervalue>0 && cat.getApprove()>0){
  							cat_approval_status="Approved";
  						}
  						if(entervalue>0 && cat.getRejected()>0){
  							cat_approval_status="Rejected";
  						}
  						if(entervalue>0 && cat.getRework()>0){
  							cat_approval_status="Rework";
  						}
  						if(entervalue==0){
  							cat_approval_status="Not Entered";
  						}
  						if(entervalue!=0 && cat.getApprove()==0 && cat.getRejected()==0 && cat.getRework()==0){
  							cat_approval_status="For Approval";
  						}
  						request.setAttribute("status",cat_approval_status);
  						
  						
  						%>
  						<s:url var="categoryClick" value="displayTimesheetCatDetails.action" namespace="/" escapeAmp="false">
  	           				<s:param name="employeeName" value="#request.Employee"/>
  	           				<s:param name="categoryName" value="#request.category"/>
  	           				<s:param name="enteredVal" value="#request.enterValue"/>
  	           				<s:param name="timesheetStatus" value="#request.status"/>
  	           				
                 			</s:url>
  							<sj:a cssClass="tooltip" targets="projectdetails_div" href="%{categoryClick}"><b><font style="font-family:Georgia;color:#4d5602"><%=categoryName %></font></b><span class="classic">Employee Name : <%=cat.getEmployeeName().getEmpFirstName() %><br>Category Name : <%=categoryName %><br>Entered Value : <%=cat.getEnterTime() %><br>Status : <%=cat_approval_status %><br></span></sj:a>
  						<%
  					}
      			}                
                
                %>
                
                </td>
               <% 
                	}else{
                		
                	%>
                	<td style="overflow: hidden" align="center" width="70" height="60" id="day_<%=(cnt-weekStartDay+1)%>">
               <s:url var="dateClick" value="createNewEvent.action" namespace="/" escapeAmp="false">
	           		<s:param name="dateInput" value="#request.dateDisplay"/>
	           		<s:param name="monthInput" value="#request.Month"/>
	           		<s:param name="yearInput" value="#request.yearList"/>
               	</s:url>
                <sj:a href="%{dateClick}" targets="timesheetCalendar_employeeScheduler_div"><%=cnt-weekStartDay+1%></sj:a>
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

<div id="projectdetails_div"></div>

</td>
</tr>
<tr>
<td>
<table align="center"> 
    	 <tr>
   		    <td>
    		    <img id="indicatorEmpSchedule" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
				<s:url var="getEmpScheduleForm" action="empScheduleForm"></s:url>
	   			 <sj:submit href="%{getEmpScheduleForm}"  key="button.label.ExpBackButton" cssClass="submitbutton117" targets="submenu_EmployeeSchedule_div" indicator="indicatorEmpSchedule"/>
   		    </td>
   		</tr>
   	  </table>
	
</td>
<td></td>
</tr>

</table>

</s:form>
</div>