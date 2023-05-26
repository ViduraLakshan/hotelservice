package com.backend.hotelservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid=UUID.randomUUID();
    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null")
    @ApiModelProperty(notes = "Name for register Hotel", example = "hilltan")
    private String name;
    @NotBlank(message = "location cannot be blank")
    @NotNull(message = "location cannot be null")
    @ApiModelProperty(notes = "location of Hotel", example = "colombo")
    private String location;
    @ManyToOne
    @JoinColumn(name = "propertyOwner", nullable = false)
    @JoinColumn(name = "propertyOwner")
    @JsonIgnore
    private PropertyOwner propertyOwner;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property",fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<RoomDetails> roomDetails;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property",fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<RoomBookDetails> roomBookDetails;
}
