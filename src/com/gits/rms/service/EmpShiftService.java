/**
 * 
 */
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmployeeShiftVO;

/**
 * @author Parveen
 *
 */
public interface EmpShiftService {

	void insertEmpShift(EmployeeShiftVO shift);

    void updateEmpShift(EmployeeShiftVO shift);
    
    List<EmployeeShiftVO> getEmpAllShift(Integer employeeId);

}
