
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.SignatureVO;

public interface SignatureDao {

    List getAllSignature();

    SignatureVO getSignature(Integer id);

    SignatureVO getSignatureEmployee(Integer id);

    void insertSignature(SignatureVO signature);

    void updateSignature(SignatureVO signature);

    void deleteSignature(SignatureVO signature);

    List signatureSearchResult(SignatureVO signature);

    List<SignatureVO> getEmployeeAllSignature(Integer employeeId);

    List getAllSignatureForEmp(SignatureVO signature);

    List getAllSignatureForLoginEmp();
}
