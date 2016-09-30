
package com.gits.rms.service;

import com.gits.rms.persistence.SupportDao;
import com.gits.rms.persistence.SupportHibernateDao;
import com.gits.rms.vo.SupportVO;

public class SupportDaoService implements SupportService {

    private SupportDao dao;

    public SupportDaoService() {
        dao = new SupportHibernateDao();
    }

    @Override
    public SupportVO getSupport(Integer id) {
        return dao.getSupport(id);
    }

    @Override
    public void insertSupport(SupportVO supportObj) {
        dao.insertSupport(supportObj);
    }
}
