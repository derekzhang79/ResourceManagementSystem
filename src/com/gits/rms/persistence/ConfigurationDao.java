package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.ChildrenVO;
import com.gits.rms.vo.ConfigurationVO;

public interface ConfigurationDao {
    
    void insertConfiguration(ConfigurationVO config);
    
    ConfigurationVO getConfiguration(Integer id);
    
    List getAllConfiguration(Integer clientId);
    
    void updateConfiguration(ConfigurationVO config);
    
    void updateClientConfiguration(ConfigurationVO config);
    
    void updateLocationConfiguration(ConfigurationVO config);
    
    void updateRegionConfiguration(ConfigurationVO config);
    
    void updateSalaryGradeConfiguration(ConfigurationVO config);
    
    void updateJobTitleConfiguration(ConfigurationVO config);
    
    void updateDepartmentConfiguration(ConfigurationVO config);
    
    void updateTeamConfiguration(ConfigurationVO config);
    
    void updateNationalityConfiguration(ConfigurationVO config);
    
    void updateEthnicRaceConfiguration(ConfigurationVO config);
    
    void updateEmployeeConfiguration(ConfigurationVO config);
    
    void updateStatus(ConfigurationVO config);
    
    void updateSkip(ConfigurationVO config);
}

