
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.ChildrenDao;
import com.gits.rms.persistence.ChildrenHibernateDao;
import com.gits.rms.vo.ChildrenVO;

public class ChildrenDaoService implements ChildrenService {
    private ChildrenDao dao;

    public ChildrenDaoService() {
        dao = new ChildrenHibernateDao();
    }

    @Override
    public List childrenSearchResult(ChildrenVO child) {
        return dao.childrenSearchResult(child);
    }

    @Override
    public void deleteChildren(ChildrenVO child) {
        dao.deleteChildren(child);
    }

    @Override
    public List getAllChildren() {
        return dao.getAllChildren();
    }
    
    @Override
    public List getAllSubEmployeeChildrenList(List<Integer> empReportToEmpId){
    	return dao.getAllSubEmployeeChildrenList(empReportToEmpId);
    }

    @Override
    public ChildrenVO getChildren(Integer id) {
        return dao.getChildren(id);
    }

    @Override
    public ChildrenVO getEmpChildren(ChildrenVO child) {
        return dao.getEmpChildren(child);
    }

    @Override
    public List<ChildrenVO> getEmployeeAllChildren(Integer employeeId) {
        return dao.getEmployeeAllChildren(employeeId);
    }

    @Override
    public void insertChildren(ChildrenVO child) {
        dao.insertChildren(child);
    }

    @Override
    public void updateChildren(ChildrenVO child) {
        dao.updateChildren(child);
    }
}
