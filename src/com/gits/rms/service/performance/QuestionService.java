package com.gits.rms.service.performance;

import java.util.List;

import com.gits.rms.vo.QuestionVO;

public interface QuestionService {
    
    QuestionVO getQuestions(Integer id);
    
    void insertQuestion(QuestionVO question);
    
    List getAllOptionalQuestions();
    
    List getAllNumberingQuestions();
    
    List getAllSummaryQuestions();
    
    void updateOptionalQuestion(QuestionVO question);
    
    void updateNumberingQuestion(QuestionVO question);
    
    void updateSummaryQuestion(QuestionVO question);
    
    List getAllOptionQuestionsView(List questionList);
    
    List getAllNumberingQuestionsView(List questionList);
    
    List getAllSummaryQuestionsView(List questionList);
}
