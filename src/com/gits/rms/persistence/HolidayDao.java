
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.HolidayVO;

public interface HolidayDao {

    List getAllHoliday();

    HolidayVO getHoliday(Integer id);

    void insertHoliday(HolidayVO holiday);

    void updateHoliday(HolidayVO holiday);

    void deleteHoliday(HolidayVO holiday);

    List holidaySearchResult(HolidayVO holiday);
}
