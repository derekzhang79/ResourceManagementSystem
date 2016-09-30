
package com.gits.rms.service;

import com.gits.rms.persistence.PayStubArchiveDao;
import com.gits.rms.persistence.PayStubArchiveHibernateDao;
import com.gits.rms.vo.PayStubArchiveVO;

public class PayStubArchiveDaoService implements PayStubArchiveService {

    private PayStubArchiveDao dao;

    public PayStubArchiveDaoService() {
        dao = new PayStubArchiveHibernateDao();
    }

    @Override
    public void insertPayStubArchive(PayStubArchiveVO payStubArchive) {
        dao.insertPayStubArchive(payStubArchive);
    }

}
