
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.BenefitsVO;
import com.gits.rms.vo.EmployeesVO;

public interface BenefitsDao {

    void deleteBenefit(BenefitsVO benefit);

    List getAllBenefit();
    
    List getAllSubEmployeeBenefitList(List<Integer> empReportToEmpId);

    List getAllBenefitYear();

    List getAllBenefitYearSearchResult(BenefitsVO benefit);

    BenefitsVO getBenefit(Integer id);

    List getEmployeeBenefit(Integer id);

    EmployeesVO getEmployeeName(Integer id);

    void insertBenefit(BenefitsVO benefit);

    void updateBenefit(BenefitsVO benefit);
}
