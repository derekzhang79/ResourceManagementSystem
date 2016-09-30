
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;

public interface RoleDao {

    List getAllRole();

    RoleVO getRole(Integer id);

    void insertRole(RoleVO role);

    void updateRole(RoleVO role);

    void deleteRole(RoleVO role);

    List roleSearchResult(RoleVO role);

    RoleVO getRoleName(String roleName);

    List<EmployeesVO> getAllAdmin(Integer roleId);

    EmployeesVO getEmployeeId(Integer roleId);

    List checkRoleInEmployee(RoleVO role);

}