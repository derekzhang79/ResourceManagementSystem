
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.service.ImportantNewsDaoService;
import com.gits.rms.service.ImportantNewsService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ImportantNewsVO;

public class ImportantNewsAction extends ActionSupport {
    private static final long serialVersionUID = -2950416558073788181L;
    private ImportantNewsService impService = new ImportantNewsDaoService();
    private List<ImportantNewsVO> impList;
    private List<EmployeesVO> employeeList;
    private ImportantNewsVO important;

    // To get List of Important News
    @SkipValidation
    public String getAllImportantNews() {
        impList = impService.getAllImportantNews();
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Important News it shows blank Form to enter New Data
    @SkipValidation
    public String setUpImportantNews() {
        if ((important != null) && (important.getImportantNewsId() != null)) {
            important = impService.getImportantNews(important.getImportantNewsId());
        }
        return SUCCESS;
    }

    // To get Particular Important News Data
    @SkipValidation
    public String importantNewsView() {
        if ((important != null) && (important.getImportantNewsId() != null)) {
            important = impService.getImportantNews(important.getImportantNewsId());
        }
        return SUCCESS;
    }

    // To insert new Important News detail or Edit Particular Important News
    // Detail
    public String insertOrUpdateImportantNews() {
        try {
            if (important.getImportantNewsId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                important.setCreated(DateUtils.getCurrentDateTime());
                important.setEmpIdObj(oEmp);
                important.setCreatedBy(oEmp);
                important.setUpdatedBy(oEmp);
                important.setIsActive(1);
                impService.insertImportantNews(important);
                addActionMessage(getText("Added Successfully"));
            } else {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                important.setUpdatedBy(oEmp);
                important.setEmpIdObj(oEmp);
                impService.updateImportantNews(important);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        return SUCCESS;
    }

    // To delete Particular Important News Detail
    @SkipValidation
    public String deleteImportantNews() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // To Delete Important News.
        important.setUpdatedBy(oEmp);
        impService.deleteImportantNews(important);
        addActionMessage(getText("Deleted Successfully"));

        return SUCCESS;
    }

    // To View the Important News Search Form
    @SkipValidation
    public String importantNewsSearchForm() {
        return SUCCESS;
    }

    // To Search Important News
    @SkipValidation
    public String importantNewsSearchResult() {
        impList = impService.importantNewsSearchResult(important);
        return SUCCESS;
    }

    // To Change Show Messages Check box in List of Important News Page
    @SkipValidation
    public String makeChangesToShowMessage() {
        important = impService.getImportantNews(important.getImportantNewsId());
        if (important.isShowMessage() == false) {
            important.setShowMessage(true);
            impService.updateImportantNews(important);
        }
        return SUCCESS;
    }

    public List<ImportantNewsVO> getImpList() {
        return impList;
    }

    public void setImpList(List<ImportantNewsVO> impList) {
        this.impList = impList;
    }

    public List<EmployeesVO> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeesVO> employeeList) {
        this.employeeList = employeeList;
    }

    public ImportantNewsVO getImportant() {
        return important;
    }

    public void setImportant(ImportantNewsVO important) {
        this.important = important;
    }

}
