
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.ErrorsAction;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.service.TargetTypeDaoService;
import com.gits.rms.service.TargetTypeService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.TargetTypeVO;
import com.gits.rms.vo.TargetsVO;

public class TargetTypeAction extends ActionSupport {
    private static final long serialVersionUID = 6152621187441516841L;
    private TargetTypeService targetTypeService = new TargetTypeDaoService();
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();
    private List<TargetTypeVO> targetTypeList;
    private List<TargetsVO> targetList;
    private TargetTypeVO targetType;

    // To get List of Target Type
    @SkipValidation
    public String getAllTargetType() {
    	targetTypeList = targetTypeService.getAllTargetType();
        return SUCCESS;
    }

    // To View the Search Form
    @SkipValidation
    public String targetTypeSearchForm() {
        return SUCCESS;
    }

    // To Search Target Type
    @SkipValidation
    public String targetTypeSearchResult() {
    	targetTypeList = targetTypeService.targetTypeSearchResult(targetType);
        return SUCCESS;
    }

    // when click Edit link it bring particular data into Form or click Add
    // Target Type it shows blank Form to enter New Data
    @SkipValidation
    public String setUpTargetType() {
        if ((targetType != null) && (targetType.getHcmoTargetTypeId() != null)) {
        	targetType = targetTypeService.getTargetType(targetType.getHcmoTargetTypeId());
        }
        return SUCCESS;
    }

    // To get Particular Target Type Data of an Employee
    @SkipValidation
    public String targetTypeView() {
    	if ((targetType != null) && (targetType.getHcmoTargetTypeId() != null)) {
        	targetType = targetTypeService.getTargetType(targetType.getHcmoTargetTypeId());
        }
        return SUCCESS;
    }

    // To insert new Target Type detail or Edit Particular Target Type Detail
    public String insertOrUpdateTargetType() {
        try {
            if (targetType.getHcmoTargetTypeId() == null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                targetType.setCreated(DateUtils.getCurrentDateTime());
                targetType.setCreatedBy(oEmp);
                targetType.setUpdatedBy(oEmp);
                targetType.setIsActive(1);
                targetTypeService.insertTargetType(targetType);
                addActionMessage(getText("Added Successfully"));
            } else if (targetType.getHcmoTargetTypeId() != null) {
                Map session = ActionContext.getContext().getSession();
                EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
                targetType.setUpdatedBy(oEmp);
                targetTypeService.updateTargetType(targetType);
                addActionMessage(getText("Updated Successfully"));
            }
        } catch (RuntimeException e) {
            ErrorsAction errAction = new ErrorsAction();
            String sError = errAction.getError(e);
            addActionError(sError);
            throw e;
        }
        
        // For Drop down List
        loadValues.getAllTargetType();
        
        return SUCCESS;
    }

    // To delete Particular Target Type Detail
    @SkipValidation
    public String deleteTargetType() {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");

        // To check that the Target Type is being used in the Employee.If Yes
        // then we are not allowing the Target Type to get delete.
        targetList = targetTypeService.checkTargetTypeInTarget(targetType);
        
        if (!targetList.isEmpty()) {
            addActionError(getText("label.header.targetType.msg.deleteTarget"));
            return SUCCESS;
        }

        // To Delete nationality.
        targetType.setUpdatedBy(oEmp);
        targetTypeService.deleteTargetType(targetType);
        addActionMessage(getText("Deleted Successfully"));

        // For Drop down List
        loadValues.getAllTargetType();
        return SUCCESS;
    }

	public LoadKeyValuesAction getLoadValues() {
		return loadValues;
	}

	public void setLoadValues(LoadKeyValuesAction loadValues) {
		this.loadValues = loadValues;
	}

	public List<TargetTypeVO> getTargetTypeList() {
		return targetTypeList;
	}

	public void setTargetTypeList(List<TargetTypeVO> targetTypeList) {
		this.targetTypeList = targetTypeList;
	}

	public List<TargetsVO> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<TargetsVO> targetList) {
		this.targetList = targetList;
	}

	public TargetTypeVO getTargetType() {
		return targetType;
	}

	public void setTargetType(TargetTypeVO targetType) {
		this.targetType = targetType;
	}
}
