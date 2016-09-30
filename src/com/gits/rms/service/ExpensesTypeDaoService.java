
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ExpensesTypeDao;
import com.gits.rms.persistence.ExpensesTypeHibernateDao;
import com.gits.rms.vo.ExpensesTypeVO;

public class ExpensesTypeDaoService implements ExpensesTypeService {
    private ExpensesTypeDao dao;

    public ExpensesTypeDaoService() {
        dao = new ExpensesTypeHibernateDao();
    }

    @Override
    public void deleteExpensesType(ExpensesTypeVO expType) {
        dao.deleteExpensesType(expType);
    }

    @Override
    public List expensesTypeSearchResult(ExpensesTypeVO expType) {
        return dao.expensesTypeSearchResult(expType);
    }

    @Override
    public List getAllExpensesType() {
        return dao.getAllExpensesType();
    }

    @Override
    public ExpensesTypeVO getExpensesType(Integer id) {
        return dao.getExpensesType(id);
    }

    @Override
    public ExpensesTypeVO getExpensesTypeId(String sExpTYpeName) {
        return dao.getExpensesTypeId(sExpTYpeName);
    }

    @Override
    public List getExpensesTypesStartingWith(String sExpType) {
        return dao.getExpensesTypesStartingWith(sExpType);
    }

    @Override
    public void insertExpensesType(ExpensesTypeVO expType) {
        dao.insertExpensesType(expType);
    }

    @Override
    public void updateExpensesType(ExpensesTypeVO expType) {
        dao.updateExpensesType(expType);
    }
}