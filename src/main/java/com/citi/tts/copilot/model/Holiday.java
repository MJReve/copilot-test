package com.citi.tts.copilot.model;

public class Holiday {
    String countryCode;
    String countryDesc;
    String holidayDate;
    String holidayName;

    public Holiday(String countryCode, String countryDesc, String holidayDate, String holidayName) {
        this.countryCode = countryCode;
        this.countryDesc = countryDesc;
        this.holidayDate = holidayDate;
        this.holidayName = holidayName;
    }

    public String key() {
        return countryCode + ":" +  holidayDate;
    }

    @Override
    public String toString() {
        return toJson();
    }

    // output to csv string
    public String toCSV() {
        return countryCode + "," + countryDesc + "," + holidayDate + "," + holidayName + "\n";
    }

    // to Json string
    public String toJson() {
        return "{\"countryCode\":\"" + countryCode + "\",\"countryDesc\":\"" + countryDesc + "\",\"holidayDate\":\"" + holidayDate + "\",\"holidayName\":\"" + holidayName + "\"}";
    }

    // equals method
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Holiday h)) {
            return false;
        }
        return h.countryCode.equals(countryCode) && h.countryDesc.equals(countryDesc) && h.holidayDate.equals(holidayDate) && h.holidayName.equals(holidayName);
    }

    public String getHolidayDate() {
        return holidayDate;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
