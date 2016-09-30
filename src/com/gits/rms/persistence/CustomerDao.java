
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.CustomerVO;

public interface CustomerDao {

    Integer checkCustomerEmailExists(CustomerVO cust);

    List checkCustomerInProject(CustomerVO cust);

    List customerSearchResult(CustomerVO cust);

    void deleteCustomer(CustomerVO cust);

    List getAllCustomer();

    CustomerVO getCustomer(Integer id);

    void insertCustomer(CustomerVO cust);

    void updateCustomer(CustomerVO cust);
}
