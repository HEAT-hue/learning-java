package com.acme.eazyschool.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

/*
 * This gets values from properties file or any other location
 * */
@Component("eazySchoolProps")       // declare bean
@ConfigurationProperties(prefix = "eazyschool")     // set configuration prefix for retrieving properties
@NoArgsConstructor                 // Empty constructor
@Getter
@Setter
@Validated
public class EazySchoolProps {

    // Get default page size
    @Min(value = 5, message = "Page size must be between 5 and 25")
    @Max(value = 25, message = "Page size must be between 5 and 25")
    private short pageSize;

    // Get mapped contacts
    private Map<String, String> contact;

    private List<String> branches;
}