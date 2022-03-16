package com.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodTruck {
    private long location_id;
    private String applicant;
    private String facility_type;
    private String status;
    private String location_description;
    private String food_items;
    private double latitude;
    private double longitude;
    private long zipcode;
}
