
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.EmpPassportDao;
import com.gits.rms.persistence.EmpPassportHibernateDao;
import com.gits.rms.vo.EmpPassportVO;

public class EmpPassportDaoService implements EmpPassportService {

    private EmpPassportDao dao;

    public EmpPassportDaoService() {
        dao = new EmpPassportHibernateDao();
    }

    @Override
    public void deleteEmpPassport(EmpPassportVO empPass) {
        dao.deleteEmpPassport(empPass);
    }

    @Override
    public List getAllEmpPassport() {
        return dao.getAllEmpPassport();
    }
    
    @Override
    public List getAllSubEmployeePasportList(List<Integer> empReportToEmpId){
    	return dao.getAllSubEmployeePasportList(empReportToEmpId);
    }

    @Override
    public EmpPassportVO getEmpPassport(Integer id) {
        return dao.getEmpPassport(id);
    }

    @Override
    public void insertEmpPassport(EmpPassportVO empPass) {
        dao.insertEmpPassport(empPass);
    }

    @Override
    public List passportSearchResult(EmpPassportVO pass) {
        return dao.passportSearchResult(pass);
    }

    @Override
    public void updateEmpPassport(EmpPassportVO empPass) {
        dao.updateEmpPassport(empPass);
    }
}
