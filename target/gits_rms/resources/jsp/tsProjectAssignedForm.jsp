<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!-- Multiselect Check Box DropDown Starts -->
	<script src="<s:url value="/resources/plugin/dropdownCheckList_v1.4/js/ui.dropdownchecklist-1.4-min.js"/>" type="text/javascript" ></script>
	<script src="<s:url value="/resources/plugin/dropdownCheckList_v1.4/doc/jquery-ui-1.8.13.custom.min.js"/>" type="text/javascript" ></script>
 	<link rel="stylesheet" type="text/css" href="<s:url value="/resources/plugin/dropdownCheckList_v1.4/css/ui.dropdownchecklist.standalone.css"/>" />
<!-- Multiselect Check Box DropDown Ends-->

<div id="submenu_TimesheetAssignProj_div">
	<div class="submenu_bg">
		<sj:a href="setUpTsProjectAssigned.action" targets="submenu_TimesheetAssignProj_div" indicator="indicatorSubMenuTsAssignProjForm" cssClass="link"><s:text name="MTIAddAssignProject" /></sj:a> |
		<sj:a href="getAllTsProjectAssigned.action" targets="submenu_TimesheetAssignProj_div" indicator="indicatorSubMenuTsAssignProjForm" cssClass="link"><s:text name="MTIViewAssignProject"/></sj:a>|
		<sj:a href="tsProjectAssignSearchForm.action" targets="submenu_TimesheetAssignProj_div" indicator="indicatorSubMenuTsAssignProjForm" cssClass="link"><s:text name="MTISearchAssignProject"/></sj:a>
	</div>
	<br/>
	<img id="indicatorSubMenuTsAssignProjForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
	
<s:if test="#session.USER_NAME==null"><%try{response.sendRedirect("doLogout.action");}catch(Exception e){e.printStackTrace();}%></s:if>
<jsp:include page="common/messages.jsp" flush="true"/>
    <s:form action="insertOrUpdateTsProjectAssigned">
    <table class="maintable">
       <tr>
        	<td align="center" ><table class="formouter">
       <tr>
            <td><table class="employeeformiinertable">
     		<tr>
                <td class="formtitle">
        		   <s:if test="tsProjAssigned==null || tsProjAssigned.projectAssignEmpId == null">
					 <s:text name="label.title.assignProject.add"/>
				   </s:if>
				   <s:else>
					 <s:text name="label.title.assignProject.edit"/>
			   		</s:else>
                </td>
        </tr>
     	<tr>
            <td class="forminner"><table class="tablealign">
               
	  	 <s:if test="tsProjAssigned != null || tsProjAssigned.projectAssignEmpId != null">
		   
	      </s:if>
          <tr><td class="inputtext"><s:text name="label.form.fields.project.projectName"/></td>
	     	     <td class="employeeinputtd">
					<s:select
						id="tsProjAssigned.projectName.projectId"
						name="tsProjAssigned.projectName.projectId" 
						headerKey=""
						headerValue="-- Please Select --"
						list="#request.APPL_PROJECT_LIST"
						listKey="projectId"
						listValue="projectName"
					    cssClass="employeeselect" 
					    onchange="getActivity(this);"
				    />
				</td>
				<td class="inputerrormessage"><s:fielderror fieldName="tsProjAssigned.projectName.projectId"/></td>
			</tr>
			<s:if test="tsProjAssigned==null || tsProjAssigned.projectAssignEmpId == null">
				<tr>
	           		<td class="inputtext"><s:text name="label.header.projectActivity.estimatedHours"/></td>
	         		<td class="employeeinputtd"><s:textfield id="projectEstimatedHours" name="projectEstimatedHours" readonly="true" onfocus="javascript:getProjEstHours()" cssClass="employeeinput"/></td>
				</tr>
			</s:if>
			<tr>
				<td class="inputtext"><s:text name="label.header.projActivity.projectActivityWithManadory"/></td>
	     	 	<td>
	     	 		<div id="resultActivity">
			     	 	<s:select 
			     	 		id="activity"
			     	 		name="activity"
							headerKey=""
							headerValue="-- Please Select --"
							list="#request.APPL_ACTIVITY_LIST"
							listKey="projectActivityId"
							listValue="activityName"
							cssClass="employeeselect" 
					    />
	     	 		</div>
	     	 	</td>
     	 </tr>
     	 <s:if test="tsProjAssigned==null || tsProjAssigned.projectAssignEmpId == null">
	     	<tr>
	       		<td class="inputtext"><s:text name="label.header.projectActivity.estHours"/></td>
	      		<td class="employeeinputtd"><s:textfield id="projectActivityEstimatedHours" name="projectActivityEstimatedHours" readonly="true" onfocus="javascript:getProjActivityEstHours()" cssClass="employeeinput"/></td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
				<td>
					<table>
		  	 			<tr>
		  	 				<td>
		  	 					<s:select
									id="s1"
									name="tsProjAssigned.empIdObjList.empWorkEmail" 
									list="subEmpList"
									listKey="empWorkEmail"
									listValue="empFullName"
									multiple="true"	  
								/>
			   				</td>
			   				<td>
								<div id="hrsDiv" style="overflow: auto; height: 100px; width: 400px;">
									<table id="hrsTable" width="100%" ></table>
								</div>
			   				</td>
			   			</tr>
	   				</table>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr>
	            <td class="inputtext"><s:text name="label.form.fields.common.empName"/></td>
		        <td class="employeeinputtd">
					<s:textfield name="tsProjAssigned.employeeName.empFirstName" cssClass="employeeselect" readonly="true"/>
					<s:hidden name="tsProjAssigned.employeeName.employeeId" />
				</td>
			</tr>
			<tr>
				<td class="inputtext"><s:text name="label.header.assignProject.allocatedHour"/></td>
				<td class="employeeinputtd"><s:textfield name="tsProjAssigned.allocatedHours" cssClass="employeeinput"/></td>
				<td class=ds><s:fielderror fieldName="tsProjAssigned.allocatedHours"/></td>
			</tr>
		</s:else>
		<!--Button image added by, R.Deepika-->
          <tr><td class="inputtext"><s:text name="label.form.fields.common.startDate"/></td>
	           <td class="employeeinputtd">
		       		<sj:datepicker name="tsProjAssigned.projectStartDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput" minDate="-2"/>
	           </td>
	           <td class="inputerrormessage"><s:fielderror fieldName="tsProjAssigned.projectStartDate" /></td>
         </tr>
         <!-- Extra text Removed by, R.Deepika -->
         <tr>
         	<td class="inputtext"><s:text name="label.form.fields.common.enddate"/></td>
		   	<td class="employeeinputtd">
		    	<sj:datepicker name="tsProjAssigned.projectEndDate" showButtonPanel="true" changeMonth="true" changeYear="true" displayFormat="mm/dd/yy" buttonImageOnly="true" cssClass="employeeinput" minDate="-2"/>
		    </td>
         	<td class="inputerrormessage">
				<s:fielderror fieldName="tsProjAssigned.projectEndDate" />
			</td>
         </tr>
         <tr>
	     	<td></td><td class="employeeinputtd"><s:text name="label.date.format"/></td>
	     </tr> 
             	<s:hidden name="tsProjAssigned.projectAssignEmpId"/>
    </table></td></tr></table></td></tr></table></td></tr></table><br/>
    <table align="center"> 
  	     <tr>
  		    <td>
			<img id="indicatorTsProjAssignForm" src="${pageContext.request.contextPath}/resources/images/indicator.gif" alt="Loading..." style="display:none"/>				    
  		    	<sj:submit key="button.label.submit" cssClass="submitbutton117" targets="submenu_TimesheetAssignProj_div" indicator="indicatorTsProjAssignForm"/>
  		    </td>
  	        <td>
  	        <s:if test="tsProjAssigned==null || tsProjAssigned.projectAssignEmpId == null">
  	        	<s:url var="resetProjectAssignedForm" action="resetProjectAssignedForm"></s:url>
  	            <sj:submit href="%{resetProjectAssignedForm}"  key="button.label.reset" cssClass="submitbutton117" targets="submenu_TimesheetAssignProj_div" indicator="indicatorTsProjAssignForm"/>
    	</s:if>
    	<s:else>
	        <s:reset key="button.label.reset" cssClass="resetbutton117" />
    	</s:else>
    	</td>
  		 </tr>
    </table> 	
  	</s:form>
</div>   

<script type="text/javascript">
    function getActivity(projId){ 
        var url = 'getProjectActivity.action';
        jQuery.ajax({
             type: "POST",
             dataType: "html",
             url: url,
             data: "projectId="+projId.value,
             success: function(data){    
             jQuery("#resultActivity").html(data);
             }      
           });
     
    }

	jQuery(document).ready(function() {
		jQuery("select[id^='s1']").children('option:selected').each( 
		    function(id, element) {
			element.selected = false;
		    }
		); 
	       jQuery("#s1").dropdownchecklist({emptyText: "Please select ...", width: 150,onItemClick: itemSelected});
	});
	
	function itemSelected(checkbox, selector){
	     var bChecked = checkbox.prop("checked");
	     var sValue = checkbox.prop("value");
	     var sEmailId = "";
	     if(sValue.indexOf("(") > -1){
	    	 sEmailId = sValue.substring((sValue.indexOf("(")+1),sValue.lastIndexOf(")"));
		 }
	     else{
	    	 sEmailId = sValue;
		 }
	     if(bChecked){
	       addHrsRow(sValue,sEmailId);
	     }
	     else{
	       removeHrsRow(sEmailId);
	     }
	}
	
	function addHrsRow(sValue,sEmailId){
	    var table = document.getElementById("hrsTable");
         var rowCount = table.rows.length;
         var row = table.insertRow(rowCount);
         
	    var cell1 = row.insertCell(0);
        	cell1.innerHTML = sValue;
        	cell1.style['width'] = '200px';

         var cell2 = row.insertCell(1);
         var element = document.createElement("input");
         	element.type = "text";
         	element.setAttribute('onkeydown','return noNumbers(event)');
	        element.id = sEmailId;
			element.name = sEmailId;
	        element.style['width'] = '100px';
         cell2.appendChild(element);	
	}
	
	function removeHrsRow(sEmailId){
	   try {
		var table = document.getElementById("hrsTable");
		var rowCount = table.rows.length;
		for(var i=0; i<rowCount; i++) {
			var row = table.rows[i];
			var txtbox = row.cells[1].childNodes[0];
			if((txtbox != null) && (txtbox.id == sEmailId)){
				table.deleteRow(i);
			} 
		}
         }catch(e) {
         }
	}

	function noNumbers(e){
	  var keynum;
	  var keychar;
	  var numcheck;

	  if(window.event) // IE
	  {
	  keynum = e.keyCode;
	  }
	  else if(e.which) // Netscape/Firefox/Opera
	  {
	  keynum = e.which;
	  }
	  keychar = String.fromCharCode(keynum);
	  numcheck = /\d/;
	  //numcheck = [0-9];
	  //numcheck = charCode || numcheck.keyCode || 0;
	  return numcheck.test(keychar);
	}
	
</script> 	