package com.sysco.sampleService.Stas.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Max;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.util.Arrays.asList;

@Target({FIELD, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = {SellerIDValidation.ValidatorSellerID.class})
public @interface SellerIDValidation {

    String message() default "SellerID must be CABL or USBL";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    public class ValidatorSellerID implements ConstraintValidator<SellerIDValidation, String>{
        @Override
        public boolean isValid(String sellerID, ConstraintValidatorContext context) {
            if (sellerID == null){
                return false;
            }
            if (asList("CABL", "USBL").contains(sellerID.toUpperCase())) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}
