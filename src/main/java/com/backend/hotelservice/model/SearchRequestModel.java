package com.backend.hotelservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestModel {

    private String location;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int NumberOfAdult;
    private int NumberOfChildren;
}
