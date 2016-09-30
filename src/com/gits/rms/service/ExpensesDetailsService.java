
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.ExpensesDetailsVO;

public interface ExpensesDetailsService {

    void deleteExpDetails(Integer id);

    List getAllExpDetails();

    ExpensesDetailsVO getExpDetails(Integer id);

    List getExpensesEmpDetails(Integer id);

    void insertExpDetails(ExpensesDetailsVO expDetails);

    void updateExpDetails(ExpensesDetailsVO expDetails);

}
