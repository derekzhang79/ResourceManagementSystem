
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.RegionDao;
import com.gits.rms.persistence.RegionHibernateDao;
import com.gits.rms.vo.RegionVO;

public class RegionDaoService implements RegionService {
    private RegionDao dao;

    public RegionDaoService() {
        dao = new RegionHibernateDao();
    }

    @Override
    public List checkRegionInClient(RegionVO reg) {
        return dao.checkRegionInClient(reg);
    }

    @Override
    public List checkRegionInEmployees(RegionVO reg) {
        return dao.checkRegionInEmployees(reg);
    }

    @Override
    public List checkRegionInEmpUsTax(RegionVO reg) {
        return dao.checkRegionInEmpUsTax(reg);
    }

    @Override
    public List checkRegionInLocation(RegionVO reg) {
        return dao.checkRegionInLocation(reg);
    }

    @Override
    public void deleteRegion(RegionVO reg) {
        dao.deleteRegion(reg);
    }

    @Override
    public List getAllRegion() {
        return dao.getAllRegion();
    }

    @Override
    public RegionVO getRegion(Integer id) {
        return dao.getRegion(id);
    }

    @Override
    public void insertRegion(RegionVO reg) {
        dao.insertRegion(reg);
    }

    @Override
    public List<RegionVO> loadRegion(Integer zipcode) {
        return dao.loadRegion(zipcode);
    }

    @Override
    public List regionSearchResult(RegionVO reg) {
        return dao.regionSearchResult(reg);
    }

    @Override
    public void updateRegion(RegionVO reg) {
        dao.updateRegion(reg);
    }
}
