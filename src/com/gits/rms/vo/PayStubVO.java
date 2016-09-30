
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class PayStubVO implements Serializable {

    private static final long serialVersionUID = 2464211591865157833L;
    private Date created;
    private EmployeesVO createdBy;
    private Date declarationDate;
    private EmployeesVO employee;
    private Double grossSalary;
    private int isActive;
    private Double netSalary;
    private Integer payStubId;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;
    private String empType;
    private Integer payFreq;
    private Double commission;
    private Double bonus;
    private Double regularRate;
    private Double overtimeRate;
    private Integer isBenefit;
    private String paymentType;
    private String role;
    private Date payDate;
    private String firstDay;
    
	public Double getRegularRate() {
		return regularRate;
	}
	public void setRegularRate(Double regularRate) {
		this.regularRate = regularRate;
	}
	public Double getOvertimeRate() {
		return overtimeRate;
	}
	public void setOvertimeRate(Double overtimeRate) {
		this.overtimeRate = overtimeRate;
	}
	
	public Integer getIsBenefit() {
		return isBenefit;
	}

	public void setIsBenefit(Integer isBenefit) {
		this.isBenefit = isBenefit;
	}

	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public PayStubVO() {
    }

	public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public Date getDeclarationDate() {
        return declarationDate;
    }

    public EmployeesVO getEmployee() {
        return employee;
    }

    public Double getGrossSalary() {
        return grossSalary;
    }

    public int getIsActive() {
        return isActive;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public Integer getPayStubId() {
        return payStubId;
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

    public void setDeclarationDate(Date declarationDate) {
        this.declarationDate = declarationDate;
    }

    public void setEmployee(EmployeesVO employee) {
        this.employee = employee;
    }

    public void setGrossSalary(Double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public void setPayStubId(Integer payStubId) {
        this.payStubId = payStubId;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	
	public Double getCommission() {
		return commission;
	}
	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(String firstDay) {
		this.firstDay = firstDay;
	}
	public Integer getPayFreq() {
		return payFreq;
	}
	public void setPayFreq(Integer payFreq) {
		this.payFreq = payFreq;
	}
	public PayStubVO(Date created, EmployeesVO createdBy, Date declarationDate, EmployeesVO employee,
			Double grossSalary, int isActive, Double netSalary, Integer payStubId, Timestamp updated,
			EmployeesVO updatedBy, int clientId, String empType, Integer payFreq, Double commission, Double bonus,
			Double regularRate, Double overtimeRate, Integer isBenefit, String paymentType, String role, Date payDate,
			String firstDay) {
		super();
		this.created = created;
		this.createdBy = createdBy;
		this.declarationDate = declarationDate;
		this.employee = employee;
		this.grossSalary = grossSalary;
		this.isActive = isActive;
		this.netSalary = netSalary;
		this.payStubId = payStubId;
		this.updated = updated;
		this.updatedBy = updatedBy;
		this.clientId = clientId;
		this.empType = empType;
		this.payFreq = payFreq;
		this.commission = commission;
		this.bonus = bonus;
		this.regularRate = regularRate;
		this.overtimeRate = overtimeRate;
		this.isBenefit = isBenefit;
		this.paymentType = paymentType;
		this.role = role;
		this.payDate = payDate;
		this.firstDay = firstDay;
	}
	
	
}
