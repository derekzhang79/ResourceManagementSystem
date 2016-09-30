
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ExpensesDetailsDao;
import com.gits.rms.persistence.ExpensesDetailsHibernateDao;
import com.gits.rms.vo.ExpensesDetailsVO;

public class ExpensesDetailsDaoService implements ExpensesDetailsService {
    private ExpensesDetailsDao dao;

    public ExpensesDetailsDaoService() {
        dao = new ExpensesDetailsHibernateDao();
    }

    @Override
    public void deleteExpDetails(Integer id) {
        dao.deleteExpensesDetails(id);
    }

    @Override
    public List getAllExpDetails() {
        return dao.getAllExpensesDetails();
    }

    @Override
    public ExpensesDetailsVO getExpDetails(Integer id) {
        return dao.getExpensesDetails(id);
    }

    @Override
    public List getExpensesEmpDetails(Integer id) {
        return dao.getExpensesEmpDetails(id);
    }

    @Override
    public void insertExpDetails(ExpensesDetailsVO expDetails) {
        dao.insertExpensesDetails(expDetails);
    }

    @Override
    public void updateExpDetails(ExpensesDetailsVO expDetails) {
        dao.updateExpensesDetails(expDetails);
    }
}