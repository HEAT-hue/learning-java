package com.acme.eazyschool.model;

import com.acme.eazyschool.annotations.FieldsValueMatch;
import com.acme.eazyschool.annotations.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data                           // Add getters and setters implicitly
@Entity                         // map variables to column names
@FieldsValueMatch.List({
        @FieldsValueMatch(field = "password", fieldMatch = "confirmPassword", message = "Password do not match"),
        @FieldsValueMatch(field = "email", fieldMatch = "confirmEmail", message = "Email addresses do not match")
})
public class Person extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private int personId;

    // Name
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    // Mobile number
    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    // Email address
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    // Confirm Email address
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    @Transient                                                    // Don't persist this field
    private String confirmEmail;

    // Password
    @NotBlank(message = "Name must not be blank")
    @Size(min = 5, message = "Name must be at least 3 characters long")
    @PasswordValidator(message = "Password does not meet requirements annotation")
    private String password;

    // Password
    @NotBlank(message = "Name must not be blank")
    @Size(min = 5, message = "Name must be at least 3 characters long")
    @Transient                                                  // Don't persist field
    private String confirmPassword;
}