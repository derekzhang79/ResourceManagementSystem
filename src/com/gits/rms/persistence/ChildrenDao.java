
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.ChildrenVO;

public interface ChildrenDao {

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
