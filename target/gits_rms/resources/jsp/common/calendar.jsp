<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ page  language="java" import="java.util.*,java.text.*"%>
<%@page import="com.gits.rms.utils.DateUtils"%>
<%@page import="com.gits.rms.vo.EventsVO"%>

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
 List<EventsVO> alllist=new ArrayList<EventsVO>();
 SimpleDateFormat dfMySQLDate = new SimpleDateFormat("yyyy-MM-dd");
 int iYear=nullIntconv(request.getAttribute("yearList").toString());
 int iMonth=nullIntconv(request.getAttribute("monthValue").toString());
 
 List birthday=(List)request.getAttribute("birthdayList");
 List events=(List)request.getAttribute("eventList");
 
 Map<String,List<EventsVO>> thisYearEvents=(Map)request.getAttribute("dateAndList");
 

 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
 
 request.setAttribute("yearList",iYear) ;
 request.setAttribute("Month",iMonth) ;

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

 GregorianCalendar cal = new GregorianCalendar (iYear, iMonth, 1); 

 int days=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
 int weekStartDay=cal.get(Calendar.DAY_OF_WEEK);
 
 cal = new GregorianCalendar (iYear, iMonth, days); 
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
			border: 0;
			float: left; position: absolute;
		}
		.tooltip:hover em {
			font-family: Candara, Tahoma, Geneva, sans-serif; font-size: 1.2em; font-weight: bold;
			display: block; padding: 0.2em 0 0.6em 0;
		}
		.classic { padding: 0.8em 1em; }
		.custom { padding: 0.5em 0.8em 0.8em 2em; }
		* html a:hover { background: transparent; }
		.classic {background: #D9FF8C; border: 1px solid #FFAD33; }
		
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



<div id="schedulerCalendar_div">
<s:form name="frm" method="post">

<div id="mainmenuCalendarId_div">
			<table class="formouterbirthdayandnews">
				<tr class="empbirthdayandlatestnews">
					<td class="calendartd">

<table width="100%" border="0" cellspacing="0" cellpadding="0">


  <tr>

   
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
    
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      
        <td width="6%">
        <s:url var="dateClick" action="decrement" namespace="/">

	         	<s:param name="yearList" value="#request.yearList"/>	
         </s:url>
         <sj:a href="%{dateClick}" targets="schedulerCalendar_div">Prev</sj:a>
        
        </td>
        <td width="6%">
        <%=iYear%>
        </td>
        <td width="6%">
         <s:url var="dateClick" action="increment" namespace="/">
	         	<s:param name="yearList" value="#request.yearList"/>	
         </s:url>
                <sj:a href="%{dateClick}" targets="schedulerCalendar_div">Next</sj:a>
        </td>
        <td>
        	<s:url var="listEvent" action="listEvent" namespace="/">
	   		</s:url>
        	<sj:a href="%{listEvent}" targets="schedulerCalendar_div">Events</sj:a>
        </td>
        
        <td width="6%">
         <s:url var="dateClick" action="decrementMonth" namespace="/">
	         <s:param name="monthInput" value="#request.monthValue"/>		
         </s:url>
                <sj:a href="%{dateClick}" targets="schedulerCalendar_div">Prev</sj:a>
                
        </td>
        
        <td width="6%">

        <%=new SimpleDateFormat("MMMM").format(new Date(iYear,iMonth,01))%>
        </td>
        
        <td width="6%">
         <s:url var="dateClick" action="incrementMonth" namespace="/">
	         	<s:param name="monthInput" value="#request.monthValue"/>	
         </s:url>
                <sj:a href="%{dateClick}" targets="schedulerCalendar_div">Next</sj:a>
        
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
                	int color=0;
                	Set list = new HashSet();
                	List<EventsVO> moreEvents=new ArrayList<EventsVO>();
                	request.setAttribute("dateDisplay",(cnt-weekStartDay+1));
                	
                	 for(int k=0;k<=birthday.size()-1;k++){
                		 	
                		 	Date date=sdf.parse(birthday.get(k).toString());
                		 if((date.getDate()==(cnt-weekStartDay+1)) && ((date.getMonth()+1)==(iMonth+1)) ){
                			 color=1;
                			 list.add("Birth Day");
                		 }
                	 }
                	 
                	 
                	 for(int day=0;day<=events.size()-1;day++){
                	 	Date allDate=sdf.parse(iYear+"-"+(iMonth+1)+"-"+(cnt-weekStartDay+1));
                	 	Date date1=sdf.parse(events.get(day).toString());
                	 
                	 	if(allDate.equals(date1)){
                	 		color=1;
                	 		
                	 		
                	 		//for(int n=0;n<=thisYearEvents.size()-1;n++){
                	 			 alllist=thisYearEvents.get(dfMySQLDate.format(date1));
                	 			 if(alllist.size()>0){
	                	 			 for(int m=0;m<=alllist.size()-1;m++){
	                	 				 String keyDate=dfMySQLDate.format(alllist.get(m).getStartDate());
	                	 				 if(keyDate.equals(dfMySQLDate.format(date1))){
	                	 					list.add(alllist.get(m).getEventName());

	                	 				 }
	                	 				 
	                	 			 }
                	 			 }
                	 	}
                	 }
                	
                	 
                	if(color==1){
                		request.setAttribute("list",list);
                 %>
                <td class="eventbackground" align="center" width="70" height="60" id="day_<%=(cnt-weekStartDay+1)%>">
                <s:url var="dateClick" value="createNewEvent.action" namespace="/" escapeAmp="false">
               		<s:param name="dateInput" value="#request.dateDisplay"/>
	           		<s:param name="monthInput" value="#request.monthValue"/>
	           		<s:param name="yearInput" value="#request.yearList"/>
               	</s:url>
                <sj:a href="%{dateClick}" targets="schedulerCalendar_div"><%=cnt-weekStartDay+1%></sj:a>
                <div></div>
                <br>
                	
					 
                	<%
                	Date allDate=sdf.parse(iYear+"-"+(iMonth+1)+"-"+(cnt-weekStartDay+1));
                	for(EventsVO event:alllist){
                		
                		Date eventDate=sdf.parse(event.getStartDate().toString());
                		if(allDate.equals(eventDate)){
                			String eventDesc="";
                			moreEvents.add(event);
                			if(event.getDescription()!=null){
                				eventDesc=event.getDescription();
                			}
                			
                			if(list.size()<2){
                			%>
	                			<sj:a cssClass="tooltip" href=""><b><font class="preppy"><%=event.getEventName() %></font></b><span class="classic">Event Name : <%=event.getEventName() %><br>TimeZone : <%=event.getTimeZone() %><br>Event Date : <%=event.getStartDate() %><br>Created By : <%=event.getCreatedBy().getEmpFirstName() %><br>Descrption : <%=eventDesc %></span></sj:a>
								<br>
                			
                			<%
                			}
                		}
                	}
                	
                	if(moreEvents.size()>=2){
                		Iterator itr=moreEvents.iterator();
                		
                		EventsVO moreEvent1=(EventsVO)itr.next();
                		EventsVO moreEvent2=(EventsVO)itr.next();
                		
                		%>
                			<s:url var="moreEventsClick" value="displayMoreEvents.action" namespace="/" escapeAmp="false">
	                				<s:param name="list" value="#request.list"/>
	                		</s:url>
	                			
	                			<sj:a cssClass="tooltip" href=""><b><font class="preppy"><%=moreEvent1.getEventName() %></font></b><span class="classic">Event Name : <%=moreEvent1.getEventName() %><br>TimeZone : <%=moreEvent1.getTimeZone() %><br>Event Date : <%=moreEvent1.getStartDate() %><br>Created By : <%=moreEvent1.getCreatedBy().getEmpFirstName() %><br>Descrption : <%=moreEvent1.getDescription() %></span></sj:a>
								<br>
								<sj:a cssClass="tooltip" href=""><b><font class="preppy"><%=moreEvent2.getEventName() %></font></b><span class="classic">Event Name : <%=moreEvent2.getEventName() %><br>TimeZone : <%=moreEvent2.getTimeZone() %><br>Event Date : <%=moreEvent2.getStartDate() %><br>Created By : <%=moreEvent2.getCreatedBy().getEmpFirstName() %><br>Descrption : <%=moreEvent2.getDescription() %></span></sj:a>
								<br>
                				<sj:a targets="schedulerCalendar_div" cssClass="tooltip" href="%{moreEventsClick}"><font color="blue">more>></font></sj:a>
                				
                		<%
                		
                	}
                	
                	%>
                	
                	
                </td>
                
                <%}else{ %>
                <td style="overflow: hidden" align="center" width="70" height="60" id="day_<%=(cnt-weekStartDay+1)%>">
               <s:url var="dateClick" value="createNewEvent.action" namespace="/" escapeAmp="false">
	           		<s:param name="dateInput" value="#request.dateDisplay"/>
	           		<s:param name="monthInput" value="#request.monthValue"/>
	           		<s:param name="yearInput" value="#request.yearList"/>
               	</s:url>
                <sj:a href="%{dateClick}" targets="schedulerCalendar_div"><%=cnt-weekStartDay+1%></sj:a>
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


	
	
	</td></tr></table></div>

	
</s:form>
</div>