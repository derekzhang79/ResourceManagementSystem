//Onblur Functions
function checkProjectEntered(obj){
	var proEntered=new Array();
	var idSplitProEntered="";
	proEntered=obj.id;
	idSplitProEntered+= proEntered;
	document.getElementById("proEnteredValue").value=idSplitProEntered;
}
function checkProject(obj){
	var projectValue=new Array();
	var idSplitProjectValue="";
	projectValue=obj.id;
    idSplitProjectValue+= projectValue;
	document.getElementById("projectValue").value=idSplitProjectValue;
}
function checkCategoryEntered(obj){
	var catEntered=new Array();
	var idSplitCatEntered="";
	catEntered=obj.id;
	idSplitCatEntered+= catEntered;
	document.getElementById("catEnteredValue").value=idSplitCatEntered;
}
function checkCategory(obj){
	var categoryValue=new Array();
	var idSplitCategoryValue="";
	categoryValue=obj.id;
	idSplitCategoryValue+= categoryValue;
	document.getElementById("categoryValue").value=idSplitCategoryValue;
}

//For Save All Button
function insertTimesheet(ob){
	var arr = new Array();
	//For Timesheet Category
	 arr = document.getElementsByName('timeEnter');
	 var dddd="";
	 var cateValueForTotal="";
	 var proValueForTotal="";
	 var assignCateValueForTotal="";
	 var assignproValueForTotal="";
	 var totalHoursValid;
	 var storeCatValue="";
	 var second;
	 
	 var date=document.getElementsByName('dateInput');
	 var month=document.getElementsByName('monthInput');
	 var year=document.getElementsByName('yearInput');
	 var emp=document.getElementsByName('submit');
	 document.insert.buttonValue.value=ob;
	for(var i = 0; i < arr.length; i++)
    {
        var obj = arr.item(i);
        var id = obj.id;
        var idSplit = id.split("$$$");
		var dateSplit = idSplit[1].split("-");
        var objectValue=parseFloat(obj.value);
        
        //After the decimal point,specifies the no of digits like 0.00
        var decimalValueFormat=objectValue.toFixed(2);
        if(obj.value != "0"){
    	dddd += obj.id + "=" + decimalValueFormat+"<<<<<";
        }else{
    	dddd += obj.id + "=" + obj.value+"<<<<<";
        }
    }
	 
	 second=dddd;
	 cateValueForTotal=dddd;
	 document.insert.sbTimeSheetDetails.value=dddd;
	 dddd="";
	 
	//For Timesheet checkbox validation
	var third;
    arr = document.getElementsByName('checkedList');
    for(var i = 0; i < arr.length; i++)
    {
    	var obj = arr.item(i);
    	if(obj.checked == true){
        var id = obj.id;
        dddd += obj.id +"<<<<<";
    	}
       
    }
    third=dddd;
    document.insert.checkedList.value=dddd;
    
	//For Timesheet Project with project activity
	arr = document.getElementsByName('protimeEnter');
    var dddd="";
    for(var i = 0; i < arr.length; i++)
    {
        var obj = arr.item(i);
        var id = obj.id;
        var idSplit = id.split("$$$");
        var dateSplit = idSplit[1].split("-");
        var objectValue=parseFloat(obj.value);
        
        if(obj.value != "0"){
        	 dddd += obj.id + "=" + objectValue.toFixed(2)+"<<<<<";
        }else{
        	dddd += obj.id + "=" + obj.value+"<<<<<";
        }
    }
    proValueForTotal=dddd;
    document.insert.proTimeSheetDetails.value=dddd;
    dddd="";
    second="";
    
    //For  Entered Timesheet Category that may be Approved or Reworked
    arr = document.getElementsByName('timeEnter1');
    var dddd="";
    for(var i = 0; i < arr.length; i++)
    {
        var obj = arr.item(i);
        var id = obj.id;
        var idSplit = id.split("$$$");
        var dateSplit = idSplit[1].split("-");
        var objectValue=parseFloat(obj.value);
        
        if(obj.value != "0"){
        	 dddd += obj.id + "=" + objectValue.toFixed(2)+"<<<<<";
        }else{
        	dddd += obj.id + "=" + obj.value+"<<<<<";
        }
    }
    assignCateValueForTotal=dddd;
    document.insert.timeSheetEnteredDetails.value=dddd;
    dddd="";
    
    //For  Entered Timesheet project with project Activity that may be Approved or Reworked
    arr = document.getElementsByName('protimeEnter1');
    var dddd="";
    for(var i = 0; i < arr.length; i++)
    {
        var obj = arr.item(i);
        var id = obj.id;
        var idSplit = id.split("$$$");
        var dateSplit = idSplit[1].split("-");
        var objectValue=parseFloat(obj.value);
        
        if(obj.value != "0"){
        	 dddd += obj.id + "=" + objectValue.toFixed(2)+"<<<<<";
        }else{
        	dddd += obj.id + "=" + obj.value+"<<<<<";
        }
    }
    assignproValueForTotal=dddd;
    document.insert.protimeSheetEnteredDetails.value=dddd;
    dddd="";
    
      var TotalHoursValueForProEntered;
      var TotalHoursValueForProjectValue;
      var TotalHoursValueForCatEntered;
      var TotalHoursValueForCategoryValue;
      var enteredValue;
      var enteredDoubleValue;
      
      TotalHoursValueForProEntered=document.getElementById("proEnteredValue").value;
      TotalHoursValueForProjectValue=document.getElementById("projectValue").value;
      TotalHoursValueForCatEntered=document.getElementById("catEnteredValue").value;
      TotalHoursValueForCategoryValue=document.getElementById("categoryValue").value;
      
      totalHoursValid=cateValueForTotal+proValueForTotal+assignCateValueForTotal+assignproValueForTotal;
      totalHoursForWeekDateValid=TotalHoursValueForProEntered+TotalHoursValueForProjectValue+TotalHoursValueForCatEntered+TotalHoursValueForCategoryValue;
      var removeSplCharWeekDateListIdArray=totalHoursForWeekDateValid.substring(totalHoursForWeekDateValid.lastIndexOf("$")+1);
      var fetchWeekDate = removeSplCharWeekDateListIdArray;
        
	    //To fetch date from category +project details that can compare with entered week day(fetchWeekDate)
	    var splitSplCharForTotalhours=totalHoursValid.split("<<<<<");
	    for(var j = 0; j < splitSplCharForTotalhours.length; j++){
	    	var forEachDateArray=splitSplCharForTotalhours[j];
	    	var forEachDateOtherNameArray=splitSplCharForTotalhours[j];
	    	var getForEachDate = forEachDateArray.substring(forEachDateArray.lastIndexOf("$")+1,forEachDateArray.indexOf("="));
	    	if(fetchWeekDate==getForEachDate){
	    		enteredValue = forEachDateOtherNameArray.substring(forEachDateOtherNameArray.indexOf("=")+1);
	    		 var zeroValue="";
	    		    if((enteredValue>24.00)||(enteredValue>24.0)||(enteredValue>24)){
	    				 alert("Your time sheet entry cannot exceed beyond  24 hours per day !");
	    				 zeroValue=0;
	    				 document.insert.enteredTotalhoursFalseValue.value=zeroValue;
	    				 return false;
	    			 }else{
	    				 zeroValue="";
	    				 document.insert.enteredTotalhoursFalseValue.value=zeroValue;
	    			 }
	    		if(document.getElementById("enteredTotalhoursValue").value== ''){
	    			document.getElementById("enteredTotalhoursValue").value=parseFloat(enteredValue);
	    		}else{
	    		document.getElementById("enteredTotalhoursValue").value=parseFloat(document.getElementById("enteredTotalhoursValue").value)+parseFloat(enteredValue);
	    		}
	    	}
        }
	    var enteredDoubleValue=document.getElementById("enteredTotalhoursValue").value;
		document.insert.enteredTotalhoursMessageValue.value=enteredDoubleValue;
		//Through the confirmation alert message that given entry time is beyond 8,16 and 24 hours
		if((enteredDoubleValue>8.00)||(enteredDoubleValue>8.0)||(enteredDoubleValue>8)){
			 var agree=confirm("Your time sheet entry is exceeding beyond 8 hours !");
			 if(agree){
			 if((enteredDoubleValue>24.00)||(enteredDoubleValue>24.0)||(enteredDoubleValue>24)){
				 alert("Your time sheet entry cannot exceed beyond  24 hours per day !");
				 zeroValue=0;
				 document.insert.enteredTotalhoursFalseValue.value=zeroValue;
				 return false;
			 }else{
				 zeroValue="";
				 document.insert.enteredTotalhoursFalseValue.value=zeroValue;
			 }
			 }else{
				 zeroValue=0;
				 document.insert.enteredTotalhoursFalseValue.value=zeroValue;
	             return false;
    	     }
		}
		document.insert.enteredTotalhoursMessageValue.value=enteredDoubleValue;
}

//function goTo(){
function timesheet(ob)
{
	var arr = new Array();
    arr = document.getElementsByName('timeEnter');
    var dddd="";
    //alert(ob);
	 document.AppRejForm.buttonValue.value=ob;
  //  alert("go to fun");
    for(var i = 0; i < arr.length; i++)
    {
        var obj = arr.item(i);
        var id = obj.id;
        var idSplit = id.split("$$$");
        var dateSplit = idSplit[1].split("-");
        dddd += obj.id + "=" + obj.value+"<<<<<";
    }
    document.AppRejForm.timeSheetDetails.value=dddd;
    dddd="";
    arr = document.getElementsByName('checkedList');
    for(var i = 0; i < arr.length; i++)
    {
    	var obj = arr.item(i);
    	if(obj.checked == true){
        var id = obj.id;
        dddd += obj.id +"<<<<<";
    	}
    }
    document.AppRejForm.checkedList.value=dddd;
    arr = document.getElementsByName('protimeEnter');
    var dddd="";
    for(var i = 0; i < arr.length; i++)
    {
        var obj = arr.item(i);
        var id = obj.id;
        var idSplit = id.split("$$$");
        var dateSplit = idSplit[1].split("-");
        dddd += obj.id + "=" + obj.value+"<<<<<";
    }
    document.AppRejForm.protimeSheetDetails.value=dddd;
    dddd="";
    return true;
}
function clears(obj){
	if(obj.value==0){
		obj.value="";
	}
}
function checkAll(obj){
    	varr = document.getElementsByName('checkedList');
    	if(obj.value=='Select All'){
        for(var i = 0; i < varr.length; i++)
        {
        	var obj = varr.item(i);
        	obj.checked = true;            
        }
        }else{
        	for(var i = 0; i < varr.length; i++)
            {
            	var obj = varr.item(i);
            	if(obj.checked==true){
            	obj.checked = false;   
            	}
            }
        }
	
	return false;
}

function cate(obj){
	
	document.CheckInOut.buttonValue.value=obj;
}
