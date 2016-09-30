
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.SalaryVO;

public interface SalaryService {

    void deleteSalary(SalaryVO sal);

    List getAllSalary();

    List<SalaryVO> getEmployeeAllSalary(Integer employeeId);

    SalaryVO getEmpSalary(SalaryVO sal);

    SalaryVO getSalary(Integer id);

    void insertSalary(SalaryVO sal);

    List salarySearchResult(SalaryVO sal);

    void updateSalary(SalaryVO sal);
}