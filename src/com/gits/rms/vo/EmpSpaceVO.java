
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class EmpSpaceVO implements Serializable {
    private static final long serialVersionUID = -1951586333700501654L;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO empIdObj;
    private List<EmployeesVO> empIdObjList;
    private Integer hcmoEmpSpaceId;
    private int isActive;
    private String sharedEmpEmailId;
    private String sharedEmpFirstName;
    private String sharedEmpIds;
    private String sharedEmpNames;
    private String sharedFileDesc;
    private String sharedFileTitle;
    private String spaceAttachFileName;
    private String type;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public EmpSpaceVO() {
    }

    public EmpSpaceVO(Integer hcmoEmpSpaceId, String spaceAttachFileName, String sharedEmpIds,
        String sharedFileTitle, String sharedFileDesc, Date created, Timestamp updated,
        int isActive, EmployeesVO updatedBy, EmployeesVO createdBy, EmployeesVO empIdObj,
        List<EmployeesVO> empIdObjList, String sharedEmpNames, int clientid,String sharedEmpFirstName,
        String sharedEmpEmailId, String type) {
        this.hcmoEmpSpaceId = hcmoEmpSpaceId;
        this.clientId=clientid;
        this.spaceAttachFileName = spaceAttachFileName;
        this.sharedEmpIds = sharedEmpIds;
        this.sharedFileTitle = sharedFileTitle;
        this.sharedFileDesc = sharedFileDesc;
        this.created = created;
        this.updated = updated;
        this.isActive = isActive;
        this.updatedBy = updatedBy;
        this.createdBy = createdBy;
        this.empIdObj = empIdObj;
        this.empIdObjList = empIdObjList;
        this.sharedEmpNames = sharedEmpNames;
        this.sharedEmpEmailId = sharedEmpEmailId;
        this.sharedEmpFirstName = sharedEmpFirstName;
        this.type = type;
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

    public List<EmployeesVO> getEmpIdObjList() {
        return empIdObjList;
    }

    public Integer getHcmoEmpSpaceId() {
        return hcmoEmpSpaceId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getSharedEmpEmailId() {
        return sharedEmpEmailId;
    }

    public String getSharedEmpFirstName() {
        return sharedEmpFirstName;
    }

    public String getSharedEmpIds() {
        return sharedEmpIds;
    }

    public String getSharedEmpNames() {
        return sharedEmpNames;
    }

    public String getSharedFileDesc() {
        return sharedFileDesc;
    }

    public String getSharedFileTitle() {
        return sharedFileTitle;
    }

    public String getSpaceAttachFileName() {
        return spaceAttachFileName;
    }

    public String getType() {
        return type;
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

    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
    }

    public void setEmpIdObjList(List<EmployeesVO> empIdObjList) {
        this.empIdObjList = empIdObjList;
    }

    public void setHcmoEmpSpaceId(Integer hcmoEmpSpaceId) {
        this.hcmoEmpSpaceId = hcmoEmpSpaceId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setSharedEmpEmailId(String sharedEmpEmailId) {
        this.sharedEmpEmailId = sharedEmpEmailId;
    }

    public void setSharedEmpFirstName(String sharedEmpFirstName) {
        this.sharedEmpFirstName = sharedEmpFirstName;
    }

    public void setSharedEmpIds(String sharedEmpIds) {
        this.sharedEmpIds = sharedEmpIds;
    }

    public void setSharedEmpNames(String sharedEmpNames) {
        this.sharedEmpNames = sharedEmpNames;
    }

    public void setSharedFileDesc(String sharedFileDesc) {
        this.sharedFileDesc = sharedFileDesc;
    }

    public void setSharedFileTitle(String sharedFileTitle) {
        this.sharedFileTitle = sharedFileTitle;
    }

    public void setSpaceAttachFileName(String spaceAttachFileName) {
        this.spaceAttachFileName = spaceAttachFileName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}