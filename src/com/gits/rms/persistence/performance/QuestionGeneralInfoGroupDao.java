package com.gits.rms.persistence.performance;

import java.util.List;

import com.gits.rms.vo.QuestionGeneralInfoGroupVO;

public interface QuestionGeneralInfoGroupDao {
    
    void insertQuestioinBankGeneralInfoGroup(QuestionGeneralInfoGroupVO group);
    
    QuestionGeneralInfoGroupVO getQuestioinBankGeneralInfoGroup(Integer id);
    
    QuestionGeneralInfoGroupVO getQuesBankGeneralInfoGroup(String name);
    
    List getQuesBGenInfoGroup(String name);
    
}
