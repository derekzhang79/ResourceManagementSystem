
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.VendorDao;
import com.gits.rms.persistence.VendorHibernateDao;
import com.gits.rms.vo.VendorVO;

public class VendorDaoService implements VendorService {

    private VendorDao dao;

    public VendorDaoService() {
        dao = new VendorHibernateDao();
    }

    @Override
    public void deleteVendor(VendorVO vendor) {
        dao.deleteVendor(vendor);
    }

    @Override
    public List getAllVendor() {
        return dao.getAllVendor();
    }

    @Override
    public List getAllVendorExceptInitialVendorName(String vendorName) {
        return dao.getAllVendorExceptInitialVendorName(vendorName);
    }

    @Override
    public VendorVO getVendor(Integer id) {
        return dao.getVendor(id);
    }

    @Override
    public void insertVendor(VendorVO vendor) {
        dao.insertVendor(vendor);
    }

    @Override
    public void updateVendor(VendorVO vendor) {
        dao.updateVendor(vendor);
    }

    @Override
    public List vendorSearchResult(VendorVO vendor) {
        return dao.vendorSearchResult(vendor);
    }

}
