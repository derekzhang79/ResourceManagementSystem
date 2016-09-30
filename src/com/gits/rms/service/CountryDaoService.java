
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.CountryDao;
import com.gits.rms.persistence.CountryHibernateDao;
import com.gits.rms.vo.CountryVO;

public class CountryDaoService implements CountryService {
    private CountryDao dao;

    public CountryDaoService() {
        dao = new CountryHibernateDao();
    }

    @Override
    public List checkCountryInClient(CountryVO cou) {
        return dao.checkCountryInClient(cou);
    }

    @Override
    public List checkCountryInEmployees(CountryVO cou) {
        return dao.checkCountryInEmployees(cou);
    }

    @Override
    public List checkCountryInLocation(CountryVO cou) {
        return dao.checkCountryInLocation(cou);
    }

    @Override
    public List checkCountryInPassport(CountryVO cou) {
        return dao.checkCountryInPassport(cou);
    }

    @Override
    public List checkCountryInRegion(CountryVO cou) {
        return dao.checkCountryInRegion(cou);
    }

    @Override
    public List countrySearchResult(CountryVO cou) {
        return dao.countrySearchResult(cou);
    }

    @Override
    public void deleteCountry(CountryVO cou) {
        dao.deleteCountry(cou);
    }

    @Override
    public List getAllCountry() {
        return dao.getAllCountry();
    }

    @Override
    public List getCountriesStartingWith(String country) {
        return dao.getCountriesStartingWith(country);
    }

    @Override
    public CountryVO getCountry(Integer id) {
        return dao.getCountry(id);
    }

    @Override
    public void insertCountry(CountryVO cou) {
        dao.insertCountry(cou);
    }

    @Override
    public void updateCountry(CountryVO cou) {
        dao.updateCountry(cou);
    }
}
