package com.citi.tts.copilot.repository;

import com.citi.tts.copilot.model.Holiday;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HolidaysTest {

    private Holidays holidays;

    @BeforeEach
    public void setUp() {
        // then
        holidays = new Holidays();
    }

    @Test
    public void get_holiday_by_specific_date() {
        // given
        String date = "2020-10-01";
        Holiday expected = new Holiday("CN", "China", "2020-10-01", "National Day");
        // when
        List<Holiday> result = holidays.getHolidayByDate(date);
        // then
        // assert result has size 1 and contains a holiday with holidayDate 2020-10-01
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(expected, result.get(0));
    }

    @Test
    public void get_holiday_in_multiple_countries_by_date() {
        // given
        String date = "2020-01-01";
        Holiday expectedCn = new Holiday("CN", "China", "2020-01-01", "New Year's Day");
        Holiday expectedUs = new Holiday("US", "United States", "2020-01-01", "New Year's Day");
        // when
        List<Holiday> result = holidays.getHolidayByDate(date);
        // then
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(expectedCn));
        Assertions.assertTrue(result.contains(expectedUs));
    }

    @Test
    public void get_holiday_not_found() {
        // given
        String date = "2020-10-02";
        // when
        List<Holiday> result = holidays.getHolidayByDate(date);
        // then
        Assertions.assertEquals(0, result.size());
    }

}
