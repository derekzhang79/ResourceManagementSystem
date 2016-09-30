
// To get the Region, Used in Client Modules
	 function getRegionDetailsClient(){
		  var date = new Date();
		  var time = date.getTime();
		  var zipcode = document.getElementById('cli.zipcode').value;
		  if(zipcode==''||zipcode==undefined){
			  alert('Please choose postalcode');
			  document.getElementById('cli.zipcode').focus;
			  return;
		  }
		  $.get("getRegionDetails.action", { zipcode: zipcode, time: time },
				  function(data){
			  		if(data==''){
					    alert('Error Occured');
			  		}else{
				  		data = data.trim();
				  		var iPos = data.indexOf("$$$");
				  		var city = data.substring(0, iPos);
				  		iPos = iPos + 3;
				  		var state = data.substring(iPos);			  		
				  		document.getElementById('cli.city').value = city;
				  		document.getElementById('cli.state').value = state;
			  		}
		  });
	};

// To get the Region, Used in Employee Module
	function getRegionDetailsEmployee(){
		  var date = new Date();
		  var time = date.getTime();
		  var zipcode = document.getElementById('employee.empZipCode').value;
		  if(zipcode==''||zipcode==undefined){
			  alert('Please choose postalcode');
			  document.getElementById('employee.empZipCode').focus;
			  return;
		  }
		  $.get("getRegionDetails.action", { zipcode: zipcode, time: time },
				  function(data){
			  		if(data==''){
					    alert('Error Occured');
			  		}else{
				  		data = data.trim();
				  		var iPos = data.indexOf("$$$");
				  		var city = data.substring(0, iPos);
				  		iPos = iPos + 3;
				  		var state = data.substring(iPos);			  		
				  		document.getElementById('employee.empCityName').value = city;
				  		document.getElementById('employee.empCounName').value = state;
			  		}
		  });
	};
	
	// To get the Region, Used in the Location Module
	function getRegionDetailsLocation(){
		  var date = new Date();
		  var time = date.getTime();
		  var zipcode = document.getElementById('loc.zipcode').value;
		  if(zipcode==''||zipcode==undefined){
			  alert('Please choose postalcode');
			  document.getElementById('loc.zipcode').focus;
			  return;
		  }
		  $.get("getRegionDetails.action", { zipcode: zipcode, time: time },
				  function(data){
			  		if(data==''){
					    alert('Error Occured');
			  		}else{
				  		data = data.trim();
				  		var iPos = data.indexOf("$$$");
				  		var city = data.substring(0, iPos);
				  		iPos = iPos + 3;
				  		var state = data.substring(iPos);			  		
				  		document.getElementById('loc.city').value = city;
				  		document.getElementById('loc.region').value = state;
			  		}
		  });
	  };
	  
	  
	 // To get the deduction, Used in Paystub Modules
	 function getDeduction(){
		  var date = new Date();
		  var time = date.getTime();
		  var payStubDeduction = document.getElementById('payStubDeduction.deduction.deductionId').value;
		  
		  if(payStubDeduction==''||payStubDeduction==undefined){
			  alert('Please choose Deduction');
			  document.getElementById('payStubDeduction.deduction.deductionId').focus;
			  return;
		  }
		  $.get("getDeductionDetails.action", { payStubDeduction: payStubDeduction,time: time },
				  function(data){
			  		if(data==''){
					    alert('Error Occured');
			  		}else{
				  		data = data.trim();
				  		var iPos = data.indexOf("$$$");
				  		var deductionType = data.substring(0, iPos);
				  		iPos = iPos + 3;
				  		var deductionMode = data.substring(iPos);
				  		document.getElementById('payStubDeduction.deduction.deductionType').value = deductionType;
				  		document.getElementById('payStubDeduction.deduction.deductionMode').value = deductionMode;
			  		}
		  		});
	 };	 
	 
	// To get the amount which is going to reduce based on the entered deduction amount, Used in Paystub Modules
	 function getDeductionAmount(){
		  var date = new Date();
		  var time = date.getTime();
		  var payStubDeductionAmount = document.getElementById('payStubDeduction.deductionAmount').value;
		  var payStubDeductionMode = document.getElementById('payStubDeduction.deduction.deductionMode').value;
		  var payStubId = document.getElementById('payStub.payStubId').value;
		  
		  if(payStubDeductionAmount==''||payStubDeductionAmount==undefined){
			  alert('Please choose Deduction and enter the deduction amount');
			  document.getElementById('payStubDeduction.deductionAmount').focus;
			  return;
		  }
		  if(payStubDeductionMode==''||payStubDeductionMode==undefined){
			  alert('Please choose Deduction');
			  document.getElementById('payStubDeduction.deduction.deductionId').focus;
			  return;
		  }

		  $.get("getDeductionAmount.action", { payStubDeductionAmount: payStubDeductionAmount, payStubDeductionMode: payStubDeductionMode, payStubId: payStubId, time: time },
				  function(data){
			  		if(data==''){
					    alert('Error Occured');
			  		}else{
				  		data = data.trim();
				  		document.getElementById('payStubDeduction.amountDeduced').value = data;
			  		}
		  		});
	 };	 
		
	// To get the estimated hours of the project, Used in Project Activity
	 function getProjectEstHours(){
		 var date = new Date();
		  var time = date.getTime();
		  var proIdEstHours = document.getElementById('proActivity.proObj.projectId').value;
		  
		  if(proIdEstHours==''||proIdEstHours==undefined){
			  alert('Please choose Project');
			  document.getElementById('proActivity.proObj.projectId').focus;
			  return;
		  }
		  $.get("getProjectEstimatedHours.action", { proIdEstHours: proIdEstHours,time: time },
				  function(data){
			  		if(data==''){
					    alert('Error Occured');
			  		}else{
				  		data = data.trim();
				  		document.getElementById('proActivity.proObj.estimatedHours').value = data;
			  		}
		  		});
		  
		  $.get("getRemainingProjectActivityHours.action", { proIdEstHours: proIdEstHours,time: time },
				  function(data){
			  		if(data==''){
					    alert('Error Occured');
			  		}else{
				  		data = data.trim();
				  		document.getElementById('proActivity.proObj.remainingHours').value = data;
			  		}
		  		});
	 };
	 
	// To get the estimated hours of the project, Used in Assign project
	function getProjEstHours(){
		 var date = new Date();
		  var time = date.getTime();
		  var proIdEstHours = document.getElementById('tsProjAssigned.projectName.projectId').value;
		  
		  if(proIdEstHours==''||proIdEstHours==undefined){
			  alert('Please choose Project');
			  document.getElementById('tsProjAssigned.projectName.projectId').focus;
			  return;
		  }
		  $.get("getProjectEstimatedHours.action", { proIdEstHours: proIdEstHours,time: time },
				  function(data){
			  		if(data==''){
					    alert('Error Occured');
			  		}else{
				  		data = data.trim();
				  		document.getElementById('projectEstimatedHours').value = data;
			  		}
		  		});
	 };
	 
	// To get the estimated hours of the project activity, Used in Assign project
		function getProjActivityEstHours(){
			 var date = new Date();
			  var time = date.getTime();
			  var proActivityIdEstHours = document.getElementById('activity').value;
			  if(proActivityIdEstHours==''||proActivityIdEstHours==undefined){
				  alert('Please choose Project Activity');
				  document.getElementById('activity').focus;
				  return;
			  }
			  $.get("getProjectActivityEstimatedHours.action", { proActivityIdEstHours: proActivityIdEstHours,time: time },
					  function(data){
				  		if(data==''){
						    alert('Error Occured');
				  		}else{
					  		data = data.trim();
					  		document.getElementById('projectActivityEstimatedHours').value = data;
				  		}
			  		});
		 };
		 