/**
 * 
 */
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmpAssetsVO;
import com.gits.rms.vo.EmployeeEEOVO;

/**
 * @author Parveen
 *
 */
public interface EmpAssetsDao {
	
	void insertEmpAssets(EmpAssetsVO assets);

    void updateEmpAssets(EmpAssetsVO assets);
    
    List<EmpAssetsVO> getEmpAllAssets(Integer employeeId);

}
