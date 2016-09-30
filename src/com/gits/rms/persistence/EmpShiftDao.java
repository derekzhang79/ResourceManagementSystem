/**
 * 
 */
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmpAssetsVO;
import com.gits.rms.vo.EmployeeShiftVO;

public interface EmpShiftDao {
	
	void insertEmpShift(EmployeeShiftVO shift);

    void updateEmpShift(EmployeeShiftVO shift);
    
    List<EmployeeShiftVO> getEmpAllShift(Integer employeeId);


}
