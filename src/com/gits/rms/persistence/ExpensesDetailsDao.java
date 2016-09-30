
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.ExpensesDetailsVO;

public interface ExpensesDetailsDao {

    List getAllExpensesDetails();

    ExpensesDetailsVO getExpensesDetails(Integer id);

    void insertExpensesDetails(ExpensesDetailsVO expDetails);

    void updateExpensesDetails(ExpensesDetailsVO expDetails);

    void deleteExpensesDetails(Integer id);

    List getExpensesEmpDetails(Integer id);
}
