
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.VendorVO;

public interface VendorService {
    void deleteVendor(VendorVO vendor);

    List getAllVendor();

    List getAllVendorExceptInitialVendorName(String vendorName);

    VendorVO getVendor(Integer id);

    void insertVendor(VendorVO vendor);

    void updateVendor(VendorVO vendor);

    List vendorSearchResult(VendorVO vendor);

}
