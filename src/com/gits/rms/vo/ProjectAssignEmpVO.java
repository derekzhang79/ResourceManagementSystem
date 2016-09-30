
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Balaguru
 *
 */

public class ProjectAssignEmpVO implements Serializable {

    private static final long serialVersionUID = 5377661033574044273L;
	private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO employeeName;
    private EmployeesVO projOwnerEmpIdObj;
    private CustomerVO customerIdObj;
    private DepartmentVO departmentIdObj;
    private int isActive;
    private int hcmoclientid;
    private List message = new LinkedList();
    private ProjectActivityVO projectActivityId;
    private Integer projectAssignEmpId;
    private Date projectEndDate;
    private Date projectEndDateEnds;
    private String projectEndDateValue;
    private ProjectVO projectName;
    private Date projectStartDate;
    private Date projectStartDateEnds;
    private String projectStartDateValue;
    private String projectWithProActivity;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private String projTargetName;
    private String projTargetType;
    private String projTargetMode;
    private String projGoalName;
    private String description;
    private String allocatedHours;
    private int isBillable;

	
	public EmployeesVO getProjOwnerEmpIdObj() {
		return projOwnerEmpIdObj;
	}
	public void setProjOwnerEmpIdObj(EmployeesVO projOwnerEmpIdObj) {
		this.projOwnerEmpIdObj = projOwnerEmpIdObj;
	}
	public int getHcmoclientid() {
		return hcmoclientid;
	}
	public void setHcmoclientid(int hcmoclientid) {
		this.hcmoclientid = hcmoclientid;
	}
	public CustomerVO getCustomerIdObj() {
		return customerIdObj;
	}
	public void setCustomerIdObj(CustomerVO customerIdObj) {
		this.customerIdObj = customerIdObj;
	}
	public DepartmentVO getDepartmentIdObj() {
		return departmentIdObj;
	}
	public void setDepartmentIdObj(DepartmentVO departmentIdObj) {
		this.departmentIdObj = departmentIdObj;
	}
	public int getIsBillable() {
		return isBillable;
	}
	public void setIsBillable(int isBillable) {
		this.isBillable = isBillable;
	}

	private List<EmployeesVO> empIdObjList = new LinkedList<EmployeesVO>();
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public ProjectAssignEmpVO() {
    	
    }

    
    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public EmployeesVO getEmployeeName() {
        return employeeName;
    }

    public int getIsActive() {
        return isActive;
    }

    public List getMessage() {
        return message;
    }

    public ProjectActivityVO getProjectActivityId() {
        return projectActivityId;
    }

    public Integer getProjectAssignEmpId() {
        return projectAssignEmpId;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public Date getProjectEndDateEnds() {
        return projectEndDateEnds;
    }

    public String getProjectEndDateValue() {
        return projectEndDateValue;
    }

    public ProjectVO getProjectName() {
        return projectName;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public Date getProjectStartDateEnds() {
        return projectStartDateEnds;
    }

    public String getProjectStartDateValue() {
        return projectStartDateValue;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setEmployeeName(EmployeesVO employeeName) {
        this.employeeName = employeeName;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setMessage(List message) {
        this.message = message;
    }

    public void setProjectActivityId(ProjectActivityVO projectActivityId) {
        this.projectActivityId = projectActivityId;
    }

    public void setProjectAssignEmpId(Integer projectAssignEmpId) {
        this.projectAssignEmpId = projectAssignEmpId;

    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public void setProjectEndDateEnds(Date projectEndDateEnds) {
        this.projectEndDateEnds = projectEndDateEnds;
    }

    public void setProjectEndDateValue(String projectEndDateValue) {
        this.projectEndDateValue = projectEndDateValue;
    }

    public void setProjectName(ProjectVO projectName) {
        this.projectName = projectName;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public void setProjectStartDateEnds(Date projectStartDateEnds) {
        this.projectStartDateEnds = projectStartDateEnds;
    }

    public void setProjectStartDateValue(String projectStartDateValue) {
        this.projectStartDateValue = projectStartDateValue;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

	public String getProjectWithProActivity() {
		return projectWithProActivity;
	}

	public void setProjectWithProActivity(String projectWithProActivity) {
		this.projectWithProActivity = projectWithProActivity;
	}

	public String getProjTargetName() {
		return projTargetName;
	}

	public void setProjTargetName(String projTargetName) {
		this.projTargetName = projTargetName;
	}

	public String getProjTargetType() {
		return projTargetType;
	}

	public void setProjTargetType(String projTargetType) {
		this.projTargetType = projTargetType;
	}

	public String getProjTargetMode() {
		return projTargetMode;
	}

	public void setProjTargetMode(String projTargetMode) {
		this.projTargetMode = projTargetMode;
	}

	public String getProjGoalName() {
		return projGoalName;
	}

	public void setProjGoalName(String projGoalName) {
		this.projGoalName = projGoalName;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<EmployeesVO> getEmpIdObjList() {
		return empIdObjList;
	}

	public void setEmpIdObjList(List<EmployeesVO> empIdObjList) {
		this.empIdObjList = empIdObjList;
	}
	
	public String getAllocatedHours() {
		return allocatedHours;
	}
	public void setAllocatedHours(String allocatedHours) {
		this.allocatedHours = allocatedHours;
	}
	
	public ProjectAssignEmpVO(Date created, EmployeesVO createdBy, EmployeesVO employeeName,
			EmployeesVO projOwnerEmpIdObj, CustomerVO customerIdObj, DepartmentVO departmentIdObj, int isActive,
			int hcmoclientid, List message, ProjectActivityVO projectActivityId, Integer projectAssignEmpId,
			Date projectEndDate, Date projectEndDateEnds, String projectEndDateValue, ProjectVO projectName,
			Date projectStartDate, Date projectStartDateEnds, String projectStartDateValue,
			String projectWithProActivity, Timestamp updated, EmployeesVO updatedBy, String projTargetName,
			String projTargetType, String projTargetMode, String projGoalName, String description,
			String allocatedHours, int isBillable,
			List<EmployeesVO> empIdObjList, int clientId) {
		
		super();
		System.out.println(" IN   *** ProjectAssignEmpVO *** CONSTRUCTOR");
		this.created = created;
		this.createdBy = createdBy;
		this.employeeName = employeeName;
		this.projOwnerEmpIdObj = projOwnerEmpIdObj;
		this.customerIdObj = customerIdObj;
		this.departmentIdObj = departmentIdObj;
		this.isActive = isActive;
		this.hcmoclientid = hcmoclientid;
		this.message = message;
		this.projectActivityId = projectActivityId;
		this.projectAssignEmpId = projectAssignEmpId;
		this.projectEndDate = projectEndDate;
		this.projectEndDateEnds = projectEndDateEnds;
		this.projectEndDateValue = projectEndDateValue;
		this.projectName = projectName;
		this.projectStartDate = projectStartDate;
		this.projectStartDateEnds = projectStartDateEnds;
		this.projectStartDateValue = projectStartDateValue;
		this.projectWithProActivity = projectWithProActivity;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.projTargetName = projTargetName;
		this.projTargetType = projTargetType;
		this.projTargetMode = projTargetMode;
		this.projGoalName = projGoalName;
		this.description = description;
		this.allocatedHours = allocatedHours;
		this.isBillable = isBillable;
		this.empIdObjList = empIdObjList;
		this.clientId = clientId;
	}
	
}