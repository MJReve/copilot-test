package com.citi.tts.copilot.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CountryHolidayData {

    private Set<String> isHoliday = new HashSet<>();
    private Set<String> notHoliday = new HashSet<>();

    public void addIsHoliday(String countryCode) {
        isHoliday.add(countryCode);
    }

    public void addNotHoliday(String countryCode) {
        notHoliday.add(countryCode);
    }

    // toJson string
    public String toJson() {
        return "{\"isHoliday\":" + setToJson(isHoliday) + ",\"notHoliday\":" + setToJson(notHoliday) + "}";
    }

    private String setToJson(Set<String> set) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (String s : set) {
            sb.append("\"");
            sb.append(s);
            sb.append("\"");
            sb.append(",");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }


    @Override
    public String toString() {
        return toJson();
    }
}
