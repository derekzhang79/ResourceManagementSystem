
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.EmpLocationHistoryVO;

public interface EmpLocationHistoryDao {
	
    void deleteEmpLocationHistory(EmpLocationHistoryVO elhist);

    List empLocHistSearchResult(EmpLocationHistoryVO elhist);

    List getAllEmpLocationHistory();
    
    List getAllSubEmployeeLocationHistoryList(List<Integer> empReportToEmpId);

    EmpLocationHistoryVO getEmpLocationHistory(Integer id);

    void insertEmpLocationHistory(EmpLocationHistoryVO elhist);

    void updateEmpLocationHistory(EmpLocationHistoryVO elhist);
}
