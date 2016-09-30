package com.gits.rms.service.performance;

import java.util.List;

import com.gits.rms.persistence.performance.AnswerDao;
import com.gits.rms.persistence.performance.AnswerHibernateDao;
import com.gits.rms.vo.AnswerVO;
import com.gits.rms.vo.QuestionBankGeneralInfoVO;

public class AnswerDaoService implements AnswerService{

    private AnswerDao dao;
    
    public AnswerDaoService(){
        dao= new AnswerHibernateDao();
    }
    
    @Override
    public QuestionBankGeneralInfoVO getQuestionBankGeneralInfoVO(Integer employeeId){
        return dao.getQuestionBankGeneralInfoVO(employeeId);
    }
    
    @Override
    public void insertAnswer(AnswerVO answer){
        dao.insertAnswer(answer);
    }
    
    @Override
    public List getAllEmployeeQuestionBankName(Integer employeeId){
       return dao.getAllEmployeeQuestionBankName(employeeId);
    }
    
    @Override
    public AnswerVO getAnswer(Integer QuestionBankId,Integer appraisingEmpId){
        return dao.getAnswer(QuestionBankId, appraisingEmpId);
    }
    
    @Override
    public AnswerVO getAppPeerAnswer(Integer QuestionBankId,Integer appraserEmpId){
        return dao.getAppPeerAnswer(QuestionBankId, appraserEmpId);
    }
    
    @Override
    public void updateAnswer(AnswerVO answer){
        dao.updateAnswer(answer);
    }
    
    @Override
    public List getAllSubEmployeeAnswerOptional(Integer employeeId,Integer questionGroupNameId){
        return dao.getAllSubEmployeeAnswerOptional(employeeId, questionGroupNameId);
    }
    
    @Override
    public List getAllSubEmployeeAnswerNumbering(Integer employeeId,Integer questionGroupNameId){
        return dao.getAllSubEmployeeAnswerNumbering(employeeId, questionGroupNameId);
    }
    
    @Override
    public List getAllSubEmployeeAnswerSummarry(Integer employeeId,Integer questionGroupNameId){
        return dao.getAllSubEmployeeAnswerSummarry(employeeId, questionGroupNameId);
    }
    
    @Override
    public List getAllAnswers(Integer questionGroupNameId,Integer questionGeneralInfoGroupIdListId,Integer appraiserId,Integer appraisingId){
        return dao.getAllAnswers(questionGroupNameId,questionGeneralInfoGroupIdListId,appraiserId,appraisingId);
    }
    
    @Override
    public AnswerVO getParticularAnswer(Integer id){
        return dao.getParticularAnswer(id);
    }
    
    @Override
    public List getAllSubEmployeePeerAnswerOptional(Integer employeeId,Integer questionGroupNameId,Integer selectPeerEmpId){
        return dao.getAllSubEmployeePeerAnswerOptional(employeeId, questionGroupNameId,selectPeerEmpId);
    }
    
    @Override
    public List getAllSubEmployeePeerAnswerNumbering(Integer employeeId,Integer questionGroupNameId,Integer selectPeerEmpId){
        return dao.getAllSubEmployeePeerAnswerNumbering(employeeId, questionGroupNameId,selectPeerEmpId);
    }
    
    @Override
    public List getAllSubEmployeePeerAnswerSummarry(Integer employeeId,Integer questionGroupNameId,Integer selectPeerEmpId){
        return dao.getAllSubEmployeePeerAnswerSummarry(employeeId, questionGroupNameId,selectPeerEmpId);
    }
    
    @Override
    public List getAllAppEmployeeQuestionBankName(Integer employeeId){
        return dao.getAllAppEmployeeQuestionBankName(employeeId);
    }
    
    @Override
    public QuestionBankGeneralInfoVO getQuestionBankPeerEmployees(Integer employeeId,Integer peerEmployee){
        return dao.getQuestionBankPeerEmployees(employeeId, peerEmployee);
    }
    
    @Override
    public QuestionBankGeneralInfoVO getQuestionGeneralInfoGroupName(Integer employeeId,String employeeType,Integer hcmoQuestionGroupNameIdentifiId){
        return dao.getQuestionGeneralInfoGroupName(employeeId, employeeType, hcmoQuestionGroupNameIdentifiId);
    }
    
    @Override
    public List getAllApprovingAnswers(Integer questionGroupNameId,Integer questionGeneralInfoGroupIdListId,Integer subEmpId){
        return dao.getAllApprovingAnswers(questionGroupNameId, questionGeneralInfoGroupIdListId,subEmpId);
    }
    
    @Override
    public List getAllPeerAnswers(Integer questionGroupNameId,Integer questionGeneralInfoGroupIdListId,Integer subEmpId){
        return dao.getAllPeerAnswers(questionGroupNameId, questionGeneralInfoGroupIdListId, subEmpId);
    }
    
    @Override
    public AnswerVO getAnswerView(Integer answerID){
        return dao.getAnswerView(answerID);
    }
}

