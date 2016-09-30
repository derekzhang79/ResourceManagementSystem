
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class EmployeesVO implements Serializable {

	private static final long serialVersionUID = -8873753680837024615L;
	private CountryVO country;
	private Date created;
	private EmployeesVO createdBy;
	private DepartmentVO departmentIdObj;
	private DocumentVO documentIdObj;
	private Date empBirthDate;
	private Date empBirthDateEnds;
	private String empBirthDateValue;
	private String empCityName;
	private String empCounName;
	private String empCustom1;
	private String empCustom2;
	private Date empDriLicenExpDate;
	private Date empDriLicenExpDateEnds;
	private String empDriLicenExpDateValue;
	private String empDriLicenNum;
	private String empFirstName;
	private String empFullName;
	private String empGender;
	private String empGenderValue;
	private String empHmTelephone;
	private Date empJoineddate;
	private Date empJoinedDateEnds;
	private String empJoinedDateValue;
	private String empLastName;
	private Integer employeeId;
	private String employeeWage;
	private String empMaritalStatus;
	private String empMidName;
	private String empMilitaryService;
	private String empMobile;
	private String empNickName;
	private String empOthEmail;
	private String empOtherId;
	private String empOtherName;
	private String empPicturePath;
	private boolean empSmoker;
	private String empSpace;
	private String empSSNNumber;
	private EmployeeStatusVO empStatusIdObj;
	private String empStreet1;
	private String empStreet2;
	private Date empTerminatedDate;
	private String empTerminatedReason;
	private String empType;
	private String empWorkEmail;
	private String empWorkTelephone;
	private String empZipCode;
	private EthnicRaceVO ethnicRaceIdObj;
	private int isActive;
	private JobTitleVO jobTitleIdObj;
	private List message = new LinkedList();
	private NationalityVO nationalityIdObj;
	private RoleVO roleObj;
	private TeamVO teamIdObj;
	private Timestamp updated;
	private EmployeesVO updatedBy;
	private VendorVO vendorIdObj;
	private int clientId;
	private String accessType;
	private Boolean rLiteAccess;

	private EmployeesVO employee;
	private ProjectAssignEmpVO assProj;
	private ProjectActivityVO proActivity;

	public EmployeesVO() {
	}

	public CountryVO getCountry() {
		return country;
	}

	public Date getCreated() {
		return created;
	}

	public EmployeesVO getCreatedBy() {
		return createdBy;
	}

	//@JsonIgnore
	public DepartmentVO getDepartmentIdObj() {
		return departmentIdObj;
	}

	public Date getEmpBirthDate() {
		return empBirthDate;
	}

	public Date getEmpBirthDateEnds() {
		return empBirthDateEnds;
	}

	public String getEmpBirthDateValue() {
		return empBirthDateValue;
	}

	public String getEmpCityName() {
		return empCityName;
	}

	public String getEmpCounName() {
		return empCounName;
	}

	public String getEmpCustom1() {
		return empCustom1;
	}

	public String getEmpCustom2() {
		return empCustom2;
	}

	public Date getEmpDriLicenExpDate() {
		return empDriLicenExpDate;
	}

	public Date getEmpDriLicenExpDateEnds() {
		return empDriLicenExpDateEnds;
	}

	public String getEmpDriLicenExpDateValue() {
		return empDriLicenExpDateValue;
	}

	public String getEmpDriLicenNum() {
		return empDriLicenNum;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public String getEmpFullName() {
		return empFullName;
	}

	public String getEmpGender() {
		return empGender;
	}

	public String getEmpGenderValue() {
		return empGenderValue;
	}

	public String getEmpHmTelephone() {
		return empHmTelephone;
	}

	public Date getEmpJoineddate() {
		return empJoineddate;
	}

	public Date getEmpJoinedDateEnds() {
		return empJoinedDateEnds;
	}

	public String getEmpJoinedDateValue() {
		return empJoinedDateValue;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeWage() {
		return employeeWage;
	}

	public String getEmpMaritalStatus() {
		return empMaritalStatus;
	}

	public String getEmpMidName() {
		return empMidName;
	}

	public String getEmpMilitaryService() {
		return empMilitaryService;
	}

	public String getEmpMobile() {
		return empMobile;
	}

	public String getEmpNickName() {
		return empNickName;
	}

	public String getEmpOthEmail() {
		return empOthEmail;
	}

	public String getEmpOtherId() {
		return empOtherId;
	}

	public String getEmpOtherName() {
		return empOtherName;
	}

	public String getEmpPicturePath() {
		return empPicturePath;
	}

	public String getEmpSpace() {
		return empSpace;
	}

	public String getEmpSSNNumber() {
		return empSSNNumber;
	}

	public EmployeeStatusVO getEmpStatusIdObj() {
		return empStatusIdObj;
	}

	public String getEmpStreet1() {
		return empStreet1;
	}

	public String getEmpStreet2() {
		return empStreet2;
	}

	public Date getEmpTerminatedDate() {
		return empTerminatedDate;
	}

	public String getEmpTerminatedReason() {
		return empTerminatedReason;
	}

	public String getEmpType() {
		return empType;
	}

	public String getEmpWorkEmail() {
		return empWorkEmail;
	}

	public String getEmpWorkTelephone() {
		return empWorkTelephone;
	}

	public String getEmpZipCode() {
		return empZipCode;
	}

	public EthnicRaceVO getEthnicRaceIdObj() {
		return ethnicRaceIdObj;
	}

	public int getIsActive() {
		return isActive;
	}

	public JobTitleVO getJobTitleIdObj() {
		return jobTitleIdObj;
	}

	public List getMessage() {
		return message;
	}

	public NationalityVO getNationalityIdObj() {
		return nationalityIdObj;
	}

	public RoleVO getRoleObj() {
		return roleObj;
	}

	public TeamVO getTeamIdObj() {
		return teamIdObj;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public EmployeesVO getUpdatedBy() {
		return updatedBy;
	}

	public VendorVO getVendorIdObj() {
		return vendorIdObj;
	}

	public boolean isEmpSmoker() {
		return empSmoker;
	}

	public void setCountry(CountryVO country) {
		this.country = country;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setCreatedBy(EmployeesVO createdBy) {
		this.createdBy = createdBy;
	}

	public void setDepartmentIdObj(DepartmentVO departmentIdObj) {
		this.departmentIdObj = departmentIdObj;
	}

	public void setEmpBirthDate(Date empBirthDate) {
		this.empBirthDate = empBirthDate;
	}

	public void setEmpBirthDateEnds(Date empBirthDateEnds) {
		this.empBirthDateEnds = empBirthDateEnds;
	}

	public void setEmpBirthDateValue(String empBirthDateValue) {
		this.empBirthDateValue = empBirthDateValue;
	}

	public void setEmpCityName(String empCityName) {
		this.empCityName = empCityName;
	}

	public void setEmpCounName(String empCounName) {
		this.empCounName = empCounName;
	}

	public void setEmpCustom1(String empCustom1) {
		this.empCustom1 = empCustom1;
	}

	public void setEmpCustom2(String empCustom2) {
		this.empCustom2 = empCustom2;
	}

	public void setEmpDriLicenExpDate(Date empDriLicenExpDate) {
		this.empDriLicenExpDate = empDriLicenExpDate;
	}

	public void setEmpDriLicenExpDateEnds(Date empDriLicenExpDateEnds) {
		this.empDriLicenExpDateEnds = empDriLicenExpDateEnds;
	}

	public void setEmpDriLicenExpDateValue(String empDriLicenExpDateValue) {
		this.empDriLicenExpDateValue = empDriLicenExpDateValue;
	}

	public void setEmpDriLicenNum(String empDriLicenNum) {
		this.empDriLicenNum = empDriLicenNum;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public void setEmpFullName(String empFullName) {
		this.empFullName = empFullName;
	}

	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}

	public void setEmpGenderValue(String empGenderValue) {
		this.empGenderValue = empGenderValue;
	}

	public void setEmpHmTelephone(String empHmTelephone) {
		this.empHmTelephone = empHmTelephone;
	}

	public void setEmpJoineddate(Date empJoineddate) {
		this.empJoineddate = empJoineddate;
	}

	public void setEmpJoinedDateEnds(Date empJoinedDateEnds) {
		this.empJoinedDateEnds = empJoinedDateEnds;
	}

	public void setEmpJoinedDateValue(String empJoinedDateValue) {
		this.empJoinedDateValue = empJoinedDateValue;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmployeeWage(String employeeWage) {
		this.employeeWage = employeeWage;
	}

	public void setEmpMaritalStatus(String empMaritalStatus) {
		this.empMaritalStatus = empMaritalStatus;
	}

	public void setEmpMidName(String empMidName) {
		this.empMidName = empMidName;
	}

	public void setEmpMilitaryService(String empMilitaryService) {
		this.empMilitaryService = empMilitaryService;
	}

	public void setEmpMobile(String empMobile) {
		this.empMobile = empMobile;
	}

	public void setEmpNickName(String empNickName) {
		this.empNickName = empNickName;
	}

	public void setEmpOthEmail(String empOthEmail) {
		this.empOthEmail = empOthEmail;
	}

	public void setEmpOtherId(String empOtherId) {
		this.empOtherId = empOtherId;
	}

	public void setEmpOtherName(String empOtherName) {
		this.empOtherName = empOtherName;
	}

	public void setEmpPicturePath(String empPicturePath) {
		this.empPicturePath = empPicturePath;
	}

	public void setEmpSmoker(boolean empSmoker) {
		this.empSmoker = empSmoker;
	}

	public void setEmpSpace(String empSpace) {
		this.empSpace = empSpace;
	}

	public void setEmpSSNNumber(String empSSNNumber) {
		this.empSSNNumber = empSSNNumber;
	}

	public void setEmpStatusIdObj(EmployeeStatusVO empStatusIdObj) {
		this.empStatusIdObj = empStatusIdObj;
	}

	public void setEmpStreet1(String empStreet1) {
		this.empStreet1 = empStreet1;
	}

	public void setEmpStreet2(String empStreet2) {
		this.empStreet2 = empStreet2;
	}

	public void setEmpTerminatedDate(Date empTerminatedDate) {
		this.empTerminatedDate = empTerminatedDate;
	}

	public void setEmpTerminatedReason(String empTerminatedReason) {
		this.empTerminatedReason = empTerminatedReason;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public void setEmpWorkEmail(String empWorkEmail) {
		this.empWorkEmail = empWorkEmail;
	}

	public void setEmpWorkTelephone(String empWorkTelephone) {
		this.empWorkTelephone = empWorkTelephone;
	}

	public void setEmpZipCode(String empZipCode) {
		this.empZipCode = empZipCode;
	}

	public void setEthnicRaceIdObj(EthnicRaceVO ethnicRaceIdObj) {
		this.ethnicRaceIdObj = ethnicRaceIdObj;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public void setJobTitleIdObj(JobTitleVO jobTitleIdObj) {
		this.jobTitleIdObj = jobTitleIdObj;
	}

	public void setMessage(List message) {
		this.message = message;
	}

	public void setNationalityIdObj(NationalityVO nationalityIdObj) {
		this.nationalityIdObj = nationalityIdObj;
	}

	public void setRoleObj(RoleVO roleObj) {
		this.roleObj = roleObj;
	}

	public void setTeamIdObj(TeamVO teamIdObj) {
		this.teamIdObj = teamIdObj;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public void setUpdatedBy(EmployeesVO updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setVendorIdObj(VendorVO vendorIdObj) {
		this.vendorIdObj = vendorIdObj;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public Boolean getrLiteAccess() {
		return rLiteAccess;
	}

	public void setrLiteAccess(Boolean rLiteAccess) {
		this.rLiteAccess = rLiteAccess;
	}

	public DocumentVO getDocumentIdObj() {
		return documentIdObj;
	}

	public void setDocumentIdObj(DocumentVO documentIdObj) {
		this.documentIdObj = documentIdObj;
	}

	public EmployeesVO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeesVO employee) {
		this.employee = employee;
	}

	public ProjectAssignEmpVO getAssProj() {
		return assProj;
	}

	public void setAssProj(ProjectAssignEmpVO assProj) {
		this.assProj = assProj;
	}

	public ProjectActivityVO getProActivity() {
		return proActivity;
	}

	public void setProActivity(ProjectActivityVO proActivity) {
		this.proActivity = proActivity;
	}

	public EmployeesVO(CountryVO country, Date created, EmployeesVO createdBy, DepartmentVO departmentIdObj,
			DocumentVO documentIdObj, Date empBirthDate, Date empBirthDateEnds, String empBirthDateValue,
			String empCityName, String empCounName, String empCustom1, String empCustom2, Date empDriLicenExpDate,
			Date empDriLicenExpDateEnds, String empDriLicenExpDateValue, String empDriLicenNum, String empFirstName,
			String empFullName, String empGender, String empGenderValue, String empHmTelephone, Date empJoineddate,
			Date empJoinedDateEnds, String empJoinedDateValue, String empLastName, Integer employeeId,
			String employeeWage, String empMaritalStatus, String empMidName, String empMilitaryService,
			String empMobile, String empNickName, String empOthEmail, String empOtherId, String empOtherName,
			String empPicturePath, boolean empSmoker, String empSpace, String empSSNNumber,
			EmployeeStatusVO empStatusIdObj, String empStreet1, String empStreet2, Date empTerminatedDate,
			String empTerminatedReason, String empType, String empWorkEmail, String empWorkTelephone, String empZipCode,
			EthnicRaceVO ethnicRaceIdObj, int isActive, JobTitleVO jobTitleIdObj, List message,
			NationalityVO nationalityIdObj, RoleVO roleObj, TeamVO teamIdObj, Timestamp updated, EmployeesVO updatedBy,
			VendorVO vendorIdObj, int clientId, String accessType, Boolean rLiteAccess, EmployeesVO employee) {
		super();
		this.country = country;
		this.created = created;
		this.createdBy = createdBy;
		this.departmentIdObj = departmentIdObj;
		this.documentIdObj = documentIdObj;
		this.empBirthDate = empBirthDate;
		this.empBirthDateEnds = empBirthDateEnds;
		this.empBirthDateValue = empBirthDateValue;
		this.empCityName = empCityName;
		this.empCounName = empCounName;
		this.empCustom1 = empCustom1;
		this.empCustom2 = empCustom2;
		this.empDriLicenExpDate = empDriLicenExpDate;
		this.empDriLicenExpDateEnds = empDriLicenExpDateEnds;
		this.empDriLicenExpDateValue = empDriLicenExpDateValue;
		this.empDriLicenNum = empDriLicenNum;
		this.empFirstName = empFirstName;
		this.empFullName = empFullName;
		this.empGender = empGender;
		this.empGenderValue = empGenderValue;
		this.empHmTelephone = empHmTelephone;
		this.empJoineddate = empJoineddate;
		this.empJoinedDateEnds = empJoinedDateEnds;
		this.empJoinedDateValue = empJoinedDateValue;
		this.empLastName = empLastName;
		this.employeeId = employeeId;
		this.employeeWage = employeeWage;
		this.empMaritalStatus = empMaritalStatus;
		this.empMidName = empMidName;
		this.empMilitaryService = empMilitaryService;
		this.empMobile = empMobile;
		this.empNickName = empNickName;
		this.empOthEmail = empOthEmail;
		this.empOtherId = empOtherId;
		this.empOtherName = empOtherName;
		this.empPicturePath = empPicturePath;
		this.empSmoker = empSmoker;
		this.empSpace = empSpace;
		this.empSSNNumber = empSSNNumber;
		this.empStatusIdObj = empStatusIdObj;
		this.empStreet1 = empStreet1;
		this.empStreet2 = empStreet2;
		this.empTerminatedDate = empTerminatedDate;
		this.empTerminatedReason = empTerminatedReason;
		this.empType = empType;
		this.empWorkEmail = empWorkEmail;
		this.empWorkTelephone = empWorkTelephone;
		this.empZipCode = empZipCode;
		this.ethnicRaceIdObj = ethnicRaceIdObj;
		this.isActive = isActive;
		this.jobTitleIdObj = jobTitleIdObj;
		this.message = message;
		this.nationalityIdObj = nationalityIdObj;
		this.roleObj = roleObj;
		this.teamIdObj = teamIdObj;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.vendorIdObj = vendorIdObj;
		this.clientId = clientId;
		this.accessType = accessType;
		this.rLiteAccess = rLiteAccess;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmployeesVO [empCityName=" + empCityName + ", empFirstName=" + empFirstName + ", empFullName="
				+ empFullName + ", empGender=" + empGender + ", employeeId=" + employeeId + "]";
	}

}
