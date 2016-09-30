
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;

public interface RoleService {
    List checkRoleInEmployee(RoleVO role);

    void deleteRole(RoleVO role);

    List<EmployeesVO> getAllAdmin(Integer roleId);

    List getAllRole();

    EmployeesVO getEmployeeId(Integer roleId);

    RoleVO getRole(Integer id);

    RoleVO getRoleName(String roleName);

    void insertRole(RoleVO role);

    List roleSearchResult(RoleVO role);

    void updateRole(RoleVO role);
}
