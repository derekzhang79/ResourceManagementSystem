
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.ChildrenVO;

public interface ChildrenService {

    List childrenSearchResult(ChildrenVO child);

    void deleteChildren(ChildrenVO child);

    List getAllChildren();
    
    List getAllSubEmployeeChildrenList(List<Integer> empReportToEmpId);

    ChildrenVO getChildren(Integer id);

    ChildrenVO getEmpChildren(ChildrenVO child);

    List<ChildrenVO> getEmployeeAllChildren(Integer employeeId);

    void insertChildren(ChildrenVO child);

    void updateChildren(ChildrenVO child);
}
