/**
 * 
 */
package com.gits.rms.service;

import java.util.List;
import com.gits.rms.vo.EmployeeEEOVO;

/**
 * @author Parveen
 *
 */
public interface EmployeeEEOService {
	void insertEmployeeEEO(EmployeeEEOVO eeo);

    void updateEmployeeEEO(EmployeeEEOVO eeo);
    
    List<EmployeeEEOVO> getEmployeeAllEEO(Integer employeeId);
    
    EmployeeEEOVO getEEO(Integer id);


}
