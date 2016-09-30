package com.gits.rms.service.performance;

import java.util.Date;
import java.util.List;

import com.gits.rms.persistence.performance.QuestionBankGeneralInfoDao;
import com.gits.rms.persistence.performance.QuestionBankGeneralInfoHibernateDao;
import com.gits.rms.vo.QuestionBankGeneralInfoVO;

public class QuestionBankGeneralInfoDaoService implements QuestionBankGeneralInfoService{

    private QuestionBankGeneralInfoDao dao;
    
    public QuestionBankGeneralInfoDaoService(){
        dao = new QuestionBankGeneralInfoHibernateDao();
    }
    
    @Override
    public List getAllEmployeeId(Integer departmentId,Integer teamId,Integer jobTitleId,Integer projectId,Date startDate,Date endDate){
        return dao.getAllEmployeeId(departmentId, teamId, jobTitleId,projectId, startDate, endDate);
    }
    
    @Override
    public void insertQuestionBankGeneralInfo(QuestionBankGeneralInfoVO quesBankGeneralInfo){
        dao.insertQuestionBankGeneralInfo(quesBankGeneralInfo);
    }
    
    @Override
    public  List getAllPeerEmployeeId(Integer departmentId,Integer teamId,Integer jobTitleId,Integer projectId,Date startDate,Date endDate){
        return dao.getAllPeerEmployeeId(departmentId, teamId, jobTitleId,projectId, startDate, endDate);
    }
    
    @Override
    public List getAllApprovingEmployeeId(Integer departmentId,Integer teamId,Integer jobTitleId,Integer projectId,Date startDate,Date endDate){
        return dao.getAllApprovingEmployeeId(departmentId, teamId, jobTitleId, projectId,startDate, endDate);
    }
    
    @Override
    public List getAllApproversSubEmployeeList(){
        return dao.getAllApproversSubEmployeeList();
    }
 
    @Override
    public List getAllEmployeesPeerEmployeeList(Integer EmployeeId){
        return dao.getAllEmployeesPeerEmployeeList(EmployeeId);
    }
    
    @Override
    public List getEmployeeListForPeerEmployee(Integer EmployeeId){
        return dao.getEmployeeListForPeerEmployee(EmployeeId);
    }
    
    @Override
    public List getPeerEmployeeQuestionBank(Integer EmployeeId){
        return dao.getPeerEmployeeQuestionBank(EmployeeId);
    }
    
    @Override
    public void updateGeneralInfoStatus(QuestionBankGeneralInfoVO quesBankGeneralInfo){
        dao.updateGeneralInfoStatus(quesBankGeneralInfo);
    }
    
    @Override
    public QuestionBankGeneralInfoVO getEmployeeQuestionBankGeneralInfo(Integer questionBankGeneralInfoId,Integer questionGroupIdentifiId,Integer questionGeneralInfoGroupIdListId){
        return dao.getEmployeeQuestionBankGeneralInfo(questionBankGeneralInfoId, questionGroupIdentifiId, questionGeneralInfoGroupIdListId);
    }
    
    @Override
    public QuestionBankGeneralInfoVO getQuestionBankGeneralInfo(Integer questionBankGeneralInfoId){
        return dao.getQuestionBankGeneralInfo(questionBankGeneralInfoId);
    }
    
    @Override
    public void updateGeneralInfoaApproverComments(QuestionBankGeneralInfoVO quesBankGeneralInfo){
        dao.updateGeneralInfoaApproverComments(quesBankGeneralInfo);
    }
    
    @Override
    public QuestionBankGeneralInfoVO getAllassignedEmployee(Date startDate,Date endDate,Integer employeeId){
        return dao.getAllassignedEmployee(startDate,endDate,employeeId);
    }
    
    @Override
    public QuestionBankGeneralInfoVO getAllAssignedAppEmployee(Date startDate,Date endDate,Integer employeeId,Integer approvingEmployeeId){
        return dao.getAllAssignedAppEmployee(startDate, endDate, employeeId, approvingEmployeeId);
    }
    
    @Override
    public QuestionBankGeneralInfoVO getAllAssignedPeerEmployee(Date startDate,Date endDate,Integer employeeId,Integer peerEmployeeId){
        return dao.getAllAssignedPeerEmployee(startDate, endDate, employeeId, peerEmployeeId);
    }
    @Override
    public List getAdminEmployeeQuesGeneralInfoList(Integer employeeId){
        return dao.getAdminEmployeeQuesGeneralInfoList(employeeId);
    }
    
    @Override
    public List getAdminApprovingEmployeeQuesGeneralInfoList(Integer employeeId){
        return dao.getAdminApprovingEmployeeQuesGeneralInfoList(employeeId);
    }
    
    @Override
    public List getAdminPeerEmployeeQuesGeneralInfoList(Integer employeeId){
        return dao.getAdminPeerEmployeeQuesGeneralInfoList(employeeId);
    }
    
    @Override
    public void updateAdminEmployeeComments(QuestionBankGeneralInfoVO quesBankGeneralInfo){
        dao.updateAdminEmployeeComments(quesBankGeneralInfo);
    }
    
    @Override
    public List getQuestionBankGeneralInfoList(Integer questionGroupIdentifiId,Integer questionGeneralInfoGroupIdListId,Integer employeeId,Integer peerEmpId){
        return dao.getQuestionBankGeneralInfoList(questionGroupIdentifiId, questionGeneralInfoGroupIdListId, employeeId, peerEmpId);
    }
    
    @Override
    public List getAllQuestionGeneralInfoGroupId(){
        return dao.getAllQuestionGeneralInfoGroupId();
    }
    
    @Override
    public List getAllQuestionBankGeneralInfo(Integer quesGeneralInfoGroupId){
        return dao.getAllQuestionBankGeneralInfo(quesGeneralInfoGroupId);
    }
    
}
