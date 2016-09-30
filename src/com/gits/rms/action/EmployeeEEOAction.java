
package com.gits.rms.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.EmployeeEEODaoService;
import com.gits.rms.service.EmployeeEEOService;
import com.gits.rms.service.EmployeesDaoService;
import com.gits.rms.service.EmployeesService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeEEOVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.utils.FileUploadUtil;

/**
 * @author Parveen
 *
 */
public class EmployeeEEOAction extends ActionSupport {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    static Logger log = Logger.getLogger(EmployeeEEOAction.class.getName());// for store log details
    private EmployeeEEOVO eeo;
    private List<EmployeeEEOVO> eeoList;
    private EmployeeEEOService eeoService = new EmployeeEEODaoService();
    private EmployeesService employeeService = new EmployeesDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private String ethnicProofFileFileName;
    private String veteranProofFileFileName;
    private String disabilityProofFileFileName;
    private String militaryProofFileFileName;
    private File ethnicProofFile;
    private File veteranProofFile;
    private File disabilityProofFile;
    private File militaryProofFile;

    
 // Based on EmployeeId get All EEO of that Employee
    @SkipValidation
    public String getEmployeeEEO() {
        Integer employeeId = Integer.valueOf(ServletActionContext.getRequest().getParameter("eeo.employee.employeeId"));
        if (employeeId == null) {
            employeeId = eeo.getEmployee().getEmployeeId();
        }
        eeoList = eeoService.getEmployeeAllEEO(employeeId);
        return SUCCESS;
    }
       
 // In the New Form when click Submit button To insert new eeo or
    // update particular eeo Data
    public String insertOrUpdateEEO() {
        try {
        	 log.debug("control enters into employee eeo details insertion");
        	 log.debug("eeo : " + eeo);
                if (eeo.getHcmoEEOId() == null) {
                	log.debug("eeo.getHcmoEEOId() == null");
                    EmployeesVO newAdminEmp = null;
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    /*if (ethnicProofFileFileName != null) {
                        fileupload.uploadFile(ethnicProofFile.getAbsolutePath(), ethnicProofFileFileName, getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath()
                            + "/", getText("EthnicAttachments") + "/" + "MASTER_CLIENTID_"
                            + session.get("MASTER_CLIENT_ID") + "/" + eeo.getEmployee().getEmployeeId() + "/");
                        eeo.setE(getEthnicProofFileFileName());
                    }
                    if (veteranProofFileFileName != null) {
                        fileupload.uploadFile(veteranProofFile.getAbsolutePath(), veteranProofFileFileName, getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath()
                            + "/", getText("VeteranAttachments") + "/" + "MASTER_CLIENTID_"
                            + session.get("MASTER_CLIENT_ID") + "/" + eeo.getEmployee().getEmployeeId() + "/");
                        eeo.setVeteranProofFile(getVeteranProofFileFileName());
                    }
                    if (militaryProofFileFileName != null) {
                        fileupload.uploadFile(militaryProofFile.getAbsolutePath(), militaryProofFileFileName, getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath()
                            + "/", getText("MilitaryAttachments") + "/" + "MASTER_CLIENTID_"
                            + session.get("MASTER_CLIENT_ID") + "/" + eeo.getEmployee().getEmployeeId() + "/");
                        eeo.setMilitaryProofFile(getMilitaryProofFileFileName());
                    }
                    if (disabilityProofFileFileName != null) {
                        fileupload.uploadFile(disabilityProofFile.getAbsolutePath(), disabilityProofFileFileName, getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath()
                            + "/", getText("DisabilityAttachments") + "/" + "MASTER_CLIENTID_"
                            + session.get("MASTER_CLIENT_ID") + "/" + eeo.getEmployee().getEmployeeId() + "/");
                        eeo.setDisabilityProofFile(getDisabilityProofFileFileName());
                    }*/
                    eeo.setCreated(DateUtils.getCurrentDateTime());
                    eeo.setCreatedBy(oEmp);
                    eeo.setUpdatedBy(oEmp);
                    log.debug("BEFORE INSERT");
                    eeoService.insertEmployeeEEO(eeo);
                    log.debug("AFTER INSERT");
                    addActionMessage(getText("Added Successfully"));
                    log.info("EEO details added successfully");
                } else {
                	log.debug("control enters into EEO details updation");
                    // To Update the EEO
                    EmployeesVO newAdminEmp = null;
                    Map session = ActionContext.getContext().getSession();
                    EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                    /*if (ethnicProofFileFileName != null) {
                        fileupload.uploadFile(ethnicProofFile.getAbsolutePath(), ethnicProofFileFileName, getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath()
                            + "/", getText("EthnicAttachments") + "/" + "MASTER_CLIENTID_"
                            + session.get("MASTER_CLIENT_ID") + "/" + eeo.getEmployee().getEmployeeId() + "/");
                        eeo.setEthnicProofFile(getEthnicProofFileFileName());
                    }
                    if (veteranProofFileFileName != null) {
                        fileupload.uploadFile(veteranProofFile.getAbsolutePath(), veteranProofFileFileName, getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath()
                            + "/", getText("VeteranAttachments") + "/" + "MASTER_CLIENTID_"
                            + session.get("MASTER_CLIENT_ID") + "/" + eeo.getEmployee().getEmployeeId() + "/");
                        eeo.setVeteranProofFile(getVeteranProofFileFileName());
                    }
                    if (militaryProofFileFileName != null) {
                        fileupload.uploadFile(militaryProofFile.getAbsolutePath(), militaryProofFileFileName, getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath()
                            + "/", getText("MilitaryAttachments") + "/" + "MASTER_CLIENTID_"
                            + session.get("MASTER_CLIENT_ID") + "/" + eeo.getEmployee().getEmployeeId() + "/");
                        eeo.setMilitaryProofFile(getMilitaryProofFileFileName());
                    }
                    if (disabilityProofFileFileName != null) {
                        fileupload.uploadFile(disabilityProofFile.getAbsolutePath(), disabilityProofFileFileName, getText("ApplicationAbsolutePath"), ServletActionContext.getServletContext().getContextPath()
                            + "/", getText("DisabilityAttachments") + "/" + "MASTER_CLIENTID_"
                            + session.get("MASTER_CLIENT_ID") + "/" + eeo.getEmployee().getEmployeeId() + "/");
                        eeo.setDisabilityProofFile(getDisabilityProofFileFileName());
                    }*/
                    eeo.setUpdatedBy(oEmp);
                    eeoService.updateEmployeeEEO(eeo);
                    addActionMessage(getText("Updated Successfully"));
                    log.info("EEO details updated successfully");
                }
            
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("Exception occurred during the EEO details insertion/updation" + e);
        }
        return SUCCESS;
}

	public EmployeesService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeesService employeeService) {
		this.employeeService = employeeService;
	}

	public LoadKeyValuesAction getLoadValues() {
		return loadValues;
	}

	public void setLoadValues(LoadKeyValuesAction loadValues) {
		this.loadValues = loadValues;
	}

	public File getEthnicProofFile() {
		return ethnicProofFile;
	}

	public void setEthnicProofFile(File ethnicProofFile) {
		this.ethnicProofFile = ethnicProofFile;
	}

	public File getVeteranProofFile() {
		return veteranProofFile;
	}

	public void setVeteranProofFile(File veteranProofFile) {
		this.veteranProofFile = veteranProofFile;
	}

	public File getDisabilityProofFile() {
		return disabilityProofFile;
	}

	public void setDisabilityProofFile(File disabilityProofFile) {
		this.disabilityProofFile = disabilityProofFile;
	}

	public File getMilitaryProofFile() {
		return militaryProofFile;
	}

	public void setMilitaryProofFile(File militaryProofFile) {
		this.militaryProofFile = militaryProofFile;
	}

	
	public EmployeeEEOService getEeoService() {
		return eeoService;
	}

	public void setEeoService(EmployeeEEOService eeoService) {
		this.eeoService = eeoService;
	}

	public String getEthnicProofFileFileName() {
		return ethnicProofFileFileName;
	}

	public void setEthnicProofFileFileName(String ethnicProofFileFileName) {
		this.ethnicProofFileFileName = ethnicProofFileFileName;
	}

	public String getVeteranProofFileFileName() {
		return veteranProofFileFileName;
	}

	public void setVeteranProofFileFileName(String veteranProofFileFileName) {
		this.veteranProofFileFileName = veteranProofFileFileName;
	}

	public String getDisabilityProofFileFileName() {
		return disabilityProofFileFileName;
	}

	public void setDisabilityProofFileFileName(String disabilityProofFileFileName) {
		this.disabilityProofFileFileName = disabilityProofFileFileName;
	}

	public String getMilitaryProofFileFileName() {
		return militaryProofFileFileName;
	}

	public void setMilitaryProofFileFileName(String militaryProofFileFileName) {
		this.militaryProofFileFileName = militaryProofFileFileName;
	}

	public EmployeeEEOVO getEeo() {
		return eeo;
	}

	public void setEeo(EmployeeEEOVO eeo) {
		this.eeo = eeo;
	}

	public List<EmployeeEEOVO> getEeoList() {
		return eeoList;
	}

	public void setEeoList(List<EmployeeEEOVO> eeoList) {
		this.eeoList = eeoList;
	}
}
