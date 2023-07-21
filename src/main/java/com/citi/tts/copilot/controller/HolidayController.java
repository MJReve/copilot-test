package com.citi.tts.copilot.controller;

import com.citi.tts.copilot.model.CountryHolidayData;
import com.citi.tts.copilot.model.Holiday;
import com.citi.tts.copilot.model.Response;
import com.citi.tts.copilot.model.UpdateHolidayRequest;
import com.citi.tts.copilot.repository.Holidays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class HolidayController {

    @Autowired
    private Holidays holidays;

    // get all holidays
    @GetMapping("/holidays")
    @ResponseBody
    public String getAllHolidays() {
        return Response.of(holidays.getAllHolidays()).toJson();
    }

    @PostMapping("/holidays")
    public String createHoliday(@RequestBody Holiday holiday) {
        holidays.createHoliday(holiday);
        return Response.of(holiday).toJson();
    }

    @PostMapping("/holidays/{country-code}/{holiday-date}")
    public String updateHoliday(
            @PathVariable("country-code") String countryCode, @PathVariable("holiday-date") String holidayDate,
            @RequestBody UpdateHolidayRequest request) {

        return Response.of(holidays.updateHoliday(countryCode, holidayDate, request)).toJson();
    }

    @DeleteMapping("/holidays/{country-code}/{holiday-date}")
    public String deleteHoliday(
            @PathVariable("country-code") String countryCode, @PathVariable("holiday-date") String holidayDate) {

        return Response.of(holidays.deleteHoliday(countryCode, holidayDate)).toJson();
    }


    /**
     * Query 3 (return holiday details): Get holiday by holiday date
     *
     * @param holidayDate
     * @return
     */
    // create a method to return holiday by holiday date
    @GetMapping("/holidays/{holiday-date}")
    @ResponseBody
    public String getHolidayByDate(@PathVariable("holiday-date") String holidayDate) {
        List<Holiday> holidayByDate = holidays.getHolidayByDate(holidayDate);
        return Response.of(holidayByDate).toJson();
    }

    /**
     * Query 3 (return whether holiday for all countries): Get holiday by holiday date
     *
     * @param holidayDate
     * @return
     */
    @GetMapping("/holidays/{holiday-date}/countries")
    @ResponseBody
    public String checkHolidayForAllCountries(@PathVariable("holiday-date") String holidayDate) {
        List<String> countries = holidays.getAllCountries();
        List<Holiday> holidayByDate = holidays.getHolidayByDate(holidayDate);
        // get country list in holidayByDate
        List<String> countriesInHolidayByDate = holidayByDate.stream()
                .map(Holiday::getCountryCode)
                .toList();
        CountryHolidayData data = new CountryHolidayData();
        for (String country : countries) {
            if (countriesInHolidayByDate.contains(country)) {
                data.addIsHoliday(country);
            } else {
                data.addNotHoliday(country);
            }

        }

        return Response.of(data).toJson();
    }

    /**
     * Query 1: Get next year's holidays
     *
     * @return
     */
    @GetMapping("/holidays/next-year")
    @ResponseBody
    public String getNextYearHolidays() {
        LocalDate date = LocalDate.now();
        int year = date.getYear() + 1;
        return Response.of(holidays.getHolidaysByYear(year)).toJson();
    }

    /**
     * Query 2: Get next holiday by country code
     * @param countryCode
     * @return
     */
    @GetMapping("/holidays/{country-code}/next")
    @ResponseBody
    public String getNextHolidayByCountryCode(@PathVariable("country-code") String countryCode) {
        LocalDate date = LocalDate.now();
        Holiday nextHoliday = holidays.getNextHolidayFromDateForCountry(countryCode, date);
        return Response.of(nextHoliday).toJson();
    }

}
