package com.acme.eazyschool.annotations;

import com.acme.eazyschool.validations.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)      // Business logic of annotation
@Target({ElementType.METHOD, ElementType.FIELD})                // Where the annotation is applicable
@Retention(RetentionPolicy.RUNTIME)                             // Annotation to be executed at runtime
public @interface PasswordValidator {

    /*
     * Annotation elements specified here
     * */
    String message() default "Please choose a strong password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}