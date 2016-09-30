package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.SaasContractDao;
import com.gits.rms.persistence.SaasContractHibernateDao;
import com.gits.rms.vo.SaasContractVO;

public class SaasContractDaoService implements SaasContractService{
    private SaasContractDao dao;
    
    public SaasContractDaoService() {
        dao = new SaasContractHibernateDao();
    }
    
    @Override
    public List getSaasContract() {
        return dao.getSaasContract();
    }
    
    @Override
    public void insertSaasContract(SaasContractVO saasContract) {
        dao.insertSaasContract(saasContract);
    }

}
