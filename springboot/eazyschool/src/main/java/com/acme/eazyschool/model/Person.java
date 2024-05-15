package com.acme.eazyschool.model;

import com.acme.eazyschool.annotations.FieldsValueMatch;
import com.acme.eazyschool.annotations.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

// Add getters and setters implicitly
@Getter
@Setter
@Entity                         // map variables to column names
@FieldsValueMatch.List({@FieldsValueMatch(field = "password", fieldMatch = "confirmPassword", message = "Password do not match"), @FieldsValueMatch(field = "email", fieldMatch = "confirmEmail", message = "Email addresses do not match")})
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
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
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
    @NotBlank(message = "Password must not be blank")
    @Size(min = 5, message = "Password must be at least 3 characters long")
    @PasswordValidator(message = "Password does not meet requirements annotation")
    private String password;

    // Confirm Password
    @NotBlank(message = "Confirm Password must not be blank")
    @Size(min = 5, message = "Confirm must be at least 3 characters long")
    @Transient                                                  // Don't persist field
    private String confirmPassword;

    // Foreign key relationship establishment
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    // Add an extra column in the table which will be the foreign key to the referenced column
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;

    // Foreign key relationship establishment
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    private Roles role;

    // Foreign key relationship
    // Indicates many persons can refer to a single class instance
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = true)
    private EazyClass eazyClass;

    // Courses relationship
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    // Name of Intermediate table to enforce many-to-many relationships
    @JoinTable(name = "person_courses",
            joinColumns = {
                    @JoinColumn(name = "person_id", referencedColumnName = "personId")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
            }
    )
    private Set<Courses> courses = new HashSet<>();
}