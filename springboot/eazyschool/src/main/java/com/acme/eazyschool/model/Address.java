package com.acme.eazyschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Address extends BaseEntity {
    @Id                                 // Act as primary key
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    // Auto increment based on DB increment feat
    @GenericGenerator(name = "native")
    @Column(name = "address_id")
    private String addressId;

    // Address1
    @NotBlank(message = "Address1 must not be blank")
    @Size(min = 5, message = "Address1 must be at least 5 characters long")
    private String address1;

    // Address2
    @Size(min = 5, message = "Address1 must be at least 5 characters long")
    private String address2;

    // City
    @NotBlank(message = "City must not be blank")
    @Size(min = 5, message = "City must be at least 5 characters long")
    private String city;

    // State
    @NotBlank(message = "State must not be blank")
    @Size(min = 5, message = "State must be at least 5 characters long")
    private String state;

    // Zip code
    @NotBlank(message = "Zip code must not be blank")
    @Pattern(regexp = "(^$|[0-9]{5})", message = "Zip must be 10 digits")
    private String zipCode;
}
