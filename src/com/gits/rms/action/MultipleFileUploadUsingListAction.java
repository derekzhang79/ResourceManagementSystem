
package com.gits.rms.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.utils.FileUploadUtil;

public class MultipleFileUploadUsingListAction extends ActionSupport {
    private static final long serialVersionUID = -7057539027280806698L;
    private List<File> uploads = new ArrayList<File>();
    private List<String> uploadFileNames = new ArrayList<String>();
    private List<String> uploadContentTypes = new ArrayList<String>();
    private FileUploadUtil fileupload = new FileUploadUtil();

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

    public String upload() throws Exception {

        int i = 0;
        for (File u : uploads) {
            fileupload.uploadFile(u.getAbsolutePath(), uploadFileNames.get(i), getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath(), getText("EmployeeImages")
                + "/");
            i++;
        }
        return SUCCESS;
    }
}
