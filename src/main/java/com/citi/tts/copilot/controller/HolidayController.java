package com.citi.tts.copilot.controller;

import com.citi.tts.copilot.model.Holiday;
import com.citi.tts.copilot.model.Response;
import com.citi.tts.copilot.repository.Holidays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HolidayController {

    @Autowired
    private Holidays holidays;


    /**
     * Query 3: Get holiday by holiday date
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
