package com.gits.rms.persistence.performance;

import java.util.List;

import com.gits.rms.vo.NationalityVO;
import com.gits.rms.vo.QuestionVO;
import com.gits.rms.vo.SubCategoryVO;

public interface QuestionDao {
    
    void insertQuestion(QuestionVO question);
    
    QuestionVO getQuestions(Integer id);
    
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

