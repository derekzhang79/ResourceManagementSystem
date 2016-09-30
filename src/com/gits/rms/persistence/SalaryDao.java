
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.SalaryVO;

public interface SalaryDao {

    List getAllSalary();

    SalaryVO getSalary(Integer id);

    void insertSalary(SalaryVO sal);

    void updateSalary(SalaryVO sal);

    void deleteSalary(SalaryVO sal);

    SalaryVO getEmpSalary(SalaryVO sal);

    List<SalaryVO> getEmployeeAllSalary(Integer employeeId);

    List salarySearchResult(SalaryVO sal);
}