package com.gits.rms.persistence;
import java.util.List;

import com.gits.rms.vo.EducationVO;
import com.gits.rms.vo.EmployeeEEOVO;

/**
 * @author Parveen
 *
 */

public interface EmployeeEEODao {
	void insertEmployeeEEO(EmployeeEEOVO eeo);

    void updateEmployeeEEO(EmployeeEEOVO eeo);
    
    List<EmployeeEEOVO> getEmployeeAllEEO(Integer employeeId);
    
    EmployeeEEOVO getEEO(Integer id);

}
