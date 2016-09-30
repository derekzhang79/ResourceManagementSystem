
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.CurrencyDao;
import com.gits.rms.persistence.CurrencyHibernateDao;
import com.gits.rms.vo.CurrencyVO;

public class CurrencyDaoService implements CurrencyService {

    private CurrencyDao dao;

    public CurrencyDaoService() {
        dao = new CurrencyHibernateDao();
    }

    @Override
    public List currencySearchResult(CurrencyVO currency) {
        return dao.currencySearchResult(currency);
    }

    @Override
    public void deleteCurrency(CurrencyVO currency) {
        dao.deleteCurrency(currency);
    }

    @Override
    public List getAllCurrency() {
        return dao.getAllCurrency();
    }

    @Override
    public CurrencyVO getCurrency(Integer id) {
        return dao.getCurrency(id);
    }

    @Override
    public String getCurrencyType() {
        return dao.getCurrencyType();
    }

    @Override
    public void insertCurrency(CurrencyVO currency) {
        dao.insertCurrency(currency);
    }

    @Override
    public void updateCurrency(CurrencyVO currency) {
        dao.updateCurrency(currency);
    }

}
