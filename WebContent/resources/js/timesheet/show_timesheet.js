	dojo.require("dojo.io.*");
	dojo.require("dojo.event.*");
function showDetails(obj,fromDate,toDate,email,maxDays,sXml,sXmlTemplate,month,year,button){
    	var sSubmit = obj.value;
    	var sDate = new Date();
    	var sTime = sDate.getTime();

/*        dojo.io.bind({
	                       url: 'ShowDetails.jsp?time='+sTime+'&email='+email+'&start_date='+fromDate+'&end_date='+toDate+'&days_of_month='+maxDays+'&sXml='+sXml+'&sXmlTemplate='+sXmlTemplate+'&month='+month+'&year='+year+'&button='+button+'&fileName='+formField.value,
	                       handler: dataBack
	                    });
*/	               
	               
        dojo.io.bind({
	                       url: 'ShowDetails.action?time='+sTime+'&email='+email+'&start_date='+fromDate+'&end_date='+toDate+'&days_of_month='+maxDays+'&sXml='+sXml+'&sXmlTemplate='+sXmlTemplate+'&month='+month+'&year='+year+'&button='+button,
	                       handler: dataBack
	                    });

}
function dataBack(type, data, evt)
{
  if (type == 'error')
     alert('Error when retrieving data from the server!');
  else
     if(data == "No"){
     }
     else{
     	alert(data.trim());
     }
}     
function showTimeSheet(){
		var emailObj = document.getElementById("email");
		var startDateObj = document.getElementById("weekStartDate");
		var endDateObj = document.getElementById("weekEndDate");
		var monthObj = document.getElementById("month");
		var yearObj = document.getElementById("year");
		var monthNumObj = document.getElementById("monthInt");
		var weekObj = document.getElementById("week_of_month");

		var email = emailObj.value;
		var startDate = startDateObj.value;
		var endDate = endDateObj.value;
		var month = monthObj.value;
		var year = yearObj.value;
		var monthNum = monthNumObj.value;
		var week = weekObj.value;
		
		var pos = startDate.indexOf('_');
		var s_date = startDate.substring(0,pos);
		pos = endDate.indexOf('_');
		var e_date = endDate.substring(0,pos);
		var mm_yyDate = endDate.substring(pos);

    	var sDate = new Date();
    	var sTime = sDate.getTime();
        dojo.io.bind({
	                       url: 'ShowTimeSheet.action?time='+sTime+'&email='+email+'&start_date='+startDate+'&end_date='+endDate+'&month='+month+'&year='+year+'&monthNum='+monthNum+'&week='+week,

	                       handler: timeSheet
	                    });		
}
function timeSheet(type, data, evt)
{
	var emailObj = document.getElementById("email");
	var startDateObj = document.getElementById("weekStartDate");
	var endDateObj = document.getElementById("weekEndDate");
	var monthObj = document.getElementById("month");
	var yearObj = document.getElementById("year");

	var email = emailObj.value;
	var startDate = startDateObj.value;
	var endDate = endDateObj.value;
	var month = monthObj.value;
	var year = yearObj.value;
	
	var pos = startDate.indexOf('_');
	var s_date = startDate.substring(0,pos);
	pos = endDate.indexOf('_');
	var e_date = endDate.substring(0,pos);
	var mm_yyDate = endDate.substring(pos);

	var roleObj = document.getElementById("role");
	var role = roleObj.value;
	
	var isOwnTSObj = document.getElementById("isOwnTS");
	var isOwnTS = isOwnTSObj.value;

  if (type == 'error')
     alert('Error when retrieving data from the server!');
  else
     if(data == "No"){
     }
     else{
	     if(data.match("Have No Time Sheet Since Appointment.")){
	     	var info = document.getElementById("infoBar");
	     	info.value = data;
			disable(s_date, e_date, mm_yyDate,role,isOwnTS);
	     }else if(data.match("Have No Time Sheet For This Month.")){
	     	var info = document.getElementById("infoBar");
	     	info.value = data;
			disable(s_date, e_date, mm_yyDate,role,isOwnTS);
		 }else if(data.match("Can Update Time Sheet Till Yesterday.")){
	     	var info = document.getElementById("infoBar");
	     	info.value = data;
			disable(s_date, e_date, mm_yyDate,role,isOwnTS);
		 }else if(data.match("Time Sheet Date Beyond Appointment")){
	     	var info = document.getElementById("infoBar");
	     	info.value = data;
			disable(s_date, e_date, mm_yyDate,role,isOwnTS);
	     }else if(data.match("Not Yet Appointed To A Client!")){
	     	var info = document.getElementById("infoBar");
	     	info.value = data;
			disable(s_date, e_date, mm_yyDate,role,isOwnTS);
	     }else{
			// For spliting the filename and location from data
			var dataPos = data.indexOf("###____$$$");
			var fileData = data.substring(0,dataPos);
			var fileStartPos = fileData.indexOf("###FILE_NAME###");
			fileStartPos += 15;
			var fileEndPos = fileData.indexOf("$$$FILE_NAME$$$");
			var fileName = fileData.substring(fileStartPos,fileEndPos);
			fileStartPos = fileData.indexOf("###LOCATION_###");
			fileStartPos += 15;
			fileEndPos = fileData.indexOf("$$$LOCATION_$$$");
			var location = fileData.substring(fileStartPos,fileEndPos);

			// To Show the File Name If Exists Or to show the Upload field with submit button

			if(fileName==""){
				var place = document.getElementById("showFile");
				place.innerHTML = "<span class='infotext'>Upload File</span><br><br>";
				var rdo = document.createElement("input");
				rdo.type = "file";
				rdo.name = "upload";
				rdo.id = "upload";
				place.appendChild(rdo);
				var rdo1 = document.createElement("input");
				rdo1.type = "image";
				rdo1.name = "button";
				rdo1.value = "Upload";
				rdo1.title = "Upload";
				rdo1.alt = "Upload";
				rdo1.src = "../images/buttons/Upload.gif";
				place.appendChild(rdo1)
			}else{
				var place = document.getElementById("showFile");
				var tag = document.getElementsByTagName("tbody");
				var rdo2 = document.createElement("input");
				rdo2.type = "image";
				rdo2.name = "button";
				rdo2.value = "Delete";
				rdo2.title = "Delete";
				rdo2.alt = "Delete";
				rdo2.src = "../images/buttons/Delete.gif";
				var rdo3 = document.createElement("input");
				rdo3.type = "hidden";
				rdo3.name = "fileName";
				rdo3.value = fileName;
				place.innerHTML = "<span class='infotext'>Uploaded File</span><br><br>";
				place.innerHTML += "<a href='" +location+ "'>" +fileName+ "</a>&nbsp;";
				place.appendChild(rdo2);
				place.appendChild(rdo3);
			}
			//split and assign the xml data 
			dataPos = dataPos + 10;
			data = data.substring(dataPos);
	     	var info = document.getElementById("infoBar");
	     	info.value = "Update Your Time Sheet";
			if (window.ActiveXObject)
			  {
			  var doc=new ActiveXObject("Microsoft.XMLDOM");
			  doc.async="false";
			  doc.loadXML(data);
			  }
			// code for Mozilla, Firefox, Opera, etc.
			else
			  {
			  var parser=new DOMParser();
			  var doc=parser.parseFromString(data,"text/xml");
			  }     
	     
			// documentElement always represents the root node
			var xml=doc.documentElement;

			var loopVar = (e_date - s_date)+1;
			
			var field = "";
			var cli_billable = "";
			var cli_billable_notes = "";
			var bci_billable = "";
			var bci_billable_notes = "";
			var non_billable = "";
			var non_billable_notes = "";
			var vacation = "";
			var vacation_notes = "";
			var holiday = "";
			var holiday_notes = "";
			var comp_time = "";
			var comp_time_notes = "";
			var total = "";
//			var notes = "";
			var dateTag = "";
			var pos = "";
			var date = "";
			var div = "";

			var notes_date = parseInt(s_date);
			var projects = document.getElementsByName("projects");
			var project = new Array();

			var iNodeIndex = 0;
			for(var i=0;i<loopVar;i++){
				dateTag = xml.childNodes[i].nodeName;
				pos = dateTag.indexOf("_");
				date = dateTag.substring(pos+1);
				var iProjNodeIndex = 0;
				var iNodeIndexTemp = 0;
				for(var projs=0;projs<projects.length;projs++){
					//get the project name
					var projectName = xml.childNodes[i].childNodes[iProjNodeIndex].nodeName;
					var dollarPos = projectName.lastIndexOf('_');
					projectName = projectName.substring(0, dollarPos) + "$" + projectName.substring(dollarPos+1);
					
					projects[projs].value = projects[projs].value.ReplaceAll(' ', '_'); 
					//populate projects if the project name exists in xml
					if(projectName==projects[projs].value){
						var field = projects[projs].value + "_" + date;
						cli_billable = document.getElementById(field);
						cli_billable.value = xml.childNodes[i].childNodes[iProjNodeIndex].childNodes[0].nodeValue;
						if(isOwnTS.match('false')){cli_billable.disabled = "true";cli_billable.style.color="#FF0000";}
	
						field = projects[projs].value + "_notes_" + date;
						cli_billable_notes = document.getElementById(field);
						cli_billable_notes.value = xml.childNodes[i].childNodes[iProjNodeIndex+1].childNodes[0].nodeValue;
						if(isOwnTS.match('false')){cli_billable_notes.disabled = "true";cli_billable_notes.style.color="#FF0000";}
	
						field = projects[projs].value + "_" + notes_date;
						div = document.getElementById(field);
						if(cli_billable_notes.value!="Empty"){
							div.style.visibility="visible";
							div.style.position="static";
						}
						iProjNodeIndex = iProjNodeIndex + 2;
						iNodeIndexTemp = iNodeIndexTemp + 2;
					}else{
						// populate default values for projects not in xml
						var field = projects[projs].value + "_" + date;
						cli_billable = document.getElementById(field);
						cli_billable.value = 0;
						if(isOwnTS.match('false')){cli_billable.disabled = "true";cli_billable.style.color="#FF0000";}
	
						field = projects[projs].value + "_notes_" + date;
						cli_billable_notes = document.getElementById(field);
						cli_billable_notes.value = "Empty";
						if(isOwnTS.match('false')){cli_billable_notes.disabled = "true";cli_billable_notes.style.color="#FF0000";}
	
						field = projects[projs].value + "_" + notes_date;
						div = document.getElementById(field);
						if(cli_billable_notes.value!="Empty"){
							div.style.visibility="visible";
							div.style.position="static";
						}

						continue;
					}
				}
				
				iNodeIndexTemp = iNodeIndexTemp - 2;
				iNodeIndex = iNodeIndexTemp;
				
				field = "bciBillable_" + date;
				bci_billable = document.getElementById(field);
				bci_billable.value = xml.childNodes[i].childNodes[iNodeIndex+2].childNodes[0].nodeValue;
				if(isOwnTS.match('false')){bci_billable.disabled = "true";bci_billable.style.color="#FF0000";}

				field = "bciBillable_notes_" + date;
				bci_billable_notes = document.getElementById(field);
				bci_billable_notes.value = xml.childNodes[i].childNodes[iNodeIndex+3].childNodes[0].nodeValue;
				if(isOwnTS.match('false')){bci_billable_notes.disabled = "true";bci_billable_notes.style.color="#FF0000";}

				field = "bciBillable_notes_" + notes_date;
				div = document.getElementById(field);
				if(bci_billable_notes.value!="Empty"){
					div.style.visibility="visible";
					div.style.position="static";
				}

				field = "nonBillable_" + date;
				non_billable = document.getElementById(field);
				non_billable.value = xml.childNodes[i].childNodes[iNodeIndex+4].childNodes[0].nodeValue;
				if(isOwnTS.match('false')){non_billable.disabled = "true";non_billable.style.color="#FF0000";}

				field = "nonBillable_notes_" + date;
				non_billable_notes = document.getElementById(field);
				non_billable_notes.value = xml.childNodes[i].childNodes[iNodeIndex+5].childNodes[0].nodeValue;
				if(isOwnTS.match('false')){non_billable_notes.disabled = "true";non_billable_notes.style.color="#FF0000";}

				field = "nonBillable_notes_" + notes_date;
				div = document.getElementById(field);
				if(non_billable_notes.value!="Empty"){
					div.style.visibility="visible";
					div.style.position="static";
				}

				field = "vacation_" + date;
				vacation = document.getElementById(field);
				vacation.value = xml.childNodes[i].childNodes[iNodeIndex+6].childNodes[0].nodeValue;
				if(isOwnTS.match('false')){vacation.disabled = "true";vacation.style.color="#FF0000";}

				field = "vacation_notes_" + date;
				vacation_notes = document.getElementById(field);
				vacation_notes.value = xml.childNodes[i].childNodes[iNodeIndex+7].childNodes[0].nodeValue;
				if(isOwnTS.match('false')){vacation_notes.disabled = "true";vacation_notes.style.color="#FF0000";}

				field = "vacation_notes_" + notes_date;
				div = document.getElementById(field);
				if(vacation_notes.value!="Empty"){
					div.style.visibility="visible";
					div.style.position="static";
				}

				field = "holiday_" + date;
				holiday = document.getElementById(field);
				holiday.value = xml.childNodes[i].childNodes[iNodeIndex+8].childNodes[0].nodeValue;
				if(isOwnTS.match('false')){holiday.disabled = "true";holiday.style.color="#FF0000";}

				field = "holiday_notes_" + date;
				holiday_notes = document.getElementById(field);
				holiday_notes.value = xml.childNodes[i].childNodes[iNodeIndex+9].childNodes[0].nodeValue;
				if(isOwnTS.match('false')){holiday_notes.disabled = "true";holiday_notes.style.color="#FF0000";}

				field = "holiday_notes_" + notes_date;
				div = document.getElementById(field);
				if(holiday_notes.value!="Empty"){
					div.style.visibility="visible";
					div.style.position="static";
				}

				field = "compTime_" + date;
				comp_time = document.getElementById(field);
				comp_time.value = xml.childNodes[i].childNodes[iNodeIndex+10].childNodes[0].nodeValue;
				if(isOwnTS.match('false')){comp_time.disabled = "true";comp_time.style.color="#FF0000";}

				field = "compTime_notes_" + date;
				compTime_notes = document.getElementById(field);
				compTime_notes.value = xml.childNodes[i].childNodes[iNodeIndex+11].childNodes[0].nodeValue;
				if(isOwnTS.match('false')){compTime_notes.disabled = "true";compTime_notes.style.color="#FF0000";}

				field = "compTime_notes_" + notes_date;
				div = document.getElementById(field);
				if(compTime_notes.value!="Empty"){
					div.style.visibility="visible";
					div.style.position="static";
				}

				field = "total_" + date;
				total = document.getElementById(field);
				total.value = xml.childNodes[i].childNodes[iNodeIndex+12].childNodes[0].nodeValue;
				if(isOwnTS.match('false')){total.disabled = "true";total.style.color="#FF0000";}

/*				field = "notes_" + date;
				notes = document.getElementById(field);
				notes.value = xml.childNodes[i].childNodes[13].childNodes[0].nodeValue;
				if(isOwnTS.match('false')){notes.disabled = "true";notes.style.color="#FF0000";}

				field = "showHide_notes_" + date;
				var showHide = document.getElementById(field);
				if(isOwnTS.match('false')){
					if(notes.value=="Empty")
						showHide.style.visibility="hidden";
				}
				
				field = "div_notes_" + date;
				var divNotes = document.getElementById(field);
				if(notes.value!="Empty"){
					divNotes.style.visibility="visible";
					divNotes.style.position="static";
				}*/
								
				if(total.value==0){
					if(isOwnTS.match('false')){
						field = "approve_" + date;
						var approve = document.getElementById(field);
						approve.disabled = "true";
					}
				}
				var approved = xml.childNodes[i].childNodes[iNodeIndex+13].childNodes[0].nodeValue;
				if(approved==1){
					if(isOwnTS.match('false')){
						field = "approve_" + date;
						var approve = document.getElementById(field);
						approve.disabled = "true";
						approve.style.visibility = "hidden";

						field = "approve_cli_" + date;
						approve = document.getElementById(field);
						approve.disabled = "true";
						approve.style.visibility = "hidden";

						field = "approve_bci_" + date;
						approve = document.getElementById(field);
						approve.disabled = "true";
						approve.style.visibility = "hidden";

						field = "approve_non_" + date;
						approve = document.getElementById(field);
						approve.disabled = "true";
						approve.style.visibility = "hidden";

						field = "approve_vac_" + date;
						approve = document.getElementById(field);
						approve.disabled = "true";
						approve.style.visibility = "hidden";

						field = "approve_hol_" + date;
						approve = document.getElementById(field);
						approve.disabled = "true";
						approve.style.visibility = "hidden";

						field = "approve_com_" + date;
						approve = document.getElementById(field);
						approve.disabled = "true";
						approve.style.visibility = "hidden";
					}else{
						field = "submit_" + date;
						var submit = document.getElementById(field);
						submit.disabled = "true";
						submit.style.visibility = "hidden";

						field = "submit_cli_" + date;
						submit = document.getElementById(field);
						submit.disabled = "true";
						submit.style.visibility = "hidden";

						field = "submit_bci_" + date;
						submit = document.getElementById(field);
						submit.disabled = "true";
						submit.style.visibility = "hidden";

						field = "submit_non_" + date;
						submit = document.getElementById(field);
						submit.disabled = "true";
						submit.style.visibility = "hidden";

						field = "submit_vac_" + date;
						submit = document.getElementById(field);
						submit.disabled = "true";
						submit.style.visibility = "hidden";

						field = "submit_hol_" + date;
						submit = document.getElementById(field);
						submit.disabled = "true";
						submit.style.visibility = "hidden";

						field = "submit_com_" + date;
						submit = document.getElementById(field);
						submit.disabled = "true";
						submit.style.visibility = "hidden";
					}
				}
				notes_date++;
			}
		}						
     }
}     
function attach(){
/*	var start_date = document.getElementById("start_date_value");
	var end_date = document.getElementById("end_date_value");

	var submit_xml = {
		script: function (input) { return; },
		varname:"input"
	};

	var approval_submit_xml = {
		script: function (input) { return; },
		varname:"input"
	};
	var as_approval_submit_xml = new bsn.AutoSuggest('approval_submit', approval_submit_xml);	*/
}
function validate(obj){
	
	var submit_value = obj.value;
	var pos = submit_value.indexOf("_");
	var button = submit_value.substring(0,pos);
	pos = pos + 1;
	var date = submit_value.substring(pos);
//	var field = "clientBillable_" + date;
//	var cli_billable = document.getElementById(field);

	var projects = document.getElementsByName("projects");
	var project = new Array();
	var project_notes = new Array();
	
	for(var projs=0;projs<projects.length;projs++){
		var field = projects[projs].value + "_" + date;
		project[projs] = document.getElementById(field);
		field = projects[projs].value + "_notes_" + date;
		project_notes[projs] = document.getElementById(field);
	}
	
	field = "bciBillable_" + date;
	var bci_billable = document.getElementById(field);
	field = "nonBillable_" + date;
	var non_billable = document.getElementById(field);
	field = "vacation_" + date; 
	var vacation =document.getElementById(field);
	field = "holiday_" + date;
	var holiday = document.getElementById(field);
	field = "compTime_" + date;
	var comp_time = document.getElementById(field);
	field = "total_" + date;
	var total = document.getElementById(field);
//	field = "notes_" + date;
//	var notes = document.getElementById(field);
	
	// Fields That Contains Notes
//	field = "proElement_notes_" + date;
//	var cli_billable_notes = document.getElementById(field);
	field = "bciBillable_notes_" + date;
	var bci_billable_notes = document.getElementById(field);
	field = "nonBillable_notes_" + date;
	var non_billable_notes = document.getElementById(field);
	field = "vacation_notes_" + date;
	var vacation_notes = document.getElementById(field);
	field = "holiday_notes_" + date;
	var holiday_notes = document.getElementById(field);
	field = "compTime_notes_" + date;
	var comp_time_notes = document.getElementById(field);
	
	var email = document.getElementById("email");
	var max_days = document.getElementById("daysOfMonth");
	var month = document.getElementById("month");
	var year = document.getElementById("year");

	var sum = 0.00;
	for(var projs=0;projs<projects.length;projs++){
		if(isNaN(project[projs].value)){
			alert("Only Numbers Are Allowed");
			project[projs].focus();
			return false;
		}else if(project[projs].value>24){
			alert("24 Hours Is Maximum");
			project[projs].focus();
			return false;
		}else{
			if(project[projs].value!="")
				sum += parseFloat(project[projs].value,10);
		}
	}

	if(isNaN(bci_billable.value)){
		alert("Only Numbers Are Allowed");
		bci_billable.focus();
		return false;
	}else if(bci_billable.value>24){
		alert("24 Hours Is Maximum");
		bci_billable.focus();
		return false;
	}else{
		if(bci_billable.value!="")
			sum += parseFloat(bci_billable.value,10);
	}

	if(isNaN(non_billable.value)){
		alert("Only Numbers Are Allowed");
		non_billable.focus();
		return false;
	}else if(non_billable.value>24){
		alert("24 Hours Is Maximum");
		non_billable.focus();
		return false;
	}else{
		if(non_billable.value!="")
			sum += parseFloat(non_billable.value,10);
	}

	if(isNaN(vacation.value)){
		alert("Only Numbers Are Allowed");
		vacation.focus();
		return false;
	}else if(vacation.value>24){
		alert("24 Hours Is Maximum");
		vacation.focus();
		return false;
	}else{
		if(vacation.value!="")
			sum += parseFloat(vacation.value,10);
	}

	if(isNaN(holiday.value)){
		alert("Only Numbers Are Allowed");
		holiday.focus();
		return false;
	}else if(holiday.value>24){
		alert("24 Hours Is Maximum");
		holiday.focus();
		return false;
	}else{
		if(holiday.value!="")
			sum += parseFloat(holiday.value,10);
	}

	if(isNaN(comp_time.value)){
		alert("Only Numbers Are Allowed");
		comp_time.focus();
		return false;
	}else if(comp_time.value>24){
		alert("24 Hours Is Maximum");
		comp_time.focus();
		return false;
	}else{
		if(comp_time.value!="")
			sum += parseFloat(comp_time.value,10);
	}
	total.value = parseFloat(sum);		

	if(sum>24){
		alert("Time Limit Exceeds 24 Hours For A Day.");
	}else if(sum==0){
		alert("Populate Your Time Sheet.");
	}else{
		
		for(var projs=0;projs<projects.length;projs++){
			if(project[projs].value==""){ project[projs].value = 0; }
			if(project_notes[projs].value==""){ project_notes[projs].value = "Empty"; }
		}
//	    if(cli_billable.value==""){ cli_billable.value = 0; }
		if(bci_billable.value==""){ bci_billable.value = 0; }
		if(non_billable.value==""){ non_billable.value = 0; }
		if(vacation.value==""){ vacation.value = 0; }
		if(holiday.value==""){ holiday.value = 0; }
		if(comp_time.value==""){ comp_time.value = 0; }
//		if(notes.value==""){ notes.value = "Empty"; }
//		if(cli_billable_notes.value==""){ cli_billable_notes.value = "Empty"; }
		if(bci_billable_notes.value==""){ bci_billable_notes.value = "Empty"; }
		if(non_billable_notes.value==""){ non_billable_notes.value = "Empty"; }
		if(vacation_notes.value==""){ vacation_notes.value = "Empty"; }
		if(holiday_notes.value==""){ holiday_notes.value = "Empty"; }
		if(comp_time_notes.value==""){ comp_time_notes.value = "Empty"; }
		
		var sXml = "<date_"+date+">";
		for(var projs=0;projs<projects.length;projs++){
			
			var field = projects[projs].value;
			var notes_field = projects[projs].value+"_notes";
			//alert(notes_field);
			sXml += "<"+field+">" +project[projs].value + "</"+field+">";
			sXml += "<"+notes_field+">" + project_notes[projs].value + "</"+notes_field+">";
		}
//		sXml += "<"+"client_billable"+">" + cli_billable.value + "</"+"client_billable"+">";
//		sXml += "<"+"client_billable_notes"+">" + cli_billable_notes.value + "</"+"client_billable_notes"+">";
		sXml += "<"+"bci_billable"+">" + bci_billable.value + "</"+"bci_billable"+">";
		sXml += "<"+"bci_billable_notes"+">" + bci_billable_notes.value + "</"+"bci_billable_notes"+">";
		sXml += "<"+"non_billable"+">" + non_billable.value + "</"+"non_billable"+">";
		sXml += "<"+"non_billable_notes"+">" + non_billable_notes.value + "</"+"non_billable_notes"+">";
		sXml += "<"+"vacation"+">" + vacation.value + "</"+"vacation"+">";
		sXml += "<"+"vacation_notes"+">" + vacation_notes.value + "</"+"vacation_notes"+">";
		sXml += "<"+"holiday"+">" + holiday.value + "</"+"holiday"+">";
		sXml += "<"+"holiday_notes"+">" + holiday_notes.value + "</"+"holiday_notes"+">";
		sXml += "<"+"comp_time"+">" + comp_time.value + "</"+"comp_time"+">";
		sXml += "<"+"comp_time_notes"+">" + comp_time_notes.value + "</"+"comp_time_notes"+">";
		sXml += "<"+"total"+">" + parseFloat(sum) + "</"+"total"+">";
//		sXml += "<"+"notes"+">" + notes.value + "</"+"notes"+">";
		
		if(button=="submit")
			sXml += "<"+"approved"+">" + 0 + "</"+"approved"+">";
		else if(button=="approve")
			sXml += "<"+"approved"+">" + 1 + "</"+"approved"+">";
			
		sXml += "</date_"+date+">";
		
		var sXmlTemplate = "<date_"+date+">";
		
		for(var projs=0;projs<projects.length;projs++){
			
			var field = projects[projs].value;
			sXmlTemplate += "<"+field+">0</"+field+">";
			sXmlTemplate += "<"+field+"_notes"+">Empty</"+field+"_notes"+">";
		}
		
//		sXmlTemplate += "<client_billable>0</client_billable>";
//		sXmlTemplate += "<client_billable_notes>Empty</client_billable_notes>";
		sXmlTemplate += "<bci_billable>0</bci_billable>";
		sXmlTemplate += "<bci_billable_notes>Empty</bci_billable_notes>";
		sXmlTemplate += "<non_billable>0</non_billable>";
		sXmlTemplate += "<non_billable_notes>Empty</non_billable_notes>";
		sXmlTemplate += "<vacation>0</vacation>";
		sXmlTemplate += "<vacation_notes>Empty</vacation_notes>";
		sXmlTemplate += "<holiday>0</holiday>";
		sXmlTemplate += "<holiday_notes>Empty</holiday_notes>";
		sXmlTemplate += "<comp_time>0</comp_time>";
		sXmlTemplate += "<comp_time_notes>Empty</comp_time_notes>";
		sXmlTemplate += "<total>0</total>";
//		sXmlTemplate += "<notes>Empty</notes>";
		sXmlTemplate += "<approved>0</approved>";
		sXmlTemplate += "</date_"+date+">";
		
		//alert(sXml);
		//alert(sXmlTemplate);
		sXml = sXml.ReplaceAll('$','_');
		sXmlTemplate = sXmlTemplate.ReplaceAll('$','_');
		sXml = sXml.ReplaceAll(' ','_');
		sXmlTemplate = sXmlTemplate.ReplaceAll(' ','_');
		//alert(sXml);
		//alert(sXmlTemplate);
		if(button=="submit")
			showDetails(obj,date,date,email.value,max_days.value,sXml,sXmlTemplate,month.value,year.value,button);
		else if(button=="approve")
			showDetails(obj,date,date,email.value,max_days.value,sXml,sXmlTemplate,month.value,year.value,button);
	}
}
function validate_all(obj){
	var submit_all = obj.value;
	var email = document.getElementById("email");
	var max_days = document.getElementById("daysOfMonth");
	var pos = submit_all.indexOf("#");
	var nextpos = submit_all.indexOf("#",pos+1);
	var start_date = submit_all.substring(pos+1,nextpos);
	var end_date = submit_all.substring(nextpos+1);
	//alert("Under Construction");
//	showDetails(obj,start_date,end_date,email.value,max_days.value);
}

function disable(s_date,e_date,mm_yyDate,role,isOwnTS){
	var field = "";
//	var cli_billable = "";
	var bci_billable = "";
	var non_billable = "";
	var vacation = "";
	var holiday = "";
	var comp_time = "";
	var total = "";
	var notes = "";
	var dateTag = "";
	var pos = "";
	var date = "";
	var projects = document.getElementsByName("projects");
	var project = new Array();
	var project_id = "";
	
	for(var i=parseInt(s_date,10);i<=parseInt(e_date,10);i++){
		for(var projs=0;projs<projects.length;projs++){
			field = projects[projs].value + "_" + i + mm_yyDate;
			project_id = document.getElementById(field);
			if(isOwnTS.match('false')){project_id.disabled = "true";}
		}
		field = "bciBillable_" + i + mm_yyDate;
		bci_billable = document.getElementById(field);
		if(isOwnTS.match('false')){bci_billable.disabled = "true";}
		
		field = "nonBillable_" + i + mm_yyDate;
		non_billable = document.getElementById(field);
		if(isOwnTS.match('false')){non_billable.disabled = "true";}
		
		field = "vacation_" + i + mm_yyDate;
		vacation = document.getElementById(field);
		if(isOwnTS.match('false')){vacation.disabled = "true";}
		
		field = "holiday_" + i + mm_yyDate;
		holiday = document.getElementById(field);
		if(isOwnTS.match('false')){holiday.disabled = "true";}
		
		field = "compTime_" + i + mm_yyDate;
		comp_time = document.getElementById(field);
		if(isOwnTS.match('false')){comp_time.disabled = "true";}

		field = "total_" + i + mm_yyDate;
		total = document.getElementById(field);
		if(isOwnTS.match('false')){total.disabled = "true";}

/*		field = "notes_" + i + mm_yyDate;
		notes = document.getElementById(field);
		if(isOwnTS.match('false')){notes.disabled = "true";}
*/
		if(isOwnTS.match('false')){
			field = "approve_" + i + mm_yyDate;
			var approve = document.getElementById(field);
			approve.disabled = "true";
			approve.style.visibility = "hidden";
		}
	}
}
String.prototype.ReplaceAll = function(stringToFind,stringToReplace){

    var temp = this;

    var index = temp.indexOf(stringToFind);

        while(index != -1){

            temp = temp.replace(stringToFind,stringToReplace);

            index = temp.indexOf(stringToFind);

        }

        return temp;

}