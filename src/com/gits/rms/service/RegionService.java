
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.RegionVO;

public interface RegionService {
    List checkRegionInClient(RegionVO reg);

    List checkRegionInEmployees(RegionVO reg);

    List checkRegionInEmpUsTax(RegionVO reg);

    List checkRegionInLocation(RegionVO reg);

    void deleteRegion(RegionVO reg);

    List getAllRegion();

    RegionVO getRegion(Integer id);

    void insertRegion(RegionVO reg);

    List<RegionVO> loadRegion(Integer zipcode);

    List regionSearchResult(RegionVO reg);

    void updateRegion(RegionVO reg);
}
