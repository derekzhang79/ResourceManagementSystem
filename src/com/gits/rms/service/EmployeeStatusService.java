
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmployeeStatusVO;

public interface EmployeeStatusService {

    List checkEmployeeStatusInEmployee(EmployeeStatusVO empStatus);

    void deleteEmployeeStatus(EmployeeStatusVO empStatus);

    List employeeStatusSearchResult(EmployeeStatusVO empStatus);

    List getAllEmployeeStatus();

    EmployeeStatusVO getEmployeeStatus(Integer id);

    void insertEmployeeStatus(EmployeeStatusVO empStatus);

    void updateEmployeeStatus(EmployeeStatusVO empStatus);
}
