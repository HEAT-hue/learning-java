package com.acme.eazyschool.validations;

import com.acme.eazyschool.annotations.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/*
 * PasswordValidator - annotation name for which ConstraintValidator is applicable
 * String - Target field type
 * */
public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {
    // Commonly guessed passwords list
    private static final String[] COMMON_PASSWORDS = {"password", "123456", "qwerty",
            // Add more common passwords as needed
    };

    private static boolean isWeakPassword(String password) {
        // Check if password is too short
        if (password.length() < 5) {
            return true;
        }

        // Check if password contains only alphabetic characters
        if (password.matches("[a-zA-Z]+")) {
            return true;
        }

        // Check if password contains only numeric characters
        if (password.matches("[0-9]+")) {
            return true;
        }

        // Check if password is in the list of common passwords
        for (String commonPassword : COMMON_PASSWORDS) {
            if (password.equalsIgnoreCase(commonPassword)) {
                return true;
            }
        }

        return false;
    }

    //  Validate weak passwords
    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext ctx) {
        return null != passwordField && (!isWeakPassword(passwordField));
    }
}
