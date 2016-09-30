package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class ConfigurationVO implements Serializable{

    private static final long serialVersionUID = 4408790929338028386L;
    private Integer hcmoConfigurationId;
    private String status;
    private int mailConfiguration;
    private int client;
    private int location;
    private int region;
    private int salaryGrade;
    private int jobTitle;
    private int department;
    private int team;
    private int nationality;
    private int ethnicRace;
    private int employee;
    private int skip;
    private Date created;
    private EmployeesVO createdBy;
    private Timestamp updated;
    private EmployeesVO updatedBy;
    private int isActive;
    private Integer clientId;  
    public Integer getClientId() {
      return clientId;
     }
    public void setClientId(Integer clientId) {
      this.clientId = clientId;
     }
    
    public ConfigurationVO() {

    }

    public ConfigurationVO(Integer hcmoConfigurationId, String status, int mailConfiguration, int client, int location, int region, int salaryGrade, 
        int jobTitle, int department, int team, int nationality, int ethnicRace, int employee, int skip, Date created,
        EmployeesVO createdBy, Timestamp updated, EmployeesVO updatedBy, int isActive,Integer clientId) {
        super();
        this.hcmoConfigurationId = hcmoConfigurationId;
        this.mailConfiguration = mailConfiguration;
        this.client=client;
        this.location=location;
        this.region=region;
        this.salaryGrade=salaryGrade;
        this.jobTitle=jobTitle;
        this.department=department;
        this.clientId=clientId;
        this.team=team;
        this.nationality=nationality;
        this.ethnicRace=ethnicRace;
        this.employee=employee; 
        this.skip=skip;
        this.status=status;
        this.created = created;
        this.createdBy = createdBy;
        this.updated = updated;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }

    public Integer getHcmoConfigurationId() {
        return hcmoConfigurationId;
    }

    public void setHcmoConfigurationId(Integer hcmoConfigurationId) {
        this.hcmoConfigurationId = hcmoConfigurationId;
    }

    public int getMailConfiguration() {
        return mailConfiguration;
    }

    public void setMailConfiguration(int mailConfiguration) {
        this.mailConfiguration = mailConfiguration;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public EmployeesVO getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(EmployeesVO createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public EmployeesVO getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(EmployeesVO updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public int getSalaryGrade() {
        return salaryGrade;
    }

    public void setSalaryGrade(int salaryGrade) {
        this.salaryGrade = salaryGrade;
    }

    public int getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(int jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getNationality() {
        return nationality;
    }

    public void setNationality(int nationality) {
        this.nationality = nationality;
    }

    public int getEthnicRace() {
        return ethnicRace;
    }

    public void setEthnicRace(int ethnicRace) {
        this.ethnicRace = ethnicRace;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }
    
    
    
}
