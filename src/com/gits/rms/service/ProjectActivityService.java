
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;

public interface ProjectActivityService {

    void deleteProjectActivity(ProjectActivityVO proActivity);

    List getActivityList(Integer ProjectId, String ActivityName);

    List getAllProjectActivity();

    List getAllProjectActivityCount(ProjectActivityVO proActivity);

    ProjectActivityVO getProjectActivity(Integer id);

    ProjectActivityVO getProjectActivity(String ActivityName);

    ProjectActivityVO getProjectActivityCount(Integer id);

    List getProjectActivityList(Integer ProjectId, Integer EmployeeId);

    ProjectActivityVO getProjectAndActivity(String ActivityName, Integer projectId);

    void insertProjectActivity(ProjectActivityVO proActivity);

    ProjectActivityVO isExistProjectActivity(ProjectActivityVO projectActivity);

    ProjectActivityVO isProjectAndActivityExists(String ActivityName, Integer projectId, Integer EmployeeId);

    List projectActivitySearchResult(ProjectActivityVO proActivity);
    
    List getPrevAddedProjActivityByProj(Integer projectId);

    void updateProjectActivity(ProjectActivityVO proActivity);
    
    List<ProjectActivityVO> getAllProjActivityByProject(ProjectVO projObj);
}
