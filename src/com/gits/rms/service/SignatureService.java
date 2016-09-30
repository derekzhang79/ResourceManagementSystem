
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.SignatureVO;

public interface SignatureService {

    void deleteSignature(SignatureVO signature);

    List getAllSignature();

    List getAllSignatureForEmp(SignatureVO signature);

    List getAllSignatureForLoginEmp();

    List<SignatureVO> getEmployeeAllSignature(Integer employeeId);

    SignatureVO getSignature(Integer id);

    SignatureVO getSignatureEmployee(Integer id);

    void insertSignature(SignatureVO signature);

    List signatureSearchResult(SignatureVO signature);

    void updateSignature(SignatureVO signature);
}
