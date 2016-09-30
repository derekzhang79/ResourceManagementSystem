
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ProjectActivityVO implements Serializable {

    private static final long serialVersionUID = -8563196668517843200L;
    private String activityName;
    private String activityNotes;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO empIdObj;
    private EmployeesVO projectOwnerEmpIdObj;
    private int isActive;
    private Integer projectActivityId;
    private ProjectVO proObj;
    private DepartmentVO departmentIdObj;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private String estimatedHours;
    private Date taskStartDate;
    private Date taskEndDate;
    private String description;

    public ProjectActivityVO() {
    }

	public ProjectActivityVO(String activityName, String activityNotes, Date created, EmployeesVO createdBy,
			EmployeesVO empIdObj, EmployeesVO projectOwnerEmpIdObj, int isActive, Integer projectActivityId,
			ProjectVO proObj, DepartmentVO departmentIdObj, Timestamp updated, EmployeesVO updatedBy,
			String estimatedHours, Date taskStartDate, Date taskEndDate, String description) {
		super();
		this.activityName = activityName;
		this.activityNotes = activityNotes;
		this.created = created;
		this.createdBy = createdBy;
		this.empIdObj = empIdObj;
		this.projectOwnerEmpIdObj = projectOwnerEmpIdObj;
		this.isActive = isActive;
		this.projectActivityId = projectActivityId;
		this.proObj = proObj;
		this.departmentIdObj = departmentIdObj;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.estimatedHours = estimatedHours;
		this.taskStartDate = taskStartDate;
		this.taskEndDate = taskEndDate;
		this.description = description;
	}




	public DepartmentVO getDepartmentIdObj() {
		return departmentIdObj;
	}

	public void setDepartmentIdObj(DepartmentVO departmentIdObj) {
		this.departmentIdObj = departmentIdObj;
	}

	public Date getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(Date taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public Date getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(Date taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActivityName() {
        return activityName;
    }

    public String getActivityNotes() {
        return activityNotes;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public EmployeesVO getEmpIdObj() {
        return empIdObj;
    }

    public int getIsActive() {
        return isActive;
    }

    public Integer getProjectActivityId() {
        return projectActivityId;
    }

    public ProjectVO getProObj() {
        return proObj;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setActivityNotes(String activityNotes) {
        this.activityNotes = activityNotes;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setProjectActivityId(Integer projectActivityId) {
        this.projectActivityId = projectActivityId;
    }

    public void setProObj(ProjectVO proObj) {
        this.proObj = proObj;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

	public String getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(String estimatedHours) {
		this.estimatedHours = estimatedHours;
	}


	public EmployeesVO getProjectOwnerEmpIdObj() {
		return projectOwnerEmpIdObj;
	}


	public void setProjectOwnerEmpIdObj(EmployeesVO projectOwnerEmpIdObj) {
		this.projectOwnerEmpIdObj = projectOwnerEmpIdObj;
	}
    
}
