package com.backend.hotelservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.backend.hotelservice.model.Availability;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid=UUID.randomUUID();
    private String roomType;
    @Enumerated(EnumType.ORDINAL)
    private Availability availability;
    @NotNull(message = "price cannot be null")
    @ApiModelProperty(notes = "price for RoomDetails", example = "+94771234567")
    private LocalDate whenAvailable;
    private int numberOfBed;
    @NotNull(message = "price cannot be null")
    @ApiModelProperty(notes = "price for RoomDetails", example = "2000")
    private float price;
    @Enumerated(EnumType.ORDINAL)
    //@Column(columnDefinition = " default 'NO'")
    private Availability acAvailability;
    @Enumerated(EnumType.ORDINAL)
    // @Column(length = 32, columnDefinition = "varchar(32) default 'NO'")
    private Availability attachedBathroom;
    @Enumerated(EnumType.ORDINAL)
    // @Column(length = 32, columnDefinition = "varchar(32) default 'NO'")
    private Availability patio;
    @Enumerated(EnumType.ORDINAL)
    // @Column(length = 32, columnDefinition = "varchar(32) default 'NO'")
    private Availability tvAvailability;
    @Enumerated(EnumType.ORDINAL)
    // @Column(length = 32, columnDefinition = "varchar(32) default 'NO'")
    private Availability dvdAvailability;
    @Enumerated(EnumType.ORDINAL)
    //@Column(length = 32, columnDefinition = "varchar(32) default 'NO'")
    private Availability wifiAvailability;
    private String size;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "property", nullable = false)
    private Property property;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomDetails",fetch = FetchType.LAZY)
    private List<RoomBookDetails> roomBookDetails;
}

