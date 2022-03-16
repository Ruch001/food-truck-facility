package com.assessment.controller;

import com.assessment.CsvHelper.CSVHelper;
import com.assessment.model.FoodTruck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class FoodTruckController {

    @GetMapping("/getAllDetails")
    public List<FoodTruck> getAllDetails() throws FileNotFoundException {
        return CSVHelper.csvRead();
    }

    @GetMapping("/getByName")
    public List<FoodTruck> getDetailsByName(@RequestParam String name) throws FileNotFoundException {
        List<FoodTruck> foodTrucks = CSVHelper.csvRead();
        List<FoodTruck> result = new ArrayList<>();
        for (FoodTruck foodTruck : foodTrucks) {
            if (foodTruck.getApplicant().equalsIgnoreCase(name)) {
                result.add(foodTruck);
            }
        }
        return result;
    }

    @GetMapping("/getByFoodItem")
    public List<FoodTruck> getDetailsByFoodItem(@RequestParam String foodItem) throws FileNotFoundException {
        List<FoodTruck> foodTrucks = CSVHelper.csvRead();
        List<FoodTruck> res = new ArrayList<>();
        for (FoodTruck foodTruck : foodTrucks) {
            if (foodTruck.getFood_items().toLowerCase(Locale.ROOT).contains(foodItem)) {
                res.add(foodTruck);
            }
        }
        return res;
    }

    @GetMapping("/getByZip")
    public List<FoodTruck> getDetailsByZip(@RequestParam long zipcode) throws FileNotFoundException {
        List<FoodTruck> foodTrucks = CSVHelper.csvRead();
        List<FoodTruck> res = new ArrayList<>();
        for (FoodTruck foodTruck : foodTrucks) {
            if (foodTruck.getZipcode() == zipcode) {
                res.add(foodTruck);
            }
        }
        return res;
    }

    @GetMapping("/getNearbyByCoordinates")
    public List<FoodTruck> getDetailsOfNearbyTrucks(@RequestParam double latitude, @RequestParam double longitude)
            throws FileNotFoundException {
        List<FoodTruck> foodTrucks = CSVHelper.csvRead();
        List<FoodTruck> res = new ArrayList<>();
        for (FoodTruck foodTruck : foodTrucks) {
            double latDiff = Math.abs(foodTruck.getLatitude() - latitude);
            double longDiff = Math.abs(foodTruck.getLongitude() - longitude);
            if (latDiff <= 0.01 && longDiff <= 0.01) {
                res.add(foodTruck);
            }
        }
        return res;
    }
}