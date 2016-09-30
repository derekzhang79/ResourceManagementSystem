
package com.gits.rms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
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
import com.gits.rms.service.EmpSpaceDaoService;
import com.gits.rms.service.EmpSpaceService;
import com.gits.rms.service.RoleDaoService;
import com.gits.rms.service.RoleService;
import com.gits.rms.service.SignatureDaoService;
import com.gits.rms.service.SignatureService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.utils.FileUploadUtil;
import com.gits.rms.vo.EmpSpaceVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;
import com.gits.rms.vo.SignatureVO;

public class EmpSpaceAction extends ActionSupport {
    private static final long serialVersionUID = 8663990416002572528L;
    private List<File> uploads = new ArrayList<File>();
    private List<String> uploadFileNames = new ArrayList<String>();
    private List<String> uploadContentTypes = new ArrayList<String>();
    private FileUploadUtil fileupload = new FileUploadUtil();
    private InputStream inStream;

    private EmpSpaceService empSpaceService = new EmpSpaceDaoService();
    private SignatureService signatureService = new SignatureDaoService();
    private RoleService roleService = new RoleDaoService();
    private List<SignatureVO> signatureList;
    private SignatureVO sigObj;
    private String sSignature;
    private RoleVO role;
    private List<EmpSpaceVO> empSpaceList;
    private List<EmployeesVO> adminRoleId;
    private List<EmpSpaceVO> empSpaceListForSharedAndUploaded;
    private List<EmpSpaceVO> empSpaceSharedIdList = new ArrayList<EmpSpaceVO>();
    private EmpSpaceVO empSpace;
    private EmpSpaceVO empSpaceSharedId;
    private String fileName;
    public Integer maxiFolderSize;

    // To get List of All Shared EmpSpace Files for LoggedIn Employee
    @SkipValidation
    public String getAllSharedEmpSpace() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empSpaceList = empSpaceService.getAllSharedEmpSpace(oEmp.getEmpWorkEmail());
        return SUCCESS;
    }

    // To get List of All Shared and Uploaded EmpSpace Files
    @SkipValidation
    public String getAllSharedAndUploadedEmpSpace() {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");
        empSpaceListForSharedAndUploaded = empSpaceService.getAllSharedAndUploadedList();
        return SUCCESS;
    }

    // To View Search Form
    @SkipValidation
    public String empSpaceSearchForm() {
        return SUCCESS;
    }

    // Alloted Space Size
    @SkipValidation
    public String getEmpAllotedSpace() {
        Map session = ActionContext.getContext().getSession();
        session.get("EMPLOYEE_OBJECT");

        String MaxSpaceValue = (String) session.get("EMPLOYEE_SPACE_MAX_SIZE");
        int maxValueCheck = Integer.parseInt(MaxSpaceValue);
        maxiFolderSize = maxValueCheck / 1048576;
        session.put("EMPLOYEE_SPACE_MAXIMUM_SIZE", maxiFolderSize);
        return SUCCESS;
    }

    // Search Result
    @SkipValidation
    public String empspaceSearchResult() {
        empSpaceList = empSpaceService.empspaceSearchResult(empSpace);
        return SUCCESS;
    }

    // To get List of EmpSpace uploaded Files
    @SkipValidation
    public String getAllEmpSpace() {
        List<String> sharedEmp = new ArrayList<String>();
        List<String> FinalsharedEmp = new ArrayList<String>();
        new ArrayList<String>();
        String EmployeeList = "";
        EmpSpaceVO newEmpSpaceListVO = null;
        EmployeesVO FinalEmpSpace = null;
        Hashtable empSpaceHash = new Hashtable();

        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empSpaceList = empSpaceService.getAllEmpSpace(oEmp.getEmployeeId());
        for (Iterator<EmpSpaceVO> it = empSpaceList.iterator(); it.hasNext();) {
            newEmpSpaceListVO = it.next();
            if (newEmpSpaceListVO.getSharedEmpIds() != null) {
                sharedEmp.add(newEmpSpaceListVO.getSharedEmpIds());
            }
        }
        if (!(sharedEmp.isEmpty())) {
            for (Iterator<String> it = sharedEmp.iterator(); it.hasNext();) {
                EmployeeList = it.next();
                String[] result = EmployeeList.split(",");
                for (int x = 0; x < result.length; x++) {
                    FinalEmpSpace = empSpaceService.getEmployeeName(Integer.parseInt(result[x]));
                    empSpaceHash.put(result[x], FinalEmpSpace.getEmpFirstName());
                    FinalsharedEmp.add(FinalEmpSpace.getEmpFirstName() + ",");
                }
            }
        }
        for (Iterator<EmpSpaceVO> itera = empSpaceList.iterator(); itera.hasNext();) {
            itera.next();
            empSpaceHash.keys();
        }
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // EmpSpace it shows blank Form to enter New Data
    @SkipValidation
    public String shareFile() {
        if ((empSpace != null) && (empSpace.getHcmoEmpSpaceId() != null)) {
            empSpace = empSpaceService.getEmpSpace(empSpace.getHcmoEmpSpaceId());
        }
        return SUCCESS;
    }

    public long getFileSize(File folder) {
        long foldersize = 0;
        File[] filelist = folder.listFiles();
        for (int i = 0; i < filelist.length; i++) {
            if (filelist[i].isDirectory()) {
                foldersize += getFileSize(filelist[i]);
            } else {
                foldersize += filelist[i].length();
            }
        }
        return foldersize;
    }

    public String upload() throws Exception {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        String fName = "";
        String sharedEmpIdList = "";
        String sharedEmpFnameList = "";
        int i = 0;
        int l;
        long fileSizeByte;
        EmployeesVO newEmpSpace = null;
        EmployeesVO newAdminEmp = null;
        String EmployeeId = "";
        EmployeesVO EmployeeEmail = null;
        String EmployeeEmailIds = "";
        String EmployeeFirstName = "";
        String folderLocation = getText("ApplicationAbsolutePath")
            + ServletActionContext.getServletContext().getContextPath() + getText("EmployeeSpace")
            + "/" + "MASTER_CLIENTID_" + session.get("MASTER_CLIENT_ID") + "/"
            + oEmp.getEmployeeId();

        String MaxSpaceValue = (String) session.get("EMPLOYEE_SPACE_MAX_SIZE");
        int maxValueCheck = Integer.parseInt(MaxSpaceValue);
        int finalMaxValue = maxValueCheck - 524288;
        File file = new File(folderLocation);
        if (file.exists() == true) {
            file.exists();
            file.isDirectory();
            file.getTotalSpace();
            fileSizeByte = getFileSize(new File(folderLocation));
            if (fileSizeByte > maxValueCheck) {
                addActionError(getText("You have reached ur maximum limit, Delete some old files to upload new files"));
                return ERROR;
            } else if ((fileSizeByte > finalMaxValue) && (fileSizeByte < maxValueCheck)) {
                // If no file hasbeen shared to employee
                if (empSpace.getEmpIdObjList() != null) {
                    for (Iterator<EmployeesVO> it = empSpace.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmpSpace = it.next();
                        EmployeeEmail = empSpaceService.getEmployeeName(newEmpSpace.getEmployeeId());

                        int loggedInEmp = oEmp.getEmployeeId();
                        int selectedEmpId = newEmpSpace.getEmployeeId();
                        if (loggedInEmp == selectedEmpId) {
                            addActionError(getText("You cant share file for your own"));
                            return ERROR;
                        }
                        EmployeeEmailIds = EmployeeEmailIds + EmployeeEmail.getEmpWorkEmail() + ",";
                        EmployeeId = EmployeeId + newEmpSpace.getEmployeeId() + ",";
                        EmployeeFirstName = EmployeeFirstName + EmployeeEmail.getEmpFirstName()
                            + ",";
                    }
                    EmployeeId = EmployeeId.substring(0, EmployeeId.lastIndexOf(','));
                    EmployeeEmailIds = EmployeeEmailIds.substring(0, EmployeeEmailIds.lastIndexOf(','));
                    EmployeeFirstName = EmployeeFirstName.substring(0, EmployeeFirstName.lastIndexOf(','));

                    for (File u : uploads) {
                        fileupload.uploadFile(u.getAbsolutePath(), uploadFileNames.get(i), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("EmployeeSpace")
                            + "/"
                            + "MASTER_CLIENTID_"
                            + session.get("MASTER_CLIENT_ID")
                            + "/"
                            + oEmp.getEmployeeId() + "/");
                        i++;
                    }
                    for (String fileName : uploadFileNames) {
                        fName += fileName + ",";
                    }
                    if (((fName.isEmpty() == true) || (fName.length() == 0) || (fName == null))) {
                        addActionError(getText("Please select atleast one file to upload"));
                        return ERROR;
                    }
                    String splitfNameArray = fName.substring(0, fName.lastIndexOf(','));
                    String[] fNameArray = splitfNameArray.split(",");

                    for (int j = 0; j < i; j++) {
                        empSpace.setSpaceAttachFileName(fNameArray[j]);
                        empSpace.setEmpIdObj(oEmp);
                        empSpace.setSharedEmpIds(EmployeeId);
                        empSpace.setSharedEmpFirstName(EmployeeFirstName);
                        empSpace.setSharedEmpEmailId(EmployeeEmailIds);
                        empSpace.setCreated(DateUtils.getCurrentDateTime());
                        empSpace.setCreatedBy(oEmp);
                        empSpace.setUpdatedBy(oEmp);
                        empSpace.setIsActive(1);
                        empSpaceService.insertEmpSpace(empSpace);
                    }
                    // Add Messaging Concept
                    empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));
                    // EmployeesVO adminRoleId =
                    // roleService.getEmployeeId(role.getHcmoRoleId());
                    int sessionEmpId = oEmp.getEmployeeId();
                    oEmp.getEmployeeId();

                    // Retrieved the Many more Admin employee list
                    List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                    int lengthForAdminEmpList = adminRoleId.size();

                    // Retrieved single Shared EmpIds
                    String selectedEmpId = empSpace.getSharedEmpIds();
                    sharedEmpIdList += selectedEmpId + ",";

                    String splitEmpIdArray = sharedEmpIdList.substring(0, sharedEmpIdList.lastIndexOf(','));
                    String[] empIdArray = splitEmpIdArray.split(",");
                    l = empIdArray.length;

                    // Retrieved single Shared EmpFirstname
                    String selEmpFname = empSpace.getSharedEmpFirstName();
                    sharedEmpFnameList += selEmpFname + ",";

                    String splitEmpFnameArray = sharedEmpFnameList.substring(0, sharedEmpFnameList.lastIndexOf(','));
                    String[] empFnameArray = splitEmpFnameArray.split(",");
                    // Retrieved single Shared FileName
                    String splitFilenameArray = fName.substring(0, fName.lastIndexOf(','));
                    String[] fileNameArray = splitFilenameArray.split(",");
                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                    String sSubject = getText("message.subject.empSpace.add");

                    Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                        newAdminEmp = it.next();
                        int adminEmpID = newAdminEmp.getEmployeeId();

                        // Add Mailing Concept without having Shared Employee
                        // Name List
                        if (empSpace.getSharedEmpIds() == null) {
                            // Logged in emp is not equal to Admin
                            if (adminEmpID != sessionEmpId) {
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // Mail to All admin employee list
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.addedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // Mail to Loggedin emp
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.addTo"), empSpace.getSpaceAttachFileName(), sSubject);
                            }
                            // Logged in emp is equal to Admin
                            else {
                                if (sessionEmpId != adminEmpID) {
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    // the mail content to Other admin Employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.addedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.addTo"), empSpace.getSpaceAttachFileName(), sSubject);
                                }
                            }
                        }
                        // Add Mailing Concept with having Shared Employee Name
                        // List
                        else {
                            // Logged in emp is not equal to Admin
                            if (adminEmpID != sessionEmpId) {
                                // Mail to All admin employee list
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);

                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to Loggedin emp
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.sharedTo"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                                for (int j = 0; j < l; j++) {
                                    String empIdSingleList = empIdArray[j];
                                    empSpace.setSharedEmpIds(empIdSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    String empFnameSingleList = empFnameArray[j];
                                    empSpace.setSharedEmpFirstName(empFnameSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    // Mail to Shared EmpName
                                    mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.add.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                            // Logged in emp is equal to Admin
                            else {
                                if (sessionEmpId != adminEmpID) {
                                    // the mail content to Other admin Employees
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.sharedTo"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                                }
                                for (int j = 0; j < l; j++) {
                                    String empIdSingleList = empIdArray[j];
                                    empSpace.setSharedEmpIds(empIdSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    String empFnameSingleList = empFnameArray[j];
                                    empSpace.setSharedEmpFirstName(empFnameSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    // Mail to Shared EmpName
                                    mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.add.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                    }
                    fileSizeByte = getFileSize(new File(folderLocation));
                    addActionMessage(getText("Uploaded Successfully"));
                    addActionError(getText("Your are going to reach your maximum limit"));
                    return SUCCESS;
                }
                // share the uploading file with some employees
                else {
                    for (File u : uploads) {
                        fileupload.uploadFile(u.getAbsolutePath(), uploadFileNames.get(i), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("EmployeeSpace")
                            + "/"
                            + "MASTER_CLIENTID_"
                            + session.get("MASTER_CLIENT_ID")
                            + "/"
                            + oEmp.getEmployeeId() + "/");
                        i++;
                    }
                    for (String fileName : uploadFileNames) {
                        fName += fileName + ",";
                    }
                    if (((fName.isEmpty() == true) || (fName.length() == 0) || (fName == null))) {
                        addActionError(getText("Please select atleast one file to upload"));
                        return ERROR;
                    }
                    String splitfNameArray = fName.substring(0, fName.lastIndexOf(','));
                    String[] fNameArray = splitfNameArray.split(",");

                    for (int j = 0; j < i; j++) {
                        empSpace.setSpaceAttachFileName(fNameArray[j]);
                        empSpace.setEmpIdObj(oEmp);
                        empSpace.setCreated(DateUtils.getCurrentDateTime());
                        empSpace.setCreatedBy(oEmp);
                        empSpace.setUpdatedBy(oEmp);
                        empSpace.setIsActive(1);
                        empSpaceService.insertEmpSpace(empSpace);
                    }

                    // Add Messaging Concept
                    empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));
                    // EmployeesVO adminRoleId =
                    // roleService.getEmployeeId(role.getHcmoRoleId());
                    int sessionEmpId = oEmp.getEmployeeId();
                    oEmp.getEmployeeId();

                    // Retrieved the Many more Admin employee list
                    List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                    int lengthForAdminEmpList = adminRoleId.size();

                    // Retrieved single Shared EmpIds
                    String selectedEmpId = empSpace.getSharedEmpIds();
                    sharedEmpIdList += selectedEmpId + ",";

                    String splitEmpIdArray = sharedEmpIdList.substring(0, sharedEmpIdList.lastIndexOf(','));
                    String[] empIdArray = splitEmpIdArray.split(",");
                    l = empIdArray.length;

                    // Retrieved single Shared EmpFirstname
                    String selEmpFname = empSpace.getSharedEmpFirstName();
                    sharedEmpFnameList += selEmpFname + ",";

                    String splitEmpFnameArray = sharedEmpFnameList.substring(0, sharedEmpFnameList.lastIndexOf(','));
                    String[] empFnameArray = splitEmpFnameArray.split(",");
                    // Retrieved single Shared FileName
                    String splitFilenameArray = fName.substring(0, fName.lastIndexOf(','));
                    String[] fileNameArray = splitFilenameArray.split(",");
                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                    String sSubject = getText("message.subject.empSpace.add");

                    Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                        newAdminEmp = it.next();
                        int adminEmpID = newAdminEmp.getEmployeeId();

                        // Add Mailing Concept without having Shared Employee
                        // Name List
                        if (empSpace.getSharedEmpIds() == null) {
                            // Logged in emp is not equal to Admin
                            if (adminEmpID != sessionEmpId) {
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // Mail to All admin employee list
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.addedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // Mail to Loggedin emp
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.addTo"), empSpace.getSpaceAttachFileName(), sSubject);
                            }
                            // Logged in emp is equal to Admin
                            else {
                                if (sessionEmpId != adminEmpID) {
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    // the mail content to Other admin Employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.addedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.addTo"), empSpace.getSpaceAttachFileName(), sSubject);
                                }
                            }
                        }
                        // Add Mailing Concept with having Shared Employee Name
                        // List
                        else {
                            // Logged in emp is not equal to Admin
                            if (adminEmpID != sessionEmpId) {
                                // Mail to All admin employee list
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to Loggedin emp
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.sharedTo"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                                for (int j = 0; j < l; j++) {
                                    String empIdSingleList = empIdArray[j];
                                    empSpace.setSharedEmpIds(empIdSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);

                                    String empFnameSingleList = empFnameArray[j];
                                    empSpace.setSharedEmpFirstName(empFnameSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    // Mail to Shared EmpName
                                    mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.add.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                            // Logged in emp is equal to Admin
                            else {
                                if (sessionEmpId != adminEmpID) {
                                    // the mail content to Other admin Employees
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.sharedTo"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                                }
                                for (int j = 0; j < l; j++) {
                                    String empIdSingleList = empIdArray[j];
                                    empSpace.setSharedEmpIds(empIdSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);

                                    String empFnameSingleList = empFnameArray[j];
                                    empSpace.setSharedEmpFirstName(empFnameSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    // Mail to Shared EmpName
                                    mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.add.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                    }
                    fileSizeByte = getFileSize(new File(folderLocation));
                    addActionMessage(getText("Uploaded Successfully"));
                    return SUCCESS;
                }
            } else {
                if (!(empSpace.getEmpIdObjList().isEmpty())) {
                    for (Iterator<EmployeesVO> it = empSpace.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmpSpace = it.next();
                        EmployeeEmail = empSpaceService.getEmployeeName(newEmpSpace.getEmployeeId());
                        int loggedInEmp = oEmp.getEmployeeId();
                        int selectedEmpId = newEmpSpace.getEmployeeId();
                        if (loggedInEmp == selectedEmpId) {
                            addActionError(getText("You cant share file for your own"));
                            return ERROR;
                        }
                        EmployeeEmailIds = EmployeeEmailIds + EmployeeEmail.getEmpWorkEmail() + ",";
                        EmployeeId = EmployeeId + newEmpSpace.getEmployeeId() + ",";
                        EmployeeFirstName = EmployeeFirstName + EmployeeEmail.getEmpFirstName()
                            + ",";
                    }
                    EmployeeId = EmployeeId.substring(0, EmployeeId.lastIndexOf(','));
                    EmployeeEmailIds = EmployeeEmailIds.substring(0, EmployeeEmailIds.lastIndexOf(','));
                    EmployeeFirstName = EmployeeFirstName.substring(0, EmployeeFirstName.lastIndexOf(','));
                    for (File u : uploads) {
                        fileupload.uploadFile(u.getAbsolutePath(), uploadFileNames.get(i), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("EmployeeSpace")
                            + "/"
                            + "MASTER_CLIENTID_"
                            + session.get("MASTER_CLIENT_ID")
                            + "/"
                            + oEmp.getEmployeeId() + "/");
                        i++;
                    }
                    for (String fileName : uploadFileNames) {
                        fName += fileName + ",";
                    }
                    if (((fName.isEmpty() == true) || (fName.length() == 0) || (fName == null))) {
                        addActionError(getText("Please select atleast one file to upload"));
                        return ERROR;
                    }
                    String splitfNameArray = fName.substring(0, fName.lastIndexOf(','));
                    String[] fNameArray = splitfNameArray.split(",");
                    for (int j = 0; j < i; j++) {
                        empSpace.setSpaceAttachFileName(fNameArray[j]);
                        empSpace.setEmpIdObj(oEmp);
                        empSpace.setSharedEmpIds(EmployeeId);
                        empSpace.setSharedEmpFirstName(EmployeeFirstName);
                        empSpace.setSharedEmpEmailId(EmployeeEmailIds);
                        empSpace.setCreated(DateUtils.getCurrentDateTime());
                        empSpace.setCreatedBy(oEmp);
                        empSpace.setUpdatedBy(oEmp);
                        empSpace.setIsActive(1);
                        empSpaceService.insertEmpSpace(empSpace);
                    }

                    // Add Messaging Concept
                    empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));
                    // EmployeesVO adminRoleId =
                    // roleService.getEmployeeId(role.getHcmoRoleId());
                    int sessionEmpId = oEmp.getEmployeeId();
                    oEmp.getEmployeeId();

                    // Retrieved the Many more Admin employee list
                    List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                    int lengthForAdminEmpList = adminRoleId.size();

                    // Retrieved single Shared EmpIds
                    String selectedEmpId = empSpace.getSharedEmpIds();
                    sharedEmpIdList += selectedEmpId + ",";

                    String splitEmpIdArray = sharedEmpIdList.substring(0, sharedEmpIdList.lastIndexOf(','));
                    String[] empIdArray = splitEmpIdArray.split(",");
                    l = empIdArray.length;

                    // Retrieved single Shared EmpFirstname
                    String selEmpFname = empSpace.getSharedEmpFirstName();
                    sharedEmpFnameList += selEmpFname + ",";

                    String splitEmpFnameArray = sharedEmpFnameList.substring(0, sharedEmpFnameList.lastIndexOf(','));
                    String[] empFnameArray = splitEmpFnameArray.split(",");
                    // Retrieved single Shared FileName
                    String splitFilenameArray = fName.substring(0, fName.lastIndexOf(','));
                    String[] fileNameArray = splitFilenameArray.split(",");
                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                    String sSubject = getText("message.subject.empSpace.add");

                    Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                        newAdminEmp = it.next();
                        int adminEmpID = newAdminEmp.getEmployeeId();

                        // Add Mailing Concept without having Shared Employee
                        // Name List
                        if (empSpace.getSharedEmpIds() == null) {
                            // Logged in emp is not equal to Admin
                            if (adminEmpID != sessionEmpId) {
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // Mail to All admin employee list
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.addedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // Mail to Loggedin emp
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.addTo"), empSpace.getSpaceAttachFileName(), sSubject);
                            }
                            // Logged in emp is equal to Admin
                            else {
                                if (sessionEmpId != adminEmpID) {
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    // the mail content to Other admin Employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.addedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.addTo"), empSpace.getSpaceAttachFileName(), sSubject);
                                }
                            }
                        }
                        // Add Mailing Concept with having Shared Employee Name
                        // List
                        else {
                            // Logged in emp is not equal to Admin
                            if (adminEmpID != sessionEmpId) {
                                // Mail to All admin employee list
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);

                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to Loggedin emp
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.sharedTo"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);

                                for (int j = 0; j < l; j++) {
                                    String empIdSingleList = empIdArray[j];
                                    empSpace.setSharedEmpIds(empIdSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);

                                    String empFnameSingleList = empFnameArray[j];
                                    empSpace.setSharedEmpFirstName(empFnameSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    // Mail to Shared EmpName
                                    mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.add.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                            // Logged in emp is equal to Admin
                            else {
                                if (sessionEmpId != adminEmpID) {
                                    // the mail content to Other admin Employees
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.sharedTo"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                                }
                                for (int j = 0; j < l; j++) {
                                    String empIdSingleList = empIdArray[j];
                                    empSpace.setSharedEmpIds(empIdSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);

                                    String empFnameSingleList = empFnameArray[j];
                                    empSpace.setSharedEmpFirstName(empFnameSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    // Mail to Shared EmpName
                                    mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.add.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                    }
                    fileSizeByte = getFileSize(new File(folderLocation));

                    addActionMessage(getText("Uploaded Successfully"));
                    return SUCCESS;
                } else {
                    for (File u : uploads) {
                        fileupload.uploadFile(u.getAbsolutePath(), uploadFileNames.get(i), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("EmployeeSpace")
                            + "/"
                            + "MASTER_CLIENTID_"
                            + session.get("MASTER_CLIENT_ID")
                            + "/"
                            + oEmp.getEmployeeId() + "/");
                        i++;
                    }
                    for (String fileName : uploadFileNames) {
                        fName += fileName + ",";
                    }
                    if (((fName.isEmpty() == true) || (fName.length() == 0) || (fName == null))) {
                        addActionError(getText("Please select atleast one file to upload"));
                        return ERROR;
                    }
                    String splitfNameArray = fName.substring(0, fName.lastIndexOf(','));
                    String[] fNameArray = splitfNameArray.split(",");
                    for (int j = 0; j < i; j++) {
                        if (((fNameArray[j].isEmpty() == true) || (fNameArray[j].length() == 0) || (fNameArray[j] == null))) {
                            addActionError(getText("Please select atleast one file to upload"));
                            return ERROR;
                        }
                        empSpace.setSpaceAttachFileName(fNameArray[j]);
                        empSpace.setEmpIdObj(oEmp);
                        empSpace.setCreated(DateUtils.getCurrentDateTime());
                        empSpace.setCreatedBy(oEmp);
                        empSpace.setUpdatedBy(oEmp);
                        empSpace.setIsActive(1);
                        empSpaceService.insertEmpSpace(empSpace);
                    }

                    // Add Messaging Concept
                    empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());
                    role = roleService.getRoleName(getText("message.label.common.adminName"));
                    // EmployeesVO adminRoleId =
                    // roleService.getEmployeeId(role.getHcmoRoleId());
                    int sessionEmpId = oEmp.getEmployeeId();
                    oEmp.getEmployeeId();

                    // Retrieved the Many more Admin employee list
                    List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                    int lengthForAdminEmpList = adminRoleId.size();

                    // Retrieved single Shared EmpIds
                    String selectedEmpId = empSpace.getSharedEmpIds();
                    sharedEmpIdList += selectedEmpId + ",";

                    String splitEmpIdArray = sharedEmpIdList.substring(0, sharedEmpIdList.lastIndexOf(','));
                    String[] empIdArray = splitEmpIdArray.split(",");
                    l = empIdArray.length;

                    // Retrieved single Shared EmpFirstname
                    String selEmpFname = empSpace.getSharedEmpFirstName();
                    sharedEmpFnameList += selEmpFname + ",";

                    String splitEmpFnameArray = sharedEmpFnameList.substring(0, sharedEmpFnameList.lastIndexOf(','));
                    String[] empFnameArray = splitEmpFnameArray.split(",");
                    // Retrieved single Shared FileName
                    String splitFilenameArray = fName.substring(0, fName.lastIndexOf(','));
                    String[] fileNameArray = splitFilenameArray.split(",");
                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                    String sSubject = getText("message.subject.empSpace.add");

                    Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                        newAdminEmp = it.next();
                        int adminEmpID = newAdminEmp.getEmployeeId();

                        // Add Mailing Concept without having Shared Employee
                        // Name List
                        if (empSpace.getSharedEmpIds() == null) {
                            // Logged in emp is not equal to Admin
                            if (adminEmpID != sessionEmpId) {
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // Mail to All admin employee list
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.addedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // Mail to Loggedin emp
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.addTo"), empSpace.getSpaceAttachFileName(), sSubject);
                            }
                            // Logged in emp is equal to Admin
                            else {
                                if (sessionEmpId != adminEmpID) {
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    // the mail content to Other admin Employees
                                    mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.addedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.addTo"), empSpace.getSpaceAttachFileName(), sSubject);
                                }
                            }
                        }
                        // Add Mailing Concept with having Shared Employee Name
                        // List
                        else {
                            // Logged in emp is not equal to Admin
                            if (adminEmpID != sessionEmpId) {
                                // Mail to All admin employee list
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // Mail to Loggedin emp
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.sharedTo"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                                for (int j = 0; j < l; j++) {
                                    String empIdSingleList = empIdArray[j];
                                    empSpace.setSharedEmpIds(empIdSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);

                                    String empFnameSingleList = empFnameArray[j];
                                    empSpace.setSharedEmpFirstName(empFnameSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    // Mail to Shared EmpName
                                    mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.add.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                            // Logged in emp is equal to Admin
                            else {
                                if (sessionEmpId != adminEmpID) {
                                    // the mail content to Other admin Employees
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                                }
                                lengthForAdminEmpList--;
                                if (lengthForAdminEmpList != 0) {
                                    continue Loop;
                                }
                                // If Login admin is same as one of the admin
                                // Employee List
                                if (sessionEmpId == adminEmpID) {
                                    // the mail content to login admin
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.sharedTo"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                                }
                                for (int j = 0; j < l; j++) {
                                    String empIdSingleList = empIdArray[j];
                                    empSpace.setSharedEmpIds(empIdSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);

                                    String empFnameSingleList = empFnameArray[j];
                                    empSpace.setSharedEmpFirstName(empFnameSingleList);
                                    empSpace.setSpaceAttachFileName(splitFilenameArray);
                                    // Mail to Shared EmpName
                                    mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.add.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                                }
                            }
                        }
                    }
                    fileSizeByte = getFileSize(new File(folderLocation));
                    addActionMessage(getText("Uploaded Successfully"));
                    return SUCCESS;
                }
            }
        }
        // If the Folder for the logged in employe is not exists
        else {
            new ArrayList<String>();
            if (!(empSpace.getEmpIdObjList().isEmpty())) {

                for (Iterator<EmployeesVO> it = empSpace.getEmpIdObjList().iterator(); it.hasNext();) {
                    newEmpSpace = it.next();
                    EmployeeEmail = empSpaceService.getEmployeeName(newEmpSpace.getEmployeeId());
                    int loggedInEmp = oEmp.getEmployeeId();
                    int selectedEmpId = newEmpSpace.getEmployeeId();

                    if (loggedInEmp == selectedEmpId) {
                        addActionError(getText("You cant share file for your own"));
                        return ERROR;
                    }
                    EmployeeEmailIds = EmployeeEmailIds + EmployeeEmail.getEmpWorkEmail() + ",";
                    EmployeeId = EmployeeId + newEmpSpace.getEmployeeId() + ",";
                    EmployeeFirstName = EmployeeFirstName + EmployeeEmail.getEmpFirstName() + ",";
                }
                EmployeeId = EmployeeId.substring(0, EmployeeId.lastIndexOf(','));
                EmployeeEmailIds = EmployeeEmailIds.substring(0, EmployeeEmailIds.lastIndexOf(','));
                EmployeeFirstName = EmployeeFirstName.substring(0, EmployeeFirstName.lastIndexOf(','));

                for (File u : uploads) {
                    fileupload.uploadFile(u.getAbsolutePath(), uploadFileNames.get(i), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("EmployeeSpace")
                        + "/"
                        + "MASTER_CLIENTID_"
                        + session.get("MASTER_CLIENT_ID")
                        + "/"
                        + oEmp.getEmployeeId() + "/");
                    i++;
                }

                for (String fileName : uploadFileNames) {
                    fName += fileName + ",";
                }
                if (((fName.isEmpty() == true) || (fName.length() == 0) || (fName == null))) {
                    addActionError(getText("Please select atleast one file to upload"));
                    return ERROR;
                }
                String splitfNameArray = fName.substring(0, fName.lastIndexOf(','));
                String[] fNameArray = splitfNameArray.split(",");

                for (int j = 0; j < i; j++) {
                    empSpace.setSpaceAttachFileName(fNameArray[j]);
                    empSpace.setEmpIdObj(oEmp);
                    empSpace.setSharedEmpIds(EmployeeId);
                    empSpace.setSharedEmpFirstName(EmployeeFirstName);
                    empSpace.setSharedEmpEmailId(EmployeeEmailIds);
                    empSpace.setCreated(DateUtils.getCurrentDateTime());
                    empSpace.setCreatedBy(oEmp);
                    empSpace.setUpdatedBy(oEmp);
                    empSpace.setIsActive(1);
                    empSpaceService.insertEmpSpace(empSpace);
                }

                // Add Messaging Concept
                empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));
                // EmployeesVO adminRoleId =
                // roleService.getEmployeeId(role.getHcmoRoleId());
                int sessionEmpId = oEmp.getEmployeeId();
                oEmp.getEmployeeId();

                // Retrieved the Many more Admin employee list
                List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                int lengthForAdminEmpList = adminRoleId.size();

                // Retrieved single Shared EmpIds
                String selectedEmpId = empSpace.getSharedEmpIds();
                sharedEmpIdList += selectedEmpId + ",";

                String splitEmpIdArray = sharedEmpIdList.substring(0, sharedEmpIdList.lastIndexOf(','));
                String[] empIdArray = splitEmpIdArray.split(",");
                l = empIdArray.length;

                // Retrieved single Shared EmpFirstname
                String selEmpFname = empSpace.getSharedEmpFirstName();
                sharedEmpFnameList += selEmpFname + ",";

                String splitEmpFnameArray = sharedEmpFnameList.substring(0, sharedEmpFnameList.lastIndexOf(','));
                String[] empFnameArray = splitEmpFnameArray.split(",");
                // Retrieved single Shared FileName
                String splitFilenameArray = fName.substring(0, fName.lastIndexOf(','));
                String[] fileNameArray = splitFilenameArray.split(",");
                empSpace.setSpaceAttachFileName(splitFilenameArray);
                String sSubject = getText("message.subject.empSpace.add");

                Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                    newAdminEmp = it.next();
                    int adminEmpID = newAdminEmp.getEmployeeId();

                    // Add Mailing Concept without having Shared Employee Name
                    // List
                    if (empSpace.getSharedEmpIds() == null) {
                        // Logged in emp is not equal to Admin
                        if (adminEmpID != sessionEmpId) {
                            empSpace.setSpaceAttachFileName(splitFilenameArray);
                            // Mail to All admin employee list
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.addedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            empSpace.setSpaceAttachFileName(splitFilenameArray);
                            // Mail to Loggedin emp
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.addTo"), empSpace.getSpaceAttachFileName(), sSubject);
                        }
                        // Logged in emp is equal to Admin
                        else {
                            if (sessionEmpId != adminEmpID) {
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // the mail content to Other admin Employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.addedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.addTo"), empSpace.getSpaceAttachFileName(), sSubject);
                            }
                        }
                    }
                    // Add Mailing Concept with having Shared Employee Name List
                    else {
                        // Logged in emp is not equal to Admin
                        if (adminEmpID != sessionEmpId) {
                            // Mail to All admin employee list
                            empSpace.setSpaceAttachFileName(splitFilenameArray);
                            mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to Loggedin emp
                            empSpace.setSpaceAttachFileName(splitFilenameArray);
                            mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.sharedTo"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                            for (int j = 0; j < l; j++) {
                                String empIdSingleList = empIdArray[j];
                                empSpace.setSharedEmpIds(empIdSingleList);
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                String empFnameSingleList = empFnameArray[j];
                                empSpace.setSharedEmpFirstName(empFnameSingleList);
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // Mail to Shared EmpName
                                mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.add.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                        // Logged in emp is equal to Admin
                        else {
                            if (sessionEmpId != adminEmpID) {
                                // the mail content to Other admin Employees
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.sharedTo"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                            }
                            for (int j = 0; j < l; j++) {
                                String empIdSingleList = empIdArray[j];
                                empSpace.setSharedEmpIds(empIdSingleList);
                                empSpace.setSpaceAttachFileName(splitFilenameArray);

                                String empFnameSingleList = empFnameArray[j];
                                empSpace.setSharedEmpFirstName(empFnameSingleList);
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // Mail to Shared EmpName
                                mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.add.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                    }
                }
                fileSizeByte = getFileSize(new File(folderLocation));
                addActionMessage(getText("Uploaded Successfully"));
                return SUCCESS;
            } else {

                for (File u : uploads) {
                    fileupload.uploadFile(u.getAbsolutePath(), uploadFileNames.get(i), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("EmployeeSpace")
                        + "/"
                        + "MASTER_CLIENTID_"
                        + session.get("MASTER_CLIENT_ID")
                        + "/"
                        + oEmp.getEmployeeId() + "/");
                    i++;
                }

                for (String fileName : uploadFileNames) {
                    fName += fileName + ",";
                }
                if (((fName.isEmpty() == true) || (fName.length() == 0) || (fName == null))) {
                    addActionError(getText("Please select atleast one file to upload"));
                    return ERROR;
                }
                String splitfNameArray = fName.substring(0, fName.lastIndexOf(','));
                String[] fNameArray = splitfNameArray.split(",");
                for (int j = 0; j < i; j++) {
                    empSpace.setSpaceAttachFileName(fNameArray[j]);
                    empSpace.setEmpIdObj(oEmp);
                    empSpace.setCreated(DateUtils.getCurrentDateTime());
                    empSpace.setCreatedBy(oEmp);
                    empSpace.setUpdatedBy(oEmp);
                    empSpace.setIsActive(1);
                    empSpaceService.insertEmpSpace(empSpace);
                }

                // Add Messaging Concept
                empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());
                role = roleService.getRoleName(getText("message.label.common.adminName"));
                // EmployeesVO adminRoleId =
                // roleService.getEmployeeId(role.getHcmoRoleId());
                int sessionEmpId = oEmp.getEmployeeId();
                oEmp.getEmployeeId();

                // Retrieved the Many more Admin employee list
                List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
                int lengthForAdminEmpList = adminRoleId.size();

                // Retrieved single Shared EmpIds
                String selectedEmpId = empSpace.getSharedEmpIds();
                sharedEmpIdList += selectedEmpId + ",";

                String splitEmpIdArray = sharedEmpIdList.substring(0, sharedEmpIdList.lastIndexOf(','));
                String[] empIdArray = splitEmpIdArray.split(",");
                l = empIdArray.length;

                // Retrieved single Shared EmpFirstname
                String selEmpFname = empSpace.getSharedEmpFirstName();
                sharedEmpFnameList += selEmpFname + ",";

                String splitEmpFnameArray = sharedEmpFnameList.substring(0, sharedEmpFnameList.lastIndexOf(','));
                String[] empFnameArray = splitEmpFnameArray.split(",");
                // Retrieved single Shared FileName
                String splitFilenameArray = fName.substring(0, fName.lastIndexOf(','));
                String[] fileNameArray = splitFilenameArray.split(",");
                empSpace.setSpaceAttachFileName(splitFilenameArray);
                String sSubject = getText("message.subject.empSpace.add");

                Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
                    newAdminEmp = it.next();
                    int adminEmpID = newAdminEmp.getEmployeeId();

                    // Add Mailing Concept without having Shared Employee Name
                    // List
                    if (empSpace.getSharedEmpIds() == null) {
                        // Logged in emp is not equal to Admin
                        if (adminEmpID != sessionEmpId) {
                            empSpace.setSpaceAttachFileName(splitFilenameArray);
                            // Mail to All admin employee list
                            mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.addedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            empSpace.setSpaceAttachFileName(splitFilenameArray);
                            // Mail to Loggedin emp
                            mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.addTo"), empSpace.getSpaceAttachFileName(), sSubject);
                        }
                        // Logged in emp is equal to Admin
                        else {
                            if (sessionEmpId != adminEmpID) {
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // the mail content to Other admin Employees
                                mailToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.addedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mail(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.addTo"), empSpace.getSpaceAttachFileName(), sSubject);
                            }
                        }
                    }
                    // Add Mailing Concept with having Shared Employee Name List
                    else {
                        // Logged in emp is not equal to Admin
                        if (adminEmpID != sessionEmpId) {
                            // Mail to All admin employee list
                            empSpace.setSpaceAttachFileName(splitFilenameArray);
                            mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // Mail to Loggedin emp
                            empSpace.setSpaceAttachFileName(splitFilenameArray);
                            mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.sharedTo"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                            for (int j = 0; j < l; j++) {
                                String empIdSingleList = empIdArray[j];
                                empSpace.setSharedEmpIds(empIdSingleList);
                                empSpace.setSpaceAttachFileName(splitFilenameArray);

                                String empFnameSingleList = empFnameArray[j];
                                empSpace.setSharedEmpFirstName(empFnameSingleList);
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // Mail to Shared EmpName
                                mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.add.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                        // Logged in emp is equal to Admin
                        else {
                            if (sessionEmpId != adminEmpID) {
                                // the mail content to Other admin Employees
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.add.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                            }
                            lengthForAdminEmpList--;
                            if (lengthForAdminEmpList != 0) {
                                continue Loop;
                            }
                            // If Login admin is same as one of the admin
                            // Employee List
                            if (sessionEmpId == adminEmpID) {
                                // the mail content to login admin
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.add.sharedTo"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                            }
                            for (int j = 0; j < l; j++) {
                                String empIdSingleList = empIdArray[j];
                                empSpace.setSharedEmpIds(empIdSingleList);
                                empSpace.setSpaceAttachFileName(splitFilenameArray);

                                String empFnameSingleList = empFnameArray[j];
                                empSpace.setSharedEmpFirstName(empFnameSingleList);
                                empSpace.setSpaceAttachFileName(splitFilenameArray);
                                // Mail to Shared EmpName
                                mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.add.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                            }
                        }
                    }
                }
                fileSizeByte = getFileSize(new File(folderLocation));
                addActionMessage(getText("Uploaded Successfully"));
                return SUCCESS;
            }

        }
    }

    @SkipValidation
    public String empSpaceFileDownload() throws Exception {
        Map session = ActionContext.getContext().getSession();
        empSpace = empSpaceService.getEmpSpace(empSpace.getHcmoEmpSpaceId());
        inStream = new FileInputStream(new File(getText("ApplicationAbsolutePath")
            + ServletActionContext.getServletContext().getContextPath() + "/"
            + getText("EmployeeSpace") + "/" + "MASTER_CLIENTID_" + session.get("MASTER_CLIENT_ID")
            + "/" + empSpace.getEmpIdObj().getEmployeeId() + "/"
            + empSpace.getSpaceAttachFileName()));
        return SUCCESS;
    }

    // delete particular EmpSpace data of an employee and also the file from the
    // system
    @SkipValidation
    public String deleteEmpSpace() {
        empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());
        String selectedEmpId = empSpace.getSharedEmpIds();
        String selEmpFname = empSpace.getSharedEmpFirstName();
        String attchFileName = empSpace.getSpaceAttachFileName();
        String titleFieldValue = empSpace.getSharedFileTitle();
        String decFieldValue = empSpace.getSharedFileDesc();

        Map session = ActionContext.getContext().getSession();
        EmployeesVO newAdminEmp = null;
        EmpSpaceVO employeeSpace = new EmpSpaceVO();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        empSpace.setUpdatedBy(oEmp);
        employeeSpace = empSpaceService.getEmpSpace(empSpace.getHcmoEmpSpaceId());
        String FileLocation = getText("ApplicationAbsolutePath")
            + ServletActionContext.getServletContext().getContextPath() + getText("EmployeeSpace")
            + "/" + "MASTER_CLIENTID_" + session.get("MASTER_CLIENT_ID") + "/"
            + oEmp.getEmployeeId() + "/" + employeeSpace.getSpaceAttachFileName();
        File file = new File(FileLocation);
        file.delete();
        empSpaceService.deleteEmpSpace(empSpace);

        String sharedEmpIdList = "";
        String sharedEmpFnameList = "";
        int l;

        // Add Messaging Concept
        role = roleService.getRoleName(getText("message.label.common.adminName"));
        // EmployeesVO adminRoleId =
        // roleService.getEmployeeId(role.getHcmoRoleId());
        int sessionEmpId = oEmp.getEmployeeId();
        oEmp.getEmployeeId();
        String sSubject = getText("message.subject.empSpace.deleted");

        // Retrieved the Many more Admin employee list
        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
        int lengthForAdminEmpList = adminRoleId.size();

        // Retrieved single Shared EmpIds
        empSpace.setSharedEmpIds(selectedEmpId);
        sharedEmpIdList += selectedEmpId + ",";
        String splitEmpIdArray = sharedEmpIdList.substring(0, sharedEmpIdList.lastIndexOf(','));
        String[] empIdArray = splitEmpIdArray.split(",");
        l = empIdArray.length;

        // Retrieved single Shared EmpFirstname
        empSpace.setSharedEmpFirstName(selEmpFname);
        sharedEmpFnameList += selEmpFname + ",";
        String splitEmpFnameArray = sharedEmpFnameList.substring(0, sharedEmpFnameList.lastIndexOf(','));
        String[] empFnameArray = splitEmpFnameArray.split(",");
        empSpace.setSpaceAttachFileName(attchFileName);
        empSpace.setSharedFileTitle(titleFieldValue);
        empSpace.setSharedFileDesc(decFieldValue);

        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
            newAdminEmp = it.next();
            int adminEmpID = newAdminEmp.getEmployeeId();

            // Add Mailing Concept For "Delete" with having Shared Employee Name
            // List
            if (empSpace.getSharedEmpIds() != null) {
                // Logged in emp is not equal to Admin
                if (adminEmpID != sessionEmpId) {
                    // Mail to All admin employee list
                    empSpace.setSharedEmpFirstName(selEmpFname);
                    mailDeletedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.DeletedWithEmpName.DeletedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to Loggedin emp
                    empSpace.setSharedEmpFirstName(selEmpFname);
                    mailDeletedToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.DeletedWithEmpName.DeletedToLogInEmp"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                    for (int j = 0; j < l; j++) {
                        String empIdSingleList = empIdArray[j];
                        empSpace.setSharedEmpIds(empIdSingleList);
                        String empFnameSingleList = empFnameArray[j];
                        empSpace.setSharedEmpFirstName(empFnameSingleList);
                        // Mail to Shared EmpName
                        mailDeletedToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.DeletedWithEmpName.DeletedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                    }
                }
                // Logged in emp is equal to Admin
                else {
                    if (sessionEmpId != adminEmpID) {
                        // the mail content to Other admin Employees
                        mailDeletedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.DeletedWithEmpName.DeletedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mailDeletedToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.DeletedWithEmpName.DeletedToLogInEmp"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                    }
                    for (int j = 0; j < l; j++) {
                        String empIdSingleList = empIdArray[j];
                        empSpace.setSharedEmpIds(empIdSingleList);
                        String empFnameSingleList = empFnameArray[j];
                        empSpace.setSharedEmpFirstName(empFnameSingleList);
                        // Mail to Shared EmpName
                        mailDeletedToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.DeletedWithEmpName.DeletedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
            // Add Mailing Concept For "Delete" without having Shared Employee
            // Name List
            else {
                // Logged in emp is not equal to Admin
                if (adminEmpID != sessionEmpId) {
                    // Mail to All admin employee list
                    mailDeletedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.DeletedWithOutEmpName.DeletedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to Loggedin emp
                    mailDeletedToLoginEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.DeletedWithOutEmpName.DeletedToLogInEmp"), empSpace.getSpaceAttachFileName(), sSubject);
                }
                // Logged in emp is equal to Admin
                else {
                    if (sessionEmpId != adminEmpID) {
                        // the mail content to Other admin Employees
                        mailDeletedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.DeletedWithOutEmpName.DeletedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        // Mail to Loggedin emp
                        mailDeletedToLoginEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.DeletedWithOutEmpName.DeletedToLogInEmp"), empSpace.getSpaceAttachFileName(), sSubject);
                    }
                }
            }
        }
        addActionMessage(getText("Deleted Sucessfully"));
        return SUCCESS;
    }

    // Share a particular file from the Employees
    @SkipValidation
    public String makeShared() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        EmployeesVO newEmpSpace = null;
        EmployeesVO newAdminEmp = null;
        String EmployeeIdGroup = "";
        EmployeesVO EmployeeEmail = null;
        String EmployeeEmailIds = "";
        String EmployeeFirstName = "";

        String sharedEmpIdList = "";
        String sharedEmpFnameList = "";
        int l;

        if (empSpace.getEmpIdObjList() == null) {
            addActionError(getText("Please select atleast one Employee to share this file"));
            return INPUT;
        }else if(empSpace.getEmpIdObjList().isEmpty()) {
            addActionError(getText("Please select atleast one Employee to share this file"));
            return INPUT;
        }
        for (Iterator<EmployeesVO> it = empSpace.getEmpIdObjList().iterator(); it.hasNext();) {
            newEmpSpace = it.next();
            int loggedInEmp = oEmp.getEmployeeId();
            int selectedEmpId = newEmpSpace.getEmployeeId();
            EmployeeEmail = empSpaceService.getEmployeeName(newEmpSpace.getEmployeeId());

            if (loggedInEmp == selectedEmpId) {
                addActionError(getText("You cant share file for your own"));
                return INPUT;
            }

            EmployeeEmailIds = EmployeeEmailIds + EmployeeEmail.getEmpWorkEmail() + ",";
            EmployeeIdGroup = EmployeeIdGroup + newEmpSpace.getEmployeeId() + ",";
            EmployeeFirstName = EmployeeFirstName + EmployeeEmail.getEmpFirstName() + ",";
        }
        EmployeeIdGroup = EmployeeIdGroup.substring(0, EmployeeIdGroup.lastIndexOf(','));
        EmployeeEmailIds = EmployeeEmailIds.substring(0, EmployeeEmailIds.lastIndexOf(','));
        EmployeeFirstName = EmployeeFirstName.substring(0, EmployeeFirstName.lastIndexOf(','));

        empSpace.setSharedEmpFirstName(EmployeeFirstName);
        empSpace.setSharedEmpEmailId(EmployeeEmailIds);
        empSpace.setSharedEmpIds(EmployeeIdGroup);
        empSpace.setUpdatedBy(oEmp);
        empSpaceService.makeShared(empSpace);

        // Add Messaging Concept
        empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());
        role = roleService.getRoleName(getText("message.label.common.adminName"));
        // EmployeesVO adminRoleId =
        // roleService.getEmployeeId(role.getHcmoRoleId());
        int sessionEmpId = oEmp.getEmployeeId();
        oEmp.getEmployeeId();

        // Retrieved the Many more Admin employee list
        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
        int lengthForAdminEmpList = adminRoleId.size();

        // Retrieved single Shared EmpIds
        String selectedEmpId = empSpace.getSharedEmpIds();
        sharedEmpIdList += selectedEmpId + ",";
        String splitEmpIdArray = sharedEmpIdList.substring(0, sharedEmpIdList.lastIndexOf(','));
        String[] empIdArray = splitEmpIdArray.split(",");
        l = empIdArray.length;

        // Retrieved single Shared EmpFirstname
        String selEmpFname = empSpace.getSharedEmpFirstName();
        sharedEmpFnameList += selEmpFname + ",";
        String splitEmpFnameArray = sharedEmpFnameList.substring(0, sharedEmpFnameList.lastIndexOf(','));
        String[] empFnameArray = splitEmpFnameArray.split(",");
        String sSubject = getText("message.subject.empSpace.shared");

        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
            newAdminEmp = it.next();
            int adminEmpID = newAdminEmp.getEmployeeId();

            // Add Mailing Concept with having Shared Employee Name List
            if (empSpace.getSharedEmpIds() != null) {
                // Logged in emp is not equal to Admin
                if (adminEmpID != sessionEmpId) {
                    // Mail to All admin employee list
                    mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.shared.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to Loggedin emp
                    mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.shared.sharedToLogInEmp"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                    for (int j = 0; j < l; j++) {
                        String empIdSingleList = empIdArray[j];
                        empSpace.setSharedEmpIds(empIdSingleList);
                        String empFnameSingleList = empFnameArray[j];
                        empSpace.setSharedEmpFirstName(empFnameSingleList);
                        // Mail to Shared EmpName
                        mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.shared.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                    }
                }
                // Logged in emp is equal to Admin
                else {
                    if (sessionEmpId != adminEmpID) {
                        // the mail content to Other admin Employees
                        mailSharedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.shared.sharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mailToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.shared.sharedToLogInEmp"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                    }
                    for (int j = 0; j < l; j++) {
                        String empIdSingleList = empIdArray[j];
                        empSpace.setSharedEmpIds(empIdSingleList);
                        String empFnameSingleList = empFnameArray[j];
                        empSpace.setSharedEmpFirstName(empFnameSingleList);
                        // Mail to Shared EmpName
                        mailToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.shared.sharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
        }
        addActionMessage(getText("Shared Sucessfully"));
        return SUCCESS;
    }

    // UnShare a particular file from the Employees
    @SkipValidation
    public String makeUnshare() {

        empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());
        String selectedEmpId = empSpace.getSharedEmpIds();
        String selEmpFname = empSpace.getSharedEmpFirstName();

        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        EmployeesVO newAdminEmp = null;

        empSpace.setUpdatedBy(oEmp);
        empSpaceService.makeUnshare(empSpace);

        String sharedEmpIdList = "";
        String sharedEmpFnameList = "";
        int l;

        // Add Messaging Concept
        empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());
        role = roleService.getRoleName(getText("message.label.common.adminName"));
        // EmployeesVO adminRoleId =
        // roleService.getEmployeeId(role.getHcmoRoleId());
        int sessionEmpId = oEmp.getEmployeeId();
        oEmp.getEmployeeId();

        // Retrieved the Many more Admin employee list
        List<EmployeesVO> adminRoleId = roleService.getAllAdmin(role.getHcmoRoleId());
        int lengthForAdminEmpList = adminRoleId.size();

        // Retrieved single Shared EmpIds
        empSpace.setSharedEmpIds(selectedEmpId);
        sharedEmpIdList += selectedEmpId + ",";

        String splitEmpIdArray = sharedEmpIdList.substring(0, sharedEmpIdList.lastIndexOf(','));
        String[] empIdArray = splitEmpIdArray.split(",");
        l = empIdArray.length;

        // Retrieved single Shared EmpFirstname
        empSpace.setSharedEmpFirstName(selEmpFname);
        sharedEmpFnameList += selEmpFname + ",";
        String splitEmpFnameArray = sharedEmpFnameList.substring(0, sharedEmpFnameList.lastIndexOf(','));
        String[] empFnameArray = splitEmpFnameArray.split(",");
        String sSubject = getText("message.subject.empSpace.unShared");

        Loop: for (Iterator<EmployeesVO> it = adminRoleId.iterator(); it.hasNext();) {
            newAdminEmp = it.next();
            int adminEmpID = newAdminEmp.getEmployeeId();

            // Add Mailing Concept For "Unshare" with having Shared Employee
            // Name List
            if (empSpace.getSharedEmpIds() != null) {
                // Logged in emp is not equal to Admin
                if (adminEmpID != sessionEmpId) {
                    // Mail to All admin employee list
                    empSpace.setSharedEmpFirstName(selEmpFname);
                    mailDeletedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.UnSharedWithEmpName.UnSharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to Loggedin emp
                    empSpace.setSharedEmpFirstName(selEmpFname);
                    mailDeletedToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.UnSharedWithEmpName.UnSharedToLogInEmp"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                    for (int j = 0; j < l; j++) {
                        String empIdSingleList = empIdArray[j];
                        empSpace.setSharedEmpIds(empIdSingleList);
                        String empFnameSingleList = empFnameArray[j];
                        empSpace.setSharedEmpFirstName(empFnameSingleList);
                        // Mail to Shared EmpName
                        mailDeletedToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.UnSharedWithEmpName.UnSharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                    }
                }
                // Logged in emp is equal to Admin
                else {
                    if (sessionEmpId != adminEmpID) {
                        // the mail content to Other admin Employees
                        mailDeletedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.UnSharedWithEmpName.UnSharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), empSpace.getSharedEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        mailDeletedToSharedLogInEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.UnSharedWithEmpName.UnSharedToLogInEmp"), empSpace.getSpaceAttachFileName(), empSpace.getSharedEmpFirstName(), sSubject);
                    }
                    for (int j = 0; j < l; j++) {
                        String empIdSingleList = empIdArray[j];
                        empSpace.setSharedEmpIds(empIdSingleList);
                        String empFnameSingleList = empFnameArray[j];
                        empSpace.setSharedEmpFirstName(empFnameSingleList);
                        // Mail to Shared EmpName
                        mailDeletedToSharedEmp(empSpace.getSharedEmpIds(), empSpace.getSharedEmpFirstName(), getText("empSpace.UnSharedWithEmpName.UnSharedToEmp"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                    }
                }
            }
            // Add Mailing Concept For "Unshare" without having Shared Employee
            // Name List
            else {
                // Logged in emp is not equal to Admin
                if (adminEmpID != sessionEmpId) {
                    // Mail to All admin employee list
                    mailDeletedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.UnSharedWithOutEmpName.UnSharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // Mail to Loggedin emp
                    mailDeletedToLoginEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.UnSharedWithOutEmpName.UnSharedToLogInEmp"), empSpace.getSpaceAttachFileName(), sSubject);
                }
                // Logged in emp is equal to Admin
                else {
                    if (sessionEmpId != adminEmpID) {
                        // the mail content to Other admin Employees
                        mailDeletedToAdmin(newAdminEmp.getEmployeeId(), newAdminEmp.getEmpFirstName(), getText("empSpace.UnSharedWithOutEmpName.UnSharedToAdmin"), empSpace.getSpaceAttachFileName(), oEmp.getEmpFirstName(), sSubject);
                    }
                    lengthForAdminEmpList--;
                    if (lengthForAdminEmpList != 0) {
                        continue Loop;
                    }
                    // If Login admin is same as one of the admin Employee List
                    if (sessionEmpId == adminEmpID) {
                        // the mail content to login admin
                        // Mail to Loggedin emp
                        mailDeletedToLoginEmp(oEmp.getEmployeeId(), oEmp.getEmpFirstName(), getText("empSpace.UnSharedWithOutEmpName.UnSharedToLogInEmp"), empSpace.getSpaceAttachFileName(), sSubject);
                    }
                }
            }
        }
        addActionMessage(getText("Updated Sucessfully"));
        return SUCCESS;
    }

    public void mail(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());
            new SimpleDateFormat("MM/dd/yyyy");
            new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.FILENAME_LIST;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
            sMessage.replace(sMessage.lastIndexOf(sFirstPerson), sMessage.lastIndexOf(sFirstPerson)
                + sFirstPerson.length(), From);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.empSpace.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.title") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empSpace.getSharedFileTitle() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empSpace.getSharedFileDesc()
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

    public void mailDeletedToLoginEmp(Integer oFirstPerson, String DearEmp, String Message, String From, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            new SimpleDateFormat("MM/dd/yyyy");
            new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.FILENAME_LIST;
            StringBuilder sMessage = new StringBuilder();
            sMessage.append(HtmlConstants.HTML_PARA_FONT_ITALIC_START_TAG
                + getText("message.common.automatedMsg") + HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_BOLD_START_TAG
                + getText("message.common.dearEmployee"));
            sMessage.replace(sMessage.indexOf(sDummy), sMessage.indexOf(sDummy) + sDummy.length(), DearEmp).append(HtmlConstants.HTML_PARA_END_TAG
                + HtmlConstants.HTML_PARA_FONT_START_TAG + HtmlConstants.HTML_SPACE + Message);
            sMessage.replace(sMessage.lastIndexOf(sFirstPerson), sMessage.lastIndexOf(sFirstPerson)
                + sFirstPerson.length(), From);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.empSpace.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.title") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empSpace.getSharedFileTitle() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empSpace.getSharedFileDesc()
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
            empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());

            new SimpleDateFormat("MM/dd/yyyy");
            new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.FILENAME_LIST;
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
                + getText("label.header.empSpace.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.title") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empSpace.getSharedFileTitle() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empSpace.getSharedFileDesc()
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

    public void mailDeletedToAdmin(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            new SimpleDateFormat("MM/dd/yyyy");
            new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.FILENAME_LIST;
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
                + getText("label.header.empSpace.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.title") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empSpace.getSharedFileTitle() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empSpace.getSharedFileDesc()
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

    public void mailToSharedEmp(String oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();
            empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());

            new SimpleDateFormat("MM/dd/yyyy");
            new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.FILENAME_LIST;
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
                + getText("label.header.empSpace.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.title") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empSpace.getSharedFileTitle() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empSpace.getSharedFileDesc()
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
            mailer.sendAlertEmail(oFirstPerson, sSubject, sMessage, sSignature);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void mailDeletedToSharedEmp(String oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            new SimpleDateFormat("MM/dd/yyyy");
            new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.FILENAME_LIST;
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
                + getText("label.header.empSpace.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.title") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empSpace.getSharedFileTitle() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empSpace.getSharedFileDesc()
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
            mailer.sendAlertEmail(oFirstPerson, sSubject, sMessage, sSignature);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void mailToSharedLogInEmp(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());
            new SimpleDateFormat("MM/dd/yyyy");
            new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.FILENAME_LIST;
            String sLoggedInPerson = Constants.EMPLOYEENAME_LIST;
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
                + getText("label.header.empSpace.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.title") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empSpace.getSharedFileTitle() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empSpace.getSharedFileDesc()
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

    public void mailDeletedToSharedLogInEmp(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();

            new SimpleDateFormat("MM/dd/yyyy");
            new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.FILENAME_LIST;
            String sLoggedInPerson = Constants.EMPLOYEENAME_LIST;
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
                + getText("label.header.empSpace.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.title") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empSpace.getSharedFileTitle() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empSpace.getSharedFileDesc()
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

    public void mailSharedToAdmin(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String EmpNameList, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();
            empSpace = empSpaceService.getAllEmpSpaceList(empSpace.getHcmoEmpSpaceId());

            new SimpleDateFormat("MM/dd/yyyy");
            new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.FILENAME_LIST;
            String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
            String sEmpNameList = Constants.EMPLOYEENAME_LIST;
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
            sMessage.replace(sMessage.indexOf(sEmpNameList), sMessage.indexOf(sEmpNameList)
                + sEmpNameList.length(), EmpNameList);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.empSpace.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.title") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empSpace.getSharedFileTitle() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empSpace.getSharedFileDesc()
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

    public void mailDeletedToAdmin(Integer oFirstPerson, String DearEmp, String Message, String From, String LoggedIn, String EmpNameList, String sSubject) {
        try {
            Map session = ActionContext.getContext().getSession();
            session.get("EMPLOYEE_OBJECT");
            HCMOneMailer mailer = new HCMOneMailer();
            new SimpleDateFormat("MM/dd/yyyy");
            new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");

            String sDummy = Constants.PERSON;
            String sFirstPerson = Constants.FILENAME_LIST;
            String sLoggedInPerson = Constants.LOGGEDIN_PERSON;
            String sEmpNameList = Constants.EMPLOYEENAME_LIST;
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
            sMessage.replace(sMessage.indexOf(sEmpNameList), sMessage.indexOf(sEmpNameList)
                + sEmpNameList.length(), EmpNameList);
            sMessage.append(HtmlConstants.HTML_SPACE + HtmlConstants.HTML_PARA_END_TAG);

            sMessage.append(HtmlConstants.HTML_TABLE_OUTER_START_TAG
                + HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_ALIGN_START_TAG
                + HtmlConstants.HTML_TABLE_INNER_START_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_HEADER_START_TAG_COLSPAN
                + getText("label.header.empSpace.info") + HtmlConstants.HTML_TABLE_HEADER_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.title") + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_DATA_START_TAG + HtmlConstants.HTML_COLON
                + empSpace.getSharedFileTitle() + HtmlConstants.HTML_TABLE_DATA_END_TAG
                + HtmlConstants.HTML_TABLE_ROW_END_TAG)

            .append(HtmlConstants.HTML_TABLE_ROW_START_TAG
                + HtmlConstants.HTML_TABLE_DATA_BOLD_START_TAG
                + getText("label.title.empSpace.description")
                + HtmlConstants.HTML_TABLE_DATA_END_TAG + HtmlConstants.HTML_TABLE_DATA_START_TAG
                + HtmlConstants.HTML_COLON + empSpace.getSharedFileDesc()
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

    public List<File> getUploads() {
        return uploads;
    }

    public void setUploads(List<File> uploads) {
        this.uploads = uploads;
    }

    public List<String> getUploadFileNames() {
        return uploadFileNames;
    }

    public void setUploadFileNames(List<String> uploadFileNames) {
        this.uploadFileNames = uploadFileNames;
    }

    public List<EmpSpaceVO> getEmpSpaceList() {
        return empSpaceList;
    }

    public void setEmpSpaceList(List<EmpSpaceVO> empSpaceList) {
        this.empSpaceList = empSpaceList;
    }

    public EmpSpaceVO getEmpSpace() {
        return empSpace;
    }

    public void setEmpSpace(EmpSpaceVO empSpace) {
        this.empSpace = empSpace;
    }

    public List<File> getUpload() {
        return uploads;
    }

    public void setUpload(List<File> uploads) {
        this.uploads = uploads;
    }

    public List<String> getUploadFileName() {
        return uploadFileNames;
    }

    public void setUploadFileName(List<String> uploadFileNames) {
        this.uploadFileNames = uploadFileNames;
    }

    public List<String> getUploadContentType() {
        return uploadContentTypes;
    }

    public void setUploadContentType(List<String> contentTypes) {
        uploadContentTypes = contentTypes;
    }

    public InputStream getInStream() {
        return inStream;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

    public List<EmpSpaceVO> getEmpSpaceSharedIdList() {
        return empSpaceSharedIdList;
    }

    public void setEmpSpaceSharedIdList(List<EmpSpaceVO> empSpaceSharedIdList) {
        this.empSpaceSharedIdList = empSpaceSharedIdList;
    }

    public EmpSpaceVO getEmpSpaceSharedId() {
        return empSpaceSharedId;
    }

    public void setEmpSpaceSharedId(EmpSpaceVO empSpaceSharedId) {
        this.empSpaceSharedId = empSpaceSharedId;
    }

    public String getFileName() {
        fileName = empSpace.getSpaceAttachFileName();
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getMaxiFolderSize() {
        return maxiFolderSize;
    }

    public void setMaxiFolderSize(Integer maxiFolderSize) {
        this.maxiFolderSize = maxiFolderSize;
    }

    public void setEmpSpaceListForSharedAndUploaded(List<EmpSpaceVO> empSpaceListForSharedAndUploaded) {
        this.empSpaceListForSharedAndUploaded = empSpaceListForSharedAndUploaded;
    }

    public List<EmpSpaceVO> getEmpSpaceListForSharedAndUploaded() {
        return empSpaceListForSharedAndUploaded;
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