
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.HolidayDao;
import com.gits.rms.persistence.HolidayHibernateDao;
import com.gits.rms.vo.HolidayVO;

public class HolidayDaoService implements HolidayService {

    private HolidayDao dao;

    public HolidayDaoService() {
        dao = new HolidayHibernateDao();
    }

    @Override
    public void deleteHoliday(HolidayVO child) {
        dao.deleteHoliday(child);
    }

    @Override
    public List getAllHoliday() {
        return dao.getAllHoliday();
    }

    @Override
    public HolidayVO getHoliday(Integer id) {
        return dao.getHoliday(id);
    }

    @Override
    public List holidaySearchResult(HolidayVO holiday) {
        return dao.holidaySearchResult(holiday);
    }

    @Override
    public void insertHoliday(HolidayVO child) {
        dao.insertHoliday(child);
    }

    @Override
    public void updateHoliday(HolidayVO child) {
        dao.updateHoliday(child);
    }
}
