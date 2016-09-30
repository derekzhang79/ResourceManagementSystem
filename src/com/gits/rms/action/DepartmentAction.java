
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.ConfigurationDaoService;
import com.gits.rms.service.ConfigurationService;
import com.gits.rms.service.DepartmentDaoService;
import com.gits.rms.service.DepartmentService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ConfigurationVO;
import com.gits.rms.vo.DepartmentVO;
import com.gits.rms.vo.EmployeesVO;

public class DepartmentAction extends ActionSupport {
    private static final long serialVersionUID = -4610201480762543660L;
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private DepartmentService deptService = new DepartmentDaoService();
    private List<DepartmentVO> deptList;
    private List<EmployeesVO> employeeList;
    private DepartmentVO dept;
    private ConfigurationVO config;
    private ConfigurationService configService=new ConfigurationDaoService();
    private List<ConfigurationVO> configList;
    Map mSession = ActionContext.getContext().getSession();
    EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
    
    // To get List of Departments
    @SkipValidation
    public String getAllDepartment() {
        deptList = deptService.getAllDepartment();
        return SUCCESS;
    }

    // To View the Department Search Form
    @SkipValidation
    public String deptSearchForm() {
        return SUCCESS;
    }

    // To Search Department
    @SkipValidation
    public String departmentSearchResult() {
        deptList = deptService.departmentSearchResult(dept);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Department it shows blank Form to enter New Data
    @SkipValidation
    public String setUpDepartment() {
        if ((dept != null) && (dept.getHcmoDepartmentId() != null)) {
            dept = deptService.getDepartment(dept.getHcmoDepartmentId());
        }
        return SUCCESS;
    }

    // To get Particular Department Data
    @SkipValidation
    public String departmentView() {
        if ((dept != null) && (dept.getHcmoDepartmentId() != null)) {
            dept = deptService.getDepartment(dept.getHcmoDepartmentId());
        }
        return SUCCESS;
    }

    // To insert new Department detail or Edit Particular Department Detail
    public String insertOrUpdateDepartment() {
        try {
            if (dept.getHcmoDepartmentId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                dept.setCreated(DateUtils.getCurrentDateTime());
                dept.setCreatedBy(oEmp);
                dept.setUpdatedBy(oEmp);
                dept.setIsActive(1);
                deptService.insertDepartment(dept);
                addActionMessage(getText("Added Successfully"));
            } else if (dept.getHcmoDepartmentId() != null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                dept.setUpdatedBy(oEmp);
                deptService.updateDepartment(dept);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        // For Drop down List
        loadValues.getAllDepartmentName();
        String configure=(String) mSession.get("CONFIGURATION");
        if(configure.equals("CONFIGURATION")){
            configList=configService.getAllConfiguration(oEmp.getClientId());
            if (!configList.isEmpty()){
                   for(int i=0;i<configList.size();i++){
                    ConfigurationVO configuration=configList.get(i);
                    config=configService.getConfiguration(configuration.getHcmoConfigurationId());
                }
                config.setStatus("department");
                config.setMailConfiguration(config.getMailConfiguration());
                config.setCreated(DateUtils.getCurrentDateTime());
                config.setCreatedBy(oEmp);
                config.setUpdatedBy(oEmp);
                config.setIsActive(1);
                configService.updateDepartmentConfiguration(config);
                
            }
        }
        return SUCCESS;
    }

    // To delete Particular Department Detail
    @SkipValidation
    public String deleteDepartment() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // To check that the Department is being used in the Employee.If Yes
        // then we are not allowing the Department to get delete.
        employeeList = deptService.checkDepartmentInEmployee(dept);
        if (!employeeList.isEmpty()) {
            addActionError(getText("label.header.department.msg.deleteEmployee"));
            return SUCCESS;
        }

        // To Delete Department.
        dept.setUpdatedBy(oEmp);
        deptService.deleteDepartment(dept);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllDepartmentName();
        return SUCCESS;
    }

    public List<DepartmentVO> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<DepartmentVO> deptList) {
        this.deptList = deptList;
    }

    public DepartmentVO getDept() {
        return dept;
    }

    public void setDept(DepartmentVO dept) {
        this.dept = dept;
    }

    public ConfigurationVO getConfig() {
        return config;
    }

    public void setConfig(ConfigurationVO config) {
        this.config = config;
    }

    public List<ConfigurationVO> getConfigList() {
        return configList;
    }

    public void setConfigList(List<ConfigurationVO> configList) {
        this.configList = configList;
    }
    
}

