
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.PayStubDeductionDao;
import com.gits.rms.persistence.PayStubDeductionHibernateDao;
import com.gits.rms.vo.PayStubDeductionsVO;

public class PayStubDeductionDaoService implements PayStubDeductionService {

    private PayStubDeductionDao dao;

    public PayStubDeductionDaoService() {
        dao = new PayStubDeductionHibernateDao();
    }

    @Override
    public boolean checkTodaysPayStubsDeduction(String clientId) {
        return dao.checkTodaysPayStubsDeduction(clientId);
    }

    @Override
    public void deletePayStubDeduction(PayStubDeductionsVO payStubDeduction) {
        dao.deletePayStubDeduction(payStubDeduction);
    }

    @Override
    public List getAllEmployeePayStubsDeduction(Integer id) {
        return dao.getAllEmployeePayStubsDeduction(id);
    }

    @Override
    public List getAllEmployeePayStubsDeductionCronJob(Integer id, String clientId) {
        return dao.getAllEmployeePayStubsDeductionCronJob(id, clientId);
    }

    @Override
    public List getAllEmpPayStubsDedWithOutSelectedDeduction(Integer payStubDeductionId, Integer payStubId) {
        return dao.getAllEmpPayStubsDedWithOutSelectedDeduction(payStubDeductionId, payStubId);
    }

    @Override
    public List getAllTodaysPayStubsDeduction(String clientId) {
        return dao.getAllTodaysPayStubsDeduction(clientId);
    }

    @Override
    public PayStubDeductionsVO getPayStubDeduction(Integer id) {
        return dao.getPayStubDeduction(id);
    }

    @Override
    public void insertPayStubDeduction(PayStubDeductionsVO payStubDeduction) {
        dao.insertPayStubDeduction(payStubDeduction);
    }

    @Override
    public void updatePayStubDeduction(PayStubDeductionsVO payStubDeduction) {
        dao.updatePayStubDeduction(payStubDeduction);
    }

    @Override
    public void updatePayStubNetSalary(int payStubID, double netSalary) {
        dao.updatePayStubNetSalary(payStubID, netSalary);
    }

    @Override
    public void updatePayStubNetSalaryCronJob(int payStubID, double netSalary, String clientId) {
        dao.updatePayStubNetSalaryCronJob(payStubID, netSalary, clientId);
    }
}
