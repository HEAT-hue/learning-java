package com.acme.eazyschool.annotations;

import java.lang.annotation.*;

/*
 * To perform content match on two fields
 * */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {

    /*
     * Define the elements to be used in your annotation
     * */
    // Default message to be used if validation fails
    String message() default "Fields value don't match";

    String field();

    String fieldMatch();

    Class<?>[] groups() default {};

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}
