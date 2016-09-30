
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.RegionVO;

public interface RegionDao {
    List getAllRegion();

    RegionVO getRegion(Integer id);

    void updateRegion(RegionVO reg);

    void insertRegion(RegionVO reg);

    void deleteRegion(RegionVO reg);

    List loadRegion(Integer zipcode);

    List regionSearchResult(RegionVO reg);

    List checkRegionInClient(RegionVO reg);

    List checkRegionInLocation(RegionVO reg);

    List checkRegionInEmployees(RegionVO reg);

    List checkRegionInEmpUsTax(RegionVO reg);
}
