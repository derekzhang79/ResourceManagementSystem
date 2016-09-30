
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.PayStubDao;
import com.gits.rms.persistence.PayStubHibernateDao;
import com.gits.rms.vo.PayStubVO;
import com.gits.rms.vo.ReportsVO;

public class PayStubDaoService implements PayStubService {

    private PayStubDao dao;

    public PayStubDaoService() {
        dao = new PayStubHibernateDao();
    }

    @Override
    public void deletePayStub(PayStubVO payStub) {
        dao.deletePayStub(payStub);
    }

    @Override
    public List<PayStubVO> getAllPayStubs() {
        return dao.getAllPayStubs();
    }
    
    @Override
    public List getAllSubEmployeePayStubList(List<Integer> empReportToEmpId){
    	return dao.getAllSubEmployeePayStubList(empReportToEmpId);
    }

    @Override
    public List getEmployeeAllPayStub(Integer id) {
        return dao.getEmployeeAllPayStub(id);
    }

    @Override
    public List getEmployeePayStubs(int payStubId) {
        return dao.getEmployeePayStubs(payStubId);
    }

    @Override
    public PayStubVO getPayStub(Integer id) {
        return dao.getPayStub(id);
    }

    @Override
    public PayStubVO getPayStubCronJob(Integer id, String clientId) {
        return dao.getPayStubCronJob(id, clientId);
    }

    @Override
    public List getPayStubEmployeeReports(ReportsVO report) {
        return dao.getPayStubEmployeeReports(report);
    }

    @Override
    public List getPayStubLeaveQuotaReports(ReportsVO report) {
        return dao.getPayStubLeaveQuotaReports(report);
    }

    @Override
    public List getPayStubLeaveReqApprovalReports(ReportsVO report) {
        return dao.getPayStubLeaveReqApprovalReports(report);
    }

    @Override
    public List getPayStubReports(ReportsVO report) {
        return dao.getPayStubReports(report);
    }

    @Override
    public void insertPayStub(PayStubVO payStub) {
        dao.insertPayStub(payStub);
    }

    @Override
    public List payStubSearchResult(PayStubVO payStubVO) {
        return dao.payStubSearchResult(payStubVO);
    }

    @Override
    public void updatePayStub(PayStubVO payStub) {
        dao.updatePayStub(payStub);
    }
}
