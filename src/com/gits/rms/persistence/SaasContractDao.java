package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.SaasContractVO;

public interface SaasContractDao {
    
    List getSaasContract();
    
    void insertSaasContract(SaasContractVO saasContract);
    
}
