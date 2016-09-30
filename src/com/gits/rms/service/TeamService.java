
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.TeamVO;

public interface TeamService {

    List checkTeamInEmployees(TeamVO team);

    void deleteTeam(TeamVO team);

    List getAllTeam();

    TeamVO getTeam(Integer id);

    void insertTeam(TeamVO team);

    List teamSearchResult(TeamVO team);

    void updateTeam(TeamVO team);
}
