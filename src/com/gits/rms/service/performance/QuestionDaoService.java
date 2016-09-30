package com.gits.rms.service.performance;

import java.util.List;

import com.gits.rms.persistence.performance.QuestionDao;
import com.gits.rms.persistence.performance.QuestionHibernateDao;
import com.gits.rms.vo.QuestionVO;

public class QuestionDaoService implements QuestionService{
    
    private QuestionDao dao;
    
    public QuestionDaoService(){
        dao=new QuestionHibernateDao();
    }
    
    @Override
    public QuestionVO getQuestions(Integer id){
        return dao.getQuestions(id);
    }
    
    @Override
    public void insertQuestion(QuestionVO question) {
        dao.insertQuestion(question);
    }

    @Override
    public List getAllOptionalQuestions(){
        return dao.getAllOptionalQuestions();
    }
    
    @Override
    public List getAllNumberingQuestions(){
        return dao.getAllNumberingQuestions();
    }
    
    @Override
    public List getAllSummaryQuestions(){
        return dao.getAllSummaryQuestions();
    }
    
    @Override
    public void updateOptionalQuestion(QuestionVO question){
        dao.updateOptionalQuestion(question);
    }
    
    @Override
    public void updateNumberingQuestion(QuestionVO question){
        dao.updateNumberingQuestion(question);
    }
    
    @Override
    public void updateSummaryQuestion(QuestionVO question){
        dao.updateSummaryQuestion(question);
    }
    
    @Override
    public List getAllOptionQuestionsView(List questionList){
        return dao.getAllOptionQuestionsView(questionList);
    }
    
    @Override
    public List getAllNumberingQuestionsView(List questionList){
        return dao.getAllNumberingQuestionsView(questionList);
    }
    
    @Override
    public List getAllSummaryQuestionsView(List questionList){
        return dao.getAllSummaryQuestionsView(questionList);
    }
}
