
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.TargetTypeDao;
import com.gits.rms.persistence.TargetTypeHibernateDao;
import com.gits.rms.vo.TargetTypeVO;

public class TargetTypeDaoService implements TargetTypeService {

    private TargetTypeDao dao;

    public TargetTypeDaoService() {
        dao = new TargetTypeHibernateDao();
    }

    @Override
    public List getAllTargetType() {
        return dao.getAllTargetType();
    }
    
    @Override
    public TargetTypeVO getTargetType(Integer id) {
        return dao.getTargetType(id);
    }
    
    @Override
    public void insertTargetType(TargetTypeVO targetType) {
        dao.insertTargetType(targetType);
    }
    
    @Override
    public void updateTargetType(TargetTypeVO targetType) {
        dao.updateTargetType(targetType);
    }    
    
    @Override
    public void deleteTargetType(TargetTypeVO targetType) {
        dao.deleteTargetType(targetType);
    }  
    
    @Override
    public List targetTypeSearchResult(TargetTypeVO targetType) {
        return dao.targetTypeSearchResult(targetType);
    }
    
    @Override
    public List checkTargetTypeInTarget(TargetTypeVO targetType) {
        return dao.checkTargetTypeInTarget(targetType);
    }

}
