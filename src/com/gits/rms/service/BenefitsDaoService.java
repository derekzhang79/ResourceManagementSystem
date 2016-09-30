
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.BenefitsDao;
import com.gits.rms.persistence.BenefitsHibernateDao;
import com.gits.rms.vo.BenefitsVO;
import com.gits.rms.vo.EmployeesVO;

public class BenefitsDaoService implements BenefitsService {

    private BenefitsDao dao;

    public BenefitsDaoService() {
        dao = new BenefitsHibernateDao();
    }

    @Override
    public void deleteBenefit(BenefitsVO benefit) {
        dao.deleteBenefit(benefit);
    }

    @Override
    public List getAllBenefit() {
        return dao.getAllBenefit();
    }
    
    @Override
    public List getAllSubEmployeeBenefitList(List<Integer> empReportToEmpId){
    	return dao.getAllSubEmployeeBenefitList(empReportToEmpId);
    }

    @Override
    public List getAllBenefitYear() {
        return dao.getAllBenefitYear();
    }

    @Override
    public List getAllBenefitYearSearchResult(BenefitsVO benefit) {
        return dao.getAllBenefitYearSearchResult(benefit);
    }

    @Override
    public BenefitsVO getBenefit(Integer id) {
        return dao.getBenefit(id);
    }

    @Override
    public List getEmployeeBenefit(Integer id) {
        return dao.getEmployeeBenefit(id);
    }

    @Override
    public EmployeesVO getEmployeeName(Integer id) {
        return dao.getEmployeeName(id);
    }

    @Override
    public void insertBenefit(BenefitsVO benefit) {
        dao.insertBenefit(benefit);
    }

    @Override
    public void updateBenefit(BenefitsVO benefit) {
        dao.updateBenefit(benefit);
    }
}
