
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;

public interface ProjectActivityDao {

    List getAllProjectActivity();

    ProjectActivityVO getProjectActivity(Integer id);

    void insertProjectActivity(ProjectActivityVO proActivity);

    void updateProjectActivity(ProjectActivityVO proActivity);

    void deleteProjectActivity(ProjectActivityVO proActivity);

    List projectActivitySearchResult(ProjectActivityVO proActivity);
    
    List getPrevAddedProjActivityByProj(Integer projectId);

    List getProjectActivityList(Integer ProjectId, Integer EmployeeId);

    List getAllProjectActivityCount(ProjectActivityVO proActivity);

    ProjectActivityVO getProjectActivity(String ActivityName);

    ProjectActivityVO getProjectAndActivity(String ActivityName, Integer projectId);

    ProjectActivityVO getProjectActivityCount(Integer id);

    ProjectActivityVO isProjectAndActivityExists(String ActivityName, Integer projectId, Integer EmployeeId);

    List getActivityList(Integer ProjectId, String ActivityName);

    ProjectActivityVO isExistProjectActivity(ProjectActivityVO projectActivity);
    
    List<ProjectActivityVO> getAllProjActivityByProject(ProjectVO projObj);
}
