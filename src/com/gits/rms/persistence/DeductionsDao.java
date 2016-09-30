
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.DeductionsVO;

public interface DeductionsDao {

    List checkDeductionInPaystubDeduction(DeductionsVO deduction);

    List deductionSearchResult(DeductionsVO deduction);

    void deleteDeduction(DeductionsVO deduction);

    List getAllDeductions();

    DeductionsVO getDeduction(Integer id);

    List getDeductionList(Integer id);

    void insertDeduction(DeductionsVO deduction);

    void updateDeduction(DeductionsVO deduction);
}
