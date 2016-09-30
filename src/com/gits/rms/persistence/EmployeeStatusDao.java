
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmployeeStatusVO;

public interface EmployeeStatusDao {
    List checkEmployeeStatusInEmployee(EmployeeStatusVO empStatus);

    void deleteEmployeeStatus(EmployeeStatusVO empStatus);

    List employeeStatusSearchResult(EmployeeStatusVO empStatus);

    List getAllEmployeeStatus();

    EmployeeStatusVO getEmployeeStatus(Integer id);

    void insertEmployeeStatus(EmployeeStatusVO empStatus);

    void updateEmployeeStatus(EmployeeStatusVO empStatus);
}
