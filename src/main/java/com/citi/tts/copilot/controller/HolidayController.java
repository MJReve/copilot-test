package com.citi.tts.copilot.controller;

import com.citi.tts.copilot.model.Holiday;
import com.citi.tts.copilot.model.Response;
import com.citi.tts.copilot.model.UpdateHolidayRequest;
import com.citi.tts.copilot.repository.Holidays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * Query 3: Get holiday by holiday date
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

}
