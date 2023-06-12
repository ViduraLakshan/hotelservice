package com.backend.hotelservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "UserName cannot be blank")
    @NotNull(message = "UserName cannot be null")
    @ApiModelProperty(notes = "UserName for bookPackage", example = "jone")
    private String userName;
    @NotBlank(message = "phoneNo cannot be blank")
    @NotNull(message = "phoneNo cannot be null")
    @ApiModelProperty(notes = "phoneNo for bookPackage", example = "+94771234567")
    private String phoneNo;
    //    private String displayName;
    @NotBlank(message = "email cannot be blank")
    @NotNull(message = "email cannot be null")
    @ApiModelProperty(notes = "email for bookPackage", example = "jone1234@gmail.com")
    private String email;
    @NotNull(message = "checkIn date cannot be null")
    @ApiModelProperty(notes = "checkIn date for bookPackage", example = "2022-12-01")
    private LocalDate checkIn;
    @NotNull(message = "checkOut date cannot be null")
    @ApiModelProperty(notes = "checkOut date for bookPackage", example = "2022-12-02")
    private LocalDate checkOut;
    @NotNull(message = "totalStay cannot be null")
    @ApiModelProperty(notes = "totalStay for bookPackage", example = "2")
    private int totalStay;
    @NotNull(message = "arrivalTime cannot be null")
    @ApiModelProperty(notes = "totalStay for bookPackage", example = "8:00")
    private LocalTime arrivalTime;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "roomDetails", nullable = false)
    private RoomDetails roomDetails;
    @ManyToOne
    @JoinColumn(name = "property", nullable = false)
    @JsonIgnore
    private Property property;
}