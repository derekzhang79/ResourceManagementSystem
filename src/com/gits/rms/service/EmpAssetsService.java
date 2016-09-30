/**
 * 
 */
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmpAssetsVO;
/**
 * @author Parveen
 *
 */
public interface EmpAssetsService {
	
	void insertEmpAssets(EmpAssetsVO asset);

    void updateEmpAssets(EmpAssetsVO asset);
    
    List<EmpAssetsVO> getEmpAllAssets(Integer employeeId);
    
}
