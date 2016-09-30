
package com.gits.rms.action;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;
import com.gits.rms.constants.HtmlConstants;
import com.gits.rms.mail.HCMOneMailer;
import com.gits.rms.service.CurrencyDaoService;
import com.gits.rms.service.CurrencyService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SalaryDaoService;
import com.gits.rms.service.SalaryService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.CurrencyVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SalaryVO;
import com.gits.rms.vo.SignatureVO;

public class SalaryAction extends ActionSupport {
    private static final long serialVersionUID = 5342248949722708250L;
    private SalaryService salService = new SalaryDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<SalaryVO> salary;
    private List<SalaryVO> curForsalary;
    private List<CurrencyVO> currency;
    private List<EmployeesVO> adminRoleId;
    private RoleVO role;
    private SalaryVO sal;
    private CurrencyService currencyService = new CurrencyDaoService();
    private RoleService roleService = new RoleDaoService();
    private EmployeesVO emps;
    private String salDecDate = "";
    private String salModifiedDate = "";
    private String currencyTypeValue;
    private String curTypeValueForSalaryField;
    private String salFieldStringValue;

    // To get All Type of Salary List
    @SkipValidation
    public String getAllSalary() {
        salary = salService.getAllSalary();

        for (int i = 0; i < salary.size(); i++) {
            salFieldStringValue = salary.get(i).getSalary().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForSalaryField = currencyTypeValue + "   " + salFieldStringValue;
                salary.get(i).setCurTypeValueForSalaryField(curTypeValueForSalaryField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForSalaryField = currencyTypeValue + "   " + salFieldStringValue;
                salary.get(i).setCurTypeValueForSalaryField(curTypeValueForSalaryField);
            }
        }
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String salarySearchForm() {
        currencyTypeValue = currencyService.getCurrencyType();
        if (currencyTypeValue == null) {
            currencyTypeValue = "";
        } else {
            currencyTypeValue = "(" + currencyTypeValue + ")";
        }
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String salarySearchResult() {
        salary = salService.salarySearchResult(sal);
        if (sal.getMessage() != null) {
            if (sal.getMessage().contains(getText("label.common.search.messageSet"))) {
                DateFormat formatter = new SimpleDateFormat(getText("label.date.simpleDateFormat"));
                formatter.setLenient(false);
                addActionMessage(getText("The Result is based on "
                    + formatter.format(sal.getDeclarationDate()) + "."));
            }
        }
        for (int i = 0; i < salary.size(); i++) {
            salFieldStringValue = salary.get(i).getSalary().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForSalaryField = currencyTypeValue + "   " + salFieldStringValue;
                salary.get(i).setCurTypeValueForSalaryField(curTypeValueForSalaryField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForSalaryField = currencyTypeValue + "   " + salFieldStringValue;
                salary.get(i).setCurTypeValueForSalaryField(curTypeValueForSalaryField);
            }
        }
        return SUCCESS;
    }

    // Based on EmployeeId get All Salary of that Employee
    @SkipValidation
    public String getEmployeeAllSalary() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("sal.empIdObj.employeeId"));
        if (employeeId == null) {
            employeeId = sal.getEmpIdObj().getEmployeeId();
        }
        salary = salService.getEmployeeAllSalary(employeeId);
        for (int i = 0; i < salary.size(); i++) {
            salFieldStringValue = salary.get(i).getSalary().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForSalaryField = currencyTypeValue + "   " + salFieldStringValue;
                salary.get(i).setCurTypeValueForSalaryField(curTypeValueForSalaryField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForSalaryField = currencyTypeValue + "   " + salFieldStringValue;
                salary.get(i).setCurTypeValueForSalaryField(curTypeValueForSalaryField);
            }
        }
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // OrganizationType it shows blank Form to enter New Data
    @SkipValidation
    public String setUpSalary() {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");

        if ((sal != null) && (sal.getHcmosalaryId() != null)) {
            sal = salService.getSalary(sal.getHcmosalaryId());
        }
        currencyTypeValue = currencyService.getCurrencyType();
        if (currencyTypeValue == null) {
            currencyTypeValue = "";
            session.put("CURRENCY_TYPE_VALUE", currencyTypeValue);
        } else {
            currencyTypeValue = "(" + currencyTypeValue + ")";
            session.put("CURRENCY_TYPE_VALUE", currencyTypeValue);
        }
        return SUCCESS;

    }

    // To get Particular Salary Data of an Employee
    @SkipValidation
    public String salaryView() {
        if ((sal != null) && (sal.getHcmosalaryId() != null)) {
            sal = salService.getSalary(sal.getHcmosalaryId());
            (sal.getSalary()).toString();
            DecimalFormat f = new DecimalFormat(".00");
            String newSalary1 = f.format(sal.getSalary());
            sal.setnewSalary1(newSalary1);

            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
            }
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new Salary for that Employee or Update data of
    // the Salary
    @SkipValidation
    public String setUpEmpSalary() {
        Map session = ActionContext.getContext().getSession();
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("sal.empIdObj.employeeId"));
        if ((sal != null) && (sal.getHcmosalaryId() != null) && (employeeId != null)) {
            sal = salService.getSalary(sal.getHcmosalaryId());
        }
        currencyTypeValue = currencyService.getCurrencyType();
        if (currencyTypeValue == null) {
            currencyTypeValue = "";
            session.put("CURRENCY_TYPE_VALUE", currencyTypeValue);
        } else {
            currencyTypeValue = "(" + currencyTypeValue + ")";
            session.put("CURRENCY_TYPE_VALUE", currencyTypeValue);
        }
        return SUCCESS;
    }

    // Based on EmployeeId Insert new Salary for that Employee or Update data of
    // the Salary
    @SkipValidation
    public String setUpEmpSalarySingle() {
        Map session = ActionContext.getContext().getSession();
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("sal.empIdObj.employeeId"));
        Integer.valueOf(ServletActionContext.getRequest().getParameter("sal.hcmosalaryId"));
        if ((sal != null) && (sal.getHcmosalaryId() != null) && (employeeId != null)) {
            sal = salService.getEmpSalary(sal);
        }
        currencyTypeValue = currencyService.getCurrencyType();
        if (currencyTypeValue == null) {
            currencyTypeValue = "";
            session.put("CURRENCY_TYPE_VALUE", currencyTypeValue);
        } else {
            currencyTypeValue = "(" + currencyTypeValue + ")";
            session.put("CURRENCY_TYPE_VALUE", currencyTypeValue);
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new Salary or update
    // particular Salary Data
    public String insertOrUpdateSalary() {
        if (sal.getHcmosalaryId() == null) {
            EmployeesVO newAdminEmp = null;
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            sal.setCreated(DateUtils.getCurrentDateTime());
            sal.setCreatedBy(oEmp);
            sal.setUpdatedBy(oEmp);
            sal.setIsActive(1);
            salService.insertSalary(sal);

            sal = salService.getSalary(sal.getHcmosalaryId());
            role = roleService.getRoleName(getText("message.label.common.adminName"));

            int sessionEmpId = oEmp.getEmployeeId();
            int employeeID = sal.getEmpIdObj().getEmployeeId();

            // Retrieved the Many more Admin employee list
            List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
            int lengthForAdminEmpList = adminRoleId.size();
            String sSubject = getText("message.subject.salary.add");

            Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                newAdminEmp = it.next();
                int adminEmpID = newAdminEmp.getEmployeeId();

                // logged in person is admin and he is entering his own
                // information
                if (adminEmpID == sessionEmpId) {
                    if (adminEmpID == employeeID) {
                        // the mail content to Other admin Employees
                        if (sessionEmpId != adminEmpID) {
                            // the mail content to Other admin Employees
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("salary.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // the mail content to login admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("salary.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                    }
                    // logged in as admin and he is entering for another
                    // employee
                    else if (adminEmpID != employeeID) {
                        if (sessionEmpId != adminEmpID) {
                            // Mail to other admin employees
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("salary.add.addedToAdmin"), sal.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // Mail to the employee
                        mail(sal.getEmpIdObj().getEmployeeId(), sal.getEmpIdObj().getEmpFirstName(), getText("salary.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // Mail to the login_Admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("salary.add.addedBy"), sal.getEmpIdObj().getEmpFirstName(), sSubject);
                        }
                    }
                }
                // logged in person is not admin
                else if (adminEmpID != sessionEmpId) {
                    // the mail content to that perticular person
                    // if he is entering his own information
                    if (sessionEmpId == employeeID) {
                        // first mail - admin has to get mail
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("salary.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - that employee has to get mail
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("salary.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                    // if this employee is enterinf information for some other
                    // employee
                    else if (sessionEmpId != employeeID) {
                        // first mail - to admin
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("salary.add.addedToAdmin"), sal.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        // Neglect repeated mail to that particular employee
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - to logged in employee
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("salary.add.addedBy"), sal.getEmpIdObj().getEmpFirstName(), sSubject);
                        // third mail -to the person whom he is adding the info
                        mail(sal.getEmpIdObj().getEmployeeId(), sal.getEmpIdObj().getEmpFirstName(), getText("salary.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
            addActionMessage(getText("Added Successfully"));
        } else {
            EmployeesVO newAdminEmp = null;
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            sal.setUpdatedBy(oEmp);
            salService.updateSalary(sal);

            sal = salService.getSalary(sal.getHcmosalaryId());
            role = roleService.getRoleName(getText("message.label.common.adminName"));

            int sessionEmpId = oEmp.getEmployeeId();
            int employeeID = sal.getEmpIdObj().getEmployeeId();
            String sSubject = getText("message.subject.salary.edit");

            // Retrieved the Many more Admin employee list
            List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
            int lengthForAdminEmpList = adminRoleId.size();

            Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                newAdminEmp = it.next();
                int adminEmpID = newAdminEmp.getEmployeeId();

                // logged in person is admin and he is entering his own
                // information
                if (adminEmpID == sessionEmpId) {
                    if (adminEmpID == employeeID) {
                        // the mail content to Other admin Employees
                        if (sessionEmpId != adminEmpID) {
                            // the mail content to Other admin Employees
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("salary.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // the mail content to login admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("salary.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                    }
                    // logged in as admin and he is entering for another
                    // employee
                    else if (adminEmpID != employeeID) {
                        if (sessionEmpId != adminEmpID) {
                            // Mail to other admin employees
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("salary.edit.updatedToAdmin"), sal.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // Mail to the employee
                        mail(sal.getEmpIdObj().getEmployeeId(), sal.getEmpIdObj().getEmpFirstName(), getText("salary.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // Mail to the login_Admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("salary.edit.updatedBy"), sal.getEmpIdObj().getEmpFirstName(), sSubject);
                        }
                    }
                }
                // logged in person is not admin
                else if (adminEmpID != sessionEmpId) {
                    // the mail content to that perticular person
                    // if he is entering his own information
                    if (sessionEmpId == employeeID) {
                        // first mail - admin has to get mail
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("salary.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - that employee has to get mail
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("salary.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                    // if this employee is enterinf information for some other
                    // employee
                    else if (sessionEmpId != employeeID) {
                        // first mail - to admin
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("salary.edit.updatedToAdmin"), sal.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        // Neglect repeated mail to that particular employee
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - to logged in employee
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("salary.edit.updatedBy"), sal.getEmpIdObj().getEmpFirstName(), sSubject);
                        // third mail -to the person whom he is adding the info
                        mail(sal.getEmpIdObj().getEmployeeId(), sal.getEmpIdObj().getEmpFirstName(), getText("salary.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
            addActionMessage(getText("Updated Successfully"));
        }
        // }
        return SUCCESS;
    }

    // To delete particular Salary data of an employee
    @SkipValidation
    public String deleteSalary() {
        EmployeesVO newAdminEmp = null;
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        sal.setUpdatedBy(oEmp);
        salService.deleteSalary(sal);

        sal = salService.getSalary(sal.getHcmosalaryId());
        role = roleService.getRoleName(getText("message.label.common.adminName"));

        int sessionEmpId = oEmp.getEmployeeId();
        int employeeID = sal.getEmpIdObj().getEmployeeId();
        String sSubject = getText("message.subject.salary.delete");

        // Retrieved the Many more Admin employee list
        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
        int lengthForAdminEmpList = adminRoleId.size();

        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
            newAdminEmp = it.next();
            int adminEmpID = newAdminEmp.getEmployeeId();

            // logged in person is admin and he is entering his own information
            if (adminEmpID == sessionEmpId) {
                if (adminEmpID == employeeID) {
                    // the mail content to Other admin Employees
                    if (sessionEmpId != adminEmpID) {
                        // the mail content to Other admin Employees
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("salary.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("salary.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                }
                // logged in as admin and he is entering for another employee
                else if (adminEmpID != employeeID) {
                    if (sessionEmpId != adminEmpID) {
                        // Mail to other admin employees
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("salary.delete.deletedToAdmin"), sal.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to the employee
                    mail(sal.getEmpIdObj().getEmployeeId(), sal.getEmpIdObj().getEmpFirstName(), getText("salary.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // Mail to the login_Admin
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("salary.delete.deletedBy"), sal.getEmpIdObj().getEmpFirstName(), sSubject);
                    }
                }
            }
            // logged in person is not admin
            else if (adminEmpID != sessionEmpId) {
                // the mail content to that perticular person
                // if he is entering his own information
                if (sessionEmpId == employeeID) {
                    // first mail - admin has to get mail
                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("salary.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - that employee has to get mail
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("salary.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                }
                // if this employee is enterinf information for some other
                // employee
                else if (sessionEmpId != employeeID) {
                    // first mail - to admin
                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("salary.delete.deletedToAdmin"), sal.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                    // Neglect repeated mail to that particular employee
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // second mail - to logged in employee
                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("salary.delete.deletedBy"), sal.getEmpIdObj().getEmpFirstName(), sSubject);
                    // third mail -to the person whom he is adding the info
                    mail(sal.getEmpIdObj().getEmployeeId(), sal.getEmpIdObj().getEmpFirstName(), getText("salary.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                }
            }
        }
        addActionMessage(getText("Deleted Successfully"));
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat UpdatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            Date salaryDeclDate = sal.getDeclarationDate();
            salDecDate = DateFormat.format(salaryDeclDate);
            Date salaryModdate = sal.getUpdated();
            salModifiedDate = UpdatedDateFormat.format(salaryModdate);

            DecimalFormat f = new DecimalFormat(".00");
            String salFieldStringValue = f.format(sal.getSalary());
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                setCurrencyTypeValue(currencyTypeValue);
                curTypeValueForSalaryField = currencyTypeValue + "   " + salFieldStringValue;
                sal.setCurTypeValueForSalaryField(curTypeValueForSalaryField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                setCurrencyTypeValue(currencyTypeValue);
                curTypeValueForSalaryField = currencyTypeValue + "   " + salFieldStringValue;
                sal.setCurTypeValueForSalaryField(curTypeValueForSalaryField);
            }

            String sDummy = Constants.PERSON;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
            sMessage.replace(sMessage.lastIndexOf(sDummy), sMessage.lastIndexOf(sDummy)
                + sDummy.length(), From);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.salary.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + sal.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.salary.name") + " " + getCurrencyTypeValue()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + sal.getCurTypeValueForSalaryField()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.salary.decDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + salDecDate
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + salModifiedDate
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

            DateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat UpdatedDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            Date salaryDeclDate = sal.getDeclarationDate();
            salDecDate = DateFormat.format(salaryDeclDate);
            Date salaryModdate = sal.getUpdated();
            salModifiedDate = UpdatedDateFormat.format(salaryModdate);

            DecimalFormat f = new DecimalFormat(".00");
            String salFieldStringValue = f.format(sal.getSalary());
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                setCurrencyTypeValue(currencyTypeValue);
                curTypeValueForSalaryField = currencyTypeValue + "   " + salFieldStringValue;
                sal.setCurTypeValueForSalaryField(curTypeValueForSalaryField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                setCurrencyTypeValue(currencyTypeValue);
                curTypeValueForSalaryField = currencyTypeValue + "   " + salFieldStringValue;
                sal.setCurTypeValueForSalaryField(curTypeValueForSalaryField);
            }

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.EMPLOYEE_PERSON;
            String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
            sMessage.replace(sMessage.indexOf(sFirstPerson), sMessage.indexOf(sFirstPerson)
                + sFirstPerson.length(), From);
            sMessage.replace(sMessage.indexOf(sLoggedInPerson), sMessage.indexOf(sLoggedInPerson)
                + sLoggedInPerson.length(), LoggedIn);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.salary.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + sal.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.salary.name") + " " + getCurrencyTypeValue()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + sal.getCurTypeValueForSalaryField()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.salary.decDate") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON + salDecDate
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + salModifiedDate
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

    public List<SalaryVO> getSalary() {
        return salary;
    }

    public void setSalary(List<SalaryVO> salary) {
        this.salary = salary;
    }

    public SalaryVO getSal() {
        return sal;
    }

    public void setSal(SalaryVO sal) {
        this.sal = sal;
    }

    public EmployeesVO getEmps() {
        return emps;
    }

    public void setEmps(EmployeesVO emps) {
        this.emps = emps;
    }

    public void setCurrency(List<CurrencyVO> currency) {
        this.currency = currency;
    }

    public List<CurrencyVO> getCurrency() {
        return currency;
    }

    public void setCurrencyTypeValue(String currencyTypeValue) {
        this.currencyTypeValue = currencyTypeValue;
    }

    public String getCurrencyTypeValue() {
        return currencyTypeValue;
    }

    public void setCurTypeValueForSalaryField(String curTypeValueForSalaryField) {
        this.curTypeValueForSalaryField = curTypeValueForSalaryField;
    }

    public String getCurTypeValueForSalaryField() {
        return curTypeValueForSalaryField;
    }

    public void setSalFieldStringValue(String salFieldStringValue) {
        this.salFieldStringValue = salFieldStringValue;
    }

    public String getSalFieldStringValue() {
        return salFieldStringValue;
    }

    public void setCurForsalary(List<SalaryVO> curForsalary) {
        this.curForsalary = curForsalary;
    }

    public List<SalaryVO> getCurForsalary() {
        return curForsalary;
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
