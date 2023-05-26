package com.backend.hotelservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column(unique = true)
    private String uid;
    @NotBlank(message = "UserName cannot be blank")
    @NotNull(message = "UserName cannot be null")
    @ApiModelProperty(notes = "UserName for register user", example = "jone")
    private String userName;
    @NotBlank(message = "phoneNo cannot be blank")
    @NotNull(message = "phoneNo cannot be null")
    @ApiModelProperty(notes = "phoneNo for register user", example = "+94771234567")
    private String phoneNo;
    //    private String displayName;
    @NotBlank(message = "email cannot be blank")
    @NotNull(message = "email cannot be null")
    @ApiModelProperty(notes = "email for register user", example = "jone1234@gmail.com")
    private String email;
    @NotNull(message = "gender cannot be null")
    @NotBlank(message = "gender cannot be blank")
    @ApiModelProperty(notes = "gender for register user", example = "Male")
    private String gender;
    @NotNull(message = "address cannot be null")
    @NotBlank(message = "address cannot be blank")
    @ApiModelProperty(notes = "address for register user", example = "kandy")
    private String address;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "userId")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "roleId") })
    private Set<Role> roles;
}