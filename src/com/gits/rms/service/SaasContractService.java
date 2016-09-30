package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.SaasContractVO;

public interface SaasContractService {
    
    List getSaasContract();
    
    void insertSaasContract(SaasContractVO saasContract);
    
}
