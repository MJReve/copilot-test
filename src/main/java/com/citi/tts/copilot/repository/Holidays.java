package com.citi.tts.copilot.repository;

import com.citi.tts.copilot.model.Holiday;
import com.citi.tts.copilot.model.UpdateHolidayRequest;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Holidays {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Holidays.class);

    // create a map containing holidays
    // key is country code and holiday date
    // value is holiday
    private Map<String, Holiday> holidays = new HashMap<>();

    public Holidays() {
        loadHolidays();
    }

    private void loadHolidays() {
        try {
            List<String> holidayLines = Files.readAllLines((Path.of("src/main/resources/holiday.csv")));
            // skip first line
            holidayLines.remove(0);
            for (String holidayLine : holidayLines) {
                String[] holidayFields = holidayLine.split(",");
                Holiday holiday = new Holiday(holidayFields[0], holidayFields[1], holidayFields[2], holidayFields[3]);
                holidays.put(holiday.key(), holiday);
            }
        } catch (IOException e) {
            logger.error("Error reading holidays.csv file", e);
        }
        logger.info("Holidays loaded: " + holidays.size());
    }

    // create a method to return holiday by holiday date
    public List<Holiday> getHolidayByDate(String holidayDate) {
        // stream of holiday keys
        // filter holiday keys by holiday date
        // map holiday keys to holidays
        // collect holidays to a list
        return holidays.keySet().stream()
                .filter(holidayKey -> holidayKey.endsWith(holidayDate))
                .map(holidayKey -> holidays.get(holidayKey))
                .toList();
    }

    synchronized public Holiday createHoliday(Holiday holiday) {
        // validate uniqueness of holiday
        if (holidays.containsKey(holiday.key())) {
            throw new RuntimeException("Holiday already exists");
        }
        holidays.put(holiday.key(), holiday);
        return holiday;
    }

    public List<Holiday> getAllHolidays() {
        // return all holidays
        return holidays.values().stream().toList();
    }

    synchronized public Holiday updateHoliday(String countryCode, String holidayDate, UpdateHolidayRequest request) {
        // create holiday key
        String holidayKey = countryCode + ":" + holidayDate;
        // find holiday by country code and holiday date
        // if holiday not found, throw exception
        if (!holidays.containsKey(holidayKey)) {
            throw new RuntimeException("Holiday not found");
        }
        // update holiday
        Holiday holiday = new Holiday(countryCode, request.getCountryDesc(), holidayDate, request.getHolidayName());
        holidays.put(holidayKey, holiday);
        return holiday;
    }

    synchronized public Holiday deleteHoliday(String countryCode, String holidayDate) {
        // create holiday key
        String holidayKey = countryCode + ":" + holidayDate;
        // find holiday by country code and holiday date
        // if holiday not found, throw exception
        if (!holidays.containsKey(holidayKey)) {
            throw new RuntimeException("Holiday not found");
        }
        return holidays.remove(holidayKey);
    }
}
