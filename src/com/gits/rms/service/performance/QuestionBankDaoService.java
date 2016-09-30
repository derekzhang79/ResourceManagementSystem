
package com.gits.rms.service.performance;

import java.util.Date;
import java.util.List;

import com.gits.rms.persistence.performance.QuestionBankDao;
import com.gits.rms.persistence.performance.QuestionBankHibernateDao;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.QuestionBankGeneralInfoVO;
import com.gits.rms.vo.QuestionBankVO;
import com.gits.rms.vo.QuestionGroupNameIdentificationVO;

public class QuestionBankDaoService implements QuestionBankService{
 
    private QuestionBankDao dao;
    
    public QuestionBankDaoService(){
        dao=new QuestionBankHibernateDao();
    }
    
    @Override
    public List getAllQuestions(){
        return dao.getAllQuestions();
    }

    @Override
    public void insertQuestionBank(QuestionBankVO quesBank){
        dao.insertQuestionBank(quesBank);
    }
    
    @Override
    public List getAllQuestionBankName(){
        return dao.getAllQuestionBankName();
    }
    
    @Override
    public void insertQuestionGroup(QuestionGroupNameIdentificationVO quesGroupId){
        dao.insertQuestionGroup(quesGroupId);
    }
    
    @Override
    public List checkQuestionBank(QuestionGroupNameIdentificationVO quesGroupId){
        return dao.checkQuestionBank(quesGroupId);
    }
    
    @Override
    public QuestionGroupNameIdentificationVO getQuestionGroupNameIdentificationVO(Integer id){
        return dao.getQuestionGroupNameIdentificationVO(id);
    }
    
    @Override
    public EmployeesVO getEmployeeId(String empFullName){
        return dao.getEmployeeId(empFullName);
    }
    
    @Override
    public List getAllQuestionBank(Integer id){
        return dao.getAllQuestionBank(id);
    }
    
    @Override
    public QuestionBankVO getQuestionBank(Integer id){
        return dao.getQuestionBank(id);
    }
    
    @Override
    public  List getAnswerCountForQuestion(Integer id){
        return dao.getAnswerCountForQuestion(id);
    }

    @Override
    public List getAllQuestionGroupNameIdentification(){
        return dao.getAllQuestionGroupNameIdentification();
    }
}
