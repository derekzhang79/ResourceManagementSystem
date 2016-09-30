
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.VendorVO;

public interface VendorDao {
    List getAllVendor();

    List getAllVendorExceptInitialVendorName(String vendorName);

    VendorVO getVendor(Integer id);

    List vendorSearchResult(VendorVO vendor);

    void insertVendor(VendorVO vendor);

    void updateVendor(VendorVO vendor);

    void deleteVendor(VendorVO vendor);

}
