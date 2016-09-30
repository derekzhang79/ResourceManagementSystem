
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.CustomerVO;

public interface CustomerService {

    Integer checkCustomerEmailExists(CustomerVO cust);

    List checkCustomerInProject(CustomerVO cust);

    List customerSearchResult(CustomerVO cust);

    void deleteCustomer(CustomerVO cust);

    List getAllCustomer();

    CustomerVO getCustomer(Integer id);

    void insertCustomer(CustomerVO cust);

    void updateCustomer(CustomerVO cust);
}
