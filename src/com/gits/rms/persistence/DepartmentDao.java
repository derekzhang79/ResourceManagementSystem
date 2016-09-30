
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.DepartmentVO;

public interface DepartmentDao {

    List checkDepartmentInEmployee(DepartmentVO dept);

    void deleteDepartment(DepartmentVO dept);

    List departmentSearchResult(DepartmentVO dept);

    List getAllDepartment();

    DepartmentVO getDepartment(Integer id);

    void insertDepartment(DepartmentVO dept);

    void updateDepartment(DepartmentVO dept);
}
