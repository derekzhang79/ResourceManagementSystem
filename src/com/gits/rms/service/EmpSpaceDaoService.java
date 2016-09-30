
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.EmpSpaceDao;
import com.gits.rms.persistence.EmpSpaceHibernateDao;
import com.gits.rms.vo.EmpSpaceVO;
import com.gits.rms.vo.EmployeesVO;

public class EmpSpaceDaoService implements EmpSpaceService {

    private EmpSpaceDao dao;

    public EmpSpaceDaoService() {
        dao = new EmpSpaceHibernateDao();
    }

    @Override
    public void deleteEmpSpace(EmpSpaceVO empSpace) {
        dao.deleteEmpSpace(empSpace);
    }

    @Override
    public List empspaceSearchResult(EmpSpaceVO empSpace) {
        return dao.empspaceSearchResult(empSpace);
    }

    @Override
    public List getAllEmpSpace(Integer id) {
        return dao.getAllEmpSpace(id);
    }

    @Override
    public EmpSpaceVO getAllEmpSpaceList(Integer id) {
        return dao.getAllEmpSpaceList(id);
    }

    @Override
    public List getAllSharedAndUploadedList() {
        return dao.getAllSharedAndUploadedList();
    }

    @Override
    public List getAllSharedEmpSpace(String eMail) {
        return dao.getAllSharedEmpSpace(eMail);
    }

    @Override
    public EmployeesVO getEmployeeName(Integer id) {
        return dao.getEmployeeName(id);
    }

    @Override
    public EmpSpaceVO getEmpSpace(Integer id) {
        return dao.getEmpSpace(id);
    }

    @Override
    public List getEmpSpaceSharedId(String empId) {
        return dao.getEmpSpaceSharedId(empId);
    }

    @Override
    public void insertEmpSpace(EmpSpaceVO empSpace) {
        dao.insertEmpSpace(empSpace);
    }

    @Override
    public void makeShared(EmpSpaceVO empSpace) {
        dao.makeShared(empSpace);
    }

    @Override
    public void makeUnshare(EmpSpaceVO empSpace) {
        dao.makeUnshare(empSpace);
    }

    @Override
    public void updateEmpSpace(EmpSpaceVO empSpace) {
        dao.updateEmpSpace(empSpace);
    }
}
