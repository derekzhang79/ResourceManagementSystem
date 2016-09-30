
package com.gits.rms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.BenefitsDaoService;
import com.gits.rms.service.BenefitsService;
import com.gits.rms.service.EmployeeReportToDaoService;
import com.gits.rms.service.EmployeeReportToService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.utils.FileUploadUtil;
import com.gits.rms.vo.BenefitsVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class BenefitsAction extends ActionSupport {

    private static final long serialVersionUID = 4510829252888337869L;
    private FileUploadUtil fileupload = new FileUploadUtil();
    private BenefitsVO benefit;
    private List<BenefitsVO> benefitList;
    private BenefitsService benefitService = new BenefitsDaoService();
    private EmployeesService employeeService = new EmployeesDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private RoleService roleService = new RoleDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private EmployeeReportToService employeeReportToService = new EmployeeReportToDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<EmployeesVO> empIdObj;
    private List<EmployeesVO> adminRoleId;
    private EmployeesVO emp;
    private InputStream inStream;
    private File benefitsAttachFile;
    private RoleVO role;
    private String benefitsAttachFileContentType;
    private String benefitsAttachFileFileName;
    private String fileName;
    private String benModifiedDate = "";

    // To get List of Benefits
    @SkipValidation
    public String getAllBenefit() {
        BenefitsVO newBenefitObj = null;
        String EmpIdList = "";
        String empNameList = "";
        int number;
        
        Map session = ActionContext.getContext().getSession();
    	EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
    	List<Integer> employeeReportToList;
    	
    	//checking whether the login person is a Report To Authority
    	boolean reportTo = employeeReportToService.checkLoginEmployeeIsReportToEmp(oEmp.getEmployeeId());
    	
    	if(reportTo){
    		employeeReportToList = employeeReportToService.getSubEmployeeList(oEmp.getEmployeeId());
    		
    		benefitList = benefitService.getAllSubEmployeeBenefitList(employeeReportToList);
    	}else{
    		benefitList = benefitService.getAllBenefit();
    	}
        
        for (Iterator<BenefitsVO> it = benefitList.iterator(); it.hasNext();) {
            newBenefitObj = it.next();
            EmpIdList = newBenefitObj.getEmpId();
            String[] employeeIdArray = EmpIdList.split(",");
            number = employeeIdArray.length;
            for (int j = 0; j < number; j++) {
                String empIdSingleList = employeeIdArray[j];
                int empIdForIntegerValue = Integer.parseInt(empIdSingleList);
                emp = employeeService.getEmployees(empIdForIntegerValue);
                empNameList = empNameList + emp.getEmpFullName() + ",";
            }
            empNameList = empNameList.substring(0, empNameList.lastIndexOf(','));
            newBenefitObj.setEmployeeName(empNameList);
            empNameList = "";
        }
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Benefits it shows blank Form to enter New Data
    @SkipValidation
    public String setUpBenefit() {
        if ((benefit != null) && (benefit.getHcmoBenefitsId() != null)) {
            benefit = benefitService.getBenefit(benefit.getHcmoBenefitsId());
        }
        return SUCCESS;
    }

    // To get Particular Benefit Data
    @SkipValidation
    public String benefitView() {
        String EmployeeId = "";
        String EmpIdList = "";
        String empNameList = "";
        int l;

        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");

        if ((benefit != null) && (benefit.getHcmoBenefitsId() != null)) {
            benefit = benefitService.getBenefit(benefit.getHcmoBenefitsId());

            EmpIdList = EmpIdList + benefit.getEmpId() + ",";
            EmployeeId = EmpIdList.substring(0, EmpIdList.lastIndexOf(','));
            String[] employeeIdArray = EmployeeId.split(",");
            l = employeeIdArray.length;

            for (int j = 0; j < l; j++) {
                String empIdSingleList = employeeIdArray[j];
                benefit.setEmpId(empIdSingleList);
                int empIdForIntegerValue = Integer.parseInt(benefit.getEmpId());
                emp = employeeService.getEmployees(empIdForIntegerValue);
                empNameList = empNameList + emp.getEmpFullName() + ",";
            }
            empNameList = empNameList.substring(0, empNameList.lastIndexOf(','));
            session.put("EMPNAME_LIST", empNameList);
        }
        return SUCCESS;
    }

    // To search new page to search list of benefit for particular year
    @SkipValidation
    public String benefitSearch() {
        return SUCCESS;
    }

    // To show list of benefit for particular year
    @SkipValidation
    public String getAllBenefitYearSearchResult() {
        benefitList = benefitService.getAllBenefitYearSearchResult(benefit);
        return SUCCESS;
    }

    // This method is used to DownLoad a particular file
    @SkipValidation
    public String fileDownload() throws Exception {
        Map session = ActionContext.getContext().getSession();
        benefit = benefitService.getBenefit(benefit.getHcmoBenefitsId());
        inStream = new FileInputStream(new File(getText("ApplicationAbsolutePath")
            + ServletActionContext.getServletContext().getContextPath() + "/"
            + getText("BenefitAttachments") + "/" + "MASTER_CLIENTID_"
            + session.get("MASTER_CLIENT_ID") + "/" + benefit.getYear() + "/"
            + benefit.getBenefitsAttachFile()));
        return SUCCESS;
    }

    @Override
    public void validate() {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");

        int fileSize = Integer.parseInt(getText("label.header.totalFileSize"));
        int maxiFolderSize = fileSize / 1048576;

        Collection<?> tmp = getActionErrors();
        Collection<String> errors = new ArrayList<String>();
        for (Object o : tmp) {
            if (o.toString().contains("the request was rejected because its size")) {
                if (maxiFolderSize >= 10) {
                    errors.add(getText("Uploaded file was too large.Your file size exceed:"
                        + maxiFolderSize + " " + "MB"));
                }
            }
        }
        setActionErrors(errors);
    }

    // In the New Form when click Submit button To insert new Benefit or update
    // particular Benefit Data
    public String insertOrUpdateBenefit() {

        EmployeesVO newEmpBenefit = null;
        EmployeesVO EmployeeEmail = null;
        EmployeesVO newAdminEmp = null;
        String EmployeeEmailIds = "";
        String EmployeeId = "";
        String EmpIdList = "";
        int l;

        if (benefit.getHcmoBenefitsId() == null) {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            if (benefitsAttachFileFileName != null) {
                fileupload.uploadFile(benefitsAttachFile.getAbsolutePath(), benefitsAttachFileFileName, getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath()
                    + "/", getText("BenefitAttachments") + "/" + "MASTER_CLIENTID_"
                    + session.get("MASTER_CLIENT_ID") + "/" + benefit.getYear() + "/");

                benefit.setBenefitsAttachFile(getBenefitsAttachFileFileName());
            }
            if (benefit.getEmpIdObjList() == null) {
                addActionError(getText("Please select atleast one Employee "));
                return INPUT;
            }
            for (Iterator<EmployeesVO> it = benefit.getEmpIdObjList().iterator(); it.hasNext();) {
                newEmpBenefit = it.next();
                EmployeeEmail = benefitService.getEmployeeName(newEmpBenefit.getEmployeeId());

                newEmpBenefit.getEmployeeId();
                EmployeeEmailIds = EmployeeEmailIds + EmployeeEmail.getEmpWorkEmail() + ",";
                EmployeeId = EmployeeId + newEmpBenefit.getEmployeeId() + ",";
            }
            EmployeeId = EmployeeId.substring(0, EmployeeId.lastIndexOf(','));
            EmployeeEmailIds = EmployeeEmailIds.substring(0, EmployeeEmailIds.lastIndexOf(','));

            benefit.setEmpId(EmployeeId);
            benefit.setEmployeeEmailId(EmployeeEmailIds);
            benefit.setCreated(DateUtils.getCurrentDateTime());
            benefit.setCreatedBy(oEmp);
            benefit.setUpdatedBy(oEmp);
            benefit.setIsActive(1);
            benefitService.insertBenefit(benefit);
            addActionMessage(getText("Added Successfully"));
            // For Alert Message
            // Retrieved single Shared EmpIds
            EmpIdList = EmpIdList + benefit.getEmpId() + ",";
            EmployeeId = EmpIdList.substring(0, EmpIdList.lastIndexOf(','));
            String[] employeeIdArray = EmployeeId.split(",");
            l = employeeIdArray.length;
            int sessionEmployeeID = oEmp.getEmployeeId();
            String sSubject = getText("message.subject.benefits.add");

            role = roleService.getRoleName(getText("message.label.common.adminName"));
            // Retrieved the Many more Admin employee list
            List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
            int lengthForAdminEmpList = adminRoleId.size();

            Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                newAdminEmp = it.next();
                newAdminEmp.getEmployeeId();
                for (int j = 0; j < l; j++) {
                    String empIdSingleList = employeeIdArray[j];
                    benefit.setEmpId(empIdSingleList);
                    int empIdForIntegerValue = Integer.parseInt(benefit.getEmpId());
                    emp = employeeService.getEmployees(empIdForIntegerValue);
                    if (sessionEmployeeID != emp.getEmployeeId()) {
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("benefits.admin.add.addToAdmin"), emp.getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                    } else {
                        mailToEmp(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("benefits.admin.addToHisOwn.addToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                    }
                }
            }
            for (int j = 0; j < l; j++) {
                String empIdSingleList = employeeIdArray[j];
                benefit.setEmpId(empIdSingleList);
                int empIdForIntegerValue = Integer.parseInt(benefit.getEmpId());
                emp = employeeService.getEmployees(empIdForIntegerValue);

                if (sessionEmployeeID != emp.getEmployeeId()) {
                    // Mail to selected employees except the logged in person
                    mailToEmp(emp.getEmployeeId(), emp.getEmpFirstName(), getText("benefits.employee.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                } else {
                    // Mail to the logged in person
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("benefits.loggedIn.add.addedBy"), sSubject);
                }
            }

        } else {
            // To Update the Benefit
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            if (benefitsAttachFileFileName != null) {
                fileupload.uploadFile(benefitsAttachFile.getAbsolutePath(), benefitsAttachFileFileName, getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath()
                    + "/", getText("BenefitAttachments") + "/" + "MASTER_CLIENTID_"
                    + session.get("MASTER_CLIENT_ID") + "/" + benefit.getYear() + "/");
            }
            for (Iterator<EmployeesVO> it = benefit.getEmpIdObjList().iterator(); it.hasNext();) {
                newEmpBenefit = it.next();
                EmployeeEmail = benefitService.getEmployeeName(newEmpBenefit.getEmployeeId());

                newEmpBenefit.getEmployeeId();
                EmployeeEmailIds = EmployeeEmailIds + EmployeeEmail.getEmpWorkEmail() + ",";
                EmployeeId = EmployeeId + newEmpBenefit.getEmployeeId() + ",";
            }
            EmployeeId = EmployeeId.substring(0, EmployeeId.lastIndexOf(','));
            EmployeeEmailIds = EmployeeEmailIds.substring(0, EmployeeEmailIds.lastIndexOf(','));

            benefit.setEmpId(EmployeeId);
            benefit.setEmployeeEmailId(EmployeeEmailIds);
            benefit.setBenefitsAttachFile(getBenefitsAttachFileFileName());
            benefit.setUpdatedBy(oEmp);
            benefitService.updateBenefit(benefit);

            addActionMessage(getText("Updated Sucessfully"));
        }
        // For Drop down List
        loadValues.getAllBenefitsYear();
        return SUCCESS;
    }

    // To delete particular Benefit data of an employee
    @SkipValidation
    public String deleteBenefit() {

        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        String EmployeeId = "";
        String EmpIdList = "";
        EmployeesVO newAdminEmp = null;
        int l;

        benefit = benefitService.getBenefit(benefit.getHcmoBenefitsId());
        benefit.getEmpId();
        EmpIdList = EmpIdList + benefit.getEmpId() + ",";
        EmployeeId = EmpIdList.substring(0, EmpIdList.lastIndexOf(','));
        String[] employeeIdArray = EmployeeId.split(",");
        l = employeeIdArray.length;
        int sessionEmployeeID = oEmp.getEmployeeId();

        benefit.setUpdatedBy(oEmp);
        benefitService.deleteBenefit(benefit);

        role = roleService.getRoleName(getText("message.label.common.adminName"));
        String sSubject = getText("message.subject.benefits.delete");
        // Retrieved the Many more Admin employee list
        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
        int lengthForAdminEmpList = adminRoleId.size();

        // For Alert Message
        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
            newAdminEmp = it.next();
            newAdminEmp.getEmployeeId();
            for (int j = 0; j < l; j++) {
                String empIdSingleList = employeeIdArray[j];
                benefit.setEmpId(empIdSingleList);
                int empIdForIntegerValue = Integer.parseInt(benefit.getEmpId());
                emp = employeeService.getEmployees(empIdForIntegerValue);

                if (sessionEmployeeID != emp.getEmployeeId()) {
                    // Mail content to All Admin Employee List
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("benefits.admin.delete.deleteToAdmin"), emp.getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to selected employees except the logged in person
                    mailToEmp(emp.getEmployeeId(), emp.getEmpFirstName(), getText("benefits.employee.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                } else {
                    // Mail content to All Admin Employee List
                    mailToEmp(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("benefits.admin.deleteToHisOwn.addToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to the logged in person
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("benefits.loggedIn.delete.deletedBy"), sSubject);
                }
            }
        }
        addActionMessage(getText("Deleted Sucessfully"));

        // For Drop down List
        loadValues.getAllBenefitsYear();
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();
            benefit = benefitService.getBenefit(benefit.getHcmoBenefitsId());

            DateFormat updatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date benefitModdate = benefit.getUpdated();
            benModifiedDate = updatedDateFormat.format(benefitModdate);

            String sDummy = Constants.PERSON;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.benefit.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.benefit.year") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + benefit.getYear() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.benefit.benefitsName")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + benefit.getBenefitsName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.benefit.benefitsAttachFile")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + benefit.getBenefitsAttachFile()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + benModifiedDate
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

            .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_BREAK);

            signatureList = signatureService.getAllSignatureForLoginEmp();
            if (signatureList.isEmpty() == true) {
                sSignature = getText("alert.common.signature");
            } else {
                for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                    sigObj = it.next();
                    if (sigObj.isPreSignature() == true) {
                        sSignature = sigObj.getSignatureName();
                    }
                }
            }
            sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
                + HtmlConstants.HTML_PARA_END_TAG);
            mailer.sendAlertEmail(oFirstPerson.toString(), sSubject, sMessage, sSignature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mailToEmp(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();
            benefit = benefitService.getBenefit(benefit.getHcmoBenefitsId());

            DateFormat updatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date benefitModdate = benefit.getUpdated();
            benModifiedDate = updatedDateFormat.format(benefitModdate);

            String sDummy = Constants.PERSON;
            String sLoggedIN = Constants.LOGGEDIN_PERSON;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
            sMessage.replace(sMessage.lastIndexOf(sLoggedIN), sMessage.lastIndexOf(sLoggedIN)
                + sLoggedIN.length(), From);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.benefit.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.benefit.year") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + benefit.getYear() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.benefit.benefitsName")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + benefit.getBenefitsName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.benefit.benefitsAttachFile")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + benefit.getBenefitsAttachFile()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + benModifiedDate
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

            .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_BREAK);

            signatureList = signatureService.getAllSignatureForLoginEmp();
            if (signatureList.isEmpty() == true) {
                sSignature = getText("alert.common.signature");
            } else {
                for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                    sigObj = it.next();
                    if (sigObj.isPreSignature() == true) {
                        sSignature = sigObj.getSignatureName();
                    }
                }
            }
            sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
                + HtmlConstants.HTML_PARA_END_TAG);
            mailer.sendAlertEmail(oFirstPerson.toString(), sSubject, sMessage, sSignature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mailToAdmin(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();
            benefit = benefitService.getBenefit(benefit.getHcmoBenefitsId());

            DateFormat updatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date benefitModdate = benefit.getUpdated();
            benModifiedDate = updatedDateFormat.format(benefitModdate);

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.EMPLOYEE_PERSON;
            String sLoggedIN = Constants.LOGGEDIN_PERSON;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
            sMessage.replace(sMessage.indexOf(sFirstPerson), sMessage.indexOf(sFirstPerson)
                + sFirstPerson.length(), From);
            sMessage.replace(sMessage.lastIndexOf(sLoggedIN), sMessage.lastIndexOf(sLoggedIN)
                + sLoggedIN.length(), LoggedIn);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.benefit.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.benefit.year") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + benefit.getYear() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.benefit.benefitsName")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + benefit.getBenefitsName()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.benefit.benefitsAttachFile")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + benefit.getBenefitsAttachFile()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + benModifiedDate
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_END_TAG + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG + HtmlConstants.HTML_TABLE_END_TAG)

            .append(HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.forAnyInfo") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_BREAK);

            signatureList = signatureService.getAllSignatureForLoginEmp();
            if (signatureList.isEmpty() == true) {
                sSignature = getText("alert.common.signature");
            } else {
                for (Iterator<SignatureVO> it = signatureList.iterator(); it.hasNext();) {
                    sigObj = it.next();
                    if (sigObj.isPreSignature() == true) {
                        sSignature = sigObj.getSignatureName();
                    }
                }
            }
            sMessage.append(HtmlConstants.HTML_PARA_FONT_START_TAG + sSignature
                + HtmlConstants.HTML_PARA_END_TAG);
            mailer.sendAlertEmail(oFirstPerson.toString(), sSubject, sMessage, sSignature);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<BenefitsVO> getBenefitList() {
        return benefitList;
    }

    public void setBenefitList(List<BenefitsVO> benefitList) {
        this.benefitList = benefitList;
    }

    public BenefitsVO getBenefit() {
        return benefit;
    }

    public void setBenefit(BenefitsVO benefit) {
        this.benefit = benefit;
    }

    public List<EmployeesVO> getEmpIdObj() {
        return empIdObj;
    }

    public void setEmpIdObj(List<EmployeesVO> empIdObj) {
        this.empIdObj = empIdObj;
    }

    public InputStream getInStream() {
        return inStream;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

    public File getBenefitsAttachFile() {
        return benefitsAttachFile;
    }

    public void setBenefitsAttachFile(File benefitsAttachFile) {
        this.benefitsAttachFile = benefitsAttachFile;
    }

    public String getBenefitsAttachFileContentType() {
        return benefitsAttachFileContentType;
    }

    public void setBenefitsAttachFileContentType(String benefitsAttachFileContentType) {
        this.benefitsAttachFileContentType = benefitsAttachFileContentType;
    }

    public String getBenefitsAttachFileFileName() {
        return benefitsAttachFileFileName;
    }

    public void setBenefitsAttachFileFileName(String benefitsAttachFileFileName) {
        this.benefitsAttachFileFileName = benefitsAttachFileFileName;
    }

    public BenefitsService getBenefitService() {
        return benefitService;
    }

    public void setBenefitService(BenefitsService benefitService) {
        this.benefitService = benefitService;
    }

    public String getFileName() {
        fileName = benefit.getBenefitsAttachFile();
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setAdminRoleId(List<EmployeesVO> adminRoleId) {
        this.adminRoleId = adminRoleId;
    }

    public List<EmployeesVO> getAdminRoleId() {
        return adminRoleId;
    }

    public void setSignatureList(List<SignatureVO> signatureList) {
        this.signatureList = signatureList;
    }

    public List<SignatureVO> getSignatureList() {
        return signatureList;
    }
}