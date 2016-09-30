/* 
 name:venkateswara
 date:7-7-2012
 description: enable all check boxes and hidden check boxes when  select all check box enabled has been done 
 */
//Show or Hide Children View Sub Div
function showHideChildrenRoleDiv(){ 
	if(document.getElementById('childrenMenuViewId').checked==true){
		document.getElementById('childrenMenuSubTableDivId').style.display = "block";
	}else{
		document.getElementById('childrenMenuSubTableDivId').style.display = "none";
	}
}

//Show or Hide Education View Sub Div
function showHideEducationRoleDiv(){ 
	if(document.getElementById('educationMenuViewId').checked==true){
		document.getElementById('educationMenuSubTableDivId').style.display = "block";
	}else{
		document.getElementById('educationMenuSubTableDivId').style.display = "none";
	}
}

//Show or Hide License View Sub Div
function showHideLicenseRoleDiv(){ 
	if(document.getElementById('licenseMenuViewId').checked==true){
		document.getElementById('licenseMenuSubTableDivId').style.display = "block";
	}else{
		document.getElementById('licenseMenuSubTableDivId').style.display = "none";
	}
}

//Show or Hide Work Experience View Sub Div
function showHideWorkExperienceRoleDiv(){ 
	if(document.getElementById('empExperienceMenuViewId').checked==true){
		document.getElementById('workExperienceMenuSubTableDivId').style.display = "block";
	}else{
		document.getElementById('workExperienceMenuSubTableDivId').style.display = "none";
	}
}

//Show or Hide Location History View Sub Div
function showHideLocationHistoryRoleDiv(){ 
	if(document.getElementById('locationHistoryMenuViewId').checked==true){
		document.getElementById('locationHistoryMenuSubTableDivId').style.display = "block";
	}else{
		document.getElementById('locationHistoryMenuSubTableDivId').style.display = "none";
	}
}

//Show or Hide Direct Debit View Sub Div
function showHideDirectDebitRoleDiv(){ 
	if(document.getElementById('directDebitMenuViewId').checked==true){
		document.getElementById('directDebitMenuSubTableDivId').style.display = "block";
	}else{
		document.getElementById('directDebitMenuSubTableDivId').style.display = "none";
	}
}

//Show or Hide Report To View Sub Div
function showHideReportToRoleDiv(){ 
	if(document.getElementById('reportToMenuViewId').checked==true){
		document.getElementById('employeeReportToMenuSubTableDivId').style.display = "block";
	}else{
		document.getElementById('employeeReportToMenuSubTableDivId').style.display = "none";
	}
}

//Show or Hide Passport View Sub Div
function showHidePassportRoleDiv(){ 
	if(document.getElementById('empPassportMenuViewId').checked==true){
		document.getElementById('passportMenuSubTableDivId').style.display = "block";
	}else{
		document.getElementById('passportMenuSubTableDivId').style.display = "none";
	}
}

//Show or Hide Benefits View Sub Div
function showHideBenefitsRoleDiv(){ 
	if(document.getElementById('benefitMenuViewId').checked==true){
		document.getElementById('benefitsMenuSubTableDivId').style.display = "block";
	}else{
		document.getElementById('benefitsMenuSubTableDivId').style.display = "none";
	}
}

//Show or Hide Leave Quota View Sub Div
function showHideLeaveQuotaRoleDiv(){ 
	if(document.getElementById('leaveQuotaMenuViewId').checked==true){
		document.getElementById('leaveQuotaMenuSubTableDivId').style.display = "block";
		
	}else{
		document.getElementById('leaveQuotaMenuSubTableDivId').style.display = "none";
		
		}
	
		
	
}

//Show or Hide PayStub View Sub Div
function showHidePayStubRoleDiv(){ 
	if(document.getElementById('paystubMenuViewId').checked==true){
		document.getElementById('payStubMenuSubTableDivId').style.display = "block";
	}else{
		document.getElementById('payStubMenuSubTableDivId').style.display = "none";
	}
}

//Check or Un-check All CheckBox
function selectAllMenuCheckBox(){ 
	if(document.getElementById('selectAllCheckBoxId').checked==true){
		
		document.getElementById('essMenuAddId').checked=true;
		document.getElementById('expenseMenuAddId').checked=true;
		document.getElementById('leaveMenuAddId').checked=true;
		document.getElementById('timeSheetMenuAddId').checked=true;
		document.getElementById('orgChartMenuAddId').checked=true;
		document.getElementById('reportsMenuAddId').checked=true;
		document.getElementById('messagingMenuAddId').checked=true;
		document.getElementById('employeeSpaceMenuAddId').checked=true;
		document.getElementById('employeetargetMenuAddedId').checked=true;
		document.getElementById('selectAllModulesCheckBoxId').checked=true;

		document.getElementById('countryMenuAddId').checked=true;
		document.getElementById('countryMenuViewId').checked=true;
		document.getElementById('countryMenuUpdateId').checked=true;
		document.getElementById('countryMenuDeleteId').checked=true;
		document.getElementById('countryMenuSelectAllId').checked=true;
		
		document.getElementById('nationalityMenuAddId').checked=true;
		document.getElementById('nationalityMenuViewId').checked=true;
		document.getElementById('nationalityMenuUpdateId').checked=true;
		document.getElementById('nationalityMenuDeleteId').checked=true;
		document.getElementById('nationalityMenuSelectAllId').checked=true;

		document.getElementById('ethnicRaceMenuAddId').checked=true;
		document.getElementById('ethnicRaceMenuViewId').checked=true;
		document.getElementById('ethnicRaceMenuUpdateId').checked=true;
		document.getElementById('ethnicRaceMenuDeleteId').checked=true;
		document.getElementById('ethnicRaceMenuSelectAllId').checked=true;

		document.getElementById('projectMenuAddId').checked=true;
		document.getElementById('projectMenuViewId').checked=true;
		document.getElementById('projectMenuUpdateId').checked=true;
		document.getElementById('projectMenuDeleteId').checked=true;
		document.getElementById('projectMenuSelectAllId').checked=true;

		document.getElementById('customerMenuAddId').checked=true;
		document.getElementById('customerMenuViewId').checked=true;
		document.getElementById('customerMenuUpdateId').checked=true;
		document.getElementById('customerMenuDeleteId').checked=true;
		document.getElementById('customerMenuSelectAllId').checked=true;

		document.getElementById('holidayMenuAddId').checked=true;
		document.getElementById('holidayMenuViewId').checked=true;
		document.getElementById('holidayMenuUpdateId').checked=true;
		document.getElementById('holidayMenuDeleteId').checked=true;
		document.getElementById('holidayMenuSelectAllId').checked=true;

		document.getElementById('salaryGradeMenuAddId').checked=true;
		document.getElementById('salaryGradeMenuViewId').checked=true;
		document.getElementById('salaryGradeMenuUpdateId').checked=true;
		document.getElementById('salaryGradeMenuDeleteId').checked=true;
		document.getElementById('salaryGradeMenuSelectAllId').checked=true;

		document.getElementById('jobTitleMenuAddId').checked=true;
		document.getElementById('jobTitleMenuViewId').checked=true;
		document.getElementById('jobTitleMenuUpdateId').checked=true;
		document.getElementById('jobTitleMenuDeleteId').checked=true;
		document.getElementById('jobTitleMenuSelectAllId').checked=true;

		document.getElementById('clientMenuAddId').checked=true;
		document.getElementById('clientMenuViewId').checked=true;
		document.getElementById('clientMenuUpdateId').checked=true;
		document.getElementById('clientMenuDeleteId').checked=true;
		document.getElementById('clientMenuSelectAllId').checked=true;

		document.getElementById('locationMenuAddId').checked=true;
		document.getElementById('locationMenuViewId').checked=true;
		document.getElementById('locationMenuUpdateId').checked=true;
		document.getElementById('locationMenuDeleteId').checked=true;
		document.getElementById('locationMenuSelectAllId').checked=true;

		document.getElementById('expenseTypeMenuAddId').checked=true;
		document.getElementById('expenseTypeMenuViewId').checked=true;
		document.getElementById('expenseTypeMenuUpdateId').checked=true;
		document.getElementById('expenseTypeMenuDeleteId').checked=true;
		document.getElementById('expenseTypeMenuSelectAllId').checked=true;

		document.getElementById('leaveTypeMenuAddId').checked=true;
		document.getElementById('leaveTypeMenuViewId').checked=true;
		document.getElementById('leaveTypeMenuUpdateId').checked=true;
		document.getElementById('leaveTypeMenuDeleteId').checked=true;
		document.getElementById('leaveTypeMenuSelectAllId').checked=true;

		document.getElementById('leaveQuotaMenuAddId').checked=true;
		document.getElementById('leaveQuotaMenuViewId').checked=true;
		document.getElementById('leaveQuotaMenuUpdateId').checked=true;
		document.getElementById('leaveQuotaMenuDeleteId').checked=true;
		document.getElementById('leaveQuotaMenuSelectAllId').checked=true;
		document.getElementById('leaveQuotaMenuSubTableDivId').style.display = "block";
		document.getElementById('leaveQuotaMenuLeaveTypeId').checked=true;
		document.getElementById('leaveQuotaMenuYearId').checked=true;
		document.getElementById('leaveQuotaMenupreLeaveCarryId').checked=true;
		document.getElementById('leaveQuotaMenuLeaveCarryForwardId').checked=true;
		document.getElementById('leaveQuotaMenuDaysAllotedId').checked=true;
		document.getElementById('leaveQuotaMenuLeaveTakenId').checked=true;

		document.getElementById('leaveapproverMenuAddId').checked=true;
		document.getElementById('leaveapproverMenuViewId').checked=true;
		document.getElementById('leaveapproverMenuUpdateId').checked=true;
		document.getElementById('leaveapproverMenuDeleteId').checked=true;
		document.getElementById('leaveapproverMenuSelectAllId').checked=true;

		document.getElementById('regionMenuAddId').checked=true;
		document.getElementById('regionMenuViewId').checked=true;
		document.getElementById('regionMenuUpdateId').checked=true;
		document.getElementById('regionMenuDeleteId').checked=true;
		document.getElementById('regionMenuSelectAllId').checked=true;

		document.getElementById('employeeMenuAddId').checked=true;
		document.getElementById('employeeMenuViewId').checked=true;
		document.getElementById('employeeMenuUpdateId').checked=true;
		document.getElementById('employeeMenuDeleteId').checked=true;
		document.getElementById('employeeMenuSelectAllId').checked=true;

		document.getElementById('departmentMenuAddId').checked=true;
		document.getElementById('departmentMenuViewId').checked=true;
		document.getElementById('departmentMenuUpdateId').checked=true;
		document.getElementById('departmentMenuDeleteId').checked=true;
		document.getElementById('departmentMenuSelectAllId').checked=true;

		document.getElementById('teamMenuAddId').checked=true;
		document.getElementById('teamMenuViewId').checked=true;
		document.getElementById('teamMenuUpdateId').checked=true;
		document.getElementById('teamMenuDeleteId').checked=true;
		document.getElementById('teamMenuSelectAllId').checked=true;

		document.getElementById('currencyMenuAddId').checked=true;
		document.getElementById('currencyMenuViewId').checked=true;
		document.getElementById('currencyMenuUpdateId').checked=true;
		document.getElementById('currencyMenuDeleteId').checked=true;
		document.getElementById('currencyMenuSelectAllId').checked=true;

		document.getElementById('taxAndDeductionMenuAddId').checked=true;
		document.getElementById('taxAndDeductionMenuViewId').checked=true;
		document.getElementById('taxAndDeductionMenuUpdateId').checked=true;
		document.getElementById('taxAndDeductionMenuDeleteId').checked=true;
		document.getElementById('taxAndDeductionMenuSelectAllId').checked=true;

		document.getElementById('paystubMenuAddId').checked=true;
		document.getElementById('paystubMenuViewId').checked=true;
		document.getElementById('paystubMenuUpdateId').checked=true;
		document.getElementById('paystubMenuDeleteId').checked=true;
		document.getElementById('paystubMenuSelectAllId').checked=true;
		document.getElementById('payStubMenuSubTableDivId').style.display = "block";
		document.getElementById('payStubMenuGrossSalaryId').checked=true;
		document.getElementById('payStubMenuDecDateId').checked=true;
		document.getElementById('payStubMenuNetSalaryId').checked=true;
		document.getElementById('payStubMenuDeductionId').checked=true;
		document.getElementById('payStubMenuDeductionTypeId').checked=true;
		document.getElementById('payStubMenuDeductionModeId').checked=true;
		document.getElementById('payStubMenudEductionAmountId').checked=true;
		document.getElementById('payStubMenuEffectiveDateId').checked=true;

		document.getElementById('empStatusMenuAddId').checked=true;
		document.getElementById('empStatusMenuViewId').checked=true;
		document.getElementById('empStatusMenuUpdateId').checked=true;
		document.getElementById('empStatusMenuDeleteId').checked=true;
		document.getElementById('empStatusMenuSelectAllId').checked=true;

		document.getElementById('licenseMenuAddId').checked=true;
		document.getElementById('licenseMenuViewId').checked=true;
		document.getElementById('licenseMenuUpdateId').checked=true;
		document.getElementById('licenseMenuDeleteId').checked=true;
		document.getElementById('licenseMenuSelectAllId').checked=true;
		document.getElementById('licenseMenuSubTableDivId').style.display = "block";
		document.getElementById('licenseMenuNumberId').checked=true;
		document.getElementById('licenseMenuDateId').checked=true;
		document.getElementById('licenseMenuRenewalDateId').checked=true;
		document.getElementById('licenseMenuDescriptionId').checked=true;

		document.getElementById('educationMenuAddId').checked=true;
		document.getElementById('educationMenuViewId').checked=true;
		document.getElementById('educationMenuUpdateId').checked=true;
		document.getElementById('educationMenuDeleteId').checked=true;
		document.getElementById('educationMenuSelectAllId').checked=true;
		document.getElementById('educationMenuSubTableDivId').style.display = "block";
		document.getElementById('educationMenuMajorId').checked=true;
		document.getElementById('educationMenuYearId').checked=true;
		document.getElementById('educationMenuGradeId').checked=true;
		document.getElementById('educationMenuStartDateId').checked=true;
		document.getElementById('educationMenuEnddateId').checked=true;
		document.getElementById('educationMenuUnivId').checked=true;
		document.getElementById('educationMenuDegreeId').checked=true;

		document.getElementById('projActivityMenuAddId').checked=true;
		document.getElementById('projActivityMenuViewId').checked=true;
		document.getElementById('projActivityMenuUpdateId').checked=true;
		document.getElementById('projActivityMenuDeleteId').checked=true;
		document.getElementById('projActivityMenuSelectAllId').checked=true;

		document.getElementById('childrenMenuAddId').checked=true;
		document.getElementById('childrenMenuViewId').checked=true;
		document.getElementById('childrenMenuUpdateId').checked=true;
		document.getElementById('childrenMenuDeleteId').checked=true;
		document.getElementById('childrenMenuSelectAllId').checked=true;
		document.getElementById('childrenMenuSubTableDivId').style.display = "block";
		document.getElementById('childrensMenuchildNameId').checked=true;
		document.getElementById('childrensMenuDateofBirthId').checked=true;

		document.getElementById('directDebitMenuAddId').checked=true;
		document.getElementById('directDebitMenuViewId').checked=true;
		document.getElementById('directDebitMenuUpdateId').checked=true;
		document.getElementById('directDebitMenuDeleteId').checked=true;
		document.getElementById('directDebitMenuSelectAllId').checked=true;
		document.getElementById('directDebitMenuSubTableDivId').style.display = "block";
		document.getElementById('directDebitMenuRoutingNoId').checked=true;
		document.getElementById('directDebitMenuAccountId').checked=true;
		document.getElementById('directDebitMenuAmountId').checked=true;
		document.getElementById('directDebitMenuAccontTypeId').checked=true;
		document.getElementById('directDebitMenuTransactionTypetId').checked=true;
		document.getElementById('directDebitMenuPrefAccountId').checked=true;

		document.getElementById('empExperienceMenuAddId').checked=true;
		document.getElementById('empExperienceMenuViewId').checked=true;
		document.getElementById('empExperienceMenuUpdateId').checked=true;
		document.getElementById('empExperienceMenuDeleteId').checked=true;
		document.getElementById('empExperienceMenuSelectAllId').checked=true;
		document.getElementById('workExperienceMenuSubTableDivId').style.display = "block";
		document.getElementById('workExperienceMenuEmployerNameId').checked=true;
		document.getElementById('workExperienceMenuJobTitleId').checked=true;
		document.getElementById('workExperienceMenuFromDateId').checked=true;
		document.getElementById('workExperienceMenuToDateId').checked=true;
		document.getElementById('workExperienceMenuCommentsId').checked=true;

		document.getElementById('reportToMenuAddId').checked=true;
		document.getElementById('reportToMenuViewId').checked=true;
		document.getElementById('reportToMenuUpdateId').checked=true;
		document.getElementById('reportToMenuDeleteId').checked=true;
		document.getElementById('reportToMenuSelectAllId').checked=true;
		document.getElementById('employeeReportToMenuSubTableDivId').style.display = "block";
		document.getElementById('empReporttoMenuSupervisorId').checked=true;
		document.getElementById('empReporttoMenuReportingModeId').checked=true;

		document.getElementById('locationHistoryMenuAddId').checked=true;
		document.getElementById('locationHistoryMenuViewId').checked=true;
		document.getElementById('locationHistoryMenuUpdateId').checked=true;
		document.getElementById('locationHistoryMenuDeleteId').checked=true;
		document.getElementById('locationHistoryMenuSelectAllId').checked=true;
		document.getElementById('locationHistoryMenuSubTableDivId').style.display = "block";
		document.getElementById('empLocationHistoryMenuNameId').checked=true;
		document.getElementById('empLocationHistoryMenuClientNameId').checked=true;
		document.getElementById('empLocationHistoryMenuStartDateId').checked=true;
		document.getElementById('empLocationHistoryMenuEndDateId').checked=true;

		document.getElementById('employeeScheduleMenuAddId').checked=true;
		document.getElementById('employeeScheduleMenuViewId').checked=true;
		document.getElementById('employeeScheduleMenuUpdateId').checked=true;
		document.getElementById('employeeScheduleMenuDeleteId').checked=true;
		document.getElementById('employeeScheduleMenuSelectAllId').checked=true;

		document.getElementById('roleMenuAddId').checked=true;
		document.getElementById('roleMenuViewId').checked=true;
		document.getElementById('roleMenuUpdateId').checked=true;
		document.getElementById('roleMenuDeleteId').checked=true;
		document.getElementById('roleMenuSelectAllId').checked=true;

		document.getElementById('userMenuAddId').checked=true;
		document.getElementById('userMenuViewId').checked=true;
		document.getElementById('userMenuUpdateId').checked=true;
		document.getElementById('userMenuDeleteId').checked=true;
		document.getElementById('userMenuSelectAllId').checked=true;

		document.getElementById('empPassportMenuAddId').checked=true;
		document.getElementById('empPassportMenuViewId').checked=true;
		document.getElementById('empPassportMenuUpdateId').checked=true;
		document.getElementById('empPassportMenuDeleteId').checked=true;
		document.getElementById('empPassportMenuSelectAllId').checked=true;
		document.getElementById('passportMenuSubTableDivId').style.display = "block";
		document.getElementById('employeeMenuPassportNoId').checked=true;
		document.getElementById('employeePassportMenuPassportIssueId').checked=true;
		document.getElementById('employeePassportMenuPassportExpiredateId').checked=true;
		document.getElementById('employeePassportMenuPassportTypeFlagId').checked=true;
		document.getElementById('employeePassportMenu_19StatusId').checked=true;
		document.getElementById('employeePassportMenu_19ReviewdateId').checked=true;
		document.getElementById('employeePassportMenuCitizenshipId').checked=true;
		document.getElementById('employeepassportMenuCommentsId').checked=true;

		document.getElementById('benefitMenuAddId').checked=true;
		document.getElementById('benefitMenuViewId').checked=true;
		document.getElementById('benefitMenuUpdateId').checked=true;
		document.getElementById('benefitMenuDeleteId').checked=true;
		document.getElementById('benefitMenuSelectAllId').checked=true;
		document.getElementById('benefitsMenuSubTableDivId').style.display = "block";
		document.getElementById('benefitsMenuYearId').checked=true;
		document.getElementById('benefitsMenuNameId').checked=true;
		document.getElementById('benefitsMenuAttachFileId').checked=true;

		document.getElementById('timesheetApproverMenuAddId').checked=true;
		document.getElementById('timesheetApproverMenuViewId').checked=true;
		document.getElementById('timesheetApproverMenuUpdateId').checked=true;
		document.getElementById('timesheetApproverMenuDeleteId').checked=true;
		document.getElementById('timesheetApproverMenuSelectAllId').checked=true;
		
		document.getElementById('targetandGoalMenuAddId').checked=true;
		document.getElementById('targetandGoalMenuViewId').checked=true;
		document.getElementById('targetandGoalMenuUpdateId').checked=true;
		document.getElementById('targetandGoalMenuDeleteId').checked=true;
		document.getElementById('targetandGoalMenuSelectAllId').checked=true;
		
		document.getElementById('targetTypeMenuAddedId').checked=true;
		document.getElementById('targetTypeMenuViewId').checked=true;
		document.getElementById('targetTypeMenuUpdateId').checked=true;
		document.getElementById('targetTypeMenuDeleteId').checked=true;
		document.getElementById('targetTypeMenuSelectAllTypeId').checked=true;

		document.getElementById('expapproverMenuAddId').checked=true;
		document.getElementById('expapproverMenuViewId').checked=true;
		document.getElementById('expapproverMenuUpdateId').checked=true;
		document.getElementById('expapproverMenuDeleteId').checked=true;
		document.getElementById('expapproverMenuSelectAllId').checked=true;

		document.getElementById('expenseAccountantMenuAddId').checked=true;
		document.getElementById('expenseAccountantMenuViewId').checked=true;
		document.getElementById('expenseAccountantMenuUpdateId').checked=true;
		document.getElementById('expenseAccountantMenuDeleteId').checked=true;
		document.getElementById('expenseAccountantMenuSelectAllId').checked=true;
		
		document.getElementById('performancemenucategoryAddId').checked=true;
		document.getElementById('performancemenucategoryViewId').checked=true;
		document.getElementById('performancemenucategoryUpdateId').checked=true;
		document.getElementById('performancemenucategoryDeleteId').checked=true;
		document.getElementById('performancecategoryMenuSelectallId').checked=true;
		
		document.getElementById('performancemenusubcategoryAddId').checked=true;
		document.getElementById('performancemenusubcategoryViewId').checked=true;
		document.getElementById('performancemenusubcategoryUpdateId').checked=true;
		document.getElementById('performancemenusubcategoryDeleteId').checked=true;
		document.getElementById('performanceSubcategoryMenuSelectallId').checked=true;
		
		document.getElementById('performanceMenukpiQuestionId').checked=true;
		document.getElementById('performanceMenukpiGroupId').checked=true;
		document.getElementById('performanceMenuKpiAssignedkpiId').checked=true;
		document.getElementById('kpiMenuSelectallNameId').checked=true;
		
		
	}else{
		
		document.getElementById('essMenuAddId').checked=false;
		document.getElementById('expenseMenuAddId').checked=false;
		document.getElementById('leaveMenuAddId').checked=false;
		document.getElementById('timeSheetMenuAddId').checked=false;
		document.getElementById('orgChartMenuAddId').checked=false;
		document.getElementById('reportsMenuAddId').checked=false;
		document.getElementById('messagingMenuAddId').checked=false;
		document.getElementById('employeeSpaceMenuAddId').checked=false;
		document.getElementById('employeetargetMenuAddedId').checked=false;
		document.getElementById('selectAllModulesCheckBoxId').checked=false;
		
		document.getElementById('countryMenuAddId').checked=false;
		document.getElementById('countryMenuViewId').checked=false;
		document.getElementById('countryMenuUpdateId').checked=false;
		document.getElementById('countryMenuDeleteId').checked=false;
		document.getElementById('countryMenuSelectAllId').checked=false;

		document.getElementById('nationalityMenuAddId').checked=false;
		document.getElementById('nationalityMenuViewId').checked=false;
		document.getElementById('nationalityMenuUpdateId').checked=false;
		document.getElementById('nationalityMenuDeleteId').checked=false;
		document.getElementById('nationalityMenuSelectAllId').checked=false;

		document.getElementById('ethnicRaceMenuAddId').checked=false;
		document.getElementById('ethnicRaceMenuViewId').checked=false;
		document.getElementById('ethnicRaceMenuUpdateId').checked=false;
		document.getElementById('ethnicRaceMenuDeleteId').checked=false;
		document.getElementById('ethnicRaceMenuSelectAllId').checked=false;

		document.getElementById('projectMenuAddId').checked=false;
		document.getElementById('projectMenuViewId').checked=false;
		document.getElementById('projectMenuUpdateId').checked=false;
		document.getElementById('projectMenuDeleteId').checked=false;
		document.getElementById('projectMenuSelectAllId').checked=false;

		document.getElementById('customerMenuAddId').checked=false;
		document.getElementById('customerMenuViewId').checked=false;
		document.getElementById('customerMenuUpdateId').checked=false;
		document.getElementById('customerMenuDeleteId').checked=false;
		document.getElementById('customerMenuSelectAllId').checked=false;

		document.getElementById('holidayMenuAddId').checked=false;
		document.getElementById('holidayMenuViewId').checked=false;
		document.getElementById('holidayMenuUpdateId').checked=false;
		document.getElementById('holidayMenuDeleteId').checked=false;
		document.getElementById('holidayMenuSelectAllId').checked=false;
		
		document.getElementById('salaryGradeMenuAddId').checked=false;
		document.getElementById('salaryGradeMenuViewId').checked=false;
		document.getElementById('salaryGradeMenuUpdateId').checked=false;
		document.getElementById('salaryGradeMenuDeleteId').checked=false;
		document.getElementById('salaryGradeMenuSelectAllId').checked=false;

		document.getElementById('jobTitleMenuAddId').checked=false;
		document.getElementById('jobTitleMenuViewId').checked=false;
		document.getElementById('jobTitleMenuUpdateId').checked=false;
		document.getElementById('jobTitleMenuDeleteId').checked=false;
		document.getElementById('jobTitleMenuSelectAllId').checked=false;

		document.getElementById('clientMenuAddId').checked=false;
		document.getElementById('clientMenuViewId').checked=false;
		document.getElementById('clientMenuUpdateId').checked=false;
		document.getElementById('clientMenuDeleteId').checked=false;
		document.getElementById('clientMenuSelectAllId').checked=false;

		document.getElementById('locationMenuAddId').checked=false;
		document.getElementById('locationMenuViewId').checked=false;
		document.getElementById('locationMenuUpdateId').checked=false;
		document.getElementById('locationMenuDeleteId').checked=false;
		document.getElementById('locationMenuSelectAllId').checked=false;

		document.getElementById('expenseTypeMenuAddId').checked=false;
		document.getElementById('expenseTypeMenuViewId').checked=false;
		document.getElementById('expenseTypeMenuUpdateId').checked=false;
		document.getElementById('expenseTypeMenuDeleteId').checked=false;
		document.getElementById('expenseTypeMenuSelectAllId').checked=false;

		document.getElementById('leaveTypeMenuAddId').checked=false;
		document.getElementById('leaveTypeMenuViewId').checked=false;
		document.getElementById('leaveTypeMenuUpdateId').checked=false;
		document.getElementById('leaveTypeMenuDeleteId').checked=false;
		document.getElementById('leaveTypeMenuSelectAllId').checked=false;
		document.getElementById('leaveQuotaMenuSubTableDivId').style.display = "none";
		document.getElementById('leaveQuotaMenuLeaveTypeId').checked=false;
		document.getElementById('leaveQuotaMenuYearId').checked=false;
		document.getElementById('leaveQuotaMenupreLeaveCarryId').checked=false;
		document.getElementById('leaveQuotaMenuLeaveCarryForwardId').checked=false;
		document.getElementById('leaveQuotaMenuDaysAllotedId').checked=false;
		document.getElementById('leaveQuotaMenuLeaveTakenId').checked=false;

		document.getElementById('leaveQuotaMenuAddId').checked=false;
		document.getElementById('leaveQuotaMenuViewId').checked=false;
		document.getElementById('leaveQuotaMenuUpdateId').checked=false;
		document.getElementById('leaveQuotaMenuDeleteId').checked=false;
		document.getElementById('leaveQuotaMenuSelectAllId').checked=false;

		document.getElementById('leaveapproverMenuAddId').checked=false;
		document.getElementById('leaveapproverMenuViewId').checked=false;
		document.getElementById('leaveapproverMenuUpdateId').checked=false;
		document.getElementById('leaveapproverMenuDeleteId').checked=false;
		document.getElementById('leaveapproverMenuSelectAllId').checked=false;

		document.getElementById('regionMenuAddId').checked=false;
		document.getElementById('regionMenuViewId').checked=false;
		document.getElementById('regionMenuUpdateId').checked=false;
		document.getElementById('regionMenuDeleteId').checked=false;
		document.getElementById('regionMenuSelectAllId').checked=false;

		document.getElementById('employeeMenuAddId').checked=false;
		document.getElementById('employeeMenuViewId').checked=false;
		document.getElementById('employeeMenuUpdateId').checked=false;
		document.getElementById('employeeMenuDeleteId').checked=false;
		document.getElementById('employeeMenuSelectAllId').checked=false;

		document.getElementById('departmentMenuAddId').checked=false;
		document.getElementById('departmentMenuViewId').checked=false;
		document.getElementById('departmentMenuUpdateId').checked=false;
		document.getElementById('departmentMenuDeleteId').checked=false;
		document.getElementById('departmentMenuSelectAllId').checked=false;

		document.getElementById('teamMenuAddId').checked=false;
		document.getElementById('teamMenuViewId').checked=false;
		document.getElementById('teamMenuUpdateId').checked=false;
		document.getElementById('teamMenuDeleteId').checked=false;
		document.getElementById('teamMenuSelectAllId').checked=false;

		document.getElementById('currencyMenuAddId').checked=false;
		document.getElementById('currencyMenuViewId').checked=false;
		document.getElementById('currencyMenuUpdateId').checked=false;
		document.getElementById('currencyMenuDeleteId').checked=false;
		document.getElementById('currencyMenuSelectAllId').checked=false;

		document.getElementById('taxAndDeductionMenuAddId').checked=false;
		document.getElementById('taxAndDeductionMenuViewId').checked=false;
		document.getElementById('taxAndDeductionMenuUpdateId').checked=false;
		document.getElementById('taxAndDeductionMenuDeleteId').checked=false;
		document.getElementById('taxAndDeductionMenuSelectAllId').checked=false;

		document.getElementById('paystubMenuAddId').checked=false;
		document.getElementById('paystubMenuViewId').checked=false;
		document.getElementById('paystubMenuUpdateId').checked=false;
		document.getElementById('paystubMenuDeleteId').checked=false;
		document.getElementById('paystubMenuSelectAllId').checked=false;
		document.getElementById('payStubMenuSubTableDivId').style.display = "none";
		document.getElementById('payStubMenuGrossSalaryId').checked=false;
		document.getElementById('payStubMenuDecDateId').checked=false;
		document.getElementById('payStubMenuNetSalaryId').checked=false;
		document.getElementById('payStubMenuDeductionId').checked=false;
		document.getElementById('payStubMenuDeductionTypeId').checked=false;
		document.getElementById('payStubMenuDeductionModeId').checked=false;
		document.getElementById('payStubMenudEductionAmountId').checked=false;
		document.getElementById('payStubMenuEffectiveDateId').checked=false;
		

		document.getElementById('empStatusMenuAddId').checked=false;
		document.getElementById('empStatusMenuViewId').checked=false;
		document.getElementById('empStatusMenuUpdateId').checked=false;
		document.getElementById('empStatusMenuDeleteId').checked=false;
		document.getElementById('empStatusMenuSelectAllId').checked=false;

		document.getElementById('licenseMenuAddId').checked=false;
		document.getElementById('licenseMenuViewId').checked=false;
		document.getElementById('licenseMenuUpdateId').checked=false;
		document.getElementById('licenseMenuDeleteId').checked=false;
		document.getElementById('licenseMenuSelectAllId').checked=false;
		document.getElementById('licenseMenuSubTableDivId').style.display = "none";
		document.getElementById('licenseMenuNumberId').checked=false;
		document.getElementById('licenseMenuDateId').checked=false;
		document.getElementById('licenseMenuRenewalDateId').checked=false;
		document.getElementById('licenseMenuDescriptionId').checked=false;

		document.getElementById('educationMenuAddId').checked=false;
		document.getElementById('educationMenuViewId').checked=false;
		document.getElementById('educationMenuUpdateId').checked=false;
		document.getElementById('educationMenuDeleteId').checked=false;
		document.getElementById('educationMenuSelectAllId').checked=false;
		document.getElementById('educationMenuSubTableDivId').style.display = "none";
		document.getElementById('educationMenuMajorId').checked=false;
		document.getElementById('educationMenuYearId').checked=false;
		document.getElementById('educationMenuGradeId').checked=false;
		document.getElementById('educationMenuStartDateId').checked=false;
		document.getElementById('educationMenuEnddateId').checked=false;
		document.getElementById('educationMenuUnivId').checked=false;
		document.getElementById('educationMenuDegreeId').checked=false;

		document.getElementById('projActivityMenuAddId').checked=false;
		document.getElementById('projActivityMenuViewId').checked=false;
		document.getElementById('projActivityMenuUpdateId').checked=false;
		document.getElementById('projActivityMenuDeleteId').checked=false;
		document.getElementById('projActivityMenuSelectAllId').checked=false;

		document.getElementById('childrenMenuAddId').checked=false;
		document.getElementById('childrenMenuViewId').checked=false;
		document.getElementById('childrenMenuUpdateId').checked=false;
		document.getElementById('childrenMenuDeleteId').checked=false;
		document.getElementById('childrenMenuSelectAllId').checked=false;
		document.getElementById('childrenMenuSubTableDivId').style.display = "none";
		document.getElementById('childrensMenuchildNameId').checked=false;
		document.getElementById('childrensMenuDateofBirthId').checked=false;

		document.getElementById('directDebitMenuAddId').checked=false;
		document.getElementById('directDebitMenuViewId').checked=false;
		document.getElementById('directDebitMenuUpdateId').checked=false;
		document.getElementById('directDebitMenuDeleteId').checked=false;
		document.getElementById('directDebitMenuSelectAllId').checked=false;
		document.getElementById('directDebitMenuSubTableDivId').style.display = "none";
		document.getElementById('directDebitMenuRoutingNoId').checked=false;
		document.getElementById('directDebitMenuAccountId').checked=false;
		document.getElementById('directDebitMenuAmountId').checked=false;
		document.getElementById('directDebitMenuAccontTypeId').checked=false;
		document.getElementById('directDebitMenuTransactionTypetId').checked=false;
		document.getElementById('directDebitMenuPrefAccountId').checked=false;

		document.getElementById('empExperienceMenuAddId').checked=false;
		document.getElementById('empExperienceMenuViewId').checked=false;
		document.getElementById('empExperienceMenuUpdateId').checked=false;
		document.getElementById('empExperienceMenuDeleteId').checked=false;
		document.getElementById('empExperienceMenuSelectAllId').checked=false;
		document.getElementById('workExperienceMenuSubTableDivId').style.display = "none";
		document.getElementById('workExperienceMenuEmployerNameId').checked=false;
		document.getElementById('workExperienceMenuJobTitleId').checked=false;
		document.getElementById('workExperienceMenuFromDateId').checked=false;
		document.getElementById('workExperienceMenuToDateId').checked=false;
		document.getElementById('workExperienceMenuCommentsId').checked=false;

		document.getElementById('reportToMenuAddId').checked=false;
		document.getElementById('reportToMenuViewId').checked=false;
		document.getElementById('reportToMenuUpdateId').checked=false;
		document.getElementById('reportToMenuDeleteId').checked=false;
		document.getElementById('reportToMenuSelectAllId').checked=false;
		document.getElementById('employeeReportToMenuSubTableDivId').style.display = "none";
		document.getElementById('empReporttoMenuSupervisorId').checked=false;
		document.getElementById('empReporttoMenuReportingModeId').checked=false;

		document.getElementById('locationHistoryMenuAddId').checked=false;
		document.getElementById('locationHistoryMenuViewId').checked=false;
		document.getElementById('locationHistoryMenuUpdateId').checked=false;
		document.getElementById('locationHistoryMenuDeleteId').checked=false;
		document.getElementById('locationHistoryMenuSelectAllId').checked=false;
		document.getElementById('locationHistoryMenuSubTableDivId').style.display = "none";
		document.getElementById('empLocationHistoryMenuNameId').checked=false;
		document.getElementById('empLocationHistoryMenuClientNameId').checked=false;
		document.getElementById('empLocationHistoryMenuStartDateId').checked=false;
		document.getElementById('empLocationHistoryMenuEndDateId').checked=false;

		document.getElementById('employeeScheduleMenuAddId').checked=false;
		document.getElementById('employeeScheduleMenuViewId').checked=false;
		document.getElementById('employeeScheduleMenuUpdateId').checked=false;
		document.getElementById('employeeScheduleMenuDeleteId').checked=false;
		document.getElementById('employeeScheduleMenuSelectAllId').checked=false;

		document.getElementById('roleMenuAddId').checked=false;
		document.getElementById('roleMenuViewId').checked=false;
		document.getElementById('roleMenuUpdateId').checked=false;
		document.getElementById('roleMenuDeleteId').checked=false;
		document.getElementById('roleMenuSelectAllId').checked=false;

		document.getElementById('userMenuAddId').checked=false;
		document.getElementById('userMenuViewId').checked=false;
		document.getElementById('userMenuUpdateId').checked=false;
		document.getElementById('userMenuDeleteId').checked=false;
		document.getElementById('userMenuSelectAllId').checked=false;

		document.getElementById('empPassportMenuAddId').checked=false;
		document.getElementById('empPassportMenuViewId').checked=false;
		document.getElementById('empPassportMenuUpdateId').checked=false;
		document.getElementById('empPassportMenuDeleteId').checked=false;
		document.getElementById('empPassportMenuSelectAllId').checked=false;
		document.getElementById('passportMenuSubTableDivId').style.display = "none";
		document.getElementById('employeeMenuPassportNoId').checked=false;
		document.getElementById('employeePassportMenuPassportIssueId').checked=false;
		document.getElementById('employeePassportMenuPassportExpiredateId').checked=false;
		document.getElementById('employeePassportMenuPassportTypeFlagId').checked=false;
		document.getElementById('employeePassportMenu_19StatusId').checked=false;
		document.getElementById('employeePassportMenu_19ReviewdateId').checked=false;
		document.getElementById('employeePassportMenuCitizenshipId').checked=false;
		document.getElementById('employeepassportMenuCommentsId').checked=false;

		document.getElementById('benefitMenuAddId').checked=false;
		document.getElementById('benefitMenuViewId').checked=false;
		document.getElementById('benefitMenuUpdateId').checked=false;
		document.getElementById('benefitMenuDeleteId').checked=false;
		document.getElementById('benefitMenuSelectAllId').checked=false;
		document.getElementById('benefitsMenuSubTableDivId').style.display = "none";
		document.getElementById('benefitsMenuYearId').checked=false;
		document.getElementById('benefitsMenuNameId').checked=false;
		document.getElementById('benefitsMenuAttachFileId').checked=false;

		document.getElementById('timesheetApproverMenuAddId').checked=false;
		document.getElementById('timesheetApproverMenuViewId').checked=false;
		document.getElementById('timesheetApproverMenuUpdateId').checked=false;
		document.getElementById('timesheetApproverMenuDeleteId').checked=false;
		document.getElementById('timesheetApproverMenuSelectAllId').checked=false;
		
		document.getElementById('targetandGoalMenuAddId').checked=false;
		document.getElementById('targetandGoalMenuViewId').checked=false;
		document.getElementById('targetandGoalMenuUpdateId').checked=false;
		document.getElementById('targetandGoalMenuDeleteId').checked=false;
		document.getElementById('targetandGoalMenuSelectAllId').checked=false;
		
		document.getElementById('targetTypeMenuAddedId').checked=false;
		document.getElementById('targetTypeMenuViewId').checked=false;
		document.getElementById('targetTypeMenuUpdateId').checked=false;
		document.getElementById('targetTypeMenuDeleteId').checked=false;
		document.getElementById('targetTypeMenuSelectAllTypeId').checked=false;

		document.getElementById('expapproverMenuAddId').checked=false;
		document.getElementById('expapproverMenuViewId').checked=false;
		document.getElementById('expapproverMenuUpdateId').checked=false;
		document.getElementById('expapproverMenuDeleteId').checked=false;
		document.getElementById('expapproverMenuSelectAllId').checked=false;

		document.getElementById('expenseAccountantMenuAddId').checked=false;
		document.getElementById('expenseAccountantMenuViewId').checked=false;
		document.getElementById('expenseAccountantMenuUpdateId').checked=false;
		document.getElementById('expenseAccountantMenuDeleteId').checked=false;
		document.getElementById('expenseAccountantMenuSelectAllId').checked=false;
		
		document.getElementById('performancemenucategoryAddId').checked=false;
		document.getElementById('performancemenucategoryViewId').checked=false;
		document.getElementById('performancemenucategoryUpdateId').checked=false;
		document.getElementById('performancemenucategoryDeleteId').checked=false;
		document.getElementById('performancecategoryMenuSelectallId').checked=false;
		
		document.getElementById('expenseAccountantMenuAddId').checked=false;
		document.getElementById('expenseAccountantMenuViewId').checked=false;
		document.getElementById('expenseAccountantMenuUpdateId').checked=false;
		document.getElementById('expenseAccountantMenuDeleteId').checked=false;
		document.getElementById('performanceSubcategoryMenuSelectallId').checked=false;
		
		document.getElementById('performanceMenukpiQuestionId').checked=false;
		document.getElementById('performanceMenukpiGroupId').checked=false;
		document.getElementById('performanceMenuKpiAssignedkpiId').checked=false;
		document.getElementById('kpiMenuSelectallNameId').checked=false;
		
		
		
	}
}

//Check or Un-check All Modules CheckBox
function selectAllModulesMenuCheckBox(){ 
	if(document.getElementById('selectAllModulesCheckBoxId').checked==true){

		document.getElementById('essMenuAddId').checked=true;
		document.getElementById('expenseMenuAddId').checked=true;
		document.getElementById('leaveMenuAddId').checked=true;
		document.getElementById('timeSheetMenuAddId').checked=true;
		document.getElementById('orgChartMenuAddId').checked=true;
		document.getElementById('reportsMenuAddId').checked=true;
		document.getElementById('messagingMenuAddId').checked=true;
		document.getElementById('employeeSpaceMenuAddId').checked=true;
		document.getElementById('employeetargetMenuAddedId').checked=true;
	}else{
		document.getElementById('essMenuAddId').checked=false;
		document.getElementById('expenseMenuAddId').checked=false;
		document.getElementById('leaveMenuAddId').checked=false;
		document.getElementById('timeSheetMenuAddId').checked=false;
		document.getElementById('orgChartMenuAddId').checked=false;
		document.getElementById('reportsMenuAddId').checked=false;
		document.getElementById('messagingMenuAddId').checked=false;
		document.getElementById('employeeSpaceMenuAddId').checked=false;
		document.getElementById('employeetargetMenuAddedId').checked=false;
	}
}

//Check or Un-check All Country CheckBox
function selectAllCoutryMenuCheckBox(){ 
	if(document.getElementById('countryMenuSelectAllId').checked==true){

		document.getElementById('countryMenuAddId').checked=true;
		document.getElementById('countryMenuViewId').checked=true;
		document.getElementById('countryMenuUpdateId').checked=true;
		document.getElementById('countryMenuDeleteId').checked=true;
	}else{
		document.getElementById('countryMenuAddId').checked=false;
		document.getElementById('countryMenuViewId').checked=false;
		document.getElementById('countryMenuUpdateId').checked=false;
		document.getElementById('countryMenuDeleteId').checked=false;
	}
}

//Check or Un-check All Nationality CheckBox
function selectAllNationalityMenuCheckBox(){ 
	if(document.getElementById('nationalityMenuSelectAllId').checked==true){

		document.getElementById('nationalityMenuAddId').checked=true;
		document.getElementById('nationalityMenuViewId').checked=true;
		document.getElementById('nationalityMenuUpdateId').checked=true;
		document.getElementById('nationalityMenuDeleteId').checked=true;
	}else{
		document.getElementById('nationalityMenuAddId').checked=false;
		document.getElementById('nationalityMenuViewId').checked=false;
		document.getElementById('nationalityMenuUpdateId').checked=false;
		document.getElementById('nationalityMenuDeleteId').checked=false;
	}
}

//Check or Un-check All EthnicRace CheckBox
function selectAllEthnicRaceMenuCheckBox(){ 
	if(document.getElementById('ethnicRaceMenuSelectAllId').checked==true){

		document.getElementById('ethnicRaceMenuAddId').checked=true;
		document.getElementById('ethnicRaceMenuViewId').checked=true;
		document.getElementById('ethnicRaceMenuUpdateId').checked=true;
		document.getElementById('ethnicRaceMenuDeleteId').checked=true;
	}else{
		document.getElementById('ethnicRaceMenuAddId').checked=false;
		document.getElementById('ethnicRaceMenuViewId').checked=false;
		document.getElementById('ethnicRaceMenuUpdateId').checked=false;
		document.getElementById('ethnicRaceMenuDeleteId').checked=false;
	}
}

//Check or Un-check All Project CheckBox
function selectAllProjectMenuCheckBox(){ 
	if(document.getElementById('projectMenuSelectAllId').checked==true){

		document.getElementById('projectMenuAddId').checked=true;
		document.getElementById('projectMenuViewId').checked=true;
		document.getElementById('projectMenuUpdateId').checked=true;
		document.getElementById('projectMenuDeleteId').checked=true;
	}else{
		document.getElementById('projectMenuAddId').checked=false;
		document.getElementById('projectMenuViewId').checked=false;
		document.getElementById('projectMenuUpdateId').checked=false;
		document.getElementById('projectMenuDeleteId').checked=false;
	}
}

//Check or Un-check All Customer CheckBox
function selectAllCustomerMenuCheckBox(){ 
	if(document.getElementById('customerMenuSelectAllId').checked==true){

		document.getElementById('customerMenuAddId').checked=true;
		document.getElementById('customerMenuViewId').checked=true;
		document.getElementById('customerMenuUpdateId').checked=true;
		document.getElementById('customerMenuDeleteId').checked=true;
	}else{
		document.getElementById('customerMenuAddId').checked=false;
		document.getElementById('customerMenuViewId').checked=false;
		document.getElementById('customerMenuUpdateId').checked=false;
		document.getElementById('customerMenuDeleteId').checked=false;
	}
}

//Check or Un-check All Holiday CheckBox
function selectAllHolidayMenuCheckBox(){ 
	if(document.getElementById('holidayMenuSelectAllId').checked==true){

		document.getElementById('holidayMenuAddId').checked=true;
		document.getElementById('holidayMenuViewId').checked=true;
		document.getElementById('holidayMenuUpdateId').checked=true;
		document.getElementById('holidayMenuDeleteId').checked=true;
	}else{
		document.getElementById('holidayMenuAddId').checked=false;
		document.getElementById('holidayMenuViewId').checked=false;
		document.getElementById('holidayMenuUpdateId').checked=false;
		document.getElementById('holidayMenuDeleteId').checked=false;
	}
}

//Check or Un-check All SalaryGrade CheckBox
function selectAllSalaryGradeMenuCheckBox(){ 
	if(document.getElementById('salaryGradeMenuSelectAllId').checked==true){

		document.getElementById('salaryGradeMenuAddId').checked=true;
		document.getElementById('salaryGradeMenuViewId').checked=true;
		document.getElementById('salaryGradeMenuUpdateId').checked=true;
		document.getElementById('salaryGradeMenuDeleteId').checked=true;
	}else{
		document.getElementById('salaryGradeMenuAddId').checked=false;
		document.getElementById('salaryGradeMenuViewId').checked=false;
		document.getElementById('salaryGradeMenuUpdateId').checked=false;
		document.getElementById('salaryGradeMenuDeleteId').checked=false;
	}
}

//Check or Un-check All JobTitle CheckBox
function selectAllJobTitleMenuCheckBox(){ 
	if(document.getElementById('jobTitleMenuSelectAllId').checked==true){

		document.getElementById('jobTitleMenuAddId').checked=true;
		document.getElementById('jobTitleMenuViewId').checked=true;
		document.getElementById('jobTitleMenuUpdateId').checked=true;
		document.getElementById('jobTitleMenuDeleteId').checked=true;
	}else{
		document.getElementById('jobTitleMenuAddId').checked=false;
		document.getElementById('jobTitleMenuViewId').checked=false;
		document.getElementById('jobTitleMenuUpdateId').checked=false;
		document.getElementById('jobTitleMenuDeleteId').checked=false;
	}
}

//Check or Un-check All Client CheckBox
function selectAllClientMenuCheckBox(){ 
	if(document.getElementById('clientMenuSelectAllId').checked==true){

		document.getElementById('clientMenuAddId').checked=true;
		document.getElementById('clientMenuViewId').checked=true;
		document.getElementById('clientMenuUpdateId').checked=true;
		document.getElementById('clientMenuDeleteId').checked=true;
	}else{
		document.getElementById('clientMenuAddId').checked=false;
		document.getElementById('clientMenuViewId').checked=false;
		document.getElementById('clientMenuUpdateId').checked=false;
		document.getElementById('clientMenuDeleteId').checked=false;
	}
}

//Check or Un-check All Location CheckBox
function selectAllLocationMenuCheckBox(){ 
	if(document.getElementById('locationMenuSelectAllId').checked==true){

		document.getElementById('locationMenuAddId').checked=true;
		document.getElementById('locationMenuViewId').checked=true;
		document.getElementById('locationMenuUpdateId').checked=true;
		document.getElementById('locationMenuDeleteId').checked=true;
	}else{
		document.getElementById('locationMenuAddId').checked=false;
		document.getElementById('locationMenuViewId').checked=false;
		document.getElementById('locationMenuUpdateId').checked=false;
		document.getElementById('locationMenuDeleteId').checked=false;
	}
}

//Check or Un-check All ExpenseType CheckBox
function selectAllExpenseTypeMenuCheckBox(){ 
	if(document.getElementById('expenseTypeMenuSelectAllId').checked==true){

		document.getElementById('expenseTypeMenuAddId').checked=true;
		document.getElementById('expenseTypeMenuViewId').checked=true;
		document.getElementById('expenseTypeMenuUpdateId').checked=true;
		document.getElementById('expenseTypeMenuDeleteId').checked=true;
	}else{
		document.getElementById('expenseTypeMenuAddId').checked=false;
		document.getElementById('expenseTypeMenuViewId').checked=false;
		document.getElementById('expenseTypeMenuUpdateId').checked=false;
		document.getElementById('expenseTypeMenuDeleteId').checked=false;
	}
}

//Check or Un-check All LeaveType CheckBox
function selectAllLeaveTypeMenuCheckBox(){ 
	if(document.getElementById('leaveTypeMenuSelectAllId').checked==true){

		document.getElementById('leaveTypeMenuAddId').checked=true;
		document.getElementById('leaveTypeMenuViewId').checked=true;
		document.getElementById('leaveTypeMenuUpdateId').checked=true;
		document.getElementById('leaveTypeMenuDeleteId').checked=true;
	}else{
		document.getElementById('leaveTypeMenuAddId').checked=false;
		document.getElementById('leaveTypeMenuViewId').checked=false;
		document.getElementById('leaveTypeMenuUpdateId').checked=false;
		document.getElementById('leaveTypeMenuDeleteId').checked=false;
	}
}

//Check or Un-check All LeaveQuota CheckBox
function selectAllLeaveQuotaMenuCheckBox(){ 
	if(document.getElementById('leaveQuotaMenuSelectAllId').checked==true){

		document.getElementById('leaveQuotaMenuAddId').checked=true;
		document.getElementById('leaveQuotaMenuViewId').checked=true;
		document.getElementById('leaveQuotaMenuUpdateId').checked=true;
		document.getElementById('leaveQuotaMenuDeleteId').checked=true;
		document.getElementById('leaveQuotaMenuSubTableDivId').style.display = "block";
		
		document.getElementById('leaveQuotaMenuLeaveTypeId').checked=true;
		document.getElementById('leaveQuotaMenuYearId').checked=true;
		document.getElementById('leaveQuotaMenupreLeaveCarryId').checked=true;
		document.getElementById('leaveQuotaMenuLeaveCarryForwardId').checked=true;
		document.getElementById('leaveQuotaMenuDaysAllotedId').checked=true;
		document.getElementById('leaveQuotaMenuLeaveTakenId').checked=true;
		
	
	}else{
		document.getElementById('leaveQuotaMenuAddId').checked=false;
		document.getElementById('leaveQuotaMenuViewId').checked=false;
		document.getElementById('leaveQuotaMenuUpdateId').checked=false;
		document.getElementById('leaveQuotaMenuDeleteId').checked=false;
		document.getElementById('leaveQuotaMenuSubTableDivId').style.display = "none";
		
		document.getElementById('leaveQuotaMenuLeaveTypeId').checked=false;
		document.getElementById('leaveQuotaMenuYearId').checked=false;
		document.getElementById('leaveQuotaMenupreLeaveCarryId').checked=false;
		document.getElementById('leaveQuotaMenuLeaveCarryForwardId').checked=false;
		document.getElementById('leaveQuotaMenuDaysAllotedId').checked=false;
		document.getElementById('leaveQuotaMenuLeaveTakenId').checked=false;
		
		
	}
}

//Check or Un-check All Leaveapprover CheckBox
function selectAllLeaveapproverMenuCheckBox(){ 
	if(document.getElementById('leaveapproverMenuSelectAllId').checked==true){

		document.getElementById('leaveapproverMenuAddId').checked=true;
		document.getElementById('leaveapproverMenuViewId').checked=true;
		document.getElementById('leaveapproverMenuUpdateId').checked=true;
		document.getElementById('leaveapproverMenuDeleteId').checked=true;
		
	}else{
		document.getElementById('leaveapproverMenuAddId').checked=false;
		document.getElementById('leaveapproverMenuViewId').checked=false;
		document.getElementById('leaveapproverMenuUpdateId').checked=false;
		document.getElementById('leaveapproverMenuDeleteId').checked=false;
	}
}

//Check or Un-check All Region CheckBox
function selectAllRegionMenuCheckBox(){ 
	if(document.getElementById('regionMenuSelectAllId').checked==true){

		document.getElementById('regionMenuAddId').checked=true;
		document.getElementById('regionMenuViewId').checked=true;
		document.getElementById('regionMenuUpdateId').checked=true;
		document.getElementById('regionMenuDeleteId').checked=true;
	}else{
		document.getElementById('regionMenuAddId').checked=false;
		document.getElementById('regionMenuViewId').checked=false;
		document.getElementById('regionMenuUpdateId').checked=false;
		document.getElementById('regionMenuDeleteId').checked=false;
	}
}

//Check or Un-check All employee CheckBox
function selectAllEmployeeMenuCheckBox(){ 
	if(document.getElementById('employeeMenuSelectAllId').checked==true){

		document.getElementById('employeeMenuAddId').checked=true;
		document.getElementById('employeeMenuViewId').checked=true;
		document.getElementById('employeeMenuUpdateId').checked=true;
		document.getElementById('employeeMenuDeleteId').checked=true;
	}else{
		document.getElementById('employeeMenuAddId').checked=false;
		document.getElementById('employeeMenuViewId').checked=false;
		document.getElementById('employeeMenuUpdateId').checked=false;
		document.getElementById('employeeMenuDeleteId').checked=false;
	}
}

//Check or Un-check All department CheckBox
function selectAllDepartmentMenuCheckBox(){ 
	if(document.getElementById('departmentMenuSelectAllId').checked==true){

		document.getElementById('departmentMenuAddId').checked=true;
		document.getElementById('departmentMenuViewId').checked=true;
		document.getElementById('departmentMenuUpdateId').checked=true;
		document.getElementById('departmentMenuDeleteId').checked=true;
	}else{
		document.getElementById('departmentMenuAddId').checked=false;
		document.getElementById('departmentMenuViewId').checked=false;
		document.getElementById('departmentMenuUpdateId').checked=false;
		document.getElementById('departmentMenuDeleteId').checked=false;
	}
}

//Check or Un-check All team CheckBox
function selectAllTeamMenuCheckBox(){ 
	if(document.getElementById('teamMenuSelectAllId').checked==true){

		document.getElementById('teamMenuAddId').checked=true;
		document.getElementById('teamMenuViewId').checked=true;
		document.getElementById('teamMenuUpdateId').checked=true;
		document.getElementById('teamMenuDeleteId').checked=true;
	}else{
		document.getElementById('teamMenuAddId').checked=false;
		document.getElementById('teamMenuViewId').checked=false;
		document.getElementById('teamMenuUpdateId').checked=false;
		document.getElementById('teamMenuDeleteId').checked=false;
	}
}

//Check or Un-check All currency CheckBox
function selectAllCurrencyMenuCheckBox(){ 
	if(document.getElementById('currencyMenuSelectAllId').checked==true){

		document.getElementById('currencyMenuAddId').checked=true;
		document.getElementById('currencyMenuViewId').checked=true;
		document.getElementById('currencyMenuUpdateId').checked=true;
		document.getElementById('currencyMenuDeleteId').checked=true;
	}else{
		document.getElementById('currencyMenuAddId').checked=false;
		document.getElementById('currencyMenuViewId').checked=false;
		document.getElementById('currencyMenuUpdateId').checked=false;
		document.getElementById('currencyMenuDeleteId').checked=false;
	}
}

//Check or Un-check All taxAndDeduction CheckBox
function selectAllTaxAndDeductionMenuCheckBox(){ 
	if(document.getElementById('taxAndDeductionMenuSelectAllId').checked==true){

		document.getElementById('taxAndDeductionMenuAddId').checked=true;
		document.getElementById('taxAndDeductionMenuViewId').checked=true;
		document.getElementById('taxAndDeductionMenuUpdateId').checked=true;
		document.getElementById('taxAndDeductionMenuDeleteId').checked=true;
	}else{
		document.getElementById('taxAndDeductionMenuAddId').checked=false;
		document.getElementById('taxAndDeductionMenuViewId').checked=false;
		document.getElementById('taxAndDeductionMenuUpdateId').checked=false;
		document.getElementById('taxAndDeductionMenuDeleteId').checked=false;
	}
}

//Check or Un-check All paystub CheckBox
function selectAllPaystubMenuCheckBox(){ 
	if(document.getElementById('paystubMenuSelectAllId').checked==true){

		document.getElementById('paystubMenuAddId').checked=true;
		document.getElementById('paystubMenuViewId').checked=true;
		document.getElementById('paystubMenuUpdateId').checked=true;
		document.getElementById('paystubMenuDeleteId').checked=true;
		document.getElementById('payStubMenuSubTableDivId').style.display = "block";
		document.getElementById('payStubMenuGrossSalaryId').checked=true;
		document.getElementById('payStubMenuDecDateId').checked=true;
		document.getElementById('payStubMenuNetSalaryId').checked=true;
		document.getElementById('payStubMenuDeductionId').checked=true;
		document.getElementById('payStubMenuDeductionTypeId').checked=true;
		document.getElementById('payStubMenuDeductionModeId').checked=true;
		document.getElementById('payStubMenudEductionAmountId').checked=true;
		document.getElementById('payStubMenuEffectiveDateId').checked=true;
	}else{
		document.getElementById('paystubMenuAddId').checked=false;
		document.getElementById('paystubMenuViewId').checked=false;
		document.getElementById('paystubMenuUpdateId').checked=false;
		document.getElementById('paystubMenuDeleteId').checked=false;
		document.getElementById('payStubMenuSubTableDivId').style.display = "none";
		document.getElementById('payStubMenuGrossSalaryId').checked=false;
		document.getElementById('payStubMenuDecDateId').checked=false;
		document.getElementById('payStubMenuNetSalaryId').checked=false;
		document.getElementById('payStubMenuDeductionId').checked=false;
		document.getElementById('payStubMenuDeductionTypeId').checked=false;
		document.getElementById('payStubMenuDeductionModeId').checked=false;
		document.getElementById('payStubMenudEductionAmountId').checked=false;
		document.getElementById('payStubMenuEffectiveDateId').checked=false;
	}
}

//Check or Un-check All empStatus CheckBox
function selectAllEmpStatusMenuCheckBox(){ 
	if(document.getElementById('empStatusMenuSelectAllId').checked==true){

		document.getElementById('empStatusMenuAddId').checked=true;
		document.getElementById('empStatusMenuViewId').checked=true;
		document.getElementById('empStatusMenuUpdateId').checked=true;
		document.getElementById('empStatusMenuDeleteId').checked=true;
	}else{
		document.getElementById('empStatusMenuAddId').checked=false;
		document.getElementById('empStatusMenuViewId').checked=false;
		document.getElementById('empStatusMenuUpdateId').checked=false;
		document.getElementById('empStatusMenuDeleteId').checked=false;
	}
}

//Check or Un-check All license CheckBox
function selectAllLicenseMenuCheckBox(){ 
	if(document.getElementById('licenseMenuSelectAllId').checked==true){

		document.getElementById('licenseMenuAddId').checked=true;
		document.getElementById('licenseMenuViewId').checked=true;
		document.getElementById('licenseMenuUpdateId').checked=true;
		document.getElementById('licenseMenuDeleteId').checked=true;
		document.getElementById('licenseMenuSubTableDivId').style.display = "block";
		document.getElementById('licenseMenuNumberId').checked=true;
		document.getElementById('licenseMenuDateId').checked=true;
		document.getElementById('licenseMenuRenewalDateId').checked=true;
		document.getElementById('licenseMenuDescriptionId').checked=true;
	}else{
		document.getElementById('licenseMenuAddId').checked=false;
		document.getElementById('licenseMenuViewId').checked=false;
		document.getElementById('licenseMenuUpdateId').checked=false;
		document.getElementById('licenseMenuDeleteId').checked=false;
		document.getElementById('licenseMenuSubTableDivId').style.display = "none";
		document.getElementById('licenseMenuNumberId').checked=false;
		document.getElementById('licenseMenuDateId').checked=false;
		document.getElementById('licenseMenuRenewalDateId').checked=false;
		document.getElementById('licenseMenuDescriptionId').checked=false;
	}
}

//Check or Un-check All education CheckBox
function selectAllEducationMenuCheckBox(){ 
	if(document.getElementById('educationMenuSelectAllId').checked==true){

		document.getElementById('educationMenuAddId').checked=true;
		document.getElementById('educationMenuViewId').checked=true;
		document.getElementById('educationMenuUpdateId').checked=true;
		document.getElementById('educationMenuDeleteId').checked=true;
		document.getElementById('educationMenuSubTableDivId').style.display = "block";
		document.getElementById('educationMenuMajorId').checked=true;
		document.getElementById('educationMenuYearId').checked=true;
		document.getElementById('educationMenuGradeId').checked=true;
		document.getElementById('educationMenuStartDateId').checked=true;
		document.getElementById('educationMenuEnddateId').checked=true;
		document.getElementById('educationMenuUnivId').checked=true;
		document.getElementById('educationMenuDegreeId').checked=true;
	}else{
		document.getElementById('educationMenuAddId').checked=false;
		document.getElementById('educationMenuViewId').checked=false;
		document.getElementById('educationMenuUpdateId').checked=false;
		document.getElementById('educationMenuDeleteId').checked=false;
		document.getElementById('educationMenuSubTableDivId').style.display = "none";
		document.getElementById('educationMenuMajorId').checked=false;
		document.getElementById('educationMenuYearId').checked=false;
		document.getElementById('educationMenuGradeId').checked=false;
		document.getElementById('educationMenuStartDateId').checked=false;
		document.getElementById('educationMenuEnddateId').checked=false;
		document.getElementById('educationMenuUnivId').checked=false;
		document.getElementById('educationMenuDegreeId').checked=false;
	}
}

//Check or Un-check All projActivity CheckBox
function selectAllProjActivityMenuCheckBox(){ 
	if(document.getElementById('projActivityMenuSelectAllId').checked==true){

		document.getElementById('projActivityMenuAddId').checked=true;
		document.getElementById('projActivityMenuViewId').checked=true;
		document.getElementById('projActivityMenuUpdateId').checked=true;
		document.getElementById('projActivityMenuDeleteId').checked=true;
	}else{
		document.getElementById('projActivityMenuAddId').checked=false;
		document.getElementById('projActivityMenuViewId').checked=false;
		document.getElementById('projActivityMenuUpdateId').checked=false;
		document.getElementById('projActivityMenuDeleteId').checked=false;
	}
}

//Check or Un-check All children CheckBox
function selectAllChildrenMenuCheckBox(){ 
	if(document.getElementById('childrenMenuSelectAllId').checked==true){

		document.getElementById('childrenMenuAddId').checked=true;
		document.getElementById('childrenMenuViewId').checked=true;
		document.getElementById('childrenMenuUpdateId').checked=true;
		document.getElementById('childrenMenuDeleteId').checked=true;
		document.getElementById('childrenMenuSubTableDivId').style.display = "block";
		document.getElementById('childrensMenuchildNameId').checked=true;
		document.getElementById('childrensMenuDateofBirthId').checked=true;
	}else{
		document.getElementById('childrenMenuAddId').checked=false;
		document.getElementById('childrenMenuViewId').checked=false;
		document.getElementById('childrenMenuUpdateId').checked=false;
		document.getElementById('childrenMenuDeleteId').checked=false;
		document.getElementById('childrenMenuSubTableDivId').style.display = "none";
		document.getElementById('childrensMenuchildNameId').checked=false;
		document.getElementById('childrensMenuDateofBirthId').checked=false;
	}
}

//Check or Un-check All directDebit CheckBox
function selectAllDirectDebitMenuCheckBox(){ 
	if(document.getElementById('directDebitMenuSelectAllId').checked==true){

		document.getElementById('directDebitMenuAddId').checked=true;
		document.getElementById('directDebitMenuViewId').checked=true;
		document.getElementById('directDebitMenuUpdateId').checked=true;
		document.getElementById('directDebitMenuDeleteId').checked=true;
		document.getElementById('directDebitMenuSubTableDivId').style.display = "block";
		document.getElementById('directDebitMenuRoutingNoId').checked=true;
		document.getElementById('directDebitMenuAccountId').checked=true;
		document.getElementById('directDebitMenuAmountId').checked=true;
		document.getElementById('directDebitMenuAccontTypeId').checked=true;
		document.getElementById('directDebitMenuTransactionTypetId').checked=true;
		document.getElementById('directDebitMenuPrefAccountId').checked=true;
	}else{
		document.getElementById('directDebitMenuAddId').checked=false;
		document.getElementById('directDebitMenuViewId').checked=false;
		document.getElementById('directDebitMenuUpdateId').checked=false;
		document.getElementById('directDebitMenuDeleteId').checked=false;
		document.getElementById('directDebitMenuSubTableDivId').style.display = "none";
		document.getElementById('directDebitMenuRoutingNoId').checked=false;
		document.getElementById('directDebitMenuAccountId').checked=false;
		document.getElementById('directDebitMenuAmountId').checked=false;
		document.getElementById('directDebitMenuAccontTypeId').checked=false;
		document.getElementById('directDebitMenuTransactionTypetId').checked=false;
		document.getElementById('directDebitMenuPrefAccountId').checked=false;
	}
}

//Check or Un-check All empExperience CheckBox
function selectAllEmpExperienceMenuCheckBox(){ 
	if(document.getElementById('empExperienceMenuSelectAllId').checked==true){

		document.getElementById('empExperienceMenuAddId').checked=true;
		document.getElementById('empExperienceMenuViewId').checked=true;
		document.getElementById('empExperienceMenuUpdateId').checked=true;
		document.getElementById('empExperienceMenuDeleteId').checked=true;
		document.getElementById('workExperienceMenuSubTableDivId').style.display = "block";
		document.getElementById('workExperienceMenuEmployerNameId').checked=true;
		document.getElementById('workExperienceMenuJobTitleId').checked=true;
		document.getElementById('workExperienceMenuFromDateId').checked=true;
		document.getElementById('workExperienceMenuToDateId').checked=true;
		document.getElementById('workExperienceMenuCommentsId').checked=true;
	}else{
		document.getElementById('empExperienceMenuAddId').checked=false;
		document.getElementById('empExperienceMenuViewId').checked=false;
		document.getElementById('empExperienceMenuUpdateId').checked=false;
		document.getElementById('empExperienceMenuDeleteId').checked=false;
		document.getElementById('workExperienceMenuSubTableDivId').style.display = "none";
		document.getElementById('workExperienceMenuEmployerNameId').checked=false;
		document.getElementById('workExperienceMenuJobTitleId').checked=false;
		document.getElementById('workExperienceMenuFromDateId').checked=false;
		document.getElementById('workExperienceMenuToDateId').checked=false;
		document.getElementById('workExperienceMenuCommentsId').checked=false;
		
	}
}

//Check or Un-check All reportTo CheckBox
function selectAllReportToMenuCheckBox(){ 
	if(document.getElementById('reportToMenuSelectAllId').checked==true){

		document.getElementById('reportToMenuAddId').checked=true;
		document.getElementById('reportToMenuViewId').checked=true;
		document.getElementById('reportToMenuUpdateId').checked=true;
		document.getElementById('reportToMenuDeleteId').checked=true;
		document.getElementById('employeeReportToMenuSubTableDivId').style.display = "block";
		document.getElementById('empReporttoMenuSupervisorId').checked=true;
		document.getElementById('empReporttoMenuReportingModeId').checked=true;
	}else{
		document.getElementById('reportToMenuAddId').checked=false;
		document.getElementById('reportToMenuViewId').checked=false;
		document.getElementById('reportToMenuUpdateId').checked=false;
		document.getElementById('reportToMenuDeleteId').checked=false;
		document.getElementById('employeeReportToMenuSubTableDivId').style.display = "none";
		document.getElementById('empReporttoMenuSupervisorId').checked=false;
		document.getElementById('empReporttoMenuReportingModeId').checked=false;
	}
}

//Check or Un-check All locationHistory CheckBox
function selectAllLocationHistoryMenuCheckBox(){ 
	if(document.getElementById('locationHistoryMenuSelectAllId').checked==true){

		document.getElementById('locationHistoryMenuAddId').checked=true;
		document.getElementById('locationHistoryMenuViewId').checked=true;
		document.getElementById('locationHistoryMenuUpdateId').checked=true;
		document.getElementById('locationHistoryMenuDeleteId').checked=true;
		document.getElementById('locationHistoryMenuSubTableDivId').style.display = "block";
		document.getElementById('empLocationHistoryMenuNameId').checked=true;
		document.getElementById('empLocationHistoryMenuClientNameId').checked=true;
		document.getElementById('empLocationHistoryMenuStartDateId').checked=true;
		document.getElementById('empLocationHistoryMenuEndDateId').checked=true;
	}else{
		document.getElementById('locationHistoryMenuAddId').checked=false;
		document.getElementById('locationHistoryMenuViewId').checked=false;
		document.getElementById('locationHistoryMenuUpdateId').checked=false;
		document.getElementById('locationHistoryMenuDeleteId').checked=false;
		document.getElementById('locationHistoryMenuSubTableDivId').style.display = "none";
		document.getElementById('empLocationHistoryMenuNameId').checked=false;
		document.getElementById('empLocationHistoryMenuClientNameId').checked=false;
		document.getElementById('empLocationHistoryMenuStartDateId').checked=false;
		document.getElementById('empLocationHistoryMenuEndDateId').checked=false;
	}
}


//Check or Un-check All employeeSchedule CheckBox
function selectAllScheduleMenuCheckBox(){ 
	if(document.getElementById('employeeScheduleMenuSelectAllId').checked==true){

		document.getElementById('employeeScheduleMenuAddId').checked=true;
		document.getElementById('employeeScheduleMenuViewId').checked=true;
		document.getElementById('employeeScheduleMenuUpdateId').checked=true;
		document.getElementById('employeeScheduleMenuDeleteId').checked=true;
	}else{
		document.getElementById('employeeScheduleMenuAddId').checked=false;
		document.getElementById('employeeScheduleMenuViewId').checked=false;
		document.getElementById('employeeScheduleMenuUpdateId').checked=false;
		document.getElementById('employeeScheduleMenuDeleteId').checked=false;
	}
}

//Check or Un-check All role CheckBox
function selectAllRoleMenuCheckBox(){ 
	if(document.getElementById('roleMenuSelectAllId').checked==true){

		document.getElementById('roleMenuAddId').checked=true;
		document.getElementById('roleMenuViewId').checked=true;
		document.getElementById('roleMenuUpdateId').checked=true;
		document.getElementById('roleMenuDeleteId').checked=true;
	}else{
		document.getElementById('roleMenuAddId').checked=false;
		document.getElementById('roleMenuViewId').checked=false;
		document.getElementById('roleMenuUpdateId').checked=false;
		document.getElementById('roleMenuDeleteId').checked=false;
	}
}

//Check or Un-check All user CheckBox
function selectAllUserMenuCheckBox(){ 
	if(document.getElementById('userMenuSelectAllId').checked==true){

		document.getElementById('userMenuAddId').checked=true;
		document.getElementById('userMenuViewId').checked=true;
		document.getElementById('userMenuUpdateId').checked=true;
		document.getElementById('userMenuDeleteId').checked=true;
	}else{
		document.getElementById('userMenuAddId').checked=false;
		document.getElementById('userMenuViewId').checked=false;
		document.getElementById('userMenuUpdateId').checked=false;
		document.getElementById('userMenuDeleteId').checked=false;
	}
}

//Check or Un-check All empPassport CheckBox
function selectAllEmpPassportMenuCheckBox(){ 
	if(document.getElementById('empPassportMenuSelectAllId').checked==true){

		document.getElementById('empPassportMenuAddId').checked=true;
		document.getElementById('empPassportMenuViewId').checked=true;
		document.getElementById('empPassportMenuUpdateId').checked=true;
		document.getElementById('empPassportMenuDeleteId').checked=true;
		document.getElementById('passportMenuSubTableDivId').style.display = "block";
		document.getElementById('employeeMenuPassportNoId').checked=true;
		document.getElementById('employeePassportMenuPassportIssueId').checked=true;
		document.getElementById('employeePassportMenuPassportExpiredateId').checked=true;
		document.getElementById('employeePassportMenuPassportTypeFlagId').checked=true;
		document.getElementById('employeePassportMenu_19StatusId').checked=true;
		document.getElementById('employeePassportMenu_19ReviewdateId').checked=true;
		document.getElementById('employeePassportMenuCitizenshipId').checked=true;
		document.getElementById('employeepassportMenuCommentsId').checked=true;
	}else{
		document.getElementById('empPassportMenuAddId').checked=false;
		document.getElementById('empPassportMenuViewId').checked=false;
		document.getElementById('empPassportMenuUpdateId').checked=false;
		document.getElementById('empPassportMenuDeleteId').checked=false;
		document.getElementById('passportMenuSubTableDivId').style.display = "none";
		document.getElementById('employeeMenuPassportNoId').checked=false;
		document.getElementById('employeePassportMenuPassportIssueId').checked=false;
		document.getElementById('employeePassportMenuPassportExpiredateId').checked=false;
		document.getElementById('employeePassportMenuPassportTypeFlagId').checked=false;
		document.getElementById('employeePassportMenu_19StatusId').checked=false;
		document.getElementById('employeePassportMenu_19ReviewdateId').checked=false;
		document.getElementById('employeePassportMenuCitizenshipId').checked=false;
		document.getElementById('employeepassportMenuCommentsId').checked=false;
		
	}
}

//Check or Un-check All benefit CheckBox
function selectAllBenefitMenuCheckBox(){ 
	if(document.getElementById('benefitMenuSelectAllId').checked==true){

		document.getElementById('benefitMenuAddId').checked=true;
		document.getElementById('benefitMenuViewId').checked=true;
		document.getElementById('benefitMenuUpdateId').checked=true;
		document.getElementById('benefitMenuDeleteId').checked=true;
		document.getElementById('benefitsMenuSubTableDivId').style.display = "block";
		document.getElementById('benefitsMenuYearId').checked=true;
		document.getElementById('benefitsMenuNameId').checked=true;
		document.getElementById('benefitsMenuAttachFileId').checked=true;
	}else{
		document.getElementById('benefitMenuAddId').checked=false;
		document.getElementById('benefitMenuViewId').checked=false;
		document.getElementById('benefitMenuUpdateId').checked=false;
		document.getElementById('benefitMenuDeleteId').checked=false;
		document.getElementById('benefitsMenuSubTableDivId').style.display = "none";
		document.getElementById('benefitsMenuYearId').checked=false;
		document.getElementById('benefitsMenuNameId').checked=false;
		document.getElementById('benefitsMenuAttachFileId').checked=false;

	}
}


//Check or Un-check All timesheet approver CheckBox
function selectAllTimesheetApproverMenuCheckBox(){ 
	if(document.getElementById('timesheetApproverMenuSelectAllId').checked==true){

		document.getElementById('timesheetApproverMenuAddId').checked=true;
		document.getElementById('timesheetApproverMenuViewId').checked=true;
		document.getElementById('timesheetApproverMenuUpdateId').checked=true;
		document.getElementById('timesheetApproverMenuDeleteId').checked=true;
	}else{
		document.getElementById('timesheetApproverMenuAddId').checked=false;
		document.getElementById('timesheetApproverMenuViewId').checked=false;
		document.getElementById('timesheetApproverMenuUpdateId').checked=false;
		document.getElementById('timesheetApproverMenuDeleteId').checked=false;
	}
}

// Check or Un-check All targetandgoal CheckBox
function selectAllTargetandGoalMenuCheckbox(){
	if(document.getElementById('targetandGoalMenuSelectAllId').checked==true){
		document.getElementById('targetandGoalMenuAddId').checked=true;
		document.getElementById('targetandGoalMenuViewId').checked=true;
		document.getElementById('targetandGoalMenuUpdateId').checked=true;
		document.getElementById('targetandGoalMenuDeleteId').checked=true;
		
	}else{
		document.getElementById('targetandGoalMenuAddId').checked=false;
		document.getElementById('targetandGoalMenuViewId').checked=false;
		document.getElementById('targetandGoalMenuUpdateId').checked=false;
		document.getElementById('targetandGoalMenuDeleteId').checked=false;
	}
}
//check or Un-check All targettype checkBox
function selectAllTargetTypeMenuCheckbox(){
	if(document.getElementById('targetTypeMenuSelectAllTypeId').checked==true){
		document.getElementById('targetTypeMenuAddedId').checked=true;
		document.getElementById('targetTypeMenuViewId').checked=true;
		document.getElementById('targetTypeMenuUpdateId').checked=true;
		document.getElementById('targetTypeMenuDeleteId').checked=true;
		
	}else{
		document.getElementById('targetTypeMenuAddedId').checked=false;
		document.getElementById('targetTypeMenuViewId').checked=false;
		document.getElementById('targetTypeMenuUpdateId').checked=false;
		document.getElementById('targetTypeMenuDeleteId').checked=false;
		
	}
}

//Check or Un-check All expense approver CheckBox
function selectAllExpapproverMenuCheckBox(){ 
	if(document.getElementById('expapproverMenuSelectAllId').checked==true){

		document.getElementById('expapproverMenuAddId').checked=true;
		document.getElementById('expapproverMenuViewId').checked=true;
		document.getElementById('expapproverMenuUpdateId').checked=true;
		document.getElementById('expapproverMenuDeleteId').checked=true;
	}else{
		document.getElementById('expapproverMenuAddId').checked=false;
		document.getElementById('expapproverMenuViewId').checked=false;
		document.getElementById('expapproverMenuUpdateId').checked=false;
		document.getElementById('expapproverMenuDeleteId').checked=false;
	}
}

//Check or Un-check All expenseAccountant CheckBox
function selectAllExpenseAccountantMenuCheckBox(){ 
	if(document.getElementById('expenseAccountantMenuSelectAllId').checked==true){

		document.getElementById('expenseAccountantMenuAddId').checked=true;
		document.getElementById('expenseAccountantMenuViewId').checked=true;
		document.getElementById('expenseAccountantMenuUpdateId').checked=true;
		document.getElementById('expenseAccountantMenuDeleteId').checked=true;
	}else{
		document.getElementById('expenseAccountantMenuAddId').checked=false;
		document.getElementById('expenseAccountantMenuViewId').checked=false;
		document.getElementById('expenseAccountantMenuUpdateId').checked=false;
		document.getElementById('expenseAccountantMenuDeleteId').checked=false;
	}
}

// Check or Un-check All performanceCategory CheckBox
function selectAllPerformanceMenuCheckBox(){
	if(document.getElementById('performancecategoryMenuSelectallId').checked==true){
	document.getElementById('performancemenucategoryAddId').checked=true;
	document.getElementById('performancemenucategoryViewId').checked=true;
	document.getElementById('performancemenucategoryUpdateId').checked=true;
	document.getElementById('performancemenucategoryDeleteId').checked=true;
	
}else{
	document.getElementById('performancemenucategoryAddId').checked=false;
	document.getElementById('performancemenucategoryViewId').checked=false;
	document.getElementById('performancemenucategoryUpdateId').checked=false;
	document.getElementById('performancemenucategoryDeleteId').checked=false;
}
}

//  Check or Un-check All performanceSubCategory CheckBox
function selectAllPerformanceMenuSubCategoryCheckBox(){
	if(document.getElementById('performanceSubcategoryMenuSelectallId').checked==true){
		document.getElementById('performancemenusubcategoryAddId').checked=true;
		document.getElementById('performancemenusubcategoryViewId').checked=true;
		document.getElementById('performancemenusubcategoryUpdateId').checked=true;
		document.getElementById('performancemenusubcategoryDeleteId').checked=true;
	}else{
		document.getElementById('performancemenusubcategoryAddId').checked=false;
		document.getElementById('performancemenusubcategoryViewId').checked=false;
		document.getElementById('performancemenusubcategoryUpdateId').checked=false;
		document.getElementById('performancemenusubcategoryDeleteId').checked=false;
	}
}

//Check or Un-check All kpi CheckBox
function selectAllkpiMenuCheckBox(){
	if(document.getElementById('kpiMenuSelectallNameId').checked==true){
		document.getElementById('performanceMenukpiQuestionId').checked=true;
		document.getElementById('performanceMenukpiGroupId').checked=true;
		document.getElementById('performanceMenuKpiAssignedkpiId').checked=true;
		
	}else{
		document.getElementById('performanceMenukpiQuestionId').checked=false;
		document.getElementById('performanceMenukpiGroupId').checked=false;
		document.getElementById('performanceMenuKpiAssignedkpiId').checked=false;
	}
}