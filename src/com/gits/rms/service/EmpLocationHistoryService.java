
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmpLocationHistoryVO;

public interface EmpLocationHistoryService {

    void deleteEmpLocationHistory(EmpLocationHistoryVO elhist);

    List empLocHistSearchResult(EmpLocationHistoryVO elhist);

    List getAllEmpLocationHistory();
    
    List getAllSubEmployeeLocationHistoryList(List<Integer> empReportToEmpId);

    EmpLocationHistoryVO getEmpLocationHistory(Integer id);

    void insertEmpLocationHistory(EmpLocationHistoryVO lhist);

    void updateEmpLocationHistory(EmpLocationHistoryVO lhist);
}
