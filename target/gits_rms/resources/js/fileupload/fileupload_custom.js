// Benefits Module File Upload
		function startCallbackForBenefitsFileUpload() {
			// make something useful before submit (onStart)
			return true;
		}
		
// Benefits Module File Upload Response
		function completeCallbackForBenefitsFileUploadResponse(response) {
			// make something useful after (onComplete)
			// document.getElementById('nr').innerHTML = parseInt(document.getElementById('nr').innerHTML) + 1;
			document.getElementById('submenu_EmployeeBenefits_div').innerHTML = response;
		}
		

		
// Start Of Expenses Module 
		function startCallbackForDelete() {
			// make something useful before submit (onStart)
			return true;
		}

		function completeCallbackForDelete(response) {
			// make something useful after (onComplete)
			// document.getElementById('nr').innerHTML = parseInt(document.getElementById('nr').innerHTML) + 1;
			document.getElementById('MultipleExpensesUploadDiv').innerHTML = response;
		}
		
		function startExpensesCallback() {
			// make something useful before submit (onStart)
			return true;
		}
 
		function completeExpensesCallback(response) {
			// make something useful after (onComplete)
			// document.getElementById('nr').innerHTML = parseInt(document.getElementById('nr').innerHTML) + 1;
			document.ExpensesAttachForm.reset();
			document.getElementById('MultipleExpensesUploadDiv').innerHTML = response;
		}
//End Of Expenses Module

		
// Start of Employee Space 		
		function startCallbackForEmpSpace() {
			// make something useful before submit (onStart)
			return true;
		}

		function completeCallbackForEmpSpace(response) {
			// make something useful after (onComplete)
			// document.getElementById('nr').innerHTML = parseInt(document.getElementById('nr').innerHTML) + 1;
			document.getElementById('submenu_EmployeeSpace_div').innerHTML = response;
		}
//End of Employee Space		