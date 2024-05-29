package com.acme.eazyschool.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
 * This class serves to update respective Person and Address Tables
 * */
@Data
public class Profile {
    //    private String profileId;
    // Name
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    // Mobile number
    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
    private String mobileNumber;

    // Email address
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    // Email address
    @NotBlank(message = "Address1 must not be blank")
    @Size(min = 5, message = "Address1 must be at least 3 characters long")
    private String address1;

    private String address2;

    // City
    @NotBlank(message = "City must not be blank")
    @Size(min = 5, message = "City must be at least 5 characters long")
    private String City;

    // City
    @NotBlank(message = "State must not be blank")
    @Size(min = 5, message = "State must be at least 5 characters long")
    private String state;

    // Mobile number
    @NotBlank(message = "Zip code must not be blank")
    @Pattern(regexp = "(^$|[0-9]{5})", message = "Zip code must be digits")
    private String zipCode;
}
