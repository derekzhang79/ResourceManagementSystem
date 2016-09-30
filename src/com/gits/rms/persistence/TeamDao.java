
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.TeamVO;

public interface TeamDao {

    List getAllTeam();

    TeamVO getTeam(Integer id);

    void insertTeam(TeamVO team);

    void updateTeam(TeamVO team);

    void deleteTeam(TeamVO team);

    List teamSearchResult(TeamVO team);

    List checkTeamInEmployees(TeamVO team);
}
