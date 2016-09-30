
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.LocationVO;

public interface LocationService {
    List checkLocationInEmpLocationHistory(LocationVO loc);

    void deleteLocation(LocationVO loc);

    List getAllLocation();

    LocationVO getLocation(Integer id);

    void insertLocation(LocationVO loc);

    List locationSearchResult(LocationVO loc);

    void updateLocation(LocationVO loc);
}
