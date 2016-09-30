
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.DeductionsDao;
import com.gits.rms.persistence.DeductionsHibernateDao;
import com.gits.rms.vo.DeductionsVO;

public class DeductionsDaoService implements DeductionService {

    private DeductionsDao dao;

    public DeductionsDaoService() {
        dao = new DeductionsHibernateDao();
    }

    @Override
    public List checkDeductionInPaystubDeduction(DeductionsVO deduction) {
        return dao.checkDeductionInPaystubDeduction(deduction);
    }

    @Override
    public List deductionSearchResult(DeductionsVO deduction) {
        return dao.deductionSearchResult(deduction);
    }

    @Override
    public void deleteDeduction(DeductionsVO deduction) {
        dao.deleteDeduction(deduction);
    }

    @Override
    public List getAllDeductions() {
        return dao.getAllDeductions();
    }

    @Override
    public DeductionsVO getDeduction(Integer id) {
        return dao.getDeduction(id);
    }

    @Override
    public List getDeductionList(Integer id) {
        return dao.getDeductionList(id);
    }

    @Override
    public void insertDeduction(DeductionsVO deduction) {
        dao.insertDeduction(deduction);
    }

    @Override
    public void updateDeduction(DeductionsVO deduction) {
        dao.updateDeduction(deduction);
    }
}
