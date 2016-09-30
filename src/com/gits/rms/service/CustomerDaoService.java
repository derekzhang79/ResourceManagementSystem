
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.CustomerDao;
import com.gits.rms.persistence.CustomerHiernateDao;
import com.gits.rms.vo.CustomerVO;

public class CustomerDaoService implements CustomerService {

    private CustomerDao dao;

    public CustomerDaoService() {
        dao = new CustomerHiernateDao();
    }

    @Override
    public Integer checkCustomerEmailExists(CustomerVO cust) {
        return dao.checkCustomerEmailExists(cust);
    }

    @Override
    public List checkCustomerInProject(CustomerVO cust) {
        return dao.checkCustomerInProject(cust);
    }

    @Override
    public List customerSearchResult(CustomerVO cust) {
        return dao.customerSearchResult(cust);
    }

    @Override
    public void deleteCustomer(CustomerVO cust) {
        dao.deleteCustomer(cust);
    }

    @Override
    public List getAllCustomer() {
        return dao.getAllCustomer();
    }

    @Override
    public CustomerVO getCustomer(Integer id) {
        return dao.getCustomer(id);
    }

    @Override
    public void insertCustomer(CustomerVO cust) {
        dao.insertCustomer(cust);
    }

    @Override
    public void updateCustomer(CustomerVO cust) {
        dao.updateCustomer(cust);
    }
}
