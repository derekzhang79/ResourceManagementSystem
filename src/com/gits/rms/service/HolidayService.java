
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.HolidayVO;

public interface HolidayService {

    void deleteHoliday(HolidayVO holiday);

    List getAllHoliday();

    HolidayVO getHoliday(Integer id);

    List holidaySearchResult(HolidayVO holiday);

    void insertHoliday(HolidayVO holyday);

    void updateHoliday(HolidayVO holyday);
}
