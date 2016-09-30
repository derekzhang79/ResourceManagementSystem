
package com.gits.rms.service;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.persistence.RoleDao;
import com.gits.rms.persistence.RoleHibernateDao;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.RoleVO;

public class RoleDaoService implements RoleService {
    private RoleDao oRoleDao;
    
    public RoleDaoService() {
        oRoleDao = new RoleHibernateDao();
    }

    @Override
    public List checkRoleInEmployee(RoleVO role) {
        return oRoleDao.checkRoleInEmployee(role);
    }

    @Override
    public void deleteRole(RoleVO role) {
        oRoleDao.deleteRole(role);
    }

    @Override
    public List<EmployeesVO> getAllAdmin(Integer roleId) {
        return oRoleDao.getAllAdmin(roleId);
    }

    @Override
    public List getAllRole() {
        return oRoleDao.getAllRole();
    }

    @Override
    public EmployeesVO getEmployeeId(Integer roleId) {
        return oRoleDao.getEmployeeId(roleId);
    }

    @Override
    public RoleVO getRole(Integer id) {
        return oRoleDao.getRole(id);
    }

    @Override
    public RoleVO getRoleName(String roleName) {
        return oRoleDao.getRoleName(roleName);
    }

    @Override
    public void insertRole(RoleVO role) {
        oRoleDao.insertRole(role);
    }

    @Override
    public List roleSearchResult(RoleVO role) {
        return oRoleDao.roleSearchResult(role);
    }

    @Override
    public void updateRole(RoleVO role) {
        oRoleDao.updateRole(role);
    }
}
