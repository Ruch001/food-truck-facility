package com.assessment.CsvHelper;

import com.assessment.model.FoodTruck;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    public static List<FoodTruck> csvRead() throws FileNotFoundException {

        String path = "src/main/java/com/assessment/CsvHelper/Mobile_Food_Facility_Permit.csv";
        FileInputStream file = new FileInputStream(path);
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<FoodTruck> foodTrucks = new ArrayList<FoodTruck>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                FoodTruck foodTruck = new FoodTruck(
                        Long.parseLong(csvRecord.get("locationid")),
                        csvRecord.get("Applicant"),
                        csvRecord.get("FacilityType"),
                        csvRecord.get("Status"),
                        csvRecord.get("LocationDescription"),
                        csvRecord.get("FoodItems"),
                        Double.parseDouble(csvRecord.get("Latitude")),
                        Double.parseDouble(csvRecord.get("Longitude")),
                        csvRecord.get("zip Codes").equals("") ? 0 : Long.parseLong(csvRecord.get("zip Codes"))
                );
                foodTrucks.add(foodTruck);
            }
            return foodTrucks;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}