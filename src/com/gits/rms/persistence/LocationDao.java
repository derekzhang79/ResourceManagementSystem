
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.LocationVO;

public interface LocationDao {
    List getAllLocation();

    LocationVO getLocation(Integer id);

    void updateLocation(LocationVO loc);

    void insertLocation(LocationVO loc);

    void deleteLocation(LocationVO loc);

    List locationSearchResult(LocationVO loc);

    List checkLocationInEmpLocationHistory(LocationVO loc);
}
