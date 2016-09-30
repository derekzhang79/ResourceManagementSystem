
package com.gits.rms.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class DirectDebitVO implements Serializable {

    private static final long serialVersionUID = 2832517103803975950L;
    private String account;
    private String accountType;
    private BigDecimal amount;
    private Date created;
    private EmployeesVO createdBy;
    private String curTypeValueForAmountField;
    private Integer empDirectDebitId;
    private EmployeesVO empIdObj;
    private int isActive;
    private boolean preAccount;
    private String preAccountValue;
    private Integer routingNum;
    private String transactionType;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int clientId;  
    public int getClientId() {
      return clientId;
     }
    public void setClientId(int clientId) {
      this.clientId = clientId;
     }

    public DirectDebitVO() {
    }

    public DirectDebitVO(int clientid,Integer empDirectDebitId, Integer routingNum, String account,
        BigDecimal amount, String accountType, String transactionType, EmployeesVO empIdObj,
        Date created, EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy,
        int isActive, boolean preAccount, String preAccountValue, String curTypeValueForAmountField) {
        this.empDirectDebitId = empDirectDebitId;
        this.routingNum = routingNum;
        this.clientId=clientid;
        this.account = account;
        this.amount = amount;
        this.accountType = accountType;
        this.transactionType = transactionType;
        this.empIdObj = empIdObj;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
        this.preAccount = preAccount;
        this.preAccountValue = preAccountValue;
        this.curTypeValueForAmountField = curTypeValueForAmountField;
    }

    public String getAccount() {
        return account;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getCreated() {
        return created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public String getCurTypeValueForAmountField() {
        return curTypeValueForAmountField;
    }

    public Integer getEmpDirectDebitId() {
        return empDirectDebitId;
    }

    public EmployeesVO getEmpIdObj() {
        return empIdObj;
    }

    public int getIsActive() {
        return isActive;
    }

    public String getPreAccountValue() {
        return preAccountValue;
    }

    public Integer getRoutingNum() {
        return routingNum;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public boolean isPreAccount() {
        return preAccount;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public void setCurTypeValueForAmountField(String curTypeValueForAmountField) {
        this.curTypeValueForAmountField = curTypeValueForAmountField;
    }

    public void setEmpDirectDebitId(Integer empDirectDebitId) {
        this.empDirectDebitId = empDirectDebitId;
    }

    public void setEmpIdObj(EmployeesVO empIdObj) {
        this.empIdObj = empIdObj;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setPreAccount(boolean preAccount) {
        this.preAccount = preAccount;
    }

    public void setPreAccountValue(String preAccountValue) {
        this.preAccountValue = preAccountValue;
    }

    public void setRoutingNum(Integer routingNum) {
        this.routingNum = routingNum;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }
}
