
package com.gits.rms.action;

import java.text.DateFormat;
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
import com.gits.rms.service.DirectDebitDaoService;
import com.gits.rms.service.DirectDebitService;
import com.gits.rms.service.EmployeeReportToDaoService;
import com.gits.rms.service.EmployeeReportToService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.DirectDebitVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class DirectDebitAction extends ActionSupport {
    private static final long serialVersionUID = 5920774372058083486L;
    private DirectDebitService directDebitService = new DirectDebitDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private RoleService roleService = new RoleDaoService();
    private CurrencyService currencyService = new CurrencyDaoService();
    private EmployeeReportToService employeeReportToService = new EmployeeReportToDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private List<DirectDebitVO> dirDebitList;
    private DirectDebitVO dirDebit;
    private RoleVO role;
    private DirectDebitVO directDebit;
    private List<EmployeesVO> empIdObj;
    private List<EmployeesVO> adminRoleId;
    private EmployeesVO emps;
    private String directModifiedDate = "";
    private String currencyTypeValue;
    private String amountFieldStringValue;
    private String curTypeValueForAmountField;

    // To get List of DirectDebit
    @SkipValidation
    public String getAllDirectDebit() {
       
        Map session = ActionContext.getContext().getSession();
    	EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
    	List<Integer> employeeReportToList;
    	
    	//checking whether the login person is a Report To Authority
    	boolean reportTo = employeeReportToService.checkLoginEmployeeIsReportToEmp(oEmp.getEmployeeId());
    	
    	if(reportTo){
    		employeeReportToList = employeeReportToService.getSubEmployeeList(oEmp.getEmployeeId());
    		
    		dirDebitList = directDebitService.getAllSubEmployeeDirectDebitList(employeeReportToList);
    	}else{
    		 dirDebitList = directDebitService.getAllDirectDebit();
    	}
        
        for (Iterator<DirectDebitVO> it = dirDebitList.iterator(); it.hasNext();) {
            setPreferrdAccountValue(it.next());
        }
        for (int i = 0; i < dirDebitList.size(); i++) {
            amountFieldStringValue = dirDebitList.get(i).getAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                dirDebitList.get(i).setCurTypeValueForAmountField(curTypeValueForAmountField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                dirDebitList.get(i).setCurTypeValueForAmountField(curTypeValueForAmountField);
            }
        }
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String directDebitSearchForm() {
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
    public String directDebitSearchResult() {
        dirDebitList = directDebitService.directDebitSearchResult(dirDebit);
        for (int i = 0; i < dirDebitList.size(); i++) {
            amountFieldStringValue = dirDebitList.get(i).getAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                dirDebitList.get(i).setCurTypeValueForAmountField(curTypeValueForAmountField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                dirDebitList.get(i).setCurTypeValueForAmountField(curTypeValueForAmountField);
            }
        }
        return SUCCESS;
    }

    // Based on EmployeeId get All DirectDebit of that Employee
    @SkipValidation
    public String getEmployeeAllDirectDebit() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("dirDebit.empIdObj.employeeId"));
        if (employeeId == null) {
            employeeId = dirDebit.getEmpIdObj().getEmployeeId();
        }
        dirDebitList = directDebitService.getEmployeeAllDirectDebit(employeeId);
        for (Iterator<DirectDebitVO> it = dirDebitList.iterator(); it.hasNext();) {
            setPreferrdAccountValue(it.next());
        }
        for (int i = 0; i < dirDebitList.size(); i++) {
            amountFieldStringValue = dirDebitList.get(i).getAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                dirDebitList.get(i).setCurTypeValueForAmountField(curTypeValueForAmountField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                dirDebitList.get(i).setCurTypeValueForAmountField(curTypeValueForAmountField);
            }
        }
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // DirectDebit it shows blank Form to enter New Data
    @SkipValidation
    public String setUpDirectDebit() {
        Map session = ActionContext.getContext().getSession();
        if ((dirDebit != null) && (dirDebit.getEmpDirectDebitId() != null)) {
            dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
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

    // Based on EmployeeId Insert new DirecrDebit for that Employee or Update
    // data of the DirectDebit
    @SkipValidation
    public String setUpEmpDirectDebit() {
        Map session = ActionContext.getContext().getSession();
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("dirDebit.empIdObj.employeeId"));
        if ((dirDebit != null) && (dirDebit.getEmpDirectDebitId() != null) && (employeeId != null)) {
            dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
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

    // Based on EmployeeId Insert new DirecrDebit for that Employee or Update
    // data of the DirecrDebit
    @SkipValidation
    public String setUpEmpDirectDebitSingle() {
        Map session = ActionContext.getContext().getSession();
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("dirDebit.empIdObj.employeeId"));
        Integer.valueOf(ServletActionContext.getRequest().getParameter("dirDebit.empEducationId"));
        if ((dirDebit != null) && (dirDebit.getEmpDirectDebitId() != null) && (employeeId != null)) {
            dirDebit = directDebitService.getEmpDirectDebit(dirDebit);
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

    private void setUpDirectDebit(Integer empDirectDebitId) {
        dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
    }

    // To get Particular DirectDebit Data
    @SkipValidation
    public String directDebitView() {

        if ((dirDebit != null) && (dirDebit.getEmpDirectDebitId() != null)) {
            dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
            setPreferrdAccountValue(dirDebit);
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
            }
        }
        return SUCCESS;
    }

    // In the New Form when click Submit button To insert new DirectDebit or
    // update particular DirectDebit Data
    public String insertOrUpdateDirectDebit() {
        try {
            if (dirDebit.getEmpDirectDebitId() == null) {
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                dirDebitList = directDebitService.getEmployeeAllDirectDebit(dirDebit.getEmpIdObj().getEmployeeId());
                if (dirDebit.isPreAccount() == false) {
                    new DirectDebitVO();
                    if (dirDebitList.isEmpty() == true) {
                        dirDebit.setCreated(DateUtils.getCurrentDateTime());
                        dirDebit.setCreatedBy(oEmp);
                        dirDebit.setUpdatedBy(oEmp);
                        dirDebit.setIsActive(1);
                        dirDebit.setPreAccount(true);
                        directDebitService.insertDirectDebit(dirDebit);

                        dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
                        role = roleService.getRoleName(getText("message.label.common.adminName"));

                        int sessionEmpId = oEmp.getEmployeeId();
                        int employeeID = dirDebit.getEmpIdObj().getEmployeeId();
                        String sSubject = getText("message.subject.directDebit.add");

                        // Retrieved the Many more Admin employee list
                        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                        int lengthForAdminEmpList = adminRoleId.size();

                        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                            newAdminEmp = it.next();
                            int adminEmpID = newAdminEmp.getEmployeeId();

                            // logged in person is admin and he is entering his
                            // own information
                            if (adminEmpID == sessionEmpId) {
                                if (adminEmpID == employeeID) {
                                    // the mail content to Other admin Employees
                                    if (sessionEmpId != adminEmpID) {
                                        // the mail content to Other admin
                                        // Employees
                                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // the mail content to login admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                    }
                                }
                                // logged in as admin and he is entering for
                                // another employee
                                else if (adminEmpID != employeeID) {
                                    if (sessionEmpId != adminEmpID) {
                                        // Mail to other admin employees
                                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // Mail to the employee
                                    mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // Mail to the login_Admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                    }
                                }
                            }
                            // logged in person is not admin
                            else if (adminEmpID != sessionEmpId) {
                                // the mail content to that perticular person
                                // if he is entering his own information
                                if (sessionEmpId == employeeID) {
                                    // first mail - admin has to get mail
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - that employee has to get
                                    // mail
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                                // if this employee is enterinf information for
                                // some other employee
                                else if (sessionEmpId != employeeID) {
                                    // first mail - to admin
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    // Neglect repeated mail to that particular
                                    // employee
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - to logged in employee
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                    // third mail -to the person whom he is
                                    // adding the info
                                    mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        session.put("HELP_INFORMATION_MESSAGE", getText("Your first Direct Debit Account will be made as Preferred Account automatically"));
                        addActionMessage(getText("Added Successfully"));
                        return SUCCESS;
                    } else if (dirDebitList.isEmpty() == false) {
                        dirDebit.setCreated(DateUtils.getCurrentDateTime());
                        dirDebit.setCreatedBy(oEmp);
                        dirDebit.setUpdatedBy(oEmp);
                        dirDebit.setIsActive(1);
                        directDebitService.insertDirectDebit(dirDebit);

                        dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
                        role = roleService.getRoleName(getText("message.label.common.adminName"));

                        int sessionEmpId = oEmp.getEmployeeId();
                        int employeeID = dirDebit.getEmpIdObj().getEmployeeId();
                        String sSubject = getText("message.subject.directDebit.add");

                        // Retrieved the Many more Admin employee list
                        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                        int lengthForAdminEmpList = adminRoleId.size();

                        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                            newAdminEmp = it.next();
                            int adminEmpID = newAdminEmp.getEmployeeId();

                            // logged in person is admin and he is entering his
                            // own information
                            if (adminEmpID == sessionEmpId) {
                                if (adminEmpID == employeeID) {
                                    // the mail content to Other admin Employees
                                    if (sessionEmpId != adminEmpID) {
                                        // the mail content to Other admin
                                        // Employees
                                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // the mail content to login admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                    }
                                }
                                // logged in as admin and he is entering for
                                // another employee
                                else if (adminEmpID != employeeID) {
                                    if (sessionEmpId != adminEmpID) {
                                        // Mail to other admin employees
                                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // Mail to the employee
                                    mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // Mail to the login_Admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                    }
                                }
                            }
                            // logged in person is not admin
                            else if (adminEmpID != sessionEmpId) {
                                // the mail content to that perticular person
                                // if he is entering his own information
                                if (sessionEmpId == employeeID) {
                                    // first mail - admin has to get mail
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - that employee has to get
                                    // mail
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                                // if this employee is enterinf information for
                                // some other employee
                                else if (sessionEmpId != employeeID) {
                                    // first mail - to admin
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    // Neglect repeated mail to that particular
                                    // employee
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - to logged in employee
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                    // third mail -to the person whom he is
                                    // adding the info
                                    mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        addActionMessage(getText("Added Successfully"));
                        return SUCCESS;
                    }
                } else if (dirDebit.isPreAccount() == true) {
                    DirectDebitVO newDirect = null;
                    if (dirDebitList.isEmpty() == false) {
                        for (Iterator<DirectDebitVO> it = dirDebitList.iterator(); it.hasNext();) {
                            newDirect = it.next();

                            if (newDirect.isPreAccount() == true) {
                                newDirect.setPreAccount(false);
                                newDirect.setUpdatedBy(oEmp);
                                directDebitService.updateDirectDebit(newDirect);
                            }
                        }
                        dirDebit.setCreated(DateUtils.getCurrentDateTime());
                        dirDebit.setCreatedBy(oEmp);
                        dirDebit.setUpdatedBy(oEmp);
                        dirDebit.setIsActive(1);
                        directDebitService.insertDirectDebit(dirDebit);

                        dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
                        role = roleService.getRoleName(getText("message.label.common.adminName"));

                        int sessionEmpId = oEmp.getEmployeeId();
                        int employeeID = dirDebit.getEmpIdObj().getEmployeeId();
                        String sSubject = getText("message.subject.directDebit.add");

                        // Retrieved the Many more Admin employee list
                        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                        int lengthForAdminEmpList = adminRoleId.size();

                        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                            newAdminEmp = it.next();
                            int adminEmpID = newAdminEmp.getEmployeeId();

                            // logged in person is admin and he is entering his
                            // own information
                            if (adminEmpID == sessionEmpId) {
                                if (adminEmpID == employeeID) {
                                    // the mail content to Other admin Employees
                                    if (sessionEmpId != adminEmpID) {
                                        // the mail content to Other admin
                                        // Employees
                                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // the mail content to login admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                    }
                                }
                                // logged in as admin and he is entering for
                                // another employee
                                else if (adminEmpID != employeeID) {
                                    if (sessionEmpId != adminEmpID) {
                                        // Mail to other admin employees
                                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // Mail to the employee
                                    mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // Mail to the login_Admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                    }
                                }
                            }
                            // logged in person is not admin
                            else if (adminEmpID != sessionEmpId) {
                                // the mail content to that perticular person
                                // if he is entering his own information
                                if (sessionEmpId == employeeID) {
                                    // first mail - admin has to get mail
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - that employee has to get
                                    // mail
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                                // if this employee is enterinf information for
                                // some other employee
                                else if (sessionEmpId != employeeID) {
                                    // first mail - to admin
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    // Neglect repeated mail to that particular
                                    // employee
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - to logged in employee
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                    // third mail -to the person whom he is
                                    // adding the info
                                    mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        session.put("HELP_INFORMATION_MESSAGE", getText("Your Previous Preferred Account has been made as Non- Preferred Account"));
                        addActionMessage(getText("Added Successfully"));
                        return SUCCESS;
                    } else if (dirDebitList.isEmpty() == true) {
                        dirDebit.setCreated(DateUtils.getCurrentDateTime());
                        dirDebit.setCreatedBy(oEmp);
                        dirDebit.setUpdatedBy(oEmp);
                        dirDebit.setIsActive(1);
                        directDebitService.insertDirectDebit(dirDebit);

                        dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
                        role = roleService.getRoleName(getText("message.label.common.adminName"));

                        int sessionEmpId = oEmp.getEmployeeId();
                        int employeeID = dirDebit.getEmpIdObj().getEmployeeId();
                        String sSubject = getText("message.subject.directDebit.add");

                        // Retrieved the Many more Admin employee list
                        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                        int lengthForAdminEmpList = adminRoleId.size();

                        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                            newAdminEmp = it.next();
                            int adminEmpID = newAdminEmp.getEmployeeId();

                            // logged in person is admin and he is entering his
                            // own information
                            if (adminEmpID == sessionEmpId) {
                                if (adminEmpID == employeeID) {
                                    // the mail content to Other admin Employees
                                    if (sessionEmpId != adminEmpID) {
                                        // the mail content to Other admin
                                        // Employees
                                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // the mail content to login admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                    }
                                }
                                // logged in as admin and he is entering for
                                // another employee
                                else if (adminEmpID != employeeID) {
                                    if (sessionEmpId != adminEmpID) {
                                        // Mail to other admin employees
                                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // Mail to the employee
                                    mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // Mail to the login_Admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                    }
                                }
                            }
                            // logged in person is not admin
                            else if (adminEmpID != sessionEmpId) {
                                // the mail content to that perticular person
                                // if he is entering his own information
                                if (sessionEmpId == employeeID) {
                                    // first mail - admin has to get mail
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - that employee has to get
                                    // mail
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                                // if this employee is enterinf information for
                                // some other employee
                                else if (sessionEmpId != employeeID) {
                                    // first mail - to admin
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.add.addedToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    // Neglect repeated mail to that particular
                                    // employee
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - to logged in employee
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.add.addedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                    // third mail -to the person whom he is
                                    // adding the info
                                    mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.add.addTo"), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        addActionMessage(getText("Added Successfully"));
                        return SUCCESS;
                    }
                }
            } else {
                EmployeesVO newAdminEmp = null;
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                dirDebit.setUpdatedBy(oEmp);

                if (dirDebit.isPreAccount() == true) {
                    dirDebitList = directDebitService.getAllDirectDebitForAEmp(dirDebit);
                    if (dirDebitList.isEmpty() == false) {
                        directDebitService.updateDirectDebit(dirDebit);
                        for (Iterator<DirectDebitVO> it = dirDebitList.iterator(); it.hasNext();) {
                            dirDebit = it.next();
                            if (dirDebit.isPreAccount() == true) {
                                dirDebit.setPreAccount(false);
                                directDebitService.updateDirectDebit(dirDebit);
                            }
                        }
                        directDebitService.updateDirectDebit(dirDebit);
                        dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
                        role = roleService.getRoleName(getText("message.label.common.adminName"));

                        int sessionEmpId = oEmp.getEmployeeId();
                        int employeeID = dirDebit.getEmpIdObj().getEmployeeId();
                        String sSubject = getText("message.subject.directDebit.edit");

                        // Retrieved the Many more Admin employee list
                        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                        int lengthForAdminEmpList = adminRoleId.size();

                        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                            newAdminEmp = it.next();
                            int adminEmpID = newAdminEmp.getEmployeeId();

                            // logged in person is admin and he is entering his
                            // own information
                            if (adminEmpID == sessionEmpId) {
                                if (adminEmpID == employeeID) {
                                    // the mail content to Other admin Employees
                                    if (sessionEmpId != adminEmpID) {
                                        // the mail content to Other admin
                                        // Employees
                                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // the mail content to login admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                    }
                                }
                                // logged in as admin and he is entering for
                                // another employee
                                else if (adminEmpID != employeeID) {
                                    if (sessionEmpId != adminEmpID) {
                                        // Mail to other admin employees
                                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updateToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // Mail to the employee
                                    mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // Mail to the login_Admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                    }
                                }
                            }
                            // logged in person is not admin
                            else if (adminEmpID != sessionEmpId) {
                                // the mail content to that perticular person
                                // if he is entering his own information
                                if (sessionEmpId == employeeID) {
                                    // first mail - admin has to get mail
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - that employee has to get
                                    // mail
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                                // if this employee is enterinf information for
                                // some other employee
                                else if (sessionEmpId != employeeID) {
                                    // first mail - to admin
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updateToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    // Neglect repeated mail to that particular
                                    // employee
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - to logged in employee
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                    // third mail -to the person whom he is
                                    // adding the info
                                    mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        addActionMessage(getText("Updated Successfully"));
                        return SUCCESS;
                    } else if (dirDebitList.isEmpty() == true) {

                        directDebitService.updateDirectDebit(dirDebit);
                        dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
                        role = roleService.getRoleName(getText("message.label.common.adminName"));

                        int sessionEmpId = oEmp.getEmployeeId();
                        int employeeID = dirDebit.getEmpIdObj().getEmployeeId();
                        String sSubject = getText("message.subject.directDebit.edit");

                        // Retrieved the Many more Admin employee list
                        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                        int lengthForAdminEmpList = adminRoleId.size();

                        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                            newAdminEmp = it.next();
                            int adminEmpID = newAdminEmp.getEmployeeId();

                            // logged in person is admin and he is entering his
                            // own information
                            if (adminEmpID == sessionEmpId) {
                                if (adminEmpID == employeeID) {
                                    // the mail content to Other admin Employees
                                    if (sessionEmpId != adminEmpID) {
                                        // the mail content to Other admin
                                        // Employees
                                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // the mail content to login admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                    }
                                }
                                // logged in as admin and he is entering for
                                // another employee
                                else if (adminEmpID != employeeID) {
                                    if (sessionEmpId != adminEmpID) {
                                        // Mail to other admin employees
                                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updateToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    }
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // Mail to the employee
                                    mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                    // If Login admin is same as one of the
                                    // admin Employee List
                                    if (sessionEmpId == adminEmpID) {
                                        // Mail to the login_Admin
                                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                    }
                                }
                            }
                            // logged in person is not admin
                            else if (adminEmpID != sessionEmpId) {
                                // the mail content to that perticular person
                                // if he is entering his own information
                                if (sessionEmpId == employeeID) {
                                    // first mail - admin has to get mail
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - that employee has to get
                                    // mail
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                                // if this employee is enterinf information for
                                // some other employee
                                else if (sessionEmpId != employeeID) {
                                    // first mail - to admin
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updateToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                    // Neglect repeated mail to that particular
                                    // employee
                                    lengthForAdminEmpList--;
                                    if (lengthForAdminEmpList != 0) {
                                        continue Loop;
                                    }
                                    // second mail - to logged in employee
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                    // third mail -to the person whom he is
                                    // adding the info
                                    mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        addActionMessage(getText("Updated Successfully"));
                        return SUCCESS;
                    }
                    directDebitService.updateDirectDebit(dirDebit);
                    dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));

                    int sessionEmpId = oEmp.getEmployeeId();
                    int employeeID = dirDebit.getEmpIdObj().getEmployeeId();
                    String sSubject = getText("message.subject.directDebit.edit");

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
                                    mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                }
                            }
                            // logged in as admin and he is entering for another
                            // employee
                            else if (adminEmpID != employeeID) {
                                if (sessionEmpId != adminEmpID) {
                                    // Mail to other admin employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updateToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to the employee
                                mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // Mail to the login_Admin
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                }
                            }
                        }
                        // logged in person is not admin
                        else if (adminEmpID != sessionEmpId) {
                            // the mail content to that perticular person
                            // if he is entering his own information
                            if (sessionEmpId == employeeID) {
                                // first mail - admin has to get mail
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - that employee has to get mail
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                            // if this employee is enterinf information for some
                            // other employee
                            else if (sessionEmpId != employeeID) {
                                // first mail - to admin
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updateToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                // Neglect repeated mail to that particular
                                // employee
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // second mail - to logged in employee
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                // third mail -to the person whom he is adding
                                // the info
                                mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    addActionMessage(getText("Updated Successfully"));
                    return SUCCESS;
                } else if (dirDebit.isPreAccount() == false) {
                    dirDebitList = directDebitService.getAllDirectDebitForAEmp(dirDebit);
                    if (dirDebitList.isEmpty() == false) {
                        for (Iterator<DirectDebitVO> it = dirDebitList.iterator(); it.hasNext();) {
                            directDebit = it.next();
                            if (directDebit.isPreAccount() == true) {

                                directDebitService.updateDirectDebit(dirDebit);
                                dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
                                role = roleService.getRoleName(getText("message.label.common.adminName"));

                                int sessionEmpId = oEmp.getEmployeeId();
                                int employeeID = dirDebit.getEmpIdObj().getEmployeeId();
                                String sSubject = getText("message.subject.directDebit.edit");

                                // Retrieved the Many more Admin employee list
                                List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                                int lengthForAdminEmpList = adminRoleId.size();

                                Loop: for (Iterator<EmployeesVO> itone = adminRoleId.iterator(); itone.hasNext();) {
                                    newAdminEmp = itone.next();
                                    int adminEmpID = newAdminEmp.getEmployeeId();

                                    // logged in person is admin and he is
                                    // entering his own information
                                    if (adminEmpID == sessionEmpId) {
                                        if (adminEmpID == employeeID) {
                                            // the mail content to Other admin
                                            // Employees
                                            if (sessionEmpId != adminEmpID) {
                                                // the mail content to Other
                                                // admin Employees
                                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                            }
                                            lengthForAdminEmpList--;
                                            if (lengthForAdminEmpList != 0) {
                                                continue Loop;
                                            }
                                            // If Login admin is same as one of
                                            // the admin Employee List
                                            if (sessionEmpId == adminEmpID) {
                                                // the mail content to login
                                                // admin
                                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                            }
                                        }
                                        // logged in as admin and he is entering
                                        // for another employee
                                        else if (adminEmpID != employeeID) {
                                            if (sessionEmpId != adminEmpID) {
                                                // Mail to other admin employees
                                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updateToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                            }
                                            lengthForAdminEmpList--;
                                            if (lengthForAdminEmpList != 0) {
                                                continue Loop;
                                            }
                                            // Mail to the employee
                                            mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                            // If Login admin is same as one of
                                            // the admin Employee List
                                            if (sessionEmpId == adminEmpID) {
                                                // Mail to the login_Admin
                                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                            }
                                        }
                                    }
                                    // logged in person is not admin
                                    else if (adminEmpID != sessionEmpId) {
                                        // the mail content to that perticular
                                        // person
                                        // if he is entering his own information
                                        if (sessionEmpId == employeeID) {
                                            // first mail - admin has to get
                                            // mail
                                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updatedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                                            lengthForAdminEmpList--;
                                            if (lengthForAdminEmpList != 0) {
                                                continue Loop;
                                            }
                                            // second mail - that employee has
                                            // to get mail
                                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedByOwn"), getText("message.common.myOwn.name"), sSubject);
                                        }
                                        // if this employee is enterinf
                                        // information for some other employee
                                        else if (sessionEmpId != employeeID) {
                                            // first mail - to admin
                                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.edit.updateToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                                            // Neglect repeated mail to that
                                            // particular employee
                                            lengthForAdminEmpList--;
                                            if (lengthForAdminEmpList != 0) {
                                                continue Loop;
                                            }
                                            // second mail - to logged in
                                            // employee
                                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.edit.updatedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                                            // third mail -to the person whom he
                                            // is adding the info
                                            mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.edit.updateTo"), oEmp.getEmpFirstName(), sSubject);
                                        }
                                    }
                                }
                                addActionMessage(getText("Updated Successfully"));
                                return SUCCESS;
                            }
                        }
                        setUpDirectDebit(dirDebit.getEmpDirectDebitId());
                        addActionError(getText("You cant update the only Preferred Account to Non-Preferred Account"));
                        dirDebit.setPreAccount(true);
                        return INPUT;
                    } else if (dirDebitList.isEmpty() == true) {
                        setUpDirectDebit(dirDebit.getEmpDirectDebitId());
                        addActionError(getText("You cant update the only Preferred Account to Non-Preferred Account"));
                        return INPUT;
                    }
                }
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    // To delete particular DirectDebit data
    @SkipValidation
    public String deleteDirectDebit() {
        EmployeesVO newAdminEmp = null;
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        dirDebit.setUpdatedBy(oEmp);
        setUpDirectDebit(dirDebit.getEmpDirectDebitId());

        dirDebitList = directDebitService.getAllDirectDebitForAEmp(dirDebit);
        if (dirDebitList.isEmpty() == true) {
            dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
            directDebitService.deleteDirectDebit(dirDebit);

            // dirDebit =
            // directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
            role = roleService.getRoleName(getText("message.label.common.adminName"));

            int sessionEmpId = oEmp.getEmployeeId();
            int employeeID = dirDebit.getEmpIdObj().getEmployeeId();
            String sSubject = getText("message.subject.directDebit.delete");

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
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // the mail content to login admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                    }
                    // logged in as admin and he is entering for another
                    // employee
                    else if (adminEmpID != employeeID) {
                        if (sessionEmpId != adminEmpID) {
                            // Mail to other admin employees
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.delete.deleteToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        }
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // Mail to the employee
                        mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                        // If Login admin is same as one of the admin Employee
                        // List
                        if (sessionEmpId == adminEmpID) {
                            // Mail to the login_Admin
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.delete.deletedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                        }
                    }
                }
                // logged in person is not admin
                else if (adminEmpID != sessionEmpId) {
                    // the mail content to that perticular person
                    // if he is entering his own information
                    if (sessionEmpId == employeeID) {
                        // first mail - admin has to get mail
                        mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - that employee has to get mail
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                    }
                    // if this employee is enterinf information for some other
                    // employee
                    else if (sessionEmpId != employeeID) {
                        // first mail - to admin
                        mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.delete.deleteToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                        // Neglect repeated mail to that particular employee
                        lengthForAdminEmpList--;
                        if (lengthForAdminEmpList != 0) {
                            continue Loop;
                        }
                        // second mail - to logged in employee
                        mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.delete.deletedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                        // third mail -to the person whom he is adding the info
                        mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
            addActionError(getText("You have deleted Only Preferred Account for ")
                + dirDebit.getEmpIdObj().getEmpFirstName() + getText(""));
            return ERROR;
        } else if (dirDebitList.isEmpty() == false) {
            if (dirDebit.isPreAccount() == true) {
                addActionError("Please Change Some other Account of this Employee as Preferred to delete this Account ");
                return ERROR;
            } else if (dirDebit.isPreAccount() == false) {
                dirDebit = directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
                directDebitService.deleteDirectDebit(dirDebit);

                // dirDebit =
                // directDebitService.getDirectDebit(dirDebit.getEmpDirectDebitId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));

                int sessionEmpId = oEmp.getEmployeeId();
                int employeeID = dirDebit.getEmpIdObj().getEmployeeId();
                String sSubject = getText("message.subject.directDebit.delete");

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
                                mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                            }
                        }
                        // logged in as admin and he is entering for another
                        // employee
                        else if (adminEmpID != employeeID) {
                            if (sessionEmpId != adminEmpID) {
                                // Mail to other admin employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.delete.deleteToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to the employee
                            mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // Mail to the login_Admin
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.delete.deletedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                            }
                        }
                    }
                    // logged in person is not admin
                    else if (adminEmpID != sessionEmpId) {
                        // the mail content to that perticular person
                        // if he is entering his own information
                        if (sessionEmpId == employeeID) {
                            // first mail - admin has to get mail
                            mail(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.delete.deletedByEmpToAdmin"), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - that employee has to get mail
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.delete.deletedByOwn"), getText("message.common.myOwn.name"), sSubject);
                        }
                        // if this employee is enterinf information for some
                        // other employee
                        else if (sessionEmpId != employeeID) {
                            // first mail - to admin
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("directDebit.delete.deleteToAdmin"), dirDebit.getEmpIdObj().getEmpFirstName(), oEmp.getEmpFirstName(), sSubject);
                            // Neglect repeated mail to that particular employee
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // second mail - to logged in employee
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("directDebit.delete.deletedBy"), dirDebit.getEmpIdObj().getEmpFirstName(), sSubject);
                            // third mail -to the person whom he is adding the
                            // info
                            mail(dirDebit.getEmpIdObj().getEmployeeId(), dirDebit.getEmpIdObj().getEmpFirstName(), getText("directDebit.delete.deleteTo"), oEmp.getEmpFirstName(), sSubject);
                        }
                    }
                }
                addActionMessage("Deleted Successfully");
            }
        }
        return SUCCESS;
    }

    @SkipValidation
    public String makePreferred() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        dirDebit.setUpdatedBy(oEmp);

        setUpDirectDebit(dirDebit.getEmpDirectDebitId());
        dirDebit.setPreAccount(true);
        directDebitService.updateDirectDebit(dirDebit);
        dirDebitList = directDebitService.getAllDirectDebitForAEmp(dirDebit);

        for (Iterator<DirectDebitVO> it = dirDebitList.iterator(); it.hasNext();) {
            dirDebit = it.next();

            if (dirDebit.isPreAccount() == true) {
                dirDebit.setPreAccount(false);
                dirDebit.setUpdatedBy(oEmp);
                directDebitService.updateDirectDebit(dirDebit);
            }
        }
        addActionMessage("Your Account has been made as Preferred Account");
        return SUCCESS;
    }

    // Convert Int to String for PreferredAccount Field
    public void setPreferrdAccountValue(DirectDebitVO debit) {
        debit.setPreAccountValue(debit.isPreAccount() == true ? getText("label.directDebit.length.value.preferred")
            : getText("label.directDebit.length.value.nonPreferred"));
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {

        try {
            setPreferrdAccountValue(dirDebit);
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date directdebitModdate = dirDebit.getUpdated();
            directModifiedDate = updateddateformat.format(directdebitModdate);

            amountFieldStringValue = dirDebit.getAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();

            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                setCurrencyTypeValue(currencyTypeValue);
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                dirDebit.setCurTypeValueForAmountField(curTypeValueForAmountField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                setCurrencyTypeValue(currencyTypeValue);
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                dirDebit.setCurTypeValueForAmountField(curTypeValueForAmountField);
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
                + getText("label.header.directDebit.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + dirDebit.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.directDebit.routingNo")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + dirDebit.getRoutingNum()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.directDebit.account")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + dirDebit.getAccount()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.directDebit.amount") + " " + getCurrencyTypeValue()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + dirDebit.getCurTypeValueForAmountField()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.directDebit.accountType")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + dirDebit.getAccountType()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.directDebit.transactionType")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + dirDebit.getTransactionType()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG);

            if (dirDebit.getPreAccountValue().isEmpty() == false) {
                sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                    + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                    + getText("label.header.directDebit.preAccount")
                    + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                    + dirDebit.getPreAccountValue() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                    + HtmlConstants.HTML_TABLE_ROW_END_TAG);
            }
            sMessage.append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + directModifiedDate
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
            setPreferrdAccountValue(dirDebit);
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            DateFormat updateddateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date directdebitModdate = dirDebit.getUpdated();
            directModifiedDate = updateddateformat.format(directdebitModdate);

            amountFieldStringValue = dirDebit.getAmount().toString();
            currencyTypeValue = currencyService.getCurrencyType();
            if (currencyTypeValue == null) {
                currencyTypeValue = "";
                setCurrencyTypeValue(currencyTypeValue);
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                dirDebit.setCurTypeValueForAmountField(curTypeValueForAmountField);
            } else {
                currencyTypeValue = "(" + currencyTypeValue + ")";
                setCurrencyTypeValue(currencyTypeValue);
                curTypeValueForAmountField = currencyTypeValue + "   " + amountFieldStringValue;
                dirDebit.setCurTypeValueForAmountField(curTypeValueForAmountField);
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
            sMessage.replace(sMessage.lastIndexOf(sLoggedInPerson), sMessage.lastIndexOf(sLoggedInPerson)
                + sLoggedInPerson.length(), LoggedIn);

            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.directDebit.info")
                + HtmlConstants.HTML_TABLE_HEADER_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.common.empName") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + dirDebit.getEmpIdObj().getEmpFirstName() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.directDebit.routingNo")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + dirDebit.getRoutingNum()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.directDebit.account")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + dirDebit.getAccount()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.directDebit.amount") + " " + getCurrencyTypeValue()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + dirDebit.getCurTypeValueForAmountField()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.directDebit.accountType")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + dirDebit.getAccountType()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.directDebit.transactionType")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + dirDebit.getTransactionType()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.header.directDebit.preAccount")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + dirDebit.getPreAccountValue()
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.common.message.modifiedDate")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + directModifiedDate
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

    public List<DirectDebitVO> getDirDebitList() {
        return dirDebitList;
    }

    public void setDirDebitList(List<DirectDebitVO> dirDebitList) {
        this.dirDebitList = dirDebitList;
    }

    public DirectDebitVO getDirDebit() {
        return dirDebit;
    }

    public void setDirDebit(DirectDebitVO dirDebit) {
        this.dirDebit = dirDebit;
    }

    public List<EmployeesVO> getEmpIdObj() {
        return empIdObj;
    }

    public void setEmpIdObj(List<EmployeesVO> empIdObj) {
        this.empIdObj = empIdObj;
    }

    public EmployeesVO getEmps() {
        return emps;
    }

    public void setEmps(EmployeesVO emps) {
        this.emps = emps;
    }

    public String getCurrencyTypeValue() {
        return currencyTypeValue;
    }

    public void setCurrencyTypeValue(String currencyTypeValue) {
        this.currencyTypeValue = currencyTypeValue;
    }

    public void setAmountFieldStringValue(String amountFieldStringValue) {
        this.amountFieldStringValue = amountFieldStringValue;
    }

    public String getAmountFieldStringValue() {
        return amountFieldStringValue;
    }

    public void setCurTypeValueForAmountField(String curTypeValueForAmountField) {
        this.curTypeValueForAmountField = curTypeValueForAmountField;
    }

    public String getCurTypeValueForAmountField() {
        return curTypeValueForAmountField;
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