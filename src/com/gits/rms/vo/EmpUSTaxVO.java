
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class EmpUSTaxVO implements Serializable {

    private static final long serialVersionUID = 1621402849176129173L;
    private Date created;
    private EmployeesVO createdBy;
    private EmployeesVO hcmoEmployeeId;
    private Integer hcmoEmpUsTaxId;
    private int isActive;
    private String taxFederalExceptions;
    private String taxFederalStatus;
    private RegionVO taxState;
    private String taxStateExceptions;
    private String taxStateStatus;
    private RegionVO taxUnempState;
    private RegionVO taxWorkState;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }
    public EmpUSTaxVO() {
    }

    public EmpUSTaxVO(Integer hcmoEmpUsTaxId, EmployeesVO hcmoEmployeeId, String taxFederalStatus,
        String taxFederalExceptions, RegionVO taxState, String taxStateStatus,
        String taxStateExceptions, RegionVO taxUnempState, RegionVO taxWorkState, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,int clientid) {
        super();
        this.hcmoEmpUsTaxId = hcmoEmpUsTaxId;
        this.clientId=clientid;
        this.hcmoEmployeeId = hcmoEmployeeId;
        this.taxFederalStatus = taxFederalStatus;
        this.taxFederalExceptions = taxFederalExceptions;
        this.taxState = taxState;
        this.taxStateStatus = taxStateStatus;
        this.taxStateExceptions = taxStateExceptions;
        this.taxUnempState = taxUnempState;
        this.taxWorkState = taxWorkState;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public EmployeesVO getHcmoEmployeeId() {
        return hcmoEmployeeId;
    }

    public Integer getHcmoEmpUsTaxId() {
        return hcmoEmpUsTaxId;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getTaxFederalExceptions() {
        return taxFederalExceptions;
    }

    public String getTaxFederalStatus() {
        return taxFederalStatus;
    }

    public RegionVO getTaxState() {
        return taxState;
    }

    public String getTaxStateExceptions() {
        return taxStateExceptions;
    }

    public String getTaxStateStatus() {
        return taxStateStatus;
    }

    public RegionVO getTaxUnempState() {
        return taxUnempState;
    }

    public RegionVO getTaxWorkState() {
        return taxWorkState;
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

    public void setHcmoEmployeeId(EmployeesVO hcmoEmployeeId) {
        this.hcmoEmployeeId = hcmoEmployeeId;
    }

    public void setHcmoEmpUsTaxId(Integer hcmoEmpUsTaxId) {
        this.hcmoEmpUsTaxId = hcmoEmpUsTaxId;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setTaxFederalExceptions(String taxFederalExceptions) {
        this.taxFederalExceptions = taxFederalExceptions;
    }

    public void setTaxFederalStatus(String taxFederalStatus) {
        this.taxFederalStatus = taxFederalStatus;
    }

    public void setTaxState(RegionVO taxState) {
        this.taxState = taxState;
    }

    public void setTaxStateExceptions(String taxStateExceptions) {
        this.taxStateExceptions = taxStateExceptions;
    }

    public void setTaxStateStatus(String taxStateStatus) {
        this.taxStateStatus = taxStateStatus;
    }

    public void setTaxUnempState(RegionVO taxUnempState) {
        this.taxUnempState = taxUnempState;
    }

    public void setTaxWorkState(RegionVO taxWorkState) {
        this.taxWorkState = taxWorkState;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

}
