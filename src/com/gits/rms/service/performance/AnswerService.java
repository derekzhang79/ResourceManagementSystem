package com.gits.rms.service.performance;

import java.util.List;

import com.gits.rms.vo.AnswerVO;
import com.gits.rms.vo.QuestionBankGeneralInfoVO;
import com.gits.rms.vo.QuestionGeneralInfoGroupVO;

public interface AnswerService {

    QuestionBankGeneralInfoVO getQuestionBankGeneralInfoVO(Integer employeeId);
    
    void insertAnswer(AnswerVO answer);
    
    List getAllEmployeeQuestionBankName(Integer employeeId);
    
    AnswerVO getAnswer(Integer QuestionBankId,Integer appraisingEmpId);
    
    AnswerVO getAppPeerAnswer(Integer QuestionBankId,Integer appraserEmpId);
    
    void updateAnswer(AnswerVO answer);
    
    List getAllSubEmployeeAnswerOptional(Integer employeeId,Integer questionGroupNameId);
    
    List getAllSubEmployeeAnswerNumbering(Integer employeeId,Integer questionGroupNameId);
    
    List getAllSubEmployeeAnswerSummarry(Integer employeeId,Integer questionGroupNameId);
    
    List getAllAnswers(Integer questionGroupNameId,Integer questionGeneralInfoGroupIdListId,Integer appraiserId,Integer appraisingId);
    
    AnswerVO getParticularAnswer(Integer id);
    
    List getAllSubEmployeePeerAnswerOptional(Integer employeeId,Integer questionGroupNameId,Integer selectPeerEmpId);
    
    List getAllSubEmployeePeerAnswerNumbering(Integer employeeId,Integer questionGroupNameId,Integer selectPeerEmpId);
    
    List getAllSubEmployeePeerAnswerSummarry(Integer employeeId,Integer questionGroupNameId,Integer selectPeerEmpId);
    
    List getAllAppEmployeeQuestionBankName(Integer employeeId);
    
    QuestionBankGeneralInfoVO getQuestionBankPeerEmployees(Integer employeeId,Integer peerEmployee);
    
    QuestionBankGeneralInfoVO getQuestionGeneralInfoGroupName(Integer employeeId,String employeeType,Integer hcmoQuestionGroupNameIdentifiId);
    
    List getAllApprovingAnswers(Integer questionGroupNameId,Integer questionGeneralInfoGroupIdListId,Integer subEmpId);
    
    List getAllPeerAnswers(Integer questionGroupNameId,Integer questionGeneralInfoGroupIdListId,Integer subEmpId);

    AnswerVO getAnswerView(Integer answerID);
}
