
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.TeamDao;
import com.gits.rms.persistence.TeamHibernateDao;
import com.gits.rms.vo.TeamVO;

public class TeamDaoService implements TeamService {

    private TeamDao dao;

    public TeamDaoService() {
        dao = new TeamHibernateDao();
    }

    @Override
    public List checkTeamInEmployees(TeamVO team) {
        return dao.checkTeamInEmployees(team);
    }

    @Override
    public void deleteTeam(TeamVO team) {
        dao.deleteTeam(team);
    }

    @Override
    public List getAllTeam() {
        return dao.getAllTeam();
    }

    @Override
    public TeamVO getTeam(Integer id) {
        return dao.getTeam(id);
    }

    @Override
    public void insertTeam(TeamVO team) {
        dao.insertTeam(team);
    }

    @Override
    public List teamSearchResult(TeamVO team) {
        return dao.teamSearchResult(team);
    }

    @Override
    public void updateTeam(TeamVO team) {
        dao.updateTeam(team);
    }

}
