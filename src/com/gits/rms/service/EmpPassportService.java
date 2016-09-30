
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmpPassportVO;

public interface EmpPassportService {

    void deleteEmpPassport(EmpPassportVO empPass);

    List getAllEmpPassport();
    
    List getAllSubEmployeePasportList(List<Integer> empReportToEmpId);

    EmpPassportVO getEmpPassport(Integer id);

    void insertEmpPassport(EmpPassportVO empPass);

    List passportSearchResult(EmpPassportVO pass);

    void updateEmpPassport(EmpPassportVO empPass);
}
