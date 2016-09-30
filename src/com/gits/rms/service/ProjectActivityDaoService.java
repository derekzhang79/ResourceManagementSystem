
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ProjectActivityDao;
import com.gits.rms.persistence.ProjectActivityHibernateDao;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;

public class ProjectActivityDaoService implements ProjectActivityService {

    private ProjectActivityDao dao;

    public ProjectActivityDaoService() {
        dao = new ProjectActivityHibernateDao();
    }

    @Override
    public void deleteProjectActivity(ProjectActivityVO proActivity) {
        dao.deleteProjectActivity(proActivity);
    }

    @Override
    public List getActivityList(Integer ProjectId, String ActivityName) {
        return dao.getActivityList(ProjectId, ActivityName);

    }

    @Override
    public List getAllProjectActivity() {
        return dao.getAllProjectActivity();
    }

    @Override
    public List getAllProjectActivityCount(ProjectActivityVO proActivity) {
        return dao.getAllProjectActivityCount(proActivity);
    }

    @Override
    public ProjectActivityVO getProjectActivity(Integer id) {
        return dao.getProjectActivity(id);
    }

    @Override
    public ProjectActivityVO getProjectActivity(String ActivityName) {
        return dao.getProjectActivity(ActivityName);
    }

    @Override
    public ProjectActivityVO getProjectActivityCount(Integer id) {
        return dao.getProjectActivityCount(id);
    }

    @Override
    public List getProjectActivityList(Integer ProjectId, Integer EmployeeId) {
        return dao.getProjectActivityList(ProjectId, EmployeeId);
    }

    @Override
    public ProjectActivityVO getProjectAndActivity(String ActivityName, Integer projectId) {
        return dao.getProjectAndActivity(ActivityName, projectId);
    }

    @Override
    public void insertProjectActivity(ProjectActivityVO proActivity) {
        dao.insertProjectActivity(proActivity);
    }

    @Override
    public ProjectActivityVO isExistProjectActivity(ProjectActivityVO projectActivity) {
        return dao.isExistProjectActivity(projectActivity);
    }

    @Override
    public ProjectActivityVO isProjectAndActivityExists(String ActivityName, Integer projectId, Integer EmployeeId) {
        return dao.isProjectAndActivityExists(ActivityName, projectId, EmployeeId);
    }

    @Override
    public List projectActivitySearchResult(ProjectActivityVO proActivity) {
        return dao.projectActivitySearchResult(proActivity);
    }
    
    @Override
    public List getPrevAddedProjActivityByProj(Integer projectId){
    	return dao.getPrevAddedProjActivityByProj(projectId);
    }

    @Override
    public void updateProjectActivity(ProjectActivityVO proActivity) {
        dao.updateProjectActivity(proActivity);
    }
    
    @Override
    public List<ProjectActivityVO> getAllProjActivityByProject(ProjectVO projObj) {
    	return dao.getAllProjActivityByProject(projObj);
    }
}
