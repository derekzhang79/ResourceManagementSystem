
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.SalaryGradeDao;
import com.gits.rms.persistence.SalaryGradeHibernateDao;
import com.gits.rms.vo.SalaryGradeVO;

public class SalaryGradeDaoService implements SalaryGradeService {
    private SalaryGradeDao dao;

    public SalaryGradeDaoService() {
        dao = new SalaryGradeHibernateDao();
    }

    @Override
    public List checkSalaryGradeInJobTitle(SalaryGradeVO salgra) {
        return dao.checkSalaryGradeInJobTitle(salgra);
    }

    @Override
    public void deleteSalaryGrade(SalaryGradeVO salgra) {
        dao.deleteSalaryGrade(salgra);
    }

    @Override
    public List getAllSalaryGrade() {
        return dao.getAllSalaryGrade();
    }

    @Override
    public SalaryGradeVO getSalaryGrade(Integer id) {
        return dao.getSalaryGrade(id);
    }

    @Override
    public void insertSalaryGrade(SalaryGradeVO salgra) {
        dao.insertSalaryGrade(salgra);
    }

    @Override
    public List salaryGradeSearchResult(SalaryGradeVO salgra) {
        return dao.salaryGradeSearchResult(salgra);
    }

    @Override
    public void updateSalaryGrade(SalaryGradeVO salgra) {
        dao.updateSalaryGrade(salgra);
    }
}
