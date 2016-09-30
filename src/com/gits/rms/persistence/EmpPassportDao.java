
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmpPassportVO;

public interface EmpPassportDao {

    void deleteEmpPassport(EmpPassportVO empPass);

    List getAllEmpPassport();
    
    List getAllSubEmployeePasportList(List<Integer> empReportToEmpId);

    EmpPassportVO getEmpPassport(Integer id);

    void insertEmpPassport(EmpPassportVO empPass);

    List passportSearchResult(EmpPassportVO pass);

    void updateEmpPassport(EmpPassportVO empPass);
}
