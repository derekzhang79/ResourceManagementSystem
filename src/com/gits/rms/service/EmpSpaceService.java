
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmpSpaceVO;
import com.gits.rms.vo.EmployeesVO;

public interface EmpSpaceService {

    void deleteEmpSpace(EmpSpaceVO empSpace);

    List empspaceSearchResult(EmpSpaceVO empSpace);

    List getAllEmpSpace(Integer id);

    EmpSpaceVO getAllEmpSpaceList(Integer hcmoEmpSpaceId);

    List<EmpSpaceVO> getAllSharedAndUploadedList();

    List getAllSharedEmpSpace(String eMail);

    EmployeesVO getEmployeeName(Integer id);

    EmpSpaceVO getEmpSpace(Integer id);

    List getEmpSpaceSharedId(String empId);

    void insertEmpSpace(EmpSpaceVO empSpace);

    void makeShared(EmpSpaceVO empSpace);

    void makeUnshare(EmpSpaceVO empSpace);

    void updateEmpSpace(EmpSpaceVO empSpace);
}
