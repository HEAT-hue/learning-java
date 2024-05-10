package com.acme.eazyschool.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data       // Generate getters and setters
@Entity     // Map field names to table columns i.e. Entity is known as a table
public class Roles extends BaseEntity {

    @Id                                 // Act as primary key
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    // Auto increment based on DB increment feat
    @GenericGenerator(name = "native")
    @Column(name = "role_id")           // Map role name
    private int roleId;              // role ID

    @Column(name = "role_name")
    private String roleName;            // Role name
}
