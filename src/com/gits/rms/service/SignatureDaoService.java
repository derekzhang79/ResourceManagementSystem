
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.SignatureDao;
import com.gits.rms.persistence.SignatureHibernateDao;
import com.gits.rms.vo.SignatureVO;

public class SignatureDaoService implements SignatureService {

    private SignatureDao dao;

    public SignatureDaoService() {
        dao = new SignatureHibernateDao();
    }

    @Override
    public void deleteSignature(SignatureVO signature) {
        dao.deleteSignature(signature);
    }

    @Override
    public List getAllSignature() {
        return dao.getAllSignature();
    }

    @Override
    public List getAllSignatureForEmp(SignatureVO signature) {
        return dao.getAllSignatureForEmp(signature);
    }

    @Override
    public List getAllSignatureForLoginEmp() {
        return dao.getAllSignatureForLoginEmp();
    }

    @Override
    public List<SignatureVO> getEmployeeAllSignature(Integer employeeId) {
        return dao.getEmployeeAllSignature(employeeId);
    }

    @Override
    public SignatureVO getSignature(Integer id) {
        return dao.getSignature(id);
    }

    @Override
    public SignatureVO getSignatureEmployee(Integer id) {
        return dao.getSignatureEmployee(id);
    }

    @Override
    public void insertSignature(SignatureVO signature) {
        dao.insertSignature(signature);
    }

    @Override
    public List signatureSearchResult(SignatureVO signature) {
        return dao.signatureSearchResult(signature);
    }

    @Override
    public void updateSignature(SignatureVO signature) {
        dao.updateSignature(signature);
    }
}
