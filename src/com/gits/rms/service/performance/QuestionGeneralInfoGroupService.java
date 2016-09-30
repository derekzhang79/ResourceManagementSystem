package com.gits.rms.service.performance;

import java.util.List;

import com.gits.rms.vo.QuestionGeneralInfoGroupVO;

public interface QuestionGeneralInfoGroupService {
    
    void insertQuestioinBankGeneralInfoGroup(QuestionGeneralInfoGroupVO group);
    
    QuestionGeneralInfoGroupVO getQuestioinBankGeneralInfoGroup(Integer id);
    
    QuestionGeneralInfoGroupVO getQuesBankGeneralInfoGroup(String name);
    
    List getQuesBGenInfoGroup(String name);

}
