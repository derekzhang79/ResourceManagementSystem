package com.gits.rms.service.performance;

import java.util.List;

import com.gits.rms.persistence.NationalityHibernateDao;
import com.gits.rms.persistence.performance.QuestionGeneralInfoGroupDao;
import com.gits.rms.persistence.performance.QuestionGeneralInfoGroupHibernateDao;
import com.gits.rms.vo.QuestionGeneralInfoGroupVO;

public class QuestionGeneralInfoGroupDaoService implements QuestionGeneralInfoGroupService{

    private QuestionGeneralInfoGroupDao dao;
    
    public QuestionGeneralInfoGroupDaoService() {
        dao = new QuestionGeneralInfoGroupHibernateDao();
    }
    
    @Override
    public void insertQuestioinBankGeneralInfoGroup(QuestionGeneralInfoGroupVO group){
        dao.insertQuestioinBankGeneralInfoGroup(group);
    }
    
    @Override
    public QuestionGeneralInfoGroupVO getQuestioinBankGeneralInfoGroup(Integer id){
        return dao.getQuestioinBankGeneralInfoGroup(id);
    }
    
    @Override
    public List getQuesBGenInfoGroup(String name){
        return dao.getQuesBGenInfoGroup(name);
    }
    
    @Override
    public QuestionGeneralInfoGroupVO getQuesBankGeneralInfoGroup(String name){
        return dao.getQuesBankGeneralInfoGroup(name);
    }

}
