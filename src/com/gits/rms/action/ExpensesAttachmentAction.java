
package com.gits.rms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.ExpensesAttachmentDaoService;
import com.gits.rms.service.ExpensesAttachmentService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.utils.FileUploadUtil;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ExpensesAttachmentVO;

public class ExpensesAttachmentAction extends ActionSupport {
    private static final long serialVersionUID = 3092396219032437154L;
    private ExpensesAttachmentService expensesAttachService = new ExpensesAttachmentDaoService();
    private ExpensesAttachmentVO expensesAttach;
    private FileUploadUtil fileupload = new FileUploadUtil();
    private List<ExpensesAttachmentVO> expensesAttachList;
    private List<File> uploads = new ArrayList<File>();
    private List<String> uploadFileNames = new ArrayList<String>();
    private List<String> uploadContentTypes = new ArrayList<String>();
    private ExpensesAttachmentVO expAttach;
    private InputStream inStream;
    private String filename;

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

    public String upload() throws Exception {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        String fName = "";
        String fType = "";
        int i = 0;
        int expensesId = expAttach.getHcmoExpensesId().getHcmoExpensesId();
        int employeeId = expAttach.getHcmoEmployeeId().getEmployeeId();
        for (String fileName : uploadFileNames) {
            String splitFileName = fileName.substring(fileName.lastIndexOf('.'));
            if (!((splitFileName.equals(".txt")) || (splitFileName.equals(".doc")) || (splitFileName.equals(".pdf")))) {
                addActionError(getText("File Extension is not Allowed"));
                return ERROR;
            }
        }

        if (uploads.size() < 1) {
            expensesAttachList = expensesAttachService.getAllExpensesAttachment(expAttach.getHcmoExpensesId().getHcmoExpensesId());
            addActionError(getText("Please select atleast one file to upload"));
            return ERROR;
        } else {
            for (File upload : uploads) {
                fileupload.uploadFile(upload.getAbsolutePath(), uploadFileNames.get(i), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("ExpensesAttachments")
                    + "/"
                    + "MASTER_CLIENTID_"
                    + session.get("MASTER_CLIENT_ID")
                    + "/"
                    + employeeId
                    + "/" + expensesId + "/");
                i++;
            }

            for (String fileNameContent : uploadFileNames) {
                fName += fileNameContent + ",";
            }
            for (String fileType : uploadContentTypes) {
                fType += fileType + ",";
            }

            String splitfTypeArray = fType.substring(0, fType.lastIndexOf(','));
            String[] fTypeArray = splitfTypeArray.split(",");

            String splitfNameArray = fName.substring(0, fName.lastIndexOf(','));
            String[] fNameArray = splitfNameArray.split(",");
            for (int j = 0; j < i; j++) {
                expAttach.setExpensesAttachFileName(fNameArray[j]);
                expAttach.setExpensesAttachType(fTypeArray[j]);
                expAttach.setCreated(DateUtils.getCurrentDateTime());
                expAttach.setCreatedBy(oEmp);
                expAttach.setUpdatedBy(oEmp);
                expAttach.setIsActive(1);
                expensesAttachService.insertExpensesAttach(expAttach);
            }
            expensesAttachList = expensesAttachService.getAllExpensesAttachment(expAttach.getHcmoExpensesId().getHcmoExpensesId());
            if (!(expAttach.getExpensesAttachFileName().isEmpty())) {
                addActionMessage(getText("Uploaded Successfully"));
            }
        }
        return SUCCESS;
    }

    @SkipValidation
    public String getAllExpensesAttach() {
        expensesAttachList = expensesAttachService.getAllExpensesAttachment(expensesAttach.getHcmoExpensesAttachId());
        return SUCCESS;
    }

    @SkipValidation
    public String setUpInsertOrUpdateExpenseAttach() {
        if ((expensesAttach != null) && (expensesAttach.getHcmoExpensesAttachId() != null)) {
            expensesAttach = expensesAttachService.getExpensesAttachment(expensesAttach.getHcmoExpensesAttachId());
        }
        return SUCCESS;
    }

    public String insertOrUpdateExpensesAttach() {
        if (expensesAttach.getHcmoExpensesAttachId() == null) {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            expensesAttach.setCreated(DateUtils.getCurrentDateTime());
            expensesAttach.setCreatedBy(oEmp);
            expensesAttach.setUpdatedBy(oEmp);
            expensesAttach.setIsActive(1);
            expensesAttachService.insertExpensesAttachment(expensesAttach);
        } else {
            Map session = ActionContext.getContext().getSession();
            EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
            expensesAttach.setUpdatedBy(oEmp);
            expensesAttachService.updateExpensesAttachment(expensesAttach);

        }
        return SUCCESS;
    }

    @SkipValidation
    public String deleteExpensesAttachments() {
        Map session = ActionContext.getContext().getSession();
        expAttach.getHcmoExpensesAttachId();
        expAttach = expensesAttachService.getExpensesAttachment(expAttach.getHcmoExpensesAttachId());
        int expensesId = expAttach.getHcmoExpensesId().getHcmoExpensesId();
        int employeeId = expAttach.getHcmoEmployeeId().getEmployeeId();
        expensesAttachService.deleteExpensesAttachment(expAttach.getHcmoExpensesAttachId());

        String success = fileupload.deleteFile(expAttach.getExpensesAttachFileName(), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("ExpensesAttachments")
            + "/"
            + "MASTER_CLIENTID_"
            + session.get("MASTER_CLIENT_ID")
            + "/"
            + employeeId
            + "/"
            + expensesId + "/");

        if (success == "SUCCESS") {
            addActionError("Deleted Successfully");
        }
        expensesAttachList = expensesAttachService.getAllExpensesAttachment(expAttach.getHcmoExpensesId().getHcmoExpensesId());
        return SUCCESS;
    }

    @SkipValidation
    public String expensesFileDownload() throws Exception {
        Map session = ActionContext.getContext().getSession();
        expAttach = expensesAttachService.getExpensesAttachment(expAttach.getHcmoExpensesAttachId());
        inStream = new FileInputStream(new File(getText("ApplicationAbsolutePath")
            + ServletActionContext.getServletContext().getContextPath()
            + getText("ExpensesAttachments") + "/" + "MASTER_CLIENTID_"
            + session.get("MASTER_CLIENT_ID") + "/" + expAttach.getHcmoEmployeeId().getEmployeeId()
            + "/" + expAttach.getHcmoExpensesId().getHcmoExpensesId() + "/"
            + expAttach.getExpensesAttachFileName()));
        filename = expAttach.getExpensesAttachFileName();
        return SUCCESS;
    }

    public ExpensesAttachmentVO getExpensesAttach() {
        return expensesAttach;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public List<ExpensesAttachmentVO> getExpensesAttachList() {
        return expensesAttachList;
    }

    public void setExpensesAttachList(List<ExpensesAttachmentVO> expensesAttachList) {
        this.expensesAttachList = expensesAttachList;
    }

    public ExpensesAttachmentVO getExpAttach() {
        return expAttach;
    }

    public void setExpAttach(ExpensesAttachmentVO expAttach) {
        this.expAttach = expAttach;
    }

    public InputStream getInStream() {
        return inStream;
    }

    public void setInStream(InputStream inStream) {
        this.inStream = inStream;
    }

}