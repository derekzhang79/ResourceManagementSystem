
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.NationalityDao;
import com.gits.rms.persistence.NationalityHibernateDao;
import com.gits.rms.vo.NationalityVO;

public class NationalityDaoService implements NationalityService {

    private NationalityDao dao;

    public NationalityDaoService() {
        dao = new NationalityHibernateDao();
    }

    @Override
    public List checkNationalityInEmployee(NationalityVO nati) {
        return dao.checkNationalityInEmployee(nati);
    }

    @Override
    public void deleteNationality(NationalityVO nati) {
        dao.deleteNationality(nati);
    }

    @Override
    public List getAllNationality() {
        return dao.getAllNationality();
    }

    @Override
    public List getAllNationalityCronJob(String clientId) {
    	return dao.getAllNationalityCronJob(clientId);
    }

    @Override
    public NationalityVO getNationality(Integer id) {
        return dao.getNationality(id);
    }

    @Override
    public void insertNationality(NationalityVO nati) {
        dao.insertNationality(nati);
    }

    @Override
    public List nationalitySearchResult(NationalityVO nati) {
        return dao.nationalitySearchResult(nati);
    }

    @Override
    public void updateNationality(NationalityVO nati) {
        dao.updateNationality(nati);
    }

}
