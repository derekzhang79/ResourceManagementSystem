
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.EmpUSTaxVO;

public interface EmpUSTaxService {

    void deleteEmpUSTax(EmpUSTaxVO empUSTax);

    List getAllEmpUSTax();

    EmpUSTaxVO getEmpUSTax(Integer id);

    void insertEmpUSTax(EmpUSTaxVO empUSTax);

    void updateEmpUSTax(EmpUSTaxVO empUSTax);

    List usTaxSearchResult(EmpUSTaxVO empUSTax);
}
