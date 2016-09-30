
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.DepartmentDao;
import com.gits.rms.persistence.DepartmentHibernateDao;
import com.gits.rms.vo.DepartmentVO;

public class DepartmentDaoService implements DepartmentService {

    private DepartmentDao dao;

    public DepartmentDaoService() {
        dao = new DepartmentHibernateDao();
    }

    @Override
    public List checkDepartmentInEmployee(DepartmentVO dept) {
        return dao.checkDepartmentInEmployee(dept);
    }

    @Override
    public void deleteDepartment(DepartmentVO dept) {
        dao.deleteDepartment(dept);
    }

    @Override
    public List departmentSearchResult(DepartmentVO dept) {
        return dao.departmentSearchResult(dept);
    }

    @Override
    public List getAllDepartment() {
        return dao.getAllDepartment();
    }

    @Override
    public DepartmentVO getDepartment(Integer id) {
        return dao.getDepartment(id);
    }

    @Override
    public void insertDepartment(DepartmentVO dept) {
        dao.insertDepartment(dept);
    }

    @Override
    public void updateDepartment(DepartmentVO dept) {
        dao.updateDepartment(dept);
    }

}
