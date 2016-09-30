
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.LocationDao;
import com.gits.rms.persistence.LocationHibernateDao;
import com.gits.rms.vo.LocationVO;

public class LocationDaoService implements LocationService {
    private LocationDao dao;

    public LocationDaoService() {
        dao = new LocationHibernateDao();
    }

    @Override
    public List checkLocationInEmpLocationHistory(LocationVO loc) {
        return dao.checkLocationInEmpLocationHistory(loc);
    }

    @Override
    public void deleteLocation(LocationVO loc) {
        dao.deleteLocation(loc);
    }

    @Override
    public List getAllLocation() {
        return dao.getAllLocation();
    }

    @Override
    public LocationVO getLocation(Integer id) {
        return dao.getLocation(id);
    }

    @Override
    public void insertLocation(LocationVO loc) {
        dao.insertLocation(loc);
    }

    @Override
    public List locationSearchResult(LocationVO loc) {
        return dao.locationSearchResult(loc);
    }

    @Override
    public void updateLocation(LocationVO loc) {
        dao.updateLocation(loc);
    }
}
