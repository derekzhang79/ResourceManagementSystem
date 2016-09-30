<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@page language="java" import="java.util.*,java.text.*"%>
<%@page import="com.gits.rms.utils.DateUtils"%>


<%!
public int nullIntconv(String inv){   
    int conv=0;
    try{
        conv=Integer.parseInt(inv);
    }
    catch(Exception e){
    	e.printStackTrace();
    }
    return conv;
}
%>
<%
 int iYear=0;
 String yr = request.getAttribute("yearList").toString(); 
 if(yr!=null && yr.length()>0) {
	 iYear=nullIntconv(yr);	 
 } 	
 int iMonth=0;
 String mn = request.getAttribute("monthList").toString(); 
 if(mn!=null && mn.length()>0) {
	 iMonth=nullIntconv(mn);	 
 }
 if(iMonth >0){
	 iMonth = iMonth - 1;
 }
	Calendar ca = new GregorianCalendar();
 	int iTDay=ca.get(Calendar.DATE);
 	int iTYear=ca.get(Calendar.YEAR);
 	int iTMonth=ca.get(Calendar.MONTH);
 	String sTYear=String.valueOf(iTYear);
 	String sTMonth=new String(String.valueOf(iTMonth));
 	
 	if(iYear==0){
      iYear=iTYear;
      iMonth=iTMonth;
 	}
 	GregorianCalendar cal = new GregorianCalendar (iYear, iMonth, 1); 
	int days=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	int weekStartDay=cal.get(Calendar.DAY_OF_WEEK);
 	cal = new GregorianCalendar (iYear, iMonth, days); 
 	int iTotalweeks=cal.get(Calendar.WEEK_OF_MONTH);
 	Date date = new Date();
	String DateFormat1 = "MM/dd/yyyy";
	DateFormat sdf = new SimpleDateFormat(DateFormat1);
	String sDate = sdf.format(date);
	date = (Date)sdf.parse(sDate);
	request.setAttribute("prevMonth",iMonth);
    request.setAttribute("prevYear",iYear);
%>




<jsp:include page="common/messages.jsp" flush="true"/>

<script>


</script>
	<style>
		body{
			font-family:Verdana, Arial, Helvetica, sans-serif
		}
		.dsb{
			background-color:#EEEEEE
		}
		.today{
			background-color:#F09292
		}
		.reject{
			background-color:ORANGE
		}
		.entered{
			background-color:#63A85D
		}
		.rework{
			background-color:GRAY
		}
		.Approve{
			background-color:#5867DB
		}
	</style>





<div id="timesheetCalendar_div1">
<s:if test="viewTimesheet=='View'">
	<div align="right">
		<s:url var="back" action="projectAssignedViewForm" namespace="/" escapeAmp="false"></s:url>
			<img id="indicatorTimesheetId_form" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>
		<sj:a href="%{back}" cssClass="link" indicator="indicatorTimesheetId_form" targets="timesheetCalendar_div1">Back</sj:a>
	</div>
</s:if>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						
					</tr>
					<tr>
						<td align="center" valign="top">
							<!--centerpart-->
<table width="100%" border="0" cellspacing="0" cellpadding="0">

	<tr>
		
		<!-- Main Content -->
		<td align="center">

<s:form name="frm" method="post">

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="formouter2">
<tr>
<td class="formtitle" colspan="6"> Timesheet Calendar</td>
</tr>
  <tr>
    <td>
    	<table>
    		<tr><td>
    			<table>
    				<tr><td class="reject"></td><td>Reject</td></tr>
    </table>
    </td><td>
    <table>
    <tr><td class="Approve"></td><td>Approved</td></tr>
    </table>
    </td><td>
    <table>
    <tr><td class="rework"></td><td>Rework</td></tr>
    </table>
    </td><td>
    <table>
    <tr><td class="entered"></td><td>New Entry</td></tr>
    </table>
      
    </td>
    <td width="300px" align="right"><div align="right">
    <table>
    <tr><td class="today"></td><td>Today</td></tr>
    
    </table>
    </div>
    </td>
    </tr></table></td>
    
  </tr>
  <tr>
   
    <td><table align="center" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table align="center" width="95%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      	<td width="30px">
		      	<s:url var="calYrDec" value="getEmployeeDataCalendarYearDec.action" namespace="/" escapeAmp="false">
		        <s:param name="monthList" value="%{#request.prevMonth}"></s:param>
		        <s:param name="yearList" value="%{#request.prevYear}"></s:param>
		        </s:url>
				<sj:a href="%{#calYrDec}" targets="timesheetCalendar_div1"><div class="previous" align="center"></div></sj:a>
	    	</td>
        <td style="color:#4E96DE"><div align="center"><h3>Year</h3></div></td>
        
        <td width="30px">
	        <s:url var="calYrInc" value="getEmployeeDataCalendarYearInc.action" namespace="/" escapeAmp="false">
	        <s:param name="monthList" value="%{#request.prevMonth}"></s:param>
	        <s:param name="yearList" value="%{#request.prevYear}"></s:param>
	        </s:url>
	    	<sj:a href="%{#calYrInc}" targets="timesheetCalendar_div1"><div class="next" align="center"></div></sj:a>
        </td>
        <td style="color:#4E96DE"><div align="center"><h3><%=new SimpleDateFormat("MMMM").format(new Date(iYear,iMonth,01))%> <%=iYear%></h3></div></td>
        <td width="7%">
	        <s:url var="calMthDec" value="getEmployeeDataCalendarDec.action" namespace="/" escapeAmp="false">
	        <s:param name="monthList" value="%{#request.prevMonth}"></s:param>
	        <s:param name="yearList" value="%{#request.prevYear}"></s:param>
	        </s:url>
	    	<sj:a href="%{#calMthDec}" targets="timesheetCalendar_div1"><div class="previous" align="center"></div></sj:a>
        </td>
        <td style="color:#4E96DE"><div align="center"><h3>Month</h3></div></td>
        <td width="8%">
	        <s:url var="calMthInc" value="getEmployeeDataCalendarInc.action" namespace="/" escapeAmp="false">
	        <s:param name="monthList" value="%{#request.prevMonth}"></s:param>
	        <s:param name="yearList" value="%{#request.prevYear}"></s:param>
	        </s:url>
			<sj:a href="%{#calMthInc}" targets="timesheetCalendar_div1"><div class="next" align="center"></div></sj:a>
	    </td>
      </tr>
    </table></td>
  </tr>
  
</table>
  </td>
    
  </tr>
  <tr><td>
    <table align="center" border="1" cellpadding="3" cellspacing="0" width="95%" class="timesheetouter">
      <tbody>
      <tr></tr>
        <tr>
          <th>&nbsp;Sunday&nbsp;</th>
          <th>&nbsp;Monday&nbsp;</th>
          <th>&nbsp;Tuesday&nbsp;</th>
          <th>&nbsp;Wedesday&nbsp;</th>
          <th>&nbsp;Thusday&nbsp;</th>
          <th>&nbsp;Friday&nbsp;</th>
          <th>&nbsp;Saturday&nbsp;</th>
        </tr>
        <%
        int cnt =1;
        for(int i=1;i<=iTotalweeks;i++)
        {
        %>
        <tr>
          <% 
            for(int j=1;j<=7;j++){
            	if(cnt<weekStartDay || (cnt-weekStartDay+1)>days){
          %>
           
            <td align="center" height="80" width="80" class="dsb">&nbsp;</td>
          <% 
                }else if((cnt-weekStartDay+1)==iTDay && iMonth==iTMonth && iYear == iTYear){
                	request.setAttribute("dateDisplay",(cnt-weekStartDay+1));
          %>
             
             <td align="center" height="80" width="80" id="day_<%=(cnt-weekStartDay+1)%>" class="today">
             	<s:url var="dateClick" action="getTimeSheetDetails" namespace="/" escapeAmp="false">
	           		<s:param name="dateInput" value="#request.dateDisplay"/>
	           		<s:param name="monthInput" value="#request.prevMonth"/>
	           		<s:param name="yearInput" value="#request.prevYear"/>
               	</s:url>
                <sj:a href="%{dateClick}" targets="timesheetCalendar_div1"><%=cnt-weekStartDay+1%></sj:a>
             </td>
          <%
                }else{
                	request.setAttribute("dateDisplay",(cnt-weekStartDay+1));
           %>
                 <s:iterator value="dateDetail">
                 	<s:set name="dateKey" value="key"></s:set>
                 	
                   	<s:if test="%{#request.dateDisplay == #dateKey}">
		                 <s:set name="dateValue" value="value"></s:set>
		                 <s:set name="status" value="true"></s:set>
                  	</s:if>
                  	<s:else>
                  		<s:if test="%{#status==false}">
                  			<s:set name="status" value="false"></s:set>
                  		</s:if>
                  	</s:else>
                 </s:iterator>
                 
                 <s:if test="%{#status == true}">
                 	<s:if test="%{#dateValue == 'Entered'}">
                 		<td align="center" height="80" width="80" id="day_<%=(cnt-weekStartDay+1)%>" class="entered">
                 			<span>
	                 			<s:url var="dateClick" action="getTimeSheetDetails" namespace="/" escapeAmp="false">
			                 		<s:param name="dateInput" value="#request.dateDisplay"/>
			                 		<s:param name="monthInput" value="#request.prevMonth"/>
			                 		<s:param name="yearInput" value="#request.prevYear"/>
			                 	</s:url>
	        		           <sj:a href="%{dateClick}" targets="timesheetCalendar_div1"><%=cnt-weekStartDay+1%></sj:a>
	        		        </span>
	        		    </td>
                 	</s:if>
                 	
                 	<s:if test="%{#dateValue == 'Rejected'}">
                 		<td align="center" height="80" width="80" id="day_<%=(cnt-weekStartDay+1)%>" class="reject">
                 			<span>
	                 			<s:url var="dateClick" action="getTimeSheetDetails" namespace="/" escapeAmp="false">
			                 		<s:param name="dateInput" value="#request.dateDisplay"/>
			                 		<s:param name="monthInput" value="#request.prevMonth"/>
			                 		<s:param name="yearInput" value="#request.prevYear"/>
	                 			</s:url>
	                 			<sj:a href="%{dateClick}" targets="timesheetCalendar_div1"><%=cnt-weekStartDay+1%></sj:a>
	                 		</span>
	                 	</td>
           			</s:if>
                 
                 	<s:if test="%{#dateValue == 'Rework'}">
                 		<td align="center" height="80" width="80" id="day_<%=(cnt-weekStartDay+1)%>" class="rework">
                 			<span>
	                 			<s:url var="dateClick" action="getTimeSheetDetails" namespace="/" escapeAmp="false">
			                 		<s:param name="dateInput" value="#request.dateDisplay"/>
			                 		<s:param name="monthInput" value="#request.prevMonth"/>
			                 		<s:param name="yearInput" value="#request.prevYear"/>
			                 	</s:url>
								<sj:a href="%{dateClick}" targets="timesheetCalendar_div1"><%=cnt-weekStartDay+1%></sj:a>
                 			</span>
                 		</td>
                 	</s:if>
                 
                 	<s:if test="%{#dateValue == 'Approve'}">
                 		<td align="center" height="80" width="80" id="day_<%=(cnt-weekStartDay+1)%>" class="Approve">
                 			<span>
			                 	<s:url var="dateClick" action="getTimeSheetDetails" namespace="/" escapeAmp="false">
			                 		<s:param name="dateInput" value="#request.dateDisplay"/>
			                 		<s:param name="monthInput" value="#request.prevMonth"/>
			                 		<s:param name="yearInput" value="#request.prevYear"/>
			                 	</s:url>
								<sj:a href="%{dateClick}" targets="timesheetCalendar_div1"><%=cnt-weekStartDay+1%></sj:a>
			                 	</span>
			            </td>
                 	</s:if>
                 </s:if>
                 <s:else>
                     	<td align="center" height="80" width="80" id="day_<%=(cnt-weekStartDay+1)%>"><span>
                     		<s:url var="dateClick" action="getTimeSheetDetails" namespace="/" escapeAmp="false">
		                 		<s:param name="dateInput" value="#request.dateDisplay"/>
		                 		<s:param name="monthInput" value="#request.prevMonth"/>
		                 		<s:param name="yearInput" value="#request.prevYear"/>
                 			</s:url>
                     		<sj:a href="%{dateClick}" targets="timesheetCalendar_div1"><%=cnt-weekStartDay+1%></sj:a></span></td>
                 </s:else>
                 <s:set name="status" value="false"></s:set>
               <% 
                }
                  cnt++; 
              }
            %>
        </tr>
        <% 
        }
        %>
      </tbody>
    </table>
    
    </td></tr>
</table>
    
    <br></br>
    
    <table>
	    <s:iterator id="activityMap" value="activityMap">
	    	<tr>
	    		<td><s:property value="key"></s:property></td><td><s:property value="value"></s:property></td>
	    	</tr>
	    </s:iterator>
    </table>
<br>


</s:form>

</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</div>