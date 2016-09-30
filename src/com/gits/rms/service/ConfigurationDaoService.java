package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ConfigurationDao;
import com.gits.rms.persistence.ConfigurationHibernateDao;
import com.gits.rms.vo.ConfigurationVO;

public class ConfigurationDaoService implements ConfigurationService{
    private ConfigurationDao dao;

    public ConfigurationDaoService() {
        dao = new ConfigurationHibernateDao();
    }
    
    @Override
    public void insertConfiguration(ConfigurationVO config) {
        dao.insertConfiguration(config);
    }
    
    @Override
    public ConfigurationVO getConfiguration(Integer id){
        return dao.getConfiguration(id);
    }
    
    @Override
    public List getAllConfiguration(Integer clientId){
        return dao.getAllConfiguration(clientId);
    }
    
    @Override
    public void updateConfiguration(ConfigurationVO config){
        dao.updateConfiguration(config);
    }
    
    @Override
    public void updateClientConfiguration(ConfigurationVO config){
        dao.updateClientConfiguration(config);
    }
    
    @Override
    public void updateLocationConfiguration(ConfigurationVO config){
        dao.updateLocationConfiguration(config);
    }
    
    @Override
    public void updateRegionConfiguration(ConfigurationVO config){
        dao.updateRegionConfiguration(config);
    }
    
    @Override
    public void updateSalaryGradeConfiguration(ConfigurationVO config){
        dao.updateSalaryGradeConfiguration(config);
    }
    
    @Override
    public void updateJobTitleConfiguration(ConfigurationVO config){
        dao.updateJobTitleConfiguration(config);
    }
    
    @Override
    public void updateDepartmentConfiguration(ConfigurationVO config){
        dao.updateDepartmentConfiguration(config);
    }
    
    @Override
    public void updateTeamConfiguration(ConfigurationVO config){
        dao.updateTeamConfiguration(config);
    }
    
    @Override
    public void updateNationalityConfiguration(ConfigurationVO config){
        dao.updateNationalityConfiguration(config);
    }
    
    @Override
    public void updateEthnicRaceConfiguration(ConfigurationVO config){
        dao.updateEthnicRaceConfiguration(config);
    }
    
    @Override
    public void updateEmployeeConfiguration(ConfigurationVO config){
        dao.updateEmployeeConfiguration(config);
    }
    
    @Override
    public void updateStatus(ConfigurationVO config){
        dao.updateStatus(config);
    }
    
    @Override
    public void updateSkip(ConfigurationVO config){
        dao.updateSkip(config);
    }
}
