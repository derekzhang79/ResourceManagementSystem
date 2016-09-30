
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.EthnicRaceDao;
import com.gits.rms.persistence.EthnicRaceHibernateDao;
import com.gits.rms.vo.EthnicRaceVO;

public class EthnicRaceDaoService implements EthnicRaceService {

    private EthnicRaceDao dao;

    public EthnicRaceDaoService() {
        dao = new EthnicRaceHibernateDao();
    }

    @Override
    public List checkEthnicRaceInEmployee(EthnicRaceVO ethRace) {
        return dao.checkEthnicRaceInEmployee(ethRace);
    }

    @Override
    public void deleteEthnicRace(EthnicRaceVO ethRace) {
        dao.deleteEthnicRace(ethRace);
    }

    @Override
    public List ethnicRaceSearchResult(EthnicRaceVO ethRace) {
        return dao.ethnicRaceSearchResult(ethRace);
    }

    @Override
    public List getAllEthnicRace() {
        return dao.getAllEthnicRace();
    }

    @Override
    public EthnicRaceVO getEthnicRace(Integer id) {
        return dao.getEthnicRace(id);
    }

    @Override
    public void insertEthnicRace(EthnicRaceVO ethRace) {
        dao.insertEthnicRace(ethRace);
    }

    @Override
    public void updateEthnicRace(EthnicRaceVO ethRace) {
        dao.updateEthnicRace(ethRace);
    }
}
