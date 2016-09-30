
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.EmpLocationHistoryDao;
import com.gits.rms.persistence.EmpLocationHistoryHibernateDao;
import com.gits.rms.vo.EmpLocationHistoryVO;

public class EmpLocationHistoryDaoService implements EmpLocationHistoryService {
    private EmpLocationHistoryDao dao;

    public EmpLocationHistoryDaoService() {
        dao = new EmpLocationHistoryHibernateDao();
    }

    @Override
    public void deleteEmpLocationHistory(EmpLocationHistoryVO elhist) {
        dao.deleteEmpLocationHistory(elhist);
    }

    @Override
    public List empLocHistSearchResult(EmpLocationHistoryVO elhist) {
        return dao.empLocHistSearchResult(elhist);
    }

    @Override
    public List getAllEmpLocationHistory() {
        return dao.getAllEmpLocationHistory();
    }
    
    @Override
    public List getAllSubEmployeeLocationHistoryList(List<Integer> empReportToEmpId){
    	return dao.getAllSubEmployeeLocationHistoryList(empReportToEmpId);
    }

    @Override
    public EmpLocationHistoryVO getEmpLocationHistory(Integer id) {
        return dao.getEmpLocationHistory(id);
    }

    @Override
    public void insertEmpLocationHistory(EmpLocationHistoryVO elhist) {
        dao.insertEmpLocationHistory(elhist);
    }

    @Override
    public void updateEmpLocationHistory(EmpLocationHistoryVO elhist) {
        dao.updateEmpLocationHistory(elhist);
    }
}
