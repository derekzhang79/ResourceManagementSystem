
package com.gits.rms.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.action.utils.LoadKeyValuesAction;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;

public class TimeSheetProjectActivityAction extends ActionSupport {
	private static final long serialVersionUID = 3855548561285064991L;
    private int projectId;
    private List<ProjectActivityVO> projActivityList;
    private LoadKeyValuesAction loadValues = new LoadKeyValuesAction();

    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) session.get("EMPLOYEE_OBJECT");
        int employeeId = oEmp.getEmployeeId();
       
        loadValues.getProjectActivity(projectId, employeeId);
       
        return SUCCESS;

    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public List<ProjectActivityVO> getProjActivityList() {
        return projActivityList;
    }

    public void setProjActivityList(List<ProjectActivityVO> projActivityList) {
        this.projActivityList = projActivityList;
    }

}
