
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.EmpUSTaxDao;
import com.gits.rms.persistence.EmpUSTaxHibernateDao;
import com.gits.rms.vo.EmpUSTaxVO;

public class EmpUSTaxDaoService implements EmpUSTaxService {

    private EmpUSTaxDao dao;

    public EmpUSTaxDaoService() {
        dao = new EmpUSTaxHibernateDao();
    }

    @Override
    public void deleteEmpUSTax(EmpUSTaxVO empUSTax) {
        dao.deleteEmpUSTax(empUSTax);
    }

    @Override
    public List getAllEmpUSTax() {
        return dao.getAllEmpUSTax();
    }

    @Override
    public EmpUSTaxVO getEmpUSTax(Integer id) {
        return dao.getEmpUSTax(id);
    }

    @Override
    public void insertEmpUSTax(EmpUSTaxVO empUSTax) {
        dao.insertEmpUSTax(empUSTax);
    }

    @Override
    public void updateEmpUSTax(EmpUSTaxVO empUSTax) {
        dao.updateEmpUSTax(empUSTax);
    }

    @Override
    public List usTaxSearchResult(EmpUSTaxVO empUSTax) {
        return dao.usTaxSearchResult(empUSTax);
    }
}
