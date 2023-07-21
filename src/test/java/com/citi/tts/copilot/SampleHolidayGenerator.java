package com.citi.tts.copilot;

import com.citi.tts.copilot.model.Holiday;

import java.io.FileWriter;
import java.io.IOException;

public class SampleHolidayGenerator {
    // generate sample data with 20 records for multiple countries
    public static Holiday[] getSampleData() {
        Holiday[] holidays = new Holiday[17];
        holidays[0] = new Holiday("US", "United States", "2020-01-01", "New Year's Day");
        holidays[1] = new Holiday("US", "United States", "2020-01-20", "Martin Luther King Jr. Day");
        holidays[2] = new Holiday("US", "United States", "2020-02-17", "Presidents' Day");
        holidays[3] = new Holiday("US", "United States", "2020-05-25", "Memorial Day");
        holidays[4] = new Holiday("US", "United States", "2020-07-03", "Independence Day");
        holidays[5] = new Holiday("US", "United States", "2020-09-07", "Labor Day");
        holidays[6] = new Holiday("US", "United States", "2020-10-12", "Columbus Day");
        holidays[7] = new Holiday("US", "United States", "2020-11-11", "Veterans Day");
        holidays[8] = new Holiday("US", "United States", "2020-11-26", "Thanksgiving Day");
        holidays[9] = new Holiday("US", "United States", "2020-12-25", "Christmas Day");
        holidays[10] = new Holiday("US", "United States", "2021-01-01", "New Year's Day");
        holidays[11] = new Holiday("US", "United States", "2021-01-18", "Martin Luther King Jr. Day");
        holidays[12] = new Holiday("US", "United States", "2021-02-15", "Presidents' Day");
        holidays[13] = new Holiday("US", "United States", "2021-05-31", "Memorial Day");
        holidays[14] = new Holiday("US", "United States", "2021-07-05", "Independence Day");
        holidays[15] = new Holiday("US", "United States", "2021-09-06", "Labor Day");
        holidays[16] = new Holiday("US", "United States", "2021-10-11", "Columbus Day");
        return holidays;
    }

    // 生成20条记录的样本数据 (Copilot not generated, manually added)
    public static Holiday[] getSampleData2() {
        Holiday[] holidays = new Holiday[6];
        String countryCode = "CN";
        String countryDesc = "China";
        holidays[0] = new Holiday(countryCode, countryDesc, "2020-01-01", "New Year's Day");
        holidays[1] = new Holiday(countryCode, countryDesc, "2020-05-01", "Labor Day");
        holidays[2] = new Holiday(countryCode, countryDesc, "2020-10-01", "National Day");
        holidays[3] = new Holiday(countryCode, countryDesc, "2021-01-01", "New Year's Day");
        holidays[4] = new Holiday(countryCode, countryDesc, "2021-05-01", "Labor Day");
        holidays[5] = new Holiday(countryCode, countryDesc, "2021-10-01", "National Day");
        return holidays;
    }

    // generate sample data in CSV format
    public static String getSampleDataCSV() {
        String csv = "countryCode,countryDesc,holidayDate,holidayName\n";
        Holiday[] sampleData = getSampleData();
        csv += arrayToCsv(sampleData);
        csv += arrayToCsv(getSampleData2());
        return csv;
    }

    private static String arrayToCsv(Holiday[] sampleData) {
        StringBuilder csv = new StringBuilder();
        for (Holiday holiday : sampleData) {
            csv.append(holiday.toCSV());
        }
        return csv.toString();
    }

    // output in csv file
    public static void main(String[] args) throws IOException {
         FileWriter writer = new FileWriter("src/main/resources/holiday.csv");
         writer.write(getSampleDataCSV());
         writer.close();
    }
}
