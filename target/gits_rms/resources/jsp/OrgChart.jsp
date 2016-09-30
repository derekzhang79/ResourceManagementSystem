<%@ page contentType="text/html; charset=UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<div id="orgChart_videos">
<s:if test="#session.CONFIGURATION=='CONFIGURATION'"><%session.removeAttribute("CONFIGURATION");session.putValue("CONFIGURATION","REMOVE_CONFIGURATION") ;%></s:if>
	<table class="maintable">
		<tr align="center">
			<td class="formtitle"><s:text name="label.header.common.orgChart" /></td>
			<td class="employeedisplaytd">
				<table align="right"><tr><td class="video"><a title="OrgChart" class="vidbox2" href="http://www.youtube.com/watch?v=Ajp5aQ1DwPo"><b>videos</b></a></td></tr></table>
			</td>
		</tr>
	</table>
</div>
<div id="orgChartTab">
	<table style="width: 100%;">
		<tr>
	  		<td style="width: 20%;">
				<sj:div id="orgChartLeftPanelDiv">

					<div class="dtree">
						<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>
					</div>
					<div id="all_employees" style="display: none;">
					    <s:iterator value="emploList">
					            <s:property value="empFirstName"/>$$$<s:property value="empLastName"/>$$$<s:property value="employeeId"/>$$$
						 </s:iterator>
					</div>
					<div id="all_report_to" style="display: none;">
					    <s:iterator value="empReportToList">
					            <s:property value="empIdObj.employeeId"/>$$$<s:property value="supEmpNumber.employeeId"/>$$$
						 </s:iterator>
					</div>
					<div id="empAllArray"></div>
					<div id="empArray"></div>
					<div id="empSupArray"></div>
	
					<script type="text/javascript">
				        function doCallServer(empid){
							doPostCall('orgChartRightPanelDiv','orgChartEmployeeTab.action?employee.employeeId='+empid+'','');
					        return false;
					    }

				        d = new dTree('d');
						d.add(0,-1,'Organization Chart', '', '', '','./resources/plugin/dtree/img/employee.gif');
				
						var empIdsAll = new Array();
						var empIds = new Array();
						var empIdsSup = new Array();
				
						var empReportTo = document.getElementById('all_report_to').innerHTML;
						var temp_empReportTo = empReportTo;
						for(var i=0;temp_empReportTo.indexOf('$$$')>-1;i++){
							var iPos1 = 0;
							var jPos1 = temp_empReportTo.indexOf('$$$'); 
							empIds[i] = temp_empReportTo.substring(iPos1, jPos1).trim();
							jPos1 = jPos1 + 3;
							
							temp_empReportTo = temp_empReportTo.substring(jPos1);
							jPos1 = temp_empReportTo.indexOf('$$$');
							empIdsSup[i] = temp_empReportTo.substring(iPos1, jPos1).trim();
							jPos1 = jPos1 + 3;
							temp_empReportTo = temp_empReportTo.substring(jPos1);
						}
				
						var employees = document.getElementById('all_employees').innerHTML;
						var temp_empIdsAll = employees;
						for(var k=0;temp_empIdsAll.indexOf('$$$')>-1;k++){
							var iPos2 = 0;
							var jPos2 = temp_empIdsAll.indexOf('$$$'); 
							jPos2 = jPos2 + 3;
							
							temp_empIdsAll = temp_empIdsAll.substring(jPos2);
							jPos2 = temp_empIdsAll.indexOf('$$$');
							jPos2 = jPos2 + 3;
				
							temp_empIdsAll = temp_empIdsAll.substring(jPos2);
							jPos2 = temp_empIdsAll.indexOf('$$$');
							empIdsAll[k] = temp_empIdsAll.substring(iPos2, jPos2).trim();
							jPos2 = jPos2 + 3;
							temp_empIdsAll = temp_empIdsAll.substring(jPos2);
						}
						
						var temp_emp = employees;
						for(var j=1;temp_emp.indexOf('$$$')>-1;j++){
							var iPos = 0;
							var jPos = temp_emp.indexOf('$$$'); 
							var firstname = temp_emp.substring(iPos, jPos).trim();
							jPos = jPos + 3;
							
							temp_emp = temp_emp.substring(jPos);
							jPos = temp_emp.indexOf('$$$');
							var lastname = temp_emp.substring(iPos, jPos).trim();
							jPos = jPos + 3;
				
							temp_emp = temp_emp.substring(jPos);
							jPos = temp_emp.indexOf('$$$');
							var empid = temp_emp.substring(iPos, jPos).trim();
							jPos = jPos + 3;
							temp_emp = temp_emp.substring(jPos);
				
							if(empIds.indexOf(empid)>-1){
								var index = empIds.indexOf(empid);
								var sup = empIdsSup[index];
								var supIndex = empIdsAll.indexOf(sup);
								d.add(j, supIndex+1, '<a href=# onclick=doCallServer('+empid+')>'+firstname+' '+lastname+'</a>', '#', 'Employee Title', '','./resources/plugin/dtree/img/employee_black.gif');
							}else{
								d.add(j, 0, '<a href=# onclick=doCallServer('+empid+')>'+firstname+' '+lastname+'</a>', '#', 'Employee Title', '','./resources/plugin/dtree/img/employee_black.gif');
							}
						}
						/*
							document.getElementById('empAllArray').innerHTML = empIdsAll;
							document.getElementById('empArray').innerHTML = empIds;
							document.getElementById('empSupArray').innerHTML = empIdsSup;
						*/
						//document.write(d);
						document.getElementById('tree_data').innerHTML = d;
					</script>
	
					<div id="tree_data"></div>
				</sj:div>   	
	  		</td>
	  		<td style="width: 80%;">
  				<s:url var="orgChartFrameContent" action="orgChartFrameContent"/>
				<sj:div id="orgChartRightPanelDiv" href="%{orgChartFrameContent}"></sj:div>
	  		</td>
		</tr>
	</table>
</div>

<script>
	$(document).ready(function() {
		try{
			$(".vidbox2").jqvideobox({
				'width' : 600,
				'height' : 400,
				'getimage' : false,
				'navigation' : true
			});
		}catch(e){
			
		}
	});
</script>