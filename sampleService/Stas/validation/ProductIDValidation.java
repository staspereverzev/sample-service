package com.sysco.sampleService.Stas.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = {ProductIDValidation.ValidatorProductID.class})

public @interface ProductIDValidation {

    String message() default "ProductID must be numeric";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    class ValidatorProductID implements ConstraintValidator<ProductIDValidation, String>{

        private static final Pattern VALID_PATTERN = Pattern.compile("^[0-9]+$");

        @Override
        public boolean isValid(String productID, ConstraintValidatorContext context) {
            if (productID == null)
                return false;
            if (VALID_PATTERN.matcher(productID).matches()){
                return true;
            }
            else return false;
        }
    }
}
